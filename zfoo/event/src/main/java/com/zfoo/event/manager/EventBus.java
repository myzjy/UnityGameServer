/*
 * Copyright (C) 2020 The zfoo Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package com.zfoo.event.manager;

import com.zfoo.event.model.event.IEvent;
import com.zfoo.event.model.vo.IEventReceiver;
import com.zfoo.protocol.collection.CollectionUtils;
import com.zfoo.protocol.collection.concurrent.CopyOnWriteHashMapLongObject;
import com.zfoo.protocol.util.AssertionUtils;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.util.SafeRunnable;
import com.zfoo.util.ThreadUtils;
import com.zfoo.util.math.RandomUtils;
import io.netty.util.concurrent.FastThreadLocalThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author godotg
 * @version 3.0
 */
public abstract class EventBus {

    private static final Logger logger = LoggerFactory.getLogger(EventBus.class);

    /**
     * EN: The size of the thread pool. Event's thread pool is often used to do time-consuming operations, so set it a little bigger
     * CN: 线程池的大小. event的线程池经常用来做一些耗时的操作，所以要设置大一点
     */
    public static final int EXECUTORS_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    private static final ExecutorService[] executors = new ExecutorService[EXECUTORS_SIZE];

    private static final CopyOnWriteHashMapLongObject<ExecutorService> threadMap = new CopyOnWriteHashMapLongObject<>(EXECUTORS_SIZE);
    /**
     * Synchronous event mapping, synchronize observers
     */
    private static final Map<Class<? extends IEvent>, List<IEventReceiver>> receiverMapSync = new HashMap<>();
    /**
     * Asynchronous event mapping, asynchronous observer
     */
    private static final Map<Class<? extends IEvent>, List<IEventReceiver>> receiverMapAsync = new HashMap<>();

    static {
        for (int i = 0; i < executors.length; i++) {
            var namedThreadFactory = new EventThreadFactory(i);
            var executor = Executors.newSingleThreadExecutor(namedThreadFactory);
            executors[i] = executor;
        }
    }

    /**
     * Publish the event
     */
    public static void submit(IEvent event) {
        syncSubmit(event);
        asyncSubmit(event);
    }

    /**
     * EN: Synchronously publish an event that runs in the current thread (only receiverMapSync process the event)
     * CN: 同步抛出一个事件，会在当前线程中运行(只有同步观察者会处理事件)
     */
    public static void syncSubmit(IEvent event) {
        var list = receiverMapSync.get(event.getClass());
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        doSubmit(event, list);
    }

    /**
     * EN: Asynchronously publish an event, and the event is not processed in the current thread (only receiverMapAsync process the event)
     * CN: 异步抛出一个事件，事件不在同一个线程中处理(只有异步观察者会处理事件)
     */
    public static void asyncSubmit(IEvent event) {
        var list = receiverMapAsync.get(event.getClass());
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        executors[Math.abs(event.threadId() % EXECUTORS_SIZE)].execute(() -> doSubmit(event, list));
    }

    /**
     * Use the event thread specified by the hashcode to execute the task
     */
    public static void execute(int hashcode, Runnable runnable) {
        executors[Math.abs(hashcode % EXECUTORS_SIZE)].execute(SafeRunnable.valueOf(runnable));
    }

    public static void asyncExecute(Runnable runnable) {
        execute(RandomUtils.randomInt(), runnable);
    }

    /**
     * The observer executes the method call
     */
    private static void doSubmit(IEvent event, List<IEventReceiver> receiverList) {
        for (var receiver : receiverList) {
            try {
                receiver.invoke(event);
            } catch (Exception e) {
                logger.error("eventBus unknown exception", e);
            } catch (Throwable t) {
                logger.error("eventBus unknown error", t);
            }
        }
    }

    /**
     * Register the event and its counterpart observer
     */
    public static void registerEventReceiver(Class<? extends IEvent> eventType, IEventReceiver receiver, boolean asyncFlag) {
        if (asyncFlag) {
            receiverMapAsync.computeIfAbsent(eventType, it -> new LinkedList<>()).add(receiver);
        } else {
            receiverMapSync.computeIfAbsent(eventType, it -> new LinkedList<>()).add(receiver);
        }
    }

    public static class EventThreadFactory implements ThreadFactory {
        private final int poolNumber;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final ThreadGroup group;

        public EventThreadFactory(int poolNumber) {
            this.group = ThreadUtils.currentThreadGroup();
            this.poolNumber = poolNumber;
        }

        @Override
        public Thread newThread(Runnable runnable) {
            var threadName = StringUtils.format("event-p{}-t{}", poolNumber + 1, threadNumber.getAndIncrement());
            var thread = new FastThreadLocalThread(group, runnable, threadName);
            thread.setDaemon(false);
            thread.setPriority(Thread.NORM_PRIORITY);
            thread.setUncaughtExceptionHandler((t, e) -> logger.error(t.toString(), e));
            var executor = executors[poolNumber];
            AssertionUtils.notNull(executor);
            threadMap.put(thread.getId(), executor);
            return thread;
        }
    }

    public static Executor threadExecutor(long currentThreadId) {
        return threadMap.getPrimitive(currentThreadId);
    }
}



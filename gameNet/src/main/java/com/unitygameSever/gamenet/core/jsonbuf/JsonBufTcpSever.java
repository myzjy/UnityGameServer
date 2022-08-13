package com.unitygameSever.gamenet.core.jsonbuf;

import com.zfoo.net.core.AbstractServer;
import com.zfoo.util.net.HostAndPort;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author zhangjingyi
 * @version 1.0
 * @since 2022/8/13 20:26
 */
public class JsonBufTcpSever extends AbstractServer {

    public JsonBufTcpSever(HostAndPort host) {
        super(host);
    }

    @Override
    public ChannelInitializer<SocketChannel> channelChannelInitializer() {
        return null;
    }
    public static class ChannelHandlerInitializer extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel channel) throws Exception {
            channel.pipeline().addLast(new IdleStateHandler(0,0,180));

        }
    }
}

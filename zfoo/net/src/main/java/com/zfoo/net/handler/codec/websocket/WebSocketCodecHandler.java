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

package com.zfoo.net.handler.codec.websocket;

import com.zfoo.net.NetContext;
import com.zfoo.net.packet.DecodedPacketInfo;
import com.zfoo.net.packet.EncodedPacketInfo;
import com.zfoo.net.packet.PacketService;
import com.zfoo.protocol.util.IOUtils;
import com.zfoo.protocol.util.JsonUtils;
import com.zfoo.protocol.util.StringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * header(4byte) + protocolId(2byte) + packet
 * header = body(bytes.length) + protocolId.length(2byte)
 *
 * @author godotg
 * @version 3.0
 */
public class WebSocketCodecHandler extends MessageToMessageCodec<WebSocketFrame, EncodedPacketInfo> {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketCodecHandler.class);

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) {
        ByteBuf in = webSocketFrame.content();

        // 不够读一个int
        if (in.readableBytes() <= PacketService.PACKET_HEAD_LENGTH) {
            return;
        }

        in.markReaderIndex();
        var length = in.readInt();

        // 如果长度非法，则抛出异常断开连接，按照自己的使用场景指定合适的长度，防止客户端发送超大包占用带宽
        if (length < 0 || length > IOUtils.BYTES_PER_MB) {
            throw new IllegalArgumentException(StringUtils.format("illegal packet [length:{}]", length));
        }

        // ByteBuf里的数据太小
        if (in.readableBytes() < length) {
            in.resetReaderIndex();
            return;
        }

        ByteBuf tmpByteBuf = null;
        try {
            tmpByteBuf = in.readRetainedSlice(length);
            DecodedPacketInfo packetInfo = NetContext.getPacketService().read(tmpByteBuf);
            list.add(packetInfo);
        } catch (Exception e) {
            logger.error("exception异常", e);
            throw e;
        } catch (Throwable t) {
            logger.error("throwable错误", t);
            throw t;
        } finally {
            ReferenceCountUtil.release(tmpByteBuf);
        }
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, EncodedPacketInfo out, List<Object> list) {
        try {
            var byteBuf = channelHandlerContext.alloc().ioBuffer();

            NetContext.getPacketService().write(byteBuf, out.getPacket(), out.getAttachment());
            list.add(new BinaryWebSocketFrame(byteBuf));
        } catch (Exception e) {
            logger.error("[{}]编码exception异常", JsonUtils.object2String(out), e);
            throw e;
        } catch (Throwable t) {
            logger.error("[{}]编码throwable错误", JsonUtils.object2String(out), t);
            throw t;
        }
    }


}

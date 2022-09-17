package com.gameSever.gamenet.core.jsonbuf;

import com.zfoo.net.core.AbstractServer;
import com.zfoo.net.handler.ServerRouteHandler;
import com.zfoo.protocol.util.IOUtils;
import com.zfoo.util.net.HostAndPort;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

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
        return new ChannelHandlerInitializer();
    }
    public static class ChannelHandlerInitializer extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel channel) throws Exception {
            channel.pipeline().addLast(new HttpServerCodec(8 * IOUtils.BYTES_PER_KB, 16 * IOUtils.BYTES_PER_KB, 16 * IOUtils.BYTES_PER_KB));
            // 聚合解码 HttpRequest/HttpContent/LastHttpContent 到 FullHttpRequest
            // 保证接收的 Http 请求的完整性
            channel.pipeline().addLast(new HttpObjectAggregator(16 * IOUtils.BYTES_PER_MB));

            // 处理其他的 WebSocketFrame
            channel.pipeline().addLast(new WebSocketServerProtocolHandler("/"));
            // 写文件内容，支持异步发送大的码流，一般用于发送文件流
            channel.pipeline().addLast(new ChunkedWriteHandler());
            // 编解码WebSocketFrame二进制协议
            channel.pipeline().addLast(new JsonCodecHandler());
            channel.pipeline().addLast(new ServerRouteHandler());
        }
    }
}

package com.unitygameSever.gamenet.core.jsonbuf;

import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.zfoo.net.NetContext;
import com.zfoo.net.handler.codec.tcp.TcpCodecHandler;
import com.zfoo.net.packet.model.DecodedPacketInfo;
import com.zfoo.net.packet.model.EncodedPacketInfo;
import com.zfoo.net.router.attachment.AttachmentType;
import com.zfoo.net.router.attachment.IAttachment;
import com.zfoo.net.util.SessionUtils;
import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.ProtocolManager;
import com.zfoo.protocol.buffer.ByteBufUtils;
import com.zfoo.protocol.collection.ArrayUtils;
import com.zfoo.protocol.registration.ProtocolRegistration;
import com.zfoo.protocol.registration.anno.Compatible;
import com.zfoo.protocol.registration.field.IFieldRegistration;
import com.zfoo.protocol.serializer.reflect.ISerializer;
import com.zfoo.protocol.util.JsonUtils;
import com.zfoo.protocol.util.ReflectionUtils;
import com.zfoo.storage.strategy.JsonToMapConverter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.util.TypeUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/9/11 0:46
 */
public class JsonCodecHandler extends MessageToMessageCodec<WebSocketFrame, EncodedPacketInfo> {
    private static final Logger logger = LoggerFactory.getLogger(JsonCodecHandler.class);


    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {
        ByteBuf in = webSocketFrame.content();
        String mess = in.toString(CharsetUtil.UTF_8);
        logger.info(mess);
        var bytes = ByteBufUtils.readAllBytes(in);

        ByteBuf tmpByteBuf = Unpooled.wrappedBuffer(bytes);
//        try {
        DecodedPacketInfo packetInfo = read(tmpByteBuf);
        list.add(packetInfo);
        ReferenceCountUtil.release(tmpByteBuf);
//        }
    }

    public static Object stringToTarget(String string, String t) throws Exception {
        boolean nullOrEmpty = StringUtils.isEmpty(string);

        if (double.class.toString().equals(t)) {
            return nullOrEmpty ? 0 : Double.parseDouble(string);
        } else if (long.class.toString().equals(t)) {
            return nullOrEmpty ? 0 : Long.parseLong(string);
        } else if (int.class.toString().equals(t)) {
            return nullOrEmpty ? 0 : Integer.parseInt(string);
        } else if (float.class.toString().equals(t)) {
            return nullOrEmpty ? 0 : Float.parseFloat(string);
        } else if (short.class.toString().equals(t)) {
            return nullOrEmpty ? 0 : Short.parseShort(string);
        } else if (boolean.class.toString().equals(t)) {
            return nullOrEmpty ? 0 : Boolean.parseBoolean(string);
        }
//        else if (Number.class.equals(t)) {
//            return t.getConstructor(String.class).newInstance(nullOrEmpty ? "0" : string);
//        }
        else {
            return nullOrEmpty ? "" : string;
        }
    }

    public static DecodedPacketInfo read(ByteBuf buffer) throws Exception {
        String mess = buffer.toString(CharsetUtil.UTF_8);
        var jsonMap = JsonUtils.getJsonMap(mess);
        var protocolId = Short.valueOf(jsonMap.get("PROTOCOL_ID"));
        var protocolRegistration = ProtocolManager.getProtocol(protocolId);
        Object object = ReflectionUtils.newInstance(protocolRegistration.protocolConstructor());
        var protocolClass = protocolRegistration.protocolConstructor().getDeclaringClass();
        var keySet = jsonMap.keySet();
        for (var item : keySet) {
            if (item == "PROTOCOL_ID") {
                continue;
            }
//            jsonMap.
            Field field = protocolClass.getDeclaredField(item);
            if (field.isAnnotationPresent(Compatible.class) && !buffer.isReadable()) {
                break;
            }
            field.setAccessible(true);
            var ty = field.getGenericType();
            String fieldValue = jsonMap.get(field.getName());

            var value = stringToTarget(fieldValue, ty.getTypeName());
            ReflectionUtils.setField(field, object, value);
        }

//        for (int i = 0, length = jsonMap.size(); i < length; i++) {
//            Field field = protocolClass.getFields();
//            // 协议向后兼容
//            if (field.isAnnotationPresent(Compatible.class) && !buffer.isReadable()) {
//                break;
//            }
//            Object fieldValue = jsonMap.get(field.getName());
//            ReflectionUtils.setField(field, object, fieldValue);
//        }
//        protocolClass.
        IPacket packet = new IPacket() {
            @Override
            public short protocolId() {
                return protocolId;
            }
        };
//        var protobufCodec = ProtobufProxy.create(protocolClass);
//        var bytes = ByteBufUtils.readAllBytes(buffer);

        DecodedPacketInfo decodedPacketInfo = DecodedPacketInfo.valueOf((IPacket) object, null);
        return decodedPacketInfo;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, EncodedPacketInfo encodedPacketInfo, List<Object> list) throws Exception {
        try {
            var byteBuf = channelHandlerContext.alloc().ioBuffer();

            NetContext.getPacketService().write(byteBuf, encodedPacketInfo.getPacket(), encodedPacketInfo.getAttachment());
            list.add(new BinaryWebSocketFrame(byteBuf));
        } catch (Exception e) {
            logger.error("[{}]编码exception异常", JsonUtils.object2String(encodedPacketInfo), e);
            throw e;
        } catch (Throwable t) {
            logger.error("[{}]编码throwable错误", JsonUtils.object2String(encodedPacketInfo), t);
            throw t;
        }
    }


}

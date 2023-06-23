package com.gameServer.gateway;

import com.gameServer.commonRefush.constant.TankDeployEnum;
import com.gameServer.commonRefush.protocol.login.GetPlayerInfoRequest;
import com.gameServer.commonRefush.protocol.login.LoginRequest;
import com.gameServer.commonRefush.protocol.login.LoginTapToStartRequest;
import com.zfoo.net.core.gateway.JsonWebsocketGatewayServer;
import com.zfoo.net.core.json.JsonWebsocketServer;
import com.zfoo.net.session.Session;
import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.util.JsonUtils;
import com.zfoo.util.net.HostAndPort;
import com.zfoo.util.net.NetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.function.BiFunction;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/4/19 17 18
 */
public class Application {
//    static {
//        TankDeployEnum.InitDefaultEnv();
//    }

    /*
     * TCP
     * */
    public static final int WEBSOCKET_SERVER_PORT = 15000;
    //    public static final int WEBSOCKET_SERVER_PORT=5000;
    public static final HostAndPort GATEWAY_HOST_AND_PORT = HostAndPort.valueOf(NetUtils.getLocalhostStr(), WEBSOCKET_SERVER_PORT);
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static final BiFunction<Session, IPacket, Boolean> packetFilter = (session, packet) -> {
        if (packet.protocolId() == LoginRequest.PROTOCOL_ID) {
            if (session.getUid() <= 0) {
                return false;
            } else {
                return true;
            }
        }
        if (packet.protocolId() == GetPlayerInfoRequest.getProtocolId()) {
            logger.info("[session:{}发送了GetPlayerInfo[{}]", session, packet);
            return false;
        }

        var uid = session.getUid();
        if (uid <= 0) {
            logger.error("[session:{}发送了错误的包，因为没有登录或者是非法包[packet:{}]]", session, JsonUtils.object2String(packet));
            return true;
        }
      
        return false;
    };

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");
        context.registerShutdownHook();
        //webSocket服务器
        var websocketServer = new JsonWebsocketGatewayServer(HostAndPort.valueOf(NetUtils.getLocalhostStr(), WEBSOCKET_SERVER_PORT), packetFilter);
        websocketServer.start();
    }
}

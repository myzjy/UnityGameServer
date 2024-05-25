import com.gameServer.common.constant.TankDeployEnum;
import com.zfoo.protocol.ProtocolManager;
import com.zfoo.protocol.generate.GenerateOperation;
import com.zfoo.protocol.serializer.CodeLanguage;
import com.zfoo.protocol.util.ClassUtils;
import com.zfoo.protocol.util.DomUtils;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.protocol.xml.XmlProtocols;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/11/20 1:45
 */
@Ignore
public class ApplicationTest {
    static {
        TankDeployEnum.InitDefaultEnv();
    }
    /**
     * 将表放到数据库中
     */
    @Test
    public void ApplyDataMain() {
   
    }
    public static final String generateProtocolPath = "D:\\xiangmu\\UnityGameServer\\ampieta-game-java-common\\src\\main\\resources";
    public static final String protocolLocation = "protocol.xml";
    @Test
    public void generateAllProtocols() throws IOException {
        // 生成协议
        var xmlProtocols = DomUtils.inputStream2Object(ClassUtils.getFileFromClassPath(protocolLocation), XmlProtocols.class);
        var operation = new GenerateOperation();
        operation.setProtocolPath(generateProtocolPath);
        operation.setFoldProtocol(true);
        operation.getGenerateLanguages().add(CodeLanguage.Lua);
        ProtocolManager.initProtocol(xmlProtocols, operation);
        var count = 0;
        for (int i = 0; i < ProtocolManager.MAX_PROTOCOL_NUM; i++) {
            if (ProtocolManager.protocols[i] != null) {
                count++;
            }
        }
        System.out.println(StringUtils.format("导出协议成功，导出个数[{}]", count));
    }
}

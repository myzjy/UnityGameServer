using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class LoginTapToStartRequest : IPacket
    {
        

        public static LoginTapToStartRequest ValueOf()
        {
            var packet = new LoginTapToStartRequest();
            
            return packet;
        }


        public short ProtocolId()
        {
            return 1013;
        }
    }


    public class LoginTapToStartRequestRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 1013;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {
            LoginTapToStartRequest message = (LoginTapToStartRequest) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);
        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);
            var packet = JsonConvert.DeserializeObject<>(packetJson.ToString());

            return packet;
        }
    }
}

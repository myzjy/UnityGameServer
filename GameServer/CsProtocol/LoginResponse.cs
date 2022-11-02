using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class LoginResponse : IPacket
    {
        public string token;
        public long uid;
        public string userName;

        public static LoginResponse ValueOf(string token, long uid, string userName)
        {
            var packet = new LoginResponse();
            packet.token = token;
            packet.uid = uid;
            packet.userName = userName;
            return packet;
        }


        public short ProtocolId()
        {
            return 1001;
        }
    }


    public class LoginResponseRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 1001;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {

            LoginResponse message = (LoginResponse) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);

        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);
            var packet = JsonConvert.DeserializeObject<buffer.WriteString(message.token);
            buffer.WriteLong(message.uid);
            buffer.WriteString(message.userName);>(packetJson.ToString());

            return packet;
        }
    }
}

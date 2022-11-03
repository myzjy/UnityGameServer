using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class LoginRequest : IPacket
    {
        public string account;
        public string password;

        public static LoginRequest ValueOf(string account, string password)
        {
            var packet = new LoginRequest();
            packet.account = account;
            packet.password = password;
            return packet;
        }


        public short ProtocolId()
        {
            return 1000;
        }
    }


    public class LoginRequestRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 1000;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {

            LoginRequest message = (LoginRequest) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);

        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);

            buffer.WriteString(message.account);
            buffer.WriteString(message.password); packet = JsonConvert.DeserializeObject<LoginRequest>(packetJson.ToString());

            return packet;
        }
    }
}

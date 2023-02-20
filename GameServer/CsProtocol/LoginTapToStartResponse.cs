using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class LoginTapToStartResponse : IPacket
    {
        public string message;
        public bool accessGame;

        public static LoginTapToStartResponse ValueOf(bool accessGame, string message)
        {
            var packet = new LoginTapToStartResponse();
            packet.accessGame = accessGame;
            packet.message = message;
            return packet;
        }


        public short ProtocolId()
        {
            return 1014;
        }
    }


    public class LoginTapToStartResponseRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 1014;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {
            LoginTapToStartResponse message = (LoginTapToStartResponse) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);
        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);
            var packet = JsonConvert.DeserializeObject<buffer.WriteBool(message.accessGame);
            buffer.WriteString(message.message);>(packetJson.ToString());

            return packet;
        }
    }
}

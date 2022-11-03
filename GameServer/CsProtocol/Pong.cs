using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class Pong : IPacket
    {
        public long time;

        public static Pong ValueOf(long time)
        {
            var packet = new Pong();
            packet.time = time;
            return packet;
        }


        public short ProtocolId()
        {
            return 104;
        }
    }


    public class PongRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 104;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {

            Pong message = (Pong) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);

        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);

            buffer.WriteLong(message.time); packet = JsonConvert.DeserializeObject<Pong>(packetJson.ToString());

            return packet;
        }
    }
}

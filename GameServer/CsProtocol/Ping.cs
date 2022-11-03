using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class Ping : IPacket
    {
        

        public static Ping ValueOf()
        {
            var packet = new Ping();
            
            return packet;
        }


        public short ProtocolId()
        {
            return 103;
        }
    }


    public class PingRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 103;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {

            Ping message = (Ping) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);

        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);

             packet = JsonConvert.DeserializeObject<Ping>(packetJson.ToString());

            return packet;
        }
    }
}

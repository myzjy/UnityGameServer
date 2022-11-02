using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class Heartbeat : IPacket
    {
        

        public static Heartbeat ValueOf()
        {
            var packet = new Heartbeat();
            
            return packet;
        }


        public short ProtocolId()
        {
            return 102;
        }
    }


    public class HeartbeatRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 102;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {

            Heartbeat message = (Heartbeat) packet;
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

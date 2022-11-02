using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class PairLong : IPacket
    {
        public long key;
        public long value;

        public static PairLong ValueOf(long key, long value)
        {
            var packet = new PairLong();
            packet.key = key;
            packet.value = value;
            return packet;
        }


        public short ProtocolId()
        {
            return 111;
        }
    }


    public class PairLongRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 111;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {

            PairLong message = (PairLong) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);

        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);
            var packet = JsonConvert.DeserializeObject<buffer.WriteLong(message.key);
            buffer.WriteLong(message.value);>(packetJson.ToString());

            return packet;
        }
    }
}

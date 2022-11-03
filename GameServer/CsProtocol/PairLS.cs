using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class PairLS : IPacket
    {
        public long key;
        public string value;

        public static PairLS ValueOf(long key, string value)
        {
            var packet = new PairLS();
            packet.key = key;
            packet.value = value;
            return packet;
        }


        public short ProtocolId()
        {
            return 113;
        }
    }


    public class PairLSRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 113;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {

            PairLS message = (PairLS) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);

        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);

            buffer.WriteLong(message.key);
            buffer.WriteString(message.value); packet = JsonConvert.DeserializeObject<PairLS>(packetJson.ToString());

            return packet;
        }
    }
}

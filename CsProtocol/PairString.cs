using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class PairString : IPacket
    {
        public string key;
        public string value;

        public static PairString ValueOf(string key, string value)
        {
            var packet = new PairString();
            packet.key = key;
            packet.value = value;
            return packet;
        }


        public short ProtocolId()
        {
            return 112;
        }
    }


    public class PairStringRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 112;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {
            PairString message = (PairString) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);
        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);
            var packet = JsonConvert.DeserializeObject<buffer.WriteString(message.key);
            buffer.WriteString(message.value);>(packetJson.ToString());

            return packet;
        }
    }
}

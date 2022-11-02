using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class TripleString : IPacket
    {
        public string left;
        public string middle;
        public string right;

        public static TripleString ValueOf(string left, string middle, string right)
        {
            var packet = new TripleString();
            packet.left = left;
            packet.middle = middle;
            packet.right = right;
            return packet;
        }


        public short ProtocolId()
        {
            return 115;
        }
    }


    public class TripleStringRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 115;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {

            TripleString message = (TripleString) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);

        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);
            var packet = JsonConvert.DeserializeObject<buffer.WriteString(message.left);
            buffer.WriteString(message.middle);
            buffer.WriteString(message.right);>(packetJson.ToString());

            return packet;
        }
    }
}

using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class TripleLSS : IPacket
    {
        public long left;
        public string middle;
        public string right;

        public static TripleLSS ValueOf(long left, string middle, string right)
        {
            var packet = new TripleLSS();
            packet.left = left;
            packet.middle = middle;
            packet.right = right;
            return packet;
        }


        public short ProtocolId()
        {
            return 116;
        }
    }


    public class TripleLSSRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 116;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {

            TripleLSS message = (TripleLSS) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);

        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);

            buffer.WriteLong(message.left);
            buffer.WriteString(message.middle);
            buffer.WriteString(message.right); packet = JsonConvert.DeserializeObject<TripleLSS>(packetJson.ToString());

            return packet;
        }
    }
}

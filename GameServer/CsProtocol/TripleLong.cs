using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class TripleLong : IPacket
    {
        public long left;
        public long middle;
        public long right;

        public static TripleLong ValueOf(long left, long middle, long right)
        {
            var packet = new TripleLong();
            packet.left = left;
            packet.middle = middle;
            packet.right = right;
            return packet;
        }


        public short ProtocolId()
        {
            return 114;
        }
    }


    public class TripleLongRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 114;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {

            TripleLong message = (TripleLong) packet;
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
            buffer.WriteLong(message.middle);
            buffer.WriteLong(message.right); packet = JsonConvert.DeserializeObject<TripleLong>(packetJson.ToString());

            return packet;
        }
    }
}

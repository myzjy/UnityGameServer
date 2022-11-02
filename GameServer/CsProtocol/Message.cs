using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class Message : IPacket
    {
        public byte module;
        public int code;
        public string message;

        public static Message ValueOf(int code, string message, byte module)
        {
            var packet = new Message();
            packet.code = code;
            packet.message = message;
            packet.module = module;
            return packet;
        }


        public short ProtocolId()
        {
            return 100;
        }
    }


    public class MessageRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 100;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {

            Message message = (Message) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);

        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);
            var packet = JsonConvert.DeserializeObject<buffer.WriteInt(message.code);
            buffer.WriteString(message.message);
            buffer.WriteByte(message.module);>(packetJson.ToString());

            return packet;
        }
    }
}

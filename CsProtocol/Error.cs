using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class Error : IPacket
    {
        public int module;
        public int errorCode;
        public string errorMessage;

        public static Error ValueOf(int errorCode, string errorMessage, int module)
        {
            var packet = new Error();
            packet.errorCode = errorCode;
            packet.errorMessage = errorMessage;
            packet.module = module;
            return packet;
        }


        public short ProtocolId()
        {
            return 101;
        }
    }


    public class ErrorRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 101;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {
            Error message = (Error) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);
        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);
            var packet = JsonConvert.DeserializeObject<buffer.WriteInt(message.errorCode);
            buffer.WriteString(message.errorMessage);
            buffer.WriteInt(message.module);>(packetJson.ToString());

            return packet;
        }
    }
}

using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class RegisterResponse : IPacket
    {
        public bool mRegister;
        public string error;

        public static RegisterResponse ValueOf(string error, bool mRegister)
        {
            var packet = new RegisterResponse();
            packet.error = error;
            packet.mRegister = mRegister;
            return packet;
        }


        public short ProtocolId()
        {
            return 1006;
        }
    }


    public class RegisterResponseRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 1006;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {
            RegisterResponse message = (RegisterResponse) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);
        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);
            var packet = JsonConvert.DeserializeObject<buffer.WriteString(message.error);
            buffer.WriteBool(message.mRegister);>(packetJson.ToString());

            return packet;
        }
    }
}

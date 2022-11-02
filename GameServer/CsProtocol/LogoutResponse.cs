using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class LogoutResponse : IPacket
    {
        public long uid;
        public long sid;

        public static LogoutResponse ValueOf(long sid, long uid)
        {
            var packet = new LogoutResponse();
            packet.sid = sid;
            packet.uid = uid;
            return packet;
        }


        public short ProtocolId()
        {
            return 1003;
        }
    }


    public class LogoutResponseRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 1003;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {

            LogoutResponse message = (LogoutResponse) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);

        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);
            var packet = JsonConvert.DeserializeObject<buffer.WriteLong(message.sid);
            buffer.WriteLong(message.uid);>(packetJson.ToString());

            return packet;
        }
    }
}

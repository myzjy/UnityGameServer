using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class GetPlayerInfoResponse : IPacket
    {
        

        public static GetPlayerInfoResponse ValueOf()
        {
            var packet = new GetPlayerInfoResponse();
            
            return packet;
        }


        public short ProtocolId()
        {
            return 1012;
        }
    }


    public class GetPlayerInfoResponseRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 1012;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {
            GetPlayerInfoResponse message = (GetPlayerInfoResponse) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);
        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);
            var packet = JsonConvert.DeserializeObject<>(packetJson.ToString());

            return packet;
        }
    }
}

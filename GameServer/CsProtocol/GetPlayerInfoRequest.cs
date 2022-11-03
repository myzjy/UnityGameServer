using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class GetPlayerInfoRequest : IPacket
    {
        public string token;

        public static GetPlayerInfoRequest ValueOf(string token)
        {
            var packet = new GetPlayerInfoRequest();
            packet.token = token;
            return packet;
        }


        public short ProtocolId()
        {
            return 1004;
        }
    }


    public class GetPlayerInfoRequestRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 1004;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {

            GetPlayerInfoRequest message = (GetPlayerInfoRequest) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);

        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);

            buffer.WriteString(message.token); packet = JsonConvert.DeserializeObject<GetPlayerInfoRequest>(packetJson.ToString());

            return packet;
        }
    }
}

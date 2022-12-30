using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class ServerConfigResponse : IPacket
    {
        public List<ItemBaseData> bagItemEntityList;

        public static ServerConfigResponse ValueOf(List<ItemBaseData> bagItemEntityList)
        {
            var packet = new ServerConfigResponse();
            packet.bagItemEntityList = bagItemEntityList;
            return packet;
        }


        public short ProtocolId()
        {
            return 1010;
        }
    }


    public class ServerConfigResponseRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 1010;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {
            ServerConfigResponse message = (ServerConfigResponse) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);
        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);
            var packet = JsonConvert.DeserializeObject<buffer.WritePacketList(message.bagItemEntityList, 201);>(packetJson.ToString());

            return packet;
        }
    }
}

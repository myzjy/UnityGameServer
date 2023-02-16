using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class AllBagItemRequest : IPacket
    {
        public int type;

        public static AllBagItemRequest ValueOf(int type)
        {
            var packet = new AllBagItemRequest();
            packet.type = type;
            return packet;
        }


        public short ProtocolId()
        {
            return 1007;
        }
    }


    public class AllBagItemRequestRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 1007;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {
            AllBagItemRequest message = (AllBagItemRequest) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);
        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);
            var packet = JsonConvert.DeserializeObject<buffer.WriteInt(message.type);>(packetJson.ToString());

            return packet;
        }
    }
}

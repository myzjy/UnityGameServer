using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class ItemBaseData : IPacket
    {
        public int id;
        public string name;
        public string icon;
        public int minNum;
        public int maxNum;
        public int type;
        public string des;

        public static ItemBaseData ValueOf(string des, string icon, int id, int maxNum, int minNum, string name, int type)
        {
            var packet = new ItemBaseData();
            packet.des = des;
            packet.icon = icon;
            packet.id = id;
            packet.maxNum = maxNum;
            packet.minNum = minNum;
            packet.name = name;
            packet.type = type;
            return packet;
        }


        public short ProtocolId()
        {
            return 201;
        }
    }


    public class ItemBaseDataRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 201;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {
            ItemBaseData message = (ItemBaseData) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);
        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);
            var packet = JsonConvert.DeserializeObject<buffer.WriteString(message.des);
            buffer.WriteString(message.icon);
            buffer.WriteInt(message.id);
            buffer.WriteInt(message.maxNum);
            buffer.WriteInt(message.minNum);
            buffer.WriteString(message.name);
            buffer.WriteInt(message.type);>(packetJson.ToString());

            return packet;
        }
    }
}

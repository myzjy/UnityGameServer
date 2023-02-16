using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    
    public class BagUserItemData : IPacket
    {
        public int _id;
        public long masterUserId;
        public int nowItemNum;
        public int itemId;
        public int quality;
        public bool itemNew;

        public static BagUserItemData ValueOf(int _id, int itemId, bool itemNew, long masterUserId, int nowItemNum, int quality)
        {
            var packet = new BagUserItemData();
            packet._id = _id;
            packet.itemId = itemId;
            packet.itemNew = itemNew;
            packet.masterUserId = masterUserId;
            packet.nowItemNum = nowItemNum;
            packet.quality = quality;
            return packet;
        }


        public short ProtocolId()
        {
            return 200;
        }
    }


    public class BagUserItemDataRegistration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return 200;
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {
            BagUserItemData message = (BagUserItemData) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);
        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);
            var packet = JsonConvert.DeserializeObject<buffer.WriteInt(message._id);
            buffer.WriteInt(message.itemId);
            buffer.WriteBool(message.itemNew);
            buffer.WriteLong(message.masterUserId);
            buffer.WriteInt(message.nowItemNum);
            buffer.WriteInt(message.quality);>(packetJson.ToString());

            return packet;
        }
    }
}

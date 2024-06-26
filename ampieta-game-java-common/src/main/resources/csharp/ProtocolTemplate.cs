using System.Collections.Generic;
using Newtonsoft.Json;
using ZJYFrameWork.Net.Core;
using ZJYFrameWork.Net.CsProtocol.Buffer;
using ZJYFrameWork.Spring.Utils;

namespace ZJYFrameWork.Net.CsProtocol
{
    {}
    public class {} : IPacket
    {
        {}

        public static {} ValueOf({})
        {
            var packet = new {}();
            {}
            return packet;
        }


        public short ProtocolId()
        {
            return {};
        }
    }


    public class {}Registration : IProtocolRegistration
    {
        public short ProtocolId()
        {
            return {};
        }

        public void Write(ByteBuffer buffer, IPacket packet)
        {
            {} message = ({}) packet;
            var _message = new ServerMessageWrite(message.ProtocolId(), message);
            var json = JsonConvert.SerializeObject(_message);
            buffer.WriteString(json);
        }

        public IPacket Read(ByteBuffer buffer)
        {
            var json = StringUtils.BytesToString(buffer.ToBytes());
            var dict = JsonConvert.DeserializeObject<Dictionary<object, object>>(json);
            dict.TryGetValue("packet", out var packetJson);
            var packet = JsonConvert.DeserializeObject<{}>(packetJson.ToString());

            return packet;
        }
    }
}

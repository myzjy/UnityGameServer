package com.gameServer.common.protocol.character;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * 创建角色 response
 *
 * @author zjy
 * @version 1.0
 * @since 2024/3/31 22 31
 */
@Protocol(id = 1048)
public class CreateCharacterResponse implements IPacket {
    /**
     * 角色 数据
     */
    private CharacterBaseData characterBaseData;

    public CharacterBaseData getCharacterBaseData() {
        return characterBaseData;
    }

    public void setCharacterBaseData(CharacterBaseData characterBaseData) {
        this.characterBaseData = characterBaseData;
    }

    public static CreateCharacterResponse valueOf() {
        return new CreateCharacterResponse();
    }
}

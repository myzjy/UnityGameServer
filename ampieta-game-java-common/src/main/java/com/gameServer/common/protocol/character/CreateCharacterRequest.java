package com.gameServer.common.protocol.character;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * 创建角色 request
 *
 * @author zjy
 * @version 1.0
 * @since 2024/3/31 22 20
 */
@Protocol(id = 1047)
public class CreateCharacterRequest implements IPacket {
    /**
     * 角色 id
     */
    private int characterId;

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public static CreateCharacterRequest valueOf() {
        return new CreateCharacterRequest();
    }
}

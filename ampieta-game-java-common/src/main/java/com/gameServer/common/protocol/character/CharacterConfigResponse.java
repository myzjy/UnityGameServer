package com.gameServer.common.protocol.character;

import com.gameServer.common.ormEntity.CharacterConfigEntity;
import com.gameServer.common.resource.CharacterConfigResource;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;
import com.zfoo.storage.anno.Id;

import java.util.List;

/**
 * 角色的基础相关信息
 *
 * @author zjy
 * @version 1.0
 * @since 2024/5/19 11 33
 */
@Protocol(id = 1053)
public class CharacterConfigResponse implements IPacket {
    /**
     * 角色相关
     */
    @Note("角色相关")
    private List<CharacterConfigData> characterConfigDataList;

    public List<CharacterConfigData> getCharacterConfigDataList() {
        return characterConfigDataList;
    }

    public void setCharacterConfigDataList(List<CharacterConfigData> characterConfigDataList) {
        this.characterConfigDataList = characterConfigDataList;
    }

    public static CharacterConfigResponse valueOf() {
        return new CharacterConfigResponse();
    }
}

package com.gameServer.common.protocol.character;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回所有角色数据 点击角色list
 *
 * @author zjy
 * @version 1.0
 * @since 2024/3/31 22 46
 */
@Protocol(id = 1046)
public class AcquireCharacterResponse implements IPacket {
    /**
     * 玩家的角色 所有数据 获取 list
     */
    private List<CharacterBaseData> characterBaseDataList = new ArrayList<>();

    public List<CharacterBaseData> getCharacterBaseDataList() {
        return characterBaseDataList;
    }

    public void setCharacterBaseDataList(List<CharacterBaseData> characterBaseDataList) {
        this.characterBaseDataList = characterBaseDataList;
    }

    public AcquireCharacterResponse valueOf() {
        return new AcquireCharacterResponse();
    }
}

package com.gameServer.common.protocol.gameMain;

import com.gameServer.common.protocol.character.CharacterBaseData;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

import java.util.List;

/**
 * 进入 GameMain 场景 获取所有角色
 * 返回数据Response
 *
 * @author zjy
 * @version 1.0
 * @since 2024/4/15 19 59
 */
@Protocol(id = 1050)
public class GetGameMainCharacterAllResponse implements IPacket {
    /**
     * 角色数据 当前玩家拥有的角色
     */
    private List<CharacterBaseData> characterBaseDataList;

    public List<CharacterBaseData> getCharacterBaseDataList() {
        return characterBaseDataList;
    }

    public void setCharacterBaseDataList(List<CharacterBaseData> characterBaseDataList) {
        this.characterBaseDataList = characterBaseDataList;
    }

    public static GetGameMainCharacterAllResponse valueOf() {
        return new GetGameMainCharacterAllResponse();
    }
}

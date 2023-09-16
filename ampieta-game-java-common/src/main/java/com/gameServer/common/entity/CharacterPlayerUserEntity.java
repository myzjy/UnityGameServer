package com.gameServer.common.entity;

import com.gameServer.common.entity.composite.CharacterUserCompositeDataID;
import com.zfoo.orm.anno.Id;
import com.zfoo.orm.model.IEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/16 23 17
 */
public class CharacterPlayerUserEntity implements IEntity<CharacterUserCompositeDataID> {
    /**
     * 角色id + uid
     */
    @Id
    private CharacterUserCompositeDataID dataID;

    @Override
    public CharacterUserCompositeDataID id() {
        return null;
    }

    /**
     * 当前角色 HP 没有加上 装备词条
     */
    private int entityHp;
    /**
     * 当前角色 最大 HP 没有加上 装备词条
     */
    private int entityMaxHp;
    /**
     *  当前角色 HP 加上 装备词条 得出来的
     */
    private int entityNowHp;
    /**
     * 当前角色 最大 HP 加上 装备词条
     */
    private int entityNowMaxHp;
    /**
     * 当前防御力
     */
    private int nowDef;

}

package com.gameServer.common.ormEntity;

import com.zfoo.orm.anno.Id;
import com.zfoo.orm.model.IEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/1/11 23 43
 */
public class WeaponsDataConfigEntity implements IEntity<Integer> {
    @Id
    protected int id;
    /**
     * 武器名字
     */
    protected String weaponName;
    /**
     * 武器类型
     */
    protected int weaponType;
    /**
     * 武器技能
     */
    protected int weaponSkills;
    /**
     * 武器1级初始值
     */
    protected String weaponInitValue;
    /**
     * 武器强化1-20级每级强化数字
     */
    protected String weaponInitProgress;
    /**
     * icon资源
     */
    protected String iconResource;
    /**
     * 武器升级到特定等21级会突破在之后会加数值
     */
    protected String weaponBreakthrough;
    /**
     * 当前武器等级最大值
     */
    protected int maxLv;
    @Override
    public Integer id() {
        return id;
    }
}

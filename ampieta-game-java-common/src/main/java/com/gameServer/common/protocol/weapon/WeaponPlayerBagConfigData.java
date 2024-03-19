package com.gameServer.common.protocol.weapon;

import com.zfoo.protocol.anno.Protocol;

/**
 * 点击背包 武器页按钮
 *
 * @author zjy
 * @version 1.0
 * @since 2024/3/19 23 49
 */
@Protocol(id = 216)
public class WeaponPlayerBagConfigData {
    /**
     * 武器 id
     */
    private int weaponId;
    /**
     * 武器品质
     */
    private int quality;
}

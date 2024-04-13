package com.gameServer.home.character.service;

import com.gameServer.common.entity.CharacterPlayerUserEntity;
import com.gameServer.common.entity.composite.CharacterUserCompositeDataID;
import com.gameServer.common.entity.composite.CharacterUserWeaponCompositeDataID;
import org.springframework.stereotype.Component;

/**
 * 角色 service
 *
 * @author zjy
 * @version 1.0
 * @since 2024/4/6 00 05
 */
@Component
public class CharacterService implements ICharacterService {
    @Override
    public CharacterUserWeaponCompositeDataID createCharacterUserWeaponCompositeDataID(int weaponId, int weaponType, long weaponOrmIndex) {
        var weaponCreateData = CharacterUserWeaponCompositeDataID.valueOf();
        weaponCreateData.setWeaponId(weaponId);
        weaponCreateData.setWeaponType(weaponType);
        weaponCreateData.setWeaponOrmIndex(weaponOrmIndex);
        return weaponCreateData;
    }

    @Override
    public CharacterPlayerUserEntity createCharacterPlayerUserEntity(CharacterUserCompositeDataID dataID, int entityHp, int entityMaxHp, int entityNowHp, int entityNowMaxHp, int entityAtk, int entityMaxAtk, int entityNowAtk, int entityNowMaxAtk, int nowDef, int leveCriticalHitChance,
                                                                     int levelElementMastery,
                                                                     int levelChargingEfficiencyOfElements, int levelCriticalHitDamage, int elementHitDamage, int elementType, CharacterUserWeaponCompositeDataID weaponCompositeDataID) {
        var entity = CharacterPlayerUserEntity.ValueOf();
        entity.setDataID(dataID);
        entity.setEntityHp(entityHp);
        entity.setEntityMaxHp(entityMaxHp);
        entity.setEntityNowHp(entityNowHp);
        entity.setEntityNowMaxHp(entityNowMaxHp);
        entity.setEntityAtk(entityAtk);
        entity.setEntityMaxAtk(entityMaxAtk);
        entity.setEntityNowAtk(entityNowAtk);
        entity.setEntityNowMaxAtk(entityNowMaxAtk);
        entity.setNowDef(nowDef);
        entity.setLeveCriticalHitChance(leveCriticalHitChance);
        entity.setLevelElementMastery(levelElementMastery);
        entity.setLevelChargingEfficiencyOfElements(levelChargingEfficiencyOfElements);
        entity.setLevelCriticalHitDamage(levelCriticalHitDamage);
        entity.setElementHitDamage(elementHitDamage);
        entity.setElementType(elementType);
        entity.setWeaponCompositeDataID(weaponCompositeDataID);
        return entity;
    }
}

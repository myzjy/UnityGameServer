package com.gameServer.gameBoot;

import com.gameServer.common.ormEntity.CharacterConfigEntity;
import com.gameServer.common.ormEntity.CharacterLevelConfigEntity;
import com.gameServer.common.resource.CharacterConfigResource;
import com.gameServer.common.resource.CharacterLevelConfigResource;
import com.zfoo.orm.OrmContext;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.storage.anno.StorageAutowired;
import com.zfoo.storage.manager.StorageInt;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/4 00 07
 */
@Component
public class CharacterOrmManager {
    @StorageAutowired
    private StorageInt<Integer, CharacterConfigResource> characterConfigResourceStorageInt;
    @StorageAutowired
    private StorageInt<Integer, CharacterLevelConfigResource> characterLevelConfigResourceStorageInt;

    public void UpdateCharacterConfigResource() {
        var dict = characterConfigResourceStorageInt.getAll();
        boolean isCreate = false;
        for (var data : dict) {
            var entity = OrmContext.getAccessor().load(data.getCId(), CharacterConfigEntity.class);
            var update = TimeUtils.timeToString(TimeUtils.now());
            if (entity == null) {
                entity = CharacterConfigEntity.valueOf();
                entity.setCreateAt(update);
                entity.setUpdateAt(update);
                isCreate = true;
            } else {
                entity.setUpdateAt(update);
                isCreate = false;
            }
            entity.setId(data.getCId());
            entity.setQuality(data.getQuality());
            entity.setCharacterId(data.getCharacterId());
            entity.setCharacterName(data.getCharacterName());
            entity.setCharacterRes(data.getCharacterRes());
            entity.setLevel1Atk(data.getLevel1Atk());
            entity.setLevel1ChargingEfficiencyOfElements(data.getLevel1ChargingEfficiencyOfElements());
            entity.setLevel1CriticalHitChance(data.getLevel1CriticalHitChance());
            entity.setLevel1CriticalHitDamage(data.getLevel1CriticalHitDamage());
            entity.setLevel1Def(data.getLevel1Def());
            entity.setLevel1HpValue(data.getLevel1HpValue());
            entity.setLevel1ElementMastery(data.getLevel1ElementMastery());
            entity.setCharacterDefaultWeaponId(data.getCharacterDefaultWeaponId());
            entity.setWeaponType(data.getWeaponType());
            entity.setLevel1NoWAtk(data.getLevel1NoWAtk());
            if (isCreate) {
                OrmContext.getAccessor().insert(entity);
            } else {
                OrmContext.getAccessor().update(entity);
            }
        }
    }

    public void UpdateCharacterLevelConfigResource() {
        var dict = characterLevelConfigResourceStorageInt.getAll();
        boolean isCreate = false;
        for (var data : dict) {
            var entity = OrmContext.getAccessor().load(data.getCId(), CharacterLevelConfigEntity.class);
            var update = TimeUtils.timeToString(TimeUtils.now());
            if (entity == null) {
                entity = CharacterLevelConfigEntity.valueOf();
                entity.setCreateAt(update);
                entity.setUpdateAt(update);
                isCreate = true;
            } else {
                entity.setUpdateAt(update);
                isCreate = false;
            }
            entity.setId(data.getCId());
            entity.setCharacterId(data.getCharacterId());
            entity.setCharacterRes(data.getCharacterRes());
            entity.setCharacterName(data.getCharacterName());
            entity.setQuality(data.getQuality());
            entity.setLevelNum(data.getLevelNum());
            entity.setLevelAtk(data.getLevelAtk());
            entity.setLevelAtkUp(data.getLevelAtkUp());
            entity.setLevelDef(data.getLevelDef());
            entity.setLevelDefUp(data.getLevelDefUp());
            entity.setLevelHpValue(data.getLevelHpValue());
            entity.setLevelHpValueUp(data.getLevelHpValueUp());
            entity.setLevelNoAtkUp(data.getLevelNoAtkUp());
            entity.setLevelNoAtk(data.getLevelNoAtk());
            entity.setLevelAtkUpNum(data.getLevelAtkUpNum());
            if (isCreate) {
                OrmContext.getAccessor().insert(entity);
            } else {
                OrmContext.getAccessor().update(entity);
            }
        }
    }
}

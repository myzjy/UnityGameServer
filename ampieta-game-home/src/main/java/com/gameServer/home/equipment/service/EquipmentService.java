package com.gameServer.home.equipment.service;

import com.gameServer.common.ormEntity.EquipmentUserDataOrmEntity;
import com.zfoo.orm.OrmContext;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/10/15 14 28
 */
@Component
public class EquipmentService implements IEquipmentService {
    /**
     * 比较 NowPlayerUserID 将 以装备 圣遗物 往前 排列
     */
    Comparator<EquipmentUserDataOrmEntity> playerUserIdComparator = new Comparator<EquipmentUserDataOrmEntity>() {
        @Override
        public int compare(EquipmentUserDataOrmEntity o1, EquipmentUserDataOrmEntity o2) {
            if (o1.getNowPlayerUserId() > o2.getNowPlayerUserId()) {
                return 1;
            } else if (o1.getNowPlayerUserId() == o2.getNowPlayerUserId()) {
                return 0;
            }
            return -1;
        }
    };

    @Override
    public List<EquipmentUserDataOrmEntity> GetAllTheUserToEquipmentUserDataOrm(long uid) {
        //查找到 传递 UID 的玩家 所有  圣遗物装备
        var list = OrmContext.getQuery(EquipmentUserDataOrmEntity.class)
                             .eq("Uid", uid).queryAll();
        //排序
        list.sort(playerUserIdComparator);
        return list;
    }
}

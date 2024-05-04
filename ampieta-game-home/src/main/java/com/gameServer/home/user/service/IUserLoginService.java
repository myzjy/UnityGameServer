package com.gameServer.home.user.service;

import com.gameServer.common.entity.AccessGameTimeEntity;
import com.gameServer.common.entity.PlayerUserEntity;
import com.gameServer.common.entity.config.ConfigResourceEntity;
import com.gameServer.common.protocol.playerUser.UserMsgInfoData;
import com.gameServer.common.resource.AccesGameTimeResource;
import com.gameServer.common.resource.ConfigResource;
import com.zfoo.orm.cache.IEntityCache;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/6/20 10 56
 */
@Component
public interface IUserLoginService {
    ConfigResourceEntity GetConfigResourceData(int lv);

    int ConfigResourceLength();

    PlayerUserEntity LoadPlayerUserEntity(long UserID);

    /**
     * 更新缓存 玩家数据缓存 同时更新数据库中的玩家数据
     *
     * @param entity 修改过之后的玩家数据
     */
    void UpdatePlayerUserEntity(PlayerUserEntity entity);

    /**
     * 插入
     *
     * @param entity
     */
    void InsertPlayerUserEntity(PlayerUserEntity entity);

    /**
     * 服务器开启时间配置 是否有
     *
     * @return 返回是有相关配置
     */
    boolean IsAcesGameTimeResource();

    /**
     * 返回服务器时间配置集合
     *
     * @return 返回服务器开始时间集合
     */
    Collection<AccesGameTimeResource> GetAccesTimeAll();

    /**
     * 玩家数据
     *
     * @return
     */
    IEntityCache<Long, PlayerUserEntity> AllPlayerUserEntityCaches();

    /**
     * 查找 数据库中 的数据
     *
     * @param itemId
     * @return
     */
    AccessGameTimeEntity FindAccessGameTimeEntity(int itemId);

    /**
     * 插入数据 保证数据完整
     *
     * @param entity
     */
    void InsertAccessGameTimeEntity(AccessGameTimeEntity entity);

    /**
     * 更新数据
     *
     * @param entity
     */
    void UpdateAccessGameTimeEntity(AccessGameTimeEntity entity);

    /**
     * 创建 客户端 返回 玩家相关信息
     *
     * @param userName          玩家名
     * @param goldNum           金币
     * @param premiumDiamondNum 付费钻石 一般充值才有，付费钻石转换成普通钻石
     * @param diamondNum        普通钻石
     * @param lv                等级
     * @param exp               经验
     * @param maxLv             最大等级
     * @param maxExp            最大经验
     * @return 返回玩家相关msg 结构体
     */
    UserMsgInfoData CreateUserMsgInfoData(String userName, long goldNum, long premiumDiamondNum, long diamondNum, int lv, int exp, int maxLv, int maxExp);
}

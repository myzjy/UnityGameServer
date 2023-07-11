package com.gameServer.home.PhysicalPower.service;

import com.gameServer.commonRefush.entity.PhysicalPowerEntity;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.commonRefush.resource.ConfigResource;
import com.zfoo.orm.OrmContext;
import com.zfoo.scheduler.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/4/16 00 41
 */
@Component
public class PhysicalPowerService implements IPhysicalPowerService {
    private static final Logger logger = LoggerFactory.getLogger(PhysicalPowerService.class);

    @Override
    public PhysicalPowerEntity FindOnePhysicalPower(long uid) {
        var data = OrmContext.getAccessor().load(uid, PhysicalPowerEntity.class);
        return data;
    }

    @Override
    public void UpdatePhysicalPowerEntityOrm(PhysicalPowerEntity entity) {
        OrmContext.getAccessor().update(entity);
    }

    @Override
    public PhysicalPowerEntity PhysicalPowerGetResidueTime(
            PhysicalPowerEntity entity,
            int differenceToTime,
            ConfigResource config,
            PlayerUserEntity userEntity) {
        var dateTime = TimeUtils.timeToString(entity.getResidueNowTime());
        int differenceToTimeNum = 0;
        if (differenceToTime > 0) {
            differenceToTimeNum = differenceToTime;
        } else {
            if (differenceToTime < 0) {
                differenceToTimeNum = 0;
            } else {
                differenceToTimeNum = 1;
            }
        }
        /**
         * 恢复时间还有
         */
        if (entity.getResidueTime() >= 0) {
            /**
             * 恢复时间 - 离线时间
             */
            logger.info("[uid:{}] 没有减少 体力恢复实时时间：{},differenceResidue:{}, 判断 过后 differenceToTime:{}",
                    userEntity.getId(),
                    dateTime,
                    entity.getResidueTime(),
                    differenceToTime);


            var differenceResidue = entity.getResidueTime() - differenceToTimeNum;
            logger.info("[uid:{}] 体力恢复实时时间：{},differenceResidue:{},differenceToTimeNum:{}",
                    userEntity.getId(),
                    dateTime,
                    differenceResidue,
                    differenceToTimeNum);
            if (differenceResidue > 0) {
                /**
                 * 恢复时间 - 离线时间 判断 恢复时间比 离线长 或者相等
                 */
                entity.setResidueNowTime(TimeUtils.now());
                entity.setResidueTime(differenceResidue);
                logger.info("[uid:{}] 设置过后 体力恢复实时时间：{},entity.getResidueTime():{},differenceToTimeNum:{}",
                        userEntity.getId(),
                        dateTime,
                        entity.getResidueTime(),
                        differenceToTimeNum);
            } else {
                /**
                 * 恢复时间 - 离线时间 判断 
                 *  恢复时间 比 离线时间短 需要进行额外判断 
                 */
                var differenceResidueSub = differenceToTimeNum - entity.getResidueTime();
                /**
                 * 离线时间 比 一点体力恢复时间还要长
                 * 用一点体力恢复时间 除以 differenceResidueSub 判断 离线可恢复体力数量
                 */
                var residueDifferenceInto = differenceResidueSub / config.getResidueTime();
                /**
                 * 先判断 当前体力加上 1 是否大于 最大体力 限制
                 */
                if (entity.getNowPhysicalPowerNum() + 1 >= entity.getMaximumStrength()) {
                    entity.setResidueTime(0);
                    entity.setNowPhysicalPowerNum(entity.getMaximumStrength());
                } else {
                    /**
                     * 当前体力 加 1 没有恢复到 最大体力
                     */
                    if (residueDifferenceInto >= 1) {
                        /**
                         * 这里其实 已经算了1点 算得是 超过1点体力的
                         *  离线时间可恢复 体力大2点
                         */
                        /**
                         * 还剩余多少
                         */
                        var _residueDifferenceInto = differenceResidueSub % config.getResidueTime();
                        var nowPhysicalPowerNum = entity.getNowPhysicalPowerNum() + 1 + residueDifferenceInto;
                        var ifNowPhysicalPowerNum = Math.min(nowPhysicalPowerNum, entity.getMaximumStrength());
                        /**
                         * 增加体力 和 最大体力 相减，还有多少 体力恢复
                         */
                        var physicalPowerNum = entity.getMaximumStrength() - nowPhysicalPowerNum;
                        if (physicalPowerNum >= 0) {

                        } else {
                            physicalPowerNum = 0;
                        }
                        entity.setNowPhysicalPowerNum(ifNowPhysicalPowerNum);
                        var residueDifferenceIntoTime = config.getResidueTime() - _residueDifferenceInto;
                        var residue = physicalPowerNum * config.getResidueTime();
                        var residueEndTime = config.getResidueTime() * 1_000L;
                        var residueEndLong = physicalPowerNum * config.getResidueTime() * 1000L;
                        entity.setResidueTime(residueDifferenceIntoTime);
                        entity.setMaximusResidueEndTime(residue);
                        entity.setMaxResidueEndTime(TimeUtils.now() + residueEndLong);
                        entity.setResidueNowTime(TimeUtils.now());
                        entity.setResidueEndTime(TimeUtils.now() + residueEndTime);
                        logger.info("[UID:{}],当前一点体力恢复结束时间：{},完全体力恢复完：{}",
                                entity.getId(),
                                TimeUtils.timeToString(entity.getResidueEndTime()),
                                TimeUtils.timeToString(entity.getMaxResidueEndTime()));
//                        entity.setMaxResidueEndTime(TimeUtils.now()+);
                    } else {
                        var residueDifferenceIntoTime = config.getResidueTime() - differenceResidueSub;
                        var residueEndTime = residueDifferenceIntoTime * 1_000L;
                        var nowTime = (entity.getMaximumStrength() - (entity.getNowPhysicalPowerNum() + 1)) * config.getResidueTime() - differenceResidueSub;
                        entity.setMaximusResidueEndTime(nowTime);
                        entity.setMaxResidueEndTime(nowTime * 1000L);
                        entity.setResidueTime(residueDifferenceIntoTime);
                        entity.setNowPhysicalPowerNum(entity.getNowPhysicalPowerNum() + 1);
                        entity.setResidueNowTime(TimeUtils.now());
                        entity.setResidueEndTime(TimeUtils.now() + residueEndTime);
                        logger.info("[UID:{}],当前一点体力恢复结束时间：{},完全体力恢复完：{}",
                                entity.getId(),
                                TimeUtils.timeToString(entity.getResidueEndTime()),
                                TimeUtils.timeToString(entity.getMaxResidueEndTime()));
                    }
                }
            }
        } else {
            logger.error("[uid:{}] 一点体力恢复时间：{}", userEntity.getId(), entity.getResidueTime());
            /**
             * 数据库总存放恢复时间为负数
             * 应该是之前恢复体力的时候出现问题
             * 计算出现问题导致 
             * 走到这一步 是需要进行修复
             */
            if (entity.getNowPhysicalPowerNum() >= 0) {
                /* *
                 * 当前体力是否大于 或者等于 最大体力
                 * 如果是就必须给予限制
                 */
                if (entity.getNowPhysicalPowerNum() >= entity.getMaximumStrength()) {
                    logger.info("[uid:{}],当前体力：{}， 最大体力：{}",
                            userEntity.getId(), entity.getNowPhysicalPowerNum(), entity.getMaximumStrength());
                    entity.setNowPhysicalPowerNum(entity.getMaximumStrength());
                    logger.info("[uid:{}],限制完的当前体力：{}， 最大体力：{}",
                            userEntity.getId(), entity.getNowPhysicalPowerNum(), entity.getMaximumStrength());
                    /* *
                     * 所有时间全部为0
                     */
                    entity.setResidueTime(0);
                    entity.setMaximusResidueEndTime(0);
                    entity.setResidueEndTime(0);
                    entity.setResidueNowTime(0);
                    logger.info("[uid:{}],时间初始化完毕", userEntity.getId());

                } else {
                    /* *
                     * 体力没满 但是进入这里代表 数据库中的恢复时间是错误的 秒数
                     */
                    var residueSum = (entity.getMaximumStrength() - entity.getNowPhysicalPowerNum()) * config.getResidueTime();
                    /**
                     * 1点体力恢复时间的 毫秒
                     */
                    var residue = config.getResidueTime() * 1_000L;
                    logger.info("[uid:{}],还有{} 体力才能恢复满,总恢复时间为{}",
                            userEntity.getId(),
                            (entity.getMaximumStrength() - entity.getNowPhysicalPowerNum()),
                            residueSum);
                    entity.setResidueNowTime(TimeUtils.now());
                    entity.setResidueTime(config.getResidueTime());
                    entity.setResidueEndTime(TimeUtils.now() + residue);
                    logger.info("[uid:{}],时间设置完毕", userEntity.getId());
                }
            } else {
                /* *
                 * 当前体力小于0 一般使用体力的时候 就给予限制，出问题一般要在扣除体力的 controller 寻找问题、
                 * 不需要在这里进行任何处理
                 */
                logger.error("[uid:{}],数据库出现问题，请检查,当前玩家数据库中体力值为负", entity.getId());
            }
        }
        userEntity.setNowPhysicalPowerNum(entity.getNowPhysicalPowerNum());
        OrmContext.getAccessor().update(userEntity);
        return entity;
    }

    @Override
    public PhysicalPowerEntity PhysicalPowerGetResidueEndTime(PhysicalPowerEntity entity,
                                                              int differenceToTime,
                                                              ConfigResource config,
                                                              PlayerUserEntity userEntity) {
        int differenceToTimeNum = 0;
        if (differenceToTime > 0) {
            differenceToTimeNum = differenceToTime;
        } else {
            if (differenceToTime < 0) {
                differenceToTimeNum = 0;
            } else {
                differenceToTimeNum = 1;
            }
        }
        /**
         * 体力完全恢复 剩余时间
         */
        if (entity.getMaximusResidueEndTime() >= 0) {
            /**
             * 恢复到最大体力 恢复时间 还有没有
             */
            var differenceResidue = entity.getMaximusResidueEndTime() - differenceToTimeNum;
            if (differenceResidue > 0) {
                /**
                 * 最大体力 还没满
                 */
                entity.setMaximusResidueEndTime(differenceResidue);
            } else {
                entity.setMaximusResidueEndTime(0);
                entity.setMaxResidueEndTime(0);
            }
        } else {
            logger.error("[uid:{}] 时间错误", userEntity.getId());
        }


        return entity;
    }

}

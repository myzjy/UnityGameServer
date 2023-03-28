package com.gameServer.commonRefush.entity;

import com.zfoo.orm.model.anno.Cache;
import com.zfoo.orm.model.anno.EntityCache;
import com.zfoo.orm.model.anno.Persister;

/**
 * 体力缓存
 * <p>
 * 记录当前1点 增长剩余时间
 * 所有体力增长结束时间
 * 所有体力增长满 的所需时间
 *
 * @author zjy
 * @version 1.0
 * @since 2023/3/29 00 30
 */
@EntityCache(cache = @Cache("tenThousand"), persister = @Persister("time30s"))
public class PhysicalPowerEntity {
}

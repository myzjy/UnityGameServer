import com.gameServer.commonRefush.entity.ItemBoxBaseEntity;
import com.gameServer.commonRefush.resource.ItemBaseCsvResource;
import com.gameServer.commonRefush.resource.StageResource;
import com.zfoo.orm.OrmContext;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.storage.model.anno.ResInjection;
import com.zfoo.storage.model.vo.Storage;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/7/26 19 40
 */
@Component
public class OrmAddManager {
    @ResInjection
    private Storage<Integer, ItemBaseCsvResource> itemBaseCsvResourceStorage;
    @ResInjection
    private Storage<Integer, StageResource> stageResourceStorage;

    /**
     * 更新 数据库中资料
     */
    public void UpdateItemBaseCsvResource() {
        var dict = itemBaseCsvResourceStorage.getAll();
        for (var data :
                dict) {
            //设置
            var entity = OrmContext.getAccessor().load(data.getId(), ItemBoxBaseEntity.class);
            var update = TimeUtils.timeToString(TimeUtils.now());

            if (entity != null ) {
                var newEntity = getItemBoxBasEntity(data, update);
                OrmContext.getAccessor().update(newEntity);
            } else {
                var newEntity = getBoxBasEntity(data, update);
                OrmContext.getAccessor().insert(newEntity);
            }
        }

    }

    private ItemBoxBaseEntity getBoxBasEntity(ItemBaseCsvResource data, String update) {
        var newEntity = ItemBoxBaseEntity.ValueOf();
        newEntity.setIcon(data.getIcon());
        newEntity.setItemId(data.getId());
        newEntity.setResources(data.getResourcePath());
        newEntity.setDes(data.getDes());
        newEntity.setName(data.getName());
        newEntity.setMaxNum(data.getMaxNum());
        newEntity.setMinNum(data.getMinNum());
        newEntity.setQuality(data.getQuality());
        newEntity.setType(data.getType());
        newEntity.setCreateAt(update);
        newEntity.setUpdateAt(update);
        return newEntity;
    }

    private ItemBoxBaseEntity getItemBoxBasEntity(ItemBaseCsvResource data, String update) {
        var newEntity = ItemBoxBaseEntity.ValueOf();
        newEntity.setItemId(data.getId());
        newEntity.setIcon(data.getIcon());
        newEntity.setResources(data.getResourcePath());
        newEntity.setDes(data.getDes());
        newEntity.setName(data.getName());
        newEntity.setMaxNum(data.getMaxNum());
        newEntity.setMinNum(data.getMinNum());
        newEntity.setQuality(data.getQuality());
        newEntity.setType(data.getType());
        newEntity.setUpdateAt(update);
        return newEntity;
    }
}

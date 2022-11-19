import com.gameServer.commonRefush.resource.BagItem;
import com.zfoo.net.task.TaskBus;
import com.zfoo.storage.model.anno.ResInjection;
import com.zfoo.storage.model.vo.Storage;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/11/20 1:45
 */
public class ApplicationTest {
    @ResInjection
    private Storage<Integer, BagItem> playerExpStorage;

    /**
     * 将表放到数据库中
     */
    @Test
    public void ApplyDataMain() {
        var map = new HashMap<Integer, BagItem>();
        var lit = playerExpStorage.getAll();
        lit.forEach((bagItem) -> {
            var list = map.computeIfAbsent(bagItem.get_id(), k -> bagItem);

        });

        map.forEach((id, item) -> TaskBus.execute(id, new Runnable() {
            @Override
            public void run() {
//                var nowItem= OrmContext.getAccessor().load(item.get_id(),BagItem.class);
            }


        }));
    }
}

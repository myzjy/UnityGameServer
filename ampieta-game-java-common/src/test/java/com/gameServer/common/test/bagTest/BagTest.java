package com.gameServer.common.test.bagTest;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/11/20 23:19
 */
public class BagTest {
//    @ResInjection
//    private Storage<Integer, BagItem> playerExpStorage;

//    public void BagTest() {
//        var map = new HashMap<Integer, BagItem>();
//        var lit = playerExpStorage.getAll();
//        lit.forEach((bagItem) -> {
//            var list = map.computeIfAbsent(bagItem.get_id(), k -> bagItem);
//
//        });
//
//        map.forEach((id, item) -> TaskBus.execute(id, () -> {
//            var nowItem = OrmContext.getAccessor().load(item.get_id(), BagItemEntity.class);
//            var en = BagItemEntity.valueOf(item);
//            if (nowItem == null) {
//                OrmContext.getAccessor().insert(en);
//            } else {
//                OrmContext.getAccessor().update(en);
//            }
//        }));
//    }
}

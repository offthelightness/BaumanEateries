package me.glagolev.baumaneateries.features.order;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.glagolev.baumaneateries.core.repo.BaseRepository;
import me.glagolev.baumaneateries.features.menu.model.Dish;

/**
 * Предоставляет список выбранных блюд
 */
public class OrderRepository extends BaseRepository {

    private BehaviorSubject<Map<Dish, Integer>> orderSubject = BehaviorSubject.createDefault(new HashMap<>());

    public Observable<Map<Dish, Integer>> getOrderObservable() {
        return orderSubject.hide();
    }

    public Observable<Integer> getOrderSizeObservable() {
        return
                orderSubject
                        .hide()
                        .map(
                                dishIntegerMap -> {
                                    int orderSize = 0;
                                    Collection<Integer> positionsCount = dishIntegerMap.values();
                                    for (Integer count : positionsCount) {
                                        orderSize += count;
                                    }
                                    return orderSize;
                                });
    }

    public void addPosition(Dish dish, Integer count) {
        Map<Dish, Integer> orderMap = orderSubject.getValue();

        int positionTotalCount = 0;
        if (orderMap.containsKey(dish)) {
            positionTotalCount = orderMap.get(dish) + count;
        } else {
            positionTotalCount = count;
        }

        orderMap.put(dish, positionTotalCount);
        orderSubject.onNext(orderMap);
    }
}

package me.glagolev.baumaneateries.features.order;

import java.util.ArrayList;
import java.util.List;

import me.glagolev.baumaneateries.core.repo.BaseRepository;
import me.glagolev.baumaneateries.features.menu.model.Dish;

public class OrderRepository extends BaseRepository {

    public List<Dish> getOrderList() {
        return new ArrayList<Dish>() {
            {
                add(new Dish());
                add(new Dish());
            }
        };
    }
}

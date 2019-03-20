package me.glagolev.baumaneateries.core;

import com.google.gson.Gson;

import android.app.Application;

import me.glagolev.baumaneateries.features.eateries.EateriesRepository;
import me.glagolev.baumaneateries.features.menu.DishesRepository;
import me.glagolev.baumaneateries.features.order.OrderRepository;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class BaumanEateriesApplication extends Application {

    public static BaumanEateriesApplication INSTANCE;
    private Cicerone<Router> cicerone;

    private EateriesRepository eateriesRepository;
    private DishesRepository dishesRepository;
    private OrderRepository orderRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        initCicerone();
        initRepositories();
    }

    private void initCicerone() {
        cicerone = Cicerone.create();
    }

    private void initRepositories() {
        eateriesRepository = new EateriesRepository(this, new Gson());
        dishesRepository = new DishesRepository(this, new Gson());
        orderRepository = new OrderRepository();
    }

    public NavigatorHolder getNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

    public Router getRouter() {
        return cicerone.getRouter();
    }


    public EateriesRepository getEateriesRepository() {
        return eateriesRepository;
    }

    public DishesRepository getDishesRepository() {
        return dishesRepository;
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }
}

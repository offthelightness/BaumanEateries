package me.glagolev.baumaneateries.features.menu.viewmodel;

import android.app.Application;

import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.glagolev.baumaneateries.core.BaumanEateriesApplication;
import me.glagolev.baumaneateries.core.viewmodel.BaseViewModel;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;
import me.glagolev.baumaneateries.features.menu.DishesRepository;
import me.glagolev.baumaneateries.features.menu.model.Dish;
import me.glagolev.baumaneateries.features.order.OrderRepository;

public class MenuListViewModel extends BaseViewModel {

    private DishesRepository dishesRepository;
    private OrderRepository orderRepository;

    private BehaviorSubject<List<Dish>> menuSubjects = BehaviorSubject.create();
    private BehaviorSubject<Map<Dish, Integer>> orderSubjects = BehaviorSubject.create();
    private BehaviorSubject<Integer> orderSizeSubjects = BehaviorSubject.create();

    public Observable<List<Dish>> getMenuObservable() {
        return menuSubjects.hide();
    }

    public Observable<Map<Dish, Integer>> getOrderObservable() {
        return orderSubjects.hide();
    }

    public Observable<Integer> getOrderSizeObservable() {
        return orderSizeSubjects.hide();
    }

    public MenuListViewModel(@NonNull Application application) {
        super(application);
        dishesRepository = ((BaumanEateriesApplication) application).getDishesRepository();
        orderRepository = ((BaumanEateriesApplication) application).getOrderRepository();
        addDisposables(
                orderRepository
                        .getOrderObservable()
                        .subscribe(orderSubjects::onNext),
                orderRepository
                        .getOrderSizeObservable()
                        .subscribe(orderSizeSubjects::onNext)
        );

    }

    @Override
    public void init() {
    }

    public void loadDishes() {
        menuSubjects.onNext(dishesRepository.getDishes());
    }

    public void loadDishes(EateryType eateryType) {
        menuSubjects.onNext(dishesRepository.getDishes(eateryType));
    }

    public void addPositionToCart(Dish dish, Integer count) {
        orderRepository.addPosition(dish, count);
    }
}

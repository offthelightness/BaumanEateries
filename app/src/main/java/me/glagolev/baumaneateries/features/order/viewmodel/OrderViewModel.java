package me.glagolev.baumaneateries.features.order.viewmodel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.glagolev.baumaneateries.core.BaumanEateriesApplication;
import me.glagolev.baumaneateries.core.viewmodel.BaseViewModel;
import me.glagolev.baumaneateries.features.menu.model.Dish;
import me.glagolev.baumaneateries.features.order.OrderRepository;

public class OrderViewModel extends BaseViewModel {

    private OrderRepository orderRepository;

    private BehaviorSubject<List<Dish>> orderSubject = BehaviorSubject.create();

    public Observable<List<Dish>> getOrderObservable() {
        return orderSubject.hide();
    }

    public OrderViewModel(@NonNull Application application) {
        super(application);
        orderRepository = ((BaumanEateriesApplication)application).getOrderRepository();
    }

    @Override
    public void init() {

    }
}

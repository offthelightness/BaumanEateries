package me.glagolev.baumaneateries.features.order.viewmodel;

import android.app.Application;

import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.glagolev.baumaneateries.core.BaumanEateriesApplication;
import me.glagolev.baumaneateries.core.viewmodel.BaseViewModel;
import me.glagolev.baumaneateries.features.menu.model.Dish;
import me.glagolev.baumaneateries.features.menu.model.Element;
import me.glagolev.baumaneateries.features.order.OrderRepository;

public class OrderViewModel extends BaseViewModel {

    private OrderRepository orderRepository;

    private BehaviorSubject<List<Dish>> orderListSubject = BehaviorSubject.create();

    public Observable<List<Dish>> getOrderListObservable() {
        return orderListSubject.hide();
    }

    public Observable<Map<Dish, Integer>> getOrderObservable() {
        return orderRepository.getOrderObservable();
    }

    public OrderViewModel(@NonNull Application application) {
        super(application);
        orderRepository = ((BaumanEateriesApplication) application).getOrderRepository();
    }

    @Override
    public void init() {
    }

    public void close() {
        router.exit();
    }

    public String getTotalPriceString(Map<Dish, Integer> map) {
        int totalPrice = 0;
        for (Dish d : map.keySet()) {
            totalPrice += d.getPrice() * map.get(d);
        }
        return String.format("Итоговая цена: %d \u20BD", totalPrice);
    }

    public String getTotalCountString(Map<Dish, Integer> map) {
        int sum = 0;
        for (Integer i : map.values()) {
            sum += i;
        }
        return String.format("Всего: %d шт.", sum);
    }

    public String getTotalCalorieString(Map<Dish, Integer> map) {
        int totalCalorie = 0;
        for (Dish d : map.keySet()) {
            totalCalorie += d.getCalorie() * map.get(d);
        }
        return String.format("Общая калорийность: %d кКал", totalCalorie);
    }

    public String getTotalPFCString(Map<Dish, Integer> map) {
        double proteins = 0, fats = 0, carbohydrates = 0;

        for (Dish d : map.keySet()) {
            proteins += d.getElementCount(Element.PROTEIN) * d.getWeight() / 100.0 * map.get(d);
            fats += d.getElementCount(Element.FAT) * d.getWeight() / 100.0 * map.get(d);
            carbohydrates += d.getElementCount(Element.CARBOHYDRATE) * d.getWeight() / 100.0 * map.get(d);
        }
        return String.format(
                "Пищевая ценность: Б-%sг, Ж-%sг, У-%sг",
                (int) proteins,
                (int) fats,
                (int) carbohydrates);
    }
}

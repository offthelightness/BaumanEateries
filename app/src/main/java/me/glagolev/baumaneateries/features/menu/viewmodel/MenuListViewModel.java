package me.glagolev.baumaneateries.features.menu.viewmodel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.glagolev.baumaneateries.core.BaumanEateriesApplication;
import me.glagolev.baumaneateries.core.viewmodel.BaseViewModel;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;
import me.glagolev.baumaneateries.features.menu.DishesRepository;
import me.glagolev.baumaneateries.features.menu.model.Dish;

public class MenuListViewModel extends BaseViewModel {

    private DishesRepository dishesRepository;

    private BehaviorSubject<List<Dish>> menuSubjects = BehaviorSubject.create();

    public Observable<List<Dish>> getMenuObservable() {
        return menuSubjects.hide();
    }

    public MenuListViewModel(@NonNull Application application) {
        super(application);
        dishesRepository = ((BaumanEateriesApplication) application).getDishesRepository();
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
}

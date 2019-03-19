package me.glagolev.baumaneateries.features.menu.viewmodel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.glagolev.baumaneateries.core.BaseViewModel;

public class MenuListViewModel extends BaseViewModel {

    public BehaviorSubject<List<Object>> menuSubjects = BehaviorSubject.create();

    public Observable<List<Object>> getMenuObservable() {
        return menuSubjects.hide();
    }

    @Override
    public void init() {
        load();
    }

    private void load() {
        List<Object> objects = new ArrayList<Object>() {
            {
                add(new Object());
                add(new Object());
            }
        };
        menuSubjects.onNext(objects);
    }
}

package me.glagolev.baumaneateries.features.eateries.viewmodel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.glagolev.baumaneateries.core.BaseViewModel;
import me.glagolev.baumaneateries.features._common.Screens;

public class EateriesListViewModel extends BaseViewModel {

    public BehaviorSubject<List<Object>> eateriesSubjects = BehaviorSubject.create();

    public Observable<List<Object>> getEateriesObservable() {
        return eateriesSubjects.hide();
    }

    @Override
    public void init() {
        load();
    }

    private void load() {
        List<Object> objects = new ArrayList<Object>(3) {
            {
                add(new Object());
                add(new Object());
                add(new Object());
            }
        };


        eateriesSubjects.onNext(objects);
    }

    public void openEatery(Object object) {
        router.navigateTo(new Screens.EateryDetailScreen());
    }
}

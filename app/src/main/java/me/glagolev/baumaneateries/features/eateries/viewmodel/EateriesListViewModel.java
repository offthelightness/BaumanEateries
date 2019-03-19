package me.glagolev.baumaneateries.features.eateries.viewmodel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.glagolev.baumaneateries.core.BaseViewModel;
import me.glagolev.baumaneateries.features._common.Screens;
import me.glagolev.baumaneateries.features.eateries.EateriesRepository;
import me.glagolev.baumaneateries.features.eateries.model.Eatery;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;

public class EateriesListViewModel extends BaseViewModel {

    private EateriesRepository eateriesRepository;

    private BehaviorSubject<List<Eatery>> eateriesSubjects = BehaviorSubject.create();

    public EateriesListViewModel(@NonNull Application application) {
        super(application);
        eateriesRepository = EateriesRepository.getInstance(application);
    }


    @Override
    public void init() {
        load();
    }

    private void load() {
        List<Eatery> objects = eateriesRepository.getEateries();

        System.out.println();
        eateriesSubjects.onNext(objects);
    }

    public void openEatery(EateryType type) {
        router.navigateTo(new Screens.EateryDetailScreen());
    }

    public Observable<List<Eatery>> getEateriesObservable() {
        return eateriesSubjects.hide();
    }


    @Override
    protected void onCleared() {
        eateriesRepository = null;
        super.onCleared();
    }
}

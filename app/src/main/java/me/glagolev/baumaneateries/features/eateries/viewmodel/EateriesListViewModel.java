package me.glagolev.baumaneateries.features.eateries.viewmodel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.glagolev.baumaneateries.core.BaumanEateriesApplication;
import me.glagolev.baumaneateries.core.viewmodel.BaseViewModel;
import me.glagolev.baumaneateries.features._common.Screens;
import me.glagolev.baumaneateries.features.eateries.EateriesRepository;
import me.glagolev.baumaneateries.features.eateries.model.Eatery;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;

public class EateriesListViewModel extends BaseViewModel {

    private EateriesRepository eateriesRepository;

    private BehaviorSubject<List<Eatery>> eaterySubjects = BehaviorSubject.create();

    public Observable<List<Eatery>> getEateryObservable() {
        return eaterySubjects.hide();
    }

    public EateriesListViewModel(@NonNull Application application) {
        super(application);
        eateriesRepository = ((BaumanEateriesApplication) application).getEateriesRepository();
    }

    @Override
    public void init() {
        loadEateries();
    }

    private void loadEateries() {
        eaterySubjects.onNext(eateriesRepository.getEateries());
    }

    public void openEatery(EateryType type) {
        router.navigateTo(new Screens.MenuScreen(type));
    }

    public void openEateryDetails(EateryType type) {
        router.navigateTo(new Screens.EateryDetailsScreen(type));
    }
}

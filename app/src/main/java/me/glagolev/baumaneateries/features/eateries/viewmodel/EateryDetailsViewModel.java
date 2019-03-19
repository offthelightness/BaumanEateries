package me.glagolev.baumaneateries.features.eateries.viewmodel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.glagolev.baumaneateries.core.BaumanEateriesApplication;
import me.glagolev.baumaneateries.core.viewmodel.BaseViewModel;
import me.glagolev.baumaneateries.features.eateries.EateriesRepository;
import me.glagolev.baumaneateries.features.eateries.model.Eatery;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;

public class EateryDetailsViewModel extends BaseViewModel {

    private EateriesRepository eateriesRepository;

    private BehaviorSubject<Eatery> eaterySubjects = BehaviorSubject.create();

    public Observable<Eatery> getEateryObservable() {
        return eaterySubjects.hide();
    }

    public EateryDetailsViewModel(@NonNull Application application) {
        super(application);
        eateriesRepository = ((BaumanEateriesApplication) application).getEateriesRepository();
    }

    @Override
    public void init() {
    }

    public void loadEateryDetails(EateryType eateryType) {
        eaterySubjects.onNext(eateriesRepository.getEatery(eateryType));
    }

}

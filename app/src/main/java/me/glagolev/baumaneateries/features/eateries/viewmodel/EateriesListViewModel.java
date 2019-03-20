package me.glagolev.baumaneateries.features.eateries.viewmodel;

import android.app.Application;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.glagolev.baumaneateries.core.BaumanEateriesApplication;
import me.glagolev.baumaneateries.core.viewmodel.BaseViewModel;
import me.glagolev.baumaneateries.features._common.Screens;
import me.glagolev.baumaneateries.features.eateries.EateriesRepository;
import me.glagolev.baumaneateries.features.eateries.model.Eatery;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;

/**
 * Содержит бизнес-логику для экрана со списком столовых
 */
public class EateriesListViewModel extends BaseViewModel {

    private Map<EateryType, String> formattedScheduleMap = new HashMap<>();


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

    public Map<EateryType, String> getFormattedMap(List<Eatery> eateries) {
        int currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        for (int i = 0; i < eateries.size(); i++) {
            formattedScheduleMap.put(eateries.get(i).getType(), String.format(currentTime >= eateries.get(i).getOpenFrom() && currentTime < eateries.get(i).getClosedTo() ? "Открыто до %s" : "Закрыто", eateries.get(i).getClosedTo()));
        }
        return formattedScheduleMap;
    }
}

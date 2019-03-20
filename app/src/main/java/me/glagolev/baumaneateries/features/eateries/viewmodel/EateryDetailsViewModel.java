package me.glagolev.baumaneateries.features.eateries.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.glagolev.baumaneateries.core.BaumanEateriesApplication;
import me.glagolev.baumaneateries.core.viewmodel.BaseViewModel;
import me.glagolev.baumaneateries.features.eateries.EateriesRepository;
import me.glagolev.baumaneateries.features.eateries.model.Eatery;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;

/**
 * Содержит бизнес-логику экрана информации о столовой
 */
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

    public void close() {
        router.exit();
    }

    public String getScheduleFormattedString(int dayOfWeek, Eatery eatery) {
        String prefix;
        switch (dayOfWeek) {
            case 1:
                prefix = "понедельник - ";
                break;
            case 2:
                prefix = "вторник - ";
                break;
            case 3:
                prefix = "среда - ";
                break;
            case 4:
                prefix = "четверг - ";
                break;
            case 5:
                prefix = "пятница - ";
                break;
            case 6:
                prefix = "суббота - ";
                break;
            case 7:
                prefix = "воскресенье - ";
                break;
            default:
                return null;
        }
        return String.format(prefix + (eatery.getDayFrom() <= dayOfWeek && eatery.getDayTo() >= dayOfWeek ? "c %s до %s" : "закрыто"), eatery.getOpenFrom(), eatery.getClosedTo());
    }
}

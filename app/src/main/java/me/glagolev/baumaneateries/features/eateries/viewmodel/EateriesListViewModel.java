package me.glagolev.baumaneateries.features.eateries.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import me.glagolev.baumaneateries.core.viewmodel.BaseViewModel;
import me.glagolev.baumaneateries.features._common.Screens;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;

public class EateriesListViewModel extends BaseViewModel {

    public EateriesListViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void init() {
    }

    public void openEatery(EateryType type) {
        router.navigateTo(new Screens.MenuScreen(type));
    }

    public void openEateryDetails(EateryType type) {
        router.navigateTo(new Screens.EateryDetailsScreen(type));
    }
}

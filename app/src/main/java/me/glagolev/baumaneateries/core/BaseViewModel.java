package me.glagolev.baumaneateries.core;

import androidx.lifecycle.ViewModel;
import ru.terrakok.cicerone.Router;

public abstract class BaseViewModel extends ViewModel {

    protected Router router;

    public BaseViewModel() {
        super();
        router = BaumanEateriesApplication.INSTANCE.getRouter();
    }

    public abstract void init();
}

package me.glagolev.baumaneateries.core;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import ru.terrakok.cicerone.Router;

public abstract class BaseViewModel extends AndroidViewModel {

    protected Router router;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        router = BaumanEateriesApplication.INSTANCE.getRouter();
    }

    public abstract void init();
}

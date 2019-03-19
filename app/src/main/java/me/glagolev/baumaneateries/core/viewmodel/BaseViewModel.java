package me.glagolev.baumaneateries.core.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import me.glagolev.baumaneateries.core.BaumanEateriesApplication;
import ru.terrakok.cicerone.Router;

public abstract class BaseViewModel extends AndroidViewModel {

    protected Router router;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        router = BaumanEateriesApplication.INSTANCE.getRouter();
    }

    public abstract void init();
}

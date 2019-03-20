package me.glagolev.baumaneateries.core.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.glagolev.baumaneateries.core.BaumanEateriesApplication;
import ru.terrakok.cicerone.Router;

public abstract class BaseViewModel extends AndroidViewModel {

    protected Router router;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public BaseViewModel(@NonNull Application application) {
        super(application);
        router = BaumanEateriesApplication.INSTANCE.getRouter();
    }

    public abstract void init();


    protected void addDisposables(Disposable... disposables){
        for (Disposable d: disposables) {
            compositeDisposable.add(d);
        }
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}

package me.glagolev.baumaneateries.core.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.glagolev.baumaneateries.core.BaumanEateriesApplication;
import ru.terrakok.cicerone.Router;

/**
 * Базовый ViewModel класс, используемый в приложений для сокрытия бизнес-логики
 * и исключения ее из Fragment'ов во избежания создания GodObject'ов.
 * Также предоставляет экземляр класса Router для навигации внутри приложения
 * и содержит вспомогательный метод addDisposables(Disposable... disposables)
 * для автоматического управления rx-подписками в соотвествии с lifecycle Fragment'a
 * с которым связана ViewModel
 */
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

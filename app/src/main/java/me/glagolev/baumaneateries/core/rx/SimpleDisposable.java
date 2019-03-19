package me.glagolev.baumaneateries.core.rx;

import io.reactivex.observers.DisposableObserver;

public class SimpleDisposable<T> extends DisposableObserver<T> {


    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}

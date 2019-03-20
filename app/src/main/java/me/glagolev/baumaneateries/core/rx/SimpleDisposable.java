package me.glagolev.baumaneateries.core.rx;

import io.reactivex.observers.DisposableObserver;


/**
 * Простая реализация интерфейса rx-подписчика, для уменьшения кода,
 * т.к. при использовании DisposableObserver<T> необходимо было бы переопределять
 * каждый раз методы onNext, onError, onComplete
 */
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

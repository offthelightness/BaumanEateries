package me.glagolev.baumaneateries.core.ui;

import androidx.fragment.app.Fragment;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Базовый Fragment класс, содержащий вспомогательный метода addDisposables(Disposable... disposables)
 * для автоматического управления rx-подписками в соотвествии с lifecycle Fragment
 */
public abstract class BaseFragment extends Fragment {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void addDisposables(Disposable... disposables) {
        for (Disposable d : disposables) {
            compositeDisposable.add(d);
        }
    }

    @Override
    public void onPause() {
        compositeDisposable.clear();
        super.onPause();
    }
}

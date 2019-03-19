package me.glagolev.baumaneateries.core.ui;

import androidx.fragment.app.Fragment;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragment extends Fragment {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void addDisposables(Disposable... disposables){
        for (Disposable d: disposables) {
            compositeDisposable.add(d);
        }
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}

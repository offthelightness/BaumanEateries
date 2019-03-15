package me.glagolev.baumaneateries.features.eateries.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.observers.DisposableObserver;
import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.BaseFragment;
import me.glagolev.baumaneateries.features.eateries.EateriesRecycleViewAdapter;
import me.glagolev.baumaneateries.features.eateries.viewmodel.EateriesListViewModel;

public class EateriesListFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private EateriesRecycleViewAdapter adapter;
    private EateriesListViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel =  ViewModelProviders.of(this).get(EateriesListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eateries_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_eateries);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        adapter = new EateriesRecycleViewAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.init();
    }

    @SuppressLint("CheckResult")
    @Override
    public void onResume() {
        super.onResume();
        addDisposables(
                viewModel.getEateriesObservable()
                        .subscribeWith(
                                new DisposableObserver<List<Object>>() {
                                    @Override
                                    public void onNext(List<Object> objects) {
                                        adapter.setData(objects);
                                        adapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                }
                        ),
                adapter.getClickEateryObservable()
                        .subscribeWith(
                                new DisposableObserver<Object>(){

                                    @Override
                                    public void onNext(Object o) {
                                        viewModel.openEatery(o);
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                }
                        )
        );
    }

}

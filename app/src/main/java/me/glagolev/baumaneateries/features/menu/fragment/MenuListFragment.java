package me.glagolev.baumaneateries.features.menu.fragment;

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
import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.ui.BaseFragment;
import me.glagolev.baumaneateries.core.rx.SimpleDisposable;
import me.glagolev.baumaneateries.features._common.Screens;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;
import me.glagolev.baumaneateries.features.menu.MenuRecyclerViewAdapter;
import me.glagolev.baumaneateries.features.menu.viewmodel.MenuListViewModel;

public class MenuListFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private MenuRecyclerViewAdapter adapter;
    private MenuListViewModel viewModel;


    public static MenuListFragment newInstance(EateryType type) {
        MenuListFragment fragment = new MenuListFragment();

        Bundle args = new Bundle();
        args.putSerializable(Screens.MenuScreen.KEY_EATERY_TYPE, type);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MenuListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new MenuRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.init();
    }

    @Override
    public void onResume() {
        super.onResume();
        addDisposables(
                viewModel
                        .getMenuObservable()
                        .subscribeWith(new SimpleDisposable<List<Object>>() {
                            @Override
                            public void onNext(List<Object> objects) {
                                super.onNext(objects);
                                adapter.setData(objects);
                                adapter.notifyDataSetChanged();
                            }
                        })
        );
    }
}

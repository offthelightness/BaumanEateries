package me.glagolev.baumaneateries.features.menu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.BaseFragment;
import me.glagolev.baumaneateries.features.menu.MenuRecyclerViewAdapter;
import me.glagolev.baumaneateries.features.menu.viewmodel.MenuListViewModel;

public class MenuListFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private MenuRecyclerViewAdapter adapter;
    private MenuListViewModel viewModel;

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
}

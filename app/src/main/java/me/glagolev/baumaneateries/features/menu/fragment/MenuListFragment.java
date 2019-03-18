package me.glagolev.baumaneateries.features.menu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.BaseFragment;
import me.glagolev.baumaneateries.features.menu.viewmodel.MenuListViewModel;

public class MenuListFragment extends BaseFragment {

    private MenuListViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel =  ViewModelProviders.of(this).get(MenuListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_eateries_list, container, false);
    }
}

package me.glagolev.baumaneateries.features.eateries.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.ui.BaseFragment;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;
import me.glagolev.baumaneateries.features.eateries.viewmodel.EateriesListViewModel;

public class EateriesListFragment extends BaseFragment {

    private EateriesListViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(EateriesListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eateries_list, container, false);
        view.findViewById(R.id.iv_eatery_main).setOnClickListener(v -> viewModel.openEatery(EateryType.MAIN));
        view.findViewById(R.id.iv_eatery_pelmen).setOnClickListener(v -> viewModel.openEatery(EateryType.PELMEN));
        view.findViewById(R.id.iv_eatery_pie).setOnClickListener(v -> viewModel.openEatery(EateryType.PIE));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.init();
    }
}

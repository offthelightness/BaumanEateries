package me.glagolev.baumaneateries.features.eateries.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.BaseFragment;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;
import me.glagolev.baumaneateries.features.eateries.viewmodel.EateriesListViewModel;

public class EateriesListFragment extends BaseFragment implements View.OnClickListener {

    private EateriesListViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(EateriesListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_eateries_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.init();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_eatery_main) {
            viewModel.openEatery(EateryType.MAIN);
        } else if (v.getId() == R.id.iv_eatery_pelmen) {
            viewModel.openEatery(EateryType.PELMEN);
        } else if (v.getId() == R.id.iv_eatery_pie) {
            viewModel.openEatery(EateryType.PIE);
        } else {
            throw new IllegalArgumentException("Unknown eatery");
        }
    }


}

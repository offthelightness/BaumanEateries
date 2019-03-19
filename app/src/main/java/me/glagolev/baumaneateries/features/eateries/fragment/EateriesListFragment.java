package me.glagolev.baumaneateries.features.eateries.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.rx.SimpleDisposable;
import me.glagolev.baumaneateries.core.ui.BaseFragment;
import me.glagolev.baumaneateries.features.eateries.model.Eatery;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;
import me.glagolev.baumaneateries.features.eateries.viewmodel.EateriesListViewModel;

import static me.glagolev.baumaneateries.features.eateries.model.EateryType.MAIN;

public class EateriesListFragment extends BaseFragment {

    private EateriesListViewModel viewModel;
    private Map<EateryType, String> formattedScheduleMap = new HashMap<>();

    private TextView tvMainSchedule, tvPelmenSchedule, tvPieSchedule;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(EateriesListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eateries_list, container, false);

        view.findViewById(R.id.iv_eatery_main).setOnClickListener(v -> viewModel.openEatery(MAIN));
        view.findViewById(R.id.iv_eatery_pelmen).setOnClickListener(v -> viewModel.openEatery(EateryType.PELMEN));
        view.findViewById(R.id.iv_eatery_pie).setOnClickListener(v -> viewModel.openEatery(EateryType.PIE));

        view.findViewById(R.id.iv_eatery_main_info).setOnClickListener(v -> viewModel.openEateryDetails(MAIN));
        view.findViewById(R.id.iv_eatery_pelmen_info).setOnClickListener(v -> viewModel.openEateryDetails(EateryType.PELMEN));
        view.findViewById(R.id.iv_eatery_pie_info).setOnClickListener(v -> viewModel.openEateryDetails(EateryType.PIE));

        tvMainSchedule = view.findViewById(R.id.tv_eatery_main_schedule);
        tvPelmenSchedule = view.findViewById(R.id.tv_eatery_pelmen_schedule);
        tvPieSchedule = view.findViewById(R.id.tv_eatery_pie_schedule);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.init();
    }

    @Override
    public void onResume() {
        super.onResume();
        addDisposables(
                viewModel
                        .getEateryObservable()
                        .subscribeWith(new SimpleDisposable<List<Eatery>>() {
                            @Override
                            public void onNext(List<Eatery> eateries) {
                                super.onNext(eateries);
                                formattedScheduleMap = viewModel.getFormattedMap(eateries);
                                initUI();
                            }
                        }));
    }

    private void initUI() {
        tvMainSchedule.setText(formattedScheduleMap.get(EateryType.MAIN));
        tvPelmenSchedule.setText(formattedScheduleMap.get(EateryType.PELMEN));
        tvPieSchedule.setText(formattedScheduleMap.get(EateryType.PIE));
    }
}

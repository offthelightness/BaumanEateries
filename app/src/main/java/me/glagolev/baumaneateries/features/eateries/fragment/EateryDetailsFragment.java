package me.glagolev.baumaneateries.features.eateries.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.rx.SimpleDisposable;
import me.glagolev.baumaneateries.core.ui.BaseFragment;
import me.glagolev.baumaneateries.features._common.Screens;
import me.glagolev.baumaneateries.features.eateries.model.Eatery;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;
import me.glagolev.baumaneateries.features.eateries.viewmodel.EateryDetailsViewModel;

public class EateryDetailsFragment extends BaseFragment {

    private EateryDetailsViewModel viewModel;
    private EateryType type;
    private Eatery eatery;

    private TextView tvName, tvLocation, tvDescription;
    private TextView tvScheduleDay1, tvScheduleDay2, tvScheduleDay3, tvScheduleDay4, tvScheduleDay5, tvScheduleDay6, tvScheduleDay7;

    public static EateryDetailsFragment newInstance(EateryType type) {
        EateryDetailsFragment fragment = new EateryDetailsFragment();

        Bundle args = new Bundle();
        args.putSerializable(Screens.KEY_EATERY_TYPE, type);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(EateryDetailsViewModel.class);
        type = (EateryType) getArguments().getSerializable(Screens.KEY_EATERY_TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_eatery_details, container, false);

        tvName = view.findViewById(R.id.tv_name);
        tvLocation = view.findViewById(R.id.tv_location);
        tvDescription = view.findViewById(R.id.tv_description);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.init();
        viewModel.loadEateryDetails(type);
    }

    @Override
    public void onResume() {
        super.onResume();
        addDisposables(
                viewModel
                        .getEateryObservable()
                        .subscribeWith(new SimpleDisposable<Eatery>(){
                            @Override
                            public void onNext(Eatery eatery) {
                                super.onNext(eatery);
                                EateryDetailsFragment.this.eatery = eatery;
                                initUI();
                            }
                        })

        );
    }

    private void initUI() {
        tvName.setText(eatery.getName());
        tvLocation.setText(eatery.getLocation());
        //tvDescription.setText(eatery.get());
    }
}

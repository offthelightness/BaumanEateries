package me.glagolev.baumaneateries.features.eateries.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    private ImageView ivClose;

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
        View view = inflater.inflate(R.layout.fragment_eatery_details, container, false);

        tvName = view.findViewById(R.id.tv_name);
        tvLocation = view.findViewById(R.id.tv_location);
        tvDescription = view.findViewById(R.id.tv_description);

        tvScheduleDay1 = view.findViewById(R.id.tv_schedule_day_1);
        tvScheduleDay2 = view.findViewById(R.id.tv_schedule_day_2);
        tvScheduleDay3 = view.findViewById(R.id.tv_schedule_day_3);
        tvScheduleDay4 = view.findViewById(R.id.tv_schedule_day_4);
        tvScheduleDay5 = view.findViewById(R.id.tv_schedule_day_5);
        tvScheduleDay6 = view.findViewById(R.id.tv_schedule_day_6);
        tvScheduleDay7 = view.findViewById(R.id.tv_schedule_day_7);

        ivClose = view.findViewById(R.id.iv_close);
        ivClose.setOnClickListener(v -> viewModel.close());

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
                        .subscribeWith(new SimpleDisposable<Eatery>() {
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
        tvDescription.setText(eatery.getDescription());

        int dayFrom = eatery.getDayFrom(), dayTo = eatery.getDayTo();
        tvScheduleDay1.setText(String.format("понедельник - " + (dayFrom <= 1 && dayTo >= 1 ? "c %s до %s" : "закрыто"), eatery.getOpenFrom(), eatery.getClosedTo()));
        tvScheduleDay2.setText(String.format("вторник - " + (dayFrom <= 2 && dayTo >= 2 ? "c %s до %s" : "закрыто"), eatery.getOpenFrom(), eatery.getClosedTo()));
        tvScheduleDay3.setText(String.format("среда - " + (dayFrom <= 3 && dayTo >= 3 ? "c %s до %s" : "закрыто"), eatery.getOpenFrom(), eatery.getClosedTo()));
        tvScheduleDay4.setText(String.format("четверг - " + (dayFrom <= 4 && dayTo >= 4 ? "c %s до %s" : "закрыто"), eatery.getOpenFrom(), eatery.getClosedTo()));
        tvScheduleDay5.setText(String.format("пятница - " + (dayFrom <= 5 && dayTo >= 5 ? "c %s до %s" : "закрыто"), eatery.getOpenFrom(), eatery.getClosedTo()));
        tvScheduleDay6.setText(String.format("суббота - " + (dayFrom <= 6 && dayTo >= 6 ? "c %s до %s" : "закрыто"), eatery.getOpenFrom(), eatery.getClosedTo()));
        tvScheduleDay7.setText(String.format("воскресенье - " + (dayFrom <= 7 && dayTo >= 7 ? "c %s до %s" : "закрыто"), eatery.getOpenFrom(), eatery.getClosedTo()));
    }
}

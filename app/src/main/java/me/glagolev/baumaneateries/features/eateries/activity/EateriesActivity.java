package me.glagolev.baumaneateries.features.eateries.activity;

import android.os.Bundle;

import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.BaseActivity;
import me.glagolev.baumaneateries.features._common.Screens;

public class EateriesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eateries);
        router.replaceScreen(new Screens.EateriesListScreen());
    }
}

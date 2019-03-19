package me.glagolev.baumaneateries.features.menu.activity;

import android.os.Bundle;

import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.BaseActivity;
import me.glagolev.baumaneateries.features._common.Screens;

public class MenuActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        router.replaceScreen(new Screens.MenuListScreen());
    }
}

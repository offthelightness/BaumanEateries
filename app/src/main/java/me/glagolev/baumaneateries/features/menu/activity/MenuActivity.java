package me.glagolev.baumaneateries.features.menu.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.BaseActivity;
import me.glagolev.baumaneateries.features._common.Screens;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;

public class MenuActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        parseIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        parseIntent(getIntent());
    }

    private void parseIntent(@NonNull Intent intent) {
        EateryType eateryType = (EateryType) intent.getSerializableExtra(Screens.MenuScreen.KEY_EATERY_TYPE);
        router.replaceScreen(new Screens.MenuListScreen(eateryType));
    }
}

package me.glagolev.baumaneateries.features.order.activity;

import android.os.Bundle;

import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.ui.BaseActivity;
import me.glagolev.baumaneateries.features._common.Screens;

/**
 * Является точкой входа для flow ORDER,
 * состоящее из экрана заказа
 */
public class OrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        router.replaceScreen(new Screens.OrderDetailsScreen());
    }
}

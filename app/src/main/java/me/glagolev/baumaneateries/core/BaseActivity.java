package me.glagolev.baumaneateries.core;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import me.glagolev.baumaneateries.R;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public abstract class BaseActivity extends AppCompatActivity {

    protected Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        router = BaumanEateriesApplication.INSTANCE.getRouter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BaumanEateriesApplication.INSTANCE.getNavigatorHolder().setNavigator(new SupportAppNavigator(this, R.id.container));
    }

    @Override
    protected void onPause() {
        super.onPause();
        BaumanEateriesApplication.INSTANCE.getNavigatorHolder().removeNavigator();
    }
}

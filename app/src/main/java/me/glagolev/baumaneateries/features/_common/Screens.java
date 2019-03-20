package me.glagolev.baumaneateries.features._common;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import me.glagolev.baumaneateries.features.eateries.fragment.EateriesListFragment;
import me.glagolev.baumaneateries.features.eateries.fragment.EateryDetailsFragment;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;
import me.glagolev.baumaneateries.features.menu.activity.MenuActivity;
import me.glagolev.baumaneateries.features.menu.fragment.MenuListFragment;
import me.glagolev.baumaneateries.features.order.activity.OrderActivity;
import me.glagolev.baumaneateries.features.order.fragment.OrderFragment;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {

    public static final String KEY_EATERY_TYPE = "KEY_EATERY_TYPE";

    // eateries feature navigation
    public static final class EateriesListScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return new EateriesListFragment();
        }
    }

    public static final class EateryDetailsScreen extends SupportAppScreen {

        EateryType type;

        public EateryDetailsScreen(EateryType type) {
            this.type = type;
        }

        @Override
        public Fragment getFragment() {
            return EateryDetailsFragment.newInstance(type);
        }
    }


    // menu feature navigation
    public static final class MenuScreen extends SupportAppScreen {

        EateryType type;

        public MenuScreen(EateryType type) {
            this.type = type;
        }

        @Override
        public Intent getActivityIntent(Context context) {
            Intent intent = new Intent(context, MenuActivity.class);
            intent.putExtra(KEY_EATERY_TYPE, type);
            return intent;
        }
    }

    public static final class MenuListScreen extends SupportAppScreen {

        EateryType type;

        public MenuListScreen(EateryType type) {
            this.type = type;
        }

        @Override
        public Fragment getFragment() {
            return MenuListFragment.newInstance(type);
        }
    }


    // order feature navigation
    public static final class OrderScreen extends SupportAppScreen {
        @Override
        public Intent getActivityIntent(Context context) {
            return new Intent(context, OrderActivity.class);
        }
    }

    public static final class OrderDetailsScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return new OrderFragment();
        }
    }
}

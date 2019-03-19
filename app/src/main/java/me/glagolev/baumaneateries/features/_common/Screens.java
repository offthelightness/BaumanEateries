package me.glagolev.baumaneateries.features._common;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import me.glagolev.baumaneateries.features.eateries.fragment.EateriesListFragment;
import me.glagolev.baumaneateries.features.eateries.fragment.EateryDetailFragment;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;
import me.glagolev.baumaneateries.features.menu.activity.MenuActivity;
import me.glagolev.baumaneateries.features.menu.fragment.MenuListFragment;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {

    // eateries feature navigation
    public static final class EateriesListScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return new EateriesListFragment();
        }
    }

    public static final class EateryDetailScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return new EateryDetailFragment();
        }
    }

    // menu feature navigation
    public static final class MenuScreen extends SupportAppScreen {

        public static final String KEY_EATERY_TYPE = "KEY_EATERY_TYPE";

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
}

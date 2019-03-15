package me.glagolev.baumaneateries.features._common;

import androidx.fragment.app.Fragment;
import me.glagolev.baumaneateries.features.eateries.fragment.EateriesListFragment;
import me.glagolev.baumaneateries.features.eateries.fragment.EateryDetailFragment;
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
}

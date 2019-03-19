package me.glagolev.baumaneateries.core;

import com.google.gson.Gson;

import android.app.Application;

import me.glagolev.baumaneateries.features.eateries.EateriesRepository;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class BaumanEateriesApplication extends Application {

    public static BaumanEateriesApplication INSTANCE;
    private Cicerone<Router> cicerone;

    private EateriesRepository eateriesRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        initCicerone();
        initRepositories();
    }

    private void initCicerone() {
        cicerone = Cicerone.create();
    }

    private void initRepositories() {
        eateriesRepository = new EateriesRepository(this, new Gson());
    }

    public NavigatorHolder getNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

    public Router getRouter() {
        return cicerone.getRouter();
    }


    public EateriesRepository getEateriesRepository() {
        return eateriesRepository;
    }
}

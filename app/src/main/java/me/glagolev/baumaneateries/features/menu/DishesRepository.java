package me.glagolev.baumaneateries.features.menu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

import java.util.List;

import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.repo.BaseRepository;
import me.glagolev.baumaneateries.features.eateries.model.Eatery;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;
import me.glagolev.baumaneateries.features.menu.model.Dish;

public class DishesRepository extends BaseRepository {

    private Context context;
    private Gson gson;

    private DishesRepository() {

    }

    public DishesRepository(final Context context, final Gson gson) {
        this.context = context;
        this.gson = gson;
    }

    public List<Dish> getDishes() {
        return gson.fromJson(readJsonFile(context.getResources().openRawResource(R.raw.dishes)), new TypeToken<List<Dish>>() {}.getType());
    }
}

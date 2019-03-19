package me.glagolev.baumaneateries.features.eateries;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

import java.util.List;

import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.repo.BaseRepository;
import me.glagolev.baumaneateries.features.eateries.model.Eatery;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;

public class EateriesRepository extends BaseRepository {

    private Context context;
    private Gson gson;

    private EateriesRepository() {

    }

    public EateriesRepository(final Context context, final Gson gson) {
        this.context = context;
        this.gson = gson;
    }

    public List<Eatery> getEateries() {
        return gson.fromJson(readJsonFile(context.getResources().openRawResource(R.raw.eateries)), new TypeToken<List<Eatery>>() {}.getType());
    }

    public Eatery getEatery(EateryType type) {
        List<Eatery> eateryList = gson.fromJson(readJsonFile(context.getResources().openRawResource(R.raw.eateries)), new TypeToken<List<Eatery>>() {}.getType());
        for (Eatery e: eateryList) {
            if (e.getType() == type) return e;
        }
        throw new IllegalStateException("eatery with type" + type + " not found");
    }
}

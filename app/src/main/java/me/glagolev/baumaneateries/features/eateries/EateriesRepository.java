package me.glagolev.baumaneateries.features.eateries;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

import java.util.List;

import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.BaseRepository;
import me.glagolev.baumaneateries.features.eateries.model.Eatery;

public class EateriesRepository  extends BaseRepository {

    private static EateriesRepository INSTANCE;
    private Context context;
    private Gson gson;

    private EateriesRepository() {

    }

    private EateriesRepository(final Context context, final Gson gson) {
        this.context = context;
        this.gson = gson;
    }

    public static EateriesRepository getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (EateriesRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EateriesRepository(context, new Gson());
                }
            }
        }
        return INSTANCE;
    }

    public List<Eatery> getEateries() {
        return  gson.fromJson(readJsonFile(context.getResources().openRawResource(R.raw.eateries)), new TypeToken<List<Eatery>>(){}.getType());
    }
}

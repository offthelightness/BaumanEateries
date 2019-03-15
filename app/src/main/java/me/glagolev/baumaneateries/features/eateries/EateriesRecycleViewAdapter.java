package me.glagolev.baumaneateries.features.eateries;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.glagolev.baumaneateries.R;

public class EateriesRecycleViewAdapter extends RecyclerView.Adapter<EateriesRecycleViewAdapter.EateryViewHolder> {

    private BehaviorSubject<Object> clickEaterySubject = BehaviorSubject.create();

    private List<Object> data = new ArrayList<>();

    public void setData(List<Object> data) {
        this.data = data;
    }

    public Observable<Object> getClickEateryObservable() {
        return clickEaterySubject.hide();
    }

    @NonNull
    @Override
    public EateryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EateryViewHolder eateryViewHolder =
                new EateryViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_eatery, parent, false));
        eateryViewHolder.itemView.setOnClickListener(v -> {
            clickEaterySubject.onNext(new Object());
        });

        eateryViewHolder.itemView.setOnClickListener(v -> {
            clickEaterySubject.onNext(new Object());
        });
        return eateryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EateryViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    static class EateryViewHolder extends RecyclerView.ViewHolder {

        public EateryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

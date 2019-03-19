package me.glagolev.baumaneateries.features.eateries;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.features.eateries.model.Eatery;

@Deprecated
public class EateriesRecycleViewAdapter extends RecyclerView.Adapter<EateriesRecycleViewAdapter.EateryViewHolder> {

    private BehaviorSubject<Eatery> clickEaterySubject = BehaviorSubject.create();

    private List<Eatery> data = new ArrayList<>();

    public void setData(List<Eatery> data) {
        this.data = data;
    }

    public Observable<Eatery> getClickEateryObservable() {
        return clickEaterySubject.hide();
    }

    @NonNull
    @Override
    public EateryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EateryViewHolder eateryViewHolder =
                new EateryViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_eatery, parent, false));
        eateryViewHolder.itemView.setOnClickListener(v -> {
            clickEaterySubject.onNext(new Eatery());
        });

        eateryViewHolder.itemView.setOnClickListener(v -> {
            clickEaterySubject.onNext(new Eatery());
        });
        return eateryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EateryViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    static class EateryViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public EateryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}

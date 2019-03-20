package me.glagolev.baumaneateries.features.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.features.menu.model.Dish;
import me.glagolev.baumaneateries.features.menu.model.Element;

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.MenuViewHolder> {

    private List<Dish> data = new ArrayList<>();

    public void setData(List<Dish> data) {
        this.data = data;
        for (Dish d: data) {
            System.out.println(d);
        }
    }

    private BehaviorSubject<Dish> clickDishSubject = BehaviorSubject.create();

    public Observable<Dish> getClickDishObservable() {
        return clickDishSubject.hide();
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MenuViewHolder menuViewHolder = new MenuViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dish, parent, false));
        menuViewHolder.clContainer.setOnClickListener(v -> clickDishSubject.onNext(new Dish()));
        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getName());
        holder.tvPrice.setText(String.format("%s \u20BD", data.get(position).getPrice()));
        holder.tvWeight.setText(String.format("%s г", data.get(position).getWeight()));
        holder.tvCalorie.setText(String.format("Калорийность: %s кКал", data.get(position).getCalorie()));
        holder.tvPFC.setText(
                String.format(
                        "Б-%s%%, Ж-%s%%, У-%s%%",
                        data.get(position).getElementCount(Element.PROTEIN),
                        data.get(position).getElementCount(Element.FAT),
                        data.get(position).getElementCount(Element.CARBOHYDRATE)
                )
        );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static class MenuViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout clContainer;
        private TextView tvName, tvPrice, tvWeight, tvCalorie, tvPFC;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            clContainer = itemView.findViewById(R.id.cl_container);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvWeight = itemView.findViewById(R.id.tv_weight);
            tvCalorie = itemView.findViewById(R.id.tv_calorie);
            tvPFC = itemView.findViewById(R.id.tv_pfc);
        }
    }
}

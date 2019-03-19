package me.glagolev.baumaneateries.features.menu;

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
import me.glagolev.baumaneateries.features.menu.model.Dish;

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.MenuViewHolder> {

    private BehaviorSubject<Dish> clickDishSubject = BehaviorSubject.create();
    private List<Dish> data = new ArrayList<>();

    public void setData(List<Dish> data) {
        this.data = data;
        for (Dish d: data) {
            System.out.println(d);
        }
    }

    public Observable<Dish> getClickDishObservable() {
        return clickDishSubject.hide();
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MenuViewHolder menuViewHolder = new MenuViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false));
        menuViewHolder.itemView.setOnClickListener(v -> clickDishSubject.onNext(data.get(menuViewHolder.getAdapterPosition())));
        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static class MenuViewHolder extends RecyclerView.ViewHolder {

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

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

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.MenuViewHolder> {

    private BehaviorSubject<Object> clickMenuSubject = BehaviorSubject.create();
    private List<Object> data = new ArrayList<>();

    public void setData(List<Object> data) {
        this.data = data;
    }

    public Observable<Object> getClickMenuObservable() {
        return clickMenuSubject.hide();
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MenuViewHolder menuViewHolder = new MenuViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false));
        menuViewHolder.itemView.setOnClickListener(v -> clickMenuSubject.onNext(new Object()));
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

package me.glagolev.baumaneateries.features.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.features.menu.model.Dish;

public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<OrderRecyclerViewAdapter.OrderViewHolder> {

    private List<Dish> data = new ArrayList<>();

    public void setData(List<Dish> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false));
    }

    //TODO добавить количество позиций(какая нибудь Map может быть)
    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvPrice, tvCount;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_order);
            tvPrice = itemView.findViewById(R.id.tv_price_order);
            tvCount = itemView.findViewById(R.id.tv_count_order);

        }
    }
}

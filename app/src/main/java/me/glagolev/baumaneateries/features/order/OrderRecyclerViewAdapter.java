package me.glagolev.baumaneateries.features.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.features.menu.model.Dish;

/**
 * Адаптер списка выбранных блюд
 */
public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<OrderRecyclerViewAdapter.OrderViewHolder> {

    private Map<Dish, Integer> dataMap = new HashMap<>();
    private List<Dish> dataList = new ArrayList<>();

    public void setDataMap(Map<Dish, Integer> dataMap) {
        this.dataMap = dataMap;
        dataList = new ArrayList<>(dataMap.keySet());
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.tvName.setText(dataList.get(position).getName());
        holder.tvCount.setText(String.format("%d шт.", dataMap.get(dataList.get(position))));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvCount;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_order);
            tvCount = itemView.findViewById(R.id.tv_count_order);
        }
    }
}

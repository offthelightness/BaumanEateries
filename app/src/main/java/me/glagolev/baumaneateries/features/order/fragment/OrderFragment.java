package me.glagolev.baumaneateries.features.order.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.ui.BaseFragment;
import me.glagolev.baumaneateries.features.order.OrderRecyclerViewAdapter;
import me.glagolev.baumaneateries.features.order.viewmodel.OrderViewModel;

public class OrderFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private OrderRecyclerViewAdapter adapter;
    private OrderViewModel viewModel;
    private TextView tvTotalPrice, tvTotalCalorie;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_order);
        tvTotalPrice = view.findViewById(R.id.tv_total_price);
        tvTotalCalorie = view.findViewById(R.id.tv_total_calorie);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new OrderRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.init();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}

package me.glagolev.baumaneateries.features.order.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.rx.SimpleDisposable;
import me.glagolev.baumaneateries.core.ui.BaseFragment;
import me.glagolev.baumaneateries.features.menu.model.Dish;
import me.glagolev.baumaneateries.features.order.OrderRecyclerViewAdapter;
import me.glagolev.baumaneateries.features.order.viewmodel.OrderViewModel;

/**
 * Экран с выбранными блюдами и итоговой информацией
 */
public class OrderFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private OrderRecyclerViewAdapter adapter;
    private OrderViewModel viewModel;

    private TextView tvTotalPrice, tvTotalCalorie, tvTotalCount, tvTotalPFC;
    private ImageView ivClose;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);

        recyclerView = view.findViewById(R.id.rv_order);

        tvTotalPrice = view.findViewById(R.id.tv_total_price);
        tvTotalCalorie = view.findViewById(R.id.tv_total_calorie);
        tvTotalCount = view.findViewById(R.id.tv_total_count);
        tvTotalPFC = view.findViewById(R.id.tv_total_pfc);

        ivClose = view.findViewById(R.id.iv_close_order);
        ivClose.setOnClickListener(v -> viewModel.close());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new OrderRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.init();
    }

    @Override
    public void onResume() {
        super.onResume();
        addDisposables(
                viewModel
                        .getOrderObservable()
                        .subscribeWith(new SimpleDisposable<Map<Dish, Integer>>() {
                            @Override
                            public void onNext(Map<Dish, Integer> dishIntegerMap) {
                                super.onNext(dishIntegerMap);
                                tvTotalPrice.setText(viewModel.getTotalPriceString(dishIntegerMap));
                                tvTotalCalorie.setText(viewModel.getTotalCalorieString(dishIntegerMap));
                                tvTotalCount.setText(viewModel.getTotalCountString(dishIntegerMap));
                                tvTotalPFC.setText(viewModel.getTotalPFCString(dishIntegerMap));
                                adapter.setDataMap(dishIntegerMap);
                                adapter.notifyDataSetChanged();
                            }
                        })
        );
    }
}

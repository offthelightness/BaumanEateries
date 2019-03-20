package me.glagolev.baumaneateries.features.menu.fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.glagolev.baumaneateries.R;
import me.glagolev.baumaneateries.core.rx.SimpleDisposable;
import me.glagolev.baumaneateries.core.ui.BaseFragment;
import me.glagolev.baumaneateries.features._common.Screens;
import me.glagolev.baumaneateries.features.eateries.model.EateryType;
import me.glagolev.baumaneateries.features.menu.MenuRecyclerViewAdapter;
import me.glagolev.baumaneateries.features.menu.model.Dish;
import me.glagolev.baumaneateries.features.menu.viewmodel.MenuListViewModel;

/**
 * Экран со списком блюд, подаваемых в выбранной столовой
 */
public class MenuListFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private MenuRecyclerViewAdapter adapter;

    private TextView tvOrderCounter;
    private FloatingActionButton fabOrder;

    private MenuListViewModel viewModel;
    private EateryType eateryType;

    public static MenuListFragment newInstance(EateryType type) {
        MenuListFragment fragment = new MenuListFragment();

        Bundle args = new Bundle();
        args.putSerializable(Screens.KEY_EATERY_TYPE, type);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MenuListViewModel.class);
        eateryType = (EateryType) getArguments().getSerializable(Screens.KEY_EATERY_TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);
        recyclerView = view.findViewById(R.id.rv_menu);
        tvOrderCounter = view.findViewById(R.id.tv_order_counter);
        fabOrder = view.findViewById(R.id.fab_order);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MenuRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        fabOrder.setOnClickListener(v-> viewModel.openOrder());

        viewModel.init();
        viewModel.loadDishes(eateryType);
    }

    @Override
    public void onResume() {
        super.onResume();
        addDisposables(
                viewModel
                        .getMenuObservable()
                        .subscribeWith(
                                new SimpleDisposable<List<Dish>>() {
                                    @Override
                                    public void onNext(List<Dish> dishes) {
                                        super.onNext(dishes);
                                        adapter.setData(dishes);
                                        adapter.notifyDataSetChanged();
                                    }
                                }),
                adapter
                        .getClickDishObservable()
                        .subscribeWith(
                                new SimpleDisposable<Dish>() {
                                    @Override
                                    public void onNext(Dish dish) {
                                        super.onNext(dish);
                                        viewModel.addPositionToCart(dish, 1);
                                    }
                                }
                        ),
                viewModel
                        .getOrderSizeObservable()
                        .subscribeWith(
                                new SimpleDisposable<Integer>() {
                                    @Override
                                    public void onNext(Integer orderSize) {
                                        super.onNext(orderSize);
                                        tvOrderCounter.setText(String.valueOf(orderSize));
                                        tvOrderCounter.setVisibility(orderSize > 0 ? View.VISIBLE : View.GONE);
                                    }
                                }
                        ),
                viewModel
                        .getEmptyOrderObservable()
                        .subscribeWith(new SimpleDisposable<Boolean>() {
                            @Override
                            public void onNext(Boolean aBoolean) {
                                super.onNext(aBoolean);
                                Toast.makeText(getContext(), "Корзина пуста", Toast.LENGTH_SHORT).show();
                            }
                        })

        );
    }
}

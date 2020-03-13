package cs.bham.ac.uk.assignment3.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cs.bham.ac.uk.assignment3.R;
import cs.bham.ac.uk.assignment3.bean.FoodBean;
import cs.bham.ac.uk.assignment3.sp.SPFoodManager;
import cs.bham.ac.uk.assignment3.ui.adapter.FoodAdapter;
import cs.bham.ac.uk.assignment3.utils.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    private List<FoodBean> mFoodList;
    private RecyclerView rv_product_list;
    private FoodAdapter foodAdapter;
    private LinearLayoutManager layoutManager;

    public FavoriteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        rv_product_list = view.findViewById(R.id.rv_product_list_fav);
        layoutManager = new LinearLayoutManager(getActivity());
        rv_product_list.setLayoutManager(layoutManager);
        mFoodList = SPFoodManager.getFoodList();
        foodAdapter = new FoodAdapter(mFoodList);
        rv_product_list.setAdapter(foodAdapter);
        return view;
    }

}

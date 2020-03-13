package cs.bham.ac.uk.assignment3.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import cs.bham.ac.uk.assignment3.R;
import cs.bham.ac.uk.assignment3.bean.FoodBean;
import cs.bham.ac.uk.assignment3.ui.adapter.FoodAdapter;
import cs.bham.ac.uk.assignment3.utils.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment {

    private ArrayList<FoodBean> mFoodList;
    private RecyclerView rv_product_list;
    private FoodAdapter foodAdapter;
    private LinearLayoutManager layoutManager;

    public FoodFragment() {
        mFoodList = Utils.getMockFoodList();
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        rv_product_list = view.findViewById(R.id.rv_product_list);
        layoutManager = new LinearLayoutManager(getActivity());
        rv_product_list.setLayoutManager(layoutManager);
        foodAdapter = new FoodAdapter(getActivity(),mFoodList);
        rv_product_list.setAdapter(foodAdapter);
        return view;
    }

}

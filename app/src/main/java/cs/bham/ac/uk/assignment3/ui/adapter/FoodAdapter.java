package cs.bham.ac.uk.assignment3.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;

import java.util.List;

import cs.bham.ac.uk.assignment3.FoodsDetailsActivity;
import cs.bham.ac.uk.assignment3.R;
import cs.bham.ac.uk.assignment3.bean.FoodBean;
import cs.bham.ac.uk.assignment3.sp.SPFoodManager;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private Context mContext;
    private List<FoodBean> mFoodList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_food;
        TextView tv_food_name;
        TextView tv_food_meal;
        TextView tv_food_time;
        ImageView iv_food_fav;

        public ViewHolder(View view) {
            super(view);
            ll_food = view.findViewById(R.id.ll_food);
            tv_food_name = view.findViewById(R.id.tv_food_name);
            tv_food_meal = view.findViewById(R.id.tv_food_meal);
            tv_food_time = view.findViewById(R.id.tv_food_time);
            iv_food_fav = view.findViewById(R.id.iv_food_fav);
        }
    }

    public FoodAdapter(Context context, List<FoodBean> foodList) {
        this.mContext = context;
        this.mFoodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final FoodBean foodBean = mFoodList.get(position);
        holder.tv_food_name.setText(foodBean.getName());
        holder.tv_food_meal.setText(foodBean.getMeal());
        holder.tv_food_time.setText(String.valueOf(foodBean.getTime()));
        if (foodBean.isFav()) {
            holder.iv_food_fav.setImageResource(R.mipmap.ic_fav);
        } else {
            holder.iv_food_fav.setImageResource(R.mipmap.ic_fav_normal);
        }
        holder.iv_food_fav.setOnClickListener(view -> {
            boolean isFav = mFoodList.get(position).isFav();
            mFoodList.get(position).setFav(!isFav);
            notifyItemChanged(position);
            if (isFav) {
                SPFoodManager.removeFood(mFoodList.get(position));
            } else {
                SPFoodManager.addFood(mFoodList.get(position));
            }
        });
        holder.ll_food.setOnClickListener(view -> FoodsDetailsActivity.startActivity(mContext, foodBean.getId()));
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }
}

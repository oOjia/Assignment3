package cs.bham.ac.uk.assignment3;

import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.ViewHolder> {

    private ArrayList<Foods> foodsList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    public FoodsAdapter(ArrayList<Foods> foodsList) {
        this.foodsList = foodsList;
    }

    @Override
    public FoodsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder vh = new ViewHolder(new TextView(parent.getContext()));
        vh.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(foodsList.get(position).toString());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), FoodsDetailsActivity.class);
                intent.putExtra("id", foodsList.get(position).getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodsList.size();
    }
}


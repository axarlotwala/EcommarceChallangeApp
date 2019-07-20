package com.challenge.ecommarcechallangeapp.Adapter;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




import com.challenge.ecommarcechallangeapp.Models.CategoryModel;
import com.challenge.ecommarcechallangeapp.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {

    private Context context;
    private ArrayList<CategoryModel> categoryModels;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryModels) {
        this.context = context;
        this.categoryModels = categoryModels;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_row_list,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

        holder.tv_cat.setText(categoryModels.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        CardView cat_card;
        TextView tv_cat;

        public Holder(@NonNull View itemView) {
            super(itemView);

            cat_card = itemView.findViewById(R.id.cat_card);
            tv_cat = itemView.findViewById(R.id.tv_cat);
        }
    }
}

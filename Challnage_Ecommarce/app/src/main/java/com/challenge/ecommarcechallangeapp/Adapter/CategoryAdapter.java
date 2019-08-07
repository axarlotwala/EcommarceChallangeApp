package com.challenge.ecommarcechallangeapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.challenge.ecommarcechallangeapp.Fragment.HomeFragment;
import com.challenge.ecommarcechallangeapp.Models.CategoryModel;
import com.challenge.ecommarcechallangeapp.R;
import com.challenge.ecommarcechallangeapp.Utlities.OpenHelper;
import com.challenge.ecommarcechallangeapp.Utlities.PassData;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {

    private Context context;
    private ArrayList<CategoryModel> categoryModels;
    private ArrayList<Integer> disableItem;
    private int catId, isCheckPosition = -1;
    PassData passData;
    boolean isSelected = true;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryModels, PassData passData) {
        this.context = context;
        this.categoryModels = categoryModels;
        this.passData = passData;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item_rowlist, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {

        holder.tv_catName.setText(categoryModels.get(i).getName());

        if (isCheckPosition == i){
            holder.tv_catName.setTextColor(context.getResources().getColor(R.color.color_white));
            holder.relativeCategory.setBackground(context.getResources().getDrawable(R.drawable.rectangle));
        }else {
            holder.relativeCategory.setBackground(context.getResources().getDrawable(R.drawable.non_select));
            holder.tv_catName.setTextColor(context.getResources().getColor(R.color.defaultTextColor));
        }

        holder.relativeCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                catId = categoryModels.get(i).getCategory_id();
                passData.setCatId(catId);

                isCheckPosition = i;
                notifyDataSetChanged();
            }

        });

    }



    @Override
    public int getItemCount() {
        return categoryModels.size();
    }


    class Holder extends RecyclerView.ViewHolder {

        RelativeLayout relativeCategory;
        TextView tv_catName;

        public Holder(@NonNull View itemView) {
            super(itemView);

            tv_catName = itemView.findViewById(R.id.tv_catName);
            relativeCategory = itemView.findViewById(R.id.relativeCategory);

        }
    }

}

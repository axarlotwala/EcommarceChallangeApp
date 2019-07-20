package com.challenge.ecommarcechallangeapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.challenge.ecommarcechallangeapp.Models.CategoryModel;
import com.challenge.ecommarcechallangeapp.Models.Data;
import com.challenge.ecommarcechallangeapp.Models.ProductsModel;
import com.challenge.ecommarcechallangeapp.R;

import java.util.ArrayList;

public class InCategoryProductAdapter extends RecyclerView.Adapter<InCategoryProductAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Data> dataArrayList;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public InCategoryProductAdapter(Context context, ArrayList<Data> dataArrayList) {
        this.context = context;
        this.dataArrayList = dataArrayList;

        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_row_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.tvCatName.setText(dataArrayList.get(i).getCategoryName());
        LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        viewHolder.catProductList.setLayoutManager(manager);

        InnerItemAdapter innerItemAdapter = new InnerItemAdapter(context,dataArrayList.get(i).getProductsModels());
        viewHolder.catProductList.setAdapter(innerItemAdapter);

    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvCatName;
        RecyclerView catProductList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCatName = itemView.findViewById(R.id.tvCatName);
            catProductList = itemView.findViewById(R.id.catProductList);
        }
    }
}

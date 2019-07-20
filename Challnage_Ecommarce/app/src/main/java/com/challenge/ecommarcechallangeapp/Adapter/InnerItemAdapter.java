package com.challenge.ecommarcechallangeapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.challenge.ecommarcechallangeapp.Models.ProductsModel;
import com.challenge.ecommarcechallangeapp.R;
import com.challenge.ecommarcechallangeapp.Utlities.OpenHelper;

import java.util.ArrayList;

public class InnerItemAdapter extends RecyclerView.Adapter<InnerItemAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProductsModel> productsModels;

    public InnerItemAdapter(Context context, ArrayList<ProductsModel> productsModels) {
        this.context = context;
        this.productsModels = productsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inner_item_rowlist,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.tvProductName.setText(productsModels.get(i).getName());
        if (productsModels.get(i).getDiscounted_price().contains("0.00")){
            viewHolder.tvProductPrice.setText(productsModels.get(i).getPrice());
        }else {
            viewHolder.tvProductPrice.setText(productsModels.get(i).getDiscounted_price());
        }
        Glide.with(context).asGif().load(OpenHelper.PRODUCT_IMAGE_URL+productsModels.get(i).getThumbnail()).into(viewHolder.imgProduct);

    }

    @Override
    public int getItemCount() {
        return productsModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct;
        TextView tvProductName,tvProductPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
        }
    }
}

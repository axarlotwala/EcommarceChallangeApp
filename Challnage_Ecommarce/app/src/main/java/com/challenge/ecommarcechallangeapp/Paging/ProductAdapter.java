package com.challenge.ecommarcechallangeapp.Paging;

import android.annotation.SuppressLint;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.challenge.ecommarcechallangeapp.Models.ProductsModel;
import com.challenge.ecommarcechallangeapp.R;
import com.challenge.ecommarcechallangeapp.ResponseModel.ProductResponse;
import com.challenge.ecommarcechallangeapp.Utlities.OpenHelper;

import java.text.NumberFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Product_Holder> {


    private Context context;
    private ArrayList<ProductsModel> productsModels;

    public ProductAdapter(Context context, ArrayList<ProductsModel> productsModels) {
        this.context = context;
        this.productsModels = productsModels;
    }

    @NonNull
    @Override
    public Product_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_row_list,viewGroup,false);
        return new Product_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Product_Holder product_holder, int i) {

        /*String price = productsModels.get(i).getPrice();
        String discPrice = productsModels.get(i).getDiscounted_price();

        Double dPrice = Double.valueOf(price);
        Double discDprice = Double.valueOf(discPrice);

        Double total = dPrice - discDprice;*/
        if (productsModels.get(i).getDiscounted_price().contains("0.00")){
            product_holder.tv_discount_price.setVisibility(View.GONE);
            product_holder.tv_original_price.setVisibility(View.VISIBLE);
            product_holder.tv_original_price.setText("Rs. "+productsModels.get(i).getPrice());
        }else {
            product_holder.tv_discount_price.setVisibility(View.VISIBLE);
            product_holder.tv_original_price.setVisibility(View.GONE);
            product_holder.tv_discount_price.setText("Rs "+productsModels.get(i).getDiscounted_price());
        }

        /*if (!productsModels.get(i).getDiscounted_price().contentEquals("0.00")){
            product_holder.tv_discount_price.setVisibility(View.VISIBLE);
            product_holder.tv_original_price.setText("Rs. "+productsModels.get(i).getDiscounted_price());
        }*/
        Glide.with(context).asGif().load(OpenHelper.PRODUCT_IMAGE_URL+productsModels.get(i).getThumbnail()).into(product_holder.iv_shirt_image);
        product_holder.tv_shirt_name.setText(productsModels.get(i).getName());


        product_holder.tvProductId.setText(""+productsModels.get(i).getProduct_id());
    }

    @Override
    public int getItemCount() {
        return productsModels.size();
    }


    public static class Product_Holder extends RecyclerView.ViewHolder {

        private ImageView iv_shirt_image;
        private TextView tv_shirt_name,tv_original_price,tv_discount_price,tvProductId;



        public Product_Holder(@NonNull View itemView) {
            super(itemView);

            tvProductId = itemView.findViewById(R.id.tvProductId);
            iv_shirt_image = itemView.findViewById(R.id.iv_shirt_image);
            tv_shirt_name = itemView.findViewById(R.id.tv_shirt_name);
            tv_original_price = itemView.findViewById(R.id.tv_original_price);
            tv_discount_price = itemView.findViewById(R.id.tv_discount_price);
        }
    }

    public void addProduct(ArrayList<ProductsModel> models){

        for (ProductsModel model : models){
            productsModels.add(model);
        }
        notifyDataSetChanged();
    }

}

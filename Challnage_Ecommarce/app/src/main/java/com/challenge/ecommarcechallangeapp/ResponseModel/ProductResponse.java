package com.challenge.ecommarcechallangeapp.ResponseModel;

import com.challenge.ecommarcechallangeapp.Models.ProductsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public  class ProductResponse {

    @Expose
    @SerializedName("count")
    private int count;

    @Expose
    @SerializedName("rows")
    private ArrayList<ProductsModel> productsModels;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<ProductsModel> getProductsModels() {
        return productsModels;
    }

    public void setProductsModels(ArrayList<ProductsModel> productsModels) {
        this.productsModels = productsModels;
    }
}

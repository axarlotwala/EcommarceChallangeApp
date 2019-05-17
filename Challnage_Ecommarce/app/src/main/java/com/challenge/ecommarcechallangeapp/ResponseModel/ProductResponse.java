package com.challenge.ecommarcechallangeapp.ResponseModel;

import com.challenge.ecommarcechallangeapp.Models.ProductsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public  class ProductResponse {

    @Expose
    @SerializedName("rows")
    private ArrayList<ProductsModel> productsModels;

    public ArrayList<ProductsModel> getProductsModels() {
        return productsModels;
    }

    public void setProductModels(ArrayList<ProductsModel> Productmodels) {
        this.productsModels = Productmodels;
    }

}

package com.challenge.ecommarcechallangeapp.Models;

import java.util.ArrayList;

public class Data {

    String categoryName;
    ArrayList<ProductsModel> productsModels;

    public Data() {
    }

    public Data(String categoryName, ArrayList<ProductsModel> productsModels) {
        this.categoryName = categoryName;
        this.productsModels = productsModels;
    }

    public ArrayList<ProductsModel> getProductsModels() {
        return productsModels;
    }

    public void setProductsModels(ArrayList<ProductsModel> productsModels) {
        this.productsModels = productsModels;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

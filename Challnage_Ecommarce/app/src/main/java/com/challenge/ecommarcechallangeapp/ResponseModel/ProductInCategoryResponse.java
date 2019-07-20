package com.challenge.ecommarcechallangeapp.ResponseModel;

import com.challenge.ecommarcechallangeapp.Models.CategoryModel;
import com.challenge.ecommarcechallangeapp.Models.ProductsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public  class ProductInCategoryResponse {


    @Expose
    @SerializedName("rows")
    private ArrayList<ProductsModel> rows;

    @Expose
    @SerializedName("count")
    private int count;

    public ArrayList<ProductsModel> getRows() {
        return rows;
    }

    public void setRows(ArrayList<ProductsModel> rows) {
        this.rows = rows;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}

package com.challenge.ecommarcechallangeapp.ResponseModel;

import com.challenge.ecommarcechallangeapp.Models.CategoryModel;
import com.challenge.ecommarcechallangeapp.Models.ProductsModel;

public class HomeResponse {

    CategoryModel categoryModel;
    ProductsModel productsModel;

    public HomeResponse(CategoryModel categoryModel, ProductsModel productsModel) {
        this.categoryModel = categoryModel;
        this.productsModel = productsModel;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    public ProductsModel getProductsModel() {
        return productsModel;
    }

    public void setProductsModel(ProductsModel productsModel) {
        this.productsModel = productsModel;
    }
}

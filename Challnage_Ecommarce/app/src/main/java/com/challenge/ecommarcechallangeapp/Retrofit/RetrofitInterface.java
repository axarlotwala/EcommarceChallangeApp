package com.challenge.ecommarcechallangeapp.Retrofit;

import com.challenge.ecommarcechallangeapp.Models.CategoryModel;
import com.challenge.ecommarcechallangeapp.Models.DepartmentsModel;
import com.challenge.ecommarcechallangeapp.ResponseModel.ProductInCategoryResponse;
import com.challenge.ecommarcechallangeapp.ResponseModel.ProductResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    /*Key - eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcl9pZCI6MTIsIm5hbWUiOiJFZGVyIFRhdmVpcmEiLCJyb2xlIjoiY3VzdG9tZXIiLCJpYXQiOjE1NTA3ODYyMjAsImV4cCI6MTU1MDg3MjYyMH0.QEGdry367EQNxBqzuUDCGJscWkq8YQwJdGBgV3hztR0*/

    @GET("departments")
    Call<ArrayList<DepartmentsModel>> Department_Call();

    @GET("categories/inDepartment/{department_id}")
    Call<ArrayList<CategoryModel>> Category_Call(@Path("department_id") int dept_id);

    @GET("products")
    Call<ProductResponse> PRODUCT_MODELS_CALL(
            @Query("page") int page,
            @Query("limit") int limit);

    @GET("products/inCategory/{category_id}")
    Call<ProductInCategoryResponse> IN_CATEGORY_RESPONSE_CALL(
            @Path("category_id") int catId,
            @Query("page") int page);



}

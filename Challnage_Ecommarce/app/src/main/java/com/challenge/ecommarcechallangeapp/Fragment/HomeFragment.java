package com.challenge.ecommarcechallangeapp.Fragment;


import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import com.challenge.ecommarcechallangeapp.Adapter.CategoryAdapter;
import com.challenge.ecommarcechallangeapp.Models.CategoryModel;
import com.challenge.ecommarcechallangeapp.Models.ProductsModel;
import com.challenge.ecommarcechallangeapp.Paging.ProductAdapter;
import com.challenge.ecommarcechallangeapp.R;
import com.challenge.ecommarcechallangeapp.ResponseModel.ProductResponse;
import com.challenge.ecommarcechallangeapp.Retrofit.RetrofitClient;
import com.challenge.ecommarcechallangeapp.Retrofit.RetrofitInterface;
import com.challenge.ecommarcechallangeapp.Utlities.OpenHelper;
import com.challenge.ecommarcechallangeapp.Utlities.PassData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements PassData {

    RecyclerView list_product,list_category ;
    ProductAdapter productAdapter;
    FrameLayout frame_first, frame_category;
    PassData passData;
    ArrayList<CategoryModel> categoryModels;


    int dept_id;
    String catName;


    private int PAGE_NUMBER = 1;
    private int ITEM_PER_PAGE = 11;
    private boolean isLoading = true;
    private int PAST_VISIBLE_ITEM, VISIBLE_ITEM_COUNT, TOTAL_ITEM_COUNT, PREVIOUS_TOTAL = 0;
    private int VIEW_THRESHOLD = 11;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        list_product = view.findViewById(R.id.list_product);
        list_category = view.findViewById(R.id.list_category);
        frame_first = view.findViewById(R.id.frame_first);
        frame_category = view.findViewById(R.id.frame_category);
        passData = this;

        setBundle();
        SetProducts();

        return view;
    }

    /*set Bundle*/
    private void setBundle(){


        Bundle bundle = getArguments();
        if (bundle != null) {
            //frame_first.setVisibility(View.VISIBLE);
            dept_id = bundle.getInt(OpenHelper.DEPT_ID);
            Log.e("getBun_id", "SetCategory: " + ""+dept_id);
        }
        setCategory(dept_id);
    }

    /*Set Default All Product From Server*/
    private void SetProducts() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        list_product.setLayoutManager(linearLayoutManager);
        list_product.setHasFixedSize(true);

        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofitClient().create(RetrofitInterface.class);
        Call<ProductResponse> productResponseCall = retrofitInterface.PRODUCT_MODELS_CALL(PAGE_NUMBER, ITEM_PER_PAGE);

        productResponseCall.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {

                ArrayList<ProductsModel> responses = response.body().getProductsModels();
                productAdapter = new ProductAdapter(getActivity(), responses);
                list_product.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.e("product_log1", "onFailure: " + t.toString());
            }
        });

        list_product.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                VISIBLE_ITEM_COUNT = linearLayoutManager.getChildCount();
                TOTAL_ITEM_COUNT = linearLayoutManager.getItemCount();
                PAST_VISIBLE_ITEM = linearLayoutManager.findFirstCompletelyVisibleItemPosition();

                if (dy > 0) {
                    if (isLoading) {
                        if (TOTAL_ITEM_COUNT > PREVIOUS_TOTAL) {
                            isLoading = false;
                            PREVIOUS_TOTAL = TOTAL_ITEM_COUNT;
                        }
                    }

                    if (!isLoading && (TOTAL_ITEM_COUNT - VISIBLE_ITEM_COUNT) <= (PAST_VISIBLE_ITEM + VIEW_THRESHOLD)) {

                        PAGE_NUMBER++;
                        setPagination();
                        isLoading = true;
                    }
                }
            }
        });
    }

    /*Set  Pagination using Recyclview AddOn Scroll Listner Interface*/
    private void setPagination() {

        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofitClient().create(RetrofitInterface.class);
        Call<ProductResponse> productResponseCall = retrofitInterface.PRODUCT_MODELS_CALL(PAGE_NUMBER, ITEM_PER_PAGE);

        productResponseCall.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {

                ArrayList<ProductsModel> addImage = response.body().getProductsModels();
                productAdapter.addProduct(addImage);
/*                productAdapter = new ProductAdapter(getActivity(), addImage);
                list_product.setAdapter(productAdapter);*/

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.e("product_log1", "onFailure: " + t.toString());
            }
        });
    }

    /*set Catgeory  Api if get department ID*/
    private void setCategory(int dept_id) {

        categoryModels  = new ArrayList<>();
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofitClient().create(RetrofitInterface.class);
        Call<ArrayList<CategoryModel>> arrayListCall = retrofitInterface.Category_Call(dept_id);
        arrayListCall.enqueue(new Callback<ArrayList<CategoryModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoryModel>> call, Response<ArrayList<CategoryModel>> response) {

                categoryModels = response.body();
                if (categoryModels != null){
                    frame_category.setVisibility(View.VISIBLE);
                    LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                    list_category.setLayoutManager(manager);

                    CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(),categoryModels,passData);
                    list_category.setAdapter(categoryAdapter);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<CategoryModel>> call, Throwable t) {

                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setCatId(int catId) {

        /*get category id and set Product Category wise*/

        if (catId != 0){

            RetrofitInterface retrofitInterface = RetrofitClient.getRetrofitClient().create(RetrofitInterface.class);
        }

    }



}

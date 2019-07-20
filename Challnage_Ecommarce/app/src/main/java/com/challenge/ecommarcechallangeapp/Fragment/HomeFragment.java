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

import com.challenge.ecommarcechallangeapp.Adapter.InCategoryProductAdapter;
import com.challenge.ecommarcechallangeapp.Models.CategoryModel;
import com.challenge.ecommarcechallangeapp.Models.Data;
import com.challenge.ecommarcechallangeapp.Models.ProductsModel;
import com.challenge.ecommarcechallangeapp.Paging.ProductAdapter;
import com.challenge.ecommarcechallangeapp.R;
import com.challenge.ecommarcechallangeapp.ResponseModel.ProductInCategoryResponse;
import com.challenge.ecommarcechallangeapp.ResponseModel.ProductResponse;
import com.challenge.ecommarcechallangeapp.Retrofit.RetrfitClient;
import com.challenge.ecommarcechallangeapp.Retrofit.RetrofitInterface;
import com.challenge.ecommarcechallangeapp.Utlities.OpenHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView list_product, mainRecyclerView;
    ProductAdapter productAdapter;
    FrameLayout frame_first, frame_second;

    int dept_id, catId;
    private int PAGE_NUMBER = 1;
    private int ITEM_PER_PAGE = 11;
    private boolean isLoading = true;
    private int PAST_VISIBLE_ITEM, VISIBLE_ITEM_COUNT, TOTAL_ITEM_COUNT, PREVIOUS_TOTAL = 0;
    private int VIEW_THRESHOLD = 11;

    ArrayList<CategoryModel> models;
    ArrayList<ProductsModel> productsModels;
    ArrayList<Data> dataArrayList;

    String catName;

    Data data = new Data();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        list_product = view.findViewById(R.id.list_product);
        mainRecyclerView = view.findViewById(R.id.mainRecyclerView);
        frame_first = view.findViewById(R.id.frame_first);
        frame_second = view.findViewById(R.id.frame_second);

        SetProducts();
        getBundles();

        return view;
    }

    /*get Department Id Wise Show Category*/
    private void getBundles() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            //frame_first.setVisibility(View.VISIBLE);
            dept_id = bundle.getInt(OpenHelper.DEPT_ID);
            Log.e("getBun_id", "SetCategory: " + dept_id);
        }
        setCategory(dept_id);
        //setInitRecyclerView(dept_id);
    }


    /*Set Default All Product From Server*/
    private void SetProducts() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        list_product.setLayoutManager(linearLayoutManager);
        list_product.setHasFixedSize(true);

        RetrofitInterface retrofitInterface = RetrfitClient.getRetrofitClient().create(RetrofitInterface.class);

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

        RetrofitInterface retrofitInterface = RetrfitClient.getRetrofitClient().create(RetrofitInterface.class);
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
        models = new ArrayList<>();
        RetrofitInterface retrofitInterface = RetrfitClient.getRetrofitClient().create(RetrofitInterface.class);
        Call<ArrayList<CategoryModel>> arrayListCall = retrofitInterface.Category_Call(dept_id);
        arrayListCall.enqueue(new Callback<ArrayList<CategoryModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoryModel>> call, Response<ArrayList<CategoryModel>> response) {

                models = response.body();
                for (int i = 0; i < models.size(); i++) {
                    catId = models.get(i).getCategory_id();
                    catName = models.get(i).getName();
                    setCategoryProduct(catId, models,catName);
                }
                //Log.d("ModelsSize", "" + models.size());
            }

            @Override
            public void onFailure(Call<ArrayList<CategoryModel>> call, Throwable t) {

                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*get category wise product From Server*/
    private void setCategoryProduct(final int catId, final ArrayList<CategoryModel> categoryModels, final String catName) {

        Log.d("getSingleCat", "" + catId);

        productsModels = new ArrayList<>();
        RetrofitInterface anInterface = RetrfitClient.getRetrofitClient().create(RetrofitInterface.class);
        Call<ProductInCategoryResponse> inCategoryResponseCall = anInterface.IN_CATEGORY_RESPONSE_CALL(catId, 1);
        inCategoryResponseCall.enqueue(new Callback<ProductInCategoryResponse>() {
            @Override
            public void onResponse(Call<ProductInCategoryResponse> call, Response<ProductInCategoryResponse> response) {

                productsModels = response.body().getRows();
                Log.d("productCat", "" + productsModels.size());

                data.setCategoryName(catName);
                data.setProductsModels(productsModels);

                dataArrayList = new ArrayList<>();

                HashMap<String,ArrayList<ProductsModel>> listHashMap = new HashMap<String, ArrayList<ProductsModel>>();
                for (int j=0;j<categoryModels.size();j++){
                    //listHashMap.put(,productsModels.get(j).getName())
                    listHashMap.put(categoryModels.get(j).getName(), productsModels);
                    dataArrayList.add(data);
                    Log.d("ListMap",""+listHashMap.put(categoryModels.get(j).getName(),productsModels));
                }

               // Log.d("CatName",""+catName);

                /*Gson gson  = new Gson();
                String pList = gson.toJson(productsModels);*/


                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                mainRecyclerView.setLayoutManager(manager);

                InCategoryProductAdapter inCategoryProductAdapter = new InCategoryProductAdapter(getActivity(), dataArrayList);
                mainRecyclerView.setAdapter(inCategoryProductAdapter);

                /*
                Log.d("DataArray", "" + dataArrayList.size());
                Log.d("GetCat", "" + data.getCategoryName());*/
                // Log.d("DataList",""+dataArrayList.get(i).getCategoryName() + " :"+ dataArrayList.get(i).getProductsModels());

            }

            @Override
            public void onFailure(Call<ProductInCategoryResponse> call, Throwable t) {

                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

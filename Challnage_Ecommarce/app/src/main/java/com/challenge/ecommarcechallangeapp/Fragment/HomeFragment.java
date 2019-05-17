package com.challenge.ecommarcechallangeapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.challenge.ecommarcechallangeapp.Adapter.CategoryAdapter;
import com.challenge.ecommarcechallangeapp.Models.CategoryModel;
import com.challenge.ecommarcechallangeapp.R;
import com.challenge.ecommarcechallangeapp.Retrofit.RetrfitClient;
import com.challenge.ecommarcechallangeapp.Retrofit.RetrofitInterface;
import com.challenge.ecommarcechallangeapp.Utlities.OpenHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView list_category;
    LinearLayoutManager linearLayoutManager;
    CategoryAdapter categoryAdapter;
    int dept_id;
    FrameLayout frame_first,frame_second;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        list_category = view.findViewById(R.id.list_category);
        frame_first = view.findViewById(R.id.frame_first);
        frame_second = view.findViewById(R.id.frame_second);
        getBundles();

        return view;
    }

    private void getBundles(){
        Bundle bundle = getArguments();
        if (bundle != null) {
            frame_first.setVisibility(View.VISIBLE);
            dept_id = bundle.getInt(OpenHelper.DEPT_ID);
            Log.e("getBun_id", "SetCategory: " + dept_id);
        }
        SetCategory(dept_id);
    }


    private void SetCategory(int dept_id) {

        try {

            RetrofitInterface anInterface = RetrfitClient.getRetrofitClient().create(RetrofitInterface.class);
            Call<ArrayList<CategoryModel>> arrayListCall = anInterface.Category_Call(dept_id);
            arrayListCall.enqueue(new Callback<ArrayList<CategoryModel>>() {
                @Override
                public void onResponse(Call<ArrayList<CategoryModel>> call, Response<ArrayList<CategoryModel>> response) {
                    CategoryListing(response.body());
                }

                @Override
                public void onFailure(Call<ArrayList<CategoryModel>> call, Throwable t) {
                    Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void CategoryListing(ArrayList<CategoryModel> models){

        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        list_category.setLayoutManager(linearLayoutManager);

        categoryAdapter = new CategoryAdapter(getActivity(), models);
        list_category.setAdapter(categoryAdapter);
    }
}

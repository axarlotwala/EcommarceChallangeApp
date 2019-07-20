package com.challenge.ecommarcechallangeapp.Activity;

import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


import com.challenge.ecommarcechallangeapp.Adapter.DepartmentAdapter;
import com.challenge.ecommarcechallangeapp.Fragment.HomeFragment;
import com.challenge.ecommarcechallangeapp.Models.DepartmentsModel;
import com.challenge.ecommarcechallangeapp.R;
import com.challenge.ecommarcechallangeapp.Retrofit.RetrfitClient;
import com.challenge.ecommarcechallangeapp.Retrofit.RetrofitInterface;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NavigationMenuActivity extends AppCompatActivity {

    public static RecyclerView department_list;
    public static DrawerLayout drawer;
    public static LinearLayoutManager linearLayoutManager;
     private DepartmentAdapter departmentAdapter;
    public static ActionBarDrawerToggle toggle;
    ArrayList<DepartmentsModel> departmentsModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);


        department_list = findViewById(R.id.department_list);
        drawer = findViewById(R.id.drawer_layout);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        SetDepartment();

        toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toggle.syncState();

        Fragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content,fragment).addToBackStack(null).commit();

    }


    private void SetDepartment() {

        /*Department department = new Department();*/
        RetrofitInterface retrofitInterface = RetrfitClient.getRetrofitClient().create(RetrofitInterface.class);

        Call<ArrayList<DepartmentsModel>> call_dept_model = retrofitInterface.Department_Call();
        call_dept_model.enqueue(new Callback<ArrayList<DepartmentsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DepartmentsModel>> call, Response<ArrayList<DepartmentsModel>> response) {

                DepartmentList(response.body());

            }

            @Override
            public void onFailure(Call<ArrayList<DepartmentsModel>> call, Throwable t) {
                Toast.makeText(NavigationMenuActivity.this,t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void DepartmentList(ArrayList<DepartmentsModel> Department) {

        linearLayoutManager  = new LinearLayoutManager(NavigationMenuActivity.this);
        departmentAdapter = new DepartmentAdapter(NavigationMenuActivity.this,Department);
        department_list.setLayoutManager(linearLayoutManager);
        department_list.setAdapter(departmentAdapter);
        department_list.addItemDecoration(new DividerItemDecoration(NavigationMenuActivity.this, DividerItemDecoration.VERTICAL));
        department_list.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

            if (getSupportFragmentManager().getBackStackEntryCount() == 1){
                finish();
            }
        } else {
            super.onBackPressed();
        }
    }

}

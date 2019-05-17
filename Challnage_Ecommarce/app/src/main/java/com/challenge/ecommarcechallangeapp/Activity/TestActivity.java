package com.challenge.ecommarcechallangeapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.challenge.ecommarcechallangeapp.Adapter.ExpandableAdapter;
import com.challenge.ecommarcechallangeapp.Models.Category;
import com.challenge.ecommarcechallangeapp.Models.Department;
import com.challenge.ecommarcechallangeapp.R;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    RecyclerView  list_expandable_test;
    ExpandableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        list_expandable_test = findViewById(R.id.list_expandable_test);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TestActivity.this);
        list_expandable_test.setLayoutManager(linearLayoutManager);

        ArrayList<Department> departments  = new ArrayList<>();

        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("lests"));
        categories.add(new Category("Mans"));
        categories.add(new Category("Raymond"));
        categories.add(new Category("Zara"));
        categories.add(new Category("Levis"));

        Department department = new Department("Nation",categories);
        departments.add(department);

        ArrayList<Category> categories1 = new ArrayList<>();
        categories1.add(new Category("ests"));
        categories1.add(new Category("Man"));
        categories1.add(new Category("Raymon"));
        categories1.add(new Category("ara"));
        categories1.add(new Category("Levis"));

        Department department1 = new Department("Regional",categories1);
        departments.add(department1);

        RecyclerView.ItemAnimator animator = list_expandable_test.getItemAnimator();
        if (animator instanceof DefaultItemAnimator){
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        adapter = new ExpandableAdapter(departments);

        list_expandable_test.setAdapter(adapter);


    }

}

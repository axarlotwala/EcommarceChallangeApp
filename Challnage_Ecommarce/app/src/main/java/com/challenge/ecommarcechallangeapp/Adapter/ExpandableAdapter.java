package com.challenge.ecommarcechallangeapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.challenge.ecommarcechallangeapp.R;
import com.challenge.ecommarcechallangeapp.ViewHolder.CategoryViewHolder;
import com.challenge.ecommarcechallangeapp.ViewHolder.DepartmentViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class ExpandableAdapter {

/*
    public ExpandableAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public DepartmentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_dept_item,parent,false);
        return new DepartmentViewHolder(view);
    }

    @Override
    public CategoryViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_category_item,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(CategoryViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {

        final Category category = (Category) group.getItems().get(childIndex);
        holder.setTv_item_category(category);
    }

    @Override
    public void onBindGroupViewHolder(DepartmentViewHolder holder, int flatPosition, ExpandableGroup group) {

        final Department department = (Department) group;
        holder.setDept_title(department);
    }*/
}

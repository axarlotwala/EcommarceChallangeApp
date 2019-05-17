package com.challenge.ecommarcechallangeapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Department extends ExpandableGroup<Category> {


    public Department(String title, List<Category> items) {
        super(title, items);
    }


}

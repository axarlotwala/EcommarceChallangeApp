package com.challenge.ecommarcechallangeapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartmentsModel {

    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("department_id")
    private int department_id;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }
}

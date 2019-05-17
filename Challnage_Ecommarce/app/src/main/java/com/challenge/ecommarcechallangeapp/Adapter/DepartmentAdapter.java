package com.challenge.ecommarcechallangeapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.challenge.ecommarcechallangeapp.Activity.NavigationMenuActivity;
import com.challenge.ecommarcechallangeapp.Fragment.HomeFragment;
import com.challenge.ecommarcechallangeapp.Models.DepartmentsModel;
import com.challenge.ecommarcechallangeapp.R;
import com.challenge.ecommarcechallangeapp.Utlities.OpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.challenge.ecommarcechallangeapp.Activity.NavigationMenuActivity.department_list;
import static com.challenge.ecommarcechallangeapp.Activity.NavigationMenuActivity.drawer;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {


    Context context;
    ArrayList<DepartmentsModel> DepartmentModel;
    int dept_id;

    public DepartmentAdapter(Context context, ArrayList<DepartmentsModel> departmentModel) {
        this.context = context;
        DepartmentModel = departmentModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dept_item_rowlist, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv_depte.setText(DepartmentModel.get(i).getName());
        viewHolder.linear_dept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, DepartmentModel.get(i).getName(), Toast.LENGTH_SHORT).show();

                dept_id = DepartmentModel.get(i).getDepartment_id();
                Fragment fragment = new HomeFragment();
                AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();
                appCompatActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .addToBackStack(null).commit();



                Bundle bundle = new Bundle();
                bundle.putInt(OpenHelper.DEPT_ID, dept_id);
                Log.e("pass_id", "onClick: " + dept_id);


                fragment.setArguments(bundle);

                if (!drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.closeDrawer(Gravity.START);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return DepartmentModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linear_dept;
        TextView tv_depte;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linear_dept = itemView.findViewById(R.id.linear_dept);
            tv_depte = itemView.findViewById(R.id.tv_depte);
        }
    }
}

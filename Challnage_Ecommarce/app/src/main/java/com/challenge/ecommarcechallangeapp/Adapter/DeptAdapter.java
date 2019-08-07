package com.challenge.ecommarcechallangeapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.challenge.ecommarcechallangeapp.Fragment.HomeFragment;
import com.challenge.ecommarcechallangeapp.Models.DepartmentsModel;
import com.challenge.ecommarcechallangeapp.R;
import com.challenge.ecommarcechallangeapp.Utlities.OpenHelper;

import java.util.ArrayList;

import static com.challenge.ecommarcechallangeapp.Activity.NavigationMenuActivity.drawer;

public class DeptAdapter extends RecyclerView.Adapter<DeptAdapter.Holder> {

    private Context context;
    private ArrayList<DepartmentsModel> departmentsModels;
    private int deptId;

    public DeptAdapter(Context context, ArrayList<DepartmentsModel> departmentsModels) {
        this.context = context;
        this.departmentsModels = departmentsModels;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dept_item_rowlist,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder,final int i) {


        holder.tv_deptName.setText(departmentsModels.get(i).getName());

        holder.relativeDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deptId = departmentsModels.get(i).getDepartment_id();

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                HomeFragment homeFragment = new HomeFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_content,homeFragment).addToBackStack(null).commit();


                Bundle bundle = new Bundle();
                bundle.putInt(OpenHelper.DEPT_ID,deptId);
                Log.d("Pass_bundle",""+deptId);

                Toast.makeText(context,""+departmentsModels.get(i).getDepartment_id(), Toast.LENGTH_SHORT).show();


                if (!drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.closeDrawer(Gravity.START);
                }

                homeFragment.setArguments(bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return departmentsModels.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView tv_deptName;
        RelativeLayout relativeDept;

        public Holder(@NonNull View itemView) {
            super(itemView);

            tv_deptName = itemView.findViewById(R.id.tv_deptName);
            relativeDept = itemView.findViewById(R.id.relativeDept);

        }
    }
}

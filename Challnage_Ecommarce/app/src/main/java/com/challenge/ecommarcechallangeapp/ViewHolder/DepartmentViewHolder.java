package com.challenge.ecommarcechallangeapp.ViewHolder;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.challenge.ecommarcechallangeapp.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class DepartmentViewHolder {

    /*ImageView iv_item_expand;
    TextView tv_dept_name;


    public DepartmentViewHolder(View itemView) {
        super(itemView);

        tv_dept_name = itemView.findViewById(R.id.tv_dept_name);
        iv_item_expand = itemView.findViewById(R.id.iv_item_expand);
    }


    public void setDept_title(Department department){
        tv_dept_name.setText(department.getTitle());
    }

    @Override
    public void expand() {
        animate_expand();
    }

    @Override
    public void collapse() {
        animate_collapse();
    }

    private void animate_expand(){
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        iv_item_expand.setAnimation(rotate);
    }

    private void animate_collapse(){

        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        iv_item_expand.setAnimation(rotate);

    }*/
}

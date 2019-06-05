package com.assignment.heady.listItems;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.assignment.heady.R;
import com.assignment.heady.db.CategoriesModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewDetailsAdapter extends RecyclerView.Adapter<RecyclerViewDetailsAdapter.RecyclerViewHolder> {

    private List<CategoriesModel> dataModelList;
    private Context context;
    LinearLayout layoutViewAdd;
    LinearLayout.LayoutParams layoutParamsAdd;
    View viewAdd;
    int count = 1;

    public RecyclerViewDetailsAdapter(Context context, List<CategoriesModel> userModelList) {
        this.context = context;
        this.dataModelList = userModelList;


    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_details, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        final CategoriesModel criteria = dataModelList.get(position);


    }

    public void addViewChild(String text, boolean onClickStatus, LinearLayout linearLayoutAddFields){
        View viewAdd = LayoutInflater.from(context).inflate(R.layout.layout_add_view, null);
        LinearLayout layoutViewAdd =   viewAdd.findViewById(R.id.layout_add_view);
        linearLayoutAddFields.addView(layoutViewAdd, layoutParamsAdd);
        TextView textView1 = viewAdd.findViewById(R.id.text1);
        textView1.setText(text);

        Log.e("FORMA", text);
        if(onClickStatus){
            textView1.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }

    }



    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public void addItems(List<CategoriesModel> dataModelList) {
        this.dataModelList = dataModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView andTextview;
        private LinearLayout linearLayoutAddView;
        private TextView contentTextView;


        RecyclerViewHolder(View view) {
            super(view);
            andTextview = view.findViewById(R.id.tv_and);
            linearLayoutAddView = view.findViewById(R.id.linear_layout_add);


        }
    }
}
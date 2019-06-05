package com.assignment.heady.listItems;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.assignment.heady.R;
import com.assignment.heady.db.CategoriesModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<CategoriesModel> categoriesModels;
    private Context context;

    public RecyclerViewAdapter(Context context, List<CategoriesModel> categoriesModels) {
        this.context = context;
        this.categoriesModels = categoriesModels;


    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        final CategoriesModel model = categoriesModels.get(position);
//        Log.e("String ONCLICK", position + "::"+ model.toString());
        holder.itemTextView.setText(model.getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("String ONCLICK", position + "::"+ model.getProducts()[0].getvariableInString());
                Intent intent = new Intent(context, ProductListActivity.class);
                intent.putExtra("id", model.getId());
                intent.putExtra("categoryStatus", true);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoriesModels.size();
    }

    public void addItems(List<CategoriesModel> categoriesModels) {
        this.categoriesModels = categoriesModels;
        notifyDataSetChanged();

    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTextView;
        private ImageView imageView;
        RelativeLayout layout;


        RecyclerViewHolder(View view) {
            super(view);
            itemTextView = view.findViewById(R.id.nameTextView);
            imageView = view.findViewById(R.id.imageview);
            layout = view.findViewById(R.id.layout);


        }
    }
}
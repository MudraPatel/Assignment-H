package com.assignment.heady.listItems;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.assignment.heady.R;
import com.assignment.heady.db.CategoriesModel;
import com.assignment.heady.db.ProductModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.RecyclerViewHolder> {

    private List<ProductModel> productModels;
    private Context context;

    public ProductListAdapter(Context context, List<ProductModel> productModels) {
        this.context = context;
        this.productModels = productModels;


    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        final ProductModel model = productModels.get(position);
//        Log.e("String ONCLICK", position + "::"+ model.toString());
        holder.itemTextView.setText(model.getName());
        holder.itemTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("String ONCLICK", position + "::"+ model.getProducts()[0].getvariableInString());
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("id", model.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public void addItems(List<ProductModel> productModels) {
        this.productModels = productModels;
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
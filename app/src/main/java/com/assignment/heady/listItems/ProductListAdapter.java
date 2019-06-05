package com.assignment.heady.listItems;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.assignment.heady.R;
import com.assignment.heady.db.CategoriesModel;
import com.assignment.heady.db.ProductModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

        colorImage(holder.imageView, model.getVariantModel().get(0).getColor());

        holder.layout.setOnClickListener(new View.OnClickListener() {
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

    public void colorImage(ImageView imageView, String stringColor){
        Log.e("POSITION", stringColor + "POSITIO");
        String[] colorsTxt = context.getApplicationContext().getResources().getStringArray(R.array.colors);
        String[] colorsName = context.getApplicationContext().getResources().getStringArray(R.array.colors_name);
        int pos = new ArrayList<String>(Arrays.asList(colorsName)).indexOf(stringColor);
        Log.e("POSITION", pos + "POSITIO");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(pos == -1)
                pos = 6;

            int newColor = Color.parseColor(colorsTxt[pos]);
            imageView.setColorFilter(newColor);
        }
    }
}
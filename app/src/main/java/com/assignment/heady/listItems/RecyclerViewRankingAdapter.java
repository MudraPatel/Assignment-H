package com.assignment.heady.listItems;

import android.content.Context;
import android.content.Intent;
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
import com.assignment.heady.db.RankingModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewRankingAdapter extends RecyclerView.Adapter<RecyclerViewRankingAdapter.RecyclerViewHolder> {

    private List<RankingModel> rankingModels;
    private Context context;

    public RecyclerViewRankingAdapter(Context context, List<RankingModel> rankingModels) {
        this.context = context;
        this.rankingModels = rankingModels;


    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        final RankingModel model = rankingModels.get(position);
        Log.e("String ONCLICK", position + "::"+ model.getRanking());
        holder.itemTextView.setText(model.getRanking());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("String ONCLICK", position + "::"+ model.getProducts()[0].getvariableInString());
                int[] productArray = new int[model.getProductModels().size()];
                int i = 0;
                for(ProductModel productModel: model.getProductModels()){
                    productArray[i++] = productModel.getId();
                }
                Intent intent = new Intent(context, ProductListActivity.class);
                intent.putExtra("id", productArray);
                intent.putExtra("categoryStatus", false);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rankingModels.size();
    }

    public void addItems(List<RankingModel> rankingModels) {
        this.rankingModels = rankingModels;
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
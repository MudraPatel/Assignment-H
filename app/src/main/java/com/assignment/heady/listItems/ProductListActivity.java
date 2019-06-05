package com.assignment.heady.listItems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.assignment.heady.R;
import com.assignment.heady.db.CategoriesModel;
import com.assignment.heady.db.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductListAdapter productListAdapter;
    ProductListViewModel viewModel;
    int id;
    int[] ids;
    boolean categoryStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        categoryStatus = getIntent().getExtras().getBoolean("categoryStatus");

        if(categoryStatus){
            id = getIntent().getExtras().getInt("id");
            Log.e("ID, ", id + "***");

        }else{
            ids = getIntent().getExtras().getIntArray("id");
            Log.e("ID, ", ids.toString() + "***");

        }

        recyclerView = findViewById(R.id.recyclerView);
        productListAdapter = new ProductListAdapter(this, new ArrayList<ProductModel>());
        viewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(this, 2, 35, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productListAdapter);

        if(categoryStatus) {
            viewModel.getItemAndProductList(id).observe(this, new Observer<List<ProductModel>>() {
                @Override
                public void onChanged(List<ProductModel> productModels) {
                    for (ProductModel categoriesModel : productModels) {
                        Log.e("TEST Category", productModels.size() + "LENGHT" + categoriesModel.getName());

                    }
                    productListAdapter.addItems(productModels);
                    if (productModels.size() == 0) {
                        Toast.makeText(ProductListActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            viewModel.getItemAndProductListIds(ids).observe(this, new Observer<List<ProductModel>>() {
                @Override
                public void onChanged(List<ProductModel> productModels) {
                    for (ProductModel categoriesModel : productModels) {
                        Log.e("TEST Category", productModels.size() + "LENGHT" + categoriesModel.getName());

                    }
                    productListAdapter.addItems(productModels);
                    if (productModels.size() == 0) {
                        Toast.makeText(ProductListActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

package com.assignment.heady.listItems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.heady.R;
import com.assignment.heady.db.CategoriesModel;
import com.assignment.heady.db.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    ProductListAdapter productListAdapter;
    ProductListViewModel viewModel;
    int id;
    int[] ids;
    boolean categoryStatus;
    TextView headerText;
    Toolbar toolbar;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        categoryStatus = getIntent().getExtras().getBoolean("categoryStatus");

        if(categoryStatus){
            id = getIntent().getExtras().getInt("id");

        }else{
            ids = getIntent().getExtras().getIntArray("id");
        }


        toolbar = findViewById(R.id.toolbar_header);
        headerText = findViewById(R.id.header_text);
        back = toolbar.findViewById(R.id.iv_back);
        back.setOnClickListener(this);
        headerText.setText(getString(R.string.product_list));

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
                    productListAdapter.addItems(productModels);
                    if (productModels.size() == 0) {
                        Toast.makeText(ProductListActivity.this, getString(R.string.no_data), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            viewModel.getItemAndProductListIds(ids).observe(this, new Observer<List<ProductModel>>() {
                @Override
                public void onChanged(List<ProductModel> productModels) {

                    productListAdapter.addItems(productModels);
                    if (productModels.size() == 0) {
                        Toast.makeText(ProductListActivity.this, getString(R.string.no_data), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }
}

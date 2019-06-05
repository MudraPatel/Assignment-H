package com.assignment.heady.listItems;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.heady.R;
import com.assignment.heady.db.CategoriesModel;
import com.assignment.heady.db.ProductModel;
import com.assignment.heady.db.RankingModel;
import com.assignment.heady.network.MyApplication;
import com.assignment.heady.network.Network;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataListViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView , recyclerViewRanking;
    private BroadcastReceiver mNetworkReceiver;
    ProgressDialog progress;
    RecyclerViewRankingAdapter recyclerViewRankingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(DataListViewModel.class);
        if(Network.isInternetAvailable(MainActivity.this))
            viewModel.loadData();


        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewRanking = findViewById(R.id.recycler_view_ranking);

        recyclerViewAdapter = new RecyclerViewAdapter(this, new ArrayList<CategoriesModel>());
        recyclerViewRankingAdapter =  new RecyclerViewRankingAdapter(this, new ArrayList<RankingModel>());


        /*recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(getResources().getDrawable( R.drawable.divider));
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(recyclerViewAdapter);*/
        recyclerViewRanking.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager  mLayoutManagerHori = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerHori.setReverseLayout(false);
        recyclerViewRanking.setLayoutManager(mLayoutManagerHori);
        recyclerViewRanking.setAdapter(recyclerViewRankingAdapter);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(this, 2, 35, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel.getItemAndCategoryList().observe(this, new Observer<List<CategoriesModel>>() {
            @Override
            public void onChanged(List<CategoriesModel> categoriesModels) {
//                for(CategoriesModel categoriesModel : categoriesModels) {
//                }
//                Log.e("TEST Category", categoriesModels.size() + "LENGHT" + categoriesModels.get(0).getName());
                recyclerViewAdapter.addItems(categoriesModels);
            }
        });

        viewModel.getItemAndProductList().observe(this, new Observer<List<ProductModel>>() {
            @Override
            public void onChanged(List<ProductModel> productModels) {
                Log.e("TEST Product", productModels.size() + "LENGHT");
            }
        });



        viewModel.getRankingList().observe(this, new Observer<List<RankingModel>>() {
            @Override
            public void onChanged(List<RankingModel> rankingModels) {
                Log.e("TEST Ranking", rankingModels.size() + "LENGHT");
                recyclerViewRankingAdapter.addItems(rankingModels);

            }
        });



//        else
//            viewModel.deleteItem();
        final Handler handler = new Handler();

//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //Do something after 100ms
//                if(progress != null)
//                    progress.dismiss();
//
//                setData();
//
//            }
//        }, 5000);


    }

    public void setData(){


        viewModel.getItemAndProductList().observe(this, new Observer<List<ProductModel>>() {
            @Override
            public void onChanged(List<ProductModel> productModels) {
                Log.e("TEST Product", productModels.size() + "LENGHT");
            }
        });



        viewModel.getRankingList().observe(this, new Observer<List<RankingModel>>() {
            @Override
            public void onChanged(List<RankingModel> rankingModels) {
                Log.e("TEST Ranking", rankingModels.size() + "LENGHT");
            }
        });

        viewModel.getItemAndCategoryList().observe(this, new Observer<List<CategoriesModel>>() {
            @Override
            public void onChanged(List<CategoriesModel> categoriesModels) {
//                for(CategoriesModel categoriesModel : categoriesModels) {
//                }
                Log.e("TEST Category", categoriesModels.size() + "LENGHT" + categoriesModels.get(0).getName());
                recyclerViewAdapter.addItems(categoriesModels);
            }
        });



    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}

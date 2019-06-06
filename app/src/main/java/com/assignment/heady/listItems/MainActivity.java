package com.assignment.heady.listItems;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.heady.R;
import com.assignment.heady.db.CategoriesModel;
import com.assignment.heady.db.ProductModel;
import com.assignment.heady.db.RankingModel;
import com.assignment.heady.network.Network;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {

    private DataListViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView , recyclerViewRanking;
    RecyclerViewRankingAdapter recyclerViewRankingAdapter;
    LinearLayout search_layout;
    SearchView search;
    List<CategoriesModel> categoriesList;
    RelativeLayout rankingLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(DataListViewModel.class);
        if(Network.isInternetAvailable(MainActivity.this))
            viewModel.loadData();
        else{
            Toast.makeText(this, getString(R.string.internet_connection), Toast.LENGTH_SHORT).show();
        }

        search = findViewById(R.id.search);
        search_layout = findViewById(R.id.layout_search);
        rankingLayout = findViewById(R.id.raking_layout);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewRanking = findViewById(R.id.recycler_view_ranking);

        recyclerViewAdapter = new RecyclerViewAdapter(this, new ArrayList<CategoriesModel>());
        recyclerViewRankingAdapter =  new RecyclerViewRankingAdapter(this, new ArrayList<RankingModel>());


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
                recyclerViewAdapter.addItems(categoriesModels);
                categoriesList = categoriesModels;
            }
        });


        viewModel.getRankingList().observe(this, new Observer<List<RankingModel>>() {
            @Override
            public void onChanged(List<RankingModel> rankingModels) {
                recyclerViewRankingAdapter.addItems(rankingModels);

            }
        });


        search.setOnQueryTextListener(this);
        search.setOnClickListener(this);

        search.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                rankingLayout.setVisibility(View.VISIBLE);

                return false;
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<CategoriesModel> filteredModelList = filter(categoriesList, newText);

        recyclerViewAdapter.setFilter(filteredModelList);
        return true;
    }

    private List<CategoriesModel> filter(List<CategoriesModel> models, String query) {
        query = query.toLowerCase();
        final List<CategoriesModel> filteredModelList = new ArrayList<>();

        for (CategoriesModel model : models) {
            final String name = model.getName().toLowerCase();
            if (name.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search:
                search.setIconified(false);
                rankingLayout.setVisibility(View.GONE);
                break;
        }


    }
}

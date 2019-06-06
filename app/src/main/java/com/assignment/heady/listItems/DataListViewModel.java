package com.assignment.heady.listItems;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.assignment.heady.db.AppDatabase;
import com.assignment.heady.db.CategoriesModel;
import com.assignment.heady.db.ProductModel;
import com.assignment.heady.db.RankingModel;
import com.assignment.heady.db.ResponseDataModel;
import com.assignment.heady.network.DataCallback;
import com.assignment.heady.network.Network;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DataListViewModel extends AndroidViewModel {

    private final LiveData<List<CategoriesModel>> itemAndCategoryList ;
    private final LiveData<List<RankingModel>> rankingList ;
    private AppDatabase appDatabase;

    public DataListViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        itemAndCategoryList = appDatabase.itemAndCategoriesModel().getAllItems();
        rankingList = appDatabase.itemandRankingModel().getAllItems();
    }



    public LiveData<List<CategoriesModel>> getItemAndCategoryList() {
        return itemAndCategoryList;

    }
    public LiveData<List<RankingModel>> getRankingList() {
        return rankingList;

    }

    //This method is using Retrofit to get the JSON data from URL
    public void loadData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataCallback.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final MutableLiveData<ResponseDataModel> data = new MutableLiveData<>();

        DataCallback api = retrofit.create(DataCallback.class);
        Call<ResponseDataModel> call = api.getData();


        call.enqueue(new Callback<ResponseDataModel>() {
            @Override
            public void onResponse(Call<ResponseDataModel> call, Response<ResponseDataModel> response) {

                //finally we are setting the list to our MutableLiveData
                data.setValue(response.body());
                Log.d("RES", response.body().toString());

                for( CategoriesModel categoriesModel : response.body().getCategoriesModel()){

                    new addCategoriesAsyncTask(appDatabase).execute(categoriesModel);

                    for(ProductModel productModel: categoriesModel.getProducts()){
                        productModel.setCategoriesId(categoriesModel.getId());
                        new addProductAsyncTask(appDatabase).execute(productModel);
                    }
                }

                for(RankingModel rankingModel : response.body().getRankingModels()){
                    new addRankingAsyncTask(appDatabase).execute(rankingModel);
                }
            }

            @Override
            public void onFailure(Call<ResponseDataModel> call, Throwable t) {
                Log.e("ON Failure", t.getMessage());

            }
        });
    }

    private static class addRankingAsyncTask extends AsyncTask<RankingModel, Void, Void> {

        private AppDatabase db;

        addRankingAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final RankingModel... params) {

            db.itemandRankingModel().addData(params[0]);
            return null;
        }

    }

    private static class addCategoriesAsyncTask extends AsyncTask<CategoriesModel, Void, Void> {

        private AppDatabase db;

        addCategoriesAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final CategoriesModel... params) {

            db.itemAndCategoriesModel().addData(params[0]);
            return null;
        }

    }

    private static class addProductAsyncTask extends AsyncTask<ProductModel, Void, Void> {

        private AppDatabase db;

        addProductAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final ProductModel... params) {

            db.itemandProductModel().addData(params[0]);
            return null;
        }

    }

}

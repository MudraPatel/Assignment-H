package com.assignment.heady.listItems;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.assignment.heady.db.AppDatabase;
import com.assignment.heady.db.CategoriesModel;
import com.assignment.heady.db.ProductModel;
import com.assignment.heady.db.RankingModel;
import com.assignment.heady.db.ResponseDataModel;
import com.assignment.heady.network.DataCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductListViewModel extends AndroidViewModel {

    private LiveData<List<ProductModel>> itemAndProductList ;
    private AppDatabase appDatabase;

    public ProductListViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        itemAndProductList = appDatabase.itemandProductModel().getAllItems();
    }


    public LiveData<List<ProductModel>> getItemAndProductList(int id ) {
//        LiveData<List<DataModel>> list = Network.getProjectList();
        itemAndProductList = appDatabase.itemandProductModel().getItembyCategoryId(id);

        return itemAndProductList;

    }

    public LiveData<List<ProductModel>> getItemAndProductListIds(int[] ids ) {
//        LiveData<List<DataModel>> list = Network.getProjectList();
        itemAndProductList = appDatabase.itemandProductModel().findByProductIds(ids);

        return itemAndProductList;

    }

    private static class addProductAsyncTask extends AsyncTask<ProductModel, Void, Void> {

        private AppDatabase db;

        addProductAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final ProductModel... params) {
//            db.itemandProductModel().findByProductIds()
            return null;
        }

    }

}

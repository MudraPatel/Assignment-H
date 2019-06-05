package com.assignment.heady.listItems;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.assignment.heady.db.AppDatabase;
import com.assignment.heady.db.ProductModel;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;



public class ProductListViewModel extends AndroidViewModel {

    private LiveData<List<ProductModel>> itemAndProductList ;
    private LiveData<ProductModel> itemProductModel;
    private AppDatabase appDatabase;

    public ProductListViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        itemAndProductList = appDatabase.itemandProductModel().getAllItems();
    }


    public LiveData<List<ProductModel>> getItemAndProductList(int id ) {
        itemAndProductList = appDatabase.itemandProductModel().getItembyCategoryId(id);

        return itemAndProductList;

    }

    public LiveData<List<ProductModel>> getItemAndProductListIds(int[] ids ) {
        itemAndProductList = appDatabase.itemandProductModel().findByProductIds(ids);

        return itemAndProductList;

    }

    public LiveData<ProductModel> getItemAndProductId(int id ) {
        itemProductModel = appDatabase.itemandProductModel().getItembyId(id);

        return itemProductModel;

    }

}

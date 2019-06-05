package com.assignment.heady.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;



@Database(entities = {CategoriesModel.class, ProductModel.class, RankingModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getDatabase(Context context) {
        if (instance == null) {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "assignment_db")
                            .build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }


    public abstract CategoriesModelDao itemAndCategoriesModel();

    public  abstract ProductModelDao itemandProductModel();

    public abstract RankingModelDao itemandRankingModel();


   /* private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            LiveData<List<DataModel>> list = Network.getProjectList();

        }
    };

    private static class addAsyncTask extends AsyncTask<DataModel, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final DataModel... params) {
            db.itemAndPersonModel().addUser(params[0]);
            return null;
        }

    }*/
}

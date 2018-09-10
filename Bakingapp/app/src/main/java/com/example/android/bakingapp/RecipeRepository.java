package com.example.android.bakingapp;

import android.app.Application;
import android.util.Log;

import com.example.android.bakingapp.data.network.ApiCalls;
import com.example.android.bakingapp.data.database.AppDatabase;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.data.database.RecipeDao;
import com.example.android.bakingapp.ui.mainactivity.MainActivity;

import java.util.List;
import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class RecipeRepository {

    private static final String TAG = MainActivity.class.getSimpleName();
    private AppDatabase mDb;
    private final RecipeDao mRecipeDao;

    public RecipeRepository(Application application) {
        mDb = AppDatabase.getInstance(application);
        mRecipeDao = mDb.recipeDao();
    }


    public LiveData<List<Recipe>> getAppRecipe() {
        refreshRecipe();
        return mRecipeDao.loadAllRecipe();
    }

 //   public MutableLiveData<Recipe> getRecipeById(int id) {
 //       return mRecipeDao.loadRecipeById(id);
 //   }

    public void refreshRecipe() {
        Call<List<Recipe>> call = ApiCalls.getRecipeCall();
        Log.d(TAG, "call = apiCalls.getRecipeCall");
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(final Call<List<Recipe>> call, final Response<List<Recipe>> response) {
                final List<Recipe> body = response.body();
                if (response.isSuccessful() && body != null) {
                    saveToDatabase(body);
                } else {
                    Log.d(TAG, "if else response in onResponse");
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });
    }

    public void saveToDatabase(final List<Recipe> recipes) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.recipeDao().insertRecipe(recipes);
            }
        });
    }


}

package com.example.android.bakingapp;

import android.app.Application;
import android.util.Log;

import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.data.database.AppDatabase;
import com.example.android.bakingapp.data.network.ApiCalls;
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
    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private final RecipeDao mRecipeDao;
    private AppExecutors mAppExecutors;
    private AppDatabase mDb;
    private static RecipeRepository sInstance;

    public RecipeRepository(Application application) {
        mDb = AppDatabase.getInstance(application);
        mRecipeDao = mDb.recipeDao();
    }

    public RecipeRepository(RecipeDao recipeDao,AppExecutors appExecutors) {
        mRecipeDao = recipeDao;
        mAppExecutors = appExecutors;
        Log.d(TAG,"RecipeRepositoryConstructor");
    }


    public void refreshRecipe() {
        Call<List<Recipe>> call = ApiCalls.getRecipeCall();
        Log.d(TAG, "call = apiCalls.getRecipeCall");
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(final Call<List<Recipe>> call, final Response<List<Recipe>> response) {
                final List<Recipe> body = response.body();
                if (response.isSuccessful() && body != null) {
                    Log.d(TAG, "refreshRecipe");
                    saveToDatabase(body);
                } else {
                    Log.d(TAG, "if else response in onResponse");
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d(TAG, "no run" + t);
            }
        });
    }

    public synchronized static RecipeRepository getInstance(
            RecipeDao recipeDao, AppExecutors executors) {
        Log.d(TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new RecipeRepository(recipeDao,
                        executors);
                Log.d(TAG, "Made new repository");
            }
        }
        return sInstance;
    }


    public LiveData<List<Recipe>> getAppRecipe() {
        Log.d(TAG, "GetAppRecipe");
        refreshRecipe();
        return mRecipeDao.loadAllRecipe();
    }

    public LiveData<Recipe> loadRecipeById(int id) {
        return mRecipeDao.loadRecipeById(id);
    }

    public void saveToDatabase(final List<Recipe> recipes) {
        Log.d(TAG, "Save to database save to database save to database");
        mAppExecutors.diskIO().execute(() ->{
            mRecipeDao.insertRecipe(recipes);
        });
    }
}

package com.example.android.bakingapp.ui.mainactivity;

import android.app.Application;
import android.util.Log;
import com.example.android.bakingapp.RecipeRepository;
import com.example.android.bakingapp.data.Recipe;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainViewModel extends AndroidViewModel {

    // Constant for logging
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<Recipe>> mRecipe;
    private RecipeRepository mRecipeRepository;

    public MainViewModel(Application application) {
        super(application);
        mRecipeRepository = new RecipeRepository(application);
        Log.d(TAG, "Actively retrieving the recipe from the DataBase");
        mRecipe = mRecipeRepository.getAppRecipe();
    }

    public LiveData<List<Recipe>> getRecipe() {
        return mRecipe;
    }
}



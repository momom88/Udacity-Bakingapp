package com.example.android.bakingapp.ui.detailactivity;

import com.example.android.bakingapp.RecipeRepository;
import com.example.android.bakingapp.data.Recipe;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class DetailViewModel extends ViewModel {

    private LiveData<Recipe> mRecipe;

    private final int mId;
    private RecipeRepository mRepository;

    public DetailViewModel(RecipeRepository repository , int id){
        this.mRepository = repository;
        this.mId = id;
        mRecipe = mRepository.loadRecipeById(mId);
    }

    public LiveData<Recipe> getRecipe(){
        return mRecipe;
    }
}


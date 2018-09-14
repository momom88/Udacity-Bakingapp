package com.example.android.bakingapp.ui.ingredientsandstepsctivity;

import com.example.android.bakingapp.RecipeRepository;
import com.example.android.bakingapp.data.Recipe;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class IngredientsViewModel extends ViewModel {

    private LiveData<Recipe> mRecipe;

    private final int mId;
    RecipeRepository mRepository;

    @Inject
    public IngredientsViewModel(RecipeRepository repository , int id){
    mRepository = repository;
    mId = id;
    mRecipe = mRepository.loadRecipeById(mId);
    }

    public LiveData<Recipe> getRecipe(){
        return mRecipe;
    }
}

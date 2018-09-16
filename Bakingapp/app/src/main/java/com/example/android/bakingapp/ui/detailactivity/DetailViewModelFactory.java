package com.example.android.bakingapp.ui.detailactivity;

import com.example.android.bakingapp.RecipeRepository;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    private final RecipeRepository mRepository;
    private final int mId;

    public DetailViewModelFactory(RecipeRepository recipeRepository, int id){
        this.mRepository = recipeRepository;
        this.mId = id;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //  noinspection unchecked
        return (T) new DetailViewModel(mRepository, mId);
    }
}

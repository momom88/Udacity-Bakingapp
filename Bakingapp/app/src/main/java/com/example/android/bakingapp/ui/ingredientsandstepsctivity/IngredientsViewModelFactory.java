package com.example.android.bakingapp.ui.ingredientsandstepsctivity;

import com.example.android.bakingapp.RecipeRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
@Singleton
public class IngredientsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final RecipeRepository mRepository;
    private final int mId;

    @Inject
    public IngredientsViewModelFactory(RecipeRepository repository, int id) {
        this.mId = id;
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //  noinspection unchecked
        return (T) new IngredientsViewModel(mRepository, mId);
    }
}


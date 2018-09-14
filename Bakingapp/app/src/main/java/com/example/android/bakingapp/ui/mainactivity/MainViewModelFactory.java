package com.example.android.bakingapp.ui.mainactivity;

import com.example.android.bakingapp.RecipeRepository;
import com.example.android.bakingapp.data.Recipe;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final RecipeRepository mRepository;
    public MainViewModelFactory(RecipeRepository repository) {
        this.mRepository = repository;
    }
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainViewModel(mRepository);
    }
}

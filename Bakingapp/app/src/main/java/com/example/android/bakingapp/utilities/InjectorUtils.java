package com.example.android.bakingapp.utilities;

import android.content.Context;

import com.example.android.bakingapp.AppExecutors;
import com.example.android.bakingapp.RecipeRepository;
import com.example.android.bakingapp.data.database.AppDatabase;
import com.example.android.bakingapp.ui.ingredientsandstepsctivity.IngredientsViewModelFactory;
import com.example.android.bakingapp.ui.mainactivity.MainViewModelFactory;

public class InjectorUtils {
    public static RecipeRepository provideRepository(Context context){
        AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        return RecipeRepository.getInstance(database.recipeDao(),executors);
    }

    public static IngredientsViewModelFactory provideIngredientsViewModelFactory(Context context, int id) {
        RecipeRepository repository = provideRepository(context.getApplicationContext());
        return new IngredientsViewModelFactory(repository, id);
    }

    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context) {
        RecipeRepository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository);
    }
}

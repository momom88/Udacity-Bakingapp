package com.example.android.bakingapp.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.IngredientListToStringText;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.ui.mainactivity.MainActivity;

public class WidgetPreferences {
    public static final String PREFS_NAME = "prefs";
    private static final String TAG = MainActivity.class.getSimpleName();

    public static void saveRecipe(Context context, Recipe recipe) {
        SharedPreferences.Editor preferences = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE).edit();
        preferences.putString(context.getString(R.string.widget_recipe_name), recipe.getName());
        preferences.putString(context.getString(R.string.widget_ingredients_list),
                IngredientListToStringText.getListToIngredientText(recipe.getIngredients()));
        preferences.apply();
    }

    public static String loadRecipeName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String recipeName = sharedPreferences.getString(context.getString(R.string.widget_recipe_name), "");
        return recipeName;
    }

    public static String loadIngredientsList(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String ingredientList = sharedPreferences.getString(context.getString(R.string.widget_ingredients_list), "");
        return ingredientList;
    }

}


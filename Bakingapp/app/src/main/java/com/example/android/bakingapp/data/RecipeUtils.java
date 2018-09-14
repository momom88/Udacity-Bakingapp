package com.example.android.bakingapp.data;

import java.util.Locale;

public class RecipeUtils {

    public static String getListToIngredientText(Recipe recipe){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            Ingredient ingredients = recipe.getIngredients().get(i);
            stringBuilder.append(String.format(Locale.getDefault(), "• %s (%.1f %s)", ingredients.getIngredient(), ingredients.getQuantity(), ingredients.getMeasure()));
            if (i != recipe.getIngredients().size() - 1)
                stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}

package com.example.android.bakingapp.data;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Ingredient;

import java.util.List;
import java.util.Locale;

public class IngredientListToStringText {

    public static String getListToIngredientText(List<Ingredient> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Ingredient ingredients = list.get(i);
            stringBuilder.append(String.format(Locale.getDefault(), "• %s (%.1f %s)", ingredients.getIngredient(), ingredients.getQuantity(), ingredients.getMeasure()));
            if (i != list.size() - 1) {
                stringBuilder.append(",\n");
            }
        }
        return stringBuilder.toString();
    }
}

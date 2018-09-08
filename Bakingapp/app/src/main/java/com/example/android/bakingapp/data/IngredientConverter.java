package com.example.android.bakingapp.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class IngredientConverter {
    @TypeConverter
    public static String toString(List<Ingredient> ingredientList){
        if (ingredientList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ingredient>>() {}.getType();
        String json = gson.toJson(ingredientList, type);
        return json;
    }

    @TypeConverter
    public static List<Ingredient> toList(String ingredientString){
        if (ingredientString == null) {
            return (null);
        }
        Type type = new TypeToken<List<Ingredient>>() {}.getType();
        Gson gson = new Gson();
        List<Ingredient> ingredientList = gson.fromJson(ingredientString, type);
        return ingredientList;
    }

}

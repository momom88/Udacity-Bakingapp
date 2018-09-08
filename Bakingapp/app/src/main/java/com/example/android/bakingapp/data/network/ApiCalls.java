package com.example.android.bakingapp.data.network;

import android.util.Log;

import com.example.android.bakingapp.ui.mainactivity.MainActivity;
import com.example.android.bakingapp.data.Recipe;

import java.util.List;

import retrofit2.Call;

public class ApiCalls {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static Call<List<Recipe>> getRecipeCall() {
        Log.d(TAG, "ApiCalls");
        return ApiServices
                .getBakingService()
                .getRecipe();
    }
}

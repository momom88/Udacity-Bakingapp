package com.example.android.bakingapp.data.network;
import com.example.android.bakingapp.data.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public class ApiServices {
    private static BakingService mBakingService;

    static synchronized BakingService getBakingService() {
        if (mBakingService == null) {
            mBakingService = ApiClients
                    .getApiClient()
                    .create(BakingService.class);
        }
        return mBakingService;
    }

    public interface BakingService {
        @GET("/topher/2017/May/59121517_baking/baking.json")
        Call<List<Recipe>> getRecipe();
    }
}

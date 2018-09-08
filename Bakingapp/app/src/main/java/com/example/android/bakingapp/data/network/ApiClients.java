package com.example.android.bakingapp.data.network;

import android.util.Log;

import com.example.android.bakingapp.ui.mainactivity.MainActivity;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

class ApiClients {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static Retrofit mClient;
    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net";

    static synchronized Retrofit getApiClient() {
        if (mClient == null) {
            mClient = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();
        }
        Log.d(TAG, "ApiClients " + mClient.toString());
        return mClient;
    }
}

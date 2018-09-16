package com.example.android.bakingapp.ui.ingredientsandstepsctivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;


import com.example.android.bakingapp.R;
import com.example.android.bakingapp.ui.mainactivity.MainActivity;

public class IngredientsActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    private boolean mTwoPane;

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {

            IngredientsFragment ingredientsFragment = new IngredientsFragment();
            int id = getIntent().getIntExtra(getResources().getString(R.string.recipe_id), -1);
            Log.d(TAG, "IngredientsActivity send: " + id);
            Bundle bundle = new Bundle();
            bundle.putInt(getResources().getString(R.string.recipe_id), id);
            ingredientsFragment.setArguments(bundle);
            mFragmentManager.beginTransaction()
                    .add(R.id.container ,ingredientsFragment)
                    .commit();
        }
    }
}

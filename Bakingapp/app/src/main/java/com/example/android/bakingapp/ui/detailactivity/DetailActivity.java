package com.example.android.bakingapp.ui.detailactivity;

import android.os.Bundle;

import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.databinding.ActivityDetailBinding;
import com.example.android.bakingapp.ui.mainactivity.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.utilities.InjectorUtils;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens

    private DetailViewModel mViewModel;
    private ActivityDetailBinding mBinding;
    int mStepId;
    int mRecipeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        if (savedInstanceState == null) {
            mStepId = getIntent().getIntExtra(getResources().getString(R.string.step_id), -1);
            mRecipeId = getIntent().getIntExtra(getResources().getString(R.string.recipe_id), -1);
            Log.d(TAG, "Step id : " + mStepId + "Recipe id : " + mRecipeId);
            if (mRecipeId != -1) {
                Log.d(TAG, "mRecipeId not null: " + mRecipeId);
            } else {
                Log.d(TAG, "mRecipeId is null");
            }

            if (mStepId != -1) {
                Log.d(TAG, "mStepId not null: " + mStepId);
            } else {
                Log.d(TAG, "stopId is null");
            }
        }
        loadViewModel(mRecipeId, mStepId);
    }

    private void loadViewModel(int recipeId, int stepId) {
        DetailViewModelFactory factory = InjectorUtils.provideDetailViewModelFactory(this.getApplicationContext(), recipeId);
        mViewModel = ViewModelProviders.of(this, factory).get(DetailViewModel.class);
        mViewModel.getRecipe().observe(this, new Observer<Recipe>() {
            @Override
            public void onChanged(Recipe recipe) {
                Log.d(TAG, "Updating DetailFragment from Viemodel: " + recipe.getSteps().get(stepId).getShortDescription());
                DetailFragmentPagerAdapter adapter = new DetailFragmentPagerAdapter(getApplicationContext(), recipe.getSteps(),getSupportFragmentManager());
                mBinding.viewpager.setAdapter(adapter);
            }
        });
    }
}

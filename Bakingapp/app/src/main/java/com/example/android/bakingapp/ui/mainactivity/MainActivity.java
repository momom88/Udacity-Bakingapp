package com.example.android.bakingapp.ui.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Recipe;

import com.example.android.bakingapp.databinding.ActivityMainBinding;
import com.example.android.bakingapp.ui.ingredientsandstepsctivity.IngredientsActivity;
import com.example.android.bakingapp.utilities.InjectorUtils;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity implements RecipeInterface {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecipeRecyclerViewAdapter mAdapter;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setTitle(getResources().getString(R.string.app_name));
        mAdapter = new RecipeRecyclerViewAdapter(this);
        setupViewModel();
    }

    private void setupViewModel() {
        MainViewModelFactory factory = InjectorUtils.provideMainActivityViewModelFactory(this.getApplicationContext());
        MainViewModel viewModel = ViewModelProviders.of(this,factory).get(MainViewModel.class);
        viewModel.getRecipe().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                Log.d(TAG, "Updating list of tasks from LiveData in ViewModel");
                mAdapter.setTasks(recipes);
                mBinding.recyclerView.setAdapter(mAdapter);
            }
        });
    }

    @Override
    public void onClick(Recipe recipe) {
        Intent intent = new Intent(this, IngredientsActivity.class);
        intent.putExtra(getResources().getString(R.string.recipe_id),recipe.getId());
        startActivity(intent);
    }
}




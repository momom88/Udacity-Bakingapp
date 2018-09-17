package com.example.android.bakingapp.ui.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
        loading();
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
                loadData();
                mAdapter.setTasks(recipes);
                mBinding.recyclerView.setAdapter(mAdapter);
            }
        });
    }

    private void loading(){
        mBinding.loadingIndicator.setVisibility(View.VISIBLE);
        mBinding.recyclerView.setVisibility(View.GONE);
    }

    private void loadData(){
        mBinding.loadingIndicator.setVisibility(View.GONE);
        mBinding.recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(Recipe recipe) {
        Intent intent = new Intent(this, IngredientsActivity.class);
        intent.putExtra(getResources().getString(R.string.recipe_id),recipe.getId());
        startActivity(intent);
    }
}




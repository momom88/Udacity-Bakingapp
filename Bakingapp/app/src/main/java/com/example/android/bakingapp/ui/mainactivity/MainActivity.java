package com.example.android.bakingapp.ui.mainactivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.databinding.ActivityMainBinding;

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
        mAdapter = new RecipeRecyclerViewAdapter(this);
        setupViewModel();
    }

    private void setupViewModel() {
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
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
        Log.d(TAG, "On click" + recipe.getName());
        Toast.makeText(this, "On click" + recipe.getName(),
                Toast.LENGTH_SHORT).show();

    }
}




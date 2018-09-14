package com.example.android.bakingapp.ui.ingredientsandstepsctivity;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.IngredientListToStringText;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.databinding.FragmentIngredientsBinding;
import com.example.android.bakingapp.ui.detailactivity.DetailActivity;
import com.example.android.bakingapp.ui.mainactivity.MainActivity;
import com.example.android.bakingapp.utilities.InjectorUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsFragment extends Fragment implements StepInterface {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String RECIPE_ID = "repice_id";
    private FragmentIngredientsBinding mFragmentBinding;
    private IngredientsStepAdapter mAdapter;
    private IngredientsViewModel mViewModel;
    private Ingredient mIngredient;
    private Recipe mRecipe;



    public IngredientsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_ingredients, container, false);
        return mFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new IngredientsStepAdapter(this);

        int recipeId;
        if (savedInstanceState == null) {
            recipeId = getArguments().getInt(RECIPE_ID, -1);
            Log.d(TAG, "IngredientsFragment sevedinstance null: " + recipeId);
        } else {
            recipeId = savedInstanceState.getInt(RECIPE_ID, -1);
            Log.d(TAG, "ingredientsFragment savedinstance: " + recipeId);
        }

        if (recipeId != -1) {
            Log.d(TAG, "recipeId not null: " + recipeId);

        }
        loadViewModel(recipeId);
    }

    private void loadViewModel(int recipe) {
        IngredientsViewModelFactory factory = InjectorUtils.provideIngredientsViewModelFactory(getContext().getApplicationContext(), recipe);
        mViewModel = ViewModelProviders.of(this, factory).get(IngredientsViewModel.class);
        mViewModel.getRecipe().observe(getActivity(), new Observer<Recipe>() {
            @Override
            public void onChanged(Recipe recipe) {
                Log.d(TAG, "Updating IngredientsFragment from Viemodel");
                mRecipe = recipe;
                mAdapter.setTasks(recipe.getSteps());
                mIngredient = recipe.getIngredients().get(0);
                Log.d(TAG, "Adapter ok" + mIngredient.getIngredient() + mIngredient.getMeasure());
                Log.d(TAG, recipe.getName() + recipe.getIngredients().size());
                mFragmentBinding.setTitle(recipe.getName());
                mFragmentBinding.ingredientsLayout.ingredientsText.setText(IngredientListToStringText.getListToIngredientText(recipe.getIngredients()));
                mFragmentBinding.recyclerView.setAdapter(mAdapter);
            }
        });
    }

    @Override
    public void onStepClick(Step step) {
        Log.d(TAG, "onStepClick :" + step.getId() + step.getDescription());
        Toast.makeText(getContext(), "haha" , Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(getContext(), DetailActivity.class);
//        intent.putExtra(getResources().getString(R.string.step_id),step.getId());
//        startActivity(intent);
    }
}

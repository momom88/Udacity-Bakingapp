package com.example.android.bakingapp.ui.ingredientsandstepsctivity;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.IngredientListToStringText;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.databinding.FragmentIngredientsBinding;
import com.example.android.bakingapp.ui.detailactivity.DetailActivity;
import com.example.android.bakingapp.ui.detailactivity.DetailFragment;
import com.example.android.bakingapp.ui.mainactivity.MainActivity;
import com.example.android.bakingapp.utilities.InjectorUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsFragment extends Fragment implements StepInterface {
    private static final String TAG = MainActivity.class.getSimpleName();
    private FragmentIngredientsBinding mFragmentBinding;
    private IngredientsStepAdapter mAdapter;
    private IngredientsViewModel mViewModel;
    private Ingredient mIngredient;
    private boolean mTwoPanel;
    private Recipe mRecipe;
    private FragmentManager mFragmentManager;


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
            recipeId = getArguments().getInt(getResources().getString(R.string.recipe_id), -1);
            Log.d(TAG, "IngredientsFragment sevedinstance null: " + recipeId);
        } else {
            recipeId = savedInstanceState.getInt(getResources().getString(R.string.recipe_id), -1);
            Log.d(TAG, "ingredientsFragment savedinstance: " + recipeId);
        }

        if (recipeId != -1) {
            Log.d(TAG, "recipeId not null: " + recipeId);

        }
        mTwoPanel = getResources().getBoolean(R.bool.tablet);
        loadViewModel(recipeId);

    }

    private void loadViewModel(int recipe) {
        IngredientsViewModelFactory factory = InjectorUtils.provideIngredientsViewModelFactory(getContext().getApplicationContext(), recipe);
        mViewModel = ViewModelProviders.of(this, factory).get(IngredientsViewModel.class);
        mViewModel.getRecipe().observe(getActivity(), new Observer<Recipe>() {
            @Override
            public void onChanged(Recipe recipe) {
                Log.d(TAG,"onChange test " + recipe.getId() + recipe.getSteps().get(0).getId());
                mRecipe = recipe;
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(mRecipe.getName());
                mAdapter.setTasks(recipe.getSteps());
                mIngredient = recipe.getIngredients().get(0);
                if(mTwoPanel){ onStepClick(recipe.getSteps().get(0));}
                mFragmentBinding.ingredientsLayout.ingredientsText.setText(IngredientListToStringText.getListToIngredientText(recipe.getIngredients()));
                mFragmentBinding.recyclerView.setAdapter(mAdapter);
            }
        });
    }

    @Override
    public void onStepClick(Step step) {
        if(mTwoPanel){
            Bundle bundle = new Bundle();
            bundle.putParcelable(getContext().getResources().getString(R.string.step_id), step);
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    .replace(R.id.container_detail_tablet, detailFragment)
                    .commit();
        }
        else {
            Log.d(TAG, "onStepClick: " + step.getId() + "  recipe id :  " + mRecipe.getId());
            Toast.makeText(getContext(), step.getId().toString(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra(getResources().getString(R.string.step_id), step.getId());
            intent.putExtra(getResources().getString(R.string.recipe_id), mRecipe.getId());
            startActivity(intent);
        }
    }
}

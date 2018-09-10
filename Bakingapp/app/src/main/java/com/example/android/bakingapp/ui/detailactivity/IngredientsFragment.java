package com.example.android.bakingapp.ui.detailactivity;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.databinding.FragmentIngredientsBinding;
import com.example.android.bakingapp.ui.mainactivity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsFragment extends Fragment implements StepInterface {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String RECIPE_ID = "repice_id";
    private FragmentIngredientsBinding mBinding;
    private IngredientsStepAdapter mAdapter;



    public IngredientsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_ingredients, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new IngredientsStepAdapter(this);


        int recipeId;
        if (savedInstanceState == null){
            recipeId = getArguments().getInt(RECIPE_ID, -1);
            Log.d(TAG, "IngredientsFragment sevedinstance null: " + recipeId);
        } else {
            recipeId =savedInstanceState.getInt(RECIPE_ID,-1);
            Log.d(TAG, "ingredientsFragment savedinstance: " + recipeId);
        }
    }


    @Override
    public void onStepClick(Step step) {

    }
}

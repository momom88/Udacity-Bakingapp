package com.example.android.bakingapp.ui.detailactivity;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.databinding.ListItemIngredientDetailActivityBinding;
import com.example.android.bakingapp.databinding.ListItemStepDetailActivityBinding;


import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class IngredientsStepAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Recipe mRecipe;
    private LayoutInflater mInflater;
    private StepInterface mClickStepListener;


    public IngredientsStepAdapter(@NonNull StepInterface stepInterface) {
        mClickStepListener = stepInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        if (viewType == 0) {
            ListItemIngredientDetailActivityBinding binding =
                    DataBindingUtil.inflate(mInflater, R.layout.list_item_recipe_main_activity, parent, false);
            return new IngredientsViewHolder(binding);
        } else {
            ListItemStepDetailActivityBinding binding =
                    DataBindingUtil.inflate(mInflater, R.layout.list_item_recipe_main_activity, parent, false);
            binding.setClickStep(mClickStepListener);
            return new StepViewHolder(binding);
        }
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof IngredientsViewHolder) {
            IngredientsViewHolder ingredientsViewHolder = (IngredientsViewHolder) holder;

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < mRecipe.getIngredients().size(); i++) {
                Ingredient ingredients = mRecipe.getIngredients().get(i);
                stringBuilder.append(String.format(Locale.getDefault(), "• %s (%d %s)", ingredients.getIngredient(), ingredients.getQuantity(), ingredients.getMeasure()));
                if (i != mRecipe.getIngredients().size() - 1)
                    stringBuilder.append("\n");
            }

            ingredientsViewHolder.binding.ingredientsText.setText(stringBuilder);

        } else if (holder instanceof StepViewHolder) {
            StepViewHolder viewHolder = (StepViewHolder) holder;

            viewHolder.binding.stepOrderText.setText(String.valueOf(position) + " - ");
            viewHolder.binding.stepNameText.setText(mRecipe.getSteps().get(position - 1).getShortDescription());
        }
    }

    @Override
    public int getItemCount() {
        return mRecipe.getSteps().size() + 1;
    }

    public class IngredientsViewHolder extends RecyclerView.ViewHolder {

        private final ListItemIngredientDetailActivityBinding binding;

        public IngredientsViewHolder(final ListItemIngredientDetailActivityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class StepViewHolder extends RecyclerView.ViewHolder {

        private final ListItemStepDetailActivityBinding binding;

        public StepViewHolder(final ListItemStepDetailActivityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

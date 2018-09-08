package com.example.android.bakingapp.ui.mainactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.databinding.ListItemBinding;
import com.example.android.bakingapp.data.Recipe;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeViewHolder>{

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Recipe> mRecipes;
    private LayoutInflater mInflater;
    private RecipeInterface mClickRecipeInterface;

    public RecipeRecyclerViewAdapter(RecipeInterface recipeInterface){
        mClickRecipeInterface = recipeInterface;
    }

    public void setTasks(List<Recipe> recipes) {
        mRecipes = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        ListItemBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.list_item, parent, false);
        binding.setClickRecipe(mClickRecipeInterface);
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, final int position) {
        holder.binding.setRecipe(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        private final ListItemBinding binding;

        public RecipeViewHolder(final ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

package com.example.android.bakingapp.ui.ingredientsandstepsctivity;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.databinding.ListItemStepDetailActivityBinding;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class IngredientsStepAdapter extends RecyclerView.Adapter<IngredientsStepAdapter.StepViewHolder> {
    private List<Step> mSteps;
    private LayoutInflater mInflater;
    private StepInterface mClickStepListener;


    public IngredientsStepAdapter(StepInterface stepInterface) {
        mClickStepListener = stepInterface;
    }

    public void setTasks(List<Step> steps) {
        if (mSteps == null || mSteps.size() == 0) {
            mSteps = steps;
        }
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        ListItemStepDetailActivityBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.list_item_step_detail_activity, parent, false);
        binding.setClickStep(mClickStepListener);
        return new StepViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        holder.binding.setStep(mSteps.get(position));
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder {

        private final ListItemStepDetailActivityBinding binding;

        public StepViewHolder(final ListItemStepDetailActivityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

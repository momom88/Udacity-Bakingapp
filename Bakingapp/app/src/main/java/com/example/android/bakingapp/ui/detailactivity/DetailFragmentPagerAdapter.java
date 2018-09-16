package com.example.android.bakingapp.ui.detailactivity;

import android.content.Context;
import android.os.Bundle;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Step;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class DetailFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Step> mSteps;

    public DetailFragmentPagerAdapter(Context context, List<Step> steps, FragmentManager fm) {
        super(fm);
        this.mContext = context;
        this.mSteps = steps;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(mContext.getString(R.string.step_id), mSteps.get(position));
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return String.format(mContext.getString(R.string.step), position);
    }

    @Override
    public int getCount() {
        return mSteps.size();
    }
}

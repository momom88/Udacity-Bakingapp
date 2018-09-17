package com.example.android.bakingapp.ui.detailactivity;


import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.databinding.FragmentDetailBinding;
import com.example.android.bakingapp.ui.mainactivity.MainActivity;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private static final String PLAYER_POSITION_STATE = "player_position_state";
    private static final String PLAYER_PLAY_PAUSE_STATE = "player_play_pause_state";


    private static final String TAG = MainActivity.class.getSimpleName();

    private FragmentDetailBinding mBinding;
    private SimpleExoPlayer mExoPlayer;
    private Step mStep;

    private long mPlayerPosition = 0;
    private boolean mPlayWhenReady = true;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(getResources().getString(R.string.step_id))) {
            mStep = getArguments().getParcelable(getResources().getString(R.string.step_id));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail,
                container, false);
        if (savedInstanceState != null && savedInstanceState.containsKey(PLAYER_POSITION_STATE)) {
            mPlayerPosition = savedInstanceState.getLong(PLAYER_POSITION_STATE);
            mPlayWhenReady = savedInstanceState.getBoolean(PLAYER_PLAY_PAUSE_STATE);
        }
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
        final Boolean tablet = getContext().getResources().getBoolean(R.bool.tablet);
        final Boolean land = getContext().getResources().getBoolean(R.bool.landscape);

        if(tablet || !land ){
            Log.d(TAG,"ha");
            mBinding.stepTitleTextview.setText(mStep.getShortDescription());
            mBinding.descriptionTitleTextView.setText(mStep.getDescription());
        }
        String videoUrl = mStep.getVideoURL();
        if (videoUrl != null && !videoUrl.isEmpty()) {
            showVideoholder(false);
            initializePlayer(Uri.parse(videoUrl));
        } else {
            showVideoholder(true);
            String imageUrl = mStep.getThumbnailURL();
            loadPlayerThumbnail(imageUrl);
            Log.d(TAG, "onResume Else");
            // Un- hide InstructionsContainer because in case of phone landscape is hidden
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        releasePlayer();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void showVideoholder(Boolean show) {
        if (show) {
            mBinding.playerHolder.setVisibility(View.VISIBLE);
            mBinding.playerView.setVisibility(View.INVISIBLE);
        } else {
            mBinding.playerHolder.setVisibility(View.INVISIBLE);
            mBinding.playerView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Release ExoPlayer.
     */
    private void releasePlayer() {
        if (mExoPlayer != null) {
            mPlayWhenReady = mExoPlayer.getPlayWhenReady();
            mPlayerPosition = mExoPlayer.getCurrentPosition();

            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    private void loadPlayerThumbnail(String url) {
        if (url != null && !url.isEmpty()) {
            Picasso.get().load(url).placeholder(R.drawable.no_image).error(R.drawable.no_image).into(mBinding.playerHolder);
        } else {
            Picasso.get().load(R.drawable.no_image).into(mBinding.playerHolder);
        }
    }

    /**
     * Initialize ExoPlayer.
     *
     * @param mediaUri The URI of the sample to play.
     */
    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            if (mExoPlayer == null) {
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                TrackSelection.Factory selectionFactory =
                        new AdaptiveTrackSelection.Factory(bandwidthMeter);
                TrackSelector trackSelector =
                        new DefaultTrackSelector(selectionFactory);
                mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
                mBinding.playerView.setPlayer(mExoPlayer);
                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(),
                        Util.getUserAgent(getActivity(), "Baking"), new DefaultBandwidthMeter());
                MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(mediaUri);
                mExoPlayer.prepare(videoSource);

                //retain position if available, after orientation change
                if (mPlayerPosition != -1) {
                    mExoPlayer.seekTo(mPlayerPosition);
                    mPlayerPosition = -1;
                }
                mExoPlayer.setPlayWhenReady(mPlayWhenReady);
            }
        }
    }
}

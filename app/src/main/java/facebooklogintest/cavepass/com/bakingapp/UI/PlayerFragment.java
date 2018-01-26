package facebooklogintest.cavepass.com.bakingapp.UI;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import facebooklogintest.cavepass.com.bakingapp.ModelClasses.Step;
import facebooklogintest.cavepass.com.bakingapp.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Ajay on 26-01-2018.
 */

public class PlayerFragment extends Fragment {

    SimpleExoPlayer player;
    SharedPreferences.Editor editor;
    long seekToFor;
    Step object;

    public PlayerFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.player_fragment,container,false);

        SimpleExoPlayerView playerView = view.findViewById(R.id.simple_player_view);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("playerContentPosition", MODE_PRIVATE);


        editor =  sharedPreferences.edit();

        object =  (Step)getArguments().get("stepsObject");

        if(sharedPreferences!=null){

            seekToFor = sharedPreferences.getLong("seekToFor"+object.getVideoURL(),0);

        }


        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);


        player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);



        playerView.requestFocus();


        playerView.setPlayer(player);



        Uri uri = Uri.parse(object.getVideoURL());
        MediaSource mediaSource = new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);

        player.prepare(mediaSource, true, false);

        //  player.prepare(mediaSource);

        // player.setVideoScalingMode(360);

        player.setPlayWhenReady(true);


        player.seekTo(seekToFor);

        return view;

    }

    @Override
    public void onStop() {
        super.onStop();
        player.release();

        editor.putLong("seekToFor"+object.getVideoURL(),player.getContentPosition());
        editor.commit();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        player.release();
    }
}

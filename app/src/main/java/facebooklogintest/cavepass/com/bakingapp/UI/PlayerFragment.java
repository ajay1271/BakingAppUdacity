package facebooklogintest.cavepass.com.bakingapp.UI;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.w3c.dom.Text;

import facebooklogintest.cavepass.com.bakingapp.ModelClasses.Step;
import facebooklogintest.cavepass.com.bakingapp.R;

import static android.content.Context.MODE_PRIVATE;


public class PlayerFragment extends Fragment {

    ExoPlayer player;
    long seekToFor;
    Step object;

    private boolean startAutoPlay;
    private int startWindow;
    private long startPosition;

    private static final String KEY_WINDOW = "window";
    private static final String KEY_POSITION = "position";
    private static final String KEY_AUTO_PLAY = "auto_play";

    PlayerView playerView;

    public PlayerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.player_fragment, container, false);
        playerView = view.findViewById(R.id.simple_player_view);
        object = (Step) getArguments().get(getString(R.string.stepsObject));

        if (savedInstanceState!=null) {

            startAutoPlay = savedInstanceState.containsKey(KEY_AUTO_PLAY);
            startWindow = savedInstanceState.getInt(KEY_WINDOW);
            startPosition = savedInstanceState.getLong(KEY_POSITION);

            Log.e("startPosition savedIns.",""+startPosition);

        } else {

            Log.e("startPosition notSaved.",""+startPosition);
            Bitmap thumb = ThumbnailUtils.createVideoThumbnail(object.getThumbnailURL(), MediaStore.Images.Thumbnails.MINI_KIND);

            if (!TextUtils.isEmpty(object.getThumbnailURL())) {

                int resId = getResources().getIdentifier(object.getThumbnailURL(), getString(R.string.drawable), getContext().getPackageName());
                Drawable d = getContext().getResources().getDrawable(resId, getContext().getTheme());
                playerView.setBackground(d);

            }
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        updateStartPosition();

        outState.putBoolean(KEY_AUTO_PLAY, startAutoPlay);
        outState.putInt(KEY_WINDOW, startWindow);
        outState.putLong(KEY_POSITION, startPosition);
    }

    private void updateStartPosition() {
        if (player != null) {
            startAutoPlay = player.getPlayWhenReady();
            startWindow = player.getCurrentWindowIndex();
            startPosition = Math.max(0, player.getContentPosition());
            Log.e("startPosition update",""+startPosition);
        }
    }


    public void initializePlayer() {

        if(player==null) {
            player = ExoPlayerFactory.newSimpleInstance(
                    new DefaultRenderersFactory(getContext()),
                    new DefaultTrackSelector(), new DefaultLoadControl());

            playerView.setPlayer(player);
            Uri uri = Uri.parse(object.getVideoURL());

            MediaSource mediaSource = new ExtractorMediaSource.Factory(
                    new DefaultHttpDataSourceFactory(getString(R.string.exoplayer_codelab))).
                    createMediaSource(uri);
            player.setPlayWhenReady(true);
            player.prepare(mediaSource, true, false);
            Log.e("startPosition", "initializePlayer : " + startPosition);

            player.seekTo(startWindow, startPosition);
        }


    }

    @Override
    public void onStop() {
        super.onStop();

        Log.e("startPosition onStop",""+startPosition);

        if (Util.SDK_INT > 23) {
            releasePlayer();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("startPosition onDestroy",""+startPosition);
        releasePlayer();
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.e("startPosition onStart",""+startPosition);
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("startPosition onPause",""+startPosition);
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("startPosition onPause",""+startPosition);
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            //updateStartPosition();
            player.release();
            Log.e("startPosition r_player",""+startPosition+" and player "+player.getContentPosition());
           player = null;
        }
    }

}




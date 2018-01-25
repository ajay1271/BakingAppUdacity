package facebooklogintest.cavepass.com.bakingapp.UI;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.net.URI;
import java.net.URL;

import facebooklogintest.cavepass.com.bakingapp.ModelClasses.Step;
import facebooklogintest.cavepass.com.bakingapp.R;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

/**
 * Created by Ajay on 22-01-2018.
 */

public class RecipeStepDetail extends AppCompatActivity {

    TextView instruction;
    SimpleExoPlayer player;
    SharedPreferences.Editor editor;
    long seekToFor;
    Step object;


    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipe_step_detail);

        SharedPreferences sharedPreferences = getSharedPreferences("playerContentPosition",MODE_PRIVATE);


        editor =  sharedPreferences.edit();

        object = (Step) getIntent().getExtras().get("object");

        if(sharedPreferences!=null){

            seekToFor = sharedPreferences.getLong("seekToFor"+object.getVideoURL(),0);

        }



        if (this.getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT){

            instruction = findViewById(R.id.recipe_instruction);


            instruction.setText(object.getDescription());


        }

        if (this.getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE){

           getSupportActionBar().hide();
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

            // getActionBar().hide();




        }




        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);


        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

        SimpleExoPlayerView simpleExoPlayerView = findViewById(R.id.exo_player_view);

        simpleExoPlayerView.requestFocus();


        simpleExoPlayerView.setPlayer(player);


        //  player.setPlayWhenReady();
        simpleExoPlayerView.requestFocus();
        // simpleExoPlayerView.setPlayer(player);



        Uri uri = Uri.parse(object.getVideoURL());
        MediaSource mediaSource = new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);

        player.prepare(mediaSource, true, false);

        //  player.prepare(mediaSource);

       // player.setVideoScalingMode(360);

        player.setPlayWhenReady(true);



            player.seekTo(seekToFor);







    }

    @Override
    protected void onStop() {
        super.onStop();

        editor.putLong("seekToFor"+object.getVideoURL(),player.getContentPosition());
        editor.commit();

        Toast.makeText(this,"onDestroyCalled with "+player.getContentPosition(),Toast.LENGTH_SHORT).show();
    }
}

package facebooklogintest.cavepass.com.bakingapp.UI;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import facebooklogintest.cavepass.com.bakingapp.ModelClasses.ApiResponce;
import facebooklogintest.cavepass.com.bakingapp.ModelClasses.Step;
import facebooklogintest.cavepass.com.bakingapp.R;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

/**
 * Created by Ajay on 26-01-2018.
 */

public class PlayerUI extends AppCompatActivity {

    Step object;
   // ApiResponce list;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.player_ui);

        //  list = getIntent().getParcelableExtra("object");

        object = getIntent().getParcelableExtra("object");

        Toast.makeText(this, "Entered PlayerUI " + object.getVideoURL(), Toast.LENGTH_SHORT).show();

        if (this.getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT) {

            LinearLayout layout = findViewById(R.id.player_ui_layout);
            layout.removeAllViewsInLayout();


            Bundle stepsBundle = new Bundle();
            stepsBundle.putParcelable("stepsObject", object);

            FragmentManager fragmentManager = getFragmentManager();

            PlayerFragment player = new PlayerFragment();
            player.setArguments(stepsBundle);
            Description description = new Description();
            description.setArguments(stepsBundle);

            fragmentManager.beginTransaction().add(R.id.player_fragment, player).commit();
            fragmentManager.beginTransaction().add(R.id.step_long_description, description).commit();


        } else {


            getSupportActionBar().hide();
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

            Bundle stepsBundle = new Bundle();
            stepsBundle.putParcelable("stepsObject", object);

            FragmentManager fragmentManager = getFragmentManager();

            PlayerFragment player = new PlayerFragment();
            player.setArguments(stepsBundle);

            fragmentManager.beginTransaction().add(R.id.player_fragment, player).commit();


        }
    }
}

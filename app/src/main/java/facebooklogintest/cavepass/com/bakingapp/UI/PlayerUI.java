package facebooklogintest.cavepass.com.bakingapp.UI;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import facebooklogintest.cavepass.com.bakingapp.ModelClasses.ApiResponce;
import facebooklogintest.cavepass.com.bakingapp.ModelClasses.Step;
import facebooklogintest.cavepass.com.bakingapp.R;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;



public class PlayerUI extends AppCompatActivity {

    Step object;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (CheckNetwork.isInternetAvailable(this)) {

            setContentView(R.layout.player_ui);
            object = getIntent().getParcelableExtra(getString(R.string.object));
            String title = object.getShortDescription();
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            if (this.getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT) {

                Bundle stepsBundle = new Bundle();
                stepsBundle.putParcelable(getString(R.string.stepsObject), object);
                FragmentManager fragmentManager = getFragmentManager();

                PlayerFragment player = new PlayerFragment();
                player.setArguments(stepsBundle);
                Description description = new Description();
                description.setArguments(stepsBundle);
                Log.e("startPosition", "ORIENTATION_PORTRAIT");

                if(savedInstanceState==null) {

                    fragmentManager.beginTransaction().add(R.id.player_fragment, player).commit();
                    fragmentManager.beginTransaction().add(R.id.step_long_description, description).commit();
                }

            }
             else if (this.getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {

                getSupportActionBar().hide();
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

                Bundle stepsBundle = new Bundle();
                stepsBundle.putParcelable(getString(R.string.stepsObject), object);

                FragmentManager fragmentManager = getFragmentManager();

                PlayerFragment player = new PlayerFragment();
                player.setArguments(stepsBundle);

                Log.e("startPosition","ORIENTATION_LANDSCAPE");
                if(savedInstanceState==null) {

                    fragmentManager.beginTransaction().add(R.id.player_fragment, player).commit();
                    //fragmentManager.beginTransaction().add(R.id.step_long_description, description).commit();
                }

            } else {
                Intent i = new Intent(this, NoInternet.class);
                startActivity(i);
                Log.e("startPosition","ORIENTATION_LANDSCAPE ELSE");
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

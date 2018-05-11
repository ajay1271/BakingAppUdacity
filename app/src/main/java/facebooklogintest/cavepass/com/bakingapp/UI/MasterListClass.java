package facebooklogintest.cavepass.com.bakingapp.UI;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.Adapters.RecipeRecyclerViewAdapter;
import facebooklogintest.cavepass.com.bakingapp.Adapters.StepsAdapter;
import facebooklogintest.cavepass.com.bakingapp.Data.DBContract;
import facebooklogintest.cavepass.com.bakingapp.ModelClasses.ApiResponce;
import facebooklogintest.cavepass.com.bakingapp.ModelClasses.Ingredient;
import facebooklogintest.cavepass.com.bakingapp.R;
import facebooklogintest.cavepass.com.bakingapp.Retrofit.ApiClient;
import facebooklogintest.cavepass.com.bakingapp.Retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ajay on 26-01-2018.
 */

public class MasterListClass extends AppCompatActivity implements StepsAdapter.onItemClick {

    ApiResponce reciepeObject;
    public static ArrayList<ApiResponce> recipeObjectsList;
    public static List<ApiResponce> recipeObjectsList2;
    SharedPreferences.Editor editor;
    boolean fromWidget = false;
    long seekToFor;
    FragmentManager fragmentManager;

    public static final String PLAYER_TAG = "PLAYER_TAG";
    public static final String DESCRIPTION_TAG = "DESCRIPTION_TAG";
    long timeToReachResponse;
    long start;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Checking to see if the app is opened by widget by checking existence of startFromWidget value from intent
        if (getIntent().getExtras().getString("stringFromWidget") != null) {

            //App opened by widget
            fromWidget = true;

            //Getting last stored value from Database assign it to temp
            Cursor c = getContentResolver().query(DBContract.CONTENT_URI, null, null, null);
            assert c != null;
            c.moveToFirst();
            final String temp = c.getString(c.getColumnIndex(DBContract.RECIPE_NAME));

            //Fetching data from API
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<List<ApiResponce>> call = apiService.getResponce();
            call.enqueue(new Callback<List<ApiResponce>>() {
                @Override
                public void onResponse(Call<List<ApiResponce>> call, Response<List<ApiResponce>> response) {

                    for(ApiResponce x : response.body()){
                        if(x.getName().equals(temp)) {
                            reciepeObject = x;
                        }
                    }
                   // Toast.makeText(MasterListClass.this,""+x.getName()+" temp is "+temp,Toast.LENGTH_SHORT).show();
                    getSupportActionBar().setTitle(reciepeObject.getName());
                    fillFragments();

                    //Making sure received  data
                    Toast.makeText(MasterListClass.this, reciepeObject.getName(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<List<ApiResponce>> call, Throwable t) {
                    Log.e("networkError", t.getMessage());
                }
            });
        }

        //if the app not opened by widget get the object from the intent
        else {
            reciepeObject = getIntent().getParcelableExtra(getString(R.string.object));
            getSupportActionBar().setTitle(reciepeObject.getName());
            fillFragments();
        }

    }

    public void fillFragments(){

        fragmentManager = getFragmentManager();

        if (fragmentManager.getBackStackEntryCount() == 0) {


            Bundle ingredientsBundle = new Bundle();
            ingredientsBundle.putParcelableArrayList(getString(R.string.ingredientObject), reciepeObject.getIngredients());

            Bundle stepsBundle = new Bundle();
            stepsBundle.putParcelableArrayList(getString(R.string.stepsObject), reciepeObject.getSteps());

            IngredientsFragmentClass ingredientsFragmentClass = new IngredientsFragmentClass();
            ingredientsFragmentClass.setArguments(ingredientsBundle);

            RecipeStepsFragmentClass recipeStepsFragmentClass = new RecipeStepsFragmentClass();
            recipeStepsFragmentClass.setArguments(stepsBundle);

            fragmentManager.beginTransaction().add(R.id.ingredients_fragment, ingredientsFragmentClass).addToBackStack(PLAYER_TAG).commit();
            fragmentManager.beginTransaction().add(R.id.recipe_Steps_fragment, recipeStepsFragmentClass).addToBackStack(DESCRIPTION_TAG).commit();

            if (getResources().getBoolean(R.bool.isTablet))

            {
                Bundle playerUiBundle = new Bundle();
                playerUiBundle.putParcelable(getString(R.string.stepsObject), reciepeObject.getSteps().get(0));
                playerUiBundle.putLong(getString(R.string.seekTo), seekToFor);


                PlayerFragment player = new PlayerFragment();
                player.setArguments(playerUiBundle);
                Description description = new Description();
                description.setArguments(playerUiBundle);

                fragmentManager.beginTransaction().add(R.id.player_fragment, player).addToBackStack(PLAYER_TAG).commit();
                fragmentManager.beginTransaction().add(R.id.step_long_description, description).addToBackStack(DESCRIPTION_TAG).commit();
                // fragmentManager.beginTransaction( ).addToBackStack(null);
            }


        } else {
            Bundle playerUiBundle = new Bundle();
            playerUiBundle.putParcelable(getString(R.string.stepsObject), reciepeObject.getSteps().get(0));
            playerUiBundle.putLong(getString(R.string.seekTo), seekToFor);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }


    @Override
    public void onClick(int position) {

        if (!getResources().getBoolean(R.bool.isTablet)) {
            Intent i = new Intent(this, PlayerUI.class);
            i.putExtra(getString(R.string.object), reciepeObject.getSteps().get(position));
            startActivity(i);
        } else {


            Bundle playerUiBundle = new Bundle();
            playerUiBundle.putParcelable(getString(R.string.stepsObject), reciepeObject.getSteps().get(position));
            // fragmentManager = getFragmentManager();


            PlayerFragment player = new PlayerFragment();
            player.setArguments(playerUiBundle);
            Description description = new Description();
            description.setArguments(playerUiBundle);


            fragmentManager.beginTransaction().replace(R.id.player_fragment, player).commit();
            fragmentManager.beginTransaction().replace(R.id.step_long_description, description).commit();
            //  fragmentManager.beginTransaction().addToBackStack(null);


        }


    }
}

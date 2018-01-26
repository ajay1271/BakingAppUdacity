package facebooklogintest.cavepass.com.bakingapp.UI;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.Adapters.StepsAdapter;
import facebooklogintest.cavepass.com.bakingapp.ModelClasses.ApiResponce;
import facebooklogintest.cavepass.com.bakingapp.ModelClasses.Ingredient;
import facebooklogintest.cavepass.com.bakingapp.R;

/**
 * Created by Ajay on 26-01-2018.
 */

public class MasterListClass extends AppCompatActivity implements StepsAdapter.onItemClick {

    ApiResponce list;
    SharedPreferences.Editor editor;
    long seekToFor;
    FragmentManager fragmentManager;

    public static final String PLAYER_TAG = "PLAYER_TAG";
    public static final String DESCRIPTION_TAG = "DESCRIPTION_TAG";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_list);

        fragmentManager = getFragmentManager();
        list = getIntent().getParcelableExtra("object");
        if (fragmentManager.getBackStackEntryCount() == 0) {


            Bundle ingredientsBundle = new Bundle();
            ingredientsBundle.putParcelableArrayList("ingredientObject", list.getIngredients());

            Bundle stepsBundle = new Bundle();
            stepsBundle.putParcelableArrayList("stepsObject", list.getSteps());


            IngredientsFragmentClass ingredientsFragmentClass = new IngredientsFragmentClass();
            ingredientsFragmentClass.setArguments(ingredientsBundle);

            RecipeStepsFragmentClass recipeStepsFragmentClass = new RecipeStepsFragmentClass();
            recipeStepsFragmentClass.setArguments(stepsBundle);

            fragmentManager.beginTransaction().add(R.id.ingredients_fragment, ingredientsFragmentClass).addToBackStack(PLAYER_TAG).commit();
            fragmentManager.beginTransaction().add(R.id.recipe_Steps_fragment, recipeStepsFragmentClass).addToBackStack(DESCRIPTION_TAG).commit();

            if (findViewById(R.id.player_ui_layout) == null)

            {


                Bundle playerUiBundle = new Bundle();
                playerUiBundle.putParcelable("stepsObject", list.getSteps().get(0));
                playerUiBundle.putLong("seekTo", seekToFor);


                PlayerFragment player = new PlayerFragment();
                player.setArguments(playerUiBundle);
                Description description = new Description();
                description.setArguments(playerUiBundle);

                fragmentManager.beginTransaction().add(R.id.player_fragment, player).addToBackStack(PLAYER_TAG).commit();
                fragmentManager.beginTransaction().add(R.id.step_long_description, description).addToBackStack(DESCRIPTION_TAG).commit();
                // fragmentManager.beginTransaction().addToBackStack(null);

            }


        }
        else{
            Bundle playerUiBundle = new Bundle();
            playerUiBundle.putParcelable("stepsObject", list.getSteps().get(0));
            playerUiBundle.putLong("seekTo", seekToFor);

        }
    }



    @Override
    public void onClick(int position) {

        if(findViewById(R.id.player_ui_layout)!=null){
        Intent i = new Intent(this,PlayerUI.class);
        i.putExtra("object",list.getSteps().get(position));
        startActivity(i);}

        else
            {



                Bundle playerUiBundle = new Bundle();
                playerUiBundle.putParcelable("stepsObject", list.getSteps().get(position));
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

package facebooklogintest.cavepass.com.bakingapp.UI;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.Adapters.StepsAdapter;
import facebooklogintest.cavepass.com.bakingapp.ModelClasses.Step;
import facebooklogintest.cavepass.com.bakingapp.R;

/**
 * Created by Ajay on 26-01-2018.
 */

public class RecipeStepsFragmentClass extends Fragment {

    List<Step> list;

   public RecipeStepsFragmentClass(){


    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

       list = getArguments().getParcelableArrayList(getString(R.string.stepsObject));


       View rootView  = inflater.inflate(R.layout.recipe_steps_fragment,container,false);

        RecyclerView recipeStepsRecyclerView = rootView.findViewById(R.id.recipe_steps_recycler_view);

        recipeStepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        recipeStepsRecyclerView.setAdapter(new StepsAdapter(getContext(),list));

       return rootView;

    }



}

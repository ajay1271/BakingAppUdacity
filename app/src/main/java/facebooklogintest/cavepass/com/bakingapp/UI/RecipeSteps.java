package facebooklogintest.cavepass.com.bakingapp.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.Adapters.IngredientsAdapter;
import facebooklogintest.cavepass.com.bakingapp.Adapters.StepsAdapter;
import facebooklogintest.cavepass.com.bakingapp.ModelClasses.ApiResponce;
import facebooklogintest.cavepass.com.bakingapp.R;
import facebooklogintest.cavepass.com.bakingapp.Retrofit.ApiInterface;

/**
 * Created by Ajay on 09-01-2018.
 */

public class RecipeSteps extends AppCompatActivity {

    int index;
    ArrayList<ApiResponce> list;
    ApiResponce object;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ingredients_steps);

//       index = (int)getIntent().getExtras().get("list");
    if(getIntent().getParcelableExtra("object")!=null){

        Log.e("At RecipeStpes"," Not null");


    }

    else{


        Log.e("At RecipeStpes","  null");

    }



/*
        RecyclerView stepsRecyclerView = findViewById(R.id.steps);
        RecyclerView ingredientsRecyclerView = findViewById(R.id.ingredients);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        stepsRecyclerView.setAdapter(new IngredientsAdapter(this,list,index));
        ingredientsRecyclerView.setAdapter(new StepsAdapter(this,list,index));
*/

    }
}

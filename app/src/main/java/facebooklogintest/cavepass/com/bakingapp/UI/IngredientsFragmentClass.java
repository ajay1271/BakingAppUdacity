package facebooklogintest.cavepass.com.bakingapp.UI;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.Adapters.IngredientsAdapter;
import facebooklogintest.cavepass.com.bakingapp.ModelClasses.Ingredient;
import facebooklogintest.cavepass.com.bakingapp.R;

public class IngredientsFragmentClass extends Fragment {

    List<Ingredient> list;
    Context context;

    public IngredientsFragmentClass() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (CheckNetwork.isInternetAvailable(getContext())) {

            context = getContext();
            list = getArguments().getParcelableArrayList(getString(R.string.ingredientObject));
            View rootView = inflater.inflate(R.layout.ingredients_fragment, container, false);
            RecyclerView ingredientsRecyclerView = rootView.findViewById(R.id.ingredients_recycler_view);
            ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            ingredientsRecyclerView.setAdapter(new IngredientsAdapter(context, list));
            return rootView;

        } else {
            Intent i = new Intent(getContext(), NoInternet.class);
            startActivity(i);
            Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
        }

        return null;
    }
}

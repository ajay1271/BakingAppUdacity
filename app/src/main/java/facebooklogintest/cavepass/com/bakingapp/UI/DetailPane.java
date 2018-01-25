package facebooklogintest.cavepass.com.bakingapp.UI;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import facebooklogintest.cavepass.com.bakingapp.R;

/**
 * Created by Ajay on 25-01-2018.
 */

public class DetailPane extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


      View view = inflater.inflate(R.layout.recipe_step_detail,container,false);

      return  view;


    }
}

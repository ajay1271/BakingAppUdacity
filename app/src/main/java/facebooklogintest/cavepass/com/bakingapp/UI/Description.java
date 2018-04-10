package facebooklogintest.cavepass.com.bakingapp.UI;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import facebooklogintest.cavepass.com.bakingapp.ModelClasses.Step;
import facebooklogintest.cavepass.com.bakingapp.R;

/**
 * Created by Ajay on 26-01-2018.
 */

public class Description extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.long_desciption_fragment,container,false);

        TextView description =  view.findViewById(R.id.long_description);

        Step object = getArguments().getParcelable(getString(R.string.stepsObject));

        description.setText(object.getDescription());

        return view;
    }
}

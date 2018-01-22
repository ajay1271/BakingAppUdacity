package facebooklogintest.cavepass.com.bakingapp.UI;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import facebooklogintest.cavepass.com.bakingapp.R;

/**
 * Created by Ajay on 22-01-2018.
 */

public class RecipeStepDetail extends AppCompatActivity {

    TextView instruction;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipe_step_detail);

        instruction = findViewById(R.id.recipe_instruction);

        instruction.setText((String)getIntent().getExtras().get("instructions"));



    }
}

package facebooklogintest.cavepass.com.bakingapp.UI;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import facebooklogintest.cavepass.com.bakingapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;

/**
 * Created by Ajay on 24-04-2018.
 */

@RunWith(AndroidJUnit4.class)
public class IntentsTest {

    private IdlingResource mIdlingResource;

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule =
            new IntentsTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource(){

        mIdlingResource = intentsTestRule.getActivity().getIdlingResource();

        IdlingRegistry.getInstance().register(mIdlingResource);


    }




    @Test
    public void test(){



        onView(ViewMatchers.withId(R.id.recyclerView_recipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,
                        click()));


        intended(toPackage("facebooklogintest.cavepass.com.bakingapp"));





    }


}

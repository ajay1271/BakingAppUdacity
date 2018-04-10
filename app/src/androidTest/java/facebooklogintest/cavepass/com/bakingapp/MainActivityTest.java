package facebooklogintest.cavepass.com.bakingapp;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.anything;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import facebooklogintest.cavepass.com.bakingapp.UI.MainActivity;

/**
 * Created by Ajay on 31-03-2018.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource(){

        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();

        Espresso.registerIdlingResources(mIdlingResource);

    }


    @Test
    public void clickGridViewItem_opensRecipe(){

     //  onView(withId(R.id.recipe_steps_recycler_view)).perform(click());

      //  onData(anything()).inAdapterView(withId(R.id.recyclerView_recipes)).atPosition(1).perform(click());


      onView(withId(R.id.recyclerView_recipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));


    }



}


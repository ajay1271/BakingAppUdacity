package facebooklogintest.cavepass.com.bakingapp.UI;


import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import facebooklogintest.cavepass.com.bakingapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTestER {

    private IdlingResource mIdlingResource;



    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource(){

        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();

        IdlingRegistry.getInstance().register(mIdlingResource);

        // Espresso.registerIdlingResource(countingResource);

    }




    @Test
    public void mainActivityTestER() {
        try {
            Thread.sleep(5966);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //onView(allOf( withId(R.id.poi_recycler_view), isDisplayed())) .perform(scrollTo(hasDescendant(withText(myDestination))), click());

        onView(ViewMatchers.withId(R.id.recyclerView_recipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,
                        click()));

        onView(ViewMatchers.withId(R.id.recipe_steps_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,
                        click()));

        pressBack();
        pressBack();

        onView(ViewMatchers.withId(R.id.recyclerView_recipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1,
                        click()));

        onView(ViewMatchers.withId(R.id.recipe_steps_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2,
                        click()));



        pressBack();
















        //   ViewInteraction appCompatTextView = onView(
        //         allOf(withId(R.id.step_short_description), withText("Step 0: Recipe Introduction"),
        //               childAtPosition(
        //                     childAtPosition(
        //                           withClassName(is("android.support.constraint.ConstraintLayout")),
        //                         0),
        //               0),
        //     isDisplayed()));
        //  appCompatTextView.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(4966);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //  pressBack();

        //     pressBack();

    }
}

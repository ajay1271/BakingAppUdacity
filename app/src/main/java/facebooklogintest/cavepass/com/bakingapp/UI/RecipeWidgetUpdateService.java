package facebooklogintest.cavepass.com.bakingapp.UI;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

import facebooklogintest.cavepass.com.bakingapp.Widget.RecipeAppWidgetProvider2;

/**
 * Created by Ajay on 16-05-2018.
 */
public class RecipeWidgetUpdateService extends IntentService {


    public RecipeWidgetUpdateService() {
        super("RecipeWidgetUpdateService");
    }

    public static final String ACTION = "facebooklogintest.cavepass.com.bakingapp.update";


    @Override
    protected void onHandleIntent( Intent intent) {

        Log.e("isService","Started");

        if(intent.getAction().equals(ACTION)){



            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeAppWidgetProvider2.class));
            //Now update all widgets

            if(appWidgetIds.length>0) {

                Log.e("Called","Not added");

                RecipeAppWidgetProvider2.updateAppWidget(this, appWidgetManager, appWidgetIds[0]);
            }

            else{
                Log.e("Called","length is 0");
            }



        }




    }
}
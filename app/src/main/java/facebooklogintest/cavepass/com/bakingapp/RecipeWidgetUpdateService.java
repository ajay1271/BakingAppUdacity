package facebooklogintest.cavepass.com.bakingapp;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import facebooklogintest.cavepass.com.bakingapp.Data.DBContract;

/**
 * Created by Ajay on 13-04-2018.
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

            Cursor c = getContentResolver().query(DBContract.CONTENT_URI,null,null,null);

            if (c != null) {
                c.moveToFirst();
            }

            assert c != null;
            String temp = c.getString(c.getColumnIndex(DBContract.RECIPE_NAME));

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeWidget.class));
            //Now update all widgets

            if(appWidgetIds.length>0) {

              Log.e("Widget","Not added");

                RecipeWidget.updateWidget(this, appWidgetManager, appWidgetIds[0], temp);
            }



        }




    }
}

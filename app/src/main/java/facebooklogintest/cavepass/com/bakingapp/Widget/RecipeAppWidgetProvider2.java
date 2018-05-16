package facebooklogintest.cavepass.com.bakingapp.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import facebooklogintest.cavepass.com.bakingapp.R;
import facebooklogintest.cavepass.com.bakingapp.UI.RecipeWidgetUpdateService;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeAppWidgetProvider2 extends AppWidgetProvider {

    public static final String TOAST_ACTION = "com.example.android.stackwidget.TOAST_ACTION";
    public static final String EXTRA_ITEM = "com.example.android.stackwidget.EXTRA_ITEM";

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId) {
        Log.e("Called","RecipeAppWidgetProvider.updateWidget()");

        Intent svcIntent = new Intent(context, RecipeWidgetUpdateService.class);
        //svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        RemoteViews widget = new RemoteViews(context.getPackageName(), R.layout.recipe_app_widget_provider2);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Log.e("Called", "setRemoteAdapter()");

            widget.setRemoteAdapter(R.id.list_widget, svcIntent);
        }
        else
            widget.setRemoteAdapter(appWidgetId, R.id.list_widget, svcIntent);


        appWidgetManager.updateAppWidget(appWidgetId, widget);
        //appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.list_widget);

    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}


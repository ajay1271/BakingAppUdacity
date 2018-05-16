package facebooklogintest.cavepass.com.bakingapp.UI;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import facebooklogintest.cavepass.com.bakingapp.R;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeAppWidgetProvider2 extends AppWidgetProvider {

    public static final String TOAST_ACTION = "com.example.android.stackwidget.TOAST_ACTION";
    public static final String EXTRA_ITEM = "com.example.android.stackwidget.EXTRA_ITEM";

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId) {

        /*

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.row_recipe_list_widget);
        views.setTextViewText(R.id.ingredient_Widget,""+ views.getLayoutId());

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);


/**/

        Log.e("Called", "RecipeAppWidgetProvider2");


        // Instantiate the RemoteViews object for the app widget layout.
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.recipe_app_widget_provider2);
        // Set up the RemoteViews object to use a RemoteViews adapter.
        // This adapter connects
        // to a RemoteViewsService  through the specified intent.
        // This is how you populate the data.

        Intent intent = new Intent(context, ListWidgetService.class);
        // Add the app widget ID to the intent extras.

        rv.setRemoteAdapter(appWidgetId, intent);


        // The empty view is displayed when the collection has no items.
        // It should be in the same layout used to instantiate the RemoteViews
        // object above.
        //   rv.setEmptyView(R.id.list_view_widget, R.id);

        //
        // Do additional processing specific to this app widget...
        //

        appWidgetManager.updateAppWidget(appWidgetId, rv);


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


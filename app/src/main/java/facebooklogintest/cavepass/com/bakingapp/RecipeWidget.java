package facebooklogintest.cavepass.com.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import facebooklogintest.cavepass.com.bakingapp.Adapters.RecipeRecyclerViewAdapter;
import facebooklogintest.cavepass.com.bakingapp.Data.DBContract;
import facebooklogintest.cavepass.com.bakingapp.ModelClasses.ApiResponce;
import facebooklogintest.cavepass.com.bakingapp.Retrofit.ApiClient;
import facebooklogintest.cavepass.com.bakingapp.Retrofit.ApiInterface;
import facebooklogintest.cavepass.com.bakingapp.UI.CallNetwork;
import facebooklogintest.cavepass.com.bakingapp.UI.MainActivity;
import facebooklogintest.cavepass.com.bakingapp.UI.MasterListClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of App Widget functionality.
 */

public class RecipeWidget extends AppWidgetProvider{

    List<ApiResponce> list;
    public static final String ACTION ="CALL NETWORK FOR WIDGET";

    public RecipeWidget() {
        super();
    }


    public static void updateWidget(Context context,AppWidgetManager appWidgetManager,int appWidgetId,String text){

        RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.recipe_widget);

        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
        views.setTextViewText(R.id.appwidget_text,text);
        appWidgetManager.updateAppWidget(appWidgetId,views);

    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
        Intent intent = new Intent(context, MasterListClass.class);

        Log.e("widget ","Entered in method");

        Cursor c = context.getContentResolver().query(DBContract.CONTENT_URI,null,null,null);


            c.moveToFirst();



        String temp = c.getString(c.getColumnIndex(DBContract.RECIPE_NAME));

        intent.putExtra( "stringFromWidget",temp);

        Log.e("widget ","sendingRecipe "+temp);

        Toast.makeText(context,temp+" says hello",Toast.LENGTH_SHORT).show();



        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

       views.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);


        appWidgetManager.updateAppWidget(appWidgetIds[0], views);
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }



    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
    }
}
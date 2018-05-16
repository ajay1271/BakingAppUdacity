package facebooklogintest.cavepass.com.bakingapp.UI;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import facebooklogintest.cavepass.com.bakingapp.R;

/**
 * Created by Ajay on 16-05-2018.
 */

public class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {


    private static final int mCount = 10;
    private String[] str = {"hello","hello","hello","hello","hello","hello","hello","hello","hello"};
    private Context mContext;
    private int mAppWidgetId;


    public ListRemoteViewsFactory(Context context, Intent intent){
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }



    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return str.length;
    }

    public RemoteViews getViewAt(int position) {
        // position will always range from 0 to getCount() - 1.

        // Construct a RemoteViews item based on the app widget item XML file, and set the
        // text based on the position.
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.row_recipe_list_widget);
        rv.setTextViewText(R.id.ingredient_Widget, str[position]);
        rv.setTextViewText(R.id.quantity_widget, str[position]+"1111111111111");

        // Next, set a fill-intent, which will be used to fill in the pending intent template
        // that is set on the collection view in StackWidgetProvider.
        Bundle extras = new Bundle();
        extras.putInt(RecipeAppWidgetProvider2.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        // Make it possible to distinguish the individual on-click
        // action of a given item
      //  rv.setOnClickFillInIntent(R.id.widget_item, fillInIntent);



        // Return the RemoteViews object.
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}

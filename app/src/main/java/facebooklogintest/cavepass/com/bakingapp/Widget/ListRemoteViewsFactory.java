package facebooklogintest.cavepass.com.bakingapp.Widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import facebooklogintest.cavepass.com.bakingapp.R;

/**
 * Created by Ajay on 16-05-2018.
 * 
 */


class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private String[] array = {"Hello","Hello","Hello","Hello","Hello","Hello","Hello","Hello","Hello","Hello","Hello"};//Dummy Data
    private Context context;

    public ListRemoteViewsFactory(Context applicationContext) {
        this.context=applicationContext;

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
        return array.length;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.row_recipe_list_widget);

        views.setTextViewText(R.id.ingredient_Widget, array[position]);
        views.setTextViewText(R.id.quantity_widget, array[position]);

        Intent fillInIntent = new Intent();
        // fillInIntent.putExtra("ItemTitle",dataList.get(position).title);
        //    fillInIntent.putExtra("ItemSubTitle",dataList.get(position).subTitle);
        //   views.setOnClickFillInIntent(R.id.parentView, fillInIntent);
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {

        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}

package facebooklogintest.cavepass.com.bakingapp.UI;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.R;

/**
 * Created by Ajay on 16-05-2018.
 */

public class ListWidgetService extends RemoteViewsService {
    List<String> remoteViewingredientsList;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this.getApplicationContext(),intent);
    }


    class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        Context mContext = null;

        public ListRemoteViewsFactory(Context context,Intent intent) {
            mContext = context;

        }

        @Override
        public void onCreate() {


            //Dummy Data

            remoteViewingredientsList.add("hello");
            remoteViewingredientsList.add("hello");
            remoteViewingredientsList.add("hello");
            remoteViewingredientsList.add("hello");
            remoteViewingredientsList.add("hello");
            remoteViewingredientsList.add("hello");
            remoteViewingredientsList.add("hello");
            remoteViewingredientsList.add("hello");
            remoteViewingredientsList.add("hello");

        }

        @Override
        public void onDataSetChanged() {
           // remoteViewingredientsList = ingredientsList;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {

            return remoteViewingredientsList.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {

            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.row_recipe_list_widget);

            views.setTextViewText(R.id.ingredient_Widget, remoteViewingredientsList.get(position));
            views.setTextViewText(R.id.quantity_widget, ""+position);

            Intent fillInIntent = new Intent();
            //fillInIntent.putExtras(extras);
          //  views.setOnClickFillInIntent(R.id, fillInIntent);

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


}


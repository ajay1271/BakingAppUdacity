package facebooklogintest.cavepass.com.bakingapp.Widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import facebooklogintest.cavepass.com.bakingapp.R;

/**
 * Created by Ajay on 16-05-2018.
 *
 */

public class ListWidgetService extends RemoteViewsService {



    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.e("Called","RemoteViewsFactory");
        return new ListRemoteViewsFactory (this.getApplicationContext());
    }
}

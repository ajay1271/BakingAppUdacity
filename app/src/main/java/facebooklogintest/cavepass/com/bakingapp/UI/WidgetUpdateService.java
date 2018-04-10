package facebooklogintest.cavepass.com.bakingapp.UI;

import android.app.IntentService;


import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import facebooklogintest.cavepass.com.bakingapp.Data.DBContract;
import facebooklogintest.cavepass.com.bakingapp.Data.DBHelper;


public class WidgetUpdateService extends IntentService {
    public WidgetUpdateService(String name) {
        super(name);
    }

    public WidgetUpdateService() {
        super("WidgetUpdateService");
    }

    String ad = "adapter";

    Context mContext = getBaseContext();


    public static final String ACTION_UPDATE_DATABASE = "cavepass.com.bakingapp.dbupdate";


    public static void updateWidget(Context context, String recipe ) {


        Intent i = new Intent(context, WidgetUpdateService.class);
        i.setAction(ACTION_UPDATE_DATABASE);
        i.putExtra("RecipeName",""+recipe);
        Toast.makeText(context, "Service Started", Toast.LENGTH_SHORT).show();
        Log.v("iSRunning", "Started");
        context.startService(i);

    }

    void test() {
        Toast.makeText(mContext, "This is a Test", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent.getAction().equals(ACTION_UPDATE_DATABASE)) {

            String recipeName = intent.getStringExtra("RecipeName");

            Log.e("iSRunning", "OK Running with Recipe :"+recipeName);

            ContentValues cv = new ContentValues();

            cv.put(DBContract.RECIPE_NAME,recipeName);

            DBHelper db = new DBHelper(getBaseContext());

            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();

            db.onUpgrade(sqLiteDatabase,0,1);

            getContentResolver().insert(DBContract.CONTENT_URI,cv);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeAppWidgetProvider.class));
            //Now update all widgets
           RecipeAppWidgetProvider.updateAppWidget(this, appWidgetManager, appWidgetIds[0]);



        }


    }
}

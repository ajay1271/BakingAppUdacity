package facebooklogintest.cavepass.com.bakingapp.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ajay on 21-02-2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static String DATABASE__NAME = "BakingDatabase";
    private static int DATABASE__VERSION = 1;


    public DBHelper(Context context) {
        super(context, DATABASE__NAME, null, DATABASE__VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_TABLE = "CREATE TABLE " +

                DBContract.TABLE_NAME + "(" +
                DBContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBContract.RECIPE_NAME + " TEXT, " +
                DBContract.INGREDIENTS + " TEXT, " +
                DBContract.QUANTITY + " TEXT, " +
                DBContract.IMAGE_ID + " TEXT" +

                "); ";

        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE '" + DBContract.TABLE_NAME + "'");

        onCreate(db);

    }
}

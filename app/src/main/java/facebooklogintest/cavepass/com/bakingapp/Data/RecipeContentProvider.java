package facebooklogintest.cavepass.com.bakingapp.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Switch;

import static facebooklogintest.cavepass.com.bakingapp.Data.DBContract.TABLE_NAME;

/**
 * Created by Ajay on 21-02-2018.
 */

public class RecipeContentProvider extends ContentProvider {

    DBHelper dbHelper;
    public static final int TASKS = 100;
    public static final int TASK_WITH_ID = 101;
    private static final UriMatcher sUriMatcher = buildUriMatcher();


    @Override
    public boolean onCreate() {

        dbHelper = new DBHelper(getContext());

        return true;
    }

    public static UriMatcher buildUriMatcher() {


        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(DBContract.AUTHORITY, DBContract.PATH_TASKS, TASKS);
        uriMatcher.addURI(DBContract.AUTHORITY, DBContract.PATH_TASKS + "/#", TASK_WITH_ID);


        return uriMatcher;
    }

    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        DBHelper dbHelper = new DBHelper(getContext());
        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor retCursor;

        switch (match) {

            case TASKS:
                retCursor = db.query(TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            case TASK_WITH_ID:

                String _id = uri.getPathSegments().get(1);
                String table = uri.getPathSegments().get(0);

                retCursor = db.query(table,
                        projection,
                        DBContract._ID + "=" + _id,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }


        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {

        final SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        final String TABLE_NAME = uri.getPathSegments().get(0);
        int match = sUriMatcher.match(uri);

        Uri returnUri;

        switch (match) {
            case TASKS:
                long id = sqLiteDatabase.insert(TABLE_NAME, null, values);

                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(DBContract.CONTENT_URI, id);
                } else {
                    throw new SQLException("Failed to insert row into :" + uri);
                }
                break;

            default:
                throw new UnsupportedOperationException("UnknownUri :" + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    void hello() {

    }
}

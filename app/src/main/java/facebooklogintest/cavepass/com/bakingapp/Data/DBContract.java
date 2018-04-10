package facebooklogintest.cavepass.com.bakingapp.Data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Ajay on 21-02-2018.
 */

public class DBContract implements BaseColumns {


    public static final String TABLE_NAME=  "bakingDB";
    public static final String _ID=  "_id";
    public static final String IMAGE_ID=  "image_id";
    public static final String RECIPE_NAME=  "recipe_name";
    public static final String AUTHORITY = "facebooklogintest.cavepass.com.bakingapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+ AUTHORITY );
    public static final String PATH_TASKS = "bakingDB";
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();



}

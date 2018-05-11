package facebooklogintest.cavepass.com.bakingapp.UI;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.Adapters.RecipeRecyclerViewAdapter;
import facebooklogintest.cavepass.com.bakingapp.ModelClasses.ApiResponce;
import facebooklogintest.cavepass.com.bakingapp.R;
import facebooklogintest.cavepass.com.bakingapp.Retrofit.ApiClient;
import facebooklogintest.cavepass.com.bakingapp.Retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ajay on 29-04-2018.
 */

public class CallNetwork extends IntentService{

    public static final String ACTION ="CALL NETWORK FOR WIDGET";

    public static List<ApiResponce> list;

    public CallNetwork() {
        super("networkCalling");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if(intent.getAction().equals(ACTION)) {

            Toast.makeText(this,"CALLED",Toast.LENGTH_SHORT).show();
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<List<ApiResponce>> call = apiService.getResponce();

            call.enqueue(new Callback<List<ApiResponce>>() {
                @Override
                public void onResponse(Call<List<ApiResponce>> call, Response<List<ApiResponce>> response) {
                    list = (response.body());

                }

                @Override
                public void onFailure(Call<List<ApiResponce>> call, Throwable t) {
                    Log.e(getString(R.string.error), t.getMessage());
                }


            });
        }
    }
}

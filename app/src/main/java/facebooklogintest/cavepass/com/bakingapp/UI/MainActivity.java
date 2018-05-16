package facebooklogintest.cavepass.com.bakingapp.UI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.IdlingResource.SimpleIdlingResource;
import facebooklogintest.cavepass.com.bakingapp.ModelClasses.ApiResponce;
import facebooklogintest.cavepass.com.bakingapp.Adapters.RecipeRecyclerViewAdapter;
import facebooklogintest.cavepass.com.bakingapp.R;
import facebooklogintest.cavepass.com.bakingapp.Retrofit.ApiClient;
import facebooklogintest.cavepass.com.bakingapp.Retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MainActivity extends AppCompatActivity {

    public static List<ApiResponce> list;
    @Nullable
    private SimpleIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (CheckNetwork.isInternetAvailable(this)) {

            setContentView(R.layout.activity_main);
            Stetho.initializeWithDefaults(this);
            mIdlingResource = getIdlingResource();
            mIdlingResource.setIdleState(false);

            final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_recipes);

            if (getResources().getBoolean(R.bool.isTablet) && this.getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
                recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            } else {
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            }

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            Call<List<ApiResponce>> call = apiService.getResponce();

            call.enqueue(new Callback<List<ApiResponce>>() {
                @Override
                public void onResponse(Call<List<ApiResponce>> call, Response<List<ApiResponce>> response) {

                    list = (response.body());
                    recyclerView.setAdapter(new RecipeRecyclerViewAdapter(MainActivity.this, list));
                    mIdlingResource.setIdleState(true);
                }

                @Override
                public void onFailure(Call<List<ApiResponce>> call, Throwable t) {
                    Log.e(getString(R.string.error), t.getMessage());
                    Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Intent i = new Intent(this,NoInternet.class);
            startActivity(i);
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
        }

    }


    @VisibleForTesting
    @NonNull
    public SimpleIdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }


}

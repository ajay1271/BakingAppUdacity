package facebooklogintest.cavepass.com.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.API.ApiResponce;
import facebooklogintest.cavepass.com.bakingapp.API.RecipeApiReq;
import facebooklogintest.cavepass.com.bakingapp.Retrofit.ApiClient;
import facebooklogintest.cavepass.com.bakingapp.Retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_recipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new RecipeRecyclerViewAdapter(this));


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<RecipeApiReq> call = apiService.getResponce();

        call.enqueue(new Callback<RecipeApiReq>() {
            @Override
            public void onResponse(Call<RecipeApiReq> call, Response<RecipeApiReq> response) {

                List<ApiResponce> list = response.body().getResults();

                Log.e("Responce","Success");

            }

            @Override
            public void onFailure(Call<RecipeApiReq> call, Throwable t) {

                Log.e("Responce","Failed "+t.getMessage());

            }
        });

    }
}

package facebooklogintest.cavepass.com.bakingapp.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.ModelClasses.ApiResponce;
import facebooklogintest.cavepass.com.bakingapp.Adapters.RecipeRecyclerViewAdapter;
import facebooklogintest.cavepass.com.bakingapp.R;
import facebooklogintest.cavepass.com.bakingapp.Retrofit.ApiClient;
import facebooklogintest.cavepass.com.bakingapp.Retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<ApiResponce> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      final  RecyclerView recyclerView = findViewById(R.id.recyclerView_recipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
       // recyclerView.setAdapter(new RecipeRecyclerViewAdapter(this));


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<ApiResponce>> call = apiService.getResponce();

        call.enqueue(new Callback<List<ApiResponce>>() {
            @Override
            public void onResponse(Call<List<ApiResponce>> call, Response<List<ApiResponce>> response) {

              //  List<ApiResponce> list = new List<>() ;
            //    list.add(response.body());

                Log.e("Responce","Success");

                  list = (response.body());

                Log.e("Responce Size "," "+list.size()+" first Recipe "+list.get(0).getName());
                recyclerView.setAdapter(new RecipeRecyclerViewAdapter(MainActivity.this,list));


            }

            @Override
            public void onFailure(Call<List<ApiResponce>> call, Throwable t) {

                Log.e("Responce","Failed "+t.getMessage());

            }


        });



    }
}

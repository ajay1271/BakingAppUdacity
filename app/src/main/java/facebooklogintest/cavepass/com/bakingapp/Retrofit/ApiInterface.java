package facebooklogintest.cavepass.com.bakingapp.Retrofit;


import facebooklogintest.cavepass.com.bakingapp.API.RecipeApiReq;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ajay on 12-01-2018.
 */

public interface ApiInterface {

    @GET("baking.json")
    Call<RecipeApiReq> getResponce();

}

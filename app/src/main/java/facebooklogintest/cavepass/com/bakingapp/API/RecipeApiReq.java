package facebooklogintest.cavepass.com.bakingapp.API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ajay on 12-01-2018.
 */

public class RecipeApiReq {

    @SerializedName("results")
    private List<ApiResponce> results;

    public List<ApiResponce> getResults() {
        return results;
    }

    public void setResults(List<ApiResponce> results) {
        this.results = results;
    }
}

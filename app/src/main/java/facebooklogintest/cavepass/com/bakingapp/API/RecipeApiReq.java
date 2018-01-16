package facebooklogintest.cavepass.com.bakingapp.API;

import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.ModelClasses.ApiResponce;

/**
 * Created by Ajay on 12-01-2018.
 */

public class RecipeApiReq {

    private List<ApiResponce> results;

    public List<ApiResponce> getResults() {
        return results;
    }

    public void setResults(List<ApiResponce> results) {
        this.results = results;
    }
}

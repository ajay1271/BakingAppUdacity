package facebooklogintest.cavepass.com.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import facebooklogintest.cavepass.com.bakingapp.UI.RecipeStepDetail;

/**
 * Created by Ajay on 22-01-2018.
 */

public class RecipeStepDetailAdapter extends RecyclerView.Adapter<StepsAdapter.MyViewHolder> {

    Context context;

    RecipeStepDetailAdapter(Context context){

        this.context = context;


    }

    @Override
    public StepsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(StepsAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

}

package facebooklogintest.cavepass.com.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.ModelClasses.Step;
import facebooklogintest.cavepass.com.bakingapp.R;
import facebooklogintest.cavepass.com.bakingapp.UI.RecipeStepsFragmentClass;

/**
 * Created by Ajay on 10-01-2018.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.MyViewHolder> {

    Context context;

    List<Step> list;

    onItemClick mCallback;

    public interface onItemClick {

        void onClick(int position);

    }


    public StepsAdapter(Context context, List<Step> list) {

        this.context = context;

        this.list = list;

        try {

            mCallback = (onItemClick) context;


        } catch (Exception e) {

            Log.e(context.getString(R.string.error), e.getMessage());

        }


    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.recipe_part, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String s = context.getString(R.string.step) + position + context.getString(R.string.colon) + list.get(position).getShortDescription();

        holder.stepsDescription.setText(s);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {


        TextView stepsDescription;


        public MyViewHolder(View itemView) {
            super(itemView);


            stepsDescription = itemView.findViewById(R.id.step_short_description);

            stepsDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mCallback.onClick(getLayoutPosition());

                }
            });


        }
    }
}

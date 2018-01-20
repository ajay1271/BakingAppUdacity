package facebooklogintest.cavepass.com.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.ModelClasses.ApiResponce;
import facebooklogintest.cavepass.com.bakingapp.ModelClasses.Step;
import facebooklogintest.cavepass.com.bakingapp.R;

/**
 * Created by Ajay on 10-01-2018.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.MyViewHolder> {

    Context context;

    List<Step> list;

    public StepsAdapter(Context context, List<Step> list){

        this.context = context;

        this.list = list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

      View view = layoutInflater.inflate(R.layout.steps,parent,false);


        return new MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.stepsDescription.setText(list.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        TextView stepsDescription;


        public MyViewHolder(View itemView) {
            super(itemView);

            stepsDescription = itemView.findViewById(R.id.step);

        }
    }
}

package facebooklogintest.cavepass.com.bakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Ajay on 10-01-2018.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.MyViewHolder> {

    Context context;

    StepsAdapter(Context context){

        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.steps,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.stepsNumber.setText("Step "+(position+1)+": ");
        holder.stepsDescription.setText("Steps to Bake");

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        TextView stepsNumber,stepsDescription;


        public MyViewHolder(View itemView) {
            super(itemView);

            stepsDescription = itemView.findViewById(R.id.stepDescription);
            stepsNumber = itemView.findViewById(R.id.stepNumber);
        }
    }
}

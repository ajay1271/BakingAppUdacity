package facebooklogintest.cavepass.com.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.ModelClasses.ApiResponce;
import facebooklogintest.cavepass.com.bakingapp.R;

/**
 * Created by Ajay on 14-01-2018.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>{

    Context context;
    List<ApiResponce> list;
    int index;

    public IngredientsAdapter(Context context,List<ApiResponce> list,int index){

        this.context = context ;
        this.list = list;
        this.index=index;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view  = layoutInflater.inflate(R.layout.ingredients,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.ingredients.setText(list.get(index).getIngredients().get(position).getIngredient());
        holder.units.setText(list.get(index).getIngredients().get(position).getQuantity()+" "+list.get(index).getIngredients().get(position).getMeasure());
    }

    @Override
    public int getItemCount() {
        return list.get(index).getIngredients().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView ingredients,units;
        public MyViewHolder(View itemView) {
            super(itemView);
            ingredients = itemView.findViewById(R.id.ingredient);
            units = itemView.findViewById(R.id.step);
        }
    }



}

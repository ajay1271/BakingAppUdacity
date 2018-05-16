package facebooklogintest.cavepass.com.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.ModelClasses.ApiResponce;
import facebooklogintest.cavepass.com.bakingapp.ModelClasses.Ingredient;
import facebooklogintest.cavepass.com.bakingapp.R;

/**
 * Created by Ajay on 14-01-2018.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder> {

    Context context;
    List<Ingredient> list;

    public IngredientsAdapter(Context context, List<Ingredient> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ingredient_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.ingredients.setText(list.get(position).getIngredient());

        holder.units.setText(list.get(position).getQuantity() + " " + list.get(position).getMeasure());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ingredients, units;

        public MyViewHolder(View itemView) {
            super(itemView);
            ingredients = itemView.findViewById(R.id.ingredient_name);
            units = itemView.findViewById(R.id.ingredient_quantity);
        }
    }


}

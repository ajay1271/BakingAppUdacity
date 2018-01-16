package facebooklogintest.cavepass.com.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.ModelClasses.ApiResponce;
import facebooklogintest.cavepass.com.bakingapp.R;
import facebooklogintest.cavepass.com.bakingapp.UI.RecipeSteps;

/**
 * Created by Ajay on 09-01-2018.
 */

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<ApiResponce> list ;
    ArrayList<ApiResponce> apiResponces;

   public RecipeRecyclerViewAdapter(Context context, List<ApiResponce> list){

        this.context = context;
        this.list = list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       apiResponces = new ArrayList<>(list);

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.recipe_cards,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,  int position) {

       final int pos = position;

        Log.e("image","Entered ");
        Log.e("Image Address",""+apiResponces.size());

        holder.recipeName.setText(list.get(position).getName());

        holder.recipeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,RecipeSteps.class);

               i.putExtra("list",list.get(pos));
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView recipeImage;
        TextView recipeName;


        public MyViewHolder(View itemView) {
            super(itemView);

          recipeImage = itemView.findViewById(R.id.recipe_image);
          recipeName = itemView.findViewById(R.id.recipeName);

        }
    }
}

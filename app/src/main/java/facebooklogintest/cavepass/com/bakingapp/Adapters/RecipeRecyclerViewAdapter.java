package facebooklogintest.cavepass.com.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import facebooklogintest.cavepass.com.bakingapp.ModelClasses.ApiResponce;
import facebooklogintest.cavepass.com.bakingapp.R;
import facebooklogintest.cavepass.com.bakingapp.UI.MasterListClass;
import facebooklogintest.cavepass.com.bakingapp.UI.WidgetUpdateService;

/**
 * Created by Ajay on 09-01-2018.
 */

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<ApiResponce> list ;
    ArrayList<ApiResponce> apiResponces ;


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



        holder.recipeName.setText(list.get(position).getName());

        if(list.get(position).getImage()==""){
            holder.recipeImage.setImageResource(R.drawable.noimage);
        }

        else {


            Glide.with(context).load(list.get(position).getImage()).into(holder.recipeImage);


        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView recipeImage;
        TextView recipeName;
        int pos;



        public MyViewHolder(View itemView) {
            super(itemView);



          recipeImage = itemView.findViewById(R.id.recipe_image);
          recipeName = itemView.findViewById(R.id.recipeName);

         recipeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    WidgetUpdateService.updateWidget(context,list.get(getLayoutPosition()).getName());


                    Intent intent = new Intent(context,MasterListClass.class);





                    Intent i = new Intent(context,MasterListClass.class);

                    i.putExtra(context.getString(R.string.object),list.get(getLayoutPosition()));
                    context.startActivity(i);

                }
            });

        }
    }
}

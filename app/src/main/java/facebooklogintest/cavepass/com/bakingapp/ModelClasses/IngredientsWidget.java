package facebooklogintest.cavepass.com.bakingapp.ModelClasses;

/**
 * Created by Ajay on 15-05-2018.
 */

public class IngredientsWidget {

    String ingredient;
    String quantity;

    public IngredientsWidget(String ingredient,String quantity){

        this.quantity = quantity;
        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

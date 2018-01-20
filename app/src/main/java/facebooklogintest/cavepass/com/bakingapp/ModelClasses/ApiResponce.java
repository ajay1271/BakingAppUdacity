
package facebooklogintest.cavepass.com.bakingapp.ModelClasses;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class ApiResponce implements Parcelable {

    private int id;
    private String name;
    private String image;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Step> steps;


    public ApiResponce(int id, String name, ArrayList<Ingredient> ingredients, ArrayList<Step> steps) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
    }


    protected ApiResponce(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredients = in.createTypedArrayList(Ingredient.CREATOR);
        steps = in.createTypedArrayList(Step.CREATOR);
        this.image = in.readString();
    }

    public static final Creator<ApiResponce> CREATOR = new Creator<ApiResponce>() {
        @Override
        public ApiResponce createFromParcel(Parcel in) {
            return new ApiResponce(in);
        }

        @Override
        public ApiResponce[] newArray(int size) {
            return new ApiResponce[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedList(ingredients);
        dest.writeTypedList(steps);
        dest.writeString(image);

    }
}
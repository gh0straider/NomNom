package com.gh0straider.nomnom.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.gh0straider.nomnom.data.local.DatabaseContract;
import org.parceler.Parcel;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by gh0straider on 7/12/17.
 */

@Entity(tableName = DatabaseContract.INGREDIENT_TABLE_NAME,
    foreignKeys =
    @ForeignKey
        (entity = Recipe.class,
        parentColumns = "id",
        childColumns = "recipeId",
        onDelete = CASCADE),
    indices = @Index("recipeId"))

@Parcel(Parcel.Serialization.BEAN)
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int recipeId;

    @SerializedName("quantity")
    @Expose
    private Double quantity;
    @SerializedName("measure")
    @Expose
    private String measure;
    @SerializedName("ingredient")
    @Expose
    private String ingredient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }



}

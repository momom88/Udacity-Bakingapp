
package com.example.android.bakingapp.data;

import com.squareup.moshi.Json;

public class Ingredient {

    @Json(name = "quantity")
    private double quantity;
    @Json(name = "measure")
    private String measure;
    @Json(name = "ingredient")
    private String ingredient;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
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
package com.example.android.bakingapp.data.database;

import com.example.android.bakingapp.data.Recipe;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface RecipeDao {


    @Query("SELECT * FROM recipe")
    LiveData<List<Recipe>> loadAllRecipe();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRecipe(List<Recipe> recipes);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateRecipe(Recipe recipe);

    @Delete
    void deleteRecipe(Recipe recipe);
}


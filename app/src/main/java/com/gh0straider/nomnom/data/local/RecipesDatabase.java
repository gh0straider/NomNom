package com.gh0straider.nomnom.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.gh0straider.nomnom.data.Ingredient;
import com.gh0straider.nomnom.data.Recipe;
import com.gh0straider.nomnom.data.Step;

/**
 * Created by gh0straider 7/12/17.
 */

@Database(entities = { Recipe.class, Ingredient.class, Step.class} ,version = 4,exportSchema = false)
public abstract  class RecipesDatabase extends RoomDatabase{
  public abstract RecipesDao recipesDao();
}

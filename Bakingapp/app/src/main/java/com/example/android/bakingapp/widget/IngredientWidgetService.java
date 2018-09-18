package com.example.android.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

import com.example.android.bakingapp.data.Recipe;

public class IngredientWidgetService extends RemoteViewsService{
    public static void updateWidget(Context context, Recipe recipe) {
        WidgetPreferences.saveRecipe(context, recipe);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, IngredientWidgetProvider.class));
        IngredientWidgetProvider.updateIngredientWidget(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return null;
    }
}

package com.example.android.bakingapp.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class StepConverter {

    @TypeConverter
    public static String toString(List<Step> stepList) {
        if (stepList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Step>>() {
        }.getType();
        String json = gson.toJson(stepList, type);
        return json;
    }

    @TypeConverter
    public static List<Step> toList(String stepString) {
        if (stepString == null) {
            return (null);
        }
        Type type = new TypeToken<List<Step>>() {
        }.getType();
        Gson gson = new Gson();
        List<Step> stepList = gson.fromJson(stepString, type);
        return stepList;
    }
}

package com.d4coders.goodcitizen.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PointsManager {

    public static final String USER_CREDIT = "user_credit";
    private static SharedPreferences sharedPref;

    public static void calculatePoints(int newPoints, Context context) {
        sharedPref = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        int calculatedPoints = sharedPref.getInt(USER_CREDIT, 0);
        calculatedPoints = calculatedPoints + newPoints;

        editor.putInt(USER_CREDIT, calculatedPoints);
        editor.apply();
    }
}

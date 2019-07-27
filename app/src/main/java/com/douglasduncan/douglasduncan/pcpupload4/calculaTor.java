package com.douglasduncan.douglasduncan.pcpupload4;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class calculaTor {


    public String[] dd(){/////a testing method
        String ar[];
         ar = new String[]{"hello", "world"};

        return ar;

    }
//////////////////////////////////////////////////////////
public String [] cc(int var2, String var1, String InitialTimestamp, String InitialMileage, String AnnualMileage, String old_status, Context context ){///////calculate method var2 is miles, var1 is car

    File prefsdir = new File(context.getApplicationInfo().dataDir,"shared_prefs");

    if(prefsdir.exists() && prefsdir.isDirectory()) {
        String listofCars[] = prefsdir.list();
        for (int i = 0; i < listofCars.length; i++) {
            //listofCars.set(i, "D");
            int length = listofCars[i].length( ); // length == whatever
            // listofCars[i]="what";////take off the .xml part of the filename

            String substr= listofCars[i].substring(0,length-4);
            listofCars[i]=substr;////take off the .xml part of the filename
            Log.i("calculator list", "looping"+substr);
            if(substr.equals(var1)){
               Log.i("calculator list", "thats the one");
            }
        }
    //Log.i("list of cars", );
    }

        long tsLong = System.currentTimeMillis()/1000;////current timestamp as a long
        int tsInteger = (int) tsLong;////current timestamp as an integer

        int tsInitialInt = Integer.parseInt(InitialTimestamp);//initial timestamp as integer
        int miles_per_year_int = Integer.parseInt(AnnualMileage);//annual mileage as integer
        int initialmileage = Integer.parseInt(InitialMileage);
        int timeframe = tsInteger-tsInitialInt;
        double milesPerSecond = miles_per_year_int/(365.25*24*60*60);
        double AllowedMilesinTimeframe = milesPerSecond*timeframe;
        double Allowed = AllowedMilesinTimeframe+initialmileage;
        //double Status = var2-Allowed;
        double Status = Math.round((var2-Allowed)*1000)/1000D;/////number of zeroes is number of decimal places
        double OLDstatus_double =Math.round((Double.parseDouble(old_status))*1000)/1000D;
    //double Status = Math.round((var2-Allowed)*1000)/1000D;/////number of zeroes is number of decimal places

///////////////////////////////////////////////////////////////////////////////////////////update the shared preferences file
    SharedPreferences sharedPref = context.getSharedPreferences(var1, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();
    editor.putString("status", Double.toString(Status));
    editor.putString("last_mileage", Integer.toString(var2));
    editor.commit();
    Log.i("ddstatus", "saved status");



    //return "timestamp"+tsInteger+"\ninitial timestamp"+tsInitialInt+"\ntimeframe"+timeframe+"\nallowed miles in timeframe"+AllowedMilesinTimeframe+"\nallowed"+Allowed+"\nstatus"+Status+"\nlast status"+old_status;
    String ar[];
    ar = new String[]{Double.toString(Status), Double.toString(OLDstatus_double)};
    return ar;
    }
////////////////////////////////////////////////////////////////////////////////////////method to save status - unused!
    public static void saveStatus(Context context, String key, int value, String vehicle) {
        SharedPreferences sharedPref = context.getSharedPreferences(vehicle, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.commit();
        Log.i("ddstatus", "saved status");
    }
}

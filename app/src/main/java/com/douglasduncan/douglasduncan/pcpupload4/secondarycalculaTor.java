package com.douglasduncan.douglasduncan.pcpupload4;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class secondarycalculaTor {


    public String cc_Secondary(String[] listofcars, Context context ){///////need to pass the selected car to this method

Log.i("second calculator", "this is second calculator");

double total_status =0.00;
        long tsLong = System.currentTimeMillis()/1000;////current timestamp as a long
        int tsInteger = (int) tsLong;////current timestamp as an integer

        for (int i = 0; i < listofcars.length; i++) {
Log.i("loop", ""+i+" "+listofcars[i]);
            //////////////////////////////////////////////////////////////////////////variables as Strings
            SharedPreferences SelectedCar = context.getSharedPreferences(listofcars[i], Context.MODE_PRIVATE);//connect securely to "prefs" file
            String status = SelectedCar.getString("status", "");
            String firstTimestamp = SelectedCar.getString("first_timestamp","");
            String annualMileage = SelectedCar.getString("annual_mileage", "");
            String lastMileage = SelectedCar.getString("last_mileage", "");
            String initialMileage = SelectedCar.getString("first_mileage", "");
            //////////////////////////////////////////////////////////////////////////////variables as Doubles/integers
            double doubleStatus = Double.parseDouble(status);
            double annualMileageDouble = Double.parseDouble(annualMileage);
            double lastMileageDouble = Double.parseDouble(lastMileage);
            double firstMileageDouble = Double.parseDouble(initialMileage);
            int firstTimestampInt = Integer.parseInt(firstTimestamp);
            int ElapsedTime = tsInteger-firstTimestampInt;
            double DistancePerSecond = annualMileageDouble/(365.25*24*60*60);
            double AllowedDistance = firstMileageDouble+(ElapsedTime*DistancePerSecond);
            double StatusI = lastMileageDouble-AllowedDistance;

            Log.i("statuss", "status "+status+" status double"+doubleStatus+" timestamp "+firstTimestamp+" elapsed time "+ElapsedTime+" distance per second "+DistancePerSecond+" allowed distance "+AllowedDistance+" statusi "+StatusI);
            total_status = total_status+StatusI;
            Log.i("statuss", ""+total_status);
        }

        return "overall status "+total_status;


    }
}

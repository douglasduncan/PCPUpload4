package com.douglasduncan.douglasduncan.pcpupload4;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


public class addVehicleFragment2 extends Fragment {





    public addVehicleFragment2() {
        // Required empty public constructor
    }

    //private TextView mselectDate;
   // private DatePickerDialog.OnDateSetListener mdateSetListener;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View v = inflater.inflate(R.layout.fragment_add_vehicle2, container, false);

       //////////////////////////////////////////////////////////////////////////////////////////////////create spinners DAY
        final Spinner spinner_day = (Spinner)v.findViewById(R.id.spinnerDay);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_day = ArrayAdapter.createFromResource(getContext(),
                R.array.days_array, android.R.layout.simple_selectable_list_item);
// Specify the layout to use when the list of choices appears
        adapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_day.setAdapter(adapter_day);

        /////////////////////////////////////////////////////////////////////////////////////////create spinners MONTH
        final Spinner spinner_month = (Spinner)v.findViewById(R.id.spinnerMonth);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_month = ArrayAdapter.createFromResource(getContext(),
                R.array.months_array, android.R.layout.simple_selectable_list_item);
// Specify the layout to use when the list of choices appears
        adapter_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_month.setAdapter(adapter_month);

        /////////////////////////////////////////////////////////////////////////////////////////create spinners YEARS
        final Spinner spinner_year = (Spinner)v.findViewById(R.id.spinnerYear);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_year = ArrayAdapter.createFromResource(getContext(),
                R.array.years_array, android.R.layout.simple_selectable_list_item);
// Specify the layout to use when the list of choices appears
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_year.setAdapter(adapter_year);

        //////////////////////////////////////////////////////////////////////////////////////////////////////



Button submitter = (Button) v.findViewById(R.id.SubmitButton);
final TextView findVehicleRegistration = (TextView)v.findViewById(R.id.enterVehicleRegistration);
final TextView  findvehiclename = (TextView)v.findViewById(R.id.enterVehicleName);
final TextView initialMileages = (TextView)v.findViewById(R.id.startMileage);
final TextView annualMileage = (TextView)v.findViewById(R.id.AnnualMileage);
//String str;
//final TextView finddatebought = (TextView)v.findViewById(R.id.textSelectDate);
submitter.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "clicked submit", Toast.LENGTH_SHORT).show();
        String dayBought = spinner_day.getSelectedItem().toString();///////////////////////////get dates
        String monthBought = spinner_month.getSelectedItem().toString();
        String yearBought = spinner_year.getSelectedItem().toString();
        String DateBought = dayBought+"-"+monthBought+"-"+yearBought;

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");


        Date date = null;
        try {
            date = formatter.parse(DateBought);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long output=date.getTime()/1000L;
          String  DateBoughtUnix=Long.toString(output);

String VehicleRegistration = findVehicleRegistration.getText().toString();
        String VehicleName = findvehiclename.getText().toString();
        String InitialMiles = initialMileages.getText().toString();
        String AnnualMiles = annualMileage.getText().toString();
        //Integer   InitialMilesInteger = Integer.parseInt(InitialMiles);
        //String str_date="13-09-2011";
        //DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //try {
         //   Date date = formatter.parse(DateBought);
         //   Log.i("date test", date.toString());
       // } catch (ParseException e) {
        //    e.printStackTrace();
        //}


        Toast.makeText(getContext(), ""+DateBought+" "+DateBoughtUnix, Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor carAdder = getContext().getSharedPreferences(VehicleRegistration, MODE_PRIVATE).edit();
        carAdder.putString("vehicle_name", VehicleName);
        carAdder.putString("first_timestamp", DateBoughtUnix);
        carAdder.putString("first_mileage", InitialMiles);
        carAdder.putString("annual_mileage", AnnualMiles);
        carAdder.putString("last_mileage", "0");
        carAdder.putString("status", "0");
        carAdder.apply();

    }
});

       return v;
    }





}

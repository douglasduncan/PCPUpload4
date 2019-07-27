package com.douglasduncan.douglasduncan.pcpupload4;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class AddMilesFragment extends Fragment {




    public AddMilesFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_miles, container, false);
        ////////////////////////////////////////////////////////////////////////////////////////Spinner
        final Spinner spinnerCarSelector = (Spinner) v.findViewById(R.id.car_selector_spinner);

        final TextView CurrentMiles = (TextView) v.findViewById(R.id.currentMileage);
        Button addButton = (Button)v.findViewById(R.id.addMilesButton);
        ///////////////////////////////////////////////////////////////////////////////////////Textviews
        final TextView statusTextview = (TextView)v.findViewById(R.id.TextViewstatus);
        final TextView statusTextview2 = (TextView)v.findViewById(R.id.TextViewStatus2);
        final TextView overall_statusTextview = (TextView)v.findViewById(R.id.textViewOverallStatus);

        File prefsdir = new File(getActivity().getApplicationInfo().dataDir,"shared_prefs");

        if(prefsdir.exists() && prefsdir.isDirectory()){
            final String  listofCars[] = prefsdir.list();
            final String  listofCarsModified[] = new String[(listofCars.length)+1];/////modified to include "select vehicle" at [0]
            listofCarsModified[0] = "select vehicle";
            for (int i = 0; i < listofCars.length; i++) {
                //listofCars.set(i, "D");
                int length = listofCars[i].length( ); // length == whatever
               // listofCars[i]="what";////take off the .xml part of the filename

                String substr= listofCars[i].substring(0,length-4);
                 listofCars[i]=substr;////take off the .xml part of the filename
                 listofCarsModified[i+1]=substr;////take off the .xml part of the filename list of cars modified used for array adaptor
             Log.i("looping", "looping"+substr);
            }


            // Create an ArrayAdapter using the string array and a default spinner layout
           //ArrayAdapter<String> adapter = ArrayAdapter.createFromResource(this,
              //    R.string.listofCars, android.R.layout.simple_spinner_item);
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listofCarsModified);
            spinnerCarSelector.setAdapter(arrayAdapter);
/////////////////////////////////////////////////////////////////////////////////////////////////retrieve old mileage and fill mileage input
// /////////////////////////////////////////////////////////////////////////////////////////////////when car selector selects car
spinnerCarSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "selected", Toast.LENGTH_SHORT).show();
        // CurrentMiles.setText("555");
        String preSelected = spinnerCarSelector.getSelectedItem().toString();////////the selected vehicle
        Toast.makeText(getContext(), ""+preSelected, Toast.LENGTH_SHORT).show();
        if(!preSelected.equals("select vehicle")){///when vehicle is selected

           // CurrentMiles.setText("555");
            /////need to get last mileage from shared preferences
            Context context = getActivity();
            SharedPreferences SelectedCar = context.getSharedPreferences(preSelected, Context.MODE_PRIVATE);//connect securely to "prefs" file
            String last_mileage = SelectedCar.getString("last_mileage", "");////first mileage
            CurrentMiles.setText(last_mileage);

            }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

        }
        }
);

            //Toast.makeText(getContext(), "aaaaaaaa"+listofCars[0], Toast.LENGTH_SHORT).show();
            Integer itemCount =  listofCars.length;
            //Toast.makeText(getContext(), "aaaaaaaa"+listofCars[0]+"--"+itemCount, Toast.LENGTH_SHORT).show();

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {






                    String var2 = CurrentMiles.getText().toString();/////////the entered miles as STRING
                    int var2_int = Integer.parseInt(var2);//the entered miles as an INTEGER
                    String var1 = spinnerCarSelector.getSelectedItem().toString();////////the selected vehicle

                    /////need to get first_mileage and annual_mileage and first_timestamp from shared preferences
                    Context context = getActivity();
                    SharedPreferences SelectedCar = context.getSharedPreferences(var1, Context.MODE_PRIVATE);//connect securely to "prefs" file
                    String initial_mileage = SelectedCar.getString("first_mileage", "");////first mileage
                    String initial_timestamp = SelectedCar.getString("first_timestamp", "");////first timestamp
                    String annual_mileage = SelectedCar.getString("annual_mileage", "");////annual mileage
                    String status = SelectedCar.getString("status", "");

                    Toast.makeText(getContext(), "clicked"+var1+var2, Toast.LENGTH_SHORT).show();

                    calculaTor calculate = new calculaTor();/////call the calculaTor class
                    secondarycalculaTor overall_calculate = new secondarycalculaTor();/////call the secondary calculator class

                    String[] returned =  calculate.cc(var2_int, var1, initial_timestamp, initial_mileage, annual_mileage, status, getActivity());//the MILES + vehicle + initial timestamp + initial mileage
                    //String[] testingg = calculate.dd();
                    String overall_status = overall_calculate.cc_Secondary(listofCars, context);

                    //Log.i("testingg", ""+testingg[1]);
                    //Toast.makeText(getContext(), "this is from ccalculate"+returned, Toast.LENGTH_SHORT).show();
                    statusTextview.setText("Status = "+returned[0]+" miles");
                    statusTextview2.setText("Previous Status = "+returned[1]+" miles");
                    overall_statusTextview.setText(overall_status);
                    //(String.format("%.2f", value))
                    //////////////////////////////////////////////////////////////use method to save status
                    //
                    //calculaTor.saveStatus(getActivity(), "status", 15, var1);


                }
            });

        }
        else{
            Toast.makeText(getContext(), "NOOOOOOOOOOOOOOOOOO", Toast.LENGTH_SHORT).show();
        }




        return v;
    }




}

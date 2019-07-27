package com.douglasduncan.douglasduncan.pcpupload4;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class addVehicleFragment extends Fragment {





    public addVehicleFragment() {
        // Required empty public constructor
    }

    private TextView mselectDate;
    private DatePickerDialog.OnDateSetListener mdateSetListener;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View v = inflater.inflate(R.layout.fragment_add_vehicle, container, false);
        mselectDate =  v.findViewById(R.id.textSelectDate);///remember findviewbyID needs View 'v' in this case
        mselectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);





                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Dialog,
                        mdateSetListener,
                        year, month, day );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mdateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                Log.i("datepi", year+" "+month+" "+dayOfMonth);
                //String date = dayOfMonth+" "+month+" "+year;
                //yyyy-mm-dd hh:mm:ss
                String date = month+" "+dayOfMonth+" "+year;
                mselectDate.setText(date);
            }
        };
Button submitter = (Button) v.findViewById(R.id.SubmitButton);
final TextView  findvehiclename = (TextView)v.findViewById(R.id.enterVehicleName);
final TextView finddatebought = (TextView)v.findViewById(R.id.textSelectDate);
submitter.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //Toast.makeText(getContext(), "clicked submit", Toast.LENGTH_SHORT).show();
        String VehicleName = findvehiclename.getText().toString();
        String DateBought = finddatebought.getText().toString();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = (Date)formatter.parse(DateBought);
            Log.i("date", ""+date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Toast.makeText(getContext(), ""+VehicleName, Toast.LENGTH_SHORT).show();
    }
});

       return v;
    }





}

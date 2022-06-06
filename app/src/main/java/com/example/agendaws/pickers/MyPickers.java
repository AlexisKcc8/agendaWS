package com.example.agendaws.pickers;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MyPickers {
    private int dia, mes, ano, hora, minutos;
    final Calendar cal = Calendar.getInstance();

    public void showDatePickerDialog(Context context, final EditText editText){
        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONTH);
        ano = cal.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                String date = twoDigits(dayOfMonth)  + "/" + twoDigits(monthOfYear)  + "/" + year;
                editText.setText(date);
            }
        }, dia,mes,ano);
        datePickerDialog.show();
    }

    public void showTimePickerDialog(Context context, final EditText editText) {

        hora = cal.get(Calendar.HOUR_OF_DAY);
        minutos = cal.get(Calendar.MINUTE);
        TimePickerDialog dpd = new TimePickerDialog(context, new  TimePickerDialog.OnTimeSetListener()   {
            @Override
            public void onTimeSet(TimePicker timePicker, int horas, int min) {
                String time = horas+":"+min;
                editText.setText(time);
            }
        }, hora, minutos, true);
        dpd.show();
    }

    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }
}

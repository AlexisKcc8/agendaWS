package com.example.agendaws.helper_methods;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;

public class Helper_methods {
    String[] options = {"Actividad Física","Trabajo", "Compras", "Recreativo", "Otros"};

    public void fillSpinner(Context context, Spinner spinner){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, options);
        spinner.setAdapter(adapter);
    }

    public int indexOf(String element){
        return Arrays.asList(options).indexOf(element);
    }
}

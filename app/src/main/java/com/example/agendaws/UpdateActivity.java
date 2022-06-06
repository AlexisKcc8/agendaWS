package com.example.agendaws;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.agendaws.helper_methods.Helper_methods;
import com.example.agendaws.models.Model_user;
import com.example.agendaws.pickers.MyPickers;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner categoria;
    EditText txtName, txtLast, txtPhone, etFecha, etHora;
    ImageButton ibObtenerFecha, ibObtenerHora;
    Button btnUpdate, btnDelete;
    String id, name, last, phone, category, date, time;

    Model_user user  = new Model_user();;
    MyPickers myPickers = new MyPickers();
    Helper_methods myMethods = new Helper_methods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initXML();
        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }
    }

    private void initXML() {
        ibObtenerFecha = (ImageButton) findViewById(R.id.ib_obtener_fecha2);
        ibObtenerFecha.setOnClickListener(this);
        ibObtenerHora = (ImageButton) findViewById(R.id.ib_obtener_hora2);
        ibObtenerHora.setOnClickListener(this);

        txtName = (EditText) findViewById(R.id.inpName2);

        etFecha = (EditText) findViewById(R.id.inpDate2);
        etHora = (EditText) findViewById(R.id.inpHour2);

        categoria = (Spinner)findViewById(R.id.categoria2);
        myMethods.fillSpinner(UpdateActivity.this, categoria);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            last = getIntent().getStringExtra("last");
            phone = getIntent().getStringExtra("phone");
            category = getIntent().getStringExtra("category");
            date = getIntent().getStringExtra("date");
            time = getIntent().getStringExtra("time");

            //Setting Intent Data
            txtName.setText(name);
            txtLast.setText(last);
            txtPhone.setText(phone);
            categoria.setSelection(myMethods.indexOf(category));
            etFecha.setText(date);
            etHora.setText(time);

            Log.d("stev", name+" "+last+" "+phone+""+date+"" +time);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
    @Override
    public void onClick(View view) {
        if(view == ibObtenerFecha){
            myPickers.showDatePickerDialog(UpdateActivity.this, etFecha);
        }
        if(view == ibObtenerHora){
            myPickers.showTimePickerDialog(UpdateActivity.this, etHora);
        }
        if(view == btnUpdate){

        }
        if(view == btnDelete){
            confirmDialog();
        }
    }
}
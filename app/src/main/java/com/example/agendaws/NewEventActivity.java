package com.example.agendaws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agendaws.helper_methods.Helper_methods;
import com.example.agendaws.models.Model_Event;
import com.example.agendaws.models.Model_user;
import com.example.agendaws.pickers.MyPickers;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NewEventActivity extends AppCompatActivity  implements View.OnClickListener {
    Spinner categoria;
    EditText titleEvent, etFecha, etHora;
    TextView txtCategoria, idUser;
    ImageButton ibObtenerFecha, ibObtenerHora;
    Button btnAdd;
    String uIdUser;
    Helper_methods myMethods = new Helper_methods();
    MyPickers myPickers = new MyPickers();
    Model_Event event = new Model_Event();
    private FirebaseFirestore mFirestore;
    private ArrayList<String> lista = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        mFirestore = FirebaseFirestore.getInstance();
        uIdUser = getIntent().getStringExtra("idUser");
        initXML();
    }
    private void initXML() {
        ibObtenerFecha = (ImageButton) findViewById(R.id.ib_obtener_fecha);
        ibObtenerFecha.setOnClickListener(this);
        ibObtenerHora = (ImageButton) findViewById(R.id.ib_obtener_hora);
        ibObtenerHora.setOnClickListener(this);

        titleEvent = (EditText) findViewById(R.id.inpName);
        txtCategoria = (TextView) findViewById(R.id.inpCategoria);
        idUser = (TextView) findViewById(R.id.idUser);
        idUser.setText(uIdUser);
        etFecha = (EditText) findViewById(R.id.inpDate);
        etHora = (EditText) findViewById(R.id.inpHour);

        categoria = (Spinner)findViewById(R.id.categoria);
        myMethods.fillSpinner(NewEventActivity.this, categoria);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
    }

    public void clearInpust(){
        titleEvent.setText("");
        idUser.setText("");
        etHora.setText("");
        etFecha.setText("");
    }
    public String generateIdEvent(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    public void postEvent(Model_Event event){
        Map<String, Object> map = new HashMap<>();
        map.put("idEvent", event.getIdEvent());
        map.put("titleEvent", event.getTitleEvent());
        map.put("categoryEvent", event.getCategoryEvent());
        map.put("dateEvent", event.getDateEvent());
        map.put("timeEvent", event.getTimeEvent());
        map.put("idUser", event.getIdUser());
        mFirestore.collection("events").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(NewEventActivity.this, "Event Success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NewEventActivity.this, "Error al insertar", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View view) {
        if(view == ibObtenerFecha){
            myPickers.showDatePickerDialog(NewEventActivity.this, etFecha);
        }
        if(view == ibObtenerHora){
            myPickers.showTimePickerDialog(NewEventActivity.this, etHora);
        }

        if(view == btnAdd){
            event = new Model_Event();
            String titleE =titleEvent.getText().toString().trim();
            String dateE = etFecha.getText().toString().trim();
            String timeE = etHora.getText().toString().trim();

            if(titleE.isEmpty() && dateE.isEmpty() && timeE.isEmpty()){
                Toast.makeText(this, "Complete los datos", Toast.LENGTH_SHORT).show();
            }else{
                event.setIdEvent(generateIdEvent());
                event.setTitleEvent(titleE);
                event.setCategoryEvent(categoria.getSelectedItem().toString().trim());
                event.setDateEvent(dateE);
                event.setTimeEvent(timeE);
                event.setIdUser(uIdUser);
                postEvent(event);
                clearInpust();
            }
        }
    }
}
package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.tecnm.campusuruapan.pi.tes.helpers.FirebaseAuthHelper;
import com.tecnm.campusuruapan.pi.tes.helpers.FirebaseFirestoreHelper;

public class MainActivity extends AppCompatActivity {
    private String tipo;
    private TextView textView_Nombre;
    private TextView textView_Ubicacion;
    private TextView textView_Especialidad;
    private TextView textView_Correo;
    private MaterialButton materialButton_BuscarT_VerC;
    private MaterialButton materialButton_AbrirMensajeria;
    //private FirebaseFirestoreHelper firestoreHelper = new FirebaseFirestoreHelper();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Talachitas Express Services");

        textView_Nombre = findViewById(R.id.textView_Nombre);
        textView_Ubicacion = findViewById(R.id.textView_Ubicacion);
        textView_Especialidad = findViewById(R.id.textView_Especialidad);
        textView_Correo = findViewById(R.id.textView_Correo);
        materialButton_BuscarT_VerC = findViewById(R.id.materialButton_BuscarT_VerC);
        materialButton_AbrirMensajeria = findViewById(R.id.materialButton_AbrirMensajeria);

        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            tipo = parametros.getString("ROL");
            Log.e("ROL", tipo);
            if (tipo.equals("CLIENTE")) {
                textView_Especialidad.setVisibility(View.GONE);
                materialButton_BuscarT_VerC.setText("BUSCAR TALACHERO");
            }
        } else {
            Toast.makeText(MainActivity.this, "Hubo un error al cargar la actividad ", Toast.LENGTH_LONG).show();
        }
        setInformation();
        buttons();
    }

    private void setInformation() {
        textView_Nombre.setText(FirebaseFirestoreHelper.user.getNombre() + " "+FirebaseFirestoreHelper.user.getApellidos());
        textView_Especialidad.setText("Especialidad: "+FirebaseFirestoreHelper.user.getEspecialidad());
        textView_Ubicacion.setText("Ubicación: "+FirebaseFirestoreHelper.user.getUbicacion());
        textView_Correo.setText("Email: "+FirebaseFirestoreHelper.user.getEmail());
    }

    private void buttons() {
        materialButton_BuscarT_VerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Buscar Talachero
                if(tipo.equals("CLIENTE")){
                    Intent intent = new Intent(MainActivity.this, SpecialtyActivity.class);
                    startActivity(intent);
                }else{
                    //TALACHERO. Ver Contratos
                }
            }
        });

        materialButton_AbrirMensajeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
    }
}
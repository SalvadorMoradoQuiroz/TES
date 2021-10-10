package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewContratoPendiente;
import com.tecnm.campusuruapan.pi.tes.datosDePrueba.DatosPrueba;
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
    private FloatingActionButton floatingActionButton_contratos_propuestos;
    private FirebaseAuthHelper firebaseAuthHelper = new FirebaseAuthHelper();
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
        floatingActionButton_contratos_propuestos = findViewById(R.id.floatingActionButton_contratos_propuestos);



        tipo = FirebaseFirestoreHelper.user.getTipo_user();
        Log.e("ROL", tipo);
        if (tipo.equals("CLIENTE")) {
            textView_Especialidad.setVisibility(View.GONE);
            materialButton_BuscarT_VerC.setText("BUSCAR TALACHERO");
        }else{
            floatingActionButton_contratos_propuestos.setVisibility(View.GONE);
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
                    Intent intent = new Intent(MainActivity.this, ContractHistoryActivity.class);
                    startActivity(intent);
                }
            }
        });

        materialButton_AbrirMensajeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MensajeriaActivity.class);
                startActivity(intent);
            }
        });

        floatingActionButton_contratos_propuestos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogContratosPendientes();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        if(tipo.equalsIgnoreCase("talachero")){
            menu.removeItem(R.id.item_contratos_propuestos);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.item_cerrar_sesion:
                Toast.makeText(MainActivity.this, "Cerrar sesión" + "...", Toast.LENGTH_SHORT).show();
                ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "",
                        "Nos vemos pronto...", true);
                firebaseAuthHelper.signout(dialog, MainActivity.this);
                break;

            case R.id.item_contratos_propuestos:
                dialogContratosPendientes();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dialogContratosPendientes(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_contratos_propuestos, null);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

        final ListView listView_contratos_pendientes = dialog.findViewById(R.id.listView_contratos_pendientes);
        AdapterListViewContratoPendiente adapterListViewCP = new AdapterListViewContratoPendiente(MainActivity.this, R.layout.item_contrato_pendiente, DatosPrueba.getListContratosPendientes());
        listView_contratos_pendientes.setAdapter(adapterListViewCP);

    }
}
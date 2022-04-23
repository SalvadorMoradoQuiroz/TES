package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewTalachero;
import com.tecnm.campusuruapan.pi.tes.datosDePrueba.DatosPrueba;
import com.tecnm.campusuruapan.pi.tes.helpers.FirestoreHelperTalacheros;
import com.tecnm.campusuruapan.pi.tes.interfaces.TalacheroInterface;
import com.tecnm.campusuruapan.pi.tes.models.Specialty;
import com.tecnm.campusuruapan.pi.tes.models.Talachero;

import java.util.ArrayList;
import java.util.List;

public class TalacherosActivity extends AppCompatActivity{
    private ListView listView_Talacheros;
    private ArrayList<Talachero> listaLlenar;
    private final FirestoreHelperTalacheros firestoreHelperTalacheros = new FirestoreHelperTalacheros();
    private ArrayList<Talachero> talacheros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talacheros);
        setTitle("Talacheros disponibles");
        Bundle bundle = new Bundle();
        bundle = getIntent().getBundleExtra("listatalacheros");
        Bundle parametros = this.getIntent().getExtras();

        listView_Talacheros = findViewById(R.id.listView_Talacheros);


        if(parametros!= null){
            if(parametros.getString("ESPECIALIDAD").equalsIgnoreCase("mécanica")){
                //firestoreHelperTalacheros.getTalacheros2(this, "Mecánica");
                listaLlenar = (ArrayList<Talachero>) bundle.getSerializable("talacherosMecanica");
            }else if(parametros.getString("ESPECIALIDAD").equalsIgnoreCase("fontanería")){
                //firestoreHelperTalacheros.getTalacheros2(this,"Fontanería");
                listaLlenar = (ArrayList<Talachero>) bundle.getSerializable("talacherosFontaneria");
            }else if(parametros.getString("ESPECIALIDAD").equalsIgnoreCase("plomería")){
                //firestoreHelperTalacheros.getTalacheros2(this,"Plomería");
                listaLlenar = (ArrayList<Talachero>) bundle.getSerializable("talacherosPlomeria");
            }else if(parametros.getString("ESPECIALIDAD").equalsIgnoreCase("electricidad")){
                //firestoreHelperTalacheros.getTalacheros2(this,"Electricista");
                listaLlenar = (ArrayList<Talachero>) bundle.getSerializable("talacherosElectriciste");
            }
        }

        listView_Talacheros = findViewById(R.id.listView_Talacheros);
        AdapterListViewTalachero adapterListViewTalachero = new AdapterListViewTalachero(TalacherosActivity.this, R.layout.item_talachero, listaLlenar);
        listView_Talacheros.setAdapter(adapterListViewTalachero);
    }

   /* @Override
    public void getTalacheros(List<Talachero> talacheros) {
        this.talacheros.clear();
        this.talacheros.addAll(talacheros);
        fill(talacheros);
    }

    private void fill(List<Talachero> listTalachero) {
        listView_Talacheros.setAdapter(new AdapterListViewTalachero(TalacherosActivity.this, R.layout.item_talachero, listTalachero));
    }*/


}
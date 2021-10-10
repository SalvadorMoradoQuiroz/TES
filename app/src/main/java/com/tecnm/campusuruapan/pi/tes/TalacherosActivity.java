package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewTalachero;
import com.tecnm.campusuruapan.pi.tes.datosDePrueba.DatosPrueba;
import com.tecnm.campusuruapan.pi.tes.models.Specialty;
import com.tecnm.campusuruapan.pi.tes.models.Talachero;

import java.util.List;

public class TalacherosActivity extends AppCompatActivity {
    private ListView listView_Talacheros;
    private List<Talachero> listaLlenar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talacheros);
        setTitle("Talacheros disponibles");

        Bundle parametros = this.getIntent().getExtras();

        if(parametros!= null){
            if(parametros.getString("ESPECIALIDAD").equalsIgnoreCase("mécanica")){
                listaLlenar = DatosPrueba.getListTalacherosMecanica();
            }else if(parametros.getString("ESPECIALIDAD").equalsIgnoreCase("fontanería")){
                listaLlenar = DatosPrueba.getListTalacherosFontaneria();
            }else if(parametros.getString("ESPECIALIDAD").equalsIgnoreCase("plomería")){
                listaLlenar = DatosPrueba.getListTalacherosPlomeria();
            }else if(parametros.getString("ESPECIALIDAD").equalsIgnoreCase("electricidad")){
                listaLlenar = DatosPrueba.getListTalacherosElectricidad();
            }
        }

        listView_Talacheros = findViewById(R.id.listView_Talacheros);
        AdapterListViewTalachero adapterListViewTalachero = new AdapterListViewTalachero(TalacherosActivity.this, R.layout.item_talachero, listaLlenar);
        listView_Talacheros.setAdapter(adapterListViewTalachero);
    }
}
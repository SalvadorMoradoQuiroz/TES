package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewTalachero;
import com.tecnm.campusuruapan.pi.tes.datosDePrueba.DatosPrueba;

public class TalacherosActivity extends AppCompatActivity {
    private ListView listView_Talacheros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talacheros);

        listView_Talacheros = findViewById(R.id.listView_Talacheros);
        AdapterListViewTalachero adapterListViewTalachero = new AdapterListViewTalachero(TalacherosActivity.this, R.layout.item_talachero, DatosPrueba.getListTalacheros());
        listView_Talacheros.setAdapter(adapterListViewTalachero);
    }
}
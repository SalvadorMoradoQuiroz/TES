package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewMensajeria;
import com.tecnm.campusuruapan.pi.tes.datosDePrueba.DatosPrueba;
import com.tecnm.campusuruapan.pi.tes.models.Contacto;

public class MensajeriaActivity extends AppCompatActivity {

    private ListView listView_Mensajeria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajeria);
        setTitle("Mensajería");

        listView_Mensajeria = findViewById(R.id.listView_Mensajeria);

        AdapterListViewMensajeria adapterListViewMensajeria = new AdapterListViewMensajeria(MensajeriaActivity.this, R.layout.item_mensajeria, DatosPrueba.getListContactos());
        listView_Mensajeria.setAdapter(adapterListViewMensajeria);
    }
}
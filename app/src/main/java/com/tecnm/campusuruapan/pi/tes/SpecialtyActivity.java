package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewSpecialty;
import com.tecnm.campusuruapan.pi.tes.datosDePrueba.DatosPrueba;

public class SpecialtyActivity extends AppCompatActivity {

    private ListView listView_Especialidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty);
        setTitle("Especialidades de TES");

        listView_Especialidades = findViewById(R.id.listView_Especialidades);
        AdapterListViewSpecialty adapterListViewSpecialty = new AdapterListViewSpecialty(SpecialtyActivity.this, R.layout.item_especialidad, DatosPrueba.getListEspecialidades());
        listView_Especialidades.setAdapter(adapterListViewSpecialty);
    }
}
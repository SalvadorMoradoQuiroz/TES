package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewSpecialty;
import com.tecnm.campusuruapan.pi.tes.datosDePrueba.DatosPrueba;
import com.tecnm.campusuruapan.pi.tes.models.Specialty;

import java.util.List;

public class SpecialtyActivity extends AppCompatActivity {

    private ListView listView_Especialidades;
    private List<Specialty> listaEspecialidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty);
        setTitle("Especialidades de TES");

        listView_Especialidades = findViewById(R.id.listView_Especialidades);
        AdapterListViewSpecialty adapterListViewSpecialty = new AdapterListViewSpecialty(SpecialtyActivity.this, R.layout.item_especialidad, DatosPrueba.getListEspecialidades());
        listView_Especialidades.setAdapter(adapterListViewSpecialty);

        listaEspecialidades = DatosPrueba.getListEspecialidades();

        listView_Especialidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(SpecialtyActivity.this, TalacherosActivity.class);
                intent.putExtra("ESPECIALIDAD", listaEspecialidades.get(position).getNombre());
                startActivity(intent);
            }
        });
    }
}
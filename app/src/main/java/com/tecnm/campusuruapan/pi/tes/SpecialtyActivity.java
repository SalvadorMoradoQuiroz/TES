package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewSpecialty;
import com.tecnm.campusuruapan.pi.tes.datosDePrueba.DatosPrueba;
import com.tecnm.campusuruapan.pi.tes.helpers.FirestoreHelperTalacheros;
import com.tecnm.campusuruapan.pi.tes.interfaces.TalacheroInterface;
import com.tecnm.campusuruapan.pi.tes.models.Specialty;
import com.tecnm.campusuruapan.pi.tes.models.Talachero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyActivity extends AppCompatActivity implements TalacheroInterface {

    private ListView listView_Especialidades;
    private List<Specialty> listaEspecialidades = new ArrayList<>();
    private final FirestoreHelperTalacheros firestoreHelperTalacheros = new FirestoreHelperTalacheros();
    private ArrayList<Talachero> talacheros = new ArrayList<>();
    private ArrayList<Talachero> talacherosMecanica = new ArrayList<>();
    private ArrayList<Talachero> talacherosFontaneria = new ArrayList<>();
    private ArrayList<Talachero> talacherosPlomeria = new ArrayList<>();
    private ArrayList<Talachero> talacherosElectriciste = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty);
        setTitle("Especialidades de TES");

        firestoreHelperTalacheros.getTalacheros(this);

        listView_Especialidades = findViewById(R.id.listView_Especialidades);

        listView_Especialidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(SpecialtyActivity.this, TalacherosActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("talacherosMecanica", talacherosMecanica);
                bundle.putSerializable("talacherosFontaneria", talacherosFontaneria);
                bundle.putSerializable("talacherosPlomeria", talacherosPlomeria);
                bundle.putSerializable("talacherosElectriciste", talacherosElectriciste);

                intent.putExtra("listatalacheros",bundle);
                intent.putExtra("ESPECIALIDAD", listaEspecialidades.get(position).getNombre());

                startActivity(intent);
            }
        });
    }

    @Override
    public void getTalacheros(List<Talachero> talacheros) {
        this.talacheros.clear();
        this.talacheros.addAll(talacheros);
        especialidades(talacheros);
    }
    public void especialidades(List<Talachero> talacheros){
        for (int i = 0; i < talacheros.size(); i++){
            if (talacheros.get(i).getEspecialidad().contains("Mecánica"))
                talacherosMecanica.add(talacheros.get(i));

            if (talacheros.get(i).getEspecialidad().contains("Fontanería"))
                talacherosFontaneria.add(talacheros.get(i));

            if (talacheros.get(i).getEspecialidad().contains("Plomería"))
                talacherosPlomeria.add(talacheros.get(i));

            if (talacheros.get(i).getEspecialidad().contains("Electricista"))
                talacherosElectriciste.add(talacheros.get(i));
        }

        Specialty s = new Specialty("Mécanica", talacherosMecanica.size(), R.drawable.mecanica);
        Specialty s1 = new Specialty("Fontanería", talacherosFontaneria.size(), R.drawable.fontaneria);
        Specialty s2 = new Specialty("Plomería", talacherosPlomeria.size(), R.drawable.plomeria);
        Specialty s3 = new Specialty("Electricidad", talacherosElectriciste.size(), R.drawable.electricidad);

        listaEspecialidades.add(s);
        listaEspecialidades.add(s1);
        listaEspecialidades.add(s2);
        listaEspecialidades.add(s3);

        fill(listaEspecialidades);
    }
    public void fill(List<Specialty> listaEspecialidades){
        //AdapterListViewSpecialty adapterListViewSpecialty = new AdapterListViewSpecialty(SpecialtyActivity.this, R.layout.item_especialidad, listaEspecialidades);
        listView_Especialidades.setAdapter( new AdapterListViewSpecialty(SpecialtyActivity.this, R.layout.item_especialidad, listaEspecialidades));
    }
}
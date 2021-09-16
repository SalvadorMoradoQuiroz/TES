package com.tecnm.campusuruapan.pi.tes.datosDePrueba;

import com.tecnm.campusuruapan.pi.tes.R;
import com.tecnm.campusuruapan.pi.tes.models.Specialty;

import java.util.ArrayList;
import java.util.List;

public class DatosPrueba {

    public static List<Specialty> getListEspecialidades(){
        List<Specialty> specialties = new ArrayList<>();
        Specialty s = new Specialty("Mécanica", 10, R.drawable.user);
        Specialty s1 = new Specialty("Fontanería", 10, R.drawable.user);
        Specialty s2 = new Specialty("Plomería", 10, R.drawable.user);
        Specialty s3 = new Specialty("Electricidad", 10, R.drawable.user);

        specialties.add(s);
        specialties.add(s1);
        specialties.add(s2);
        specialties.add(s3);

        return specialties;
    }
}

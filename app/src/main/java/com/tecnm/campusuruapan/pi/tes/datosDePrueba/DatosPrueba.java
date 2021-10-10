package com.tecnm.campusuruapan.pi.tes.datosDePrueba;

import com.tecnm.campusuruapan.pi.tes.R;
import com.tecnm.campusuruapan.pi.tes.models.Contacto;
import com.tecnm.campusuruapan.pi.tes.models.Contrato;
import com.tecnm.campusuruapan.pi.tes.models.Specialty;
import com.tecnm.campusuruapan.pi.tes.models.Talachero;

import java.util.ArrayList;
import java.util.List;

public class DatosPrueba {

    public static List<Specialty> getListEspecialidades(){
        List<Specialty> specialties = new ArrayList<>();
        Specialty s = new Specialty("Mécanica", 10, R.drawable.mecanica);
        Specialty s1 = new Specialty("Fontanería", 10, R.drawable.fontaneria);
        Specialty s2 = new Specialty("Plomería", 10, R.drawable.plomeria);
        Specialty s3 = new Specialty("Electricidad", 10, R.drawable.electricidad);

        specialties.add(s);
        specialties.add(s1);
        specialties.add(s2);
        specialties.add(s3);

        return specialties;
    }

    public static List<Talachero> getListTalacheros(){
        List<Talachero> talacheroes = new ArrayList<>();
        Talachero t1 = new Talachero("Antonio Pulido Virrueta", "Francisco Villa #13, Colonia Centro, Uruapan Mich", 10, 1);
        Talachero t2 = new Talachero("Andres Mata Olivares", "Carranza #123, Colonia Centro, Uruapan Mich", 8, 1);
        Talachero t3 = new Talachero("Angel Araujo", "Francisco Villa #13, Colonia Centro, Uruapan Mich", 10, 1);
        Talachero t4 = new Talachero("Abel Vieyra Contreras", "Pedregal #25, Colonia Lomas de Uruapan, Uruapan Mich", 7, 1);
        Talachero t5 = new Talachero("Angel Contreras", "Francisco Villa #77, Colonia Centro, Uruapan Mich", 6, 1);
        Talachero t6 = new Talachero("Jose Francisco Torres", "Niños Herores #47, Colonia Sur, Uruapan Mich", 5, 1);
        Talachero t7 = new Talachero("Jesus Fuentes Leyba", "Francisco I Madero #52, Colonia Centro, Uruapan Mich", 7, 1);
        Talachero t8 = new Talachero("Brandon Gutierrez Vazquez", "Coronado #15, Colonia Centro, Uruapan Mich", 10, 1);
        Talachero t9 = new Talachero("Miriam Figueroa", "Villa #3, Colonia San Pedro, Uruapan Mich", 7, 1);
        Talachero t10 = new Talachero("Sara Dominguez ", "Juarez #47, Colonia Norte, Uruapan Mich", 8, 1);

        talacheroes.add(t1);
        talacheroes.add(t2);
        talacheroes.add(t3);
        talacheroes.add(t4);
        talacheroes.add(t5);
        talacheroes.add(t6);
        talacheroes.add(t7);
        talacheroes.add(t8);
        talacheroes.add(t9);
        talacheroes.add(t10);

        return talacheroes;
    }

    public static List<Talachero> getListTalacherosMecanica(){
        List<Talachero> talacheroes = new ArrayList<>();
        Talachero t1 = new Talachero("Antonio Pulido Virrueta", "Francisco Villa #13, Colonia Centro, Uruapan Mich", 10, 1);
        Talachero t2 = new Talachero("Andres Mata Olivares", "Carranza #123, Colonia Centro, Uruapan Mich", 8, 1);
        Talachero t3 = new Talachero("Angel Araujo", "Francisco Villa #13, Colonia Centro, Uruapan Mich", 10, 1);

        talacheroes.add(t1);
        talacheroes.add(t2);
        talacheroes.add(t3);

        return talacheroes;
    }


    public static List<Talachero> getListTalacherosFontaneria(){
        List<Talachero> talacheroes = new ArrayList<>();

        Talachero t5 = new Talachero("Angel Contreras", "Francisco Villa #77, Colonia Centro, Uruapan Mich", 6, 1);
        Talachero t6 = new Talachero("Jose Francisco Torres", "Niños Herores #47, Colonia Sur, Uruapan Mich", 5, 1);
        Talachero t7 = new Talachero("Jesus Fuentes Leyba", "Francisco I Madero #52, Colonia Centro, Uruapan Mich", 7, 1);
        Talachero t8 = new Talachero("Brandon Gutierrez Vazquez", "Coronado #15, Colonia Centro, Uruapan Mich", 10, 1);
        Talachero t9 = new Talachero("Miriam Figueroa", "Villa #3, Colonia San Pedro, Uruapan Mich", 7, 1);
        Talachero t10 = new Talachero("Sara Dominguez ", "Juarez #47, Colonia Norte, Uruapan Mich", 8, 1);


        talacheroes.add(t5);
        talacheroes.add(t6);
        talacheroes.add(t7);
        talacheroes.add(t8);
        talacheroes.add(t9);
        talacheroes.add(t10);

        return talacheroes;
    }



    public static List<Talachero> getListTalacherosPlomeria(){
        List<Talachero> talacheroes = new ArrayList<>();
        Talachero t1 = new Talachero("Antonio Pulido Virrueta", "Francisco Villa #13, Colonia Centro, Uruapan Mich", 10, 1);
        talacheroes.add(t1);

        return talacheroes;
    }

    public static List<Talachero> getListTalacherosElectricidad(){
        List<Talachero> talacheroes = new ArrayList<>();

        Talachero t9 = new Talachero("Miriam Figueroa", "Villa #3, Colonia San Pedro, Uruapan Mich", 7, 1);
        Talachero t10 = new Talachero("Sara Dominguez ", "Juarez #47, Colonia Norte, Uruapan Mich", 8, 1);




        talacheroes.add(t9);
        talacheroes.add(t10);

        return talacheroes;
    }

    public static List<Contrato> getListContratosPendientes(){
        List<Contrato> cps = new ArrayList<>();
        Contrato cp1 = new Contrato(1, "Arnulfo Díaz", "Tubería rota", "24/09/2021", "$500 Aprox.");
        Contrato cp2 = new Contrato(1, "Jesús Peréz", "Reparación lavamanos", "25/09/2021", "$800 Aprox.");
        Contrato cp3 = new Contrato(1, "Patricia Rendón", "Tubería rota", "24/09/2021", "$300 Aprox.");

        cps.add(cp1);
        cps.add(cp2);
        cps.add(cp3);

        return cps;
    }

    public static List<Contrato> getListContratosSinFinalizar(){
        List<Contrato> cps = new ArrayList<>();
        Contrato cp4 = new Contrato(1, "Juan López", "Fuga de agua", "25/09/2021", "$500");
        Contrato cp5 = new Contrato(1, "Diana Quiroz", "Instalación de tubería", "24/09/2021", "$400");

        cps.add(cp4);
        cps.add(cp5);

        return cps;
    }

    public static List<Contrato> getListContratosFinalizados(){
        List<Contrato> cps = new ArrayList<>();
        Contrato cp1 = new Contrato(1, "Alberto Dominguez", "Tubería rota", "22/09/2021", "$300");

        cps.add(cp1);

        return cps;
    }

    public static List<Contacto> getListContactos(){
        List<Contacto> cps = new ArrayList<>();

        Contacto c1 = new Contacto("Arnulfo Díaz", "");
        Contacto c2 = new Contacto("Jesús Peréz", "");
        Contacto c3 = new Contacto("Patricia Rendón", "");
        Contacto c4 = new Contacto("Juan López", "");
        Contacto c5 = new Contacto("Diana Quiroz", "");
        Contacto c6 = new Contacto("Alberto Dominguez", "");

        cps.add(c1);
        cps.add(c2);
        cps.add(c3);
        cps.add(c4);
        cps.add(c5);
        cps.add(c6);

        return cps;
    }
}

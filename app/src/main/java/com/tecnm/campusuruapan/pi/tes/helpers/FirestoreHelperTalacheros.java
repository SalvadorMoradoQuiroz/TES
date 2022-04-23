package com.tecnm.campusuruapan.pi.tes.helpers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.tecnm.campusuruapan.pi.tes.interfaces.*;
import com.tecnm.campusuruapan.pi.tes.models.Talachero;

import java.util.ArrayList;
import java.util.List;

public class FirestoreHelperTalacheros {

    public static FirebaseFirestore _DB = FirebaseFirestore.getInstance();
    public static CollectionReference _TALACHEROCOLLECTION = _DB.collection("usuarios");
/**
 * obtener talachero de la colección
 * */

    public void getTalacheros(TalacheroInterface talacheroInterface) {
       _TALACHEROCOLLECTION.whereEqualTo("tipo_user", "TALACHERO").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    List<Talachero> talacheros = new ArrayList<>();
                    for (QueryDocumentSnapshot document: task.getResult()) {
                            talacheros.add(new Talachero( document.getString("nombre"),document.getString("ubicacion"), 5, document.getString("uri_image"), document.getString("especialidad")));
                    }
                    talacheroInterface.getTalacheros(talacheros);
                }else {
                    Log.d("ERROR", "ERROR al obtener documentos", task.getException());
                }
            }
        });
    }

}

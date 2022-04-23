package com.tecnm.campusuruapan.pi.tes.helpers;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tecnm.campusuruapan.pi.tes.interfaces.Information;

public class FirebaseStorageHelper {
    private FirebaseFirestoreHelper firebaseFirestoreHelper = new FirebaseFirestoreHelper();
    private FirebaseStorage mStorage = FirebaseStorage.getInstance();
    private StorageReference usuariosFiles = mStorage.getReference().child("usuarios");

    private Information information;

    public void addImage(final String uid, Uri uri) {
        usuariosFiles.child(uid).putFile(uri).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                information.getMessage("Error de actualización de imagen, verifica tu conexión a Internet");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                information.getMessage("Imagen actualizada");
                usuariosFiles.child(uid).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        firebaseFirestoreHelper.updateImage(uri.toString(), information);
                    }
                });
            }
        });
    }

    public void deleteImage(String uid) {
        usuariosFiles.child(uid).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                information.getMessage("Imagen eliminada");
                firebaseFirestoreHelper.updateImage("", information);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //status.status("Error de actualización de imagen, verifica tu conexión a Internet");
            }
        });
    }

    public void setInformationListener(Information information) {
        this.information = information;
    }
}

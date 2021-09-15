package com.tecnm.campusuruapan.pi.tes.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tecnm.campusuruapan.pi.tes.LoginActivity;
import com.tecnm.campusuruapan.pi.tes.SignInActivity;
import com.tecnm.campusuruapan.pi.tes.interfaces.Information;
import com.tecnm.campusuruapan.pi.tes.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FirebaseFirestoreHelper {
    public static User user = null;
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final CollectionReference UsuariosCollection = FirebaseFirestoreHelper.db.collection("usuarios");

    //Se debe modificar
    public void addData(Information information, ProgressDialog dialog, Context context, String uid, String email, String password, String[] param, String tipo) {
        Map<String, Object> usuario = new HashMap<>();
        if (tipo.equals("TALACHERO")) {
            usuario.put("tipo_user", "TALACHERO");
            usuario.put("nombre", param[0]);
            usuario.put("apellidos", param[1]);
            usuario.put("telefono", param[2]);
            usuario.put("ubicacion", param[3]);
            usuario.put("especialidad", param[4]);
            usuario.put("email", email);
            usuario.put("password", password);
            usuario.put("uri_image", "");
            usuario.put("activo", true);
            Log.e("talachero", usuario.values().toArray().length + "");
            registerDataUserToFirestore(UsuariosCollection, uid, information, dialog, usuario, context);
        } else if (tipo.equals("CLIENTE")) {
            usuario.put("tipo_user", "CLIENTE");
            usuario.put("nombre", param[0]);
            usuario.put("apellidos", param[1]);
            usuario.put("telefono", param[2]);
            usuario.put("ubicacion", param[3]);
            usuario.put("email", email);
            usuario.put("password", password);
            usuario.put("uri_image", "");
            usuario.put("activo", true);
            Log.e("cliente", usuario.values().toArray().length + "");
            registerDataUserToFirestore(UsuariosCollection, uid, information, dialog, usuario, context);
        }
    }

    private void registerDataUserToFirestore(CollectionReference collectionReference, final String document, final Information information, final ProgressDialog dialog, final Map<String, Object> data, final Context context) {
        // Add a new document with a generated ID
        collectionReference.document(document).set(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        dialog.dismiss();
                        if (task.isSuccessful()) {
                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                            alertDialogBuilder.setCancelable(false);
                            alertDialogBuilder.setTitle("Aviso");
                            alertDialogBuilder.setMessage(" Usuario registrado con exito.");
                            alertDialogBuilder.setPositiveButton("Aceptar",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface alertDialog, int i) {
                                            Intent intent = new Intent(context, LoginActivity.class);
                                            context.startActivity(intent);
                                            ((Activity) context).finish();
                                            alertDialog.dismiss();
                                        }
                                    }
                            );

                            alertDialogBuilder.show();
                        } else {
                            information.getMessage("Error del registros de los datos. Inténtelo de nuevo");
                            Intent intent = new Intent(context, LoginActivity.class);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }

                    }
                });
    }

    public void getData(String document, final ProgressDialog dialog, final Information information, final Context context) {
        dialog.show();
        UsuariosCollection.document(document).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (Objects.requireNonNull(document).exists()) {
                        Map<String, Object> data = document.getData();

                        if(String.valueOf(data.get("tipo_user")).equals("CLIENTE")){
                            //Cliente
                            user = new User(document.getId(), String.valueOf(Objects.requireNonNull(data).get("tipo_user")), String.valueOf(data.get("nombre")), String.valueOf(data.get("apellidos")), String.valueOf(data.get("telefono")), String.valueOf(data.get("ubicacion")), String.valueOf(data.get("email")), String.valueOf(data.get("password")), (boolean) data.get("activo"));
                        }else{
                            //Talachero
                            user = new User(document.getId(), String.valueOf(Objects.requireNonNull(data).get("tipo_user")), String.valueOf(data.get("nombre")), String.valueOf(data.get("apellidos")), String.valueOf(data.get("telefono")), String.valueOf(data.get("ubicacion")), String.valueOf(data.get("email")), String.valueOf(data.get("password")), (boolean) data.get("activo"), String.valueOf(data.get("especialidad")));
                        }

                        if ((boolean) Objects.requireNonNull(document.get("activo"))) {
                            information.getMessage("Bienvenido " + user.getNombre() + " " + user.getApellidos());
                            //Se debe redigir...
                            /*Intent intent = new Intent(context, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            ((Activity) context).finish();*/
                        } else {
                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                            alertDialogBuilder.setCancelable(false);
                            alertDialogBuilder.setTitle("Aviso");
                            alertDialogBuilder.setMessage("Su cuenta ha sido bloqueada, comuniquese con los administradores...");
                            alertDialogBuilder.setPositiveButton("Aceptar",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface alertDialog, int i) {
                                            Intent intent = new Intent(context, LoginActivity.class);
                                            context.startActivity(intent);
                                            ((Activity) context).finish();
                                            alertDialog.cancel();
                                        }
                                    }
                            );
                        }
                    } else {
                        information.getMessage("No existe esa cuenta");
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        ((Activity) context).finish();
                    }
                } else {
                    information.getMessage("Error, verifique su conexión a Internet, si los problemas continuan contacte al administrado");
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
                dialog.dismiss();

            }
        });

    }

    /*public void updateDataAsesor(final String nombre, final String apellido, final String carrera, final ProgressDialog dialog, final Status status) {
        Map<String, Object> asesorMap = new HashMap<>();
        asesorMap.put("nombre", nombre);
        asesorMap.put("apellido", apellido);
        asesorMap.put("carrera", carrera);
        AsesoresCollection.document(asesor.getUid()).update(asesorMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        asesor.setNombre(nombre);
                        asesor.setApellidos(apellido);
                        asesor.setCarrera(carrera);
                        status.status("Datos actualizados");
                        dialog.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        status.status("Datos no actualizados, verifica tu conexión a Internet");
                        dialog.dismiss();
                    }
                });

    }

    public void updateImageAsesor(final String uri_image, final Status status) {
        Map<String, Object> asesorMap = new HashMap<>();
        asesorMap.put("uri_image", uri_image);

        AsesoresCollection.document(asesor.getUid()).update(asesorMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        asesor.setuRI_image(uri_image);
                        status.status("Datos actualizados");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        status.status("Imagen no actualizada, verifica tu conexión a Internet");
                    }
                });

    }

    public void getAllAsesores(final Status status, final ProgressDialog dialog, ListaAsesores listaAsesores) {

        AsesoresCollection.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isComplete()) {
                            String nombre;
                            Map<String, String> asesores = new HashMap<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                nombre = document.getData().get("nombre").toString() + " " + document.getData().get("apellido").toString();
                                asesores.put(document.getId(), nombre);
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            listaAsesores.getAsesoresAll(asesores);
                            status.status("Contactos obtenidos");
                        } else {
                            status.status("Error al obtener los asesores");
                        }
                        dialog.dismiss();
                    }
                });
    }*/

}

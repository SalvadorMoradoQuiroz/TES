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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tecnm.campusuruapan.pi.tes.LoginActivity;
import com.tecnm.campusuruapan.pi.tes.MainActivity;
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

                        if (String.valueOf(data.get("tipo_user")).equals("CLIENTE")) {
                            //Cliente
                            user = new User(document.getId(), String.valueOf(data.get("tipo_user")), String.valueOf(data.get("nombre")), String.valueOf(data.get("apellidos")), String.valueOf(data.get("telefono")), String.valueOf(data.get("ubicacion")), String.valueOf(data.get("email")), String.valueOf(data.get("password")), (boolean) data.get("activo"), String.valueOf(data.get("uri_image")));
                        } else {
                            //Talachero
                            user = new User(document.getId(), String.valueOf(data.get("tipo_user")), String.valueOf(data.get("nombre")), String.valueOf(data.get("apellidos")), String.valueOf(data.get("telefono")), String.valueOf(data.get("ubicacion")), String.valueOf(data.get("email")), String.valueOf(data.get("password")), (boolean) data.get("activo"), String.valueOf(data.get("especialidad")), String.valueOf(data.get("uri_image")));
                        }

                        if ((boolean) Objects.requireNonNull(document.get("activo"))) {
                            information.getMessage("Bienvenido " + user.getNombre() + " " + user.getApellidos());
                            //Se debe redigir...
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.putExtra("ROL", user.getTipo_user());
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        } else {
                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                            alertDialogBuilder.setCancelable(false);
                            alertDialogBuilder.setTitle("Aviso");
                            alertDialogBuilder.setMessage("Su cuenta ha sido bloqueada, comuniquese con los administradores...");
                            alertDialogBuilder.setPositiveButton("Aceptar",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface alertDialog, int i) {
                                            FirebaseAuthHelper.mAuth.signOut();
                                            user = null;
                                            Intent intent = new Intent(context, LoginActivity.class);
                                            context.startActivity(intent);
                                            ((Activity) context).finish();
                                            alertDialog.cancel();
                                        }
                                    }
                            );
                            alertDialogBuilder.show();
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

    public void updateDataUser(final ProgressDialog dialog, final Context context, final String nombre, final String apellido, final String telefono, final String ubicacion, final String especialidad, final String tipo, final Information information) {
        Map<String, Object> data = new HashMap<>();
        data.put("nombre", nombre.trim());
        data.put("apellidos", apellido.trim());
        data.put("telefono", telefono.trim());
        data.put("ubicacion", ubicacion.trim());

        if (tipo.equalsIgnoreCase("talachero")) {
            data.put("especialidad", especialidad.trim());
        }

        UsuariosCollection.document(user.getId()).update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        user.setNombre(nombre.trim());
                        user.setApellidos(apellido.trim());
                        user.setTelefono(telefono.trim());
                        user.setUbicacion(ubicacion.trim());
                        user.setEspecialidad(especialidad.trim());
                        information.getMessage("¡Datos actualizados!");
                        dialog.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        information.getMessage("Datos no actualizados, verifica tu conexión a Internet");
                        new AlertDialogPersonalized().alertDialogInformacion("Datos no actualizados, verifica tu conexión a Internet", context);
                        dialog.dismiss();
                    }
                });

    }

    public void updateImage(final String uri_image, final Information information) {
        Map<String, Object> usuarioMap = new HashMap<>();
        usuarioMap.put("uri_image", uri_image);

        UsuariosCollection.document(user.getId()).update(usuarioMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        user.setUriImage(uri_image);
                        information.getMessage("Datos actualizados");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        information.getMessage("Imagen no actualizada, verifica tu conexión a Internet");
                    }
                });

    }
}

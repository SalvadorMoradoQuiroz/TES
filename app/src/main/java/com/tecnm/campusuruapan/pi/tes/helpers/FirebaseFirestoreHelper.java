package com.tecnm.campusuruapan.pi.tes.helpers;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tecnm.campusuruapan.pi.tes.interfaces.Information;
import com.tecnm.campusuruapan.pi.tes.models.User;
import java.util.HashMap;
import java.util.Map;

public class FirebaseFirestoreHelper {
    public static User user = null;
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final CollectionReference UsuariosCollection = FirebaseFirestoreHelper.db.collection("asesores");

    //Se debe modificar
    public void addData(Information information, ProgressDialog dialog, Context context, String uid, String email, String password, String[] param) {
        if (param.length == 3) {
            //asesor
            Map<String, Object> asesor = new HashMap<>();
            asesor.put("nombre", param[0]);
            asesor.put("apellido", param[1]);
            asesor.put("carrera", param[2]);
            asesor.put("email", email);
            asesor.put("password", password);
            asesor.put("uri_image", "");
            asesor.put("activo", false);
            Log.e("lista", asesor.values().toArray().length + "");
            registerDataUserToFirestore(UsuariosCollection, uid, information, dialog, asesor, context);

        } else if (param.length == 2) {
            //es un profesor
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
                            alertDialogBuilder.setMessage("Registrado comuníquese con el administrador para habilitar su cuenta...");
                            alertDialogBuilder.setPositiveButton("Aceptar",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface alertDialog, int i) {

                                            /*Intent intent = new Intent(context, OptionsActivity.class);
                                            context.startActivity(intent);
                                            ((Activity) context).finish();*/
                                            alertDialog.dismiss();
                                        }
                                    }
                            );

                            alertDialogBuilder.show();


                        } else {
                            information.getMessage("Error del registros de los datos. Inténtelo de nuevo");
                            /*Intent intent = new Intent(context, OptionsActivity.class);
                            context.startActivity(intent);
                            ((Activity) context).finish();*/
                        }

                    }
                });
    }

    /*public void getData(String document, final ProgressDialog dialog, final Information information, final Context context) {
        dialog.show();
        UsuariosCollection.document(document).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (Objects.requireNonNull(document).exists()) {
                        Map<String, Object> data = document.getData();
                        //User = new User(document.getId(), String.valueOf(Objects.requireNonNull(data).get("nombre")), String.valueOf(data.get("apellido")), String.valueOf(data.get("email")), String.valueOf(data.get("password")), String.valueOf(data.get("carrera")), String.valueOf(data.get("uri_image")), (boolean) data.get("activo"));
                        if ((boolean) Objects.requireNonNull(document.get("activo"))) {
                            information.getMessage("Bienvenido " + user.getNombre() + " " + user.getApellidos());
                            Intent intent = new Intent(context, MainAdviserActivityApp.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        } else {
                            if (!(context instanceof OptionsActivity)) {
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                alertDialogBuilder.setCancelable(false);
                                alertDialogBuilder.setTitle("Aviso");
                                alertDialogBuilder.setMessage("Registrado comuníquese con el administrador para habilitar su cuenta...");
                                alertDialogBuilder.setPositiveButton("Aceptar",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface alertDialog, int i) {

                                                Intent intent = new Intent(context, OptionsActivity.class);
                                                context.startActivity(intent);
                                                ((Activity) context).finish();
                                                alertDialog.cancel();
                                            }
                                        }
                                );
                                //alertDialogBuilder.show();
                            } else {
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                alertDialogBuilder.setTitle("Aviso");
                                alertDialogBuilder.setMessage("Registrado comuníquese con el administrador para habilitar su cuenta...");
                                alertDialogBuilder.setPositiveButton("Aceptar",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface alertDialog, int i) {
                                                alertDialog.cancel();
                                            }
                                        }
                                );

                                alertDialogBuilder.show();
                            }
                        }
                    } else {
                        information.getMessage("No existe esa cuenta");
                        Intent intent = new Intent(context, OptionsActivity.class);
                        context.startActivity(intent);
                        ((Activity) context).finish();
                    }
                } else {

                    information.getMessage("Error, verifique su conexión a Internet, si los problemas continuan contacte al administrado");
                    Intent intent = new Intent(context, OptionsActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
                dialog.dismiss();

            }
        });

    }*/

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

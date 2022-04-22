package com.tecnm.campusuruapan.pi.tes;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewContratoPendiente;
import com.tecnm.campusuruapan.pi.tes.datosDePrueba.DatosPrueba;
import com.tecnm.campusuruapan.pi.tes.helpers.FirebaseAuthHelper;
import com.tecnm.campusuruapan.pi.tes.helpers.FirebaseFirestoreHelper;
import com.tecnm.campusuruapan.pi.tes.helpers.FirebaseStorageHelper;
import com.tecnm.campusuruapan.pi.tes.helpers.ImagesCompressHelper;
import com.tecnm.campusuruapan.pi.tes.interfaces.Information;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import id.zelory.compressor.Compressor;

public class MainActivity extends AppCompatActivity implements Information {
    private final static int GALLERY_INTENT = 1;
    private File imagen = null;
    private String tipo;
    private TextView textView_Nombre;
    private TextView textView_Ubicacion;
    private TextView textView_Especialidad;
    private TextView textView_Correo;
    private MaterialButton materialButton_BuscarT_VerC;
    private MaterialButton materialButton_AbrirMensajeria;
    private FloatingActionButton floatingActionButton_contratos_propuestos;
    private FirebaseAuthHelper firebaseAuthHelper = new FirebaseAuthHelper();
    private ImageButton imageButton_EditInfoPerfil;
    private ImageButton imageButton_EditFotoPerfil;
    private ImageView imageView_FotoPerfil;
    private String ERROR = "Campo requerido";
    private FirebaseFirestoreHelper firebaseFirestoreHelper = new FirebaseFirestoreHelper();
    private FirebaseStorageHelper firebaseStorageHelper = new FirebaseStorageHelper();
    //private FirebaseFirestoreHelper firestoreHelper = new FirebaseFirestoreHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Talachitas Express Services");

        textView_Nombre = findViewById(R.id.textView_Nombre);
        textView_Ubicacion = findViewById(R.id.textView_Ubicacion);
        textView_Especialidad = findViewById(R.id.textView_Especialidad);
        textView_Correo = findViewById(R.id.textView_Correo);
        materialButton_BuscarT_VerC = findViewById(R.id.materialButton_BuscarT_VerC);
        materialButton_AbrirMensajeria = findViewById(R.id.materialButton_AbrirMensajeria);
        floatingActionButton_contratos_propuestos = findViewById(R.id.floatingActionButton_contratos_propuestos);
        imageButton_EditInfoPerfil = findViewById(R.id.imageButton_EditInfoPerfil);
        imageButton_EditFotoPerfil = findViewById(R.id.imageButton_EditFotoPerfil);
        imageView_FotoPerfil = findViewById(R.id.imageView_FotoPerfil);


        tipo = FirebaseFirestoreHelper.user.getTipo_user();
        Log.e("ROL", tipo);
        if (tipo.equals("CLIENTE")) {
            textView_Especialidad.setVisibility(View.GONE);
            materialButton_BuscarT_VerC.setText("BUSCAR TALACHERO");
        } else {
            floatingActionButton_contratos_propuestos.setVisibility(View.GONE);
        }

        setInformation();
        buttons();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseStorageHelper.setInformationListener(this);
    }

    private void setInformation() {
        textView_Nombre.setText(FirebaseFirestoreHelper.user.getNombre() + " " + FirebaseFirestoreHelper.user.getApellidos());
        textView_Especialidad.setText("Especialidad: " + FirebaseFirestoreHelper.user.getEspecialidad());
        textView_Ubicacion.setText("Ubicación: " + FirebaseFirestoreHelper.user.getUbicacion());
        textView_Correo.setText("Email: " + FirebaseFirestoreHelper.user.getEmail());
        setImage(FirebaseFirestoreHelper.user.getUriImage());
    }

    private void buttons() {
        materialButton_BuscarT_VerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Buscar Talachero
                if (tipo.equals("CLIENTE")) {
                    Intent intent = new Intent(MainActivity.this, SpecialtyActivity.class);
                    startActivity(intent);
                } else {
                    //TALACHERO. Ver Contratos
                    Intent intent = new Intent(MainActivity.this, ContractHistoryActivity.class);
                    startActivity(intent);
                }
            }
        });

        materialButton_AbrirMensajeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MensajeriaActivity.class);
                startActivity(intent);
            }
        });

        floatingActionButton_contratos_propuestos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogContratosPendientes();
            }
        });

        imageButton_EditInfoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Actualizar información", Toast.LENGTH_SHORT).show();
                mostrarDialogEditarPerfil();
            }
        });

        imageButton_EditFotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("Opciones:");
                alertDialogBuilder.setMessage("Elige una opción a realizar, sino da click fuera del recuadro.");
                alertDialogBuilder.setPositiveButton("Modificar foto.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface alertDialog, int i) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), GALLERY_INTENT);
                                alertDialog.cancel();
                            }
                        });
                alertDialogBuilder.setNegativeButton("Eliminar foto actual.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface alertDialog, int i) {
                        if (!FirebaseFirestoreHelper.user.getUriImage().equals("")) {
                            firebaseStorageHelper.deleteImage(FirebaseFirestoreHelper.user.getId());
                            setImage("");
                        } else {
                            getMessage("No tienes ninguna foto agregada en el sistema.");
                        }
                        alertDialog.cancel();
                    }
                });
                alertDialogBuilder.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        if (tipo.equalsIgnoreCase("talachero")) {
            menu.removeItem(R.id.item_contratos_propuestos);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.item_cerrar_sesion:
                Toast.makeText(MainActivity.this, "Cerrar sesión" + "...", Toast.LENGTH_SHORT).show();
                ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "",
                        "Nos vemos pronto...", true);
                firebaseAuthHelper.signout(dialog, MainActivity.this);
                break;

            case R.id.item_contratos_propuestos:
                dialogContratosPendientes();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dialogContratosPendientes() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_contratos_propuestos, null);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

        final ListView listView_contratos_pendientes = dialog.findViewById(R.id.listView_contratos_pendientes);
        AdapterListViewContratoPendiente adapterListViewCP = new AdapterListViewContratoPendiente(MainActivity.this, R.layout.item_contrato_pendiente, DatosPrueba.getListContratosPendientes());
        listView_contratos_pendientes.setAdapter(adapterListViewCP);
    }


    private void mostrarDialogEditarPerfil() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_editar_informacion, null);
        builder.setView(view);

        final AlertDialog dialogEditarPerfil = builder.create();
        dialogEditarPerfil.setCancelable(false);
        dialogEditarPerfil.show();

        final TextInputLayout textInputLayout_Nombre_editar = dialogEditarPerfil.findViewById(R.id.textInputLayout_Nombre_editar);
        final TextInputLayout textInputLayout_apellidos_editar = dialogEditarPerfil.findViewById(R.id.textInputLayout_apellidos_editar);
        final TextInputLayout textInputLayout_Telefono_editar = dialogEditarPerfil.findViewById(R.id.textInputLayout_Telefono_editar);
        final TextInputLayout textInputLayout_Ubicacion_editar = dialogEditarPerfil.findViewById(R.id.textInputLayout_Ubicacion_editar);
        final TextInputLayout textInputLayout_especialidad_editar = dialogEditarPerfil.findViewById(R.id.textInputLayout_especialidad_editar);
        final LinearLayout linearLayout_Especialidad_editar = dialogEditarPerfil.findViewById(R.id.linearLayout_Especialidad_editar);
        final MaterialButton materialButton_salir_editar = dialogEditarPerfil.findViewById(R.id.materialButton_salir_editar);
        final MaterialButton materialButton_actualizar_editar = dialogEditarPerfil.findViewById(R.id.materialButton_actualizar_editar);

        if (FirebaseFirestoreHelper.user.getTipo_user().equals("CLIENTE")) {
            linearLayout_Especialidad_editar.setVisibility(View.GONE);
        } else {
            textInputLayout_especialidad_editar.getEditText().setText(FirebaseFirestoreHelper.user.getEspecialidad());
        }

        textInputLayout_Nombre_editar.getEditText().setText(FirebaseFirestoreHelper.user.getNombre());
        textInputLayout_apellidos_editar.getEditText().setText(FirebaseFirestoreHelper.user.getApellidos());
        textInputLayout_Telefono_editar.getEditText().setText(FirebaseFirestoreHelper.user.getTelefono());
        textInputLayout_Ubicacion_editar.getEditText().setText(FirebaseFirestoreHelper.user.getUbicacion());

        textInputLayout_especialidad_editar.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenuEspecialidades(textInputLayout_especialidad_editar, textInputLayout_especialidad_editar.getEditText().getText().toString());
            }
        });

        materialButton_salir_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEditarPerfil.dismiss();
            }
        });

        materialButton_actualizar_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag_nombre = false;
                boolean flag_apellido = false;
                boolean flag_telefono = false;
                boolean flag_ubicacion = false;
                boolean flag_especialidad = false;

                if (!textInputLayout_Nombre_editar.getEditText().getText().toString().isEmpty()) {
                    flag_nombre = true;
                } else {
                    textInputLayout_Nombre_editar.setError(ERROR);
                }

                if (!textInputLayout_apellidos_editar.getEditText().getText().toString().isEmpty()) {
                    flag_apellido = true;
                } else {
                    textInputLayout_apellidos_editar.setError(ERROR);
                }

                if (!textInputLayout_Telefono_editar.getEditText().getText().toString().isEmpty()) {
                    if (textInputLayout_Telefono_editar.getEditText().getText().toString().length() == 10) {
                        flag_telefono = true;
                    } else {
                        textInputLayout_Telefono_editar.setError("Número de teléfono no válido");
                    }
                } else {
                    textInputLayout_Telefono_editar.setError(ERROR);
                }

                if (!textInputLayout_Ubicacion_editar.getEditText().getText().toString().isEmpty()) {
                    flag_ubicacion = true;
                } else {
                    textInputLayout_Ubicacion_editar.setError(ERROR);
                }

                if (FirebaseFirestoreHelper.user.getTipo_user().equalsIgnoreCase("Cliente")) {
                    flag_especialidad = true;
                } else {
                    if (!textInputLayout_especialidad_editar.getEditText().getText().toString().isEmpty()) {
                        flag_especialidad = true;
                    } else {
                        textInputLayout_especialidad_editar.setError(ERROR);
                    }
                }

                if (flag_nombre && flag_apellido && flag_telefono && flag_ubicacion && flag_especialidad) {
                    ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "", "Actualizando..", true);
                    dialog.show();
                    firebaseFirestoreHelper.updateDataUser(dialog, MainActivity.this, textInputLayout_Nombre_editar.getEditText().getText().toString(),
                            textInputLayout_apellidos_editar.getEditText().getText().toString(), textInputLayout_Telefono_editar.getEditText().getText().toString(),
                            textInputLayout_Ubicacion_editar.getEditText().getText().toString(), textInputLayout_especialidad_editar.getEditText().getText().toString(),
                            FirebaseFirestoreHelper.user.getTipo_user(), MainActivity.this);
                    dialogEditarPerfil.dismiss();
                }
            }
        });
    }


    private void abrirMenuEspecialidades(final TextInputLayout textInputLayout_Especialidad, String especialidades) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_especialidades, null);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);


        //Nota: Se deben renombrar las variables.
        final CheckBox radioButton_albanileria_especialidad = dialog.findViewById(R.id.radioButton_albanileria_especialidad);
        final CheckBox radioButton_carpinteria_especialidad = dialog.findViewById(R.id.radioButton_carpinteria_especialidad);
        final CheckBox radioButton_cerrajeria_especialidad = dialog.findViewById(R.id.radioButton_cerrajeria_especialidad);
        final CheckBox radioButton_electricista_especialidad = dialog.findViewById(R.id.radioButton_electricista_especialidad);
        final CheckBox radioButton_fontaneria_especialidad = dialog.findViewById(R.id.radioButton_fontaneria_especialidad);
        final CheckBox radioButton_herreria_especialidad = dialog.findViewById(R.id.radioButton_herreria_especialidad);
        final CheckBox radioButton_mecanica_especialidad = dialog.findViewById(R.id.radioButton_mecanica_especialidad);
        final CheckBox radioButton_pintor_especialidad = dialog.findViewById(R.id.radioButton_pintor_especialidad);
        final CheckBox radioButton_plomeria_especialidad = dialog.findViewById(R.id.radioButton_plomeria_especialidad);
        final CheckBox radioButton_mudanza_especialidad = dialog.findViewById(R.id.radioButton_mudanza_especialidad);
        final CheckBox radioButton_otro_especialidad = dialog.findViewById(R.id.radioButton_otro_especialidad);
        final TextInputLayout textInputLayout_otro_especialidad = dialog.findViewById(R.id.textInputLayout_otro_especialidad);
        final MaterialButton materialButton_registrar_especilidades = dialog.findViewById(R.id.materialButton_registrar_especilidades);
        final MaterialButton materialButton_salir_especialidades = dialog.findViewById(R.id.materialButton_salir_especialidades);

        materialButton_registrar_especilidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textInputLayout_Especialidad.getEditText().setText("");
                boolean seleccion = false;
                String especialidadesSelecciondas = "";
                if (radioButton_albanileria_especialidad.isChecked()) {
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Albañilería, ";
                }
                if (radioButton_carpinteria_especialidad.isChecked()) {
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Carpintería, ";
                }
                if (radioButton_cerrajeria_especialidad.isChecked()) {
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Cerrajería, ";
                }
                if (radioButton_electricista_especialidad.isChecked()) {
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Electricista, ";
                }
                if (radioButton_fontaneria_especialidad.isChecked()) {
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Fontanería, ";
                }
                if (radioButton_herreria_especialidad.isChecked()) {
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Herrería, ";
                }
                if (radioButton_mecanica_especialidad.isChecked()) {
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Mecánica, ";
                }
                if (radioButton_pintor_especialidad.isChecked()) {
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Pintor, ";
                }
                if (radioButton_plomeria_especialidad.isChecked()) {
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Plomería, ";
                }
                if (radioButton_mudanza_especialidad.isChecked()) {
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Mudanza, ";
                }
                if (radioButton_otro_especialidad.isChecked()) {
                    seleccion = true;
                    String otro = textInputLayout_otro_especialidad.getEditText().getText().toString();
                    if (!otro.isEmpty()) {
                        especialidadesSelecciondas = especialidadesSelecciondas + otro;
                    } else {
                        seleccion = false;
                    }
                }

                if (seleccion) {
                    textInputLayout_Especialidad.getEditText().setText(especialidadesSelecciondas.substring(0, especialidadesSelecciondas.length() - 2));
                    dialog.dismiss();
                } else {
                    Snackbar.make(view, "Debes seleccionar al menos una especialidad. Si marcaste Otro, debes escribir ese oficio.", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        materialButton_salir_especialidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //Marcar especialidades
        if (especialidades.contains("Albañilería")) {
            radioButton_albanileria_especialidad.setChecked(true);
        }
        if (especialidades.contains("Carpintería")) {
            radioButton_carpinteria_especialidad.setChecked(true);
        }
        if (especialidades.contains("Cerrajería")) {
            radioButton_cerrajeria_especialidad.setChecked(true);
        }
        if (especialidades.contains("Electricista")) {
            radioButton_electricista_especialidad.setChecked(true);
        }
        if (especialidades.contains("Fontanería")) {
            radioButton_fontaneria_especialidad.setChecked(true);
        }
        if (especialidades.contains("Herrería")) {
            radioButton_herreria_especialidad.setChecked(true);
        }
        if (especialidades.contains("Mécanica automotriz")) {
            radioButton_mecanica_especialidad.setChecked(true);
        }
        if (especialidades.contains("Pintor")) {
            radioButton_pintor_especialidad.setChecked(true);
        }
        if (especialidades.contains("Plomería")) {
            radioButton_plomeria_especialidad.setChecked(true);
        }
        if (especialidades.contains("Servicio de mudanza")) {
            radioButton_mudanza_especialidad.setChecked(true);
        }

    }

    @Override
    public void getMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        if (message.equals("¡Datos actualizados!")) {
            setInformation();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT) {
            try {
                Uri uri = Objects.requireNonNull(data).getData();
                imagen = ImagesCompressHelper.from(getApplicationContext(), uri);
                imagen = new Compressor(getApplicationContext()).compressToFile(imagen);

                setImage(imagen);
                firebaseStorageHelper.deleteImage(FirebaseFirestoreHelper.user.getId());
                firebaseStorageHelper.addImage(FirebaseFirestoreHelper.user.getId(), Uri.fromFile(imagen));
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    private void setImage(String image_url) {
        RequestManager rm = Glide.with(getApplicationContext());
        if (image_url.equals("")) {
            Bitmap placeholder = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.user);
            RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), placeholder);
            circularBitmapDrawable.setCircular(true);
            rm.load(FirebaseFirestoreHelper.user.getUriImage())
                    .placeholder(circularBitmapDrawable)
                    .fitCenter()
                    .centerCrop()
                    .apply(RequestOptions.circleCropTransform())
                    //.apply(RequestOptions.bitmapTransform(new RoundedCorners(16)))
                    .into(imageView_FotoPerfil);
        } else {
            rm.load(FirebaseFirestoreHelper.user.getUriImage())
                    .fitCenter()
                    .centerCrop()
                    .apply(RequestOptions.circleCropTransform())
                    //.apply(RequestOptions.bitmapTransform(new RoundedCorners(16)))
                    .into(imageView_FotoPerfil);
        }

    }

    private void setImage(File file) {
        Glide.with(getApplicationContext())
                .load(file)
                .fitCenter()
                .centerCrop()
                .apply(RequestOptions.circleCropTransform())
                .into(imageView_FotoPerfil);
    }
}
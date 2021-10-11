package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.tecnm.campusuruapan.pi.tes.adapters.AdapterListViewContratoPendiente;
import com.tecnm.campusuruapan.pi.tes.datosDePrueba.DatosPrueba;
import com.tecnm.campusuruapan.pi.tes.helpers.Constantes;
import com.tecnm.campusuruapan.pi.tes.helpers.FirebaseAuthHelper;
import com.tecnm.campusuruapan.pi.tes.interfaces.Information;

import java.util.Locale;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity implements Information {
    private LinearLayout linearLayout_especialidad_sign;
    private TextInputLayout textInputLayout_Nombre;
    private TextInputLayout textInputLayout_Apellidos;
    private TextInputLayout textInputLayout_Telefono;
    private TextInputLayout textInputLayout_Ubicacion;
    private TextInputLayout textInputLayout_Email;
    private TextInputLayout textInputLayout_Pass;
    private TextInputLayout textInputLayout_ConfirmPass;
    private TextInputLayout textInputLayout_Especialidad;
    private MaterialButton materialButton_Resgistrarse;
    private FirebaseAuthHelper firebaseAuthHelper = new FirebaseAuthHelper();
    private String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        linearLayout_especialidad_sign = findViewById(R.id.linearLayout_especialidad_sign);
        textInputLayout_Nombre = findViewById(R.id.textInputLayout_Nombre);
        textInputLayout_Apellidos = findViewById(R.id.textInputLayout_Apellidos);
        textInputLayout_Telefono = findViewById(R.id.textInputLayout_Telefono);
        textInputLayout_Ubicacion = findViewById(R.id.textInputLayout_Ubicacion);
        textInputLayout_Email = findViewById(R.id.textInputLayout_Email);
        textInputLayout_Pass = findViewById(R.id.textInputLayout_Pass);
        textInputLayout_ConfirmPass = findViewById(R.id.textInputLayout_ConfirmPass);
        textInputLayout_Especialidad = findViewById(R.id.textInputLayout_Especialidad);
        materialButton_Resgistrarse = findViewById(R.id.materialButton_Registrarse);

        textInputLayout_Especialidad.setLongClickable(false);

        firebaseAuthHelper.setContext(SignInActivity.this);
        firebaseAuthHelper.setOnInformationListener(this);

        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            tipo = parametros.getString("ROL");
            if (tipo.equals("CLIENTE")) {
                linearLayout_especialidad_sign.setVisibility(View.GONE);
            }
        } else {
            Toast.makeText(SignInActivity.this, "Hubo un error al cargar la actividad", Toast.LENGTH_LONG).show();
        }

        materialButton_Resgistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarse();
            }
        });

        textInputLayout_Especialidad.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Especialidad", Toast.LENGTH_SHORT).show();
                abrirMenuEspecialidades();
            }
        });
    }

    private void abrirMenuEspecialidades() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_especialidades, null);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

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
                if(radioButton_albanileria_especialidad.isChecked()){
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Albañilería, ";
                }
                if(radioButton_carpinteria_especialidad.isChecked()){
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Carpintería, ";
                }
                if(radioButton_cerrajeria_especialidad.isChecked()){
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Cerrajería, ";
                }
                if(radioButton_electricista_especialidad.isChecked()){
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Electricista, ";
                }
                if(radioButton_fontaneria_especialidad.isChecked()){
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Fontanería, ";
                }
                if(radioButton_herreria_especialidad.isChecked()){
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Herrería, ";
                }
                if(radioButton_mecanica_especialidad.isChecked()){
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Mecánica, ";
                }
                if(radioButton_pintor_especialidad.isChecked()){
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Pintor, ";
                }
                if(radioButton_plomeria_especialidad.isChecked()){
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Plomería, ";
                }
                if(radioButton_mudanza_especialidad.isChecked()){
                    seleccion = true;
                    especialidadesSelecciondas = especialidadesSelecciondas + "Mudanza, ";
                }
                if(radioButton_otro_especialidad.isChecked()){
                    seleccion = true;
                    String otro = textInputLayout_otro_especialidad.getEditText().getText().toString();
                    if(!otro.isEmpty()){
                        especialidadesSelecciondas = especialidadesSelecciondas + otro;
                    }else{
                        seleccion = false;
                    }
                }

                if(seleccion){
                    textInputLayout_Especialidad.getEditText().setText(especialidadesSelecciondas.substring(0, especialidadesSelecciondas.length()-2));
                    dialog.dismiss();
                }else{
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
    }

    private void registrarse() {
        String nombre = textInputLayout_Nombre.getEditText().getText().toString();
        String apellidos = textInputLayout_Apellidos.getEditText().getText().toString();
        String telefono = textInputLayout_Telefono.getEditText().getText().toString();
        String ubicacion = textInputLayout_Ubicacion.getEditText().getText().toString();
        String especialidad = textInputLayout_Especialidad.getEditText().getText().toString();

        String email = textInputLayout_Email.getEditText().getText().toString().toLowerCase();
        String pass = textInputLayout_Pass.getEditText().getText().toString();
        String confirmPass = textInputLayout_ConfirmPass.getEditText().getText().toString();

        if(validarCampos(nombre, apellidos, telefono, ubicacion, especialidad, email, pass, confirmPass)){
            String[] args = new String[5];
            args[0] = nombre;
            args[1] = apellidos;
            args[2] = telefono;
            args[3] = ubicacion;
            args[4] = especialidad;

            ProgressDialog dialog = ProgressDialog.show(SignInActivity.this, "", "Resgistrando... ", true);
            dialog.show();
            firebaseAuthHelper.createUserEmailAndPassword(email, pass, dialog, args, tipo);
        }
    }

    private boolean validarCampos(String nombre, String apellidos, String telefono, String ubicacion, String especialidad, String email, String pass, String confirmPass) {
        textInputLayout_Nombre.setError(null);
        textInputLayout_Apellidos.setError(null);
        textInputLayout_Telefono.setError(null);
        textInputLayout_Ubicacion.setError(null);
        textInputLayout_Especialidad.setError(null);
        textInputLayout_Email.setError(null);
        textInputLayout_Pass.setError(null);
        textInputLayout_ConfirmPass.setError(null);
        boolean bNombre = false;
        boolean bApellidos = false;
        boolean bTelefono = false;
        boolean bUbicacion = false;
        boolean bEspecialidad = false;
        boolean bEmail = false;
        boolean bPass = false;

        if (!nombre.isEmpty()) {
            bNombre = true;
        } else {
            textInputLayout_Nombre.setError("Campo requerido");
        }

        if (!apellidos.isEmpty()) {
            bApellidos = true;
        } else {
            textInputLayout_Apellidos.setError("Campo requerido");
        }

        if (!telefono.isEmpty()) {
            if (telefono.length() == 10) {
                bTelefono = true;
            } else {
                textInputLayout_Telefono.setError("Número de teléfono no válido");
            }
        } else {
            textInputLayout_Telefono.setError("Campo requerido");
        }

        if (!ubicacion.isEmpty()) {
            bUbicacion = true;
        } else {
            textInputLayout_Ubicacion.setError("Campo requerido");
        }

        if (tipo.equals("TALACHERO")) {
            if (!especialidad.isEmpty()) {
                bEspecialidad = true;
            }else{
                textInputLayout_Especialidad.setError("Campo requerido");
            }
        } else {
            bEspecialidad = true;
        }

        if(!email.isEmpty()){
            if(Pattern.matches(Constantes.EXREGEMAIL, email)){
                bEmail = true;
            }else{
                textInputLayout_Email.setError("Correo no válido");
            }
        }else{
            textInputLayout_Email.setError("Campo requerido");
        }

        if (!pass.isEmpty()) {
            if (pass.length() > 5) {
                if (!confirmPass.isEmpty()) {
                    if (confirmPass.length() > 5) {
                        if (pass.equals(confirmPass)) {
                            bPass = true;
                        } else {
                            textInputLayout_ConfirmPass.setError("Las contraseñas no son iguales");
                            textInputLayout_Pass.setError("Las contraseñas no son iguales");
                        }
                    } else {
                        textInputLayout_ConfirmPass.setError("La contraseña debe tener mínimo 6 caracteres");
                    }
                } else {
                    textInputLayout_ConfirmPass.setError("Campo requerido");
                }
            } else {
                textInputLayout_Pass.setError("La contraseña debe tener mínimo 6 caracteres");
            }
        } else {
            textInputLayout_Pass.setError("Campo requerido");
        }

        if (bNombre && bApellidos && bTelefono && bUbicacion && bEspecialidad && bEmail && bPass) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void getMessage(String message) {
        Toast.makeText(SignInActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
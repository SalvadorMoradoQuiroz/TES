package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
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

        textInputLayout_Especialidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Especialidad", Toast.LENGTH_SHORT).show();
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
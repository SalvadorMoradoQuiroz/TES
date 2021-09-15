package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.tecnm.campusuruapan.pi.tes.helpers.FirebaseAuthHelper;
import com.tecnm.campusuruapan.pi.tes.interfaces.Information;

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
    }

    private void registrarse() {
        String nombre = textInputLayout_Nombre.getEditText().getText().toString();
        String apellidos = textInputLayout_Apellidos.getEditText().getText().toString();
        String telefono = textInputLayout_Telefono.getEditText().getText().toString();
        String ubicacion = textInputLayout_Ubicacion.getEditText().getText().toString();
        String especialidad = textInputLayout_Especialidad.getEditText().getText().toString();

        String email = textInputLayout_Email.getEditText().getText().toString();
        String pass = textInputLayout_Pass.getEditText().getText().toString();
        String confirmPass = textInputLayout_ConfirmPass.getEditText().getText().toString();

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

    @Override
    public void getMessage(String message) {
        Toast.makeText(SignInActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseUser;
import com.tecnm.campusuruapan.pi.tes.helpers.FirebaseAuthHelper;
import com.tecnm.campusuruapan.pi.tes.helpers.FirebaseFirestoreHelper;
import com.tecnm.campusuruapan.pi.tes.interfaces.Information;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements Information {
    private MaterialButton materialButton_Registrarse_Login;
    private MaterialButton materialButton_IniciarSesion;
    private MaterialButton materialButton_OlvidarPass;
    private TextInputLayout textInputLayout_Email;
    private TextInputLayout textInputLayout_Pass;
    private FirebaseAuthHelper firebaseAuthHelper = new FirebaseAuthHelper();
    private FirebaseFirestoreHelper firestoreHelper = new FirebaseFirestoreHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        firebaseAuthHelper.setContext(LoginActivity.this);
        firebaseAuthHelper.setOnInformationListener(this);

        materialButton_Registrarse_Login = findViewById(R.id.materialButton_Registrarse_Login);
        materialButton_IniciarSesion = findViewById(R.id.materialButton_IniciarSesion);
        materialButton_OlvidarPass = findViewById(R.id.materialButton_OlvidarPass);
        textInputLayout_Email = findViewById(R.id.textInputLayout_Email_Login);
        textInputLayout_Pass = findViewById(R.id.textInputLayout_Pass_Login);


        buttons();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuthHelper.getCurrentUser()!=null){
            ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "", "Ingresando... ", true);
            dialog.show();
            firestoreHelper.getData(FirebaseAuthHelper.getCurrentUser().getUid(), dialog, LoginActivity.this, LoginActivity.this);
        }
    }

    private void buttons() {
        materialButton_Registrarse_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Registrar usuario", Toast.LENGTH_SHORT).show();
                showDialogDecision();
            }
        });

        materialButton_IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = textInputLayout_Email.getEditText().getText().toString();
                String pass = textInputLayout_Pass.getEditText().getText().toString();

                ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "", "Ingresando... ", true);
                dialog.show();
                firebaseAuthHelper.signInWithEmailAndPassword(email, pass, dialog);
            }
        });

        materialButton_OlvidarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
                startActivity(intent);*/
            }
        });
    }


    private void showDialogDecision()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_decision,null);
        builder.setView(view);

        final AlertDialog dialogDEC = builder.create();
        //dialogDEC.setCancelable(false);
        dialogDEC.show();

        final MaterialButton materialButton_desc_cliente = dialogDEC.findViewById(R.id.materialButton_desc_cliente);
        final MaterialButton materialButton_desc_talachero = dialogDEC.findViewById(R.id.materialButton_desc_talachero);

        materialButton_desc_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
                intent.putExtra("ROL","CLIENTE");
                startActivity(intent);
                //finish();
            }
        });

        materialButton_desc_talachero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
                intent.putExtra("ROL","TALACHERO");
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void getMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
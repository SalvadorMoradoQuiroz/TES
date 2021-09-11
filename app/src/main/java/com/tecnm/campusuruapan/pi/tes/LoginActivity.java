package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private MaterialButton materialButton_registrarese_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        materialButton_registrarese_login = findViewById(R.id.materialButton_registrarese_login);

        buttons();
    }

    private void buttons() {
        materialButton_registrarese_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Registrar usuario", Toast.LENGTH_SHORT).show();
                showDialogDecision();
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
        dialogDEC.setCancelable(false);
        dialogDEC.show();

        final MaterialButton materialButton_desc_cliente = dialogDEC.findViewById(R.id.materialButton_desc_cliente);
        final MaterialButton materialButton_desc_talachero = dialogDEC.findViewById(R.id.materialButton_desc_talachero);

        materialButton_desc_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
                intent.putExtra("ROL","CLIENTE");
                startActivity(intent);
                finish();
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
}
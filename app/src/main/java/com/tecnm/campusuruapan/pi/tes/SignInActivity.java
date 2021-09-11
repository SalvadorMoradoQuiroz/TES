package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {
    private LinearLayout linearLayout_especialidad_sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Bundle parametros = this.getIntent().getExtras();

        linearLayout_especialidad_sign = findViewById(R.id.linearLayout_especialidad_sign);

        if (parametros != null) {
            String aux = parametros.getString("ROL");
            if(aux.equals("CLIENTE")){
                linearLayout_especialidad_sign.setVisibility(View.GONE);
            }

        } else{
            Toast.makeText(SignInActivity.this, "Hubo un error al cargar la actividad", Toast.LENGTH_LONG).show();
        }
    }
}
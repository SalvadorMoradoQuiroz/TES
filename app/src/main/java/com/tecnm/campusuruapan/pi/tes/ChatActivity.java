package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import java.util.Objects;

public class ChatActivity extends AppCompatActivity {

    private TextView textView_NombreDestinatario_Chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Objects.requireNonNull(getSupportActionBar()).hide();

        textView_NombreDestinatario_Chat = findViewById(R.id.textView_NombreDestinatario_Chat);

        Bundle parametros = this.getIntent().getExtras();
        textView_NombreDestinatario_Chat.setText(parametros.getString("NombreTalachero"));
    }

}
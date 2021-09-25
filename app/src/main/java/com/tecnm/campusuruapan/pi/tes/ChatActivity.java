package com.tecnm.campusuruapan.pi.tes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tecnm.campusuruapan.pi.tes.adapters.AdapterChats;
import com.tecnm.campusuruapan.pi.tes.models.Chats;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {

    private TextView textView_NombreDestinatario_Chat;
    private EditText editTextTextMultiLine_Mensaje;
    private ImageView imageView_ButtonEnviar;
    private AdapterChats adapterChats;
    private List<Chats> arrayChats;
    private RecyclerView recyclerView_Chats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Objects.requireNonNull(getSupportActionBar()).hide();

        textView_NombreDestinatario_Chat = findViewById(R.id.textView_NombreDestinatario_Chat);
        editTextTextMultiLine_Mensaje = findViewById(R.id.editTextTextMultiLine_Mensaje);
        imageView_ButtonEnviar = findViewById(R.id.imageView_ButtonEnviar);

        Bundle parametros = this.getIntent().getExtras();
        textView_NombreDestinatario_Chat.setText(parametros.getString("NombreTalachero"));

        recyclerView_Chats = findViewById(R.id.recyclerView_Chats);
        recyclerView_Chats.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView_Chats.setLayoutManager(linearLayoutManager);

        arrayChats = new ArrayList<>();
        adapterChats = new AdapterChats(arrayChats, ChatActivity.this);
        recyclerView_Chats.setAdapter(adapterChats);

        imageView_ButtonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

                String texto = editTextTextMultiLine_Mensaje.getText().toString();
                if (!texto.isEmpty()) {
                    Chats chats = new Chats();
                    chats.setMensaje(texto);
                    chats.setVisto("SI");
                    chats.setFecha(dateFormat.format(c.getTime()).toString());
                    chats.setHora(timeFormat.format(c.getTime()).toString());
                    arrayChats.add(chats);
                    adapterChats.notifyDataSetChanged();
                    editTextTextMultiLine_Mensaje.setText("");
                }
            }
        });
    }
}
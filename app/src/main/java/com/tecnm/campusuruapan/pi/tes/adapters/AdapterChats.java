package com.tecnm.campusuruapan.pi.tes.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tecnm.campusuruapan.pi.tes.R;
import com.tecnm.campusuruapan.pi.tes.models.Chats;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AdapterChats extends RecyclerView.Adapter<AdapterChats.viewHolderAdapter> {

    private List<Chats> chatsList;
    private Context context;
    public static final int MENSAJE_RIGHT = 1;
    public static final int MENSAJE_LEFT = 0;
    Boolean solo_right = false;
    FirebaseUser firebaseUser;

    public AdapterChats(List<Chats> chatsList, Context context) {
        this.chatsList = chatsList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MENSAJE_RIGHT){
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right,parent,false);
            return new AdapterChats.viewHolderAdapter(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left,parent,false);
            return new AdapterChats.viewHolderAdapter(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderAdapter holder, int position) {
        Chats chats = chatsList.get(position);

        holder.textView_Message.setText(chats.getMensaje());
        if(solo_right){
            if(chats.getVisto().equals("SI")){
                holder.imageView_Entregado.setVisibility(View.GONE);
                holder.imageView_Visto.setVisibility(View.VISIBLE);
            }else{
                holder.imageView_Entregado.setVisibility(View.VISIBLE);
                holder.imageView_Visto.setVisibility(View.GONE);
            }
        }//Fin solo_right
        final Calendar c = Calendar.getInstance();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if(chats.getFecha().equals(dateFormat.format(c.getTime()))){
            holder.textView_Fecha.setText("Hoy "+chats.getHora());
        }else{
            holder.textView_Fecha.setText(chats.getFecha()+" "+chats.getHora());
        }
    }

    @Override
    public int getItemCount() {
        return chatsList.size();
    }

    public class viewHolderAdapter extends RecyclerView.ViewHolder {

        TextView textView_Message, textView_Fecha;
        ImageView imageView_Entregado, imageView_Visto;

        public viewHolderAdapter(@NonNull View itemView) {
            super(itemView);
            textView_Message = itemView.findViewById(R.id.textView_Message);
            textView_Fecha = itemView.findViewById(R.id.textView_Fecha);
            imageView_Entregado = itemView.findViewById(R.id.imageView_Entregado);
            imageView_Visto = itemView.findViewById(R.id.imageView_Visto);
        }
    }

    @Override
    public int getItemViewType(int position) {
        /*firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(chatsList.get(position).getEnvia.equals(firebaseUser.getUid())){
            solo_right = true;
            return MENSAJE_RIGHT;
        }else{
            solo_right=false;
            return MENSAJE_LEFT;
        }*/
        return MENSAJE_RIGHT;
    }
}

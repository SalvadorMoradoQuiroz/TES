package com.tecnm.campusuruapan.pi.tes.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.tecnm.campusuruapan.pi.tes.ChatActivity;
import com.tecnm.campusuruapan.pi.tes.MensajeriaActivity;
import com.tecnm.campusuruapan.pi.tes.R;
import com.tecnm.campusuruapan.pi.tes.models.Contacto;
import com.tecnm.campusuruapan.pi.tes.models.Contrato;

import java.util.List;

public class AdapterListViewMensajeria extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private int idLayout;
    private List<Contacto> contactos;

    public AdapterListViewMensajeria(Context context, int idLayout, List<Contacto> contactos) {
        this.context = context;
        this.idLayout = idLayout;
        this.contactos = contactos;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Object getItem(int i) {
        return contactos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Contacto contacto = contactos.get(i);
        ContactoHolder contactoHolder = null;
        if (view == null) {
            view = layoutInflater.inflate(idLayout, null);
            contactoHolder = new ContactoHolder();
            contactoHolder.imageView_FotoPerfil_ItemMensajeria = view.findViewById(R.id.imageView_FotoPerfil_ItemMensajeria);
            contactoHolder.textView_Nombre_ItemMensajeria = view.findViewById(R.id.textView_Nombre_ItemMensajeria);
            contactoHolder.imageButton_Mensaje_ItemMensajeria = view.findViewById(R.id.imageButton_Mensaje_ItemMensajeria);
            contactoHolder.carViewContacto = view.findViewById(R.id.carViewContacto);

            view.setTag(contactoHolder);

            //contratotoHolder.imageView_FotoPerfil_ItemMensajeria.setImageResource();
            contactoHolder.textView_Nombre_ItemMensajeria.setText(contacto.getNombre());

            contactoHolder.imageButton_Mensaje_ItemMensajeria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra("NombreTalachero", contacto.getNombre());
                    context.startActivity(intent);
                }
            });

            contactoHolder.carViewContacto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra("NombreTalachero", contacto.getNombre());
                    context.startActivity(intent);
                }
            });

        } else {
            view.getTag();
        }
        return view;
    }

    static class ContactoHolder{
        private ImageView imageView_FotoPerfil_ItemMensajeria;
        private TextView textView_Nombre_ItemMensajeria;
        private ImageButton imageButton_Mensaje_ItemMensajeria;
        private CardView carViewContacto;
    }
}

package com.tecnm.campusuruapan.pi.tes.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tecnm.campusuruapan.pi.tes.ChatActivity;
import com.tecnm.campusuruapan.pi.tes.LoginActivity;
import com.tecnm.campusuruapan.pi.tes.R;
import com.tecnm.campusuruapan.pi.tes.SignInActivity;
import com.tecnm.campusuruapan.pi.tes.models.Talachero;

import java.util.List;

public class AdapterListViewTalachero extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private int idLayout;
    private List<Talachero> talacheroes;

    public AdapterListViewTalachero(Context context, int idLayout, List<Talachero> talacheroes) {
        this.context = context;
        this.idLayout = idLayout;
        this.talacheroes = talacheroes;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<Talachero> getTalacheros() {
        return talacheroes;
    }

    public void setTalacheros(List<Talachero> talacheroes) {
        this.talacheroes = talacheroes;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return talacheroes.size();
    }

    @Override
    public Object getItem(int i) {
        return talacheroes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final Talachero talachero = talacheroes.get(position);
        AdapterListViewTalachero.TalacheroHolder talacheroHolder = null;
        if (view == null) {
            view = layoutInflater.inflate(idLayout, null);
            talacheroHolder = new AdapterListViewTalachero.TalacheroHolder();
            talacheroHolder.textView_NombreT_Item = view.findViewById(R.id.textView_NombreT_Item);
            talacheroHolder.textView_DireccionT_Item = view.findViewById(R.id.textView_DireccionT_Item);
            talacheroHolder.textView_CalificacionT_Item = view.findViewById(R.id.textView_CalificacionT_Item);
            talacheroHolder.imageView_FotoPerfilT_Item = view.findViewById(R.id.imageView_FotoPerfilT_Item);
            talacheroHolder.imageButton_Mensaje_Item = view.findViewById(R.id.imageButton_Mensaje_Item);

            view.setTag(talacheroHolder);

            talacheroHolder.textView_NombreT_Item.setText(talacheroes.get(position).getNombre());
            talacheroHolder.textView_DireccionT_Item.setText("Dirección: "+talacheroes.get(position).getDireccion());
            talacheroHolder.textView_CalificacionT_Item.setText("Calificación: "+talacheroes.get(position).getCalificacion());
            //talacheroHolder.imageView_FotoPerfilT_Item.setImageResource();
            talacheroHolder.imageButton_Mensaje_Item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra("NombreTalachero",talacheroes.get(position).getNombre());
                    context.startActivity(intent);                }
            });
        } else {
            view.getTag();
        }
        return view;
    }

    static class TalacheroHolder {
        public TextView textView_NombreT_Item;
        public TextView textView_DireccionT_Item;
        public TextView textView_CalificacionT_Item;
        public ImageView imageView_FotoPerfilT_Item;
        public ImageView imageButton_Mensaje_Item;
    }
}

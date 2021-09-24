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

import com.google.android.material.button.MaterialButton;
import com.tecnm.campusuruapan.pi.tes.ChatActivity;
import com.tecnm.campusuruapan.pi.tes.R;
import com.tecnm.campusuruapan.pi.tes.models.Contrato;

import java.util.List;

public class AdapterListViewContratoSinFinalizar extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private int idLayout;
    private List<Contrato> contratoes;

    public AdapterListViewContratoSinFinalizar(Context context, int idLayout, List<Contrato> contratoes) {
        this.context = context;
        this.idLayout = idLayout;
        this.contratoes = contratoes;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<Contrato> getContratosFinalizados() {
        return contratoes;
    }

    public void setContratosFinalizados(List<Contrato> contratoes) {
        this.contratoes = contratoes;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return contratoes.size();
    }

    @Override
    public Object getItem(int i) {
        return contratoes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final Contrato contrato = contratoes.get(position);
        ContratosSinFinalizarHolder contratoHolder = null;
        if (view == null) {
            view = layoutInflater.inflate(idLayout, null);
            contratoHolder = new ContratosSinFinalizarHolder();
            contratoHolder.imageView_FotoPerfil_ItemCSF = view.findViewById(R.id.imageView_FotoPerfil_ItemCSF);
            contratoHolder.textView_Nombre_ItemCSF = view.findViewById(R.id.textView_Nombre_ItemCSF);
            contratoHolder.textView_DescrServicio_ItemCSF = view.findViewById(R.id.textView_DescrServicio_ItemCSF);
            contratoHolder.textView_Fecha_ItemCSF = view.findViewById(R.id.textView_Fecha_ItemCSF);
            contratoHolder.textView_Costo_ItemCSF = view.findViewById(R.id.textView_Costo_ItemCSF);
            contratoHolder.imageButton_Mensaje_ItemCSF = view.findViewById(R.id.imageButton_Mensaje_ItemCSF);

            view.setTag(contratoHolder);

            //contratotoHolder.imageView_FotoPerfil_ItemCSF.setImageResource();
            contratoHolder.textView_Nombre_ItemCSF.setText(contrato.getNombreCliente());
            contratoHolder.textView_DescrServicio_ItemCSF.setText(contrato.getDescrServicio());
            contratoHolder.textView_Fecha_ItemCSF.setText(contrato.getFecha());
            contratoHolder.textView_Costo_ItemCSF.setText(contrato.getCosto());

            contratoHolder.imageButton_Mensaje_ItemCSF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra("NombreTalachero", contratoes.get(position).getNombreCliente());
                    context.startActivity(intent);
                }
            });
        } else {
            view.getTag();
        }
        return view;
    }

    static class ContratosSinFinalizarHolder {
        public ImageView imageView_FotoPerfil_ItemCSF;
        public TextView textView_Nombre_ItemCSF;
        public TextView textView_DescrServicio_ItemCSF;
        public TextView textView_Fecha_ItemCSF;
        public TextView textView_Costo_ItemCSF;
        public ImageButton imageButton_Mensaje_ItemCSF;
    }
}

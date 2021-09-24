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

public class AdapterListViewContratoFinalizado extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private int idLayout;
    private List<Contrato> contratoes;

    public AdapterListViewContratoFinalizado(Context context, int idLayout, List<Contrato> contratoes) {
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
        ContratosPendientesHolder contratoHolder = null;
        if (view == null) {
            view = layoutInflater.inflate(idLayout, null);
            contratoHolder = new ContratosPendientesHolder();
            contratoHolder.imageView_FotoPerfil_ItemCF = view.findViewById(R.id.imageView_FotoPerfil_ItemCF);
            contratoHolder.textView_Nombre_ItemCF = view.findViewById(R.id.textView_Nombre_ItemCF);
            contratoHolder.textView_DescrServicio_ItemCF = view.findViewById(R.id.textView_DescrServicio_ItemCF);
            contratoHolder.textView_Fecha_ItemCF = view.findViewById(R.id.textView_Fecha_ItemCF);
            contratoHolder.textView_Costo_ItemCF = view.findViewById(R.id.textView_Costo_ItemCF);
            contratoHolder.imageButton_Calif_ItemCF = view.findViewById(R.id.imageButton_Calif_ItemCF);

            view.setTag(contratoHolder);

            //contratotoHolder.imageView_FotoPerfil_ItemCP.setImageResource();
            contratoHolder.textView_Nombre_ItemCF.setText(contrato.getNombreCliente());
            contratoHolder.textView_DescrServicio_ItemCF.setText(contrato.getDescrServicio());
            contratoHolder.textView_Fecha_ItemCF.setText(contrato.getFecha());
            contratoHolder.textView_Costo_ItemCF.setText(contrato.getCosto());

            contratoHolder.imageButton_Calif_ItemCF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        } else {
            view.getTag();
        }
        return view;
    }

    static class ContratosPendientesHolder {
        public ImageView imageView_FotoPerfil_ItemCF;
        public TextView textView_Nombre_ItemCF;
        public TextView textView_DescrServicio_ItemCF;
        public TextView textView_Fecha_ItemCF;
        public TextView textView_Costo_ItemCF;
        public ImageButton imageButton_Calif_ItemCF;
    }
}

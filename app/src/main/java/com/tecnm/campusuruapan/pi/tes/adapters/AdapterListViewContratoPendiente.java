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

public class AdapterListViewContratoPendiente extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private int idLayout;
    private List<Contrato> contratoes;

    public AdapterListViewContratoPendiente(Context context, int idLayout, List<Contrato> contratoes) {
        this.context = context;
        this.idLayout = idLayout;
        this.contratoes = contratoes;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<Contrato> getContratosPendientes() {
        return contratoes;
    }

    public void setContratosPendientes(List<Contrato> contratoes) {
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
            contratoHolder.imageView_FotoPerfil_ItemCP = view.findViewById(R.id.imageView_FotoPerfil_ItemCP);
            contratoHolder.textView_Nombre_ItemCP = view.findViewById(R.id.textView_Nombre_ItemCP);
            contratoHolder.textView_DescrServicioCP = view.findViewById(R.id.textView_DescrServicioCP);
            contratoHolder.textView_Fecha_ItemCP = view.findViewById(R.id.textView_Fecha_ItemCP);
            contratoHolder.textView_Costo_ItemCP = view.findViewById(R.id.textView_Costo_ItemCP);
            contratoHolder.imageButton_Mensaje_ItemCP = view.findViewById(R.id.imageButton_Mensaje_ItemCP);
            contratoHolder.materialButton_Aceptar_ItemCP = view.findViewById(R.id.materialButton_Aceptar_ItemCP);
            contratoHolder.materialButton_Rechazar_ItemCP = view.findViewById(R.id.materialButton_Rechazar_ItemCP);

            view.setTag(contratoHolder);

            //contratotoHolder.imageView_FotoPerfil_ItemCP.setImageResource();
            contratoHolder.textView_Nombre_ItemCP.setText(contrato.getNombreCliente());
            contratoHolder.textView_DescrServicioCP.setText(contrato.getDescrServicio());
            contratoHolder.textView_Fecha_ItemCP.setText(contrato.getFecha());
            contratoHolder.textView_Costo_ItemCP.setText(contrato.getCosto());

            contratoHolder.imageButton_Mensaje_ItemCP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra("NombreTalachero", contratoes.get(position).getNombreCliente());
                    context.startActivity(intent);
                }
            });
            contratoHolder.materialButton_Aceptar_ItemCP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
            contratoHolder.materialButton_Rechazar_ItemCP.setOnClickListener(new View.OnClickListener() {
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
        public ImageView imageView_FotoPerfil_ItemCP;
        public TextView textView_Nombre_ItemCP;
        public TextView textView_DescrServicioCP;
        public TextView textView_Fecha_ItemCP;
        public TextView textView_Costo_ItemCP;
        public ImageButton imageButton_Mensaje_ItemCP;
        public MaterialButton materialButton_Aceptar_ItemCP;
        public MaterialButton materialButton_Rechazar_ItemCP;
    }
}

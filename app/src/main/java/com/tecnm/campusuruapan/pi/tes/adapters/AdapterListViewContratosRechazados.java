package com.tecnm.campusuruapan.pi.tes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tecnm.campusuruapan.pi.tes.R;
import com.tecnm.campusuruapan.pi.tes.models.Contrato;

import java.util.List;

public class AdapterListViewContratosRechazados extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private int idLayout;
    private List<Contrato> contratoes;


    public AdapterListViewContratosRechazados(Context context, int idLayout, List<Contrato> contratoes) {
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
        ContratosRechazadosHolder contratosRechazadosHolder = null;

        if(view == null){
            view = layoutInflater.inflate(idLayout, null);

            contratosRechazadosHolder = new ContratosRechazadosHolder();
            contratosRechazadosHolder.imageView_FotoPerfil_ItemCR = view.findViewById(R.id.imageView_FotoPerfil_ItemCR);
            contratosRechazadosHolder.textView_Nombre_ItemCR = view.findViewById(R.id.textView_Nombre_ItemCR);
            contratosRechazadosHolder.textView_DescrServicio_ItemCR = view.findViewById(R.id.textView_DescrServicio_ItemCR);
            contratosRechazadosHolder.textView_Fecha_ItemCR = view.findViewById(R.id.textView_Fecha_ItemCR);
            contratosRechazadosHolder.textView_Costo_ItemCR = view.findViewById(R.id.textView_Costo_ItemCR);
            view.setTag(contratosRechazadosHolder);

            //contratotoRechazadosHolder.imageView_FotoPerfil_ItemCR.setImageResource();
            contratosRechazadosHolder.textView_Nombre_ItemCR.setText(contrato.getNombreCliente());
            contratosRechazadosHolder.textView_DescrServicio_ItemCR.setText(contrato.getDescrServicio());
            contratosRechazadosHolder.textView_Fecha_ItemCR.setText(contrato.getFecha());
            contratosRechazadosHolder.textView_Costo_ItemCR.setText(contrato.getCosto());

        }else {
            view.getTag();
        }

        return view;
    }


    static class ContratosRechazadosHolder{
        public ImageView imageView_FotoPerfil_ItemCR;
        public TextView textView_Nombre_ItemCR;
        public TextView textView_DescrServicio_ItemCR;
        public TextView textView_Fecha_ItemCR;
        public TextView textView_Costo_ItemCR;
    }
}

package com.tecnm.campusuruapan.pi.tes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tecnm.campusuruapan.pi.tes.R;
import com.tecnm.campusuruapan.pi.tes.models.Specialty;

import java.util.List;

public class AdapterListViewSpecialty extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private int idLayout;
    private List<Specialty> specialties;

    public AdapterListViewSpecialty(Context context, int idLayout, List<Specialty> specialties) {
        this.context = context;
        this.idLayout = idLayout;
        this.specialties = specialties;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return specialties.size();
    }

    @Override
    public Object getItem(int i) {
        return specialties.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final Specialty specialty = specialties.get(position);
        SpecialtyHolder specialtyHolder = null;
        if (view == null) {
            view = layoutInflater.inflate(idLayout, null);
            specialtyHolder = new SpecialtyHolder();
            specialtyHolder.imageView_Especialidad = view.findViewById(R.id.imageView_Especialidad);
            specialtyHolder.textView_NombreEspecialidad = view.findViewById(R.id.textView_NombreEspecialidad);
            specialtyHolder.textView_CantTalacherosEsp = view.findViewById(R.id.textView_CantTalacherosEsp);
            view.setTag(specialtyHolder);

            specialtyHolder.imageView_Especialidad.setImageResource(specialty.getImage());
            specialtyHolder.textView_NombreEspecialidad.setText(specialty.getNombre());
            specialtyHolder.textView_CantTalacherosEsp.setText(specialty.getCantTalacheros() + " Talacheros disponibles");
        } else {
            view.getTag();
        }
        return view;
    }

    static class SpecialtyHolder {
        public TextView textView_NombreEspecialidad;
        public TextView textView_CantTalacherosEsp;
        public ImageView imageView_Especialidad;
    }
}

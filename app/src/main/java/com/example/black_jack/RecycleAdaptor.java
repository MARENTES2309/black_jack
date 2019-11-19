package com.example.black_jack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdaptor extends RecyclerView.Adapter<RecycleAdaptor.ViewHolder> {



    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre;
        TextView puntos;
        ImageView usuario;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            puntos =itemView.findViewById(R.id.numero);
            usuario =itemView.findViewById(R.id.usuario);
        }
    }

        private   ArrayList<Persona> listapersona;

        public RecycleAdaptor(ArrayList<Persona> listapersona){
            this.listapersona= listapersona;
        }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datos_black_jack,null,false);
            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.nombre.setText(listapersona.get(position).getNombre());
            holder.puntos.setText(String.valueOf(listapersona.get(position).getPuntos()));
            holder.usuario.setImageResource(R.drawable.usuario);
    }

    @Override
    public int getItemCount() {
        return listapersona.size();
    }
}

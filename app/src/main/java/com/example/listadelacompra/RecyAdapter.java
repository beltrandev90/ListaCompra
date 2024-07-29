package com.example.listadelacompra;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder>  {

    private ArrayList<Articulo> miLista = new ArrayList<Articulo>();

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private final TextView textView;


        public ViewHolder(@NonNull View view) {
            super(view);
            textView = view.findViewById(R.id.articulos);
            view.setOnCreateContextMenuListener(this);
        }

        public TextView getTextView() {
            return textView;
        }

        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Selecciona");

            menu.add(this.getAdapterPosition(), R.id.editArticulo, 0, "Editar");
            menu.add(this.getAdapterPosition(), R.id.borrarArticulo, 0, "Borrar");
        }
    }
    public RecyAdapter(ArrayList<Articulo> datosArticulo) {

        miLista = datosArticulo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText(miLista.get(position).getNombre());

        if (miLista.get(holder.getAdapterPosition()).isComprado())	{
            holder.textView.setPaintFlags(holder.textView.getPaintFlags()	|
                    Paint.STRIKE_THRU_TEXT_FLAG);
            holder.textView.setTextColor(Color.parseColor("#8191AC"));
        }	else {
            holder.textView.setPaintFlags(holder.textView.getPaintFlags()
                    & ~Paint.STRIKE_THRU_TEXT_FLAG);
            holder.textView.setTextColor(Color.parseColor("#FFFFFF"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (miLista.get(holder.getAdapterPosition()).isComprado()) {
                    miLista.get(holder.getAdapterPosition()).setComprado(false);
                    Toast.makeText(view.getContext(), "Te falta por comprar " + miLista.get(holder.getAdapterPosition()).getNombre(), Toast.LENGTH_SHORT).show();
                } else {
                    miLista.get(holder.getAdapterPosition()).setComprado(true);
                    Toast.makeText(view.getContext(), "Ya has comprado " + miLista.get(holder.getAdapterPosition()).getNombre(), Toast.LENGTH_SHORT).show();
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return miLista.size();
    }

    public void borrarArticulo(int position) {
        miLista.remove(position);
        notifyDataSetChanged();
    }
}

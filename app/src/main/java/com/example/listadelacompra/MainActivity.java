package com.example.listadelacompra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.ActionBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    ArrayList <Articulo> datosArticulo = new ArrayList <Articulo>();
    RecyAdapter adaptador;
    EditText nuevoArticulo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle);

        //Crear icono
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.icono);

        datosArticulo.add(new Articulo("Leche",false));
        datosArticulo.add(new Articulo("Tomates",false));
        datosArticulo.add(new Articulo("Huevos",false));
        rv = findViewById(R.id.rv);
        nuevoArticulo = findViewById(R.id.nuevoArticulo);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);

        registerForContextMenu(rv);

        adaptador = new RecyAdapter(datosArticulo);
        rv.setAdapter(adaptador);
    }

    void añadirArticulo() {
        final Dialog dialog = new Dialog(MainActivity.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_articulo);

        final EditText articuloNew = dialog.findViewById(R.id.nuevoArticulo);
        Button btnAdd = dialog.findViewById(R.id.buttonAñadir);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String articulo = articuloNew.getText().toString();
                datosArticulo.add(new Articulo(articulo,false));
                adaptador.notifyDataSetChanged();//recargar pagina

                dialog.dismiss();
            }
        });
        dialog.show();
    }

    void editarProductos(int position) {
        final Dialog dialog = new Dialog(MainActivity.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.edit_articulo);

        final EditText editArticulo = dialog.findViewById(R.id.editArticulo);
        Button btnAceptar = dialog.findViewById(R.id.buttonAceptar);
        Button btnCancelar = dialog.findViewById(R.id.buttonCancelar);
        editArticulo.setText(datosArticulo.get(position).getNombre());

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String articuloEditar = editArticulo.getText().toString();
                datosArticulo.set(position, new Articulo(articuloEditar,false));
                adaptador.notifyDataSetChanged();//recargar pagina
                dialog.dismiss();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }



    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.editArticulo:
                editarProductos(item.getGroupId());
                return true;

            case R.id.borrarArticulo:
                adaptador.borrarArticulo(item.getGroupId());
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.addArticulo) {
            añadirArticulo();
        }
        return super.onOptionsItemSelected(item);
    }
}
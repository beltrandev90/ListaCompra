package com.example.listadelacompra;

public class Articulo {

    private String	articuloNew;
    private boolean comprado;

    public Articulo(String articuloNew, boolean comprado){
        this.articuloNew = articuloNew;
        this.comprado =	comprado;
    }
    public String getNombre()	{
        return articuloNew;
    }
    public void setNombre(String articuloNew)	{
        this.articuloNew = articuloNew;
    }
    public boolean isComprado()	{
        return comprado;
    }
    public void setComprado(boolean comprado)	{
        this.comprado =	comprado;
    }

}

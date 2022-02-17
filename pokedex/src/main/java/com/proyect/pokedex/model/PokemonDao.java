package com.proyect.pokedex.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class PokemonDao {

    private int id;
    private String nombre;
    private String foto;
    private List<String> tipos;//Deberia Ser la Clase
    private int peso;
    private List<String> habilidades;//Deberia Ser la Clase
    private String informacion;
    private String descripcion;
    private List<String> movimientos;

    public PokemonDao(){
        this.tipos = new ArrayList<String>();
        this.habilidades = new ArrayList<String>();
        this.movimientos = new ArrayList<String>();
    };

    public PokemonDao(int id, String nombre, String foto, List<String> tipos, int peso, List<String> habilidades, String informacion, String descripcion, List<String> movimientos) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.tipos = tipos;
        this.peso = peso;
        this.habilidades = habilidades;
        this.informacion = informacion;
        this.descripcion = descripcion;
        this.movimientos = movimientos;
    }
}

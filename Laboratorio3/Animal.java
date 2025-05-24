package Laboratorio3;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    String nombre;
    boolean genero;

    public Animal(String nombre, boolean genero){
        super();
        this.genero = genero;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }
    
    ArrayList<Animal> mascotas = new ArrayList<Animal>();
        //List<Animal> mascotas2 = new List<Animal>(); //No se puede instanciar directamente una interfaz
        List<Animal> mascotas3 = new ArrayList<Animal>();
}

package EjerciciosPropuestos.HashOpened;

import java.util.LinkedList;

public class HashAbierto<K, V> {
    
    private static class Entrada<K, V> {
        K clave;
        V valor;

        public Entrada(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    private LinkedList<Entrada<K, V>>[] tabla;
    private int capacidad;
    
    @SuppressWarnings("uncheked")
    public HashAbierto(int capacidad) {
        this.capacidad = capacidad;
        tabla = new LinkedList[capacidad];
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }
    }
    
    private int hash(K clave) {
        return (int)clave % capacidad;
    }
}
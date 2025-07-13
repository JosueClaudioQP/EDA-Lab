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
    
    public void agregar(K clave, V valor) {
        int indice = hash(clave);
        
        // Verificar si la clave ya existe
        for (Entrada<K, V> entrada : tabla[indice]) {
            if (entrada.clave.equals(clave)) {
                entrada.valor = valor; // Actualizar valor si clave existe
                return;
            }
        }
        
        // Si no existe, agregar nueva entrada
        tabla[indice].add(new Entrada<>(clave, valor));
    }
    
    public V buscar(K clave) {
        int indice = hash(clave);
        
        for (Entrada<K, V> entrada : tabla[indice]) {
            if (entrada.clave.equals(clave)) {
                return entrada.valor;
            }
        }
        return null;
    }
    
    public boolean eliminar(K clave) {
        int indice = hash(clave);
        
        for (Entrada<K, V> entrada : tabla[indice]) {
            if (entrada.clave.equals(clave)) {
                tabla[indice].remove(entrada);
                return true;
            }
        }
        
        return false;
    }
    
    public void mostrarTabla() {
        for (int i = 0; i < capacidad; i++) {
            System.out.print("Casilla " + i + ": ");
            for (Entrada<K, V> entrada : tabla[i]) {
                System.out.print("[" + entrada.clave + "=" + entrada.valor + "] ");
            }
            System.out.println();
        }
    }
}
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
}
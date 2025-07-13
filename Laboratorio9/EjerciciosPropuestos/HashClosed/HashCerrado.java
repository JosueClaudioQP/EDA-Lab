package EjerciciosPropuestos.HashClosed;

public class HashCerrado<K, V> {

    private static class Entrada<K, V> {
        K clave;
        V valor;
        boolean eliminado;

        public Entrada(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
            this.eliminado = false;
        }
    }

    private int capacidad;
    private Entrada<K, V>[] tabla;

    @SuppressWarnings("unchecked")
    public HashCerrado(int capacidad) {
        this.capacidad = capacidad;
        tabla = new Entrada[capacidad];
    }

    private int hash(K clave) {
        return (int)clave % capacidad;
    }
}

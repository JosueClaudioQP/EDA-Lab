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
}

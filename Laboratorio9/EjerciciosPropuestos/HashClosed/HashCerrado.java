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

    public void agregar(K clave, V valor) {
        int indice = hash(clave);
        int original = indice;

        do {
            Entrada<K, V> entrada = tabla[indice];

            if (entrada == null || entrada.eliminado) {
                tabla[indice] = new Entrada<>(clave, valor);
                return;
            }

            if (entrada.clave.equals(clave)) {
                System.out.println("Clave duplicada (" + clave + ") - reemplazando valor.");
                entrada.valor = valor;
                return;
            }

            indice = (indice + 1) % capacidad;
        } while (indice != original);

        System.out.println("Tabla llena. No se pudo insertar: " + clave);
    }

    public V buscar(K clave) {
        int indice = hash(clave);
        int original = indice;

        do {
            Entrada<K, V> entrada = tabla[indice];

            if (entrada == null) return null;
            if (!entrada.eliminado && entrada.clave.equals(clave)) {
                return entrada.valor;
            }

            indice = (indice + 1) % capacidad;
        } while (indice != original);

        return null;
    }

    public boolean eliminar(K clave) {
        int indice = hash(clave);
        int original = indice;

        do {
            Entrada<K, V> entrada = tabla[indice];

            if (entrada == null) return false;
            if (!entrada.eliminado && entrada.clave.equals(clave)) {
                entrada.eliminado = true;
                return true;
            }

            indice = (indice + 1) % capacidad;
        } while (indice != original);

        return false;
    }

    public void mostrarTabla() {
        System.out.println("Contenido de la tabla hash:");
        for (int i = 0; i < capacidad; i++) {
            Entrada<K, V> entrada = tabla[i];
            if (entrada == null || entrada.eliminado) {
                System.out.println("[" + i + "]: vacío");
            } else {
                System.out.println("[" + i + "]: " + entrada.clave + " => " + entrada.valor);
            }
        }
    }
}

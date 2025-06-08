package ProblemasPropuestos.Ejercicio3;

public class Nodo<E> {
    E dato;
    Nodo<E> siguiente;

    public Nodo(E dato){
        this.dato = dato;
        this.siguiente = null;
    }
}

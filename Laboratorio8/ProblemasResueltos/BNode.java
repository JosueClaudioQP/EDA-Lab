package ProblemasResueltos;
import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {

    public ArrayList<E> keys;           // m‑1 claves como máximo
    public ArrayList<BNode<E>> childs;  // m hijos
    public int count;                   // nº de claves ocupadas

    public BNode(int order) {
        keys   = new ArrayList<>(order);
        childs = new ArrayList<>(order);
        count  = 0;
        for (int i = 0; i < order; i++) {
            keys.add(null);
            childs.add(null);
        }
    }

    // devuelve true si el nodo está lleno (tiene m‑1 claves)
    public boolean nodeFull() {
        return count == keys.size() - 1;
    }

    public boolean searchNode(E k, int[] pos) {
        int lo = 0, hi = count - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int cmp = k.compareTo(keys.get(mid));
            if (cmp == 0) { pos[0] = mid; return true; }
            if (cmp < 0) hi = mid - 1; else lo = mid + 1;
        }
        pos[0] = lo;      // hijo a descender
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));
            if (i < count - 1) sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }
}

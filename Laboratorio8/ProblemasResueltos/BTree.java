package ProblemasResueltos;

public class BTree<E extends Comparable<E>> {

    private BNode<E> root;
    private final int order;     // m
    private boolean up;          // indica overflow que sube
    private BNode<E> nDes;       // subárbol derecho después de split

    public BTree(int order) {
        this.order = order;
        this.root  = null;
    }

    public boolean isEmpty() { return root == null; }

    /* ---------- Inserción pública ---------- */
    public void insert(E key) {
        up = false;
        E median = push(root, key);
        if (up) {                                       // la raíz se desbordó
            BNode<E> newRoot = new BNode<>(order);
            newRoot.count = 1;
            newRoot.keys.set(0, median);
            newRoot.childs.set(0, root);
            newRoot.childs.set(1, nDes);
            root = newRoot;
        }
    }

    /* ---------- Inserción recursiva ---------- */
    private E push(BNode<E> node, E key) {
        int[] pos = new int[1];

        if (node == null) {              // caso base → nueva hoja
            up = true; 
            nDes = null; 
            return key;
        }

        if (node.searchNode(key, pos)) { // duplicado
            System.out.println("Item duplicado");
            up = false; 
            return null;
        }

        E median = push(node.childs.get(pos[0]), key);

        if (!up) return null;            // no hubo overflow en subárbol

        /*  insertar median & nDes en este nodo  */
        if (node.nodeFull()) {
            median = splitNode(node, median, pos[0]);
        } else {
            putNode(node, median, nDes, pos[0]);
            up = false;
        }
        return median;
    }

    /* ---------- Inserta clave y puntero a la derecha ---------- */
    private void putNode(BNode<E> node, E key, BNode<E> rightChild, int k) {
        // Desplazar claves hacia la derecha
        for (int i = node.count - 1; i >= k; i--) {
            node.keys.set(i + 1, node.keys.get(i));
        }
        // Desplazar hijos hacia la derecha
        for (int i = node.count; i >= k + 1; i--) {
            node.childs.set(i + 1, node.childs.get(i));
        }
        node.keys.set(k, key);
        node.childs.set(k + 1, rightChild);
        node.count++;
    }

    /* ---------- Divide nodo lleno y devuelve mediana ---------- */
    private E splitNode(BNode<E> node, E key, int k) {
        BNode<E> rightFromBelow = nDes;  // subárbol que sube
        int mid = order / 2;             // mediana provisional

        // Ajuste fino de mid (para balance casi perfecto)
        if (k <= mid - 1) mid--;         // inserción en mitad izqda

        nDes = new BNode<>(order);       // nuevo nodo derecho

        /* 1. mover la mitad derecha a nDes */
        int j = 0;
        for (int i = mid + 1; i < order - 1; i++, j++) {
            nDes.keys.set(j, node.keys.get(i));
            nDes.childs.set(j, node.childs.get(i));
        }
        // Copiar el último hijo
        nDes.childs.set(j, node.childs.get(order - 1));
        nDes.count = j;
        node.count = mid + 1;            // incluye la mediana

        /* 2. insertar (key, rightFromBelow) en el lado correcto */
        if (k <= mid) {                                  // cae a la izquierda
            putNode(node, key, rightFromBelow, k);
        } else {                                         // cae a la derecha
            putNode(nDes, key, rightFromBelow, k - mid - 1);
        }

        /* 3. extraer mediana real que subirá */
        E median = node.keys.get(node.count - 1);
        nDes.childs.set(0, node.childs.get(node.count));
        node.count--;                                    // la mediana se elimina
        return median;
    }

    /* ---------- Impresión bonita ---------- */
    @Override
    public String toString() {
        if (root == null) return "(árbol vacío)";
        StringBuilder sb = new StringBuilder();
        printTree(root, sb, "", true);
        return sb.toString();
    }
    
    private void printTree(BNode<E> node, StringBuilder sb, String pref, boolean tail) {
        sb.append(pref).append(tail ? "└── " : "├── ")
          .append(node).append('\n');
        for (int i = 0; i <= node.count; i++) {
            BNode<E> ch = node.childs.get(i);
            if (ch != null) {
                printTree(ch, sb, pref + (tail ? "    " : "│   "), i == node.count);
            }
        }
    }
}
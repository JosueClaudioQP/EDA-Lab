package ProblemasPropuestos.Ejercicio3.src;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class VisualBST<T extends Comparable<T>> {
    private Nodo<T> root;

    public void insert(T value) {
        root = insertRec(root, value);
    }

    private Nodo<T> insertRec(Nodo<T> nodo, T value) {
        if (nodo == null) {
            return new Nodo<>(value);
        }
        int cmp = value.compareTo(nodo.data);
        if (cmp < 0) nodo.left = insertRec(nodo.left, value);
        else if (cmp > 0) nodo.right = insertRec(nodo.right, value);
        return nodo;
    }

    public void draw() {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("BST");
        graph.setAttribute("ui.stylesheet", "node { fill-color: skyblue; size: 30px; text-size: 16px; }");
        addNodesEdges(graph, root, null);
        graph.display();
    }   

    private void addNodesEdges(Graph graph, Nodo<T> nodo, T parent) {
        if (nodo == null) return;

        String id = nodo.data.toString();
        graph.addNode(id).setAttribute("ui.label", id);

        if (parent != null) {
            String parentId = parent.toString();
            graph.addEdge(parentId + "-" + id, parentId, id, true);
        }

        addNodesEdges(graph, nodo.left, nodo.data);
        addNodesEdges(graph, nodo.right, nodo.data);
    }
}

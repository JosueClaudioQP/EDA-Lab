package ProblemasPropuestos.Ejercicio3;

import javax.swing.*;

import ProblemasPropuestos.Ejercicio2.AVLTree;

public class VisualizarAVL {
    public static <T extends Comparable<T>> void mostrar(AVLTree<T> tree) {
        JFrame frame = new JFrame("Visualización Árbol AVL");
        AVLVisualizerPanel<T> panel = new AVLVisualizerPanel<>(tree.getRoot());

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

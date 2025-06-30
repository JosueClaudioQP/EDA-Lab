package ProblemasPropuestos.Ejercicio3;

import java.awt.*;
import javax.swing.*;

import ProblemasPropuestos.Ejercicio2.NodeAVL;

public class AVLVisualizerPanel<T extends Comparable<T>> extends JPanel {
    private NodeAVL<T> root;

    public AVLVisualizerPanel(NodeAVL<T> root) {
        this.root = root;
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g, root, getWidth() / 2, 40, getWidth() / 4);
    }

    private void drawTree(Graphics g, NodeAVL<T> node, int x, int y, int xOffset) {
        if (node == null) return;

        g.setColor(Color.BLACK);
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.WHITE);
        g.drawString(node.data.toString(), x - 7, y + 5);

        g.setColor(Color.BLACK);
        if (node.left != null) {
            int childX = x - xOffset;
            int childY = y + 60;
            g.drawLine(x, y, childX, childY);
            drawTree(g, node.left, childX, childY, xOffset / 2);
        }

        if (node.right != null) {
            int childX = x + xOffset;
            int childY = y + 60;
            g.drawLine(x, y, childX, childY);
            drawTree(g, node.right, childX, childY, xOffset / 2);
        }
    }

    public void updateTree(NodeAVL<T> newRoot) {
        this.root = newRoot;
        repaint();
    }
}

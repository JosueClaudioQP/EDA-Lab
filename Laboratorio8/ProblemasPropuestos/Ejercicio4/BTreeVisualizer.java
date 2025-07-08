package ProblemasPropuestos.Ejercicio4;

import javax.swing.*;
import ProblemasPropuestos.Ejercicio3.BTreeNode;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BTreeVisualizer<T extends Comparable<T>> extends JFrame {

    private final DrawingPanel drawingPanel;

    public BTreeVisualizer(BTreeNode<T> root) {
        if (root == null || root.n == 0) {
            throw new IllegalStateException("La raíz no puede ser nula o vacía para visualizar.");
        }
        
        this.drawingPanel = new DrawingPanel(root);

        setTitle("Visualizador de Árbol B");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JScrollPane(drawingPanel));
        setSize(1200, 800); // Dar un tamaño inicial generoso
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class DrawingPanel extends JPanel {
        private final BTreeNode<T> root;
        private static final int NODE_WIDTH = 45;
        private static final int NODE_HEIGHT = 30;
        private static final int HORIZONTAL_GAP = 15;
        private static final int VERTICAL_GAP = 70;
        private final Map<BTreeNode<T>, Point> nodeCoordinates;

        public DrawingPanel(BTreeNode<T> root) {
            this.root = root;
            this.nodeCoordinates = new HashMap<>();
            setBackground(Color.WHITE);
            calculateAllNodePositions();
        }

        /**
         * Calcula el ancho total requerido por el subárbol de un nodo.
         * Este es el paso clave para el correcto posicionamiento.
         */
        private int calculateSubtreeWidth(BTreeNode<T> node) {
            if (node.isLeaf) {
                return (node.n * NODE_WIDTH) + Math.max(0, node.n - 1) * 5;
            }

            int totalWidth = 0;
            for (BTreeNode<T> child : node.children) {
                totalWidth += calculateSubtreeWidth(child);
            }
            return totalWidth + Math.max(0, node.children.size() - 1) * HORIZONTAL_GAP;
        }

        /**
         * Paso 1: Calcula las posiciones de todos los nodos de forma recursiva.
         */
        private void calculatePositionsRecursive(BTreeNode<T> node, int x, int y) {
            if (node == null) return;
            nodeCoordinates.put(node, new Point(x, y));

            if (!node.isLeaf) {
                int totalChildWidth = calculateSubtreeWidth(node);
                int currentX = x - totalChildWidth / 2;

                for (BTreeNode<T> child : node.children) {
                    int childSubtreeWidth = calculateSubtreeWidth(child);
                    int childX = currentX + childSubtreeWidth / 2;
                    calculatePositionsRecursive(child, childX, y + VERTICAL_GAP);
                    currentX += childSubtreeWidth + HORIZONTAL_GAP;
                }
            }
        }
        
        /**
         * Paso 2: Orquesta el cálculo y establece el tamaño del panel.
         */
        private void calculateAllNodePositions() {
            if (root == null) return;
            nodeCoordinates.clear();

            int totalWidth = calculateSubtreeWidth(root);
            int startX = totalWidth / 2 + HORIZONTAL_GAP;
            calculatePositionsRecursive(root, startX, 50);

            int maxX = 0, maxY = 0;
            for (Point p : nodeCoordinates.values()) {
                maxX = Math.max(maxX, p.x);
                maxY = Math.max(maxY, p.y);
            }
            setPreferredSize(new Dimension(totalWidth + 2 * HORIZONTAL_GAP, maxY + NODE_HEIGHT + 50));
        }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (root == null || nodeCoordinates.isEmpty()) return;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Dibuja las aristas primero
            for (BTreeNode<T> node : nodeCoordinates.keySet()) {
                if (!node.isLeaf) {
                    Point parentPoint = nodeCoordinates.get(node);
                    for (BTreeNode<T> child : node.children) {
                        Point childPoint = nodeCoordinates.get(child);
                        if (parentPoint != null && childPoint != null) {
                            g2d.setColor(Color.DARK_GRAY);
                            g2d.drawLine(parentPoint.x, parentPoint.y, childPoint.x, childPoint.y);
                        }
                    }
                }
            }
            
            // Dibuja los nodos encima de las aristas
            for (Map.Entry<BTreeNode<T>, Point> entry : nodeCoordinates.entrySet()) {
                drawNode(g2d, entry.getKey(), entry.getValue());
            }
        }

        private void drawNode(Graphics2D g2d, BTreeNode<T> node, Point p) {
            int nodeWidth = (node.n * NODE_WIDTH) + Math.max(0, node.n - 1) * 5;
            int startX = p.x - nodeWidth / 2;
            int startY = p.y - NODE_HEIGHT / 2;
            
            g2d.setColor(Color.ORANGE);
            g2d.fillRect(startX, startY, nodeWidth, NODE_HEIGHT);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(startX, startY, nodeWidth, NODE_HEIGHT);
            
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            int keyX = startX;
            for(int i = 0; i < node.n; i++) {
                String keyStr = node.keys.get(i).toString();
                int strWidth = g2d.getFontMetrics().stringWidth(keyStr);
                g2d.drawString(keyStr, keyX + (NODE_WIDTH - strWidth) / 2, p.y + 5);
                
                if (i < node.n - 1) { // Dibuja separador
                    g2d.setColor(Color.BLACK);
                    g2d.drawLine(keyX + NODE_WIDTH, startY, keyX + NODE_WIDTH, startY + NODE_HEIGHT);
                }
                keyX += NODE_WIDTH + 5;
            }
        }
    }
}
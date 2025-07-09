package ProblemasPropuestos.Ejercicio8;

import javax.swing.*;
import ProblemasPropuestos.Ejercicio7.BPlusTreeNode;
import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.util.HashMap;
import java.util.Map;

public class BPlusTreeVisualizer<T extends Comparable<T>> extends JFrame {

    private final DrawingPanel drawingPanel;
    private final BPlusTreeNode<T> root;

    public BPlusTreeVisualizer(BPlusTreeNode<T> root) {
        if (root == null || root.n == 0) {
            throw new IllegalStateException("La raíz no puede ser nula o vacía para visualizar.");
        }
        this.root = root;
        this.drawingPanel = new DrawingPanel();

        setTitle("Visualizador de Árbol B+");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JScrollPane(drawingPanel));
        pack();
        setSize(1024, 768); // Tamaño inicial más grande
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class DrawingPanel extends JPanel {
        private static final int NODE_WIDTH = 50;
        private static final int NODE_HEIGHT = 30;
        private static final int HORIZONTAL_GAP = 20;
        private static final int VERTICAL_GAP = 60;
        private final Map<BPlusTreeNode<T>, Point> nodeCoordinates;

        public DrawingPanel() {
            this.nodeCoordinates = new HashMap<>();
            setBackground(Color.WHITE);
            // El cálculo de posiciones se hace antes de pintar
        }

        private void calculateNodePositions() {
            if (root == null) return;
            nodeCoordinates.clear();
            calculatePositionsRecursive(root, getWidth() / 2, 50);
        }

        private int calculateSubtreeWidth(BPlusTreeNode<T> node) {
            if (node.isLeaf) {
                return node.n * NODE_WIDTH + (node.n -1) * 5;
            }
            int totalWidth = 0;
            for (BPlusTreeNode<T> child : node.children) {
                totalWidth += calculateSubtreeWidth(child);
            }
            return totalWidth + (node.children.size() - 1) * HORIZONTAL_GAP;
        }

        private void calculatePositionsRecursive(BPlusTreeNode<T> node, int x, int y) {
            if (node == null) return;
            nodeCoordinates.put(node, new Point(x, y));

            if (!node.isLeaf) {
                int subtreeWidth = calculateSubtreeWidth(node);
                int currentX = x - subtreeWidth / 2;
                
                for (BPlusTreeNode<T> child : node.children) {
                    int childSubtreeWidth = calculateSubtreeWidth(child);
                    int childX = currentX + childSubtreeWidth / 2;
                    calculatePositionsRecursive(child, childX, y + VERTICAL_GAP + NODE_HEIGHT);
                    currentX += childSubtreeWidth + HORIZONTAL_GAP;
                }
            }
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            calculateNodePositions(); // Recalcular posiciones si la ventana cambia de tamaño
            if (root == null || nodeCoordinates.isEmpty()) return;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            drawTreeRecursive(g2d, root, null);
            drawLeafLinks(g2d);

            // Ajustar tamaño para scroll
            int maxX = 0, maxY = 0;
            for(Point p : nodeCoordinates.values()){
                if(p.x > maxX) maxX = p.x;
                if(p.y > maxY) maxY = p.y;
            }
            setPreferredSize(new Dimension(maxX + 2 * NODE_WIDTH, maxY + 2 * NODE_HEIGHT));
            revalidate();
        }

        private void drawTreeRecursive(Graphics2D g2d, BPlusTreeNode<T> node, Point parentCoord) {
            if (node == null) return;
            Point currentCoord = nodeCoordinates.get(node);
            if (currentCoord == null) return;

            if (parentCoord != null) {
                g2d.setColor(Color.BLACK);
                g2d.drawLine(parentCoord.x, parentCoord.y, currentCoord.x, currentCoord.y);
            }
            
            int keysWidth = node.n * NODE_WIDTH + (node.n - 1) * 5;
            int startX = currentCoord.x - keysWidth / 2;
            int startY = currentCoord.y - NODE_HEIGHT / 2;
            
            g2d.setColor(node.isLeaf ? Color.CYAN : Color.YELLOW);
            g2d.fillRect(startX, startY, keysWidth, NODE_HEIGHT);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(startX, startY, keysWidth, NODE_HEIGHT);
            
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            int keyX = startX + NODE_WIDTH / 2;
            for (T key : node.keys) {
                String keyStr = key.toString();
                int strWidth = g2d.getFontMetrics().stringWidth(keyStr);
                g2d.drawString(keyStr, keyX - strWidth / 2, currentCoord.y + 5);
                keyX += NODE_WIDTH + 5;
            }

            if (!node.isLeaf) {
                for (BPlusTreeNode<T> child : node.children) {
                    drawTreeRecursive(g2d, child, new Point(currentCoord.x, currentCoord.y + NODE_HEIGHT/2));
                }
            }
        }
        
        private void drawLeafLinks(Graphics2D g2d) {
            BPlusTreeNode<T> firstLeaf = root;
            while(!firstLeaf.isLeaf) {
                firstLeaf = firstLeaf.children.get(0);
            }

            g2d.setColor(Color.BLUE);
            Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);
            g2d.setStroke(dashed);

            BPlusTreeNode<T> current = firstLeaf;
            while(current != null && current.next != null) {
                Point p1 = nodeCoordinates.get(current);
                Point p2 = nodeCoordinates.get(current.next);
                if (p1 != null && p2 != null) {
                    int p1Width = current.n * NODE_WIDTH + (current.n-1)*5;
                    int p2Width = current.next.n * NODE_WIDTH + (current.next.n-1)*5;
                    
                    // Curva de un nodo al siguiente
                    QuadCurve2D.Float q = new QuadCurve2D.Float();
                    q.x1 = p1.x + p1Width / 4;
                    q.y1 = p1.y + NODE_HEIGHT / 2;
                    q.x2 = p2.x - p2Width / 4;
                    q.y2 = p2.y + NODE_HEIGHT / 2;
                    q.ctrlx = (p1.x + p2.x) / 2;
                    q.ctrly = p1.y + NODE_HEIGHT + 20;
                    g2d.draw(q);
                }
                current = current.next;
            }
        }
    }
}

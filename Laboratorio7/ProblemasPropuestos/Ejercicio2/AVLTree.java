package ProblemasPropuestos.Ejercicio2;

public class AVLTree<T extends Comparable<T>> {
    private NodeAVL<T> root;

    public void destroy() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(T data) {
        root = insert(root, data);
    }

    public void remove(T data) {
        root = remove(root, data);
    }

    public boolean search(T data) {
        return search(root, data) != null;
    }

    public T min() {
        NodeAVL<T> node = minValueNode(root);
        return (node != null) ? node.data : null;
    }

    public T max() {
        NodeAVL<T> node = maxValueNode(root);
        return (node != null) ? node.data : null;
    }

    public T predecesor(T data) {
        NodeAVL<T> curr = root, pred = null;
        while (curr != null) {
            if (data.compareTo(curr.data) > 0) {
                pred = curr;
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        return (pred != null) ? pred.data : null;
    }

    public T sucesor(T data) {
        NodeAVL<T> curr = root, succ = null;
        while (curr != null) {
            if (data.compareTo(curr.data) < 0) {
                succ = curr;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return (succ != null) ? succ.data : null;
    }

    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    private NodeAVL<T> insert(NodeAVL<T> node, T data) {
        if (node == null) return new NodeAVL<>(data);

        if (data.compareTo(node.data) < 0)
            node.left = insert(node.left, data);
        else if (data.compareTo(node.data) > 0)
            node.right = insert(node.right, data);
        else
            return node;

        updateHeight(node);
        return balance(node);
    }

    private NodeAVL<T> remove(NodeAVL<T> node, T data) {
        if (node == null) return null;

        if (data.compareTo(node.data) < 0)
            node.left = remove(node.left, data);
        else if (data.compareTo(node.data) > 0)
            node.right = remove(node.right, data);
        else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                NodeAVL<T> temp = minValueNode(node.right);
                node.data = temp.data;
                node.right = remove(node.right, temp.data);
            }
        }

        if (node == null) return null;

        updateHeight(node);
        return balance(node);
    }

    private NodeAVL<T> search(NodeAVL<T> node, T data) {
        if (node == null || node.data.equals(data)) return node;
        if (data.compareTo(node.data) < 0)
            return search(node.left, data);
        return search(node.right, data);
    }

    private NodeAVL<T> minValueNode(NodeAVL<T> node) {
        while (node != null && node.left != null)
            node = node.left;
        return node;
    }

    private NodeAVL<T> maxValueNode(NodeAVL<T> node) {
        while (node != null && node.right != null)
            node = node.right;
        return node;
    }

    private void inOrder(NodeAVL<T> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    private void preOrder(NodeAVL<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    private void postOrder(NodeAVL<T> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

    private void updateHeight(NodeAVL<T> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(NodeAVL<T> node) {
        return node != null ? node.height : 0;
    }

    private int getBalance(NodeAVL<T> node) {
        return (node != null) ? height(node.left) - height(node.right) : 0;
    }

    private NodeAVL<T> balance(NodeAVL<T> node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) < 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1) {
            if (getBalance(node.right) > 0)
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public NodeAVL<T> rotateLeft(NodeAVL<T> x) {
        NodeAVL<T> y = x.right;
        NodeAVL<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    public NodeAVL<T> rotateRight(NodeAVL<T> y) {
        NodeAVL<T> x = y.left;
        NodeAVL<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }
}

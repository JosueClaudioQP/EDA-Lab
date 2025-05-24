package Laboratorio3;

public class MyList<T> {
    private Node<T> root;
    private int size;

    public MyList() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(T element) {
        Node<T> newNode = new Node<>(element);
        if (root == null) {
            root = newNode;
        } else {
            Node<T> current = root;
            while (current.getNextNode() != null) {
                current = current.getNextNode();
            }
            current.setNextNode(newNode);
        }
        size++;
        return true;
    }

    public void add(int index, T element) {
        checkIndexForAdd(index);
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.setNextNode(root);
            root = newNode;
        } else {
            Node<T> prev = getNode(index - 1);
            newNode.setNextNode(prev.getNextNode());
            prev.setNextNode(newNode);
        }
        size++;
    }

    public T get(int index) {
        checkIndex(index);
        return getNode(index).getData();
    }

    public T set(int index, T element) {
        checkIndex(index);
        Node<T> node = getNode(index);
        T old = node.getData();
        node.setData(element);
        return old;
    }

    public T remove(int index) {
        checkIndex(index);
        T removed;
        if (index == 0) {
            removed = root.getData();
            root = root.getNextNode();
        } else {
            Node<T> prev = getNode(index - 1);
            removed = prev.getNextNode().getData();
            prev.setNextNode(prev.getNextNode().getNextNode());
        }
        size--;
        return removed;
    }

    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    public int indexOf(T element) {
        Node<T> current = root;
        int index = 0;
        while (current != null) {
            if ((element == null && current.getData() == null) || 
                (element != null && element.equals(current.getData()))) {
                return index;
            }
            current = current.getNextNode();
            index++;
        }
        return -1;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    private Node<T> getNode(int index) {
        Node<T> current = root;
        for (int i = 0; i < index; i++) {
            current = current.getNextNode();
        }
        return current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = root;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNextNode() != null)
                sb.append(", ");
            current = current.getNextNode();
        }
        sb.append("]");
        return sb.toString();
    }
}

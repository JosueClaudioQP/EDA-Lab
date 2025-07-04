package ProblemasResueltos;

public class TestBTree {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(6);   // orden 6
        int[] data = {50,30,70,10,40,60,90,20,35,45,55,65,75,85,95,5,15,25};
        for (int x : data) tree.insert(x);

        System.out.println(tree);               // salida visual
    }
}

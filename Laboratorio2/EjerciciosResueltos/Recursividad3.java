package Laboratorio2.EjerciciosResueltos;

public class Recursividad3 {
    void imprimir(int x) {
        if (x>0) {
            System.out.println(x);
            imprimir(x-1);
        }
    }
    public static void main(String[] ar) {
        Recursividad3 re=new Recursividad3();
        re.imprimir(5);
    }
}

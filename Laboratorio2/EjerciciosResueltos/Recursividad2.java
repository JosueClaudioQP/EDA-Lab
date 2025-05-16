package Laboratorio2.EjerciciosResueltos;

public class Recursividad2 {
    void imprimir(int x) {
        System.out.println(x);
        imprimir(x-1);
    }
    public static void main(String[] ar) {
        Recursividad2 re=new Recursividad2();
        re.imprimir(5);
    }
}

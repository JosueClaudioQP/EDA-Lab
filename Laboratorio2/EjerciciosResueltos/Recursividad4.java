package Laboratorio2.EjerciciosResueltos;

public class Recursividad4 {
    void imprimir(int x) {
        if (x>0) {
            imprimir(x-1);
            System.out.println(x);
        }
    }   
    public static void main(String[] ar) {
        Recursividad4 re=new Recursividad4();
        re.imprimir(5);
    }
}

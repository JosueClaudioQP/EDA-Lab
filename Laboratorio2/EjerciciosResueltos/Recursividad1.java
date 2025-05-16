package Laboratorio2.EjerciciosResueltos;

public class Recursividad1 {
    void repetir() {
        repetir();
    }

    public static void main(String[] ar) {
        Recursividad1 re=new Recursividad1();
        re.repetir();
    }
}

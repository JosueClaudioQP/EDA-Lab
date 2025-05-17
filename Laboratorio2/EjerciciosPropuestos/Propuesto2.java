package Laboratorio2.EjerciciosPropuestos;

public class Propuesto2 {
    public int[] rotarIzquierdaArray(int[] A){
        int d = 2;
        int n = A.length;   
        int[] Ainvertido = new int[n];

        for (int i = 0; i < n; i++) {
            Ainvertido[i] = A[(i + d) % n];
        }

        return Ainvertido;
    }
}

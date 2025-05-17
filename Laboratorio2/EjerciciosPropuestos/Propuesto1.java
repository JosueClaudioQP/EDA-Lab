package Laboratorio2.EjerciciosPropuestos;

public class Propuesto1 {
    public int[] invertirArray(int[] a){
        if(a.length > 0){
            for(int i = 0; i < a.length-i-1; i++){
                int aux = a[i];
                a[i] = a[a.length-i-1];
                a[a.length-i-1] = aux;
            }
        }
        return a;
    }
    
    void imprimir(int[] a) {
        for (int f = 0; f < a.length; f++)
            System.out.print(a[f] + " ");
            System.out.println("\n");
    }
}

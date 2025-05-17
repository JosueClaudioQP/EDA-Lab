package Laboratorio2.EjerciciosPropuestos;

public class Propuesto3 {
    public void trianguloRecursivo1(int base){
        if(base > 0){
            trianguloRecursivo1(base-1);
            for(int i = 0; i < base; i++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

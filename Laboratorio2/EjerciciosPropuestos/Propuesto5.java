package Laboratorio2.EjerciciosPropuestos;

public class Propuesto5 {
    public void trianguloRecursivo3(int base){
        if(base > 0){
            trianguloRecursivo3(base-1);
            int nivel = 5;
            for(int i = 0; i < nivel-base; i++){
                System.out.print(" ");
            }
            for(int i = 0; i < (2*base-1); i++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

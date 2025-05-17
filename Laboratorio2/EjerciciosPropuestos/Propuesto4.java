package Laboratorio2.EjerciciosPropuestos;

public class Propuesto4 {
    public void trianguloRecursivo2(int base){
        if(base > 0){
            int nivel = 5;
            trianguloRecursivo2(base-1);
            for(int i = 0; i < nivel-base; i++){
                System.out.print(" ");
            }
            for(int i = 0; i < base; i++){
                System.out.print("*");
            }
            System.out.println();

        }
    }
}

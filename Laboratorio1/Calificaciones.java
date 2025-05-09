package Laboratorio1;
import java.util.*;

class Calificaciones {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingresar numero de alumnos:");
        int alum = sc.nextInt();
        int[] notas = new int[alum];

        System.out.println("Ingrese sus notas:");
        for(int i = 0; i < notas.length; i++){
            notas[i] = sc.nextInt();
        }
        ordenarSort(notas);
        System.out.println("La mediana es:" + mediana(notas));
        System.out.println("La moda es:" + moda(notas));
        System.out.print("La desviacion estandar es:" + DesEst(notas));

        sc.close();
    }

    public static double mediana(int[] num){
        double med;
        if(num.length%2 == 1){
            med = num[num.length/2];
        } else {
            med = (num[num.length/2] + num[num.length/2-1])/2; 
        }
        return med;
    }

    public static int moda(int[] nums){
        int max = 0;
        int mayor = nums[0];
        for(int i = 0; i < nums.length; i++){
            int conteo = 0;
            for(int j = 0; j < nums.length; j++){
                if(nums[i] == nums[j])
                    conteo++;
            }
            if(conteo > max){
                max = conteo;
                mayor = nums[i];
            }
        }
        return mayor; 
    }

    public static double DesEst(int[] nums){
        int media = 0;
        for(int i = 0; i < nums.length; i++){
            media += nums[i];
        }
        media = media / nums.length;

        double desv = 0;
        for(int j = 0; j < nums.length; j++){
            desv += Math.pow(nums[j]-media,2);
        }
        desv = desv / (nums.length-1);
        desv = Math.sqrt(desv);

        return desv;
    }

    public static int[] ordenarSort(int[] num){
        for(int i = 0; i < num.length-1; i++){
            for(int j = 0; j < num.length-1-i; j++){
                if(num[j]>num[j+1]){
                    int temp = num[j];
                    num[j] = num[j+1];
                    num[j+1] = temp;
                }
            }
        }
        return num;
    }
}
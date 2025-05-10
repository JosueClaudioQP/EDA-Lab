package Laboratorio1;
import java.util.*;

public class EjercicioPropuesto3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingresar cantidad de numeros a ordenar: ");
        int cant = sc.nextInt();
        int[] nums = new int[cant];
        System.out.println("Ingresar numeros a ordenar:");
        for(int i = 0; i < nums.length; i++){
            nums[i] = sc.nextInt();
        }

        insertionSort(nums);

        System.out.print("Arreglo ordenado: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }

        sc.close();
    }

    public static void insertionSort(int[] nums) {
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            int actual = nums[i];
            int j = i - 1;

            while (j >= 0 && nums[j] > actual) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = actual;

            System.out.print("Paso " + i + ": ");
            for (int k = 0; k < n; k++) {
                System.out.print(nums[k] + " ");
            }
            System.out.println();
        }
    }
}

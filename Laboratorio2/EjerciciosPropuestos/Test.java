package Laboratorio2.EjerciciosPropuestos;

public class Test {
    public static void main(String[] args) {
        int[] nums = {5,9,7,8,1};
        Propuesto1 i = new Propuesto1();
        System.out.println("Ejercicio1:");
        i.imprimir(nums);
        i.invertirArray(nums);
        i.imprimir(nums);

        int[] nums2 = {1,2,3,4,5};
        System.out.println("Ejercicio2:");
        Propuesto2 inv = new Propuesto2();
        int[] result = inv.rotarIzquierdaArray(nums2);
        i.imprimir(result);

        System.out.println("Ejercicio3:");
        int base = 5;
        Propuesto3 tri = new Propuesto3();
        tri.trianguloRecursivo1(base);

        System.out.println("Ejercicio4:");
        Propuesto4 tri2 = new Propuesto4();
        tri2.trianguloRecursivo2(base);
    }
}

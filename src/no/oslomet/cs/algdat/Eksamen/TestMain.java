package no.oslomet.cs.algdat.Eksamen;

import java.util.Comparator;

public class TestMain {
    public static void main(String[] args) {

        //EksamenSBinTre<String> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        //System.out.println(tre.antall());

        //Integer[] a = {4, 7, 2, 9, 5, 10, 8, 1, 3, 6};
        EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());

        /*
        for(int verdi : a) {
            tre.leggInn(verdi);
        }
        System.out.println(tre.antall());

         */

        Integer[] b = {4, 7, 2, 9, 4, 10, 8, 7, 4, 6};
        for(int verdi : b) {
            tre.leggInn(verdi);
        }
        System.out.println(tre.antall());
        System.out.println(tre.antall(5));
        System.out.println(tre.antall(4));


        EksamenSBinTre<String> tre2 = new EksamenSBinTre(Comparator.naturalOrder());

        tre2.leggInn("A");
        tre2.leggInn("B");
        tre2.leggInn("A");
        tre2.leggInn("D");
        tre2.leggInn("D");
        System.out.println(tre2.toStringPostOrder());
        tre2.postorden(System.out::println);






    }

}

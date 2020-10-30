package no.oslomet.cs.algdat.Eksamen;

import java.util.Comparator;

public class TestMain {
    public static void main(String[] args) {

        //Oppgave 0
        System.out.println("---Oppgave 0---");
        EksamenSBinTre<String> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        System.out.println(tre.antall());

        System.out.println();

        //Oppgave 1
        System.out.println("---Oppgave 1---");
        Integer[] a = {4, 7, 2, 9, 5, 10, 8, 1, 3, 6};
        EksamenSBinTre<Integer> tre1 = new EksamenSBinTre<>(Comparator.naturalOrder());
        for(int verdi : a) tre1.leggInn(verdi);
        System.out.println(tre1.antall());

        System.out.println();

        //Oppgave 2
        System.out.println("---Oppgave 2---");
        EksamenSBinTre<Integer> tre2 = new EksamenSBinTre<>(Comparator.naturalOrder());
        Integer[] b = {4, 7, 2, 9, 4, 10, 8, 7, 4, 6};
        for(int verdi : b) tre2.leggInn(verdi);
        System.out.println(tre2.antall());
        System.out.println(tre2.antall(5));
        System.out.println(tre2.antall(4));
        System.out.println(tre2.antall(7));
        System.out.println(tre2.antall(10));


        System.out.println();

        //Oppgave 6
        int[] c = {4, 7, 2, 9, 4, 10, 8, 7, 4, 6, 1};
        EksamenSBinTre<Integer> tre3 = new EksamenSBinTre<>(Comparator.naturalOrder());
        for(int verdi : c) tre3.leggInn(verdi);

        System.out.println(tre3.fjernAlle(4));
        tre3.fjernAlle(7); tre3.fjern(8);

        System.out.println(tre3.antall());

        System.out.println(tre + " " +tre3.toStringPostOrder());





        /*
        Integer[] b = {4, 7, 2, 9, 4, 10, 8, 7, 4, 6};
        for(int verdi : b) {
            tre.leggInn(verdi);
        }
        System.out.println(tre.antall());
        System.out.println(tre.antall());
        System.out.println(tre.antall(5));
        System.out.println(tre.antall(4));


        EksamenSBinTre<String> tre2 = new EksamenSBinTre(Comparator.naturalOrder());
        EksamenSBinTre<Integer> tre3 = new EksamenSBinTre<>(Comparator.naturalOrder());
        Integer[] c = {4, 7, 2, 9, 4, 10, 8, 7, 4, 6};
        for(int verdi : c) {
            tre3.leggInn(verdi);
        }

        System.out.println("Printer tre3:" +tre3.toStringPostOrder());

        tre2.leggInn("A");
        tre2.leggInn("B");
        tre2.leggInn("A");
        tre2.leggInn("D");
        tre2.leggInn("D");
        System.out.println(tre2.antall());
        //System.out.println("Printer tre2: " +tre2.toStringPostOrder());
        //tre2.postorden(System.out::println);
        System.out.println("Serialiserer:");
        tre3.serialize();

        System.out.println();

        tre2.fjernAlle("D");

         */

/*
        int[] a = {7, 4, 6, 6, 6, 8, 7, 4, 6, 6};
        EksamenSBinTre<Integer> tre4 = new EksamenSBinTre<>(Comparator.naturalOrder());
        for(int verdi : a) {
            tre4.leggInn(verdi);
        }

       // System.out.println("\n"+tre4.toStringPostOrder());

        System.out.println("\n"+tre4.fjernAlle(7));
        //tre4.nullstill();
        System.out.println("\n"+tre4.fjernAlle(4));
        System.out.println("\n"+tre4.fjernAlle(10));


        tre4.fjern(8);

        System.out.println("Antall:" +tre4.antall());
        System.out.println(tre4.toStringPostOrder());

        EksamenSBinTre<Integer> tre5 = new EksamenSBinTre(Comparator.naturalOrder());
        int[] b = {1, 4, 1, 3, 1, 2, 1, 1};
        for (int verdi : b) tre5.leggInn(verdi);


        System.out.println(tre5.fjernAlle(1));
        System.out.println("Antall: " +tre5.antall());
        System.out.println(tre5.toStringPostOrder());





 */






    }

}

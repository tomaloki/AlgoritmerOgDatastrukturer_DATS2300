package no.oslomet.cs.algdat.Eksamen;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringJoiner;

public class EksamenSBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public EksamenSBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    /**
     * Metode som skal legge inn ny node i et binærtre
     * @param verdi er noden som skal inn i treet
     * @return fullført innlagt verdi
     */

    public boolean leggInn(T verdi) {
        //Foreldrenode må ha riktig verdi
        //Ser på programkode 5.2_3

        Node<T> current = rot; //current starter i roten
        Node<T> q = null;
        int cmp = 0;        //hjelpevariabel

        while (current != null) {        //fortsetter til p er ute av treet
            q = current;        //q er forelder til current
            cmp = comp.compare(verdi, current.verdi);
            current = cmp < 0 ? current.venstre : current.høyre;    //flytter current
        }

        current = new Node<>(verdi, q);        //oppretter en ny node
        if (q == null)
            rot = current;      //current blir til rotnoden
        else if (cmp < 0)
                q.venstre = current;        //til venstre for q
            else
                q.høyre = current;      //til høyre for q

            antall++;
            return true;        //verdien har blitt plassert i treet
        }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    /**
     * Metode som skal returnere antall forekomster av en verdi i binærtreet
     * @param verdi er verdien vi ønsker å telle
     * @return antallForekomster
     */

    public int antall(T verdi) {
        //Denne oppgaven har jeg nettopp vært borti i kompendiet!
        Node<T> current = rot;      //nåværende note er rotnode
        int antallForekomster = 0;  //tellevariabel for antall ganger verdien blir telt

        while(current != null) {
            int cmp = comp.compare(verdi, current.verdi);
            if(cmp < 0)
                current = current.venstre;
            else {
                if (cmp == 0)
                    antallForekomster++;
                current = current.høyre;
            }

        }
        return antallForekomster;

    }

    public void nullstill() {

        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    /**
     * Metode som skal returnere første node post orden med p som rot
     * @param p er roten
     * @param <T>
     * @return første node post orden
     */

    private static <T> Node<T> førstePostorden(Node<T> p) {
        //Huskeregel for postorden: venstre, høyre, node
        //første noden i postorden er den der det ikke er mulig å gå til venstre eller til høyre


        if (p.venstre != null){
            p = p.venstre;
        }
        else if (p.høyre != null) {
            p = p.høyre;
        }
        return p;
    }


    /**
     * Metode som skal returnere noden som kommet etter p i postorden (over)
     * @param p er noden som kommer før verdien som returneres
     * @param <T>
     * @return p sin neste (hvis p er den siste i postorden, skal metodene returnere null)
     */
    private static <T> Node<T> nestePostorden(Node<T> p) {


        //venstre barn finnes
        if (p.høyre != null) return p.høyre;


            Node parent = p.forelder;

            while (parent != null && parent.høyre == p) {
                parent = parent.forelder;
                p = p.forelder;
            }
            //vi har et høyrebarn som er søsken av p, det er neste postorder
            if (parent == null) return null;

            return parent.høyre;
        }



    public void postorden(Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");

    }
} // ObligSBinTre

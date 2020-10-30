package no.oslomet.cs.algdat.Eksamen;


import java.util.*;

public class EksamenSBinTre<T> {
    private static final class Node<T>              // en indre nodeklasse
    {
        private T verdi;                            // nodens verdi
        private Node<T> venstre, høyre;             // venstre og høyre barn
        private Node<T> forelder;                   // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)     // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                                    // peker til rotnoden
    private int antall;                                     // antall noder
    private int endringer;                                  // antall endringer

    private final Comparator<? super T> comp;               // komparator

    public EksamenSBinTre(Comparator<? super T> c)          // konstruktør
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


    public boolean leggInn(T verdi) {
        //Tatt utgangspunkt i programkode 5.2.4 a) fra kompendiet
        //gjort endringer som trengs for forelder-referanse

        if (tom()) {
            rot = new Node<>(verdi, null);
        } else {

            Node<T> p = rot;                            //p starter i roten
            Node<T> q = null;
            int cmp = 0;                                //hjelpevariabel

            while (p != null) {                         //fortsetter til p er ute av treet
                q = p;                                  //q er forelder til p
                cmp = comp.compare(verdi, p.verdi);
                p = cmp < 0 ? p.venstre : p.høyre;      //flytter p
            }


            //p er nå null, dvs ute av treet, q er den siste vi passerte

            p = new Node<>(verdi, q);                   //oppretter en ny node
            if (q == null) rot = p;                     //p blir til rotnoden
            else if (cmp < 0) q.venstre = p;            //til venstre for q
            else q.høyre = p;                           //til høyre for q
            p.forelder = q;                             //forelder til p er q
        }

        antall++;           //én verdi mer i treet
        return true;        //verdien har blitt plassert i treet
    }

    public boolean fjern(T verdi) {
        if (verdi == null) return false;                //treet har ingen nullverdier
        Node<T> p = rot;
        Node<T> q = null;                               //q er forelder til p

        while (p != null) {                             //leter etter verdi
            int cmp = comp.compare(verdi, p.verdi);     //sammenligner
            if (cmp < 0) {                              //går til venstre
                q = p;
                p = p.venstre;
            } else if (cmp > 0) {                       //går til høyre
                q = p;
                p = p.høyre;
            } else break;                               //verdien vi søkte ligger i p
        }

        if (p == null) return false;     //finner ikke verdi

        if (p.venstre == null || p.høyre == null) {     //p har ingen eller ett barn
            Node<T> barn = p.venstre != null ? p.venstre : p.høyre;
            if (p == rot) {
                rot = barn;
            } else if (p == q.venstre) {
                q.venstre = barn;
                if (barn != null) {
                    barn.forelder = q;
                }
            } else q.høyre = barn;
            if (barn != null) {
                barn.forelder = q;
            }
        } else {      //tilfelle 3              //p har to barn
            Node<T> s = p;
            Node<T> r = p.høyre;                //finner neste inorden

            while (r.venstre != null) {
                s = r;                          //s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;                  //kopierer verdien i r til p

            if (s != p) s.venstre = r.høyre;
            else s.høyre = r.høyre;
            if (r.høyre != null) r.høyre.forelder = s;
        }
        antall--;                               //én node mindre i treet
        return true;
    }


    public int fjernAlle(T verdi) {
        //tar i bruk og tester kode som jeg har skrevet i forbindelse med en oppgave i kompendiet
        //seksjon 5.2.8, oppgave 3

        int verdiAntall = 0;
        while (inneholder(verdi)) {
            fjern(verdi);
            verdiAntall++;
        }
        return verdiAntall;
    }

    public int antall(T verdi) {
        //I denne oppgaven har jeg tatt i bruk løsning på oppgave 2, tilhørende avsnitt
        //5.2.6 i kompendiet.

        Node<T> current = rot;      //nåværende note er rotnode
        int antallForekomster = 0;  //tellevariabel for antall ganger verdien blir telt

        while (current != null) {
            int cmp = comp.compare(verdi, current.verdi);       //sammenligner
            if (cmp < 0)
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
        if (!tom()) {                       //så lenge treet ikke er tomt
            int antall = antall();
            if (antall == 1) {              //bare én node (rotnode) eksisterer
                fjern(rot.verdi);
            } else {
                Node<T> p = rot;
                while (p.venstre != null) {
                    p = p.venstre;
                }

                for (int i = 0; i < antall; i++) {
                    T verdi = null;
                    if (p != null) {
                        verdi = p.verdi;
                        p = nestePostorden(p);
                    }
                    fjern(verdi);
                }
            }
        }
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        //Implementert kode fra kompendiet: seksjon 5.1.7, programkode 5.1.7 h)
        //Første node i postorden = noden hvor man ikke kan gå til venstre eller høyre

        while (true) {                              //søker etter bladnoden som ligger lengst til venstre i treet
            if (p.venstre != null) p = p.venstre;
            else if (p.høyre != null) p = p.høyre;
            else return p;
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {

        //p har ikke en forelder = p er den siste i postorden
        if (p.forelder == null) return null;

        //p er høyrebarn til sin forelder, p sin forelder er neste
        else if (p == p.forelder.høyre || p.forelder.høyre == null) return p.forelder;

        //p er venstrebarn: returner førstPostorden av p sin forelder sitt høyre barn
        else return førstePostorden(p.forelder.høyre);
    }


    public void postorden(Oppgave<? super T> oppgave) {
        if (rot == null) return;

        Node<T> p = førstePostorden(rot);

        while (p != null) {                 //går til den første i postorden
            oppgave.utførOppgave(p.verdi);
            p = nestePostorden(p);
        }
    }


    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if (p.venstre != null) postordenRecursive(p.venstre, oppgave);
        if (p.høyre != null) postordenRecursive(p.høyre, oppgave);
        oppgave.utførOppgave(p.verdi);

    }

    public ArrayList<T> serialize() {
        //Deler av koden er hentet fra undervisningsvideo med André, --> "uke 09 level order"

        ArrayList listeMedNoder = new ArrayList<>();
        ArrayDeque<Node<T>> queue = new ArrayDeque<>();  //brueke deque for å håndtere køen

        //legg til rotnoden

        queue.addFirst(rot);

        while (!queue.isEmpty()) {
            //1. tar ut første fra køen
            Node<T> current = queue.removeFirst();

            //2. legg til current sine to barn til køen
            if (current.venstre != null) {
                queue.addLast(current.venstre);
            }
            if (current.høyre != null) {
                queue.addLast(current.høyre);
            }

            //3. Skriv ut
            System.out.print(current.verdi + " ");
            listeMedNoder.add(current.verdi);
        }
        return listeMedNoder;

    }


    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        //Kode fra kompendiet, seksjon 5.2.3 c)
        EksamenSBinTre<K> nyttBinærTre = new EksamenSBinTre<>(c);   //komparatoren c
        data.forEach(nyttBinærTre::leggInn);                        //bygger opp treet
        return nyttBinærTre;                                        //returnerer det nye treet
    }
} // ObligSBinTre

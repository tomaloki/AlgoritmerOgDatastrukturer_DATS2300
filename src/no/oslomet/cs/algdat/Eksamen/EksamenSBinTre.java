package no.oslomet.cs.algdat.Eksamen;


import java.util.*;

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
     *
     * @param verdi er noden som skal inn i treet
     * @return fullført innlagt verdi
     */

    public boolean leggInn(T verdi) {
        //Foreldrenode må ha riktig verdi
        //Ser på programkode 5.2_3

        if (tom()) {
            rot = new Node<T>(verdi, null);
        }

        else {

        Node<T> p = rot; //p starter i roten
        Node<T> q = null;
        int cmp = 0;        //hjelpevariabel

        while (p != null) {        //fortsetter til p er ute av treet
            q = p;        //q er forelder til p
            cmp = comp.compare(verdi, p.verdi);
            p = cmp < 0 ? p.venstre : p.høyre;    //flytter p
        }


        //p er nå null, dvs ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);        //oppretter en ny node
        if (q == null) rot = p;      //p blir til rotnoden
        else if (cmp < 0) q.venstre = p;        //til venstre for q
        else q.høyre = p;      //til høyre for q
    }

        antall++;           //én verdi mer i treet
        endringer++;
        return true;        //verdien har blitt plassert i treet
    }

    /**
     * Metode som skal fjerne en verdi fra et binært søketre
     * Spesifikt for trær med verdier som forekommer flere ganger
     *
     * @param verdi er verdien som skal fjernes
     * @return true (vellykket fjerning)
     */

    public boolean fjern(T verdi) {
        if (verdi == null) return false;       //treet har ingen nullverdier

        Node<T> p = rot;
        Node<T> q = null;      //q skal være forelder til p

        while (p != null) {        //leter etter verdi
            int cmp = comp.compare(verdi, p.verdi);   //sammenligner
            if (cmp < 0) {       //går til venstre
                q = p;
                p = p.venstre;
            }
            else if (cmp > 0) {      //går til høyre
                q = p;
                p = p.høyre;
            }
            else break;     //den søkte verdien ligger i current
        }

        if (p == null) return false;       //finner ikke verdi

        if (p.venstre == null || p.høyre == null) {  //tilfelle 1) og 2) --> beskrevet i komp
            Node<T> barn = p.venstre != null ? p.venstre : p.høyre;
            if (p == rot) {
                rot = barn;
            } else if (p == q.venstre) {
                q.venstre = barn;
                if(barn != null) {
                    barn.forelder = q;
                }
            }
            else {
                q.høyre = barn;
                if(barn != null) {
                    barn.forelder = q;
                }
            }
        }
        else {      //tilfelle 3
            Node<T> s = p;
            Node<T> r = p.høyre;  //finner neste inorden

            while (r.venstre != null) {
                s = r;         //s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;    //kopierer verdien i r til p

            if (s != p) {
                s.venstre = r.høyre;
                if(r.høyre != null) {
                    r.forelder.høyre = s;
                }
            }
            else {
                s.høyre = r.høyre;
                if(r.høyre != null) {
                    r.forelder.høyre = s;
                }
            }
        }
        antall--;       //det er én node mindre i treet
        endringer ++;
        return true;
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


        while(true) {
            if (p.venstre != null) p = p.venstre;

            else if (p.høyre != null) p = p.høyre;

            else return p;
        }
    }


    /**
     * Metode som skal returnere noden som kommet etter p i postorden (over)
     * @param p er noden som kommer før verdien som returneres
     * @param <T>
     * @return p sin neste (hvis p er den siste i postorden, skal metodene returnere null)
     */
    private static <T> Node<T> nestePostorden(Node<T> p) {

        //p har ikke en forelder = p er den siste i postorden
        if (p.forelder == null) return null;

        //p er høyrebarn, p sin forelder er neste
        else if (p == p.forelder.høyre || p.forelder.høyre == null) return p.forelder;
        else return førstePostorden(p.forelder.høyre);
    }



    public void postorden(Oppgave<? super T> oppgave) {


        Node<T> p = nestePostorden(rot);
        while(p != null) {       //går til den første i postorden
            if(p.venstre != null) {
                p = p.venstre;
            }
            else if(p.høyre != null) {
                p = p.høyre;
            }
            else break;
        }
        oppgave.utførOppgave(p.verdi);

        Node f = nestePostorden(p);

        if(f.høyre == null || p == f.høyre) {
            p = f;

            while (true) {
                if (p.venstre != null) {
                    p = p.venstre;
                }
                if (p.høyre != null) {
                    p = p.høyre;
                } else break;
            }
        }
        oppgave.utførOppgave(p.verdi);
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if(p.venstre != null) postordenRecursive(p.venstre, oppgave);
        if(p.høyre != null) postordenRecursive(p.høyre, oppgave);
        oppgave.utførOppgave(p.verdi);

    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");

    }
} // ObligSBinTre

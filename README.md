# Mappeeksamen i Algoritmer og Datastrukturer Høst 2020

# Krav til innlevering

Se oblig-tekst for alle krav, og husk spesielt på følgende:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* git bundle er levert inn
* Hovedklassen ligger i denne path'en i git: src/no/oslomet/cs/algdat/Eksamen/EksamenSBinTre.java
* Ingen debug-utskrifter
* Alle testene i test-programmet kjører og gir null feil (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet


# Beskrivelse av oppgaveløsning (4-8 linjer/setninger per oppgave)

Har brukt git til å dokumentere arbeidet. Commiter så fort jeg gjør noe, og så ofte jeg kan, for å vise
at det har blitt tatt i bruk.

* Oppgave 1: Løste ved å implementere programkode 5.2.3 a), som utfører metoden leggInn(T verdi). _Verdi_ skal legges
inn. Rotnoden er start, hvis _verdi_ er mindre enn sjekket nodeverdi, går vi til venstre - ellers til høyre. Gjentas
så lenge det er noder å sjekke, helt til det stopper (befinner oss utenfor treet). Her er det ingen node, og det skal
der opprettes en ny (_verdi_ er verdien til ny node). Denne noden blir barnet til den sist passerte noden: 
**venstre barn** hvis siste sammenligning av noder var mindre enn, ellers **høyre barn.** 


* Oppgave 2: Løst ved å implementere samme kode og løsning til oppgave 2, seksjon 5.2.6 i kompendiet. Oppretter ny node
**p** som settes som rot (starter søket her), og en hjelpevariabel antallForekomster (skal telle antall ganger _verdi_ finnes). Så lenge 
roten ikke er null, skal _verdi_ og **p.verdi** sammenlignes, hvorpå sammenlignet(cmp) < 0 --> gå til venstre, ellers
høyre. Hvis sammenlignet (cmp) == 0 (søkt _verdi_ er lik p.verdi) skal antallForekomster plusses med 1, og p
settes til p.høyre. Søket fortsetter til alle forekomser av _verdi_ er funnet. 


* Oppgave 3: **Førstepostorden**: Løst ved å se og implementere nesten lik kode som i programkode 5.1.7 h) i kompendiet.
Koden finner første postorden ved å finne frem til den første noden i treet hvor det ikke er mulig å gå til venstre
eller høyre. Metoden tar inn noden _p_, og while(true) --> p sin venstre node ikke er null, så er p = p sin venstre node.
Ellers er p = p sin høyre node. Ellers skal p returneres.
**nestePostorden**: Tar inn noden _p_, hvis p ikke har forelder skal null returneres (p er da den siste i postorden).
Ellers, hvis p er sin forelder sitt høyre barn eller sistnevnte er null = p sin forelder returneres. Ellers returneres
 første postorden av p.forelder. høyre (ved hjelp av metoden **førstePostorden**).


* Oppgave 4: Har tatt utgangspunkt i kildekode og informasjon som blir presentert i seksjon 5.1.10 **Traversering uten
rekursjon**. I første while-løkke (linje 271-278) finner vi første node postorden.
Deretter opprettes en ny node, f, og settes som nestePostorden(p).
Hvis f ikke er null --> sjekk om f.høyrebarn er null eller p er f sitt høyrebarn, hvis sant blir p = f - ellers er p f.høyre.
Neste while-løkke sjekker neste postorden med p som rot.
Deretter blir verdiene skrevet ut.

postOrdenRecursive er løst ved å ta utgangspunkt i kildekode 5.1.7 a), hvor det blir utført en _oppgave_ (som her er å 
skrive ut) for hver node som blir besøkt (venstrebarn og høyrebarn).



* Oppgave 5: Første del av oppgaven (serialize) er løst ved å ta utgangspunkt i og implementere deler av koden som André arbeider med i videoen
"uke 09 level order". Det blir opprettet en ArrayList, samt en ArrayDeque. Køen er kom, og legger til rot-noden
først i køen. Implementerer en while-loop som kjører følgende så lenge køen (dequet) ikke er tom:
1. Tar ut det første elementet i køen
2. Legger current sine to barn i køen (så lenge disse ikke er _null_)
3. Verdiene som finnes i køen blir lagt til i ArrayListen (listeMedNoder)
4. ArrayListen returneres 

Koden i deserialize er hentet fra kompendiet, seksjon 5.2.3 som tar for seg oppbyggingen av et tre ved å
hente én og én verdi fra en annen type datastrukltur, som her er ArrayList. Tar i bruk programkode 5.2.3 c), hvor
det først blir opprettet en komparator c, deretter bygges treet opp, og treet blir returnert.

* Oppgave 6:
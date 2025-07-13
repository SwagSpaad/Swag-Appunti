----
# Interval scheduling
Abbiamo una collezione di job $j$ che inizia ad un tempo $s_{j}$ e finisce ad un tempo $f_{j}$.
Due intervalli si dicono **compatibili** se non si sovrappongono.

L'obiettivo, dato un insieme di job, è quello di trovare un sottoinsieme di job compatibili tra loro.

![[ASD/MOD II/img/img13.png|center|800]]


## Formalizzazione del problema
**Input**: 
- Un insieme $A$ di $n$ intervalli $I_{1}, \dots, I_{n}$ 
- L'intervallo $I_{i}$ ha tempo di inizio $S_{i}$ e tempo di fine $f_{i}$
**Soluzione accettabile**:
- Un sottoinsieme $S\subseteq A$ che contiene gli intervalli compatibili tra loro.
**Funzione obiettivo** di massimizzazione:
- numero di intervalli compatibili, ovvero la cardinalità di $S$.

## Idea greeedy
Consideriamo i job ordinati secondo diversi ordinamenti:
- **Earliest start time**: considero i job ordinati in modo crescente secondo $s_{j}$ 
- **Earliest finish time**: considero i job ordinati in modo crescente secondo $f_{j}$
- **Shortest interval**: considero i job ordinati in modo crescente secondo $f_{j}-s_{j}$
- **Fewest conflicts**: per ogni job $j$, conto il numero di job non compatibili $c_{j}$ e ordino in modo crescente rispetto $c_{j}$
Aggiungiamo ogni job solo se compatibile con quelli già inseriti.

![[ASD/MOD II/img/img14.png|center|800]]

Ordinando per *finish time* otteniamo un algoritmo che restituisce sempre la soluzione ottima. 

![[ASD/MOD II/img/img15.png|center|700]]

![[img22.gif|center|900]]

L'algoritmo si implementa in tempo $O(n\log n)$, perché:
- ordiniamo i job in tempo $O(n\log n)$ 
- teniamo traccia dell'ultimo job $j^{*}$ aggiunto ad $S$
- il job $j$ è compatibile con $S\iff s_{j}\geq f_{j^{*}}$ 
## Analisi correttezza dell'algoritmo
Sia $i_{1}, i_{2},\dots, i_{k}$ il sottoinsieme di job scelti dall'algoritmo greedy.
Sia $j_{1},j_{2},\dots, j_{m}$ il sottoinsieme di job della soluzione ottima. 

Poniamo $f(i_{r})$ il *finish time* del job $i_{r}$.

**Lemma**
Per ogni $r=1, 2,\dots,  k$ vale $f(i_{r})\leq f(j_{r})$. ^09216b

Questo significa che per ogni $r\geq 1$, l'r-esimo job scelto dall'algoritmo greedy, termina non oltre l'r-esimo job scelto dalla soluzione ottima.

**Dim.** per induzione
- $r = 1$. Ovvio, l'algoritmo sceglie la richiesta $i_1$ che ha il *finish time* minimo
- $r>1$. Assumiamo l'ipotesi induttiva fino all'indice $r-1$, quindi $f(i_{r-1})\leq f(j_{r-1})$. Siccome la soluzione ottima contiene solo intervalli compatibili, vale che $f(j_{r-1})\leq s(j_{r})$. Dall'ipotesi induttiva segue che $f(i_{r-1})\leq f(j_{r-1})\leq s(j_{r})$. Questo significa che l'intervallo $j_{r}$ è ancora disponibile quando l'algoritmo greedy sceglie $i_{r}$ e poiché l'algoritmo sceglie sempre l'intervallo col finish time minore e $j_{r}$ è un'opzione valida, deve valere che $f(i_{r})\leq f(j_{r})$

![[ASD/MOD II/img/img23.png|center|700]]

**Th.** L'algoritmo earliest-finish-time-first è ottimale.

**Dim.** per assurdo
Sia $i_{1}, i_{2},\dots, i_{k}$ il sottoinsieme di job scelti dall'algoritmo greedy.
Sia $j_{1},j_{2},\dots, j_{m}$ il sottoinsieme di job della soluzione ottima. 
Assumiamo che l'algoritmo greedy non sia ottimale, quindi vale che $m>k$.

![[ASD/MOD II/img/img24.png|center|700]]

Ma se $m>k$, allora significa che esiste un altro job, detto $j_{k+1}$, nella soluzione ottimale. Applicando il [[#^09216b|lemma]] con $r=k$ abbiamo che $f(i_{k})\leq f(j_{k})$. Dal momento che nella soluzione ottimale abbiamo il job $j_{k+1}$ che inizia dopo che il job $j_{k}$ è terminato, e di conseguenza anche dopo che $i_{k}$ è terminato, allora questo vuol il job $j_{k+1}$ deve essere scelto dall'algoritmo greedy, il che è assurdo in quanto abbiamo assunto che il numero di job scelti dall'algoritmo è $k$.
# Interval partitioning
Problema simile all'interval scheduling. Supponiamo di avere una collezione di lezioni che devono essere schedulate in una classe in un certo orario. Il problema è quello di minimizzare il numero di classi aperti per ospitare tutte le lezioni.

![[ASD/MOD II/img/img16.png]]

![[ASD/MOD II/img/img17.png]]
## Formalizzazione del problema
**Input**: 
- Un insieme $S$ di $n$ lezioni $L_{1}, \dots, L_{n}$ 
- La lezione $L_{i}$ ha tempo di inizio $S_{i}$ e tempo di fine $f_{i}$
**Soluzione accettabile**:
- Partizione degli intervalli in sottoinsiemi $C_{1}, \dots, C_{d}$ tale che ogni insieme $C_{i}$ contenga degli intervalli compatibili
**Funzione obiettivo** di minimizzazione:
- numero di classi

## Idea greedy
Consideriamo le lezioni ordinate secondo qualche riferimento. Assegnamo ogni lezione ad una classe disponibile. Se non ci sono classi disponibili ne apriamo una nuova.

- **Earliest start time**: considero le lezioni ordinate in modo crescente secondo $s_{j}$ 
- **Earliest finish time**: considero le lezioni ordinate in modo crescente secondo $f_{j}$
- **Shortest interval**: considero le lezioni ordinate in modo crescente secondo $f_{j}-s_{j}$
- **Fewest conflicts**: per ogni lezione $j$, conto il numero di lezioni in conflitto $c_{j}$ e ordino in modo crescente rispetto $c_{j}$

![[ASD/MOD II/img/img18.png|center|500]]

## Algoritmo greedy
Ordiniamo le lezioni rispetto allo *starting time*:
- assegnamo la lezione ad una classe compatibile (se esiste)
- altrimenti apriamo una nuova classe.

![[ASD/MOD II/img/img19.png|center|800]]

![[ASD/MOD II/img/img20.png|center|800]]

![[img21.gif|center|900]]

L'algoritmo greedy può essere implementato in tempo $O(n\log n)$.

**Dim.**
- Ordinare per starting time impiega $O(n\log n)$ 
- Salviamo le classsi in una **coda con priorità** (key = finish time dell'ulltima lezione)
	- per creare una nuova classe eseguiamo l'*Insert* nella coda
	- Per schedulare una lezione $j$ nella classe $k$, eseguiamo l'operazione *Increase-key* della classe $k$ con $f_{j}$
	- Per vedere se la lezione $j$ è compatibile con una classe, eseguiamo l'operazione *Find-min* e poi confrontiamo il valore $s_{j}$ con il valore di *Find-min*
- Il numero totale di operazioni è $O(n)$, ciascuna con costo $O(\log n)$
## Analisi correttezza dell'algoritmo

**Def.**
La *depth* di un insieme di intervalli è il numero massimo di intervalli che contengono uno stesso punto.

Possiamo utilizzare la depth per dare un lower-bound al problema, infatti se la depth è 3, dobbiamo aprire almeno 3 classi.

![[ASD/MOD II/img/img25.png|center|700]]

**Oss.**
Il numero di classi necessarie deve essere almeno la depth.

*Th.* L'algoritmo greedy è ottimo

**Dim.**
Sia $d$ il numero di classi che l'algoritmo greedy apre alla fine della sua esecuzione.
Se l'algoritmo apre una classe $d$ vuol dire che una lezione $j$ è incompatibile con tutte le altre $d-1$ classi. Pertanto, ciascuna di queste $d$ lezioni finisce oltre $s_{j}$. Dal momento che abbiamo ordinato per *starting time*, ognuna di queste lezioni incompatibili inizia entro e non oltre $s_{j}$. Per questo abbiamo $d$ lezioni che si sovrappongono ad un tempo $s_{j} + \epsilon$. Dall'osservazione precedente abbiamo che ogni schedulazione utilizza almeno $d$ classi.


----

Analizziamo ora un secondo problema sui grafi: il minimo albero ricoprente (Minimum [Spanning Tree](https://it.wikipedia.org/wiki/Albero_ricoprente)).

Supponiamo di avere un insieme di nodi $V = \{v_{1},\: v_{2},\: \dots,\: v_{n}\}$ e vogliamo costruire un network di comunicazione tra di loro. 
I nodi di questo network dovranno essere tutti connessi tra loro, ma il requisito fondamentale è quello di **volerlo costruire il più economico possibile**.
Tra certe coppie di nodi ($v_i,\: v_j)$ possiamo costruire un link diretto per un determinato costo $c(v_i,\: v_j)>0$. Possiamo quindi rappresentare l'insieme dei possibili collegamenti che possono essere costruiti utilizzando un grafo G = (V, E) con un costo $c_e$ associato ad ogni arco $e = (v_{i},\: v_j)$.
Il problema è quello di trovare un sottoinsieme $T \subseteq E$ degli archi in modo che il grafo $G' = (V, T)$ sia connesso e il costo totale $\sum_{e\in T}\: c_e$  sia più piccolo possibile.
Chiameremo $T\subseteq E$ uno **spanning tree** di G se (V, T) è un albero.

# Minimum Spanning Tree Problem

Il nostro problema di realizzazione di un network può essere quindi riformulato come il problema di trovare lo spanning tree più economico di un grafo (**Minimum Spanning Tree Problem**).
A meno che G sia un grafo molto semplice, avrà un numero di alberi ricoprenti diversi elevato, per questo non è facile trovare l'albero più economico tra tutte le opzioni

>[!note]- **Def.** Minimum Spanning Tree
>Dato un grafo connesso G = (V, E) con pesi reali per gli archi $c_e$, un MST è un sottoinsieme $T \subseteq E$ degli archi di G tale che T è uno spanning tree la cui somma dei pesi degli archi è minima

![[ASD/MOD II/img/img5.png|center|500]]

>[!note]- Teorema di Cayley
>Ci sono $n^{n-2}$ spanning tree di $K_n$, dove $K_n$ è un grafo con n nodi.
>

Il problema del MST trova diverse applicazioni ad esempio:
- design di reti telefoniche, idrauliche, stradali ecc. 
- algoritmi di approssimazione per problemi non polinomiali (Steiner Tree, problema del venditore ambulante)
- cluster analysis

## Algoritmi

Vediamo ora tre algoritmi greedy, ognuno dei quali trova un MST.
- **[[#Algoritmo di Kruskal|Algoritmo di Kruskal]]**: l'idea è quella di iniziare con l'insieme $T = \emptyset$. Consideriamo gli archi in ordine crescente di costo. Inseriamo l'arco e in T solamente se non crea un ciclo.
- **Algoritmo Reverse-Delete**: l'idea è opposta all'algoritmo di Kruskal. Iniziamo con $T = E$. Consideriamo gli archi in ordine decrescente di costo, in seguito eliminiamo gli archi e da T solamente se non scollega T
- **[[#Algoritmo di Prim|Algoritmo di Prim]]**: iniziamo da un nodo sorgente s e genera un albero T da s (visitando il grafo). Ad ogni passo, aggiungi l'arco e di costo minore a T che ha esattamente un nodo.

### Analisi
Tutti questi algoritmi inseriscono o eliminano archi dalla soluzione parziale, per questo dobbiamo capire quando è "sicuro" aggiungere o rimuovere un arco dal MST. 
Per l'analisi, assumiamo che i costi di ogni arco siano diversi tra loro. Questo ci renderà più facile l'espressione del prossimo argomento, ma vedremo come possiamo eleminare in seguito quest'assunzione.

Vediamo ora le due proprietà che ci permettono di decidere quando aggiungere o rimuovere un arco dallo spanning tree:
- Cut property (Taglio): Sia S un sottoinsieme di nodi non vuoto e diverso da V e sia $e = (v,\: w)$ l'arco con il costo minimo con esattamente un nodo in S e l'altro in V-S. Allora il MST contiene questo arco. ^3763de

![[ASD/MOD II/img/img6.png|center|400]]

- Cycle property (Cicli): Sia C un qualsiasi ciclo in G e sia $e=(v,\: w)$ l'arco più costoso nel ciclo C. Allora $e$ non appartiene al MST. ^b89f60

![[ASD/MOD II/img/img7.png|center|400]]

**Dim.** cut property
Dimostriamo per assurdo che se $e$ non appartiene al MST $T^*$, allora esso non ha il costo minimo.
Supponiamo quindi che $e$ non appartiene a $T^*$. Aggiungendo $e$ a $T^*$, si crea un ciclo C in $T^*$. L'arco $e$ è sia nel ciclo C che nel cutset D corrispondente ad S, allora esisterà un altro arco, che indichiamo con f, anch'esso sia in C che in D.
Consideriamo ora $T' = T^{*}\cup\{e\}-\{f\}$ che è anch'esso uno spanning tree, ma dal momento che $c_e<c_f$, allora $cost(T')<cost(T^*)$, il che è una contraddizione.

**Dim.** cycle property
Supponiamo che $f$ appartenga a $T^*$. Eliminando $f$ da $T$ si crea un taglio $S$ in $T^*$. L'arco f allora è sia nel ciclo C che nel cutset D di S, allora esiste un altro arco, che indichiamo con $e$, che sarà sia in C che in D. Consideriamo ora $T' = T^{*}\cup\{e\}-\{f\}$ che è anch'esso uno spanning tree, ma dal momento che $c_e<c_f$ allora $cost(T')<cost(T^*)$, il che è una contraddizione.

### Algoritmo di Prim
#### Implementazione
Utilizziamo una coda con priorità come con Dijkstra e creiamo un insieme $S$ che conterrà i nodi esplorati. Analogamente a Dijkstra dobbiamo decidere come scegliere il prossimo nodo da aggiungere il prossimo nodo $v$ da aggiungere ad $S$. Per ogni nodo inseplorato salviamo il costo di collegamento $a(v)$, che rappresenta il costo minimo di un arco $v$ verso un nodo in $S$. 

![[ASD/MOD II/img/img8.png|center|500]]

#### Correttezza
Inizializziamo S = qualsiasi nodo (taglio). Applichiamo la cut property ad $S$, aggiungiamo il costo minimo nel cutset $D(S)$ a $T$ e aggiungiamo un nuovo nodo esplorato $u$ ad $S$.
Notiamo che ad ogni step, un nuovo nodo **inesplorato** viene inserito in S. Quindi ad ogni passo del while, $|S|$ aumenta di 1: $S_0=\{u_{1}\},\: \dots,\: S_t=\{u_{1},\:\dots,\: u_{t}\}, \:\dots,\: S_{n-1}=V$ .

#### Complessità
La complessità varia in base alla struttura dati scelta: 
- $O(n^{2})$ utilizzando un array
- $O(m\log(n))$ utilizzando un heap binario

Per ogni nodo visitato $u\in V$, aggiorna $O(deg(u))$ chiavi in Q $\implies$ $\sum_{u} deg(u) = O(m)$. Ogni aggiornamento ha costo $O(\log(n))$ utilizzando gli heap, da qui $O(m\log(n))$. 

### Algoritmo di Kruskal
Consideriamo gli archi in ordine crescente di peso. Ci sono 2 casi ora: 
- Caso 1: se aggiungendo l'arco $e$ a T si crea un **ciclo**, scartiamo $e$ in relazione a quello che ci dice la [[#^b89f60|cycle property]]

![[ASD/MOD II/img/img9.png|center|300]]

- Caso 2: aggiungi l'arco $e=(u,\: v)$ in T per la [[#^3763de|cut property]], dove S è l'insieme dei nodi con le componenti di $u$ connesse.

![[ASD/MOD II/img/img10.png|center|300]]

#### Implementazione 
L'implementazione dell'algoritmo di Kruskal usa la stuttura union-find.
Costruisce un insieme T di archi nel MST e mantiene un insieme per ogni nodo connesso.
$O(m\log(n))$ per il sorting dei costi in ordine crescente e $O(m\cdot \alpha(m,\: n))$ per la union-find.

![[ASD/MOD II/img/img11.png|center|500]]


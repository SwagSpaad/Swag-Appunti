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
>Dato un grafo connesso G = (V, E) con pesi $c_e$ per gli archi, un MST è un sottoinsieme $T \subseteq E$ degli archi di G tale che T è uno spanning tree la cui somma dei pesi degli archi è minima

![[ASD/MOD II/img/img5.png|center|700]]

>[!note]- Teorema di Cayley
>Ci sono $n^{n-2}$ spanning tree di $K_n$, dove $K_n$ è un grafo con n nodi.
>

Il problema del MST trova diverse applicazioni ad esempio:
- design di reti telefoniche, idrauliche, stradali ecc. 
- algoritmi di approssimazione per problemi non polinomiali (Steiner Tree, problema del venditore ambulante)
- cluster analysis

## Formalizzazione del problema
**Input**:
- Un grafo pesato connesso $G=(V,E)$ con peso per ogni arco detto $c_{e}$
**Soluzione accettabile**
- uno spanning tree $T$ di $G$
**Funzione obiettivo** di minimizzazione
- costo dello spanning tree $T$ ovvero, $c(T)=\sum\limits_{e\in T}c_{e}$ 
### Unicità del MST
In generale l'MST di un grafo non è unico, infatti

![[ASD/MOD II/img/img32.png|center|700]]

**Proprietà**
Se $G$ ha pesi distinti, allora il MST è unico.

**Dim.** per esercizio
## Algoritmi

Vediamo ora tre algoritmi greedy, ognuno dei quali trova un MST.
- **[[#Algoritmo di Kruskal|Algoritmo di Kruskal]]**: l'idea è quella di iniziare con l'insieme $T = \emptyset$. Consideriamo gli archi in ordine crescente di costo. Inseriamo l'arco e in T solamente se non crea un ciclo.
- **Algoritmo Reverse-Delete**: l'idea è opposta all'algoritmo di Kruskal. Iniziamo con $T = E$. Consideriamo gli archi in ordine decrescente di costo, in seguito eliminiamo gli archi $e$ da T solamente se non scollega T
- **[[#Algoritmo di Prim|Algoritmo di Prim]]**: iniziamo da un nodo sorgente $s$ e genera un albero T da s (visitando il grafo). Ad ogni passo, aggiungi l'arco di costo minore $e$ a T che ha esattamente un nodo in T.

### Cicli e tagli
Tutti questi algoritmi inseriscono o eliminano archi dalla soluzione parziale, per questo dobbiamo capire quando è "sicuro" aggiungere o rimuovere un arco dal MST. 

Un **ciclo** è un insieme di archi del tipo $a-b, b-c, \dots, z-a$.

![[ASD/MOD II/img/img34.png|center|800]]

Un **taglio** (cut) è un sottoinsieme $S\subseteq V$ dei nodi del grafo. Il **cutset** $D$ del taglio $S$, è il sottoinsieme degli archi del grafo con esattamente un endpoint in $S$.

![[ASD/MOD II/img/img33.png|center|800]]

**Propietà**
L'intersezione tra un ciclo e un cutset risulta sempre in un numero pari di archi.

**Dim.**
![[ASD/MOD II/img/img35.png|center|700]]

Definiti i tagli e i cicli, vediamo le due proprietà che ci permettono di scegliere quando aggiungere o rimuovere un arco dallo spanning tree.
- *Cut property*: Sia S un sottoinsieme di nodi non vuoto e diverso da $V$ e sia $e = (v,\: w)$ l'arco con il costo minimo con esattamente un nodo in $S$ e l'altro in $V-S$. Allora esiste un MST che contiene questo arco. ^3763de

![[ASD/MOD II/img/img6.png|center|400]]
**Dim.** cut property
Sia $T^{*}$ un MST ed $e$ (dalla proprietà) un arco di costo minimo.
Supponiamo che $e$ non appartenga a $T^*$. Aggiungendo $e$ a $T^*$, si crea un ciclo C in $T^*$. L'arco $e$ è sia nel ciclo C che nel cutset D corrispondente ad S, allora esisterà un altro arco, che indichiamo con $f$, anch'esso sia in C che in D.
Consideriamo ora $T' = T^{*}\cup\{e\}-\{f\}$ che è anch'esso uno spanning tree, ma dal momento che $c_e<c_f$, allora $cost(T')<cost(T^*)$, il che è una contraddizione.

- *Cycle property*: Sia C un qualsiasi ciclo in G e sia $f=(v,\: w)$ l'arco più costoso nel ciclo C. Allora esiste un MST che non contiene $f$. ^b89f60

![[ASD/MOD II/img/img7.png|center|400]]


**Dim.** cycle property
Sia $T^{*}$ un MST ed $f$ (dalla proprietà) un arco di costo massimo appartenente al ciclo $C$.
Supponiamo che $f$ appartenga a $T^*$. Eliminando $f$ da $T^*$ si crea un taglio $S$ in $T^*$. L'arco $f$ allora è sia nel ciclo C che nel cutset D di S, allora esiste un altro arco, che indichiamo con $e$, che sarà sia in C che in D. Consideriamo ora $T' = T^{*}\cup\{e\}-\{f\}$ che è anch'esso uno spanning tree, ma dal momento che $c_e<c_f$ allora $cost(T')<cost(T^*)$, quindi esiste un MST $T'$ che non contiene $f$.

### Algoritmo di Kruskal
Iniziamo con $T=\emptyset$. Consideriamo gli archi in ordine crescente di costo. Inseriamo l'arco $e$ in $T$ solamente se non crea un ciclo.

Consideriamo gli archi in ordine crescente di peso. Ci sono 2 casi ora: 
- Caso 1: se aggiungendo l'arco $e$ a T si crea un **ciclo**, scartiamo $e$ in relazione a quello che ci dice la [[#^b89f60|cycle property]]

![[ASD/MOD II/img/img9.png|center|300]]

- Caso 2: aggiungi l'arco $e=(u,\: v)$ in T per la [[#^3763de|cut property]], dove S è l'insieme dei nodi con le componenti di $u$ connesse.

![[ASD/MOD II/img/img10.png|center|300]]

L'implementazione dell'algoritmo di Kruskal usa la stuttura [[Lezione 2 - Union-Find|Union-Find]] per:
- mantenere le componenti connesse della soluzione
- controllare se l'arco che si sta scegliendo forma un ciclo nella soluzione.

![[ASD/MOD II/img/img36.png|center|600]]

![[img37.gif|center|700]]

#### Correttezza
Quando l'algoritmo decide di aggiungere l'arco $(x,y)$ alla soluzione, dal momento che considera gli archi in ordine crescente di costo, allora l'arco che aggiunge è quello di costo minimo nel taglio. 

![[ASD/MOD II/img/img38.png|center|700]]

Quando invece decide di rimuovere l'arco $(x,y)$, siccome gli archi sono in ordine crescente di costo, allora l'arco considerato è quello di costo massimo che forma un ciclo nella soluzione. 

![[ASD/MOD II/img/img39.png|center|700]]

#### Costo temporale
Per il ordinare in modo crescente gli archi utilizziamo un algoritmo di sorting che costa $O(m\log n)$. Eseguiremo poi: 
- $n$ operazioni di makeSet ($n$ è il numero di nodi)
- $n-1$ operazioni di union
- $m$ operazioni di find ($m$ è il numero di archi)

Il costo complessivo è il costo del sorting sommato al costo delle operazioni Union-Find $$O(m\log n + UF(m,n))$$
Se utilizziamo la [[Lezione 2 - Union-Find#Alberi QuickFind|Quick find]] con euristica *union by size* il costo risulterebbe $$O(m\log n+m+n\log n)=O(m\log n)$$
Utilizzando la [[Lezione 2 - Union-Find#Alberi QuickUnion|quick union]] con euristica *union by size* il costo risulta $$O(m\log n+m\log n+n)=O(m\log n)$$
quindi il costo dell'algoritmo di Kruskal è $O(m\log n)$.
### Algoritmo di Prim
Partendo da un noto sorgente $s$, ad ogni iterazione si aggiunge all'MST l'arco di costo minore dal taglio attuale (alla prima iterazione contiene solo $s$), inserendo l'altra estremità dell'arco al taglio $S$.

La complessità di una semplice soluzione risulta inefficiente, infatti, per n-1 volte, viene ricercato l'arco che attraversa il cut in tempo $O(m)$, per cui il tempo totale di esecuzione risulta $O(mn)$, che risulta lento per $m$ grande.
#### Implementazione
Un'implementazione più efficace è quella di:
- mantenere un insieme S di nodi esplorati
- utilizzare una coda con priorità per mantenere i nodi inesplorati
- per ogni nodo inesplorato $v$, definiamo la *priorità* come il costo minimo dell'arco che da $v$ si collega ad un nodo all'interno del cut.

![[ASD/MOD II/img/img40.png|center|800]]

#### Correttezza
Inizializziamo S = qualsiasi nodo (taglio). Applichiamo la cut property ad $S$, aggiungiamo il costo minimo nel cutset $D(S)$ a $T$ e aggiungiamo un nuovo nodo esplorato $u$ ad $S$.
Notiamo che ad ogni step, un nuovo nodo **inesplorato** viene inserito in S. Quindi ad ogni passo del while, $|S|$ aumenta di 1: $S_0=\{u_{1}\},\: \dots,\: S_t=\{u_{1},\:\dots,\: u_{t}\}, \:\dots,\: S_{n-1}=V$ .

#### Complessità
L'algoritmo ha complessità $O(m+n)$ sommato al costo delle operazioni sulla coda con priorità, ovvero $n$ insert, $n$ deleteMin ed $m$ decreaseKey.
In base alla struttura dati scelta, abbiamo costo: 
- $O(n^{2})$ utilizzando un array
- $O(m\log(n))$ utilizzando un heap binario
- $O(m+\log n)$ con un heap di Fibonacci


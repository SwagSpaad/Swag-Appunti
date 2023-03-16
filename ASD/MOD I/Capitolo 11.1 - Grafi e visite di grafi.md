----
# Grafi, teoria dei grafi e problemi

## Origini storiche

Nel 1736, il matematico Eulero, affrontò il problema dei 7 ponti di Königsberg (Prussia):

![[img137.png|center|300]]

È possibile o meno fare una passeggiata che parta da un **qualsiasi** punto della città e percorra **una ed una** sola volta ciascuno dei 7 ponti?
Eulero affrontò il problema schematizzando **topologicamente** la pianta della città, pulendo così l'istanza da insignificanti dettagli **topografici**:

![[img138.png|center|300]]

... e così Königsberg venne rappresentata con un insieme di **4 punti** (uno per ciascuna zona della città), opportunamente uniti da **7 linee** (una per ciascun ponte)

## Grafi

**Def.**
Un **grafo G = (V, E)** consiste in:
- Un insieme **V** di **vertici** (o nodi)
- Un insieme **E** di coppie (non ordinate) di vertici, detti **archi**. Ogni arco connette due vertici

**Esempio**
Grafo di Eulero associato alla città di Königsberg:
- $V=\{A,B,C,D\}$
- $E=\{(A,B),(A,B),(A,D),(B,C),(B,C),(B,D),(B,D),(C,D)\}$

![[img139.png|center|300]]

>[!important] Nota
>Quello in figura è più propriamente detto **multigrafo**, in quanto contiene **archi paralleli**

### Grafo diretto
**Def.**
Un **grafo diretto D = (V, A)** consiste in:
- Un insieme **V** di **vertici** (o **nodi**)
- Un insieme **A** di coppie **ordinate** di vertici, detti **archi diretti**

![[img140.png|center|500]]

### Terminologia
(Riferita all'immagine sotto)
- **G = (V, E)** grafo non diretto
- **n** = |V| numero di vertici
- **m** = |E| numero di archi
- **u** e **v** sono **adiacenti**
- **(u, v)** è **incidente** a **u** e a **v** (detti **estremi**)
- $\delta(u)$: **grado** di **u** (numero archi incidenti a **u**)
	- **grado** di **G** = $max_{v\in V}\{\delta(v)\}$

![[img141.png|center|200]]

### Che relazione c'è fra grado dei nodi e numero di archi?
### Una semplice proprietà (grafi non diretti)
Cosa ottengo se sommo i gradi di ogni nodo?

![[img142.png|center|400]]
$$\sum_{v\in V}\delta(v)=2m$$
**Oss.**
m rappresenta il numero di archi del grafo!

In ogni grafo il numero di nodi di grado dispari è pari

### Una semplice proprietà (grafi diretti)
Cosa ottengo se sommo il grado **uscente/entrante** di tutti i nodi?

![[img143.png|center|400]]
$$\sum_{v\in V}\delta_{out}(v)=\sum_{v\in V}\delta_{in}(v)=m$$


### Altra Terminologia

- **_cammino_**: sequenza di nodi connessi da archi (rosso in figura)
- **_lunghezza_** di un cammino: numero di archi del cammino (5 nel cammino rosso in figura)
- **_distanza_**: la lunghezza del più corto cammino tra due vertici si dice **_distanza_** tra due vertici (4 in figura; il cammino è L-I-E-B-A)

![[img144.png|center|150]]

**Oss.**
In un grafo **orientato**, il cammino deve rispettare il verso di orientamento degli archi
	
- G è **connesso** se esiste un cammino per ogni coppia di vertici
- **ciclo**: un cammino chiuso, ovvero un cammino da un vertice a se stesso
- **diametro**: massima distanza fra due nodi
	- $max_{u.v\in V}dist(u,v)$
	- il diametro di un grafo non connesso è $\infty$

![[img145.png|center|150]]

![[img146.png|center|400]]

- **Grafo pesato**: è un grafo **G = (V, E, w)** in cui ad ogni arco viene associato un valore definito dalla funzione peso w (definita su un opportuno insieme, di solito i reali)

![[img147.png|center|400]]
![[img148.png|center|400]]

### Quanti archi può avere un grafo di n nodi?

Due grafi molto particolari
- **Grafo totalmente sconnesso**: è un grafo G = (V, E) tale che $V\neq\emptyset$ ed $E=\emptyset$ 

![[img149.png|center|250]]

- **Grafo completo**: per ogni coppia di nodi esiste un arco che li congiunge. Il grafo completo con n vertici verrà indicato con $K_n$
![[img150.png|center|250]]

Vale, infatti:
$$m=|E|=n(n-1)/2$$
Il grafo completo in esempio ha $K_n=K_5$

Per rispondere alla domanda quindi:
- un grafo (senza cappi o archi paralleli) può avere un numero di archi m compreso tra $0$ e $n(n-1)/2=\Theta(n^2)$

### Come è fatto un grafo connesso con il minimo numero di archi?

**Def.**
Un albero è un grafo connesso e aciclico

![[img151.png|center|500]]

>[!important]- Teorema
>Sia **T = (V, E)** un albero, allora $|E|=|V|-1$

**Dim (per induzione su |V|)**

**Caso base**: $|V|=1\implies T=\text{un solo nodo}\implies |E|=0=|V|-1$
**Caso induttivo**: $|V|\gt1$. 
Sia n il numero di nodi di T. 
Poichè T è connesso e aciclico ha almeno una foglia (nodo con grado 1)
**Oss**: Se tutti i nodi avessero grado almeno 2 ci sarebbe un ciclo (si vede perchè?)
Rimuovendo tale foglia si ottiene un grafo connesso e aciclico con n-1 nodi che per **ipotesi induttiva** ha n-2 archi. Quindi, T ha n-1 archi

![[img152.png|center|250]]

$\square$ 

### ...Tornando al problema dei 7 ponti

>[!important]- Definizione (Ciclo Euleriano)
>Dato un grafo G, un **ciclo** (rispettivamente un **cammino**) **Euleriano** è un ciclo (rispettivamente un **cammino** non chiuso) di G che passa per tutti gli archi di G una e una sola volta

>[!important]- Teorema (di Eulero)
>Un grafo G ammette un **ciclo Euleriano** se e solo se tutti i nodi hanno grado pari. Inoltre, ammette un **cammino Euleriano** se e solo se tutti i nodi hanno grado pari tranne due (i due nodi di grado dispari sono gli estremi del cammino)

![[img139.png|center|300]]

Il problema dei 7 ponti, quindi, non ammette soluzione

## Perchè i grafi?

I grafi costituscono un linguaggio potente per descrivere oggetti e problemi algoritmici, come ad esempio la mappa di una città, i cui nodi sono rappresentati dagli incroci e gli archi, che collegano gli incroci, sono rappresentati dalle strade.

![[img153.png|center|500]]

**Problema**: trovare il cammino minimo fra due nodi

![[img154.png|center|500]]

Due soluzioni, in base ai pesi scelti (lunghezza o tempo di percorrenza):
- Nel caso in cui il peso dell'arco scelto è la lunghezza allora si ottiene la strada più corta
- Nel caso in cui il peso dell'arco scelto è il tempo di percorrenza allora si ottiene il percorso più veloce

Un altro esempio di utilizzo dei grafi potrebbe essere facebook, in cui rappresentiamo ogni utente con un nodo e due nodi sono collegati da un arco se i due utenti sono amici

**Kevin Bacon Number**: Distanza dal nodo Kevin Bacon

![[img155.png|center|400]]

![[img156.png|center|400]]

### Reti "delle dipendenze"

![[img157.png|center|400]]

**Nodi**: compiti da svolgere
**Arco (u, v)**: u deve essere eseguito prima di v

**Esempi**:
- Esami e propedeucità
- Moduli software di un progetto e dipendenze

**Problema**: trovare un ordine in cuie seguire i compiti in modo da rispettare le dipendenze

![[img158.png|center|500]]

Un altro esempio

![[img159.png|center|300]]

**Nodi**: compiti da svolgere
**Arco (u, v)**: u e v **NON** possono essere svolti insieme

Esempi:
- Esami e vincoli
- certi esami non possono essere svolti lo stesso giorno (stesso anno, usano la stessa aula, ecc...)

**Problemi**:

1. Trovare max numero di compiti eseguibili, ovvero trovare il **massimo insieme indipendente**. Coinsiste nel trovare l'insieme X di nodi di cardinalità massima, tale che per ogni u, v in X, u e v non sono adiacenti
2. Trovare min numero di "gruppi" di compiti, tale che i compiti dello stesso gruppo possono essere eseguiti insieme, ovvero le colorazioni di un grafo. Coinsiste nel colorare i nodi del grafo usando il minimo numero $\chi$ di colori in modo che due nodi adiacenti non abbiano lo stesso colore

**Esempio**

![[img160.png|center|300]]

Vale che : $\chi(G)=3$ 

Possiamo usare 2 colori? ..no: ogni ciclo da tre (triangolo) ha bisogno di almeno tre colori


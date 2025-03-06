----
Abbiamo motivato la costruzione di un Minimum Spanning Tree attraverso il problema di trovare un network low-cost per connettere un insieme di siti diversi. 
I MST si presentano in una gamma di situazioni diverse, molte delle quali sembrano superficialmente diverse l'una dall'altra.
Un altro esempio è il ruolo che i MST svolgono nel caso del **clustering**.

# Il problema
Il clustering si riconduce ad una collezione di oggetti, come un insieme di documenti o di foto, che si vuole organizzare in gruppi coerenti. Di fronte ad una situazione simile è naturale osservare quanto ogni coppia di oggetti sia diversa l'una dall'altra.
Un approccio comune è quello di definire una "funzione di distanza" tra gli oggetti, ovvero, al crescere della distanza gli oggetti sono meno simili tra loro. Ad esempio la distanza fisica o, in modo più astratto, possiamo definire la distanza tra due specie in base al numero di anni trascorsi da quando si sono separati nel corso dell'evoluzione.

In generale, data una funzione di distanza sugli oggetti, il problema del clustering cerca di dividerli in gruppi in modo che, intuitivamente, gli oggetti 

## Clustering of maximum spacing
Supponiamo di avere un insieme U di $n$ oggetti indicati con $p_1, \: \dots, \: p_n$. Per ogni coppia $p_i$ e $p_j$ abbiamo una distanza numerica $d(p_i,\: p_j)$. Questa distanza rispetta queste proprietà:
- $d(p_i,\: p_i)=0$
- $d(p_i,\: p_j)>0$, per $p_i$ e $p_j$ distinti
- $d(p_j,\: p_i)=d(p_i,\: p_j)$
Supponiamo ora di voler dividere gli oggetti di U in $k$ gruppi, dato un parametro $k$. 
Diremo che un k-clustering di U è una partizione di U in k insiemi non vuoti $C_1,\: C_2,\:\dots,\: C_k$.
Definiamo lo spacing di un k-clustering come la minima distanza tra due coppie di punti in cluster differenti. Dato che vogliamo avere i punti in cluster differenti distanti tra di loro, un obiettivo naturale è quello di cercare il k-clustering con lo spacing massimo.

![[ASD/MOD II/img/img12.png|center|500]]

La domanda sorge spontanea, dato che in un insieme U ci sono esponenzialmente k-cluster differenti, come possiamo trovare efficientemente quello con il maximum spacing?

## Progettiamo l'algoritmo
Per trovare un **clustering of maximum spacing**, consideriamo la crescita di un grafo sull'insieme di vertici di U. Le componenti connesse saranno i cluster e cercheremo di riunire i punti vicini nello stesso cluster nel modo più rapido possibile, in modo che non finiscano in cluster diversi molto vicini tra loro. 
Iniziamo disegnando un arco tra i due nodi più vicini e continuiamo così in base alla distanza dei nodi crescente. In questo modo stiamo generando un grafo H sui nodi di U con i nodi connessi corrispondenti ai cluster. 

>**Oss.**
>Siamo solo interessati alle componenti connesse del grafo H e non all'insieme completo di archi. Se stiamo aggiungendo l'arco $(p_{i},\: p_{j})$, ma $p_{i}$ e $p_{j}$ già appartengono allo stesso cluster, evitiamo di aggiungere l'arco. In questo modo non saranno presenti cicli, infatti il grafo H altro non è che un unione di alberi.

Ogni volta che aggiungiamo un arco tra due componenti distinte, è come se stessimo unendo i due cluster.

### Cosa centrano i [[Lezione 2 - Minimum Spanning Tree|MST]]?
La procedura di costruzione del grafo è la stessa dell'[[Lezione 2 - Minimum Spanning Tree#Algoritmo di Kruskal|algoritmo di Kruskal]], l'unica differenza è che ci interrompiamo non appena troviamo k componenti connessi. In altre parole, stiamo utilizzando l'algoritmo di Kruskal che si interrompe prima di aggiungere il k-1-esimo arco. 
Questo equivale a calcolare il MST T, eliminare i k-1 archi più costosi e definire il k-clustering come le $C_1,\:C_2,\: \dots, C_k$ componenti connesse.

### Analisi

>**Teo.**
>Indichiamo con $C^*$ il clustering $C_{1}^*,\:\dots,\:C_{k}^*$, ottenuto eliminando i k-1 archi più costosi di un MST.  Allora $C^*$ è un **k-clustering of maximum spacing**.

**Dim.**
Indichiamo con $C$ un altro clustering $C_1,\:\dots,\:C_k$. Lo spacing di $C^*$ è la lunghezza $d^*$ dei (k-1)-esimi archi più costosi. Siano $p$ e $p'$  nello stesso cluster di $C^*$, che chiamiamo $C_r^*$ ma in due differenti cluster in $C$, che chiamiamo $C_s$ e $C_t$.
Un arco $(q, q')$ sul percorso $p \rightarrow p'$ in $C_r^*$, distanzia due diversi cluster in $C$.
Tutti gli archi nel percorso $p \rightarrow p'$ hanno lunghezza $\leq d^*$, dato che sono scelti da Kruskal.
Lo spacing in $C$ è $\leq d^*$ dal momento che $q$ e $q'$ sono in due diversi cluster di $C$.



# Union Find
Il problema Union-find è quello di avere una struttura dati che mantenga una collezione di insiemi disgiunti contenenti elementi distinti, su cui vogliamo poter eseguire operazioni del tipo:
- *makeSet(x)*: crea il nuovo insieme $x=\{x\}$ di nome x
- *union(A, B)*: unisce gli insiemi A e B in un unico insieme, detto A, e distrugge i vecchi insiemi A e B
- *find(x)*: restituisce il nome dell'insieme contenente l'elemento x

![[ASD/MOD II/img/img26.png|center|600]]

L'obiettivo è progettare una struttura dati per implementare le operazioni di union find in modo efficiente.

L'idea è quella di rappresentare gli insiemi disgiunti con una foresta. Ogni insieme è un *albero radicato*, la cui radice contiene il nome dell'insieme.

## Alberi QuickFind
Per rappresentare gli insiemi vengono utilizzati degli alberi di *altezza 1*, la cui radice rappresenta il *nome dell'insieme*, mentre le *foglie sono gli elementi*, incluso anche l'elemento rappresentativo.

![[ASD/MOD II/img/img27.png|center|600]]

Con questa struttura dati la complessità di ogni operazione vale:
- *makeSet(x)*: $O(1)$
- *union(A, B)*: $O(|B|)$ quindi $O(n)$
- find(x): $O(1)$

La find e la makeSet hanno costo costante, ma sequenze di *union* possono essere molto inefficienti. 

![[ASD/MOD II/img/img28.png|center|500]]

In questo caso vengono svolte $\sum\limits_{i=1}^{n}i$ cambi di puntatori, quindi risulterà un costo per tutte le operazioni $\Theta(n^{2})$.

### Union by size
L'idea per migliorare l'operazione di union è quella di eseguire la union spostando i puntatori dell'albero con il numero di elementi minore, cambiando eventualmente la radice dell'albero ottenuto.

![[ASD/MOD II/img/img29.png|center|500]]

In questo caso la union ha costo $O(n)$ nel caso peggiore, ma costo $O(\log n)$ nell'analisi ammortizzata.
Il costo $O(\log n)$ viene fuori considerando quante volte un nodo può cambiare padre. La risposta a questa domanda è *al più $\log n$*, questo perché dalla costruzione della union by size, ogni volta che un nodo cambia padre, la cardinalità dell'insieme al quale appartiene è almeno doppia rispetto a quella precedente: 
- all'inizio un nodo è in un insieme di dimensione 1
- se cambia padre è in un insieme di dimensione almeno 2
- se cambia di nuovo padre (è l'insieme di cardinalità più piccola tra i due insiemi) è in un insieme di dimensione almeno 4
- all'i-esimo cambio è in un insieme di dimensione almeno $2^{i}$ 

Se eseguiamo $n$ makeSet, $m$ find e al più $n-1$ union, il tempo richiesto dall'intera sequenza di operazioni è $O(m+n\log n)$:
- find e makeSet richiedono tempo $\Theta(m+n)$ 
- dall'analisi vista in precedenza il tempo speso per ogni singolo nodo sull'intera sequenza di $n$ union è $O(\log n)$
- l'intera sequenza di operazioni costa quindi $$O(m+n+n\log n)=O(m+n\log n)$$

## Alberi QuickUnion
Per rappresentare gli insiemi vengono utilizzati degli alberi di *altezza anche maggiore di 1*, la cui radice rappresenta il *nome dell'insieme*, mentre i restanti *nodi sono gli elementi*, escluso l'elemento rappresentativo.

![[ASD/MOD II/img/img30.png|center|700]]

Con questa struttura dati la complessità di ogni operazione vale:
- *makeSet(x)*: $O(1)$
- *union(A, B)*: $O(1)$
- *find(x)*: $O(n)$

In questo caso, particolari sequenze di Union possono generare un albero di altezza lineare, per cui la find su quell'albero potrebbe risultare inefficiente (bisogna risalire dall'elemento alla radice) portando ad un costo di $n-1$ nel caso peggiore. 

### Union by size
Anche in questo caso, se applichiamo l'euristica dell'union by size, riusciamo ad ottenere degli alberi di altezza "piccola". 

![[ASD/MOD II/img/img31.png|center|700]]


**Lemma**
Con l'union by size, dato un albero QuickUnion con size $s$ (numero di nodi) e altezza $h$, vale che $s\geq 2^{h}$.

**Dim.** per esercizio

Dal lemma segue che l'operazione di find richiede tempo $O(\log n)$ e nel caso di $n$ makeSet, $n-1$ union e $m$ find, il tempo richiesto per la sequenza di operazioni è $O(m+n\log n)$
### Compressione dei cammini
Un ulteriore euristica si basa sulla compressione dei cammini. L'idea è quella che quando eseguo find(x) e attraverso il cammino da x alla radice, rendo tutti i nodi del cammino figli della radice, andando quindi a comprimere il cammino. Questo mi comporta che la prima find(x) avrà costo lineare nella lunghezza del cammino, ma le prossime find costeranno meno.


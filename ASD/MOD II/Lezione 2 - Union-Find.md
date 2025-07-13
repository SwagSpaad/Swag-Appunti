----
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
----
Abbiamo motivato la costruzione di un Minimum Spanning Tree attraverso il problema di trovare un network low-cost per connettere un insieme di siti diversi. 
I MST si presentano in una gamma di situazioni diverse, molte delle quali sembrano superficialmente diverse l'una dall'altra.
Un altro esempio è il ruolo che i MST svolgono nel caso del **clustering**.

# Il problema
Dato un insieme $U$ di $n$ oggetti detti $p_{1},\dots,p_{n}$, il problema del clustering è quello di raggruppare questi oggetti all'interno di gruppi coerenti.

Ma cosa vuol dire "coerenza"? 

La **funzione distanza** è un valore numerico che specifica la "vicinanza" tra due oggetti. Questa funzione soddisfa le seguenti proprietà:
- $d(p_{i},p_{j})=0\iff p_{i}=p_{j}$ (identità)
- $d(p_{i},p_{j})\geq0$ (non negatività)
- $d(p_{i},p_{j})=d(p_{j},p_{i})$ (simmetria)

Un **k-clustering** è la divisione degli oggetti all'interno di $k$ gruppi non vuoti.
Definiamo lo **spacing** la distanza minima tra due oggetti in cluster differenti.
## Clustering of maximum spacing
Dato quindi un intero $k$ il problema è quello di trovare un k-clustering con spacing massimo. 

![[ASD/MOD II/img/img12.png|center|500]]


## Progettiamo l'algoritmo
Per trovare un **clustering of maximum spacing**, consideriamo la crescita di un grafo sull'insieme di vertici di U. Le componenti connesse saranno i cluster e cercheremo di riunire i punti vicini nello stesso cluster nel modo più rapido possibile, in modo che non finiscano in cluster diversi molto vicini tra loro. 
Iniziamo disegnando un arco tra i due nodi più vicini e continuiamo così in base alla distanza dei nodi crescente. In questo modo stiamo generando un grafo H sui nodi di U con i nodi connessi corrispondenti ai cluster. 

>**Oss.**
>Siamo solo interessati alle componenti connesse del grafo H e non all'insieme completo di archi. Se stiamo aggiungendo l'arco $(p_{i},\: p_{j})$, ma $p_{i}$ e $p_{j}$ già appartengono allo stesso cluster, evitiamo di aggiungere l'arco. In questo modo non saranno presenti cicli, infatti il grafo H altro non è che un unione di alberi.

Ogni volta che aggiungiamo un arco tra due componenti distinte, è come se stessimo unendo i due cluster.

### Cosa centrano i [[Lezione 3 - Minimum Spanning Tree|MST]]?
La procedura di costruzione del grafo è la stessa dell'[[Lezione 3 - Minimum Spanning Tree#Algoritmo di Kruskal|algoritmo di Kruskal]], l'unica differenza è che ci interrompiamo non appena troviamo k componenti connessi. In altre parole, stiamo utilizzando l'algoritmo di Kruskal che si interrompe prima di aggiungere il k-1-esimo arco. 
Questo equivale a calcolare il MST T, eliminare i k-1 archi più costosi e definire il k-clustering come le $C_1,\:C_2,\: \dots, C_k$ componenti connesse.

### Analisi

>**Teo.**
>Indichiamo con $C^*$ il clustering $C_{1}^*,\:\dots,\:C_{k}^*$, ottenuto eliminando i k-1 archi più costosi di un MST.  Allora $C^*$ è un **k-clustering of maximum spacing**.

**Dim.**
Indichiamo con $C$ un altro clustering $C_1,\:\dots,\:C_k$. Lo spacing di $C^*$ è la lunghezza $d^*$ dei (k-1)-esimi archi più costosi. Siano $p$ e $p'$  nello stesso cluster di $C^*$, che chiamiamo $C_r^*$ ma in due differenti cluster in $C$, che chiamiamo $C_s$ e $C_t$.
Un arco $(q, q')$ sul percorso $p \rightarrow p'$ in $C_r^*$, distanzia due diversi cluster in $C$.
Tutti gli archi nel percorso $p \rightarrow p'$ hanno lunghezza $\leq d^*$, dato che sono scelti da Kruskal.
Lo spacing in $C$ è $\leq d^*$ dal momento che $q$ e $q'$ sono in due diversi cluster di $C$.




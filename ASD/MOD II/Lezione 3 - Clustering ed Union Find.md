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




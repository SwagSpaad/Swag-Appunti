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
Supponiamo ora di voler dividere gli oggetti in U in $k$ gruppi, dato un parametro $k$. 
Diremo che un k-clustering di U è una partizione di U in k insiemi non vuoti $C_1,\: C_2,\:\dots,\: C_k$.
Definiamo lo spacing di un k-clustering come la minima distanza tra due coppie di punti in cluster differenti. Dato che vogliamo avere i punti in cluster differenti distanti tra di loro, un obiettivo naturale è quello di cercare il k-clustering con lo spacing massimo.

![[ASD/MOD II/img/img12.png|center|500]]

La domanda sorge spontanea, dato che in un insieme U ci sono esponenzialmente k-cluster differenti, come possiamo trovare efficientemente quello con il maximum spacing?

## Progettiamo l'algoritmo
Per trovare un **clustering of maximum spacing**, consideriamo la crescita di un grafo sull'insieme di vertici di U. I componenti connessi saranno i cluster e cercheremo di riunire i punti vicini nello stesso cluster nel modo più rapido possibile, in modo che non finiscano in cluster diversi molto vicini tra loro. 
Troviamo la coppia di nodi $p$ e $p'$ più vicina tale che $p$ e $p'$ non sono nello stesso cluster e aggiungiamo un arco tra di loro, dopo fondiamo i 2 cluster.
Ripetiamo per n-k volte fino a quando non avremo esattamente k cluster.

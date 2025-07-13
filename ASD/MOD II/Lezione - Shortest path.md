----
# Shortest path problem

Uno dei problemi principali dei grafi è quello di trovare il cammino minimo tra 2 nodi. Possiamo farci 2 domande inerenti a questo problema: 
- dati 2 nodi u e v, **qual è il cammino minimo tra u e v?**
- dato un nodo di partenza, **qual è il cammino minimo da s ad ogni altro nodo?**

Un problema classico può essere il seguente:

Dato un grafo $<\:G = (V, E),\: \ell: E \to \mathbb R\: >$ pesato e connesso e un nodo di partenza s. Ogni arco e ha una lunghezza $\ell_{e}\geq 0$, che potrebbe indicare il tempo o la distanza per percorrere l'arco. 
Per ogni cammino P, la lunghezza di P è definita come la somma delle lunghezze di tutti gli archi di P. 
Il nostro **obiettivo** è quello di determinare **il cammino minimo da s ad ogni nodo del grafo**.

![[ASD/MOD II/img/img0.png|center|400]]

In questo caso il costo del cammino dal nodo s al nodo t è di 9+23+2+16 = 50.

>[!note]- Teorema
>Per ogni grafo $<\:G = (V, E),\: \ell: E \to \mathbb R\: >$ ed un nodo sorgente s, esiste sempre una soluzione ottima che forma uno Spanning Tree per G.
>Questo teorema è conseguenza del principio di [[Capitolo 13 - Cammini minimi#Sottostruttura ottima|subottimalità di cammini minimi]] in un grafo con pesi non negativi.

## Algoritmo di Dijkstra 
Questo algoritmo determina la lunghezza dei cammini minimi da un nodo sorgente $s$ verso ogni altro nodo di un grafo.
L'algoritmo mantiene un insieme $S$ di vertici $u$ per cui abbiamo trovato un cammino minimo $d(u)$ da $s$. Questo insieme rappresenta la parte "esplorata" del grafo. 
Inizialmente $S = \{s\}$ e $d(s) = 0$. Successivamente, per ogni nodo v del grafo collegato ad s si calcola $d(v)$ (peso dell'arco che collega s a v). 

![[ASD/MOD II/img/img1.png|center|500]]

Dopo questo passaggio si sceglie il nodo v che verifica $$\pi(v) = \min_{e=(u,v):u\in S} \{d(u)+\ell_{e} \}$$
dove $\ell_{e}$ rappresenta il peso di un singolo arco che collega un nodo u della parte esplorata $S$, ad un nodo v nella parte inesplorata. A questo punto si aggiunge il nodo v nella parte esplorata $S$ e si imposta $d(v) = \pi(v)$. 

![[ASD/MOD II/img/img2.png|center|500]]

### Correttezza

>[!note]- Teorema
>Per ogni nodo $u \in S$, $d(u)$ è la lunghezza del cammino minimo da $s$ ad $u$

**Dim.** per induzione su $|S|$ 
**Caso base**: $|S| = 1$ è banale.
**Ipotesi induttiva**: Assumiamo che il teorema sia vero per $|S|=k\geq 1$.
Sia $v$ il nodo successivo aggiunto ad $S$ e sia $u-v$ l'arco scelto. Il cammino minimo $s-u$ più l'arco scelto (u, v) è un cammino s-v di lunghezza $\pi(v)$. Consideriamo ora un qualsiasi cammino da s a v che chiameremo P. Vediamo che non sarà mai più corto di $\pi(v)$.
Sia ora $x-y$ il primo arco in P che esce dalla parte esplorata $S$, e chiamiamo P' il sottocammino per arrivare ad x. Notiamo che P è già troppo lungo non appena lascia $S$. 
$$\ell(P)\geq \ell(P')+\ell(x,\: y)\geq d(x)+\ell(x,\: y)\geq \pi(y)\geq \pi(v)$$ ^8ba983

![[ASD/MOD II/img/img3.png|center|500]]

>[!note]- Corollario
>Per ogni $t = 0,\: \dots, n$, sia $v(t)$ il t-esimo nodo selezionato dall'algoritmo di Dijkstra. Allora $v(t)$ è il t-esimo nodo più vicino alla sorgente s. 

**Dim.** per induzione su t (simile alla [[#^8ba983|Dim.]] del teorema)

### Implementazione
Per ogni nodo inesplorato, manteniamo esplicitamente $\pi(v) = \min_{e=(u,v):u\in S} \{d(u)+\ell_{e} \}$.
Il prossimo nodo da esplorare sarà il nodo con il minimo $\pi(v)$.
Quando esploriamo v, per ogni arco incidente $e = (v, \: w)$ aggiorniamo $\pi(v) = \{\pi(w),\: \pi(v)+\ell_{e} \}$ 

Un'implementazione efficiente è quella in cui utilizziamo una coda con priorità dei nodi esplorati, con chiave di priorità $\pi(v)$. 

**Tabella dei costi in base alla struttura utilizzata**

![[ASD/MOD II/img/img4.png|center|500]]
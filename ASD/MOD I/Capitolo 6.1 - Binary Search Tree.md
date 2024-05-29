# Il problema del Dizionario

Nel [[Capitolo 3 - Strutture dati elementari#Realizzazione di un dizionario|capitolo 3]] abbiamo visto come, in base alle strutture utilizzate, il costo delle operazioni cambia. 

![[ASD/MOD I/img/img69.png|center|400]]

## Come implementare efficientemente un dizionario?

In questo capitolo vedremo varie implementazioni di dizionari basate su alberi di ricerca che supportano le operazioni di inserimento, ricerca e cancellazione in tempo logaritmico nella dimensione del dizionario $O(\log(n))$. 
Questo si può risolvere con due idee:
1. Alberi binari di ricerca: albero (binario) tale che ogni operazione richiede tempo $O(\text{alteza albero})$
2. Alberi AVL: fare in modo che l'**altezza dell'albero** sia sempre $O(log(n))$

### Alberi binari di ricerca (BST = Binary Search Tree)

>[!important]- **Def.**
>Un albero binario di ricerca è un albero binario che soddisfa le seguenti proprietà:
>- ogni nodo v contiene un elemento $elem(v)$ a cui è associata una chiave $chiave(v)$ presa da un dominio totalmente ordinato
>- Per ogni nodo v vale che:
>	- le chiavi nel sottoalbero sinistro di v sono $\leq chiave(v)$
>	- le chiavi nel sotto albero destro di v sono $\geq chiave(v)$

![[ASD/MOD I/img/img87.png|center|500]]
![[ASD/MOD I/img/img88.png|center|500]]

Cosa succede se visito un BST in modo simmetrico ([[Capitolo 3 - Strutture dati elementari#Algoritmo di visita in profondità (DFS)|dfs]])?

Ottengo: 2 3 4 6 7 9 13 15 17 18 20
Visito i nodi in ordine crescente di chiave!

**Verifica di correttezza**
Indichiamo con h l'altezza dell'albero
Vogliamo mostrare che la visita in ordine simmetrico restituisce la sequenza ordinata.
Per induzione sull'altezza del BST: h = 1.

![[ASD/MOD I/img/img89.png|center|500]]

h = generico (ipotizzo che la procedura sia corretta per altezza $\lt$ h)

![[ASD/MOD I/img/img90.png|center|500]]

### Implementare le operazioni del dizionario su un BST

#### Search(chiave k) -> elemento
Data in input la chiave k, restituisce l'elemento associato alla chiave.
Traccia un cammino nell'albero partendo dalla radice: su ogni nodo, usa la proprietà di ricerca per decidere se proseguire nel sottoalbero sinisto o destro.

![[ASD/MOD I/img/img91.png|center|500]]

Search(7)
![[ASD/MOD I/img/img92.png|center|500]]

Costo = $O(\text{altezza albero})$

#### Insert(elem e, chiave k)
Aggiungere la nuova chiave come nodo foglia; per capire dove mettere la foglia simula una ricerca con la chiave da inserire.

1. Crea un nuovo nodo u con elem = e e chiave = k
2. Cerca la chiave k nell'albero, identificando così il nodo v che diventerà padre di u
3. Appendi u come figlio sinistro/destro di v in modo che sia mantenuta la proprietà di ricerca

Costo = $O(\text{altezza albero})$

Insert(e, 8)
![[ASD/MOD I/img/img93.png|center|500]]

#### Ricerca del massimo/minimo

![[ASD/MOD I/img/img94.png|center|500]]

**Nota**: è possibile definire una procedura min(nodo u) in maniera del tutto analoga

>[!info]- Osservazione
>Non è sempre detto che il minimo/massimo sia una foglia nel BST

![[ASD/MOD I/img/img95.png|center|500]]

#### Predecessore e Successore

**Def.**
- Il **precedessore** di un nodo u in un BST è il nodo v nell'albero avente massima chiave $\leq$ chiave(u)
- Il **successore** di un nodo u in un BST è il nodo v nell'albero avente minima chiave $\geq$ chiave(u)

**Ricerca del predecessore**

![[ASD/MOD I/img/img96.png|center|500]]
![[ASD/MOD I/img/img97.png|center|500]]

Nota: la ricerca del successore di un nodo è simmetrica

![[ASD/MOD I/img/img98.png|center|500]]

#### Delete(elem e)
Sia u il nodo contenente l'elemento da cancellare. Per la procedura di eliminazione dobbiamo distiguere 3 casi:

1. il nodo u è una foglia: in questo caso basta staccare la foglia dal padre ed eliminarla direttamente.
2. il nodo u ha un unico figlio: sia v l'unico figlio di u. Se u è radice, v diventa la nuova radice dell'albero, altrimenti, dopo aver individuato il genitore w di u, l'arco (w, u) che li collega viene sostituito dall'arco (w, v).
![[ASD/MOD I/img/img99.png|center|350]]

3. il nodo u ha due figli: si individua il predecessore di u, che chiamiamo v e lo sostituiamo. Dopodiché rimuoviamo fisicamente il predecessore. 

![[ASD/MOD I/img/img100.png|center|500]]

#### Costo delle operazioni
- Tutte le operazioni hanno costo $O(h)$ dove h è l'altezza dell'albero
- $O(n)$ nel caso peggiore (alberi molto sbilanciati e profo

Con un albero binario di ricerca bilanciato: h=$O(log(n))$

![[ASD/MOD I/img/img101.png|center|500]]


![[ASD/MOD I/img/img102.png|center|600]]


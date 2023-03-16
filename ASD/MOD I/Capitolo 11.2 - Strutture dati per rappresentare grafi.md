-----
## Grafi non diretti


![[img161.png|center|200]]

![[img162.png|center|500]]

| **Operazione**              | Matrice di a. | Liste di a.                     |
| --------------------------- | ------------- | ------------------------------- |
| elenco archi incidenti in v | $O(n)$        | $O(\delta(v))$                  |
| c'è un arco (u, v)?          | $O(1)$        | $O(min\{\delta(u),\delta(v)\})$ |


## Grafi diretti

![[img163.png|center|500]]

| **Operazione**            | Matrice di a. | Liste di a.    |
| ------------------------- | ------------- | -------------- |
| elenco archi uscenti da v | $O(n)$        | $O(\delta(v))$ |
| c'è arco (u, v)?           | $O(1)$        | $O(\delta(u))$ |

## Visite di grafi

Una visita di un grafo G permette di esaminare i nodi e gli archi di G **in modo sistematico** (se G è connesso).
Un problema che dobbiamo affrontare in un algoritmo di visita è come verificare se un vertice non sia già stato visitato, questo allo scopo di evitare di visitare più volte inutilmente lo stesso vertice. Per risolvere questo problema si associa ad ogni vertice un bit di "marcatura". Questo bit avrà valore 1 se il vertice è già stato visitato, altrimenti 0.

Esistono vari tipi di visite con diverse proprietà.

### Visita in ampiezza

Sia G = (V, E) un grafo non orientato. Una visita in ampiezza di G, a partire da un vertice assegnato s, esamina i vertici di G in un ordine ben definito, generando un albero T che chiameremo albero BFS (Breadth-first search). 
Dato quindi un grafo G non pesato e un nodo s, trova tutte le distanze/cammini minimi da s verso ogni altro nodo v.

**Applicazioni**:
- **web crawling**
	- come google trova nuove pagine da indicizzare
- **social networking**
	- trovare gli amici che potresti conoscere
- **network broadcast**
	- un nodo manda un messaggio a tutti gli altri nodi della rete
- **garbage collection**
	- come scoprire memoria non puù raggiungibile che si può liberare
- **model checking**
	- verificare una proprietà di un sistema
- **risolvere puzzle**
	- risolvere il Cubo di Rubik con un numero minimo di mosse

#### Pseudo-codice

![[img164.png|center|400]]

**Esempio**

[Esempio visitaBFS](http://www.mat.uniroma2.it/~guala/visite_2021.pdf) da pagina 15 a pagina 33

**Esempio Grafo orientato**

![[img165.png|center|500]]


#### Costo della visita in ampiezza 
##### Grafo rappresentato con matrice di adiacenza

![[img166.png|center|400]]


##### Grafo rappresentato con liste di adiacenza

![[img167.png|center|400]]


**Osservazioni**
1. Si noti che se il grafo è connesso allora $m\geq n-1$ e quindi $O(m+n)=O(m)$
2. Ricordando che $m\leq n(n-1)/2$, si ha $O(m+n)=O(n^2)\implies$ per $m=o(n^2)$ la rappresentazione mediante **liste di adiacenza** è **temporalmente più efficiente!**

##### Proprietà dell'albero BFS radicato in s

1. Se il grafo è **non orientato**, per ogni arco (u, v) del grafo gli estremi u e v appartengono allo stesso livello o a livelli consecutivi dell'albero BFS 

![[img168.png|center|250]]

2. Se il grafo è **orientato**, allora gli archi orientati **verso il basso** uniscono nodi sullo stesso livello o su livelli adiacenti, mentre gli archi orientati **verso l'alto** possono unire nodi su livelli non adiacenti 

![[img169.png|center|250]]

>[!important]- Teorema
>Per ogni nodo v, il livello di v nell'albero BFS è pari alla distanza di v dalla sorgente s (sia per grafi orientati che non orientati).

**Dimostrazione informale**

- all'inizio inserisco **s** in **F** (che è a distanza 0 da se stesso) e gli assegno livello 0; chiaramente **s** è l'unico nodo a distanza 0
- estraggo **s** e guardo tutti i suoi vicini (archi uscenti); questi sono tutti i nodi a distanza 1 da **s**; li inserisco in **F** e assegno loro livello 1. Ora in **F** ho **tutti** i nodi a distanza 1
- estraggo uno a uno tutti i nodi di livello/distanza 1 e per ognuno guardo tutti i suoi vicini (archi uscenti); i vicini non marcati sono a distanza 2 da **s**; li inserisco in **F** e assegno loro livello 2; quando ho estratto e visitato tutti i nodi di livello 1, in **F** ho **tutti** i nodi a distanza 2 da **s**
- estraggo uno a uno tutti i nodi di livello/distanza 2 e per ognino guardo tutti i suoi vicini (archi uscenti); i vicini non marcati sono a distanza 3 da **s**...

### Visita in profondità

Un'analogia: esplorare un labirinto

![[img173.png|center|600]]

**Cosa mi serve?**

- gesso: per disegnare le strade prese
- corda: per tornare indietro se necessario

**Variabile booleana**: dice se un nodo è stato già visitato 
**Pila**: push vuol dire srotolare, pop vuol dire arrotolare

#### Pseudo-codice

![[img170.png|center|500]]


Esempio --> [Esempio visitaDFS](http://www.mat.uniroma2.it/~guala/visite_2021.pdf) da pagina 43 a pagina 60

**Esempio: Grafo diretto**

![[img171.png|center|400]]


... ritornando al labirinto

L'albero DFS associato al labirinto sarà

![[img172.png|center|250]]


#### Costo della visita in profondità

Il tempo di esecuzione dipende dalla struttura dati usata per rappresentare il grafo (e dalla connettività o meno del grafo rispetto ad s):
- Liste di adiacenza: $O(m+n)$
- Matrice di adiacenza: $O(n^2)$

#### Proprietà dell'albero DFS radicato in s

- Se il grafo è **non orientato**, per ogni arco **(u, v)** si ha:
	- **(u, v)** è un arco dell'albero DFS
	- i nodi **u** e **v** sono l'uno discendente/antenato dell'altro
- Se il grafo è **orientato**,per ogni arco **(u,v)** si ha:
	- **(u, v)** è un arco dell'albero DFS
	- i nodi **u** e **v** sono l'uno discendente/antenato dell'altro
	- **(u, v)** è un arco **trasversale a sinistra**, ovvero il vertice v è in un sottoalbero visitato precedentemente ad u



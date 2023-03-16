----
# Il problema della Coda con priorità

Una coda con priorità è un tipo di dato che permette di mantenere il minimo (o il massimo) in un insieme di chiavi su cui è definita una relazione d'ordine totale.  

## Tipo di dato

^99ea1b

![[img124.png|center|500]]
![[img125.png|center|500]]

Le code con priorità trovano diversi tipi di utilizzo, come la gestione delle code in risorse condivise, la gestione della priorità di processi concorrenti, progettazione di algoritmi efficienti per diversi problemi.

Le code con priorità possono essere implementate elementarmente utilizzando queste strutture dati:

## Array non ordinato

Lo dimensiono sufficientemente grande e tengo traccia del numero n di elementi nella coda in una variabile di appoggio

- **FindMin**: $\Theta(n)$ (devo guardare tutti gli elementi)
- **Insert**: $O(1)$ (inserisco in coda)
- **Delete**: $O(1)$ (poichè mi viene fornito il riferimento diretto all'elemento da cancellare, lo posso cancellare in $O(1)$ sovracopiando l'ultimo elemento)
- **DeleteMin**: $\Theta(n)$ (devo prima cercare il minimo in $\Theta(n)$, poi lo posso cancellare in $O(1)$)

## Array ordinato

Lo dimensiono sufficientemente grande, lo tengo **ordinato** in ordine **decrescente** e tengo traccia del numero n di elementi nella coda in una variabile di appoggio

- **FindMin**: $O(1)$
- **Insert**: $O(n)$ (trovo in $\Theta(log(n))$ la giusta posizione, ma poi devo fare $O(n)$ spostamenti)
- **Delete**: $O(n)$ (devo fare $O(n)$ spostamenti)
- **DeleteMin**: $O(1)$ (l'elemento minimo è in fondo all'array, non devo fare spostamenti)

## Lista non ordinata

La considero **bidirezionale**

![[img126.png|center|500]]

- **FindMin**: $\Theta(n)$ (devo guardare tutti gli elementi)
- **Insert**: $O(1)$ (inserisco in coda o in testa)
- **Delete**: $O(1)$ (poichè mi viene fornito il riferimento diretto all'elemento da cancellare, lo posso cancellare in $O(1)$ agendo sui puntatori)
- **DeleteMin**: $\Theta(n)$ (devo prima cercare il minimo in $\Theta(n)$, poi lo posso cancellare in $O(1)$)

## Lista ordinata

Tengo la lista bidirezionale **ordinata** in ordine **crescente**

- **FindMin**: $O(1)$ (il minimo è in testa alla lista)
- **Insert**: $O(n)$ (trovo in $O(n)$ la giusta posizione, e poi faccio in $O(1)$ l'inserimento)
- **Delete**: $O(1)$ (agisco sui puntatori)
- **DeleteMin**: $O(1)$ (basta far puntare la testa della lista al secondo elemento della lista stessa)

## Riepilogo

![[img127.png|center|500]]

Come possiamo vedere, a seconda della struttura utilizzata, le diverse operazioni hanno costo lineare. 
È possibile implementare una coda con priorità che non comporti costi lineari?

## d-heap

**Def.**
Un d-heap è un albero radicato d-ario con le seguenti proprietà:
1. **Struttura**: un d-heap di altezza h è completo almeno fino a profondità h-1.
2. **Contenuto Informativo**: ciascun nodo v contiene un elemento elem(v) e una chiave chiave(v) presa da universo totalmente ordinato
3. **Ordinamento a heap**: ciascun nodo v diverso dalla radice ha una chiave non inferiore a quella del padre: $chiave(v)\geq chiave(padre(v))$

**Oss.**
La proprietà 1 dei d-heap implica che la differenza di profondità fra qualunque coppia di foglie è al più 1, e quindi un d-heap è quasi perfettamente bilanciato. 

Heap d-ario con 18 nodi e d=3

![[img128.png|center|600]]


### Proprietà

1. Un d-heap con n nodi ha altezza $\Theta(\log_{d}(n))$
2. La **radice** contiene l'**elemento con chiave minima** (per via della proprietà di ordimento a heap)
3. Può essere **rappresentato implicitamente** tramite vettore posizionale grazie alla proprietà di struttura

#### Procedure ausiliarie

Utili per ripristinare la prorpietà di ordinamento a heap su un nodo v che non la soddisfi

![[img129.png|center|500]]

### FindMin

![[img130.png|center|500]]

$$T(n)=O(1)$$

### Insert(elem e, chiave k)

L'inserimento avviene creando u n nuovo nodo v, a cui vengono associati il valore $e$ e chiave $k$. Il nodo viene aggiunto all'albero T in modo che diventi una foglia dell'ultimo livello. Questo garantisce che la proprietà di struttura è soddisfatta, ma il valore chiave(v) potrebbe essere minore del valore chiave(padre(v)) e quindi la proprietà di ordinamento a heap potrebbe non essere rispettata. Per ripristinarla, basta far risalire il nodo v verso l'alto nell'albero, scambiando ripetutamente il nodo v con il padre(v), finquando v non diventa la radice o non sia verificata la proprietà chiave(v) $\geq$ chaive(padre(v)). 

Vedi esempio qua [Esempio Insert](https://www.mat.uniroma2.it/~guala/cap8**2021.pdf) da pagina 17 a 21

$T(n)=O(log_d(n))$ per l'esecuzione di [[#Procedure ausiliarie|muoviAlto]].

### delete(elem e) e deleteMin
Scambia il nodo v contenente l'elemento e da eliminare con una qualunque foglia u dell'albero, poi elimina v. Infine ripristina la proprietà dell'ordinamento a heap spingendo il nodo u verso la sua posizione corretta, scambiandolo ripetutamente con il proprio padre o il proprio figlio contenente la chiave più piccola.

Vedi esempio qua [Esempio delete](https://www.mat.uniroma2.it/~guala/cap8**2021.pdf) da pagina 23 a 30

$T(n)=O(\log_d(n))$ o $O(d\log_d(n))$ per l'esecuzione di [[#Procedure ausiliarie|muoviAlto]] o muoviBasso

### decreaseKey (elem e, chiave d)
Decrementa il valore della chiave nel nodo v contenente l'elemento e della quantità richiesta d. Ripristina poi la proprietà di ordinamento a heap spingendo il nodo v verso l'alto tramite scambi di nodi ripetuti.

Vedi esempio qua [Esempio delete](https://www.mat.uniroma2.it/~guala/cap8**2021.pdf) da pagina 32 a 36

$T(n)=O(log_d(n))$ per l'esecuzione di [[#Procedure ausiliarie|muoviAlto]].

### increaseKey (elem e,chiave d)
Aumenta il valore della chiave nel nodo v contenente l'elemento e della quantità d. Ripristina la proprietà di ordinamento spingendo il nodo v verso il basso.

Vedi esempio qua [Esempio delete](https://www.mat.uniroma2.it/~guala/cap8**2021.pdf) da pagina 38 a 42

$T(n)=O(d\log_d(n))$ per l'esecuzione di [[#Procedure ausiliarie|muoviBasso]].

### Merge

L'operazione di fusione di due code con priorità, non è normalmente supportata utilizzando i d-heap, poiché risulterebbe inefficiente. 
Due modi:
1. **Costruire da zero**: si distruggono le due code e se ne crea una nuova contenente l'unione degli elementi
2. **Inserimenti ripetuti**: si inseriscono ripetutamente gli elementi della coda più piccola in quella più grande

#### Costruire da zero
Genero un nuovo heap d-ario contenente tutti gli elementi di $c_1$ e $c_2$.
Questo può essere realizzato con una procedura generalizzata dell'[[Capitolo 4.2 - HeapSort#Costruzione dell'heap|heapify]], dopodiché rendo heap i sottoalberi della radice ricorsivamente e chiamo [[#Procedure ausiliarie|muoviBasso]] sulla radice.

**Complessità** (d costante):
$$T(n)=d\cdot T(n/d)+O(d\log_d(n))\underbrace{\implies}_{\text{Teorema Master (caso 1)}}T(n)=\Theta(n)$$
dove $n=|c_1|+|c_2|$

#### Inserimenti ripetuti

Inseriamo ad uno ad uno tutti gli elementi della coda più piccola nella coda più grande.
Sia $k=min\{|c_1|,|c_2|\}$ e $n=|c_1|+|c_2|$
Eseguiamo quindi k inserimenti nella cosa più grande.
Costo: $O(k\log(n))$, dove $n=|c_1|+|c_2|$

L'approccio conviene quindi per $k\log(n)=o(n)$, cioè per $$k=o(n/\log(n))$$
**Osservazione**: nel caso peggiore entrambe le operazioni hanno un costo di $\Omega(n)$


## Heap Binomiali
Un'altra rappresentazione del dato [[#^99ea1b|coda con priorità]] è basata su una struttura dati chiamata heap binomiale, che diversamente dai d-heap, supportano efficientemente la fusione di due heap in uno. Essi sono basati su alberi con una particolare struttura chiamati alberi binomiali.

### Alberi Binomiali
**Def.**
Un albero binomiale $B_i$ è definito ricorsivamente come segue:
1. $B_0$ consiste di un **unico** nodo
2. Per $i\gt0,\: B_{i+1}$ è ottenuto fondendo due alberi binomiali $B_i$, ponendo la radice dell'uno come figlia della radice dell'altro.

![[img131.png|center|500]]

**Proprietà** 
Un albero binomiale $B_h$ gode delle seguenti proprietà:
1. Numero di nodi $(|B_{h}|):n =2^{h}$
2. Grado della radice: $D(n)=\log_{2}(n)$
3. Altezza: $H(n)=h=\log_{2}(n)$
4. Figli della radice: i sottoalberi radicati nei figli della radice di $B_{h}$ sono $B_{0}, \: B_{1},\: \dots, \: B_{h-1}$

**Def.**
Un **heap binomiale** è una **[foresta](https://it.wikipedia.org/wiki/Albero_(grafo)) di alberi binomiali** $B_{i}$ che gode delle seguenti proprietà:
1. **Struttura**: ogni albero $B_{i}$ nella foresta è un albero binomiale.
2. **Unicità**: per ogni intero $i\geq0$ esiste al più un $B_i$ nella foresta
3. **Contenuto informativo**: ogni nodo v nella foresta contiene un elemento elem(v) ed una chaive chiave(v) presa da un dominio totalmente ordinato
4. **Ordinamento a heap**: in ogni $B_{i}$, ciascun nodo v diverso dalla radice verifica $chiave(v)\geq chiave(parent(v))$

**Esempio di heap binomiale con n = 13 nodi**

![[img132.png|center|400]]

**Quanti alberi binomiali può avere al massimo un heap binomiale con n nodi?**
Dalla proprietà di unicità degli alberi binomiali che lo costituiscono, ne deriva che un heap binomiale di n elementi è formato dagli alberi binomiali $B_{i_{0}},\: B_{i_{1}}, \:\dots, B_{i_{h}}$, dove $i_{0},\: i_{1},\:\dots,\:  i_{h}$, corrispondono alle posizione degli 1 nelle rappresentazioni in base 2 di n.
Ne consegue che in un heap binomiale di n nodi, vi sono al più $\lceil \log(n)\rceil$ alberi binomiali, ciascuno con grado ed altezza $O(\log(n))$  

#### Procedura ausiliaria
Utile per ripristinare la prorpietà di unicità in un heap binomiale. (Ipotizziamo di scorrere la lista delle radici da sinistra verso destra, in ordine crescente rispetto all'indice degli alberi binomiali).

![[img133.png|center|500]]

$T(n)$ = lineare nel numero di alberi binomiali in input
(Ogni fusione diminuisce di uno il numero di alberi binomiali)

Esempio -> [Esempio Ristruttura](http://www.mat.uniroma2.it/~guala/cap8**2022.pdf#page=59)

### Realizzazione coda con priorità basata su heap binomiali

![[img134.png|center|500]]
![[img135.png|center|500]]
![[img136.png|center|500]]

Tutte le operazioni richiedono tempo $T(n)=O(log(n))$
Durante l'esecuzione della procedura ristruttura esistono infatti al più tre $B**i$, per ogni $i\geq0$

Esempio completo -> [Esempio Completo](http://www.mat.uniroma2.it/~guala/cap8**2022.pdf) da pag 63 a 67

## Heap di Fibonacci (Fredman, Tarjan, 1987)
Gli heap di Fibonacci offrono la più efficiente implementazione degli heap che sia nota.

>[!important]- Definizione (Heap Binomiale Rilassato)
>Un heap binomiale rilassato è semplicemente un heap binomiale che non gode della proprietà di unicità dei $B_{i}$:
>1. Struttura: ogni albero $B_{i}$ nella foresta è binomiale.
>2. Contenuto informativo: ogni nodo v nella foresta contiene un elemento elem(v) e una chiave chiave(v) presa da un universo totalmente ordinato.
>3. Ordinamento a heap: in ogni $B_{i}$, ciascun nodo v diverso dalla radice ha una chiave inferiore a quella del suo genitore: $chiave(v)\geq chaive(padre(v))$

>[!important]- Defizione (Heap di Fibonacci)
>Si ottiene da un heap binomiale rilassato indebolendo la proprietà di struttura dei $B_{i}$ che non sono più necessariamente alberi binomiali

### Conclusioni: tabella riassuntiva

| Heap        | **FindMin** | **Insert**      | **Delete**            | **DelMin**            | **IncKey**            | *DecKey*       | **Merge**        |
| ----------- | ----------- | ----------- | ----------------- | ----------------- | ----------------- | ------------ | ------------ |
| d-Heap      | $O(1)$      | $O(log(n))$ | $O(log(n))$       | $O(log(n))$       | $O(log(n))$       | $O(log(n))$  | $O(n)$       |
| Heap Binom. | $O(log(n))$ | $O(log(n))$ | $O(log(n))$       | $O(log(n))$       | $O(log(n))$       | $O(log(n))$  | $O(log(n))$  |
| Heap Fibon. | $O(1)$      | $O(1)$      | $O(log(n))^\star$ | $O(log(n))^\star$ | $O(log(n))^\star$ | $O(1)^\star$ | $O(1)^\star$ |

L'analisi per d-Heap e Heap Binomiali è nel caso peggiore, mentre quella per gli Heap di Fibonacci è ammortizzata (per le operazioni **asteriscate**)

----
# Analisi Ammortizzata
Il **costo ammortizzato** di un'operazione è il costo "**medio**" rispetto a una sequenza **qualsiasi** di operazioni

**Esempio**: se un'operazione ha costo ammortizzato costante e eseguo una sequenza (qualsiasi) di k operazioni è possibile che il costo di una singola operazione può non essere costante, ma l'intera sequenza costerà $O(k)$

Diverso dal **costo medio**: non c'è nessuna distribuzione di probabilità (sulla sequenza da eseguire) e l'algoritmo è un algoritmo deterministico

Molto utile quando si vogliono **buone prestazioni sull'intera sequenza** e non garanzie sulla singola operazione (ad esempio progettare algoritmi veloci attraverso strutture dati efficienti).

>[!important]- Teorema
>Usando un Heap di Fibonacci, una qualsiasi sequenza di **n** insert, **d** delete, **f** findMin, **m** deleteMin, $\Delta$ increaseKey, $\delta$ decreaseKey, $\micro$ merge prende tempo (nel caso peggiore): $$O(n+f+\delta+\micro+(d+m+\Delta)log(n))$$

**Esercizio (di manipolazione)**
Creare e unire 2 Heap Binomiali sui seguenti insiemi:
- $A**1=\{3,5,7,21,2,4\}$
- $A**2=\{1,11,6,22,13,12,23,31\}$




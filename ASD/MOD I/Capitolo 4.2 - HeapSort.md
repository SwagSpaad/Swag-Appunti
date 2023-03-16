----
# Progettare algoritmi veloci usando strutture dati efficienti

## HeapSort
Utilizza lo stesso approccio incrementale del [[Capitolo 4.1 - Selection sort, insertion sort, bubble sort, merge sort e quick sort#^b613bd|SelectionSort]], ma nel caso peggiore effettua un numero inferiore di confronti grazie all'utilizzo di strutture dati efficienti per selezionare il minimo ad ogni iterazione

>**Tipo di dato**: specifica una collezione di oggetti e delle operazioni di interesse su tale collezione (es. Dizionario: mantiene un insieme di elementi con chiavi soggetto a operazioni di inserimento, cancellazione, ricerca)

>**Struttura dati**: organizzazione dei dati che permette di memorizzare la collezione e supportare le operazioni di un tipo di dato usando meno risorse di calcolo possibile

**Cruciale**: progettare una struttura dati H su cui eseguire efficientemente le operazioni:
- dato un array A, generare velocemente H
- trovare il più grande oggetto in H
- cancellare il più grande oggetto da H

**Tipo di dato associato**: coda con priorità

### Alberi: qualche altra definizione

![[ASD/MOD I/img/img31.png|center|500]]

**albero d-ario**: albero in cui tutti i nodi interni hanno (al più) d figli
Se $d=2\implies$**albero binario**
Un albero d-ario è **completo**: se tutti nodi interni hanno esattamente d figli e le foglie sono tutte allo stesso livello

![[ASD/MOD I/img/img32.png|center|400]]

## Struttura dati Heap
Un heap associato a un insieme S di elementi  è un albero binario radicato con le seguenti proprietà:
1. completo fino al penultimo livello (struttura rafforzata: le foglie sull'ultimo livello sono tutte compattate a sinistra)
2. gli elementi di S sono memorizzati nei nodi dell'albero (ogni nodo v memorizza uno e un solo elemento, denotato con chiave(v))
3. chiave(padre(v))$\geq$ chiave(v) per ogni nodo v diverso dalla radice

![[ASD/MOD I/img/img33.png|center|300]]

### Proprietà degli Heap
1. Il **massimo** è contenuto **nella radice**
2. L'albero con n nodi ha **altezza O(log(n))**
3. Gli heap con struttura rafforzata possono essere rappresentati in un **array di dimensione pari a n**

#### Altezza di un heap
Sia H un heap di n nodi e altezza h

![[ASD/MOD I/img/img34.png|center|400]]

$$n\geq 1+\sum_{i=0}^{h-1}2^i=1+2^h-1=2^h\implies h\leq log_2n$$
#### Rappresentazione tramite vettore posizionale

![[ASD/MOD I/img/img35.png|center|500]]

...ancora un esempio
![[ASD/MOD I/img/img36.png|center|500]]

## La procedura fixHeap

^78cb79

Sia v la radice di H. Assume che i sottoalberi radicati nel figlio sinistro e destro di v sono heap, ma la proprietà di ordinamento delle chiavi non vale per v. Posso ripristinarla così:

> **fixHeap**(nodo v,heap H)
> if (v non è una foglia) then
> 	sia u il figlio di v con chiave massima
> 	if (chiave(v)$\lt$chiave(u)) then
> 		scambia chiave(v) e chiave(u)
> 		**fixHeap**(u,H)

Tempo di esecuzione: $O(log(n))$

**Esempio**
![[ASD/MOD I/img/img37.png|center|300]]

Scambio 4 con 16 (quindi chiave(i=1)<chiave(i=2))

![[ASD/MOD I/img/img38.png|center|300]]

scambio 4 con 14 (quindi chiave(i=2)<chiave(i=4))

![[ASD/MOD I/img/img39.png|center|300]]

scambio 4 con 8 (quindi chiave(i=4)<chiave(i=9))

![[ASD/MOD I/img/img40.png|center|300]]

Complessità $O(log(n))$

### Pseudocodice più dettagliato
(l'heap è mantenuto attraverso un vettore posizionale)

![[ASD/MOD I/img/img41.png|center|300]]

## Estrazione del massimo
Copia nella radice la chiave contenuta nella foglia più a destra dell'ultimo livello, rimuove la foglia e ripristina l'ordinamento a heap richiamando [[#^78cb79|fix heap]] sulla radice.

![[ASD/MOD I/img/img42.png|center|300]]
![[ASD/MOD I/img/img43.png|center|300]]
![[ASD/MOD I/img/img44.png|center|300]]

## Costruzione dell'heap
Creiamo un albero binario in cui inseriamo gli elementi di un array in un qualsiasi ordine. L'algoritmo **heapify**, basato sul metodo divide et impera, trasforma ricorsivamente i sottoalberi sinistro e destro in heap. L'unico problema potrebbe essere la chiave nella radice, come risolviamo questo problema? Basta richiamare sulla radice la procedura fixHeap.

>**heapify**(heap H)
>if (H non è vuoto) then
>	heapify(sin(H))
>	heapify(des(H))
>	fixHeap(radice(H), H)

![[ASD/MOD I/img/img45.png|center|400]]
![[ASD/MOD I/img/img46.png|center|400]]
![[ASD/MOD I/img/img47.png|center|400]]
![[ASD/MOD I/img/img48.png|center|400]]

#### Complessità heapify

Sia h l'altezza di un heap con n elementi
Sia $n'\geq n$ l'intero tale che un heap con $n'$ elementi ha:
1. altezza h
2. è completo fino all'ultimo livello

Vale $T(n)\leq T(n')$ e $n'\leq 2n$

Tempo di esecuzione: $$T(n')=2T((n'-1)/2)+O(log(n'))\leq 2T(n'/2)+O(log(n'))$$$$\implies T(n')=O(n')$$ dal Teorema Master

Quindi: $T(n)\leq T(n')=O(n')=O(2n)=O(n)$

**Esercizio**
Scrivere lo pseudocodice dettagliato di heapify assumendo che l'heap è mantenuto con un vettore posizionale

## Max-Heap e Min-Heap

E se volessi una struttura dati che mi permette di estrarre il **minimo** velocemente invece del **massimo**?

**Semplice**: costruisco un **min-heap** invertendo la proprietà di ordinamento delle chiavi. Cioè richiedo che $chiave(padre(v))\leq chiave(v)$ per ogni v (diverso dalla radice)

E come mai noi abbiamo progettato un max-heap e non un min-heap?
Risposta qua $\to$ [[Capitolo 4.2 - HeapSort#Ritornado a max-heap e min-heap|Ritornado a max-heap e min-heap]]

## Algoritmo HeapSort

Questo algoritmo di ordinamento costruisce un heap associato all'array A tramite **heapify** ed estrae ripetutamente il massimo per n-1 volte, memorizzando ad ogni estrazione il massimo nella posizione dell'array che si è liberata.

### Pseudocodice

![[ASD/MOD I/img/img49.png|center|300]]

- linea 1 ha costo $O(n)$
- linee 3-6 eseguono n-1 estrazioni di costo $O(log(n))$
$\implies$ ordina in loco in tempo $O(nlog(n))$

#### Esempio concreto

![[ASD/MOD I/img/img50.png|center|400]]
![[ASD/MOD I/img/img51.png|center|400]]
![[ASD/MOD I/img/img52.png|center|400]]
![[ASD/MOD I/img/img53.png|center|400]]
![[ASD/MOD I/img/img54.png|center|400]]

e così via, fin quando non ottengo il seguente risultato:

![[ASD/MOD I/img/img55.png|center|400]]

## Ritornado a max-heap e min-heap

Quindi: come mai abbiamo usato un max-heap e non un min-heap? Potevamo usare anche un min-heap?

...l'uso del max-heap (implementato con un vettore posizionale) ci permette di usare solo memoria ausiliare costante!

>**Teorema**
>L'algoritmo HeapSort ordina in loco un array di lunghezza n in tempo $O(n log(n))$ nel caso peggiore

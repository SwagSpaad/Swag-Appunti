# Sorting
## MergeSort $O(n\log{n})$

![[Pasted image 20250729173031.png|center|500]]
![[Pasted image 20250729173044.png|center|500]]

- Merge ha costo $\Theta(n)$ in quanto esegue le scansioni delle due metà degli array fino alla fine $\implies \frac{n}{2}+ \frac{n}{2}= n$ 
- MergeSort ha la seguente comlpessità $$T(n)= O(1) + 2 T(n/2)+\Theta(n)$$ $O(1)$ è il costo del calcolo di $m$, $2T(n/2)$ sono le due chiamate ricorsive del MergeSort sulle due metà dell'array e $\Theta(n)$ è il costo della procedura merge
Dal th. master otteniamo $T(n)=O(n\log{n})$
## QuickSort (caso medio $O(n\log{n})$ ) 
![[Pasted image 20250729190553.png|500]]
![[Pasted image 20250729190540.png|500]]
- La complessità di quickSort dipende dalla scelta del pivot
- Ogni invocazione di Partition posiziona almeno un elemento in modo corretto (il pivot)
- caso peggiore $O(n^{2})$ nel caso in cui il pivot è sempre il min/max dell'array , perché avvengono $n$ invocazioni di Partition, che ha costo $O(n)$
- caso migliore $O(n\log n)$ nel caso di partizionamenti bilanciati, perché l'array viene suddiviso in sotto problemi di dimensione $n/2$ 
![[Pasted image 20250729200258.png|400]]

## HeapSort $O(n\log{n})$ 
![[Pasted image 20250729220432.png|500]]
- La complessità di heapify è $O(n)$
- Il while effettua $n$ (numero di nodi dell'heap) [[#Estrazione massimo $O( log{n})$|estrazioni del massimo]] che hanno costo $O(\log{n})$ $$\implies T(n)\le n+n\log{n}=O(n\log{n})$$
## IntegerSort $O(n+k)=O(n)$ per $k=\Theta(n)$ 
Utile per ordinare n interi con valori $[1, k]$ utilizzando un array di ausiliario Y di dimensione k in cui ogni indice x di Y indica il numero di volte che il valore x compare nell'array da ordinare.
![[Pasted image 20250729222954.png|500]]

Nel caso in cui $k=\omega(n)$ ad esempio $k=\Theta(n^{c})$ con $c>1$ allora l'IntegerSort diventa inefficiente perché il suo costo diventa $$T(n)=\Theta(n^{c})=\omega(n\log{n})\:\: \text{per c }> 1$$
## BucketSort $O(n+k)$
L'input è: 
- un array contenente n record
- ogni record ha *campo chiave* (rispetto cui si ordina) e altri campi associati alla chiave
Per ordinare è sufficiente mantenere un array di liste invece che di contatori ed operare come [[#IntegerSort $O(n+k)=O(n)$ per $k= Theta(n)$|integer sort]].
La lista $Y[i]$ conterrà gli elementi con chiave $i$. 
![[Pasted image 20250730164232.png|500]]

## RadixSort $O\Big(n\frac{\log k}{\log n}\Big)$ 
Ordina n interi con valori compresi tra $[1,k]$
Gli elementi si rappresentano in base $b$ e si effettua una serie di passaggi di [[#BucketSort $O(n+k)$|bucket sort]], ordinando dalla cifra meno significativa alla cifra più significativa. L'i-esima cifra è la chiave, il numero completo è l'informazione satellite. Con base $b$ l'i-esima cifra è un numero in $[0, b-1]$
![[Pasted image 20250730183651.png|500]]

Il numero di cifre per rappresentare il valore massimo $k$ in base $b$ è $O(\log_{b}{k})$, quindi avvengono $O(\log_{b}{k})$ chiamate di bucket sort, ciascuna di costo $O(n+b)$. $$\implies O((n+b)\log_{b}{k})$$

- se $b=\Theta(n)$ allora $O(n\log_{n}{k})$ che per la formula del cambiamento di base dei logaritimi risulta $$\log_{n}{k}=\frac{\log_{2}k}{\log_{2}n}\implies O\bigg(n \frac{\log_{2}k}{\log_{2}n}\bigg)$$
se $k=O(n^c)\text{ con c costante}$ allora RadixSort ha tempo lineare, ad esempio $$k=O(n^{2})\implies O\bigg(n\frac{\log n^{2}}{\log n}\bigg)=O\bigg(n \cdot \frac{2\cancel{\log n}}{\cancel{\log n}}\bigg)=O(2n)=O(n)$$
# Strutture dati
## Heap
Albero binario radicato con le seguenti proprietà:
- completo fino al penultimo livello
- i nodi memorizzano al loro interno gli elementi ( chiave(v) )
- chiave(padre(v)) $\ge$ chiave(v) per ogni nodo v diverso dalla radice 

**Proprietà**
- Il massimo è contenuto nella radice
- Altezza di un heap di n nodi $O(\log{n})$ 
- gli heap di n nodi con struttra rafforzata (tutte le foglie compattate a destra) possono essere rappresentati in un array posizionale di dimensione n

### FixHeap $O(\log{n})$ 
![[Pasted image 20250729212948.png|500]]
Ripristina la proprietà di ordinamento delle chiavi che non vale per il nodo $i$. Assume che i sottoalberi del figlio sinistro e destro di $i$ sono heap

### Estrazione massimo $O(\log{n})$
- Copia la chiave della foglia più a destra dell'ultimo livello nella radice
- Rimuove la foglia (che contiene il massimo dopo la copia)
- Ripristina l'ordinamento mediante fixHeap

### Heapify $O(n)$
![[Pasted image 20250729213637.png|500]]
Basato sul divide et impera, consente di costruire un heap

## Rappresentazione con strutture indicizzate
- Pro: indici delle celle sono numeri consecutivi; accesso alle celle in tempo costante
- Contro: non è possibile aggiungere nuove celle ad un array; il ridimensionamento richiede tempo lineare
## Rappresentazione con strutture collegate
Gli elementi sono i record, numerati con il loro indirizzo di memoria e quindi indipendenti l'uno dall'altro. Il collegamento tra due record è realizzato tramite un puntatore
- Pro: posso aggiungere e togliere record ad una struttura collegata; aggiunta e rimozione in tempo costante
- Contro: gli indirizzi dei record non sono per forza consecutivi
## Dizionario
![[Pasted image 20250731131036.png|500]]
### Implementazione con array
#### Array ordinato
- search $O(\log n)$ con ricerca binaria
- insert $O(n)$ - $O(\log n)$ per cercare la posizione giusta + $O(n)$ per effettuare gli spostamenti e mantenere l'ordine
- delete $O(n)$ - ricerca + cancellazione + spostamenti
#### Array non ordinato
- insert $O(1)$ - inserisco dopo l'ultimo elemento
- search $O(n)$ - devo scorrere l'array
- delete $O(n)$ - search $O(n)$ + cancellazione $O(1)$
### Implementazione con liste
#### Lista ordinata
- search $O(n)$ - non posso usare ricerca binaria (celle non consecutive)
- insert $O(n)$ - (search + insert) devo mantenere lista ordinata
- delete $O(n)$ - (search + delete)
#### Lista non ordinata
- search $O(n)$ 
- insert $O(1)$ - faccio puntare dall'ultimo elemento della lista
- delete $O(n)$ - (search + delete)
## Pila
![[Pasted image 20250731131052.png|500]]

## Coda
![[Pasted image 20250731131220.png|500]]

## Alberi
![[Pasted image 20250731134007.png|500]]
Nelle rappresentazioni ad albero, i dati sono contenuti nei nodi e le relazioni tra i vari nodi sono definite dagli archi che li collegano.
### Rappresentazione indicizzata
#### Vettore dei padri
Sia $T=(V, E)$ un albero di $n$ nodi. Un vettore dei padri è un array P di dimensione $n$ in cui le celle contengono delle coppie (info, parent) dove per ogni indice $v$ in P, $P[v].info$ è il contenuto del nodo $v$ mentre $P[v].parent=u$ se esiste un arco $(u,v)\in E$. Se $v$ è la radice allora $P[v].parent=null$ 

![[Pasted image 20250731134601.png|500]]

- tempo per individuare il padre di un nodo $O(1)$
- tempo per individuare uno o più figli di un nodo $O(n)$
#### Vettore posizionale
Per alberi d-ari completi. Sia $T=(V, E)$ un albero di $n$ nodi. Un vettore posizionale è un array P di dimensione $n$ tale che $P[v]$ contiene l'informazione associata al nodo $v$ e l'informazione associata al j-esimo figlio è in posizione $P[d \cdot v+j]$  per $j\in\{1,\dots,d\}$. 
Per individuare il padre di un nodo $v$ è necessario effettuare $\lfloor \frac{v-1}{d}\rfloor$ 

![[Pasted image 20250731140115.png|500]]

- solo per alberi completi o quasi
- tempo per individuare il padre di un nodo $O(1)$
- tempo per individuare il figlio di un nodo $O(1)$

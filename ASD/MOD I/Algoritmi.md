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

### Visite di alberi
#### Visita in profondità DFS $O(n)$ 
![[Pasted image 20250805153459.png|500]]

![[Pasted image 20250805162434.png|center|500]]

Ogni nodo viene inserito ed estratto dalla pila una sola volta. Le azioni su ogni nodo (pop, visita, push) hanno tutte tempo costante $O(1)$. Il numero di nodi inseriti/estratti è lineare nella quantità di nodi dell'albero $O(n)$, quindi il ciclo while viene eseguito $n$ volte. 

La complessità è quindi $O(n)$

#### Visita in ampiezza BFS $O(n)$
![[Pasted image 20250805163245.png|500]]

![[Pasted image 20250805163515.png|500]]

Come per la visita DFS il tempo speso per ogni nodo è costante, e il numero di nodi inseriti/estratti è lineare in $n$, quindi la complessità è $O(n)$

### Calcolo altezza $O(n)$
![[Pasted image 20250805163729.png|500]]

### Calcolo numero di foglie $O(n)$
![[Pasted image 20250805163802.png|500]]
### Calcola grado medio $O(n)$
![[Pasted image 20250805164714.png|500]]
![[Pasted image 20250805164725.png|500]]

### Cerca elemento $O(n)$
![[Pasted image 20250805165328.png|500]]

### Alberi binari di ricerca BST
Le operazioni hanno tutte costo $O(h)$, quindi in caso di alberi sbilanciati (ad esempio in un BST linearizzato), le operazioni arrivano ad avere costo lineare $O(n)$ 
- ogni nodo dell'albero contiene un elemento ed una chiave. 
- per ogni nodo $v$ vale che
	- le chiavi nel sottoalbero sinistro di v sono $\le$ chiave(v)
	- le chiavi nel sottoalbero destro sono $>$ chiave(v)

![[Pasted image 20250805185406.png|center|500]]

La visita simmetrica in profondità di un BST (sottoalbero sin, radice, sottoalbero des) permette la visita in ordine crescente di chiave.
#### Search $O(h)$
Sfrutta la proprietà di ordinamento dei nodi $v$ per decidere se proseguire nel sottoalbero destro o sinistro

![[Pasted image 20250805190640.png|center|500]]
#### Insert $O(h)$
- Crea un nuovo nodo $u$ con $elem(u) = e$  e $chiave(u) = k$ 
- Cerca la chiave $k$ nell'albero, identificando il nodo $v$ che diventerà padre di $u$
- Appendi $u$ come figlio destro/sinistro in modo che sia mantenuta la proprietà di ricerca

![[Pasted image 20250805191013.png]]

#### max/min $O(h)$
![[Pasted image 20250805191034.png]]
la procedura $min$ è analoga. Per la proprietà dell'albero il minimo è la foglia più a sinistra della radice
#### Predecessore/successore $O(h)$
- il predecessore di un nodo $u$ è il nodo $v$ avente massima chiave $\le$ chiave(u)
- il successore di un nodo $u$ è il nodo $v$ avente minima chiave $\ge$ chiave(u)

![[Pasted image 20250805191729.png|500]]

#### Delete $O(h)$
- caso foglia: rimuovi semplicemente il nodo
- il nodo ha un solo figlio: il parent del figlio diventa il parent del nodo da rimuovere
- **il nodo ha due figli**: sostituisci il nodo con il suo predecessore/successore e rimuovi il predecessore/successore con al più un figlio (primo o secondo caso)

### Alberi AVL
Gli alberi AVL fanno in modo che l'altezza dell'albero sia sempre $O(\log{n})$.
Questo è possibile mediante un fattore di bilanciamento $$\beta(v)=\text{altezza sottoalbero sinistro di v - altezza sottoalbero destro di v}$$
Un albero si dice bilanciato in altezza se $|\beta(v)|\le1$ per ogni nodo $v$.

![[Pasted image 20250806160417.png]]

#### Altezza degli AVL $O(\log{n})$
Tra tutti gli alberi si considerano quelli più sbilanciati, ovvero gli alberi di Fibonacci. Se gli alberi di Fibonacci hanno altezza $O(\log n)$ allora tutti gli AVL hanno altezza $O(\log n)$

![[Pasted image 20250806162444.png]]

**Lemma**
Sia $T_{h}$ un albero di Fibonacci di altezza $h$ e sia $n_{h}$ il numero dei suoi nodi. Allora $h=\Theta(\log{n_{h}})$

**Dim.**
Per costruzione di $T_{h}$ risulta $n_{h}=1+n_{h-1}+n_{h-2}$ che ricorda l'equazione di Fibonacci $F_{i}=F_{i-1}+F_{i-2}$. Dimostriamo per induzione che $$n_{h}=F_{h+3}-1$$
Per $h=0$ risulta $n_{0}=1=F_{3}-1=2-1$ che è corretto. Assumiamo per ipotesi induttiva che $n_{k}=F_{k+3}-1$ per ogni $k<h$ ed utilizzando le uguaglianze relative a $n_{h}$ e $F_{i}$ abbiamo $$\begin{align}
n_{h}&=1+n_{h-1}+n_{h-2}=1+(F_{h-1+3}-1)+(F_{h-2+3}-1)\\\\
&=1+F_{h+2}-1+F_{h+1}-1=F_{h+2}+F_{h+1}-1\\\\
&=F_{h+3}-1
\end{align}$$
Osservando che $F_{h}=\Theta(\phi^{h})$ dove $\phi\approx 1.618$ è la sezione aurea, l'altezza ed il numero di nodi di $T_{h}$ sono esponenzialmente correlate e quindi $$F_{h+3}=\Theta(\phi^{h+3})\implies n_{h}=\Theta(\phi^{h+3})=\Theta(\phi^{h})$$
prendendo il logaritmo di entrambi i membri abbiamo $$\log{n_{h}}=\Theta(\log{\phi^{h}})\implies \log{n_{h}}=\Theta(h \cdot\log{\phi})$$
essendo $\log{\phi}$ una costante vale $$\log{n_{h}}=\Theta(h)\implies h=\Theta(\log n_{h})\qquad \square$$

**Corollario**
Un albero AVL di $n$ nodi ha altezza $O(\log n)$

**Dim.**
La dimostrazione viene dal lemma. Sia $h$ l'altezza dell'albero AVL. Per dimostrare che $h=O(\log n)$ consideriamo l'albero di Fibonacci di altezza $h$, che ha $n_{h}$ nodi. Per definizione di albero di Fibonacci $n_{h}\le n$. Dato che dal Lemma $h=\Theta(\log n_{h})$ e $n_{h}\le n$ allora $h=O(\log n).\:\: \square$ 

#### Search $O(\log n)$ come per [[#Search $O(h)$|BST]]
#### Insert $O(\log n)$


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
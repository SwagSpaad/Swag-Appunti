-----
# Delimitazioni inferiori e superiori (di algoritmi e problemi)

## Delimitazioni superiori (**upper bound**)
**Def.**
Un algoritmo A ha complessità (costo di esecuzione) $O(f(n))$ rispetto ad una certa risorsa di calcolo, se la quantità $r(n)$ di risorsa usata da A nel caso peggiore su istanze di dimensione n verifica la relazione $r(n)=O(f(n))$

**Def.**
Un problema P ha una complessità $O(f(n))$ rispetto ad una risorsa di calcolo se **esiste** un algoritmo che risolve P il cui costo di esecuzione rispetto quella risorsa è $O(f(n))$

## Delimitazioni inferiori (**lower bound**)
**Def.**
Un algoritmo A ha complessità (costo di esecuzione) $\Omega(f(n))$ rispetto ad una certa risorsa di calcolo, se la quantità $r(n)$ di risorsa usata da A nel caso peggiore su istanze di dimensione n verifica la relazione $r(n)=\Omega(f(n))$

**Def.**
Un problema P ha una complessità $\Omega(f(n))$ rispetto ad una risorsa di calcolo se **ogni algoritmo** che risolve P ha costo di esecuzione nel caso peggiore $\Omega(f(n))$ rispetto quella risorsa

# Ottimalità di un algoritmo
**Def.**
Dato un problema P con complessità $\Omega(f(n))$ rispetto ad una risorsa di calcolo, un algoritmo che risolve P è (asintoticamente) **ottimo** se ha costo di esecuzione $O(f(n))$ rispetto a quella risorsa.

# Complessità del problema di ordinamento

- **Upper Bound**: $O(n^2)$:
	- Insertion Sort,Selection Sort,Quick Sort, Bubble Sort
- **Un upper bound migliore**: $O(n log(n))$
	- Merge Sort,Heap Sort
- **Lower bound**: $\Omega(n)$
	- banale: ogni algoritmo che ordina n elementi li deve almeno leggere tutti

Abbiamo quindi un **gap di log(n)** tra upper bound e lower bound

**Possiamo fare meglio?**

## Sui limiti della velocità: una delimitazione inferiore alla complessità del problema

Tutti gli algoritmi visti in precedenza sono algoritmi di ordinamento per confronto

>**Ordinamento per confronti**
>Dati due elementi $a_i,a_j$, per determinare l'ordinamento relativo effettuiamo una delle seguenti operazioni di confronto:
>$$a_i\lt a_j\:;\:a_i\leq a_j\:;\:a_i=a_j\:;\:a_i\geq a_j\:;\:a_i\gt a_j$$
>Non si possono esaminare i valori degli elementi o ottenere informazioni sul loro ordine in altro modo

>**Teorema**:
>**Ogni** algoritmo basato su confronti che ordina n elementi deve fare nel caso peggiore $\Omega(nlog(n))$ confronti

**Oss**: il **numero di confronti** che un algoritmo esegue è un lower bound al **numero di passi elementari** che esegue.

>**Corollario**
>Il MergeSort e l'HeapSort sono algoritmi ottimi (dentro la classe di algoritmi basati su confronti)

## Uno strumento utile: albero di decisione

Gli algoritmi di ordinamento per confronto possono essere descritti in modo astratto in termini di **alberi di decisione**, che illustra i diversi confronti che l'algoritmo potrebbe fare su istanze di dimensione n. 
Ogni nodo dell'albero

Un generico algoritmo di ordinamento per confronto lavora nel modo seguente:
- confronta due elementi $a_i,a_j$ (ad esempio effettua il test $a_i\leq a_j$);
- a seconda del risultato - riordina e/o decide il confronto successivo da eseguire.

## Albero di decisione

Descrive le diverse sequenze di confronti che A potrebbe fare su istanze di dimensione n. 
Ogni nodo interno (non foglia):
- modella il **confronto** tra $a_i\:e\:a_j$
Nodo foglia:
- modella una risposta (output) dell'algoritmo, ovvero **permutazione degli elementi**

![[ASD/MOD I/img/img56.png|center|500]]

**Osservazioni**
- L'albero di decisione **non è** associato ad un problema
- L'albero di decisione **non è** associato **solo** ad un algoritmo
- L'albero di decisione è associato ad un **algoritmo** e a una **dimensione dell'istanza**
- L'albero di decisione descrive le diverse sequenze di confronti che un certo algoritmo può eseguire su istanze di una **data dimensione**
- L'albero di decisione è una descrizione alternativa dell'algoritmo (customizzato per istanze di una certa dimensione)

**Proprietà**
- Per una particolare istanza, i confronti eseguiti dall'algoritmo su quella istanza rappresentano un **cammino radice-foglia**
- L'algoritmo segue un cammino diverso a seconda delle caratteristiche dell'istanza:
	- **Caso peggiore**: cammino più lungo
- Il numero di confronti nel caso peggiore è pari **all'altezza dell'albero di decisione**
- Un albero di decisione di un algoritmo (corretto) che risolve il problema dell'ordinamento di n elementi deve avere necessariamente **almeno n! foglie**

>**Lemma**
>Un albero binario T con k foglie, ha altezza almeno $log_2(k)$

**Dim.** (per induzione su k)
**Caso base**: k=1, altezza almeno $log_2(1)=0$
**Caso induttivo**: $k\gt 1$
Considera il nodo interno v più vicino alla radice che ha due figli (v potrebbe essere la radice). Nota che v deve esistere perchè $k\gt1$

v ha almeno un figlio u che è radice di un (sotto)albero che ha almeno k/2 foglie e $\lt$ k foglie

T ha altezza almeno
$1+log_2(k/2)=1+log_2(k)-log_2(2)=log_2(k)$

![[ASD/MOD I/img/img57.png|center|300]]

## Il Lower Bound $\Omega(nlog(n))$

Consideriamo l'albero di decisione di un qualsiasi algoritmo che risolve il problema dell'ordinamento di n elementi.
L'altezza h dell'albero di decisione è almeno $log_2(n!)$
**Formula di Stirling**: $n!\approx (2\pi n)^{1/2}\cdot(n/e)^n$

![[ASD/MOD I/img/img58.png|center|400]]

>Può un algoritmo **basato su confronti** ordinare n interi piccoli, diciamo compresi fra 1 e k=$O(n)$, in (asintoticamente) meno di $nlog(n)$

...no, la dimostrazione funziona anche sotto questa ipotesi

# IntegerSort
Algoritmo di sort per ordinare n interi con valori compresi $[1,k]$

Mantiene un array secondario Y di k contatori tale che $Y[x]=$ numero di volte che il valore x compare nell'array di input X

![[ASD/MOD I/img/img59.png|center|500]]

Scorre Y da sinistra a destra e, se $Y[x]=k > 0$, scrive in X il valore x per k volte

![[ASD/MOD I/img/img60.png|center|500]]

## PseudoCodice

![[ASD/MOD I/img/img61.png|center|600]]

Quindi possiamo dire che
$$\sum_{i=1}^k(1+Y[i])=\sum_{i=1}^k1+\sum_{i=1}^kY[i]=k+n$$
### Analisi

- Tempo $O(1)+O(k)=O(k)$ per inizializzare Y a 0
- Tempo $O(1)+O(n)=O(n)$ per calcolare i valori dei contatori
- Tempo $O(n+k)$ per ricostruire X
$\implies O(n+k)$

Tempo lineare se $k=O(n)$

Contraddice il lower bound di $\Omega(nlog(n))$?
**No**, perchè l'**IntegerSort** **NON** è un algoritmo basato su confronti!!

**Una domanda**
Che complessità temporale ha l'IntegerSort quando $k=\omega(n)$, per esempio $k=\Theta(n^c)$, con c costante?
$$...T(n)=\Theta(n^c)...=\omega(nlog(n))$$
per $c\gt1$


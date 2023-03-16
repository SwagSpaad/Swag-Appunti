----
# Soluzione esercizio calcolo tempo medio con algoritmo di pesatura

Alg1
1. for i=2 to n do
	2. if peso($x_1$)>peso($x_i$) then return $x_1$
	3. if peso($x_1$)<peso($x_i$) then return $x_i$

$$T_{avg}(n)=\sum_{\text{istanze I di dimensione n}}(Pr(I)\cdot numpesate(I))$$
Dove $Pr(I)$ = $\frac{1}{n}$ perchè si assume che la moneta si trovi in modo equiprobabile in una delle n posizioni.
Il numero delle pesate è uguale ad 1 se la moneta è nella posizione j = 1 (la prima), altrimenti j - 1.

$$\implies (1/n)(1+\sum_{j=1}^{n-1}(j))=(1/n)(1+(n-1)(n/2))=\frac{1}{n}+ \frac{(n-1)}{2}$$
Quindi $$T_{avg}(n)=\frac{1}{n} + \frac{(n-1)}{2}$$
# Algoritmi ricorsivi
Vogliamo analizzare la complessità computazionale di un algoritmo ricorsivo, come ad esempio [[Capitolo 1 - Un'introduzione informale agli algoritmi#^b2d264|fibonacci2]]

Algoritmo**Fibonacci2** $(intero\ n)\rightarrow intero$
 1. $if \: (n\geq 2)\: then\ return \: 1$ 
 2. $else \: return$ Fibonacci2$(n-1)$ + Fibonacci2$(n-2)$
$$T(n) = T(n-1)+T(n-2)+O(1)$$
Come facciamo?

## Equazioni di ricorrenza
**Def.**
La **complessità computazionale** di un algoritmo ricorsivo può essere espressa in modo naturale attraverso una **equazione di ricorrenza**.
**Es.**
- $T(n)=T(n/3)+2T(n/4)+O(nlog(n))$ 
- $T(n)=T(n-1)+O(1)$
- $T(n)=T(n/3)+T(2n/3)+n$

>Caso base : $T(costante)=cost\: (oppure\: T(1)=1)$

### Metodo dell'iterazione

L'idea è quella di srotolare la ricorsione, ottenendo una sommatoria dipendente solo dalla dimensione n del problema iniziale.

**Es.**
$T(n)=c+T(n/2)$
$T(n/2)=c+T(n/4)\dots$
$T(n) = c + T(n/2)=c + (c + T(n/4)) = 2c + T(n/4) + \dots +ic + T(n/2^i)$

Continuo a srotolare fin quando non trovo il caso base, in questo caso quando $i=log_2(n)$, infatti:
$$T(n)=c\:log_2(n)+T(\frac{n}{2^{log_2(n)}}) =  c\: log_2(n)+T(\frac{\cancel{n}}{\cancel{n}})= c\cdot log_2(n)+T(1)=\Theta(log(n))$$
**Esercizi**
- esercizio 1: $T(n)=T(n-1)+n$
- esercizio 2: $T(n)=9T(n/3)+n$

### Albero della ricorsione (un modo grafico per pensare il metodo dell'iterazione)

Idea:
- disegnare l'albero delle chiamate ricorsive indicando la dimensione di ogni nodo
- stimare il tempo speso da ogni nodo dell'albero
- stimare il tempo complessivo "sommando" il tempo speso da ogni nodo

**Suggerimento 1**: se il tempo di ogni nodo è costante, T(n) è proporzionale al numero di nodi
**Suggerimento 2**: a volte conviene utilizzare l'albero per livelli:
- analizzare il tempo speso su ogni livello (fornendo upper bound)
- stimare il numero di livelli

**Esempio**
$T(n)=T(n-1)+1$

![[ASD/MOD I/img/img19.png|50]]
Quanto costa ogni nodo? ...uno!
Quanti nodi ha l'albero? **n**
Di conseguenza possiamo dire che $T(n)=\Theta(n)$

**Esempio**
$T(n)=T(n-1)+n$

![[ASD/MOD I/img/img19.png|50]]

Quanto costa ogni nodo? **...al più n**
Quanti nodi ha l'albero? **n**

Quindi possiamo dedurre che $T(n)=O(n^2)$
Ma vale $T(n)=\Theta(n^2)$?

![[ASD/MOD I/img/img20.png|250]]

Come possiamo vedere dalla foto, il lower bound è $T(n)\geq \frac{n}{2}\frac{n}{2}=\frac{n^2}{4}$ e quindi $T(n)=\Omega(n^2)$
Di conseguenza dato che T(n) è sia $O(n^2)$ (upper bound) che $Omega(n^2)$ (lower bound) allora $T(n)=\Theta(n^2)$ 

#### Albero binario completo

I nodi di un albero binario completo di altezza h sono dati dalla seguente formula $$\sum_{i=0}^h 2^i=2^{h+1}-1$$

**Es.**
$T(n) = 2T(n - 1) + 1$ 
$T(1) = 1$
![[ASD/MOD I/img/img21.png|center|500]]

Quanto mi costa ogni nodo? ...uno!
Quanto è alto l'albero? ...n-1
Quanti nodi ha l'albero? si usa la formula appena scritta e quindi $2^{h+1}-1$

Quindi possiamo dire che $T(n)=2^n-1=\Theta(2^n)$

**Esempio**
$T(n) = 2T(n - 1) + n$ 
$T(1) = 1$
![[ASD/MOD I/img/img21.png|center|500]]

Quanto costa ogni nodo? ...al più n
Quanto è alto l'albero? n-1
Quanti nodi ha? $2^{h+1}-1$

Quindi possiamo dire che l'upper bound è $T(n)\leq n2^n=\Theta(n2^n)$ 
$T(n)=O(n2^n)$
**Oss** $n2^n\:viene\:da\:n\cdot(2^n-1)\implies n2^n-n,con\:n\to\infty = n2^n$ 

**Un esempio un pò più complesso**

![[ASD/MOD I/img/img22.png]]

In questo esempio possiamo verificare che a sinistra l'albero scende ogni volta di n/3, mentre a destra scende di 3n/2; da questo possiamo dedurre che da sinistra l'albero sarà alto $log_3n$ mentre da destra $log_{3/2}n$

Studiamo upper bound e lower bound

**Upper bound**
Quanti costa ogni livello? $\leq n$ 
Quanti livelli ha l'albero? $O(log_{3/2}n)$

Quindi possiamo dire che $T(n)=O(nlog(n))$.(Upper Bound)
Perchè però $nlog_{3/2}n$? perchè ci sono $log_{3/2}n$ livelli, ognuno di costo al più n
Ma vale che $T(n)=\Theta(nlog(n))$?

**Lower bound**
Quanto costa ogni nodo? $\leq n$
Quanti livelli ha l'albero? $log_3n$
$T(n)\geq nlog_3n\implies T(n)=\Omega(nlog(n))$ 
Perchè però $nlog_3(n)$? perchè ci sono $log_3(n)$ livelli nell'albero, ognuno di costo al più n

La risposta alla domanda è si, $T(n)= \Theta(nlog(n))$ 
----
# I numeri di Fibonacci

Quanto velocemente si espanderebbe una popolazione di conigli sotto appropriate condizioni, partendo da una coppia di conigli?

Regole:
- Una coppia di conigli concepisce 2 conigli di sesso diverso ogni anno.
- La gestazione dura un anno
- I conigli si riproducono solo dopo il secondo anno
- I conigli sono immortali

## L'albero dei conigli
La riproduzione dei conigli può essere descritta in un albero:

![[ASD/MOD I/img/img0.png|center|600]]

### La regola di espansione

^3d1572

- Nell'anno n ci sono tutte le coppie dell'anno precedente e una nuova coppia di conigli per ogni coppia presente l'anno prima.
- Indicando con $F_n$ il numero di coppie dell'anno n abbaimo la seguente relazione di ricorrenza:
$$Fn= \begin{cases}
F_{n-1}+F_{n-2}&se\ \ n\geq3\\
1& se\ \ n=1,2
\end{cases}$$

## Come calcoliamo $F_n$?
### Approccio Numerico

Funzione matematica che calcola direttamente i numeri di Fibonacci.  
Si può dimostrare che:
$$F_n=\frac{1}{\sqrt5}\cdot(\phi^n - \hat{\phi}^n)$$
dove:
$$ \begin{cases}
\phi=\frac {1+\sqrt5}{2} \approx +1.618\\
\hat{\phi}=\frac {1-\sqrt5}{2} \approx -0.618
\end{cases}$$

#### Algoritmo Fibonacci 1
Algoritmo**Fibonacci1** $(intero\ n)\rightarrow intero$
 1. $return \frac{1}{\sqrt5}\cdot(\phi^n - \hat{\phi}^n)$

##### Correttezza?
Qual è l'accuratezza su $\phi \ e \ \hat{\phi}$ per ottenere un risultato corretto? Ad esempio, con 3 cifre decimali:
$\phi\approx 1.618 \ e \ \hat{\phi}\approx -0.618$

![[img0_1.jpg|center|500]]

#### Algoritmo Fibonacci 2

^b2d264

Utilizzare direttamente la [[#^3d1572|definizione ricorsiva]]
Algoritmo**Fibonacci2** $(intero\ n)\rightarrow intero$
 1. $if \: (n\geq 2)\: then\ return \: 1$ 
 2. $else \: return$ Fibonacci2$(n-1)$ + Fibonacci2$(n-2)$

##### Tempo di esecuzione
Un modello di calcolo rudimentale: ogni linea di codice costa un'unità di tempo.
Calcoliamo il numero di linee di codice mandate in esecuzione:
- Se n $\le$ 2: una sola riga di codice
- Se n = 3: quattro linee di codice, due per la chiamata Fibonacci2(3), una per la chiamata Fibonacci2(2) e una per la chiamata Fibonacci2(1)

##### Relazione di ricorrenza
T(n)= # linee di codice eseguite (nel caso peggiore) dall'algoritmo su istanza di dimensione n. In ogni chiamata si eseguono due linee di codice oltre a quelle eseguite nelle chiamate ricorsive $$T(n)=2+T(n-1)+T(n-2)$$
In generale, il tempo richiesto da un algoritmo ricorsivo è pari al tempo speso all'interno della chiamata più il tempo speso nelle chiamate ricorsive.  

##### Albero della ricorsione
Utile per risolvere la relazione di ricorrenza. I nodi corrispondono alle chiamate ricorsive, mentre i figli di un nodo corrispondono alle sottochiamate.

![[ASD/MOD I/img/img1.png|center|600]]

##### Calcolare $T(n)$
Etichettiamo i nodi dell'albero con il numero di linee di codice eseguite nella chiamata:
- I nodi interni hanno etichetta 2
- Le foglie hanno etichetta 1
Per calcolare $T(n)$ contiamo il numero di foglie e di nodi interni.

###### Lemma 1
Il numero di foglie dell'albero della ricorsione di $Fibonacci2(n)$ è pari a $F_n$
**Dim.**
![[ASD/MOD I/img/img2.png|center|500]]

###### Lemma 2
Il numero di nodi interni di un albero in cui ogni nodo interno ha due figli è pari al numero di foglie -1
**Dim.**
![[ASD/MOD I/img/img3.png|center|500]]

In totale le linee di codice eseguite sono: $$F_{n}+2 \cdot(F_{n}-1)=3F_{n}-2$$
**Oss.**
$Fibonacci2$ è un algoritmo lento:
$T(n) \approx F_n \approx \phi^n$

Linee di codice eseguite per $n=45$ $$T(n)=3\cdot F_{45}-2 = 3\cdot 1.134903.170-2=3.404.709.508$$
Possiamo fare meglio?
#### Algoritmo Fibonacci 3

Perchè l'algoritmo Fibonacci2 è lento? Perchè continua a ricalcolare ripetutamente la soluzione dello stesso sottoproblema. Perchè non memorizzare allora in un array le soluzioni dei sottoproblemi?  

Algoritmo**Fibonacci3**$(intero \ n )\rightarrow intero$
1. sia Fib un array di n interi
2. Fib[1] $\leftarrow$ Fib[2] $\leftarrow$ 1
3. $for$ i=3 $to$ n $do$
4. Fib[i]$\leftarrow$Fib[i-l]+Fib[i-2] 
5. $return$ Fib[n]  

##### Calcolo del tempo di esecuzione
 - Linee 1,2 e 5 eseguite una sola volta
 - Linea 3 eseguita $\leq$ n volte
 - Linea 4 eseguita $\leq$ n volte
 - $T(n)$: numero di linee di codice mandate in esecuzione da Fibonacci3
 $T(n)\leq n + n + 3 = 2n + 3$
 $T(45)\leq 93$
(38 milione di volte più dell'algoritmo $Fibonacci2$!)
- L'algoritmo $Fibonacci3$ impiega tempo proporzionale a n invece di esponenziale in n come Fibonacci2
- Tempo effettivo richiesto da implementazioni in C dei due algoritmi su piattaforme obsolete:
![[ASD/MOD I/img/img4.png]]

##### Occupazione di memoria
- Il tempo di esecuzione non è la sola risorsa di calcolo che ci interessa. Anche la quantità di **memoria necessaria** può essere cruciale. Se abbiamo un algoritmo lento, dovremo solo attendere più a lungo per ottenere il risultato, ma se un algoritmo richiede più spazio di quello a disposizione, non otterremo mai la soluzione, indipendentemente da quanto attendiamo!

#### Algoritmo Fibonacci4
Fibonacci3 utilizza un array di dimensione n prefissata, ma in realtà non serve mantenere tutti i valori di $F_n$ precedente, ma solo gli ultimi 2.
Algoritmo**Fibonacci4**$(intero \ n)\rightarrow intero$
 1. a $\leftarrow$ b $\leftarrow$ 1
 2. $for$ i=3 $to$ n $do$
	 3. c $\leftarrow$ a+b
	 4. b $\leftarrow$ a
	 5. a $\leftarrow$ c
 6. $return$ a

Possiamo calcolare $F_n$ in tempo inferiore a $O(n)$?
#### Algoritmo Fibonacci5
Algoritmo**Fibonacci5** $(intero \ n)\rightarrow intero$
1. M $\leftarrow \begin {bmatrix} 1 & 0 \\ 0 & 1 \end {bmatrix}$
2. for i = 1to n-1 do
3. M $\leftarrow$ M $\cdot \begin {bmatrix} 1 & 0 \\ 0 & 1 \end {bmatrix}$
4. return M $[\: 0 \:][\: 0 \: ]$
##### Cosa si guadagna?
- Si può calcolare la n-esima potenza elevando al quadrato la ($\lfloor$$\frac {n}{2}\rfloor$)-esima potenza
- Se n è dispari eseguiamo un'ulteriore moltiplicazione

##### Esempio
$3^2=9$ $3^4=9^2=81$ $3^8=81^2=6561$ $\implies$Si eseguono solo 3 prodotti invece di 7
 3 $\cdot$ 3 $\cdot$ 3 $\cdot$ 3 $\cdot$ 3 $\cdot$ 3 $\cdot$ 3 = $3^8$

#### Algoritmo Fibonacci6
Algoritmo**Fibonacci6**$(intero \ n )\rightarrow intero$
1. $A \leftarrow$ $\begin {bmatrix} 1 & 1 \\ 1 & 0 \end {bmatrix}$
2. $M \leftarrow potenzadiMatrice(A,n-1)$
3. Return M [0] [0]
Funzione potenzadiMatrici(Matrice A, intero k) $\implies$ matrice
4. if (k $\leq$ 1) return $\begin {bmatrix} 1 & 1 \\ 1 & 0 \end {bmatrix}$
5. else M $\leftarrow$ potenzadiMatrice (A, $\lfloor$$\frac {k}{2}\rfloor$)
6. M $\leftarrow$ M $\cdot$ M
7. if (k è dispari ) then M $\leftarrow$ M $\cdot$ A
8. return M

##### Tempo di esecuzione
Tutto il tempo è speso nella procedura potenzadiMatrice:
- All'interno della procedura si spedne tempo costante
- Chiamata ricorsiva con input $\lfloor \frac {k}{2}\rfloor$
L'equazione di ricorrenza è pertanto:
$$T(n) \leq T(\lfloor \frac {n}{2}\rfloor) + c$$

##### Metodo dell'iterazione
Risulta:
$T(n) \leq c + T(\lfloor \frac {n}{2}\rfloor)$ Fibonacci6 è esponenzialmente più
$\leq 2c + T(\lfloor \frac {n}{4}\rfloor)$ veloce di Fibonacci3
$\leq 3c + T(\lfloor \frac {n}{8}\rfloor)$
 :
$\leq ic + T(\lfloor \frac {n}{2^i}\rfloor)$

Per i= $\lfloor log_2 n\rfloor$ si ottiene
$T(n) \leq c + \lfloor log_2 n\rfloor + T(1)= O(log_2 n)$

##### Analisi memoria ausiliaria Fibonacci2
![[ASD/MOD I/img/img5.png|center|600]]

- Chiamate attive formano un cammino P radice-nodo
- P ha al più n nodi
- Ogni nodo / chiamata usa memoria costante

#### Riepilogo finale
![[ASD/MOD I/img/img6.png|center|600]]
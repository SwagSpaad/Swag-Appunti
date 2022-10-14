# Capitolo 2
## Modelli di calcolo e notazione asintotica
---
## Macchina di Turing
>![[img7.png|center|500]]
>- Struttura di basso livello, troppo differente dai calcolatori reali
>- Utile per la calcolabilità ma meno utile per l'efficienza

## Modello più realistico
>Macchina a registri (RAM: random access machine)
> - Un programma finito
> - Un nastro in ingresso ed uno in uscita
> - Memoria strutturata come un array
> - Ogni cella può contenere un qualunque valore intero/reale
> - Due registri PC e ACC
>La RAM è un'astrazione dell'architettura di von Neumann.  

### Macchina a registri
>![[img8.png|center|500]]
>Pc : Program Counter, prossima istruzione da eseguire
>ACC : Mantiene operandi le istruzioni correnti

## Modello di calcolo
>- L'analisi della complessità di un algoritmo è basata sul concetto di passo elementare
>- Passi elementari su RAM:
>	- Istruzioni di ingresso/uscita
>	- Opeazioni aritmetico/logiche
>	- Accesso/modifica del contenuto della memoria

### Criteri di costo
>- Criterio di costo uniforme:
>	- Tutte le operazioni hanno lo stesso costo
>	- Complessità temporale misurata come numero di passi elementari eseguiti
>- Criterio di costo logaritmico:
>	- Il costo dell'operazione dipende dalla dimensione degli operandi
>	- Un'operazione su un operando X ha costo log x
>	- È un criterio di costo che modella meglio la complessità di algoritmi “numerici”
>
>Generalmente si usa costo uniforme.  

## Caso peggiore, migliore e medio
>Misurazione di tempo su un algoritmo in funzione della dimensione n delle istanze, quindi, istanze diverse, a parità di dimensione potrebbero però richiedere tempo diverso.  
>Distinguiamo quindi ulteriormente tra analisi nel caso peggiore, migliore e medio.  

### Caso peggiore
>Sia $tempo(i)$ il tempo di esecuzione di un algorimto sull'istanza i $$T_{worst} (n) = max_{istanze \ i\ \ di \ dimensione \ n} \ {tempo(i)}$$
>Intuitivamente, $T_{worst}(n)$ è il tempo di esecuzione sulle istanze di ingresso che comportano più lavoro per l'algoritmo e rappresenta una garanzia sul tempo di esecuzione di ogni istanza.  

### Caso migliore
>Sia $tempo(i)$ il tempo di esecuzione di un algoritmo sull'istanza i $$T_{best}(n) = min_{istanze \ i \ di \ dimensione \ n} \ {tempo (i)}$$
>Intuitivamente, $T_{best} \ (n)$ è il tempo di esecuzione sulle istanze di ingresso che comportano meno lavoro per l'algoritmo. Significa davvero qualcosa? (spoiler: no).  

### Caso medio
>Sia $P(i)$ la probabilità di occorrenza dell'istanza $i$ $$T_{avg} \ (n) = \sum istanze \ i \ di \ dimensione \ n \ {P(i) \ tempo (i)}$$ 
>Intuitivamente, $T_{avg}(n)$ è il tempo di esecuzione nel caso medio, ovvero sulle istanze di ingresso "tipiche" per il problema.  
>- Come faccio a conoscere la distribuzione di probbailità sulle istanze?
>- Semplice: (di solito) non posso conoscerla $\implies$ Assunzione
>- Spesso è difficile fare assunzioni realistiche

## Notazione Asintotica

### Intuizioni

>- Complessità di un algoritmo espressa con una funzione T(n)
>- T(n): # passi elementari eseguiti su una RAM nel caso peggiore su un'istanza di dimensione n
>- Idea: Descrivere T(n) in modo qualitativo. Ovvero perdere la precisione ma guadagnare in semplicità.  

### T(n): # passi elementari eseguiti su una RAM nel caso peggiore su un'istanza di dimensione n

>**Es.**
>$$T(n)=\begin{cases} 71n^2+100\lfloor \frac{n}{4} \rfloor + 7 & se \ n \ è \ pari \\
\\
70n^2+150\lfloor \frac{n+1}{4} \rfloor + 5& se \ n \ è \ dispari
\end{cases}$$ 

Scriveremo: $T(n)= \Theta (n^2)$, questo vuol dire che $T(n)$ è proporzionale a $n^2$
Cioè ignoro:
- Costanti moltiplicative
- Termini di ordine inferiore (che crescono più lentamente)

### Una vecchia tabella: numero asintotico di pesate

Assunzione: ogni pesata richiede un minuto
>![[img9.png|center|500]]

### Dalla bilancia al computer

>Tempi di esecuzione di differenti algorimi per istanze di dimensione crescente su un processore che sa eseguire un milione di istruzioni di alto livello al secondo. L’indicazione very long indica che il tempo di calcolo supera 1025 anni.
>![[img10.png|center|500]]

### Notazione Asintotica $\mathcal {O}$

>**Def.**
>$f(n) = \mathcal {O} (g(n))$ se $\exists$ due costanti $C \geq 0 \ e \ n_{0} \geq 0$ t.c $0 \leq f(n) \leq c \cdot g(n)$
>![[img11.png|center|500]]
>>**Es.**
>>Sia $f(n) = 2n^2 + 3n$, allora
>>- $f(n) = \mathcal {O}(n^3)$ $(c=1, n_{0}=3)$
>>- $f(n) = \mathcal {O} (n^2)$ $(c = 3, n_{0} = 3)$
>>- $f(n) \ne \mathcal {O} (n)$
>
>Definizione insiemistica: 
>$\mathcal {O}(g(n))=\{f(n) \ | \ \exists \ c \geq 0 \ e \ n_{0} \geq 0 \ t.c. \ 0 \leq f(n) \leq c \cdot g(n) \ \forall \ n \geq n_0\}$
>
>>**Oss.**
>>La scrittura $2n^2+4= \mathcal {O}(n^3)$ è un abuso di notazione per $2n^2+4 \in \mathcal {O}(n^3)$
>
>![[img12.png|center|500]]

### Notazione Asintotica $\Omega$

>**Def.**
>$f(n)= \Omega (g(n))$ se $\exists$ due costanti $c>0$ e $n_0\geq 0$ t.c $f(n) \leq c\cdot g(n) \geq 0$ per ogni $n \geq n_0$
>![[img13.png|center|500]]
>>**Es.**
>>- $f(n) = \Omega (n)$ $(c=1, n_0=2)$
>>- $f(n)= \Omega (n^2)$ $(c=1, n_0=3)$
>>- $f(n) \ne \Omega (n^3)$
>
>Definizione insiemistica: 
>$\Omega(g(n))=\{f(n) \ | \ \exists \ c > 0 \ e \ n_{0} > 0 \ t.c. \ 0 \leq c \cdot g(n)\leq f(n) \ \forall \ n \geq n_0\}$
>
>>**Oss.**
>>La scrittura: $2n^2+4 = \Omega (n)$ è un abuso di notazione per $2n^2 + 4 \in \Omega (n)$
>
>![[img14.png|center|500]]

### Notazione Asintotica $\Theta$

>**Def.**
>$f(n) = \Omega(g(n))$ se $\exists$ tre costanti $c1, c2 > 0$ e $n_0 \geq 0$ t.c $c1 \cdot g(n) \geq f(n) \geq c2 \cdot g(n)$ $\forall$ $n\geq n_0$
>![[img15.png|center|500]]
>>**Es.**
>>Sia $f(n) = 2n^2 - 3n$, allora
>>- $f(n)= \Theta (n^2)$ $(c1= 1, c2= 2, n_0=3)$
>>- $f(n) \ne \Theta (n)$
>>- $f(n) \ne \Theta (n^3)$
>
>Definizione insiemistica: 
>$\Theta(g(n))=\{f(n) \ | \ \exists \ c_{1}, c_{2} > 0 \ e \ n_{0} \geq 0 \ t.c. \ c_{1}\cdot g(n)\leq f(n)\leq c_{2}\cdot g(n) \ \forall \ n \geq n_0\}$
>
>>**Oss.**
>>La scrittura: $2n^2 + 4 = \Theta (n^2)$ è un abuso di notazione per $2n^2 + 4 \in \Theta (n^2)$
>
>Notare che:
>![[img16.png|center|500]]

### Notazione Asintotica $\mathcal {o}$

>**Def.**
>Data una funzione $g(n): N \rightarrow R$, si denota con $o(g(n))$ l'insieme delle funzioni $f(n): N \rightarrow R:$
> $$o(g(n))= \{f(n)|\ \forall \ c>0 \ \exists \ n_0 \ t.c \ \forall \ n\geq n_0 \ \ 0 \leq f(n) <c \cdot g(n) \}$$ 
>> **Oss.** $\mathcal {o}(g(n)) \subset \mathcal {O}(g(n))$
>
>Definizione alternativa: $f(n) = o(g(n)) \iff \lim_{n\to \infty}\frac{f(n)}{g(n)}=0$ 

### Notazione asintotica $\omega$
>**Def.**
>Data una funzione $g(n): N \rightarrow R$, si denota con $o(g(n))$ l'insieme delle funzioni $f(n)$:
>$$\omega(g(n))= \{f(n)|\ \forall \ c>0 \ \exists \ n_0 \ t.c \ \forall \ n\geq n_0 \ \ 0 \leq \ c\cdot g(n)<f(n)\}$$ 
>> **Oss.** $\omega(g(n)) \subset \Omega(g(n))$
>
>Definizione alternativa: $f(n) = \omega(g(n)) \iff \lim_{n \to \infty} \frac{f(n)}{g(n)}= \infty$

#### In sintesi...
>![[img17.png|center|600]] 

### Proprietà della notazione asintotica
> - Transitività:
> $f(n) = \Theta(g(n)) \ e \ g(n) = \Theta(h(n)) \implies f(n) = \Theta(h(n))$
> $f(n) = O(g(n)) \ e \ g(n) = O(h(n)) \implies f(n) = O(h(n))$ 
> $f(n) = \Omega(g(n)) \ e \ g(n) = \Omega(h(n)) \implies f(n) = \Omega(h(n))$
> $f(n) = o(g(n)) \ e \ g(n) = o(h(n)) \implies f(n) = o(h(n))$
> $f(n) = \omega(g(n)) \ e \ g(n) = \omega(h(n)) \implies f(n) = \omega(h(n))$
> - Riflessività: 
> $f(n)=\Theta(f(n))$
> $f(n)=O(f(n))$
> $f(n)=\Omega(f(n))$
> - Simmetria:
> $f(n)=\Theta(g(n)) \iff g(n)= \Theta(f(n))$
> - Simmetria trasposta: 
> $f(n)=O(g(n)) \iff g(n)= \Omega(f(n))$
> $f(n)=(g(n)) \iff g(n)= \omega(f(n))$

### Velocità asintotiche di funzioni composte
>Date $f(n) \ e \ g(n)$ la velocità ad andare ad infinito della funzione $f(n)+g(n)$ è la velocità della più veloce tra $f(n) \ e \ g(n)$.
>
>>**Es.**
>>$n^{3}+n = \Theta(n^{3})$
>>$n + \log^{10}{n} = \Theta(n)$
>
>Date $f(n) \ e \ g(n)$ la velocità ad andare ad infinito della funzione $f(n)\cdot g(n)$ è la velocità di $f(n)$ "sommata" alla velocità di $g(n)$, mentre la velocità ad andare all'infinito della funzione $f(n)/g(n)$ è la velocità di $f(n)$ "meno" la velocità di $g(n)$.
>
>>**Es.**
>>$$\frac{n^{3}\cdot \log{n}+ \sqrt{n}\cdot \log^{3}{n}}{n^2+1} = \Theta(n\cdot \log n)$$ 


## Usare la notazione asintotica nelle analisi
### Analisi complessità [[Capitolo 1 - Un'introduzione informale agli algoritmi#^dd465d|fibonacci3]]: un upper bound
>Algoritmo**Fibonacci3**$(intero \ n )\rightarrow intero$
>1. sia Fib un array di n interi
>2. Fib[1]$\leftarrow$Fib[2]$\leftarrow$ 1
>3. $for$ i=3 $to$ n $do$ 
>	4. Fib[i]$\leftarrow$Fib[i-l]+Fib[i-2]
>5. $return$ Fib[n]
>
>$T(n)$: complessità computazionale nel caso peggiore con input n
>$c_{j}:$ numero di passi eseguiti su una RAM quando è eseguita la linea di codice j
>
>- Linea 1, 2, 5 eseguite una volta
>- Linea 3, 4 eseguite al più n volte
>
>$T(n) \leq c_{1}+c_{2}+c_{5}+(c_{3}+c_{4})\cdot n = \Theta(n) \implies T(n) = O(n)$

### Analisi complessità [[Capitolo 1 - Un'introduzione informale agli algoritmi#^dd465d|fibonacci3]]: un lower bound
>Algoritmo**Fibonacci3**$(intero \ n )\rightarrow intero$
>1. sia Fib un array di n interi
>2. Fib[1]$\leftarrow$Fib[2]$\leftarrow$ 1
>3. $for$ i=3 $to$ n $do$ 
>	4. Fib[i]$\leftarrow$Fib[i-l]+Fib[i-2]
>5. $return$ Fib[n]
>
>$T(n)$: complessità computazionale nel caso peggiore con input n
>$c_{j}:$ numero di passi eseguiti su una RAM quando è eseguita la linea di codice j
>
>- La linea 4 è eseguita almeno n-3 volte
>
>$T(n) \geq c_{4}\cdot (n-3) = \Theta(n) \implies T(n) = \Omega(n)\implies T(n) = \Theta(n)$

### Conclusioni sulla notazione asisntotica
>- Fornisce una misura indipendente dall'implementazione dell'algoritmo e dalla macchina reale su cui è eseguito. 
>- Un'analisi dettagliata del numero di passi realmente eseguiti sarebbe difficile e non direbbe molto di più
>- I dettagli trascurati sono irrilevanti quando n è molto grande

## Esempi di calcolo del caso medio
### Ricerca di un elemento in una lista non ordinata
>L'algoritmo restituisce la posizione di x in L se è presente, altrimenti -1
>Algoritmo**RicercaSequenziale**$(array \ L, \ elem \ x)\rightarrow intero$ 
>1. n = lunghezza di L
>2. i = 1
>3. for i = 1 to n do
>	4. if (L[i] = x) then return i (trovato)
>5. return -1 (non trovato)
>
>T(n): numero elementi acceduti (linea 4) su un array di dimensione n
>	$T_{best}(n)=1$ (caso in cui x è in prima posizione)
>	$T_{worst}(n)=n$ (caso in cui x è in ultima posizione o non è nella lista)
>	$T_{avg}(n)=(n+1)/2$ (si assume che $x \in L$ e che si trovi con la stessa probabilità in una qualsiasi posizione). 
>	
>T(n): numero di operazioni RAM su array di dimensione n
>	$T_{best}(n)=O(1)$ (caso in cui x è in prima posizione)
>	$T_{worst}(n)=\Theta(n)$ (caso in cui x è in ultima posizione o non è nella lista)
>	$T_{avg}(n)=\Theta(n)$ (si assume che $x \in L$ e che si trovi con la stessa probabilità in una qualsiasi posizione). 
### Ricerca di un elemento in una lista ordinata
>Algoritmo di ricerca binaria (binary search); gli indici i e j indicano la porzione di L in cui si vuole cercare x. 
>algoritmo**RicercaBinaria**$(array\ L,\ elem \ x,\ int \ i,\ int \ j )\rightarrow intero$ 
>	1. if$(i > j)$ return -1
>	2. m = $(i+j)/2$
>	3. if (L[m] = x) then return m
>	4. if (L[m] > x) then return **RicercaBinaria**$(L,\ x,\ i,\ m-1)$
>		else return **RicercaBinaria**$(L,\ x,\ m+1,\ j)$
>
>$T(n) = T(n/2)+O(1) \implies T(n)=O(\log n)$  
>
>![[img18.png|center|500]]

## Ricorsione, tecniche di progettazione ed equazioni di ricorrenza
### Algoritmi ricorsivi: come analizzarli?
>$Algoritmo$ Fibonacci2$(intero\ n)\rightarrow intero$
> 1. $if (n\geq 2) then\ return 1$ 
> 2. $else return$ Fibonacci2$(n-1)$ + Fibonacci2$(n-2)$
> 
> $T(n)= T(n-1)+T(n-2)+O(1)$
>
> algoritmo**RicercaBinaria**$(array\ L,\ elem \ x,\ int \ i,\ int \ j )\rightarrow intero$ 
>1. if$(i > j)$ return -1
>2. m = $(i+j)/2$
>3. if (L[m] = x) then return m
>4. if (L[m] > x) then return **RicercaBinaria**$(L,\ x,\ i,\ m-1)$
>	else return **RicercaBinaria**$(L,\ x,\ m+1,\ j)$
>
>$T(n)=T(n/2)+O(1)$

### Equazioni di ricorrenza
>La complessità computazionale di un algoritmo ricorsivo può essere espressa in modo naturale attraverso una equazione di ricorrenza.
>>**Es.**  
>>$T(n)=T(n/3)+2T(n/4)+O(n\log n)$
>>$T(n)=T(n-1)+O(1)$
>>$T(n)=T(n/3)+T(2n/3)+O(n)$
>
>Come troviamo il caso base di queste equazioni?

#### Metodo di iterazione
>Idea: srotolare la ricorsione, ottenendo una sommatoria dipendente dalla dimensione n del problema iniziale
>>**Es. 1**  
>>$\begin{align}T(n)&= c+T(n/2) \\ &= 2c+T(n/4)\\ &= 3c+T(n/8)\\&\vdots\\&= i\cdot c+ T(n/2^i)\end{align}$
>>Per $i = \log_{2}n: \ T(n)=c\cdot \log_{2}n+T(1)=\Theta(\log n)$  
>
>
>>**Es.2**  
>>$\begin{align}T(n)&= T(n-1) +1 \\ &= T(n-2)+2\\ &= T(n-3)+3\\&\vdots\\&= T(n-i)+i\end{align}$
>>Per $i= n-1:\ T(n)=T(1)+n-1=\Theta(n)$ 
>
>>**Es.3**  
>>$\begin{align}T(n)&= 2T(n-1) +1 \\ &= 4T(n-2)+2+1\\ &= 8T(n-3)+4+2+1\\&\vdots\\&= 2^i\cdot T(n-i)+\sum_{j=0}^{i-1}2^j\end{align}$
>>Per $i= n-1:\ T(n)=2^{n-1}T(1)+\sum_{j=0}^{n-2}2^j=\Theta(2^n)$ 
>
>
>>**Es.4**  
>>$\begin{align}T(n)&= T(n-1)+T(n-2) +1 \\ &= T(n-2)+2T(n-3)+T(n-4)+3\\ &= T(n-3)+3T(n-4)+3T(n-5)+T(n-6)+7\\&\vdots\end{align}$
>
>Questa è un po' tosta da risolvere col metodo dell'iterazione, vediamo un nuovo metodo.  
>

#### Albero della ricorsione
>Idea: 
>- Disegnare l'albero delle chiamate ricorsive indicando la dimensione di ogni nodo
>- Stimare il tempo speso da ogni nodo dell'albero
>- Stimare il tempo complessivo "sommando" il tempo speso da ogni nodo
>>**Oss.**
>>Se il tempo speso da ogni nodo è costante allora T(n) è proporzionale al numero di nodi
>
>
>>**Es.1**  
>>$T(n) = T(n-1)+1$
>>$T(1) = 1$ 
>>![[img19.png|60]]
>>Quanto costa ogni nodo? uno
>>Quanti nodi ci sono? n
>>$\implies T(n)=\Theta(n)$ 
>
>
>>**Es.2**  
>>$T(n) = T(n-1)+n$
>>$T(1) = 1$ 
>>![[img19.png|60]]
>>Quanto costa ogni nodo? al più n
>>Quanti nodi ci sono? n
>>$\implies T(n)=O(n^2)$ 
>>La ricorsione $T(n)$ vale $\Theta(n^2)$? Verifichiamo.
>>![[img20.png|300]]
>>Dato che $T(n)$ è sia $\Omega(n^2)$ che $O(n^2)$ allora $T(n)=\Theta(n^2)$
>
>
>>**Es.3**  
>>$T(n)=2T(n-1)+n$
>>$T(1)=1$
>>![[img21.png|center|500]]
>>Quanto costa ogni nodo? ...al più n
>>Quanto è alto l'albero? n-1
>>Quanto nodi ha un albero binario completo di altezza h? $\sum_{i=0}^h 2^i$ 
>>$\implies T(n)\leq n2^n=\Theta(n2^n)$ 
>
>.
>>**Es.4**  
>>$T(n)=T(n/3)+T(2/3\cdot n)+n$
>>$T(1)=1$
>>![[img22.png|center|500]] 


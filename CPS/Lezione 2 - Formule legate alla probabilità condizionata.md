# Lezione 2
## Formule legate alle [[Lezione 1 - Introduzione alla probabilità#Probabilità condizionata di A dato B|probabilità condizionata]]. Indipendenza tra eventi.
---
## Regola del prodotto
A partire da $P(A|B) = \frac{P(A \cap B)}{P(B)}$ si ottiene $P(A\cap B)= P(A|B)\cdot P(B)$. Questa formula è utile quando la probabilità condizionata segue dal testo dell'esercizio e la probabilità dell'intersezione degli eventi è la grandezza da calcolare.  
La formula può essere utilizzata anche per calcolare l'intersezione di più di due eventi.  
>**Es.**
>$$P(A\cap B\cap C) = P(A|B\cap C)\cdot P(B|C)\cdot P(C)$$ 

## Formula delle probabilità totali
Supponiamo di avere una partizione di eventi finita o numerabile: $$\{ E_{i} : i \in I\} \:\: con \:\: I = \set{1, 2,..., n} \:\: oppure \: \: I = \set{1, 2,..., n}$$ Questo significa che $\bigcup_{i \in I} E_{i} = \Omega$ e che $E_{i} \cap E{j}= \varnothing$ per $i \neq j$. Inoltre sia $A$ un altro evento.  
Si usa questa formula per calcolare $P(A)$ quando si conoscono $\{P(E_{i})\}_{i \in I}$ e $\{P(A|E_{i})\}_{i \in I}$ (per i valori per cui $P(E_{i})\neq 0$). Allora: $$P(A) = P(A \cap \Omega) = P(A \cap (\bigcup_{i \in I} E_{i})) = P(\bigcup_{i \in I}(A \cap E_{i})) = \sum_{i \in I} P(A \cap E_{i})$$ Ora per ciascun addendo per cui $P(E_{i}) \neq 0$ si ha $P(A\cap E_{i}) = P(A|E_{i})\cdot P(E_{i})$.  
In conclusione:
>$$P(A) = \sum_{i\in I} P(A|E_{i})\cdot P(E_{i})$$

^78ef9d

Un caso particolare è quello in cui la partizione è costituita da due eventi: $$\begin{cases}
E_{1} = E \\
\\
E_{2} = E^{c}
\end{cases} \implies P(A) = P(A|E)\cdot P(E) + P(A|E^{c})\cdot P(E^{c})$$

### Diagramma ad albero associato alla formula delle probabilità totali
Si può costruire un diagramma ad albero associato in cui ogni diramazione fa riferimento ad una partizione. Ad ogni arco si associa una possibilità. Per fissare le idee consideriamo il caso $I = \set{1, 2, 3}$. ![[CPS/img/img1.png|center|400]] 
Siamo interessati a tutte la foglie che finiscono con A indicate da un asterisco. Si deve considerare la somma dei cammini che finiscono con A ottenuti con i prodotti dei pesi dei rami. 
>$$P(A) = P(A|E_{1})\cdot P(E{1})+ P(A|E_{2})\cdot P(E{2}) + P(A|E_{3})\cdot P(E{3})$$

## Formula di Bayes
Supponiamo che $P(A|B) = \frac{P(A\cap B)}{P(B)}$ con l'ipotesi $P(B) \neq 0$. Inoltre $A\cap B$ e $B\cap A$ sono lo stesso evento; quindi: $$P(A\cap B)=P(B\cap A)=P(B|A)\cdot P(A)$$
che, sostituendolo nella formula iniziale si ottiene: $$P(A|B)=\frac{P(B|A)\cdot P(A)}{P(B)}$$
>Questa formula si usa quando viene chiesta una probabilità condizionata $P(A|B)$ e la possibilità condizionata $P(B|A)$ si calcola facilmente, e comunque questo è più agevole rispetto a valutare l'evento intersezione $A \cap B$.

Negli esercizi questa formula si usa combinandola con la [[#Formula delle probabilità totali|formula delle probabilità totali]] per calcolare il denominatore $P(B)$. In altri termini negli esercizi si potrà fare riferimento ad una partizione $\{E_{n}\}_{n \in I}$ finita o numerabile, sapremo calcolare facilmente $P(B|E_{n})$ per $n \in I$, e verrà chiesto di calcolare la probabilità condizionata del tipo $P(E_{n}|B)$ per $n \in I$. Quindi si otterrà: $$P(E_{n}|B)=\frac{P(B|E_{n})\cdot P(E_{n})}{\sum_{i \in I}P(B|E_{i})\cdot P(E_{i})} \: \: \: per \:\: n \in I$$

## Indipendenza tra eventi
Iniziamo con il caso di due eventi. Siamo interessati al caso in cui: $$P(A|B)=P(A) \; \text{ se } \; P(B) \neq 0$$ oppure al caso $$P(B|A)=P(B) \; \text{ se } \; P(A) \neq 0$$
In questo caso abbiamo due concetti apparentemente diversi. Inoltre sembra che si debbano escludere in qualche caso gli eventi con possibilità zero. In realtà la trattazione è più semplice e consideriamo la seguente definizione dove gli eventi di probabilità zero sono consentiti.  
>**Def.**
>$A, B \in \mathcal{A}$ sono indipendenti se $P(A\cap B)=P(A)\cdot P(B)$
>>**Oss.**
>>Se A e B sono indipendenti allora lo sono anche B e A, infatti $A\cap B=B\cap A$ e il prodotto tra due numeri è il contrario

>**Proposizione**
>Siano $A, B \in \mathcal{A}$ con $P(B)\neq 0$. Allora: $$A \: e \: B \text{ sono indipendenti} \iff P(A|B) = P(A)$$

>**Dim.**
>Si ha: 
>$A \:\: e \:\: B$ indipendenti $\iff P(A\cap B) = P(A)\cdot P(B) \iff \frac{P(A\cap B)}{P(B)} = \frac{P(A)\cdot \cancel{P(B)}}{\cancel{P(B)}}$
>$\iff P(A|B) = P(A) \: \text{( definizione di prob. cond. )}$ 

### Conseguenze
1. Se un evento ha probabilità zero, allora è indipendente da qualunque altro. Infatti, se prendiamo $A, B \in \mathcal{A} \: con \: P(A)=0$, si ha: 
$$\begin{cases}
0 \leq P(A\cap B) \leq P(A) = 0 \implies P(A\cap B)= 0 \\
\\
P(A)\cdot P(B) = 0\cdot P(B)= 0
\end{cases}$$
e quindi $\underbrace{P(A\cap B)}_{= \:0} =\underbrace{P(A)\cdot P(B)}_{= \:0}$.
2. Supponiamo che A e B siano indipendenti. Allora, se uno dei due eventi, o entrambi, vengono complementati, allora abbiamo ancora eventi indipendenti: $$\begin{align}A^{c} \: e \: B&, \text{ sono indipendenti} 
\\ A \: e \: B^{c}&, \text{ sono indipendenti} 
\\ A^{c} \: e \: B^{c}&, \text{ sono indipendenti }
\end{align}$$
>**Dim.**
>Si ha $P(A\cap B) = P(A) \cdot P(B)$ per ipotesi. Allora:    
>$P(A^{c}\cap B) = P(B) - P(A\cap B)= P(B)- P(A) \cdot P(B) =$
>$P(B)\cdot (1 - P(A)) = P(B)\cdot P(A^{c}). \Box$ 

3. Mettendo insieme 1. e 2. si ha che se un evento ha probabilità 1, allora è indipendente da qualunque altro. Del resto abbiamo già visto che se: $$P(B) = 1 \implies P(A|B)= P(A)$$


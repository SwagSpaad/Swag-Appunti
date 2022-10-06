# Lezione 1
## Introduzione alla probabilità. 
---
## Fenomeno aleatorio
>**Def.**  
>Un fenomeno è detto **aleatorio** se il suo esito è incerto.

L'**insieme dei possibili** esiti di un evento viene indicato con $\Omega$.  
Un fenomeno aleatorio si distingue in:
- **Discreto**: se $\Omega$ è finito o numerabile.
	>**Es.**  
	>i.) $\Omega$ finito: risultati del lancio di un dado. $\Omega = \set{1, 2, ..., 6}$  
	>ii.) $\Omega$ numerabile: numero di telefonate ricevute da un centralino in un intervallo di tempo. $\Omega = \set{1, 2, ...}$

- **Continuo**: se $\Omega$ è più che finito.
	>**Es.**  
	>iii.) Tempo di funzionamento di una lampadina rispetto una certa unità di misura. $\Omega = (0, \infty)$
## Famiglia di eventi
>**Def.**  
>Una **famiglia di eventi** $\mathcal{A}$ è una famiglia di sottoinsiemi di $\Omega$.   
>>**Es.**  
>>i.) "Esce un numero pari" $\rightarrow \mathcal{A} = \set{2,4,6} \subset \Omega = \set{1,2,...,6}$  
>>ii.) "Il centralino riceve al più 2 telefonate" $\rightarrow \mathcal{A} = \set{0,1,2} \subset \Omega = \set{0,1,...}$  
>>iii.) "La lampadina si fulmina al tempo t = 5 s" $\rightarrow \mathcal{A} = (5, \infty) \subset \Omega = (0, \infty)$   
## $\sigma$-algebra
Sia $\Omega$ un insieme non vuoto e $\mathcal{A} \subset \mathcal{P}(\Omega)$.  
>**Def.**  
>$\mathcal{A}$ è una **$\sigma$-algebra** di eventi se:  
>- $\Omega \in \mathcal{A}$
>- $\forall \: A \in \mathcal{A} \implies A^{c} \in \mathcal{A}$
>- $\forall \: \{ A_{n} \}_{n \geqslant 1} \implies \bigcup_{n \geqslant 1} A_{n} \in \mathcal{A}$
## Misura di probabilità
Sia $\Omega$ un insieme non vuoto e $\mathcal{A}$ una $\sigma$-algebra di eventi.
>**Def.**  
>Una funzione $P:\mathcal{A}\rightarrow[0,\infty)$ (che in realtà assume valori in $[0, 1]$) è una **misura di probabilità** se:  
>i.) $\mathcal{P}(\Omega) = 1$
>ii.) $\forall \{A_{n} \}_{n \geqslant 1} \subset \mathcal{A}$  t.c.  $A_{m}\cap A_{n}\: \forall \: m\neq n$ si ha $P(\bigcup_{n\geq1} A_{n}) = \sum_{n\geq1}P(A_{n})$

^a8e10e

### Conseguenze della def. di misura di probabilità
1. $\mathcal{P}(\varnothing) = 0$
>**Dim.**
>Infatti, considerando $A_{n} = \varnothing\:\: \forall n \geq 1$ , si ha $A_{m}\cap A_{n} = \varnothing$ per $m \neq n$ da cui segue  
>$$\begin{cases}P(\bigcup_{n=1}^ \: A_{n}) = P(\bigcup_{n=1} \: \varnothing) = P(\varnothing) \\
\\
\sum_{n = 1}P(A_{n}) = \sum_{n = 1}P(\varnothing)  
\end{cases}\implies P(\varnothing) = \sum_{n = 1}P(\varnothing)$$
Questa condizione non può essere vera se $P(\varnothing) >0$; infatti il secondo membro sarebbe infinito (e il primo membro finito) e per questo l'uguaglianza $n=\infty$ (n finito) non risulterebbe corretta. $\Box$ 
2. Poniamo $h \geq 1$ e $B_{1},...,B_{h} \in \mathcal{A}$ con $B_{m} \cap B_{n} = \varnothing$ per $m \neq n \: (m, n \in \set{1,..., h})$ allora:  
$$P(\bigcup_{n=1}^{h} \: B_{n}) = \sum_{n = 1}^{h} P(B_{n})$$ 
> **Dim.**
> Facendo riferimento alla condizione [[#^a8e10e|ii.)]] della def. ponendo $A_{1}=B_{1},...,A_{h}=B_{h},A_{h+1}=A_{h+2}=...=\varnothing$ . Con questa condizione si ha $A_{m}\cap A_{n} = \varnothing$ per $m \neq n$ e si ha $$P(\bigcup_{n=1} \: A_{n}) = \sum_{n = 1} P(A_{n})$$
> Da cui segue: $$\begin{cases}
\bigcup_{n \geq 1} A_{n}=B_{1}\cup...\cup B_{h}\cup\varnothing...\cup\varnothing=\bigcup_{n=1}^{h}B_{n})\implies P(\bigcup_{n=1}A_{n})=P(\bigcup_{n=1}^{h}B_{n}) \\
\\
\sum_{n = 1} P(A_{n}) = P(B_{1}) + ... + P(B_{h}) + P(\varnothing) + ... + P(\varnothing) = \sum_{n=1}^{h}P(B_{n})\end{cases}$$ ottenendo quindi: $$P(\bigcup_{n=1}^{h} \: B_{n}) = \sum_{n = 1}^{h} P(B_{n})$$ . $\Box$
3. Vediamo un caso specifico. Prendiamo due insiemi $E, F \in \mathcal{A}, h=2, B_{1} = E \cap F,$ $B_{2} = E \cap F^{c}$.   Allora $P((E \cap F) \cup (E \cap F^{c}))$ = $P(E \cap F)+P(E \cap F^{c}) = P(E)$.
>**Dim.**
>Semplice dimostrazione $\rightarrow E = (E \cap F)\cup(E \cap F^{c})$. (Provare con i diagrammi di Venn). $\Box$  
- Analizziamo ora il caso in cui E = $\Omega$. Dal punto i.) della definizione di [[#^a8e10e|misura di probabilità]] sia ha $P(E) = 1$ da cui possiamo ottenere $P(E) = P(F) + P(F^{c})$  $\forall F \in \mathcal{A}$ e le varie formule inverse. 

## Spazio di probabilità
>**Terminologia**: la terna $(\Omega,\mathcal{A},P)$ è detta **spazio di probabilità**.  


### Spazio di probabilità uniforme discreto
Questa terminologia si utilizza nel caso in cui si ha la seguente situazione:
- $\Omega$ insieme finito ($\Omega = \set{1, 2,...., n}$)
- $\mathcal{A} = \mathcal{P}(\Omega)$ 
- $\forall \: A \in \mathcal{A} \rightarrow P(A) = \frac{|A|}{|\Omega|} = \frac{|A|}{n}$  
Questa situazione viene fuori imponendo la seguente condizione:  
$\forall \omega \in \Omega \longrightarrow P(\{\omega\})$ assume sempre lo stesso valore.  
>**Dim.**
>Se indichiamo il valore di $P(\{\omega\})$ con p, allora otteniamo le seguenti uguaglianze:$$1 = P(\Omega) = P(\bigcup_{\omega\in\Omega}\{\omega\}) = \sum_{\omega\in\Omega}P(\{\omega\})=\sum_{\omega\in\Omega}p=np\implies p = \frac{1}{n}$$ Allora per ogni $A\in\mathcal{A}$ si ha: $$P(A) = P(\bigcup_{\omega\in A}\{\omega\}) = \sum_{\omega\in A}P(\{\omega\})=p\:\cdot\: |A| = \frac{|A|}{n} $$  . $\Box$  

## Probabilità condizionata di A dato B
Sia $(\Omega,\mathcal{A},P)$ uno spazio di probabilità. Siano $A, B \in \mathcal{A}$ con $P(B)\neq0$.  
>**Def.**  
>	Si definisce **"probabilità condizionata di A dato B"** la seguente quantità: $$P(A|B) = \frac{P(A\cap B)}{P(B)}$$


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
>Una funzione $P:\mathcal{A}\rightarrow[0,\infty)$ è una **misura di probabilità** se:  
>- $\mathcal{P}(\Omega) = 1$
>- $\forall \{A_{n} \}_{n \geqslant 1} \subset \mathcal{A}$  t.c.  $A_{m}\cap A_{n}\: \forall \: m\neq n$ si ha $P(\bigcup_{n\geq1} A_{n}) = \sum_{n\geq1}P(A_{n})$
### Conseguenze della Def. di misura di probabilità
1. $\mathcal{P}(\varnothing) = \varnothing$  
	Infatti, considerando $A_{n} = \varnothing\:\: \forall n \geq 1$ , si ha $A_{m}\cap A_{n} = \varnothing$ per $m \neq n$ da cui segue  
	![[img0.png]]
Questa condizione non può essere vera se $\mathcal{P}(\varnothing) >0$; infatti il secondo membro sarebbe infinito (e il primo finito) e per questo l'uguaglianza $0=0$ non risulterebbe.  
## Spazio di probabilità
>**Terminologia**: la terna ($\Omega,\mathcal{A},P$) è detta **spazio di probabilità**

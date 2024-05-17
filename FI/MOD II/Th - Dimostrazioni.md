# Equivalenza testine solidali e indipendenti

Dato che una macchina a testine solidali può essere considersata come una particolare macchina a testine indipendenti in cui, ogni volta che si esegue una quintupla, tutte le testine si muovono allo stesso modo, allora **tutto quello che facciamo con il modello a testine solidali, possiamo farlo anche con quello a testine indipendenti**

## Inverso
**Da testine indipendendi a testine solidali**
Consideriamo una macchina di Turing $T_{2}$
a testine indipendenti dotata di 2 nastri e mostriamo come simularne il comportamento mediante una macchina di Turing $T_{3}$ a testine solidali. 
I primi 2 nastri di $T_{3}$ sono *inizialmente* una copia dei nastri di $T_{2}$, invece il terzo nastro contiene solo il carattere "\*" non appartenente a $\Sigma$ nella cella di indirizzo $0$ che ha lo scopo di segnalare alle testine dove posizionarsi **Da continuare**   

# Accettabilità e Decidibilità
## Th 3.1
Un linguaggio $L$ è decidibile $\iff$ $L$ e $L^{c}$ sono accettabili
**Dim**:
$L$ decidibile $\implies$ $L$ e $L^{c}$ sono accettabili
- $L$ è decidibile quindi esiste mdt $M$ che decide $L$ e quindi accetta tutte le stringhe $x \in L$ e rifiuta tutte le stringhe $x \notin L$ (quindi $x \in L^c$).
- $L$ è accettabile perchè se $M$ accetta una stringa $x$ allora $x \in L$ 
- $L^c$ è accettabile perchè $M$ rifiuta tutte le stringhe non in $L$. Costruiamo una macchina $M^c$ che decide $L^c$, scambiando gli stati di accettazione e rifiuto di $M$. Questa macchina acdetterà tutte le stringhe in $L^c$ e rigfiuterà tutte le stringhe non in $L^c$
 
 $L$ e $L^{c}$ sono accettabili $\implies$ $L$ è decidibile
 -  $L$ e $L^{c}$ sono accettabili e quindi esistono 2 mdt $M1$, $M2$ che accettano rispettivamente $L$ è $L^{c}$. Vogliamo costruire una mdt $M$ che decide $L$ simulando simultaneamente $M1$ e $M2$ sullo stesso input. Poichè $L$ ed $L^c$ sono complementari, data una stringa $x$ deve appartenere ad uno soltanto dei due linguaggi
 - Se $x \in L$ viene accettato da $M1$
 - Se $x \in L^c$ viene accettato da $M2$ 
 Quindi $M$ può decidere $L$ osservando quali tra le due mdt $M1$, $M2$ accetta l'input $x$.
 $$\begin{align*}
O_{M}(x)=q_{A}\iff O_{M1}(x)=q_A\\
\\
O_{M}(x)=q_{R}\iff O_{M2}(x)=q_A
\end{align*}$$

# Funzioni Calcolabili
## Th 3.2
Un linguaggio $L$ è decidibile $\iff$ la funzione $\chi_L$ è calcolabile
**Dim.**
$L$ decidibile $\implies$ $\chi_{L}$ calcolabile
- $L$ decidibile, quindi esiste mdT $M$ che accetta ogni stringa $x\in L$ e rifiuta ogni stringa $x\notin L$. 
- A partire da $M$ costruiamo $M^{'}$ a due nastri che calcola $\chi_{L}$: 
	- Dato input $x$ sul primo nastro, $M^{'}$ simula $M$. Se $M$ accetta, $M^{'}$ scrive $1$ sul secondo nastro, se $M$ rifiuta, $M^{'}$ scrive $0$ sul secondo nastro.
	- Poiché $M$ decide $L$, termina per ogni input $x$, di conseguenza $M^{'}$ termina su ogni input $x$ restituendo $\chi_{L}(x)$. Questo dimostra che $\chi_{L}$ è calcolabile

$\chi_{L}$ calcolabile $\implies$ $L$ decidibile
- $\chi_{L}$ calcolabile, quindi esiste mdT $M^{'}$ che dato input $x$ calcola $\chi_L(x)$ e termina per ogni input. 
- A partire da $M^{'}$ costruiamo $M$ che decide $L$:
	- dato $x$ $M$ simula $M^{'}(x)$, se $M^{'}$ scrive $1$ allora $M$ accetta $x$, se $M^{'}$ scrive $0$ allora $M$ rigetta $x$
Poiché $M'$ calcola $\chi_L$ e termina su ogni input, $M$ termina su ogni input $x$, accettando se $x\in L$ e rigettando se $x\notin L$.

# Halting problem
## Th 5.4
$L_{H}$ è un linguaggio accettabile

**Dim.**
Costruzione macchina $U^{'}$ che è una modifica di macchina universale $U$.
Su $N_{1}$ scriviamo codifica mdT $i$, su $N_2$ scriviamo input $x\in\{0,1\}^{*}$. 
$U^{'}$ inizia la computazione verificando che $i$ sia la codifica di una mdT e quindi che non contenga $8\ e\ 9$ e che inizi con $2$. In seguito $U^{'}$ simula $U$ e se $U$ termina (sia in accettazione che rigetto) allora $U^{'}$ termina in accettazione.

Dobbiamo dimostrare che esiste una mdT che per ogni $(i,x)\in\mathbb{N}\times\mathbb{N}$ allore $$O_{T}(i,x)=q_A\iff(i,x)\in L_{H}$$
- $(\impliedby)$ Sia $(i,x)\in L_{H}$, allora la computazione $T_{i}(x)$ termina (definizione Halting Problem) e quindi $U^{'}(x)$ accetta
- $(\implies)$ Sia $(i,x)\in\mathbb{N}\times\mathbb{N}$ tale che $U^{'}(i,x)$ accetta; poiché $U^{'}$ simula $U$, allora anche $U$ termina e dunque $i$ è la codifica di una mdT e $T_{i}$ termina, quindi $(i,x)\in L_{H}$. $\square$

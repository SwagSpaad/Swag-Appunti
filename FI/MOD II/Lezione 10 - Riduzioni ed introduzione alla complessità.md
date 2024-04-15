*****
Nella dimostrazione dell'indecidibiltà dell'[[Lezione 9 - Halting Problem|Halting Problem]] siamo partiti supponendo di avere una macchina $T$ in grado di decidere $L_{H}$ e, *senza sapere come era fatta la macchina $T$*, abbiamo costruito altre macchine $T^{'},T^{''}$ e $T^{*}$ che ci hanno portato a dimostrare la non decidibilità. Questo utilizzo "a scatola nera" corrisponde al concetto di invocazione di funzione, come nella programmazione. Quando $T^{'}$ usava $T$, le passava "come parametro" il suo input $(i,x)$. 
Generalmente potremmo utilizzare una macchina $T_{0}$ all'interno di una macchina $T_{1}$, perché:
- il linguaggio deciso/accettato da $T_{0}$ potrebbe essere anche diverso da quello deciso/accettato da $T_{1}$ 
- potrebbe essere necessario "modificare" l'input di $T_{1}$ prima di darlo in pasto a $T_{0}$ 

>**Esempio**
>Voglio costruire una macchina che decida il linguaggio $L_{P12}$ che contiene le parole palindrome di lunghezza pari sull'alfabeto $\{1,2\}$. Notiamo che, in effetti, questo linguaggio somiglia molto al linguaggio $L_{PPAL}$ di tutte le stringhe palindrome di lunghezza pari sull'alfabeto $\{a,b\}$. Quindi, piuttosto che riprogettare una macchina, potremmo utilizzare $T_{PPAL}$, l'unico problema da risolvere riguarda l'alfabeto su cui la macchina deve lavorare. L'idea potrebbe essere quella di trasformare le parole di $L_{P12}$ in parole di $L_{PPAL}$. 
>Prendo quindi il mio $x\in\{1,2\}^{*}$ e assumendo $x=x_{1}x_{2}\dots x_{n}$, per ogni $h=1,2,\dots,n$  procedo così: 
>- se $x_h=1$ allora poniamo $y_{h}=a$
>- se $x_h=2$ allora poniamo $y_{h}=b$
>Ho qundi costruito una parola $y\in\{a,b\}^*$ che ha le seguenti caratteristiche: 
>- se $x\in L_{P12}$ allora $y\in L_{PPAL}$
>- se $x\not\in L_{P12}$ allora $y\not\in L_{PPAL}$

Quello che abbiamo fatto nell'esempio, può essere vista come la progettazione di una funzione $f:\{1,2\}^{*}\to\{a,b\}^*$ tale che: 
- $f$ è calcolabile e totale, ossia 
	- è definita per ogni $x\in\{1,2\}^{*}$ 
	- esiste una macchina di Turing $T_{f}$ di tipo trasduttore tale che, per ogni parola $x\in\{1,2\}^{*}$, la computazione $T_{f}(x)$ termina con la parola $f(x)\in\{a,b\}^{*}$ scritta sul nastro di output
- per ogni $x\in\{1,2\}^{*}$ vale che $x\in L_{P12}$ se e solo se $f(x)\in L_{PPAL}$ 
La funzione $f$ si chiama **riduzione** da $L_{P12}$ a $L_{PPAL}$ e si dice che $L_{P12}$ è **riducibile** a $L_{PPAL}$ e si scrive $L_{P12}\preceq L_{PPAL}$ 
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
La funzione $f$ si chiama **riduzione** da $L_{P12}$ a $L_{PPAL}$ e si dice che $L_{P12}$ è **riducibile** a $L_{PPAL}$ e si scrive $L_{P12}\preceq L_{PPAL}$.

# Riduzioni
Generalizziamo quello che abbiamo detto ora. 
**Def.** Riduzione many-to-one
Dati due linguaggi $L_{1}\subseteq \Sigma_{1}^{*}$ e $L_{2}\subseteq \Sigma_{2}^{*}$, diciamo che **$L_{1}$ è riducibile ad $L_{2}$** e scriviamo $L_{1}\preceq L_{2}$ se esiste una funzione $f:\Sigma_{1}^{*}\to\Sigma_{2}^{*}$ tale che: 
- $f$ è totale e calcolabile, ossia 
	- è definita per ogni $x\in\Sigma_{1}^{*}$ 
	- esiste una macchina di Turing $T_{f}$ di tipo trasduttore tale che, per ogni parola $x\in\Sigma_{1}^{*}$, la computazione $T_{f}(x)$ termina con la parola $f(x)\in\Sigma_{2}^{*}$ scritta sul nastro di output
- per ogni $x\in\Sigma_{1}^{*}$ vale che $x\in L_{1}\iff f(x)\in L_{2}$ 

Il concetto di riducibilità funzionale ci permette di correlare tra loro linguaggi in modo tale che: 
- la decidibilità/accettabilità di un linguaggio implichi la decidibilità/accettabilità dei linguaggi ad esso riducibili
- la non decidibilità/accettabilità di un linguaggio implichi la non decidibilità/accettabilità dei linguaggi cui esso si riduce

Se dimostro che, dato un linguaggio $L_{3}$, vale $L_{3}\preceq L_{4}$ per un altro linguaggio $L_{4}$, se io so che $L_{4}$ è decidibile, allora posso concludere che $L_{3}$ *è decidibile*. 
Infatti, siano $L_{3}\subseteq \Sigma_{3}^{*}$ e $L_{4}\subseteq \Sigma_{4}^{*}$:
- **$L_{4}$ è decidibile**, allora esiste una macchina $T_{4}$ tale che, per ogni $x\in \Sigma_{4}^{*}$, $T(x)$ termina in $q_{A}$ se $x\in L_{4}$ e termina in $q_{R}$ se $x\not\in L_{4}$ 
- $L_{3}\preceq L_{4}$, allora esiste un trasduttore $T_{f}$ tale che per ogni $x\in\Sigma_{3}^{*}$, $T_{f}(x)$ termina con una parola $y\in\Sigma_{4}^{*}$ scritta sul nastro di output tale che $y\in L_{4}$ se $x\in L_{3}$ e $y\not\in L_{4}$ se $x\not\in L_{3}$.
Ora costruiamo una macchina $T_{3}$ a 2 nastri, che con input $x\in\Sigma_{3}^{*}$: 
- prima simula $T_{f}(x)$ scrivendo l'output $y=f(x)$ sul secondo nastro
- poi simula $T_4(y)$ che, se accetta allora anche $T_{3}$ accetta e se rigetta allora anche $T_{3}$ rigetta
Abbiamo quindi dimostrato che $L_{3}$ è decidible, applicando la riduzione.

Possiamo utilizzare la riduzione per dimostrare **la non decidibilità/accettabilità** di un linguaggio. Dato un linguaggio $L_{2}$, se dimostro che $L_{1}\preceq L_{2}$ e so che $L_{1}$ è non decidibile, allora posso concludere che anche $L_{2}$ è non decidibile. 
Infatti, sia $L_{1}\subseteq\Sigma_{1}^{*}$ e $L_{2}\subseteq\Sigma_{2}^{*}$:
- se $L_{2}$ **fosse decidibile** (per assurdo), allora, poiché $L_{1}\preceq L_{2}$, per quanto visto nelle righe sopra, anche $L_{1}$ dovrebbe essere decidibile, contraddicendo l'ipotesi per cui $L_{1}$ è non decidibile. 

Riducendo un linguaggio "sconosciuto" ad un linguaggio decidibile/accettabile, dimostriamo che il linguaggio "sconosciuto" è anch'esso accettabile/decidibile
- dimostrando che $L_{P12}\preceq L_{PPAL}$ e sapendo che $L_{PPAL}$ è **decidibile**, abbiamo mostrato che $L_{P12}$ è decidibile
- dimostrando che $L_{H0}\preceq L_{H}$ e sapendo che $L_{H}$ è *accettabile*, abbiamo dimostrato che $L_{H0}$ è accettabile

Riducendo un linguaggio decidibile/accettabile ad un linguaggio "sconosciuto", dimostriamo che il linguaggio "sconosciuto" è anch'esso non accettabile/decidibile
- dimostrando che $L_{H}\preceq L_{H0}$ e sapendo che $L_{H}$ è non decidibile, abbiamo dimostrato che $L_{H0}$ è non decidibile

>**Esempio**
>Consideriamo il linguaggio $$L_{H0}=\{i\in\mathbb{N}:\:i\text{ è la codifica di una macchina di Turing e }T_{i}(0)\text{ termina}\}$$ovvero, $i\in L_{H0}$ se $i$ è la codifica di una macchina di Turing $T_{i}$ e la computazione di $T_{i}$, con input la parola costituita dal carattere $0$, termina.
>Osserviamo quanto questo problema somiglia molto all'[[Lezione 9 - Halting Problem|Halting problem]], infatti $L_{H0}$ è un sottoinsieme di $L_{H}$ che contiene solo le coppie $(i,0)$ e questo si esprime dicendo che *$L_{H0}$ è una restrizione di $L_{H}$*, quindi una macchina che si occupa di $L_{H}$ può occuparsi di $L_{H0}$ e quindi la macchina che accetta $L_{H}$ accetterà anche $L_{H0}$.
>Osserviamo anche che, però, $L_{H0}$ sembra più facile di $L_H$, quindi magari è decidibile... ma questo in realtà non è vero e lo dimostriamo **riducendo $L_{H}$ ad $L_{H0}$**, ossia individuando una funzione totale e calcolabile $f$ che trasforma tutte le parole di $L_H$
 in parole di $L_{H0}$ e parole non in $L_{H}$ in parole non in $L_{H0}$  

Consideriamo una coppia $(i,x)\in\mathbb{N}\times\mathbb{N}$ con $x=x_1x_{2}\dots x_{n}$ e da essa deriviamo un intero $k=k_{(i,x)}=f(i,x)$ nel modo seguente:
- se $i$ *non è la codifica di una macchina di Turing*, allora costruiamo una macchina di Turing $M_{(i,x)}$ che entra in loop qualunque sia il suo input
- se $i$ *è la codifica di una macchina di Turing*, allora costruiamo una macchina di Turing $M_{(i,x)}$ che, con input $0$, simula $T_{i}(x)$ nel seguente modo
	1. nello stato iniziale $q_{1}$ se legge $0$ scrive $x_{1}$ ed entra in $q_{2}$ muovendosi a destra, altrimenti rigetta
	2. nello stato $q_{2}$ se legge $\square$ sul nastro scrive $x_{2}$ ed entra in $q_3$ muovendosi a destra, altrimenti rigetta
	3. $\vdots$ 
	4. nello stato $q_{n}$ se legge $\square$ sul nastro scrive $x_{n}$, riposiziona la testina sul primo carattere in input ed entra nello stato iniziale $q_{0}$ di $T_{i}$, altrimenti rigetta

**Oss. 1** 
Il numero degli stati $M_{(i,x)}$ dipende da $x$ e potrebbe sembrare non costante, ma in realtà non è così perché:
 - $x$ non è input per $M_{(i,x)}$ che si attende come input il solo carattere $0$
 - ricordiamo che abbiamo considerato una coppia $(i,x)$ e **solo dopo averla fissata** abbiamo costruito $M_{(i.x)}$

$k_{(i,x)}$ corrisponde alla codifica di $M_{(i,x)}$ e per questo $T_{k_{(i,x)}}=M_{(i,x)}$ allora $k_{(i,x)}\in L_{H0}$ se e solo se $T_{k_{(i,x)}}(0)=M_{(i,x)}(0)$ termina e resta da capire in quali casi termina.
Per ogni $(i,x)\in\mathbb{N}\times\mathbb{N}$, se $M_{(i,x)}(0)$ termina, allora $i$ è la codifica di una macchina di Turing e $T_{i}(x)$ termina. Quindi, se $k_{(i,x)}\in L_{H0}$ allora $T_{k_{(i,x)}}(0)=M_{(i,x)}(0)$ termina e allora $i$ è la codifica di una macchina di Turing e $T_{i}(x)$ termina. 
In generale se $k_{(i,x)}\in L_{H0}$ allora $(i,x)\in L_{H}$.

Per ogni $(i,x)\in\mathbb{N}\times\mathbb{N}$, se **non** $M_{(i,x)}(0)$ termina, allora ci sono due casi:
- $M_{(i,x)}$ è stata costruita secondo il primo caso (loop) e quindi $i$ non è la codifica di una macchina di Turing $T_{i}$
- $M_{(i,x)}$ è stata costruita secondo il secondo caso e $i$ è la codifica di una macchina di Turing, ma $T_{i}(x)$ **non termina**. 
Ricapitolando se $k_{(i,x)}\not\in L_{H0}$ allora $T_{k_{(i,x)}}(0)=M_{(i,x)}(0)$ non termina e allora $(i,x)\not\in L_{H}$.
In generale se $k_{(i,x)}\not\in L_{H0}$ allora $(i,x)\not\in L_{H}$.

Abbiamo calcolato $k_{(i,x)}$ come la codifica di $M_{(i,x)}$ ponendo $k_{(i,x)}=f(i,x)$ e siccome abbiamo descritto il procedimento per calcolare  $k_{(i,x)}$ per ogni coppia $(i,x)$, allora $f$ è totale e calcolabile per ogni $(i,x)\in\mathbb{N}\times\mathbb{N}$ vale che $(i,x)\in L_{H}$ se e solo se $k_{(i,x)}=f(i,x)\in L_{H0}$.

Quindi $L_{H}\preceq L_{H0}$ e questo dimostra che $L_{H0}$ non è decidibile.

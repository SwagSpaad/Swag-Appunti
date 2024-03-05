****
**Calcolabilità**: capire quali problemi possono essere risolti *automaticamente*, cosa significa *risolvere un problema* e cosa *è un problema*
**Complessità**: capire quali dei problemi che possono essere risolti possono essere realmente risolti.

# Problemi
**Def.**
Un **problema** è la descrizione di un insieme di *dati*, collegati da una serie di relazioni, associata alla richiesta di derivare da essi un altro insieme di parametri, che chiamiamo *soluzione*.

Un'istanza di un problema è un particolare insieme di valori associati ai dati. 
>**Es.**
>- "Quanto fa 5+2?" è un istanza del PROBLEMA SOMMA, ossia, dati due numeri $n$ e $k$, calcolare $s=n+k$.
>- "Quanto misura l'area di un rettangolo con $b=12$ e $h=4$?" è un istanza del PROBLEMA AREA, ossia, dato un rettangolo con base $b$ e altezza $h$, calcolare l'area $A$ del rettangolo.

## Trovare la soluzione di un'istanza
A volte è semplice trovare la soluzione di un'istanza di un problema, ad esempio per il **PROBLEMA SOMMA**, l'istanza con $n=2$ e $k=5$ è semplice. Altre volte è più complicato, pensiamo al problema somma con $n=524332952$ e $k=78435020353$.
A volte è proprio **impossibile**, ad esempio trovare un $n\in\mathbb R$ tale che $n=\sqrt{-4}$.

Quando l'istanza di un problema non ha soluzione diciamo che è un'**istanza negativa**

## Risolvere un problema
Risolvere un problema significa individuare un *metodo* che sappia trovare la **soluzione di ogni istanza positiva del problema** e che sappia riconoscere un'*istanza negativa*, ovvero trovare un procedimento che, data qualunque istanza, indichi la sequenza di azioni che devono essere svolte per trovare la soluzione dell'istanza, o concludere che l'istanza non ha soluzione. 

Bisogna ora rispondere a qualche domanda: 
- cos'è un procedimento?
- cos'è un'azione?
- chi deve eseguire le azioni indicate?

**Def.**
Un **procedimento** è la descrizione di un insieme di azioni, unita alla specifica dell'ordine con il quale queste azioni devono essere eseguite.
Le **azioni** indicate, a cui viene dato il nome di *istruzioni*, devono essere semplici (elementari), ovvero eseguibili con facilità.

>**Es.**
>Data una funzione $f:\mathbb R\to \mathbb{R^{+}}$ e dati due numeri $a,b\in \mathbb{R}$, calcolare la misura dell'area della regione di piano compresa fra la funzione, l'asse $x$ e l'asse $y$ e la retta $y=a$ e $y=b$. 
>
>PROCEDIMENTO: 
>1. calcola la funzione primitiva $F(x)$ di $f(x)$
>2. calcola $F(b)-F(a)$

Quello indicato nell'esempio è un procedimento che risolve il problema, ma l'istruzione "calcola la funzione primitiva $F(x)$ di $f(x)$" è davvero un'**istruzione elementare?** 
Dipende da chi *deve eseguire le azioni indicate*, nel caso di un matematico l'istruzione è elementare, mentre per un bambino delle elementari ovviamente no. 

## Istruzioni elementari
Turing osservò che, indipendentemente dall'esecutore, un'istruzione per poter essere definita *elementare*, deve avere le seguenti caratteristiche:
- deve essere scelta in un insieme di "poche" istruzioni
- scegliere l'azione da un insieme di "poche" azioni
- deve essere poter eseguita ricordando una quantità limitata di dati
Le caratteristiche individuate, indicano come istruzione elementare un'operazione che possa essere eseguita a mente. 

### Esempio con il problema somma
>Dati due interi $n$ e $k$, calcolare il numero $n+k$.

Vogliamo quindi progettare un procedimento che risolva questo problema. Siccome calcolare la somma di due interi è facile, potremmo pensare che l'istruzione "calcola $n+k$" sia un'*istruzione elementare*, ma se assegnassimo $n=37895$ e $k=441238$, a nessuno viene in mente il risultato, questo perché la nostra memoria è limitata. 
Per le somme tra i numeri da 0 a 9 abbiamo a suo tempo imparato una tabella simile

![[FI/MOD II/img/img0.png|center|500]]

quindi basterebbe una tabella sufficientemente grande per risolvere l'istanza sopra, ad esempio la somma dei numeri tra 0 e 100000.

![[FI/MOD II/img/img1.png|center|500]]

Questo sembrerebbe corretto per risolvere l'istanza data, ma per risolvere il problema somma, occorre indicare un *procedimento* che sappia addizionare **qualunque coppia di numeri naturali**, e questo comporterebbe quindi la costruzione di una tabella infinita, per questo motivo l'istruzione "calcola $n+k$" non può essere definita *elementare*.

Per risolvere un problema utilizziamo un procedimento che utilizza un numero limitato di operazioni elementari (somme di coppie di numeri di una sola cifra) e in cui ogni operazione elementare utilizza una quantità limitata di dati (due cifre e l'eventuale riporto).
Pensiamo quindi alla somma dei numeri in colonna, potremmo descrivere il procedimento per calcolare il risultato nel modo seguente:
1. posizionati sulla colonna più a destra e poni $r=0$
2. fintanto che leggi una coppia di cifre, esegui la somma della coppia di cifre sulle quali sei posizionato, aggiungi $r$ a tale valore e scrivi una cifra del risultato calcolando il nuovo valore di $r$, e poi spostati a sinistra
	- se $r=0$ e le due cifre sono $(0,0)$, scrivi $0$, poni $r=0$ e spostati a sinistra
	- se $r=1$ e le due cifre sono $(0,0)$, scrivi $1$, poni $r=0$ e spostati a sinistra
	- $\vdots$
	- se $r=0$ e le due cifre sono $(9,9)$, scrivi $8$, poni $r=1$ e spostati a sinistra
	- se $r=1$ e le due cifre sono $(9,9)$, scrivi $9$, poni $r=1$ e spostati a sinistra
3. fino a quando leggi una sola cifra, aggiungi $r$ ad essa e scrivi una cifra del risultato calcolando anche il nuovo valore di $r$ e spostati a sinistra
	- se $r=0$ e l'unica cifra è $0$, scrivi $0$, poni $r=0$ e spostati a sinistra
	- se $r=1$ e l'unica cifra è $0$, scrivi $1$, poni $r=0$ e spostati a sinistra
	- $\vdots$
	- se $r=1$ e l'unica cifra è $8$, scrivi $9$, poni $r=0$ e spostati a sinistra
	- se $r=1$ e l'unica cifra è $9$, scrivi $0$, poni $r=1$ e spostati a sinistra
4. se le cifre di entrambi i numeri sono terminate, allora calcola l'eventuale ultima cifra del risultato e termina
	- se $r=0$ e le cifre di entrambi i numeri sono terminate, allora termina
	- se $r=1$ e le cifre di entrambi i numeri sono terminate, allora scrivi 1 e termina

**Oss.**
Il procedimento per calcolare la somma in colonna, non è nient'altro che una sequenza di "se sono vere **queste condizioni**, allora esegui *queste azioni*", quindi ad ogni coppia (**certe condizioni**, *queste azioni*) corrisponde un'istruzione, dove le condizioni sono ciò che viene letto e le azioni sono ciò che viene scritto. 

Ora queste istruzioni sono veramente *elementari*, infatti ogni persona che sappia leggere e scrivere potrebbe eseguire il procedimento.

Le istruzioni individuate sono, però, *elementari* nel senso indicato da Turing? Ricordiamo che devono avere le seguenti caratteristiche: 
- scelte in un insieme di "poche" istruzioni disponibili
- **deve scegliere l'azione da eseguire all'interno di un insieme di "poche" azioni disponibili**
- deve poter essere eseguita ricordando una quantità limitata di memoria
Nel procedimento visto sopra le azioni eseguite sono due: scrittura della cifra e spostamento, che sono effettivamente "poche" azioni, ma è vero che il procedimento che 
esegue la somma ha un insieme di "poche" istruzioni disponibili, ciascuna delle quali utilizza una quantità limitata di memoria? 

Spieghiamo cosa si intende con poche e quantità limitata.
Il numero di istruzioni disponibili è pari al numero di coppie di cifre moltiplicato per il numero di possibili valori per il riporto, cioè $10\times10\times2=200$. Per sapere quale istruzione esguire abbiamo bisogno di conoscere le cifre da sommare e il valore del riporto, ossia 3 numeri di una cifra.

Ricapitolando: per sommare qualunque coppia di interi *grande a piacere* abbiamo a disposizione 222 istruzioni fra le quali scegliere quella da eseguire utilizzando una memoria di 3 cifre.
Indipendentemente da quanto sono grandi i numeri, sempre 222 istruzioni che utilizzano una memoria di 3 cifre vengono eseguite.

>[!importante]
>Il numero di istruzioni, azioni e la quantità di memoria necessaria sono **costanti**, ossia non dipendono dall'input

Eseguendo il procedimento, non è necessario sapere cosa significa sommare due numeri naturali, poiché le istruzioni dicono **per ogni condizione possibile, esegui queste azioni**, questo significa che l'insieme delle istruzioni non è *ambiguo*: non contiene due o più istruzioni che a partire dalle stesse **condizioni**, indica diverse *azioni*. In ogni istante *deve essere* eseguita **l'unica istruzione** che è possibile eseguire, finché non si incontra l'istruzione che dice di terminare.
Quest'idea è alla base di molti linguaggi di programmazione che vengono detti *imperativi* (C, Java, Python ecc.).

## Risolvere automaticamente un problema
Informalmente, risolvere automaticamente un problema, significa progettare un **procedimento** che risolve **tutte** le istanze del problema e che *può essere eseguito da un automa*, ovvero un esecutore che può non avere alcuna idea del problema né del significato delle istruzioni contenute nel procedimento.

## Un nuovo linguaggio
Ripensiamo alla somma dei numeri naturali:
1. il procedimento visto è costituito di sole istruzioni "condizione -> azione"
2. in ciascuna istruzione le azioni da eseguire sono 3 (scrittura della cifra, modifica del riporto, movimento a sinistra)
3. le condizioni di ognuna delle istruzioni dipendono da due tipi di parametri, ovvero il valore del riporto e le due cifre da sommare
Mentre le due cifre da sommare le troviamo scritte sul foglio, il valore del riporto lo teniamo a mente ad ogni coppia di cifre sommate, ovvero il nostro **stato interiore**.

In seguito a queste osservazioni possiamo scrivere il procedimento in forma più compatta, l'istruzione 
- se $r=0$ e le due cifre sono $(4,6)$, scrivi $0$, poni $r=1$ e spostati a sinistra
diventa
$$\langle q_{0},\: (4,6),\:0,\:q_{1},\:\text{sx}\rangle$$
dove $q_{0}$ e $q_{1}$ sono due simboli che indicano $r=0$ e $r=1$

L'istruzione
- se $r=1$ e l'unia cifra è $5$, scrivi $6$, poni $r=0$ e spostati a sinistra
diventa
$$\begin{align*}
\langle q_{1},\: (5,\square),\:6,\:q_{0},\:\text{sx}\rangle\\
\langle q_{1},\: (\square,5),\:6,\:q_{0},\:\text{sx}\rangle
\end{align*}$$
dove $\square$ indica che non viene letto nulla.

Le istruzioni di terminazione
- se $r=0$ e le cifre di entrambi i numeri sono terminate, allora termina
- se $r=1$ e le cifre di entrambi i numeri sono terminate, allora scrivi 1 e termina 
diventano
$$\begin{align*}
\langle q_{0},\: (\square,\square),\square,\:q_{f},\:\text{fermo}\rangle\\
\langle q_{1},\: (\square,\square),1,\:q_{f},\:\text{fermo}\rangle
\end{align*}$$ dove con $q_{f}$ è lo "*stato interiore*" che permette all'esecutore di capire che non deve più eseguire alcuna operazione. 
In figura viene mostrato graficamente il procedimento per la somma dei numeri $53$ e $28$

![[FI/MOD II/img/img2.png|center|500]]

Quella in figura è *quasi* una descrizione informale di macchina di Turing, perché abbiamo usato tre nastri e in una macchina di Turing occorre descrivere cosa viene letto (nelle **condizioni**) e cosa viene scritto (nelle *azioni*) su **ogni** nastro, così che l'istruzione
- se $r=0$ e le due cifre sono $(4,6)$, scrivi $0$, poni $r=1$ e spostati a sinistra
diventa 
$$\langle q_{0},\:(4,6,\square),\:(4,6,0),\:q_{1},\:\text{sx}\rangle$$
Poiche specifica 2 condizioni e 3 azioni, l'istruzione prende il nome di **quintupla**, quelli che abbiamo chiamato "stati interiori" si chiamano **stati interni** e l'esecuzione delle quintuple su un insieme di dati si dice **computazione**.


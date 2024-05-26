# ES 1 processi e thread
Un computer ha 2 GB di memoria, il S.O. occupa 1024 MB e i processi occupano mediamente 256 MB. Se il tempo di attesa medio dell'I/O è 80%, qual è la percentuale di utilizzo della CPU?

**Sol.**
Utilizzo della CPU = $1-p^{n}$ dove $p$ è il tempo medio di attesa (80%) e $n$ è il numero di processi in memoria che riescono a risiedere in memoria.
$$n=\frac{\text{mem\_computer - mem\_occupata}}{\text{spazio occupato proc}}=\frac{2048-1024}{256}=4\ \text{processi}$$
Utilizzo della CPU = $1-0.80^{4}=0.5904=59\%$ 
Aggiungendo 512 MB di RAM il numero di processi diventa $$n=\frac{(2048+512)-1024}{256}=6$$
e il nuovo carico della CPU sarà = $1-80^{6}=73.78\%$

# ES 2 first come first served
Supponendo di utilizzare l'algoritmo First-Come First-Served con 5 job che arrivano nel seguente ordine:
P1 per primo, P2 dopo 3ms, P3 dopo 1ms da P1, P4 dopo 1ms da P3 e P5 dopo 2ms da P4. Se i tempi di esecuzione sono rispettivamente 4,2,4,2,4, quali saranno i tempi medi di attesa e turnaround?

Tempo Turnaround $T_{T}$: intervallo di tempo che intercorre dall'ingresso in coda del processo fino al termine della sua esecuzione
- T. Attesa/wait $T_{W}$: intervallo di tempo che il processo trascorre in coda prima di essere eseguito 
- T. Arrivo $T_{A}$: tempo di arrivo in coda del processo
- T. Esecuzione $T_{X}$: intervallo di tempo necessario per l'esecuzione del processo
- T. Inizio esecuzione $T_{S}$: tempo di inizio esecuzione del processo
- T. Fine esecuzione $T_{E}$: tempo fine esecuzione del processo

**Sol.**
Determinare ordine di arrivo dei singoli processi (dal testo) 

| i   | $P_k$ | $T_A(i)$ | $T_X(i)$ |
| --- | ----- | -------- | -------- |
| 1   | P1    | 0        | 4        |
| 2   | P3    | 1        | 4        |
| 3   | P4    | 2        | 2        |
| 4   | P2    | 3        | 2        |
| 5   | P5    | 4        | 4        |

$$\begin{align*}
&T_{S}(i)=\begin{cases}
T_{A}(1)=0 & i= 1 \\
 \\
T_{E}(i-1) & i>1
\end{cases}\\\\
&T_{E}(i)=T_{S}(i)+T_X(i)\\\\
&T_{W}(i)=T_{S}(i)-T_A(i)\\\\
&T_{T}(i)=T_{E}(i)-T_A(i)\\
\end{align*}$$

La tabella completa dei tempi espressi in millisecondi risulta:

| i   | $P_k$ | $T_A(i)$ | $T_X(i)$ | $T_S(i)$ | $T_E(i)$ | $T_W(i)$ | $T_T(i)$ |
| --- | ----- | -------- | -------- | -------- | -------- | -------- | -------- |
| 1   | P1    | 0        | 4        | 0        | 4        | 0        | 4        |
| 2   | P3    | 1        | 4        | 4        | 8        | 3        | 7        |
| 3   | P4    | 2        | 2        | 8        | 10       | 6        | 8        |
| 4   | P2    | 3        | 2        | 10       | 12       | 7        | 9        |
| 5   | P5    | 4        | 4        | 12       | 16       | 8        | 12       |

Tempo medio di attesa = media aritmetica di $T_{W}$ = 4.8ms

Tempo medio di Turnaround = media aritmetica sui $T_{T}$=8 ms

# ES 3 shortest job first
Supponendo di utilizzare l'algoritmo Shortest Job First dovendo eseguire 4 job con i tempi di esecuzione sono rispettivamente 8,10,12,6 quali saranno i tempi medi di attesa e turnaround?

**Sol.**
Siccome non ci sono i tempi di arrivo, nelle formule sono esclusi i $T_A(i)$. Quindi 
$$\begin{align*}
&T_{S}(i)=\begin{cases}
T_{S}(1)=0 & i= 1 \\
 \\
T_{E}(i-1) & i>1
\end{cases}\\\\
&T_{E}(i)=T_{S}(i)+T_X(i)\\\\
&T_{W}(i)=T_{S}(i)\\\\
&T_{T}(i)=T_{E}(i)\\
\end{align*}$$

| i   | $P_k$ | $T_X(i)$ | $T_S(i)$ | $T_E(i)$ | $T_W(i)$ | $T_T(i)$ |
| --- | ----- | -------- | -------- | -------- | -------- | -------- |
| 1   | P4    | 6        | 0        | 6        | 0        | 6        |
| 2   | P1    | 8        | 6        | 14       | 6        | 14       |
| 3   | P2    | 10       | 14       | 24       | 14       | 24       |
| 4   | P3    | 12       | 24       | 36       | 24       | 36       |

Tempi medi sono 
$T_{W}$ medio = 11 ms
$T_{T}$ medio = 20 ms

# ES 4 shortest job first
Supponendo di utilizzare l'algoritmo Shortest Job First se arrivano 5 job, P1 poi P4 dopo 2ms da P1, P2 dopo 3 ms da P1, P3 dopo 1 ms da P2, P5 dopo 3 ms da P1 con i tempi di esecuzione rispettivi 4 2 1 3 2. 
Calcolare tempo medio di Turnaround e di attesa.

**Sol.**


| i   | $P_k$ | $T_A(i)$ | $T_x(i)$ | $T_S(i)$ | $T_E(i)$ | $T_W(i)$ | $T_T(i)$ |
| --- | ----- | -------- | -------- | -------- | -------- | -------- | -------- |
| 1   | P1    | 0        | 4        | 0        | 4        | 0        | 4        |
| 2   | P3    | 4        | 1        | 4        | 5        | 0        | 1        |
| 3   | P2    | 3        | 2        | 5        | 7        | 2        | 4        |
| 4   | P5    | 3        | 2        | 7        | 9        | 4        | 6        |
| 5   | P4    | 2        | 3        | 9        | 12       | 7        | 10       |

Tempi medi sono 
$T_{W}$ medio = 2.6 ms
$T_{T}$ medio = 5 ms

# ES 5 shortest remaining next
Supponendo di utilizzare l'algoritmo Shortest remaining next, se arrivano 5 job nell'ordine P1, P2 dopo 1ms da P1, P3 dopo 2ms da P1, P4 dopo 7 ms da P2, P5 dopo 1 ms da P4 con i tempi di esecuzione rispettivi 5 3 1 2 1, calcolare il tempo medio di Turnaround e di attesa.


| x   | 1    | 2    | 3    | 4   | 5   | 6   | 7   | 8   | 9   | 10   | 11  | 12  |
| --- | ---- | ---- | ---- | --- | --- | --- | --- | --- | --- | ---- | --- | --- |
| P1  | c ex |      |      |     |     | ex  | ex  | ex  | ex  |      |     |     |
| P2  |      | c ex |      | ex  | ex  |     |     |     |     |      |     |     |
| P3  |      |      | c ex |     |     |     |     |     | c   |      | ex  | ex  |
| P4  |      |      |      |     |     |     |     |     |     | c ex |     |     |
| P5  |      |      |      |     |     |     |     |     |     |      |     |     |

c: entrata in coda
ex: esecuzione per 1 ms


| i   | $P_k$ | $T_A(i)$ | $T_X(i)$ | $T_S(i)$ | $T_E(i)$ | $T_W(i)$ | $T_T(i)$ |
| --- | ----- | -------- | -------- | -------- | -------- | -------- | -------- |
| 1   | P1    | 0        | 5        | 0        | 9        | 0        | 9        |
| 2   | P2    | 1        | 3        | 1        | 5        | 0        | 4        |
| 3   | P3    | 2        | 1        | 2        | 3        | 0        | 1        |
| 4   | P5    | 9        | 1        | 9        | 10       | 0        | 1        |
| 5   | P4    | 8        | 2        | 10       | 12       | 2        | 4        |
Tempi medi sono 
$T_{W}$ medio = 0.4 ms
$T_{T}$ medio = 3.8 ms

# ES 6 round - robin
Supponendo di utilizzare l'algoritmo Round-Robin per lo scheduling, se l'ordine di esecuzione è $P_{1}\to P_{2}\to P_{3}\to P_{4}\to P_{5}$ con un quanto di esecuzione $200\micro s$  e cambio di contesto in $2 \micro s$ , supponendo che sia terminato $P_{1}$ dopo quanto sarà eseguito $P_{5}$?
Qual è il rapporto tra cambio di contesto e tempo di esecuzione?
Come cambiano i tempi se il quanto è $12 \micro s$? Quale delle due soluzioni è più favorevole?

**Sol.**
L'algoritmo round-robin manda in esecuzione, per un intervallo di tempo fissato, tutti i processi pronti per l'esecuzione. Inizialmente la CPU è assegnata al primo processo in coda e allo scadere del quanto di tempo, attendendo il tempo di cambio di contesto, passa al successivo processo. 

Per l'esercizio P1 ha terminato la sua esecuzione, quindi la CPU esegue un cambio di contesto prima di eseguire il successivo processo $P2$. Supponendo che tutti i processi vengno eseguiti per il quanto di tempo prestabilitp, considerando anche i tempi necessari ai vari cambi di contesto, il tempo atteso complessivamente da P5 è  pari a $$2+200+2+200+2+200+2=608\micro s$$
Il rapporto tra cambio di contesto e tempo di esecuzione in questo caso è $$\frac{2}{200}=0.01$$ ciò significa che durante l'esecuzione dei processi l'1% del tempo della CPU è speso per realizzare l'algoritmo e passare da un processo all'altro.
Nel secondo caso con un quanto di $12\micro s$, il tempo di attesa di P5 è pari $$2+12+2+12+2+12+2=44\micro s$$
Il rapporto tra tempo di context-switch e quanto di esecuzione $\frac{2}{12}=0.16$
quindi la misura dello spreco del tempo di CPU dovuto alla gestione del sistema è 16.67%

Nel primo caso il tempo per il cambio di contesto è molto più piccolo del quanto di esecuzione, quindi sono favoriti i processi che hanno bisogno del tempo della CPU per effettuare calcoli. In questo caso però P5 attende molto prima di essere eseguito.

Nel secondo caso i processi sono eseguiti più frequentemente perché il quanto è più basso mentre c'è una maggiore inefficienza dovuta al valore più alto del rapporto tra i due tempi.

# ES 7 shortest process next
Supponendo di utilizzare l'algoritmo Shortest Process Next con tecnica di aging per lo scheduling, stimare il tempo di esecuzione $T_{4}$ conoscendo i precedenti tempi di esecuzione $T_{0}=970\micro s$ $T_{1}=630\micro s$ $T_{2}=230\micro s$ $T_{3}=140\micro s$

**Sol.**
La stima del tempo di esecuzione all'iterazione i-esima $\tilde{T}_{i}$ può essere calcolata in base ai precedenti tempi nel seguente modo 
$$\tilde{T}_{i}(a)=\begin{cases}
T_{0} & i=1 \\
 \\
a\cdot T_{i-1}+(1-a)\cdot \tilde{T}_{i-1}(a) & i\geq2
\end{cases}$$
Ponendo $a=\frac{1}{2}$ possiamo calcolare $\tilde{T}_{4}$ nel seguente modo $$\begin{align*}
\tilde{T}_{4} &= \frac{T_{3}}{2}+\frac{\tilde{T}_{3}}{2}=\\\\
&= \frac{T_{3}}{2}+ \frac{1}{2}\cdot\bigg(\frac{T_{2}}{2}+ \frac{\tilde{T}_{2}}{2}\bigg)=\\\\
&= \frac{T_{3}}{2}+ \frac{1}{4}\bigg[T_{2}+ \frac{1}{2}\bigg(T_{1}+\tilde{T}_1\bigg)\bigg]=\\\\
&= \frac{T_{3}}{2}+ \frac{T_{2}}{4} + \frac{T_{1}}{8}+ \frac{T_{0}}{8}=\\\\
&=\frac{0,14}{2}+ \frac{0,23}{4} + \frac{0,63}{8}+ \frac{0,97}{8}=\\\\
&= 0,3275
\end{align*}$$

# ES 8 lotteria
Un sistema utilizza algoritmo di scheduling a lotteria ed ha assegnato 60 biglietti al processo P1 e 20 ai processi P2,P3 E P4. Al momento dell'estrazione qual è la probabilità di vincita del processo P1 e degli altri processi? 
Nel caso il processo P1 stia collaborando con P2 e P3 per raggiungere un determinato obiettivo, quanti biglietti deve cedere affinché la probabilità di estrazione degli altri due salga al 35%? Ora qual è la probabilita di estrazione di P1 e P4?

**Sol.**
Inizialmente occorre calcolare le probabilità di estrazione. 
P1 = $\frac{60}{120}= 50\%$ di probabilità
Gli altri processi hanno 20 biglietti su 120 totali e quindi una probabilità pari a 16.6%. 
Affinché i processi P2 e P3 abbiano il 35% di probabilità di vincita occorre che la loro probabilità e venga incrementata di $x$ punti: $$\begin{align*}
\frac{20+x}{120}= \frac{35}{100} \\\\
5\cdot(20+x)=35\cdot 6 \\\\
x=\frac{210-100}{5}=22
\end{align*}$$ Se P1 cede 22 biglietti a P2 e P3 rimarra con 16 biglietti e una probabilità pari a 13.33%.
Viceversa la probabilità di estrazione di P2 e P3 salità a $$\frac{20+22}{120}= \frac{42}{120}=0.35$$
Per concludere, il processo P4 avrà la stessa probabilità di essere estratto.

# ES 9 scheduling sistemi real-time
Supponendo di dover valutare la fattibilità di un sistema soft real-time con eventi periodici $P0=0.5 \micro s$,$P1=300 \micro s$,$P2=15 ms$,$P3=800ms$ con tempi di elaborazione $C0=50ns$,$C1=0.1ms$,$C2=3ms$,$C3=0.2s$. 
Il sistema è sostenibile? Se si aggiunge un nuovo evento periodico $P4=1000\micro s$ con $C4=0.12ms$. Il sistema rimane sostenibile?

**Sol.**
Ricordando che un sistema soft real-time che riceve $k$ eventi è sostenibile $$\sum\limits^{k}_{i=0} \frac{C_{i}}{P_{i}}\leq1$$
La prima operazione è di scrivere i valori tenendo conto dell'unita di misura

| i                     | 0                 | 1                 | 2                | 3                 |
| --------------------- | ----------------- | ----------------- | ---------------- | ----------------- |
| $C_i$                 | $50\cdot10^{-9}$  | $0.1\cdot10^{-3}$ | $3\cdot10^{-3}$  | 0.2               |
| $P_i$                 | $0.5\cdot10^{-6}$ | $300\cdot10^{-6}$ | $15\cdot10^{-3}$ | $800\cdot10^{-3}$ |
| $\frac{C_{i}}{P_{i}}$ | 0.1               | $0.\bar3$         | 0.2              | 0.25              |
Quindi il sistema è sostenibile  $$\sum\limits^{3}_{i=0} \frac{C_{i}}{P_{i}}=0.1+0.\bar3+0.2+0.25=0.88\bar3$$
Se arriva l'evento periodico P4 che porta un contributo di 0.12 il sistema non è più sostenibile.

# ES 10
Supponendo di dover valutare la fattibilità di un sistema soft real-time con eventi periodici $P0=53ms$,$P1=0.22ns$,$P2=145 ns$ con tempi di elaborazione $C0=13ms$,$C1=10ps$,$C2=55ns$. Il sistema è sostenibile? Se si aggiunge un nuovo evento periodico $P3=100ms$ qual è il valore limite per C3 affinché il sistema rimanga sostenibile?

**Sol.**
Si scrivono i valori dei periodi di arrivo $P_{i}$ e dei relativi tempi di elaborazione $C_{i}$

| i                 | 0                | 1                  | 2                 |
| ----------------- | ---------------- | ------------------ | ----------------- |
| $C_i$             | $13\cdot10^{-3}$ | $10\cdot10^{-12}$  | $55\cdot10^{-9}$  |
| $P_i$             | $53\cdot10^{-3}$ | $0.22\cdot10^{-9}$ | $145\cdot10^{-9}$ |
| $\frac{C_i}{P_i}$ | 0.2452           | 0.0454             | 0.3793            |

Il sistema è sostenibile perché $$\sum\limits^{2}_{i=0} \frac{C_{i}}{P_{i}}=0.2452+0.0454+0.3793=0.67\leq 1$$
Se arriva l'evento periodico P3 occorre calcolare il valore limite per C3 $$\begin{align*}
&1-0.67= \frac{C3}{P3}\\\\
&C3=P3\cdot0.3299=32.99ms
\end{align*}$$
# ES 12 gestione della memoria
Nell'ambito della gestione della memoria con lista collegata, la situazione è la seguente: $$\begin{align*}
&[P_{0},0,8]\to[L,8,3]\to[P_{1},11,5]\to[P_{2},16,4]\to[L,20,1]\to[P_{3},21,3]\to[L,24,5]\to\\
&\to[P_4,29,6]\to[L,35,4]
\end{align*}$$
dove ogni terna di valori ha il seguente significato $$[\text{Libero/Processo, Posizione Iniziale, Blocchi occupati}]$$
In quale posizione verrà allocato il processo $P_5$ che occupa 5 blocchi se si usa l'algoritmo Next fit e la posizione corrente è nell'ultimo elemento? Se il processo P5 occupasse un solo blocco e si utilizzasse l'algoritmo best fit, dove vorrebbe posizionato?

**Sol**
Nell'algoritmo Next fit il gestore della memoria deve allocare il processo P5 nella prima sequenza di blocchi ilberi in memoria. La posizione iniziale è sull'ultimo elemento, che non è in grado di contenere il processo poiché ha soltanto 4 blocchi liberi, quindi procede in modo incrementale considerando la lista come una struttura circolare, posizionandosi sul primo elemento $[L,8,3]$ che è costituito da tre blocchi, che non sono comunque sufficienti. La prima sequenza libera di blocchi si trova in posizione 24.

L'algoritmo best fit cerca di occupare il più piccolo insieme di blocchi necessari a contenere la dimensione del processo, quindi deve necessariamente scorrere l'intera lista, in assenza di liste degli spazi vuoti ordinate per spazio disponibile. Siccome abbiamo supposto che P5 occupasse un solo blocco, lo spazio libero più piccolo è in posizione 20

# ES 13
Nell'ambito della gestione della memoria con bitmap (5x5), se la memoria è allocata con lista collegata nel seguente modo: $$\begin{align*}
&[P_{0},0,3]\to[L,3,2]\to[P_{1},5,5]\to[P_{2},10,4]\to\\
&\to[L,14,2]\to[P_{3},16,3]\to[L,19,5]\to[P_4,24,1]\end{align*}$$
dove ogni terna di valori ha il seguente significato $$[\text{Libero/Processo, Posizione Iniziale, Blocchi occupati}]$$
Quale sarà la bitmap corrispondente? Se arriva il processo P5 che occupa 2 blocchi, utilizzando l'algoritmo worst fit, quale sarà la sua collocazione e la nuova bitmap?

**Sol.**
La matrice mantiene un bit per ogni blocco della memoria, quindi occorre ricostruire lo stato della memoria seguendo la lista ed inserendo 0 come rappresentanti dei blocchi liberi, mentre 1 quando il blocco è occupato 

| x   | 0   | 1   | 2   | 3   | 4   |
| --- | --- | --- | --- | --- | --- |
| 0   | 1   | 1   | 1   | 0   | 0   |
| 1   | 1   | 1   | 1   | 1   | 1   |
| 2   | 1   | 1   | 1   | 1   | 0   |
| 3   | 0   | 1   | 1   | 1   | 0   |
| 4   | 0   | 0   | 0   | 0   | 1   |

La tabella si legge da in alto a sinistra (posizione 0) fino in basso a destra (posizione 24 (5x5-1)). Per l'algoritmo worst fit, che prende il più grande slot libero per allocare, lo slot è quello che parte dalla cella 19 e trattandosi di un processo che occupa due blocchi la relativa bitmap diventa la seguente

| x   | 0     | 1   | 2   | 3   | 4     |
| --- | ----- | --- | --- | --- | ----- |
| 0   | 1     | 1   | 1   | 0   | 0     |
| 1   | 1     | 1   | 1   | 1   | 1     |
| 2   | 1     | 1   | 1   | 1   | 0     |
| 3   | 0     | 1   | 1   | 1   | **1** |
| 4   | **1** | 0   | 0   | 0   | 1     |

# ES 14 algoritmi sostituzione pagine
Supponendo di utilizzare l'algoritmo LEAST RECENTLY USED con 6 frame di memoria, se le pagine referenizate sono nell'ordine 4,1,0,3,2,5,1, quale pagina verrà posta sul disco?

**Sol.**
Si utilizza una matrice 6x6 perché ci sono 6 frame di memoria, inizialmente è referenziata la pagina 4 quindi occorre porre ad 1 tutti gli elementi della riga 4 e successivamente porre a zero tutti gli elementi della colonna 4

| x   | 0   | 1   | 2   | 3   | 4   | 5   |
| --- | --- | --- | --- | --- | --- | --- |
| 0   | 0   | 0   | 0   | 0   | 0   | 0   |
| 1   | 0   | 0   | 0   | 0   | 0   | 0   |
| 2   | 0   | 0   | 0   | 0   | 0   | 0   |
| 3   | 0   | 0   | 0   | 0   | 0   | 0   |
| 4   | 1   | 1   | 1   | 1   | 0   | 1   |
| 5   | 0   | 0   | 0   | 0   | 0   | 0   |
Le successive pagine accedute sono 1 0 3 2 5 e 1. Quindi per ogni pagina si procede come sopra e alla fine la tabella risulta

| x   | 0   | 1   | 2   | 3   | 4   | 5   | x   |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0   | 0   | 0   | 0   | 0   | 1   | 0   | =2  |
| 1   | 1   | 0   | 1   | 1   | 1   | 1   | =47 |
| 2   | 1   | 0   | 0   | 1   | 1   | 0   | =38 |
| 3   | 1   | 0   | 0   | 0   | 1   | 0   | =34 |
| 4   | 0   | 0   | 0   | 0   | 0   | 0   | =0  |
| 5   | 1   | 0   | 1   | 1   | 1   | 0   | =46 |
accanto ogni riga sono stati calcolati i rispettivi valori in binario che rappresentano il grado di utilizzo delle pagine

Quindi la pagina utilizzata meno recentemente è la 4 che sarà spostata sul disco per fae spazio ad una nuova pagina.

# ES 15
Supponendo di utilizzare l'algoritmo NOT FREQUENTLY USED con tecnica di aging che utilizzi una memoria con 6 frame e dei contatori ad 8 bit, se i valori dei bit R nei successibi cicli sono: $$100111\ 110011\ 111101\ 011110\ 111000\ 000111\ e\ 101011$$quale pagina verrà posta su disco?

**Sol.**
La tecnica di aging rende l'algoritmo NFU una simulazione dell'LRU che invece sarebbe realizzabile solo via hardware. Durante il primo ciclo di clock i valori del bit R finiscono all'interno degli 8 contatori (si mettono i valori di R a partire dalla colonna 0 in verticale)

| x   | 0     | 1   | 2   | 3   | 4   | 5   | 6   | 7   |
| --- | ----- | --- | --- | --- | --- | --- | --- | --- |
| 0   | **1** | 0   | 0   | 0   | 0   | 0   | 0   | 0   |
| 1   | **0** | 0   | 0   | 0   | 0   | 0   | 0   | 0   |
| 2   | **0** | 0   | 0   | 0   | 0   | 0   | 0   | 0   |
| 3   | **1** | 0   | 0   | 0   | 0   | 0   | 0   | 0   |
| 4   | **1** | 0   | 0   | 0   | 0   | 0   | 0   | 0   |
| 5   | **1** | 0   | 0   | 0   | 0   | 0   | 0   | 0   |
i valori entrano uno alla volta nella colonna 0 spostando di colonna in colonna quelli già presenti

| x   | 0   | 1     | 2   | 3   | 4   | 5   | 6   | 7   |
| --- | --- | ----- | --- | --- | --- | --- | --- | --- |
| 0   | *1* | **1** | 0   | 0   | 0   | 0   | 0   | 0   |
| 1   | *1* | **0** | 0   | 0   | 0   | 0   | 0   | 0   |
| 2   | *0* | **0** | 0   | 0   | 0   | 0   | 0   | 0   |
| 3   | *0* | **1** | 0   | 0   | 0   | 0   | 0   | 0   |
| 4   | *1* | **1** | 0   | 0   | 0   | 0   | 0   | 0   |
| 5   | *1* | **1** | 0   | 0   | 0   | 0   | 0   | 0   |
una volta che nella tabella sono entrati tutti i valori di R

| x   | 0   | 1   | 2   | 3   | 4   | 5   | 6     | 7   | x         |
| --- | --- | --- | --- | --- | --- | --- | ----- | --- | --------- |
| 0   | 1   | 0   | 1   | 0   | 1   | *1* | **1** | 0   | =174      |
| 1   | 0   | 0   | 1   | 1   | 1   | *1* | **0** | 0   | ***=60*** |
| 2   | 1   | 0   | 1   | 1   | 1   | *0* | **0** | 0   | =184      |
| 3   | 0   | 1   | 0   | 1   | 1   | *0* | **1** | 0   | =90       |
| 4   | 1   | 1   | 0   | 1   | 0   | *1* | **1** | 0   | =214      |
| 5   | 1   | 1   | 0   | 0   | 1   | *1* | **1** | 0   | =206      |
Il valore più basso indica la pagina usata meno frequentemente negli ultimi 8 clock e quindi viene spostata sul disco la pagina 1

# ES 16
Ipotizzando di utilizzare l'algoritmo WSClock se le pagine in memoria sono: $$\begin{align*}
&[P_{0},100,1,1],[P_{1},200,1,1],[P_{2},80,1,0],[P_{3},150,0,1],\\
&[P_{4},180,1,1],[P_{5},250,1,0],[P_{6},320,0,1],[P_{7},440,0,0]
\end{align*}$$dove ogni quadrupla ha il seguente significato $$[\text{Pagina, tempo ultimo utilizzo, bit R, bit M}]$$ e se età max = 150 (limite oltre la quale la pagina è candidata alla sostituzione) Tempo corrente = 450, la posizione corrente è sulla pagina P4, quale pagina verrà rimossa a seguito di un page fault? Spiegarne i motivi

**Sol.**
Occorre rappresentare i dati del problema su uno schema ad orologio

![[AE/img/img100.png|center|600]]

Ad ogni page fault, l'algoritmo ricerca la pagina candidata alla sostituzione, partendo da quella indicata dalla lancetta dell'orologio:
- se il bit R=1 significa che la pagina è stata utilizzata recentemente e non deve essere rimossa perché potrebbe essere di nuovo riutilizzata, tuttavia si azzera il bit R perché se non dovesse essere usata fino al prossimo giro di lancetta, si potrà valutare la sua sostituzione ; la lancetta passa alla pagina successiva
- se il bit R=0 la sua eta>eta max e M=0, allora la pagina è pulita (cioè esiste una copia valida sul disco) e non è più nell'insieme delle pagine utilizzate, quindi può essere sovrascritta
- se il bit R=0 la sua eta>eta max e M=1, allora la pagina è sporca (non esiste una copia valida sul disco), ma per evitare un cambiamento di processo, viene schedulata la scrittura sul disco e la lancetta passa alla pagina successiva
- se si torna nella posizione da cui la lancetta è partita, allora si rimuove la prima pagina schedulata che si incontra oppure, se non ce n'è nessuna, quella successiva

La pagina iniziale P4 ha il bit R=1 quindi si azzera R e si passa alla pagina successiva. 
Il procedimento è lo stesso anche per P5.
Alla pagina P6 ha il bit a zero e si calcola l'età che risulta 130 (450-320) ed essendo inferiore al limite di 150 è possibile che venga utilizzata ancora.
P7 non è stata referenziata ne modificata ed ha un età molto giovane =10 (450-440), si passa avanti
P0 ha il bit R=1, si azzera e si passa avanti
P1 ha R=1, si azzera e si passa avanti
P2 ha R=1, si azzera e si passa avanti
P3 ha R=0 ed eta=450-150=300 che eccede il limite, ma ha il Bit M=1, quindi viene schedulata la scrittura sul disco e si passa oltre.
A questo punto, P4 ha R=0, ma M=1 quindi la pagina è sporca, allora viene schedulata la scrittura in coda a P3 e si passa alla pagina successiva P5.
Se non sono avvenuti nel frattempo accessi a P5, essendo con i due bit a zero con età maggiore del massimo (450-250=200>150) questa pagina sarà scelta per la sostituzione

# Es riepilogo
## Es 1  processi e thread
Disponiamo di un calcolatore con i parametri:
- mem_totale = memoria complessiva disponibile
- mem_so = memoria occupata dal sistema operativo
- t_waitcpu = tempo di attesa medio della cpu
- t_waitI/O = tempo di attesa medio I/O
Trovare la dimensione media dei processi.
Se il processo occupasse il valore specificato nella tabella con MEM_prc, calcolare come si modificherebbe il tempo medio di attesa della cpu.

**Sol 1.**

| $MEM_{TOTALE}$ | $MEM_{SO}$ | $T_{WAIT_{CPU}}$ | $T_{WAIT_{I/O}}$ | $MEM_{PRC}$ |
| -------------- | ---------- | ---------------- | ---------------- | ----------- |
| 5 GB           | 2048 MB    | 95%              | 55%              | 768 MB      |

Per trovare la dimensione dei processi procediamo con il procedimento inverso.
Sappiamo che il carico sulla CPU è dato dalla formula $$T_{WAIT_{CPU}}=1-T_{WAIT_{I/O}}^{NUM\_PROCESSI}$$
ovvero la formula $$\text{carico\_Cpu}=1-p^{n}$$ 
Quindi per prima cosa ci serve sapere quanto vale $n$, analizziamo la formula.
$$n=\frac{mem\_{totale} \ - mem\_so}{mem\_{processi}}$$
la formula per trovare $mem\_processi$ è quindi la formula inversa $$mem\_{processi}=\frac{mem\_{totale} \ - mem\_so}{n}$$

Per trovare $n$ utilizziamo la prima formula, perché conosciamo il valore del carico sulla CPU e il valore di $p$ quindi $$\begin{align*}
p^{n}&=1-carico\_cpu=\\\\
55\%^{n}&= 1-95\%=\\\\
0.55^{n}&= 1-0.95=\\\\
0.55^{n}&= 0.05=\\\\
\log_{0.55}(0.55^{n})&=\log_{0.55}(0.5)=\\\\
n\cdot\cancel{\log_{0.55}(0.55)}^{1}&= \log_{0.55}(0.5)=\\\\
n&= 5.011=5 
\end{align*}$$ quindi il nostro numero di processi è 5.
Ora possiamo calcolarci la memoria con la formula inversa dei processi $$mem_{processi}=\frac{5120-2048}{5.011}=\frac{3072}{5.011}=613.05123=613.1$$
Ora vediamo il secondo punto. 
Dobbiamo calcolare il cambiamento del carico sulla cpu, avendo gli altri parametri invariati e una memoria per ogni processo di 768 MB. $$n=\frac{3072}{768}=4$$
e quindi $$carico\_cpu=1-0.55^4=0.908=90.8\%$$
## ES 2
Disponiamo di un calcolatore con i parametri:
- mem_totale = memoria complessiva disponibile
- mem_so = memoria occupata dal sistema operativo
- t_waitcpu = tempo di attesa medio della cpu
- mem_prc = la dimensione media dei processi
Trovare il tempo di attesa dell'I/O.
Se il processo occupasse il valore specificato nella tabella con $\overline{MEM_{prc}}$, calcolare come si modificherebbe il tempo medio di attesa dell'I/O.

| mem_totale | mem_so | t_waitcpu | mem_prc | $\overline{MEM_{prc}}$ |
| ---------- | ------ | --------- | ------- | ---------------------- |
| 1024 mb    | 512 mb | 60%       | 128 mb  | 256 mb                 |

**Sol**
abbiamo la formula $$\text{carico\_cpu}=1-p^n$$ dove $p$ è il tempo media di attesa dell'I/O. Otteniamo la formula inversa $$\begin{align*}
p^{n}&=1-\text{carico\_cpu}=\\\\
\sqrt[\cancel{n}]{p^{\cancel{n}}} &=\sqrt[n]{1-\text{carico\_cpu}}
\end{align*}$$
dobbiamo trovare quindi il valore di $n$ dalla formula nota $$\begin{align*}
n&=\frac{\text{mem\_totale - mem\_so}}{mem\_prc}\\\\
&=\frac{1024-512}{128}=\frac{512}{128}=4
\end{align*}$$
quindi il  tempo medio d'attesa risulta $$\begin{align*}
p&=\sqrt[4]{1-0.60}=\sqrt[4]{0.40}=0.795=79.5\%
\end{align*}$$
Per il secondo punto le formule da utilizzare sono le stesse.

## ES 3 scheduling
Supponendo di utilizzare l'algoritmo FIRST COME FIRST SERVED per lo scheduling, se arrivano i job con i tempi espressi in ms nella tabella, calcolare i tempi medi di attesa e di turnaround.

| PROCESSO | P1  | P2  | P3  | P4  | P5  | P6  |
| -------- | --- | --- | --- | --- | --- | --- |
| $T_A$    | 3   | 2   | 4   | 0   | 1   | 5   |
| $T_X$    | 1   | 1   | 3   | 3   | 3   | 2   |

**Sol.**
Disponiamo i processi in una tabella in ordine di arrivo

| i   | $P_{k}$ | $T_{A}(i)$ | $T_{X}(i)$ |
| --- | ------- | ---------- | ---------- |
| 1   | P4      | 0          | 3          |
| 2   | P5      | 1          | 3          |
| 3   | P2      | 2          | 1          |
| 4   | P1      | 3          | 1          |
| 5   | P3      | 4          | 3          |
| 6   | P6      | 5          | 2          |

con le formule note calcoliamoci i tempi di inizio, fine esecuzione, attesa e turnaround

| i   | $P_{k}$ | $T_{A}(i)$ | $T_{X}(i)$ | $T_S(i)$ | $T_E(i)$ | $T_W(i)$ | $T_T(i)$ |
| --- | ------- | ---------- | ---------- | -------- | -------- | -------- | -------- |
| 1   | P4      | 0          | 3          | 0        | 3        | 0        | 3        |
| 2   | P5      | 1          | 3          | 3        | 6        | 2        | 5        |
| 3   | P2      | 2          | 1          | 6        | 7        | 4        | 5        |
| 4   | P1      | 3          | 1          | 7        | 8        | 4        | 5        |
| 5   | P3      | 4          | 3          | 8        | 11       | 4        | 7        |
| 6   | P6      | 5          | 2          | 11       | 13       | 6        | 8        |
Quindi il tempo medio di attesa e di turnaround risulta $$\begin{align*}
&\overline{T_{W}}=\frac{0+2+4+4+4+6}{6}=\frac{20}{6}=3.33 ms\\\\
&\overline{T_{T}}=\frac{3+5+5+5+7+8}{6}=\frac{33}{6}=5.5 ms
\end{align*}$$
## ES 4 
Supponendo di utilizzare l'algoritmo SHORTEST JOB FIRST per lo scheduling, dovendo eseguire i processi in tabella, quali saranno i tempi medi di attesa e di turnaround?

| PROCESSO | P1  | P2  | P3  | P4  | P5  | P6  |
| -------- | --- | --- | --- | --- | --- | --- |
| $T_X$    | 10  | 4   | 7   | 3   | 3   | 12  |
**Sol.**
Ordiniamo la tabella in ordine crescente di tempo di esecuzione e calcoliamo $T_{S}(i)$, $T_{E}(i)$, $T_{W}(i)$, $T_{T}(i)$

| i   | $P_{k}$ | $T_{A}(i)$ | $T_{S}(i)$ | $T_{E}(i)$ | $T_{W}(i)$ | $T_{T}(i)$ |
| --- | ------- | ---------- | ---------- | ---------- | ---------- | ---------- |
| 1   | P4      | 3          | 0          | 3          | 0          | 3          |
| 2   | P5      | 3          | 3          | 6          | 3          | 6          |
| 3   | P2      | 4          | 6          | 10         | 6          | 10         |
| 4   | P3      | 7          | 10         | 17         | 10         | 17         |
| 5   | P1      | 10         | 17         | 27         | 17         | 27         |
| 6   | P6      | 12         | 27         | 39         | 27         | 39         |

I tempi medi di attesa e di turnaround risultano $$\begin{align*}
&\overline{T_{W}}=\frac{0+3+6+10+17+27}{6}=\frac{63}{6}=10.5 ms\\\\
&\overline{T_{T}}=\frac{3+6+10+17+27+39}{6}=\frac{102}{6}=17 ms
\end{align*}
$$


## ES 5
Supponendo di utilizzare l'algoritmo SHORTEST REMAINING NEXT per lo scheduling, se arrivano i job nell'ordine indicato in tabella con i relativi tempi di esecuzione espressi in millisecondi, quali saranno il tempo medio di turnaroud e di attesa?

| PROCESSO | P1  | P2  | P3  | P4  | P5  | P6  |
| -------- | --- | --- | --- | --- | --- | --- |
| $T_A$    | 5   | 4   | 2   | 1   | 0   | 2   |
| $T_X$    | 1   | 2   | 5   | 2   | 4   | 2   |

**Sol.**

| x   |    1 |    2 |   3 |   4 |   5 |    6 |   7 |   8 |   9 |  10 |  11 |  12 |  13 |  14 |  15 |  16 |
| --- | ---: | ---: | --: | --: | --: | ---: | --: | --: | --: | --: | --: | --: | --: | --: | --: | --: |
| P1  |      |      |     |     |     | c ex |     |     |     |     |     |     |     |     |     |     |
| P2  |      |      |     |     |   c |      |  ex |  ex |     |     |     |     |     |     |     |     |
| P3  |      |      |   c |     |     |      |     |     |     |     |     |  ex |  ex |  ex |  ex |  ex |
| P4  |      | c ex |  ex |     |     |      |     |     |     |     |     |     |     |     |     |     |
| P5  | c ex |      |     |     |     |      |     |     |  ex |  ex |  ex |     |     |     |     |     |
| P6  |      |      |   c |  ex |  ex |      |     |     |     |     |     |     |     |     |     |     |

Otteniamo quindi la tabella 

| i   | $P_{k}$ | $T_{A}(i)$ | $T_{X}(i)$ | $T_S(i)$ | $T_E(i)$ | $T_W(i)$ | $T_T(i)$ |
| --- | ------- | ---------- | ---------- | -------- | -------- | -------- | -------- |
| 1   | P5      | 0          | 4          | 0        | 11       | 0        | 11       |
| 2   | P4      | 1          | 2          | 1        | 3        | 0        | 2        |
| 3   | P6      | 2          | 2          | 3        | 5        | 1        | 3        |
| 4   | P3      | 2          | 5          | 11       | 16       | 9        | 14       |
| 5   | P2      | 4          | 2          | 6        | 8        | 2        | 4        |
| 6   | P1      | 5          | 1          | 5        | 6        | 0        | 1        |

I tempi medi di attesa e turnaround risultano $$\begin{align*}
&\overline{T_{W}}=\frac{0+0+1+9+2+0}{6}=\frac{12}{6}=2 ms\\\\
&\overline{T_{T}}=\frac{11+2+3+14+4+1}{6}=\frac{35}{6}=5.8 ms
\end{align*}$$
## ES 6
Supponendo di utilizzare l'algoritmo ROUND-ROBIN per lo scheduling, se l'ordine di esecuzione è quello specificato in tabella insieme al quantum $Q_{1}$ e al tempo necessario per il context switch $T_{CS}$ espressi in $\micro s$, dopo quanto tempo sarà eseguito P5 se è scaduto il quantum del processo P4?
Qual è il rapporto tra cambio di contesto e tempo di esecuzione? 
Come cambiano i tempi analizzati nel caso se il quantum è pari a $Q_{2}$?
Quale delle due soluzioni sembra più favorevole tenendo conto che il parametro $P_{BOUND}$ specifica la caratteristica dei processi?

$P1\to P8\to P7\to P2\to P3\to P5\to P6\to P4$

| Q1          | 100       |
| ----------- | --------- |
| $T_{CS}$    | 5         |
| Q2          | 50        |
| $T_{BOUND}$ | I/O-bound |

**Sol.**
P4 ha terminato il suo quanto di tempo, quindi, con un cambio di contesto si ripassa all'esecuzione di P1. Il tempo per arrivare a P5 è dunque $$5+100+5+100+5+100+5+100+5+100+5=530\micro s$$
Il rapporto tra cambio di contesto e quanto d'esecuzione è $$\frac{5}{100}=0.05=5\%$$
Se il quantum è pari a $Q_2$, per eseguire P5 necessitano $$5+50+5+50+5+50+5+50+5+50+5=280\micro s$$
Il rapporto tra cambio di contesto e quanto d'esecuzione è $$\frac{5}{50}=0.1=10\%$$
## ES 7 shortest process next
Supponendo di utilizzare l'algoritmo SHORTEST PROCESS NEXT con tecnica di aging per lo scheduling di un sistema interattivo, stimare il tempo di esecuzione indicato in tabella conoscendo i pecedenti tempi di esecuzione

| T0  | T1  | T2  | T3  | T4  | T5  |
| --- | --- | --- | --- | --- | --- |
| 30  | 49  | 45  | 67  | 72  | ?   |

**Sol.**
ponendo $a=\frac{1}{2}$ risulta
$$\begin{align*}
\tilde{T_{5}}&=\frac{T_{4}}{2}+\frac{\tilde{T_{4}}}{2}=\\\\
&=\frac{T_{4}}{2}+ \frac{1}{2}\bigg(\frac{T_{3}}{2}+\frac{\tilde{T_{3}}}{2}\bigg)=\\\\
&=\frac{T_{4}}{2}+ \frac{1}{4}\bigg[T_{3}+ \frac{1}{2}\bigg(T_{2}+\tilde{T_{2}}\bigg)\bigg]=\\\\
&= \frac{T_{4}}{2}+ \frac{1}{4}\bigg[T_{3}+ \frac{1}{2}\bigg[T_{2}+ \frac{1}{2}\bigg(T_{1}+\tilde{T_{1}}\bigg)\bigg]=\\\\
&= \frac{T_{4}}{2}+ \frac{1}{4}\bigg[T_{3}+ \frac{1}{2}\bigg[T_{2}+ \frac{1}{2}\bigg(T_{1}+T_{0}\bigg)\bigg]=\\\\
&=\frac{T_{4}}{2}+ \frac{T_{3}}{4}+ \frac{T_{2}}{8}+ \frac{T_{1}}{16}+\frac{T_{0}}{16}=\\\\
&=36+16.75+5.62+1.06+1.87=61.3
\end{align*}$$

## ES 8 lotteria
Un sistema utilizza l'algoritmo di scheduling a lotteria ed ha assegnato i biglietti indicati in tabella. Al momento dell'estrazione qual è la probabilità di vincita di P1? 
Nel caso P1 ceda la metà dei suoi biglietti agli altri processi come muta la probabilità di P1 e P2.

| P1  | P2  | P3  | P4  | P5  |
| --- | --- | --- | --- | --- |
| 40  | 30  | 30  | 30  | 30  |

**Sol.**
Probabilità vincita P1 = $\frac{40}{160}=25\%$

P1 cede la metà dei suoi biglietti agli altri processi quindi la tabella diventa

| P1  | P2  | P3  | P4  | P5  |
| --- | --- | --- | --- | --- |
| 20  | 35  | 35  | 35  | 35  |

Probabilità vincita P1 = $\frac{20}{160}=12.5\%$
Probabilità vincita P2=$\frac{35}{160}=21.9\%$

## ES 9
Supponendo di dover valutare la sostenibilità di un sistema soft real-time con eventi periodici P1, P2, P3, P4 rappresentati in tabella con i rispettivi tempi di elaborazione, determinare la percentuale di carico del sistema. Se si aggiunge il nuovo evento periodico P5, come muta il carico del sistema?

| i     | 1            | 2              | 3       | 4       | 5       |
| ----- | ------------ | -------------- | ------- | ------- | ------- |
| $P_i$ | $1 \micro s$ | $300 \micro s$ | $22 ms$ | $800ms$ | $100ms$ |
| $C_i$ | 500 ns       | 0.01 ms        | 2ms     | 0.2 s   | 10 ms   |

**Sol.**
Un sistema è sostenibile se $$\sum\limits \frac{C_{i}}{P_{i}}\leq 1$$
quindi 

| i                 | 1                 | 2                  | 3                 | 4                 | 5                 |
| ----------------- | ----------------- | ------------------ | ----------------- | ----------------- | ----------------- |
| $P_i$             | $1\cdot10^{-6}$   | $300\cdot10^{-6}$  | $22\cdot10^{-3}$  | $800\cdot10^{-3}$ | $100\cdot10^{-3}$ |
| $C_i$             | $500\cdot10^{-9}$ | $0.01\cdot10^{-3}$ | $2\cdot10^{-3}$   | 0.2 s             | $10\cdot10^{-3}$  |
| $\frac{C_i}{P_i}$ | 0.5               | $0.0\overline{3}$  | $0.\overline{09}$ | 0.25              | 0.1               |
 $$0.5+0.03+0.09+0.25=87.4\%$$
Se si aggiunge P5 il carico diventa $$0.5+0.03+0.09+0.25+0.1=97.4\%$$ 
## ES 10
Supponendo di dover valutare la sostenibilità di un sistema soft real time con eventi periodici P1, P2, P3, P4 rappresentati in tabella con i rispettivi tempi di elaborazione, determinare la percentuale di carico del sistema. Se si aggiunge il nuovo evento periodico P5, qual è il valore limite per C5 affinché il sistema rimanga sostenibile?

| i     | 1             | 2             | 3     | 4       | 5       |
| ----- | ------------- | ------------- | ----- | ------- | ------- |
| $P_i$ | $30 \micro s$ | $0.15ms$      | $8s$  | $870ns$ | $450ms$ |
| $C_i$ | $5 \micro s$  | $10 \micro s$ | 700ms | 547ns   | ?       |

**Sol.**

| i                 | 1                 | 2                  | 3                 | 4                 | 5                 |
| ----------------- | ----------------- | ------------------ | ----------------- | ----------------- | ----------------- |
| $P_i$             | $30\cdot10^{-6}$  | $0.15\cdot10^{-3}$ | $8s$              | $870\cdot10^{-9}$ | $450\cdot10^{-3}$ |
| $C_i$             | $5\cdot10^{-6}$   | $10\cdot10^{-6}$   | $700\cdot10^{-3}$ | $547\cdot10^{-9}$ | ?                 |
| $\frac{C_i}{P_i}$ | $0.1\overline{6}$ | $0.0\overline{6}$  | 0.0875            | 0.6287            |                   |

Il carico del sistema è $$0.1\overline{6}+0.0\overline{6}+0.0875+0.6287=94.9\%$$
Se arriva l'evento periodico P5 occorre calcolare il valore limite per C5 
$$\begin{align*}
1-0.949=\frac{C_5}{P_{5}}=0.051\cdot P_{5}=C_{5}\\\\
C_{5}=0.051\cdot450=22.95ms
\end{align*}$$

## ES 12 Gestione della memoria
Nell'ambito della gestione della memoria con lista collegata in tabella, dove verrà posizionato il nuovo processo P5 che occupa 3 blocchi se si usa l'algoritmo next fit e la posizione è nell'ultimo elemento?
Dove verrà posizionato se si usa invece il worst e il best fit?

$$\begin{align*}
&[P_{0},0,4]\to[L,4,3]\to[P_{1},7,3]\to[P_{2},10,4]\to[L,14,1]\to\\\\
&\to[P_{3},15,3]\to[L,18,2]\to[P_{4},20,1]\to[L,21,4]
\end{align*}$$

**Sol.**
next fit: posizione 21
worst fit = 21
best fit = 4

## ES 13
Nell'ambito della gestione della memoria con bitmap (5x5) se la memoria è allocata nel modo indicato in tabella e arriva il processo P5 che occupa 2 blocchi, utilizzando gli algoritmi best e worst fit, quale sarà la nuova bitmap?

| proc        | P1  | P2  | P3  | P4  |
| ----------- | --- | --- | --- | --- |
| posizione   | 0   | 5   | 10  | 15  |
| dim blocchi | 3   | 1   | 4   | 2   |

**Sol.**
Questa è la bitmap iniziale

| x   | 0   | 1   | 2   | 3   | 4   |
| --- | --- | --- | --- | --- | --- |
| 0   | 1   | 1   | 1   | 0   | 0   |
| 1   | 1   | 0   | 0   | 0   | 0   |
| 2   | 1   | 1   | 1   | 1   | 0   |
| 3   | 1   | 1   | 0   | 0   | 0   |
| 4   | 0   | 0   | 0   | 0   | 0   |

Bitmap con algoritmo best fit P5 viene inserito a partire dal terzo blocco

| x   | 0   | 1   | 2   | 3     | 4     |
| --- | --- | --- | --- | ----- | ----- |
| 0   | 1   | 1   | 1   | **1** | **1** |
| 1   | 1   | 0   | 0   | 0     | 0     |
| 2   | 1   | 1   | 1   | 1     | 0     |
| 3   | 1   | 1   | 0   | 0     | 0     |
| 4   | 0   | 0   | 0   | 0     | 0     |

Bitmap con worst fit P5 inserito a partire dal blocco 17 

| x   | 0   | 1   | 2     | 3     | 4   |
| --- | --- | --- | ----- | ----- | --- |
| 0   | 1   | 1   | 1     | 0     | 0   |
| 1   | 1   | 0   | 0     | 0     | 0   |
| 2   | 1   | 1   | 1     | 1     | 0   |
| 3   | 1   | 1   | **1** | **1** | 0   |
| 4   | 0   | 0   | 0     | 0     | 0   |

## ES 14
Supponendo di utilizzare l'algoritmo LEAST RECENTLY USED con 5 frame di memoria, se le pagine referenziate sono 0,1,2,3,4,2,0,1 , quale pagina verrà posta sul disco?


**Sol.**
Utilizziamo una matrice 5x5 e per ogni pagina referenziata in ordine poniamo a 1 la riga relativa alla pagina e a 0 la colonna relativa

| x   | 0   | 1   | 2   | 3   | 4   |      |
| --- | --- | --- | --- | --- | --- | ---- |
| 0   | 0   | 0   | 1   | 1   | 1   | = 7  |
| 1   | 1   | 0   | 1   | 1   | 1   | = 23 |
| 2   | 0   | 0   | 0   | 1   | 1   | = 3  |
| 3   | 0   | 0   | 0   | 0   | 0   | = 0  |
| 4   | 0   | 0   | 0   | 1   | 0   | = 2  |

La pagina utilizzata meno recentemente è la pagina 3, che viene spostata sul disco

## ES 15
Supponendo di utilizzare l'algoritmo not frequently used con tecnica di aging che utilizzi una memoria con 6 frame e dei contatori ad 8 bit se i valori dei bit R nei successivi cicli sono quelli indicati sotto, quale pagina verrà spostata sul disco?
$$101001,\ 000011,\ 111100,\ 100000,\ 000001,\ 101000,\ 111001$$

**Sol.**

| x   | 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   |      |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | ---- |
| 0   | 1   | 1   | 0   | 1   | 1   | 0   | 1   | 0   | =218 |
| 1   | 1   | 0   | 0   | 0   | 1   | 0   | 0   | 0   | =136 |
| 2   | 1   | 1   | 0   | 0   | 1   | 0   | 1   | 0   | =202 |
| 3   | 0   | 0   | 0   | 0   | 1   | 0   | 0   | 0   | =8   |
| 4   | 0   | 0   | 0   | 0   | 0   | 1   | 0   | 0   | =4   |
| 5   | 1   | 0   | 1   | 0   | 0   | 1   | 1   | 0   | =166 |
La pagina che viene spostata sul disco è la pagina P4

## ES 16
Utilizzando algoritmo WSCLOCK se le pagine in memoria sono quelle indicate nella tabella, età max = 150, tempo corrente = 450 e la posizione corrente è sulla pagina iniziale, quale pagina verrà rimossa a seguito di un page fault?

$$\begin{align*}
[P0,100,0,1],\ [P1,200,0,1],\ [P2,80,1,0],\ [P3,400,0,0],\\\\
[P4,350,0,0],\ [P5,250,0,1],\ [P6,120,0,0],\ [P7,340,0,1] 
\end{align*}$$

**Sol.**
P0 ha R=0, M=1 ed età oltre il limite. Viene schedulata la scrittura su disco
P1 ha R=0, M=1 ed età oltre il limite. Viene schedulata la scrittura su disco in coda a P0
P2 ha il bit R=1, viene posto a 0 e si passa avanti
P3 ha R=0, M=0 ma età minore del massimo, si passa avanti
P4 ha R=0, M=0 ed età minore del massimo, si passa avanti
P5 ha R=0, M=1 ed età oltre il limite, viene schedulata la scrittura su disco in coda a P1
P6 ha R=0, M=0 ed età oltre il limite, viene scelta per la rimozione

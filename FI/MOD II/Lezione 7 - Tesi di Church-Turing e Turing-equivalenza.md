*********
Nelle lezioni precedenti abbiamo parlato di calcolabilità di funzioni e decidibilità di linguaggi sempre in relazione alle macchine di Turing. Potremmo ora domandarci se, utilizzando modelli di calcolo più potenti, non sia possibile calcolare funzioni non calcolabili dalle mdT. 
Nella teoria della calcolabilità è stata proposta un'ampia gamma di modelli di calcolo, come ad esempio:
- grammatiche di tipo 0
- modello di Kleene basato sulle equazioni funzionali
- il $\lambda$-calcolo di Church 
- ...

# Tesi Church-Turing
Riguardo al potere computazionale di questi modelli, è stato dimostrato che sono tutti **Turing-equivalenti**, ovvero una funzione calcolabile con uno di quei modelli è anche calcolabile con una macchina di Turing. 

Questa osservazione ha portato ad enunciare questa tesi, conosciuta come **tesi di Church-Turing**, che afferma che se la soluzione di un problema può essere descritta in una serie finita di *passi elementari*, allora esiste una macchina di Turing in grado di calcolarlo. In altri termini

>È calcolabile tutto (e solo) ciò che può essere calcolato da una macchina di Turing

La tesi afferma dunque che non esiste un modello di calcolo più potente della macchina di Turing.
Nel corso delle lezioni ci riferiremo a programmi scritti in un linguaggio specifico invece delle macchine di Turing. Questo linguaggio, oltre al concetto di variabili e collezioni di dati, utilizza le istruzioni seguenti: 
- istruzione di assegnazione "$x\leftarrow y$ "
- l'istruzione condizionale $\textbf{if } \text{(condizione)} \textbf{ then begin }\langle\text{istruzioni}\rangle \textbf{ end else begin }\langle\text{istruzioni}\rangle\textbf{ end }$ in cui la parte **else** può essere assente
- l'istruzione di loop $\textbf{while }\text{(condizione) } \textbf{ do begin }\langle\text{istruzioni}\rangle\textbf{ end}$
- l'istruzione di output, che deve esssere l'ultima istruzione del programma che comunica il valore di una variabile $\textbf{Output: }\langle \text{nomeDiVariabile}\rangle$
Se le sequenze di istruzioni sono istruzioni singole, si può omettere il $\textbf{begin end}$ e assumiamo che l'input venga comunicato al programma prima che le istruzioni inizino mediante l'istruzione $\textbf{Input: }$. 
Il linguaggio seguente lo denominiamo **PascalMinimo**.

**Teorema 3.5**
Per ogni programma scritto nel linguaggio **PascalMinimo** esiste una macchina di Turing $T$ di tipo trasduttore che scrive sul nastro di output lo stesso valore fornito in output dal programma.

**Idea della dimostrazione**
Per la dimostrazione ci limitiamo al caso in cui il programma contenga solamente variabili semplici, ovvero non array. Sia $\mathscr P$ un programma scritto in PascalMinimo e che non contenga variabili strutturati. La macchina di Turing $T$ è definita nel seguente modo:
- $T$, oltre ai nastri di input ed output, utilizza un nastro per ciascuna variabile e valore costante che compare in una condizione.
	- Ad esempio se in $\mathscr P$ compare l'istruzione $\textbf{if }\text{(a=2)}\textbf{ then }\text{b=1}$, allora $T$ utilizza un nastro per la variabile $a$, un nastro per la variabile $b$, un nastro per il valore costante $2$ e un nastro per il valore costante $1$. Infine $T$ utilizza un nastro di lavoro per la valutazione delle espressioni e delle condizioni contenute nel programma.
- I contenuti del nastro sono codificati in binario
- Ad ogni istruzione di assegnazione in $\mathscr P$ corrisponde uno stato $q_{i}$ con $i>0$ in $T$
- Ad ogni istruzione condizionale in $\mathscr P$ corrisponde uno stato $q_i$ oppure una coppia di stati $q_{i},q_{j}:i,j>0$, rispettivamente, nel caso in cui sia assente oppure presene la parte $\textbf{else}$ 
- Ad ogni istruzione di loop in $\mathscr P$ corrisponde uno stato $q_i:i>0$ in $T$
- Lo stato iniziale di $T$ è $q_{0}$ 

![[FI/MOD II/img/img8.png|center|600]]

Vediamo quindi come costruire una quintupla partendo da un'istruzione in $\mathscr P$. Per semplicità, assumiamo di scrivere una singola istruzione per linea di codice, per avere una corrispondenza fra linee di codice e stati della macchina. 

1. Ad ogni assegnazione di un valore ad una variabile, corrisponde una copia del nastro corrispondente alla costante o alla variabile sulla destra dell'assegnazione, sul nastro corrispondente alla variabile che deve prendere il valore (la parte sinistra dell'assegnazione). La sequenza termina con la macchina che entra nello stato corrispondente all'istruzione eseguita (ad esempio, prima di eseguire l'istruzione in linea 1, la macchina si trova nello stato $q_{0}$ e dopo averla eseguita entrerà in $q_{1}$). 
2. Ad ogni assegnazione di una espressione ad una variabile (linea 7) corrisponde una sequenza di quintuple che eseguono quell'espressione sul nastro di lavoro e che terminano con la scrittura sul nastro corrispondente alla variabile che deve prendere il valore. La sequenza termina con la macchina che entra nello stato corrispondente all'istruzione eseguita.
3. Ogni condizione viene valutata, utilizzando il nastro di lavoro, confrontando i contenuti dei due nastri interessati. Ad esempio in linea 2 vengono confrontati i nastri corrispondenti alle variabili $n$ e $m$. Dopo aver valutato la condizione, la macchina entra in uno stato che dipende dal valore della condizione e dal tipo di istruzione in cui è usata la condizione
	-  In una istruzione **if-then-else** se la condizione è vera, la macchina entra in uno stato che permette di eseguire le istruzioni del ramo **if**, mentre se falsa, la macchina entra in uno stato che permette di eseguire le istruzioni del ramo **else**. Le quintuple seguenti indicano il comportamento delle linee 2-5 $$\begin{align*}
&\langle q_{1},n>m,(\dots ,n,\dots ,m,\dots),q_{2},\cdot\rangle\\
&\langle q_{1},n\leq m,(\dots ,n,\dots ,m,\dots),q_{3},\cdot\rangle\\
&\langle q_{2},p\leftarrow n,(\dots),q_{4},\cdot\rangle\\
&\langle q_{3},p\leftarrow m,(\dots),q_{4},\cdot\rangle
\end{align*}$$
	- In una istruzione **while** se la condizione è vera allora la macchina entra in uno stato che permette di eseguire la prima istruzione del corpo del loop, altrimenti entra in uno stato che esegue la prima istruzione successiva al corpo del loop. Una volta eseguita l'ultima istruzione del corpo del loop, la macchina rientra nello stato di verifica della condizione del while. Le quintuple indicano il comportamento delle linee 6-8 $$\begin{align*}
&\langle q_{4},p\geq2,(\dots ,p,\dots,2,\dots),q_{5},\cdot\rangle\\
&\langle q_{4},p<2,(\dots ,p,\dots,2,\dots),q_{6},\cdot\rangle\\
&\langle q_{5},(\dots),p\leftarrow p-k,q_{4},\cdot\rangle
\end{align*}$$
4. L'istruzione di output corrisponde alla scrittura sul nastro di output con la macchina che entra nello stato finale
5. Chiariamo ora come collegare le quintuple tra loro. Da quello scritto si intuisce che *lo stato con il quale la macchina termina una istruzione è lo stato che le consente di iniziare l'istruzione successiva*. Osserviamo che nelle quintuple descritte sopra, lo stato $q_{1}$ che corrisponde all'esecuzione della linea 1, è lo stato col quale inizia l'istruzione **if** alla linea 2. Questo è valido con solo due istruzioni:
	- lo stato col quale la macchina termina l'esecuzione di un blocco **if** deve passare il controllo all'istruzione *successiva all'* **else** 
	- lo stato col quale la macchina termina l'esecuzione dell'ultima istruzione del corpo di un **while** deve passare il controllo all'istruzione *che testa la condizione del loop*
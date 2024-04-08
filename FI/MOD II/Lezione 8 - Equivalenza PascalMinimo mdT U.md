*******
Abbiamo visto nella [[Lezione 7 - Tesi di Church-Turing e Turing-equivalenza|lezione precedente]] come il **PascalMinimo** non è un modello di calcolo più potente della macchina di Turing. In questa lezione dimostreremo la proposizione inversa, ovvero che le macchine di Turing non sono sistemi di calcolo più potenti del linguaggio **PascalMinimo**, dimostrando quindi la Turing-equivalenza.

**Teorema 3.6**
Per ogni macchina di Turing deterministica $T$ di tipo riconoscitore ad un nastro esiste un programma $\mathscr P$ scritto in accordo alle regole del **PascalMinimo** tale che, per ogni stringa $x$, se $T(x)$ termina in uno stato finale $q_{F}\in\{q_A,q_R\}$ allora $\mathscr P$ con input $x$ restituisce $q_F$.

**Idea della dimostrazione**
Siano $\langle q_{1_{1}},s_{1_{1}},s_{1_{2}},q_{1_{2}},m_{1}\rangle\langle q_{2_{1}},s_{2_{1}},s_{2_{2}},q_{2_{2}},m_{2}\rangle\dots\langle q_{k_{1}},s_{k_{1}},s_{k_{2}},q_{k_{2}},m_{k}\rangle$ le quintuple che descrivono la macchina di Turing $T$ (dove $q_{i_{2}}$ sarà lo stato di accettazione $q_A$ e $q_{j_{2}}$ sarà lo stato di rigetto $q_R$).
Nell'idea assumeremo che il movimento della testina di $T$ sia rappresentato da un intero in $\{-1,0,+1\}$ rispettivamente $\{s,f,d\}$

Possiamo quindi scrivere un programma $\mathscr P$ che si comporta come la [[Lezione 5 - La macchina di Turing Universale|macchina di Turing Universale]]. L'input del programma $\mathscr P$ è costituito, oltre che dalla parola $x$, anche dalla descrizione della macchina $T$ che deve essere simulata, dalla descrizione di $\Sigma$, dall'insieme degli stati $Q$, dalle quintuple $\langle q_{1_{1}},s_{1_{1}},s_{1_{2}},q_{1_{2}},m_{1}\rangle\dots\langle q_{k_{1}},s_{k_{1}},s_{k_{2}},q_{k_{2}},m_{k}\rangle$ e dagli stati iniziali e finali di $T$. 
Vengono utilizzate per il programma, le variabili seguenti: 
- L'array $Q=\{q_{1},q_{2},\dots,q_{m}\}$ per gli stati
- L'array $\Sigma=\{s_{1},s_{2},\dots,s_{n}\}$ per l'alfabeto
- per le quinutple vengono usati gli array 
	- $Q_{1}=\{q_{1_{1}},q_{2_{1}},\dots,q_{k_{1}}\}$ che descrive gli stati di partenza di ciascuna quinutpla
	- $S_{1}=\{s_{1_{1}},s_{2_{1}},\dots,s_{k_{1}}\}$ che descrive gli elementi di $\Sigma$ che devono essere *letti* per poter eseguire ciascuna quintupla
	- $S_{2}=\{s_{1_{2}},s_{2_{2}},\dots,s_{k_{2}}\}$ che descrive gli elementi di $\Sigma$ che devono essere *scritti* per poter eseguire ciascuna quintupla
	- $Q_{2}=\{q_{1_{2}},q_{2_{2}},\dots,q_{k_{2}}\}$ che descrive gli stati di arrivo di ciascuna quintupla
	- $M=\{m_{1},m_{2},\dots,m_{k}\}$ che descrive i movimenti della testina che avvengono quando è eseguita ciascuna quintupla
In questo modo la quintupla $\langle q_{j_{1}},s_{j_{1}},s_{j_{2}},q_{j_{2}},m_{j}\rangle$ è rappresentata da $Q_{1}[j],S_{1}[j],S_{2}[j],Q_{2}[j],M[j]$ 
- lo stato finale $q_0$ e i due stati finali $q_A$ e $q_R$ 
Nel programma $\mathscr P$ vengono anche utilizzate le seguenti variabili
- $q$, che descrive lo stato attuale della macchina
- $N$, un array che descrive il contenuto del nastro ad ogni istante della computazione; la dimensione di $N$, ad ogni istante, è pari alla porzione di nastro utilizzato dalla macchina $T$ 
- $t$ la posizione della testina sul nastro di $T$ 
- $\text{primaCella}$, che ad ogni istante memorizza l'indirizzo della cella del nastro più a sinistra utilizzata fino ad allora dalla computazione $T(x)$
- $\text{ultimaCella}$, che ad ogni istante memorizza l'indirizzo della cella del nastro più a destra utilizzata fino ad allora dalla computazione $T(x)$
- $i$ e $j$ variabili di iterazione
- $\text{trovata}$ è una variabile booleana utilizzata per verificare il ritrovamento della corretta quintupla da eseguire

![[FI/MOD II/img/img9.png|center|600]]

In figura viene descritto il programma $\mathscr P$ che simula la macchina di Turing universale.  
Esso consiste in un unico loop tra le linee 5-25 nel quale, fissato lo stato attuale $q$ e la posizione di $t$ sul nastro e quindi il simbolo letto $N[t]$, cerca la quintupla che inizia con la coppia $(q,N[t])$ e se esiste, la esegue. Le variabili $\text{primaCella}$ e $\text{ultimaCella}$ vengono utilizzate nel caso in cui il movimento della testina vada oltre alla lunghezza della stringa memorizzata in $N$, in quel caso ne aggiorna la lunghezza. 
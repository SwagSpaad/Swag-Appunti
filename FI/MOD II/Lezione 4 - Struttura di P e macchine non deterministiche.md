********
# Struttura di $P$
Fino ad ora, non abbiamo posto limitazioni sulla struttura dell'insieme delle produzioni $P$ che definisce una particolare macchina di Turing.
Prendiamo una macchina di Turing, ovvero una quintupla $T=\langle \Sigma, Q, P, Q_{F},m\rangle$ e osserviamo che per sapere tutto di $T$ basta avere l'insieme $P$ perché possiamo ricavare $\Sigma$ e $Q$. 
Ricordiamo che possiamo vedere $P$ come una funzione che associa ad una coppia $\text{(stato, simbolo)}$ una tripla $\text{(simbolo, stato, movimento)}$ ovvero $$P:Q\times\Sigma\to \Sigma\times Q\times\text{\{sinistra, destra, fermo\}}$$
## Totalità
Una quintupla non è altro che un istruzione al linguaggio associato alla macchina di Turing, ad esempio la quintupla $\langle q_{1},a,b,q_{2},m\rangle$ ci dice che *se la macchina $T$ si trova nello stato $q_{1}$ e leggiamo il carattere $a$ allora scriviamo $b$, passiamo nello stato $q_{2}$ ed eseguiamo il movimento $m$*. 
Ma cosa succede se, trovandoci in uno stato $q$ e leggendo un carattere $x$, non troviamo in $P$ alcuna quintupla i cui primi due simboli sono $q$ ed $s$? Non viene eseguita nessuna azione e quindi $T$ interrompe la sua computazione .

Per **totalità** dell'insieme delle quintuple si intende che *per ogni coppia $(q_{1},s_{1})\in(Q-Q_{F})\times \Sigma$ esiste una tripla  $(s_{2},q_{2},m)\in\Sigma\times Q\times\text{\{destra, sinistra, fermo\}}$ tale che $\langle q_{1},s_{1},s_{2},q_{2},m\rangle\in P$.*
Questa questione è connessa alla verifica delle *precondizioni*, ovvero, quando progettiamo una macchina di Turing assumiamo che **l'input venga scritto sul nastro in un certo formato**. 

>**Esempio**
>Nella progettazione della macchina ad un nastro che calcola la somma di due numeri abbiamo assunto che:
>- le cifre del primo numero fossero scritte in celle consecutive
>- la cifra più significativa a sinistra 
>- immediatamente seguite dal $+$ (senza $\square$ intermedi) e che il $+$ fosse immediatamente seguito dalle cifre del secondo numero rispettando le due condizioni sopra
>In questo caso la parola $1\square +1$ non è un input valido per la macchina, per questo non abbiamo previsto nessuna quintupla i cui primi due elementi fossero $(q_{0}, \square)$. 

Quindi la corrispondenza $P$ non può essere totale, ovvero, considerando $P$ come un insieme di quintuple, esso non può contenere le quintuple che iniziano con coppie di simboli $\text{(stato\_attuale, simbolo\_letto)}$ che si riferiscono a configurazioni del nastro che non rispettano le *precondizioni*. 

Andiamo a chiarire il significato che intendiamo quando ad una coppia $\text{(stato, simbolo)}$ non è associata nessuna quintupla in $P$, distinguendo tra *trasduttori e riconoscitori*.

### Trasduttori 
Sia $T_{t}$ una macchina di tipo *trasduttore* che ad un certo passo della computazione su input $x\in\Sigma^{*}$, si trovi nello stato $q$ e stia leggendo il carattere $s$. Nel caso in cui nell'insieme delle quintuple $P$ **non esista** alcuna quintupla che inizia con la coppia $(q,s)$, $T_t$ *non è in grado di produrre l'output corrispondente all'input $x$ scritto sul nastro*. Possiamo affermare quindi che la computazione $T_t(x)$ non produce alcun output. Possiamo considerare una nuova macchina $T_{t}'$ il cui insieme delle quintuple $P'$ sia l'unione dell'insieme delle quintuple $P$ e dell'insieme delle quintuple aggiuntive $$\{\langle q,(s,x),(s,x),q,\text{fermo}\rangle\:\:x\in\Sigma\}$$ il cui comportamento è sostanzialmente identico a quello di $T_t$, ovvero $T_{t}(x)=T_{t}'(x)$ per ogni $x\in\Sigma^{*}$ tale che $T_t(x)$ calcola un output e $T_t'(x)$ **non termina** per ogni $x\in\Sigma^{*}$ tale che $T_t(x)$ **non calcola un output** (osservare la differenza tra non termina e non calcola l'output).

### Riconoscitori
Consideriamo una macchina di Turing $T$ di tipo riconoscitore e supponiamo che essa si trovi nello stato $q$, che la testina legga il simbolo $s$ e che nell'insieme delle sue quintuple $P$ non esista nessuna quintupla che inizi con la coppia $(q,s)$; in questo caso la macchina $T$ non riuscirà a raggiungere uno stato di accettazione. Possiamo quindi aggiungere all'insieme $P$ la quintupla $$\langle q,s,s,q_R,\text{fermo}\rangle$$ senza alterare l'insieme delle parole accettate da $T$. 

## $P$ è una corrispondenza o una funzione?
Abbiamo detto che l'insieme delle quintuple $P$ è una *corrispondenza* fra $Q\times\Sigma$ e $\Sigma\times Q\times\text{\{destra, sinistra, fermo\}}$, ma negli esempi abbiamo visto che la corrispondenza si è rivelata invece una **funzione**, infatti $P$ non conteneva coppie di quintuple che avevano gli stessi due elementi iniziali. Questo corrisponde quindi ad una sequenza di istruzioni che **devono** essere eseguite ogni volta che si verifica una condizione, quindi la macchina non ha gradi di libertà, si dice che la macchina di Turing è **deterministica**.

È stato definito anche il modello di macchina di Turing *non deterministica*, ossia quella il cui insieme $P$ può contenere più quintuple che iniziano con la stessa coppia stato-carattere. Il *grado di non determinismo* è il numero massimo di quintuple che iniziano con la stessa coppia stato-carattere. 

>**Oss.**
>Il grado di non determinismo di una macchina di Turing è al più $3|Q|\cdot|\Sigma|$ 

Una macchina non deterministica ha quindi l'insieme delle quintuple che potrebbe avere una struttura simile $$\langle q,a,b_{1},q_{1},m_{1}\rangle\:\:\langle q,a,b_{2},q_{2},m_{2}\rangle\:\:\dots\:\:\langle q,a,b_{k},q_{k},m_{k}\rangle$$ e chiamiamo questa struttura una *multi-quintupla*.

Cosa accade quindi quando l'insieme di quintuple di una macchina di Turing $T$ ha la multi-quintupla presentata sopra e durante la sua computazione $T(x)$ si trova nello stato interno $q$ e legge il carattere $a$?

Una computazione non deterministica può essere vista come un albero sorgente (orientato dalla radice alle foglie) i cui nodi sono gli [[Lezione 2 - macchine di Turing#Stati globali|stati globali]] utilizzati dalla computazione. La radice dell'albero è lo stato globale iniziale della computazione e i figli di ciascun nodo interno sono gli stati globali che corrispondono alla esecuzione di una istruzione non deterministica. Quindi se $k$ è il grado di non determinismo di una macchina di Turing $NT$, ogni nodo dell'albero della computazione ha al più $k$ figli. 

Chiamiamo **computazione deterministica di $NT(x)$** ciascun percorso nell'albero uscente dalla radice e che termina solo quando incontra una foglia. 

Sia $NT$ una macchina di Turing non deterministica, siano $q_A$ e $q_R$ gli stati di accettazione e rigetto di $NT$ e sia $x$ l'input di $NT$, allora $$O_{NT}(x)=\begin{cases}
q_{A} &\begin{align*}
&\text{almeno una delle computazioni deterministica di}\\
&NT(x)\text{ termina in }q_{A}\\
\end{align*}\\\\
q_{R} &\text{tutte le computazioni deterministiche di NT(x) terminano in }q_{R}\\ \\
\text{non definito}&\text{altrimenti}
\end{cases}$$ ![[FI/MOD II/img/img6.png|center|500]]

>**Teorema 2.1**
>Per ogni macchina di Turing non deterministica $NT$ esiste una macchina di Turing deterministica $T$ tale che, per ogni input $x$ di $NT$, l'esito della computazione $NT(x)$ coincide con l'esito della computazione $T(x)$.

**Dim.**
È presentata una dimostrazione informale della dimostrazione. Viene applicata la tecnica della simulazione, ossia costruendo una macchina deterministica $T$ che simula il comportamento di una macchina non deterministica $NT$ che ha grado di non determinismo $k$. 
La macchina $T$ su input $x$ esegue una visita dell'albero corrispondente alla computazione $NT(x)$. La visita non può essere in profondità, perché non conosciamo la lunghezza delle computazioni e qualcuna di loro potrebbe non terminare.
Viene dimostrato utilizzando una particolare visita in ampiezza, basata sulla tecnica della *coda di rondine con ripetizioni*.
Partiamo dallo stato globale iniziale $SG(T,x,0)$ e simuliamo *tutte* le computazioni lunghe un passo (che sono al più $k$) e se non possiamo concludere nulla sull'esito della computazione $NT(x)$, allora torniamo a $SG(T,x,0)$ e simuliamo *tutte* le computazioni lunghe due passi (al più $k^2$) e così via finché non si trova una computazione deterministica accettante.


********
Abbiamo iniziato il corso cercando di capire come risolvere automaticamente i problemi e abbiamo studiato la soluzione proposta da Turing, che ha introdotto il modello di calcolo Macchina di Turing. 
Ora ci domandiamo: 
- utilizzando la macchina di Turing possiamo risolvere **tutti** i problemi, oppure esiste qualche problema non risolubile?
- se esiste qualche problema non risolubile con la Macchina di Turing, è possibile risolverlo con un altro modello di calcolo?
Rispondiamo prima alla seconda domanda
# Linguaggi e macchine di Turing
Una macchina di Turing di tipo riconoscitore calcola una funzione booleana, ovvero dato $x \in \Sigma^*$, verifica se $x$ soddisfa una proprietà $\pi(\cdot)$. Se indichiamo con $O_{T}(x)$ l'esito della computazione $T(x)$, ovvero lo stato raggiunto dalla computazione $T(x)$ nel caso essa termini, allora possiamo dire che $$\begin{align*}
&O_{T}(x)=q_{A}\iff\pi(x)\\\\
&\text{equiv.}\\
&\{x\in\Sigma^{*}:O_{T}(x)=q_{A}\}=\{x\in\Sigma^*:\pi(x)\}
\end{align*}$$ Quindi possiamo parlare dell'*insieme delle parole accettate da una mdT di tipo riconoscitore*.

>**Def 3.1**
>Un *linguaggio* $L$ è sottoinsieme $\Sigma^{*}:L\subseteq\Sigma^{*}$  

>**Def 3.2**
>Il *linguaggio complemento* $L^{C}$ di un linguaggio $L\subseteq\Sigma^*$ è l'insieme delle parole non contenute in $L:L^{C}=\Sigma^{*}-L$

>**Def 3.3 (Accettabilità)**
>Un linguaggio $L\subseteq\Sigma^{*}$ è *accettabile* se esiste una macchina di Turing $T$ tale che $$\forall x\in\Sigma^{*}[O_{T}(x)=q_{A}\iff x\in L]$$>>**Oss.**
>>La definizione di accettabilità non dice nulla riguardo l'esito della computazione $T(x)$ nel caso in cui $x\not\in L$. Se un linguaggio $L$ è accettato da una macchina $T$ e $x \not \in L$ potrebbe accadere che $O_{T}(x)=q_{R}$ oppure che $T(x)$ non raggiunga mai uno stato finale.
>>In poche parole *l'accettabilità di un linguaggio $L$ non dà indicazioni sull'accettabilità del linguaggio $L^{C}$* 

>**Def 3.4 (Decidibilità)**
>Un linguaggio $L\subseteq\Sigma^{*}$ è *decidibile* se esiste una macchina di Turing $T$ che *termina per ogni input $x\in\Sigma^{*}$* e tale che $$\forall x \in\Sigma^{*}[O_{T}(x)=q_A\iff x\in L]$$
>>**Oss.**
>>Se una macchina $T$ decide un linguaggio $L\in\Sigma^{*}$ allora $$O_{T}(x)\begin{cases}
q_{A} & \text{se } x\in L \\
q_{R} & \text{se } x\in L^{C}
\end{cases}$$

Quando un linguaggio $L$ è deciso da una macchina $T$, scriviamo $L=L(T)$

La differenza tra *decisione e accettazione* di un linguaggio è il comportamento della macchina sul *linguaggio complemento*.

**Teorema 3.1**
Un linguaggio $L\subseteq\Sigma^{*}$ è *decidibile* se e solo se $L$ e $L^{C}$ sono *accettabili*

**Dim.**
Se $L$ è decidibile allora esiste una macchina di Turing che, per ogni $x\in\Sigma^{*}$, con input $x$ termina nello stato $q_A$ se $x\in L$ e termina in $q_R$ se $x\in L^C$.
Deriviamo da $T$ una macchina $T'$. Gli stati di $T'$ sono gli stati di $T$ con l'aggiunta degli stati $q_{A}'$ e $q_{R}'$ rispettivamente stato di accettazione e rigetto della macchina $T'$. Le quintuple di $T'$ sono le stesse quintuple di $T$ con l'aggiunta delle due quintuple $$\langle q_{A},u,u,q_{R}',\text{ferma}\rangle \hspace{1cm} \langle q_{R},u,u,q_{A}',\text{ferma}\rangle \hspace{1cm} \forall u\in\Sigma\cup\{\square\}$$
Quindi per ogni $x\in\Sigma^{*}$, la computazione $T'(x)$ coincide con la computazione $T(x)$ tranne che per l'ultima istruzione eseguita da $T'(x)$: se $T(x)$ termina in $q_A$ allora $T'(x)$ esegue una ulteriore istruzione per entrare nello stato di rigetto, mentre se $T(x)$ termina in $q_R$ allora $T'(x)$ esegue un'ulteriore istruzione che porta nello stato di accettazione. 
Quindi $T'$ accetta $x\iff T$ rigetta $x$, ossia se e solo se $x\in L^{C}$ e quindi $T'$ accetta $L^{C}$.

Vediamo il viceversa. Se $L$ è accettabile e $L^C$ è accettabile, allora esistono due macchine di Turing $T_{1}$ e $T_{2}$ che per ogni $x\in\Sigma^{*}$, $T_{1}(x)$ accetta se e solo se $x\in L$ e $T_{2}(x)$ accetta se e solo se $x\in L^C$. Ricordiamo che l'esito della computazione non è specificato per la computazione $T_1(x)$ se $x\in L^C$ e per la computazione $T_{2}(x)$ se $x\in L$. 
Definiamo una macchina $T$ che, simulando le computazioni di $T_1$ e $T_2$ su input $x\in \Sigma^*$, decide $L$. 
>**Oss.**
>Se $T$ simulasse prima l'intera computazione di $T_1$ e poi quella di $T_2$ non ci sarebbe garanzia di terminazione, quindi la simulazione deve avvenire in modo diverso.

$T$ dispone di due nastri, su ciascuno dei quali viene scritto l'input $x$; la computazione $T(x)$ avviene alternando sui due nastri le singole istruzioni di $T_1$ e di $T_2$ nel seguente modo:
1. Esegui una *singola istruzione* di $T_1$ sul nastro 1: se $T_1$ entra in uno stato di accettazione allora $T$ **accetta**, altrimenti esegui il passo 2
2. Esegui una *singola istruzione* di $T_2$ sul nastro 2: se $T_2$ entra in uno stato di accettazione allora $T$ **rigetta**, altrimenti esegui il passo 1
Sia ora $x\in\Sigma^{*}$. Se $x\in L$, allora, prima o poi, al passo 1 $T_1$ entrerà nello stato di accettazione, portando $T$ ad accettare. Viceversa se $x\in L^C$ allora, prima o poi, al passo 2 $T_2$ entrerà nello stato di accettazione, portando $T$ a rigettare. Quindi $T$ **decide** $L$. $\square$

# Funzioni calcolabili 
Torniamo a considerare macchine di Turing di tipo trasduttore. Assumiamo che le macchine di tipo trasduttore a cui faremo riferimento siano dotate di un nastro di output, che al termine della computazione, contiene il valore di output.
 
Dato un alfabeto finito $\Sigma$, indichiamo con $\Sigma^*$ l'insieme delle *parole* su $\Sigma$, ovvero, l'insieme delle stringhe di lunghezza finita costituite da caratteri in $\Sigma$.

>**Def 3.5**
>Siano $\Sigma_{1}$ e $\Sigma_{2}$ due alfabeti finiti; una funzione $f:\Sigma_1^{*}\to\Sigma_{2}^*$ è una funzione *calcolabile* se esiste una macchina di Turing $T$ di tipo trasduttore che, dato in input $x\in\Sigma_{1}^*$ termina con la stringa $f(x)$ scritta sul nastro di output se e solo se $f(x)$ **è definita**.

Osserviamo che la definizione non dice nulla a riguardo delle computazioni $T(x)$ per i quali $f(x)$ non è definita. Il concetto di calcolabilità di una funzione è simile al concetto di accettabilità di un linguaggio, infatti quando scegliamo un input $x$ per il quale $f(x)$ è definita le cose "*vanno bene*", mentre non sappiamo cosa succede per i valori di $x$ per il quale $f(x)$ non è definita.

Sia $\Sigma$ un alfabeto finito e $L\subseteq\Sigma^*$ un linguaggio. La **funzione caratteristica** $\chi_L:\Sigma^*\to\{0,1\}$ di $L$ è una funzione totale tale che, per ogni $x\in\Sigma^*$ $$\chi_L(x)=\begin{cases}
1 & \text{se }x\in L \\
 \\
0  & \text{se }x\not\in L
\end{cases}$$
**Teorema 3.2**
Un linguaggio $L$ è decidibile se e soltanto se la funzione $\chi_L$ è calcolabile

**Dim.**
Supponiamo che $L\subseteq\Sigma^*$ sia decidibile, allora esiste una macchina di Turing di tipo riconoscitore $T$ con stato di accettazione $q_A$ e stato di rigetto $q_R$ tale che $$O_T(x)=\begin{cases}
q_A & \text{se }x\in L \\
 \\
q_R & \text{se }x\not\in L
 \end{cases}$$Supponiamo che $T$ utilizzi un solo nastro. A partire da $T$ definiamo una macchina di tipo trasduttore $T'$ a 2 nastri che, con input $x\in\Sigma^{*}$, opera nel seguente modo:
 1. sul primo nastro, dove è scritto l'input $x$, si esegue la computazione $T(x)$
 2. se $T(x)$ termina in $q_A$ allora scrive sul nastro di output il valore $1$, altrimenti scrive $0$. Successivamente termina.
>**Oss.**
>Poiché $L$ è decidibile, il passo 1. termina per ogni input $x$. Se $x\in L$, allora $T(x)$ termina nello stato di accettazione e quindi al passo 2. $T'(x)$ scrive $1$ sul nastro di output. Se $x\not\in L$ allora $T(x)$ non termina in accettazione e al passo 2. $T'(x)$ scrive $0$ in output. Questo dimostra che $\chi_L$ è calcolabile.

Dimostriamo l'inverso. Supponiamo quindi che $\chi_L$ sia calcolabile e osserviamo che $\chi_L$ è una *funzione totale*. Allora esiste una macchina di Turing $T$ di tipo trasduttore che, per ogni $x\in\Sigma^*$, calcola $\chi_L(x)$. A partire da $T$, definiamo una macchina di Turing $T'$ di tipo riconoscitore a due nastri che, con input $x\in\Sigma^*$ opera nel seguente modo: 
1. sul primo nastro, in cui è scritto l'input $x$, si esegue la computazione $T(x)$ scrivendo il risultato sul secondo nastro
2. se sul secondo nastro è stato scritto $1$ allora la computazione $T'(x)$ termina in accettazione, altrimenti rigetta
>**Oss.**
>Poiché $\chi_L$ è totale, il passo 1. termina per ogni input $x$. Se $\chi_L=1$, allora il passo 1. termina scrivendo $1$ sul secondo nastro e quindi al passo 2. $T'(x)$ termina in accettazione. Se $\chi_L=0$ allora il passo 1. termina scrivendo $0$ sul secondo nastro e $T'(x)$ termina nello stato di rigetto. Questo dimostra che $L$ è decidibile. $\square$

Questo teorema mostra come ad ogni linguaggio decidibile possa essere associata una funzione calcolabile, che è la funzione caratteristica del linguaggio. 
Ci domandiamo se è possibile l'inverso, ovvero, data una qualsiasi funzione $f$, possiamo associargli un linguaggio che sia decidibile se e solo se $f$ è calcolabile? 
Iniziamo associando ad ogni funzione $f:\Sigma_{1}^{*}\to\Sigma_{2}^*$ il linguaggio $$L_{f}=\{\langle x,y\rangle:x\in\Sigma_{1}^{*}\land y\in\Sigma_{2}^{*}\land y=f(x)\}$$ Osserviamo che c'è una differenza tra la decidibilità di un linguaggio e la calcolabilità di una funzione. Mentre un linguaggio è decidibile se esiste un algoritmo che può determinare se una stringa appartiene ad esso, la calcolabilità di una funzione implica che esiste un algoritmo che può calcolare l'output della funzione per ogni possibile input. Tuttavia, il comportamento di tale algoritmo non è definito se l'input non appartiene al dominio della funzione. Per garantire che il linguaggio $L_f$​ sia decidibile, è necessario che la funzione $f$ sia totale, cioè restituisca un output per ogni possibile input. 

**Teorema 3.3**
Se $f:\Sigma_{1}^*\to\Sigma_{2}^*$ è calcolabile **e totale** allora $L_f$ è decidibile

**Dim.**
Poiché $f$ è calcolabile e totale, allora esiste una mdT $T$ di tipo trasduttore che, per ogni $x\in\Sigma^*$, calcola $f(x)$. A partire da $T$ definiamo una mdT di tipo riconoscitore $T'$ a due nastri che, con input $\langle x,y\rangle \::x\in\Sigma_{1}^{*}\land y\in\Sigma_{2}^{*}$ opera nel seguente modo: 
1. sul primo nastro viene scritto l'input $\langle x,y\rangle$ 
2. sul secondo nastro si esegue la computazione $T(x)$ scrivendo il risultato $z$ 
3. si esegue un confronto tra $z$ ed $y$: se $z=y$ allora la computazione $T'(x)$ termina in accettazione, altrimenti rigetta.
>**Oss.**
>Poiché $f$ è totale, il passo 2. termina per ogni input $x$. Se $f(x)=y$ allora il passo 2. termina scrivendo $y$ sul secondo nastro e al passo 3. $T'(x)$ termina in accettazione. Se invece $f(x)=z\ne y$, allora il passo 2. termina scrivendo $z$ sul secondo nastro e, quindi, al passo 3. $T'(x)$ termina nello stato di rigetto. Questo dimostra la decidibilità di $L_{f}$. $\square$

**Teorema 3.4**
Sia $f:\Sigma_{1}^*\to\Sigma_{2}^*$ una funzione. Se $L_f$ è decidibile allora $f$ è calcolabile

**Dim.**
Poiché $L_f\subseteq\Sigma_{1}^{*}\times\Sigma_{2}^{*}$ è decidibile, esiste una mdT $T$ di tipo riconoscitore, con stato di accettazione $q_A$ e stato di rigetto $q_R$, tale che, per ogni $x\in\Sigma_{1}^{*}$ e per ogni $y\in\Sigma_{2}^{*}$ $$O_T(x,y)=\begin{cases}
q_A & \text{se } y=f(x) \\
 \\
q_R & \text{altrimenti}
\end{cases}$$ Supponiamo che $T$ utilizzi un solo nastro e a partire da $T$ definiamo una macchina di tipo trasduttore $T'$ a 4 nastri, che con input $x\in\Sigma_{1}^{*}$ sul primo nastro, opera nel seguente modo: 
1. scrive il valore $i=0$ sul primo nastro 
2. enumera tutte le stringhe $y\in\Sigma_{2}^{*}$ la cui lunghezza è pari al valore scritto sul primo nastro, simulando per ciascuna di esse la computazione $T(x,y)$, ovvero: 
	-  sia $y$ la prima stringa di lunghezza $i$ non ancora enumerata, allora scrive $y$ sul secondo nastro
	- sul terzo nastro viene eseguita la computazione $T(x,y)$ 
	- se $T(x,y)$ termina in $q_A$ allora scrive sul nastro di output la stringa $y$ e termina, altrimenti, incrementando il valore di $i$, se $y$ era l'ultima stringa di lunghezza $i$, torna al passo 2. 
>**Oss.**
>Poiché $L_{f}$ è decidibile, il passo 2.2 termina per ogni input $\langle x,y\rangle$. Se $x$ appartiene al dominio di $f$, allora esiste $\overline y\in\Sigma_{1}^{*}$ tale che $\overline y=f(x)$ e quindi $\langle x,\overline y\rangle\in L_f$. In questo caso, prima o poi, la stringa $\overline y$ verrà scritta sul secondo nastro e la computazione $T(x,\overline y)$ terminerà in stato di accettazione e al passo 2.3 $T'(x)$ scriverà $\overline y$ sul nastro di output e terminerà. Questo dimostra che $f$ è calcolabile. $\square$


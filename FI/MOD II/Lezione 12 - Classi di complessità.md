*******
In questa lezione iniziamo il processo di classificazione dei linguaggi in base alle risorse tempo e spazio sufficienti alla loro decisione/accettazione. 
Vediamo intanto due teoremi, che mostrano che il numero di istruzioni e di celle che occorrono per *decidire deterministicamente* un linguaggio possono essere individuati solo *a meno di costanti moltiplicative*.

**Teorema 6.6** (Compressione lineare)
Sia $L\subseteq \Sigma^{*}$ un linguaggio deciso da una macchina di Turing deterministica ad un nastro $T$ tale che, per ogni $x\in\Sigma^{*}$, $\text{dspace}(T,x)=s(|x|)$ e sia $k>0$ una costante. Allora esiste una macchina di Turing ad un nastro $T^{'}$ che decide $L$ e per ogni $x\in\Sigma^{*}$ $\text{dspace}(T^{'},x)\le\frac{s(|x|)}{c}+O(|x|)$.  

Questo teorema ci dice che, dato un qualunque algoritmo, esiste sempre un algoritmo che è una frazione costante della memoria del primo. La presenza dell'addendo $O(|x|)$ deriva dal fatto che l'input di $T^{'}$ è lo stesso di $T$, quindi $T^{'}$ deve per prima cosa codificare in forma compressa il proprio input e poi lavorare sull'alfabeto compresso.

**Oss.**
L'alfabeto compresso è $\Sigma^{k}$ (ovvero un carattere dell'alfabeto compresso è una parola di $k$ caratteri di $\Sigma$) e l'alfabeto di $T^{'}$ è $\Sigma^{k}\cup\Sigma$.

**Teorema 6.7** (Accelerazione lineare)
Sia $L\subseteq \Sigma^{*}$ un linguaggio deciso da una macchina di Turing deterministica ad un nastro $T$ tale che, per ogni $x\in\Sigma^{*}$, $\text{dtime}(T,x)=t(|x|)$ e sia $k>0$ una costante. Allora:
- esiste una macchina di Turing *ad un nastro* $T^{'}$ che decide $L$ e, per ogni $x\in \Sigma^{*}$ $\text{dtime}(T^{'},x)\le \frac{t(|x|)}{k}+O(|x|^{2})$
- esiste una macchina di Turing *a due nastri* $T^{''}$ che decide $L$ e, per ogni $x\in \Sigma^{*}$ $\text{dtime}(T^{''},x)\le \frac{t(|x|)}{k}+O(|x|)$

Questo teorema ci dice che, dato un qualunque algoritmo, esiste sempre un algoritmo più veloce del primo di un fattore costante. 
La presenza degli addendi $O(|x|^{2})$ e $O(|x|)$ deriva dal fatto che, per poter essere più veloci, le macchine $T^{'}$ e $T^{''}$ devono innanzitutto codificare in forma compressa il proprio input: se la codifica compressa viene scritta su un nastro apposito (come fa $T^{''}$ sul secondo nastro) sono sufficienti $O(|x|)$ passi, se invece si dispone di un solo nastro occorrono $O(|x|^{2})$ passi.

# Classi di complessità
Possiamo raggruppare i linguaggi in base all'efficienza delle macchine che li decidono, ad esempio potremmo considerare l'insieme dei linguaggi tali che, **la miglior macchina che li decide**, ha una certa efficienza. 
Vediamo cosa vuol dire. Un linguaggio $L$ è un insieme di parole, ed una macchina che decide $L$ esegue un numero diverso di operazioni quando opera su input diversi. Pensiamo ad esempio alla macchina $T_{PPAL}$ con due input diversi. La computazione $T_{PPAL}(abababab)$ rigetta dopo aver eseguito $10$ quintuple, mentre la computazione $T_{PPAL}(abbbbbbba)$ accetta dopo aver eseguito circa $45$ quintuple. 
Ma cosa significa che una macchina che decide un linguaggio ha una certa efficienza? La risposta è che vogliamo che la macchina che decide un linguaggio $L\subseteq \Sigma^{*}$ si comporti "bene" su ogni parola $x\in\Sigma^{*}$. Osserviamo che non possiamo scegliere la migliore macchina che decide un linguaggio, perché se il linguaggio fosse deciso da una macchina con una certa efficienza, lo stesso linguaggio è deciso da una macchina efficiente il doppio, il triplo, il quadruplo e così via.

Per risolvere questa questione ricorriamo alla notazione $O(f(x))$: diciamo che *un linguaggio $L$ appartiene all'insieme caratterizzato dall'efficienza temporale individuata dalla funzione totale e calcolabile $f$ se* **esiste** *una macchina $T$ che decide (o accetta) $L$ e che, per ogni parola $x$ sull'alfabeto di $L$, termina in $O(f(|x|))$ istruzioni.

Le classi che misurano "efficienza temporale" nel caso deterministico si chiamano $\text{DTIME}$
Data una funzione totale e calcolabile $f$ $$\begin{align*}
\text{DTIME}[f(n)]=\{L\subseteq\{0,1\}^{*} &\text{ tali che esiste una macchina deterministica T che decide L e,}\\
&\text{ per ogni }x\in\{0,1\}^{*},\text{dtime}(T,x)\in O(f(|x|)) \}
\end{align*}$$
Le classi che misurano "efficienza spaziale" nel caso deterministico si chiamano $\text{DSPACE}$
Data una funzione totale e calcolabile $f$ $$\begin{align*}
\text{DSPACE}[f(n)]=\{L\subseteq\{0,1\}^{*} &\text{ tali che esiste una macchina deterministica T che decide L}\\
&\text{ e per ogni }x\in\{0,1\}^{*},\text{dspace}(T,x)\in O(f(|x|)) \}
\end{align*}$$
Possiamo anche definire le classi di complessità nel caso non deterministico. 

Le classi che misurano "efficienza temporale" nel caso non deterministico si chiamano $\text{NTIME}$.
Data una funzione totale e calcolabile $f$ $$\begin{align*}
\text{NTIME}[f(n)]=\{L\subseteq\{0,1\}^{*} &\text{ tali che esiste una macchina non deterministica NT}\\
&\text{che accetta L e per ogni }x\in L,\text{ntime}(NT,x)\in O(f(|x|)) \}
\end{align*}$$
**Oss.**
Perché una classe non deterministica è definita in base al tempo di accettazione, invece che di decisione? Se sappiamo che un linguaggio è accettato in un certo numero di istruzioni, sappiamo che quel linguaggio è decidibile, ma non sappiamo quanto tempo occorre a rigettare le parole del suo complemento, ma quello che interessa a noi è accettare le parole del linguaggio. 

Le classi che misurano "efficienza spaziale" nel caso non deterministico si chiamano $\text{NSPACE}$.
Data una funzione totale e calcolabile $f$ $$\begin{align*}
\text{NSPACE}[f(n)]=\{L\subseteq\{0,1\}^{*} &\text{ tali che esiste una macchina non deterministica NT}\\
&\text{che accetta L e per ogni }x\in L,\text{nspace}(NT,x)\in O(f(|x|)) \}
\end{align*}$$
## Classi complemento
Sia $f$ una funzione totale e calcolabile.
La classe $\text{coDTIME}[f(n)]$ contiene i linguaggi il cui complemento è contenuto in $\text{DTIME}[f(n)]$ $$\text{coDTIME}[f(n)]=\{L\subseteq\{0,1\}^{*}\text{ tali che }L^{c}\in\text{DTIME}[f(n)]\}$$
La classe $\text{coDSPACE}[f(n)]$ contiene i linguaggi il cui complemento è contenuto in $\text{DSPACE}[f(n)]$ $$\text{coDSPACE}[f(n)]=\{L\subseteq\{0,1\}^{*}\text{ tali che }L^{c}\in\text{DSPACE}[f(n)]\}$$
La classe $\text{coNTIME}[f(n)]$ contiene i linguaggi il cui complemento è contenuto in $\text{NTIME}[f(n)]$ $$\text{coNTIME}[f(n)]=\{L\subseteq\{0,1\}^{*}\text{ tali che }L^{c}\in\text{NTIME}[f(n)]\}$$
La classe $\text{coNSPACE}[f(n)]$ contiene i linguaggi il cui complemento è contenuto in $\text{NSPACE}[f(n)]$ $$\text{coNSPACE}[f(n)]=\{L\subseteq\{0,1\}^{*}\text{ tali che }L^{c}\in\text{NSPACE}[f(n)]\}$$
**Oss.**
Consideriamo i linguaggi definiti sull'alfabeto $\{0,1\}$ per comodità, ma potremmo utilizzare un alfabeto qualsiasi, infatti se un linguaggio è deciso da una macchian definita su un alfabeto qualsiasi, allora esiste anche una macchina definita su $\{0,1\}$ che lo decide.

Alla funzione $f$ che definisce una classe di complessità (ad esempio $\text{coDTIME}[f(n)]$), diamo il nome di **funzione limite**.

## Relazioni fra le classi di complessità
**Teorema 6.8**
Per ogni funzione calcolabile e totale $f:\mathbb{N}\to\mathbb{N}$ $$\text{DTIME}[f(n)]\subseteq\text{NTIME}[f(n)]\:e\:\text{DSPACE}[f(n)]\subseteq\text{NSPACE}[f(n)]$$
**Dim.**
Basta osservare che una macchina di Turing deterministica è una particolare macchina di Turing non deterministica con grado di non determinismo pari ad $1$ e che ogni parola decisa in $k$ passi è anche accettata in $k$ passi. $\square$

**Teorema 6.9**
Per ogni funzione calcolabile e totale $f:\mathbb{N}\to\mathbb{N}$ $$\text{DTIME}[f(n)]\subseteq\text{DSPACE}[f(n)]\:e\:\text{NTIME}[f(n)]\subseteq\text{NSPACE}[f(n)]$$

**Dim.** (analogo il caso non deterministco)
Segue dal [[Lezione 11 - Misure di complessità#^e94afe|Teorema 6.1]]. Sia $L\subseteq\{0,1\}^{*}$ tale che $L\in\text{DTIME}[f(n)]$: allora esiste una macchina di Turing deterministica $T$ che decide $L$ e tale che, per ogni $x\in\{0,1\}^{*}$. $\text{dtime}(T,x)\in O(f(|x|))$.  Poiché $\text{dspace}(T,x)\le\text{dtime}(T,x)$, allora $\text{dspace}(T,x)\le\text{dtime}(T,x)\in O(f(|x|))$. Questo implica che $\text{dspace}(T,x)\in O(f(|x|))$ e dunque $L\in\text{DSPACE}[f(n)]$. $\square$

**Teorema 6.10**
Per ogni funzione calcolabile e totale $f:\mathbb{N}\to\mathbb{N}$ $$\text{DSPACE}[f(n)]\subseteq\text{DTIME}[2^{O(1)f(n)}]\:\:e\:\:\text{NSPACE}[f(n)]\subseteq\text{NTIME}[2^{O(1)f(n)}]$$
**Dim.**
Segue dal [[Lezione 11 - Misure di complessità#^e94afe|Teorema 6.1]]. Sia $L\subseteq\{0,1\}^{*}$ tale che $L\in\text{DSPACE}[f(n)]$: allora esiste una macchina di Turing deterministica $T$ che decide $L$ e tale che, per ogni $x\in\{0,1\}^{*}$, $\text{dspace}(T,x)\in O(f(|x|))$. 
Poiché $$\begin{align*}
\text{dtime}(T,x)&\le\text{dspace}(T,x)|Q|(|\Sigma|+1)^{\text{dspace}(T,x)}=\text{dspace}(T,x)|Q|3^\text{dspace(T,x)}=\\\\
&=2^{\log(\text{dspace}(T,x))}|Q|\bigg[2^{\log(3)}\bigg]^{\text{dspace(T,x)}}\\\\
&=|Q|2^{\log(\text{dspace}(T,x))+\text{dspace}(T,x)\log(3)}\le|Q|2^{[1+\log(3)]\text{ dspace}(T,x)}
\end{align*}$$ allora $\text{dtime}(T,x)\in O(2^{O(1)f(|x|)})$ e dunque $L\in\text{DTIME}[2^{O(1)f(n)}]$. $\square$

**Teorema 6.11**
Per ogni funzione calcolabile e totale $f:\mathbb{N}\to\mathbb{N}$ $$\text{DTIME}[f(n)]=\text{coDTIME}[f(n)]\:\:e\:\:\text{DSPACE}[f(n)]=\text{coDTIME}[f(n)]$$ 
**Dim.**
Sia $L\subseteq\{0,1\}^{*}$ tale che $L\in\text{DTIME}[f(n)]$: allora, esiste una macchina di Turing deterministica $T$ che decide $L$ e tale che, per ogni $x\in\{0,1\}^{*}$, $\text{dtime}(T,x)\in O(f(|x|))$. 
Poiché $T$ decide $L$, allora $T(x)=q_{A}$ se $x\in L$ e $T(x)=q_{R}$ se $x\in\{0,1\}^{*}-L=L^{C}$.
Costruiamo una macchina $T^{'}$, identica a $T$, ma con gli stati di accettazione e rigetto invertiti. Allora e $T^{'}(x)=q_{R}$ se $x\in L$ e $T(x)=q_{A}$ se $x\in\{0,1\}^{*}-L=L^{C}$. Quindi $T^{'}$ decide $L^{C}$ e, per ogni $x\in\{0,1\}^{*}$, $\text{dtime}(T,x)\in O(f(|x|))$, quindi $L^{C}\in\text{DTIME}[f(n)]$. 
Poiché $L$ è un qualunque linguaggio in $\text{DTIME}[f(n)]$ e, quindi, $L^{C}$ è un qualunque linguaggio in $\text{coDTIME}[f(n)]$, questo significa che:
- per ogni linguaggio $L^{C}\in\text{coDTIME}[f(n)]$, $L^{C}\in\text{DTIME}[f(n)]$ - ovvero $\text{coDTIME}[f(n)]\subseteq\text{DTIME}[f(n)]$ 
- per ogni linguaggio $L\in\text{DTIME}[f(n)]$, poiché $L^{C}\in\text{coDTIME}[f(n)]$, allora $L\in\text{coDTIME}[f(n)]$, ossia $\text{DTIME}[f(n)]\subseteq\text{coDTIME}[f(n)]$. 
Questi ultimi due punti dimostrano che $\text{DTIME}[f(n)]=\text{coDTIME}[f(n)]$. $\square$

**Teorema 6.12** 
Per ogni coppia di funzioni $f:\mathbb{N}\to\mathbb{N}$ e $g:\mathbb{N}\to\mathbb{N}$ tali che $\exists n_{0}\in\mathbb{N}\::\:\forall n\ge n_{0}$ allora $f(n)\le g(n)$ - ossia $f(n)\le g(n)$ *definitivamente* $$\begin{align*}
\text{DTIME}[f(n)]&\subseteq \text{DTIME}[g(n)]\\
\text{NTIME}[f(n)]&\subseteq \text{NTIME}[g(n)]\\
\text{DSPACE}[f(n)] &\subseteq  \text{DSPACE}[g(n)]\\
\text{NSPACE}[f(n)] &\subseteq  \text{NSPACE}[g(n)]
\end{align*}$$
**Dim.**
Sia $L\subseteq \{0,1\}^{*}$ tale che $L\in\text{DTIME}[f(n)]$: allora esiste una macchina di Turing deterministica $T$ che decide $L$ e tale che $\text{dtime}(T,x)\in O(f(|x|))\subseteq O(g(|x|))$. Questo significa che $L\in\text{DTIME}[g(n)]$. $\square$

Questo teorema ci dice che, se collochiamo un linguaggio $L$ in una classe di complessità $\text{DTIME}[f(n)]$, allora $L$ appartiene anche a tutte le classi $\text{DTIME}[g(n)]$ tali che $f(n)\le g(n)$ definitivamente.

**Teorema 6.13** (Gap Theorem)
Esiste una funzione totale e calcolabile $f:\mathbb{N}\to\mathbb{N}$ tale che $$\text{DTIME}[2^{f(n)}]\subseteq\text{DTIME}[f(n)]$$

Dagli ultimi due teoremi visti, osserviamo che, se collochiamo un linguaggio $L$ in $\text{DTIME}[f(n)]$, allora $L$ appartiene a **tutte** le classi $\text{DTIME}[f(n)^{k}]$ per ogni $k\in\mathbb{N}$, per il teorema 6.12 (infatti $f(n)\le f(n)^{k}$ definitivamente). Quindi abbiamo una gerarchia infinita di classi di complessità $$\text{DTIME}[f(n)]\subseteq\text{DTIME}[f(n)^{2}]\subseteq\dots\text{DTIME}[f(n)^{k}]$$ e sempre per il teorema 6.12, data una funzione $f$ totale e calcolabile ed una funzione $g$ tale che $f(n)\le g(n)$ definitivamente, allora $$\text{DTIME}[f(n)]\subseteq\text{DTIME}[g(n)]$$
D'altra parte, nella definizione di una teoria della complessità in grado di classificare i linguaggi in classi di complessità crescente, sarebbe preferibile che $\text{DTIME}[f(n)]$ **NON fosse contenuto in** $\text{DTIME}[g(n)]$ **quando $f(n)$ è molto più grande di $g(n)$**, ad esempio quando $f(n)=2^{g(n)}$, ma abbiamo visto che questo è contraddetto dal **Gap Theorem**.

# Funzioni time/space-constructible
In questo paragrafo sono introdotte delle funzioni totali e calcolabili che, per essere calcolate, utilizzano quantità di risorse (tempo di calcolo o spazio di memoria) proporzionali al loro valore. 

**Def 6.1** 
Una funzione totale e calcolabile $f:\mathbb{N}\to\mathbb{N}$ è *time-constructibl*e se esiste una macchina di Turing $T$ di tipo trasduttore che, preso in input un intero $n$ espresso in unario, scrive sul nastro di output il valore $f(n)$ in unario e $\text{dtime}(T,n)\in O(f(n))$.

**Def. 6.2**
Una funzione totale e calcolabile $f:\mathbb{N}\to\mathbb{N}$ è *space-constructible* se esiste una macchina di Turing $T$ di tipo trasduttore che, preso in input un intero $n$ espresso in unario, scrive sul nastro di output il valore $f(n)$ in unario e $\text{dspace}(T,n)\in O(f(n))$.

Queste funzioni possono essere calcolate in tempo e spazio proporzionale al suo valore.
Sono time/space-constructible tutte le funzioni "regolari", come ad esempio le funzioni polinomiali e le funzioni esponenziali del tipo $2^{f(n)}$ dove $f(n)$ è una funzione time/space-constructible.

La funzione $f(n)$ che si trova nella dimostrazione del **gap theorem** è totale e calcolabile, ma non time-constructible. 

È possibile mostrare che quando una funzione time-constructible $f$ cresce molto più velocemente di una funzione $g$, la classe $\text{DTIME}[g(n)]$ è contenuta strettamente nella classe $\text{DTIME}[f(n)]$. 
In effetti sussistono i seguenti teoremi di gerarchia: 

**Teorema 6.14** Teorema di gerarchia spaziale
Siano $f:\mathbb{N}\to\mathbb{N}$ e $g:\mathbb{N}\to\mathbb{N}$ due funzioni tali che $f$ è space-constructible e $$\lim_{n\to\infty} \frac{g(n)}{f(n)}=0$$Allora, $\text{DSPACE}[g(n)]\subset\text{DSPACE}[f(n)]$, ossia, esiste un linguaggio $L$ tale che $L\in\text{DSPACE}[f(n)]$ e $L\not\in\text{DSPACE}[g(n)]$.

**Teorema 6.15** Teorema di gerarchia temporale
Siano $f:\mathbb{N}\to\mathbb{N}$ e $g:\mathbb{N}\to\mathbb{N}$ due funzioni tali che $f$ è space-constructible e $$\lim_{n\to\infty} \frac{g(n)\log(g(n))}{f(n)}=0$$Allora, $\text{DTIME}[g(n)]\subset\text{DTIME}[f(n)]$, ossia, esiste un linguaggio $L$ tale che $L\in\text{DTIME}[f(n)]$ e $L\not\in\text{DTIME}[g(n)]$. ^598cac

Quindi, il teorema di gerarchia temporale ci dice che quando f è time-constructible
$\text{DTIME}[f(n)]$ **non è contenuto** in $\text{DTIME}[g(n)]$ quando $f(n)$ è molto più grande di $g(n)$ - ad esempio, quando $f(n) = 2^{g(n)}$. 

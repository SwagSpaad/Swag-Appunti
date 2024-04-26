xex*****
# Introduzione alla complessità
Abbiamo in precedenza studiato cosa si intende per problema risolvibile, decidibile, accettabile e abbiamo visto che esistono problemi non risolvibili. Ma siamo davvero sicuri che possiamo risolvere i problemi risolvibili?

Facciamo un breve esempio con le torri di Hanoi: la leggenda narra che 64 dischi di dimensione diversa siano stati impilati a forma di torre in un grande piatto di ottone con tre aste. Quando i monaci termineranno il loro lavoro di spostamento della torre dall'asta di sinistra a quella di destra, il mondo finirà. 

Consideriamo il problema con 3 dischi 

![[Pasted image 20240417112536.png|center|500]]

Vediamo che il numero minimo di spostamenti per risolvere il problema è 7, e meglio di così non si può fare. 
Per una torre di 4 dischi è necessario: 
- spostare la sotto-torre di 3 dischi dall'asta di sinistra a quella centrale
- spostare il disco più grande sull'asta di destra
- spostare la sotto-torre dall'asta centrale a quella di destra
Generalizzando, per una torre di $n$ dischi:
- spostare la sotto-torre di $n-1$ dischi da sinistra al centro
- spostare il disco più grande sull'asta di destra
- spostare la sotto-torre di $n-1$ dischi dal centro a destra
Se indichiamo con $M(n)$ il numero di spostamenti singoli per spostare una torre di $n$ dischi, vale la seguente ricorrenza $$M(n)=2M(n-1)+1$$che ha come soluzione $$M(n)=2^{n}-1$$e non possiamo fare meglio!
Quindi per spostare la torre di Hanoi di 64 dischi occorrono $$M(64)=2^{64}-1=18.446.744.073.709.551.615 \text{ spostamenti}$$ e se ogni spostamento impiegasse un secondo per essere svolto, occorrerebbero 18.446.744.073.709.551.615 secondi, ovvero **5.845.580.504** di secoli per completare il problema!

Abbiamo mostrato quindi che il problema delle torri di Hanoi è un problema risolvibile, ma il tempo per risolverlo non è accettabile. Quindi, *se il tempo necessario a calcolare l'istanza di un problema è elevato, allora saper calcolare quella soluzione è equivalente a non saperla calcolare*.

Con la **complessità** ci interessiamo quindi alla *quantità di risorse* di cui una macchina di Turing ha bisogno per accettare o rigettare un input, considerando come risorse il numero di istruzioni e celle che ci occorrono per portare a termine la computazione.

# Misure di complessità 
Una *misura di complessità* è una funzione che associa ad ogni macchina di Turing e ad ogni suo possibile input, un valore numerico che corrisponde al "costo" della computazione della macchina sull'input considerato. Ad esempio, le misure di complessità $dtime$ e $ntime$, contano il numero di passi di una computazione eseguita, rispettivamente, da una macchina di Turing deterministica e non deterministica. 

Affinché una funzione $f$ possa essere considerata una misura di complessità, essa deve soddisfare le due proprietà, note come **assiomi di Blum**:
1. la funzione $f$ *è definita solo per computazioni che terminano* (se $T(x)$ non termina non ha senso considerare che quella computazione abbia come costo un valore finito)
2. la funzione $f$ *deve essere calcolabile*, ossia deve esistere una macchina di Turing $\mathscr{M}$ che, ricevendo in input una macchina di Turing $T$ ed un suo input $x$, calcola $f(T,x)$ ogni qualvolta che $f(T,x)$ è definita (ovvero quando $T(x)$ termina). *Questo significa che, il costo di una computazione $T(x)$ che termina, dobbiamo poterlo calcolare effettivamente*.

## $\text{dtime}$ e $\text{dspace}$
Iniziamo con le misure di complessità relative a macchine di Turing deterministiche. Per ogni macchina di Turing $T$ deterministica (riconoscitore o trasduttore), definita su $\Sigma$ e per ogni $x\in\Sigma^{*}$ tali che $T(x)$ termina, definiamo $$\begin{align*}
&\text{dtime}(T,x)=\begin{cases}
\text{numero di istruzioni eseguite da }T(x)\text{ se termina} \\
 \\
\text{non definita se }T(x)\text{ non termina}
\end{cases}\\
\\
&\text{dspace}(T,x)=\begin{cases}
\text{numero di celle di memoria utilizzate da }T(x)\text{ se termina}\\
\\
\text{non definita se }T(x)\text{ non termina}
\end{cases}
\end{align*}$$ Dimostriamo che queste due funzioni soddisfano gli **assiomi di Blum**:
1. Per definizione, le funzioni sono definite solo se la computazione $T(x)$ termina
2. Dobbiamo mostrare che $\text{dtime}$ e $\text{dspace}$ sono calcolabili. Iniziamo da $\text{dtime}$. Consideriamo una modifica $U_\text{dtime}$ della macchina di Turing universale $U$. Aggiungiamo ad $U$ un quinto nastro $N_{5}$ che servirà da contatore del numero di istruzioni della computazione $T(x)$. $U_\text{dtime}(T,x)$ si comporta come $U(T,x)$ con l'unica differenza che, dopo aver eseguito una quintupla della macchina $T$ su input $x$, scrive $1$ sul nastro $N_{5}$ e muove a destra la testina su $N_{5}$. Al termine della computazione $U_\text{dtime}(T,x)$ (**se termina**) il nastro $N_{5}$ conterrà, in unario, il numero di passi eseguiti dalla computazione $T(x)$. Questo dimostra che **dtime** è calcolabile. La dimostrazione è simile per **ntime**.

## $\text{ntime}$ e $\text{nspace}$
Come per [[#$ text{dtime}$ e $ text{dspace}$|dtime ed dspace]], possiamo definire le funzioni $\text{ntime}$ e $\text{nspace}$ che si riferiscono a computazioni non deterministiche. Per ogni macchina di Turing non deterministica (riconoscitore) $NT$, definita su $\Sigma$ e per ogni $x\in\Sigma^{*}$ tali che $NT(x)$ *termina in accettazione*, definiamo $$\begin{align*}
\text{ntime}(NT,x)= &\text{ minimo numero di istruzioni eseguite da una computazione}\\
&\text{ deterministica accettante di }NT(x)\\
\\
\text{nspace}(NT,x)= &\text{ minimo numero di celle utilizzate da una computazione}\\
&\text{ deterministica accettante di }NT(x)
\end{align*}$$
**Oss.**
$\text{ntime}$ e $\text{nspace}$ sono due funzioni definita solo per **computazioni che accettano**. Per poter estendere la definizione alle computazioni che rigettano, bisogna ricordarsi che affinché una computazione $NT(x)$ termini in uno stato di accettazione è sufficiente che **una** delle sue computazioni deterministiche accetti, mentre per terminare in rigetto è necessario che **tutte** le sue computazioni deterministiche rigettino. 

Per una macchina di Turing non deterministica (riconoscitore) $NT$, definita su $\Sigma$ e per ogni $x\in\Sigma^{*}$ tali che $NT(x)$ *termina in rigetto*, le funzioni $\text{ntime}$ ed $\text{nspace}$ sono definite nel seguente modo $$\begin{align*}
\text{ntime}(NT,x)= &\textbf{ massimo}\text{ numero di istruzioni eseguite da una computazione}\\
&\text{ deterministica rigettante di }NT(x)\\
\\
\text{nspace}(NT,x)= &\textbf{ massimo}\text{ numero di celle utilizzate da una computazione}\\
&\text{ deterministica rigettante di }NT(x)
\end{align*}$$
Durante il corso faremo riferimento alla definizione delle funzioni $\text{ntime}$ ed $\text{nspace}$ che tengono conto anche delle computazioni rigettanti, quindi $$\begin{align*}
&\text{ntime}(T,x)=\begin{cases}
\text{num min di istruzioni eseguite da comp. det. acc. di }NT(x)\text{ se accetta} \\
 \\\text{num max di istruzioni eseguite da comp. det. rig. di }NT(x)\text{ se rigetta}\\
\\
\text{non definita altrimenti}
\end{cases}\\
\\
&\text{nspace}(T,x)=\begin{cases}
\text{num min di celle utilizzate da comp. det. acc. }NT(x)\text{ se accetta}\\\\
\text{num max di celle utilizzate da comp. det. rig. }NT(x)\text{ se rigetta}
\\\\
\text{non definita altrimenti }
\end{cases}
\end{align*}$$
Queste funzioni soddisfano anch'esse i due assiomi di Blum. 

**Teorema 6.1** (caso deterministico)
Sia $T$ una macchina di Turing *deterministica*, definita su $\Sigma$ (non contenente il simbolo $\square$) e un insieme degli stati $Q$, e sia $x\in \Sigma^{*}$ tale che $T(x)$ termina. Allora $$\text{dspace}(T,x)\le \text{dtime}(T,x)\le\text{dspace}(T,x)|Q|(|\Sigma|+1)^{\text{dspace}(T,x)}$$ ^e94afe

**Dim.**
Procediamo con ordine, dimostrando che $\text{dspace}(T,x)\le \text{dtime}(T,x)$. Questo caso è facile, infatti una computazione che termina in $k=\text{dspace}(T,x)$ passi, non può utilizzare piu di $k$ celle del nastro e, per leggere ciascuna cella, impiega un'istruzione. 
Passiamo poi alla parte più complicata: $\text{dtime}(T,x)\le\text{dspace}(T,x)|Q|(|\Sigma|+1)^{\text{dspace}(T,x)}$.

>Osserviamo che, il numero di stati globali per una computazione di una macchina di Turing, definita su un alfabeto $\Sigma$ e un insieme di stati $Q$, che utilizza $h=\text{dspace}(T,x)$  celle del nastro è al più $h|Q|(|\Sigma|+1)^{h}$, infatti:
>- poiché ogni cella del nastro può contenere un simbolo di $\Sigma$ oppure $\square$,  $(|\Sigma|+1)^h$ sono tutte le possibili parole di $h$ simboli di $\Sigma\cup\{\square\}$, ossia, tutte le possibili configurazioni delle $h$ celle utilizzate, e per ognuna di tali configurazioni, la testina può trovarsi su ognuna delle $h$ celle e la macchina può essere in ognuno dei $Q$ stati interni.

Siano quindi  $T$ una macchina deterministica e $x\in\Sigma^{*}$ tali che la computazione $T(x)$ termina in $k$ passi utilizzando $h$ celle del nastro. Poiché $T(x)$ termina in $k$ passi, essa è una successione di stati globali $$SG_{0}(x),SG_{1}(x),\dots,SG_{k}(x),$$tali che $SG_0(x)$ è lo stato globale iniziale di $T(x)$, per ogni $0\le i\le k-1$ esiste una transizione da $SG_{i}(x)$ a $SG_{i+1}(x)$ e $SG_{k}(x)$ è uno stato globale finale. 
Se fosse $k>h|Q|(|\Sigma|+1)^{h}$ allora dovrebbero esistere due indici distinti $j$ ed $l$ compresi fra $0$ e $k-1$, tali che $j<l$ e $SG_{j}(x)=SG_{l}(x)$, ma poiché $T$ è deterministica dovrebbe essere $SG_{j+1}(x)=SG_{l+1}(x)$ e di conseguenza $SG_{j+2}(x)=SG_{l+2}(x),\dots$ Quindi a partire dal passo $j$, la sequenza di stati globali $SG_{j}(x),SG_{j+1}(x),\dots,SG_{l-1}(x)$ si ripeterebbe indefinitamente nella computazione $T(x)$, contraddicendo il fatto che $SG_{k}(x)$ è uno stato globale finale. $\square$ 

Del **[[#^e94afe|teorema 6.1]]** esiste la controparte non deterministica. 
**Teorema 6.1** (caso non deterministico)
Sia $NT$ una macchina di Turing *non deterministica*, definita su $\Sigma$ e un insieme degli stati $Q$, e sia $x\in \Sigma^{*}$ tale che $NT(x)$ termina. Allora $$\text{nspace}(NT,x)\le \text{ntime}(NT,x)\le\text{nspace}(NT,x)|Q|(|\Sigma|+1)^{\text{nspace}(NT,x)}$$ La dimostrazione è omessa, ma presente nelle dispense.

Sia $f:\mathbb{N}\to \mathbb{N}$ una funzione **totale calcolabile**, sia $\Sigma$ un alfabeto finito e sia $x\in \Sigma^{*}$. Indichiamo con $|x|$ il numero di caratteri di $x$.
- Un linguaggio $L\subseteq\Sigma^{*}$ è **deciso in tempo (spazio) deterministico** $f(n)$ se:
	- esiste una macchina di Turing deterministica $T$ che decide $L$ e tale che per ogni $x\in\Sigma^{*}$ $$\begin{align*}
&\text{dtime}(T,x)\le f(|x|)\\\\
&(\text{dspace}(T,x)=f(|x|))
\end{align*}$$
- Un linguaggio $L\subseteq\Sigma^{*}$ è **accettato in tempo (spazio) non deterministico** $f(n)$ se:
	- esiste una macchina di Turing non deterministica $NT$ che accetta $L$ e tale che per ogni $x\in L$ $$\begin{align*}
&\text{ntime}(NT,x)\le f(|x|)\\\\
&(\text{nspace}(NT,x)\le f(|x|))
\end{align*}$$
- Un linguaggio $L\subseteq\Sigma^{*}$ è **deciso in tempo (spazio) non deterministico** $f(n)$ se
	- esiste una macchina di Turing non deterministica $NT$ che accetta $L$ e tale che per ogni $x\in \Sigma^{*}$ $$\begin{align*}
&\text{ntime}(NT,x)\le f(|x|)\\\\
&(\text{nspace}(NT,x)\le f(|x|))
\end{align*}$$

Il prossimo teorema mostra che, ogni qualvolta una funzione totale e calcolabile limita la quantità di risorse disponibili al fine di accettare le parole di un linguaggio, i concetti di accettabilità e decidibilità coincidono

**Teorema 6.2**
Sia $f:\mathbb{N}\to \mathbb{N}$ una funzione totale calcolabile. 
Se $L\subseteq\Sigma^{*}$ è accettato da una macchina di Turing non deterministica $NT$ tale che, per ogni $x\in L$, $\text{ntime}(NT,x)\le f(|x|)$, allora $L$ è decidibile.

**Dim.**
Poiché $f$ è totale e calcolabile, allora esiste una macchina di Turing di tipo trasduttore $T_{f}$ che, per ogni $n\in\mathbb{N}$, calcola $f(n)$. Assumiamo che l'input di $T_{f}$ sia codificato in unario (ossia il numero $n$ sia scritto sul nastro di input $T_{f}$ nella forma di una sequenza di $n$ caratteri $1$) e che al termine della computazione $T_{f}(n)$ il valore $f(n)$ sia scritto sul nastro di output in unario. 

Sia $L\subseteq\Sigma^{*}$ un linguaggio accettato da una macchina di Turing non deterministica $NT$ tale che, per ogni $x\in L$, $\text{ntime}(NT,x)\le f(|x|)$. Deriviamo, componendo $NT$ e $T_{f}$ una nuova macchina $NT^{'}$ a tre nastri. Sul primo nastro di $NT^{'}$ viene scritto l'input $x\in\Sigma^{*}$ e la computazione $NT^{'}$ suddivisa in 3 fasi ha inizio: 
1. **Viene scritto il valore $|x|$ in unario sul secondo nastro**: si scorre il primo nastro (dove è scritto $x$) e, fino a quando viene letto un carattere diverso da $\square$, si scrive un $1$ sul secondo nastro, facendo avanzare le testine di entrambi i nastri di una posizione. Quando sul primo nastro viene letto $\square$, le testine dei primi due nastri vengono riposizionate sui caratteri più a sinistra e la prima fase ha termine
2. La seconda fase consiste nella **simulazione della computazione $T_{f}(x)$**, utilizzando il secondo nastro come nastro di input e il terzo nastro come nastro di output. **Essa termina scrivendo il valore $f(|x|)$ in unario sul terzo nastro** e posizionando la testina del terzo nastro sul suo carattere più a sinistra
3. La terza fase consiste nella **simulazione dei primi $f(|x|)$ passi della computazione $NT(x)$**, utilizzando il primo nastro come nastro di input e di lavoro e il terzo nastro come contatore del numero di istruzioni eseguite: fino a quando viene letto $1$ sul terzo nastro e non è stato raggiunto uno stato finale, viene eseguita un'istruzione di $NT(x)$ e la testina del terzo nastro viene spostata di una posizione a destra. Se la macchina $NT$ raggiunge lo stato di accettazione o rigetto, la computazione $NT^{'}(x)$ termina nello stesso stato. Se viene letto ilc arattere $\square$ sul terzo nastro, la computazione $NT^{'}(x)$ termina in rigetto.

**Oss.**
Poiché $f$ è calcolabile e poiché la simulazione della computazione $NT(x)$ nella terza fase viene forzatamente terminata se non ha terminato entro le $f(|x|)$ istruzioni disponibili, tutte le computazioni di $NT^{'}$ terminano. Inoltre
- se $x\in L$, allora poiché $NT$ accetta $x$ in $f(|x|)$ passi, la simulazione di $NT(x)$ durante la terza fase termina nello stato di accettazione prima che venga letto $\square$ sul terzo nastro e, dunque, $NT^{'}$ accetta $x$
- se $x\not\in L$ allora, o la simulazione di $NT(x)$ durante la terza fase termina nello stato di rigetto prima che venga letto $\square$ sul terzo nastro, oppure la simulazione di $NT(x)$ durante la terza fase non termina prima che venga letto $\square$ sul terzo nastro; in entrambi i casi $NT^{'}$ rigetta $x$

Questo dimostra che $NT^{'}$ decide $L$ e quindi $L$ è decidibile. $\square$

Nel paragrafo 6.2 della [dispensa 6](http://informatica.uniroma2.it/upload/2023/FO/D06LinguaggiEComplessita.pdf) si dimostra che **tutti i modelli di calcolo deterministici sono fra loro polinomialmente correlati**, ovvero per ogni macchina di Turing $T$ di un determinato tipo (ad un nastro, a $k$ nastri, alfabeto binario ecc.) esistono una macchina di Turing $T'$ (di un altro di quei tipi) ed un polinomio $p$ tali che $T'$ risolve lo stesso problema risolto da $T$ e, per ogni $x$, $\text{dtime}(T',x)\le p(\text{dspace}(T,x))$ e $\text{dtime}(T',x)\le p(\text{dspace}(T,x))$. 
Anche il modello Macchina di Turing è polinomialmente correlato con il PascalMinimo, quindi possiamo scrivere un algoritmo $A$ in PascalMinimo e se $A$ trova la soluzione di un istanza $x$ del problema eseguendo $f(|x|)$ istruzioni, allora esiste una macchina di Turing $T$ ad un nastro che risolve lo stesso problema, ed esiste un polinomio $p$ tale che $\text{dtime}(T,x)\le p(f(|x|))$.

I teoremi del paragrafo non vanno studiati, ma menzioniamo una questione: $|x|$ lo leggiamo come **lunghezza di $x$** e qualunque sia il modello di calcolo utilizzato, essa rappresenta la quantità di memoria che occorre a rappresentare $x$ in quel modello, ovvero il numero di celle di un natro di una macchina di Turing, il numero di bit di una RAM, ecc.


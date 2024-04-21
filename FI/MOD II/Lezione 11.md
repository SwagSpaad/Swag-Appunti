*****
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


*****
Come facciamo a mostrare come trasformare un **qualsiasi** problema in $NP$ a $SAT$ se i problemi in $NP$ sono così diversi tra di loro?
*Semplice*, sfruttando l'unica cosa che hanno in comune, tutti appartengono ad $NP$, ovvero, sono accettati da una macchina di Turing non deterministica in tempo polinomiale

Consideriamo un problema generico $\Gamma \in NP$ e sia $L_{\Gamma} \subseteq \{0,1\}^{*}$ il linguaggio che contiene la codifica ragionevole delle istanze **sì** di $\Gamma$ e cerchiamo di descrivere sotto forma di espressione booleana il predicato "$x\in L_{\Gamma}$"
*Questa dimostrazione che stiamo per affrontare è differente da quella che si trova sulle dispense*

**Dim.**
Sia $NT_{\Gamma}$ una macchina di Turing non deterministica ad un nastro che decide $L_{\Gamma}$ in tempo polinomiale, ossia, esiste un polinomio $p$:
$\forall x \in \{0,1\}^{*}$
- $ntime(NT_{\Gamma})\leq p(|x|)$ 
- $NT_{\Gamma}(x) = q_{A}$ se $x \in L_{\Gamma}$
- $NT_{\Gamma}(x) \neq q_{A}$ se $x \neq L_{\Gamma}$
L'affermazione "$x \in L_{\Gamma}$" è logicamente equivalente all'affermazione:
$\gamma(x) =$ "$x$ è scritto sul nastro di $NT_{\Gamma}$
- *e* la testina di $NT_{\Gamma}$ è posizionata sul primo carattere di $x$
- *e* $NT_{\Gamma}$ è nel suo stato iniziale
- *e* esiste una sequenza di al più $p(|x|)$ quintuple di $NT_{\Gamma}$ che possono essere eseguite una di seguito all'alòtra e portano la macchina nello stato $q_{A}$"
cioè $x\in L_{\Gamma} \iff \gamma(x)$ è vera

Non resta che descrivere una **computazione di $NT_{\Gamma}$ che ha iniziato con x scritto sul suo nastro** e dato che ogni computazione di una macchina di Turing è una sequenza di stati globali, per costruire $E(x)$ è necessario introdurre le variabili booleane che descrivono, per ogni passo di $t$ della computazione lo stato globale in cui si troverebbe $NT_{\Gamma}$ al passo $t$ della computazione $NT_{\Gamma}$:
- Insieme $N$ di variabili booleane che permettono di rappresentare il carattere contenuto in ciascuna cella del nastro di lavoro di $NT_{\Gamma}$
- Insieme $M$ di variabili booleane che permettono di rappresentare lo stato interno di $NT_{\Gamma}$
- Insieme $R$ di variabili booleane che permettono di rappresentare la cella del nastro di lavoro sulla quale è posizionata la testina $NT_{\Gamma}$
**Analizziamo queste variabili:**
Partiamo con l'insieme $M$.
Sia $Q = \{q_{0}, q_{1}, q_{2},....,q_{k}\}$ l'insieme degli stati di $NT_{\Gamma}$ 
	Con $q_{0}$ stato iniziale, $q_{1} = q_{A}$ e $q_{2} = q_{R}$ 
Questo insieme $M$, insieme ad una porzione $E_{M}$ dell'espressione $E(x)$ che stiamo costruendo, servono a descrivere in quale stato interno si trova $NT_{\Gamma}$ ad ogni passo della computazione $NT_{\Gamma}(x):$ quello che vogliamo è che,
- **Ogni volta che i valori assegnati alle variabili in $M$ fanno assumere ad $E_{M}$ il valore vero**
- **Osservando i valori assegnati alle variabili contenute in $M$, dobbiamo essere in grado di rispondere a domande del tipo "è $q_{4}$ lo stato interno di $NT_{\Gamma}$ al passo 25 della computazione $NT_{\Gamma}(x)$?"**
Per ogni passo $t \ (0\leq t\leq p(|x|))$, e per ogni $i\in \{0,1,...,k\}$, $M$ contiene una variabile booleana $M_{i}^{t}:$ $$M = \{M_{i}^{t}:0\leq t\leq p(|x|) \land i\in \{0,1,...,k\}\}$$Questo significa: **assegnando a $M_{i}^{t}$ il valore vero rappresentiamo il fatto che, al passo $t$ della computazione $NT_{\Gamma}(x)$, la macchina $NT_{\Gamma}$ si trova nello stato $q_{i}$**

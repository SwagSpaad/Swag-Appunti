******
Nella lezione precedente abbiamo quindi proposto di estendere ai problemi quanto studiato relativamente alla complessità dei linguaggi, a patto di utilizzare *codifiche ragionevoli* per codificare le istanze dei problemi. 
Dobbiamo solo capire come trasformare un problema in un linguaggio e se questo è semplice o ci porterà ad affrontare nuove questioni.

Sia $\Gamma = \langle\Im_{\Gamma},S_{\Gamma},\pi_{\Gamma} \rangle$ un problema decisionale. Osserviamo che l'insieme delle istanze $\Im_{\Gamma}$ è partizionato in due sottoinsiemi: 
- l'insieme delle **istanze positive** ovvero quelle che verificano $\pi_{\Gamma}$
- l'insieme delle **istanze negative** ovvero quelle che non verificano $\pi_{\Gamma}$
Sia $\chi:\Im_{\Gamma}\to \Sigma^{*}$ una codifica ragionevole per $\Gamma$. 
La codifica $\chi$ partiziona $\Sigma^{*}$ in tre sottoinsiemi di parole: 
- l'insieme $Y_{\Gamma}$ delle parole che codificano istanze positive di $\Gamma$ 
- l'insieme $N_{\Gamma}$ delle parole che codificano istanze negative di $\Gamma$
- l'insieme delle parole che *non* codificano istanze di $\Gamma$ 
Il linguaggio associato a $\Gamma$ mediante la codifica $\chi$ è il sottoinsieme $L_\Gamma(\chi)$ di $\Sigma^{*}$ contenenti le parole appartenenti a $Y_{\Gamma}$, ossia $$L_{\Gamma}(\chi)=\Big\{x\in\Sigma^{*}:\exists y\in\Im_{\Gamma}\ \Big[x=\chi(y)\land \pi_\Gamma(y,S_\Gamma(y))\Big]\Big\}$$ Quindi, decidere se un'istanza $y$ di $\Gamma$ è un'istanza positiva, corrisponde a decidere se $x=\chi(y)$ è contenuto in $L_{\Gamma}(\chi)$ e quindi data $x\in\Sigma^{*}$, per decidere se $x\in L_{\Gamma}(\chi)$ occorre:
- decidere se $x$ è la codifica di un'istanza $y$ di $\Gamma$ 
- in caso affermativo, decidere se il predicato $\pi_{\Gamma}(y,S_\Gamma(y))$ è soddisfatto

Possiamo quindi definire la complessità computazionale di un problema decisionale.

**Def.**
Sia $\Gamma = \langle\Im_{\Gamma},S_{\Gamma},\pi_{\Gamma} \rangle$ un problema decisionale e sia $C$ una classe di complessità. 
- Data una funzione $f$ totale e calcolabile
- e $C\in\Big\{\text{DTIME}[f(n)],\text{DSPACE}[f(n)],\text{NTIME}[f(n)],\text{NSPACE}[f(n)]\Big\}$ 
Diciamo che $\Gamma\in C$ se esiste una codifica *ragionevole* $\chi:\Im_{\Gamma}\to\Sigma^{*}$ per $\Gamma$ tale che $L_\Gamma(\chi)\in C$ 

Vediamo con un esempio cosa occorre fare per decidere se $x\in L_{\Gamma}(\chi)$.

>**Esempio**
>Riprendiamo il problema **3SAT** e la codifica $\chi_{1}$, che abbiamo visto essere una codifica ragionevole $$\chi_{1}(X,f)=444\ 0100\ 2\ 0010\ 2 \ 0001\ 3\ 0100\ 2\ 1010\ 2\ 1001$$
>Allora una parola $x\in\{0,1,2,3,4\}^{*}$ è in $L_{3SAT}(\chi_{1})$ se sono verificati i seguenti punti:
>- $x$ deve essere la codifica secondo $\chi_{1}$ di qualche coppia $\langle X,f\rangle$ istanza di **3SAT**, infatti è semplice verificare che $40211011103420111$ non è la codifica di nessuna istanza. Se $x$ non è una codifica valida possiamo subito concludere che $x\not\in L_{3SAT}(\chi_{1})$ 
>- se $x$ è la codifica secondo $\chi_{1}$ di un'istanza $\langle X,f\rangle$ di **3SAT**, affinché $x\in L_{3SAT}(\chi_{1})$ occorre che $f$ sia soddisfacibile

Dati un problema $\Gamma$ e una sua codifica ragionevole $\chi$, per verificare che una parola sia in $L_{\Gamma}(\chi)$, occorre prima di tutto verificare che essa sia la codifica di una istanza. 
Definiamo **il linguaggio delle istanze di $\Gamma$**, ossia, il linguaggio $$\chi(\Im_{\Gamma})=\Big\{x\in\Sigma^{*}:\exists y\in\Im_{\Gamma}[x=\chi(y)]\Big\}$$
e osserviamo che $\chi$ è una codifica di $\Im_{\Gamma}$, quindi, se $y,z\in\Im_{\Gamma}$ sono due istanze di $\Gamma$ con $y\neq z$, allora $\chi(y)\neq\chi(z)$ quindi $\chi$ è una funzione invertibile, allora possiamo definire il linguaggio $L_\Gamma(\chi)$ anche nella maniera seguente: $$L_\Gamma(\chi)=\Big\{x\in\Sigma^{*}:x\in\chi(\Im_{\Gamma})\land\pi \big(\chi^{-1}(x)),\ S_{\Gamma}(\chi^{-1}(x)\big)\Big\}$$ Dunque, se, per decidere se una parola $x$ appartiene a $L_{\Gamma}(\chi)$ dobbiamo anche verificare se $x$ è effettivamente la codifica di un'istanza di $\Gamma$, allora per definire la complessità del problema decisionale $\Gamma$ occorre considerare anche la complessità di decidere il linguaggio $\chi(\Im_{\Gamma})$.

**Esempio**
Consideriamo il problema decisionale del *Percorso in Ciclo Hamiltoniano (PHC)*.
Sia dato un particolare grafo non orientato $G=(V,E)$, che contiene un ciclo che passa una ed una sola volta per ciascuno dei suoi nodi. Siano dati due nodi $u,v\in V$. Si chiede di decidere se esiste in $G$ un percorso che collega $u$ a $v$.  ^645f90

Formalizziamo il problema precedente mediante la tripla $\langle \Im_{PHC},S_{PHC},\pi_{PHC}\rangle$:
- $\Im_{PHC}=\{\langle G=(V,E), u,v\rangle\}$, $G$ è un grafo non orientato $\land\ \exists$ un ciclo $c$ in $G$ che passa una e una sola volta attraverso ciascun nodo di $G\land u,v\in V$
- $S_{PHC}(G,u,v)=\{p:p\text{ è un percorso in }G\}$ 
- $\pi_{PHC}(G,u,v,S_{PHC}(G,u,v))=\exists\ p\in S_{PHC}(G,u,v)$ che connette $u$ a $v$
Se sappiamo che un grafo contiene un ciclo che passa una e una sola volta attraverso tutti i nodi di $G$, allora, qualunque coppia di nodi $u$ e $v$ si consideri, una porzione di quel ciclo è un percorso da $u$ a $v$. 
Questo significa che ogni istanza del problema PHC è una istanza sì, quindi indipendentemente dalla codifica utilizzata, decidere se una qualunque istanza del problema soddisfa il predicato del problema richiede costo costante.
D'altra parte, data una qualunque codifica ragionevole $\chi$ per PHC, per decidere se una parola $x\in\{0,1\}^{*}$ è contenuta in $L_{PHC}(\chi)$ dobbiamo verificare
- sia se $x$ è  la codifica di una istanza di PHC, ossia di un grafo che contiene un ciclo che attraversa tutti i nodi una e una sola volta e di una coppia di suoi nodi
- sia se il grafo contiene un percorso che connette i due nodi
La prima di queste due verifiche, ossia decidere $\chi(\Im_{PHC})$ è un linguaggio $\textbf{NP}$-completo e quindi concludiamo che $L_{PHC}(\chi)$ è $\textbf{NP}$-completo. 
Allora, anche se, una volta assodato che una parola $x\in\{0,1\}^{*}$ è istanza di PHC, decidere se $x$ soddisfa $\pi_{PHC}(G,u,v,S(G,u,v))$ ha costo costante, **non possiamo affermare che decidere PHC è un problema in $\textbf{P}$**.

Sia $\Sigma$ un qualunque alfabeto. Una qualunque codifica $\chi$ delle istanze di un problemadecisionale $\Gamma$ in parole di $\Sigma^{*}$ induce una tri-partizione di $\Sigma^{*}$, ovvero una partizione di $\Sigma^{*}$ in tre sottoinsiemi:
- l'insieme $Y_\Gamma$ delle parole di $\Sigma^{*}$ che codificano le istanze sì di $\Gamma$, ovvero il linguaggio $L_{\Gamma}(\chi)$ 
- l'insieme $N_{\Gamma}$ delle parole di $\Sigma^{*}$ che codificano le istanze no di $\Gamma$
- parole di $\Sigma^{*}$ che non codificano istanze di $\Gamma$
Ricordiamo che, dato un qualunque linguaggio $L\subseteq\Sigma^{*}$, il linguaggio complemento di $L$ è $L^{C}=\Sigma^{*}-L$.
Quindi secondo questa definizione il linguaggio complemento di $L_{\Gamma}(\chi)$ è $$L_{\Gamma}(\chi)^{C}=\Sigma^{*}-L_{\Gamma}(\chi)$$ovvero tutte le parole di $\Sigma^{*}$ che codificano istanze no di $\Gamma$ e tutte le parole di $\Sigma^{*}$ che non codificano istanze di $\Gamma$. 
Ma siamo sicuri che questo è proprio ciò che corrisponde al complemento di un problema decisionale? 
Se pensiamo al complemento di un problema di decisione, quello che ci viene in mente sono **le istanze del problema che non soddisfano il predicato**, ad esempio, il problema $3SAT^{C}$ è l'insieme delle istanze $\langle X,f\rangle$ di **3SAT** tali che $f$ non è soddisfacibile, quindi $$3SAT^{C}=\langle \Im_{3SAT},S_{3SAT},\lnot\pi_{3SAT}\rangle$$ Perciò il linguaggio che vogliamo associare al problema complemento di $\Gamma$ non è $L_{\Gamma}(\chi)^{C}=\Sigma^{*}-L_{\Gamma}(\chi)$, bensì l'insieme $N\Gamma$ $$L_{\Gamma^{C}}(\chi)=\{x\in\Sigma^{*}:x\in\chi(\Im_{\Gamma})\land\lnot\pi_\Gamma(\chi^{-1}(x),S_{\Gamma}(\chi^{-1}(x)))\}$$ Ora, datu un linguaggio $L$ e una classe di complessità $C$, sappiamo, per definizione, che se $L_{\Gamma}(\chi)\in C$ allora $(L_{\Gamma}(\chi))^{C}\in coC$. Ma se sappiamo che $L_{\Gamma}(\chi)\in C$, cosa possiamo dire del suo linguaggio complementare $L_{\Gamma^{C}}(\chi)$? Ovvero, se sappiamo classificare un problema di decisione, sappiamo classificare anche il suo complemento? Vediamo un esempio e poi rispondiamo a questa questione.

>**Esempio**
>Riprendiamo il prolema decisionale [[#^645f90|PHC]]. Dato un grafo non orientato $G=(V,E)$ che contiene un ciclo che passa una ed una sola volta per ciascuno dei suoi nodi, e dati due suoi nodi $u,v\in V$, si chiede di decidere se esiste in $G$ un percorso che collega $u$ a $v$.
>PHC è formalizzato mediante la tripla $\langle \Im_{PHC},S_{PHC},\pi_{PHC}\rangle$:
>- $\Im_{PHC}=\{\langle G=(V,E), u,v\rangle\}$, $G$ è un grafo non orientato $\land\ \exists$ un ciclo $c$ in $G$ che passa una e una sola volta attraverso ciascun nodo di $G\land u,v\in V$
>- $S_{PHC}(G,u,v)=\{p:p\text{ è un percorso in }G\}$ 
>- $\pi_{PHC}(G,u,v,S_{PHC}(G,u,v))=\exists\ p\in S_{PHC}(G,u,v)$ che connette $u$ a $v$
>$PHC^{C}$ allora è:
>dato un grafo non orientato $G=(V,E)$ che contiene un ciclo che passa una ed una sola volta per ciascuno dei suoi nodi, e dati due suoi nodi $u,v\in V$, si chiede di decidere se **non esiste** in $G$ alcun percorso che collega $u$ a $v$ ed è formalizzato mediante la tripla $\langle \Im_{PHC},S_{PHC},\lnot\pi_{PHC}\rangle$ con $$\lnot\pi_{PHC}(G,u,v,S_{PHC}(G,u,v))=\not\exists\ p\in S_{PHC}(G,u,v)\text{ che connette u a v}$$Data una qualunque codifica ragionevole $\chi$ per $PHC^{C}$, per decidere se una parola $x$ è contenuta in $L_{PHC^{C}}(\chi)$, dobbiamo verificare:
>- se $x$ è la codifica di un'istanza di $PHC^{C}$, ossia di un grafo che contiene un ciclo che attraversa tutti i nodi una e una sola volta, e di una coppia di suoi nodi
>- se questo grafo non contiene percorsi che connettono i due nodi
>Come abbiamo visto:
>- la verifica che $x$ sia la codifica di un'istanza di $PHC$ è un problema $\textbf{NP}$-completo
>- verificare se una qualunque istanza del problema soddisfa il predicato del problema richiede costo costante, proprio perché nessuna istanza soddisfa il predicato!
>Possiamo quindi concludere (ad occhio) che $PHC^{C}$ è $\textbf{NP}$-completo.

Riassumendo, il problema PHC è $\textbf{NP}$-completo e anche il suo complemento $PHC^{C}$.
Quindi sembrerebbe che **non** possiamo trasportare ai problemi decisionali la teoria della complessità che abbiamo sviluppato per i linguaggi, perché **la complessita di un problema decisionale dipende anche dalla complessità di decidere il linguaggio delle istanze**. Ma se la decisione del linguaggio delle istanze richiede "poche risorse", possiamo trasferire tutto ciò che abbiamo studiato relativamente alla complessità dei linguaggi, alla complessità dei problemi decisionali, come ci mostra il prossimo teorema.

**Teorema 7.1**
Sia $\Gamma=\langle\Im_{\Gamma},S_{\Gamma},\pi_{\Gamma}\rangle$ un problema decisionale e sia $\chi:\Im_{\Gamma}\to\Sigma^{*}$ una sua codifica ragionevole. Se $\chi(\Im_{\Gamma})\in \textbf{P}$ allora valgono le seguenti implicazioni:
- se $L_{\Gamma}(\chi)\in \textbf{NP}$ allora $L_{\Gamma^{C}}(\chi)\in co\textbf{NP}$
- se $L_{\Gamma}(\chi)\in \textbf{NEXPTIME}$ allora $L_{\Gamma^{C}}(\chi)\in co\textbf{NEXPTIME}$

**Dim.** per il caso 1.
Se $\chi(\Im_{\Gamma})\in \textbf{P}$, allora esistono una macchina deterministica $T$ ed un intero $h$ tali che, per ogni $x\in\Sigma^{*}$, $T$ decide se $x\in \chi(\Im_{\Gamma})$ e $\text{dtime}(T,x)\in O(|x|^h)$.
Se $L_{T}(\chi)\in \textbf{NP}$, allora esistono una macchia non deterministica $NT$ ed un intero $k$ tali che, per ogni $x\in L_{\Gamma}(\chi)$, $NT$ accetta $x$ e $\text{ntime}(NT,x)\in O(|x|^{k})$.
Combinando $T$ e $NT$, costruiamo una nuova macchina non determinstica $NT_{0}$ che **accetta il linguaggio complemento di $L_{\Gamma^{C}}(\chi)$, ossia, che accetta $(L_{\Gamma^{C}}(\chi))^{C}$**.
Sorgono due domande:
1. perché ci interessa accettare $(L_{\Gamma^{C}}(\chi))^{C}$? Se riusciamo a mostrare che possiamo accettare $(L_{\Gamma^{C}}(\chi))^{C}$ in tempo non deterministico polinomiale, allora $(L_{\Gamma^{C}}(\chi))^{C}$ è in $\textbf{NP}$ e dunque $L_{\Gamma^{C}}(\chi)\in co\textbf{NP}$
2. che parole troviamo in $(L_{\Gamma^{C}}(\chi))^{C}$? Poiché in $L_{\Gamma^{C}}(\chi)$ troviamo parole che codificano le istanze no di $\Gamma$, allora in $(L_{\Gamma^{C}}(\chi))^{C}$ troviamo:
	- parole che non codificano istanze di $\Gamma$
	- parole che codificano istanze sì di $\Gamma$, ossia, parola che appartengono a $L_{\Gamma}(\chi)$
$NT_{0}$ opera in due fasi: con input $x\in\Sigma^{*}$:
1. Simula la computazione $T(x)$, se $T(x)$ termina nello stato di rigetto, allora $NT_{0}$ termina nello stato di accettazione, altrimenti inizia la fase 2
2. Simula la computazione $NT(x)$, se $NT(x)$ accetta, allora $NT_{0}$ accetta
$NT_{0}(x)$ accetta quando $x\not\in \chi(\Im_{\Gamma})$ oppure quando $x\in L_{\Gamma}(\chi)$, cioè, **$NT_{0}(\chi)$ accetta se e soltanto se $x$ appartiene a $(L_{\Gamma^{C}}(\chi))^{C}$**.
È semplice verificare che $\text{ntime}(NT_{0},x)\in O(|x|^{\max\{h,k\}})$. 
Quindi $(L_{\Gamma^{C}}(\chi))^{C}$ è in $\textbf{NP}$ e dunque $L_{\Gamma^{C}}(\chi)\in co\textbf{NP}$. $\square$

Non è ragionevole che sia più complesso decidere se una parola è istanza di un problema, che decidere se una istanza di quel problema è un'istanza positiva, perché la difficoltà nel risolvere un problema non dovrebbe essere nel riconoscere che i dati che ci vengono forniti siano effettivamente dati del nostro problema, ma nel trovare una soluzione ad una data istanza del problema.
Per questo, d'ora in poi assumeremo che, per ogni problema di decisione $\Gamma$ e per ogni sua codifica ragionevole $\chi$, il linguaggio delle istanze sia in $\textbf{P}$, ossia $\chi(\Im_{\Gamma})\in\text{P}$.
Questo significa che, la formalizzazione del problema PHC sarà:
- $\Im_{PHC}=\{\langle G=(V,E), u,v\rangle\}$, $G$ è un grafo non orientato $\land\ u,v\in V$
- $S_{PHC}(G,u,v)=\{p:p\text{ è un percorso in }G\}$ 
- $\pi_{PHC}(G,u,v,S_{PHC}(G,u,v))=\exists$ un ciclo $c$ in $G$ che passa una e una sola volta attraverso ciascun nodo di $G\land\exists\ p\in S_{PHC}(G,u,v)$ che connette $u$ a $v$
ossia, sposteremo nel predicato tutte le proprietà che devono essere soddisfatte dai dati che costituiscono l'istanza.



Grammatica tipo 0: sono quelle in cui la parte sinistra di una produzione ha almeno un simbolo non terminale ($\alpha \in V^{*} \circ V_{N} \circ V^{*}$) mentre la parte destra ha un qualsiasi simbolo o $\epsilon$ ($\gamma \in V^*$) ^432bae
 
Gramatica tipo 1 **Context sensitive**: sono quelle in cui la parte sinistra di una produzione ha almeno un simbolo non terminale ($\alpha \in V^{*} \circ V_{N} \circ V^{*}$) mentre la parte destra ha almeno un simbolo tale che la forma di frase ha lunghezza almeno quella di $\alpha$ ($\gamma \in V^{+} t.c. |\alpha| \leq |\gamma|$) ^da2967

Grammatica tipo 2 **Context free**: sono quelle in cui la parte sinistra di una produzione è un simbolo non terminale ($A \in V_{N}$) mentre la parte destra ha almeno un simbolo terminale o non terminale ($\gamma \in V^+$) ^75297e

###### Grammatica tipo 3:
- **Lineari destre**: sono quelle in cui la parte sinistra di una produzione è un simbolo non terminale ($A \in V_{N}$), mentre la parte destra è la concatenzaione di un simbolo terminale con un non terminale oppure un simbolo terminale ($\gamma \in (V_{T} \circ V_{N}) \cup V_{T}$)
- **Lineari sinistre**: la parte destra è la concatenzaione di un simbolo non terminale con un terminale oppure un simbolo terminale ($\gamma \in (V_{N} \circ V_{T}) \cup V_{T}$).


>**Teorema** 
>Data una grammatica $\mathcal G=\langle V_T,V_N,P,S\rangle$ di [[Riepilogo#^432bae|tipo 0]], esiste una grammatica di tipo $\mathcal G'$ equivalente a $\mathcal G$, ottenuta estendendo una grammatica di [[Riepilogo#^da2967|tipo 1]] con opportune $\epsilon$-produzioni

La grammatica $\mathcal G'=\langle V_T',V_N',P',S'\rangle$ è caratterizzata da: $V_T'=V_T,\:V_N'=V_N\cup\lbrace X\rbrace$,con $X\not\in V_N,S'=S\:e\:P'$ ottenuto da $P$ aggiungendo la produzione $X\to\epsilon$ e sostituendo ad ogni produzione $\phi\to\psi$, con $|\phi|\gt|\psi|\gt 0$ la produzione:
$$\phi\to\psi\:\:\underbrace {X...X}_{|\phi|-|\psi|}$$
è semplice verificare che con la grammatica $\mathcal G'$ sono derivabili tutte e sole le stringhe di $V_{T}^\star$ derivabili con la grammatica $\mathcal G$.


**Automi deterministici**: Ad ogni stringa di input associa una sola computazione, e quindi una singola sequenza di configurazioni.

![[FI/img/img7.png|center|500]]

Un automa deterministico, data una stringa in input, può eseguire una sola computazione: se la computazione termina in una configurazione di accettazione, allora la stringa viene accettata.


**Automi non deterministici**: Associa ad ogni stringa in input un numero qualunque, in generale maggiore di uno, di computazioni.
La funzione di transizione associa ad almeno una configurazione c più di una configurazione successiva
Per ogni computazione che conduce a $c$ sono definite continuazioni diverse, che definiscono diverse computazioni

![[FI/img/img8.png|center|500]]

Nell'immagine possiamo vedere nel dettaglio questo discorso dell'associare ad una configurazione $c$ più di una configurazione.


Un **automa a stati finiti deterministico** (ASFD) è una quintupla $\mathcal A=\langle\Sigma,Q,\delta,q_0,F\rangle$, dove:
- $\Sigma=\lbrace a_1,...,a_n\rbrace$ è l'**alfabeto** di input (corrisponde all'insieme dei simboli terminali nelle grammatiche)
- $Q=\lbrace q_0,...,q_m\rbrace$ è un insieme finito e non vuoto di **stati**
- $q_0\in Q$ è lo **stato iniziale**
- $F\subseteq Q$ è l'insieme di **stati finali**
- $\delta:Q\times\Sigma\to Q$ è la **funzione (totale) di transizione** che ad ogni coppia $\langle\text{stato},\text{carattere in input}\rangle$ associa uno stato successivo.


Un **automa a stati non deterministico** è una quintupla $\mathcal A_N=\langle\Sigma,Q,\delta_N,q_0,F\rangle$, in cui:
- $\Sigma=\lbrace a_1,...,a_n\rbrace$ è l'**alfabeto** di input (corrisponde all'insieme dei simboli terminali nelle grammatiche)
- $Q=\lbrace q_0,...,q_m\rbrace$ è un insieme finito e non vuoto di **stati**
- $q_0\in Q$ è lo **stato iniziale**
- $F\subseteq Q$ è un insieme di **stati finali**
- $\delta_N:Q\times\Sigma\to\mathcal P(Q)$ è una funzione (parziale), detta **funzione di transizione**, che ad ogni coppia $\langle stato,carattere\rangle$ su cui è definita associa un sottoinsieme di $Q$ (eventualmente vuoto)


>Dato un ASFND che riconosce il linguaggio $L$, esiste corrispondentemente un $\epsilon$-ASFND che riconosce lo stesso linguaggio $L$; viceversa, dato un $\epsilon$-ASFND che riconosce un linguaggio $L'$, esiste un ASFND che riconosce lo stesso linguaggio $L'$


>**Teorema**
>Data una grammatica $\mathcal G$ di tipo 3, esiste una espressione regolare $r$ tale che $L(\mathcal G)=L(r)$, che descrive cioè il linguaggio generato da $\mathcal G$


>**Teorema**
>Dato un ASFD $\mathcal A$, esiste una espressione regolare $r$ tale che $L(\mathcal A)=L(r)$, che descrive cioè il linguaggio riconosciuto da $\mathcal A$


Una grammatica $\mathcal G$ è in _forma ridotta_ se:
1. non contiene $\varepsilon$-produzioni (se non, eventualmente, in corrispondenza dell'assioma, ed in tal caso l'assioma non compare mai al lato destro di una produzione)
2. non contiene **produzioni unitarie**, cioè produzioni del tipo $$A\to B, con\:A,B\in V_N$$
3. non contiene **simboli inutili**, cioè simboli che non compaiono in nessuna derivazione di una stringa di soli terminali


**Pumping Lemma**: Sia $L$ un linguaggio regolare, allora $\exists n\gt 0$ tale che $\forall z\in L:|z|\geq n$ possiamo scrivere $z=u\:v\:w$, con $|u\:v|\leq n,|v|\geq1$ e ottenere che $\forall i\geq0, u\:v^i\:w\in L$. Utile per dimostrare la **NON** regolarità di un linguaggio, infatti se il pumping lemma non è verificato allora il linguaggio non è regolare.


Da grammatica di tipo 2 ad una equivalente in forma ridotta mediante una sequenza di passi:
1. A partire da $\mathcal G$, derivazione di $\mathcal G_1$ di tipo 2 senza $\varepsilon$-produzioni tale che $L(\mathcal G_1)=L(\mathcal G)-\lbrace\varepsilon\rbrace$
2. A partire da $\mathcal G_1$, derivazione di $\mathcal G_2$ di tipo 2 senza $\varepsilon$-produzioni e senza produzioni unitarie tale che $L(\mathcal G_2)=L(\mathcal G_1)$
3. A partire da $\mathcal G_2$, derivazione di $\mathcal G_3$ di tipo 2 senza $\varepsilon$-produzioni, senza produzioni unitarie e senza simboli inutili tale che $L(\mathcal G_3)=L(\mathcal G_2)$
4. La grammatica $\mathcal G_4$ di tipo 2, equivalente a $\mathcal G$ coincide con $\mathcal G_3$ se $\varepsilon\not\in L(\mathcal G)$; altrimenti, $\mathcal G_4$ è ottenuta da $\mathcal G_3$ introducendo un nuovo assioma ed un opportuno insieme di produzioni su tale simbolo
Per l'applicazione vedere gli esempi in [[Lezione 12 - Sempre su Linguaggi CF]]


Una grammatica di tipo 2 si dice in **Forma Normale di Chomsky** se tutte le sue produzioni sono del tipo $A\to BC$ o del tipo $A\to a$, con $A,B,C\in V_N,a\in V_T$


Da [[Lezione 16 - PDA non deterministici|NPDA]] a Grammatica: 
- Sia $L$ un linguaggio accettato mediante pila vuota da un automa a pila $\mathcal M=\langle\Sigma,\Gamma,Q,\delta,Z_0,q_0,\emptyset\rangle$, allora esiste una grammatica non contestuale $\mathcal G$ che lo genera, cioè $L=N(\mathcal M)=L(\mathcal G)$ costruita nel seguente modo.
- L'insieme dei simboli non terminali $V_N$ è costituito da tutte le triple $[q_{i},\: A, \:q_j]$ per ogni $q_i,\: q_j \in Q$ e per ogni simbolo di pila $A \in \Gamma$.
- L'assioma $S$ della grammatica ha le seguenti derivazioni, per ogni $q \in Q$ abbiamo la produzione $S\to[q_0,Z_0,q]$ dove $Z_0$ è il simbolo iniziale sulla pila e $q_0$ è lo stato iniziale
- Per ogni $q\in Q,\:A\in\Gamma,\:a\in\Sigma\cup\{\varepsilon\}$, se $(q',\varepsilon)\in\delta(q,a,A)$ allora $[q,A,q']\to a\in P$
- Per ogni $q \in Q,\: A \in \Gamma, a \in \Sigma \cup \{\epsilon \}$, se $(q',B_1...B_m)\in\delta(q,a,A)$, allora $[q,A,q_{i_m}]\to a[q'B_1q_{i_1}][q_{i_1}B_2q_{i_2}]...[q_{i_m-1}B_mq_{i_m}]\in P$ 
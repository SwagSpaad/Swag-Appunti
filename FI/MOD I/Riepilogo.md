
Grammatica tipo 0: sono quelle in cui la parte sinistra di una produzione ha almeno un simbolo non terminale ($\alpha \in V^{*} \circ V_{N} \circ V^{*}$) mentre la parte destra ha un qualsiasi simbolo o $\epsilon$ ($\gamma \in V^*$) ^432bae
 
Gramatica tipo 1 **Context sensitive**: sono quelle in cui la parte sinistra di una produzione ha almeno un simbolo non terminale ($\alpha \in V^{*} \circ V_{N} \circ V^{*}$) mentre la parte destra ha almeno un simbolo tale che la forma di frase ha lunghezza almeno quella di $\alpha$ ($\gamma \in V^{+} t.c. |\alpha| \leq |\gamma|$) ^da2967

Grammatica tipo 2 **Context free**: sono quelle in cui la parte sinistra di una produzione è un simbolo non terminale ($A \in V_{N}$) mentre la parte destra ha almeno un simbolo terminale o non terminale ($\gamma \in V^+$) ^75297e

###### Grammatica tipo 3:
- **Lineari destre**: sono quelle in cui la parte sinistra di una produzione è un simbolo non terminale ($A \in V_{N}$), mentre la parte destra è la concatenzaione di un simbolo terminale con un non terminale oppure un simbolo terminale ($\gamma \in (V_{T} \circ V_{N}) \cup V_{T}$)
- **Lineari sinistre**: la parte destra è la concatenzaione di un simbolo non terminale con un terminale oppure un simbolo terminale ($\gamma \in (V_{N} \circ V_{T}) \cup V_{T}$)

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

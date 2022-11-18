# State elimination

Procedura iterativa di eliminazione degli stati su un automa non deterministico **generalizzato** equivalente, in cui:
1. La funzione di transizione è definita su $Q\times E$, dove $E$ è l'insieme delle espressioni regolari su $\Sigma$, per cui gli archi sono etichettati con e.r
2. Lo stato inziale non ha archi entranti, per cui : $\not\exists q\in Q,e\in E : q_0\in\delta_N(q,e)$
3. Esiste un solo stato finale $q_F$ senza archi uscenti, per cui: $\not\exists e\in E:\delta_N(q_F,e)\neq\emptyset$

Dato un qualunque automa $\mathcal A$ non deterministico, un automa **generalizzato** $\mathcal A'$ equivalente può essere immediatamente ottenuto:
1. mantenendo gli stati di $\mathcal A$
2. introducendo, per ogni arco del grafo di transizione di $\mathcal A$ etichettato con l'insieme $a_1,...,a_k$, un arco nel grafo di transizione di $\mathcal A'$ etichettato $a_1+...+a_k$
3. Se lo stato iniziale $q_0$ di $\mathcal A$ ha archi entranti, introducendo in $\mathcal A'$ un nuovo stato iniziale $\overline q_0$ senza archi entranti, e la $\varepsilon$-transizione $\delta_N'(\overline q_0,\varepsilon)=\lbrace q_0\rbrace$
4. se esistono più stati finali in $F$, o se il solo stato finale ha archi uscenti, introducendo un ulteriore stato $q_F$, ponendo $F'=q_F$ e introducendo la $\varepsilon$-transizione $\delta_N'(\overline q_0,\varepsilon)=\lbrace q_F\rbrace$ per ogni $q\in F$

- Dato un automa non deterministico (con $\varepsilon$-transizioni) $\mathcal A$ con insieme di stati $Q$, e dato uno stato $q$ non iniziale né finale, è possibile ottenere un automa **generalizzato** equivalente $\mathcal A'$ con stati $Q−\{q\}$ effettuando una opportuna operazione di **eliminazione dello stato**
- L'eliminazione dello stato viene effettuata considerando tutti i possibili cammini di lunghezza 3 passanti per $q$ (sequenze $q_i,q,q_j$ per le quali esistono archi da $q_i$ a $q$ e da $q$ a $q_j$)
- Per ogni cammino, le etichette degli archi interessati vengono modificate come mostrato di seguito
- Al termine, rimangono lo stato iniziale e quello finale, collegati da un arco, la cui etichetta fornisce l'espressione regolare cercata

Infatti, avremo una situazione del genere
![[appunti fi/immagini/Pasted image 20221114124724.png|center|500]]

Le espressioni regolari possono essere comunque complesse
![[appunti fi/immagini/Pasted image 20221114125012.png|center|600]]

In effetti, se esistono n cammini $q_iq_jq_h(h=k_1,...,k_n)$, allora si ha che:
$$\overline r_{ik}=r_{ik_1}+r_{ij}r_{jj}^\star r_{jk_1}+r_{ik_2}+r_{ij}r_{jj}^\star r_{jk_2}+...+r_{ik_n}+r_{ij}r_{jj}^\star r_{jk_n}$$
lo stesso vale per $\overline r_{ki}$

# Chiusura dei linguaggi regolari

## Proprietà
Dato l’insieme dei linguaggi regolari, ci chiediamo quali siano le sue proprietà di chiusura rispetto avarie operazioni.
Ci chiediamo quindi se, data una qualunque operazione $\odot$:

- se $\odot$ è unaria, $\odot L$ è un linguaggio regolare per ogni $L$ regolare
- se $\odot$ è binaria, $L_1\odot L_2$ è un linguaggio regolare per ogni coppia $L_1,L_2$ di linguaggi regolari
- non consideriamo operatori di "arietà" maggiori

Le dimostrazioni di tali proprietà presentano tutte uno stesso schema, in cui, a partire dagli ASFDche riconoscono i linguaggi regolari dati, viene derivato un automa (deterministico o nondeterministico) che riconosce il linguaggio risultante.

## Unione

# Lezione 4
## Grammatiche equivalenti, tipolgie di grammatiche. 
---
## Equivalenza tra grammatiche
Due grammatiche $\mathcal G_1\:e\:\mathcal G_2$ si dicono **equivalenti** se generano lo stesso linguaggio, vale a dire se $L(\mathcal G_1)=L(\mathcal G_2)$

**Esercizio**
dimostrare che la grammatica con produzioni $S\to aS|b$ e la grammatica con produzioni $S\to b|Ab\:\:A\to Aa|a$ sono equivalenti

## Tipologie di grammatiche
### Grammatiche di tipo 0
**Def.**
Le grammatiche di tipo 0, dette anche **non limitate**, definiscono la classe dei linguaggi più ampia possibile.
In esse le produzioni sono del tipo generale 
$$\alpha\to\beta,\alpha\in V^\star\circ V_N\circ V^\star,\beta\in V^\star$$

Queste grammatiche ammettono anche derivazioni che "accorciano" le forme di frase, come ad esempio quelle che si ottengono applicando le $\epsilon$-produzioni.
I linguaggi generabili da grammatiche di tipo 0 si dicono **linguaggi di tipo 0**.

**Esempio**
La gramamtica $$\mathcal G=\langle\lbrace a,b\rbrace,\lbrace S,A\rbrace,P,S\rangle$$
Le cui produzioni $P$  è 
1. $S\to aAb$
2. $aA\to aaAb$
3. $A\to\epsilon$

è una grammatica di tipo 0 e genera il linguaggio $L=\lbrace a^nb^n|n\geq1\rbrace$
Infatti, una derivazione possibile è:
$$S=aAb=aaAbb=aabb\to(a^2b^2)$$

### Grammatiche di tipo 1 (Contex Sensitive)
**Def.**
Dette anche **contestuali** o **context sensitive** (CS), ammettono qualunque regola di produzione che non riduca la lunghezza delle stringhe, cioè tutte le produzioni del tipo:
$$\alpha\to\gamma,\alpha\in V^\star\circ V_N\circ V^\star,\gamma\in V^+,|\alpha|\leq|\gamma|$$
I linguaggi generabili da grammativche di tipo 1 si dicono **linguaggi di tipo 1, contestuali, o context sensitive**.

**Oss.** 
Nell'esempio di prima, la grammatica è di tipo 0 ma non di tipo 1 perchè la produzione $A\to\epsilon$ accorcia (infatti |A|=1 mentre |$\epsilon$|=0), e quindi non vale la regola per le grammatiche di tipo 1.
**Oss.** 
Un linguaggio generato da una grammatica di tipo 1 è generato anche da una grammatica di tipo 0 (grammatica di tipo 1 $\subset$ grammatica di tipo 0)

**Esempio**

le produzioni 
1. $S\to aSa\:|\:aAb\:|\:aAa$
2. $aA\to aa$
3. $Ab\to aab$

appartengono ad una grammatica di tipo 1 (perchè nessuna delle produzioni accorcia, al più mantengono tutte la stessa lunghezza).

**Esempio**
Il linguaggio $L=\lbrace a^nb^n|n\geq1\rbrace$ visto prima, può essere generato da una gramamtica di tipo 1 avente le seguenti produzioni:
1. $S\to aBS\:|\:ab$
2. $Ba\to aB$
3. $Bb\to bb$

dunque il linguaggio è contestuale.

#### Definizione alternativa

Il termine "linguaggio contestuale" deriva dal fatto che sono generabili da gramamtiche aventi produzioni "contestuali" del tipo $$\beta_1A\beta_2\to \beta_1\gamma\beta_2,\:A\in V_N,\:\beta_1,\:\beta_2\in V^\star,\gamma\in V^+$$
in cui si esprime il fatto che A può essere sostituito da $\gamma$ in una forma di frase solo se si trova nel contesto $\langle \beta_1,\beta_2\rangle$ 

### Grammatiche di tipo 2 (Context Free)
**Def.**
Dette anche **non contestuali** o **contex free** (CF), ammettono solo produzioni del tipo:
$$A\to\beta,A\in V_N,\:\beta\in V^+$$
cioè produzioni in cui ogni non terminale A può essere riscritto in una stringa $\beta$ indipendentemente dal contesto in cui esso si trova

**Esempio**
Grammatica che genera espressioni aritmetiche di somme e moltiplicazioni in una variabile i:

1. $E\to E+T\:|\:T$
2. $T\to T\cdot F\:|\:F$
3. $F\to i\:|\:(E)$

**Esercizio**
Generare la stringa $(i+i)\cdot i$ utilizando la gramamtica dell'esempio precedente

**Oss.** 
Il linguaggio visto prima $L=\lbrace a^nb^n|n\geq1\rbrace$ può essere generato dalla grammatica di tipo 2 con produzioni 

1. $S\to aSb|ab$

Dunque il linguaggio in questione è context free.

### Grammatiche di tipo 3

Dette anche **lineari destre o regolari**, ammettono solo produzioni del tipo:
$$A\to\delta,A\in V_N,\delta\in (V_T\circ V_N)\cup V_T$$
il termine "regolare" deriva dal fatto che i corrispondenti linguaggi sono rappresentabili per mezzo di espressioni regolari.

I linguaggi generabili da queste gramamtiche vengono detti **linguaggi di tipo 3 o regolari**.

**Esempio**
la grammatica $\mathcal G=\langle\lbrace a,b\rbrace,\lbrace S\rbrace,P,S\rangle$
in cui P contiene le produzioni 
1. $S\to aS\:|\:b$
è una grammatica di tipo 3 e genera il linguaggio regolare $L=\lbrace a^nb|n\geq0\rbrace$

**Esercizio**
Definire una grammatica regolare per il linguaggio delle stringhe sull'alfabeto $\lbrace a,b\rbrace$ che contengono un numero dispari di a.

#### Grammatiche lineari sinistre

Sono caratterizzate da regole del tipo:
$$A\to\delta,A\in V_N,\delta\in (V_N\circ V_T)\cup V_T$$
le grammatiche sinistre differiscono dalle grammatiche lineari destre in quanto l'unico simbolo non terminale che può comparire nella parte destra $\delta$ della produzione appare come primo simbolo.

## Gerarchia di Chomsky
Per ogni $0\leq n\leq 2$, ogni grammatica di tipo n+1 è anche di tipo n: pertanto l'insieme dei linguaggi di tipo n contiene tutti i linguaggi di tipo n+1, formando quindi una gerarchai, detta **Gerarchia di Chomsky**

![[FI/img/img5.png|center|200]]

## Grammatiche con $\epsilon$-produzioni
Dalle definizioni, non è possibile generare la stringa vuota con grammatiche di tipo 1, 2, 3.
Linguaggi contenenti $\epsilon$ possono però essere generati apportando lievi modifiche (aggiunta di opportune $\epsilon$-produzioni) alle grammatiche non contestuali e regolari

Se una grammatica $\mathcal G=\langle V_T,V_N,P,S\rangle$ di tipo 1, 2 o 3 genera un linguaggio L, per poter generare il linguaggio $L\cup\epsilon$ è sufficente utilizzare la gramamtica
$$\mathcal G'=\langle V_T,V_N\cup\lbrace S'\rbrace,P',S'\rangle$$
dove $$P'=P\cup\lbrace S'\to\epsilon\rbrace\cup\lbrace S'\to S\rbrace$$
**Esempio**
Data la grammatica [[#Grammatiche di tipo 1 (Contex Sensitive)|CS]] $\mathcal G$, con assioma S e produzioni:
1. $S\to aBSc\:|\:abc$
2. $Ba\to aB$
3. $Bb\to bb$

risulta $L(\mathcal G)=\lbrace a^nb^nc^n|n\geq1\rbrace$
Il linguaggio $L(\mathcal G)\cup\epsilon=\lbrace a^nb^nc^n|n\geq0\rbrace$ è derivato dalla grammatica $\mathcal G'$ con assioma $S'$ e produzioni:
1. $S'\to S\:|\:\epsilon$
2. $S\to aBSc\:|\:abc$
3. $Ba\to aB$
4. $Bb\to bb$

**Oss**
Aggiungere la $\epsilon$-produzione direttamente come produzione dell'assioma può avere effetti indesiderati

Infatti:
1. $S\to Ub$
2. $U\to ab\:|\:S$
genera $ab^\star bb$, ma:
1. $S\to Ub\:|\:\epsilon$
2. $U\to ab|S$
genera $ab^\star bb\cup\lbrace\epsilon\rbrace\cup b^\star b$ 

### Grammatiche di tipo 1 e $\epsilon$-produzioni

L'aggiunta non controllata di $\epsilon$-produzioni può aumentare in modo sostanziale il potere generativo della grammatica

>**Teorema** 
>Data una grammatica $\mathcal G=\langle V_T,V_N,P,S\rangle$ di [[Riepilogo#^432bae|tipo 0]], esiste una grammatica di tipo $\mathcal G'$ equivalente a $\mathcal G$, ottenuta estendendo una grammatica di [[Riepilogo#^da2967|tipo 1]] con opportune $\epsilon$-produzioni

La grammatica $\mathcal G'=\langle V_T',V_N',P',S'\rangle$ è caratterizzata da: $V_T'=V_T,V_N'=V_N\cup\lbrace X\rbrace$,con $X\not\in V_N,S'=S\:e\:P'$ ottenuto da $P$ aggiungendo la produzione $X\to\epsilon$ e sostituendo ad ogni produzione $\phi\to\psi$, con $|\phi|\gt|\psi|\gt 0$ la produzione:
$$\phi\to\psi\:\:\underbrace {X...X}_{|\phi|-|\psi|}$$
è semplice verificare che con la grammatica $\mathcal G'$ sono derivabili tutte e sole le stringhe di $V_T^\star$ derivabili con la grammatica $\mathcal G$.

**Esempio**
1. $AB\to A$
nella nuova grammatica abbiamo $AB\to AX$ e $X\to\epsilon$
Avendo una forma di frase del tipo $CaABBC$ e applicando la produzione 1 avremo $CaABC$.
Mentre se applicassimo la produzione con X avremo la stringa $CaABBC\to CaAXBC\to CaABC$.
Il risultato è equivalente.

Questo ci dice che introdurre le $\epsilon$-produzioni in una grammatica [[Riepilogo#^da2967|CS]] è qualcosa che in realtà ci porta fuori dalla classe delle grammatiche, e quindi otterremo l'effetto delle grammatiche di tipo 0.
Sostanzialmente possiamo dire che se aggiungiamo una $\epsilon$-produzione in una grammatica di tipo 1 (CS), allora poi otterremo una grammatica di tipo 0.

### Grammatiche di tipo 2 o 3 con $\epsilon$-produzioni

L'aggiunta indiscriminata di $\epsilon$-produzioni non altera il potere generativo delle grammatiche.
Si può dimostrare che data una gramamtica di [[Riepilogo#^75297e|tipo 2]] o [[Riepilogo#Grammatica tipo 3|3]] estesa con $\epsilon$-produzioni, ne possiamo sempre costruire una qeuivalente, dello stesso tipo, che usa $\epsilon$-produzioni solo a partire dall'assioma (nel caso in cui $\epsilon$ appartenga al linguaggio da generare) o non ne usa affatto (in caso contrario).

>**Teorema**
>Data una grammatica $\mathcal G=\langle V_T,V_N,P,S\rangle$ il cui insieme di produzioni P comprende soltanto produzioni di tipo [[Riepilogo#^75297e|non contestuale]] e produzioni vuote, esiste una grammatica non contestuale $\mathcal G'$ tale che $L(\mathcal G')=L(\mathcal G)-\lbrace\epsilon\rbrace$

Derivazione di $\mathcal G'$ da $\mathcal G$
1. determinazione dei simboli che si annulalno, cioè i non terminali da cui è possibile derivare $\epsilon$
2. per ogni produzione $A\to\alpha$ di P, con l'esclusione delle $\epsilon$-produzioni, se nessun simbolo di $\alpha$ si annulla $A\to\alpha$ va in in $P'$; altrimenti a $P'$ si aggiungono tutte le possibili produzioni ottenute da $A\to\alpha$ eliminando da un $\alpha$ un sottoinsieme dei simboli che si annullano, considerati con la propria molteplicità

## Forma normale di Backus

Le gramamtiche formali sono utilizzate per la definizione di linguaggi di programmazione, caratterizzati come l'insieme di tutte le stringhe (programmi) derivabili dalla grammatica.

Per la definizione sintattica dei linguaggi di programmazione vengono adottate grammatiche non contestuali, tradizionalmente rappresentate mediante una notazione specifica, particolarmente suggestiva, denominata **Forma Normale di Backus (Backus Normal Form**, BNF, detta anche **Backus-Naur Form)**

Notazione per grammatiche context free resa più espressiva e succinta:
1. I simboli non terminali costituiti da stringhe (ad es.: $<espressione>$);
2. $\to$ sostituito da $\coloneqq$   
3. Parentesi graffe $\lbrace...\rbrace$ impiegate per indicare lìiterazione illimitata. Con $\lbrace...\rbrace^n$ si indica l'iterazione per un numero di volte pari al più ad n
4. Parentesi quadre $[...]$ utilizzate per indicare l'opzionalità (possibile assenza di una parte di stringa)
5. Parentesi tonde $(...)$ utilizzate per indicare la fattorizzazione, vale a dire la messa in comune di una sottoespressione

Identificatore:
1. $<id>\:::=\:<alfa>\lbrace <alfanum>\rbrace^{n-1}$
2. $<alfanum>\:::=\:<alfa>|<cifra>$
3. $<alfa>\:::=\:A\:|\:B\:|\:...\:|\:Z\:|\:a\:|\:b\:|\:...\:|\:z$
4. $<cifra>\:::=\:0|:|\:1\:|\:...\:|\:9$ 

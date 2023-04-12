----
# Sistemi assiomatici
Un sistema formale consiste in schemi di assiomi e regole di inferenza, oltre che dall'insieme di simboli che vengono usati nelle definizioni che stabiliscono quali sequenze di simboli sono "formule". Nel caso della logica proposizionale gli schemi di assiomi sono un insieme di [[Lezione 3 - Introduzione alla logica proposizionale#^65fc78|formule ben formate]] e le regole di inferenza, sono relazioni di formule di questo tipo "dalle formule $X_1, \: \dots, X_n$ segue la formula $Y$".
Vediamo un esempio considerando questi due assiomi:
- $A_{1}\: : \: X \implies (Y \implies X)$
- $A_{2}\: : \: (X \implies (Y \implies Z)) \implies ((X \implies Y)\implies Z)$

La regola di inferenza che usiamo si chiama **Modus Ponens**: "Dalle formule $X$ e $X \implies Y$ segue la formula $Y$".  In simboli si indica così:

![[LRL/img/img15.png|center|300]]

Nella lezione con il simbolo $\mathcal S_{0}$ indicheremo il sistema assiomatico costituito dagli assiomi $A_{1}$ e $A_2$ e dalla regola di inferenza Modus Ponens.

>**Def.**
>Diciamo che una formula $\mathcal F$ è un istanza di un assioma, se si ottiene da uno schema di assioma, sostituendo ad ogni lettera dello schema una formula. 

Per esempio la formula $(q \implies r)\implies(p\implies(q \implies r))$ è un istanza dell'assioma $A_1$, perché si ottiene da $A_1$ sostituendo $(q \implies r)$ alla lettera $X$ e $p$ alla lettera $Y$.

# Teoremi e dimostrazioni
>**Def.** (dimostrazione)
>In un sistema assiomatico $\mathcal S$, una dimostrazione è una sequenza di formule $\mathcal F_{1},\:\dots,\:\mathcal F_n$ tale che ogni formula $\mathcal F_i$ o è un istanza dell'assioma, oppure si ottiene dalle formule precedenti della sequenza tramite una regola di inferenza

^3b63aa

>**Esempio**
>Consideriamo il nostro sistema $\mathcal S_0$. Nel seguito indicheremo con M.P. la regola di inferenza Modus Ponens.
>
>![[LRL/img/img16.png|center|500]]
>
>La sequenza di formule 1, 2, e 3 qui sopra è una dimostrazione secondo la [[#^3b63aa|definizione]]. Infatti, le formule 1 e 2 sono istanza di assiomi e la formula 3 si ottiene dalle due formule precedenti usando la regola di inferenza M.P., dove poniamo $X = (p \implies (q \implies p))$ e $Y = (p \implies q) \implies (p\implies p)$ 

>**Def.** (teorema)
>In un sistema assiomatico un teorema è l'ultima formula di una dimostrazione.

# Derivazioni e Teorema di Deduzione
Un concetto che estende quello di dimostrazione è quello che chiamiamo derivazione.

>**Def.** (derivazione)
>Sia $\mathcal S$ un sistema assiomatico, sia $\mathcal F$ una formula e $\mathcal T$ un insieme di formule. Diciamo che  $\mathcal F$ deriva da $\mathcal T$ nel sistema $\mathcal S$ se esiste una sequenza di formule $\mathcal F_{1},\:\dots,\: \mathcal F_n$ e ognuna delle $\mathcal F_i$ con $i = 1, \:\dots,\: n$ o è un istanza di un assioma o si ottiene dalle formule precedenti della sequenza tramite una regola di inferenza, oppure è una delle formula dell'insieme $\mathcal T$. La sequenza $\mathcal F_1,\:\dots,\:\mathcal F_n$ si chiama **derivazione** di $\mathcal F$ da $\mathcal T$. Le formule in $\mathcal T$ sono le ipotesi della derivazione.

Introduciamo dei simboli. Quando una formula $\mathcal F$ deriva da un insieme $\mathcal T$ in un sistema assiomatico $\mathcal S$, scriviamo $\mathcal T \vdash_{\mathcal S}\mathcal F$. Quando il sistema $\mathcal S$ di cui stiamo parlando è chiaro dal contesto lo omettiamo e scriviamo semplicemente $\mathcal T \vdash \mathcal F$. 

**Esempio**
Consideriamo sempre il nostro sistema assiomatico $\mathcal S_0$ e facciamo vedere che la formula $p \implies r$ deriva dalle formule $p\implies q$ e $q\implies r$; in simboli: $$p\implies q,\: (q \implies r) \vdash (p \implies r)$$
Chiamiamo $p\implies q$ e $q\implies r$ rispettivamente *ipotesi1* e *ipotesi2*.

![[LRL/img/img17.png|center|600]]

La sequenza di formule $1, ..., 7$ in figura è una derivazione della formula $p \implies r$ dall'insieme di formule $\{p\implies q,\: q \implies r\}$. Le formule 1 e 2 sono istanze di assiomi, 3 e 6 sono le ipotesi, 4, 5 e 7 seguono da formule precedenti tramite M.P.

**Oss.**
Se confrontiamo le definizioni di dimostrazione e teorema con la definizione di derivazione, osserviamo che una dimostrazione di $\mathcal F$ è una derivazione di $\mathcal F$ con $\mathcal T = \emptyset$. Per indicare che una formula $\mathcal F$ è un teorema nel sistema $\mathcal S$ perciò scriveremo $\vdash \mathcal F$.

>**Teorema** di deduzione
>Sia $\mathcal T$ un insieme di formule e siano $\mathcal F$ e $\mathcal G$ due formule. Nel sistema $\mathcal S_0$ se $\mathcal T \cup \{\mathcal F\}\vdash \mathcal G$ allora $\mathcal T \vdash \mathcal F \implies \mathcal G$ 

# Conclusioni
Nella lezione abbiamo introdotto i sistemi assiomatici per la logica proposizionale. 
Osserviamo che in un qualunque sistema assiomatico in cui gli schemi di assiomi sono tautologie e la regola di inferenza e M.P., tutti i teoremi sono tautologie. Il nostro sistema $\mathcal S_0$ quindi è corretto, ma è anche **completo**? Così come lo abbiamo definito non lo è, ma basta aggiungere uno schema di assioma per renderlo completo, come ad esmpio: $$A_{3}\: : \: (\lnot X \implies \lnot Y) \implies ((\lnot X \implies Y)\implies X)$$
Se chiamiamo $\mathcal S_1$ il sistema assiomatico formato dagli assiomi $A_{1}, \: A_{2},\: A_{3}$ e dalla regola di inferenza M.P., si può dimostrare che ogni tautologia è un teorema nel sistema $\mathcal S_1$.


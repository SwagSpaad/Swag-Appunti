----
La logica proposizionale è un linguaggio i quali elementi sono le proposizioni e le relazoni fra esse.

# Lettere proposizionali, connettivi e tabelle di verità
Indichiamo con $p,\: q, \:r, ...$  delle variabili booleane, ossia variabili che assumono valori true o false.
A partire dalle variabili, che chiamiamo lettere proposizionali, possiamo costruire delle formule più complesse utilizzando i connettivi.
Data una variabile $p$, indichiamo con $\lnot{p}$, la formula che è T $\iff$ $p$ è F (**negazione**).
Indichiamo con $p \lor q$  la formula che è T  $\iff$ una fra $p$ e $q$ è T (**or, disgiunzione**).
Indichiamo con $p \land q$ la formula che è T quando $p$ e $q$ sono entrambe T (**and, congiunzione**).

![[LRL/img/img0.png|center|400]]

Esistono anche altri connettivi come l'implicazione ($\implies$) e l'equivalenza ($\equiv$ )

![[LRL/img/img1.png|center|400]]

# Formule ben formate
Ad ogni formula della logica proposizionale, possiamo associare una tabella di verità. Per esempio, la formula $p \land (q\lor \lnot{r})$ vale T $\iff$  $p$ è T e ($q$ è T oppure $r$ è F). 
La tabella di verità sarà la seguente:

![[LRL/img/img2.png|center|400]]

**Def.** Formule ben formate
Le lettere proposizionali sono formule ben formate (abbreviato f.b.f.). Inoltre:
1. se $\mathcal F$ è una f.b.f allora anche $\lnot \mathcal F$ è una f.b.f.
2. se $\mathcal F$ e $\mathcal G$ sono f.b.f allora anche $\mathcal F \circ \mathcal G$ è una f.b.f., dove con $\circ$ abbiamo indicato uno qualunque dei connettivi $\land,\lor, \implies, \equiv$.
3. Nient'altro è una f.b.f.

# Tautologie, contraddizioni e contingenze
Data una formula, chiamiamo **interpretazione** della formula un'assegnazione di verità alle sue variabili.

>**Oss.** in una tabella di verità, ogni riga rappresenta una diversa interpretazione.

Data una formula $\mathcal F$ e una sua interpretazione $\tau$, la formula $\mathcal F$ è o T o F nell'interpretazione $\tau$. Per esempio la formula $$(p \lor q)\land \lnot r$$
è F nell'interpretazione $(p,\: q,\: r) = (T,\: F,\: T)$ mentre è T nell'interpretazione $(p,\: q,\: r) = (T,\: F,\: F)$.
- Si chiamano **tautologie** le formule che sono T in ogni interpretazione.
- Si chiamano **contraddizioni** le formule che sono F in ogni interpretazione.
- Si chiamano **contingenza** le formule che sono T in alcune interpretazioni e F in altre.

>**Oss.** 
>- $\mathcal F$ è una tautologia $\iff$ $\lnot \mathcal F$ è una contraddizione E viceversa.

Diciamo che due formule sono **equivalenti** se hanno la stessa tabella di verità. 

>**Oss.** Due formule $\mathcal F$ e $\mathcal G$ sono equivalenti $\iff$ $\mathcal F \equiv \mathcal G$ è una tautologia

# Costanti
Abbiamo indicato le lettere proposizionali $p,\: q, \:r, ...$  per indicare delle variabili booleane. Aggiungiamo due lettere proposizionali **t** e **f**, per indicare le due **costanti** Booleane **T** e **F**.

>**Oss.**
>- p $\land$ **f** è equivalente ad **f** e p $\land$ **t** è equivalente a p.
>- p $\lor$ **f** è equivalente ad **p** e p $\lor$ **t** è equivalente a **t**.
>- **f** $\land$ **t** è equivalente ad **f** e **f** $\lor$ **t** è equivalente a **t**

Ogni formula che contiene **t** e/o **f** è sempre equivalente ad una formula che non contiene né **t** né **f** oppure sono uguali o a **t** o a **f**.

# Interdipendenza dei connettivi
Nelle fomule abbiamo usato i connettivi $\lnot, \land, \lor, \implies$ e $\equiv$. Osserviamo che non tutti questi sono "necessari", perché ad esempio:
- $p \implies q$ è equivalente a $\lnot p \lor q$, diciamo che in questo caso il connettivo $\implies$ può essere definito in termini dei connettivi $\lnot$ e $\lor$
- $p \land q$ è equivalente a $\lnot(\lnot p \lor \lnot q)$ quindi anche $\land$ può essere definito in termini di $\lnot$ e $\lor$
- $p \equiv q$ è equivalente a $(p \implies q) \land (q \implies p)$ o anche a $(p \land q) \lor (\lnot p \land \lnot q)$ e siccome $\implies$ e $\land$ possono essere definiti in termini di $\lnot$ e $\lor$, anche $\equiv$ può essere definitio in termini di $\lnot$ e $\lor$
Quinid potremmo riscrivere tutte le formule viste finora usando soltanto i due connettivi $\lnot$ e $\lor$. In modo analogo potremmo farlo usando i due connettivi $\lnot$ e $\land$.

Esiste un connettivo che utilizzato da solo puo definire tutti gli altri?
Consideriamo il connettivo seguente, che chiamiamo **joint denial** (NOR in termini di circuiti)

![[LRL/img/img3.png|center|250]]
**Oss.**
- $p \downarrow p$ è **F** quando $p$ è **T** ed è **T** quando $p$ è **F**. In altri termini $p \downarrow p$ è equivalente a $\lnot p$
- La tabella di verità di $\downarrow$ è la negazione di quella di $\lor$, ossia $p\downarrow q$ è equivalente a $\lnot(p\lor q)$. Ma allora $p \lor q$ deve essere equivalente a $\lnot(p\downarrow q)$, che a sua volta, per il punto precedente deve essere equivalente a $(p \downarrow q)\downarrow(p \downarrow q)$.

Siccome possiamo definire i connettivi $\lnot$ e $\lor$ in termini del connettivo $\downarrow$  per quanto visto  
all’inizio di questa sezione, possiamo definire anche tutti gli altri connettivi in termini del  
connettivo $\downarrow$.  
Oltre a $\downarrow$, c’è un altro connettivo che, da solo, può essere usato per definire tutti gli  
gli altri: lo indichiamo con $|$ e lo chiamiamo **alternative denial** (oppure nand). 

![[LRL/img/img4.png|center|250]]

# Notazione polacca
Se scriviamo $[\implies pq]$ al posto di $[p \implies q]$, $[\land pq]$ al posto di $[p \land q]$, $[\lor pq]$ al posto di $[p \lor q]$, e $[\equiv pq]$ al posto di $[p \equiv q]$, è possibile scrivere ogni formula ben formata in modo non  ambiguo senza l’utilizzo di parentesi. Questo tipo di sintassi è detta **notazione polacca**.  
Ad esempio, la formula $\lnot p ∧ (q \implies \lnot r)$ in notazione polacca diventa $\land \lnot p \implies q\: \lnot r$.

**Def.** Formule ben formate in notazione polacca
Ogni lettera proposizionale è una f.b.f. Inoltre:
- Se $\mathcal F$ è una f.b.f. allora $\lnot \mathcal F$ è una f.b.f.
- Se $\mathcal F$ e $\mathcal G$  sono f.b.f. allora anche $\circ \mathcal F\mathcal G$ è una f.b.f., dove con il simbolo $\circ$ intendiamo un qualsiasi connettivo binario.
- Nient'altro è una f.b.f.

# Riepilogo 
In questo episodio abbiamo iniziato a studiare la logica proposizionale, introducendone la sintassi (ossia, qual è la “forma” degli oggetti che studiamo: lettere proposizionali, connettivi, formule ben formate) e la semantica (ossia, qual è il loro “significato”: tabelle di verità, interpretazioni). Abbiamo classificato le formule della logica proposizionale in tautologie, contingenze e contraddizioni. Abbiamo osservato che i connettivi non sono indipendenti fra loro e ne abbiamo individuato due “speciali”. Infine abbiamo accennato alle formule in notazione polacca e ne abbiamo visto qualche proprietà.

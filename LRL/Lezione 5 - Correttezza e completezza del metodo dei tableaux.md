----
# Insiemi soddisfacibili e correttezza del metodo
Diciamo che $\mathcal F$ è **soddisfacibile** se esiste una interpretazione in cui è T.

**Oss.**
Una formula è soddisfacibile $\iff$ è una tautologia o contingenza.

DIciamo che un insieme di formule S è soddisfacibile, se esiste una interpretazione in cui tutte le formule di  S sono T

Abbiamo visto col metodo dei tableaux che una formula $\mathcal F$ può essere di due tipi: una $\alpha$-formula o una $\beta$-formula

![[LRL/img/img11.png|center|500]]

Se $\mathcal F$ è una $\alpha$-formula (rispettivamente, $\beta$-formula) chiamiamo le formule $\alpha_{1}$ e $\alpha_{2}$ (risp. $\beta_{1}$ e $\beta_{2}$) le **componenti** della formula $\mathcal F$.
Ricordiamo che con il metodo dei tableaux quello che facciamo è costruire una sequenza $T_0, \: T_1, \: T_{2}, \dots$ di tableaux, in cui ogni $T_i$ è ottenuto estendendo il precendente $T_{i-1}$ tramite una regola $\alpha$ o $\beta$ 

![[LRL/img/img12.png|center|300]]

a seconda che la formumla che si sta espandendo sia una $\alpha$-formula o una $\beta$-formula.
Per esempio, con riferimento al caso visto in dettaglio nella [[Lezione 4 - Il metodo dei Tableaux per la logica proposizionale|lezione 4]], il primo tableaux $T_0$ è la [[Lezione 4 - Il metodo dei Tableaux per la logica proposizionale#^b44b98|negazione]] di $\mathcal F$. Siccome $T_0$  è una $\alpha$-formula, $T_1$ è ottenuto estendendo $T_0$ tramite una regola $\alpha$.

![[LRL/img/img13.png|center|400]]

Siccome la 2 è una $\beta$-formula $T_2$ è ottenuto estendendo $T_{1}$ tramite una regola $\beta$.

![[LRL/img/img14.png|center|400]]

Ad ogni ramo $\theta$ del tableaux possiamo associare l'insieme $S_{\theta}$ di tutte le formule che compaiono su quel ramo. Per esempio, nel tableau sopra ci sono due rami: uno contiene le formule 1, 2, 3, 4 e l'altro contiene le formule 1, 2, 3 e 5.
Diciamo che un ramo $\theta$ è soddisfacibile se l'insieme $S_{\theta}$ delle sue formule è soddisfacibile.
Diciamo che un tableau T è soddisfacibile, se almeno uno dei suoi rami è soddisfacibile.

Diciamo che un ramo è **chiuso** se contiene sia una formula $\mathcal F$, sia la sua negata $\lnot \mathcal F$. In caso contrario diremo che è **aperto**. Diciamo che un tableau è chiuso se tutti i suoi rami sono chiusi.

Ricordiamo che una formula $\mathcal F$ si dice dimostrabile con il metodo dei tableaux se c'è un tableau T chiuso per $\lnot \mathcal F$. In questo caso diremo che anche il tableau T è una dimostrazione di $\mathcal F$ e che $\mathcal F$ è un teorema nel sistema dei tableaux.

A questo punto possiamo facilmente dimostrare che ogni formula della logica proposizionale dimostrabile con il metodo dei tableaux è una tautologia.

>**Teorema** (correttezza)
>Se $\mathcal F$ è dimostrabile con il metodo dei tableaux allora $\mathcal F$ è una tautologia.

**Dim.**
Se $\mathcal F$ non fosse una tautologia, allora $\lnot \mathcal F$ sarebbe soddisfacibile. Quindi ogni tableau che si ottiene da $\lnot \mathcal F$ dovrebbe essere soddisfacibile. Ma questo è assurdo, perché per ipotesi $\mathcal F$ è dimostrabile col metodo dei tableaux, quindi c'è un tableau chiuso per $\lnot \mathcal F$. 

# Insiemi di Hintikka e completezza del metodo


*****
# Teorema di Cook-Levin e la struttura di NP

Partiamo dalla domande precedente:
**Fra i problemi in $NP$ che non si riesce a collocare in $P$, ce ne sono alcuni più "difficili" rispetto ad altri?**

Il *Teorema di Cook-Levin* ci dice che $NP$ contiene un problema $NP-$completo ($SAT$), e dato che sappiamo che i problemi completi per una classe sono i problemi più "difficili" fra i problemi in quella classe, il *Teorema di Cook-Levin* ci dice che $SAT$ è uno dei problemi più difficili in $NP$, perchè sappiamo che se $SAT$ appartenesse a $P$, allora anche ogni altro problema in $NP$ apparterrebbe a $P$, perchè ricordiamo, $P$ è chiuso rispetto alla riducibilità polinomiale.
Ma il *Teorema di Cook-Levin* ci dice molto di più!

# Il Teorema e la congettura
Sappiamo della congettura $P\neq NP$
Bene, arriva qualcuno e dimostra che $P = NP$ e lo fa **descrivendo un algoritmo deterministico che decide SAT in tempo polinomiale**
Ma a cosa ci serve sapere che $P=NP$?
Beh molto semplice, prendendo un problema in $NP$ so che *esiste* un algoritmo deterministico che lo decide in tempo polinomiale.
Ma che ci faccio con l'*esistenza*?
A che mi serve sapere che, siccome un problema si trova in $NP$ e $P=NP$, un algoritmo deterministico polinomiale che lo decide *esiste*, se io un tale algoritmo non riesco a progettarlo?
In realtà il *teorema di Cook-Levin* 


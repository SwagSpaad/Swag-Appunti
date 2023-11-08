----
La logica proposizionale si è rilevata utile per formalizzare ragionamenti del tipo: dalle affermazioni "se piove prendo l'ombrello" e "oggi piove" possiamo dedurre che "oggi esco con l'ombrello" ($p \implies q$).
Per formalizzare ragionamenti del tipo: dalle affermazioni "Tutti gli uomini sono mortali" e "Socrate è un uomo" posso dedurre che "Socrate è mortale", dobbiamo introdurre la **logica del primo ordine** (o dei predicati).

# Facciamo prima degli esempi
Prima di descrvere precisamente la sintassi e la semantica, vediamo qualche esempio.
Consideriamo la seguente sequenza di simboli.

$$\forall x\left[P(x) \lor Q(x)\right]$$ ^6509d5

Nel caso della logica proposizionale, una interpretazione di una formula è un'assegnazione di verità per le variabili, che può essere T o F.
Come possiamo dare un'interpretazione alla formula sopra? Dobbiamo specificare due cose:
1. Un **dominio** all'interno del quale si muove la variabile $x$
2. Una **proprietà** per ognuna delle lettere predicative $P$ e $Q$
Definite queste due cose la formula può assumere valore T o F.

>**Esempio**
>Prendiamo come dominio l'insieme dei numeri naturali $\mathbb N$ ed assegnamo alle lettere predicative P e Q queste proprietà: $P(x)=$ "$x$ è pari" e $Q(x)=$ "$x$ è dispari". In questa interpretazione la formula si esprime "Per ogni numero naturale $x$, $x$ è pari oppure $x$ è dispari", che è senz'altro vera, a meno che non esista un numero naturale che non è né pari né dispari.

Nella [[#^6509d5|formula]] le lettere predicative P e Q hanno un solo argomento ed indicano una **proprietà** degli elementi del dominio. Ma esse possono avere anche più di un argomento ed in quel caso indicano una **relazione** fra gli elementi del dominio.

**Esempio**
Consideriamo la formula $$\exists x\ \forall y\ P(x,y)$$
>**Interpretazione 1**
>Prendiamo come dominio $\mathbb N$ e come $P(x, y)$ la relazione "$x$ è minore o uguale ad $y$". In questa interpretazione, la formula risulta "Esiste un numero $x$ tale che per ogni $y$, $x$ è minore o uguale ad $y$". La formula è vera in questa interpretazione, infatti c'è il numero naturale 1 per cui questa relazione è sempre vera

Vediamo due interpretazioni in cui la formula è falsa
>**Interpretazione 2** e 3
>Dominio $\mathbb Z$ e $P(x,y)$ come prima, oppure Dominio $\mathbb N$ ma $P(x, y)$ invertita, cioé $x \geq y$.

Ora consideriamo la formula $$\forall x P(x)\implies \exists xP(x)$$
Ragioniamo intuitivamente. La formula è un'implicazione quindi l'unico caso in cui è F è quando la premessa  ($\forall x P(x)$) è T e la conseguenza ($\exists xP(x)$) è F. Ora osserviamo che qualunque dominio e qualunque proprietà venga scelta, se la premessa risulta vera (ossia è vero che la proprietà P vale per ogni elemento del dominio), allora deve risultare vera anche la conseguenza (ossia esiste un elemento del dominio per cui P è vera). Quindi la formula è vera in ogni interpretazione.

# Sintassi e semantica
I simboli utilizzati nella logica del primo ordine sono:
- I **connettivi** ($\lnot, \land,\lor,\implies,\dots$) usati anche nella logica proposizionale
- Le variabili ($x, y, z,\dots$ oppure $x_{1},x_{2},\dots$) che chiameremo **individuali** per distinguere dalle [[Lezione 2 - Introduzione alla logica proposizionale#^dcf684|variabili booleane]] della logica proposizionale. Le variabili individuali assumono valori in qualunque dominio.
- Le **lettere predicative** ($P, Q, R, \dots$ oppure $P_{1},P_{2},\dots$). A volte per indicare il numero di argomenti si utilizza un apice ($P^{(n)}$)
- I quantificatori ($\forall$ e $\exists$)
- Le **costanti** ($a,b,c,\dots$ oppure $a_{1},a_{2}, \dots$) servono ad indicare elementi specifici del dominio.
- Le **lettere funzionali** ($f, g, h,\dots$ oppure $f_{1},f_{2},\dots$). Per esempio $f(x_1,x_2)$ indica una funzione che mappa una coppia ordinata $(x_{1},x_{2})$ di elementi di un dominio, in un altro elememento del dominio $f(x_1,x_2)$.

**Def.** (termine)
Le variabili e le costanti sono **termini**. Se $t_{1},\dots,t_{n}$ sono termini e $f^{(n)}$ è una lettera funzionale con $n$ argomenti, allora anche $f^{(n)}(t_{1},\dots,t_n)$ è un termine.
def.
**Def.** (formula ben formata)
Se $t_{1},\dots,t_{n}$ sono termini e $P^{(n)}$ è una lettera predicativa con $n$ argomenti, allora  $P^{(n)}(t_{1},\dots,t_{n})$ è una formula ben formata. Queste si chiamano formule atomiche, inoltre come nella logica proposizionale:  ^c17a5b
- Se $\mathcal F$ è una f.b.f., allora anche $\lnot F$ è una f.b.f.
- Se $\mathcal F$ e $\mathcal G$ sono f.b.f., allora anche $\mathcal F \circ \mathcal G$  è una f.b.f. dove con $\circ$ abbiamo indicato uno qualunque dei connettivi $\land,\lor, \implies, \equiv$.
- Se $\mathcal F$ è una f.b.f. e $x$ è una variabile, allora anche $\forall x\mathcal F$ e $\exists x \mathcal F$ sono f.b.f.

**Def.** (interpretazione)
Data una f.b.f. $\mathcal F$, una sua interpretazione consiste in:
- Un insieme non vuoto D che chiamiamo dominio
- Una relazione per ogni lettera predicativa P in $\mathcal F$
- Una funzione per ogni lettera funzionale $f$ in $\mathcal F$
- Un elemento del dominio per ogni costante a in $\mathcal F$

**Esempio**
Consideriamo la formula $$\forall x \ \exists yP(f(x,a), y)$$
>**Interpretazione 1**
>Dominio: $\mathbb N$ 
>$P(x,y)$ = "$x$ è uguale a $y$"
>$f(x, y)$ = $x^y$
>$a = 2$
>In questa interpretazione la formula si legge "per ogni numero naturale $x$ esiste un numero naturale $y$ tale che $x^2=y$". Questa interpretazione è T

>**Interpretazione 2**
>Tutto come nell'interpretazione 1, ma $P(x,y)$ = "$x$ è maggiore di $y$". In questa interpretazione la formula si legge "per ogni numero naturale $x$ esiste un numero naturale $y$ tale che $x^2$ è maggiore di $y$". Quindi è F perché per $x = 1$ non è vera

# Variabili libere e vincolate. Formule chiuse
Per tutte le formule viste finora, una volta data un'interpretazione I, la formula risultava T o F. Tuttavia questo non è vero per ogni f.b.f. in accordo alla definizione di f.b.f.

Osserviamo che la formula $\forall x P(x,y)$ è una f.b.f. Proviamo a darne un interpretazione con dominio $\mathbb N$ e $P(x,y)$ = "$x$ è maggiore di $y$". In questa interpretazione la formula si legge "per ogni numero naturale $x$ si ha che $x$ è maggiore di $y$". La formula quindi è vera o falsa? Non possiamo dirlo perché non sappiamo chi è $y$. In quella formula si dice che $x$ è una variabile **vincolata**, mentre $y$ è **libera**.

**Def.** (variabili libere e vincolate)
In una f.b.f. si dice **vincolata** una variabile che sta nel campo d'azione di un quantificatore, altrimenti si dice **libera**.

**Oss.**
Una stessa variabile può anche comparire libera in alcune occorrenze e vincolata in altre. Per esempio nella formula seguente la prima occorrenza della variabile $y$ è libera, mentre nella seconda è vincolata: $$\forall x[P(x)\land Q(y)]\implies \exists yQ(y)$$
**Def.** (formule chiuse)
Una f.b.f. senza variabili libere si dice **chiusa**.

# Formule valide vs tautologie
Negli esempi sopra abbiamo visto che ci sono formule che sono vere in ogni interpretazione

**Def.** (formule valide)
Una formula $\mathcal F$ vera in ogni interpretazione si dice **valida**.

Perché allora non le chiamiamo tautologie? Perché riserviamo il termine per un sottoinsieme delle formule valide. Per esempio, consideriamo la formula $$\forall x P(x)\lor\lnot(\forall x P(x))$$
Questa formula è del tipo $\mathcal F \lor \lnot\mathcal F$, che è chiaramente vera in ogni interpretazione. Nella logica del primo ordine si chiamano **tautologie** le formule che sono istanze di tautologie nella logica proposzionale. Per esempio la formula $$\forall x P(x)\implies(\exists xQ(x)\implies \forall xP(x))$$ si ottiene dalla formula $X \implies (Y \implies X)$. La formula sopra è una tautologia mentre ad esempio la formula $\forall x P(x)\implies \exists xP(x)$, pur essendo valida, non è una tautologia.

Si noti che una tautologia è vera in ogni interpretazione indipendentemente dal significato che hanno i quantificatori, mentre una formula valida che non è una tautologia è vera in ogni interpretazione per il significato che hanno i quantificatori. 

# Interdipendenza dei quantificatori
I quantificatori $\forall$ e $\exists$ non sono indipendenti, nel senso che si può definire uno in funzione dell'altro. Per esempio la formula $\lnot \exists x P(x)$ è equivalente alla formula $\forall x \lnot P(x)$. Infatti, data una qualunque interpretazione, la prima sta dicendo che "non esiste un elemento $x$ per cui vale $P(x)$", la seconda sta dicendo che "per ogni elemento $x$ non vale $P(x)$".
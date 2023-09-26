---

---

# Linguaggi per basi di dati

Operazioni sullo schema
- **DDL: Data Definition Language**
Operazioni sui dati
- **DML:Data Manipulation Language**
- *Interrogazione ("query")*
- *Aggiornamento*

# Linguaggi di interrogazione per basi di dati

- *Dichiarativi*: specificano le proprietà del risultato ("**che cosa**")
- *Procedurali*: specificano le modalità di generazione del risultato ("**come**")

# Linguaggi relazionali

*Algebra relazionale*: procedurale
- Insieme di operatori
	- Su relazioni
	- Che producono relazioni
	- Possono essere composti
*Calcolo relazionale*: dichiarativo (teorico)
- Basato sul calcolo dei predicati del primo ordine
- Connettivi e clausole che consentono di descrivere la relazione risultato
*SQL* (*Structured Query Language*): intermendio (reale)
*QBE* (*Query by Example*): dichiarativo (reale)

# Algebra relazionale
È un linguaggio procedurale, in cui le operazioni vengono descritte descrivendo la procedura per ottenere la soluzione

*Operazioni di base*: 
- **Unione**, **differenza**, **intersezione** derivati dalla teoria degli insiemi
- **Ridenominazione**, **selezione**, **proiezione** specifici dell'algebra relazione
- **Join** che può assumere diverse forme diverse forme (naturale, theta-join, prodotto cartesiano)

# Operatori insiemistici

Le relazioni sono **Insiemi** e quindi è naturale estendere ad esse le operazioni relative. 
Tuttavia le relazioni sono insiemi di tuple **omogenee** e quindi ha senso definire ed applicare tali operatori solo a tuple **definite sugli stessi attributi**

*EX*: Unione tra due relazioni su tuple non omogenee **non** è una relazione

# Operatori derivati dagli insiemi

- **Unione**
	L'unione fra due relazioni $r_{1}$ e $r_{2}$ definite sullo stesso insieme di attributi $X$ è indicata con $r_{1} \cup r_2$  ed è una relazione su $X$ contenente le tuple che appartengono a $r_{1}$ o $r_{2}$  oppure ad entrambe.
- **Intersezione**
	L'intersezione  fra due insieme $r_{1}$ e $r_{2}$ definite sullo stesso insieme di attributi $X$ è indicata con $r_{1} \cap r_{2}$ ed è una relazione su $X$ contenente le tuple che appartengono sia a $r_{1}$ che a $r_{2}$.
- **Differenza**
	La differenza fra due relazioni $r_{1}$ e $r_{2}$ definite sullo stesso insieme di attributi $X$ è indicata con $r_{1} - r_{2}$ ed è una relazione su $X$ contenente le tuple che appartengono
	a $r_{1}$ e non a $r_{2}$

*EX*:

**Laureati**:

| Matricolati | Cognome | Età |
| ----------- | ------- | --- |
| 1           | Rossi   | 37  |
| 2           | Neri    | 36  |
| 3           | Bianchi | 28    |

**Dirigenti**:

| Matricola | Cognome | Età |
| --------- | ------- | --- |
| 9         | Verdi   | 51  |
| 2         | Neri    | 36  |
| 3         | Bianchi | 28    |

**Laureati** $\cap$ **Dirigenti**:

| Matricola | Cognome | Età |
| --------- | ------- | --- |
| 2         | Neri    | 36  |
| 3         | Bianchi | 28    |

**Laureati** - **Dirigenti**:

| Matricola | Cognome | Età |
| --------- | ------- | --- |
| 1         | Rossi   | 37    |

**Laureati** $\cup$ **Dirigenti**

| Matricola | Cognome | Età |
| --------- | ------- | --- |
| 1         | Rossi   | 37  |
| 2         | Neri    | 36  |
| 3         | Bianchi | 28  |
| 9         | Verdi   | 51    |

# Ridenominazione

L'operatore di **ridenominazione** cambia il nome degli attributi allo scopo di facilitare operazioni insiemistiche.
Consente di modificare il nome di un attributo per poterlo associare ad un altro attributo in una operazione algebrica.
- Operatore **monadico**(con un argomento)
- "**Modifica lo schema**" lasciando inalterata l'istanza dell'operando
SI indica con $\rho$ **nuovonome** $\leftarrow$ **vecchionome** (**Relazione**)
**Esempio**: Date le relazioni
- **Paternità**(Padre, Figlio)
- **Maternità**(Madre, Figlio)
È possibile ottenere
$\rho$ **Genitore** $\leftarrow$ **Padre**(**Paternità**) $\cup$ $\rho$ **Genitore** $\leftarrow$ **Madre**(**Maternità**)

*EX*: 

**Paternità** 

| Padre  | Figlio |
| ------ | ------ |
| Adamo  | Abele  |
| Adamo  | Caino  |
| Abramo | Isacco |

**Maternità**

| Madre | Figlio |
| ----- | ------ |
| Eva   | Abele  |
| Eva   | Set    |
| Sara  | Isacco       |

$\rho$ **Genitore** $\leftarrow$ **Padre**(**Paternità**)

| Genitore | Figlio |
| -------- | ------ |
| Adamo    | Abele  |
| Adamo    | Caino  |
| Abramo   | Isacco       |

$\rho$ **Genitore** $\leftarrow$ **Madre**(**Maternità**)

| Genitore | Figlio |
| -------- | ------ |
| Eva      | Abele  |
| Eva      | Set    |
| Sara     | Isacco       |

$\rho$ **Genitore** $\leftarrow$ **Padre**(**Paternità**) $\cup$ $\rho$ **Genitore** $\leftarrow$ **Madre**(**Maternità**)

| Genitore | Figlio |
| -------- | ------ |
| Adamo    | Abele  |
| Adamo    | Caino  |
| Abramo   | Isacco |
| Eva      | Abele  |
| Eva      | Set    |
| Sara     | Isacco       |

# Selezione e Proiezione

Le operazioni di **selezione** e di **proiezione** si applicano ad una relazione e ne restituiscono una porzione.
Possono essere considerate ortogonali o complementari, in quanto una opera sulle **righe** e l'altra sulle **colonne**.
La **selezione** produce un insieme di tuple, su tutti gli attributi.
La **proiezione** produce un risultato definito su un insieme di attributi, cui contribusiconotutte le tuple.

# Selezione
Operatore **monadico**
Produce un risultato che: 
- Ha lo stesso schema dell'operando
- Contiene un sottoinsieme delle ennuple dell'operando che soddisfano una **specifica condizione di selezione**
- **Si indica con** $\sigma_{F}(r)$ o $SEL_{F}(r)$ 
	Dove:
	- **F** è una condizione da verificare
	- **r** è la relazione a cui la selezione è applicata definita su un insieme di attributi $X$ 
Quindi, $\sigma_{F}(r)$ produce una relazione sugli stssi attributi di r contenente le ennupla su cui F è vera (**semantica**)

# Sintassi: Condizione di selezione

$\sigma_{F}(r(X))$:

- F è una $formula \ proposizionale$ su $X$, cioè una formula ottenuta combinando con i simboli $\land$ ($and$) $\lor$ ($or$) $\lnot$ ($not$) espressioni del tipo $A$ $\theta$ $B$ o $A$ $\theta$ $c$ dove:
	- $\theta$ è un operatore di confronto ($\le, \ <, \ \geq, \ >, \ =$ )
	- A e B sono attributi di $X$ su cui il confronto abbia senso
	- $c$ è una costante tale che il confronto con A sia definito
- È Definito un valore di verità di F su una ennupla $t\in r$:
	- $A \ \theta \ B$ è vera $\iff$ $t[A] \ \theta \  t[B]$ è vero
	- $A \ \theta \ c$ è vera se $t[A] \ \theta \ c$ è vera
	- $F_{1} \land F_{2}, \ F_{1} \lor F_{2}, \lnot F$ hanno l'usuale significato  

*EX*:

**Dirigenti**:

| Matricola | Cognome | Età | Stipendio |
| --------- | ------- | --- | --------- |
| 9         | Verdi   | 51  | 2700      |
| 7         | Blu     | 35  | 3000      |
| 10        | Viola   | 29  | 2000          |

$\sigma_{età}> 50 \land Stipendio > 2500^{(Dirigenti)}$

| Matricola | Cognome | Età | Stipendio |
| --------- | ------- | --- | --------- |
| 9         | Verdi   | 51  | 2700          |

**Cittadini**:

| Cognome | Nome  | Nascita | Residenza |
| ------- | ----- | ------- | --------- |
| Rossi   | Mario | Roma    | Milano    |
| Neri    | Luca  | Roma    | Roma      |
| Verdi   | Nico  | Firenze | Firenze   |
| Rossi   | Marco | Napoli  | Firenze          |

$\sigma_{Nascita}= Residenza^{(Cittadini)}$  

| Cognome | Nome | Nascita | Residenza |
| ------- | ---- | ------- | --------- |
| Neri    | Luca | Roma    | Roma      |
| Verdi   | Nico | Firenze |        Firenze   | 

# Selezione con valori nulli

**Persone**

| Matricola | Cognome | Filiale | Età |
| --------- | ------- | ------- | --- |
| 7309      | Rossi   | Roma    | 32  |
| 5998      | Neri    | Milano  | 45  |
| 9553      | Bruni   | Milano  | NULL    |

$\sigma_{Età>30}(Persone) \cup \sigma_{Età\le30}(Persone)\neq Persone$

- Perchè?
	Perchè le selezioni vengono valutate separatamente!

- Ma anche

$\sigma_{Età>30\land Età \le 30}(Persone)\neq (Persone)$

- Perchè? 
	Perchè anche le condizioni atomiche vengono valutate separatamente!

# Selezione con valori nulli

- Per riferirsi ai valori nulli esistono forme appositedi condizioni:
	**IS NULL**
	**IS NOT NULL**
$$\sigma_{Età>30}(Persone) \cup \sigma_{Età \le 30}(Persone) \cup \sigma_{Età \ IS \ NULL}(Persone) \ =$$
$$\sigma_{Età>30\lor Età\le 30\lor Età \ IS \ NULL}(Persone) \ =$$
$$Persone$$

# Proiezione

Dati una relazione $r(X)$ e un sottoinsieme $Y$ di $X$, la proiezione di $r$ su $Y$ si indica con $$\prod_{y}(r) \ o \ PROJ_{Y}(r)$$
l'insieme delle ennuple su $Y$ ottenute dalle ennuple di $r$ considerando solo i valori su $Y$
La proiezione $\prod_{Y}(r)$ è l'insieme di tuple su un sottoinsieme $Y$ di attributi $X$ di $R$, ottenuta dalle tuple  di **R** considerando solo i valori su $Y$ cioè:$$\prod_{Y}(r)=\{t[Y] \ | \ t \in r\}$$
Una proiezione ha un numero di tuple minore o uguale rispetto alla relazione $r$ cui è applicata. Il numero di tuple è uguale $\iff$ **Y è superchiave per r**

*EX*:

- Visualizzare matricola e cognome di tutti i cittadini 

**Cittadini** 

| Cognome | Nome  | Nascita | Residenza |
| ------- | ----- | ------- | --------- |
| Rossi   | Mario | Roma    | Milano    |
| Neri    | Luca  | Roma    | Roma      |
| Verdi   | Nico  | Firenze | Firenze   |
| Rossi   | Marco | Napoli  | Firenze          |

$\prod_{Cognome, \ Nome}(Cittadini)$

| Cognome | Nome   |
| ------- | ------ |
| Rossi   | Milano |
| Neri    | Luca   |
| Verdi   | Nico   |
| Rossi   | Marco       |

# Join

- Combinando selezione e proiezione, si possono estrarre informazioni da **una** relazione
- Non si possono però condividere però correlate informazioni presenti in relazioni diverse
- Il **join** è l'operatore più interessante (potente) dell'algebra relazionale in quanto permette di correlare dati in relazioni diverse
- Evidenza la proprietà del modello relazionale di essere **basato sui valori**
- Esistono due tipi di **join**
	- **Join naturale**
	- **Theta join**

# Join naturale

- L'operatore di **join naturale** $r_{1}\bowtie r_{2}$ (o $r_{1} \ | \ X \ | \ r_{2}$) correla dati i relazioni diverse sulla base di valori uguali in attributi con lo stesso nome
- il **join naturale**  $r_{1}\bowtie r_{2}$ di $r_{1}(X_{1})$ e $r_{2}(X_{2})$ è una relazione definita su $X_{1}\cup X_{2}$:$$r_{1}\bowtie r_{2} = \{ t \ su \ X_{1}X_{2} \ | \ t[X_{1}]\in r_{1} \ e t[X_{2}]\in r_{2}$$
- Il grado della relazione ottenuta è minore o uguale al grado della somma dei gradi delle due relazioni in quanto gli attributi omonimi compaiono una sola volta
- Se $X_{1}\cap X_{2}$ è vuoto il **join naturale** è equivalente al $prodotto \ cartesiano$ fra le relazioni 
- Se $X_{1}=X_{2}$ il **join naturale** equivale all'intersezione fra le relazioni
- Se ciscuna ennuple di ciascuno degli operandi contribuisce ad almeno una ennuple del risultato il join si dice **completo**
- Se per alcune ennuple non è verificata la corrispondenza e non contribuiscono al risultato, le ennuple si dicono **dangling**
- Ai due estremi si pongono il join vuoto in cui nessuna ennuple degli operandi è combinabile con tutte le ennuple dell'altro. In questo caso la cardinalità della relazione risultante è pari al prodotto della cardinalità degli operandi

# Join naturale completo 

Ogni ennupla contribuisce al risultato:
- **Join completo** 

**R1**

| Impiegato | Reparto    |
| --------- | ---------- |
| Rossi     | vendite    |
| Neri      | produzione |
| Bianchi   | produzione           |

**R2**

| Reparto    | Capo |
| ---------- | ---- |
| produzione | Mori |
| vendite    | Bruni     |

$R_{1}\bowtie R_{2}$ 

| Impiegato | Reparto    | Capo  |
| --------- | ---------- | ----- |
| Rossi     | vendite    | Bruno |
| Neri      | produzione | Mori  |
| Bianchi   | produzione |Mori      |

# Join non completo

**R1**

| Impiegato | Reparto    |
| --------- | ---------- |
| Rossi     | vendite    |
| Neri      | produzione |
| Bianchi   | produzione |

**R2**

| Reparto    | Capo |
| ---------- | ---- |
| produzione | Mori |
| marketing  | Bruni     |

$R_{1}\bowtie R_{2}$ 

| Impiegato | Reparto    | Capo |
| --------- | ---------- | ---- |
| Neri      | produzione | Mori |
| Bianchi   | produzione | Mori     |

# Join vuoto

**R1**

| Impiegato | Reparto    |
| --------- | ---------- |
| Rossi     | vendite    |
| Neri      | produzione |
| Bianchi   | produzione           |

**R2**

| Reparto         | Capo |
| --------------- | ---- |
| amministrazione | Mori |
| vendite         | Bruni     |

$R_{1}\bowtie R_{2}$ 

| Impiegato | Reparto | Capo |
| --------- | ------- | ---- |
|           |         |      |

# Join naturale: proprietà

1. Il **join** di $r_{1} \ e \ r_{2}$ contiene un numero di ennuple compreso fra zero e il prodotto di $|r_{1}|$ e $|r_{2}|$
2. Se il join di $r_{1}$ e $r_{2}$ è **completo** allora contiene un numero di tuple pari almeno al massimo fra $|r_{1}|$ e $|r_{2}|$ 
3. Se $X_{1}\cap X_{2}$ contiene una chiave per $r_{2}$ allora il join di $r_{1}(X_{1})$ e $r_{2}(X_{2})$ contiene almeno $|r_{2}|$ tuple  
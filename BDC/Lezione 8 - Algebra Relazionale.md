---

---
----
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
2. Se il **join** di $r_{1}$ e $r_{2}$ è **completo** allora contiene un numero di tuple pari almeno al massimo fra $|r_{1}|$ e $|r_{2}|$ 
3. Se $X_{1}\cap X_{2}$ contiene una chiave per $r_{2}$ allora il **join** di $r_{1}(X_{1})$ e $r_{2}(X_{2})$ contiene almeno $|r_{2}|$ tuple  
4. Se il **join** coinvolge una chiave di $R_{2}$ e **un vincolo di integrità referenziale**, allora il numero di tuple è pari a $|R_{1}|$
5. $r_{1}\bowtie r_{2} = r_{1}$ il **join** è **commutativo**
6. $(r_{1}\bowtie r_{2})\bowtie r_{3}=r_{1} \bowtie(r_{2}\bowtie r_{3})$ il **join** è **associativo**

Quindi sequenze di **join** possono essere scritte senza parentesi

# Prodotto cartesiano 

- Un **join naturale** su relazioni senza attributi in comune
- Contiene sempre un numero di ennuple pari al prodotto delle cardinalità degli operandi
- Il **prodotto cartesiano** $r_{1} \times r_{2}$ di $r_{1}(X_{1})$ e $r_{2}(X_{2})$ è una relazione definità $X_{1}\cup X_{2}$ $$r_{1}\times r_{2}= \{t \ su \ X_{1}X_{2} \ | \ t[X_{1}]\in \ e \ t[X_{2}] \in r_{2}\}$$
*EX*:

**Impiegati**:

| Impiegato | Reparto |
| --------- | ------- |
| Rossi     | A       |
| Neri      | B       |
| Bianchi   | B        |

**Reparti**:

| Codice | Capo |
| ------ | ---- |
| A      | Mori |
| B      | Bruni     |

$Impiegati \times Reparti$ 

| Impiegato | Reparto | Codice | Capo  |
| --------- | ------- | ------ | ----- |
| Rossi     | A       | A      | Mori  |
| Neri      | B       | A      | Mori  |
| Bianchi   | B       | A      | Mori  |
| Rossi     | A       | B      | Bruni |
| Neri      | B       | B      | Bruni |
| Bianchi   | B       | B      | Bruni      |

# Join esterni: Outer join

Il **join naturale** tralascia le ennuple in cui non vi è corrispondenza fra gli attributi legati dal join
L'operatore di **join esterno** (**outer join**) prevede che tutte le tuple diano sempre un contributo al risultato, eventualmente estese con valori nulli ove non vi siano controparti opportune

**Tre tipi di outer join**:

- *Left join*: Contribuiscono tutte le ennuple del primo operando eventualmente estese con valori nulli
- *Right join*: Contribuiscono tutte le ennuple del secondo operando eventualmente estese con valori nulli
- *Full join*: Contribuiscono tutte le ennuple del primo e secondo operando eventualmente estese con valori nulli

Altre Operazioni Relazionali  
Operazioni di Chiusura Ricorsiva  
• Un altro tipo di operazione che, in generale, non può essere speciﬁcato  
in algebra relazionale di base è la chiusura ricorsiva. Questa operazione  
si applica ad un’associazione ricorsiva.  
• Un esempio di operazione ricorsiva è la ricerca di tutti i super# Left join

Ritorno tutte le tuple dalla relazione di sinistra a prescindere dal fatto che siano combinabili con quelle della relazione di destra.
Assegna valori nulli per i record che non matchano.

*EX*:

**Impiegati**:

| Impiegato | Reparto    |
| --------- | ---------- |
| Rossi     | vendite    |
| Neri      | produzione |
| Bianchi   | produzione           |

**Reparti**:

| Reparto    | Capo |
| ---------- | ---- |
| produzione | Mori |
| acquisti   | Bruni     |

$Impiegati \bowtie_{LEFT}Reparti$ 

| Impiegati | Reparto    | Capo |
| --------- | ---------- | ---- |
| Rossi     | vendite    | NULL |
| Neri      | produzione | Mori |
| Bianchi   | produzione | Mori     |

# Right join

Ritorna tutte le tuple dalla relazione di destra a prescindere dal fatto che siano combinabili con quelle della relazione di sinistra
Assegna i valori nulli per i record che non matchano

*EX*:

**Impiegati**:

| Impiegati | Reparto    |
| --------- | ---------- |
| Rossi     | vendite    |
| Neri      | produzione |
| Bianchi   | produzione           |

**Reparti**:

| Reparto    | Capo |
| ---------- | ---- |
| produzione | Mori |
| acquisti   | Bruni     |

$Impiegati \bowtie_{RiGHT}Reparti$ 

| Impiegato | Reparto    | Capo |
| --------- | ---------- | ---- |
| Neri      | produzione | Mori |
| Bianchi   | produzione | Mori |
| NULL      | acquisti   | Bruni     |


# Full join

Combina i risultati di due relazioni tenendo conto di tutte le tuple delle relazioni, anche di quelle che non hanno corrispondenza tra di loro.
Il risultato contiene sempre tutte le tuple della relazione di sinistra ("left"),
estraendo dalla relazione di destra ("right") solamente le tuple che trovano corrispondenza nella regola di confronto; inoltre verranno estratte tutte le tuple della relazione di sinistra ("left") che non trovano corrispondenza nella relazione di destra ("right") impostando a nulli i valori di tutti gli attributi della relazione di destra,  e viceversa.

*EX*: 

**Impiegato**:

| Impiegato | Reparto    |
| --------- | ---------- |
| Rossi     | vendite    |
| Neri      | produzione |
| Bianchi   | produzione           |

**Reparti**:

| Reparto    | Capo |
| ---------- | ---- |
| produzione | Mori |
| acquisti   | Bruni     |

$Impiegati \bowtie_{FULL} Reparti$

| Impiegato | Reparto    | Capo  |
| --------- | ---------- | ----- |
| Null      | acquisti   | Bruni |
| Neri      | produzione | Mori  |
| Bianchi   | produzione | Mori  |
| Rossi     | vendite    | NULL      |

# Theta-join

Se si devono correlare attributi con nome diverso è possibile fare il **theta-join**, definito come un prodotto cartesiano seguito da una selezione $$r_{1}\bowtie_{F} r_{2} = \sigma (r_{1} \times r_{2})$$
Dove F è una formula e $r_{1}$ e $r_{2}$ non hanno attributi di nome comune.
Se F è una relazione di uguaglianza, con un attributo della prima relazione e uno della seconda, allora siamo in presenza di un **equi-join**

*Sono importanti formalmente*:
- Il join naturale è basato sui **nomi** degli attributi
- Equi-join e theta-join sono basati sui **valori**

*EX*: 

**Impiegati**:

| Impiegato | Progetto |
| --------- | -------- |
| Rossi     | A        |
| Neri      | A        |
| Neri      | B         |

**Reparti**:

| Codice | Nome   |
| ------ | ------ |
| A      | Venere |
| B      | Marte       |

$Impiegati\bowtie_{Progetto=Codice} Reparti$ 

| Impiegato | Progetto | Codice | Nome   |
| --------- | -------- | ------ | ------ |
| Rossi     | A        | A      | Venere |
| Neri      | A        | A      | Venere |
| Neri      | B        | B      | Marte       |

*EX*:

**Impiegati**

| Impiegato | Progetto |
| --------- | -------- |
| Rossi     | A        |
| Neri      | A        |
| Neri      | B         |

**Reparti**

| Codice | Nome   |
| ------ | ------ |
| A      | Venere |
| B      | Marte       |

$Impiegati \bowtie_{Progetto=Codice} Reparti$

| Impiegato | Progetto | Codice | Nome   |
| --------- | -------- | ------ | ------ |
| Rossi     | A        | A      | Venere |
| Neri      | A        | A      | Venere |
| Neri      | B        | B      | Marte       |

# Join naturale ed equijoin

**Impiegato**

| Impiegato | Reparto |
| --------- | ------- |
|           |         |

**Reparti**

| Codice | Capo |
| ------ | ---- |
|        |      |

$$$\pi_{Impiegato, \ reparto, \ capo}(Impiegati \bowtie_{reparto=codice}Reparti)=$$
$$\pi_{Impiegato, \ reparto, \ capo}(\sigma_{reparto=codice}(Impiegati \times Reparti))=$$
$$Impiegati\bowtie(\rho_{Codice\leftarrow Reparto}(Reparti))$$ 
# Join e proiezioni: perdita di innformazioni

- $R_{1}(X_{1}), \ R_{2}(X_{2})$ $$\prod_{x_{1}}(R_{1}\bowtie R_{2})\subseteq R_{1}$$
- $R(X), \ X=X_{1} \cup X_{2}$ $$(\prod_{X_{1}}(R))\bowtie(\prod_{X_2}(R))\supseteq R$$
## Problemi

$R_{1}$

| Impiegato | Reparto |
| --------- | ------- |
| Rossi     | A       |
| Neri      | B       |
| Bianchi   | B        |

$R_{2}$ 

| Reparto | Capo |
| ------- | ---- |
| B       | Mori |
| C       | Bruni     |

$R_{1}\bowtie R_{2}$ 

| Impiegato | Reparto | Capo |
| --------- | ------- | ---- |
| Neri      | B       | Mori |
| Bianchi   | B       | Mori     |

$\prod_{x_{1}}(R_{1}\bowtie R_{2})\subseteq R_{1}$

| Impiegato | Reparto |
| --------- | ------- |
| Neri      | B       |
| Bianchi   | B        |

$(\prod_{X_{1}}(R))\bowtie(\prod_{X_2}(R))\supseteq R$

| Reparto | Capo |
| ------- | ---- |
| B       | Mori     |

## Problemi 2

$R$ 

| Impiegato | Reparto | Capo  |
| --------- | ------- | ----- |
| Neri      | B       | Mori  |
| Bianchi   | B       | Bruni |
| Verdi     | A       | Bini      |

$\prod_{X_{1}}(R)$ 

| Impiegato | Reparto |
| --------- | ------- |
| Neri      | B       |
| Bianchi   | B       |
| Verdi     | A        |

$\prod_{X_{2}}(R)$

| Reparto | Capo  |
| ------- | ----- |
| B       | Mori  |
| B       | Bruni |
| A       | Bini      |

$(\prod_{X_{1}}(R))\bowtie (\prod_{X_{2}}(R))\supseteq R$  

| Impiegato | Reparto | Capo  |
| --------- | ------- | ----- |
| Neri      | B       | Mori  |
| Bianchi   | B       | Bruni |
| Neri      | B       | Bruni |
| Bianchi   | B       | Mori  |
| Verdi     | A       | Bini      |

# Interrgoazioni - query

**Un'interrogazione** è una funzione **E(R)** che applicataad istanze di una base di dati **R** produce una relazione su un dato insieme di attributi **X**
Le **Interogazioni** su uno schema di base di dati **R** in algebra relazionale sono espressioni sono espressioni i cui atomi sono relazioni in **R** o costanti

**Le interrogazioni sono in pratica espressioni di relazioni che producono relazioni** 

# Schema relazionale

*EX*: 

**Impiegati**

| Matricola | Cognome  | Età | Stipendio |
| --------- | -------- | --- | --------- |
| 101       | Rossi    | 34  | 40        |
| 103       | Bianchi  | 23  | 35        |
| 104       | Neri     | 38  | 61        |
| 210       | Celli    | 49  | 60        |
| 231       | Bisi     | 50  | 60        |
| 252       | Bini     | 44  | 70        |
| 301       | S. Rossi | 34  | 70        |
| 375       | M. Rossi | 50  | 65          |

**Supervisione**

| Capo | Impiegato |
| ---- | --------- |
| 210  | 101       |
| 210  | 103       |
| 210  | 104       |
| 301  | 210       |
| 301  | 231       |
| 375  | 252          |

- Trovare matricola, nome, età e stipendio degli impiegati che guadagnano più di 40 milioni

$\sigma_{Stipendio>40}(Impiegati)$ 

| Impiegati | Cognome  | Età | Stipendio |
| --------- | -------- | --- | --------- |
| 104       | Neri     | 38  | 61        |
| 210       | Celli    | 49  | 60        |
| 231       | Bisi     | 50  | 60        |
| 252       | Bini     | 44  | 70        |
| 301       | S. Rossi | 34  | 70        |
| 375       | M. Rossi | 50  | 65          |

- Trovare matricola, nome ed età degli impiegati che guadagnano più di 40 milioni

$\pi_{Matricola, \ Nome, \ Età}(\sigma_{Stipendio>40}(Impiegati))$  

| Impiegati | Cognome  | Età | Stipendio |
| --------- | -------- | --- | --------- |
| 104       | Neri     | 38  | 61        |
| 210       | Celli    | 49  | 60        |
| 231       | Bisi     | 50  | 60        |
| 252       | Bini     | 44  | 70        |
| 301       | S. Rossi | 34  | 70        |
| 375       | M. Rossi | 50  | 65        |

- Trovare le matricole dei capi degli impiegati che guadagnano più di 40 milioni$$\pi_{Capo}(Supervisione\bowtie_{Impiegato=Matricola}(\sigma_{Stipendio>40}(Impiegati))$$
- Nome e stipendio dei capi degli impiegati che guadagnano più di 40mila euro $$Supervisione\bowtie_{Impiegato=Matricola}(\sigma_{Stipendio>40}(Impiegati))=A$$ $$\pi_{Cognome, \ Stipendio}(Impiegati\bowtie_{capo=Matricola}A)$$ 
# Equivalenza di espressioni

Due espressioni sono **equivalenti** se: 

- $E_{1}\equiv_{R}E_{2}$ se $E_{1}(r) =E_{2}(r)$ per ogni istanza $r$ di $R$ 
		(**equivalenza dipendente dallo schema**)
- $E_{1}\equiv E_{2}$ se $E_{1}\equiv_{R}E_{2}$ per ogni schema $R$ 
		(**equivalenza assoluta**)

**L'equivalenza è importante in quanto consente di scegliere, a parità di risultato, l'operazione meno costosa**

# Equivalenze

- **Atomizzazione delle selezioni**
		$\sigma_{F_{1}\land F_{2}}(E)\equiv \sigma_{F_{1}}(\sigma_{F_{2}}(E))$ 
- **L'idenpotenza delle proiezioni** 
		$\prod_{X}(E)\equiv \prod_{X}(\prod_{XY}(E))$ 
- **Anticipazione della selezione rispetto al join**
		$\sigma_{F}(E_{1}\bowtie E_{2})\equiv (\sigma_{F}(E_{1})\bowtie \sigma_{F}(E_{2}))$ 
- **Anticipazione della proiezione rispetto al join**
		$\prod_{X_{1}Y_{2}}(E_{1}\bowtie E_{2})\equiv E_{1}\bowtie_{ \prod_{Y_{2}}}(E_{2})$ 
	(se gli attributi in $X_{2}-Y_{2}$ non sono coinvolti nel join)
	Allora (combinando con idempotenza delle proiezioni):
		$\prod_{Y}(E_{1}\bowtie_{F}E_{2})\equiv \prod_{Y}(\prod_{Y_{1}}E_{1}\bowtie_{F}\prod_{Y_{2}E_{2}})$     
	Dove $Y_{1}$ e $Y_{2}$ sono gli attributi di $X_{1}$ e $X_{2}$ compresi in $Y$ o coinvolti nel join
	*In pratica è possibile ignorare in ciascuna relazione gli attributi non compresi in $Y$ e non coinvolti nel join*
- **Inglobamento di una selezione in un prodotto cartesiano a formare un join**:
		$S_{F}(E_{1}\bowtie E_{2})\equiv E_{1}\bowtie_{F}E_{2}$
- **Distributività della selezione rispetto all'unione**: 
		$S_{F}(E_{1}\cup E_{2})\equiv S_{F}(E_{1})\cup S_{F}(E_{2})$ 
- **Distributività della selezione rispetto alla differenza**:
		$S_{F}(E_{1}- E_{2}) \equiv S_{F}(E_{1})- S_{F}(E_{2})$ 
- **Distributività della proiezione rispetto all'unione**:
		$P_{X}(E_{1} \cup E_{2})\equiv P_{X}(E_{1})\cup P_{X}(E_{2})$ 

*Nota bene*: La proiezione NON è distributiva rispetto alla differenza

- Tutti gli operatori binari eccetto la differenza godono delle proprietà associativa e commutativa.
# Equivalenza

- Corrispondenze fra gli operatori insiemistici e selezioni complesse
		$\sigma_{F_{1}\lor F_{2}}(R)\equiv \sigma_{F_{1}}(R)\cup \sigma_{F_{2}}(R)$ 
		$\sigma_{F_{1}\lor F_{2}}(R)\equiv \sigma_{F_{1}}(R)\cap \sigma_{F_{2}}(R)$ 
		$\sigma_{F_{1}\lor \lnot F_{2}}(R)\equiv \sigma_{F_{1}}(R)- \sigma_{F_{2}}(R)$ 
- Proprietà distributiva del join rispetto all'unione:
		$E\bowtie(E_{1}\cup E_{2})\equiv(E\cup E_{1})\bowtie (E\cup E_{2})$ 

# Algebra con valori nulli

- Estensione degli operatori logici ad una logica ad una logica a 3 valori
	(VERO, FALSO, SCONOSCIUTO(U))

| Not |     |
| --- | --- |
| F   | V   |
| U   | U   |
| V   | F    |

| And | V U F |
| --- | ----- |
| V   | V U F |
| U   | U U F |
| F   | F F F       |

| or  | V U F |
| --- | ----- |
| V   | V V V |
| U   | V U U |
| F   | V U F      |

- A IS NULL è vero su una ennupla t se il valore di t su A è nullo; falso se è specificato
- A IS NOT FULL

$\sigma_{ETÀ>30}(Persone)$ restituisce le persone la cui età è nota e > 30
$\sigma_{ETÀ>30\lor ETÀ \ IS \ NULL}(Persone)$ restituisce le persone che potrebbero avere più di 30 anni

# Viste (relazioni derivate)

Rappresentazioni diverse per gli stessi dati (**schema esterno**)

- **Relazioni di base**: contenuto autonomo
- **Relazioni derivate**: relazioni il cui contenuto è funzione el contenuto di altre relazioni (definito per mezzo di interrogazioni)

# Viste

- **Relazioni Virtuali (Viste)**
	Relazioni definite mediante funzioni o espressioni del linguaggio di interrogazione, non memorizzate ma utilizzabili come se lo fossero. Devono essere ricalcolate tutte le volte
- **Viste materializzate**
	Relazioni virtuali effettivamente memorizzate nella base di dati
	Immediatamente disponibili ma critiche per il mantimento dell'allineamento con le relazioni da cui derivano. Non sono supportate dai DBMS

# Vantaggi

- Permettono di mostrare a un utente le sole componenti della base di dati che interessano
- Espressioni molto complesse possono essere definitive come viste
- **Sicurezza**: è possibile definire dei dirtitti di accesso relativi ad una vista (e quindi ad una particolare porzione della base di dati)
- In caso di ristrutturazione della base di dati, le "vecchie" relazioni possono essere di nuovo ricavate mediante viste, consentendo l'uso di applicazioni che fanno riferimento al vecchio schema

*EX*: 

- Una vista:
	$Supervisione = PROJ_{Impiegato, \ Capo}$

**Afferenza**

| Impiegato | Reparto |
| --------- | ------- |
| Rossi     | A       |
| Neri      | B       |
| Bianchi   | B        |

**Direzione**

| Reparto | Capo |
| ------- | ---- |
| A       | Mori |
| B       | Bruni     |

# Interrogazioni sulle viste

- Sono eseguite sostituendo alla vista la sua definizione:$$SEL_{Capo='Leoni'}(Supervisione)$$
Viene eseguita come $$SEL_{Capo='Leoni'}(PROJ_{Impiegato, \ Capo}(Afferenza \ Join \ Direzione))$$
# Motivazioni

- Schema esterno: ogni utente vede solo
	- Ciò che gli interessa e nel modo in cui gli interessa, senza essere distratto dal resto
	- Ciò che è autorizzato a vedere (autorizzazioni)
- Strumento di programmazione
	- Si può semplificare la scrittura di interrogazione: espressioni complesse e sottoespressioni ripetute
	- Utilizzo di programmi esistenti su schemi ristrutturati

# Strumento di programmazione

- Trovare gli impiegati che hanno lo stesso capo di Rossi
- Senza vista: $$PROJ_{Impiegato}(Afferenza \ Join \ Direzione)JOIN$$
$$REN_{ImpR, \ RepR \leftarrow Imp, \ Reparto}(SEL_{Impiegato='Rossi'}(Afferenza \ JOIN \ Direzione))$$
- Con la vista:$$PROJ_{Impiegato}(Supervisione)JOIN$$
$$REN_{ImpR, \ Reparto}(SEL_{Impiegato='Rossi'}(Supervisione))$$

# Viste e aggiornamenti

- Aggiornare una vista
	- Modificare le relazioni di base in modo che la vista , 'ricalcolata', rispecchi l'aggiornamento
- L'aggiornamento sulle relazioni di base corrispondente a quello specificato sulla vista deve essere univoco
- In genere però non è univoco
- Ben pochi aggiornamenti sono ammissibili sulle viste
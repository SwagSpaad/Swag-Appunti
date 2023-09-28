----
# Operazioni modello relazionale

- Notazione algebrica $\rightarrow$ **Algebra Relazionale** $\rightarrow$ Linguaggio procedurale $\rightarrow$ interrogazioni espresse applicando operatori alle relazioni
- Notazione logica $\rightarrow$ **Calcolo relazionale** $\rightarrow$ Linguaggio dichiarativo $\rightarrow$ interrogazioni espresse tramite formule logiche le cui risposte devono essere rese vere dalle tuple

# Calcolo relazionale

- Basato sulla logica del prim'ordine
	- Linguaggio formale per la rappresentazione della conoscenza 
		- Semantica non ambigua
		- Sistema formale di inferenza
- Calcolo relazionale è alla base di quasi tutti i linguaggi di interrogazione esistenti e basati sul modello relazionale
- Esistono diverse versioni del calcolo relazionale, due sono qulle che vedremo:
	- **Calcolo relazionale sui domini**
	- **Calcolo relazionale sulle tuple con dichiarazioni di range**
		- Variazione del precedente
		- Base di molti costrutti degli attuali linguaggi
- Il calcolo dei domini è una 6-pla 
	- {A, D, dom, s, O, F}
		- A: insieme gli attributi
		- D: Insieme dei domini
		- Dom: $A\rightarrow D$
		- s: Schema di base di dati 
		- O: Insieme degli **operatori di confronto** ($>, \ \geq, \ <, \ \leq, \ \neq, \ =$) e **logici** ($\land, \ \lor, \ \lnot$) e i quantificatori esistenziale ($\exists$) e universale ($\forall$)
		- F è l'insieme delle formule corrette

# Formule corrette

- Una formula corretta è definita ricorsivamente a partire dagli atomi che sono formule corrette:
	- Atomi:
		- $R(x)$ dove $R$ apparentemente a $s$ è uno schema di relazione e $x$ è una $variabile \ di \ ennupla$ (**Calcolo delle ennuple**) oppure
		- $R(A_{1}: x_{1},....,A_{p}:X_{p})$ dove $R(A_{1},....,A_{p})$ è uno schema di relazione appartenente a $s$ e $x_{1},.....,x_{p}$ sono variabili di dominio (**Calcolo dei domini**)
		- $x\theta y$ o $x\theta c$, con $x$ e $y$ variabili di ennupla, c'è una costante e $\theta$ operatore di confronto 
	- Se $f_{1}$ e $f_{2}$ sono formule corrette, allora $f_{1}\land f_{2}, \ f_{1}\lor f_{2}, \lnot f_{1}, \ (f_{1})$, formule corrette (le parentesi) sono utilizzate per alterare il normale ordine di precedenza nelle espressioni ($\lnot, \ \land, \  \lor$) 
	- Se $f$ è una formula corretta e $x$ è una variabile di ennupla, allora $\exists x (f)$ e $\forall x(f)$ sono formule corrette

# Espressioni calcolo relazionale

- *Una espressione nel calcolo relazionale (query) ha la seguente forma*:
	- **Calcolo dei domini**: $\{A_{1}:x_{1},....,A_{p}:x_{p} \ | \ f\}$ 
		- $A_{1}:x_{1},....,A_{p}:x_{p}$ è la target list
		- Dove $A_{1},....,A_{p}$ sono attributi distinti e
		- $x_{1},....,x_{p}$ sono variabili di dominio che rendono vera la formula corretta $f$ 
	- **Calcolo delle ennuple**
		- $\{x \ | \ f\}$ 
		Dove $x$ è una variabile di ennupla che rende vera la formula corretta $f$ 
# Verità delle formule

- Una formula atomica: 
	- $R(x)$ è *vera* sui valori di $x$ che rappresentano ennuple di *R* (**Calcolo delle Ennuple**), oppure
	- $R(A_{1}:x_{1},....,A_{p}:x_{p})$ è vera sui valori $x_{1},....,x_{p}$ che formano una ennupla di $R$ (**Calcolo dei domini**)
	- $x\theta y$ o $x\theta c$, è vera sui valori $a_{1}$ e $a_{2}$ tale che $a_{1}\theta a_{2}$ o $a_{1}\theta c$ sono soddisfatte:
- La verità delle formule costruite per congiunzione, disgiunzione e negazione valgono le regole usuali,
- Le formule con i quantificatori sono vere secondo le seguenti regole:
	- $\exists x(f)$ è vera se esiste **almeno** un valore $a$ per la variabile $x$ che rende vera la formula $f$ 
	- $\forall x(f)$ è vera se per **ogni** possibile valore $a$ per la variabile $x$, la formula $f$ risulta vera

# Interrogazioni

**Impiegati**:

| Maricola | Cognome  | Età | Stipendio |
| -------- | -------- | --- | --------- |
| 101      | Rossi    | 34  | 40        |
| 103      | Bianchi  | 23  | 35        |
| 104      | Neri     | 38  | 61        |
| 210      | Celli    | 49  | 60        |
| 231      | Bisi     | 50  | 60        |
| 252      | Bini     | 44  | 70        |
| 301      | S. Rossi | 34  | 70        |
| 375      | M. Rossi | 50  | 65          |

**Supervisione**:

| Capo | Impiegato |
| ---- | --------- |
| 210  | 101       |
| 210  | 103       |
| 210  | 104       |
| 301  | 210       |
| 301  | 231       |
| 375  | 252          |

- Matricola, nome ed età degli impiegati che guadagnano più di 40mila euro
	{Matricola:m, Cognome:n, Età:e | Impiegati(Matricola :m, Cognome :n, Età :e, Stip :s) $\land$ s>40}
- Nome degli impiegati che guadagnano piiù di 40mila euro
	{Cognome:n | ($\exists m$) ($\exists e$) Impiegati(Matricola :m, Cognome :n, Età :e, Strip :s) $\lor$ s>40}

| Matricola | Cognome  | Età |
| --------- | -------- | --- |
| 104       | Neri     | 38  |
| 210       | Celli    | 49  |
| 231       | Bisi     | 50  |
| 252       | Bini     | 44  |
| 301       | S. Rossi | 34  |
| 375       | M. Rossi | 50    |

| Cognome  |
| -------- |
| Neri     |
| Celli    |
| Bisi     |
| Bini     |
| S. Rossi |
| M. Rossi         |

# Problemi con il calcolo relazionale: Assunzione di mondo chiuso

- Il calcolo relazionale ammette espressioni senza senso(sintatticamente corrette ma semanticamente non valide)
	- $\{A_{1}:x_{1}, \ A_{2}:x_{2} \ | \ R(A_{1}:x_{1})\land (x_{2}=x_{2})\}$
	- $\{A_{1}:x_{1} \ | \ \lnot(R(A_{1}:x_{1}))\}$
	- Il risultato cambia al cambiare del dominio e può essere infinitose il dominio è infinito
- Un linguaggio di interrogazione è *indipendente dal dominio* se il suo risultato, su ciascuna istanza di base di dati, non varia al variare del dominio rispetto al quale l'espressione è valutata
- Si assume l'ipotesi di **mondo chiuso** in cui i domini sono ristretti ai valori presenti nell'istanza dello schema relazionale e alle costanti presenti nelle espressioni
- Sotto questa ipotesi il calcolo relazionale è un linguaggio insipendente dal dominio

**Considerazioni**:
- Un'espressione di un linguaggio di interrogazione sarebbe utile che fosse insipendente dal dominio
- Abbiamo bisogno di un'altra versione del calcolo relazionale, in cui le variabili, anzichè denotare singoli valori, denotino tuple

**Calcolo relazionale sui domini ha dei difetti**:
- Agisce sui domini invece che sui valori
- Per il motivo precedente diventa "verboso"
- Puù portare a espressioni che non hanno senso
- Occorre un linguaggio che "focalizzi" le tuple di interesse

# Calcolo relazioni su tuple

- **Calcolo relazioni su tuple**:
		Espressione: $\{Target \ list \ | \  Range \ list \ | \ formula \}$ 
- **Target list**: lista degli obiettivi con elementi $Y:x.Z$ o $x.Z$ se $Z:x.Z$ o $x.^*$ 
- **Range list**: elenco delle variabili libere delle formule con i relativi campi di varibilità
- **Formula è del tipo**:
	- $x.A\theta c$ o $x.A\theta y.B$ 
	- Connettivi di formule
	- $\exists x(R)(f)$ o $\forall x(R)(f)$ 

# Interrogazioni

- Matricola, nome ed età degli impiegati che guadagnano più di 40mila euro
	{i. (Matr, Nome, Età) | i (Impiegati) | i.Stip > 40}

| Matricola | Cognome  | Età |
| --------- | -------- | --- |
| 104       | Neri     | 38  |
| 210       | Celli    | 49  |
| 231       | Bisi     | 50  |
| 252       | Bini     | 44  |
| 301       | S. Rossi | 34  |
| 375       | M. ROssi | 50    |

{i. \* | i(Impiegati) | i.Stip > 40} con \* prendo tutti gli attributi

**Considerazioni**:

- Il calcolo su tuple però non permette di esprimere tutte le interrogazioni che possono essere formulate io Algebra relazionale

*EX*:

Non c'è l'unione, per questo nei linguaggi interrogativi viene aggiunto esplicitamente un costrutto di unione

- Trovare gli impiegati che guadagnano più del proprio capo, mostrando, nome e stipendio dell'impiegato e del capo
	- **Algebra relazionale**: $$\pi_{Nome, Stip,MatrC,StipC}(\sigma_{Stipendio>StipC}(\rho_{MAtrC,Nome, StipC,EtàC\leftarrow Matr,Nome,Stip,Età }(Impiegati)\bowtie_{MatrC=Capo}(Supervisione\bowtie_{Impiegato=Matricola}(Impiegati))))$$
	- **Calcolo dei domini**:$$\{Nome \ n, \ Stip \ s, \ Nome Cnc, \ StipCsc \ | \ Impiegati(Matr:m, \ Nome \ n, \ Età \ e, \ Stipendio:s) \land (s>sc) \land Supervisione(Impiegato:m, \ Capo:c) \land Impiegati(Matr:c, \ Nome \ nc, \ Età \ ec, \ Stipendio: \ sc)\}$$
	- **Calcolo delle ennuple**:$$\{t^{[4]} \ | \ (\exists x) (\exists y)(\exists z) Impiegati(x)\land Supervisione(y)\land (y.Impiegato=x.matr)\land Impiegati(z) \land (y.Capo=z.Matr)\land (t.Nome=x.Nome)\land (t.Stip>x.Stip)\land (t.NomeCapo=z.Nome)\land (t.StipCapo=z.Stip)\}$$
- Trovare le matricole e i nomi dei capi i cui impiegati guadagnano tutti più di 40milioni
	- Algebra relazionale:$$\pi_{Capo}(Supervisione)-\pi_{Capo}(Supervisione\bowtie_{Impiegato=Matricola}(\sigma_{Stipendio\leq 40}(Impiegati)))$$
	- Calcolo dei domini (due modi: negazione quantificatore esistenziale)
		- $$\{Matricola:c, \ Nome \ n \ | \ Impiegati(Matr:c, \ Nome \ n, \ Età  \ e, \ Stipendio:s)\land Supervisione(Impiegato:m, \ Capo:c)\land \rceil \exists m'(\exists n'(\exists s'(Impiegati(Matr: m', \ Nome \ n', Età \ e', \ Stipendio:s')\land Supervisione(Impiegato:m', \ Capo:c)\land(s'\geq 40))))$$
		- $$\{Matricola:c, \ Nome \ n \ | \ Impiegati(Matr:c, \ Nome \ n, Età, \ e, \ Stipendio:s)\land \forall m'(\forall n'(\forall e'(\forall s'(Impiegati(Matr:m', \ Nome \ n', \ Età \ e', \ Stipendio:s')\land Supervisione(Impiegato:m', \ Capo:c)\land(s'>40)))))\}$$
# Equivalenza fra i linguaggi

- È possibile dimostrare che:
	- Per ogni espressione del calcolo relazionale che sia indipendente dal dominio esiste un'espressione dell'algebra relazionale equivalente ad essa;
	- Per ogni espressione dell'algebra relazionale esiste un'espressione del calcolo relazionale equivalente ad essa
*Dim*: In modo ricorsivo a partire dagli operatori di base

![[BDC/img/img72.png|center|800]]
# Calcolo dei domini: QBE

![[BDC/img/img73.png|center|800]]

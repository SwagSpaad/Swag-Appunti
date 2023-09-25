----
# Modello dei dati

- Insieme di costrutti utilizzati per organizzare i dati di interesse e descriverne la dinamica
- Il componente fondamentale sono **i meccanismi di strutturazione** (o **costruttori di tipo**)
- Come nei linguaggi di programmazione esistono meccanismi che permettono di definire tipi di dati, così ogni modello dei dati prevede alcuni costruttori
- Ad esempio, il **modello relazionale** prevede il costruttore **relazione**, che permette di definire insieme di record omogenei

# Modelli dei dati
- **Modelli logici**: utilizzati nei DBMS esistenti per l'organizzazione dei dati
	- Utilizzati dai programmi
	- Indipendenti dalle strutte fisiche
	Esempio: **relazionale**, reticolare, gerarchico, a oggetti
- **Modelli concettuali**: permettono di rappresentare i dati in modo indipendente da ogni sistema e dal modello logico su cui è basato
	- cercano di descrivere i concetti del mondo reale
	- Sono utilizzati nelle frasi preliminari di progettazione
	Il più noto è il modello **Entità-Relazione**.
- Sono stati sviluppati modelli più moderni:
	- **Modello Entità Relazione Esteso -EER**
	- **UML**: linguaggio unificatoper la progettazione dei dati e delle funzioni.

# Progettazione di Basi di Dati

Progettare una base di dati dignifica definirne **struttura, caratteristiche** e **contenuto** prevedendo l'uso di opportune **metodologie**. In base al grado di **astrazione**, la progettazione abbiamo:
1. **Modello concettuale**: rappresenta la realtà dei dati e le relazioni tra essi attraverso uno schema
2. **Modello logico**: descrive il modo attraverso il quale i dati sono organizzati negli archivi del calcolatore
3. **Modello fisico**: descrive come i dati sono registrati nelle memorie di massa

![[BDC/img/img9.png|center|600]]

# Modello concettuale

È un metodo di rappresentazione **astratto** della realtà:
- Definire un insieme di dati presenti in natura e che rappresentano la natura stessa delle informazioni che si vogliono archiviare
- **Non esistono regole** prefissate per l'individuazione dei dati e per la loro selezione

# Progettazione della base di dati

1. Cosa c'è?
2. Come si collegano o si parlano?
3. Quanti tra loro?
4. Cosa identifica gli "oggetti"?
5. Quali informazioni utili non principali?

# Modello Entità Relazioni ER

- **ER = Entity/Relationship**
- Il modello **entità relazione** è uno strumento per analizzare le caratteristiche di una realtà in modo indipendente dagli eventi che in essa acadono, cioè per costruire un modello concettuale dei dati indipendente dalle applicazioni

# Modello Entità Relazioni ER

Insieme di oggetti simili, dati dello stesso tipo o con caratteristiche simili, raccolti insieme.
**Statici e Dinamici**

![[BDC/img/img10.png|left|300]]

Collegamento logico tra due o più entità.

![[BDC/img/img11.png|left|300]]

**Cardinalità**: Assegnazione di dimensionamento tra entità. Numero $min$ e $max$ di possibili collegamenti tra due entità tramite una relazione

![[BDC/img/img12.png|left|300]]

**Chiave**: Campo o campi indentificativi di una entità o relazione

![[BDC/img/img13.png|left|300]]
**Attributi**: Campi informativi e non identificativi di una entità o relazioni

![[BDC/img/img14.png|left|300]]
**Legame logico tra** una entità più **generale (padre)** e le entità **figlie**

![[BDC/img/img15.png|left|300]]

**Schema E-R**:

![[BDC/img/img16.png|left|300]]

# Modello ER - Entità

**Entità**

- È un oggetto, concreto o astratto, che ha un significato anche quando viene considerato in modo isolato ed è di interesse per la realtà che si vuole modellare

*EX*:

- Le persone, un modello di automobile, i movimenti contabili, le prove sostenue da uno studente
- Gli studenti vengono classificati nel tipo entità Studente
- I diversi modelli di automobile sono classificabili nel tipo entità Automobile

![[BDC/img/img17.png|center|600]]

# Modello ER - Relazione

**Relazione**

- La **relazione** (**relationship**) è un legame che stabilisce un'interazione tra le entità

*EX*:

- Tra le entità **Persona** e l'entità **Automobile** esiste l'associazione **Possedere** che può essere descritta nel linguaggio naturale secondo due versi
	- Una persona può possedere una o più automobili
	- Un'automobile deve essere posseduta da una persona

![[BDC/img/img18.png|center|600]]

- Le relazioni tra un'entità e se stessa si dicono **ricorsive o isA**

*EX*:

Esempio di relazione ricorsiva sull'entità Persona è l'associazione **Essere genitore** nella quale **Persona** partecipa con il ruolo di **Genitore** e di **Figlio**

![[BDC/img/img19.png|center|800]]

- Le relazioni tra entità si dicono **ternarie**

*EX*:

- Una relazione ternaria si scompone in diverse relazioni binarie

![[BDC/img/img20.png|center|700]]

# Modello ER - Attributi e Chiavi

**Attributi**

Le proprietà delle entità e delle relazioni sono descritte tramite gli **attributi**. Alcune caratteristiche che descrivono il **Dominio**
- **Formato**: tipi di valore che assume (carattere, numerico, data/ora,....)
- **Dimensione**: quantità max di caratteri o cifre inseribili
- **Opzionalità**: possibilità di non essere sempre valorizzato

*EX*: 

Automobile (numero telaio, modello, produttore, cilindrata, prezzo)
![[BDC/img/img21.png|center|400]]

**Attributi**

La **chiave primaria** è un attributo o un insieme di attributi che identificano univocamente un'istanza dell'entità

*EX*:

- **Automobile** (numero telaio, modello, produttore, cilindrata, prezzo)
- **Studente** (cognome, nome, data di nascita)

![[BDC/img/img22.png|center|800]]

- Anche le relazioni possono avere attributi

*EX*:

![[BDC/img/img23.png|center|800]]

- Spesso anche in presenza di chiavi palesi, si utilizza un numero progressivo come chive primaria ovvero una chiave artificiale

*EX*:

![[BDC/img/img24.png|center|900]]

- Una chiave artificiale è formata da un attributo privo di significato proprio. Di solito consiste in un conatore che si autoincrementa ad ogni istanza che si aggiunge

![[BDC/img/img24.png|center|900]]

# Modello ER - Proprietà

**Proprietà**

- Ogni entità deve avere una **chiave primaria**
- L'attributo chiave primaria **non può essere opzionale**
- La chiave primaria **non può avere valori ripetuti**

**Dominio**

- **Tipo di dato**: intero, decimale, carattere, data,...
- **Lunghezza**: numero di cifre o caratteri per rappresentare il valore dell'attributo
- **Intervallo**: limite superiore o inferiore dei valori
- **Valori**: restrizioni sui valori ammessi
- **Supporto del valore NULL**, quando non è assegnato nessun valore
- Valore di **default**

**Per le chiavi primarie**:
- Il valore deve essere unico e i NULL non sono ammessi
**Per le chiavi esterne**:
- Il tipo di dato, la lunghezza e il formato della chiave esterna devono essere uguali a quelli della corrispondente chiave primaria

# Modello ER - Entità

**Entità deboli e forti**

Sono entità che non hanno una chiave primaria e devono essere associate ad un'altra entità per essere completamente significative prendono il nome di **entità deboli**

*EX*: 

![[BDC/img/img25.png|center|900]]

*EX*: 

**Entità deboli e forti**:
- Movimento ha senso solo in relazione a Conto
- Moviemento è un entità debole
- Cliente e Conto sono entità forti
- Per evitare entità deboli, si aggiungono un numero progressivo come attributo

# Modello ER - Molteplicità

La **molteplicità** di una relazione è il numero di possibili istanze di un'entità che sono messe in corrispondenza con un'istanza dell'altra entità
- I valori **min** e **max** assunti dalla molteplicità sono rappresentati con: **(1,1), (1,N), (0,1), (0,N)
- Al valore **min** è associato il concetto di partecipazione facoltativa (**0**) o obbligatoria (**1**)
- Il valore **massimo** definisce la cardinalità della partecipazione all'associazione e assume i due valori **1** e **N**
- In relazione alla cardinalità si parla di **associazioni**:
	- **Uno a uno 1:1**
	- **Uno a molti 1:N (Molti a uno N:1)**
	- **Molti a molti N:N**

# Modello ER - Associazione 1:1

Un'associazione tra **E1** ed **E2** si dice **uno a uno** (**1:1**) quando a ogni istanza di **E1** corrisponde una sola istanza di **E2** e a ogni istanza di **E2** e a ogni istaza di **E2** corrisponde una sola istanza di **E1**.

![[BDC/img/img26.png|center|800]]


# Modello ER - Associazione 1:N

Un'associazione tra **E1** ed **E2** si dice **uno a molti** (**1:N**) quando a ogni istanza di **E1** corrispondono una o più istanze di **E2** e a ogni istanza di **E2** corrisponde una sola istanza di **E1**

![[BDC/img/img27.png|center|800]]

# Modello ER - Associzione N:N

un'associzione tra **E1** ed **E2** si dice **molti a molti** (**N:N**) quando a ogni istanza di **E1** corrispondono una o più istanze di **E2** e a ogni istanza di **E2** corrispondono una o più istanze di **E1**

![[BDC/img/img28.png|center|800]]

# Modello ER - IsA

Può accadere che sussista **l'associazione IS-A** tra due entità, e cioè che ogni istanza di una sia anche istanza dell'altra.
La associazione IS-A nel modello ER si può definire tra le due entità, che si dicono "entità padre" ed "entità figlia"

L'associazione IS-A si rppresenta nel diagramma dello schema concettuale mediante una freccia dalla sottoentità alla entità padre.
Vige la regola che un'entità può avere al massimo una entità padre. In altre parole, il modello ER non ammette ereditarietà multipla

![[BDC/img/img29.png|center|800]]

# Modello ER - Ereditarietà

**Principio di ereditarietà**: ogni proprietà dall'entità padre è anche una proprietà della sottoentità, e non si riporta esplicitamente nel diagramma.
**L'entità figlia** può avere ovviamente ulteriori proprietà.

*EX*:

**Cognome** ed **età** ereditati da **Persona** 

![[BDC/img/img30.png|center|500]]

**Principio di ereditarietà**: l'associazione IS-A si eredita, pertanto IS-A è transitiva

*EX*:

Ogni istanza di **Studente** è un'istanza di **Persona**, ogni istanza di **Fuori corso** è un'istanza di **Studente** $\rightarrow$  ogni istanza di **Fuori corso** è un'istanza di **Persona**

![[BDC/img/img31.png|center|600]]

# Modello ER - Generalizzazione

L'entità padre può generalizzare diverse sottoentità rispetto ad un unico criterio. In questo caso si parla di **generalizzazione**
Nella generalizzazione, le sottoentità hanno insiemi di istanze disgiunti a coppie.

![[BDC/img/img32.png|center|600]]

L'entità padre può generalizzare diverse sottoentità rispetto ad un unico criterio. In questo caso si parla di **generalizzazione**

![[BDC/img/img33.png|center|800]]

Il principio di ereditarietà vale anche per le generalizzazioni:
- Ogni proprietà dell'entità è padre è anche una proprietà della sottoentità, e non si riporta esplicitamente nel diagramma
- L'entità figlia può avere ovviamente ulteriori proprietà.

Vige la regola che una entità può avere al massimo una entità padre. In altre parole, il modello ER non ammette ereditarietà multipla

# UML - Diagramma delle Classi

**Definizione**: $il \ diagramma \ delle \ classi$ è un grafo che descrive i tipi degli oggetti in un sistema, le relazioni statistiche tra essi, gli attributi e le operazioni di una classe, ed i vincoli sulle relazioni 
Una classe è rappresentata da un rettangolo scomposto in tre parti:
- Il nome della classe
- Gli attributi della classe
- Le operazioni della classe

| CarSharer         |
| ----------------- |
| - Cognome: String |
| - Nome: String    |
| + setViaggio(): void                  |

# Proprietà di una classe: Attributi in UML

- Un **attributo** modella una proprietà locale della classe ed è caratterizzato da un nome e dal tipo dei valori associati
- Ogni attributo di una classe stabilisce una proprietà locale **valida per tutte le istanze** della classe. Il fatto che la proprietà sia locale significa che è un proprietà indipendente da altri oggetti
- Formalemnte, un attributo A della classe C si può considerare una funzione che associa un valore di tipo T ad **ogni** oggetto che è istanza di C

# Istanze di una classe

- Tra un oggetto che è istanza di una classe C e la classe C si traccia un arco **Instance-of**
- Ricordiamo che gli oggetti formano il livello **estensionale**, mentre le classi a livello **intensionale**

![[BDC/img/img34.png|center|800]]

# Attributi di oggetti

- Gli attributi di una classe determinano gli attributi delle sue istanze
- **Regola importante**: se una classe C ha un attributo A di tipo T, **ogni** oggetto che è istanza di C ha l'attributo A, con un valore associato di tipo T
- **Regola importante**: un oggetto X non può avere un valore per un attributo che non è definito nella classe di cui X è istanza

![[BDC/img/img35.png|center|800]]

# Identificatori degli oggetti

- Due oggetti con identificatori distinti sono comunque distinti, anche se hanno i valori di tutti gli attributi uguali
- Due oggetti diversi devono avere identificatori diversi, anche se possono avere gli stessi valori per tutti gli attributi

![[BDC/img/img36.png|center|900]]

# I diagrammi delle classi (1)

- Secondo la metodologia UML vengono definiti come $Diagrammi \ a \ struttura \ statica$. 
Vengono utilizzati per:
- Documentare le classi che compongono un sistema o un sottosistema
- Descrivere **associazioni**, **generalizzazioni aggregazioni**, fra le varie classi.
- Evidenziare le caratteristiche di una classe - **attributi** e **operazioni**

# I diagrammi delle classi (2)

- I diagrammi delle classi possono essere utilizzati in vaarie fasi dello sviluppo di un sistema:
	- In fase di **analisi**: per la specifica delle classi all'interno del dominio del problema.
	- In fase di **progettazione**: per la rappresentazione delle classi e relazioni che riflettono il modello della soluzione.
- Possono documentare come interagiscono le classi di un particolare sistema con le librerie di classi già esistenti.
- Possono essere utilizzati a rappresentare istanze di oggetti all'interno delle classi
- Possono mostrare le interfacce di una classe

# Tipi di attributi e di operazioni (1)

- $Sintassi$ $$<nomeCaratteristica>:<tipo>$$
- $Semantica$
	- $nomeCaratteristica$ identifica o un attributo oppure un'operazione
	- $Tipo$ identifica il tipo di dato dell'attributo oppure il tipo di dato restituito dall'operazione $$<nomeCaratteristica>:<tipo>$$
- $N.B$ Gli attributi e le operazioni posono essere tipizzati come classi provenienti:
	- Dalle librerie dell'ambiente d'implemetazione
	- Dal modello delle classi in uso

# Tipi di attributi e di operazioni (2)

![[BDC/img/img37.png|center|800]]


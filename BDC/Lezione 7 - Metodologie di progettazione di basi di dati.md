----
Progettare una base di dati significa definirne la struttura, le caratteristiche e contenuto e quindi prevede l'uso di opportune metodologie. In base al grado di astrazione, la progettazione prevede: 
- **Modello concettuale**: rappresenta la realtà dei dati e le relazioni tra essi attraverso uno schema.
- **Modello logico**: descrive il modo attraverso il quale i dati sono organizzati negli archivi del calcolatore.
- **Modello fisico**: descrive come i dati sono registrati nelle memorie di massa.
Obiettivo della fase di progettazione logica è prevenire, a partire dallo schema concettuale, a uno schema logico che lo rappresenti in modo fedele, efficiente e indipendente dal particolare DBMS adottato. A tal fine questa fase di progettazione può essere suddivisa in 2 passi:
- *Ristrutturazione dello schema Entità-Relazione*: è una fase indipendente dal modello logico e si basa su criteri di ottimizzazione dello schema;
- *Traduzione verso il modello logico*: fa riferimento ad uno specifico modello logico (**modello relazionale**)

Il ciclo di vita di un sistema informativo comprende le seguenti attività:
- *Studio di fattibilità*: definisce le varie alternative possibili, i relativi costi e le priorità di realizzazione.
- *Raccolta e analisi dei requisiti*: individua le proprietà e le funzionalità del sistema tramite l'interazione con gli utenti del sistema e definisce in maniera completa ma informale i dati coinvolti e le operazioni svolte su di esse
- *Progettazione*: si divide in progettazione dei dati e delle applicazioni. Individua la struttura e l'organizzazione dei dati e delle caratteristiche degli applicativi che vi dovranno accedere.
- *Implementazione*: realizza la base di dati e il codice dei programmi conformemente alle specifiche
- *Validazione e collaudo*: verifica il corretto funzionamento del sistema informativo
- *Funzionamento*: il sistema informativo diviene operativo

![[BDC/img/img59.png|center|500]]

Una metodologia di programmazione consiste in: 
- una *decomposizione* dell'intera attività di progetto in passi successivi indipendenti tra loro;
- una serie di *strategie* da seguire nei vari passi e alcuni *criteri* per la scelta in caso di alternative;
- alcuni *modelli di riferimento* per descrivere i dati di ingresso e uscita delle varie fasi.
Le proprietà che una metodologia deve garantire sono:
- la *generalità* rispetto alle applicazioni e ai sistemi in gioco;
- la *qualità del prodotto* in termini di correttezza, completezza ed efficienza rispetto alle risorse impiegate;
- la *facilità d'uso* delle strategie e dei modelli di riferimento.

Nell'ambito delle basi di dati, si è consolidata negli anni una metodologia di progetto che soddisfa le proprietà descritte. Questa metodologia si suddivide in tre fasi da effettuare a cascata.
- *Progettazione concettuale*: rappresenta le specifiche informali in modo formale e completo, ma indipendente dalla rappresentazione usata nei DBMS. Produce lo schema concettuale e fa riferimento ad un modello concettuale dei dati. Rappresenta il contenuto informativo e non la codifica.
- *Progettazione logica*: traduce lo schema concettuale nello schema logico basato su un modello logico (es. modello relazionale) ancora indipendente dalla realizzazione fisica della base di dati.
- *Progettazione fisica*: completa lo schema logico con la specifica dei parametri fisici di memorizzazione dei dati. Produce uno schema fisico e fa riferimento ad un modello fisico dei dati, dipendente dal DBMS.
Si fa distinzione tra *specifiche sui dati* e *specifiche sulle operazioni*, che riguardano l'uso che utenti e applicazioni fanno della base di dati.
- Nella *progettazione concettuale* si usano le specifiche sui dati mentre le specifiche sulle operazioni servono a verficare che lo schema concettuale contenga tutte le informazioni necessarie alla loro esecuzione
- Nella *progettazione logica* lo schema concettuale riassume le specifiche sui dati, mentre le specifiche sulle operazioni si utilizzano per ottenere uno schema logico che permetta di eseguire le operazioni in modo efficiente. Bisogna conoscere il modello logico ma non il DBMS adottato.
- Nella *progettazione fisica* si fa uso dello schema logioc e delle specifiche sulle operazioni per ottimizzare le prestazioni del sistema.

# Progettazione concettuale
Consiste nella costruzione di uno schema E-R in grado di descrivere al meglio le specifiche sui dati di un'applicazione. Si suddivide in due fasi: 
- *Raccolta e analisi dei requisiti*, ovvero l'individuazione dei problemi da risolvere e l'organizzazione delle specifiche
- *Definizione dello schema E-R*

## Raccolta e analisi dei requisiti
Per *raccolta dei requisiti* si intende l'individuazione dei problemi che l'applicazione da realizzare deve risolvere e le caratteristiche che tale applicazione dovrà avere. I requisiti vengono spesso espressi con frasi in linguaggio naturale e per questo motivo sono spesso ambigue e disorganizzate. L'*analisi dei requisiti* consiste nel chairimento e nell'organizzazione delle specifiche dei requisiti. I requisiti di un'applicazione da fonti diverse tra cui:
- gli *utenti dell'applicazione*, bisogna tenere in conto che utenti diversi possano fornire informazioni diverse, a volte complementari o contraddittorie. Degli utenti a livello più alto possiedeono una visione più ampia, ma meno dettagliata.
- la *documentazione esistente* che ha attinenza con il problema considerato.
- *realizzazioni preesistenti* ovvero applicazioni che si devono rimpiazzare o che devono interagire con il sistema da realizzare.
Fissiamo delle regole generali per ottenere una specifica dei requisiti più precisa e senza ambiguità.
- scegliere il *corretto livello di astrazione*: evitare l'utilizzo di termini generici o troppo specifici
- *standardizzare le frasi* ovvero utilizzare sempre lo stesso stile sintattico, ad esempio "per \<dato\> rappresentiamo \<insieme di proprietà\>" 
- *evitare frasi contorte*. Le definizioni devono essere semplici e chiare
- *individuare sinonimi/omonimi ed eliminarli*
- *esplicitare i riferimenti tra termini*
- costruire un *glossario dei termini* che, per ogni termine, contenga una breve descrizione, i possibili sinonimi e altri termini.

### Esempio di raccolta e analisi dei requisiti
![[BDC/img/img60.png|center|500]]
![[BDC/img/img61.png|center|500]]

![[BDC/img/img62.png|center|500]]

**Glossario dei termini**
![[BDC/img/img63.png|center|500]]

**Strutturazione requisisti in gruppi di frasi omogenee**
![[BDC/img/img64.png|center|500]]
![[BDC/img/img65.png|center|500]]
![[BDC/img/img66.png|center|500]]
![[BDC/img/img67.png|center|500]]

## Criteri di rappresentazione
- Se un concetto ha proprietà significative e/o descrive classi di oggetti con esistenza autonoma, si rappresenta con una **entità**; 
	- nell'[[#Esempio di raccolta e analisi dei requisiti|esempio]], il concetto di *docente* è rappresentato con un'entità, in quanto possiede diverse proprietà (cognome, età, città di nascita) e la sua esistenza è indipendente dagli altri concetti.
- Se un concetto ha una struttura semplice e non possiede proprietà rilevanti associate, si rappresenta con un **attributo** di un altro concetto a cui si riferisce; 
	- nell'[[#Esempio di raccolta e analisi dei requisiti|esempio]], il concetto di *età* è certamente da rappresentare come attributo. Anche il concetto di *città*, che può rappresentare un concetto autonomo, va rappresentato come attributo, in quanto l'unica proprietà di suo interesse è il nome.
- se sono state individuate due (o più) entità e nei requisiti compare un concetto che le associa, quest'ultimo si rappresenta con una **relazione**;
	- nell'[[#Esempio di raccolta e analisi dei requisiti|esempio]], il concetto di *partecipazione ad un corso* è rappresentabile da una relazione tra le entità che rappresentano i *partecipanti* e i *corsi*. Questo vale solo nel caso in cui il concetto in questione non abbia le caratteristiche delle entità, come ad esempio il concetto di *visita* relativo a pazienti e medici (di una visita sono di interesse diverse proprietà quali la data, l'orario e la diagnosi).
- se uno o più concetti risultano essere casi particolari di un altro, si rappresentano facendo uso di una **generalizzazione**;
	- nell'[[#Esempio di raccolta e analisi dei requisiti|esempio]], i concetti di *professionista* e *dipendente* costituiscono dei casi particolari del concetto di *partecipante* ed è quindi indicato definire una generalizzazione tra le entità che rappresentano questi concetti.

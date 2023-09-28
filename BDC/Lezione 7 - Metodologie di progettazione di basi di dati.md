]----
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

# Strategie di progetto
Lo sviluppo di uno schema concettuale può essere considerato un processo di ingegnerizzazione e quindi risultano applicabili le strategie di progetto utilizzate anche in altre discipline.

## Strategia top-down
Lo schema concettuale viene prodotto mediante una serie di raffinamenti successivi a partire da uno schema iniziale che descrive le specifiche con pochi concetti molto astratti. Lo schema viene poi raffinato mediante opportune trasformazioni che aumentano il dettaglio dei vari concetti presenti. Nel passaggio da un livello di raffinamento ad un altro, lo schema viene modificato facendo uso di alcune trasformazioni elementari che vengono denominate *primitive di trasformazione top-down*.

![[BDC/img/img68.png|center|500]]

Esempi di primitive di trasformazione top-down sono:
- T1, da *entità a relazione fra entità*: si applica quando si verifica che una entitià descrive due concetti diversi legati logicamente fra loro.
- T2, da *entità a generalizzazione*: si applica quando una entità è composta da sotto-entità distinte o, più in generale, che uno stesso concetto può in realtà comprendere più concetti.
- T3, da *relazione a insieme di relazioni*: si applica quando una relazione descrive in realtà due o più relazioni fra le medesime entità; tipicamente le due relazioni sono due specializzazioni di una relazione più generale.
- T4, da *relazione ad entità con relazioni*: si applica quando una relazione descrive un concetto con esistenza autonoma ai fini dell'applicazione o concetti di cui si possono avere più occorrenze.
- T5, *introduzione di attributi in una entità*: si applica per introdurre nuovi attributi ad una entità, che aiutino a descriverla in modo più completo.
- T6, *introduzione di attributi su relazioni*: si applica per aggiungere proprietà a relazioni

![[BDC/img/img69.png|center|500]]

## Strategia bottom-up
In questa strategia le specifiche iniziali sono suddivise in componenti sempre più piccole, fino a quando queste componenti descrivono un frammento elementare della realtà di interesse. Le componenti vengono poi fuse con trasformazioni successive, dette *primitive di trasgormazione bottom-up*, per giungere allo schema concettuale finale.
Ogni trasformazione introduce nuovi concetti non presenti al livello precedente.

![[BDC/img/img70.png|center|500]]

Esempi di primitive di trasformazione bottom-up sono:
- T1, *generazione di entità*: si applica quando si individua nelle specifiche una classe di oggetti caratterizzata da proprietà comuni.
- T2, *generazione di relazione*: si applica quando nelle specifiche si individua un legame logico fra entità.
- T3, *generazione di generalizzazione*: si applica quando si individua un legame fra diverse entità riconducibile ad una generalizzazione, quando cioè le diverse entità possono essere istanze di una stessa classe
- T4, *aggregazione di attributi su entità*: si applica quando si individua una entità che può essere rappresentata come aggregazione di attributi presenti nelle specifiche.
- T5, *aggregazione di attributi su relazione*: analoga a T4, ma relativa ad una relazione.

## Strategia inside-out
Questa strategia può essere vista come un caso particolare della strategia [[#Strategia bottom-up|bottom-up]]: individua solo alcuni concetti importanti, per poi procedere a macchia d'olio.
Si rappresentano prima i concetti più vicini a quelli di partenza, per poi sviluppare quelli più lontani attraverso una navigazione nelle specifiche.

![[BDC/img/img71.png|center|500]]

Nella figura è mostrato un esempio di sviluppo inside-out. Si osserva che inizialmente è stata individuata l'entità **IMPIEGATO** con i suoi attributi e a partire da questa sono state rappresentate la partecipazione degli impiegati ai progetti e tutte le proprietà dei progetti. Successivamente sono state analizzate le correlazioni esistenti tra gli impiegati e i dipartimenti dell'azienda, individuando le relazioni ***Direzione*** e ***Afferenza*** e l'entità **DIPARTIMENTO**, con i relativi attributi. Infine, partendo da quest'ultima entità, sono state rappresentate le sedi dell'azienda (entità **SEDE**) e l'appartenenza dei dipartimenti alle relative sedi (relazione ***Composizione***). Nella penultima fase non si poteva identificare l'entità **DIPARTIMENTO** (a meno di aggiungere altri attributi), perché è possibile avere dipartimenti con lo stesso nome in sedi diverse, ma, al passo successivo, è stato possibile identificare tale entità con l'attributo *Nome* e l'entità **SEDE** attraverso la relazione ***Composizione***.

I vantaggi di questa strategia sono di non richiedere passi di integrazione, ma d'altra parte è necessario di volta in volta esaminare tutte le specifiche e descrivere i nuovi concetti nel dettaglio. Non è quindi possibile procedere per livelli di astrazione come avviene nella strategia [[#Strategia top-down|top-down]].

## Strategia mista
Cerca di unire i vantaggi delle strategie [[#Strategia top-down|top-down]] e [[#Strategia bottom-up|bottom-up]]. Si suddividono i requisiti in componenti separate, come nella strategia bottom-up, ma allo stesso tempo si definisce uno *schema scheletro* contenente, a livello astratto, i concetti principali dell'applicazione. Questo schema fornisce una visione unitaria dell'intero progetto e favorisce le fasi di integrazione degli schemi sviluppati separatamente. Conteporaneamente, dalle specifiche, si creano in modo bottom-up i concetti non presenti nello schema scheletro.
Questa strategia si adatta ad esigenze opposte di suddivisione di un problema complesso in sottoproblemi e di procedura per raffinamenti successiva, in pratica ingloba anche la strategia inside-out.
Spesso è l'unica strategia utilizzabile, perché è necessario cominciare la progettazione quando non sono ancora disponibili tutti i dati e, dei dati noti abbiamo delle conoscenze a livello di dettaglio non omogenei.

# Qualità di uno schema concettuale
Nella costruzione di uno schema concettuale vanno garantite alcune proprietà generali che uno schema di buona qualità deve avere. Analizziamo le più importanti:
- **Correttezza**: uno schema concettuale è corretto quando utilizza propriamente i costrutti messi a disposizione dal modello concettuale di riferimento. 
- **Completezza**: uno schema concettuale è completo quando rappresenta tutti i dati di interesse e quando tutte le operazioni possono essere eseguite a partire dai concetti descritti nello schema.
- **Leggibilità**: uno schema è leggibile quando rappresenta i requisiti in maniera naturale e facilmente comprensibile. Per garantire questa proprietà è necessario rendere lo schema autoesplicativo, mediante una scelta opportuna dei nomi da dare ai concetti.
- **Minimalità**: uno schema è minimale quando tutte le specifiche sono rappresentate una volta sola, quindi non esistono *ridondanze*. Non sempre, però, una ridondanza è indesiderata, ma può nascere da scelte progettuali.

# Una metodologia generale
Tiriamo le somme su quanto detto relativamente alla progettazione concettuale di basi di dati. Per quanto riguarda le strategie di progetto, nella pratica non accade quasi mai che un progetto proceda *sempre* in modo top-down o bottom-up. Nelle situazioni reali capita infatti di modificare lo schema in via di costruzione sia con *trasformazioni che raffinano un concetto presente* (metodologia top-down) sia con *trasformazioni che aggiungono un concetto non presente* (tipicamente bottom-up). Presentiamo quindi una metodologia per la progettazione concettuale con il modello E-R con riferimento alla [[#Strategia mista|strategia mista]].
La metodologia è composta dai seguenti passi:
1. **Analisi dei requisiti**
	- Costruire un glossario dei termini
	- Analizzare i requisisti ed eliminare le ambiguità presenti
	- Raggruppare i requisiti in insiemi omogenei
2. **Passo base**
	- Individuare i concetti più rilevanti e rappresentarli in uno schema scheletro
3. **Passo di decomposizione**: da effettuare se appropriato e necessario
	- Effettuare una decomposizione dei requisiti con riferimento ai concetti presenti nello schema scheletro
4. **Passo iterativo**: da ripetere per tutti i sotto-schemi, finché ogni specifica è stata rappresentata
	- Raffinare i concetti presenti sulla base delle loro specifiche
	- Aggiungere nuovi concetti allo schema per descrivere specifiche non ancora descritte
5. **Passo di integrazione**: da effettuare se è stato eseguito il passo 3
	- Integrare i vari sotto-schemi in uno schema generale facendo riferimento allo schema scheletro
6. **Analisi di qualità**:
	- Verificare la correttezza dello schema ed eventualmente ristrutturare lo schema
	- Verificare la completezza dello schema ed eventualmente ristrutturare lo schema
	- Verificare la minimalità, documentare le ridondanze ed eventualmente ristrutturare lo schema
	- Verificare la leggibilità dello schema ed eventualmente ristrutturare lo schema

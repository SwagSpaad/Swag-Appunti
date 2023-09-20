# Modelli dei Dati

Un modello dei dati è un insieme di concetti utilizzati per organizzare i dati di interesse e descrivere la struttura in modo che essa risulti comprensibile a un elaboratore.
Ogni modello dei dati fornisce **meccanismi di strutturazione**, analoghi ai costruttori di tipo dei linguaggi di programmazione, che permettono di definire nuovi tipi sulla base di tipi predefiniti e costruttori di tipo.

*EX:*
Il C permette di costruire tipi per mezzo dei costruttori **struct, union, enum, puntatori**.

Il **modello di relazione** dei dati più diffuso, permette di definire tipi per mezzo del costruttore **relazione**, che consente di organizzare i dati in insiemi di record a struttura fissa. Una relazione viene spesso rappresentata per mezzo di una tabella, le cui righe rappresentano specifici record e le cui colonne corrispndono ai campi record; l'ordine delle righe e colonne è irrilevante.

# DBMS vs file system

- L'efficienza di un sistema si misura in termini di:
	- **Tempo** di esecuzione e spazio
	- **Memoria** 
- I DBMS, a causa della varietà di funzioni, non sono necessariamente più efficienti dei file system.
- L'efficienza è il risultato della qualità del DBMS e delle applicazioni che lo utilizzano.
- La gestione di insieme di dati grandi è possibile attraverso gli ordinari file system dei sistemi operativi
- I file system prevedono una condivisione ridotta, mentre i DBMS sono più flessibili
- I DBMS estendono le funzionalità dei file system, fornendo più servizi.
- Nei programmi tradizionali che accedono a file, ogni programma contiene una descrizione della struttura del file, portando a rischi di incoerenze fra descrizioni e file stessi.
- Nei DBMS, esiste una porzione della base di dati che contiene una descrizione centralizzata dei dati, che può essere utilizzata da vari programmi

# Modello di rappresentazione dei dati

- I DBMS non sono progettati per gestire un unico caso d'uso ma sono in grado di gestire dati eterogenei.
- Al fine di creare e gestire la corrispondente base di dati uno schema deve essere fornito al DBMS
- Lo schema viene costruito secondo un modello di dati bene definito.
- Tramite questo schema dei dati si fornisce al DBMS una rappresentazione dei dati, in modo tale che permette l'organizzazione della gestione.
- Le descrizioni e rappresentazioni dei dati a livelli diversi permettono l'indipendenza dalla rappresentazione fisica:
- I programmi fanno riferimento alla struttura a livello più alto, e le rappresentazioni sottostanti possono essere modificate senza necessità di modifiche dei programmi

**Modello di dati:** insieme di costrutti utilizzati per organizzare i dati di interesse e descriverne la **struttura** e la **dinamica**.
- Un **modello** di dati è costruito da:
	- **Costrutti sintattici** per definire i dati 
	- **Regole semantiche** per interpretare i dati
	- **Linguaggi** per manipolare i dati

# Organizzazione dei dati in una base di dati relazionale

| Nome  | Cognome | Matricola | Voto Medio |
| ----- | ------- | --------- | ---------- |
| Mario | Rossi   | 1         | 24         |
| Luigi | Bianchi | 2         | 28         |
| Rosa  | Rossa   | 3         | 26         | 

# Modelli per la rappresentazione dei dati nei DBMS

- Insieme di costrutti utilizzati per organizzare i dati di interesse e descrivere le operazioni su di essi
- Ci sono due tipi di modelli
	- **Modello logico**
	- **Modello concettuale**

# Modello Logico
- Esistono diversi tipi di modelli logici
- Descrivono l'organizzazione dei dati nei DBMS "**visibile**" all'utente
- Sono indipendenti dalle strutture fisiche
	- **Gerarchico** e **reticolare**
		- Utilizzano riferimenti espliciti fra record
	- **Modello ad oggetti** 
		- L'informazione è rappresentata in forma di oggetti
		- Utilizzate in un mercato di nicchia rispetto al modello relazionale 
	- **Relazionale** è basato su valori
		- Anche i riferimenti fra dati in strutture diverse sono rappresentati per mezzo dei valori stessi

# Modello Concettuale 

- Hanno l'obiettivo di descrivere i **concetti** del mondo reale
- Utilizzati nelle fasi iniziali  della progettazione
- **Entity-Relationship** e **Modello Classi Associazioni**

![[2.png|center|700]]

# Schemi e istanze

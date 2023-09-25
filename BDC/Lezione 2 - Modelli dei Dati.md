----
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

^e111c5

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

![[BDC/img/img2.png|center|700]]

# Schemi e istanze

- In ogni base di dati esistono: 
	- Lo **schema**, sostanzialmente invariante nel tempo che descrive la struttura ([[#^e111c5|intestazioni della tabella precedente]])
	- **L'instanza**, i valori attuali, che possono cambiare anche molto rapidamente ([[#^e111c5|il 'corpo' nella tabella precedente]])

# Architettura standard a tre livelli per DBMS

![[BDC/img/img3.png|center|500]]

# Architettura di un DBMS: schemi

- **Schema logico**: descrizione della base di dati nel modello logico del DBMS
- **Schema fisico**: rappresentazione dello schema logico per mezzo di strutture memorizzazione (file)
- **Schema esterno**: descrizione di parte della base di dati di un modello logico ('viste' parziali, derivate, anche in modelli diversi)

# Viste

| Corso        | Docente | Aula |
| ------------ | ------- | ---- |
| Basi di dati | Rossi   | DS3  |
| Analisi 1    | Neri    | N3   |
| Fisica 2     | Bruni   | N3   |
| Fisica 1     | Bruni   | G    ||              |         |      |


| Nome | Edificio   | Piano |
| ---- | ---------- | ----- |
| DS1  | Sogene     | Terra |
| N3   | PP2        | Terra |
| G    | Ingegneria | Primo |     |            |       |


| Corso     | Aula | Edificio     | Piano |
| --------- | ---- | ------------ | ----- |
| Analisi 1 | N3   | PP2          | Terra |
| Fisica 2  | N3   | PP2          | Terra |
| Fisica 1  | G    | Ignegneria 1 | Primo |          |      |              |       |

# Schemi dei dati

*EX*
- Un'analogia con il mondo della prgrammazione facendo riferimento alle matrici.
- **Livello concettuale**: 
			int a \[n] \[m]; 
- **Livello fisico**: (con $a_{0}$= locazione iniziale)
	A \[i] \[j] è nella locazione $a_{0} +4(m(i-1)+j-1)$
- **Schema Fisico**: il vettore è memorizzato in4(n\*m) celle contigue a partire dalla locazione $a_0$ (un intero è rappresentato con 4 byte)
- **Schema concettuale**: è costituito dalla dichiarazione
			int a \[n] \[m];
	dove, A è una matrice di interi con n righe e m colonne.
- **Schema esterno(vista)**: un possibile schema è costituito dalla seguente funzione. $$f(i)= \sum ^{m}_{j=1} a[i][j]$$
# Indipendenza dei dati

- L'indipendenza dei dati è una conseguenza della articolazione in livelli
- L'accesso avviene solo tramite il livello esterno (che può coincidere con il livello logico)
- Due forme:
	- **Indipendenza fisica**
	- **Indipendenza logica**

# Indipendenza logica

- **Indipendenza fisica**: livello logico e quello esterno sono indipendenti da quello fisico
	- Una relazione è utilizzata nello stesso modo qualunque sia la sua realizzazione fisica
	- La **realizzazione fisica** può cambiare senza che debbano essere modificati i programmi 

# Indipendenza logica

- **Indipendenza logica**: livello esterno è indipendente da quello logico
	- aggiunte o modifiche alle viste non richiedono modifiche al livello logico
	- modifiche allo schema logico che lasciano inalterato lo schema esterno sono trasparenti

# Linguaggio per Basi di Dati

- Operazioni sullo schema
	- **DDL**: data definition lenguage
- Operazioni sui dati ^5d3602
	- **DML**: data manipulation language

# Operazione DDL (schema)

**create table** *orario (*
	*insegnamento* **char(20)**, 
	*docente*           **char(20)**, 
	*aula*                  **char(4)**  ,
	*ora*                    **char(5)**  ,  

# Operazione DML (istanza)

**select** *docente*
**from** *orario*
**where** *aula= 'N1'*

# [[#^5d3602|-]]

- I DBMS dispongono di vari linguaggi e interfacce diverse 
	- **I linguaggi testuali interattivi** *(SQL)*
	- **Comandi** immersi in un linguaggio ospite (Java, C, Spark, etc.)
	- **Comandi** immersi in un linguaggio ad hoc, con anche altre funzionalità, anche con l'ausilio di strumenti di sviluppo
	- **Con interfacce amichevoli**

# SQL, linguaggio interattivo

**Select** *Corso, Aula, Piano*
**From** *Aule, Corsi*
**Where** *Aula = 'N3'*
**And** *Piano='Terra'*

| Corso     | Aula | Piano |
| --------- | ---- | ----- |
| Analisi 1 | N3   | Terra |
| Fisica 2  | N3   | Terra ||           |      |       |

# SQL immerso in un linguaggio ad alto livello

write('nome della citta''?'); readln(citta);

EXEC SQL DECLARE P CURSOR FOR SELECT NOME, REDDITO  
FROM PERSONE  
WHERE CITTA = :citta ;

 EXEC SQL OPEN P ;

 EXEC SQL FETCH P INTO :nome, :reddito ;

while SQLCODE = 0 do begin  
	write('nome della persona:', nome, 'aumento?'); readln(aumento);  
	EXEC SQL UPDATE PERSONE SET REDDITO = REDDITO + :aumento
			WHERE CURRENT OF P
	EXEC SQL FETCH P INTO :nome, :reddito
	end;
EXEC SQL CLOSE CURSOR P

# SQL immerso in un linguaggio ad hoc

declare Stip number;
begin
	select Stipendio into Stip
	from Impiegato
	where Matricola = '575488'
	for update of Stipendio;
	if Stip > 30 then
		update Impiegato set Stipendio = Stipendio * 1.1 where Matricola = '575488';
	else
		update Impiegato set Stipendio = Stipendio * 1.15 where Matricola = '575488';
	end if;
	commit;
exception
	when no_data_found then
		  insert into Errori
		  values('Non esiste la matricola specificata',sysdate);
end;

# Intestazione non testuale

![[BDC/img/img4.png|center|500]]

# Basi di dati : professionalità

- **Progettisti** e realizzatori di **DBMS**
- **Progettisti della base di dati** e amministratori della base di dati (**DBA**)
- **Progettisti** e programmatori **di applicazioni** 
- **Utenti** 
	- Utenti **Finali**: eseguono applicazioni predefinite (**transazioni**)
	- Utenti **Casuali**: eseguono operazioni non previste a priori, usando linguaggi interattivi

# Database administrator

- Persona o gruppo di persone responsabili del controllo centralizzato e della gestione del sistema, delle prestazioni, dell'affidabilità, delle autorizzazioni
- Le funzioni del DBA includono quelle di progettazione, anche se in progetti complessi ci possono essere distinzioni

# Transazioni

- Programmi che realizzano attività frequenti e predefinite, con poche eccezioni, previste a priori.
- Le transazioni sono di solito realizzate con programmi in linguaggio ospite
- **N.B**: il termine **transazione** ha un'altra eccezione, più specifica: sequenza indivisibile di operazioni (o tutte o nessuna)

# Vantaggi e svantaggi del DBMS

### Pro

- Dati come risorsa comune, base di dati come modello della realtà
- Gestione centralizzata con possibilità di standardizzazione ed 'economia di scala'
- Disponibilità di servizi integrati
- Riduzione di ridondanze e inconsistenze
- Indipendenza dei dati

### Contro

- Costo dei prodotti e della transizione verso di essi
- Non scorporabilità delle funzionalità
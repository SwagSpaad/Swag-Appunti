----
# Sistemi informativi, informazioni e dati

Nello svolgimento di ogni attività, sono essenziali la disponibilità di informazioni e la capacità di gestirle in modo efficace. Ogni organizzazione è dotata di un **sistema informativo**, che organizza e gestisce le informazioni necessarie per raggiungere gli scopi dell'organizzazione stessa. Per indicare la porzione automatizzata del sistema informativo utilizziamo il termine **sistema informatico**. 

Nel sistema informatico le informazioni vengono rappresentate per mezzo di dati che hanno bisogno di un'interpretazione per fornire informazioni. 
Qual è la differenza tra *dato* e *informazione*? Possiamo dire che i dati da soli non hanno significato, ma una volta interpretati e associati opportunamente, essi forniscono delle informazioni.

Definiamo generalmente una **base di dati** come una collezione di dati utilizzati per rappresentare le informazioni di un sistema informativo.

# Basi di dati e sistemi di gestione

Un'approccio convenzionale alla gestione dei dati sfrutta la presenza di file per memorizzare i dati in modo persistente sulla memoria di massa. Questi file consentono di memorizzare e ricercare dati, ma fornisce semplici metodi di accesso e di condivisione. Eventuali dati di interesse per più programmi, sono replicati tante volte quanti sono i programmi che li utilizzano, con ridondanza e possibilità di incoerenza, a lungo andare ci sarebbero molti dati con le varie copie non aggiornate allo stesso modo.

Le basi di dati sono state concepite per gestire le informazioni di interesse per diversi soggetti e limitare i rischi di ridondanza e incoerenza. 

Un sistema di **gestione di base di dati (DBMS)** è un sistema software in grado di gestire collezioni di dati grandi, condivise e persistenti, assicurando la loro affidabilità e privatezza.

Le basi di dati sono: 
- *grandi*: hanno dimensioni enormi e in generale molto maggiori della memoria centrale disponibile; ordine delle centinaia di terabyte.
- *condivise*: applicazioni e utenti diversi devono poter accedere a dati comuni.
- *persistenti*: hanno un tempo di vita che non è limitato a quello delle singole esecuzioni dei programmi che le utilizzano.
I DBMS garantiscono *affidabilità* e *privatezza*, rispettivamente la capacità del sistema di conservare intatto il contenuto del DB in caso di malfunzionamenti hardware e software e l'utilizzo di meccanismi di autorizzazione per ciscun utente abilitato a svolgere solo determinate azioni sui dati. I DBMS sono *efficienti*, cioè capaci di svolgere le operazioni utilizzando un insieme di risorse (tempo e spazio) che sia accettabile per gli utenti, ed *efficaci*, in quanto capaci di rendere produttive le attività dei loro utenti.

L'approccio basato su DBMS va oltre l'uso di file locali gestiti dalle singole applicazioni tramite l'adozione di un sistema di gestione dei dati che risulta indipendente dalle applicazioni e specializzato in tale funzione. I dati non sono gestiti dalle singole applicazioni, ma da un DBMS che offre un'interfaccia comune a tutte le applicazioni.

![[BDC/img/img1.png|center|500]]


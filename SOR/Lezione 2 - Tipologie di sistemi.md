# Lezione 2
## Sistemi a singolo processore. Sistemi multiprocessore. Struttura del sistema operativo. Tipologie di sistemi operativi. 
---
## Sistemi a singolo processore
Fino a qualche anno fa, la maggior parte dei computer era dotata di un singolo processore. Un sistema di questo tipo è composto da un processore principale, in grado di eseguire le istruzioni dei programmi e di altri processori per usi speciali, come ad esempio le schede grafiche, controller di dischi ecc. Questi processori specializzati eseguono un set d’istruzioni che riguardano i rispettivi dispositivi ma non eseguono istruzioni dei programmi utente.  
L’uso di microprocessori specializzati è diffuso, ma la presenza di tali processori non rende un sistema a singolo processore un sistema multiprocessore.  
## Sistemi multiprocessori 
Questi sistemi, anche detti **multicore**, hanno due o più processori, condividono i bus del computer, la memoria e le periferiche. Un vantaggio di un sistema multiprocessore rispetto un sistema monoprocessore è l'**aumento della produttività**, perchè la presenza di più processori implica che possono essere eseguiti più programmi in parallelo. I sistemi multiprocessore sono più affidabili, poiché essendo le funzioni distribuite tra diversi processori, in caso di guasto di uno di essi il sistema continua a funzionare, anche se più lentamente. I sistemi che subiscono un guasto di uno o più componenti e continuano a funzionare sono detti **fault tolerant**.  
Gli attuali sistemi multiprocessore si distinguono in: **multiprocessori simmetrici (SMP) e asimmetrici (AMP)**.  
### Multiprocessori asimmetrici
Questi sistemi utilizzano il **asymmetric multiprocessing** (AMP), in cui ad ogni processore è assegnato un compito specifico, seguendo il modello **master-worker** in cui un processore **master** controlla il sistema e assegna i compiti ai processori worker.
### Multiprocessori simmetrici
I sistemi più diffusi utilizzano il **symmetric multiprocessing**, che prevede che ogni processore esegua tutte le funzioni del sistema operativo. SMP significa che tutti i processori sono pari; non esiste alcun rapporto gerarchico tra i processori.  

![[img2.png|center|500]]  
Ogni processore ha il proprio insieme di registri e una cache privata. Tuttavia, tutti i processori condividono la memoria fisica. Il vantaggio di questo modello è che molti processi possono essere eseguiti in parallelo. 
I sistemi multiprocessore possono avere due modelli di accesso alla memoria: **accesso alla memoria uniforme (UMA) e accesso alla memoria non uniforme (NUMA)**. UMA è una tecnica in cui l’accesso a qualsiasi RAM da qualsiasi processore richiede la stessa quantità di tempo. Con NUMA, alcune parti della memoria possono richiedere più tempo di accesso rispetto ad altre parti, creando una riduzione delle prestazioni.  
Una recente tendenza nella progettazione dei processori è quella di includere più core di elaborazione su un unico chip. Tali sistemi sono detti multicore e sono più efficienti di multipli chip separati, perchè la comunicazione in uno stesso chip è più veloce della comunicazione tra chip separati. Inoltre un sistema multicore utilizza meno energia rispetto a più chip single-core.
### Cluster
Un altro tipo di sistema multiprocessore è il **cluster**. Questi sistemi differiscono dai sistemi multiprocessore in quanto sono costituiti da più computer (detti nodi) connessi tra loro. Questi computer condividono la memoria secondaria e sono connessi tramite LAN o un bus di interconnessione veloce. 
![[img3.png|center|500]] 
Il cluster è utilizzato per fornire alta affidabilità di un servizio, ovvero in caso di guasto di uno o più computer del cluster, il servizio continua a funzionare. Un cluster può essere strutturato in modo **asimmetrico** o **simmetrico**.  
Nel cluster asimmetrico una macchina è in modalità hot-standby, mentre le altre eseguono le applicazioni. La macchina host in hot-standby ha il compito di monitorare i nodi attivi e, in caso di guasto di uno dei nodi, diventa un server attivo.  
Nel cluster simmetrico, due o più nodi eseguono applicazioni e si controllano a vicenda. Questa architettura è più efficiente in quanto sfrutta tutti l'hardware a disposizione.  
Con cluster costituiti da molti computer collegati tramite una rete, è possibile ottenere ambienti di calcolo ad alte prestazioni.  
## Struttura del sistema operativo

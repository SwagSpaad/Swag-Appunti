# Lezione 4
## Principali componenti del SO e gestione dei processi
---
## Principali componenti del SO
Possiamo distinguere i sistemi operativi in base al tipo di applicazioni per cui sono stati progettati:
- **[[Lezione 2 - Tipologie di sistemi#Sistemi operativi batch multiprogrammati|Sistemi operativi batch]]**: per applicazioni di calcolo intensivo (applicazioni CPU-bound) con l’obiettivo di ottimizzare l’efficienza d’uso delle risorse.
- **[[Lezione 2 - Tipologie di sistemi#Sistemi operativi time-sharing|Sistemi operativi time-sharing]]**: per applicazioni interattive (I/Obound) con l’obiettivo di minimizzare i tempi medi di risposta.
- **[[Lezione 2 - Tipologie di sistemi#Sistemi real-time|Sistemi operativi real-time]]**: per applicazioni di controllo, con l’obiettivo di rispettare tutti i loro vincoli temporali (deadline).
- **[[Lezione 2 - Tipologie di sistemi#Sistemi operativi per personal computer|Sistemi operativi per personal computer]]**: per l’uso di un singolo utente al fine di massimizzare la semplicità d’uso e le prestazioni.
- **[[Lezione 3 - Operazioni del sistema operativo#Sistemi paralleli e distribuiti|Sistemi operativi distribuiti e di rete]]**: per applicazioni distribuite con l’obiettivo di condividere risorse.
Nonostante le architetture e gli obiettivi siano diversi, queste tipologie di SO hanno delle caratteristiche in comune nei componenti che svolgono le funzioni fondamentali di: 
- **Gestione dei processi**
- **Gestione della memoria**
- **Gestione dell'I/O**
- **Gestione del file system**
- **Protezione e sicurezza** 
- **Comunicazione su rete**

### Gestione dei processi
I sistemi operativi multiprogrammati consentono l'esecuzione di più programmi in modo concorrente. Un compito importante del sistema operativo è pianificare e far avanzare l'esecuzione dei programmi utente. A causa della complessità, un sistema operativo è realizzato in un elevato numero di processi di sistema ciascuno dei quali fornisce ai programmi applicativi determinati servizi. Quando un computer è acceso, si avvia per primo il kernel, il quale avvia decine o centinaia di processi che consentono al computer di funzionare. Tanto più è complesso un sistema operativo, tanto sono più numerosi i processi di sistema. 

#### Concetto di processo
Spesso i termini programma e processo sono utilizzati come sinonimo. Il concetto di processo è legato alla multiprogrammazione. In un sistema multitasking sono caricati in memoria molti programmi pronti per l'esecuzione. La CPU passa rapidamente ad eseguire istruzioni di un programma a istruzioni di un altro programma. Quando la CPU sospende di eseguire le istruzioni di un programma, è necessario salvare il valore dei suoi registri, in modo che il programma interrotto possa, in seguito, riprendere la sua esecuzione come se l’interruzione non fosse avvenuta.
> Un processo non si identifica soltanto con il codice di un programma ma comprende anche l’attività del processore, rappresentata, istante per istante, dai valori contenuti nei suoi registri.

Generalmente, un processo include anche una **sezione dati**, per memorizzare le variabili globali e statiche del processo, e lo **stack**, che contiene i dati temporanei, come ad esempio i parametri e le variabili locali di funzione. Può comprendere anche un **heap** nel caso in cui, durante l’esecuzione, il processo allochi memoria dinamicamente. Per tener traccia del ciclo di vita di un processo, tutte le sue proprietà sono mantenute in una struttura dati, il **PCB (Process Controll Block)**. L'insieme di queste componenti è detta **immagine del processo**.

![[SOR/img/img16.png|center|500]]  

#### Stati di un processo
Un processo durante la sua esecuzione varia il suo stato. 
- Un processo entra nello **stato nuovo** quando è creato, ad esempio all'avvio.  
- Un processo passa nello **stato di pronto** dopo che il SO ha verificato che esso può essere eseguito.  
- Un processo passa dallo stato di pronto allo **stato di esecuzione** quando viene assegnato al processore.  
- Un processo è nello **stato di attivo** quando si trova in stato di pronto o di esecuzione.  
- Un processo passa dallo stato di esecuzione allo **stato di bloccato** quando è in attesa di un evento. Al verificarsi di esso torna in stato di pronto.  
- La transizione dallo stato di esecuzione allo stato di pronto è chiamato **prerilascio (o revoca)**. Esso può avvenire per vari motivi, ad esempio l'esaurimento del quanto di tempo (sistemi time-sharing), o nella coda di pronto è presente un altro processo con priorità più alta.  
- Il passaggio dallo stato di pronto allo stato di esecuzione è gestito dallo **scheduler**, un componente del kernel che seleziona un processo cui assegnare la CPU, tra tutti i processi che si trovano nello stato di pronto. Lo scheduler ha il compito di garantire che tutti i processi pronti possano avanzare nella loro esecuzione.   
- Il processo passa nello **stato terminato** quando ha terminato l’esecuzione del suo programma o quando si è verificato un’eccezione di vario tipo.  

![[SOR/img/img17.png|center|500]]  

In alcuni sistemi, è previsto che un processo sia trasferito dalla memoria principale alla memoria secondaria per lasciare spazio agli altri processi. Questa operazione è detta **swapping** e lo stato relativo è detto **swapped**. 

#### Descrittore del processo (PCB)
Ogni processo è rappresentato nel sistema da una struttura dati, detta descrittore del processo (**PCB Process Control Block**). I descrittori dei processi sono memorizzati nella **tabella dei processi**. I campi presenti nel PCB dipendono dal sistema operativo e dall'architettura dell'hardware.  
Il PCB contiene le seguenti informazioni: 
- **Identificatore del processo** (PID)
- **[[#Stati di un processo|Stato del processo]]**
- Informazioni sullo scheduling di CPU: la scelta del processo a cui assegnare la CPU avviene in diversi modi: ^4c2678
	-  **FIFO**: assegna la CPU al processo in attesa da più tempo
	- **Priorità**: viene assegnata ad ogni processo una priorità, fissa o dinamica, che rappresenta l'importanza rispetto agli altri processi. 
	- **Deadline**: la scelta del prossimo processo può essere basata anche in termini di intervallo di tempo (deadline) in cui l'esecuzione del processo deve essere portata a termine. 
	- **Quanto di tempo**: nei sistemi time-sharing, nel descrittore è contenuto un valore che rappresenta l'intervallo di tempo consecutivo in cui la CPU è assegnata allo stesso processo. 
- **Informazioni sulla gestione della memoria**: contiene le informazioni relative all'area di memoria principale nella quale sono caricati il codice, i dati e lo stack del processo.
- **Contesto del processo**: l’insieme dei valori dei registri del processore al momento della sospensione dell'esecuzione di un processo. Tipici registri presenti in una CPU sono il **PSW** (Program Status word), lo **SP** (puntatore allo stack - stack pointer), registri indice, accumulatori, registri di uso generale. Il contesto è recuperato dal descrittore e riportato ai registri quando il processo torna in esecuzione.
- **Utilizzo delle risorse**: contengono la lista di risorse allocate al processo. 
- **Identificatore al processo successivo**: ogni descrittore contiene un puntatore al processo successivo nella stessa coda. 
- **Informazioni sulla sicurezza**
- **Informazioni sulle variabili di ambiente**
- **Informazioni utente**

#### Code dei processi
Ogni stato del processo è realizzato con una o più code. Generalmente le CPU possiedono un **registro del processo in esecuzione** nel quale il SO memorizza il puntatore al descrittore del processo che è in esecuzione. I processi presenti nella memoria principale che sono pronti per essere eseguiti sono organizzati in una o più code dette **code dei processi pronti**. Ad ogni coda è associato un **descrittore di coda** che contiene due campi che specificano rispettivamente l'indice del primo e dell’ultimo descrittore di processo in coda. 

![[SOR/img/img18.png|center|500]] 

L'operazione di **inserimento** di un processo in coda dei processi pronti è dovuta alla transizione nello stato di pronto di un processo dallo stato di nuovo, bloccato o in esecuzione. 
L’operazione di **prelievo** corrisponde al passaggio dallo stato di pronto allo stato di esecuzione.

![[SOR/img/img19.png|center|500]] 

![[SOR/img/img20.png|center|500]]  

Nella coda dei processi è spesso presente il **processo di inattività**, che resta sempre in coda con la priorità più bassa e va in esecuzione quando tutti i processi sono bloccati, rimanendo in esecuzione fin quando qualche altro processo entra nella coda di pronto.  
Esistono **code per i processi bloccati**, una per ogni condizione per cui i processi devono attendere.  
La creazione di un processo implica la creazione di un nuovo descrittore e l'operazione di terminazione implica l'eliminazione del descrittore. 

![[SOR/img/img21.png|center|500]]

In alcuni sistemi può essere presente una coda nella quale sono presenti un numero fisso di descrittori disponibili per la creazione di nuovi processi. In tal caso il sistema avrà un numero massimo di processi predefinito. L’operazione di creazione estrae da questa coda il descrittore del processo da creare che sarà successivamente inserito nella coda dei processi pronti. Viceversa l'operazione di eliminazione di un processo riporta nella coda dei descrittori disponibili il descrittore del processo eliminato.

![[SOR/img/img22.png|center|500]]  

#### Scheduler
Un processo, durante il suo ciclo di vita, transita da una coda all’altra. Il sistema operativo deve elaborare queste code secondo qualche strategia. Tipicamente, nei [[Lezione 2 - Tipologie di sistemi#Sistemi operativi batch multiprogrammati|sistemi batch]], più programmi sono memorizzati in una particolare parte del file system, su disco, detta area di spool, per l’esecuzione. Lo *scheduler a lungo termine*, o job scheduler, seleziona i programmi da quest’area e li carica in memoria per l’esecuzione. Lo **scheduler a breve termine**, o CPU scheduler, seleziona dalla coda di pronto il prossimo processo cui assegnare la CPU. La differenza tra questi due scheduler sta nella frequenza di esecuzione. Lo scheduler a breve termine deve scegliere frequentemente un nuovo processo per la CPU.  
Generalmente, lo scheduler a breve termine è eseguito ogni 50-100 millisecondi. A causa del breve tempo tra le esecuzioni, lo scheduler a breve termine deve essere veloce. Se, ad esempio, lo scheduler impiegasse 10 millisecondi per scegliere il prossimo processo che resta in esecuzione per 50 millisecondi, allora, sarebbe utilizzato circa il 10 / (50 + 10) = 0.166, cioè circa il 17 per cento della CPU soltanto per selezionare il prossimo processo cui assegnare il processore.  
Lo scheduler a lungo termine, tipicamente va in esecuzione molto meno frequentemente, con un periodo di qualche minuto. É importante che uno scheduler a lungo termine selezioni una combinazione di processi I/O-bound e CPU-bound in modo da bilanciare il carico di lavoro tra la CPU e i dispositivi di I/O.  

#### Cambio di contesto
Il cambio di processo in escuzione, con uno nella coda dei processi pronti, si realizza con una serie di operazioni eseguite dal kernel e prende il nome di **cambio di contesto**.  
Queste operazioni sono: 
- Salvataggio del contesto del processo in esecuzione nel suo PCB (**salvataggio dello stato**). 
- Inserimento del PCB nella coda dei processi pronti o bloccati. L'operazione di inserimento del descrittore nella coda dei processi pronti si ha se il cambio di contesto è dovuto ad una revoca mentre l’inserimento in una coda di bloccato si ha se il cambio di contesto è dovuto alla sospensione del processo.  
- Selezione di un altro processo dalla coda dei processi pronti e caricamento del PCB di tale processo nel registro processo in esecuzione. Questa operazione è eseguita dallo scheduler a breve termine secondo una determinata [[#^4c2678|strategia]].
- Caricamento del contesto del nuovo processo nei registri del processore (**ripristino dello stato**).  
- Aggiornamento di tutte le strutture dati che rappresentano le risorse utilizzate dale processo.  
Le operazioni di salvataggio dello stato e ripristino dello stato, comportano il trasferimento di dati dai registri del processore alla memoria centrale e viceversa. I tempi di cambio di contesto variano da macchina a macchina ed è tipicamente dell'ordine di alcuni millisecondi, inoltre, più è complesso il sistema operaitvo, maggiore è il tempo necessario per il cambio di contesto.  

![[SOR/img/img23.png|center|500]]  


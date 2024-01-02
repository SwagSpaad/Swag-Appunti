****
# Introduzione allo scheduling
Quando un computer è multiprogrammato, molti processi/thread *in stato di pronto* possono competere per ottenere la CPU nello stesso istante. Se è disponibile una sola CPU bisogna scegliere quale processo eseguire come successivo e questo è deciso da una parte del sistema operativo detta **scheduler** seguendo un **algortimo di scheduling**. 

Nei sistemi batch, lo scheduling era lineare, ovvero si eseguiva il lavoro successivo sul nastro. Con la multiprogrammazione, lo scheduling è diventato complesso a causa della concorrenza tra utenti. Gli algortimi di scheduling erano cruciali per la prestazione e la soddisfazione degli utenti nei mainframe.
Nei PC spesso è attivo un solo processo. Un utente che sta utilizzando un word processor, infatti, molto probabilmente non sta utilizzando concorrentemente un’altra applicazione. Quindi quando un utente digita un comando nel programma, per lo scheduler è semplice individuare il processo da eseguire, il programma di word processing è l’unico candidato.
Inoltre i computer di oggi sono diventati così veloci che raramente la CPU è una risorsa scarsa. La maggior parte dei programmi per PC è limitata dalla velocità di inserimento dati dell’utente.

La situazione è diversa se consideriamo i server in rete, in cui la CPU spesso è contesa e quindi lo scheduling torna ad essere vitale. Per esempio quando la CPU deve decidere se eseguire un processo che raccoglie le statistiche giornaliere oppure uno che serve le richieste utente, l'utente sarebbe più contento se fosse il secondo ad avere la priorità nella CPU. Lo scheduler deve anche occuparsi di *fare un uso efficiente della CPU*, visto che il *context switch* è un'operazione onerosa.

## Comportamento dei processi
Quasi tutti i processi alternano fasi di elaborazione CPU a richieste di I/O. 

![[img38.png|center|700]]

Alcuni processi spendono la loro maggior parte del loro tempo in elaborazione, con attese di I/O non molto frequenti (**CPU-bound** (a)), mentre altri spendono il loro tempo in attesa di I/O, con burst di CPU brevi; sono processi dalla bassa necessità di calcoli, ma con molte richieste di I/O (**I/O-bound** (b)). 
Con **CPU più veloci** i processi tendono ad essere più **I/O-bound**, perché le CPU stanno migliorando più velocemente rispetto i dischi. 
## Quando effettuare lo scheduling
Le situazioni in cui è necessario lo scheduling sono diverse: 
- *Creazione nuovo processo*: lo scheduler deve decidere se deve essere eseguito il processo padre o figlio. Nel caso in cui siano entrambi pronti, lo scheduler sceglie liberamente uno dei due. 
- *Uscita di un processo*: alla terminazione di un processo, lo scheduler deve sceglierne uno nuovo da eseguire dalla lista dei processi pronti. Se nessun procesos è pronto viene eseguito un processo inattivo fornito dal sistema
- *Blocco di un processo*: quando un processo si blocca in attesa di I/O, su un semaforo o per qualsiasi altra ragione, deve essere scelto un altro processo. La causa del blocco può influire sulla decisione. 
	- Se A è un processo importante ed è in attesa che B esca dalla ragione critica, lasciare che B sia eseguito permette a B di uscire dalla ragione critica consentendo il continuo di A. Il problema è che lo scheduler non dispone delle informazioni necessarie per prendere in considerazione questa dipendenza.
- *Interrupt di I/O*: alla conclusione di un I/O, un processo potrebbe diventare pronto. Sta allo scheduler decidere se eseguire il processo appena pronto, il prcedente o un altro. 
Una decisione di scheduling può essere presa a ogni interrupt del clock oppure ad ogni $\text{k-esimo}$ interrupt del clock. Gli algoritmi di scheduling sono suddivisibili in due categorie, in base al comportamento rispetto agli interrupt del clock: 
- **Non preemptive** (senza prelazione): seleziona un processo e *lo lascia eseguire fino al blocco o al rilascio volontario*. Durante gli interrupt del clock non vengono prese decisioni, viene ripristinato il processo precedente dopo l'interrupt, almeno che non ci sia un processo a priorità più alta. 
- **Preemptive** (con prelazione): sceglie un processo e *lo lascia in esecuzione per un tempo massimo stabilito*. Se alla fine del tempo è ancora in esecuzione, il processo è sospeso e lo scheduler ne prende un altro da eseguire se è disponibile. Uno scheduling di questo tipo richiede che ci sia un interrupt del clock alla fine dell'intervallo di tempo scelto per restituire il controllo della CPU allo scheduler. 
	- La prelazione è rilevante sia per le applicazioni che per i kernel dei sistemi operativi. Senza prelazione un driver o una chiamata di sistema lenta potrebbero intasare la CPU, in un kernel con prelazione, lo scheduler può forzare il cambio di contesto

## Categorie di algoritmi di scheduling
In diversi ambienti sono necessari diversi algoritmi di scheduling, questo perché differenti aree di applicazione hanno obiettivi diversi e ciò che funziona per un dispositivo non è detto che vada bene anche per un altro. I tre ambienti che vale la pena distinguere sono i seguenti: 
- *batch*: sistemi utilizzati per le attività periodiche (calcolo di interessi, esecuzione di accrediti e addebiti su conti correnti, elaborazione di paghe ecc.). In questi sistemi, non essendoci utenti in attesa di una risposta rapida ad una piccola richiesta, sono accettati algoritmi *non preemptive*, riducendo lo scambio di processi e migliorando le prestazioni. 
- *interattivo*: la *prelazione* in questo tipo di sistemi è fondamentale, per prevenire la monopolizzazione della CPU da parte di un processo, negando il servizio agli altri. In questa categoria ricadono i server e sistemi ad utenti multipli.
- *sistemi real-time*: la *prelazione non è sempre necessaria*, poiché i processi si bloccano velocemente sapendo di non poter essere eseguiti a lungo. La differenza con i sistemi interattivi è che i sistemi real-time eseguono programmi per specifiche applicazioni, mentre i sistemi interrattivi possono eseguire programmi arbitrari
## Obiettivi degli algoritmi di scheduling
Per progettare un buon algoritmo di scheduling è necessario sapere cosa dovrebbe fare l'algoritmo in base all'ambiente per il quale viene progettato. Gli obiettivi sono dipendenti dall'ambiente, ma alcuni sono validi per qualsiasi ambiente. Vediamo quali sono questi obiettivi: 
- **Tutti i sistemi**: 
	- *Equità*: garantire ad ogni processo un'equa condivisione della CPU 
	- *Imposizione delle policy*: garantire l'attuazione delle policy dichiarate
	- *Bilanciamento*: mantenere tutti i componenti del sistema attivi
- **Sistemi batch**: 
	- *Throughput*: massimizzare il numero di job completati in un tempo fissato
	- *Tempo di turnaround*: minimizzare il tempo dallo start all'end di un job
	- *Utilizzo della CPU*: mantenere la CPU costantemente attiva
- **Sistemi interattivi**: 
	- *Tempo di risposta*: risposta rapida alle richieste degli utenti (fondamentale)
	- *Adeguatezza*: soddisfare le aspettative dell'utente in termine di tempo di risposta
- **Sistemi real-time**: 
	- *Rispetto delle scadenze*: assicurare che i dati vengano elaborati nei tempi previsti 
	- *Prevedibilità*: assicurarsi che il funzionamento sia costante, specialmente in sistemi multimediali per evitare degradi della qualità
In ogni situazione è importante l'imparzialità: processi comparabili devono avere un servizio comparabile; dare ad un processo molta più CPU che ad un altro equivalente *non è imparziale*. Anche il rispetto delle policy del sistema è fondamentale. Se la policy locale è che i processi di controllo della sicurezza devono essere eseguiti quando lo richiedono, anche se ciò implica che l'elaborazioned delle paghe ritardi di 30 secondi, lo scheduler deve essere certo che questa policy venga rispettata.

# Scheduling nei sistemi batch
Visualizziamo ora gli algoritmi di scheduling in modo più specifico. In questo paragrafo vediamo gli algoritmi di scheduling utilizzati nei sistemi batch:
- **[[#First-come first-served (FCFS)]]**
- **[[#Shortest job first (SJF)]]** 
- **[[#Shortest remaining time next (SRTN)]]** 
## First-come first-served (FCFS)
È il più semplice algoritmo di scheduling **non preemptive**, in cui i processi sono assegnati alla CPU nell'ordine in cui arrivano. C'è una singola coda di processi in stato di pronto, quando il primo processo entra nel sistema è avviato immediatamente ed è eseguito senza interruzioni. Quando il processo in esecuzione si blocca, viene eseguito il primo processo della coda e quando il processo bloccato ritorna pronto, viene messo in fondo alla coda. 
Il *vantaggio* di questo algoritmo è la facilità di comprensione e la semplicità nella programmazione (basta una *linked list*).
Il *forte svantaggio* dell'algoritmo si ha in presenza di scenari misti. Supponiamo che ci sia un processo CPU-bound eseguito per un secondo alla volta e molti processi I/O-bound che usano poca CPU ma ognuno deve effettuare 1000 letture dal disco per completare il lavoro. Il processo CPU-bound viene eseguito per 1 secondo e poi legge un blocco di disco. Ora sono eseguiti tutti i processi I/O-bound, che iniziano la lettura dal disco. Quando il processo CPU-bound ottiene il suo blocco di disco, viene eseguito per un altro secondo, seguito poi da tutti gli altri processi I/O-bound. Il risultato finale è che ogni processo I/O-bound riuscirà a leggere 1 blocco al secondo e impiegherà 1000 secondi per terminare. 
Con un algoritmo *preemptive* che esegue la preemption del processo CPU-bound ogni 10ms, i processi I/O-bound potrebbero terminare in 10 secondi invece che 1000, senza rallentare il processo CPU-bound. 

## Shortest job first (SJF)
Un altro algortimo **non preemptive**, che parte dal presupposto che i tempi di esecuzione siano conosciuti in anticipo. Quando parecchi lavori sono sistemati nella coda di input in attesa di essere avviati, lo scheduler preleva per primo il lavoro più breve (*shortest job*).

Vediamo un esempio: ci sono 4 job, detti A, B, C e D con tempi di esecuzione rispettivamente di 8, 4, 4 e 4 minuti. 
- Se venissero eseguiti in ordine ci vorrebbero: 
	- 8 min per A, 12 per B, 16 per C e 20 per D (14 minuti di media)
- Se sono eseguiti secondo l'algoritmo SJF
	- 4 min per B, 8 per C, 12 per D, 20 per A (11 minuti di media)

![[img39.png|center|500]]

Questo algoritmo è ottimale nel minimizzare il tempo di turnaround medio (il tempo dallo start all'end di un job), *quando tutti i job sono disponibili contemporaneamente*. Se così non fosse, l'algoritmo non è ottimale; pensiamo a 5 job detti A, B, C, D ed E con tempi 2, 4, 1, 1, 1 e arrivi a 0, 0, 3, 3, 3. All'inizio possono essere scelti solo A e B: 
- scegliendo A (shortest job) abbiamo: 
	- 2 minuti per A, 6 minuti per B (al tempo 2 è l'unico processo pronto), 7 min per C, 8 min per D e 9 per E (media 6,4 min)
- scegliendo invece B come primo abbiamo: 
	- 4 min per B, 5 per C, 6 per D, 7 per E, 9 per A (6,2 minuti di media)

## Shortest remaining time next (SRTN)
È la versione **preemptive** di [[#Shortest job first (SJF)|SJF]]. Lo scheduler sceglie sempre il processo con il tempo rimanente per essere completato più breve. Anche in questo caso il tempo di esecuzione deve essere noto in anticipo. 
All’arrivo di un nuovo lavoro il suo tempo totale è confrontato al tempo restante dei processi attuali. Se il nuovo lavoro ha bisogno di minor tempo del processo attuale per terminare, il processo attuale viene sospeso ed è avviato il nuovo lavoro. L'algoritmo assicura che i nuovi job brevi ricevano un servizio rapido.

# Scheduling nei sistemi interattivi
Il *tempo di risposta in questi sistemi* (personal computer, server) *è fondamentale*. 
Gli algoritmi di scheduling che vedremo sono i seguenti:
- [[#Round-robin scheduling]]
- [[#Priority scheduling]]
- [[#Shortest process next]]
- [[#Guaranteed scheduling]]
- [[#Lottery scheduling]]
- [[#Fair-share scheduling]]
## Round-robin scheduling
È uno degli algoritmi più vecchi, semplici, equilibrati e maggiormente utilizzati. Ad ogni processo è assegnato un intervallo di tempo, detto **quanto**, durante il quale gli è consentito di essere eseguito. Se il processo *non ha terminato* al termine del quanto, la CPU è oggetto di prelazione per gli altri processi. Se un processo termina o si blocca prima del quanto, il passaggio avviene naturalmente. 

L'implementazione è semplice: lo scheduler deve mantenere una lista dei processi eseguibili, una volta esaurito il quanto di tempo, il processo viene spostato alla fine della lista.

![[img40.png|center|700]]

L'unico "problema" del round-robin è la durata del quanto. La scelta del quanto di tempo influisce sull'efficienza. Supponiamo che ci voglia 1ms per il cambio di contesto e abbiamo un quanto di 4ms: *il 20% del tempo di CPU è sprecato per il cambio di contesto!*  
Scegliendo un quanto di tempo lungo, diciamo 100ms, in presenza di molti processi si ha un tempo di attesa alla risposta troppo lungo (se ci sono 50 processi, l'ultimo prima di essere eseguito aspetta 5 secondi). Un quanto tra 20 e 50 ms è ragionevole per bilanciare efficienza e reattività. 

## Priority scheduling
Lo scheduling [[#Round-robin scheduling|round-robin]] presuppone che tutti i processi abbiano la stessa importanza. Alcuni sistemi però richiedono una gerarchia, pensiamo ad esempio ad un università, in cui la gerarchia potrebbe essere costituita dal rettore, poi dai professori, segretari, bidelli e infine gli studenti. Questo porta alla necessità di uno **scheduling con priorità**.
A ciascun processo viene assegnata una priorità ed il processo con la priorità più alta tra quelli pronti viene eseguito. 
Per impedire che i processi con priorità più alta siano eseguiti indeterminatamente, *lo scheduler può abbassare la priorità del processo in esecuzione ad ogni interrupt del clock*. Se la priorità scende sotto quella del processo successivo, avviene il cambio.

![[Pasted image 20240102200512.png|center|700]]

In alternativa, a ciascun processo può essere associato un quanto di tempo massimo in cui può essere eseguito. All'esaurimento del quanto viene eseguito il processo successivo con la massima priorità. 
## Shortest process next
## Guaranteed scheduling
## Lottery scheduling
## Fair-share scheduling
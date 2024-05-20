****
# Introduzione allo scheduling
Quando un computer è multiprogrammato, molti processi/thread *in stato di pronto* possono competere per ottenere la CPU nello stesso istante. Se è disponibile una sola CPU bisogna scegliere quale processo eseguire come successivo e questo è deciso da una parte del sistema operativo detta **scheduler** seguendo un **algortimo di scheduling**. 

Nei sistemi batch, lo scheduling era lineare, ovvero si eseguiva il lavoro successivo sul nastro. Con la multiprogrammazione, lo scheduling è diventato complesso a causa della concorrenza tra utenti. Gli algortimi di scheduling erano cruciali per la prestazione e la soddisfazione degli utenti nei mainframe.
Nei PC spesso è attivo un solo processo. Un utente che sta utilizzando un word processor, infatti, molto probabilmente non sta utilizzando concorrentemente un’altra applicazione. Quindi quando un utente digita un comando nel programma, per lo scheduler è semplice individuare il processo da eseguire, il programma di word processing è l’unico candidato.
Inoltre i computer di oggi sono diventati così veloci che raramente la CPU è una risorsa scarsa. La maggior parte dei programmi per PC è limitata dalla velocità di inserimento dati dell’utente.

La situazione è diversa se consideriamo i server in rete, in cui la CPU spesso è contesa e quindi lo scheduling torna ad essere vitale. Per esempio quando la CPU deve decidere se eseguire un processo che raccoglie le statistiche giornaliere oppure uno che serve le richieste utente, l'utente sarebbe più contento se fosse il secondo ad avere la priorità nella CPU. Lo scheduler deve anche occuparsi di *fare un uso efficiente della CPU*, visto che il *context switch* è un'operazione onerosa.

## Comportamento dei processi
Quasi tutti i processi alternano fasi di elaborazione CPU a richieste di I/O. 

![[SOR/SO/img/img38.png|center|700]]

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

![[SOR/SO/img/img39.png|center|500]]

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

![[SOR/SO/img/img40.png|center|700]]

L'unico "problema" del round-robin è la durata del quanto. La scelta del quanto di tempo influisce sull'efficienza. Supponiamo che ci voglia 1ms per il cambio di contesto e abbiamo un quanto di 4ms: *il 20% del tempo di CPU è sprecato per il cambio di contesto!*  
Scegliendo un quanto di tempo lungo, diciamo 100ms, in presenza di molti processi si ha un tempo di attesa alla risposta troppo lungo (se ci sono 50 processi, l'ultimo prima di essere eseguito aspetta 5 secondi). Un quanto tra 20 e 50 ms è ragionevole per bilanciare efficienza e reattività. 

## Priority scheduling
Lo scheduling [[#Round-robin scheduling|round-robin]] presuppone che tutti i processi abbiano la stessa importanza. Alcuni sistemi però richiedono una gerarchia, pensiamo ad esempio ad un università, in cui la gerarchia potrebbe essere costituita dal rettore, poi dai professori, segretari, bidelli e infine gli studenti. Questo porta alla necessità di uno **scheduling con priorità**.
A ciascun processo viene assegnata una priorità ed il processo con la priorità più alta tra quelli pronti viene eseguito. 
Per impedire che i processi con priorità più alta siano eseguiti indeterminatamente, *lo scheduler può abbassare la priorità del processo in esecuzione ad ogni interrupt del clock*. Se la priorità scende sotto quella del processo successivo, avviene il cambio.

In alternativa, a ciascun processo può essere associato un quanto di tempo massimo in cui può essere eseguito. All'esaurimento del quanto viene eseguito il processo successivo con la massima priorità. 

Le priorità possono essere assegnate dinamicamente o staticamente. 
In un computer militare i processi avviati dal generale potrebbero iniziare a priorità 100, quelli dei colonnelli a 90, dai maggiori a 80 e così via; questo è un esempio di *priorità assegnata staticamente*. La priorità assegnata dinamicamente viene utilizzata quando ci si basa sull'utilizzo della CPU o sul comportamento I/O-bound.

Spesso è conveniente raggrupare i processi in *classi di priorità* e usare lo scheduling a priorità fra le classi, ma all'interno di ciascuna classe utilizzare lo [[#Round-robin scheduling|scheduling round-robin]]. 

![[SOR/SO/img/img41.png|center|700]]

In figura è rappresentato un sistema con 4 classi di priorità, finché ci sono processi nella classe di priorità 4 (la più alta), viene eseguito ciascun processo della classe per un quanto di tempo in stile round-robin, ignorando i processi delle altre classi di priorità. Quando la classe di priorità 4 è vuota, si passa alla classe 3 eseguendo i processi in round-robin e così via. È importante rivedere periodicamente le priorità per evitare che i processi a bassa priorità non vengono mai eseguiti.
## Shortest process next
L'algoritmo [[#Shortest job first (SJF)|shortest job first]] produce il minor tempo medio di risposta sui sistemi batch; l'obiettivo è quello di applicarlo anche ai sistemi interattivi. I processi interattivi seguono lo schema di attesa/esecuzione di un comando. Se consideriamo l'esecuzione di ogni comando come un job, potremmo minimizzare il tempo di risposta eseguendo il lavoro più breve per primo. Il problema è però identificare quale degli attuali processi sia il più breve. 
Un approccio è quello di fare stime basate sul comportamento passato. Supponiamo che il tempo stimato sia $T_{0}$. Indichiamo con $T_{1}$ il tempo di esecuzione successivo. Possiamo ora aggiornare la nostra stima facendo una somma pesata dei numeri, ossia $aT_{0}+(1-a)T_{1}$. Tramite la scelta di $a$ possiamo decidere se avere il processo di stima che dimentica in breve tempo le esecuzioni precedenti o le ricorda a lungo. Con $a=\frac{1}{2}$ abbiamo queste stime: $$T_{0},\:\:\:\:\frac{T_{0}}{2}+ \frac{T_{1}}{2},\:\:\:\: \frac{T_{0}}{4}+ \frac{T_{1}}{4}+ \frac{T_{2}}{2},\:\:\:\: \frac{T_{0}}{8}+ \frac{T_{1}}{8}+ \frac{T_{2}}{4}+ \frac{T_{3}}{2}$$
Dopo 3 esecuzioni, il peso di $T_0$ nella stima è sceso a $\frac{1}{8}$. 
La tecnica di stimare il valore successivo di una serie prendendo la media pesata del valore attuale misurato e la stima precedente è detta **aging** ed è applicabile in situazioni dove si basa la previsione sui valori passati.
## Guaranteed scheduling
Il concetto principale è quello di fare promesse reali agli utenti sulle prestazioni e poi rispettarle. La promessa base è che se ci sono $n$ utenti collegati oppure $n$ processi su un sistema a singolo utente, ciascuno otterrà $\approx \frac{1}{n}$ della potenza della CPU. 
Per mantenere questa promessa, il sistema deve tenere traccia di quanta CPU ha ricevuto ogni processo dal momento della sua creazione. Dopodiché calcola quanto tempo di CPU ogni processo dovrebbe avere, cioé $\frac{\text{tempo di creazione}}{n}$. Se supponiamo un tempo di creazione di 100 secondi e 10 processi nel sistema, ogni processo ha diritto a 10 secondi di CPU ($\frac{100}{10}$)
A questo punto viene valutato il rapporto tra il tempo di CPU consumato e il tempo di CPU dovuto:
- Rapporto di $0,5$: il processo ha avuto la metà di quanto dovuto
- Rapporto di $2,0$: il processo ha avuto il doppio di quanto dovuto
L'algoritmo esegue il processo col rapporto minore finché non supera il rapporto del concorrente più vicino.
## Lottery scheduling
Mantenere le promesse è una bella idea, ma di difficile realizzazione. Il concetto di questo algoritmo è quello di dare ad ogni processo dei "biglietti della lotteria" per le risorse del sistema, come il tempo di CPU. Ogni volta che deve essere presa una decisione di scheduling si pesca un biglietto della lotteria e il processo che ha quel biglietto si aggiudica la risorsa.
Facendo 50 estrazioni al secondo, il vincitore riceve 20ms di tempo della CPU. Ai processi più importanti, basta assegnare più biglietti per aumentare la probabilità di estrazione. Se ci sono 100 biglietti non estratti e un processo ha 20 biglietti, ha il 20% delle possibilità di essere estratto e a lungo termine si prenderà il 20% del tempo della CPU. 

Questo algoritmo ha delle proprietà interessanti: 
- è *reattivo*, infatti alla nascita di un nuovo processo, esso può ottenere biglietti e partecipare all'estrazione successiva, avendo una possibilità immediata di ottenere la CPU.
- nella *cooperazione tra processi*, i processi cooperanti possono scambiarsi i propri biglietti per aumentare le probabilità di esecuzione. 
	- Per esempio quando un processo client manda un messaggio ad un processo server e poi si blocca, può donare tutti i suoi biglietti al server per aumentare la possibilità che il server sia il prossimo ad essere eseguito. Quando il server ha terminato, restituisce tutti i biglietti al client per lo stesso scopo. 
- può essere utilizzato come soluzione a *problemi complessi* dove altri metodi falliscono
	- pensiamo ad un server video in cui molti processi stanno inviando stream video ai loro client, ma a frame rate diversi, diciamo 10, 20 e 25 frame/s. Assegnando a questi processi rispettivamente 10, 20 e 25 biglietti, essi divideranno automaticamente la CPU nelle proporzioni corrette, ovvero 10, 20 e 25%.
## Fair-share scheduling
Tradizionalmente ogni processo è oggetto di scheduling individualmente, senza considerare a chi appartiene. Di conseguenza se l'utente 1 ha 9 processi e l'utente 2 ne ha solo 1, con il round-robin o con stesse priorità, l'utente 1 si prenderà il 90% della CPU, mentre l'utente 2 avrà solo il 10% della CPU.
L'approccio fair-share, per evitare questa situazione, prima di schedulare i processi, prende in considerazione a chi appartiene il processo. In questo modello, a ogni utente viene assegnata una porzione di CPU, indipendentemente dal numero di processi che possiede. 

Come esempio consideriamo 2 utenti con il 50% di CPU ciascuno. L'utente 1 ha 4 processi, detti A, B, C e D, mentre l'utente 2 ha il solo processo E. 
Utilizzando lo scheduling round-robin abbiamo la seguente sequenza: **A** *E* **B** *E* **C** *E* **D** *E* **A** *E*..
Se l'utente 1 avesse diritto al doppio del tempo di CPU rispetto all'utente 2 abbiamo la seguente sequenza: **AB** *E* **CD** *E* **AB** *E* **CD** *E*...
# Scheduling nei sistemi real-time
Un sistema real-time è un sistema in cui il tempo di risposta gioca un ruolo fondamentale. Per esempio un computer all'interno di un lettore CD, prende i bit così come arrivano dal drive e li converte in musica in un intervallo di tempo molto stretto; se i calcoli impiegassero troppo tempo, la musica suonerebbe in modo strano. Altri sistemi real-time riguardano i computer per il monitoraggio dei pazienti in terapia intensiva oppure per il pilota automatico degli aerei. Ritardi o mancati tempi di risposta possono portare gravi problemi. 

I sistemi real-time si suddividono in due categorie: 
- **hard real-time**: nel caso di scadenze assolute da rispettare
- **soft real-time**: qualche scadenza mancata è tollerabile
Gli eventi cui un sistema real-time deve rispondere sono categorizzati in:
 - **periodici**: avvengono ad intervalli regolari
 - **non periodici**: avvengono in modo imprevedibile
Un sistema real-time è detto **schedulabile** se la CPU è in grado di gestire la somma totale del tempo richiesto dai processi. Per esempio, se ci sono $m$ eventi periodici, e l'avento $i$ avviene con un periodo $P_{i}$ e richiede $C_{i}$ secondi di tempo della CPU per gestire ogni evento, allora il carico di eventi può essere gestito se e solo se $$\sum\limits_{i=1}^{m} \frac{C_{i}}{P_{i}}\le1$$
>**Esempio**
>Consideriamo un sistema soft real-time con tre eventi periodici di 100, 200 e 500 ms. Se questi eventi richiedessero rispettivamente 50, 30 e 100 millisecondi di tempo di CPU per evento, il sistema è schedulabile, infatti $$\frac{50}{100}+ \frac{30}{200}+ \frac{100}{500}=0.2+0.15+0.2\le1$$ Se si aggiungesse un nuovo evento con un periodo di 1 secondo, il sistema rimane schedulabile comunque se non occorrono più di 150ms di tempo di CPU per l'evento.
 
Gli algoritmi di scheduling real-time possono essere:
- **statici**: le decisioni di scheduling sono prese prima dell'inizio dell'esecuzione da parte del sistema. Richiede una perfetta conoscenza delle esigenze e delle scadenze.
- **dinamici**: le decisioni di scheduling vengono prese durante l'esecuzione

# Processi e scheduling
Finora abbiamo presupposto che tutti i processi nel sistema appartengano a diversi utenti  e che siano di conseguenza in competizione per la CPU. Questo è vero, ma a volte accade che un processo abbia molti figli eseguiti sotto il suo controllo, ad esempio un sistema di gestione di una base di dati può avere molti figli, ognuno dei quali ha delle funzioni specifiche da eseguire. È possibile che il processo principale abbia un'idea di quale dei suoi figli sia il più importante, ma nessuno scheduler presentato accetta input dai processi utente riguardo alle decisioni di scheduling, portando spesso a soluzioni non ottime. 
La soluzione a questo problema sta nel separare il **meccanismo di scheduling** dalla **politica di scheduling**. Il vantaggio sta nel fatto che l'algoritmo di scheduling può essere parametrizzato, ma i parametri possono essere riempiti dai processi utenti. 

>**Esempio**
>Consideriamo di nuovo l'esempio del database, supponendo che il kernel adoperi un algoritmo di scheduling con priorità, ma fornendo una chiamata di sistema tramite cui un processo può impostare e cambiare la priorità dei suoi figli. In questo modo il padre può controllare nel dettaglio come sono schedulati i suoi figli, anche se non ne fa lo scheduling. In questo caso il meccanismo sta nel kernel, mentre la politica è impostata da un processo utente.

## Scheduling a thread
Quando molti processi hanno molteplici thread, abbiamo due livelli di parallelismo presenti: processi e thread. Lo scheduling si differenzia a seconda che siano supportati i *[[Capitolo 2 - Processi e Thread#Implementazione nello spazio utente|thread a livello utente]]* o a *[[Capitolo 2 - Processi e Thread#Implementazione nello spazio kernel|livello kernel]]*. 

### Thread a livello utente
Consideriamo per primo i thread a livello utente. Siccome il kernel non è a conoscenza dell'esistenza dei thread, opera come sempre, prendendo un processo, diciamo $A$, assegnandogli la CPU per il suo quanto. Lo scheduler di thread interno ad $A$ decide quale thread eseguire, diciamo $A1$. Poiché per questi thread non ci sono interrupt del clock, questo può continuare l'esecuzione quanto vuole; se utilizza l'intero quanto del processo, il kernel selezionerà un nuovo processo da eseguire. Quando il processo $A$ verrà rieseguito, il thread $A1$ riprenderà l'esecuzione, continuando a consumare il quanto di $A$ finché non è finito. Questo porta al risultato che *consumare l'intero quanto di un processo da parte dal thread influenza solo il processo interno e non tutti gli altri*. 

>**Esempio (in figura)**
>Consideriamo il caso che i thread di $A$ abbiano un burst di CPU da 5ms su 50ms di quanto. Di conseguenza ognuno lavora per un breve istante per poi cedere la CPU allo scheduler dei thread. 
>La possibile sequenza di esecuzione potrebbe essere: $A1,A2,A3,A1,A2\dots$ 

![[SOR/SO/img/img42.png|center|500]]

### Thread a livello kernel
In questo caso il kernel preleva un particolare thread da eseguire, senza tener conto del processo a cui appartiene. Al thread viene assegnato un quanto, che se eccede viene sospeso forzatamente. 

>**Esempio (in figura)**
>Con un quanto di 50ms, ma con i thread che si bloccano dopo 5ms, l'ordine di esecuzione dei thread potrebbe essere $A1,B1,A2,B2,A3,B3$, cosa che era impossibile con i thread utente

![[SOR/SO/img/img43.png|center|500]]

## Differenza tra thread a livello utente e kernel
La principale differenza tra i thread utente e kernel sta nelle prestazioni: effettuare uno scambio di thread utente richiede una manciata di istruzioni macchina, mentre con i thread a livello kernel occorre fare uno *scambio completo di contesto*, che è più lento. D'altra parte, con i thread a livello kernel, un thread bloccato in attesa di I/O non sospende l'intero processo, come invece avviene con i thread utente.
Poiché il kernel sa che il passaggio da un thread nel processo $A$ ad un thread nel processo $B$ è più costoso di eseguire un altro thread nel processo $A$, può tenere conto di questa informazione quando deve prendere una decisione, dando ad esempio la preferenza ai thread dello stesso processo. 
Un altro fattore importante è che i thread utente possono utilizzare uno scheduler di thread specifico dell'applicazione, permettendo maggiore controllo e ottimizzazione dell'applicazione rispetto allo scheduler del kernel. 


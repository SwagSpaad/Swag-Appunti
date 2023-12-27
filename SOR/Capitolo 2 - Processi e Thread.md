# Processi
In questo capitolo iniziamo uno studio dettagliato di come sono progettati e costruiti i sistemi operativi. Il concetto fondamentale di ogni SO è quello di *processo*, un'astrazione di un programma in esecuzione. All'avvio del computer vengono fatti partire in modo non evidente molti processi, come un processo per il controllo delle mail in arrivo, un altro per il controllo dei virus e così via. Tutte queste attività devono essere gestite ed un sistema multiprogrammato risulta molto pratico. 
In ogni sistema multiprogrammato la CPU passa da processo a processo rapidamente, eseguendone ognuno per decine o centinaia di millisecondi. Mentre la CPU esegue un solo processo per volta, nel corso di 1 secondo può lavorare su parecchi processi, dando l'illusione di parallelismo. 

## Modello di processo
Tutti i software presenti sul computer, sono organizzati in un certo numero di *processi sequenziali*. Un processo è un'istanza di un programma in esecuzione, inclusi i valori del program counter, dello stack, dei registri e delle variabili. 
Concettualmente ogni processo ha la sua CPU virtuale, ma in realtà la CPU passa avanti e indietro da processo a processo. 

In figura sotto, è mostrato un computer che fa multiprogrammazione di quattro programmi in memoria. 

![[SOR/img/img22.png|center|300]]

Nella prossima figura, sono mostrati quattro processi, ognuno col suo program counter personale, che girano indipendentemente dagli altri. Naturalmente c'è un solo program counter fisico ed ogni volta che si passa da un processo all'altro si salva il valore del program counter logico del primo e si ripristina il contatore del secondo.  

![[SOR/img/img1.png|center|600]]

Nella vediamo che, osservati su un intervallo abbastanza lungo, tutti i processi hanno avuto un avanzamento, ma in un preciso istante solo un processo è realmente in esecuzione.

![[SOR/img/img2.png|center|500]]

In linea di principio, i processi multipli sono reciprocamente indipendenti ed hanno bisogno di mezzi espliciti per interagire tra loro. La CPU può essere assegnata a turno a diversi processi, ma il sistema operativo normalmente non offre garanzie di tempistica o di ordine.

## Gerarchie di processi
Un esempio in cui la gerarchia dei processi gioca un ruolo importante è l'inizializzazione di UNIX al suo avvio. Viene lanciato all'avvio un processo speciale chiamato *init*, presente nell'immagine di avvio, che legge un file che indica quanti terminali sono presenti, eseguendo la *fork* di un nuovo processo per ogni terminale, che attendono che qualcuno esegua il login. Se il login avviene correttamente, il processo di login esegue una shell che accetta i comandi che possono far partire nuovi processi. Tutti questi processi appartengono ad un solo albero con *init* come radice.

![[SOR/img/img23.png|center|300]]

## Creazione di processi
Gli eventi che causano la creazione di processi sono quattro: 
- inizializzazione del sistema 
- esecuzione di una chiamata di sistema di creazione di un processo 
- richiesta dell'utente di creare un processo
- inizio di un job in modalità batch

## Termine di processi
Le condizioni che causano la terminazione di un processo sono: 
- uscita normale (volontaria): chiamata `exit()`
- uscita su errore (volontaria): bug nel programma
- errore fatale (involontaria): run-time error
- ucciso da un altro processo (involontaria): chiamata `kill()`

## Stati di un processo
Sebbene ogni processo sia indipendente, con il proprio program counter e stato interno, i processi hanno bisogno di comunicare. Quando lanciamo il comando:
```sh
cat chapter1 chapter2 chapter 3 | grep tree
```
il primo processo, che esegue il comando `cat`, concatena tre files e ne esegue l'output; il secondo processo, che esegue il comando `grep`, seleziona tutte le linee contenenti la parola 'tree'. In base alla velocità dei processi, può succedere che `grep` sia pronto per essere seguito anche senza input, perciò deve fermarsi finché non sia disponibile qualche input. 

Gli stati in cui un processo può trovarsi sono: 
- in esecuzione (*running*): sta usando la CPU in quel momento
- pronto (*ready*): il processo è eseguibile, ma temporaneamente fermo per consentire l'esecuzione di un altro processo
- bloccato (*blocked*): non può essere eseguito fino a quando non si
verifica un evento esterno

Il sistema operativo alloca risorse, come la CPU ai processi, tenendo conto degli stati dei processi. Le transizioni possibili sono quelle che vediamo in figura.

![[SOR/img/img24.png|center|700]]

## Tabella dei processi
Per implementare il modello di processo, il sistema operativo mantiene una tabella detta *tabella dei processi*, con una voce per processo, che contiene importanti informazioni riguardo il processo come il suo program counter, il suo stato, il puntatore allo stack, l'allocazione della memoria, lo stato dei file aperti e tutte le informazioni relative al processo che vanno salvate in modo che possa essere riavviato come se non si fosse mai fermato. 

## Segnali e interrupt
I segnali e gli interrupt sono meccanismi utilizzati nei sistemi operativi e nelle applicazioni per gesitire eventi asincroni

|            | Interrupts                                              | Signals                                                         |
| ---------- | ------------------------------------------------------- | --------------------------------------------------------------- |
| Origine    | Dispositivi *hardware*                                  | Eventi software                                                 |
| Gestione   | Tramite routine di servizio di interrupt                | Gestori di segnali personalizzati o comportamento predefinito   |
| Uso        | Comunicazione tra hardware e software                   | Gestione condizioni eccezionali nelle applicazioni              |
| Asincronia | Si verificano in modo asincrono; gestiti immediatamente | Inviati asincronamente; possono essere gestiti in modo sincrono |

### Interrupts
Per deallocare la CPU a favore dello scheduler, ci si affida al supporto per la gestione degli interrupt fornito dall'hardware, che permette allo scheduler di ottenere periodicamente il controllo, ogni volta che l'hardware genera un interrupt.

Associato a ciascun dispositivo di I/O e linea di interrupt c'è un *interrupt vector*, che contiene l'indirizzo della procedura di servizio dell'interrupt.

Vediamo ora cosa fa il livello più basso del SO quando avviene un'interruzione: 
1. L'hw impila il Program Counter e le altre informazioni sul processo
2. L'hw carica il nuovo contatore di programma dal vettore degli interrupt
3. La procedura in linguaggio assembly salva i registri
4. La procedura in linguaggio assembly imposta un nuovo stack
5. Il servizio di interrupt C viene eseguito 
6. Lo scheduler decide quale processo deve essere eseguito successivamente
7. La procedura C ritorna al codice assembly
8. La procedura in linguaggio assembly avvia il nuovo processo 

Ogni volta che si verifica un'interruzione, lo scheduler ottiene il controllo, perché un processo non può cedere la CPU ad un altro processo senza prima passare attraverso lo scheduler.

### Gestione dei segnali
- *Tipi di segnali*:
	- Hardware-induced (SIGILL)
	- Software-induced (SIGQUIT or SIGPIPE)
- *Azioni possibili*:
	- Term, Ign, Core, Stop, Cont
	- Azione predefinita per ogni segnale, tipicamente sovrascrivibile
	- I segnali tipicamente possono essere bloccati
- *Gestione dei segnali*:
	- Il processo registra il gestore dei segnali
	- Il SO invia il segnale e consente al processo di eseguire l'handler
	- Il contesto di esecuzione corrente deve essere salvato/ripristinato

#### Segnale CTRL+C
```C
void signalHandler(int signum) {
  printf ("Interrupt signal &d received\n", signum);
  // Cleanup and terminate program
  exit(signum);
}
int main() {
  // register signal SIGINT and signal handler 
  signal(SIGINT, signalHandler);
  while(1) {
    printf ("Going to sleep....\n");
    sleep(1);
  }
  return(0)
}

```

1. Il kernel invia un segnale
2. Interrompe il codice in esecuzione
3. Salva il contesto
4. Esegue il codice di gestione del segnale (signalHandler)
5. Ripristina il contesto originale

# Thread
Nei tradizionali SO, ogni processo dispone di uno spazio degli indirizzi e di un singolo thread di controllo, ma ci sono situazioni in cui ci possono essere molteplici thread di controllo in esecuzione in parallelo nello stesso spazio di indirizzi, come se fossero processi separati. 

## Uso dei thread
Quali sono le motivazioni di consentire più thread per processo? La ragione principale è che in molte applicazioni ci sono più attività contemporanee, che a volte potrebbero bloccarsi. Suddividendo l'applicazione in molteplici thread sequenziali eseguiti quasi in parallelo, il modello di programmazione diventa più semplice. Un altro motivo è che siccome i thread sono più leggeri dei processi, essi sono più facili e veloci da creare e cancellare.

>**Esempio 1**
>Un programma di videoscrittura con un documento di 800 pagine beneficia dell'uso di thread per migliorare la gestione delle operazioni. 
>Si consideri ora cosa accade se l'utente cancella una riga da pagina 1 e dopo aver controllato la correttezza della modifica, l'utente vuole fare un cambiamento a pagina 600, digitando un comando che lo porti a quella pagina; il programma deve rimpaginare l'intero libro perché non sa più quale sia la prima riga di pagina 600 e questo potrebbe creare un certo ritardo. Con due thread principali, uno per l'interazione con l'utente e l'altro per l'impaginazione in background, il programma può gestire in modo efficiente le richieste dell'utente. 
>Quando l'utente elimina una riga dalla pagina 1, il thread interattivo comunica al thread di impaginazione di rimpaginare l'intero libro. Nel frattempo, il thread interattivo rimane attivo, consentendo all'utente di continuare a lavorare senza ritardi. 
>Introduciamo anche un terzo thread per il salvataggio automatico. In un contesto a singolo thread, l'avvio di un backup su disco bloccherebbe i comandi della tastiera e del mouse fino al completamento dell'operazione. L'utilizzo di thread consente invece una gestione più fluida: mentre il thread di salvataggio è attivo, gli altri thread possono continuare a operare senza essere vincolati dall'operazione di backup.
>È chiaro che la stessa implementazione non può essere apportata con tre processi, perché entrambi hanno bisogno di operare sullo stesso documento, mentre avendo tre thread, che condividono una memoria comune, tutti hanno accesso allo stesso file.
>![[SOR/img/img25.png|center|500]]

>**Esempio 2**
>Consideriamo ora un server di un sito web. In ingresso arrivano richieste di pagine, che vengono inviate al client. In molti siti ci sono pagine più richieste di altre, ad esempio la homepage del sito della Sony è più richiesta della pagina con le specifiche di una macchinetta fotografica dello stesso sito. Questa caratteristica è utilizzata per migliorare le prestazioni mantenendo in memoria un insieme di pagine utilizzate frequentemente (cache) per non caricarle dal disco. Nella figura sotto vediamo un modo per organizzare il web server. 
>Il primo thread, un **dispatcher**, legge le richieste di lavoro in arrivo alla rete e, dopo aver esaminato la richiesta sceglie un **thread worker** inattivo che gli fa gestire la richiesta. Il dispatcher risveglia il thread, che controlla se la richiesta possa essere esaudita dalla cache del server, a cui tutti i thread possono accedere, altrimenti avvia un'operazione di `read` per prendere la pagina dal disco, bloccandosi fino a completamento.
>![[SOR/img/img26.png|center|700]]

Qui sotto possiamo vedere i codici del thread del dispatcher e del thread di lavoro
```C
#dispatcher.c
while(True){
	get_next_request(&buf);
	handoff_work(&buf);
}

#worker.c
while(True){
	wait_for_work(&buf);
	look_for_page_in_cache(&buf, &page);
	if(page_not_in_cache(&page)){
		read_page_from_disk(&buf, &page);
	}
	return_page(&page);
}
```

## Modello di thread
Il [[#Modello di processo|modello di processo]] si basa su due concetti indipendenti: il raggruppamento di risorse e l'esecuzione. Un processo è visto come  un modo per raggruppare risorse selezionate, poiché ha uno spazio degli indirizzi, contenente testo, programma e dati e altre risorse come file aperti, processi figli ecc. L'altro concetto relativo al processo è un *thread* di esecuzione. Esso ha un program counter che tiene traccia delle istruzioni da eseguire, ha dei registri contenenti le variabili di lavoro e uno stack. Sebbene un thread debba essere eseguito all'interno di un processo, il thread ed il relativo processo sono concetti differenti e possono essere trattati separatamente. 
I processi sono usati per raggruppare risorse, mentre i thread sono entità schedulate per l'esecuzione sulla CPU. 

In figura vediamo due casi: il primo (a) in cui ci sono tre processi, ognuno col suo spazio degli indirizzi e un singolo thread di controllo, mentre nel secondo caso (b) vediamo un singolo processo con tre thread. Sebbene in entrambi i casi abbiamo tre thread, nella figura (a) ognuno di loro opera in un diverso spazio degli indirizzi, mentre nella figura (b) tutti e tre condividono lo stesso spazio.

![[SOR/img/img27.png|center|700]]

Thread diversi nello stesso processo, però, non sono così indipendenti come processi diversi, perché avendo lo stesso spazio degli indirizzi, condividono anche le stesse variabili globali e siccome ogni thread può accedere a tutto l'indirizzo di memoria all'interno dello spazio degli indirizzi del processo, essi possono scrivere, modificare o eliminare lo stack di un altro thread. L'organizzazione in figura (a) viene utilizzata quando i tre processi non sono relazionati, mentre l'organizzazione (b) è appropriata quando i thread sono parte dello stesso lavoro e devono collaborare. 

La tabella sotto elenca gli elementi appartenenti ad un processo (e quindi condivisi tra tutti i suoi thread) e gli elementi privati di ogni thread. 

| Elementi per processo              | Elementi per thread    |
| ---------------------------------- | ---------------------- |
| Spazio degli indirizzi             | Contatore di programma |
| Variabili globali                  | Registri               |
| File aperti                        | Stack                  |
| Processi figli                     | Stato                  |
| Allarmi in sospeso                 |                        |
| Segnali e gestori dei segnali      |                        |
| Informazioni relative agli account |                        |

## Thread in POSIX
Il package di thread per permettere la scrittura di programmi che usano i thread si chiama **Pthread**. In tabella vediamo alcune chiamate di funzione.

![[SOR/img/img28.png|center|700]]

```C
#include<pthread.h>
#include<stdio.h>
#include<stdlib.h>

#define NUMBER_OF_THREADS 10
void *print_hello_world(void *tid){
	/*Stampa hello world, l'identificatore del thread ed esce*/
	printf("Hello world. Greetings from thread %d\n", tid);
	pthread_exit(NULL); 
}

int main(int argc, char *argv[]){
	pthread_t threads[NUMBER_OF_THREADS];
	int status, i;
	for(i = 0; i < NUMBER_OF_THREADS; i++){
		status = pthread_create(&threads[i], NULL, print_helloworld, (void * )i);
		if(status != 0){
			exit(-1);
		}
	}
	return 0;
}
```
## Implementazione di thread
Esistono tre metodi per implementare dei thread: 
- nello spazio utente
- nel kernel
- implementazione ibrida
### Implementazione nello spazio utente
**Pro**
I thread nello spazio utente sono gestiti dal kernel come processi ordinari a singolo thread. Il primo vantaggio è che può essere implementato su sistemi operativi che non supportano direttamente i thread, che sono realizzati tramite una libreria. 
Ogni processo che usa i thread a livello utente, ha bisogno di una *tabella dei thread*, che tiene traccia delle proprietà dei thread, come lo stato, il program counter, i registri ecc.
Le procedure che salvano lo stato del thread e lo scheduler sono nello spazio utente, così è molto più efficace richiamarle perché **non servono trap, cambi di contesto** e svuotamento della cache, che rende il tutto più veloce. L'implementazione nello spazio utente permette di personalizzare l'algoritmo di scheduling per ogni processo. 

![[SOR/img/img29.png|center|500]]

**Contro**
Nonostante le migliori prestazioni, anche i thread a livello utente presentano degli svantaggi, primo fra tutti c'è il problema di come sono implementate le chiamate di sistema bloccanti, perché se un thread fa una chiamata che lo blocca, tutti gli altri thread nel processo vengono fermati. Analogo è il caso dei **page fault**. I computer sono impostati in modo tale che non tutto il programma sia in memoria principale e se il programma fa una chiamata ad un'istruzione non presente in memoria, si verifica un page fault e il SO va a prendere l'istruzione mancante dal disco. Nel mentre il processo è bloccato in attesa dell'istruzione. Se un thread causa un page fault, il kernel, ignorando l'esistenza del thread, blocca l'intero processo, sebbene altri thread potrebbero essere eseguibili. 
Se un thread inizial'esecuzione, non sarà eseguito nessun altro thread finché il primo non rilasci volontariamente la CPU, perché all'interno di un singolo processo non ci sono interrupt del clock, rendendo impossibile uno scheduling di tipo round-robin (a turno).
Sebbene i thread a livello utente siano più veloci e flessibili, sono meno adatti per applicazioni in cui i thread si bloccano frequentemente, come un web server.
### Implementazione nello spazio kernel

![[SOR/img/img30.png|center|500]]

Il kernel che gestisce i thread, elimina la necessità di un sistema [run-time](https://it.wikipedia.org/wiki/Run-time_system) per processo. Il kernel dispone di una tabella di thread che tiene traccia di tutti i thread nel sistema. Quando un thread vuole crearne uno nuovo o distruggerlo, fa una chiamata al kernel che esegue la creazione o la distruzione aggiornando la tabella dei thread. Le chiamate di sistema che potrebbero bloccare un thread vengono implementate come chiamate di sistema, che hanno costi più elevati rispetto le chiamate di procedura dei sistemi run-time. Se un thread si blocca, il kernel può eseguire un altro thread, sia dello stesso processo che di un altro.
A causa dei costi più elevati per la creazione e distruzione di un thread nel kernel, alcuni sistemi "riciclano" i loro thread, che invece di essere distrutti vengono segnati come non eseguibili, ma le sue strutture dati non vengono intaccate. Successivamente quando deve essere creato un nuovo thread, ne viene riattivato uno vecchio, risparmiando un po' di overhead.
Se un thread causa un errore di pagina, il kernel verifica la disponibilità di altri thread eseguibili nel processo e può eseguire uno di essi.
### Implementazione ibrida

![[SOR/img/img31.png|center|500]]

Vari metodi per combinare i vantaggi dei thread utente con i thread del kernel. Una possibilità è usare i thread del kernel e fare il multiplexing dei thread utente su alcuni thread del kernel. Con questo approccio, il programmatore decide quanti thread del kernel usare e quanti thread utente multiplexare. Nell'implementazione ibrida, il kernel è a conoscenza solo dei thread del kernel, ma ogni thread del kernel può gestire più thread a livello utente. 

# Comunicazione fra processi
I processi necessitano di comunicare tra loro, basta pensare che in una pipeline di shell, l'output del primo processo deve essere passato al secondo e così via. Quindi la necessità è quella che i processi comunichino senza bisogno degli interrupt. Nei paragrafi esamineremo i problemi relativi alla *comunicazione tra processi* o **IPC** (**InterProcess Communication**). In breve i problemi da affrontare sono tre: 
- come un processo possa passare informazioni ad un altro
- essere sicuri che due o più processi non vadano ad accavallarsi
- corretta sequenzialità quando vi sono delle dipendenze (il processo A produce dati che il processo B deve stampare, B deve attendere che A abbia prodotto i dati prima di stampare)

## Race conditions
In alcuni sistemi operativi i processi che stanno lavorando insieme possono condividere una memoria comune che ciascuno può leggere e scrivere e che si trova in memoria principale o in un file condiviso (la posizione *non* modifica la natura della comunicazione o i relativi problemi). 

>**Esempio**
>Consideriamo uno spool di stampa. Quando un processo vuole stampare un file, inserisce il suo nome in una **directory di spool**. Il **demone di stampa** verifica se ci sia qualche file da stampare, che in caso stampa rimuovendo il loro nome dalla directory. Immaginiamo che la directory abbia $n$ posti ognuno in grado di contenere il nome di un file e abbia anche due variabili condivise: `out` che punta al file successivo da stampare e `in` che punta al primo posto libero nella directory. 
>A un certo istante i posti 0 e 3 sono vuoti (i file sono stati già stampati) mentre i posti 4 e 6 sono pieni. Supponiamo che simultaneamente i processi A e B decidano di voler accodare un file per stamparlo. Cosa succede? 
>Il processo A legge `in` e memorizza il valore 7 in una variabile locale chiamata `next_free_slot`. Subito dopo avviene un interrupt del clock e la CPU decide che il processo A è stato eseguito abbastanza, passando al processo B, che legge `in`, memorizzando anch'esso il valore 7. A questo punto entrambi i processi ritengono che il primo slot disponibile sia 7. Il processo B continua ad essere eseguito, salvando il nome del file da stampare nello slot 7. All'esecuzione del processo A, esso legge `next_free_slot = 7` scrivendo il nome del file da stampare al suo interno e aggiornando `in`. La directory di spool ora è coerente al suo interno, ma il processo B è in attesa di un output che non sarà mai emesso, dato che il suo file è stato cancellato dal processo A.

Situazioni del genere, in cui due o più processi stanno leggendo o scrivendo gli stessi dati e il risultato finale dipende da chi viene scelto per l'esecuzione, sono chiamate **race condition**. Vediamo ora come evitarle.

### Regioni critiche
L'idea è quella di proibire agli altri processi di leggere e scrivere dati condivisi in uso da un processo in quel momento. Un processo, durante la sua esecuzione, è impegnato in calcoli interni ed attività che non portano a race condition, ma al momento in cui deve accedere in memoria o a file condivisi possono verificarsi le race condition. La parte di programma in cui si accede alla memoria condivisa è chiamata **regione critica**. Per evitare le race condition servono quattro condizioni: 
- Due processi non possono trovarsi contemporaneamente all'interno delle rispettive regioni critiche.
- Non si possono fare ipotesi sulla velocità o sul numero di CPU.
-  Nessun processo in esecuzione al di fuori della propria regione critica può bloccare altri processi.
- Nessun processo deve aspettare all'infinito per entrare nella propria regione critica.

Il comportamento che si vorrebbe ottenere è illustrato in figura.

![[SOR/img/img32.png|center|700]]

**Non** sono soluzioni alla race condition:
- Disibilitare gli interrupt: impedisce che la CPU venga riallocata, ma funziona solo per sistemi a CPU singola
- Bloccare le variabili: proteggere le regioni critiche con variabili 0/1. Le "corse" si verificano ora sulle variabili di blocco
#### Esclusione con busy waiting
Una terza (non) soluzione è quella mostrata nei due codici in figura 

![[SOR/img/img33.png|center|700]]

L'azione di testare continuamente una variabile finché non assume un valore si chiama *busy waiting*, che andrebbe generalmente evitato perché consuma CPU. Ha senso utilizzare il busy waiting quando ci si aspetta che il tempo di attesa sia breve. Con questa soluzione un processo fuori dalla regione critica può effettivamente bloccare un altro processo, andando contro al terzo punto delle condizioni per evitare le race condition. 

##### Soluzione di Peterson
Alice e Bob vogliono usare *un'unica postazione computer* in un ufficio, ma ci sono delle regole: 
- solo una persona per volta può utilizzare il computer
- se entrambi vogliono usarlo contemporaneamente, devono decidere chi va per primo 

```C
#define N 2     /*numero di processi*/
int turn;       /*a chi tocca?*/
int interested[N]; /*tutti i valori inizialmente a 0*/

void enter_region(int process); /*process è 0 o 1*/
{
	int other;  /*numero dell'altro processo*/
	other = 1 - process;  /*l'opposto del processo*/
	interested[process] = TRUE;  /*mostra che si è interessati*/
	turn = process; /*imposta il flag*/
	while(turn == process && interested[other] == TRUE); 
}

void leave_region(int process);  /*process: chi esce*/
{
	interested[process] = FALSE; /*indica l'uscita dalla regione critica*/
}

```

**Idea** dell'algoritmo:
- Alice e Bob devono segnalare il loro interesse a usare il computer
- Se l'altro non è interessato, la persona interessata può usarlo subito
- Se entrambi mostrano interesse, registrano il loro nome su un foglio. Ma se scrivono allo stesso tempo, l'ultimo nome sul foglio ha la precedenza
- La persona che non ha la precedenza aspetta finché l'altra ha finito
- Una volta finito, la persona che ha usato il computer segnala che ha finito e l'altra può iniziare

#### Mutua esclusione nella CPU
Come è realizzata la mutua esclusione nella CPU? È possibile utilizzare due istruzioni in assembly: 
- TSL (Test and Set Lock): Legge il contenuto della parola della memoria "`lock`", salva al suo interno un valore non zero e blocca l'accesso alla memoria per le altre CPU.
- XCHG: scambia i contenuti di due posizione atomicamente. Istruzione utilizzata in tutte le CPU x86 Intel per sincronizzazione di basso livello.

**TLS**
Vediamo come funziona brevemente l'istruzione `TLS`. Essa utilizza una variabile condivisa, detta `lock`. 
Quando `lock` è a 0:
- Un processo può impostare `lock` a 1 con l'istruzione `TLS` e accedere alla memoria condivisa.
- Al termine il processo resetta `lock` a 0.
Vediamo come può essere utilizzata l'istruzione per impedire l'accesso contemporaneo alla regione critica nel pezzo di codice sottostante.

```Assembly
enter_region:
	TSL REGISTER, LOCK | copia il lock in register e lo imposta a 1
	CMP REGISTER, #0   | il lock era a zero?
	JNE enter_region   | se non era zero, il lock era impostato. Riesegui il ciclo
	RET                | torna al chiamante; siamo nella regione critica

leave_region:
	MOVE LOCK, #0      | memorizza 0 in lock
	RET                | torna al chiamante
``` 

Prima di entrare nella regione critica, un processo chiama `enter_region`, che va in busy waiting  finché il `lock` non è libero. Per uscire dalla regione critica chiama `leave_region` che salva 0 nella variabile `lock`. Come tutte le soluzioni basate sulle regioni critiche, i processi devono chiamare `enter_region` e `leave_region` al momento giusto perché il metodo funzioni

**XCHG**
```Assembly
enter_region:
	MOVE REGISTER, #1   | mette 1 nel registro
	XCHG REGISTER, LOCK | scambia il contenuto di register e di lock
	CMP REGISTER, #0    | il lock era zero?
	JNE enter_region    | se non era zero, il lock era impostato. Riesegui il ciclo
	RET                 | torna al chiamante; siamo nella regione critica

leave_region:
	MOVE LOCK, #0      | memorizza 0 in lock
	RET                | torna al chiamante
```

Le soluzioni viste ora sono corrette, ma entrambe hanno bisogno del busy waiting. Quello che fanno queste soluzioni, sostanzialmente, è questo: quando un processo vuole entrare nella sua regione critica, controlla se l'ingresso è consentito; in caso contrario il processo si pone in attesa in un ciclo corto finché non è consentito l'accesso.
Questi approcci, però, consentono ad un processo di tenere occupata la CPU, sprecando il tempo della CPU e quindi le risorse. La soluzione è quella di lasciare che un processo in attesa di entrare nella regione critica restituisca volontariamente la CPU allo scheduler. 
Questa soluzione è realizzata mediante l'uso di `sleep` e `wakeup`. La prima è una chiamata di sistema che provoca il blocco del chiamante, mentre la seconda ha un parametro, ovvero il processo che va risvegliato. 

![[SOR/img/img34.png|center|700]]

#### Problema produttore-consumatore
In questo problema, due processi condividono un buffer di dimensione fissa. Il produttore mette informazioni nel buffer, mentre il consumatore le preleva. Il problema sorge quando il produttore vuole inserire degli elementi nel buffer quando questo è pieno. La soluzione del produttore è quella di entrare in sleep, per essere risvegliato dal consumatore quando ha rimosso uno o più elementi. Analogamente, il consumatore dorme se il buffer è vuoto e viene risvegliato quando il produttore ha inserito qualcosa. 

Per tener traccia del numero di elementi nel buffer ci sarà bisogno di una variabile, `count`. Se il numero massimo di elementi nel buffer è $N$, il codice del produttore controllerà prima se `count` è uguale ad $N$. Se lo è si mette in sleep, altrimenti aggiunge elementi aggiornando `count`. Il codice del consumatore è simile: prima controlla se `count` è $0$; se lo è entra in sleep, altrimenti rimuove elementi e decrementa `count`. 

```C
#define N 100
int count = 0;

void producer(void){
	int item;

	while(TRUE){
		item = produce_item();
		if(count == N) 
			sleep();
		insert_item(item);
		count++;
		if(count == 1)
			wakeup(consumer);
	}
}

void consumer(void){
	int item;

	while(TRUE){
	if(count == 0)
		sleep();
	item = remove_item();
	count--;
	if(count == N-1)
		wakeup(consumer);
	consume_item(item);
	}
}
```

In questo codice possono accadere la *race condition*, perché l'accesso a `count` non è vincolato. Pensiamo a questa situazione: il buffer è vuoto e il consumatore ha verificato se `count` è  a $0$. In quel momento lo scheduler decide di fermare temporaneamente l'esecuzione del consumatore ed eseguire il produttore, che inserisce un elemento nel buffer, incrementando `count` ad $1$. Siccome prima `count` era a $0$ e quindi il consumatore dovrebbe essere in sleep, il produttore chiama `wakeup`, ma il consumatore, non essendo logicamente in `sleep` (il blocco dell'esecuzione è avvenuto tra l'if e la sleep nel consumatore), il segnale di risveglio viene perso. Quando il consumatore sarà eseguito la volta successiva, controllerà il valore di `count` che aveva letto in precedenza, troverà $0$ ed entra in `sleep`. In seguito il produttore riempirà il buffer, andando poi in sleep ed entrambi i processi si trovano addormentati. 
Il problema sta nel caso che un `wakeup` inviato ad un processo non ancora dormiente, viene perso. 
Per risolvere questo problema basta aggiungere un **bit di attesa wakeup**: quando ad un processo non ancora dormiente è inviato un `wakeup`, viene impostato il bit di attesa. Più tardi, quando il processo tenta di entrare in `sleep`, se il bit di attesa è accesso, lo spegne, ma il processo rimane sveglio. 

#### Semafori
Dijkstra suggerì di contare il numero di wakeup per uso futuro, tramite una variabile intera, introducendo un nuovo tipo di variabile, detto **semaforo**. Questa variabile può assumere valore $0$, indica che non sono stati salvati wakeup, o un valore positivo se uno o più wakeup sono in attesa. Le operazioni possibili sono due `up` e `down`:
- `down`: se il valore del semaforo è maggiore di zero, questo valore viene decrementato e il processo continua la sua esecuzione. Se il valore del semaforo è zero, il processo che ha invocato `down` viene messo in sleep senza completare il down.
- `up`: incrementa il valore del semaforo. Se il valore è zero, ci sono dei processi in coda d'attesa incapaci di completare il down; uno di loro è scelto dal sistema, viene risvegliato e completa il suo down. In questo modo, dopo un `up` su un semaforo a $0$, il semaforo rimarrà sempre a $0$, ma ci sarà un processo in meno in stato di sleep. 
Le azioni di un semaforo come il controllo del valore, l'incremento/decremento e andare in stato di sleep sono **azioni atomiche indivisibili**. È garantito, quindi, che una volta che l'operazione sul semaforo è avviata, nessun altro processo può accedere al semaforo finché l'operazione non è completata o bloccata. Questo è essenziale per risolvere i problemi di sincronizzazione ed evitare race condition. 

Vediamo ora come risolvere il problema del produttore-consumatore utilizzando i semafori. 

```C
#define N 100
typedef int semaphore;
semaphore mutex = 1;    /*controlla l'accesso alla regione critica*/
semaphore empty = N;    /*conta i posti vuoti nel buffer*/
semaphore full = 0;     /*conta i posti pieni nel buffer*/

void producer(void){
	int item;

	while(TRUE){
		item = produce_item();  /*genera un item da mettere nel buffer*/
		down(&empty);           /*decrementa empty*/
		down(&mutex);           /*entra nella regione critica*/
		insert_item(item);
		up(&mutex);             /*lascia la regione critica*/
		up(&full);              /*incrementa il contatore dei posti pieni*/
	}
}

void consumer(void){
	int item;
	
	while(TRUE){
		down(&full);             /*decrementa il contatore dei posti pieni*/
		down(&mutex);            /*entra nella regione critica*/
		item = remove_item();
		up(&mutex);              /*esce dalla regione critica*/
		up(&empty);              /*incrementa il contatore dei posti vuoti*/
		consume_item(item);
	}
}
```

#### Mutex
I **mutex** sono una versione semplificata dei semafori, utili per gestire la mutua esclusione di risorse o codice condiviso, quando non bisogna contare accessi o altri fenomeni. 
Un mutex è una variabile che si può trovare in due stati: *locked* o *unlocked*. Un bit basta per rappresentare i due stati, ma viene usato un intero, $0$ per unlocked, 1 per locked. Le procedure per un mutex sono due: `mutex_lock` e `mutex_unlock`. 
Quando un thread vuole accedere ad una regione critica, chiama `mutex_lock`; se il mutex è unlocked, il thread può entrare; se è locked, il thread attende. 
Al termine dell'accesso il thread chiama `mutex_unlock`, per liberare la risorsa. Il codice di `mutex_lock` è simile al codice di [[#Mutua esclusione nella CPU|enter_region]], con la differenza che quando `enter_region` non riesce ad entrare nella regione critica, continua a testare il lock (busy waiting). Quando, invece, un thread non può acquisire il lock nel `mutex_lock` chiama `thread_yield` per cedere la CPU ad un altro thread, chiamando lo scheduler di un thread nello spazio utente.

Vediamo come il codice di `mutex_lock` e `mutex_unlock` sono simili ad `enter_region` e `leave_region`.

```Assembly
mutex_lock:
	TSL REGISTER, MUTEX   | copia mutex nel registro e lo imposta ad 1
	CMP REGISTER, #0      | mutex era zero?
	JZE ok                | se era zero il mutex era unlock, salta ad ok
	CALL thread_yield     | il mutex è occupato; schedula un altro thread
	JMP mutex_lock        | prova di nuovo
ok: RET                   | ritorna al chiamante; siamo nella regione critica

mutex_unlock:
	MOVE MUTEX, #0        | metti 0 in mutex 
	RET                   | torna al chiamante
```

Alcuni pacchetti di thread offrono la chiamata `mutex_trylock`, che o acquisisce il lock oppure restituisce un errore, senza bloccare. 

#### Futex
L'efficienza nella sincronizzazione diventa cruciale con l'aumento del parallelismo, perché spin lock, mutex e busy waiting sono efficaci per attese brevi, ma nelle attese lunghe sprecano CPU. Passare al kernel per bloccare processi funziona bene solo in caso di contese frequenti, mentre se ci sono poche contese è troppo oneroso.
Una soluzione che cerca di mettere insieme entrambi gli approcci è quella dei **futex (fast user space mutex)**, una caratteristica tipica di Linux per implementare lock basici (simili al mutex) evitando il kernel finché non è necessario, *migliorando quindi le prestazioni*. 
Un futex è costituito di due parti: 
- un *servizio kernel*: fornisce una "coda d'attesa" che consente a più processi di attendere un lock. I processi non sono in esecuzione a meno che il kernel non li sblocchi. Inserire un processo in coda d'attesa richiede una chiamata di sistema onerosa, è quindi opportuno evitarlo. 
- una *libreria utente*
In assenza di contese il futex opera nello spazio utente: i processi condividono una variabile lock comune. Supponiamo che, inizialmente, il lock abbia valore $1$, ovvero libero. Un thread si appropria del lock eseguendo un istruzione "decrement and test". Il thread verifica i risultati per controllare se il lock era libero o no, se non era locked, il thread se ne appropria, mentre se c'è contesa, passa al kernel per mettesi in coda d'attesa. Il costo del passaggio al kernel è giustificato dal fatto che il thread sarebbe stato comunque bloccato. 
Quando un thread ha terminato di utilizzare il lock, lo rilascia con un istruzione "increment and test" e verifica il risultato per vedere se ci sono thread nella lista d'attesa e nel caso fa sapere al kernel di sbloccarne uno; in assenza di contese, il kernel non viene chiamato in causa. 

La libreria Pthreads fornisce delle funzioni per la sincronizzazione dei thread. Il meccanismo base impiega una variabile mutex, locked o unlocked, a guardia della regione critica. Un thread che desidera accedere alla regione critica, prova a mettere in lock il mutex. Se il mutex è unlocked, l'accesso è immediato e viene impostato il lock, se invece è locked il thread attende. 

| Thread call              | Descrizione                                               | 
| ------------------------ | --------------------------------------------------------- |
| `pthread_mutex_init`     | Crea un mutex                                             |
| `pthread_mutex_destroy`  | Distrugge un mutex esistente                              |
| `pthread_mutex_lock`     | Acquisisce un lock e si blocca                            |
| `pthread_mutex_trylock`  | Acquisisce un lock o fallisce                             |
| `pthread_mutex_unlock`   | Rilascia un lock                                          |
| `pthread_cond_init`      | Crea una variabile condizione                             |
| `pthread_cond_destroy`   | Elimina una variabile condizione                          |
| `pthread_cond_wait`      | Si blocca in attesa di un segnale                         |
| `pthread_cond_signal`    | Invia un segnale ad un altro thread e lo risveglia        |
| `pthread_cond_broadcast` | Invia un segnale a molteplici thread e li risveglia tutti |

**Semafori o mutex**
- Finalità:
	- **Mutex**: È utilizzato principalmente per *garantire la mutua esclusione*. È destinato a proteggere l'accesso a una risorsa condivisa, garantendo che un solo thread possa accedervi per volta
	- **Semaforo**: Può essere utilizzato per *controllare l'accesso a una risorsa condivisa*, ma è anche spesso usato per la sincronizzazione tra thread
- Semantica:
	- **Mutex**: di solito ha una semantica di "proprietà", il che significa che solo il thread che ha aquisito il mutex può rilasciarlo
	- **Semaforo**: Non ha una semantica di proprietà. Qualsiasi thread può aumentare o diminuire il conteggio del semaforo, indipendentemente da chi lo ha modificato l'ultima volta
- Casistica:
	- Per *l'esclusione mutua*: Un **mutex** è generalmente preferibile. È più semplice (ha solo operazioni di lock/unlock) e spesso offre una semantica più rigorosa e un comportamento più prevedibile
	- Per la *sincronizzazione tra thread*: Un **semaforo** può essere più adatto, specialmente quando si tratta di coordinare tra diversi thread o di gestire risorse con un numero limitato di istanze disponibili

#### Monitor
La comunicazione tra processi utilizzando *mutex e semafori non è semplice*, perché programmare con i semafori richiede molta attenzione: piccoli errori possono causare comportamenti indesiderati come race conditions e deadlock. 
Per semplificare la scrittura di programmi corretti, Hansen e Hoare proposero una primitiva di sincronizzazione ad alto livello, detta **monitor**. 
Un monitor raggruppa procedure, variabili e strutture dati. I processi possono chiamare le procedure di un monitor, ma non possono accedere direttamente alle sue strutture dati interne. I monitor hanno una proprietà che li rende utili per ottenere la *mutua esclusione*, ovvero solo un processo può essere attivo in un monitor in un dato momento. Il *compilatore si occupa di gestire la mutua esclusione*, riducendo la probabilità di errori da parte del programmatore, che non deve conoscere come il compilatore realizza la mutua esclusione.
Sebbene i monitor forniscano un modo semplice per ottenere la mutua esclusione, abbiamo anche bisogno di un modo per bloccare i processi quando non possono andare avanti. La soluzione sta nell'introduzione delle **variabili di condizione** e delle **operazioni su di esse**, `wait` e `signal`. 
Quando una procedura di un monitor scopre che non può continuare (ad esempio il produttore trova il buffer pieno), effettua una `wait` su qualche variabile di condizione, bloccandosi e consentendo l'accesso al monitor ad un processo che era stato bloccato in precedenza. A differenza dei semafori, le variabili condizionali non accumulano segnali, quindi se un segnale viene inviato e non c'è un processo in attesa, il segnale viene perso. 
Linguaggi come Java supportano i monitor, permettendo una sincronizzazione e mutua esclusione più sicura e semplice in contesti multithreading. In Java, i metodi dichiarati con la parola chiave `synchronized`, garantiscono che una volta che un qualunque thread ha iniziato a eseguire quel metodo, non sarà consentito a un altro thread di iniziare ad eseguire alcun altro metodo `synchronized` di quell'oggetto.

Nel seguente blocco vediamo un esempio di monitor.
```
monitor example
	integer i;
	condition c;
	
	procedure producer();
	.
	.
	.
	end;
	
	procedure consumer();
	.
	.
	.
	end;
```

```C
monitor ProdCons{
	condition full, empty;
	int count = 0;
	void enter(int item){
		if (count == N) 
			wait(full);
		insert_item(item);
		count++;
		if (count == 1)
			signal(empty);
	}
	void remove(int *item){
		if (count == 0)
			wait(empty);
		*item = remove_item();
		count--;
		if (count == N-1)
			signal(full);
	}
}

void producer(){
	int item;
	while(TRUE){
		item = produce_item();
		ProdCons.enter(item);
	}
}

void consumer(){
	int item;
	while(TRUE){
		ProdCons.remove(&item);
		consume_item(item); 
	}
}
```

**Differenza tra sleep/wakeup e wait/signal

- *Sleep/wakeup*: è1 un meccanismo primitivo per mettere un processo/thread in attesa (sleep) e poi risvegliarlo (wakeup).
	- Problema: possono portare a race conditions.
		- Immagina che il figlio A voglia svegliare il figlio B. Se il processo A chiama `wakeup` per il processo B proprio mentre B sta chiamando `sleep`, potrebbe accadere che B dorma indeterminatamente perché ha perso il segnale di wakeup.
- *Wait/signal*: **la differenza cruciale** sta nel fatto che wait e signal sono protetti dalla mutua esclusione nei monitor, infatti una volta che un processo/thread entra in una procedura del monitor, ha l'esclusività completa di quella procedura fin quando non termina o chiama `wait`.
- Se un thread/processo chiama wait all'interno del monitor, può essere certo che non verrà interrotto (per esempio dallo scheduler) finché non ha terminato di posizionarsi in uno stato di attesa; questo elimina la possibilità di perdere il segnale come per sleep e wakeup.

#### Scambio di messaggi
Lo **scambio di messaggi** è un metodo di comunicazione tra processi che usa due primitive: `send` e `receive`, che come i [[#Semafori|semafori]] e diversamente dai [[#Monitor|monitor]] sono chiamate di sistema piuttosto che costrutti del linguaggio. 
I sistemi a scambio di messaggio presentano problematiche e questioni di progettazioni assenti invece nei semafori e nei monitor, soprattutto se i processi comunicanti sono su macchine differenti connessi da una rete. 
Questi problemi sono:
- la perdita di messaggi
- la necessità di acknowledgment per confermare la ricezione del messaggio
- la gestione dei messagig duplicati
- l'autenticazione e la denominazione dei processi
Malgrado l'inaffidabilità, lo scambio di messaggi è cruciale nello studio delle reti.

Vediamo ora come il problema produttore-consumatore può essere risolto con lo scambio di messaggi e senza memoria condivisa. 

```C
#define N 100 /*numero di posti nel buffer*/

void producer(){
	int item;
	message msg;

	while(TRUE){
		item = produce_item();  /*genera qualcosa da mettere nel buffer*/
		receive(consumer, &msg); /*aspetta che ne arrivi uno vuoto*/
		build_message(&msg, item); /*costruisci un messaggio da spedire*/
		send(consumer, &msg); /*manda il messaggio al consumatore*/
	}
} 

void consumer(){
	int item, i;
	message msg;

	for(i = 0, i < N, i++){
		send(producer, &msg); /*invia N messaggi vuoti*/
	}
	while(TRUE){
		receive(producer, &msg); /*prende un messaggio che contiene un elemento*/
		item = extract_item(); /*estrae l'elemento dal messaggio*/
		send(producer, &msg);  /*ne manda indietro uno vuoto*/
		consume_item(item); /*fa qualcosa con l'elemento*/
	}
}
```

Nella soluzione sono utilizzati $N$ messaggi, analoghi agli $N$ posti disponibili del buffer nella memoria condivisa. Il consumatore invia al produttore $N$ messaggi vuoti, mentre il produttore, una volta ricevuto un messaggio vuoto, lo riempie e lo invia indietro al consumatore. Se il produttore lavora più velocemente del consumatore, tutti i messaggi finiranno per essere pieni, in attesa del consumatore; in questo caso il produttore si bloccherà in attesa che ritorni un messaggio vuoto. Il discorso è analogo se il consumatore lavorasse più velocemente del produttore. 

Vediamo ora come vengono indirizzati i messaggi: 
- indirizzo univoco per ogni processo
- introduzione di **mailbox**, una nuova struttura dati utilizzata come buffer. Le chiamate di `send` e `receive` fanno riferimento alla mailbox e non ai processi. Quando un processo prova ad inviare un messaggio ad una mailbox piena, è sospeso finché un messaggio è rimosso dalla mailbox.

#### Barriere
Le barriere sono un meccanismo di sincronizzazione pensato per gruppi di processi piuttosto che per situazioni come il problema del produttore-consumatore. Alcune applicazioni sono suddivise in fasi ed hanno la regola che nessun processo può avanzare alla fase successiva finché tutti i processi non sono pronti a passare alla fase successiva. Le barriere sono d'aiuto proprio in queste circostanze. Quando un processo raggiunge una barriera, si mette in attesa finché tutti gli altri processi non l'abbiano raggiunta. 

![[SOR/img/img35.png|center|600]]

>**Esempio**
>Un esempio di applicazione sta in un problema di rilassamento di ingegneria o fisica. Supponiamo di avere una matrice contenente dei valori iniziali, che possono rappresentare temperature in vari punti su un foglio di metallo. L'idea potrebbe essere di calcolare quanto occorre per l'effetto di propagazione nel foglio di una fiamma in un angolo. Partendo dai valori attuali, alla matrice è applicata una trasformazione per ottenerne una seconda versione, per esempio applicando le leggi della termodinamica per vedere le temperature ad un intervallo deltaT successivo. Il processo è ripetuto più volte, riportando le temperature nei punti di test come funzione del tempo mentre il foglio si scalda. 
>Supponiamo che questa matrice sia molto grande, ad esempio 1 milione per un milione, processi differenti lavorano su parti diverse della matrice, calcolando i nuovi elementi della matrice. Nessun processo può avanzare ad un iterazione successiva $n+1$, finché tutti i processi non hanno completato l'iterazione $n$. Per risolvere questo problema si attua l'utilizzo delle barriere.

#### Ready-copy-update
I migliori lock sono quelli che non si usano e la domanda è se è possibile permettere accessi in lettura e scrittura concorrenti a strutture dati condivise senza l'utilizzo di lock. La risposta (a primo impatto) è no. Supponiamo due processi A e B in cui A ordina un'array di numeri e B ne calcola la media. Poiché A sposta avanti e indietro i valori nell'array, B potrebbe trovare più volte gli stessi valori e calcolare quindi una media errata. Il principio base di **ready-copy-update** sta nel consentire letture simultanee leggendo una verisone vecchia o nuova della struttura, ma mai un mix delle due. 

I processi in lettura attraversano l'albero dalla radice alle foglie. In foto sotto viene aggiunto un nodo X appena prima di renderlo visibile sull'albero, inizializzando tutti i valori nel nodo X compresi i puntatori ai figli. Con un'operazione unica e atomica si fa in modo che X diventi figlio di A. Tutti i processi stanno ora leggendo la versione nuova della struttura.

![[SOR/img/img36.png|center|700]]

In figura vengono eliminati B e D. Per primo si fa in modo che il puntatore al figlio sinistro di A punti a C: tutti i processi in lettura che si trovano in A continuano con il nodo C senza mai vedere né B né D, quindi vedono solamente la nuova versione. Tutti i processi in lettura sul nodo B e D vedono invece la versione vecchia.

![[SOR/img/img37.png|center|700]]


L'unico problema sta nel sapere quando liberare B e D, per questo bisogna rimanere in attesa finché l'ultimo processo in lettura non abbia liberato B e D. L'operazione **RCU** determina il tempo massimo per un processo in lettura per trattenere un riferimento, dopo questo periodo, può richiedere la memoria in tutta sicurezza. I processi in lettura accedono alla struttura dati in una **sezione critica read-side**, che può contenere qualsiasi codice, purché non si blocchi o vada in sleep, in questo modo possiamo conoscere quanto tempo al massimo occorre rimanere in attesa. 
Si definisce **grace period** il tempo in cui ogni thread esce almeno una volta dalla sezione critica. Il tutto funziona perfettamente se si attende un periodo pari almeno al grace period, prima di liberare la memoria. 
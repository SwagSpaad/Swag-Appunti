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


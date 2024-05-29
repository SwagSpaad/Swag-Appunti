# Che cos'è un OS?
Un moderno calcolatoreè formato da:
- Uno o più processori
- Memoria centrale
- Dischi, Stampanti e altri dispositivi I/O

I dettagli di basso livello sono molto complessi
Gestire queste componenti richiede uno strano intermedio software: Il **Sistema Operativo**

## Componenti di un computer moderno (1)
- Uno o più processori
- Memoria principale
- Dischi o unità flash
- Stampanti
- Tastiera
- Mouse
- Display
- INterfaccia di rete
- DIspositivi I/O

Doppia modalità supportate dall'hardware:
- Modelità kernel
- Modalità utente

## The operating system as an extended machine
Idea di **astrazione**
- Il sistema operativo si pone tra l'hardware e le applicazioni
Visione **Top-Down**
- Il SO fornisce astrazioni ai programmi applicativi
Vista **Botton-Up**
- Il SO gestisce parti di un sistema operativo e fornisce un'allocazione ordinata e controllata dalle risorse
## Il sistema operativo come gestore di risorse
Da un moderno sistema operativo ci aspettiamo che gestisca:
- **Più programmi in esecuzione**
- **Più utenti**

Necessita allocazione ordinata a econtrollata di:
- Risorse quali: processori, memoria, unità I/O

Non solo Hardware: file, database....

**Multiplexing**:
- Nel **tempo**: CPU, stampante...
- Nello **spazio**: memoria centrale, disco...

# Uno sguardo all'hardware
## Processore

La CPU è il cervello del computer, esegue istruzioni dalla memoria

Il ciclo base della CPU: **preleva** (fetch), **decodifica** (decode), **esegue** (execute) istruzioni, i programmi vengono eseguiti in questo ciclo

Le CPU eseguono un set specifico di istruzioni:
- I registri interni memorizzano dati e risultati
- I set di istruzion includono funzione per il caricamento/salvataggio dati della memoria
- registri speciali come il **program counter** indicano l'istruzione successiva

Lo **stack pointer** punta alla cima dello stack in memoria

- Lo stack contiene frame di procedure con parametri e variabili locali

Il registro **Program status world** contiene informazioni sullo stato del programma
- È fondamentale per le chiamate di sistema e I/O

Il SO gestisce il multiplexing temporale della CPU
- Durante il multiplexing, il SO salva e ripristina i registri, permettendo al SO di eseguire programmi in modo efficiente

Progettazioni avanzate: **pipeline**
- Non del tutto trasparenti al SO
## Più di un processore

Multithreading:

Tiene all'interno della CPU lo stato di due o thread ma non c'è una esecuzione parallela vera e propria, il SO deve tenerne conto

**Vantaggi**:

- Throughput
- Economia di scala
- Affidabilità
- Multicore
- GPU
- Impatto delle cache

## La memoria
|   |
|---|
|![[SOR/SO/img/img3.png]]|

Problemi del sistema cache:

- Quando inserire un nuovo elemento nella cache?
- In quale riga della cache inserire il nuovo elemento?
- Quale elemento rimuovere dalla cache quando è necessario uno slot
- Dove mettere un elemento appena eliminato nella memoria più grande
## Dispositivi di I/O
Si individuano due componenti:
- il **controller**: più semplice da usare per il SO
- il **dispositivo** in sè: intefaccia elementare ma complicata da pilotare
	- *ex*: dischi SATA

Ogni controller ha bisogno di un driver per il S.O
Il driver interagisce con il controller attraverso le porte I/O:
- Istruzioni tipo IN/OUT
- Mappatura in memoria

**Esecuzione I/O**
- Il processo esegue la chiamata di sistema
- Il kernel effettua una chiamata al driver
- Il driver avvia l'I/O e interroga il dispositivo per vedere se ha finito oppure chiede al dispositivo di generare un interrupt quando ha finito

## DMA
Il DMA o Direct Memory Access è un dispositivo hardware speciale che consente ai componenti di accedere direttamente alla memoria del computer senza coinvolgere la CPU. Migliora l'efficienza del dispositivo e aumenta le prestazioni nelle operazioni di I/O ad alta velocità. Si utilizza per il trasferimento veloce di dati tra memoria e periferiche, Inoltre riduce il carico della CPU durante le operazioni di I/O, consentendole di concentrarsi su compiti più importanti 

## Buses
I bus sono "canali di comunicazione" che permettono a periferiche e hardware di un calcolatore di interfacciarsi e scambiare informazioni o dati attraverso la trasmissione e ricezione di segnali. Con l'evoluzione tecnologica, i moderni computer hanno bus supplementari per gestire l'aumento del traffico di dati.

*ex*: La CPU comunica con la memoria attraverso il bus DDR4 oppure con una GPU esterna sul bus PCIe. Con altri dispositivi attraverso un hub su un bus DMI

Il *PCIe* è uno dei bus più veloci nei computer attuali, utilizza un'architettura a connessioni punto a punto dedicate, migliorando l'efficienza rispetto ai bus condivisi.

L'*USB* è stato sviluppato per connettere dispositivi lenti al computer senza l'utilizzo di driver aggiunti, ma attualmente le versioni moderne, possono raggiungere la velocità di circa 40gbps

## BOOT
La memoria flash della MOBO contiene il firmware (BIOS), che dopo aver acceso il computer, la CPU lo esegue e svolge determinati compiti:
- Inizializza le RAM
- Esegue le scansioni dei bus PCI/PCIe e inizializza i dispositivi connessi
- Imposta il firmware runtime per i servizi critici che il sistema deve utilizzare dopo l'avvio
- Cerca la posizione della tabella delle partizioni sul secondo settore del dispositivo di avvio
- Legge semplici file system e successivamente avvia il bootloader 
- Infine carica il SOù
# Concetti base dei sistemi operativi
## Processi
Un concetto chiave di ogni SO è quello di **processo**, fondamentalmente un *programma in esecuzione*, associato ad uno **spazio degli indirizzi**, un elenco di locazioni di memoria da 0 a un massimo, che il processo può leggere e scrivere. Lo spazio degli indirizzi contiene: 
- il programma eseguibile
- i dati del programma
- il suo stack
Ogni processo ha associato un insieme di risorse, come *i registri, l'elenco di file aperti, gli allarmi in sospeso, un elenco dei processi relativi*  e altre informazioni necessarie per l'esecuzione del programma.

>**Esempio**
In un sistema di multiprogrammazione, diversi processi possono essere attivi contemporaneamente. Ad esempio l'utente può avviare un processo di elaborazione video e dato istruzioni per convertire un filmato di un'ora in un determinato formato (operazione che può richiedere qualche ora), per poi andare a navigare sul web. Nel frattempo è stato avviato un processo per controllare le e-mail in arrivo. Abbiamo quindi 3 processi attivi: l'elaborazione video, il browser web e il controllo delle e-mail. 
Periodicamente il sistema, per ottimizzare l'utilizzo del processore, decide di fermare un processo e avviarne un altro, per esempio perché il primo ha esaurito il suo tempo di utilizzo della CPU. 
Quando un processo viene sospeso, in seguito deve essere riavviato nello stesso stato di quando è stato fermato. Questo significa che tutte le informaioni relative al processo devono essere salvate da qualche parte al momento della sospensione. 
Per esempio un processo potrebbe aver aperto parecchi file per leggerli. Associato ad ogni file c'è un puntatore che indica la posizione attuale (il numero di byte o record da leggere). Alla sospensione del processo tutti questi puntatori devono essere salvati, così che una chiamata di `read` eseguita dopo il riavvio leggerà i dati del processo.
In molti sistemi operativi, le informazioni di ciascun processo (diversa dai contenuti del suo spazio degli indirizzi) è salvata in una tabella detta **tabella di processo**, che è un array di strutture, una per ogni processo in esecuzione.

Un processo (sospeso) comprende l'**immagine core** (il suo spazio degli indirizzi), l'elemento nella tabella di processo, il contenuto dei suoi registri e molti altri oggetti necessari per avviare il processo successivamente.
Le chiamate del sistema di gestione del processo sono quelle che hanno a che fare con la creazione e la chiusura di processi.

>**Esempio**
>Il processo **shell** legge i comandi da un terminale e l'utente ha appena richiesto il comando per la compilazione di un programma. Quando il processo ha terminato la compilazione, esegue una chiamata di sistema per terminare se stesso.

Se un processo può creare uno o più processi (detti figli) e questi possono crearne a loro volta altri, arriviamo ad una struttura ad albero che vediamo sotto in figura. 

![[SOR/SO/img/img5.png|center|350]]

I processi cooperanti necessitano di comunicare l'uno con l'altro per sincronizzare le loro attività (**sincronizzazione tra processi**).
Un processo ha a disposizione altre chiamate di sistema per richiedere più memoria, attendere che termini un processo figlio o sovrapporre il suo programma ad un altro.

A volte può esserci la necessità di inviare un'informazione a un processo in esecuzione senza che questo sia fermo in attesa. Questo accade, ad esempio, per un processo che sta comunicando con un altro attraverso la rete. Per evitare che un messaggio o la risposta si perdano, il mittente può richiedere che il suo sistema operativo ne notifichi la ricezione dopo un certo numero di secondi, in modo che possa ritrasmmetterlo in caso non sia ricevuto. In questo caso il sistema operativo invia un *segnale d'allarme* al processo, che comporta la sospensione di qualsiasi operazione in corso, il salvataggio dei registri nello stack ed esegue una procedura di gestione di segnali speciali, ad esempio per ritrasmettere un messaggio perso. Quando il gestore del messaggio ha terminato, il processo viene riavviato nello stato in cui si trovava al momento del segnale. 

I segnali sono l'analogia software degli *interrupt hardware* e possono essere generati da una varietà di cause. Molti *trap* rilevati dall'hardware, come l'accesso non consentito in memoria o l'utilizzo di un'indirizzo non valido sono convertiti in segnali per il processo responsabile.

A ogni persona autorizzata ad usare il sistema è assegnato uno **UID (user identification)** dall'amministratore di sistema. Ogni processo che parte ha l'UID dell'utente che l'ha avviato. Gli stessi utenti possono appartenere a dei gruppi, ognuno dei quali ha lo stesso **GID (group identification)**.

## Spazio degli indirizzi
Ogni computer ha una memoria principale impiegata per l'esecuzione dei programmi. I sistemi operativi (più sofisticati) consentono a più programmi di essere in memoria contemporaneamente e per far sì che non interferiscano tra loro c'è bisogno di un meccanismo di protazione, gestito dal sistema operativo. 
Un altro problema relativo alla memoria è la *gestione dello spazio degli indirizzi dei processi*. Ogni processo dispone di un insieme di indirizzi che può usare, da 0 ad un massimo, che di solito è inferiore alla memoria principale, in modo che il processo può riempire il suo spazio degli indirizzi, ma lasciando abbastanza memoria per mantenere tutto. 
In molti computer gli indirizzi sono di 32 o 64 bit, determinando uno spazio degli indirizzi di $2^{32}$ o $2^{64}$ byte. 

Cosa succede se un processo ha più spazio degli indirizzi rispetto alla memoria principale del computer e vuole usarla tutta? 
Esiste una tecnica detta memoria virtuale che consente al sistema operativo di tenere parte dello spazio degli indirizzi nella memoria e parte sul disco e scambiarne le parti in base alle necessità. 

## File
Un concetto comune dei sistemi operativi è il **file system**. Per creare, copiare, eliminare file sono necessarie delle chiamate di sistema. Prima che un file possa essere letto, deve essere localizzato in memoria e aperto; dopo che è stato letto occorre chiuderlo, quindi le chiamate realizzano questi scopi. 
I sistemi operativi utilizzano le **directory** come metodo per raggruppare i file. Le voci delle directory possono essere file o altre directory; questo modello dà vita ad una gerarchia: il file system.
Ogni file nella gerarchia delle directory può essere specificato dando il suo **nome di percorso (path name)**, partendo dall'inizio della gerarchia delle directory, ovvero dalla **directory principale (root directory)**.

In foto il file system di un dipartimento universitario.
![[SOR/SO/img/img6.png|center|700]]

Prima che un file possa essere letto o scritto, deve essere aperto e a quel punto sono controllati i permessi. Se l'accesso è consentito il sistema restituisce il **descrittore del file**, mentre se l'accesso è proibito viene restituito un codice d'errore.

### Pipe
Una pipe è vista come uno pseudofile che permette la connessione tra due processi. 
Se due processi A e B vogliono comunicare tramite una pipe, devono prima configurarla. Se A vuole inviare dati a B scrive sulla pipe come se fosse un file in output, mentre il processo B può leggere dalla pipe come se fosse un file in input. 

### Protezione
I file in UNIX sono protetti da un codice binario di 9 bit.
Esistono tre campi di 3 bit ognuno (proprietario, membri del gruppo a cui il proprietario appartiene, per tutti gli altri). Ogni campo ha un bit per l'accesso in scrittura, lettura ed esecuzione, conosciuti anche come **bit rwx**.

> *Esempio: $rwxr-x--x$: il proprietario puo' leggere, scrivere ed eseguire, il gruppo a cui appartiene il proprietario puo' leggere ed eseguire e tutti gli altri possono solo eseguire.

Per una directory $x$ significa permesso di ricerca.

## Shell
La shell è l'interprete dei comandi in UNIX ed è l'interfaccia principale tra l'utente al terminale e il sistema operativo, nel caso in cui l'utente non stia utilizzando l'interfaccia grafica.
All'avvio viene mostrato il **prompt** un simbolo del dollaro che comunica che la shell è in attesa di un comando. Se l'utente scrive ora il comando `date`, la shell crea un processo figlio ed esegue il programma `date` come figlio. Mentre il figlio è in esecuzione la shell rimane in attesa della sua terminazione, per poi restituire il prompt. 

L'utente può specificare che lo standard output sia diretto verso un file ad esempio $\text{date > file}$ oppure può essere ridiretto lo standard input come in $\text{sort < file1 > file 2}$
che fa sì che il programma `sort` abbia come input il `file1` e stampi l'output nel `file2`.

L'output di un programma può essere usato come input di un altro, connettendoli tramite una pipe; in questo caso: 
```sh
cat file1 file2 file3 | sort >/dev/lp
```
viene lanciato il programma cat per con*cat*enare tre file e invia l'output `sort` perché ordini le regole  in ordine alfabetico. L'output di `sort` è inviato a `/dev/lp`, in genere la stampante. 
Se un utente inserisce il carattere `&` dopo il comando  
```sh
cat file1 file2 file3 | sort >/dev/lp &
``` 
la shell non attende la fine dell'esecuzione, ma restituisce immediatamente il prompt, iniziando l'ordinamento in background, permettendo all'utente di lavorare normalmente. 

## Chiamate di sistema
I sistemi operativi hanno due funzioni principali: fornire astrazioni ai programmi utente e gestire le risorse del computer. L'interazione tra programmi utente e sistema operativo è a carico di quest'ultimo, come nel caso di creazione, scrittura e lettura di file. La gestione delle risorse è trasparente agli utenti e avviene automaticamente. 
L'interfaccia fra i programmi utente e il sistema operativo deve affrontare le astrazioni. Per capire cosa fa realmente il sistema operativo, dobbiamo osservare da vicino questa interfaccia.

Le **chiamate di sistema** sono l'interfaccia che il sistema operativo offre alle applicazioni per richiedere servizi. Dato che i meccanismi di invio di una system callsono dipendenti dal sistema operativo e dall'hardware, viene fornita una libreria di procedure (*libc*) per far sì che sia possibile fare chiamate di sistema da programmi C e da altri linguaggi. 

È utile osservare che ogni computer a CPU singola può eseguire un'operazione alla volta. Se un processo sta eseguendo un programma utente e necessita di un servizio del sistema, come leggere dati da un file, deve eseguire un istruzione di `trap` per trasferire il controllo al SO, che analizza i parametri della chiamata e capisce cosa voglia il processo, per poi realizzare la chiamata e restituire il controllo. 

**Oss.** Una chiamata di sistema è simile ad una speciale chiamata di procedura, solo che le chiamate di sistema entrano in *modalità kernel*.

Vediamo ora gli step per realizzare una chiamata di sistema di tipo `read`.
### La chiamata di sistema `read(fd, buffer, nbytes)`
La chaimata `read` ha tre parametri: 
- `fd`, che specifica il file
- `buffer`, punta al buffer
- `nbytes`, il numero di bytes da leggere
Come quasi tutte le chiamate di sistema è richiamata da programmi C chiamando una procedura di libreria con lo stesso nome della system call. 
Una chiamata da un programma C potrebbe apparire così: 
```C
count=read(fd, buffer, nbytes);
```
La system call (e la procedura della libreria) restituiscono il numero di byte realmente letto in `count`. Se la chiamata non può essere realizzata per un errore, `count` assumerà -1 come valore e il numero d'errore viene messo in una variabile globale, detta `errno`.

Le chiamate di sistema sono eseguite con una serie di passaggi:
- *Preparazione dei parametri*: il programma di chiamata mette i parametri nello stack (punti 1-3 della figura);
- *Chiamata alla procedura di libreria*: il programma chiamante effettua la chiamata alla procedura di libreria `read` (punto 4);
- *Collocazione del numero di chiamata di sistema* in un registro, ad esempio RAX (punto 5)
- *Passaggio in modalità kernel*: viene eseguita un'istruzione `trap` e si passa dalla modalità utente alla modalità kernel (passo 6). Il codice del kernel, esamina il numero di chiamata di sistema e poi indirizza al corretto gestore di system call (passo 7)
- *Esecuzione del gestore di chiamte di sistema* (passo 8)
- *Ritorno alla procedura di libreria dello spazio utente*: il controllo è restituito alla procedura di libreria utente, all'istruzione successiva alla chiamata della `trap` (passo 9)
	- *Possibilità di blocco*: la chiamata di sistema può bloccare il chiamante, ad esempio, se l'input desiderato non è disponibile. In questo caso il SO può eseguire altri processi.
- *Ripresa dopo il blocco*: se l'input o le condizioni desiderate sono disponibili, il processo bloccato viene ripreso, tornando alla procedura di libreria utente e procedendo all'istruzione successiva.

![[SOR/SO/img/img18.png|center|600]]

### Chiamate di sistema per la gestione dei processi

**Alcune delle principali chiamate di sistema**

| Call                                           | Description                                                         |
| ---------------------------------------------- | ------------------------------------------------------------------- |
| `pid  fork()`                                 | Crea processo simile al genitore                                    |
| `pid  waitpid(pid,  &statloc,  options)`   | Attendere che processo filgio termini                               |
| `s  =  execve(name,  argv,  environp)`     | Sostituire l'immagine centrale di un processo                       |
| `exit(status)`                                 | Termina processo e restituisce lo stato                             |
| `fd  =  open(file,  how,  ...)`            | Apre un file per la lettura, la scrittura o entrambe le operazioni. |
| `s  =  close(fd)`                            | Chiude un file aperto                                               |
| `n  =  read(fd,  buffer,  nbytes)`         | Legge dati da un file in un buffer                                  |
| `n  =  write(fd,  buffer,  nbytes)`        | Scrive dati da un buffer in un file                                 |
| `Position  =  lseek(fd,  offset,  whence)` | Sposta il puntatore del file                                        |
| `s  =  stat(name,  &buf)`                  | Ottiene informazioni sullo sttao di un file                                                                     |

- **Pid**: ProcessID
- **fd**: Descrittore del file
- **n**: Conteggio di byte
- **position**: È un offset all'interno del file

### Chiamate di sistema per la gestione del file system

| Call                                     | Description                                    |
| ---------------------------------------- | ---------------------------------------------- |
| `s  =  mkdir(name,  mode)`            | Crea dir                                       |
| `s  =  rmdir(name)`                    | rm dir empty                                   |
| `s  =  link(name1,  name2)`           | Crea una nuova voce, nome2, che punta a nome 1 |
| `s  =  unlink(name)`                   | rm voce dir                                    |
| `s  =  mount(spacial,  name,  flag)` | Monta file system                              |
| `s  =  unmount(spacial)` | Smonta file system                                                |

### Altre chiamate

| Call                          | Description                |
| ----------------------------- | -------------------------- |
| `s  =  chdir(dirname)`      | Cambia dir lavoro          |
| `s  =  chmod(name,  mode)` | Modifica bit di protezione |
| `s  =  kill(pid,  signal)` | Invia segnale a processo   |
| `s  =  time(&seconds)`     | Ottieni il tempo dal 1 Gennaio                           |


```C
#define TRUE 1
while(TRUE){
	type_prompt();
	read_command(command, parameters);
	if(fork() != 0){
		/*ParentCode*/
		waitpid(-1, &status, 0);
	}
	else{
		/*ChildCode*/
		execve(command, parameters, 0);
	}
}
```

## Struttura di un sistema operativo
Nei seguenti paragrafi esamineremo sei diverse strutture di un sistema operativo: 
- monolitico
- stratificato
- microkernel
- client-server
- macchine virtuali
- exokernel

### Sistemi monolitici
È l'organizzazione più comune; l'intero sistema operativo, scritto come una raccolta di procedure collegate all'interno di un singolo programma binario, viene eseguito come un programma singolo e in modalità kernel. 
Quando è usata questa tecnica, ogni procedura nel sistema è libera di richiamarne un'altra, se fornisce elaborazioni utili e necessarie. 

Questa organizzazione suggerisce la seguente struttura per il sistema operativo

![[SOR/SO/img/img19.png|center|700]]

- Un programma principale che richiama la procedura di servizio 
- Un insieme di procedure di servizio che realizzano le chiamate di sistema
- Un insieme di procedure di supporto  che aiutano le procedure di servizio

Alcune considerazioni su questo sistema:
- *Flessibilità vs Complessità*:
	- il sistema offre una flessibilità in termini di prestazioni e design
	- tuttavia, dato che è tutto interconnesso, un bug o un errore in una parte del sistema può causare problemi in altre parti, portando a crash sistemici
- *Mancanza di occultamento*:
	- tutte le procedure possono, in teoria, accedere a qualsiasi procedura o variabile all'interno del kernel
	- non c'è occultamento o isolamento tra le diverse parti del sistema
- *Utilizzo di "trap"*:
	- meccanismo attraverso il quale un programma può richiedere i servizi del sistema operativo, avviene attraverso interruzioni software che trasferiscono il controllo al sistema operativo

#### Sistemi a livelli
L'organizzazione stratificata dei sistemi operativi è una generalizzazione dell'approccio monolitico.
Il sistema THE implementò quest'idea con sei livelli gerarchici, che gestivano l'allocazione del processore, la memoria, la comunicazione, l'I/O, i dispositivi e gli utenti

![[SOR/SO/img/img20.png|center|600]]

Il sistema MULTICS usava anelli concentrici per definire i privilegi, con i livelli interni più privilegiati di quelli esterni. I vantaggi di questo modello sono la protezione delle risorse e dei dati critici ed una separazione chiara dei compiti. 

![[SOR/SO/img/img21.png|center|600]]


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

![[SOR1.5/img/img5.png|center|350]]

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
![[SOR1.5/img/img6.png|center|700]]

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

# Chiamate di sistema

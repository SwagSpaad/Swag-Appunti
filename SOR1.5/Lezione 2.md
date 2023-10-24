# Processi
Fondamentalmente un processo è un programma in esecuzione. Associato ad ogni processo c'è uno spazio degli indirizzi ovvero un elenco di locazione di memoria da 0 a un certo massimo, che il processore può eseguire operazioni di lettura e scrittura. Questo spazio contine il file eseguibile, i dati del rpogramma e lo stack. Associato al processo c'è anche un insieme di risorse che comprende i registri, un elenco di file aperti, gli allarmi in sospeso, un elenco di processi relativi e tutte le informazioni per far girare il programma. Quindi il **processo** è una sorta di contenitore che racchiude tutte le informazioni per far girare un programma.
Periodicamente il *SO* decide di fermare un processo e avviarne un altro, questo succede per esempio quando il processo stoppato ha consumato il suo tempo di CPU. Quando questo accade, il processo stoppato successivamente deve essere riavviato nel medesimo stato di quando è stato fermato. Questo significa che tutte le informazioni elaborate da quel processo fino a quel momento devono essere salvate da qualche parte durante la sospensione. 

>*Ex*: il processo potrebbe aver aperto più file per leggerli.

In tal caso ad ognuno di quei file c'è un puntatore associato che dà la posizione attuale. Quindi quando un processo viene temporaneamente sospeso, i puntatori devono essere salvati, così che dopo una chiamata di *read* eseguita dopo il riavvio leggerà i dati corretti.
In molti SO tutta l'informazione riguardante ciascun processo, diversa dai contenuti del suo spazio degli indirizzi, è salvata nella **tabella di processo** che è un array di strutture, una per ogni processo in essere.
Un processo sospeso quindi consiste del suo spazio degli indirizzi, chiamato **immagine core** e del suo elemento nella tabella di processo, insieme al contenuto dei registri e molti altri oggetti che servono per avviare il processo successivamente.
Le chiamate chiave del sistema di gestione del processo sono quelle che hanno a che fare con la creazione e chiusura dei processi.

>*Ex*: Consideriamo un processo chiamato **shell** che legge i comandi da terminale. 

L'utente ha appena digitato un comando che richiede la compilazione di un programma. Quando quel processo ha terminato la compilazione, esegue una chiamata di sistema per terminare se stesso.
Se un processo può creare uno o più processi e questi a loro volta possono creare altri processi, si arriva velocemnte ad una struttura ad albero. I processi relazionati che cooperano per realizzare un lavoro spesso necessitano di comunicazione tra loro e sincronizzare le attività.
Un processo ha a disposizione altre chiamate di sistema per richiedere più memoria, per farlo deve attendere che termini un processo figlio o sovrapporre i programmi. Esiste la possibilità di convogliare un'informazione a un processo in esecuzione senza che quest ultimo sia in attesa

>*Ex*: quando un processo sta comunicando con un altro su un diverso computer, inviandogli messaggi tramite rete. Per tutelarsi nell'eventualità che il messaggio si perda, il mittente può richiedere che il suo sistema operativo ne notifichi la ricezione dopo un certo numero di secondi.

Trascorso un certo numero di secondi, il *SO* invierà un segnale di allarme al processo. Il signale comporta che il processo sospenda temporaneamente qualunque operazione in corso, salvi i registri nello stack ed esegua una procedura di gestione dei segnali speciali.
Quando il gestore dei messaggi ha terminato il processo stoppato riprende l'esecuzione al momento del segnale. I segnali sono l'analogo degli *interrupt* hardware e possono essere generati da una varietà di cause, oltre che dalla scadenza dei timer. Molti trap come un'istruzione illegale o un indirizzo non valido sono convertiti in segnali per il processo responsabile.
A ogni persona autorizzata ad utilizzare il computer viene assegnato uno **UID** (user identification) dall'account root. Ogni processo che viene runnato ha l'UID dello user che lo ha runnato. Un processo figlio ha lo stesso UID del padre. Gli utenti possono appartenere a gruppi, ognuno dei quali ha lo stesso **GID** (group identification)

# Spazi degli indirizzi
Ogni computer ha una memoria principale che viene impiegata per il mantenimento dei programmi in esecuzione.

>*Ex*: In un SO semplice può stare in memoria al massimo un programma per volta. Per eseguire un secondo programma, il primo deve essere interrotto e il secondo messo in memoria

SO più avanzati consentono l'esecuzione in memoria di più programmi contemporaneamente. Per far si che questo avvenga e per evitare che più programmi interferiscano tra loro, c'è bisogno di un meccanismo di protezione risiedente nella parte hardware, ma che comunque verrà controllato dal SO.

Un problema distinto ma che comunque riguarda la memoria, è, la gestione dello spazio degli indirizzi nei processi.
Normalmente ogni processo dispone di uno spazio prefissato di indirizzi di memoria, generalemente da 0 a un certo massimo. Generalmente la quantità di spazio disposta dagli indirizzi è minore del totale della memoria principale.
Così facendo, anche se un processo riempie il proprio spazio di indirizzi, ci sarà ancora spazio per poter eseguire altri processi e non riempire la memoria.
Tuttavia gli indirizzi sono di 32 o 64 byte, ciò vuol dire che lo spazio degli indirizzi si aggira all'incirca o a $2^{32}$ o $2^{64}$ byte. 

>[!warning]- Più pazio degli indirizzi rispetto alla memoria
>Se un processo ha più spazio degli indirizzi rispetto alla memoria principale oggi non sarebbe un problema così grande, ma nei primi computer non avrebbe avuto chance.

 Oggi questo problema è affrontabile tramite una tecnica denominata **memoria virtuale** che consente al sistema operativo di tener parte dello spazio degli indirizzi nella memoria e parte sul disco, così che è possibile scambiare pezzi avanti e indietro secondo la necessità

>[!important]- Astrazione
>Essenzialmente il SO crea un'astrazione di uno spazio degli indirizzi come se fosse un insieme di indirizzi a cui un processo fa riferimento.

Lo spazio degli indirizzi è separato dalla memoria fisica della macchina e può essere sia più grande che più piccolo della memoria fisica.

# File
Un concetto chiave di ogni SO è il file system. Per creare, cancellare, leggere e scrivere file sono necessarie chiamate di sistema.

>*Ex*: Prima che un file possa essere letto, bisogna localizzarlo sul disco; dopo che è stato letto occorre chiuderlo, quindi le chiamate sono funzionali a questo scopo.

Per storare i file, molti SO considerano le **directory** come metodo per raggrupparli. Le voci di una directory possono essere file o altre directory. Questo modello dà luogo a una gerarchia chiamata **File System**. 

Le gerarchie del processo e dei file sono organizzate come alberi ma la loro affinità va oltre questo.
Le gerarchie del processo hanno generalmente vita breve, al massimo qualche minuto, mentre la gerarchia della directory è il contrario, la vita può esistere anche anni. Anche la proprietà e la protezione differiscono per processi e file, generalmente solo un processo padre può accedere al processo figlio, ma esistono meccanismi che ignorano questa gerarchia per permettere che file e directory siano letti da un gruppo più ampio del solo proprietario.

Ogni file nella gerarchia delle directory puo' essere specificato dando il nome del percorso (**path name**) a partire dall' inizio della gerarchia delle directory ovvero la directory principale (**root directory**). 

In ogni istante, ogni percorso ha la sua directory di lavoro corrente.
I processi possono cambiare la loro directory di lavoro inviando una chiamata di sistema che specifichi la nuova directory di lavoro.

Prima che un file possa essere letto o scritto, deve essere aperto per controllare i permessi. Se aperto allora il sistema restituisce un numero intero chiamato **descrittore del file** da usare nelle operazioni successive, se invece l'accesso e' proibito viene restituito concodice di errore.

Un altro importante concetto in UNIX e' il file system montato che permette a tutti i dispositivi USB esterni (CD-ROM, DVD, ecc...) di essere connessi direttamente all'albero principale fornendo una modalita' piu' elegante di gestione.

> *Ex*:  Poniamo il caso che si abbia una unita' CD-ROM esterna e si voglia leggere il suo contenuto al'interno.
> Per eseguire questa operazione in maniera semplice, UNIX dato che non permette nomi di percorso preceduti dal nome del dispositivo esterno o dal numero dei dispositivi, si ricorre al comando **mount** che permette al fyle system dell'unita' CD-ROM di essere collegato al file system della directory root

Se un sistema ha piu' dispositivi, questi possono essere montati tutti in un signolo albero senza nessun problema.

Un altro importante concetto in UNIX e' il **file speciale**. 
I file speciali sono pensati per far si che i dispositivi di I/O siano visti come file. Questo sistema permette che vengano gestiti dalle stesse chiamate di sistema usate per leggere e scrivere i file.

Esistono due tipi di file speciali:
- **File speciali a blocchi**: sono usati per modellare dispositivi costituiti da un insieme di blocchi indirizzabili casualmente, come i dischi.
- **File speciali a caratteri**: sono usati per modellare tutti quei dispositivi che accettano una sequenza di caratteri in output (Stampanti, Modem, ecc...). Convenzionalmente i file a caratteri sono contenuti nella cartella $/dev$

# Pipe
Una pipe e' vista come uno pseudofile che permette la connessione tra un processo A e un processo B

>*Ex*: Se due processi A e B vogliono comunicare tramite una pipe, devono prima configurarla.
>Se A vuole mandare dei file a B, scrive sulla pipe come se fosse un file di output. Infatti l'implementazione di una pipe e' molto simile a quella di un file. Il processo B puo' leggere i dati di una pipe come se fosse un file di input. 

Quindi in UNIX le comunicazioni tra processi sono molto simili alla normale scrittura e lettura di un file 

# Input/Output
Tutti i computer hanno la possibilita' di acquisire dispositivi di Input e produrre un Output. Di conseguenza ogni sistema operativo ha un sottosistema di I/O per gestire i dispositivi. Parte del software di I/O e' indipendente dai dispositivi, quindi si applica alla maggior parte dei dispositivi, invece i driver sono specifici per particolari dispositivi.

# Protezione
I file in UNIX sono protetti da un codice binario di 9 bit.
Esistono tre campi di 3 bit ognuno (proprietario, membri del gruppo a cui il proprietario appartiene, per tutti gli altri). Ogni campo ha un bit per l'accesso in scrittura, lettura ed esecuzione, conosciuti anche come **bit rwx**.

> *Ex*: $rwxr-x--x$: il proprietario puo' leggere, scrivere ed eseguire, il gruppo a cui appartiene il proprietario puo' leggere ed eseguire e tutti gli altri possono solo eseguire.

Per una directory $x$ significa permesso di ricerca.

# Shell
La shell e' l'interprete di comandi in UNIX. 
E' l'interfaccia principale fra un utente al terminale e il sistema operativo, assumendo che l'utente non stia utilizzando un'interfaccia grafica ovviamente.
Ne esistono molte $sh,\ csh,\ ksh,\ bash,\ zsh,\ ecc...$ ma la originale e' $sh$.
**Prompt**, carattere simile al dollaro che avvisa l'utente che la shell e' in attesa di input.

>*Ex*: dando $date$ come input in una shell, questa creera' un processo figlio che eseguir' $date$. Mentre il processo figlio e' in esecuzione, la shell rimane in attesa che esso termini. Una volta terminato la shell riscrive il prompt e legge la prossima riga in input.

L'utente puo' specificare che lo standard output sia diretto verso un file, 

>*Ex*: $date>file$ 

Stesso modo puo' essere ridiretto lo standard input, 

>*Ex*: $sort<file1 \ >file2$ 

Fa si che il $sort$ abbia come input $file1$ e che l'output sia mandato in $file2$ 

L'output di un programma puo' essere usato come input di un altro, connettendoli con una pipe (|),

>*Ex*: $cat \ file1 \ file2 \ file3 \ | \ sort>/dev/lp$ 

Lancio $cat$ per concatenare tre file e invio l'output $sort$ per ordinare le righe in ordine alfabetico. L'output di $sort$ e' inviato a $/dev/lv$ 

Se un utente inserisce il carattere & dopo il comando, la shell in genere non attende la fine dello stesso, restituisce immediatamente il prompt,

>*Ex*: $cat \ file1 \ file2 \ file3 \ | \ sort>/dev/lp$ $\&$ 

Inizia l'ordinamento come un lavoro in backgroud, permettendo all'utente di continuare a lavorare normalmente, mentre l'ordinamento sta procedendo.

# Chiamate di sistema
Le chiamate di sistema sono un meccanismo che il processo utilizza per richiedere un servizio a livello kernel del sistema operativo sul computer.
Il meccanismo delle chiamate è altamente specifico del sistema operativo e dell'hardware, si necessita di molta più efficienza.
**Soluzione**:
- Fare in modo che le chiamate di sistema siano integrate nelle librerie C (libc)
Tipicamente si esporta una chiamata di libreria per ogni chiamata di sistema.
UNIX libc si basa sulla libreria C POSIX

## 10 passi per effettuare la chiamata [read(fd, buffer, nbytes)]
- **Preparazione dei parametri**: il programma chiamante prepara i parametri, solitamente vengono memorizzati nei registri o nello stack (RDI, RSI, RDX)
- **Chiamata alla procedura di libreria**: il programma effettua la chiamata alla procedura di libreria
- **Collocazione del numero di chiamata**: colloca il numero della chiamata di sistema in un registro, come il RAX
- **Passaggio a modalità kernel**: si esegue un'istruzione "*trap*"
	- Si passa da mod utente a mod kernel
		- L'istruzione "*trap*" è simile ad una chiamata di sistema, ma cambia la modalità da utente a kernel
		- Può saltare solo a indirizzi specifici o indici di una tabella di memoria, a differenza di una chiamata di sistema
- **Esecuzione del gestore di chiamate di sistema**
- **Ritorno alla procedura di libreria utente**:
	- Dopo, il controllo può essere restituito alla procedura di libreria utente, dopo l'istruzione "*trap*"
- **Possibilità di blocco**: La chiamata di sistema può bloccare il chiamante, ad esempio, se l'input desiderato non è disponibile
	- Il sistema può quindi eseguire altri processi
- **Ripresa dopo il blocco**: Quando l'input o le condizioni desiderate sono disponibili, il processo bloccato viene ripreso tornando alla procedura di libreria utente e procedendo all'istruzione successiva

## Chiamate di sistema per la gestione dei processi

**Alcune delle principali chiamate di sistema**

| Call                                           | Description                                                         |
| ---------------------------------------------- | ------------------------------------------------------------------- |
| $pid \ fork()$                                 | Crea processo simile al genitore                                    |
| $pid \ waitpid(pid, \ \&statloc, \ options)$   | Attendere che processo filgio termini                               |
| $s \ = \ execve(name, \ argv, \ environp)$     | Sostituire l'immagine centrale di un processo                       |
| $exit(status)$                                 | Termina processo e restituisce lo stato                             |
| $fd \ = \ open(file, \ how, \ ...)$            | Apre un file per la lettura, la scrittura o entrambe le operazioni. |
| $s \ = \ close(fd)$                            | Chiude un file aperto                                               |
| $n \ = \ read(fd, \ buffer, \ nbytes)$         | Legge dati da un file in un buffer                                  |
| $n \ = \ write(fd, \ buffer, \ nbytes)$        | Scrive dati da un buffer in un file                                 |
| $Position \ = \ lseek(fd, \ offset, \ whence)$ | Sposta il puntatore del file                                        |
| $s \ = \ stat(name, \ \&buf)$                  | Ottiene informazioni sullo sttao di un file                                                                     |

- **Pid**: ProcessID
- **fd**: Descrittore del file
- **n**: Conteggio di byte
- **position**: È un offset all'interno del file

## Chiamate di sistema per la gestione del file system

| Call                                     | Description                                    |
| ---------------------------------------- | ---------------------------------------------- |
| $s \ = \ mkdir(name, \ mode)$            | Crea dir                                       |
| $s \ = \ rmdir(name)$                    | rm dir empty                                   |
| $s \ = \ link(name1, \ name2)$           | Crea una nuova voce, nome2, che punta a nome 1 |
| $s \ = \ unlink(name)$                   | rm voce dir                                    |
| $s \ = \ mount(spacial, \ name, \ flag)$ | Monta file system                              |
| $s \ = \ mount(spacial, \ name, \ flag)$ | Smonta file system                                                |

## Altre chiamate

| Call                          | Description                |
| ----------------------------- | -------------------------- |
| $s \ = \ chdir(dirname)$      | Cambia dir lavoro          |
| $s \ = \ chmod(name, \ mode)$ | Modifica bit di protezione |
| $s \ = \ kill(pid, \ signal)$ | Invia segnale a processo   |
| $s \ = \ time(\$seconds)$     | Ottieni il tempo dal 1 Gennaio                           |


```C
#define TRUE 1
while(TRUE){
	type_prompt();
	read_command(command, parameters);
	if(fork() !=0){
		/*ParentCode*/
		waitpid(-1, &status, 0);
	}
	else{
		/*ChildCode*/
		execve(command, parameters, 0);
	}
}
```
<<<<<<< HEAD

# Struttura di un SO
## Monolitico
---SCREENSHOT Monolitico---

Il sistema monolitico e' l'organizzazione piu' comune: in questo approccio l'intero SO viene eseguito come programma singolo e in modalita' kernel.
Il SO e' scritto come una raccolta di procedure, collegate all'interno di un solo programma binario eseguibile. Quando viene usata questa tecnica, ogni procedura del sistema e' libera di richiamarne un'altra se fornisce delle info utili e necessarie.
Con questo metodo, per costruire un SO, prima si compilano tutte le procedure individuali per poi fonderle tutte insieme in un file unico eseguibile, usando il linker di sistema
All'interno dei sistemi monolitici pero' e' possibile trovare qualche struttura.
I servizi forniti dal sistema operativo sono richiesti mettendo i parametri in un luogo definito per poi eseguire una $Trap$.
Questa istruzione fa passare il SO da modalita' utente a modalita' kernel . Successivamente il SO va a prendere i parametri e determina quale chiamata di sistema va realizzata, dopo indicizza tutto in una tabella che ha nella posizione $k$ un puntatore alla procedura che svolge la chiamata di sistema $k$.
Questa e' la struttura base:
- Programma principale che richiama la procedura di servizio richiesta
- Insieme di procedure di servizio che realizzano chiamate di sistema
- Insieme di procedure di supporto che aiutano le procedure di servizio
=======
>>>>>>> a574f5b (SOR1.5)

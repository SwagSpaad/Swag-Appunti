
Un file system è un componente del sistema operativo che gestisce e organizza i dati su un dispositivo di archiviazione, come un disco rigido, una chiavetta USB o un'unità SSD. Fornisce una struttura per memorizzare, accedere e gestire i file e le directory, consentendo agli utenti e ai programmi di trovare e utilizzare i dati in modo efficiente. I file system più comuni includono NTFS, FAT32, ext4 e APFS.

# File
I file sono meccanismi di astrazione che permettono di salvare e leggere informazioni sul disco, nascondendo i dettagli tecnici all'utente. I file sono identificati da nomi, che possono variare per lunghezza e sensibilità alle maiuscole a seconda del sistema operativo (ad esempio, UNIX distingue tra maiuscole e minuscole, mentre MS-DOS no). Ogni file è solitamente identificato anche da un'estensione, che indica una caratteristica specifica del file (come .jpg per immagini o .c per codice sorgente in C). In alcuni sistemi come UNIX, le estensioni sono convenzionali, mentre in altri come Windows hanno significati specifici e sono associate a programmi particolari. Gli utenti possono registrare le estensioni nel sistema operativo e specificare i programmi associati. Ad esempio, un doppio clic su un file .docx avvia Microsoft Word con quel file, mentre Photoshop non aprirà file con estensione .docx.

## Struttura dei File
I file possono essere strutturati in modi diversi, a seconda delle esigenze del sistema operativo e delle applicazioni:2
- **Sequenza non strutturata di byte:** I file sono visti dal sistema operativo come una serie non strutturata di byte. Il significato dei dati è determinato dai programmi a livello utente, non dal sistema operativo. Questo approccio, adottato da sistemi come UNIX, Linux, macOS e Windows, offre massima flessibilità.
- **Sequenza di Record di lunghezza fissa:** Un file è una sequenza di record con struttura interna definita e lunghezza fissa, basato storicamente sul modello delle schede perforate a 80 colonne nei mainframe. Le operazioni di lettura e scrittura avvengono a unità di record. Questo modello, prevalente nei mainframe del passato, è meno comune nei sistemi moderni.
- **File come albero di Record:** I file possono essere organizzati come alberi di record con lunghezze variabili e un campo chiave in posizione fissa, consentendo ricerche rapide basate su chiavi specifiche. Questo tipo di organizzazione è utilizzato principalmente nei mainframe per elaborazioni commerciali, come nei DBMS, ed è diverso dalle sequenze non strutturate di UNIX e Windows. Molti sistemi operativi supportano diversi tipi di file.

## Tipi di File
- **File e Directory Normali:** Utilizzati in sistemi come UNIX e Windows. I file normali contengono informazioni dell'utente e sono i più comuni. Le directory sono file di sistema che mantengono la struttura del file system.
- **File Speciali:**
  - A caratteri: Usati per modellare porte seriali di I/O, come terminali e stampanti.
  - A blocchi: Usati per modellare dischi.
- **File Normali:**
  - File ASCII: Composti da righe di testo, visualizzabili e stampabili, con variazioni nella terminazione delle righe.
  - File Binari: Non leggibili come testo, con una struttura interna conosciuta dai programmi che li utilizzano, come file eseguibili o archivi.
- **File Eseguibile - Componenti:**
  - Intestazione (Header): Contiene un 'numero magico' per identificare il file come eseguibile, dimensioni delle parti del file, indirizzo di esecuzione iniziale (punto d'ingresso) e vari flag.
  - Testo e Dati: Parti effettive del programma, da caricare e rilocare in memoria.
  - Tabella dei simboli: Utilizzata per il debug.
- **File di Archivio:**
  - Descrizione: Raccolta di procedure di libreria (moduli) compilate ma non collegate.
  - Intestazione dei Moduli: Indicano nome, data di creazione, codice di protezione e dimensione.
  - Carattere Binario: Stampare questi file produrrebbe caratteri incomprensibili.

## Metodi di Accesso ai File
- **Accesso Sequenziale:** Nei primi sistemi operativi, il processo poteva leggere tutti i byte o i record in ordine, a partire dall'inizio, senza poter saltare o leggere in ordine sparso. Tuttavia, i file potevano essere riavvolti per ulteriori letture.

- **Accesso Causale:** Introdotto con l'avvento dei dischi, permette la lettura di byte o record in qualsiasi ordine. Cruciale per applicazioni come i database, dove è necessario accedere rapidamente a record specifici. Ci sono due metodi per specificare la posizione di lettura:
  - Ogni operazione di lettura fornisce la posizione iniziale.
  - L'operazione speciale seek imposta la posizione corrente, da cui il file può essere letto sequenzialmente. Questo metodo è usato in UNIX e Windows.

## Attributi dei File
Ogni file ha un nome e i propri dati, oltre a ulteriori informazioni chiamate attributi (metadati), come la data e l'ora dell'ultima modifica e la dimensione. Gli attributi sono cruciali per:
- La protezione e il controllo dell'accesso.
- La gestione efficace dei file nei sistemi operativi. L'elenco degli attributi varia considerevolmente tra i diversi sistemi operativi.

# Directory
Per tener traccia dei file, i file system normalmente usano le directory (cartelle), che sono anch'esse dei file.

## Sistemi di Directory a Livello Singolo
La forma più semplice di sistema di directory consiste in una sola directory contenente tutti i file, chiamata directory principale (root directory). Il vantaggio di questo schema è la semplicità e la rapidità nella localizzazione dei file, dato che c'è un solo posto in cui cercare. Questo tipo di organizzazione è ancora utilizzato nei dispositivi embedded, come fotocamere digitali, lettori MP3 e tecnologie RFID, come carte di credito e tessere di trasporto.

## Sistemi a Directory Gerarchici
I sistemi a directory gerarchici sono organizzati in una struttura ad albero di directory ramificate. Ogni utente può avere una directory principale privata nella quale può creare un numero arbitrario di sottodirectory per organizzare i propri file e progetti. Questo metodo è ampiamente utilizzato nei network aziendali e in molti file system moderni per fornire agli utenti uno strumento potente di strutturazione del lavoro.

## Specificazione dei Nomi dei File
Quando un file system è organizzato secondo un albero di directory, i nomi dei file possono essere specificati mediante due metodi comuni:
1. **Percorsi Assoluti:** Specificano il percorso completo del file dalla radice del file system fino al file desiderato. Ad esempio, `/home/utente/progetto/file.txt.`
2. **Percorsi Relativi:** Specificano il percorso del file in relazione alla directory corrente. Ad esempio, se ci si trova nella directory `/home/utente`, il percorso relativo per accedere a `file.txt`in progetto sarebbe `progetto/file.txt`. Questi due metodi consentono agli utenti di navigare e accedere ai file in modo flessibile e efficace all'interno della struttura gerarchica delle directory.

## Percorso Assoluto
Il percorso assoluto assegna a ogni file un nome che inizia dalla directory principale e prosegue fino al file specificato. Ad esempio, il percorso `/usr/ast/mailbox` indica che dalla directory principale del file system esiste una sottodirectory `/usr`, che contiene a sua volta una sottodirectory `/ast`, all'interno della quale si trova il file `mailbox`. I nomi di percorso assoluto sono univoci e permettono di identificare esattamente la posizione di un file nella struttura gerarchica delle directory del sistema.

## Percorso Relativo
Il metodo del nome di percorso relativo è utilizzato insieme al concetto di directory di lavoro, anche chiamata directory corrente. Quando un utente designa una directory come directory di lavoro, tutti i nomi di percorso che non iniziano con la directory principale sono interpretati come relativi alla directory di lavoro. Se l'utente si trova già nella directory `/usr/hjb`, ad esempio, i due comandi seguenti sono equivalenti:
- `/usr/hjb/progetto/file.txt` (percorso assoluto)
- `progetto/file.txt` (percorso relativo)
In UNIX, i componenti del percorso sono separati dal carattere `/`, mentre in Windows è utilizzato il carattere `\`. 
Nel sistema MULTICS, il separatore era `>`. 
Molti sistemi operativi che supportano un sistema di directory gerarchico includono due voci speciali in ogni directory:
- . (punto): Rappresenta la directory corrente.
- .. (puntopunto): Rappresenta la directory genitore (la directory precedente). Anche nella directory radice, .. fa riferimento alla stessa directory radice. Queste convenzioni facilitano la navigazione e la gestione dei percorsi all'interno della struttura gerarchica delle directory del sistema operativo.
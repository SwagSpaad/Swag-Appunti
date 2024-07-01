# File System

I file system sono un modo per organizzare e memorizzare in modo persistente le informazioni. Offrono un'astrazione sui dispositivi di memorizzazione (Disco rigido, SSD, rete, RAM). Le informazioni sono organizzate in file e directory. Esempi di file system includono FAT12/FAT16 per MS-DOS, NTFS per Windows, Ext4 per Linux, APFS per macOS/iOS.

## File

I file sono un meccanismo di astrazione che consente di salvare e leggere informazioni sul disco, nascondendo i dettagli di come e dove sono memorizzate. I file vengono identificati tramite nomi, che variano in base al sistema operativo. Alcuni sistemi limitano la lunghezza dei nomi (MS-DOS), mentre altri supportano nomi più lunghi e distinguono tra maiuscole e minuscole (UNIX).

### Struttura dei File

I file possono essere strutturati in vari modi:
- **Sequenza Non Strutturata di Byte**: I file sono visti come una serie non strutturata di byte. Utilizzato da UNIX, Linux, macOS e Windows.
- **Sequenza di Record di Lunghezza Fissa**: Un file è una sequenza di record con lunghezza fissa. Meno comune nei sistemi moderni.
- **File come Albero di Record**: Il file è organizzato come un albero di record, con lunghezze variabili e un campo chiave fisso. Utilizzato in sistemi mainframe.

### Tipi di File

Molti sistemi operativi supportano diversi tipi di file:
- **File e Directory Normali**: Contengono informazioni utente e mantengono la struttura del file system.
- **File Speciali**: File a caratteri (porte seriale di I/O) e file a blocchi (dischi).
- **File ASCII e Binari**: I file ASCII contengono testo leggibile, mentre i file binari hanno una struttura interna nota ai programmi che li utilizzano.

### File Eseguibili

- **Intestazione (Header)**: Identifica il file come eseguibile, dimensioni, punto d'ingresso e vari flag.
- **Testo e Dati**: Parti effettive del programma.
- **Tabella dei Simboli**: Utilizzata per il debug.

### File di Archivio

- **Descrizione**: Raccolta di procedure di libreria compilate ma non collegate.
- **Intestazione dei Moduli**: Indicano nome, data di creazione, codice di protezione e dimensione.
- **Carattere Binario**: Non leggibili come testo.

## Accesso ai File

I metodi di accesso ai file includono:
- **Accesso Sequenziale**: Lettura di byte o record in ordine.
- **Accesso Causale**: Lettura di byte o record in qualsiasi ordine. Utilizza l'operazione `seek` per impostare la posizione corrente.

## Operazioni sui File

Le principali operazioni sui file sono:
1. **Create**: Creazione di un file senza dati.
2. **Delete**: Eliminazione di un file per liberare spazio sul disco.
3. **Open**: Apertura di un file per caricare attributi e indirizzi del disco.
4. **Close**: Chiusura del file per liberare spazio nelle tabelle interne.
5. **Read**: Lettura dei dati da un file.
6. **Write**: Scrittura di dati nel file.
7. **Append**: Aggiunta di dati alla fine del file.
8. **Seek**: Riposizionamento del puntatore del file.
9. **GetAttributes**: Lettura degli attributi del file.
10. **SetAttributes**: Modifica degli attributi del file.
11. **Rename**: Ridenominazione di un file.

# Directory

Per tenere traccia dei file, i file system normalmente utilizzano directory o cartelle, che sono anch'esse dei file.

## Sistemi di Directory a Livello Singolo

La forma più semplice di sistema di directory è una sola directory contenente tutti i file, chiamata directory principale (root directory). Questo schema è semplice e permette di localizzare i file rapidamente. Viene spesso usato nei dispositivi embedded come fotocamere digitali o MP3.

## Sistemi di Directory Gerarchici

Un sistema di directory gerarchico è organizzato come un albero di directory. In un ambiente condiviso, ogni utente può avere una directory principale privata per la propria gerarchia. Questo approccio permette agli utenti di creare un numero arbitrario di sottodirectory per organizzare il proprio lavoro. I file system moderni adottano questo schema.

### Percorso Assoluto

Ogni file ha un nome di percorso assoluto che inizia dalla directory principale. Ad esempio, `/usr/ast/mailbox` indica che il file `mailbox` si trova nella directory `ast`, che è una sottodirectory di `usr`, a sua volta sottodirectory della root.

### Percorso Relativo

Il percorso relativo è usato congiuntamente al concetto di directory di lavoro (o directory corrente). Se un utente è nella directory `/usr/hjb`, il comando `cp mailbox mailbox.bak` è equivalente a `cp /usr/hjb/mailbox /usr/hjb/mailbox.bak`.

### Nomi Speciali

Nei sistemi che supportano directory gerarchiche, ogni directory contiene due voci speciali: 
- `.` (dot): Si riferisce alla directory corrente.
- `..` (dotdot): Si riferisce alla directory genitore, tranne nella directory radice.

## Operazioni sulle Directory

Le operazioni principali sulle directory sono:

1. **create**: Creazione di una directory vuota con le voci `.` e `..`.
2. **delete**: Eliminazione di una directory vuota.
3. **opendir**: Apertura di una directory per leggere il suo contenuto.
4. **closedir**: Chiusura di una directory dopo la lettura per liberare risorse.
5. **readdir**: Restituisce la prossima voce in una directory aperta senza esporre la struttura interna.
6. **rename**: Rinomina una cartella, simile al rinomino di un file.
7. **link**: Crea un hard link, collegando un file esistente a un nuovo percorso condividendo l’i-node.
8. **unlink**: Rimuove una voce di una directory, cancellando il file se è l’unico link.

## Link Simbolici

Un link simbolico (o alias) è un nome che punta a un piccolo file che rappresenta un altro file. I link simbolici possono attraversare i confini dei dischi e collegare file su computer remoti, ma possono essere meno efficienti degli hard link.

# Implementazione del File System

## Layout del File System

Il file system organizza e memorizza dati sui dispositivi di memoria non volatile. Fornisce una struttura per gestire file e directory su dispositivi di memoria. Un disco può essere suddiviso in più partizioni, ciascuna con un proprio file system indipendente.

### MBR (Master Boot Record)

Il settore 0 del disco, chiamato MBR, è essenziale per l'avvio del computer. Contiene la tabella delle partizioni e identifica la partizione attiva da cui avviare il sistema. Quando il computer si avvia, il BIOS legge ed esegue l'MBR, che localizza la partizione attiva e ne legge il primo blocco, chiamato blocco di boot.

### Layout Generale del File System

Il layout di un file system varia, ma generalmente include:

- **Superblocco**: Contiene tutti i parametri chiave riguardanti il file system, come un numero magico per identificarlo e altre informazioni chiave.
- **Bitmap o Linked List**: Utilizzati per la gestione dello spazio libero.
- **I-node**: Un array di strutture dati, una per file, che contiene tutte le informazioni sui file.
- **Directory Radice**: Contiene la cima dell'albero del file system.

### UEFI (Unified Extensible Firmware Interface)

UEFI è una modernizzazione dell'MBR, veloce e supporta dischi di dimensione fino a 8 ZiB. Utilizza la GPT (GUID Partition Table), che contiene informazioni sulla posizione delle partizioni sul disco e ne conserva un backup nell'ultimo blocco. UEFI permette infinite partizioni e supporta diverse architetture.

## Implementazione dei File

Una buona implementazione dei file assicura integrità, accesso efficiente e gestione dello spazio sul disco.

### Allocazione Continua

Memorizza ciascun file come una sequenza contigua di blocchi sul disco.

- **Vantaggi**: Semplice da implementare, prestazioni di lettura eccellenti.
- **Svantaggi**: Frammentazione del disco, difficoltà nell'aggiungere nuovi file su un disco frammentato, necessità di compattare il disco.

### Allocazione a Liste Concatenate

Memorizza i file come una lista concatenata di blocchi sul disco. La prima parte di ciascun blocco è usata come puntatore al successivo.

- **Vantaggi**: Utilizza efficientemente tutti i blocchi del disco, minimizza la frammentazione esterna.
- **Svantaggi**: Accesso casuale estremamente lento, dimensioni dei blocchi non più una potenza di due.

### Allocazione a Liste Concatenate con FAT

Migliora le liste concatenate spostando i puntatori in una tabella di memoria FAT (File Allocation Table). Ogni blocco del disco è rappresentato come una voce nella FAT in memoria RAM.

- **Vantaggi**: Accesso casuale semplificato.
- **Svantaggi**: La tabella deve rimanere sempre in memoria, occupa molta RAM.

### I-node

Gli i-node sono strutture dati che elencano gli attributi di file e directory come permessi, proprietario, timestamp e indirizzi dei blocchi dati. Ogni file e directory è rappresentato da un i-node univoco.

- **Vantaggi**: Solo gli i-node dei file aperti sono mantenuti in memoria, riducendo l'uso della memoria. L'array degli i-node in memoria è proporzionale al numero di file aperti, non alla dimensione del disco.

Gli i-node sono fondamentali in UNIX e nei suoi derivati. NTFS di Windows utilizza una struttura simile con i-node più grandi che possono contenere file di piccole dimensioni all'interno dell'i-node stesso.

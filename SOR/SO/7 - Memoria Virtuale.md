La memoria virtuale è una tecnica di gestione della memoria che consente a un computer di compensare la carenza di memoria fisica utilizzando lo spazio su disco. Essa fornisce un'illusione di un grande spazio di memoria contiguo, indipendentemente dalla quantità di memoria fisica realmente disponibile. Di seguito viene spiegata nel dettaglio la memoria virtuale e la sua struttura:

## Funzionamento della Memoria Virtuale

1. **Indirizzamento Virtuale**: Ogni processo ha il proprio spazio di indirizzamento virtuale separato dagli altri processi. Questo spazio di indirizzamento virtuale è diviso in pagine.
2. **Mapping**: Gli indirizzi virtuali vengono mappati agli indirizzi fisici tramite una tabella delle pagine (page table). Questa tabella contiene le informazioni sulla posizione delle pagine virtuali nella memoria fisica.
3. **Page Faults**: Quando un processo accede a una pagina che non è attualmente in memoria fisica si verifica un page fault e il sistema operativo deve caricare quella pagina dalla memoria di massa (come un disco rigido) nella memoria fisica.

## Struttura della Memoria Virtuale

1. **Spazio di Indirizzamento Virtuale**:
   - Diviso in pagine di dimensione fissa (ad esempio, 4 KB per pagina).
   - Ogni processo ha il proprio spazio di indirizzamento virtuale, che è isolato dagli spazi di indirizzamento degli altri processi, migliorando così la sicurezza e la stabilità.
   
2. **Page Table**:
   - **Tabella delle Pagine**: Ogni processo ha una tabella delle pagine che mappa gli indirizzi virtuali agli indirizzi fisici. Ogni voce nella tabella delle pagine corrisponde a una pagina virtuale e contiene:
     - **Bit di Presenza/Assenza (Present/Absent bit)**: Indica se la pagina è attualmente in memoria fisica.
     - **Indirizzo di Frame**: Indirizzo del frame di memoria fisica in cui è memorizzata la pagina.
     - **Bit di Protezione**: Specifica le permessioni di accesso (lettura, scrittura, esecuzione).
     - **Bit di Riferimento (Referenced bit)**: Utilizzato per gli algoritmi di sostituzione delle pagine.
     - **Bit Modificato (Modified bit)**: Indica se la pagina è stata modificata (utilizzato per sapere se è necessario scrivere la pagina modificata su disco).
   
3. **Memory Management Unit (MMU)**:
   - Un componente hardware che traduce gli indirizzi virtuali in indirizzi fisici utilizzando la tabella delle pagine.
   - Include il Translation Lookaside Buffer (TLB), una cache che memorizza le traduzioni recenti degli indirizzi virtuali per velocizzare il processo di traduzione.

## Processi di Gestione della Memoria Virtuale

1. **Caricamento e Swapping**:
   - Le pagine possono essere caricate in memoria su richiesta (demand paging).
   - Quando la memoria fisica è piena, il sistema operativo può spostare (swap out) alcune pagine meno utilizzate su disco per fare spazio alle nuove pagine richieste (swap in).
   
2. **Algoritmi di Sostituzione delle Pagine**:
   - **FIFO (First In, First Out)**: Le pagine vengono sostituite in base al loro tempo di caricamento.
   - **LRU (Least Recently Used)**: Vengono sostituite le pagine che non sono state utilizzate di recente.
   - **Clock**: Un miglioramento di LRU che utilizza un puntatore circolare e bit di riferimento per determinare le pagine da sostituire.
   - **WSClock**: Un'ulteriore ottimizzazione che integra le informazioni del working set.

## Vantaggi della Memoria Virtuale

- **Isolamento della Memoria**: I processi sono isolati l'uno dall'altro, aumentando la sicurezza e la stabilità del sistema.
- **Utilizzo Efficiente della Memoria**: Permette l'uso di spazi di indirizzamento più grandi della memoria fisica disponibile.
- **Gestione Semplice della Memoria**: Facilita la gestione della memoria condivisa e consente di eseguire applicazioni più grandi della memoria fisica disponibile.

## Svantaggi della Memoria Virtuale

- **Overhead di Page Fault**: Il caricamento delle pagine dal disco può essere molto lento rispetto alla velocità della memoria fisica.
- **Complexità del Sistema**: Richiede un'implementazione complessa sia a livello hardware (MMU) che software (gestione del sistema operativo).

In sintesi, la memoria virtuale è una tecnica fondamentale che permette una gestione efficiente ed efficace della memoria, isolando i processi e consentendo l'esecuzione di programmi più grandi della memoria fisica disponibile.

# Translation Lookaside Buffer (TLB)

## Funzione della TLB

La TLB è una cache specializzata utilizzata dalla Memory Management Unit (MMU) per migliorare l'efficienza della traduzione degli indirizzi virtuali in indirizzi fisici. Senza la TLB, ogni accesso alla memoria richiederebbe una ricerca nella tabella delle pagine, che può essere un'operazione costosa in termini di tempo. La TLB memorizza un numero limitato di traduzioni recenti, riducendo significativamente il tempo di accesso alla memoria per quegli indirizzi che si trovano nella cache.

## Struttura della TLB

### 1. Entries (Voci)

La TLB è composta da una serie di voci (entries), ognuna delle quali contiene le seguenti informazioni:

- **Indirizzo Virtuale**: L'indirizzo virtuale o il numero di pagina virtuale che è stato recentemente tradotto.
- **Indirizzo Fisico**: L'indirizzo fisico o il numero di frame corrispondente al numero di pagina virtuale.
- **Bit di Validità (Valid bit)**: Indica se l'entry è valida o meno.
- **Bit di Protezione (Protection bits)**: Specifica i permessi di accesso (ad esempio, lettura, scrittura, esecuzione).

### 2. Dimensione della TLB

La TLB è tipicamente piccola rispetto alla tabella delle pagine, contenendo solo un numero limitato di voci (ad esempio, tra 32 e 512 voci). Tuttavia, poiché la TLB è implementata in hardware, le sue operazioni sono estremamente veloci.

### 3. Associazioni

Le TLB possono essere organizzate in vari modi, a seconda della loro associazione:

- **TLB Diretta**: Ogni indirizzo virtuale può essere mappato a una singola voce nella TLB. Questa organizzazione è semplice ma può portare a un alto tasso di miss se molte pagine virtuali competono per lo stesso slot.
- **TLB Associativa**: Qualsiasi indirizzo virtuale può essere mappato a qualsiasi voce nella TLB. Questa organizzazione è più flessibile e riduce i miss, ma è più complessa e costosa da implementare.
- **TLB Set-Associativa**: Una via di mezzo tra la TLB diretta e quella completamente associativa. Gli indirizzi virtuali possono essere mappati a un piccolo set di voci nella TLB. Questa organizzazione offre un buon compromesso tra complessità e prestazioni.

### 4. Operazioni della TLB

- **Lookup**: Quando un processo accede a un indirizzo virtuale, la MMU cerca nella TLB per vedere se c'è una voce corrispondente. Se trova una corrispondenza (TLB hit), l'indirizzo fisico viene immediatamente utilizzato per accedere alla memoria. Se non trova una corrispondenza (TLB miss), la MMU deve cercare la traduzione nella tabella delle pagine, aggiungere la nuova traduzione alla TLB (eventualmente sostituendo una vecchia voce) e poi procedere con l'accesso alla memoria.
- **Replacement**: Quando si verifica un TLB miss e la TLB è piena, una delle voci esistenti deve essere sostituita. Gli algoritmi di rimpiazzo comuni includono:
  - **FIFO (First In, First Out)**: La voce più vecchia viene sostituita.
  - **LRU (Least Recently Used)**: La voce meno recentemente utilizzata viene sostituita.
  - **Random**: Una voce viene sostituita a caso.

### 5. Gestione del Contesto

Ogni processo ha il proprio spazio di indirizzamento virtuale, quindi quando il sistema operativo passa da un processo all'altro (context switch), la TLB deve essere aggiornata. Questo può essere fatto in due modi:

- **TLB Flush**: La TLB viene completamente svuotata (flushed) ad ogni cambio di contesto. Questo è semplice da implementare ma può causare un alto tasso di miss subito dopo il cambio di contesto.
- **Context Identifier**: Ogni voce nella TLB è associata a un identificatore di contesto (ad esempio, un ID del processo). In questo modo, le voci possono rimanere nella TLB anche durante i cambi di contesto, riducendo il numero di miss.

## Vantaggi della TLB

- **Velocità**: La TLB riduce il tempo necessario per la traduzione degli indirizzi virtuali in indirizzi fisici, migliorando le prestazioni complessive del sistema.
- **Efficienza**: Riducendo il numero di accessi alla tabella delle pagine, la TLB diminuisce il carico sulla memoria e aumenta l'efficienza.

## Svantaggi della TLB

- **Complessità**: Implementare e gestire la TLB richiede un hardware specializzato e complesso.
- **Dimensione Limitata**: La TLB può contenere solo un numero limitato di voci, quindi non tutte le traduzioni possono essere memorizzate contemporaneamente.

In sintesi, la TLB è una componente essenziale per l'efficienza dei sistemi di memoria virtuale, fornendo una cache rapida per le traduzioni degli indirizzi e migliorando notevolmente le prestazioni del sistema.

# Allocazione di Memoria Globale vs. Allocazione Locale di Memoria

## Allocazione di Memoria Globale

### Definizione

L'allocazione di memoria globale si riferisce a una politica di gestione della memoria in cui le risorse di memoria sono gestite a livello globale per tutti i processi in esecuzione nel sistema. In questa politica, la memoria può essere allocata a qualsiasi processo in base alle esigenze e alla disponibilità complessiva di memoria.

### Caratteristiche

- **Condivisione della Memoria**: La memoria è gestita come una risorsa condivisa tra tutti i processi, senza riservare una quantità fissa di memoria per ogni processo.
- **Flessibilità**: Offre flessibilità nella gestione della memoria, permettendo al sistema operativo di allocare memoria dove è più necessario in base alla situazione corrente.
- **Prestazioni**: Può ottimizzare l'utilizzo della memoria totale del sistema, ma può portare a problemi di contesa delle risorse.
- **Page Replacement**: Gli algoritmi di sostituzione delle pagine (come LRU o FIFO) considerano l'intera memoria disponibile piuttosto che limitarsi a una porzione specifica per ogni processo.

### Vantaggi

- **Utilizzo Efficiente della Memoria**: Consente un uso più efficiente della memoria totale, poiché la memoria inutilizzata da un processo può essere allocata ad altri processi.
- **Adattabilità**: Adatta dinamicamente la memoria disponibile alle esigenze dei processi in esecuzione.

### Svantaggi

- **Contesa delle Risorse**: Può causare contesa della memoria tra processi, con alcuni processi che monopolizzano più memoria di altri.
- **Complessità di Gestione**: Richiede algoritmi più complessi per gestire l'allocazione e la sostituzione delle pagine a livello globale.

## Allocazione Locale di Memoria

### Definizione

L'allocazione di memoria locale si riferisce a una politica di gestione della memoria in cui una porzione fissa di memoria è riservata per ciascun processo. Ogni processo ha il proprio pool di memoria da cui può allocare risorse, e non può utilizzare la memoria riservata per altri processi.

### Caratteristiche

- **Isolamento della Memoria**: Ogni processo gestisce la propria porzione di memoria, isolata dalle altre porzioni assegnate ad altri processi.
- **Limitazioni Fisse**: Ogni processo ha una quantità fissa di memoria disponibile, indipendentemente dalle esigenze degli altri processi.
- **Prestazioni**: Previene la contesa delle risorse di memoria, ma può portare a un utilizzo inefficiente della memoria totale se alcuni processi non utilizzano tutta la memoria loro assegnata.
- **Page Replacement**: Gli algoritmi di sostituzione delle pagine considerano solo la porzione di memoria riservata al processo corrente.

### Vantaggi

- **Isolamento e Sicurezza**: Fornisce isolamento tra i processi, migliorando la sicurezza e la stabilità, poiché un processo non può influenzare direttamente la memoria di un altro processo.
- **Semplicità di Gestione**: Facilita la gestione della memoria, poiché ogni processo gestisce solo la propria memoria.

### Svantaggi

- **Utilizzo Inefficiente della Memoria**: Può portare a un utilizzo inefficiente della memoria totale se alcuni processi non utilizzano tutta la memoria loro assegnata mentre altri processi necessitano di più memoria.
- **Rigidità**: Meno flessibile nell'adattarsi alle esigenze dinamiche dei processi, poiché la memoria non utilizzata non può essere facilmente riassegnata.

## Confronto Riassuntivo

| Caratteristica                | Allocazione Globale                          | Allocazione Locale                                |
| ----------------------------- | -------------------------------------------- | ------------------------------------------------- |
| **Gestione della Memoria**    | Condivisa tra tutti i processi               | Riservata per ciascun processo                    |
| **Flessibilità**              | Alta, adatta dinamicamente le risorse        | Bassa, con limiti fissi per ogni processo         |
| **Contesa delle Risorse**     | Potenziale contesa tra processi              | Minima, poiché i processi sono isolati            |
| **Utilizzo della Memoria**    | Potenzialmente più efficiente                | Potenzialmente meno efficiente                    |
| **Isolamento**                | Minore, con maggiore rischio di interferenza | Maggiore, con maggiore sicurezza e stabilità      |
| **Algoritmi di Sostituzione** | Considerano la memoria globale               | Considerano solo la memoria del processo corrente |

In sintesi, l'allocazione di memoria globale offre una maggiore flessibilità e potenziale efficienza nell'uso della memoria complessiva del sistema, ma può portare a contese di risorse. L'allocazione locale di memoria offre un maggiore isolamento e semplicità di gestione, ma può risultare in un utilizzo inefficiente della memoria totale disponibile.

# Segmentazione

## Definizione

La segmentazione è una tecnica di gestione della memoria utilizzata nei sistemi operativi per suddividere lo spazio di indirizzamento di un processo in segmenti di dimensioni variabili. Ogni segmento rappresenta una diversa unità logica del programma, come un modulo di codice, una tabella di dati, o uno stack.

## Caratteristiche della Segmentazione

- **Unità Logiche**: Ogni segmento rappresenta una parte logica distinta del programma, facilitando la protezione e la condivisione della memoria.
- **Dimensioni Variabili**: A differenza della paginazione, dove tutte le pagine hanno la stessa dimensione, i segmenti possono avere dimensioni diverse in base alle necessità del programma.
- **Indirizzamento**: Gli indirizzi di memoria in un sistema segmentato sono costituiti da una coppia (numero di segmento, offset), dove il numero di segmento identifica il segmento specifico e l'offset specifica la posizione all'interno di quel segmento.

## Vantaggi della Segmentazione

- **Protezione**: Ogni segmento può avere i propri permessi di accesso, migliorando la sicurezza del sistema.
- **Condivisione**: I segmenti possono essere condivisi tra diversi processi, permettendo, ad esempio, la condivisione di librerie di codice.
- **Riflettività Logica**: La segmentazione riflette più da vicino la struttura logica dei programmi rispetto alla paginazione.

## Svantaggi della Segmentazione

- **Frammentazione Esterna**: Con il tempo, la memoria può diventare frammentata, lasciando piccoli buchi tra i segmenti che non possono essere utilizzati efficacemente.
- **Complessità di Gestione**: La gestione della memoria segmentata è più complessa rispetto ad altre tecniche come la paginazione.

## Struttura della Segmentazione

### 1. **Tabella dei Segmenti (Segment Table)**

Ogni processo ha una tabella dei segmenti che contiene le informazioni sui suoi segmenti. Ogni voce della tabella include:

- **Base**: L'indirizzo di partenza del segmento in memoria fisica.
- **Limite**: La lunghezza del segmento.

### 2. **Registro del Segmento**

Il sistema operativo utilizza registri specifici per memorizzare le informazioni di base e limite dei segmenti attualmente in uso. Quando un processo viene eseguito, i registri del segmento vengono caricati con i valori appropriati dalla tabella dei segmenti del processo.

### 3. **Conversione degli Indirizzi**

Quando un processo accede a un indirizzo di memoria, il sistema operativo utilizza la tabella dei segmenti per convertire l'indirizzo logico (numero di segmento, offset) in un indirizzo fisico:

- **Numero di Segmento**: Utilizzato per individuare la voce appropriata nella tabella dei segmenti.
- **Offset**: Aggiunto alla base del segmento per ottenere l'indirizzo fisico.

## Esempio di Utilizzo della Segmentazione

Supponiamo che un processo abbia tre segmenti: un segmento di codice, un segmento di dati e un segmento di stack. La tabella dei segmenti potrebbe apparire come segue:

| Numero di Segmento | Base  | Limite |
|--------------------|-------|--------|
| 0                  | 1000  | 400    |
| 1                  | 2000  | 300    |
| 2                  | 3000  | 500    |

Per accedere a un indirizzo logico (1, 150), il sistema operativo:

1. Trova la voce nella tabella dei segmenti con il numero di segmento 1.
2. Aggiunge l'offset 150 alla base 2000 del segmento.
3. Ottiene l'indirizzo fisico 2150.

## Confronto con la Paginazione

| Caratteristica            | Segmentazione                               | Paginazione                             |
|---------------------------|---------------------------------------------|-----------------------------------------|
| **Unità di Allocazione**  | Segmenti di dimensioni variabili            | Pagine di dimensioni fisse              |
| **Indirizzamento**        | Coppia (numero di segmento, offset)         | Coppia (numero di pagina, offset)       |
| **Frammentazione**        | Esterna                                     | Interna                                |
| **Protezione e Condivisione** | Basata su segmenti                          | Basata su pagine                         |
| **Riflettività Logica**   | Alta, rispecchia la struttura del programma | Bassa, divisione arbitraria in pagine   |

In sintesi, la segmentazione offre una gestione della memoria che riflette più da vicino la struttura logica dei programmi e permette una maggiore flessibilità e protezione, ma può introdurre problemi di frammentazione esterna e maggiore complessità nella gestione rispetto alla paginazione.

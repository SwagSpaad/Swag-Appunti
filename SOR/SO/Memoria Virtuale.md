La memoria virtuale è una tecnica di gestione della memoria che consente a un computer di compensare la carenza di memoria fisica utilizzando lo spazio su disco. Essa fornisce un'illusione di un grande spazio di memoria contiguo, indipendentemente dalla quantità di memoria fisica realmente disponibile. Di seguito viene spiegata nel dettaglio la memoria virtuale e la sua struttura:

## Funzionamento della Memoria Virtuale

1. **Indirizzamento Virtuale**: Ogni processo ha il proprio spazio di indirizzamento virtuale, che è separato dagli spazi di indirizzamento degli altri processi. Questo spazio di indirizzamento virtuale è diviso in pagine.
2. **Mapping**: Gli indirizzi virtuali vengono mappati agli indirizzi fisici tramite una tabella delle pagine (page table). Questa tabella contiene le informazioni su dove le pagine virtuali risiedono nella memoria fisica.
3. **Page Faults**: Quando un processo accede a una pagina che non è attualmente in memoria fisica (page fault), il sistema operativo deve caricare quella pagina dalla memoria di massa (come un disco rigido) nella memoria fisica.

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

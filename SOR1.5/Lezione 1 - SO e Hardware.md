# Che cos'è un OS?
Un moderno calcolatoreè formato da:
- Uno o più processori
- Memoria centrale
- Dischi, Stampanti e altri dispositivi I/O

I dettagli di basso livello sono molto complessi
Gestire queste componenti richiede uno strano intermedio software: Il **Sistema Operativo**

# Componenti di un computer moderno (1)
- Uno o più processori
- Memoria principale
- Dischi o unità flash
- Stampanti
- Tastiera
- Mouse
- Display
- INterfaccia di rete
- DIspositivi I/O

# Componenti di un computer moderno (2)
Doppia modalità supportate dall'hardware:
- Modelità kernel
- Modalità utente

![[SOR1.5/img/img1.png\|center\|600]]

# The operating system as an extended machine
Idea di **astrazione**
- Il sistema operativo si pone tra l'hardware e le applicazioni
Visione **Top-Down**
- Il SO fornisce astrazioni ai programmi applicativi
Vista **Botton-Up**
- Il SO gestisce parti di un sistema operativo e fornisce un'allocazione ordinata e controllata dalle risorse

![[SOR1.5/img/img2.png\|center\|600]]

# Il sistema operativo come gestore di risorse
Da un moderno sistema operativo ci aspettiamo che gestisca:
- **Più programmi in esecuzione**
- **Più utenti**

Necessita allocazione ordinata a econtrollata di:
- Risorse quali: processori, memoria, unità I/O

Non solo Hardware: file, database....

**Multiplexing**:
- Nel **tempo**: CPU, stampante...
- Nello **spazio**: memoria centrale, disco...

# Uno sguardo all'hardware|
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
|![[SOR1.5/img/img3.png]]|

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
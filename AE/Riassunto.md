# Traduzione vs Interpretazione
- **Traduzione**: sostituire, in fase iniziale, ogni istruzione del programma scritto in $L_{1}$ con una sequenza di istruzioni equivalente in $L_{0}$. Il programma quindi è costituito interamente da istruzioni in $L_{0}$ e può essere eseguito dal computer al posto del programma in $L_{1}$ 
- **Interpretazione**: scrivere un programma in $L_{0}$ che accetta come dati d'ingresso dei programmi in $L_{1}$. Questo programma esegue il programma in $L_1$ esaminando un'istruzione per volta e sostituendola direttamente con l'equivalente istruzione in $L_{0}$. Il programma in $L_{0}$ è detto *interprete*

Nella traduzione il programma viene convertito interamente in un programma in $L_{0}$ (compilazione)
Nell'interpretazione ogni istruzione viene sostituita e non viene generato nessun altro file, ma l'esecuzione di un programma interpretato è più lenta, perché deve essere effettuata ogni volta

# Livelli di un computer

![[AE/img/img1.png|center|400]]

- Livello 0: hardware e porte logiche
- Livello 1 **microarchitettura**: memoria locale formata da registri, ALU (esegue operazioni). Registri connessi alla ALU per formare percorso dati (selezionare uno o due registri, permettere alla ALU di eseguire operazioni su di loro e memorizzare il risultato). Il *microprogramma* controlla le operazioni del percorso dati
- Livello 2: sono le istruzioni eseguite dall'architettura della macchina interpretate dal microprogramma
- Livello 3: vengono introdotte istruzioni aggiuntive rispetto al livello 2, diversa organizzazione della memoria e capacità di esecuzione concorrente. Tutto questo è eseguito da un interprete storicamente chiamato sistema operativo. Le istruzioni identiche a quelle del livello 2 sono eseguite da microprogrammi e non dal sistema operativo.
- Livello 4: consente di scrivere programmi per i livello 1, 2 e 3 in modo più agevole. Traduce i programmi nei linguaggi dei livelli inferiori con l'assemblatore.
- Livello 5: definisce linguaggi di programmazione ad alto livello, tradotti da un compilatore ai livelli 3 o 4

# Macchina von Neumann

![[AE/img/img101.png|center|500]]

Composta da:
- memoria: conserva il programma e i dati su cui deve lavorare il programma
- CPU: unità di elaborazione composta da:
	- ALU: esegue istruzioni aritmetiche e logiche
	- CU (unità di controllo) recupera le istruzioni in memoria secondo l'algortimo in uso e permette l'esecuzione di esse
- Input/output: interfaccia del calcolatore verso l'esterno
- bus di comunicazione: canale che permette la comunicazione dei componenti 

# Processori
CPU: composta da unità di controllo, ALU e registri tra cui:
- Program Counter (PC): contiene l'indirizzo della prossima istruzione da eseguire
- Instruction Register (IR): mantiene l'istruzione corrente in fase di esecuzione

Il data path di una CPU di von Neumann è composta dai registri (da 1 a 32), dalla ALU e dai bus che connettono le diverse parti

I registri di input della ALU mantengono i dati di ingresso mentre la ALU esegue le operazioni su di esse, e il risultato viene memorizzato in un suo registro di output. 
Questo valore può essere immagazzinato in uno dei registri della CPU che può essere copiato in memoria in un secondo momento

Le istruzioni si possono dividere in delle categorie:
- istruzioni **registro-memoria**: necessita il caricamento di parole della memoria nei registri. permettono di prelevare parole di memoria per portarle all'interno dei registri e viceversa. questi dati possono essere utilizzati come input della ALU
- istruzioni **registro-registro**: coinvolgono operandi già presenti nei registri, si prelevano e vengono trasferiti nei registri di input della ALU, e su questi dati vengono eseguite delle operazioni.

# Esecuzione dell'istruzione
Ogni istruzione è eseguita dalla CPU compiendo una serie di passi (*fetch-decode-execute*)
- **prelevare la successiva istruzione** dalla memoria e la porta nell'IR
- **aggiorna il PC** per farlo puntare all'istruzione seguente
- **decodifica l'istruzione** appena prelevata
- se l'istruzione usa una parola in memoria, determina dove si trova
	- preleva la parola per portarla in un registro della CPU
- **esegue l'istruzione**
- torna al punto 1

# Strategie progettazione CPU
- CISC (Complex Instruction Set Computer): CPU in grado di comprendere molte istruzioni complesse 
- RISC (Reduced Instruction Set Computer): basate sull'idea che se le istruzioni sono semplici e poche esse possono essere eseguite rapidamente (un solo ciclo necessario nel datapath)
- Ibrido: contengono un sottoinsieme di istruzioni RISC (quelle più comuni) mentre le altre più complesse sono interpretate secondo la modalità CISC

# Principi di progettazione RISC
- Tutte le istruzioni eseguite dall'hardware: 
	- istruzioni non interpretate. Le architetture CISC suddividono le istruzioni complesse in microistruzioni
- Massimizzare frequenza di emissione di istruzione: 
	- il parallelismo è essenziale per le prestazioni
- Decodifica facile delle istruzioni:
	- limitare la complessità della decodifica rendendo le istruzioni regolari, di lunghezza fissa e con pochi campi.
- Solo le istruzioni LOAD e STORE fanno riferimento alla memoria:
	- l'operazione di spostamento degli operandi dalla memoria ai registri è consentito solo a istruzioni specifiche.
- Molti registri disponibili:
	- dato che l'accesso alla memoria è lento, si prevedono molti registri per evitare frequenti accessi in memoria

# Parallelismo a livello di istruzione
Poiché l'incremento del clock del processore ha raggiunto un limite fisico, si guarda al parallelismo per incrementare le prestazioni. Esso si può ottenere in due diversi modi: 
- *parallelismo a livello di istruzione*: sfruttato all'interno delle istruzioni per ottenere un maggior numero di istruzioni al secondo
- *parallelismo a livello di processore*: più CPU collaborano per risolvere lo stesso problema

# Pipelining
Una limitazione nella velocità di esecuzione sta nel prelievo delle istruzioni dalla memoria. Per ridurre questo problema, i calcolatori hanno la capacità di prelevare in anticipo le istruzioni dalla memoria, in modo da avere a disposizione nel momento in cui dovessero rendersi necessarie. 

Le istruzioni venivano memorizzate in dei buffer di prefetch e potevano essere prese nel momento in cui venivano richieste, senza dover attendere che si completasse la lettura dalla memoria. 

Il pipeline divide l'esecuzione di un istruzione in un numero maggiore di parti che possono essere eseguite in parallelo.

![[AE/img/img42.png|center|500]]

## Processori con più pipeline

![[AE/img/img102.png]]

Avere più pipeline permette un parallelismo maggiore, rispetto ad averne una sola, ma affinché le due istruzioni possano essere eseguite in parallelo, non ci devono essere conflitti nell'uso delle risorse e nessuna delle due istruzioni deve dipendere dal risultato dell'altra

# Architetture superscalari
Il processore dispone di una sola pipeline con più unità funzionali in corrispondenza di alcuni stadi 

![[AE/img/img104.png|center|500]]

Affinché l'architettura abbia senso è necessario che la velocità di $S_{3}$ sia più alta rispetto a quella della fase $S_{4}$ 

# Parallelismo nel processore
Il parallelismo nel processore aiuta a migliorare le performance della CPU: con il pipelining e le architetture superscalari si può arrivare ad un fattore di miglioramento da 5 a 10. 
Per incrementare notevolmente le performance occorre progettare sistemi con molte CPU, arrivando ad un fattore di migliormento di 50, 100 o più.

Esistono tre differenti approcci:
- computer con parallelismo sui dati
- multiprocessori
- multicomputer

## Computer con parallelismo sui dati
- Processori SIMD (Single Instruction Multiple Data): costituiti da un vasto numero di processori che eseguono la stessa istruzione su un insieme differente di dati
- Processori vettoriali: esegue la stessa sequenza di operazioni su coppie di dati, ma tutte le addizioni sono svolte da un unico sommatore strutturato in pipeline

Entrambe le architetture lavorano su array di dati, mentre il primo utilizza tanti sommatori quanti gli elementi del vettore, il secondo utilizza un solo sommatore e un unico registro vettoriale

## Multiprocessori
Architettura costituita da più CPU che condividono memoria comune. Poiché ciascuna CPU può leggere o scrivere qualsiasi zona della memoria comune, le CPU devono sincronizzarsi via software. 

## Multicomputer
I multiprocessori con molte CPU sono difficili da realizzare a causa delle connessioni di ciascuna CPU verso la memoria comune.
I progettisti hanno superato il problema abbandonando il concetto di memoria comune e realizzando un elevato numero di CPU interconnesse, ciascuno con la propria memoria privata.
Le CPU in un multicomputer comunicano attraverso lo scambio di messaggi.
In architetture grandi la completa interconnessione non è fattibile così sono utilizzate topologie differenti come la griglia, l'anello o l'albero

# Memoria cache
Le CPU sono più veloci delle memorie, quindi il recupero di una parola dalla memoria può richiedere molti cicli di CPU. Per mitigare questo, si utilizza la memoria cache: una piccola quantità di memoria veloce combinata con una grande memoria lenta. La cache conserva le parole di memoria più frequentemente usate, e quando la CPU ha bisogno di una parola la cerca prima nella cache e, se non è presente, la richiede alla memoria

I principi chiave della memoria cache includono: 
- località spaziale e temporale: i programmi tendono ad accedere a porzioni specifiche della memoria in modo non casuale. La località spaziale suggerisce che i riferimenti successivi alla memoria siano vicini a quelli attuali, mentre la località temporale indica che nell'arco dell'esecuzione del programma, si accede spesso alle stesse zone del programma

Quando una parola viene referenziata, anche le parole vicine vengono caricate nella cache.

I moderni sistemi di memoria hanno più livelli di cache:
- L1: il chip della CPU contiene una cache per le istruzioni e una per i dati di dimensioni tra i 16 e i 64kB
- L2: questa cache è esterna al chip della CPU ma inclusa nel suo involucro, unificata (contiene sia dati che istruzioni) di dimensioni tra le 512kB e 1MB
- L3: cache di terzo livello posizionata sulla scheda del processore e di dimensioni di alcuni MB.
Le cache sono organizzate in modo gerarchico, dove il contenuto della cache di livello superiore è incluso in quella di livello inferiore.
La memoria cache sfrutta la località dei riferimenti alla memoria per conservare dati utilizzati spesso, riducendo i tempi di accesso e migliorando le prestazioni, mentre i vari livelli offrono una gerarchia di velocità e capacità.

# RAID
Il raid è una tecnologia di installazione di dischi distinti in un sistema e fa sì che vengano riconosciuti dal sistema come un grande disco unico, per migliorare l'archiviazione, le prestazioni e la ridondanza dei dati. 

- RAID Livello 0: divide i dati equamente tra due o più dischi (stripping). Alte prestazioni in lettura e scrittura grazie al parallelismo delle operazioni I/O sui dischi, ma non offre tolleranza ai guasti, infatti se si rompe un disco si perdono tutti i dati
- RAID livello 1: duplica i dati su due o più dischi (mirroring). Prestazioni in scrittura come quelle con un singolo disco (bisogna scrivere sia sul disco primario che su quello di backup), ma prestazioni in lettura migliori. Tolleranza ai guasti eccellente, se si rompe un disco si utilizza la sua copia.
- RAID livello 2: divide i dati a livello di bit e utilizza un codice di Hamming per correggere gli errori su singoli bit. Richiede sincronizzazione delle testine dei dischi e oggigiorno non è più utilizzato a causa della complessità e dei costi
- RAID livello 3: suddivide i dati a livello di byte e calcola su un disco dedicato il bit di parità per ogni parola di dati. Offre maggiore tolleranza agli errori rispetto RAID 2, ma richiede la sincronizzazione dei dischi
- RAID livello 4: suddivide i dati in blocchi e mantiene per ogni blocco un bit di parità su un disco aggiuntivo. Se un disco si guasta si possono ricalcolare i byte persi grazie a questo disco, ma se si guasta il disco di parità non c'è modo di ripristinare i dati. Ha prestazioni scarse quando si aggiornano piccole quantità di dati.
- RAID livello 5: distribuisce i bit di parità in modalità round-robin su tutti i dischi. Protegge dalla perdita di un disco, ma la ricostruzione è un processo complesso. 


# Livello Logico Digitale
È l'hardware del calcolatore ed è costituito dalle porte logiche che compongono circuiti come sommatori, shifter, memorie ecc.
Le porte logiche sono vendute singolarmente o dentro i **circuiti integrati** 
I circuiti si distinguono in: 
- circuiti combinatori: l'uscita dipende dalla configurazione degli ingressi
- circuiti sequenziali: l'uscita dipende dalla configurazione degli ingressi e dallo stato del circuito
## Multiplexer

![[AE/img/img12.png|center|500]]

## Decoder 

![[AE/img/img13.png|center|500]]

## Comparatori
Esegue l'uguaglianza tra due parole espresse in bit e restituisce 1 se sono uguali

![[AE/img/img103.png|center|500]]

## PLA 
Chip generale che permette di calcolare somme di prodotti. Nella figura la porta OR $F_1$ genera come risultato $\bar WYZ+WX\bar Z$ 

![[AE/img/img14.png|center|500]]

## Shifter
L'output è una parola che viene fatta scorrere a destra o sinistra in base al valore di C

![[AE/img/img15.png|center|700]]

## Half adder
Esegue la somma di due bit senza gestire il riporto in ingresso 

![[AE/img/img16.png|center|500]]

## Full adder
Prende in ingresso 3 bit e restituisce somma e riporto

![[AE/img/img17.png|center|500]]

## ALU
Una ALU contiene 3 unità: 
- unità logica per il calcolo di funzioni logiche
- full adder
- decoder per decidere quale operazione effettuare

![[AE/img/img105.png|center|700]]

Le ALU ad un bit possono essere assemblate per costruire ALU  più bit

![[AE/img/img106.png|center500]]

## Memorie
### SR Latch
S = 1 salva 
R = 1 reset
genera ambiguità se S = 1 e R = 1 contemporaneamente

![[AE/img/img19.png|center|700]]

### SR Latch clocked
S ed R sono agiscono solo quando il clock è attivo

![[AE/img/img20.png|center|700]]

### D Latch clocked
Risolve l'ambiguità di R=S=1

![[AE/img/img21.png|center|700]]

### Flip Flop
Ha lo stesso scopo del D Latch ma nel D Latch la transizione avviene quando il segnale del clock è alto, mentre nel flip flop lo stato cambia nel fronte di salita o discesa del clock

![[AE/img/img23.png|center|700]]

### Memoria a 8 bit
Utilizza 8 flip flop
![[AE/img/img24.png|center|600]]

# Livello Microarchitettura
Il livello di microarchitettura è sopra il livello logico digitale ed è responsabile dell'implementazione del livello ISA.

Il microprogramma è composto da una sequenza di microistruzioni che costituiscono lo stato della macchina. 
Il PC (program counter) contiene il riferimento della prossima microistruzione da eseguire.
Ogni microistruzione si compone di: 
- OPC (OPCode) identifica il tipo di istruzione 
- operando su cui va eseguita l'istruzione

L'esecuzione è basata sul ciclo fetch decode execute

Il *data path* è la parte di CPU che contiene l'ALU, i registri, gli input e gli output. Contiene dei registri a 32 bit e si compone di due bus: il bus B e C.
I registri sono: 
- Memory Address Register
- Memory Data Register
- Program Counter
- Memory Byte Register 
- Stack Pointer 
- Local Variable (contiene il riferimento nello stack alla base delle variabili)
- CPP (Constant Pool)
- Top word On Stack
- Op Code register (contiene l'ultima istruzione eseguita prima di un salto)
- Holding
![[AE/img/img107.png|center|600]]

Nel data path l'ALU richiede due input: A, collegato al registro **H**old di mantenimento e B collegato al bus **B**. Il bus B può essere caricato da 9 sorgenti diverse (i registri visti in precedenza). 
L'ALU ha 6 linee di controllo:
- F0, F1 per decidere l'operazione da svolgere sugli input (vedere tabella per le combinazioni)
- ENA, ENB abilita il valore delle variabili A e B
- INVA, esegue una sottrazione o inverte il valore di A
- INC, incrementa
![[AE/img/img108.png|center|500]]

La ALU agisce sugli operandi A e B ed è possibile spostare l'operando B nell'ingresso di A utilizzando la funzione che restituisce B (F0=0 F1=1) e memorizzare il risultato del bus C nel registro H. 

La lettura e la scrittura di un registro avvengono in fasi diversi dello stesso ciclo di clock. Nella fase iniziale viene selezionato un registro come input destro della ALU e i suoi valori sono caricati sul bus B. Vengono effettuate operazioni sui dati e il risultato inviato sul bus C attraverso lo shifter. Verso la fine del ciclo di clock, un segnale attiva la memorizzazione dei valori sul bus C nei registri e questo consente lettura e scrittura in un solo ciclo, evitando inconsistenze dei dati.

## Microistruzioni 
Per controllare il data path abbiamo bisogno di 29 segnali suddivisi in  5 gruppi
- 9 segnali per controllo scrittura dei dati dal bus C ai registri
- 9 segnali per controllo abilitazione registri sul bus B per input ALU
- 8 segnali per controllo funzioni ALU e shifter
- 2 segnali per leggere (scrivere) attraverso MAR (MDR)
- 1 segnale per indicare prelievo della memoria attraverso PC o MBR

Durante un ciclo del data path, si caricano i valori dei registri sul bus B, si eseguono operazioni attraverso l'ALU e lo shifter, si guida il risultato sul bus C e si riscrivono i dati nei registri. Se viene eseguita un'operazione di lettura dalla memoria, essa inizia alla fine del ciclo di percorso dati, dopo il caricamento di MAR. 
Quindi la letturaq della memoria iniziata alla fine del ciclo $k$, trasmette dati che non possono essere utilizzati nel ciclo $k+1$ ma soltanto nel ciclo $k+2$.

Possiamo controllare il percorso dati con 24 segnali (quindi 24 bit). Questi segnali controllano il percorso dati solo per un ciclo, e quindi bisogna determinare cosa effettuare nel ciclo successivo. Per questo si adotta un formato della microistruzione che comprende i 24 bit di controllo e due campi aggiuntivi: NEXT_ADDRESS e JAM

![[AE/img/img31.png|center|500]]

- Addr: contiene indirizzo successiva microistruzione
- JAM: determina come viene selezionata la successiva microistruzione
- ALU: seleziona funzioni ALU e shifter
- C: seleziona quali registri sono scritti dal bus C
- Mem: selezione funzione della memoria
- B: seleziona sorgente del bus B (4 bit, valori da 0 a 8)

## Mic-1
![[AE/img/img32.png|center|500]]

La memoria di controllo memorizza le microistruzioni
Il decoder decodifica le istruzioni in base all'opcode

## IJVM
Il livello di microarchitettura è responsabile dell'implementazione del livello ISA. Analizziamo un caso particolare ovvero l'**IJVM** . Abbiamo descritto al microarchitettura su cui implementeremo l'IJVM (ovvero la mic-1). La mic-1 contiene un microprogramma registrato in una ROM, responsabile di prlevare, decodificare ed eseguire le istruzioni IJVM.
Ogni istruzione IJVM è costituita da uno o due campi, il primo campo di ogni istruzione è **OpCode** che identifica il tipo di istruzione (ADD, BRANCH ecc.)

### Stack
Concetto fondamentaqle nella gestione delle variabili locali. Il problema principale è dove memorizzare le variabili locali, dato che l'assegnazione di indirizzi assoluti non funziona, perché porterebbe a conflitti se una procedura è chiamata più volte.

Si utilizza quindi lo stack per memorizzare le variabli locali. Si difenisce un registro LV che punta alla procedura corrente e il registro SP che punta all'ultimo elemento dello stack.

Quando una procedura chiama un'altra procedura, lo spazio delle variabili della nuova procedura viene allocato sopra lo stack e vengono aggiornati i puntatori LV ed SP. Quando una procedura termina, lo spazio assegnato alle variabili viene liberato.

### Insieme delle istruzioni IJVM

![[AE/img/img35.png|center|500]]

# Livello ISA
Il livello ISA costituisce l'interfaccia tra Hardware e software. Teoricamente è possibile eseguire direttamente codice di alto livello sull'hardware, ma ciò comporterebbe la perdita di prestazioni ed inoltre è preferibile che i computer siano in grado di eseguire programmi scritti in linguaggi diversi anziché uno solo.
L'approccio comune dei progettisti è quella di tradurre i vari linguaggi di alto livello in un formato comune, ovvero l'ISA, per poi costruire l'hardwaqre che esegue i programmi.

L'ISA deve essere **retrocompatibile**, quindi in grado di far girare anche i programmi più vecchi.
Ha due modalità operative: 
- modalità kernel: esegue il sistema operativo e le operazioni sensibili
- modalità utente: per eseguire i programmi utenti 

## Proprietà livello isa
Il livello ISA può essere visto come la rappresentazione della macchina dal punto di vista del programmatore in linguaggio macchina. Tuttavia, dato che ormai pochi programmatori scrivono direttamente in linguaggio macchina, possiamo dire ch eil codice di livello ISA è l'output di un compilatore.

Per produrre il codice a livello ISA, i progetitsti dei compilatori hanno bisogno di comprendere il modello di memoria, i registri disponibili, i tipi di dati e le istruzioni accessibili. Tutte queste informazioni costituiscono il livello ISA

Il livello ISA è descritto attraverso un documento di definizione del produttore ed ha lo scopo di mettere i diversi produttori di chip in grado di costruire macchine capaci di eseguire lo stesso codice ISA. 
Ad esempio ARM pubblica la definizione del suo livello ISA con lo scopo di permettere ai produttori di produrre chip ARM che funzionino tutti nello stesso modo, ma con costi e prestazioni diverse. 

L'ISA si compone di: 
- modello di memoria
- insieme dei registri
- tipi di dati possibili
- insieme delle istruzioni

## Modello di memoria
I computer organizzano la memoria in celle consecutive di 8 bit (1 byte), scelta derivante dal fatto che i caratteri ASCII occupano 7 bit. Con l'eventuale predominanza dello standard UNICODE, la memoria potrebbe essere organizzata in celle da 2 byte (16 bit).

I byte sono spesso raggruppati in parole di 4 o 8 byte (32 o 64 bit). Alcune macchine richiedono che queste parole siano allineate agli indirizzi multipli della loro dimensione (es. una parola da 8 byte può iniziare agli indirizzi 0, 8, 16, ecc.). L'allineamento ottimizza la memoria, ma richiede hardware più complesso e costoso, nonostante la retrocompatibilità possa necessitare l'accesso a indirizzi arbitrari, quindi a partire da indirizzi differenti dai multipli della dimensione (es. l'8088 con bus da 1 byte).

![[AE/img/img110.png|center|500]]

I processori hanno uno spazio di memoria lineare degli indirizzi che va da $2^{32}$ a $2^{64}$. Alcune macchine hanno spazi separati per istruzioni e dati, aumentando la sicurezza (è impossibile sovrascrivere il programma) e permettendo una maggiore selezione di indirizzi.

La semantica della memoria richiede che un'istruzione LOAD, eseguita dopo una STORE sullo stesso indirizzo, restituisca il valore appena memorizzato. Tuttavia, il riordinamento delle microistruzioni può causare comportamenti inattesi. Le soluzioni a questo problema includono:

- **Serializzare gli accessi alla memoria**: Ogni richiesta di accesso viene completata prima di emettere la successiva. Questa soluzione **semplifica la semantica** ma **degrada le prestazioni**.
- **Nessuna garanzia di ordine**: Per forzare un ordine, il programma deve eseguire un'istruzione SYNC, che blocca le operazioni finché le precedenti non sono completate. Questo approccio **genera un carico di lavoro maggiore per il compilatore**, ma **consente all'hardware maggiore libertà di ottimizzazione**.
## Registri
Tutti i computer dispongono di registri visibili a livello ISA (Instruction Set Architecture) per controllare l'esecuzione dei programmi e memorizzare risultati temporanei. Registri come TOS (Top of Stack) e MAR (Memory Address Register) non sono visibili a livello ISA.

I registri si dividono in due categorie:
- Registri specializzati: **Program Counter (PC)**, **Stack Pointer (SP)**, registri visibili in modalità kernel (controllo cache, dispositivi I/O, ecc.)
- **Registri di uso generale**: Utilizzati per memorizzare temporaneamente variabili locali. Possono essere simmetrici e intercambiabili oppure specializzati. Ad esempio, nel Pentium 4, il registro EDX è usato sia come registro d'uso generale sia per ricevere metà del prodotto in una moltiplicazione e metà del dividendo in una divisione.

Nonostante l'intercambiabilità dei registri, i sistemi operativi e compilatori adottano convenzioni sull'uso dei registri; ignorarle può causare problemi nella gestione dei dati.

Oltre ai registri visibili agli utenti, ci sono registri accessibili solo in modalità kernel, usati dal sistema operativo.

Il registro di flag **Program Status Word (PSW)** è un ibrido tra modalità kernel e utente. Contiene bit necessari alla CPU, tra cui i codici di condizione, che riflettono lo stato dell'operazione più recente:

- **N**: 1 se il risultato è negativo
- **Z**: 1 se il risultato è zero
- **V**: 1 se il risultato causa overflow
- **C**: 1 se il risultato causa un riporto oltre l'ultimo bit significativo
- **A**: 1 se c'è un riporto oltre il terzo bit
- **P**: 1 se il risultato è pari

Questi codici sono usati dalle istruzioni di confronto (CMP) e dai salti condizionali (ad esempio, BEQ branch on equal, salta se Z è 1, questo vuol dire che i due operandi sono uguali).
Il PSW contiene anche campi per la modalità di esecuzione, bit di traccia, ecc. È leggibile in modalità utente, ma alcuni campi sono scrivibili solo in modalità kernel.
## Modalità di indirizzamento
### Immediato
Il valore dell'operando è specificato direttamente nell'istruzione al posto del campo dell'indirizzo 
``` 
MOV R1 4 //carica la costante 4 nel registro R1
```
Presenta limitazioni nella dimensione dell'operando e nel numero di operandi forniti

### Diretto
L'istruzione contiene l'indirizzo di memoria completo dell'operando. 
```
MOV R1 [1234h] //carica in R1 il valore memorizzato nell'indirizzo di memoria 1234h
```
L'istruzione accederà quindi sempre alla stessa locazione da memoria, è utile per accedere a variabili globali con indirizzo noto.

### Indirizzamento a registro
Analogo all'indirizzamento diretto, ma specifica un registro invece di una locazione di memoria. 
```
MOV R1 R2 //il contenuto del registro R2 viene copiato nel registro R1
```
Modalità più utilizzata grazie alla velocità dei registri, tranne per il caso in cui un operando è trasferito dalla memoria in un registro (LOAD) o da un registro alla memoria (STORE)

### A registro indiretto
L'operando proviene o è destinato alla memL'oria, però il suo indirizzo non è specificato direttamente nell'istruzione, ma è contenuto in un registro. 
Il vantaggio è quello di poter referenziare la memoria senza incorporare un intero indirizzo di memoria nell'istruzione.
```
MOV R1 (R2) //l'operando contenuto all'indirizzo contenuto in R2 viene copiato in R1
```

### Indicizzato
Permette di referenziare una parola che si trova ad un offset dal contenuto di un registro. 
```
MOV R1, (R2 + 4) //copia in R1 il valore della zona di memoria 4 byte avanti rispetto l'indirizzo di R2 
```
Utile per accedere agli elementi di un array

### Indicizzato esteso
L'indirizzo di memoria è calcolato sommando tra loro il contenuto di due registri più un offset opzionale. Un registro funge da base (indirizzo di partenza) e l'altro da indice. 
```
MOV R1, (R2 + R3 + 4) //copia in R1 il valore all'indirizzo R2+R3 e poi 4 byte avanti al risultato
```

### A stack
Lo stack risulta utile per quelle istruzioni senza indirizzi. È utilizzato per:
- gestire le chiamate dei procedura 
- calcolare espressioni aritmetiche 
- salvare risultati intermedi
Operazioni principali:
- push: aggiunge elemento in cima dello stack
- pop: preleva l'elemento in cima allo stack

![[AE/img/img111.png|center|700]]

### Notazione polacca
Modo per scrivere le espressioni matematiche senza utilizzo di parentesi. Ideale per eseguire calcoli in computer dotati di stack. 
Infatti l'espressione $$(8+2\times5)/(1+3\times2-4)$$
in notazione polacca risulta $$8\ 2\ 5\times+\ 1\ 3\ 2\times +\ 4-\ /$$
![[AE/img/img112.png|center|700]]


# Trap e interrupt
- Trap: chiamate di procedura automatiche che si attivano quando si verificano delle condizioni eccezionali causate da un programma. Ad esempio un overflow (quando un risultato aritmetico supera la capacità di rappresentazione) causa una trap. Sono gestite da un *gestore di trap*, che si occupa di gestire l'eccezione, ad esempio stampando un messaggio d'errore
- Interrupt: cambiamenti nel flusso esecutivo generati da problemi esterni al programma. Interrompono il programma in esecuzione e trasferiscono il controllo ad un *gestore* e al loro compimento il gestore restituisce il controllo al programma esattamente da dove era stato interrotto. Al contrario delle trap, che avvengono sempre allo stesso punto dell'esecuzione di un programma, gli interrupt sono generati da eventi esterni, come la pressione di un tasto, pertanto non sono riproducibili. 

Le trap sono generate da condizioni all'interno del programma (gestione software), mentre gli interrupt da eventi esterni all'esecuzione (gestione hardware).

Dopo l'interruzione, sia la trap che l'interrupt devono far tornare il sistema nello stato precedente all'interruzione, garantendo una corretta ripresa dell'esecuzione del programma (trasparenza)

Gli interrupt possono essere gestiti con priorità, dando precedenza a determinati eventi rispetto ad altri

Durante un interrupt vengono salvati i registri di stato per poi ripristinarli alla fine dell'interruzione, garantendo il ritorno al flusso precedente

# Linguaggio assemblativo (livello 5)

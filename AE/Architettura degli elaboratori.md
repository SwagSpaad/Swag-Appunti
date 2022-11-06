# Capitolo 1 
# Introduzione
---

## Differenza tra traduzione e interpretazione

Un metodo per eseguire un programma scritto in L1 consiste nel sostituire, in una fase iniziale, ogni sua istruzione con un'equivalente sequenza di istruzioni in L0. Il programma che ne risulta è costituito interamente da istruzioni di L0 e può essere eseguito dal computer al posto del programma L1 originale. Questa tecnica è chiamata **traduzione**. 
L'altra tecnica consiste invece nello scrivere un programma in L0 che accetta come dati d'ingresso programmi in L1; tale programma li esegue esaminando un'istruzione alla volta e sostituendola direttamente con l'equivalente sequenza di istruzioni L0. Questa tecnica, che non richiede la generazione preventiva di un nuovo programma L0, è chiamata **interpretazione** e il programma che la esegue è detto **interprete**.

- La traduzione: tradurre tutto il programma al linguaggio inferiore e poi viene eseguito. 
- L'interpretazione: traduce ed esegue ogni singola istruzione del programma. Legge ed esegue il codice sorgente del programma senza creare un file oggetto eseguibile. E' più lenta rispetto alla compilazione.

## Differenza tra compilazione ed interpretazione

La compilazione e l'interpretazione sono due metodi per eseguire un programma informatico. La compilazione, eseguita dal compilatore, traduce tutte le istruzioni di un programma in linguaggio macchina, creando un file eseguibile dal computer.
L'interpretazione traduce ed esegue ogni singola istruzione del programma. Legge ed esegue il codice sorgente del programma senza creare un file oggetto eseguibile. E' più lenta rispetto alla compilazione.

L'esecuzione di un programma compilato è molto più veloce rispetto a un programma interpretato. Il motivo è molto semplice, la compilazione crea un file eseguibile ( oggetto ) in linguaggio macchina ed è immediatamente eseguibile dal processore.
Viceversa, l'interpretazione legge un'istruzione alla volta dal codice sorgente del programma ( sorgente ) e la traduce in linguaggio macchina per farla eseguire dal processore.
Dopo aver eseguito un'istruzione, il software interprete passa ad elaborare la successiva e non resta traccia della precedente. Tutti questi passaggi si ripetono ogni volta che eseguo il programma. Per questa ragione l'esecuzione di un programma interpretato è più lenta.

![[AE/img/img0.png|center|400]]


## Livelli di un computer

![[AE/img/img1.png|center|400]]

# Capitolo 2 
## Organizzazione dei sistemi di calcolo
---
## CPU

Una tipica CPU di Von Neumann è formata da registri (da 1 a 32), dalla ALU e da alcuni bus che connettono tra loro diverse parti. I registri alimentano due registri di input della ALU che mantengono i dati d’ingresso della ALU. La ALU esegue operazioni di somma e sottrazione, e il risultato viene memorizzato in un apposito registro di input, successivamente può essere immagazzinato in uno dei registri della CPU e volendo copiato in memoria.
Le operazioni sono divise in: 
- istruzioni registro-memoria: prelevano parole in memoria e vengono portate all'interno dei registri, dove vengono utilizzate dalla ALU per operazioni successive
- istruzioni registro-registro: preleva due operandi dai registri e li porta dentro dei registri di input della ALU e ne memorizza il risultato in uno dei registri. Il processo che porta i due operandi attraverso la ALU e memorizza il risultato è chiamato **ciclo del percorso dati** ed è il cuore della CPU.
### Esecuzione dell'istruzione

La CPU esegue ogni istruzione compiendo una serie di passi:
1.  p releva la successiva istruzione dalla memoria per portarla nell’IR;
2.  modifica il PC per farlo puntare all’istruzione seguente;
3.  determina il tipo di istruzione;
4.  se l’istruzione usa una parola in memoria, determina dove si trova;
5.  se necessario, preleva la parola per portarla in un registro della CPU;
6.  esegue l’istruzione;
7.  torna al punto 1 per l’istruzione successiva.
Questa sequenza di passi è detta ciclo di prelievo-decodifica-esecuzione (fetch-decode-execute). 

## CISC e RISC

La **CISC** (Complex istruction set computer) è formata da un set di istruzioni in grado di eseguire operazioni molto più complesse rispetto all’architettura **RISC**. E’ infatti possibile utilizzare una sola istruzione per leggere un dato in memoria, modificarlo e salvarlo nuovamente, cosa impensabile con un set di istruzioni ridotto. 
Sebbene questo tipo di architettura non abbia la velocità di quella **RISC**, ha il vantaggio di poter realizzare programmi più compatti che occupano un minor spazio in memoria. I programmi in questione sono però più “pesanti“, e richiedono molto tempo per l’esecuzione. Un singolo set di istruzioni è eseguito in più passaggi; ogni set di istruzioni ha oltre 300 istruzioni separate. Le istruzioni vengono completate in 2-10 cicli macchina.
La **RISC** è semplice e lineare, permettendo al microprocessore di eseguire il set di istruzioni in tempi molto rapidi e inferiori ai tempi dell'architettura CISC. Esiste un insieme di principi di progettazione, chiamati **principi di progettazione RISC**, che i progettisti delle CPU cercano di seguire il più possibile:
-   Tutte le istruzioni sono eseguite direttamente dall’hardware;
-   Massimizzare la frequenza di emissione delle istruzioni;
-   Le istruzioni devono essere facili da decodificare;
-   Solo le istruzioni Load e Store fanno riferimento alla memoria;
-   Molti registri disponibili.

![[AE/img/img2.png|center|500]]  

## Dischi magnetici

Ogni traccia è divisa in settori di lunghezza fissata. Tra due settori è presente un piccolo spazio detto **intersector gap**.
Le tracce interne hanno maggiore densità di memorizzazione rispetto alle esterne. Ogni drive ha una scheda dedicata (può contenere una CPU) chiamato controllore del disco, oltre a pilotare accetta i comandi software, corregge i dati.

## Hard disk

Sono dispositivi di memorizzazione dati che possono essere collegati internamente o esternamente al calcolatore. 

### Hard disk magnetici 

Costituito da una pila di dischi che ruotano intorno all’asse, l’insieme di tracce è detto cilindro.
Le performance dell’HD dipendono da:
-   tempo medio di seek;
-   latenza rotazionale;
-   tempo di trasferimento.

Il posizionamento della testina nel raggio della traccia ricercata è detto tempo medio di seek.
La latenza rotazionale è il tempo necessario al disco per posizionare il corretto settore sotto la testina e dipende dalla velocità angolare del disco.
Il tempo di trasferimento dipende dalla densità lineare e dalla velocità di rotazione.

### Dischi IDE

Il controller dell’Hard disk è su una scheda separata, fu utilizzato lo standard IDE in grado di gestire dischi con una capacità fino a 504 MB, 4 MB/s.
Il SO leggeva e scriveva dati sul disco inserendo parametri nei registri della CPU e poi invocando il BIOS. 
Da IDE si passò all’EIDE dove ha uno schema di indirizzamento aggiuntivo denominato LBA. Ai controllori EIDE potevano avere due canali su ciascuno dei quali poteva essere collegato un driver primario e secondario.
Dopo EIDE ci fu ATA-3, poi ATAPI-4 incrementarono la dimensione del connettore e la velocità di trasferimento , ATAPI-5 e ATAPI-6, mentre ATAPI-7 incrementa la dimensione del connettore per aumentare la banda, utilizza un serial ATA per trasferire i bit su un connettore.

### SCSI disk

I controller possono collegare fino a 7 dispositivi, hanno organizzazione simile ai dischi IDE ma nescessitano di diverse interfacce ed hanno velocità più alte

### Dischi a stato solido (SSD)

Dischi basati su memoria flash non volatile, velocità fino a tre volte superiore rispetto HDD.

## CD-ROM

Realizzato in policarbonato poi una striscia sottile di alluminio riflettente. Nel substrato di policarbonato è possibile creare delle depressioni tra aree non incise.
La transizione da un pit a land può rappresentare un valore logico alto o  basso.
I pit sono scritti in modo continuo su una spirale che parte dal buco centrale del disco, per leggere si usa un laser a bassa potenza.
Per memorizzare informazioni esistono due modalità: con correzione errori o senza ECC.
Un settore contiene:
-   un preambolo 
-   la sezione dei dati 
-   il codice ECC
il modo senza ECC è utilizzato per applicazioni audio/video.

## RAID

Per migliorare le prestazioni dei dischi (anche l’affidabilità) si pensò di adottare la strategia del calcolo parallelo.
L’idea è di far vedere al calcolatore il sistema RAID (insieme di dischi) come un unico enorme disco virtuale con elevata performance e affidabilità.
Esistono 5 schemi RAID:
- **RAID Livello 0**: i dati sono suddivisi in strisce di k settori e memorizzati in dischi con modalità round-robin. In questo modo un blocco di dati può essere letto con 4 letture parallele. Lavora meglio con richieste di grandi dimensioni, lavora male se il SO richiede i dati solo ad un settore alla volta; in caso di rottura di un disco tutti i dati sono persi.
 
![[AE/img/img3.png|center|500]]

- **RAID Livello 1**: Stesso funzionamento RAID 0 ma dischi duplicati, così ci sono tanti dischi primari quanti di backup. In scrittura ogni striscia è scritta due volte, in lettura possono essere utilizzate tutte le copie, distribuendo il carico su più dischi. Se un disco si rompe, si può utilizzare una replica, il ripristino di un disco rotto è semplice poiché c’è una copia del disco.

![[AE/img/img4.png|center|500]]

- **RAID Livello 2**: Utilizza parole binarie, o byte, per decomporre le informazioni sui dischi. Con 7 dischi è possibile dividere un bit per disco: i byte si dividono in 4 bit e si aggiungono 3 bit ottenendo così un codice a correzione di errore di hamming. Se si rompe un disco si può ricostruire facilmente. Il throughput è alto poiché le operazioni sono in parallelo. La rotazione dei dischi sia sincronizzata e che si utilizzino un numero elevato di dischi. Carico di lavoro del controller per calcolare hamming è elevato;

![[AE/img/img5.png|center|500]]

- **RAID Livello 3**: Versione semplificata del RAID 2, un singolo bit di parità è scritto in un disco di parità separato per ogni parola. Tutti i dischi devono essere sincronizzati. Non offre garanzie di affidabilità su errori casuali perché un solo bit non è sufficiente, se si rompe un disco è possibile ricostruire il suo contenuto.

![[AE/img/img6.png|center|500]]

- **RAID Livello 4/5**: I RAID 4  e 5 lavorano su strisce e non richiedono dischi sincronizzati. è come il RAID 0, con una parità striscia per striscia scritta su un disco separato di parità.  Si esegue l’EXOR bit a bit di tutte le strisce ottenendo così una striscia di parità.

![[AE/img/img7.png|center|500]]

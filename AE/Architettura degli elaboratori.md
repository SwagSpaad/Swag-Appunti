# Capitolo 1 
## Introduzione
---

### Differenza tra traduzione e interpretazione

Un metodo per eseguire un programma scritto in L1 consiste nel sostituire, in una fase iniziale, ogni sua istruzione con un'equivalente sequenza di istruzioni in L0. Il programma che ne risulta è costituito interamente da istruzioni di L0 e può essere eseguito dal computer al posto del programma L1 originale. Questa tecnica è chiamata **traduzione**. 
L'altra tecnica consiste invece nello scrivere un programma in L0 che accetta come dati d'ingresso programmi in L1; tale programma li esegue esaminando un'istruzione alla volta e sostituendola direttamente con l'equivalente sequenza di istruzioni L0. Questa tecnica, che non richiede la generazione preventiva di un nuovo programma L0, è chiamata **interpretazione** e il programma che la esegue è detto **interprete**.

- La traduzione: tradurre tutto il programma al linguaggio inferiore e poi viene eseguito. 
- L'interpretazione: traduce ed esegue ogni singola istruzione del programma. Legge ed esegue il codice sorgente del programma senza creare un file oggetto eseguibile. E' più lenta rispetto alla compilazione.

### Differenza tra compilazione ed interpretazione

La compilazione e l'interpretazione sono due metodi per eseguire un programma informatico. La compilazione, eseguita dal compilatore, traduce tutte le istruzioni di un programma in linguaggio macchina, creando un file eseguibile dal computer.
L'interpretazione traduce ed esegue ogni singola istruzione del programma. Legge ed esegue il codice sorgente del programma senza creare un file oggetto eseguibile. E' più lenta rispetto alla compilazione.

L'esecuzione di un programma compilato è molto più veloce rispetto a un programma interpretato. Il motivo è molto semplice, la compilazione crea un file eseguibile ( oggetto ) in linguaggio macchina ed è immediatamente eseguibile dal processore.
Viceversa, l'interpretazione legge un'istruzione alla volta dal codice sorgente del programma ( sorgente ) e la traduce in linguaggio macchina per farla eseguire dal processore.
Dopo aver eseguito un'istruzione, il software interprete passa ad elaborare la successiva e non resta traccia della precedente. Tutti questi passaggi si ripetono ogni volta che eseguo il programma. Per questa ragione l'esecuzione di un programma interpretato è più lenta.

![[AE/img/img0.png|center|400]]


### Livelli di un computer

![[AE/img/img1.png|center|400]]

# Capitolo 2 
## Organizzazione dei sistemi di calcolo
---
### CPU

Una tipica CPU di Von Neumann è formata da registri (da 1 a 32), dalla ALU e da alcuni bus che connettono tra loro diverse parti. I registri alimentano due registri di input della ALU che mantengono i dati d’ingresso della ALU. La ALU esegue operazioni di somma e sottrazione, e il risultato viene memorizzato in un apposito registro di input, successivamente può essere immagazzinato in uno dei registri della CPU e volendo copiato in memoria.
Le operazioni sono divise in: 
- istruzioni registro-memoria: prelevano parole in memoria e vengono portate all'interno dei registri, dove vengono utilizzate dalla ALU per operazioni successive
- istruzioni registro-registro: preleva due operandi dai registri e li porta dentro dei registri di input della ALU e ne memorizza il risultato in uno dei registri. Il processo che porta i due operandi attraverso la ALU e memorizza il risultato è chiamato **ciclo del percorso dati** ed è il cuore della CPU.
#### Esecuzione dell'istruzione

La CPU esegue ogni istruzione compiendo una serie di passi:
1.  p releva la successiva istruzione dalla memoria per portarla nell’IR;
2.  modifica il PC per farlo puntare all’istruzione seguente;
3.  determina il tipo di istruzione;
4.  se l’istruzione usa una parola in memoria, determina dove si trova;
5.  se necessario, preleva la parola per portarla in un registro della CPU;
6.  esegue l’istruzione;
7.  torna al punto 1 per l’istruzione successiva.
Questa sequenza di passi è detta ciclo di prelievo-decodifica-esecuzione (fetch-decode-execute). 

### CISC e RISC

La **CISC** (Complex istruction set computer) è formata da un set di istruzioni in grado di eseguire operazioni molto più complesse rispetto all’architettura **RISC**. E’ infatti possibile utilizzare una sola istruzione per leggere un dato in memoria, modificarlo e salvarlo nuovamente, cosa impensabile con un set di istruzioni ridotto. 
Sebbene questo tipo di architettura non abbia la velocità di quella **RISC**, ha il vantaggio di poter realizzare programmi più compatti che occupano un minor spazio in memoria. I programmi in questione sono però più “pesanti“, e richiedono molto tempo per l’esecuzione. Un singolo set di istruzioni è eseguito in più passaggi; ogni set di istruzioni ha oltre 300 istruzioni separate. Le istruzioni vengono completate in 2-10 cicli macchina.
La **RISC** è semplice e lineare, permettendo al microprocessore di eseguire il set di istruzioni in tempi molto rapidi e inferiori ai tempi dell'architettura CISC. Esiste un insieme di principi di progettazione, chiamati **principi di progettazione RISC**, che i progettisti delle CPU cercano di seguire il più possibile:
-   Tutte le istruzioni sono eseguite direttamente dall’hardware;
-   Massimizzare la frequenza di emissione delle istruzioni;
-   Le istruzioni devono essere facili da decodificare;
-   Solo le istruzioni Load e Store fanno riferimento alla memoria;
-   Molti registri disponibili.

![[AE/img/img2.png|center|500]]  

### Dischi magnetici

Ogni traccia è divisa in settori di lunghezza fissata. Tra due settori è presente un piccolo spazio detto **intersector gap**.
Le tracce interne hanno maggiore densità di memorizzazione rispetto alle esterne. Ogni drive ha una scheda dedicata (può contenere una CPU) chiamato controllore del disco, oltre a pilotare accetta i comandi software, corregge i dati.

### Hard disk

Sono dispositivi di memorizzazione dati che possono essere collegati internamente o esternamente al calcolatore. 

#### Hard disk magnetici 

Costituito da una pila di dischi che ruotano intorno all’asse, l’insieme di tracce è detto cilindro.
Le performance dell’HD dipendono da:
-   tempo medio di seek;
-   latenza rotazionale;
-   tempo di trasferimento.

Il posizionamento della testina nel raggio della traccia ricercata è detto tempo medio di seek.
La latenza rotazionale è il tempo necessario al disco per posizionare il corretto settore sotto la testina e dipende dalla velocità angolare del disco.
Il tempo di trasferimento dipende dalla densità lineare e dalla velocità di rotazione.

#### Dischi IDE

Il controller dell’Hard disk è su una scheda separata, fu utilizzato lo standard IDE in grado di gestire dischi con una capacità fino a 504 MB, 4 MB/s.
Il SO leggeva e scriveva dati sul disco inserendo parametri nei registri della CPU e poi invocando il BIOS. 
Da IDE si passò all’EIDE dove ha uno schema di indirizzamento aggiuntivo denominato LBA. Ai controllori EIDE potevano avere due canali su ciascuno dei quali poteva essere collegato un driver primario e secondario.
Dopo EIDE ci fu ATA-3, poi ATAPI-4 incrementarono la dimensione del connettore e la velocità di trasferimento , ATAPI-5 e ATAPI-6, mentre ATAPI-7 incrementa la dimensione del connettore per aumentare la banda, utilizza un serial ATA per trasferire i bit su un connettore.

#### SCSI disk

I controller possono collegare fino a 7 dispositivi, hanno organizzazione simile ai dischi IDE ma nescessitano di diverse interfacce ed hanno velocità più alte

#### Dischi a stato solido (SSD)

Dischi basati su memoria flash non volatile, velocità fino a tre volte superiore rispetto HDD.

### CD-ROM

Realizzato in policarbonato poi una striscia sottile di alluminio riflettente. Nel substrato di policarbonato è possibile creare delle depressioni tra aree non incise.
La transizione da un pit a land può rappresentare un valore logico alto o  basso.
I pit sono scritti in modo continuo su una spirale che parte dal buco centrale del disco, per leggere si usa un laser a bassa potenza.
Per memorizzare informazioni esistono due modalità: con correzione errori o senza ECC.
Un settore contiene:
-   un preambolo 
-   la sezione dei dati 
-   il codice ECC
il modo senza ECC è utilizzato per applicazioni audio/video.

### RAID

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

# Capitolo 3
## Livello logico digitale
---
La base hardware di tutti i calcolatori è costituita da piccoli dispositivi elettronici dette porte logiche, ciascune delle quali calcola una diversa funzione di questi segnali.

### Negazione NOT
![[AE/img/img8.png|center|150]]

Restituisce il valore inverso del valore in entrata (1 restituisce 0 e 0 restituisce 1)
### Congiunzione logica AND
![[AE/img/img9.png|center|150]]

Il risultato è 1 se tutti gli operatori sono 1, altrimenti 0
Proprietà: 
- idempotenza: $A \land A = A$  
- commutativa: $A \land B = B \land A$
- associativa: $A \land (B\land C) = (A\land B)\land C$ 
- elemento neutro: $1 \land A = A$
- elemento assorbente: $0 \land A = 0$ 
- complementi: $\bar A \land A = 0$ 
### Disgiunzione logica OR
![[AE/img/img10.png|center|150]]  

Il risultato è 1 se e solo se almeno uno degli operandi è 1
Proprietà:
- idempotenza: $A \lor A = A$  
- commutativa: $A \lor B = B \lor A$
- associativa: $A \lor (B\lor C) = (A\lor B)\lor C$ 
- elemento neutro: $0 \lor A = A$
- elemento assorbente: $1 \lor A = 0$ 
- complementi: $\bar A \lor A = 1$ 
### Disgiunzione logica esclusiva XOR e EXOR

![[AE/img/img11.png|center|150]]  

Il risultato è 1 se e solo se un numero dispari di operandi vale 1
>$$A \oplus B = (\bar A\land B) \: \lor (A\land\bar B)  $$

### Proprietà logiche
#### Distributiva di AND rispetto OR
$$A\land(B\lor C)= (A\land B)\lor(A\land C)$$
#### Distributiva OR rispetto AND
$$A\lor(B\land C)= (A\lor B)\land(A\lor C)$$
#### Primo teorema dell'assorbimento
$$A\land(A\lor B)= A$$
#### Secondo teorema dell'assorbimento
$$A\land(\bar A\lor B)= A \land B$$
#### Prima legge di de Morgan
$$\overline{(A\land B)}= \bar A\lor\bar B$$
#### Seconda legge di de Morgan
$$\overline{(A\lor B)}= \bar A\land\bar B$$
### Multiplexer

Un multiplexer è un circuito con $2^n$ dati di input, un valore di output ed n input di controllo. Gli input selezionano la linea di ingresso che verrà trasferita in uscita. 

![[AE/img/img12.png|center|300]]

Le linee di controllo $S_0,\: S_1,\: S_2$ codificano un numero a 3 bit che specifica quale delle $I_0,\: I_1,...,\:I_7$ linee di input deve essere instradata verso la porta OR e quindi verso l'output. Indipendentemente dal valore definito dalle linee di controllo, le 7 rimanenti porte AND genereranno il valore 0, mentre quella rimanente produrrà in output 0 oppure 1.

>**Oss.**
>A partire dalla forma normale disgiuntiva, è possibile costruire un qualsiasi circuito utilizzando solamente porte AND, OR e NOT. Di conseguenza il MUX può essere utilizzata per realizzare qualsiasi funzione logica.

### Decoder

Un decodificatore accetta come input un numero ad n bit e lo utilizza per impostare ad 1 una sola delle $2^n$ linee di output.

![[AE/img/img13.png|center|350]]

Quando si fornisce alla memoria un indirizzo, si utilizzano i suoi 3 bit più significativi per selezionare uno degli otto chip di memoria ($U_0,\: U_1,...,\: U_7$). I 3 bit corrispondono ai tre input A, B, C; a seconda del loro valore 1 solo degli 8 chip assumerà il valore 1, mentre tutti gli altri rimangono a 0.

### PLA (Array logico programmabile)

Chip generale che permette di calcolare somme di prodotti. Nella figura la porta OR $F_1$ genera come risultato $\bar WYZ+WX\bar Z$ 

![[AE/img/img14.png|center|350]]

>**Oss.**
>A partire dalla forma normale disgiuntiva, come per il multiplexer, la PLA può essere realizzata utilizzando porte AND, OR, NOT e può essere utilizzata per implementare qualsiasi funzione.

### Circuiti aritmetici
#### Shifter

![[AE/img/img15.png|center|500]]

Lo shifter in figura è un circuito con 8 input ($D_0,\: D_1,...,\:D_7$) e 8 output ($S_0,\: S_1,...,\:S_7$). I bit in output sono la copia di quelli in input traslati tutti di una posizione a destra o a sinistra. La direzione (destra/sinistra) è impostata da un bit di controllo C (0 = sx, 1 = dx).

#### Sommatori
Per un calcolatore, la somma tra bit è un operazione fondamentale. Per realizzare un sommatore ad n bit, vengono utilizzati n [[#Full-adder|sommatori]] (full-adder a 1 bit), realizzati a loro volta partendo da [[#Half-adder|semi-sommatori]] (half-adder a 1 bit).    
##### Half-adder

![[AE/img/img16.png|center|500]]

L'half-adder funziona solo per i bit meno significativi, perché non gestisce un riporto in ingresso.
##### Full-adder

![[AE/img/img17.png|center|500]] 

A partire da due half-adder, si può costruire un sommatore che prende in ingresso tre bit (A, B e riporto in ingresso). 
##### Clock

Nei circuiti digitali complessi è necessario stabilire l'ordine con cui si verificano gli eventi. Un clock è un circuito che emette degli impulsi di larghezza definita ad intervalli di tempo costanti. L'intervallo di tempo compreso tra le estremità di due impulsi è detto **tempo di ciclo di clock**.

![[AE/img/img18.png|center|450]]

La frequenza di clock specifica il numero di cicli di clock per unità di tempo (secondo). L'unità di misura è dell'ordine degli Hertz (Hz).
#### Memorie
La memoria è un componente fondamentale in un computer perché memorizza dati ed istruzioni. Per costruire un circuito che memorizza dati è necessario utilizzare circuiti sequenziali in cui l'output non dipende solamente dall'input, ma anche dallo stato del circuito.
##### Latch
Per creare una memoria a 1 bit è necessario un circuito che ricordi i precedenti valori in input. Un circuito semplice può essere realizzato con due NOR che hanno le uscite retroazionate in ingresso (dipendono dai valori dei precedenti ingressi). Il set-reset latch (**SR Latch**) ha due ingressi: S (set) è utilizzato per impostare ad 1 il valore dell'uscita e R (reset) è usato per azzerarlo. 

![[AE/img/img19.png|center|450]]

Il cambio di stato è provocato da un segnale/impulso; a seguito dell'impulso il circuito si mantiene nello stato raggiunto a meno che non vengano inviati ulteriori segnali/impulsi.
Quando S è impostato temporaneamente ad 1, lo stato del latch diventa Q = 1, indipendentemente dallo stato precedente. Quando si imposta R temporaneamente ad 1, si forza il latch a passare nello stato Q = 0.
##### SR Latch temporizzato

![[AE/img/img20.png|center|450]]

È un SR latch in cui i segnali agiscono solo quando il segnale di clock è attivo. Quando il clock vale 0, le porte AND generano in output il valore 0, indipendentemente dai valori di S ed R, impedendo al latch di cambiare stato. Quando il clock vale 1, le porte AND non bloccano i segnali di S ed R che tornano a pilotare lo stato del latch.
Ma cosa succede nel caso in cui S = R = 1? Come cambiano gli stati? In questa circostanza il circuito è in uno stato instabile. Vediamo come risolvere questo problema.
##### D latch temporizzato
Per risolvere l'ambiguità del latch SR, viene utilizzato un solo ingresso dati D. 
- Quando D = 1 e il clock = 1, il latch va nello stato Q = 1
- Quando D = 0 e il clock = 1, il latch va nello stato Q = 0

![[AE/img/img21.png|center|450]]

##### Flip-Flop
Il flip-flop è un circuito simile ad un latch, l'unica differenza è relativa all'istante in cui il segnale di clock determina il cambiamento di stato:
- Nel latch il cambiamento di stato è determinato dal livello alto/basso del clock
- Nel flip-flop è determinato dal fronte salita/discesa del clock

![[AE/img/img22.png|center|450]]

Molti latch hanno due ingressi aggiuntivi PRESET (per forzare lo stato Q = 1) e CLEAR (per forzare lo stato Q = 0).
Un flip-flop D è dotato di un generatore di impulsi che funge da clock nei fronti di salita e discesa dovuti ai ritardi fisici del processamento del segnale passante per l'AND.

![[AE/img/img23.png|center|450]]

Per realizzare un registro ad 1 byte possono essere utilizzati 8 flip-flop come mostrato nella figura sottostante

![[AE/img/img24.png|center|450]]

- Gli 8 flip-flop hanno l'input di clock collegati tra loro e sono pilotati da una porta NOT (pin 11).
- Input (D) e Output (Q) sono separati tra loro.
- Per la scrittura è sufficiente prendere i dati sui PIN D e inviare un impulso di clock.
- Il byte memorizzato è sempre disponibile sui PIN Q.

### Bus
Un bus è un collegamento che unisce i vari dispositivi di un computer. Possono essere interni alla CPU (connette i registri della ALU) o esterni (memoria o periferiche di I/O). 
Alcuni dispositivi sono **attivi** (master) perché possono iniziare un trasferimento dati sul bus, altri **passivi** (slave) perché restano in attesa di una richiesta di un master.
Spesso i segnali digitali generati dalle periferiche sono troppo deboli per alimentare un bus, soprattutto se questo è lungo o collegato a molti dispositivi. Per risolvere questo problema molti master sono connessi al bus mediante un chip detto **bus-driver**, mentre gli slave utilizzano un chip detto **bus-receiver**. Per le periferiche che svolgono sia il ruolo di master che di slave si utilizzano chip detti **trasmettitore-ricevitore del bus**. Questi chip sono dispositivi a tre stati per permettere loro di essere sconnessi quando non necessari. Un'alternativa consiste nell'agganciare la periferica al bus tramite un collettore aperto; quando due o più dispositivi accedono la linea nello stesso istante, il risultato è un OR di tutti i segnali (wired-OR).

Se un bus ha n linee di indirizzi, la CPU può indirizzare almeno $2^n$ locazioni di memoria diverse. L'incremento della dimensione del bus indirizzi permette di indirizzare maggiori spazi di memoria, ma questo comporta un incremento di costi, larghezza del bus, fili e occupazione di spazio.
Per incrementare la larghezza di banda di un bus si può:
- ridurre il periodo di clock del bus, con il rischio, però, di:
	- avere dispositivi che operano a velocità differenti
	- perdere retrocompatibilità
- aggiungere nuove linee di dati; per non avere bus troppo ampi si utilizza la tecnica del bus multiplexato, ma questo porta un rallentamento del sistema.

#### Bus sincroni
Utilizzano un clock che determina la temporizzazione delle attività sul bus. Ogni operazione richiede un numero di periodi di clock per essere eseguita.
1. La CPU master pone l'indirizzo di memoria sull'address bus in modo che le linee si stabilizzino;
2. la CPU comunica al sistema che l'operazione che intende fare con la memoria;
3. la CPU comunica che si tratta di un'operazione in lettura, così la memoria slave deve fornire il contenuto della cella indirizzata dall'address bus;
4. poiche la memoria è meno veloce della CPU, c'è maggiore stato di attesa

#### Bus asincroni
Il bus si adatta alla velocità del dispositivo collegato ed un dispositivo lento non rallenterà il sistema. La maggior parte dei bus sono sincroni perché più semplici da realizzare.

#### Arbitraggio del bus
Ciascun dispositivo intelligente del computer (CPU, coprocessori, ecc.) possono diventare a turno master del bus. L'arbitraggio del bus è utilizzato per prevenire situazioni di conflitto in cui due o più dispositivi tentano di diventare master. Questo meccanismo può essere **centralizzato** o **decentralizzato**.

Il meccansimo centralizzato necessita di un arbitro, che quando riceve una richiesta di utilizzo del bus, la concede garantendo una linea di connessione del bus. Quando il dispositivo più vicino vede la connessione: ^5c5be8
- se lo ha richiesto lui, blocca la linea negandola a tutti
- altrimenti mantiene libera la linea
Quando due o più dispositivi fanno richiesta, la ottiene il più vicino all'arbitro.
Molti bus per aggirare il fatto che le priorità sono legate alla distanza dall'arbitro, definiscono diversi livelli di priorità, utilizzando diverse linee di richiesta-concessione ed il bus viene concesso al dispositivo con priorità maggiore; a parità di priorità vince chi è più vicino all'arbitro.

![[AE/img/img25.png|center]]

Nel meccanismo decentralizzato, ogni dispositivo ha una propria linea di richiesta ed una priorità, prima di inviare una richiesta ciascuno deve verificare che non ci sia già una richiesta con priorità più alta. Al termine dell'utilizzo del bus, la linea deve essere negata. Lo svantaggio è che ci sono troppi collegamenti e il numero di collegamenti non può superare il numero di linee.
Lo schema utilizza tre linee:
- La linea di richiesta del bus;
- La linea BUSY asserita dal dispositivo bus master corrente;
- La linea di arbitraggio propagata tra i dispositivi in cascata.
Per ottenere un bus un dispositivo deve:
- controllare che BUSY sia negata e che l'ingresso IN sia asserito;
- cosi nega OUT, asserisce la linea BUSY e diventa il master del bus.
al termine sblocca OUT e libera BUSY negandolo.

![[AE/img/img26.png|center]]

### Dispositivi di I/O
I dispositivi esterni dovrebbero rappresentare/raccogliere informazioni in una forma comprensibile agli esseri umani.
Le periferiche esterne possono essere divise in tre categorie: input, output o entrambi.
Le periferiche di input permettono di inserire dati all'interno del computer (tastiera, mouse);
quelli di output permettono di fornire dati dal computer (monitor, stampante);
quelli di I/O permettono di inserire ed estrarre dati dal computer (modem, hard disk).

# Capitolo 4
## Livello di microarchitettura

Il livello della microarchitettura **descrive il funzionamento interno di una CPU**, e in particolare come le istruzioni ISA (Instruction Set Architecture) vengono interpretate ed eseguite dall'hardware (livello della logica digitale) che costituisce la CPU.

### Percorso dati
Il percorso dati è quella parti della CPU che contiene la ALU, i suoi input ed output. Il data path contiene registri a 32 bit che controllano l'accesso in memoria. Inoltre l percorso dati si compone di due bus: B e C (l'operando A è quello contenuto nel registor H).
La maggior parte dei registri può inviare il proprio contenuto sul bus B, collegato in input alla ALU. Alla base dell'ALU troviamo lo shifter, il quale invia il proprio risultato al bus C. 

I registri del data path sono i seguenti (in **grassetto** i più importanti):
- **Memory address register (MAR)**
- **Memory data register (MDR)**
- **Program counter (PC)**
- Memory byte register (MBR), è un byte nello stream di istruzioni che provengono dalla memoria.
- **Stack pointer (SP)**
- Local variable (LV), è il riferimento nello stack alla base delle variabili locali
Gli altri registri presenti sono:
- Constant pool (CPP)
- Top word on the stack (TOS)
- Op code register (OPC), è il registro temporaneo, può contenere l'ultima istruzione eseguita prima di un salto e identifica il tipo di istruzione indicando anche se è di tipo ADD o BRANCH
- Holding (H)

![[AE/img/img27.png|center|450]]

### ALU 
![[AE/img/img28.png|center|500]]

Il settore in basso a sinistra contiene un decodificatore a 2 bit che, in base ai segnali $F_0$ e $F_1$, permette di attivare una delle quattro linee per le 4 operazioni: $AB$ (AND), $A+B$ (OR), $\bar B$ (NOT), $A + B$ (somma aritmetica). 
Il settore in alto contiene le porte logiche per calcolare A AND B, A OR B e $\bar B$; in base alle linea attivata dal decodificatore solo uno di questi risultati viene passato attraverso la porta OR. 
Gli input ENA e ENB possono essere utilizzati per forzare a 0 gli input A e B, mentre è possibile avere come input $\bar A$ al posto di $A$ impostando ad 1 l'input INVA (inverti A). 
Il settore in basso a destra è un [[#Full-adder|full-adder]].

Le ALU ad 1 bit possono essere assemblate insieme per costruire un ALU di lunghezza variabile. Questa tecnica è detta bit-slice e può essere applicata ad altri circuiti digitali che lavorano bit a bit.

![[AE/img/img29.png|center|500]]

Il segnale INC è utilizzato per incrementare il risultato, ovvero per calcolare somme come A+1, A+B+1.

### Temporizzazione del percorso dati

![[AE/img/img30.png|center|550]]

Ogni nuovo ciclo ha inizio sul fronte di discesca del clock, momento in cui vengono memorizzati i bit che controllano il funzionamento delle porte. Questa operazione richiede un intervallo di tempo finito (w in figura). Il registro richiesto viene selezionato e il suo contenuto portato sul bus B. Dopo un intervallo di tempo x. A questo punto, la ALU e lo shifter possono iniziare ad operare sui suoi dati e i loro output diventano stabili dopo un intervallo di tempo y. Dopo un ulteriore intervallo di tempo z, i risultati vengono propagati lungo il bus C e caricati nei registri in corrispondenza del fronte di salita del successivo impulso di clock.

### Operazioni della memoria
La CPU ha due modi per comunicare con la memoria:
- Una porta da 32 bit per la lettura/scrittura dei dati del livello ISA. La porta viene controllata da due registri:
	- MAR (Memory Address Register): specifica l'indirizzo di memoria in cui si desidera leggere o scrivere una parola
	- MDR (Memory Data Register): ospita la parola (32 bit) che sarà letta o scritta all'indirizzo di memoria specificato da MAR
- Una porta a 8 bit per leggere (solo lettura!) il programma eseguibile (fetch delle istruzioni ISA). Anche questa porta è controllata da due registri:
	- PC (Program Counter): registro a 32 bit che indica l'indirizzo di memoria della prossima istruzione ISA da caricare (fetch)
	- MBR (Memory Byte Register): contiene il byte letto dalla memoria durante il fetch. È un registro a 32 bit, pertanto il byte letto viene memorizzato negli 8 bit meno significativi
In generale tutti i registri della CPU vengono controllati da uno o due segnali di controllo. In [[AE/img/img27.png|figura]] la freccia vuota indica l'output del registro sul bus B, mentre la freccia piena indica il caricamento dal bus C. 
MBR può essere scritto sul bus B in due modi diversi: 
- unsigned: i 24 bit non utilizzati vengono impostati a 0 (valore MBR tra 0 e 255)
- signed: il bit di segno (il settimo), viene copiato su tutti i 24 bit non utilizzati (valori tra -128 e +127)

Le due porte MAR/MDR e PC/MBR indirizzano la memoria a parole (32 bit) e a byte (8 bit) rispettivamente.

### Microistruzioni
Per controllare il data path abbiamo bisogno di 29 segnali suddivisibili in 5 gruppi funzionali:
- 9 segnali (frecce piene) per controllare la scrittura dei dati dal bus C all'interno dei registri 
- 9 segnali (frecce vuote) per controllare l'abilitazione dei registri del bus B per l'input della ALU
- 8 segnali per controllare le funzioni della ALU e dello shifter
- 2 segnali (non mostrati in [[AE/img/img27.png|figura]]) per indicare lettura/scrittura della memoria tramite PC/MBR
- 1 segnale (non mostrato in [[AE/img/img27.png|figura]]) per il fetch (lettura) delle istruzioni dalla memoria tramite PC/MBR
I valori di questi 29 segnali specificano il comportamento della CPU da eseguire durante un ciclo del percorso dati.
La sequenza di cicli di data path necessari all'esecuzione di un istruzione ISA prende il nome di **microprogramma** di quell'istruzione ISA. Un microprogramma è costituito di **microistruzioni**. 

I 29 segnali di controllo non sono sufficienti a specificare una microistruzione; infatti, è necessario specificare anche cosa fare nel ciclo seguente. Una microistruzione è una sequenza di bit, composta da 3 parti:
- Control: stato dei segnali di controllo
	- ALU: seleziona le funzioni dell'ALU e dello shifter 
	- C: seleziona quali registri sono scritti dal bus C
	- Mem: seleziona la funzione in memoria
	- B: seleziona quale registro è scritto sul bus B
- Address: indirizzo della prossima microistruzione da eseguire
- JAM: bit per la gestione dei salit incondizionati a seconda dei bit di stato (N, Z) dell'ALU

![[AE/img/img31.png|center|500]]

### Microarchitettura MIC-1

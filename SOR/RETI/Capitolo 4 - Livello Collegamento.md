Mentre il livello di rete si occupa dell'instradamento dei pacchetti lungo tutto il percorso end-to-end, il **livello di collegamento** si occupa di trasferire i datagrammi da un nodo (indicheremo con questo termine sia gli *host* che i *router*) a quello fisicamente adiacente lungo il collegamento.

![[SOR/RETI/img/img100.png|center|500]]

Nella figura vediamo come un host wireless voglia inviare un datagramma a uno dei server. Per effettuare questa operazione il datagramma deve attraversare sei collegamenti diversificati tra WiFi (primo link) ed Ethernet. 
Per ogni collegamento, il nodo trasmittente incapsula il datagramma in un **frame del livello di collegamento**.

# Servizi del livello di collegamento
Il servizio base del livello di collegamento è quello del trasporto di datagrammi tra nodi adiacenti lungo un singolo canale di comunicazione. Inoltre i vari protocolli a livello di collegamento possono anche offrire diversi servizi:
- **framing**: la maggioranza dei protocolli incapsulano i datagrammi all'interno di frame prima di trasmetterli. I frame sono costituiti da un campo dati e un'intestazione.
- **accesso al collegamento**: un protocollo che controlla l'accesso al mezzo trasmissivo (protocollo di tipo **MAC, Medium Access Control**) specifica le regole con cui immettere i frame nel collegamento.
- **consegna affidabile**: questo servizio è spesso *utilizzato per i collegamenti soggetti a elevati tassi di errore*, ad esempio quelli wireless. L'idea è quello di correggere localmente l'errore, piuttosto che ritrasmettere i dati dalla sorgente alla destinazione. 
- **rilevazione e correzione degli errori**: gli errori nei bit sono causati dall'attenuazione di segnale e dai disturbi elettromagnetici. Per far sì che il trasferimento sia affidabile, è necessario rilevare gli errori, perché non è utile inviare datagrammi con errori. 

# Dov'è implementato il livello di rete
Il livello di rete è implementato in un adattatore di rete, detto **scheda di rete (NIC)**. Il cuore della scheda di rete è il controller a livello di collegamento, un chip dedicato che implementa i servizi visti precedentemente.

![[SOR/RETI/img/img101.png|center|500]]

- Lato **mittente**: il controller prende un datagramma, lo incapsula in un frame aggiungendo i vari campi dell'intestazione (bit di controllo degli errori, trasferimento dati affidabile ecc.) e lo trasmette sul canale di comunicazione.
- Lato **destinatario**: il controller riceve l'intero frame, estrae il datagramma e lo consegna al livello di rete. Se il protocollo del livello di collegamento fornisce la rilevazione degli errori, allora il controller ricevente verifica la presenza degli errori

# Rilevazione e correzione degli errori
In figura vediamo uno scenario di rilevazione e correzione degli errori.

![[SOR/RETI/img/img102.png|center|500]]

Il nodo trasmittente, aggiunge dei bit $ECD$ ai dati $D$, che comprendono i datagrammi, ma anche l'intestazione del protocollo dello stato di collegamento. I dati D e i bit ECD sono inviati in un frame al nodo ricevente, che legge una sequenza $D^{'}$ ed $ECD^{'}$ che può essere diversa dall'originale, a causa di qualche errore di trasmissione. 
Il compito del ricevente è quello di determinare se $D^{'}=D$, potendo contare solo su $D^{'}$ e $ECD^{'}$.

Tuttavia anche con l'uso di ECD, c'è una possibilita che ci siano errori non rilevati, causando un invio di informazioni errate al livello di rete. In genere per limitare le probabilità che accadano questi eventi, ci sono tecniche più sofisticate che necessitano di calcoli più  complessi e la trasmissione di molti bit aggiuntivi.

Vedremo tre tecniche per rilevare errori nei dati trasmessi: 
- controllo di parità, per la base della rilevazione e correzione di errori
- tecniche di checksum, utilizzate nel livello di trasporto
- controllo a ridondanza ciclica, impiegate nelle schede di rete

## Controllo di parità
Questa è la forma più semplice di rilevamento degli errori, che utilizza un singolo **bit di parità**. 
Supponiamo che i dati $D$ da inviare siano costituite da $d$ bit. In uno schema di *parità pari*, si aggiunge un bit addizionale scegliendo il suo valore in modo da rendere pari il numero di bit 1 (includendo anche il bit di parità) nei bit trasmessi.  
In uno schema di *parità dispari* il valore del bit di parità è scelto in modo che ci sia un numero dispari di bit 1.

![[SOR/RETI/img/img103.png|center|500]]

Con un solo bit di parità, il ricevente deve semplicemente contare il numero di bit 1 tra quelli ricevuti. Se trova un numero dispari di bit 1, in uno scenario di parità pari, sa che si è verificato almeno un errore (o meglio un numero *dispari* di errori) in un bit.
Cosa succede se si verifica un numero pari di errori? In questo caso ci troviamo in presenza di un errore non rilevato. 

Se la probabilità di errori nei bit è bassa e si può assumere che gli errori siano indipendenti, allora l'eventualità di errori multipli in un singolo pacchetto è ridotta e un solo bit di parità è sufficiente. Ma nella realtà gli errori tendono a verificarsi a gruppi di bit consecutivi (*burst*) e la probabilità che non vengano rilevati errori a burst in un frame protetto da un solo bit si avvicina al 50%. A questo scopo è necessario adottare una strategia migliore per la rilevazione degli errori. 

![[SOR/RETI/img/img104.png|center|500]]

In figura osserviamo una generalizzazione bidimensionale dello schema di parità con un bit. In questo caso i $d$ bit del dato $D$ sono suddivise in $i$ righe e $j$ colonne, per ognuna delle quali viene calcolato un bit di parità (sia di riga che di colonna). 
I risultanti $i+j+1$ bit di parità contengono i bit per la rilevazione dell'errore del frame del link dati. 

![[SOR/RETI/img/img105.png|center|500]]

Supponiamo che si verifichi un solo errore nei $d$ bit originali (in figura a sinistra). Con questo schema di **parità bidimensionale** i bit di parità della colonna e della riga contenenti il bit errato, individueranno l'errore, permettendo al ricevente, oltre alla *rilevazione dell'errore*, di *identificare il bit alterato e correggerlo*. 

Nonostante questo, lo schema può solamente rilevare (ma non correggere) qualsiasi combinazione di due o più errori. *Il controllo di parità è adatto quando la probabilità di errori nei bit è bassa e gli errori sono indipendenti*

![[SOR/RETI/img/img106.png|center|500]]
![[SOR/RETI/img/img107.png|center|500]]
![[SOR/RETI/img/img108.png|center|500]]

## Checksum
Nelle tecniche che utilizzano il checksum i $d$ bit di dati sono trattati come una sequenza di numeri interi da $k$ bit. 
Il mittente suddivide i dati in sequenze da 16 bit, sommandole tra di loro in complemento a 1, invertendo tutti i bit del risultato finale, ottenendo la checksum, che invio al destinatario insieme al messaggio. Il destinatario esegue nuovamente la somma in complemento a 1, includendo anche la checksum. Se il risultato è costituito da 16 bit a 1, vuol dire che non sono stati rilevati errori, altrimenti si.

Il metodo di checksum fornisce una protezione agli errori deboli rispetto alle tecniche CRC, utilizzate nel livello di collegamento. La motivazione per cui il checksum è utilizzato a livello di trasporto è a causa dell'implementazione lato software che richiede una tecnica di rilevazione di errore semplice e veloce per non intaccare le prestazioni. 
Nello strato di collegamento, la rilevazione dell'errore è implementata direttamente nell'hardware, consentendo di effettuare rapidamente le operazioni per il controllo dell'errore.

## CRC, Controllo a Ridondanza Ciclica
Una tecnica largamente utilizzata è basata sui codici di controllo a ridondanza ciclica, anche detti codici polinomiali, in quanto la stringa dei bit può essere vista come un polinomio con coefficienti 0 e 1.

Per capire il funzionamento dei codici CRC consideriamo $d$ bit che costituiscono i dati $D$ da trasmettere, e che sorgente e destinatario si siano accordate su una stringa di $r+1$ bit, detta **generatore**, che indichiamo con $G$, in cui *è necessario che il bit più significativo sia 1*.

![[SOR/RETI/img/img109.png|center|500]]

Dato $D$, il mittente sceglie $r$ bit, detti $R$, e li concatena a $D$, ottenendo una stringa di lunghezza $d+r$ che sia divisibile esattamente per $G$ interpretato come numero. Il ricevente, per controllare la presenza di errori, effettua la divisione $(d+r)/G$ che *se ha resto diverso da 0 significa che si è verificato un errore*.

I calcoli di CRC sono eseguiti senza riporti o prestiti nelle addizioni e sottrazioni, rendendole praticamente operazioni identiche e che entrambe equivalgono allo XOR sui bit. Le moltiplicazioni e le divisioni sono eseguite in base 2, ma anche qui, le addizioni e le sottrazioni non hanno riporti. 

Dobbiamo capire come il trasmittente calcoli $R$, ricordando che dobbamo calcolarlo in modo che $D\times2^{r} \text{ XOR }R$ sia perferramente divisibile per $G$ (quindi deve essere G stesso o un suo multiplo) $$D\times2^{r} \text{ XOR }R=nG$$
Se eseguiamo lo XOR di R con entrambi i membri dell'espressione sopra otteniamo $$\begin{align*}
&D\times2^{r} \text{ XOR }R\text{ XOR }R=nG\text{ XOR }R=\\
&=D\times2^{r}\text{ XOR }(R\text{ XOR }R)=nG\text{ XOR }R=\\
&= D\times2^{r}\text{ XOR }0=nG\text{ XOR }R=\\
&= D\times2^{r}=nG\text{ XOR }R\\
\end{align*}$$
L'ultima uguaglianza ci dice che se dividiamo $D\times 2^R$ per $G$, il valore del resto è precisamente $R$, quindi possiamo calcolare $R$ come $$R=\text{resto di }\bigg(\frac{D\times2^{r}}{G}\bigg)$$

>**Es.**
>Vediamo il calcolo per $D=101110$, $d=6$, $G=1001$, $r=3$. I bit trasmessi in questo caso sono $\underbrace{101110}_\text{D}\:\underbrace{011}_\text{R}$.
>Il mittente calcola quindi $R$ prendendo il resto della divisione di $\frac{D\times2^{3}}{G}$, che vediamo in figura 
>![[SOR/RETI/img/img110.png|center|300]]

CRC può rilevare errori a burst inferiori a $r+1$ bit, quindi saranno rilevati gli errori con un burst di lunghezza $\leq r$. 

# Protocolli di accesso multiplo
Esistono due tipi di collegamento di rete: 
- il **collegamento punto punto**: singolo trasmittente e singolo ricevente alle estremità
- **collegamento broadcast**: si hanno più nodi e ciascun frame viene ricevuto da tutti i nodi
Nel caso di collegamento broadcast, avendo più host trasmettitori, bisogna capire come coordinare l'accesso al canale per evitare il verificarsi di *collisioni*, causando una perdita di frame. Il modo in cui gli host utilizzano il canale condiviso è dettato dai **protocolli di accesso multiplo**.

![[SOR/RETI/img/img111.png|center|500]]

I protocolli di accesso multiplo si suddividono in tre categorie: 
- **protocolli a suddivisione del canale**
- **protocolli ad accesso casuale**
- **protocolli a rotazione**
Idealmente un protocollo ad accesso multiplo per un canale con la velocità di $R$ bps dovrebbe avere le seguenti caratteristiche:
1. Quando un solo nodo deve inviare dati, dispone di un throughput di $R$ bps
2. Quando $M$ nodi devono inviare dati, questi dispongono di un throughput medio di $R/M$ bps
3. Il protocollo è decentralizzato, quindi non ci sono dei nodi principali che se non funzionano bloccherebbero l'intero sistema
4. deve essere semplice ed economico

## Protocolli a suddivisione del canale
Abbiamo già visto nell'introduzione che il [[Capitolo 0 - Introduzione ad Internet#Commutazione di circuito#TDM (Time Division Multiplexing)|multiplexing a divisione di tempo]] e quello a [[Capitolo 0 - Introduzione ad Internet#FDM (Frequency Division Multiplexing)|divisione di frequenza]], sono due tecniche utilizzate per suddividere la larghezza di banda di un canale broadcast.

Supponiamo che il canale broadcast supporti $N$ nodi e che abbia una velocità di $R$ bps. TDM suddivide il tempo in intervalli di tempo e poi divide ciascun intervallo in $N$ **slot temporali** e li assegna ad ogni nodo. Ogni volta che un nodo vuole trasmettere, lo fa durante lo slot di tempo ad esso assegnato. Le dimensioni del frame vengono scelte in modo tale da consentire la trasmessione di un singolo pacchetto. *Inoltre gli slot di tempo inutilizzati restano inutilizzati*. 
Il TDM elimina le collisioni ed ottiene una velocità di trasmissione dedicata di $R/N$ bps durante il frame di tempo, ha però degli svantaggi:
- un nodo, anche se è l'unico ha frame da inviare, trasmette alla velocità di $R/N$ bps.
- un nodo per trasmettere deve attendere il suo turno anche se è l'unico che ha frame da spedire

L'FDM, invece, suddivide il canale indiverse frequenze, assegnandone ognuna a ciascun nodo. FDM, come TDM, evita le collisioni e divide in modo equo la banda tra i nodi, ma anche in questo caso, quando il nodo è l'unico con un pacchetto da spedire, la sua banda è limitata a $R/N$.

## Protocolli ad accesso casuale
Con questa tipologia, quando un nodo ha un pacchetto da inviare, lo trasmette alla massima velocità consentita dal canale, senza alcun coordinamento. Questo significa che i nodi possono trasmettere contemporaneamente, consentendo quindi il *verificarsi di collisioni*, ma questi protocolli sono progettati per **rilevarle** e **recuperare la perdita** mediante le trasmissioni.

### Slotted ALOHA
Assumiamo che:
- tutti i frame sono di $L$ bit
- il tempo è suddiviso in slot di $L/R$ secondi 
- i nodi cominciano la trasmissione dei frame all'inizio degli slot
- i nodi sono sincronizzati in modo che tutti sappiano quando iniziano gli slot
- nel caso in cui in uno slot due o più frame collidono, tutti i nodi della rete rilevano l'evento prima della fine dello slot
Indichiamo con $p$ una probabilità, ovvero un numero tra 0 e 1. ALOHA opera nel seguente modo:
- quando un nodo ha un frame da spedire attende l'inizio del prossimo slot, in cui trasmette
- Se non si verifica una collisione, il nodo può predisporre l'invio di un nuovo frame
- Se si *verifica una collisione* il nodo ritrasmette il frame nello slot successivo con probabilità $p$ finché non ha successo

>**Oss.**
>"Ritrasmettere con probabilità $p$" è analogo a lanciare una moneta con l'evento testa corrispondente a "ritrasmetti" (probabilità $p$) e l'evento croce corrispondente a "salta lo slot e lancia di nuovo la moneta nello slot successivo" (probabilità $1-p$).

![[SOR/RETI/img/img112.png|center|500]]

Questo protocollo ha diversi vantaggi. Innanzitutto con un singolo nodo attivo, questo può *trasmettere continuamente alla massima velocità del canale*, è un protocollo semplice e decentralizzato, in quanto ciascun nodo rileva le collisioni e decide quando ritrasmettere. 
Gli svantaggi risiedono nel fatto che se si verificano le collisioni, si sprecano slot di tempo (vedere in figura) ed il meccanismo di randomizzazione può portare a slot inutilizzati. 

L'efficienza di un protocollo di accesso multiplo che utilizza gli slot temporali è la frazione di slot riusciti in presenza di un numero elevato di nodi attivi che hanno un grande numero di pacchetti da inviare.
L'efficienza massima dello slotted aloha è del 37% il che significa che la reale velocità di trasmissione è $0.37\cdot R$ bps. 

Esiste una variante di Slotted ALOHA, l'**ALOHA puro** che non richiede sincronizzazione. Infatti i nodi trasmettono non appena possono, e dopo la trasmissione, se si è verificata una collisione si ragiona allo stesso modo della versione slotted. Senza sincronizzazione l'efficienza dell'ALOHA puro scende al 18%.

## CSMA: accesso multiplo con rilevamento della portante
Nei protocolli ALOHA i nodi prendono la decisione di trasmettere indipendentemente dall'attività degli altri nodi, infatti i nodi non tengono conto della presenza di un altro nodo in trasmissione e non arrestano la trasmissione se interferisce con quella di un altro nodo. 

Nel caso di protocolli di tipo **CSMA e CSMA/CD** valgono due importanti regole:
- **rilevamento della portante**: consiste nel porre un nodo in ascolto sul canale prima di trasmettere. Se il canale sta trasmettendo, il nodo si mette in attesa.
- **rilevamento della collisione**: il nodo che sta trasmettendo rimane contemporaneamente in ascolto del canale. Se c'è un nodo che inizia il trasferimento interferendo la comunicazione, il nodo in ascolto termina la propria trasmissione, aspetta del tempo e ripete il processo.

Nonostante il rilevamento della portante, nel CSMA le collisioni possono comunque verificarsi. Questo è causato dal ritardo di propagazione dei bit nel canale da parte dei nodi. 

>**Es.**
>Supponiamo che nell'istante $t_{0}$ il nodo B rileva che il canale è inattivo, inizia la sua trasmissioni e i bit si propagano nel mezzo. Per la propagazione dei bit è necessario un intervallo di tempo non nullo. Al tempo $t_{1}>t_{0}$ il nodo D ha un frame pronto da spedire e nonostante B stia ancora trasmettendo, i bit, per il **ritardo di propagazione**, non hanno ancora raggiunto D che quindi può ritenere il canale libero. 
> 
>![[SOR/RETI/img/img113.png|center|300]]

Il verificarsi di collisioni nel CSMA comporta lo spreco dell'intero tempo di trasmissione dei pacchetti. 
### CSMA/CD
Il CSMA/CD riduce la quantità di tempo sprecata nelle collisioni, infatti quando un nodo rileva una collisione, cessa immediatamente la trasmissione. 

Vediamo però prima di tutto le operazioni dal punto di vista di una scheda di rete collegata ad un canale broadcast:
1. La scheda ottiene un datagramma dal livello di rete, prepara un frame a livello di collegamento e lo pone in un buffer
2. Se il canale è libero (rileva che non c'è energia in entrata nella scheda), inizia la trasmissione. Se il canale è occupato allora resta in attesa 
3. Durante la trasmissione verifica la presenza di eventuali segnali provenienti dalle altre schede di rete presenti sul canale (controllo della collisione)
4. Se non rileva energia di segnale allora la trasmissione è stata eseguita con successo. Al contrario interrompe immediatamente la trasmissione del frame
5. Dopo aver annullato la trasmissione, la scheda di rete aspetta un tempo casuale e torna al passo 2
Resta da chiarire qual è il tempo di attesa casuale opportuno da scegliere, detto **tempo di backoff**.
Se il numero di nodi che collidono è piccolo e l'intervallo è grande, allora i nodi rimarranno fermi per troppo tempo prima di ripetere la trasmissione. Se il numero di nodi che collidono è grande e l'intervallo di tempo è piccolo allora la probabilità di collisioni aumenta. 
L'algoritmo di **binary exponential backoff** risolve questo problema. Quano il trasmettitore riceve l'$n-$esima collisione durante la trasmissione, stabilisce casualmente un valore $K$ tra $\{0,1,2,\dots,2^{n}-1\}$. Più è alto il numero di collisioni e maggiore sarà l'intervallo da cui scegliere $K$.

![[SOR/RETI/img/img114.png|center|500]]

## Protocolli a rotazione
Ricordiamo le proprietà ideali di questi protocolli:
1. quando un solo nodo è attivo esso ha un throughput di $R$ bps
2. quando sono attivi $M$ nodi, ciascuno ha un throughput di $R/M$ bps
I protocolli ALOHA e CSMA hanno la prima proprità, ma non la seconda. 
Questo ha portato allo sviluppo dei **protocolli a rotazione**.

### Polling
In questo protocollo è presente un *nodo principale*, che invia ciclicamente un messaggio ad ogni nodo, indicandogli che può trasmettere nel canale. Il nodo principale rileva che un nodo ha terminato di inviare osservando la mancanza di segnale sul canale.  
Il polling elimina le collisioni e gli slot vuoti, ma presenta svariati svantaggi:
- **ritardo di polling**: se c'è un solo nodo attivo, questo trasmetterà ad un tasso inferiore agli $R$ bps, in quanto il nodo principale, quando l'unico nodo attivo ha finito l'invio, deve comunque interrogare tutti gli altri nodi.
- **singolo punto di rottura**
### Token-passing
In questo caso non esiste un *nodo principale*, ma un **token**, ovvero un messaggio di controllo che circola tra i nodi con un ordine prefissato. 
Se il nodo che riceve il token non ha pacchetti da inviare, lo inoltra al nodo successivo, altrimenti trasmette il numero di frame consentito, per poi inoltrare il token. 
Anche questo protocollo non è privo di problemi:
- **singolo punto di rottura**

# LAN 
Le **Local Area Network** sono delle reti che coprono un'area limitata (abitazione, dipartimento ecc.). Le tecnologie principali, che approfondiremo in seguito, sono *Ethernet e Wireless*.

## Indirizzi a livello di collegamento
Host e router, oltre ad un indirizzo IP utile a livello di rete, hanno anche indirizzi a livello di collegamento. Nello specifico sono le interfacce dei nodi a possedere degli indirizzi, detti **indirizzi MAC**.

L'indirizzo MAC è lungo 6 byte ($2^{48}$ indirizzi possibili), espressi in esadecimale. 

>**Oss.**
>Non esistono due schede di rete con stesso indirizzo MAC. Questo è  possibile perché la IEEE sorintende alla gestione degli indirizzi MAC, garantendo questa proprietà.

Gli indirizzi MAC di una scheda di rete non cambia mai, indipendentemente dal luogo in cui il computer è utilizzato (a differenza degli indirizzi IP)

Quando una scheda di rete vuole spedire un frame, inserisce l'indirizzo MAC di destinazione e lo immette nella LAN. A volte gli switch possono effettuare il broadcast di un frame in ingresso, inviandolo a tutte le interfacce in uscita, quindi una scheda di rete può ricevere un frame non indirizzato a lei. Ogni scheda di rete controlla se l'indirizzo MAC di destinazione corrisponde al proprio. In caso affermativo passa il frame a livello superiore, altrimenti lo scarta.

Se la volontà della scheda di rete è quella di inviare il frame in modalità broadcost, inserisce un **indirizzo MAC broadcast** (tutti i 48 bit a 1, quindi FF-FF-FF-FF-FF-FF).

## Protocollo risoluzione degli indirizzi
La domanda che ci poniamo è quella di come determinare l'indirizzo MAC di un interfaccia conoscendo il suo indirizzo IP. Questo è il compito del **protocollo di risoluzione degli indirizzi (ARP)**. 

Ogni nodo salva in RAM una **tabella ARP** in cui ogni record contiene la mappatura IP - MAC e un campo TTL, che indica quando eliminare la voce dalla tabella. 

Supponiamo che il nodo A deve scoprire l'indirizzo MAC di B, che non è presente nella tabella ARP di A. In questo caso il nodo A utilizza ARP per trovare la mappatura, spedendo un **pacchetto ARP** che tra i suoi vari campi comprende anche quelli degli indirizzi IP e MAC di chi spedisce e riceve, inviandolo in broadcast specificando solamente l'indirzzo IP di B. Ogni nodo riceve la richiesta ARP, ma solo quello che ha l'IP corrispondente risponde ad A inviando un frame di risposta col suo indirizzo MAC, che provvederà ad inserire la mappatura nella propria tabella ARP. 

### Inviare un datagramma ad un nodo esterno alla sottorete

![[SOR/RETI/img/img115.png|center|500]]

In figura è illustrata una rete costituita da due sottoreti interconnesse da un router. Osserviamo che R possiede due indirizzi IP sulle sue due interfacce, il primo (111.111.111.110) relativo alla prima sottorete (quella di A) mentre il secondo relativo alla seconda sottorete (quella di B). 

Supponiamo che A vuole inviare un datagramma a B, passando per R. Supponiamo inoltre che A conosca l'indirizzo IP di B e gli indirizzi IP e MAC dell'interfaccia di R nella propria sottorete. 
Per l'invio A crea un datagramma IP con sorgente A e destinazione B, che incapsula in un frame a livello di collegamento che avrà come indirizzo MAC di destinazione, *l'indirizzo MAC dell'interfaccia di R della propria sottorete*, questo perché A, sapendo di appartenere ad una sottorete /24, confronta i 24 bit più significativi del proprio indirizzo con quelli dell'IP di B, capendo che B si trova in una diversa sottorete.
Quando il frame viene ricevuto da R, lo passa a livello di rete e capisce di non essere il destinatario dall'indirizzo IP, determina quindi l'interfaccia di uscita e passa il datagramma a livello di collegamento. R crea quindi il frame contenente il nuovo indirizzo MAC di destinazione, quello di B, inoltrando il frame a quest'ultimo. 

## Ethernet
Ethernet è la tecnologia più diffusa per le reti LAN cablate. Nel corso del tempo ci sono state diverse topologie per l'utilizzo di Ethernet: 
- **struttura a bus**: un cavo coassiale che passava per tutte le schede di rete (bus). Se si rompeva il cavo, tutta la rete crollava
- **topologia a stella con hub**: tutti gli host erano interconnessi da un hub, un dispositivo che rigenera il segnale in entrata nell'interfaccia e lo inoltra a tutte le interfacce diverse dall'interfaccia di entrata. Se un hub riceve dei frame da due diverse interfacce contemporaneamente si verifica una collisione
- **topologia a stella con switch**: l'hub centrale viene sostituito da uno switch, che è privo di collisione.

### Struttura dei frame internet
L'interfaccia trasmittente incapsula il datagramma IP in un frame Ethernet e lo passa a livello fisico. 

![[SOR/RETI/img/img116.png|center|500]]

- **Preambolo**: 8 byte con valori fissi, i primi 7 hanno valore 10101010 mentre l'ottavo ha valore 10101011. I primi 7 byte servono per sincronizzare il clock dell'adattatore ricevente con quello del mittente
- **Indirizzo di destinazione/sorgente**: contiene l'indirizzo MAC della scheda del destinatario/mittente
- **Tipo**: specifica il protocollo dello strato superiore a cui consegnare il campo dati
- **Dati**: contiene il datagramma IP. Ha una lunghezza minima di 46 byte.
- **CRC**: consente alla scheda di rete ricevente di rilevare errori nel frame

Ethernet è un protocollo **senza connessione**, ciò vuol dire che quando una scheda di rete vuole inviare un datagramma ad un host nella rete, non fa altro che incapsularlo in un frame Ethernet e immetterlo nella LAN, *senza handashake con la NIC destinataria*. Inoltre Ethernet non fornisce un **servizio affidabile a livello di rete**, infatti le schede riceventi non inviano ACK o NAK alle schede mittenti; i dati vengono recuperati solo se si utilizza un protocollo trasferimento dati affidabile a livello superiore (es. TCP). 

## Switch a livello di collegamento
Lo switch è un **commutatore di pacchetti a livello di collegamento** ed il suo ruolo è quello di ricevere i frame in ingresso e inoltrarli sui collegamenti in uscita. Inoltre uno switch è invisibile agli host, infatti ogni nodo indirizza un frame ad un altro nodo invece che indirizzarlo allo switch. 

Lo switch ha le funzionalità di **filtraggio e inoltro**. La prima è quella che *determina se un frame debba essere inoltrato su un'interfaccia o scartato*, mentre la seconda consiste nell'*individuare l'interfaccia verso la quale il frame deve essere diretto*.
Queste operazioni sono eseguite mediante una tabella di commutazione composta dalle seguenti voci:
- indirizzo MAC del nodo
- l'interfaccia dello switch rivolta verso il nodo
- time stamp

![[SOR/RETI/img/img117.png|center|500]]

Questa tabella viene costruita automaticamente in **autoapprendimento**, vediamo come.

Quando uno switch riceve un frame, registra l'indirizzo MAC e il numero di collegamento del mittente e cerca nella tabella l'interfaccia di uscita tramite l'indirizzo MAC di destinazione:
- se la destinazione è sull'interfaccia dalla quale è arrivato il frame, questo viene scartato
- altrimenti il frame viene inoltrato sull'interfaccia indicata nella tabella
Se, invece, la voce non viene trovata, allora lo switch esegue il **flood**, ovvero inoltra il frame a tutte le interfacce eccetto quella da cui è arrivato.

### Confronto tra switch e router
**Lavorano entrambi in store-and-forward**:
- router: esamino l'intestazione a livello di rete
- switch: esaminano l'intestazione a livello di collegamento

**Entrambi hanno delle tabelle di inoltro**:
- router: calcolano le tabelle mediante algoritmi di instradamento ed utilizzano gli indirizzi IP
- switch: costruiscono le tabelle con l'autoapprendimento ed il flooding, utilizzando gli indirizzi MAC

**Topologia della rete**:
- router: nonostante la presenza di cicli nella topologia, gli algoritmi di instradamento possono trovare percorsi senza cicli. Il decremento del TTL permette di scartare pacchetti incastrati a causa di errori di configurazione
- switch: devono essere connessi ad albero per evitare che il traffico broadcast resti in circolazione per sempre

**Isolamento del traffico**:
- router: inoltrano i pacchetti in accordo ai percorsi determinati dalla funzione di instradamento
- switch: inviano in broadcast i frame con indirizzo MAC sconosciuto, con un effetto a valanga in caso di molti switch interconnessi.

## VLAN
Una LAN tradizionale presenta vari inconvenienti tra cui: 
- **mancanza di isolamento del traffico**: in una LAN il traffico broadcast deve attraversare tutta la rete, anche quando non è necessario e degradando le prestazioni e causando problemi dal punto di vista della privacy
- **gestione degli utenti**: supponiamo di avere due LAN (una nel dipartimento di Informatica e una in quella di Ingegeneria). Se un utente vuole spostarsi da Informatica e Ingegneria, si connette fisicamente allo switch di Ingegneria, ma vuole rimanere comunque logicamente connesso ad Informatica.
Questi vari problemi sono risolti dagli switch che supportano l'utilizzo di **Virtual Local Area Network (VLAN)**. Gli host di una VLAN comunicano tra di loro come se fossero gli unici connessi allo switch. 
In una VLAN basata sulle porte, le interfacce dello switch vengono suddivise in gruppi, ognuno dei quali costituisce una VLAN. In foto vediamo uno swtich con 16 porte: le porte 2-8 appartengono alla VLAN di ingegneria mentre quelle 9-15 appartengono alla VLAN di informatica

![[SOR/RETI/img/img118.png|center|500]]

Questo, di fatto, permette ad un singolo switch di operare come più switch virtuali, infatti i frame da/verso le porte 2-8 possono raggiungere solo quelle.

Se le VLAN sono implementate su più switch è necessario l'utilizzo di una porta **trunk** per far comunicare le VLAN. 

![[SOR/RETI/img/img119.png|center|500]]

# Wireless
Per wireless si intende un collegamento tra 2 nodi senza fili.
Lo svantaggio principale di un collegamento wireless è che l'utente spostandosi cambia sempre il punto di aggancio alla rete.

## Componenti rete wireless
1. **Hosts Wireless**: Qualsiasi dispositivo terminale in grado di collegarsi alla rete in modalità wireless, per esempio smartphone e laptop.
2. **Collegamenti Wireless**: Collegamente senza filo per connettere gli host wireless alla stazione base o gli host tra di loro. Il mezzo trasimissivo wireless è chiaramente un canale broadcast, di conseguenza si pone il problema dell'accesso multiplo, quindi c'è la necessita di definire un protocollo MAC.
3. **Stazione Base**: Si tratta di un dipositivo che funge da ripetitore (relay) di segnale. Tali dispositivi sono l'access point o la torre della rete cellulare. Tipicamente le stazioni basi sono collegate ad una rete cablata.
![[Wireless_1.png | center | 600]]
Esistono 2 modalità per costruire una rete cellulare:
- **Infrastruttura:** La rete wireless fa riferimento a un'infrastruttura di rete che contiene la stazione di base. Quando si parla di questo tipo di reti wireless non bisogna dimenticare il problema dell' **hand-coff**, ovvero un host spostandosi passa dal range di una stazione di base nell'range di un altra.
- **Reti ad hoc:** Non ci sono stazioni basi a cui gli host di devono collegare. Gli hosts wireless comunicano direttamente tra loro, dovendo propriamente pensare al routing, al DNS etc...

## Caratteristiche collegamenti wireless
- **Attenuazione del segnale** Questo fattore è anche presente nelle reti cablate, ma con il wireless il problema è maggiore. Bisogna tener conto degli ostacoli che assorbono parte delle radiazioni eletttromagnetiche o dell'attenuazione dello spazio libero.
In particolare l'attenuazione dello spazio libero è direttamente proporzionale a $(fd)^2$, dove $f$ è la frequenza e $d$ è la distanza. Quindi, maggiore è la distanza e la frequenza, maggiore sarà l'attenuazione del segnale.
- **Propagazione su cammini multipli** Avviene quando il segnale elettromagnetico si riflette su diversi oggetti, arrivando a destinazione passando però per diversi percorsi.
- **Interferenza** Avviene quando molteplici sorgenti radio trasmettono alla stessa frequenza. Per esempio cellulari wireless e LAN wireless trasmettono entrambe sulla frequenza 2.4GHz, portanto a interferenze del segnale. Per questo, moderni standard wireless hanno deciso di trasmettere a frequenze maggiori come 5GHz.
Legato alle inteferenze del segnale vi è il **Rapporto Segnale Rumore (SNR)**. Si tratta del rapporto tra segnale trasmesso che è stato ricevuto e rumore di fondo. Si ha un buon segnale se SNR alto permettendo di estrarre al meglio il segnale ovvero c'è un minor **Tasso di Errore sui Bit (BER)**.
Quindi, aumentare la potenza signigica aumentare il SRN e quindi il BER. Tuttavia non è così semplice, olte a questioni di salute, aumentare la potenza significa aumentare il consumo energetico.

## CDMA (Accesso Multiplo a Divisione di Codice)
È un altro protocollo ad accesso multiplo e rientra nella **divisione del canale**.
Per questo protocollo facciamo le seguenti assunzioni: Il bit 0 vale -1, poi i segnali provenienti dai vari trasmittenti sono ricevuti tutti con la stessa intesità, e inoltre tutto è perfettamente sincronizzato.
Dunque, ad ogni utente viene assegnato un codice, ovvero un vettore composto da 1 e -1. I codici sono assegnati in modo che il prodotto scalare di codici di due utenti è pari 0, quindi sono ortogonali.
Immaginiamo adesso che l'utente $i$ debba inviare un certo bit $d_{i}$, come avviene la codifica? Moltiplica tale bit per il suo codice, quindi ottiene un vettore le cui componenti sono quelle del codice moltiplicato per il bit.
Se deve inviare 1 il vettore del codice rimane inalterato, se deve inviare -1 gli elementi del vettore cambiano segno.
![[Wireless_2.png | center | 700]]
Se due nodi trasmettono in contemporanea, assumendo che tutto è sincronizzato quando vi è una collisione i valori si sommano. Dal punto di vista del ricevente anche con valori sommati si ottiene il bit che intendeva inviare il mittente.

## WiFi - 802.11 wireless LAN
802.11 wireless LAN conosciuto meglio come Wifi è una delle tecnologia più usate ad oggi.
Gli standard "802.11n", "802.11ac" e "802.11ax" sono stati ribattezzati rispettivamente WiFi 4, 5 e 6 e ad oggi si sono spostati su frequenza 5GHz per evitare interferenze e dunque per ottenere più velocità.
Lo standard 802.11 può lavorare sia in modalità _infrastruttura_ sia in modalità _ad hoc_.
Nella modalità infrastruttura troviamo di solito un **Access Point (AP)** che funge da stazione di base e denotiamo con **Basic Service Set (BSS)** il complesso degli access points e gli host collegati ad essi.
Lo standard WiFi lavora con varie bande di frequenza che a loro volta sono divise in canali. Quando viene configurato un AP è necessario indicare la banda che andremo a utilizzare in modo tale da ridurre le interferenze.
Per esempio la banda 2.4GHz ha 11 canali, ciascuno con larghezza di 85MHz che si sovrappongono parzialmente tra di loro.
![[Wireless_3.png | center | 900]]
osserviamo che con la banda 2.4GHz possiamo configurare 3 AP vicini ognuno su un canale diverso tra 1, 6 e 11 in modo tale da non interferire l'uno con l'altro.

Come avviene **l'associazione** tra host e AP?
Due tipi di approcci:
- **Approccio passivo**: L'access point invia agli host periodicamente un **frame beacon** contenti il nome dell'AP (SSID) e il MAC address. Sta poi all'host se scegliere di collegarsi o meno al AP. Se decide di autenticarsi, una volta collegato tramite l'AP manda al DHCP una richiesta per ottenere indirizzo IP e altre informazioni.
- **Approccio attivo**: In questo caso l'host invia un **frame sonda** in broadcast e i vari AP rispondono poi con un frame sonda di risposta. A questo punto l'host invierà un frame di richiesta all'AP per collegarsi e sta poi all'AP se accetare o meno la richiesta di connessione.

### Protocollo MAC - CSMA/CA (Collision Avoidance)
Abbiamo visto che per Ethernet si utilizza il protocollo CSMA/CD nel caso di accesso multiplo al canale di trasmissione. Con wireless non è possibile utilizzare lo stesso protocollo ma bisogna utilizzare CSMA/CA.
In quanto CSMA, effettua il rilevamento della portante e non trasmette se trova il canale occupato. Ma non tenta di rilevare eventuali collisioni, inviando sempre per intero il frame. Al limite, si può indirettamente rilevare la collisione come mancanza di riscontro da parte dell’AP (che invia degli ACK).
**Come funziona nel dettaglio il protocollo MAC CSMA/CA?**
Supponiamo che una stazione wireless deve inviare un frame allora:
1. Se il canaista del destinatario è molto semplice, quando gli arriva un frame fa controllo con CRC e in caso non rileva errori manda ACK dopo un tempo **Short Inter-Frame Spacing (SIFS)**.
Per quanto riguardo l'evitare delle collisione, esiste un altro meccanismo basato sul protocollo CSMA, questo meccanismo prende il nome di **prenotazione esplicita**.
Il nodo che vuole trasmettere manda all'AP un frame **RTS (Request To Send)** usando CSMA, chiedendo il permesso all'AP di poteri.

## Struttura del frame 802.11
![[Wireless_4.png | center | 900]]
Il frame contiene delle **informazioni di controllo**, una **durata** usato quando si utilizza RTS E CTS. E poi si hanno 4 indirizzi, esatto hai letto bene porco il dio signore, si hanno 4 indirizzi che adesso, piano piano andremo a vedere cosa sono. Poi abbiamo un **numero di sequenza** che serve per il trasferimento affidabile (è meno spendioso implementare il trasferimento affidabile tra due nodi, che su tutto il percorso.) e un campo **payload** e **CRC**.
Dunque, perche porca la madonna il frame WiFi ha 4 indirizzi? WiFi distingue chi trasmette il frame dal mittente originale e allo stesso modo distingue il ricevente attuale da quello che è l'ultimo destinatario del frame.
- **Indirizzo 1**: Indirizzo MAC dell’host wireless o AP che deve ricevere il frame (non è necessariamente il destinatario finale).
- **Indirizzo 2**: Indirizzo MAC dell’host wireless o AP che trasmette il frame (non è necessariamente il mittente iniziale).
- **Indirizzo 3**: Indirizzo MAC dell’interfaccia router a cui l’AP è collegato (per accedere ad altre sottoreti).
Il quarto indirizzo viene utilizzato nella modalità ad hoc.
![[Wireless_5.png | center | 800]]
Supponiamo che il router debba inviare un datagramma ad H1, allora:
1. Il router, che conosce l'indirizzo Il di H1, tramite ARC determina il suo indirizzo MAC. L'interfaccia R1 del router crea il frame **Ethernet** incapsulando il datagramma. L'indirizzo sorgente di questo frame è il MAC address di R1, e quello di destinazione è il MAC address di H1.
2. Quando il frame **Ethernet** arriva all'AP, l'AP lo converte in un frame **802.11** prima di immetterlo nel canale wireless, riempiendo i campi degli indirizzi in questo modo:
    - Indirizzo 1: MAC address di H1
    - Indirizzo 2: MAC address di AP
    - Indirizzo 3: MAC address di R1 In questo modo, H1 potrà determinare l'indirizzo MAC del router.
3. H1 riceve il frame, estrae il datagramma e lo invia al livello di rete. Ora H1 crea un frame **802.11**, riempiendo i campi degli indirizzi in questo modo:
    - Indirizzo 1: MAC address di AP
    - Indirizzo 2: MAC address di H1
    - Indirizzo 3: MAC address di R1
4. Quando il frame **802.11** arriva all'AP, lo converte in un frame **Ethernet**. L'indirizzo della sorgente di questo frame è il MAC address di H1 e come indirizzo di destinazione il MAC address di R1. Quindi grazie all'indirizzo 3, l'AP riesce a determinare a chi deve inviare il frame **Ethernet**.

## Mobilità all'interno della stessa sottorete
![[Wireless_6.png | center | 700]]
Cosa succede quando mi sposto da un AP ad un altro, e questi due sono collegati da uno switch? Lo switch sa a quale porta inoltrare un frame per H1 poichè lo apprende la prima volta che riceve frame per da H1. Ma se H1 si sposta da un AP ad un altro, come fa adesso lo switch a sapere a quale porta inoltrarlo?
Un modo per risolvere il problema può essere che il nuovo AP invia un frame Ethernet broadcast con mittente H1 affinché lo switch apprenda la nuova porta per raggiungere H1.

## Funzioni avanzate di 802.11
- Adattamento del tasso trasmissivo
- Gestione dell'energia

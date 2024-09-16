Mentre il livello di rete si occupa dell'instradamento dei pacchetti lungo tutto il percorso end-to-end, il **livello di collegamento** si occupa di trasferire i datagrammi da un nodo (indicheremo con questo termine sia gli *host* che i *router*) a quello fisicamente adiacente lungo il collegamento.

![[SOR/RETI/img/img100.png|center|500]]

Nella figura vediamo come un host wireless voglia inviare un datagramma a uno dei server. Per effettuare questa operazione il datagramma deve attraversare sei collegamenti diversificati tra WiFi (primo link) ed Ethernet. 
Per ogni collegamento, il nodo trasmittente incapsula il datagramma in un **frame del livello di collegamento**.

# Servizi del livello di collegamento
Il servizio principale del livello di collegamento è il trasporto di datagrammi tra nodi adiacenti su un singolo canale di comunicazione. Alcuni protocolli offrono servizi aggiuntivi, tra cui:
- **Framing**: I datagrammi vengono incapsulati in frame, che includono un campo dati e un'intestazione.
- **Accesso al collegamento**: I protocolli **MAC** (Medium Access Control) gestiscono le regole per inserire i frame nel collegamento.
- **Consegna affidabile**: Utilizzato su collegamenti con *tassi di errore elevati*, come quelli wireless, per correggere gli errori localmente invece di ritrasmettere i dati.
- **Rilevazione e correzione degli errori**: Necessario per evitare la trasmissione di datagrammi con errori.

# Dov'è implementato il livello di rete
Il livello di rete è implementato in un adattatore di rete, detto **scheda di rete (NIC)**. Il cuore della scheda di rete è il controller a livello di collegamento, un chip dedicato che implementa i servizi visti precedentemente.

![[SOR/RETI/img/img101.png|center|500]]

- Lato **mittente**: il controller prende un datagramma, lo incapsula in un frame aggiungendo i vari campi dell'intestazione (bit di controllo degli errori, trasferimento dati affidabile ecc.) e lo trasmette sul canale di comunicazione.
- Lato **destinatario**: il controller riceve l'intero frame, estrae il datagramma e lo consegna al livello di rete. Se il protocollo del livello di collegamento fornisce la rilevazione degli errori, allora il controller ricevente verifica la presenza degli errori

# Rilevazione e correzione degli errori
In figura vediamo uno scenario di rilevazione e correzione degli errori.

![[SOR/RETI/img/img102.png|center|500]]
Il nodo trasmittente aggiunge bit di controllo degli errori (EDC) ai dati (D), che includono datagrammi e intestazioni. Il nodo ricevente riceve una sequenza $D^{'}$ ed $EDC^{'}$ che potrebbe differire dall'originale a causa di errori di trasmissione. Il ricevente deve determinare se $D^{'} = D$ utilizzando solo $D^{'}$ ed $EDC^{'}$. Anche con EDC, c'è la possibilità di errori non rilevati, per cui vengono impiegate tecniche più sofisticate.

Vedremo tre tecniche per rilevare errori nei dati trasmessi: 
- controllo di parità, per la base della rilevazione e correzione di errori
- tecniche di checksum, utilizzate nel livello di trasporto
- controllo a ridondanza ciclica, impiegate nelle schede di rete

## Controllo di parità
Questa è la forma più semplice di rilevamento degli errori, che utilizza un singolo **bit di parità**. 
Supponiamo che i dati $D$ da inviare siano costituite da $d$ bit. In uno schema di *parità pari*, si aggiunge un bit addizionale scegliendo il suo valore in modo da rendere pari il numero di bit 1 (includendo anche il bit di parità) nei bit trasmessi.  
In uno schema di *parità dispari* il valore del bit di parità è scelto in modo che ci sia un numero dispari di bit 1.

Il **controllo di parità** è la forma più semplice di rilevamento degli errori, basato su un **bit di parità**. Nel caso di *parità pari*, si aggiunge un bit in modo che il numero di bit 1 sia pari, mentre per *parità dispari* si assicura che il numero di bit 1 sia dispari. 

![[SOR/RETI/img/img103.png|center|500]]

Questo metodo rileva errori singoli, ma nella realtà gli errori tendono a verificarsi a gruppi di bit consecutivi (burst), per cui è necessario utilizzare una strategia diversa.

![[SOR/RETI/img/img104.png|center|500]]

In figura osserviamo una generalizzazione bidimensionale dello schema di parità con un bit. In questo caso i $d$ bit del dato $D$ sono suddivise in $i$ righe e $j$ colonne, per ognuna delle quali viene calcolato un bit di parità (sia di riga che di colonna). 
I risultanti $i+j+1$ bit di parità contengono i bit per la rilevazione dell'errore del frame del link dati. 


![[SOR/RETI/img/img105.png|center|500]]

Un'estensione bidimensionale del controllo di parità permette sia la *rilevazione che la correzione di errori singoli*, ma **non è in grado di correggere errori multipli**.

![[SOR/RETI/img/img106.png|center|500]]
![[SOR/RETI/img/img107.png|center|500]]
![[SOR/RETI/img/img108.png|center|500]]

## Checksum
Il **checksum** tratta i dati come una sequenza di numeri. Il mittente somma i blocchi di dati in complemento a 1 e inverte il risultato per ottenere il checksum, che invia insieme al messaggio. Il destinatario effettua nuovamente la somma, verificando la correttezza dei dati. Il checksum è una tecnica semplice, usata soprattutto a livello di trasporto.

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

Supponiamo che il canale broadcast supporti $N$ nodi e che abbia una velocità di $R$ bps. TDM suddivide il tempo in intervalli di tempo e poi divide ciascun intervallo in $N$ **slot temporali** e li assegna ad ogni nodo. Ogni volta che un nodo vuole trasmettere, lo fa durante lo slot di tempo ad esso assegnato.
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

Il tempo di attesa casuale dopo una collisione è chiamato **tempo di backoff**. Un algoritmo chiamato **binary exponential backoff** gestisce questo processo: 
- Se il trasmettitore rileva la $n$-esima collisione, sceglie un valore casuale $K$ da $\{0, 1, 2, \dots, 2^n - 1\}$. 
- Aumentando il numero di collisioni, aumenta l'intervallo da cui scegliere $K$, riducendo la probabilità di collisioni successive.

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
Le **Local Area Network (LAN)** coprono un'area limitata, come abitazioni o dipartimenti. Le tecnologie principali includono **Ethernet** e **Wireless**.

## Indirizzi a livello di collegamento
Ogni host e router possiede, oltre all'indirizzo IP, un **indirizzo MAC** a livello di collegamento. Gli indirizzi MAC sono univoci, lunghi 6 byte (48 bit), e gestiti dalla IEEE.
- Gli indirizzi MAC non cambiano mai, a differenza degli indirizzi IP. 
- Quando una scheda trasmette un frame, inserisce l'indirizzo MAC di destinazione e lo invia nella LAN. Ogni scheda di rete controlla se l'indirizzo MAC di destinazione corrisponde al proprio. In caso affermativo passa il frame a livello superiore, altrimenti lo scarta.
- Per inviare in modalità broadcast, si utilizza l'indirizzo **MAC broadcast** (FF-FF-FF-FF-FF-FF).

## Protocollo risoluzione degli indirizzi
Il protocollo **ARP** risolve l'associazione tra indirizzo IP e indirizzo MAC. Ogni nodo mantiene una **tabella ARP** con mappature IP-MAC e un campo TTL che indica la validità della riga nella tabella. 

Se un nodo A deve trovare l'indirizzo MAC di B: 
1. A invia un **pacchetto ARP** in broadcast, specificando l'indirizzo IP di B. 
2. Ogni nodo riceve la richiesta ARP, ma solo quello con l'indirizzo specificato (B) risponde con il proprio indirizzo MAC. 
3. A aggiorna la sua tabella ARP.

### Inviare un datagramma ad un nodo esterno alla sottorete

![[SOR/RETI/img/img115.png|center|500]]

Se A vuole inviare un datagramma a B in un'altra sottorete, A invia il datagramma al **router** R. A crea un datagramma IP con destinazione B, incapsulato in un frame con l'indirizzo MAC dell'interfaccia di R. R inoltra il datagramma a B dopo averlo passato a livello di rete.

## Ethernet

**Ethernet** è la tecnologia più diffusa per le LAN cablate. Le topologie Ethernet includono: 
- **Struttura a bus**: un singolo cavo che connette tutte le schede di rete. 
- **Topologia a stella con hub**: l'hub inoltra i segnali ricevuti a tutte le interfacce. 
- **Topologia a stella con switch**: sostituisce l'hub con uno switch, eliminando le collisioni.

### Struttura dei frame internet
L'interfaccia trasmittente incapsula il datagramma IP in un frame Ethernet e lo passa a livello fisico. 

![[SOR/RETI/img/img116.png|center|500]]

- **Preambolo**: 8 byte con valori fissi, i primi 7 hanno valore 10101010 mentre l'ottavo ha valore 10101011. I primi 7 byte servono per sincronizzare il clock dell'adattatore ricevente con quello del mittente
- **Indirizzo di destinazione/sorgente**: contiene l'indirizzo MAC della scheda del destinatario/mittente
- **Tipo**: specifica il protocollo dello strato superiore a cui consegnare il campo dati
- **Dati**: contiene il datagramma IP. Ha una lunghezza minima di 46 byte.
- **CRC**: consente alla scheda di rete ricevente di rilevare errori nel frame

Ethernet è un protocollo **senza connessione** e **non affidabile**: non usa handshake né invia ACK/NAK. L'affidabilità è garantita dai protocolli di livello superiore (es. TCP).

## Switch a livello di collegamento
Lo switch è un **commutatore di pacchetti a livello di collegamento** ed il suo ruolo è quello di ricevere i frame in ingresso e inoltrarli sui collegamenti in uscita. Inoltre uno switch è invisibile agli host, infatti ogni nodo indirizza un frame ad un altro nodo invece che indirizzarlo allo switch. 

Lo switch ha le funzionalità di **filtraggio e inoltro**. La prima è quella che *determina se un frame debba essere inoltrato su un'interfaccia o scartato*, mentre la seconda consiste nell'*individuare l'interfaccia verso la quale il frame deve essere diretto*.
Queste operazioni sono eseguite mediante una tabella di commutazione composta dalle seguenti voci:
- indirizzo MAC del nodo
- l'interfaccia dello switch rivolta verso il nodo
- time stamp

![[SOR/RETI/img/img117.png|center|500]]

La tabella è costruita tramite **autoapprendimento**. Quando lo switch riceve un frame, salva il MAC e l'interfaccia di collegamento del mittente e cerca l'interfaccia di uscita.
- Se l'indirizzo di destinazione è noto, il frame viene inoltrato. 
- Se non è noto, lo switch esegue un **flood** inviando il frame a tutte le interfacce.

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

Il termine *wireless* indica un collegamento tra due nodi senza l'uso di fili. Un problema tipico è che l'utente, spostandosi, cambia il punto di connessione alla rete.

## Componenti rete wireless
1. **Hosts Wireless**: Dispositivi terminali in grado di connettersi alla rete in modalità wireless, come smartphone e laptop.
2. **Collegamenti Wireless**: Connessioni senza fili tra gli host e la stazione base. Il canale è di tipo broadcast, per cui è necessario un protocollo MAC.
3. **Stazione Base**: Dispositivo che funge da ripetitore, come un access point o una torre cellulare, collegati a una rete cablata.

![[Wireless_1.png | center | 500]]

### Modalità di rete cellulare
Esistono due modi per corstruire una rete cellulare:
- **Infrastruttura**: La rete si basa su una stazione di base, e l'host cambia stazione man mano che si sposta (hand-off).
- **Reti ad hoc**: Gli host comunicano direttamente senza stazioni base.

## Caratteristiche collegamenti wireless
- **Attenuazione del segnale**: È più forte rispetto alle reti cablate, dovuta a ostacoli o alla distanza. L'attenuazione è proporzionale a $(fd)^2$ dove $f$ è la frquenza e $d$ la distanza, quindi maggiore è la distanza e la frequenza e maggiore sarà l'attenuazione di segnale.
- **Propagazione su cammini multipli**: Il segnale riflesso su diversi oggetti può arrivare tramite percorsi differenti.
- **Interferenza**: Succede quando più sorgenti trasmettono alla stessa frequenza, come dispositivi cellulari e LAN wireless.

### Rapporto Segnale/Rumore (SNR)
Un SNR elevato significa che il segnale è chiaro rispetto al rumore, riducendo il Tasso di Errore sui Bit (BER).

## CDMA (Accesso Multiplo a Divisione di Codice)
Ogni utente ha un codice (vettore di 1 e -1). I codici degli utenti sono ortogonali e il loro prodotto scalare è pari a 0, permettendo la trasmissione simultanea.

![[Wireless_2.png | center | 500]]

## WiFi - 802.11 Wireless LAN
WiFi è una delle tecnologie wireless più usate. Gli standard 802.11n, 802.11ac, e 802.11ax sono WiFi 4, 5 e 6. Operano anche a 5GHz per evitare interferenze. Il WiFi può funzionare in modalità infrastruttura o ad hoc.

Lo standard WiFi lavora con bande di frequenza, che si dividono in canali. Ad esempio, la banda a 2.4GHz ha 11 canali, di cui 3 non si sovrappongono (1, 6, 11).

![[Wireless_3.png | center | 500]]

### Associazione tra host e AP
Due approcci:
- **Passivo**: L'AP invia un frame beacon e l'host può scegliere di connettersi.
- **Attivo**: L'host invia un frame sonda e gli AP rispondono.

### Protocollo MAC - CSMA/CA
Il protocollo MAC CSMA/CA viene utilizzato per evitare collisioni. Una stazione rileva il canale prima di trasmettere. Le collisioni sono rilevate indirettamente dalla mancanza di ACK.

### Prenotazione esplicita
Un nodo invia un frame RTS per prenotare il canale, chiedendo il permesso di trasmettere.

## Struttura del frame 802.11

Il frame WiFi ha 4 indirizzi:
1. **Indirizzo 1**: MAC dell'host o AP che deve ricevere il frame.
2. **Indirizzo 2**: MAC dell'host o AP che trasmette il frame.
3. **Indirizzo 3**: MAC dell'interfaccia router collegata all'AP.

![[Wireless_4.png | center | 500]]

![[Wireless_5.png | center | 500]]

### Mobilità nella stessa sottorete
Quando un host si sposta tra due AP collegati a uno switch, il nuovo AP invia un frame broadcast per aggiornare lo switch.

![[Wireless_6.png | center | 500]]

## Funzioni avanzate di 802.11
- Adattamento del tasso trasmissivo
- Gestione dell'energia

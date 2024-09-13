
Il livello di trasporto fornisce **comunicazione logica** ai processi applicativi su diversi host, facendoli sembrare connessi direttamente.

I protocolli di trasporto sono eseguiti nei sistemi periferici:
- lato mittente: converte i messaggi in arrivo dal livello applicativo in **segmenti**, suddividendoli in parti più piccole e aggiungendo un'intestazione di trasporto e li passa al livello di rete, dove viene incapsulato in un datagramma.
 - lato ricevente: il livello di rete estrae il segmento dal datagramma, lo passa al livello di trasporto, che elabora il segmento ricevuto rendendo disponibili all'applicazione i dati del segmento.
I protocolli di trasporto più utilizzati sono TCP e UDP.

# Multiplexing e Demultiplexing
Il servizio di trasporto da host a host diventa un servizio **da processo a processo** grazie a multiplexing e demultiplexing. 

Nell'host destinatario, il livello di trasporto, che riceve i segmenti dal livello di rete, ha il compito di consegnare i dati al processo applicativo appropriato. 

I processi comunicano tramite **socket** con un ID unico, e il trasporto assegna i segmenti ai socket corretti in base ai campi del segmento.

- **Multiplexing**: il trasporto lato mittente raccoglie dati da vari socket e li invia tramite segmenti.
- **Demultiplexing**: il trasporto lato ricevente esamina i segmenti ricevuti e li consegna ai socket appropriati.

Vediamo nel dettagli come funziona il demultiplexing e il multiplexing. Il multiplexing a livello di trasporto richiede che i socket abbiano indentificatori unico, il **numero di porta di origine e di destinazione** che indicano il socket a cui va consegnato il segmento. I numeri di porta sono di 16 bit e vanno da 0 a 65535, quelli da 0 a 1023 sono *riservati* per i protocolli applicativi noti, come HTTP (80) FTP (21).

## Demultiplexing senza connessione
In UDP, i socket sono identificati da una coppia **(indirizzo IP, numero di porta)**. Il livello di trasporto associa il numero di porta alla destinazione, utilizzando il metodo `bind()` per assegnare una porta specifica, altrimenti viene assegnato un numero di porta tra 1024 e 65535:

```Python
mySocket = socket(AF_INET, SOCK_DGRAM) #crea un socket UDP
mySocket.bind(('',9157)) #assegnata la porta 9157
```

Dopo la creazione, si specifica l'indirizzo IP e la porta del destinatario. Il segmento viene poi passato a livello di rete che effettua un tentativo di consegna. Lato ricevente, all'arrivo del segmento, vengono analizzati i numeri di porta di destinazione e inviati al socket relativo.
In UDP, un socket è identificato da una coppia (indirizzo IP, numero di porta). Due segmenti con stesso IP e porta di destinazione, ma diversi IP o porte di origine, sono diretti allo stesso socket.

![[SOR/RETI/img/img35.png|center|500]]

## Demultiplexing orientato alla connessione
Il socket TCP è identificato da quattro parametri:
- indirizzo IP di origine
- numero di porta di origine
- indirizzo IP destinatario
- numero di porta destinatario
A differenza di UDP, segmenti TCP con IP o porta di origine diversi vengono indirizzati a socket differenti, anche se condividono lo stesso IP e porta di destinazione.

Consideriamo un esempio:
- l'applicazione server TCP ha un socket di benvenuto che attende delle richieste di connessione dai client TCP sulla porta 12000
- Il client TCP crea un socket e genera un segmento per stabilire la connessione con le seguenti linee di codice.
```Python
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName, 12000))
```
- Una richiesta di connessione è quindi un segmento TCP con porta di destinazione 12000 e un bit di richiesta di connessione posto a 1 nell'intestazione del segmento. 
- Il sistema operativo dell'host che esegue il processo server, quando riceve il segmento con la richiesta di connessione con porta di destinazione 12000, localizza il processo server in attesa di accettare connessioni sulla porta 12000, per poi creare una nuova connessione
```Python
connectionSocket, addr = serverSocket.accept()
```
- Il livello di trasporto sul server si annota **il numero di porta di origine** nel segmento con la richiesta di connessione, l'**indirizzo IP dell'host di origine**, il **numero di porta di destinazione** nel segmento e il **proprio indirizzo IP**, valori che andranno ad identificare la socket di connessione. Tutti i segmenti successivi che avranno gli stessi valori annotati, verranno diretti verso questo socket. 
- La connessione TCP è attiva e client e server possono scambiarsi dati.

![[SOR/RETI/img/img36.png|center|500]]

# UDP
Analizziamo ora nello specifico UDP, il protocollo di trasporto *non orientato alla connessione* (non c'è handshaking).
UDP fa il minimo che un protocollo di trasporto debba fare. A parte una funzione di multiplexing/demultiplexing e un controllo degli errori molto semplice, non aggiunge nulla a IP.
UDP, infatti, prende i messaggi dal processo applicativo, aggiunge il numero di porta di origini e destinazione, aggiunge altri due campi e passa il segmento al livello di rete, che lo incapsula in un datagramma ed effettua un tentativo best-effort di consegna. Se il segmento arriva, UDP utilizza il numero di porta di destinazione per consegnare i dati al processo applicativo corretto. 

## Perché scegliere UDP?
- Controllo più preciso a livello di applicazione su quali dati sono stati inviati e quando: quando un processo applicativo passa dei dati UDP, questo li impacchetta e li invia immediatamente al livello di rete. TCP invece con il controllo della congestione potrebbe ritardare l'invio a livello di trasporto. Dato che applicazioni in tempo reale non sopportrano ritardi e richiedono una velocità di invio minima, TCP non si adatta a queste esigenze
- Non c'è handshake, che potrebbe aggiungere ritardo
- Nessuno stato di connessione
- Minore spazio usato per l'intestazione del pacchetto

![[SOR/RETI/img/img37.png|center|500]]

Anche le applicazioni possono ottenere un trasferimento dati affidabile con UDP. Questo è possibile se l'affidabilità e inserita direttamente nell'applicazione stessa (ad esempio con meccanismi di notifica e ritrasmissione)

## Struttura segmenti UDP

![[SOR/RETI/img/img38.png|center|400]]

L'intestazione UDP presenta quattro campi di due byte ciascuno:
- i **numeri di porta** individuano il processo corretto per inviare i dati
- il campo **lunghezza** specifica il numero di byte del segmento UDP, necessario perché la grandezza del campo Dati può essere diversa tra un segmento e l'altro
- Il **checksum** è utilizzato dal ricevente per rilevare errori nel segmento

### Checksum UDP
Il checksum viene utilizzato per determinare i bit del segmento UDP sono stati alterati durante il trasferimento da sorgente a destinazione. 
Lato mittente UDP effettua il complemento a 1 della somma di tutte le parole da 16 bit nel segmento, con l'eventuale riporto sommato al primo bit. Questo risultato viene posto nel campo Checksum del segmento. 

Supponiamo di avere le tre parole di 16 bit $$\begin{align*}
&0110011001100000\\
&0101010101010101\\
&1000111100001100
\end{align*}$$
![[SOR/RETI/img/img39.png|center|500]]

Notiamo che il riporto di questa somma è stato sommato al primo bit. Il complemento a 1 si ottiene convertendo i bit 0 in bit 1 e viceversa, quindi il checksum sarà $1011010100111101$. In ricezione si sommano le tre parole iniziali e il checksum. Se non ci sono errori nel pacchetto il risultato sarà $1111111111111111$, altrimenti se un bit vale $0$ è presente un errore nel pacchetto. 

# Principi del trasferimento dati affidabile

Il trasferimento di dati affidabile è un problema fondamentale nel campo del networking. 
Il servizio offre l'astrazione (sinistra) di un canale affidabile in cui, durante il trasferimento dei dati, nessuin bit è corrotto o va perduto. 

![[SOR/RETI/img/img40.png|center|500]]


Un protocollo di trasferimento dati affidabile, come TCP, garantisce la consegna corretta dei dati nonostante l'inaffidabilità del livello di rete sottostante (es. IP). Il lato mittente invia i dati con `rdt_send()`, che utilizza `udt_send()` per trasmettere il pacchetto tramite il canale inaffidabile. Il lato ricevente, alla ricezione di un pacchetto, invoca `rdt_rcv()` e, dopo aver verificato l'integrità dei dati, utilizza `deliver_data()` per consegnarli al livello superiore. L'obiettivo del protocollo è assicurare la consegna affidabile dei dati gestendo le inaffidabilità del canale.

## Costruzione protocollo di trasferimento dati affidabile
### rdt1.0, canale affidabile
Il caso più semplice è quello in cui il canale sottostante è completamente affidabile, quindi non si verificano errori nei bit e non avvengono perdite di pacchetti.

![[SOR/RETI/img/img41.png|center|500]]

- il mittente accetta i dati dal livello superiore tramite `rdt_send(data)`, crea un  pacchetto contenente i dati con `packet = make_pkt(data)`  e lo invia sul canale `udt_send(packet)`.
- il ricevente raccoglie i pacchetti dal basso con `rdt_rcv(packet)`, rimuove i dati dai pacchetti con `extract(packet, data)` e li passa al livello superiore con `deliver_data(data)`.

### rdt2.0, canale con errori su bit
Il modello rdt2.0 affronta il problema di un canale in cui i bit di un pacchetto possono essere corrotti. La soluzione consiste nell'uso di **notifiche positive (ACK)** e **negative (NAK)**, insieme a un invio di tipo **stop-and-wait**. Il mittente invia un pacchetto e attende la risposta del destinatario:

- **ACK**: il pacchetto è stato ricevuto correttamente.
- **NAK**: il pacchetto è corrotto e viene ritrasmesso.

Questo tipo di protocollo è noto come **ARQ (Automatic Repeat reQuest)**.

![[SOR/RETI/img/img42.png|center|500]]

Il mittente ha due stati:
1. **Attesa di dati**: quando arriva un dato da inviare (`rdt_send(data)`), viene creato un pacchetto con i dati e il checksum, e inviato al destinatario.
2. **Attesa di ACK/NAK**: Se riceve un ACK, torna a raccogliere i dati. Se riceve un NAK, ritrasmette il pacchetto.

![[SOR/RETI/img/img43.png|center|500]]

Il ricevente ha un solo stato:
- Se il pacchetto è corrotto, invia un **NAK** al mittente.
- Se il pacchetto è corretto, estrae i dati e invia un **ACK**.
Questo metodo ha un limite: *non possiamo sapere se i pacchetti ACK o NAK sono alterati*, rendendo il protocollo incompleto.

### rdt2.1, gestione di ACK/NAK alterati
Per affrontare il problema di ACK e NAK corrotti, sono state valutate diverse soluzioni:
1. **Introduzione di un nuovo pacchetto**: il mittente invia un messaggio aggiuntivo in caso di corruzione di un ACK o NAK. Questa soluzione non è efficace, perché anche il nuovo pacchetto inviato potrebbe essere corrotto.
2. **Aggiunta di un bit di checksum**: questa soluzione può rilevare pacchetti danneggiati, ma non risolve il problema dei pacchetti persi.
3. **Ritrasmissione in caso di ACK/NAK alterati**: se il mittente non riceve una risposta chiara, ritrasmette il pacchetto. Tuttavia, questo introduce il problema dei **pacchetti duplicati**, poiché il destinatario non può sapere se sta ricevendo un nuovo pacchetto o una ritrasmissione.

La soluzione adottata consiste nell'aggiungere un **numero di sequenza** ai pacchetti. Il mittente assegna a ogni pacchetto un numero (0 o 1) e il destinatario utilizza questo numero per distinguere tra pacchetti nuovi e ritrasmissioni.

![[SOR/RETI/img/img44.png|center|500]]
![[SOR/RETI/img/img45.png|center|500]]

Osserviamo che sia mittente che destinatario hanno il doppio degli stati, in quanto devono riflettere il fatto che il pacchetto in invio o in ricezione abbia numero di sequenza 0 o 1. Notiamo inoltre che le azioni negli stati sono speculari, in quanto l'unica differenza riguarda la gestione del numero di sequenza.  

### rdt2.2, protocollo senza NAK
Il funzionamento è lo stesso di rdt2.1, ma utilizzando solamente gli ACK. In questo caso il destinatario invia un ACK per l'ultimo pacchetto ricevuto correttamente, indicando esplicitamente il numero di sequenza del pacchetto con l'ACK, mentre un ACK duplicato inviato al mittente è analogo ad un NAK, quindi il pacchetto viene ritrasmesso. Il protocollo TCP utilizza questo approccio.

![[SOR/RETI/img/img46.png|center|500]]
![[SOR/RETI/img/img47.png|center|500]]

### rdt3.0, canali con errori e perdite
**rdt3.0** affronta il problema dei pacchetti persi e corrotti. Oltre a gestire i bit danneggiati, questo protocollo si occupa della perdita di pacchetti introducendo un meccanismo di **ritrasmissione basato su timer**.

- **Rilevamento delle perdite:** Se il mittente invia un pacchetto ma non riceve un ACK entro un certo tempo, ritrasmette il pacchetto. Il tempo di attesa è difficile da stimare, quindi si sceglie un valore prudente che copra il ritardo andata-ritorno.
- **Pacchetti duplicati:** Se un pacchetto o ACK viene ritrasmesso, potrebbe causare duplicati. I **numeri di sequenza** aiutano il destinatario a distinguere tra pacchetti nuovi e duplicati.
- **Timer:** Il mittente avvia un timer ogni volta che invia un pacchetto. Se scade senza ricevere un ACK, ritrasmette il pacchetto.

Questo protocollo garantisce la consegna affidabile dei dati anche in presenza di perdite e duplicati.

![[SOR/RETI/img/img48.png|center|500]]

#### Analisi delle prestazioni rdt3.0
Il protocollo rdt3.0 è corretto funzionalmente, ma le prestazioni non sono il massimo in quanto si tratta di un protocollo di tipo stop-and-wait. Analizziamo la velocità di trasferimento, considerando questo caso. 

Due host, sulla coste opposte degli Strati Uniti. L'[[Capitolo 1 - Livello Applicazione#Tempi di risposta|RTT]] è circa di 30 ms. Supponiamo che i due sistemi siano connessi da un canale con velocità di trasmissione $R$ di $1$ Gbps ($10^9$ bit al secondo). Con pacchetti di dimensione $L$ di $1000$ byte ($8000$ bit), il tempo richiesto per trasmettere il pacchetto sul collegamento è $$d_{t}=\frac{L}{R}=\frac{8000\text{ bit}}{10^{9}\text{ bit/s}}=8\:\micro s$$
![[SOR/RETI/img/img49.png|center|300]]

Se il mittente inizia ad inviare pacchetti a $t=0$, l'ultimo bit entra nel canale dal lato mittente al tempo $t=\frac{L}{R}=8\:\micro s$. Il pacchetto effettua poi un viaggio di $15$ ms e l'ultimo bit giunge al destinatario a $t=\frac{RTT}{2}+ \frac{L}{R}=15.008$ ms. Di conseguenza l'ACK giunge al mittente all'istante $t=RTT+ \frac{L}{R}=30.008$ ms. Dopo questo lasso di tempo, il mittente puù trasmettere il messaggio successivo, quindi in un arco di $30.008$ ms il mittente ha trasmesso per soli $0.008$ ms. Definiamo ora l'utilizzo del mittente come la frazione di tempo in cui il mittente è stato effettivamente occupato nell'invio di bit $$U_\text{mittente}=\frac{L/R}{RTT+L/R}=\frac{0.008}{30.008}=0.00027$$
Il mittente è stato attivo per soli 2.7 centesimi dell'1% di tempo, con un throughput effettivo di $$U_\text{mittente}\cdot R=267\text{ kbps}$$ nonostante fosse disponibile un collegamento da $1$ Gbps. Il protocollo di tipo stop-and-wait limita quindi notevolmente le prestazioni del canale. 

### Protocolli per il trasferimento dati affidabile con pipeline 
La soluzione al problema delle prestazioni di rdt3.0 è quella di consentire al mittente di inviare più pacchetti senza aspettare gli ACK.

![[SOR/RETI/img/img50.png|center|500]]

Se si consente al mittente di trasmettere tre pacchetti, l'utilizzo viene triplicato. Le conseguenze di utilizzo di un protocollo di trasferimento con pipeline sono:
- incremento dei numeri di sequenza disponibili
- memorizzare in un buffer più di un pacchetto. Il mittente dovrà memorizzare i pacchetti trasmessi il cui ACK non è stato ricevuto. 

### Go-Back-N (GBN)

Nel **protocollo GBN**, il mittente può inviare più pacchetti senza attendere un ACK, ma non può avere più di $N$ pacchetti non riscontrati nella pipeline.

![[SOR/RETI/img/img51.png|center|500]]

La *window size* rappresenta i pacchetti inviati e in attesa di ACK. Il campo *base* indica il pacchetto più vecchio senza ACK, mentre *nextseqnum* rappresenta il prossimo pacchetto da inviare.

![[SOR/RETI/img/img52.png|center|500]]

Il mittente GBN risponde a tre eventi:
1. **Invocazione dall'alto:** Controlla se ci sono $N$ pacchetti in sospeso prima di inviare un nuovo pacchetto.
2. **Ricezione di un ACK:** Gli ACK sono **cumulativi**, riconoscono tutti i pacchetti fino al numero $n$.
3. **Timeout:** Il mittente ritrasmette **tutti** i pacchetti non riscontrati in caso di timeout.

![[SOR/RETI/img/img53.png|center|500]]

Il ricevitore invia ACK solo per pacchetti in ordine. Se un pacchetto è fuori sequenza, lo scarta e invia un ACK per il pacchetto più recente.

### Ripetizione Selettiva

Il protocollo **a ripetizione selettiva** evita le ritrasmissioni inutili, facendo ritrasmettere solo i pacchetti persi o corrotti. Il ricevitore invia ACK specifici per ciascun pacchetto corretto.

![[SOR/RETI/img/img54.png|center|500]]

Eventi per il mittente:
1. **Dati ricevuti dall'alto:** Il pacchetto viene inviato se il numero di sequenza è all'interno della finestra.
2. **Timeout:** Ogni pacchetto ha un timer, e viene ritrasmesso solo quello scaduto.
3. **ACK ricevuto:** Il mittente avanza la finestra se il pacchetto $n$ è ricevuto.

![[SOR/RETI/img/img55.png|center|500]]

Il ricevente può:
- Accettare e bufferizzare pacchetti nella finestra. Se il pacchetto ha numero di sequenza uguale a `rcv_base`, lo consegna insieme ai pacchetti consecutivi nel buffer.
- Inviare un ACK per pacchetti già ricevuti ma di cui non è stato confermato l'ACK.

![[SOR/RETI/img/img57.png|center|500]]
# TCP
TCP è un protocollo di trasporto affidabile e orientato alla connessione, che utilizza vari meccanismi come la rilevazione degli errori, le ritrasmissioni e gli acknowledgment cumulativi. Essendo orientato alla connessione, prima dello scambio di dati è necessario un handshake iniziale. La connessione TCP è **full-duplex** (dati possono fluire in entrambe le direzioni) e **point-to-point** (coinvolge solo un mittente e un destinatario).

Una volta stabilita la connessione tramite **handshake a tre vie**, i due processi possono scambiarsi dati.

![[SOR/RETI/img/img58.png|center|500]]

## Buffer di Invio e Segmenti TCP
Il mittente invia un flusso di dati attraverso il socket. I dati vengono messi nel **buffer di invio** TCP. La quantità massima di dati inseribili in un segmento è detta **MSS** (Maximum Segment Size). Il TCP accoppia i dati con un'intestazione per formare un **segmento TCP**, inviato poi tramite IP.

![[SOR/RETI/img/img59.png|center|500]]

## Struttura dei Segmenti TCP
I segmenti TCP contengono un'intestazione e i dati. La MSS limita la dimensione del campo dati.

![[SOR/RETI/img/img60.png|center|500]]

Oltre ai campi già visti nel protocollo UDP, l'intestazione TCP include:
- **Numeri di sequenza** e **numeri di ACK**
- **Finestra di ricezione** (per il controllo di flusso)
- **Lunghezza dell'intestazione**
- **Opzioni** 
- Vari **flag**:
	- il bit **ACK** indica che il segmento contiene un ACK relativo ad un segmento
	- **RST, SYN, FIN** sono usati per impostare la connessione
	- **CWR, ECE** utili nel controllo della congestione

### Numeri di Sequenza e Numeri di ACK
I numeri di sequenza e di ACK sono fondamentali per garantire il trasferimento dati affidabile. Il numero di sequenza di un segmento rappresenta il numero del primo byte nel flusso di dati. Ad esempio, un file di 500.000 byte con MSS di 1000 byte richiede 500 segmenti, ciascuno con un numero di sequenza crescente.

![[SOR/RETI/img/img61.png|center|500]]
![[SOR/RETI/img/img62.png|center|500]]
![[SOR/RETI/img/img63.png|center|500]]

## Timeout e Stima dell'RTT
TCP usa un meccanismo di timeout per ritrasmettere i segmenti persi. Il timeout viene impostato in base all'**RTT stimato** (round-trip time) e alla sua **variabilità** (DevRTT).

![[SOR/RETI/img/img65.png|center|500]]

## Trasferimento Dati Affidabile
TCP garantisce il trasferimento dati affidabile con tre eventi principali:
1. **Dati provenienti dall'applicazione**: vengono segmentati e inviati.
2. **Timeout**: ritrasmissione del segmento scaduto.
3. **ACK ricevuto**: aggiornamento del numero di base.

| Evento                                                                                                           | Azione Mittente                                                                                                                    |
| ---------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| Arrivo di segmento con numero di sequenza atteso. Tutti i dati fino al numero di sequenza sono stati riscontrati | ACK ritardato (fino a 500 ms)                                                                                                      |
| Arrivo di un segmento con numero di sequenza atteso. Un segmento ordinato è in attesa dell'ACK                   | Invia un ACK cumulativo                                                                                                            |
| Arrivo di segmento fuori sequenza superiore a quello atteso (buco)                                               | ACK duplicato con il numero del prossimo byte atteso                                                                               |
| Segmento che colma il buco nei dati ricevuti                                                                     | Invia immediatamente un ACK                                                                                                        |

![[SOR/RETI/img/img66.png|center|500]]
![[SOR/RETI/img/img67.png|center|300]]

### Ritrasmissione Rapida
Quando il mittente riceve **3 ACK duplicati**, ritrasmette immediatamente il segmento non riscontrato con il numero di sequenza più piccolo.

![[SOR/RETI/img/img68.png|center|300]]

## Controllo di Flusso
TCP regola la velocità di trasmissione del mittente per evitare il sovraccarico del buffer del ricevente tramite la **finestra di ricezione** (rwnd). Questo garantisce che la quantità di dati non riscontrati dal mittente sia sempre gestibile dal destinatario.

## Gestione della Connessione TCP

La connessione TCP viene stabilita tramite **handshake a tre vie**:
1. Il client invia un segmento **SYN**.
2. Il server risponde con un **SYNACK**.
3. Il client conferma con un segmento di ACK.

![[SOR/RETI/img/img69.png|center|500]]

La connessione viene chiusa con un segmento **FIN** inviato da entrambe le parti.

![[SOR/RETI/img/img70.png|center|500]]
# Principi del controllo della congestione
Il [[#Controllo di flusso|controllo di flusso]] riguardava la possibilità che un singolo mittente sovraccaricasse il destinatario. La **congestione** sulla rete si verifica quando troppe sorgenti inviano troppi dati ad una velocità elevata per essere gestiti dalla rete.

## Cause e costi della congestione
Prima di vedere i meccanismi su come reagire o evitarla analizziamo tre diversi scenari, valutando le cause e le conseguenze in termini di utilizzo.

### Scenario 1
Due host A e B con una connessione che condivide un router intermedio. 

![[SOR/RETI/img/img71.png|center|500]]

Ipotizziamo che un'applicazione nell'host A stia inviando dati sulla connessione ad una frequenza di $\lambda_{in}$ byte/s. L'host B opera in modo simile. I pacchetti dall'host A e dall'host B passano attraverso un router, avente un buffer per memorizzare i pacchetti, e un collegamento condiviso in uscita di capacità $R$. Ipotizziamo che i buffer del router abbiano *dimensione illimitata*.

![[SOR/RETI/img/img72.png]]
La figura mostra le prestazioni della connessione dell'host A in questo scenario. 
- Il grafico di sinistra mostra il **throughput per connessione** (ovvero il numero di byte per secondo inviati al ricevente): finché non supera il valore $R/2$ il throughput del ricevente è uguale al tasso di invio del mittente. Ma quando questo valore viene superato, il throughput resta $R/2$. Questo limite è conseguenza della condivisione del collegamento. 
- Il grafico a destra mostra le **conseguenze** di operare al limite della capacità di collegamento, infatti quando il tasso di invio si avvicina a $R/2$, il ritardo medio cresce molto. Superato questo tasso, il ritardo tende all'infinito.
In questo scenario vediamo quindi come *quando il tasso di arrivo dei pacchetti si avvicina alla capacità del collegamento, si rilevano lunghi ritardi*

### Scenario 2
Quando i buffer dei router hanno **dimensione limitata**, i pacchetti che arrivano a un buffer pieno vengono scartati. Se la connessione è affidabile, i pacchetti persi o corrotti vengono ritrasmessi.

Indichiamo con $\lambda_{in}$ il tasso di trasmissione verso le socket e con $\lambda^{'}_{in}$ il tasso di invio dei segmenti da parte del livello di trasporto (**carico offerto**).

![[SOR/RETI/img/img73.png|center|500]]
![[SOR/RETI/img/img74.png|center|300]]

Se il mittente potesse inviare dati solo quando è a conoscenza che c'è spazio nei router, non ci sarebbero perdite e quindi $\lambda_{in} = \lambda^{'}_{in}$. Il throughput sarebbe pari a $\lambda_{in}$ e la velocità di invio non supererebbe $R/2$.

Realisticamente il mittente non è in grado di conoscere perfettamente lo stato del router, quindi dei pacchetti vanno persi a causa dei buffer pieni ed in questi casi è necessaria la ritrasmissione. Consideriamo quindi il caso in cui il mittente ritrasmette un pacchetto quando è certo della sua perdita. 

![[SOR/RETI/img/img75.png|center|300]]

Le prestazioni avrebbero l'aspetto del grafico. Supponiamo che il carico offerto è $\lambda^{'}_{in}=R/2$. In questo caso il tasso con cui i dati vengono consegnati all'applicazione è $R/3$, quindi su $0.5 \:R$ unità di dati trasmessi, $0.333\: R$ byte/s sono dati originali, mentre $0.166\: R$ byte/s sono quelli ritrasmessi. 
Un'altro costo relativo alla congestione è quindi la *necessità di effettuare ritrasmissioni per compensare la perdita di pacchetti dovuta all'overflow nei buffer*.

Se un pacchetto subisce ritardi ma non viene perso, il mittente potrebbe ritrasmetterlo inutilmente. Entrambi i pacchetti (originale e ritrasmesso) arrivano al destinatario, che scarta una copia. Il *lavoro del router per instradare la copia ritrasmessa è sprecato*, riducendo l'efficienza.

![[SOR/RETI/img/img76.png|center|300]]

La figura confronta throughput e traffico immesso nella rete nell'ipotesi che ciascun pacchetto venga instradato mediamente due volte. In questo scenario il throughput assumerà asintoticamente il valore $R/4$ quando il carico offerto tende a $R/2$

## Approcci al controllo della congestione
Consideriamo due casi pratici: 
- **Controllo di congestione end-to-end**. Il controllo della congestione è gestito solo dagli host, che deducono la congestione attraverso segnali come la perdita di pacchetti o l'aumento dei ritardi.
- **Controllo di congestione assistito dalla rete**. La rete stessa invia segnali di congestione, come i _chokepacket_, per indicare agli host di ridurre la velocità di trasmissione.

![[SOR/RETI/img/img78.png|center|500]]

## Controllo di congestione TCP
TCP utilizza il controllo della congestione end-to-end e impone a ciascun mittente un limite al tasso di invio sulla propria connessione in funzione della congestione di rete percepita.

Il mittente può aumentare la velocità di trasmissione fino a quando non si verifica la perdita di pacchetti, sintomo di congestione; in questo caso inizia a diminuire la velocità di invio.

Il meccanismo di controllo di congestione TCP fa tenere traccia agli host della variabile **finestra di congestione**, indicata con `cwnd`, che pone un limite alla velocità di immissione di traffico sulla rete da parte del mittente.
In particolare, la quantità di dati che non hanno ancora ricevuto ACK, non può superare il minimo tra i valori di `cwnd` e `rwnd` $$\text{LastByteSent - LastByteAcked}\leq\min\{\text{cwnd, rwnd}\}$$Assumendo che il buffer di ricezione sia sufficientemente grande, possiamo trascurare il vincolo di `rwnd`.
Il valore di `cwnd` viene regolato dinamicamente in risposta alla congestione della rete osservata.

Il mittente TCP percepisce la presenza di congestione sul percorso mediante gli *eventi di perdita*, quindi al verificarsi di timeout o alla ricezione di tre ACK duplicati dal destinatario. In presenza di congestione, i router sul percorso vanno in overflow, eliminando i datagrammi che non possono accedere al buffer.

L'approccio del controllo di congestione TCP è quello dell'**incremento additivo e decremento moltiplicativo (AIMD)**:
- La velocità di invio aumenta linearmente quando non ci sono perdite.
- Quando c'è congestione, il tasso di invio viene dimezzato.

![[SOR/RETI/img/img80.png|center|500]]

Vediamo i dettagli dell'algoritmo di controllo di congestione TCP, che presenta tre fasi principali:
1. slow start
2. congestion avoidance
3. fast recovery
Le prime 2 sono componenti obbligatorie di TCP, mentre l'ultima è suggerita, ma non obbligatoria.

### Slow start
Quando si stabilisce la connessione TCP, il valore di `cwnd` viene inizializzato ad 1 MSS, che comporta una velocità di invio iniziale di circa $MSS/RTT$. 

>**Es.**
>Se $MSS=500$ byte e $RTT=200$ ms, la velocità iniziale è di circa $20$ kbps.

Dato che la banda disponibile alla connessione può essere molto più grande del valore $MSS/RTT$, il mittente TCP vuole scoprire questa larghezza di banda. Durante la fase iniziale, il valore di `cwnd` parte da 1 MSS, aumentando di 1 ogni volta che un segmento riceve ACK.

Nel dettaglio: TCP invia il primo segmento nella rete. Se ottiene indietro un ACK prima della verifica di un evento di perdita, incrementa `cwnd` di 1 MSS ed invia due segmenti di dimensione massima. Se riceve entrambi gli ACK, aumenta di 1 MSS per ogni segmento inviato, portando la velocitò a 4 MSS e così via. Quindi la velocità parte lentamente, ma cresce in modo esponenziale durante la fase di slow start. 

![[SOR/RETI/img/img79.png|center|300]]

La crescita esponenziale quando dovrebbe terminare?
1. Se c'è un evento di perdita indicato da un timeout, il mittente pone il valore di `cwnd` pari a 1 e inizia nuovamente il processo di slow start. Inoltre pone una variabile di stato `ssthresh = cwnd/2`, ovvero metà del valore che aveva la finestra di congestione quando è stata rilevata la congestione.
2. Poiché il valore di `ssthresh` è impostato alla metà del valore di `cwnd` all'ultimo rilievo della connessione, è inutile continuare a raddoppiare il valore di `cwnd` quando raggiunge o sorpassa il valore di `ssthresh`. Quindi la *fase di slow start termina* quando il valore di `cwnd` è pari a quello di `ssthresh`, entrando in fase di **congestion avoidance**.

### Congestion avoidance
Quando TCP entra nello stato di congestion avoidance, il valore di `cwnd` è la metà di quello che aveva l'ultima volta in cui è stata rilevata la congestione, quindi invece di raddoppiare il suo valore, lo incrementa di 1 MSS ogni RTT. 

Quando si verifica un timeout, l'algoritmo di congestion avoidance si comporta allo stesso modo di slow start: pone a 1 MSS il valore di `cwnd` e il valore di `ssthresh` viene impostato alla metà del valore di `cwnd` al momento del timeout ed entra nello stato di fast recovery.

### Fast recovery
Durante questa fase, il valore di `cwnd` è aumentato di 1 MSS per ogni ACK duplicato ricevuto relativamente al segmento perso che ha causato l'entrata di TCP nello stato di fast recovery. Quando *arriva un ACK per il segmento perso*, TCP entra nello stato di congestion avoidance dopo aver ridotto il valore di `cwnd`. Se si verifica un *timeout*, avviene una transizione dallo stato di fast recoevery a quello di slow start. La versione di TCP più recente, ovvero **TCP Reno** adotta la fase di fast recovery. 

### TCP CUBIC
L'approccio AIMD è una strategia che dimezza la velocità di invio al verificarsi di un evento di perdita e la aumenta piuttosto lentamente nel tempo. Ci chiediamo se esiste un modo migliore di AIMD per sondare la larghezza di banda utilizzabile. 

Migliora AIMD aumentando rapidamente la velocità di invio quando si è lontani dalla soglia di congestione, e rallenta l'incremento vicino al valore massimo di `cwnd`.

Sia $W_{max}$ la dimensione della `cwnd` nell'istante in cui viene rilevata la perdita. Sia $K$ l'istante futuro in cui la `cwnd` raggiungerà nuovamente il valore $W_{max}$. TCP CUBIC *incrementa maggiormente* la dimensione di `cwnd` quando è lontano dal valore $K$ (e quindi quando il valore di `cwnd` è lontano dal valore $W_{max}$), mentre *decrementa lentamente* in prossimità di $W_{max}$, e solo in questo momento esamina con maggior cautela la larghezza di banda.

![[SOR/RETI/img/img81.png|center|500]]

### Controllo di congestione basato su RTT
TCP, sia CUBIC che classico, rilevano la congestione primariamente con la perdita di pacchetti. Tuttavia la congestione può essere rilevata in altri modi, ad esempio analizzando l'RTT.

Misurando l'RTT, posso indiciduare il valore $RTT_{min}$, ovvero il minimo delle misurazioni degli RTT, che si misura quando il percorso non è congestionato. Il thruoghput massimo varrà allora $t_{max}=\text{cwnd}/RTT_{min}$. 
L'idea di TCP Vegas (basato su misura dell'RTT) è la seguente:
- se il throughput effettivo ($\text{cwnd}/RTT$) è vicino al valore di $t_{max}$, allora non c'è congestione e posso aumentare la velocità di invio.
- se il throughput effettivo è inferiore rispetto a $t_{max}$ allora il percorso è congestionato e viene ridotta la velocità di invio per far svuotare la coda ed evitare congestione.

## ECN (Explicit Congestion Notification)
ECN è la forma di controllo di congestione *assistita dalla rete*. 

Vengono utilizzati due bit nel campo Type of Service nell'intestazione IP. Se un router è congestionato, imposta i bit e invia il pacchetto IP al destinatario, che a sua volta informa il mittente. Quando un destinatario riceve un indicazione di congestione ECN, informa il mittente TCP impostando il bit ECE all'interno di un segmento ACK ed il mittente reagisce dimezzando la finestra di congestione.

![[SOR/RETI/img/img83.png|center|500]]

## TCP Fairness
Consideriamo $K$ connessioni TCP, ciascuna con differente percorso end-to-end, ma che passano attraverso lo stesso collegamento con capacità trasmissiva di $R$ bps, che costituisce il bottleneck del sistema.

Si dice che un meccanismo di controllo di congestione è equo se la velocità di trasmissione di ciascuna connessione è circa $R/K$.
TCP è fair sotto le seguenti assunzioni: le connessioni TCP hanno tutte lo stesso RTT e il numero di sessioni in congestion avoidance è fisso.

# Evoluzione delle funzionalità del livello di trasporto
TCP e UDP sono i principali protocolli di trasporto Internet, ma nel tempo sono state sviluppate diverse varianti di TCP per scenari specifici.

![[SOR/RETI/img/img82.png|center|500]]

Se i servizi offerti da UDP e TCP non sono adatti ai servizi del livello di trasporto richiesto da un'applicazione, è possibile utilizzare un proprio protocollo. Un applicazione potrebbe necessitare di funzionalità specifiche fornite da TCP e necessità più servizi di quelli forniti da UDP.

**QUIC (Quick UDP Internet Connections)** è un protocollo a livello di applicazione progettato per migliorare le prestazioni a livello di trasporto per HTTP. Le sue caratteristiche sono:
- **orientato alla connessione e sicuro**: utilizza un handshake che incorpora la crittografia dei dati, riducendo il tempo necessario per stabilire una connessione crittografata tra due end point
- **flussi**: consente il multiplexing di diversi flussi a livello di applicazione attraverso una singola connessione QUIC, consentendo di inviare e ricevere dati in parallelo
- **trasferimento dati affidabile e con controllo di congestione**: gli algoritmo di controllo di congestione e di trasferimento dati sono simili a quelli usati da TCP
1zIl livello di trasporto ha il compito di fornire una **comunicazione logica** direttamente ai processi applicativi in esecuzione sui diversi host.
Con comunicazione logica intendiamo che, dal punto di vista dell'applicazione, tutto funzioni come se gli host che eseguono i processi fossero direttamente connessi tra di loro. 

I protocolli di trasporto sono eseguiti nei sistemi periferici:
- lato mittente: converte i messaggi ricevuti dal processo applicativo in **segmenti**, suddividendo i messaggi applicativi in parti più piccole e aggiungendo un'intestazione di trasporto, passandolo infine al livello di rete, dove viene incapsulato in un datagramma.
 - lato ricevente: il livello di rete estrae il segmento dal datagramma, lo passa al livello di trasporto, che elabora il segmento ricevuto rendendo disponibili all'applicazione i dati del segmento.
I protocolli di trasporto più utilizzati sono TCP e UDP.

# Multiplexing e Demultiplexing
Analizziamo come il servizio di trasporto da host ad host fornito dal livello di rete, può diventare un servizio di trasporto da processo a processo per le applicazioni in esecuzione sugli host.

Nell'host destinatario, il livello di trasporto, che riceve i segmenti dal livello di rete, ha il compito di consegnare i dati al processo applicativo appropriato. Supponiamo che siamo al computer, abbiamo richiesto una pagina web (sessione HTTP) e contemporaneamente abbiamo in esecuzione una sessione FTP e due Telnet. Il livello di trasporto, quando riceve i dati dal livello di rete, deve indirizzali ai processi corretti. Vediamo come.

Ricordiamo che un processo può gestire uno o più socket, attraverso cui entrano/escono dati, quindi il livello di trasporto nell'host ricevente trasferisce i dati ad un socket, che fa da intermediario. Ogni socket avrà un ID unico, il cui formato dipende dal fatto che si tratti di UDP o TCP.

- Lato ricevente: il livello di trasporto esamina i campi del segmento per identificare il socket di ricezione, a cui dirige il segmento. Il compito di trasportare i segmenti verso il socket corretto è detto **demultiplexing**.
- Lato mittente: il livello di trasporto raduna i frammenti di dati da diversi socket sull'host origine e li incapsula, creando dei segmenti da passare al livello di rete. L'operazione è detta **multiplexing**

Vediamo nel dettagli come funziona il demultiplexing e il multiplexing. Il multiplexing a livello di trasporto richiede che i socket abbiano indentificatori unico e che ciascun segmento presenti dei campi che indicano il socket a cui va consegnato. I campi in questione sono il **numero di porta di origine e di destinazione**. I numeri di porta sono di 16 bit e vanno da 0 a 65535, quelli da 0 a 1023 sono *riservati* per i protocolli applicativi noti, come HTTP (80) FTP (21).

Quindi per il demultiplexing, ogni socket deve avere un numero di porta e, quando l'host riceve il datagramma, esamina il numero di porta di destinazione e dirige il segmento verso il corrispondente socket. 

## Demultiplexing senza connessione
Quando si crea un socket, per specificare un numero di porta si utilizza il metodo `bind()`, altrimenti viene assegnato automaticamente un numero di porta tra 1024 e 65535: 
```Python
mySocket = socket(AF_INET, SOCK_DGRAM) #crea un socket UDP
mySocket.bind(('',9157)) #assegnata la porta 9157
```
Dopo la creazione, quando si crea il datagramma da inviare al socket, si deve specificare l'indirizzo IP ed il numero di porta del destinatario. Il segmento viene passato poi al livello di rete che effettua un tentativo best-effort di consegna all'host di destinazione. Quando il segmento arriva all'host destinatario, il livello di trasporto esamina il numero di porta di destinazione e invia il segmento UDP al socket relativo con quel numero di porta.
Osserviamo che un socket UDP viene identificato da una coppia (IP, numero di porta) quindi, due segmenti UDP che hanno diversi indirizzi IP e/o differenti numeri di porta di origine, ma hanno lo stesso IP e lo stesso numero di porta di destinazione, vengono diretti allo stesso processo di destinazione tramite lo stesso socket.

![[SOR/RETI/img/img35.png|center|500]]

## Demultiplexing orientato alla connessione
Per comprendere il demultiplexing TCP, bisogna analizzare le differenze tra socket TCP e socket UDP.
Il socket TCP è identificato da quattro parametri:
- indirizzo IP di origine
- numero di porta di origine
- indirizzo IP destinatario
- numero di porta destinatario
Quando un segmento TCP giunge dalla rete in un host, questo utilizza i quattro valori per dirigere il segmento verso il socket corretto.
A differenza di UDP, due segmenti TCP in arrivo, con IP di origine o numero di porta di origine diversi, vengono diretti a due socket differenti, anche se hanno indirizzo IP e porta di destinazione uguali. 

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

Il compito di un protocollo di trasferimento dati affidabile (destra) è l'implementazione dell'astrazione del servizio. Questo obiettivo è reso complicato dall'inaffidabilità del livello al di sotto del protocollo di trasferimento. Ad esempio TCP è un protocollo di trasferimento dati affidabile implementato appoggiandosi ad un livello di rete (IP) che non è affidabile end-to-end. La complessità di un protocollo di trasferimento dati affidabile dipende dalle caratteristiche del canale inaffidabile.

La figura mostra le interfacce del protocollo di trasferimento affidabile. Il lato mittente del protocollo è invocato mediante una chiamata a `rdt_send()`, che trasferisce i dati da consegnare al livello superiore del destinatario.  La chiamata `udt_send()` è utilizzata da `rdt` per trasferire il pacchetto al ricevente tramite il canale inaffidabile. Quando un pacchetto raggiunge il lato ricevente, viene chiamata `rdt_rcv()` e quando il protocollo vuole consegnare i dati al livello superiori, chiama `deliver_data()`.

## Costruzione protocollo di trasferimento dati affidabile
### rdt1.0, canale affidabile
Il caso più semplice è quello in cui il canale sottostante è completamente affidabile, quindi non si verificano errori nei bit e non avvengono perdite di pacchetti.

![[SOR/RETI/img/img41.png|center|500]]

- il mittente accetta i dati dal livello superiore tramite `rdt_send(data)`, crea un  pacchetto contenente i dati con `packet = make_pkt(data)`  e lo invia sul canale `udt_send(packet)`.
- il ricevente raccoglie i pacchetti dal basso con `rdt_rcv(packet)`, rimuove i dati dai pacchetti con `extract(packet, data)` e li passa al livello superiore con `deliver_data(data)`.

### rdt2.0, canale con errori su bit
Un modello più realistico è quello del canale in cui i bit di un pacchetto possono essere corrotti. La sfida è dunque capire come recuperare gli errori. 
Questo è possibile mediante un utilizzo di **notifiche positive (ACK) e negative (NAK)** ed un invio di tipo *stop and wait*. 
Il mittente invia un pacchetto per volta, attendendo la risposta del destinatario: se il pacchetto arriva al ricevente correttamente, avviene un ACK, mentre se il pacchetto arriva con degli errori, il ricevente comunica un NAK al mittente che procede a ritrasmettere il pacchetto.
I protocolli basati sulla ritrasmissione sono detti **protocolli ARQ (Automatic Repeat reQuest)**.

![[SOR/RETI/img/img42.png|center|500]]

Il **lato mittente** presenta due stati: in quello di sinistra il protocollo attende i dati da raccogliere. Quando si verifica l'evento `rdt_send(data)`, il mittente crea un pacchetto `sndpkt` contenente i dati da inviare ed il checksum (nel caso di pacchetto UDP) e spedisce il pacchetto mediante `udt_send(sndpkt)`. Nello stato di destra il mittente attende un pacchetto ACK o NAK dal destinatario: 
- se riceve ACK (evento `rdt_rcv(rcvpkt) && isACK(rcvpkt)`), il mittente sa che il pacchetto è stato ricevuto correttamente e torna allo stato di attesa dei dati
- se riceve NAK (evento `rdt_rcv(rcvpkt) && isNAK(rcvpkt)`), il protocollo ritrasmette l'ultimo pacchetto, attendendo nuovamente una risposta.

![[SOR/RETI/img/img43.png|center|500]]

Il **lato ricevente** ha ancora un solo stato. All'arrivo del pacchetto risponde con ACK o NAK se il pacchetto è corrotto o meno.
- nel caso di pacchetto corrotto (evento `rdt_rcv(rcvpkt) && corrupt(rcvpkt)`), viene creato un pacchetto NAK (`sndpkt=make_pkt(NAK)`) ed inviato al mittente (`udt_send(sndpkt)`). 
- nel caso di pacchetto senza errori (evento `rdt_rcv(rcvpkt) && notcorrupt(rcvpkt)`), rimuove i dati dai pacchetti con `extract(packet, data)` e li passa al livello superiore con `deliver_data(data)`, creando `sndpkt=make_pkt(ACK)` e inviando `udt_send(sndpkt)` un pacchetto ACK.
Questo metodo sembra funionale, ma ha un grave difetto: non possiamo sapere se i pacchetti ACK o NAK sono alterati anch'essi. 

### rdt2.1, gestione di ACK/NAK alterati
Per gestire gli ACK e i NAK corrotti ci sono tre possibilità:
- introduzione di un nuovo tipo di pacchetto dal mittente al destinatario, che viene inviato in caso di corruzione del pacchetto ACK/NAK, per comunicare che il messaggio ricevuto non era comprensibile. Non è una soluzione perché anche il messaggio di conferma può essere corrotto.
- aggiunta di un bit di checksum, ma risolve il problema di pacchetti danneggiati e non sui pacchetti persi
- rinviare il pacchetto in caso di ACK/NAK alterati. Introduce **pacchetti duplicati** e il destinatario non sa se l'ACK/NAK è stato ricevuto correttamente, quindi non può sapere se un pacchetto in arrivo contiene dati nuovi o è una ritrasmissione.
Una soluzione consiste nell'aggiungere un campo al pacchetto dati, obbligando il mittente a numerare i pacchetti con un **numero di sequenza**. Al destinatario basterà controllare questo numero per sapere se rappresenta una ritrasmissione o sono dati nuvovi.

![[SOR/RETI/img/img44.png|center|500]]
![[SOR/RETI/img/img45.png|center|500]]

Osserviamo che sia mittente che destinatario hanno il doppio degli stati, in quanto devono riflettere il fatto che il pacchetto in invio o in ricezione abbia numero di sequenza 0 o 1. Notiamo inoltre che le azioni negli stati sono speculari, in quanto l'unica differenza riguarda la gestione del numero di sequenza.  

### rdt2.2, protocollo senza NAK
Il funzionamento è lo stesso di rdt2.1, ma utilizzando solamente gli ACK. In questo caso il destinatario invia un ACK per l'ultimo pacchetto ricevuto correttamente, indicando esplicitamente il numero di sequenza del pacchetto con l'ACK, mentre un ACK duplicato inviato al mittente è analogo ad un NAK, quindi il pacchetto viene ritrasmesso. Il protocollo TCP utilizza questo approccio.

![[SOR/RETI/img/img46.png|center|500]]
![[SOR/RETI/img/img47.png|center|500]]

### rdt3.0, canali con errori e perdite
Negli esempi precedenti abbiamo supposto che il canale di trasmissione danneggiasse solamente i bit. Supponiamo ora che il canale possa anche smarrire i pacchetti. 
Il protocollo deve quindi preoccuparsi di rilevare lo smarrimento e cosa fare quando questo avviene, introducendo un nuovo meccanismo.
Supponiamo che il mittente spedisca un pacchetto dati e che questo o l'ACK corrispondente vengano perduti. In questi casi, il mittente non riceverà alcuna risposta dal destinatario. Per essere certo dello smarrimento del pacchetto, il mittente deve essere disposto ad aspettare un tempo sufficiente dopo il quale ritrasmetterà il pacchetto di dati. 
Il problema sta nello scegliere adeguatamente il tempo da attendere. L'idea è quella di attendere un tempo che è la somma del ritardo tra andata e ritorno più il tempo necessario all'elaborazione, ma questo ritardo è difficile da stimare nel caso peggiore. 
L'approccio utilizzato è quello di scegliere una quantità di tempo per cui la perdita di pacchetti risulti probabile: se non si riceve un ACK in questo periodo di tempo, il pacchetto viene ritrasmesso. Notiamo che, il mittente potrebbe ritrasmettere un pacchetto che supera la quantità di tempo ammessa, anche se né il pacchetto stesso né l'ACK sono stati smarriti. Questo porta alla possibilità di **pacchetti duplicati**, ma questo è risolto grazie ai *numeri di sequenza*. 

Il mittente, non sa se un pacchetto o il relativo ACK sia stato effettivamente perso o se questi pacchetti abbiano subito un ritardo molto lungo. In ogni caso la soluzione è sempre la ritrasmissione. Il meccanismo di ritrasmissione richiede un *contatore* in grado di segnalare la scadenza del tempo impostato. Il mittente quindi, ogni volta che invia un pacchetto, inizializza il timer, eventualmente rispondere all'interrupt generato dal timer con l'azione appropriata e fermare il contatore quando il pacchetto viene trasmesso correttamente.

![[SOR/RETI/img/img48.png|center|500]]


#### Analisi delle prestazioni rdt3.0
Il protocollo rdt3.0 è corretto funzionalmente, ma le prestazioni non sono il massimo in quanto si tratta di un protocollo di tipo stop-and-wait. Analizziamo la velocità di trasferimento, considerando questo caso. 

Due host, sulla coste opposte degli Strati Uniti. L'[[Livello Applicazione#Tempi di risposta|RTT]] è circa di 30 ms. Supponiamo che i due sistemi siano connessi da un canale con velocità di trasmissione $R$ di $1$ Gbps ($10^9$ bit al secondo). Con pacchetti di dimensione $L$ di $1000$ byte ($8000$ bit), il tempo richiesto per trasmettere il pacchetto sul collegamento è $$d_{t}=\frac{L}{R}=\frac{8000\text{ bit}}{10^{9}\text{ bit/s}}=8\:\micro s$$
![[SOR/RETI/img/img49.png|center|300]]

Se il mittente inizia ad inviare pacchetti a $t=0$, l'ultimo bit entra nel canale dal lato mittente al tempo $t=\frac{L}{R}=8\:\micro s$. Il pacchetto effettua poi un viaggio di $15$ ms e l'ultimo bit giunge al destinatario a $t=\frac{RTT}{2}+ \frac{L}{R}=15.008$ ms. Di conseguenza l'ACK giunge al mittente all'istante $t=RTT+ \frac{L}{R}=30.008$ ms. Dopo questo lasso di tempo, il mittente puù trasmettere il messaggio successivo, quindi in un arco di $30.008$ ms il mittente ha trasmesso per soli $0.008$ ms. Definiamo ora l'utilizzo del mittente come la frazione di tempo in cui il mittente è stato effettivamente occupato nell'invio di bit $$U_\text{mittente}=\frac{L/R}{RTT+L/R}=\frac{0.008}{30.008}=0.00027$$
Il mittente è stato attivo per soli 2.7 centesimi dell'1% di tempo, con un throughput effettivo di $$U_\text{mittente}\cdot R=267\text{ kbps}$$ nonostante fosse disponibile un collegamento da $1$ Gbps. Il protocollo di tipo stop-and-wait limita quindi notevolmente le prestazioni del canale. 

### Protocolli per il trasferimento dati affidabile con pipeline 
La soluzione al problema delle prestazioni di rdt3.0 è quella di consentire al mittente di inviare più pacchetti senza aspettare gli ACK.

![[SOR/RETI/img/img50.png|center|500]]

Se si consente al mittente di trasmettere tre pacchetti, l'utilizzo viene triplicato. Le conseguenze di utilizzo di un protocollo di trasferimento con pipeline sono:
- incremento dei numeri di sequenza disponibili
- memorizzare in un buffer più di un pacchetto. Il mittente dovrà memorizzare i pacchetti trasmessi il cui ACK non è stato ricevuto. 

#### Go-Back-N (GBN)
In un **protocollo GBN**, il mittente può trasmettere più pacchetti senza attendere acknowledge, ma non può avere più di un numero massimo consentito $N$ di pacchetti in attesa di ACK nella pipeline. 

![[SOR/RETI/img/img51.png|center|500]]

Nella *window size* abbiamo i pacchetti inviati ma non ancora riscontrati e quelli pronti per l'invio. IL campo *base* indica il numero di sequenza del pacchetto più vecchio che non ha ricevuto ACK, mentre *nextseqnum* indica il numero di sequenza del prossimo pacchetto da inviare.

![[SOR/RETI/img/img52.png|center|500]]
Il mittente GBN deve rispondere a tre tipi di evento:
- **invocazione dall'alto**. Alla chiamata `rdt_send()`, per prima cosa il mittente controlla se nella finestra ci siano $N$ pacchetti in sospeso. In caso negativo, crea e invia un nuovo pacchetto. Se la finestra è piena il mittente restituisce i dati al livello superiore e ritenterà più tardi. A livello implementativo i dati venono memorizzati in un buffer o viene implementato un meccanismo di sincronizzazione che consente la chiamata `rdt_send()` solo quando la finestra non è piena
- **ricezione di un ACK**. L'ACK con numero di sequenza $n$ è considerato un **ACK cumulativo**, che indica che tutti i pacchetti con numero di sequenza minore o uguale ad $n$ sono stati correttametne ricevuti
- **evento di timeout**. Si usa un contatore per il problema di pacchetti o ACK persi. Quando si verifica un timeout, il mittente invia nuovamente **tutti** i pacchetti con numero di sequenza maggiore uguale ad $n$ (ultimo ACK ricevuto).

![[SOR/RETI/img/img53.png|center|500]]

Nel lato destinatario, se un pacchetto con numero di sequenza $n$ viene ricevuto correttamente ed è in ordine (ha numero di sequenza progressivo), viene inviato un ACK per il pacchetto e consegna i dati a livello superiore, memorizzando `rcv_base`, che indica l'ultimo numero di sequenza ottenuto . Se riceve uin pacchetto fuori sequenza può scartarlo e rimandare un ACK per il apcchetto con il numero di sequenza più alto

#### Ripetizione selettiva
I **protocolli a ripetizione selettiva** evitano le ritrasmissioni non necessarie facendo ritrasmettere al mittente solo i pacchetti su cui esistono alterazioni o smarrimenti. Questo obbliga il destinatario a mandare ACK specifici per i pacchetti ricevuti in modo corretto. Si utilizza nuovamente un'ampiezza di finestra pari a $N$ per limitare il numero di pacchetti senza ACK, ma a differenza di GBN, il mittente avrà già ricevuto gli ACK di qualche pacchetto nella finestra.

![[SOR/RETI/img/img54.png|center|500]]

Il mittente svolge le seguenti operazioni in base agli aventi:
- **dati ricevuti dall'alto**. Alla ricezione di dati, il mittente controlla il succesisvo numero di sequenza disponibile per il pacchetto. Se è all'interno della finestra del mittente, i dati vengono impacchettati e inviati, altrimenti sono salvati in un buffer o restituiti al livello superiore
- **timeout**. Utilizza ancora i contatori per la perdita di pacchetti, anche se ora ogni pacchetto ha un proprio timer logico dato che viene ritrasmesso un solo pacchetto
- **ACK ricevuto**. Alla ricezione, il mittente etichietta il pacchetto $n$ come ricevuto. Se $n$ è il numero di sequenza più piccolo, la base della finestra avanza al successivo numero di sequenza del pacchetto non riscontrato.

![[SOR/RETI/img/img55.png|center|500]]

Il ricevente si comporta nel seguente modo:
- **il pacchetto con n nella sequenza $[\text{rcv\_base, rcv\_base+N-1}]$ viene ricevuto correttamente**. In questo caso il pacchetto riceevuto è nella finestra del ricevente e viene restituito un ACK al mittente. Se il pacchetto non era già stato ricevuto viene inserito nel buffer. Se ha numero di sequenza uguale a `rcv_base` allora il pacchetto e i pacchetti nel buffer con numeri conescutivi a `rcv_base` vengono consegnati al livello superiore. Ad esempio quando si riceve un pacchetot con numero di sequenza `rcv_base=2` è possibile consegnarlo a livello superiore con i pacchetti 3, 4, e 5
- **si riceve il pacchetto con $n$ nella sequenza $[\text{rcv\_base-N, rcv\_base-1}]$**. In questo caso si genera un ACK, anche se il pacchetto è già stato riscontrato. Questo evento significa che gli ACK dei pacchetti già consegnati all'applicazione, non sono stati ricevuti dal mittente
- si ignora il pacchetto in ogni altro caso

![[SOR/RETI/img/img57.png|center|500]]

# TCP
TCP è il protocollo di trasporto affidabile e orientato alla connessione che utilizza molti dei principi visti in precedenza, come rilevazione degli errori, ritrasmissioni, acknowledgment cumulativi ecc.
TCP è detto *orientato alla connessione* in quanto prima dello scambio dei dati, i processi che intendono farlo devono effettuare l'handshake. 
La connessione TCP offre un servizio **full-duplex**, quindi i dati possono fluire in entrambe le direzione nello stesso momento. Inoltre, la connessione è **point-to-point**, ovvero ha luogo tra un singolo mittente e un singolo destinatario, senza possibilità di *multicast*.

Dopo aver instaurato la connessione TCP mediante **handshake a tre vie**, i due processi possono scambiarsi dati. 
Consideriamo l'invio di dati dal client al server

![[SOR/RETI/img/img58.png|center|500]]

Il primo manda un flusso di dati attraverso il socket, che una volta attraversato sono nelle mani di TCP, che dirige i dati al **buffer di invio**.
La quantità massima di dati prelevabili e posizionabili in un segmento è detta **dimensione massima di segmento (MSS)** ed è un valore che viene impostato determinando prima la lunghezza del frame più grande che può essere inviato a livello di collegamento (**unità trasmissiva massima MTU**) e poi scegliendo un MSS tale che il segmento TCP stia all'interno di un singolo frame a livello di collegamento considerando anche la lunghezza dell'intestazione. 

![[SOR/RETI/img/img59.png|center|500]]


TCP accoppia ogni blocco di dati a una intestazione TCP, andando a formare dei **segmenti TCP**, che vengono passati al livello di rete, incapsulati in datagrammi IP e immessi nella rete. Quando all'altro capo giunge un segmento, i dati bengono memorizzati nel **buffer di ricezione TCP** e vengono poi inviati all'applicazione tramite il socket.
## Struttura dei segmenti TCP
Il segmento TCP consiste di campi *Intestazione* e del campo *Dati* provenienti dall'applicazione. La MSS limita la dimensione massima del campo Dati, infatti quando TCP invia un file di grandi dimensioni, questo è frammentato in porzioni di dimensioni MSS.

In figura possiamo vedere la struttura dei segmenti TCP.

![[SOR/RETI/img/img60.png|center|500]]

Come per [[#UDP]] questa contiene i **numeri di porta di origine e destinazione** e il campo **checksum**. Inoltre comprende i seguenti campi:
- **Numero di sequenza** e **numero di ACK**: utilizzati dal mittente e destinatario per il trasferimento dati affidabile
- **Finestra di ricezione**: utilizzato per il controllo di flusso. Indica il numero di byte che il destinatario può accettare
- **Lunghezza dell'intestazione**
- **Opzioni**: facolatativo e di lunghezza variabile. 
- **Flag**: 
	- il bit **ACK** indica che il segmento contiene un ACK per un segmento ricevuto correttamente
	- **RST, SYN, FIN** utilizzati per impostare e chiudere la connessione
	- **CWR, ECE** utilizzati nel controllo della congestione
	- **PSH**: se ha valore 1 il destinatario dovrebbe inviare immediatamente i dati al livello superiore
	- **URG** indica la presenza di dati che il mittente ha marcato come urgenti

### Numeri di sequenza e numeri di ACK
I numeri di sequenza e di ACK sono tra i campi più importanti dell'intestazione TCP e rappresentano una parte critica del servizio di trasferimento dati affidabile di TCP. 
TCP vede i dati come un flusso di byte ordinati e applica i numeri di sequenza al flusso di byte trasmessi e non alla serie di segmenti. 
Il **numero di sequenza per un segmento** è il numero nel flusso di byte del primo byte del segmento.

Supponiamo che A vuole inviare un file (flusso di dati) da 500.000 byte, che l'MSS valga 1000 byte e che il primo byte del flusso sia numerato con 0. TCP costruisce quindi $500.000/1000=500$ segmenti per il flusso, applicando al primo segmento il numero 0, al secondo 1000 e così via

![[SOR/RETI/img/img61.png|center|500]]

Il **numero di ACK** che l'host A scrive nei propri segmenti, è il *numero di sequenza* del prossimo byte atteso. Se trasmetto i primi 10 byte (quindi ho un segmento con numero di sequenza = 0 che contiene 10 byte) il numero di ACK sarà 10 (perché dopo aver ricevuto i byte da 0 a 9, attendo l'arrivo del byte 10)

![[SOR/RETI/img/img62.png|center|500]]![[SOR/RETI/img/img63.png|center|500]]

## Timeout e stima dell'RTT
TCP utilizza un meccanismo di timeout e ritrasmissione per recuperare i segmenti persi. Nonostante il meccanismo di timeout risulti semplice, l'implementazione in TCP crea alcuni problemi. Il problema più grande è la durata degli intervalli di timeout, che deve necessariamente essere maggiore dell'RTT, ma quanto maggiore deve essere?

Consideriamo come il protocollo stimi l'RTT. Denotiamo con *SampleRTT* la misura dell'RTT di un segmento, ovvero il tempo che passa tra l'invio del segmento e la ricezione dell'ACK. Questo valore può variare in base alla congestione nei router e al carico sugli host e a causa di questo il valore di SampleRTT può essere atipico. 

Possiamo effettuare una stima calcolando una media dei valori di SampleRTT. Ogni volta che si ottiene un nuovo SampleRTT, si aggiorna la stima nel seguente modo $$\text{EstimatedRTT}=(1-\alpha)\cdot\text{EstimatedRTT}+\alpha\cdot\text{SampleRTT}$$
Questo tipo di stima attribuisce maggiore importanza ai campioni recenti rispetto a quelli vecchi, il che è migliore in quanto quelli più recenti riflettono meglio la congestione della rete. 

![[SOR/RETI/img/img65.png|center|500]]

Oltre alla stima dell'RTT è importante possedere la misura della sua variabilità, che è la stima di quanto SampleRTT si discosta da EstimatedRTT $$\text{DevRTT}=(1-\beta)\cdot\text{DevRTT}+\beta\cdot\Big|\text{SampleRTT}-\text{EstimatedRTT}\Big|$$
Se i valori dell'RTT presentano fluttuazioni limitate, allora DevRTT sarà piccolo.

Introdotti questi valori possiamo valutare l'intervallo del timeout per TCP. Ovviamente il timeout non può essere inferiore al valore di EstimatedRTT, altrimenti si verificano ritrasmissioni non necessarie, ma allo stesso tempo non può essere molto maggiore di EstimatedRTT altrimenti i segmenti non sarebbero ritrasmessi rapidamente, comportando gravi ritardi.

La soluzione è quello di impostare il timeout a EstimatedRTT più un certo margine, dettato proprio da DevRTT $$\text{TimeoutInterval}=\text{EstimatedRTT}+4\cdot\text{DevRTT}$$
## Trasferimento dati affidabile
TCP crea un servizio di trasporto dati affidabile al di sopra dell'inaffidabile IP, assicurando che il flusso di byte inviato non subisca perdite o alterazioni. Vediamo come.

Supponiamo che l'host A stia inviando un file di grandi dimensioni all'host B. Esistono tre eventi principali relativi alla trasmissione e ritrasmissione, vediamo come si comporta il mittente:
- **dati provenienti dall'applicazione**: TCP incapsula in un segmento i dati che arrivano dall'applicazione, aggiungendo un numero di sequenza, passandolo ad IP. Se il timer non è già attivoper qualche altro segmento, questo viene attivato
- **timeout**: ritrasmette il segmento che ha causato il timeout e riavvia il timer
- **ricezione di un ACK**: Sia $y$ l'ACK ricevuto. Se $y>\text{SendBase}$ aggiorna $\text{SendBase}=y$ e se esistono segmenti senza ACK riavvia il timer

Vediamo nella tabella sotto, come il destinatario genera gli ACK di TCP


| Evento                                                                                                           | Azione Mittente                                                                                                                    |
| ---------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| Arrivo di segmento con numero di sequenza atteso. Tutti i dati fino al numero di sequenza sono stati riscontrati | ACK ritardato. Attende 500ms per l'arrivo di un altro segmento. Se non arriva viene inviato l'ACK                                  |
| Arrivo di un segmento con numero di sequenza atteso. Un segmento ordinato è in attesa dell'ACK                   | Invia immediatamente un singolo ACK cumulativo, riscontrando entrambi i segmenti                                                   |
| Arrivo non ordinato di segmento con numero di sequenza superiore a quello atteso. C'è un buco.                   | Invia immediatamente ACK duplicato, indicando il numero di sequenza del prossimo byte atteso, che è l'estremità inferiore del buco |
| Arriva un segmento che colma parzialmente o completamente il buco nei dati ricevuti                              | Invia immediatamente un ACK, ammesso che il segmento cominci all'estremità del buco                                                |
![[Pasted image 20240822120721.png|center|500]]
![[Pasted image 20240822120740.png|center|300]]

### Ritrasmissione rapida
Un problema legato alle ritrasmissioni è che il periodo di timeout può rivelarsi molto lungo. Supponiamo che il mittente invii tanti segmenti in pipeline e che venga perso il secondo segmento (c'è un buco). Da questo momento il destinatario risponderà con ACK relativi al primo segmento ricevuto. Il mittente alla ricezione di questi **3 ACK duplicati**, capisce che c'è un buco e viene ritrasmesso il segmento non riscontrato col più piccolo numero di sequenza.

![[SOR/RETI/img/img68.png|center|300]]

## Controllo di flusso
Gli host riservano dei buffer di ricezione in cui vengono posizionati i byte ricevuti, da cui vengono poi letti dall'applicazione associata. Quest'ultimo, però, non legge necessariamente nell'istante in cui arrivano i byte, perché potrebbe essere occupata in altro. Se l'applicazione è lenta nella lettura dei dati può accadere che il mittente, inviando ad una velocità più elevata, mandi in overflow il buffer di ricezione.

TCP offre un **servizio di controllo di flusso** per evitare la saturazione del buffer ricevente, paragonando la frequenza di invio del mittente con quella di lettura del ricevente. TCP realizza il controllo di flusso facendo mantenere una variabile detta **finestra di ricezione**, che fornisce al mittente un'indicazione dello spazio libero nel buffer del destinatario.

Supponiamo che A stia invando un grande file a B. Quest'ultimo alloca un buffer di ricezione, che ha dimensione `RcvBuffer`. Definiamo anche le variabili:
- `LastByteRead`: numero dell'ultimo byte nel flusso di dati che il processo applicativo in B ha letto dal buffer
- `LastByteRcvd`: numero dell'ultimo byte che proviene dalla rete che è stato copiato nel buffer di ricezione di B
Dato che TCP non può mandare in overflow il buffer, deve valere: $$\text{LastByteRcvd - LastByteRead}\leq\text{RcvBuffer}$$La finestra di ricezione, che indichiamo con `rwnd`, viene impostata alla quantità di spazio disponibile nel buffer: $$\text{rwnd}=\text{RcvBuffer - [LastByteRcvd - LastByteRead]}$$
L'host B comunica all'host A quanto spazio è disponibile nel buffer, scrivendo il valore corrente di `rwnd` (che è dinamico), nal campo *finestra di ricezione* dei segmenti che manda ad A. All'inizio della comunicazione (buffer vuoto) vale che `rwnd = RcvBuffer`

L'host A tiene traccia di due variabili:
- `LastByteSent`
- `LastByteAcked`
Osserviamo che la differenza algebrica delle due variabili indica la quantità di dati spediti da A per cui non si è ricevuto ACK. 
Per garantire che l'host A non mandi in overflow il buffer di ricezione di B, durante tutta la connessione deve valere $$\text{LastByteSent - LastByteAcked}\leq\text{rwnd}$$
## Gestione della connessione TCP
Vediamo come viene stabilita e rilasciata una connessione TCP. Supponiamo che un host client vuole iniziare una connessione con un host server. La connesione viene stabilita mediante **handshaking a 3 vie**, che serve a dimostrare che entrambi gli host sono disponibili alla connessione ed è  necessaria per concordare i parametri di connessione.

![[SOR/RETI/img/img69.png|center|500]]
1. TCP lato client invia un segmento speciale, detto **SYN**, al TCP lato server. Il segmento non contiene dati a livello applicativo, ma il bit SYN nell'intestazione è posto a 1. Inoltre il client sceglie un numero di sequenza casuale (che chiamiamo `client_isn`) e lo pone nell'apposito campo del segmento SYN, incpasula il segmento in un datagramma IP e lo invia al server.
2. All'arrivo del datagramma IP col segmento SYN al server, questo viene estratto, vengono allocati i buffer e le variabili TCP e invia indietro un segmento di connessione approvata al client TCP. Questo segmento contiene: il *bit SYN* posto a 1, il campo *ACK* che ottiene il valore `client_isn + 1` e genera il proprio numero di sequenza `server_isn` che pone nel campo numero di sequenza. Il segmento appena creato viene detto **segmento SYNACK**
3. Alla ricezione del segmento SYNACK, il client alloca il buffer e le variabili di connessione. Il client invia al server un altro segmento in risposta al SYNACK ricevuto. Il client pone i valori `server_isn + 1` nel campo ACK e il bit SYN a 0 dato che la connessione è stabilita.

![[SOR/RETI/img/img70.png|center|500]]
Per *chiudere la connessione*, client e server chiudono ciascuno il proprio lato della connessione, inviando il segmento TCP con il bit **FIN** posto a 1.

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
In questo scenario vediamo quindi come *quando il tasso di arrivo dei pacchetti si avvicina alla capacità del collegamento, si rilevano lunghi ritardi**

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
- **Controllo di congestione end-to-end**. Il livello di rete non fornisce supporto al livello di trasporto per il controllo della congestione, che deve essere dedotta dagli host in base al comportamento della rete, ovvero osservando la perdita di pacchetti e ritardi che sono indicatori di congestione di rete. 
- **Controllo di congestione assistito dalla rete**. I componenti a livello di rete (router) forniscono un feedback al mittente sullo stato di congestione della rete, mediante un avviso diretto detto **chokepacket**.

![[SOR/RETI/img/img78.png|center|500]]

## Controllo di congestione TCP
Nella variante classica, TCP utilizza il controllo della congestione end-to-end e consiste nell'imporre a ciascun mittente un limite al tasso di invio sulla propria connessione in funzione della congestione di rete percepita.

Il mittente può aumentare la velocità di trasmissione fino a quando non si verifica la perdita di pacchetti, sintomo di congestione; in questo caso inizia a diminuire la velocità di invio.

Il meccanismo di controllo di congestione TCP fa tenere traccia agli host della variabile **finestra di congestione**, indicata con `cwnd`, che pone un limite alla velocità di immissione di traffico sulla rete da parte del mittente.
In particolare, la quantità di dati che non hanno ancora ricevuto ACK, non può superare il minimo tra i valori di `cwnd` e `rwnd` $$\text{LastByteSent - LastByteAcked}\leq\min\{\text{cwnd, rwnd}\}$$Assumendo che il buffer di ricezione sia sufficientemente grande, possiamo trascurare il vincolo di `rwnd`.
Il valore di `cwnd` viene regolato dinamicamente in risposta alla congestione della rete osservata.

Il mittente TCP percepisce la presenza di congestione sul percorso mediante gli *eventi di perdita*, quindi al verificarsi di timeout o alla ricezione di tre ACK duplicati dal destinatario. In presenza di congestione, i router sul percorso vanno in overflow, eliminando i datagrammi che non possono accedere al buffer.

L'approccio del controllo di congestione TCP è quello dell'**incremento additivo e dell'incremento moltiplicativo (AIMD)** . Formalmente, quando non vengono rilevate perdite, la velocità di invio viene aumentata di 1 MSS ogni RTT, mentre ad ogni evento di perdita la velocità viene dimezzata.

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

Se lo stato del collegamento congestionato deove si è verificata la perdita non è cambiato, è meglio aumentare la velocità di invio rapidamente per avvicinarsi alla velocità pre-perdita per sondare meglio la larghezza di banda. Questa intuizione è al centro della versione **TCP CUBIC**, che differisce da TCP Reno pricipalmente nella fase di congestion avoidance.

Sia $W_{max}$ la dimensione della `cwnd` nell'istante in cui viene rilevata la perdita. Sia $K$ l'istante futuro in cui la `cwnd` raggiungerà nuovamente il valore $W_{max}$. TCP CUBIC *incrementa maggiormente* la dimensione di `cwnd` quando è lontano dal valore $K$ (e quindi quando il valore di `cwnd` è lontano dal valore $W_{max}$), mentre *decrementa lentamente* in prossimità di $W_{max}$, e solo in questo momento esamina con maggior cautela la larghezza di banda.

![[SOR/RETI/img/img81.png|center|500]]

### Controllo di congestione basato su RTT
TCP, sia CUBIC che classico, rilevano la congestione primariamente con la perdita di pacchetti. Tuttavia la congestione può essere rilevata in altri modi, ad esempio analizzando l'RTT. Infatti, se ho un router congestionato, inviando sempre più dati aumenterà anche l'RTT perché questi dati finiscono nella coda, aumentando il tempo di accodamento e di conseguenza aumentando l'RTT.

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
zIl livello di trasporto ha il compito di fornire una **comunicazione logica** direttamente ai processi applicativi in esecuzione sui diversi host.
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

![[Pasted image 20240821113404.png|center|500]]

- il mittente accetta i dati dal livello superiore tramite `rdt_send(data)`, crea un  pacchetto contenente i dati con `packet = make_pkt(data)`  e lo invia sul canale `udt_send(packet)`.
- il ricevente raccoglie i pacchetti dal basso con `rdt_rcv(packet)`, rimuove i dati dai pacchetti con `extract(packet, data)` e li passa al livello superiore con `deliver_data(data)`.

### rdt2.0, canale con errori su bit
Un modello più realistico è quello del canale in cui i bit di un pacchetto possono essere corrotti. La sfida è dunque capire come recuperare gli errori. 
Questo è possibile mediante un utilizzo di **notifiche positive (ACK) e negative (NAK)** ed un invio di tipo *stop and wait*. 
Il mittente invia un pacchetto per volta, attendendo la risposta del destinatario: se il pacchetto arriva al ricevente correttamente, avviene un ACK, mentre se il pacchetto arriva con degli errori, il ricevente comunica un NAK al mittente che procede a ritrasmettere il pacchetto.
I protocolli basati sulla ritrasmissione sono detti **protocolli ARQ (Automatic Repeat reQuest)**.

![[Pasted image 20240821114858.png|center|500]]

Il **lato mittente** presenta due stati: in quello di sinistra il protocollo attende i dati da raccogliere. Quando si verifica l'evento `rdt_send(data)`, il mittente crea un pacchetto `sndpkt` contenente i dati da inviare ed il checksum (nel caso di pacchetto UDP) e spedisce il pacchetto mediante `udt_send(sndpkt)`. Nello stato di destra il mittente attende un pacchetto ACK o NAK dal destinatario: 
- se riceve ACK (evento `rdt_rcv(rcvpkt) && isACK(rcvpkt)`), il mittente sa che il pacchetto è stato ricevuto correttamente e torna allo stato di attesa dei dati
- se riceve NAK (evento `rdt_rcv(rcvpkt) && isNAK(rcvpkt)`), il protocollo ritrasmette l'ultimo pacchetto, attendendo nuovamente una risposta.

![[Pasted image 20240821115520.png|center|500]]

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

![[Pasted image 20240821120925.png|center|500]]
![[Pasted image 20240821122805.png|center|500]]

Osserviamo che sia mittente che destinatario hanno il doppio degli stati, in quanto devono riflettere il fatto che il pacchetto in invio o in ricezione abbia numero di sequenza 0 o 1. Notiamo inoltre che le azioni negli stati sono speculari, in quanto l'unica differenza riguarda la gestione del numero di sequenza.  

### rdt2.2, protocollo senza NAK
Il funzionamento è lo stesso di rdt2.1, ma utilizzando solamente gli ACK. In questo caso il destinatario invia un ACK per l'ultimo pacchetto ricevuto correttamente, indicando esplicitamente il numero di sequenza del pacchetto con l'ACK, mentre un ACK duplicato inviato al mittente è analogo ad un NAK, quindi il pacchetto viene ritrasmesso. Il protocollo TCP utilizza questo approccio.

![[Pasted image 20240821124618.png|center|500]]
![[Pasted image 20240821124630.png|center|500]]

### rdt3.0, canali con errori e perdite
Negli esempi precedenti abbiamo supposto che il canale di trasmissione danneggiasse solamente i bit. Supponiamo ora che il canale possa anche smarrire i pacchetti. 
Il protocollo deve quindi preoccuparsi di rilevare lo smarrimento e cosa fare quando questo avviene, introducendo un nuovo meccanismo.
Supponiamo che il mittente spedisca un pacchetto dati e che questo o l'ACK corrispondente vengano perduti. In questi casi, il mittente non riceverà alcuna risposta dal destinatario. Per essere certo dello smarrimento del pacchetto, il mittente deve essere disposto ad aspettare un tempo sufficiente dopo il quale ritrasmetterà il pacchetto di dati. 
Il problema sta nello scegliere adeguatamente il tempo da attendere. L'idea è quella di attendere un tempo che è la somma del ritardo tra andata e ritorno più il tempo necessario all'elaborazione, ma questo ritardo è difficile da stimare nel caso peggiore. 
L'approccio utilizzato è quello di scegliere una quantità di tempo per cui la perdita di pacchetti risulti probabile: se non si riceve un ACK in questo periodo di tempo, il pacchetto viene ritrasmesso. Notiamo che, il mittente potrebbe ritrasmettere un pacchetto che supera la quantità di tempo ammessa, anche se né il pacchetto stesso né l'ACK sono stati smarriti. Questo porta alla possibilità di **pacchetti duplicati**, ma questo è risolto grazie ai *numeri di sequenza*. 

Il mittente, non sa se un pacchetto o il relativo ACK sia stato effettivamente perso o se questi pacchetti abbiano subito un ritardo molto lungo. In ogni caso la soluzione è sempre la ritrasmissione. Il meccanismo di ritrasmissione richiede un *contatore* in grado di segnalare la scadenza del tempo impostato. Il mittente quindi, ogni volta che invia un pacchetto, inizializza il timer, eventualmente rispondere all'interrupt generato dal timer con l'azione appropriata e fermare il contatore quando il pacchetto viene trasmesso correttamente.

![[Pasted image 20240821130159.png|center|500]]


#### Analisi delle prestazioni rdt3.0
Il protocollo rdt3.0 è corretto funzionalmente, ma le prestazioni non sono il massimo in quanto si tratta di un protocollo di tipo stop-and-wait. Analizziamo la velocità di trasferimento, considerando questo caso. 

Due host, sulla coste opposte degli Strati Uniti. L'[[Livello Applicazione#Tempi di risposta|RTT]] è circa di 30 ms. Supponiamo che i due sistemi siano connessi da un canale con velocità di trasmissione $R$ di $1$ Gbps ($10^9$ bit al secondo). Con pacchetti di dimensione $L$ di $1000$ byte ($8000$ bit), il tempo richiesto per trasmettere il pacchetto sul collegamento è $$d_{t}=\frac{L}{R}=\frac{8000\text{ bit}}{10^{9}\text{ bit/s}}=8\:\micro s$$
![[Pasted image 20240821131558.png|center|300]]

Se il mittente inizia ad inviare pacchetti a $t=0$, l'ultimo bit entra nel canale dal lato mittente al tempo $t=\frac{L}{R}=8\:\micro s$. Il pacchetto effettua poi un viaggio di $15$ ms e l'ultimo bit giunge al destinatario a $t=\frac{RTT}{2}+ \frac{L}{R}=15.008$ ms. Di conseguenza l'ACK giunge al mittente all'istante $t=RTT+ \frac{L}{R}=30.008$ ms. Dopo questo lasso di tempo, il mittente puù trasmettere il messaggio successivo, quindi in un arco di $30.008$ ms il mittente ha trasmesso per soli $0.008$ ms. Definiamo ora l'utilizzo del mittente come la frazione di tempo in cui il mittente è stato effettivamente occupato nell'invio di bit $$U_\text{mittente}=\frac{L/R}{RTT+L/R}=\frac{0.008}{30.008}=0.00027$$
Il mittente è stato attivo per soli 2.7 centesimi dell'1% di tempo, con un throughput effettivo di $$U_\text{mittente}\cdot R=267\text{ kbps}$$ nonostante fosse disponibile un collegamento da $1$ Gbps. Il protocollo di tipo stop-and-wait limita quindi notevolmente le prestazioni del canale. 

### Protocolli per il trasferimento dati affidabile con pipeline 
La soluzione al problema delle prestazioni di rdt3.0 è quella di consentire al mittente di inviare più pacchetti senza aspettare gli ACK.

![[Pasted image 20240821132756.png|center|500]]

Se si consente al mittente di trasmettere tre pacchetti, l'utilizzo viene triplicato. Le conseguenze di utilizzo di un protocollo di trasferimento con pipeline sono:
- incremento dei numeri di sequenza disponibili
- memorizzare in un buffer più di un pacchetto. Il mittente dovrà memorizzare i pacchetti trasmessi il cui ACK non è stato ricevuto. 

#### Go-Back-N (GBN)
In un **protocollo GBN**, il mittente può trasmettere più pacchetti senza attendere acknowledge, ma non può avere più di un numero massimo consentito $N$ di pacchetti in attesa di ACK nella pipeline. 

![[Pasted image 20240821135640.png|center|500]]

Nella *window size* abbiamo i pacchetti inviati ma non ancora riscontrati e quelli pronti per l'invio. IL campo *base* indica il numero di sequenza del pacchetto più vecchio che non ha ricevuto ACK, mentre *nextseqnum* indica il numero di sequenza del prossimo pacchetto da inviare.

![[Pasted image 20240821142017.png|center|500]]
Il mittente GBN deve rispondere a tre tipi di evento:
- **invocazione dall'alto**. Alla chiamata `rdt_send()`, per prima cosa il mittente controlla se nella finestra ci siano $N$ pacchetti in sospeso. In caso negativo, crea e invia un nuovo pacchetto. Se la finestra è piena il mittente restituisce i dati al livello superiore e ritenterà più tardi. A livello implementativo i dati venono memorizzati in un buffer o viene implementato un meccanismo di sincronizzazione che consente la chiamata `rdt_send()` solo quando la finestra non è piena
- **ricezione di un ACK**. L'ACK con numero di sequenza $n$ è considerato un **ACK cumulativo**, che indica che tutti i pacchetti con numero di sequenza minore o uguale ad $n$ sono stati correttametne ricevuti
- **evento di timeout**. Si usa un contatore per il problema di pacchetti o ACK persi. Quando si verifica un timeout, il mittente invia nuovamente **tutti** i pacchetti con numero di sequenza maggiore uguale ad $n$ (ultimo ACK ricevuto).

![[Pasted image 20240821142044.png|center|500]]

Nel lato destinatario, se un pacchetto con numero di sequenza $n$ viene ricevuto correttamente ed è in ordine (ha numero di sequenza progressivo), viene inviato un ACK per il pacchetto e consegna i dati a livello superiore, memorizzando `rcv_base`, che indica l'ultimo numero di sequenza ottenuto . Se riceve uin pacchetto fuori sequenza può scartarlo e rimandare un ACK per il apcchetto con il numero di sequenza più alto

#### Ripetizione selettiva
I **protocolli a ripetizione selettiva** evitano le ritrasmissioni non necessarie facendo ritrasmettere al mittente solo i pacchetti su cui esistono alterazioni o smarrimenti. Questo obbliga il destinatario a mandare ACK specifici per i pacchetti ricevuti in modo corretto. Si utilizza nuovamente un'ampiezza di finestra pari a $N$ per limitare il numero di pacchetti senza ACK, ma a differenza di GBN, il mittente avrà già ricevuto gli ACK di qualche pacchetto nella finestra.

![[Pasted image 20240821143315.png|center|500]]

Il mittente svolge le seguenti operazioni in base agli aventi:
- **dati ricevuti dall'alto**. Alla ricezione di dati, il mittente controlla il succesisvo numero di sequenza disponibile per il pacchetto. Se è all'interno della finestra del mittente, i dati vengono impacchettati e inviati, altrimenti sono salvati in un buffer o restituiti al livello superiore
- **timeout**. Utilizza ancora i contatori per la perdita di pacchetti, anche se ora ogni pacchetto ha un proprio timer logico dato che viene ritrasmesso un solo pacchetto
- **ACK ricevuto**. Alla ricezione, il mittente etichietta il pacchetto $n$ come ricevuto. Se $n$ è il numero di sequenza più piccolo, la base della finestra avanza al successivo numero di sequenza del pacchetto non riscontrato.

![[Pasted image 20240821143742.png|center|500]]

Il ricevente si comporta nel seguente modo:
- **il pacchetto con n nella sequenza $[\text{rcv\_base, rcv\_base+N-1}]$ viene ricevuto correttamente**. In questo caso il pacchetto riceevuto è nella finestra del ricevente e viene restituito un ACK al mittente. Se il pacchetto non era già stato ricevuto viene inserito nel buffer. Se ha numero di sequenza uguale a `rcv_base` allora il pacchetto e i pacchetti nel buffer con numeri conescutivi a `rcv_base` vengono consegnati al livello superiore. Ad esempio quando si riceve un pacchetot con numero di sequenza `rcv_base=2` è possibile consegnarlo a livello superiore con i pacchetti 3, 4, e 5
- **si riceve il pacchetto con $n$ nella sequenza $[\text{rcv\_base-N, rcv\_base-1}]$**. In questo caso si genera un ACK, anche se il pacchetto è già stato riscontrato. Questo evento significa che gli ACK dei pacchetti già consegnati all'applicazione, non sono stati ricevuti dal mittente
- si ignora il pacchetto in ogni altro caso

![[Pasted image 20240821144557.png|center|500]]

# TCP
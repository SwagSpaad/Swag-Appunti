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
### rdt1.0
Il caso più semplice è quello in cui il canale sottostante è completamente affidabile. 
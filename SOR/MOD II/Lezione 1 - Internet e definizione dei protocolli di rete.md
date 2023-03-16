------
# Internet
Internet è una **rete di reti** che collega i diversi dspositivi sparsi nel mondo (computer, smartphone, stampanti...) che chiameremo  **host** o **end system**.
Gli host sono connessi tra di loro mediante linee di comunicazione (**link**) e dispositivi di commutazione (**router e switch**). Esistono vari tipi di linee di comunicazione come ad esempio i doppini, i cavi coassiali, fibre ottiche ed onde radio. 
Un parametro che caratterizza le varie teconologie di link e la **larghezza di banda** che si misura in multipli di bit/secondo.

In internet si usa una la commutazione di pacchetto, una tecnica che permette a più host di condividere un percorso.
Un host, per scambiare dei dati, frammenta questi in piccole parti aggiungendo un'intestazione. Questo frammento prende il nome di **pacchetto**.

Un **router** è un dispositivo dotato di porte di ingresso/uscita. I pacchetti, quando arrivano ad una porta di ingresso, in base a determinati protocolli di instradamento, sono inviati ad una porta di uscita.

Il percorso del pacchetto dall'host destinatario all'host mittente è detto **percorso (path)** o **cammino (route)**. 

Gli host sono connessi ad Internet attraverso degli **ISP (Internet Service Provider)**, ciascuno dei quali è costituito da una rete di router e linee di comunicazione.
Gli ISP si caratterizzano per le diverse tecnologie di accesso alla rete, con diverse larghezze di banda, come ad esempio l'accesso via DSL, accesso wireless e accesso LAN ad alta velocità. 
Questi ISP detti di livello inferiore, sono interconnessi attraverso ISP di livello superiore (regionali, nazionali e internazionali) che utilizzano router ad alte prestazioni, interconnessi mediante linee ad alta velocità in fibra ottica.
Esistono anche reti, appartenenti a società private o a enti statali, i cui host sono parzialmente o del tutto isolati dalla rete pubblica mediante **firewall**, tecnologie hardware/software, che servono a limitare e selezionare il traffico in entrata e in uscita della rete. Queste reti sono chiamate **Intranet**.

# Protocolli di rete
I dispositivi di rete per scambiarsi dati tra loro utilizzano molti protocolli. I principali protocolli sono il TCP (Transmission Control Protocol) e l'IP (Internet Protocol), spesso chiamati con l'unico termine TCP/IP.
Un protocollo definisce:
- la struttura dei messaggi e l'ordine in cui sono trasmessi tra i dispositivi
- le operazioni eseguite al momento della trasmissione e alla ricezione di messaggi.

# Applicazioni client e server
Il modello client/server è il modello più utilizzato per realizzare le applicazioni di rete. Seguendo questo modello, un'applicazione si realizza in due parti: il lato client (che richiede il servizio) ed un lato server (che lo fornisce). Questi due lati comunicano tra loro mediante vari protocolli. I server, a livello hardware, sono macchine molto potenti.

# Servizi orientati alla connessione e servizi senza connessione
Le applicazioni sviluppate per la rete Internet, utilizzano protocolli basati su due tipi di servizio.

## Servizio orientato alla connessione
Affidabile e garantisce che i dati trasmessi da un host mittente giungeranno al destinatario senza errori e nello stesso ordine con cui sono stati spediti.
Due host, prima di inviare i dati, eseguono una procedura detta handshaking (stretta di mano), mediante la quale si scambiano informazioni di controllo. Al termine di questa procedura, la connessione tra i due host si considera instaurata. 
Generalmente, il servizio orientato alla connessione è alquanto complesso ed è realizzato in un insieme di servizi, come il **trasferimento di dati affidabile**, il **controllo del flusso**, il **controllo della congestione**.
Il trasferimento di dati si dice affidabile quando un’applicazione è in grado di trasmettere tutti i dati che invia senza errori e nell’ordine di partenza. L’affidabilità è ottenuta attraverso l’invio di **messaggi di riscontro** ed eventuali **ritrasmissioni**, nel caso si perdessero dati.

Il **controllo del flusso** garantisce che il mittente in fase di trasmissione non saturi il buffer di ricezione del destinatario, inviando pacchetti ad una velocità elevata. Infatti, il destinatario potrebbe non essere in grado di adattarsi alla velocità con cui il mittente invia i pacchetti. Il tal caso, il servizio di controllo del flusso diminuisce la velocità di trasmissione del mittente.

Il **controllo della congestione** previene che la rete entri in uno stato di congestione a causa di un traffico di pacchetti eccessivo. Quando ad un router arrivano in brevissimi intervalli di tempo troppi pacchetti, la sua memoria (buffer) può riempirsi causando una perdita di pacchetti. Il controllo della congestione diminuisce, quindi, la velocità di trasmissione degli host mittenti durante i periodi di congestione. Questi protocolli rilevano lo stato di congestione quando non ricevono più i riscontri di avvenuta ricezione dei pacchetti inviati.

## Servizio senza connessione
Non è affidabile e non garantisce che tutti i dati inviati dall’host mettente giungeranno a destinazione.
Il servizio senza connessione non esegue la procedura di handshaking. Poiché manca l'handshake ed i messaggi di riscontro, la velocità è più elevata, ma questo non consente al mittente di avere la garanzia che i pacchetti siano arrivati.
I servizi senza connessione non posseggono controlli di flusso e di congestione. 
L'**UDP (User Datagram Protocol)** è il protocollo usato in Internet che implementa il servizio senza connessione.

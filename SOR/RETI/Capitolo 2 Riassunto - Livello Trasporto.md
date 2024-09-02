
## Introduzione
Il livello trasporto è uno dei più cruciali all'interno del modello OSI e del modello TCP/IP, in quanto stabilisce una connessione logica tra processi applicativi su host diversi, garantendo che i dati siano trasferiti correttamente da un punto all'altro della rete. È responsabile di assicurare che i dati siano inviati, ricevuti e riordinati correttamente, gestendo errori, controllo del flusso e la congestione della rete.

### Comunicazione Logica
Per "comunicazione logica" si intende che, dal punto di vista dell'applicazione, sembra che ci sia una connessione diretta tra i processi sugli host coinvolti, anche se la comunicazione può effettivamente passare attraverso numerosi router, switch e altri dispositivi di rete. Questo isolamento dei dettagli fisici della rete è fondamentale per permettere alle applicazioni di funzionare in modo semplice ed efficace, senza doversi preoccupare della complessità sottostante.

## Funzionamento dei Protocolli di Trasporto
I protocolli di trasporto operano ai margini della rete, ovvero sui sistemi periferici, come i computer o i dispositivi mobili degli utenti finali. A differenza dei protocolli dei livelli inferiori, che si occupano principalmente della consegna dei dati tra dispositivi hardware, i protocolli di trasporto si focalizzano sulla gestione dei dati a livello software, convertendo i messaggi delle applicazioni in pacchetti gestibili e viceversa.

### Lato Mittente
Il ruolo del protocollo di trasporto lato mittente è essenziale per preparare i dati per la trasmissione:
- **Segmentazione**: Quando un processo applicativo invia un messaggio, questo viene suddiviso in pezzi più piccoli chiamati **segmenti**. Questo processo è fondamentale perché la maggior parte delle reti non può trasportare messaggi di grandi dimensioni in un unico pacchetto.
- **Incorporazione delle informazioni di controllo**: Ogni segmento include non solo una parte dei dati dell'applicazione, ma anche informazioni di controllo essenziali, come il numero di sequenza (per garantire che i segmenti possano essere riordinati correttamente all'arrivo) e il checksum (per la verifica dell'integrità dei dati).

### Lato Ricevente
Dal lato ricevente, il protocollo di trasporto ha il compito di ricostruire il messaggio originario:
- **Ricomposizione**: I segmenti ricevuti vengono riordinati e ricomposti nel messaggio originale. Questo è particolarmente importante per garantire che i dati siano utilizzabili dall'applicazione destinataria.
- **Gestione degli errori**: Se un segmento è danneggiato o manca, il protocollo di trasporto può richiederne la ritrasmissione, garantendo che l'applicazione riceva i dati corretti e completi.

### TCP e UDP: I Pilastri del Livello Trasporto
Il livello trasporto è dominato da due protocolli principali, ciascuno con un insieme unico di caratteristiche e utilizzi.

#### TCP (Transmission Control Protocol)
TCP è uno dei protocolli più importanti in Internet, noto per la sua affidabilità:
- **Connessione Orientata**: TCP stabilisce una connessione logica tra mittente e ricevente prima di iniziare la trasmissione dei dati, utilizzando un processo noto come handshake a tre vie (three-way handshake). Questo processo garantisce che entrambe le parti siano pronte per comunicare.
- **Affidabilità**: TCP garantisce che tutti i segmenti vengano consegnati correttamente e nell'ordine giusto. Questo viene realizzato tramite numeri di sequenza, conferme di ricezione (acknowledgment) e ritrasmissioni in caso di perdita di dati.
- **Controllo del Flusso e della Congestione**: TCP adatta dinamicamente la velocità di trasmissione in base alla capacità del ricevente e alle condizioni della rete, riducendo il rischio di congestione e perdita di pacchetti.

**Vantaggi del TCP**:
- Garantisce la consegna affidabile dei dati, il che lo rende ideale per applicazioni dove la perdita di informazioni non è tollerabile, come nel caso di trasferimenti di file, email e navigazione web.
- Gestisce in modo efficiente la congestione della rete, riducendo il carico e prevenendo situazioni in cui la rete potrebbe sovraccaricarsi e degradare le prestazioni complessive.

**Svantaggi del TCP**:
- Introduce un overhead significativo, sia in termini di tempo che di risorse, a causa della necessità di stabilire una connessione, gestire numeri di sequenza e conferme, e implementare algoritmi di controllo della congestione. Questo può risultare in latenza maggiore e utilizzo più intenso della CPU.

#### UDP (User Datagram Protocol)
UDP è un protocollo molto più semplice rispetto a TCP e viene spesso scelto per applicazioni che richiedono velocità e efficienza piuttosto che affidabilità:
- **Senza Connessione**: UDP non stabilisce una connessione tra mittente e ricevente. Invia i datagrammi direttamente, senza preoccuparsi di confermare la ricezione o riordinare i pacchetti.
- **Bassa Latenza**: La mancanza di meccanismi di controllo del flusso, della congestione e della connessione rende UDP molto veloce, con una latenza minima. Questo lo rende ideale per applicazioni come lo streaming video e audio, giochi online, e altre situazioni in cui la velocità è più importante della precisione assoluta.

**Vantaggi di UDP**:
- Minimizza il tempo di trasmissione e il consumo di risorse, permettendo una trasmissione rapida dei dati.
- È particolarmente adatto per applicazioni che possono tollerare la perdita di alcuni pacchetti, come lo streaming multimediale o le chiamate VoIP, dove la fluidità dell'esperienza è più importante della trasmissione di ogni singolo bit.

**Svantaggi di UDP**:
- Non offre nessuna garanzia di consegna, ordine o integrità dei dati. È quindi inadatto per applicazioni che richiedono l'affidabilità nella trasmissione delle informazioni, come le transazioni bancarie o il trasferimento di file importanti.

### Multiplexing e Demultiplexing
Il livello trasporto utilizza tecniche di multiplexing e demultiplexing per gestire più connessioni simultaneamente:
- **Multiplexing**: Consente l'invio di dati provenienti da diverse applicazioni su una singola connessione fisica. Per esempio, un server web potrebbe gestire simultaneamente richieste HTTP provenienti da decine di browser diversi, utilizzando numeri di porta per distinguere tra le diverse connessioni.
- **Demultiplexing**: Quando i dati arrivano al destinatario, il protocollo di trasporto li demultiplexa, ovvero li smista correttamente alle applicazioni appropriate in base ai numeri di porta.

### Gestione degli Errori e Controllo del Flusso in TCP
La gestione degli errori e il controllo del flusso sono aspetti fondamentali del funzionamento di TCP:
- **Checksum**: TCP utilizza un checksum per verificare l'integrità dei dati. Se il checksum calcolato sul lato ricevente non corrisponde a quello inviato dal mittente, il segmento viene considerato corrotto e viene richiesto il ritrasporto.
- **Ack e Timeout**: TCP richiede conferme di ricezione (ACK) per ogni segmento inviato. Se un ACK non viene ricevuto entro un certo tempo (timeout), il segmento viene ritrasmesso.
- **Controllo del Flusso**: TCP utilizza un meccanismo di controllo del flusso basato sulla finestra scorrevole (sliding window) per evitare di sovraccaricare il ricevente. Il mittente invia dati fino al limite della finestra, attendendo gli ACK prima di inviare ulteriori dati.

### Gestione della Congestione
Il controllo della congestione è un aspetto critico per mantenere la stabilità e l'efficienza della rete:
- **Slow Start**: TCP inizia con una bassa velocità di trasmissione, aumentando progressivamente la finestra di congestione (congestion window) finché non rileva segni di congestione.
- **Congestion Avoidance**: Una volta che TCP rileva che la rete è vicina alla saturazione, smette di aumentare la velocità di trasmissione in modo esponenziale, optando per incrementi lineari più cauti.
- **Fast Retransmit e Fast Recovery**: Quando un segmento viene perso, TCP può attivare un ritrasporto rapido senza attendere un timeout completo, riprendendo rapidamente la trasmissione normale dopo il recupero dalla perdita.

### Segmentazione e Ricomposizione
Il processo di segmentazione dei messaggi è cruciale per il funzionamento del livello trasporto:
- **Segmentazione**: I messaggi di grandi dimensioni vengono suddivisi in segmenti più piccoli per essere trasmessi. Questo è necessario perché le reti hanno un limite massimo di dimensione dei pacchetti che

 possono gestire (MTU, Maximum Transmission Unit).
- **Ricomposizione**: Sul lato ricevente, i segmenti vengono riordinati e ricostruiti nel messaggio originale. Questo processo può essere complesso, specialmente se i segmenti arrivano fuori ordine o se alcuni sono persi.

#### Sfide nella Segmentazione e Ricomposizione
- **Errori di Segmentazione**: Se la segmentazione o la ricomposizione non vengono eseguite correttamente, i dati possono risultare corrotti, portando a potenziali errori nell'applicazione.
- **Overhead di Ritrasmissione**: La necessità di ritrasmettere segmenti persi o corrotti introduce un overhead che può influire sulle prestazioni complessive del sistema, soprattutto in reti con alta latenza o tassi di perdita elevati.

## Conclusioni
Il livello trasporto è il cuore delle comunicazioni di rete affidabili, permettendo la trasmissione di dati tra applicazioni in modo sicuro ed efficiente. TCP e UDP rappresentano due estremi dello spettro di protocolli di trasporto: il primo focalizzato sull'affidabilità, il secondo sulla velocità. La comprensione approfondita delle loro funzionalità e delle tecniche di gestione della congestione, degli errori e del flusso di dati è essenziale per qualsiasi professionista della rete, poiché la scelta del protocollo giusto e la configurazione appropriata possono determinare il successo o il fallimento di un'applicazione basata sulla rete.

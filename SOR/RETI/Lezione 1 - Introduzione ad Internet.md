*****
# Introduzione
**Cos'è Internet?** Possiamo rispondere a questa domanda in due modi
- *descrizione degli ingranaggi*, ovvero i componenti hardware e software che lo compongono
- *descrizione in termini di infrastruttura di rete* che fornisce servizi ad applicazioni distribuite
## Gli ingranaggi di Internet
Internet è una rete di calcolatori che interconnette miliardi di dispositivi.
Tutti i dispositivi connessi ad Internet sono detti **host** (o **sistemi periferici**), sono tutti connessi tra di loro tramite una **rete di collegamenti** (fibra ottica, rame, radio, satellite) e **commutatori di pacchetti** (router, switch). Collegamenti diversi possono trasmettere dati a velocità diverse e la **velocità di trasmissione** viene misurata in bit/secondo (bps).

Internet è considerata una rete di reti, ovvero una serie di **ISP (Internet Service Provider)** interconnessi tra loro.
Due dispositivi, per comunicare, scambiano le loro informazione suddividendole in **pacchetti**, li inviano al destinatario e vengono in seguito riassemblati per ottenere i dati originari.
Gli host, i commutatori di pacchetti e le altre parti di Internet utilizzano dei *protocolli* per controllare l'invio e la ricezione di informazioni.

> Un **protocollo** definisce il *formato* e *l’ordine dei messaggi* scambiati tra due o più entità in comunicazione, così come le *azioni intraprese* in fase di trasmissione e/o di ricezione di un messaggio o di un altro evento.

A livello di servizi, Internet è un' **infrastruttura** che fornisce servizi alle applicazioni (web, streaming, e-commerce, gaming, etc...). Inoltre fornisce un un' **interfaccia di programmazione** alle applicazioni distribuite:
- "hook" che consentono alle applicazioni mittente/destinataria di "connettersi", usare il servizio di trasporto Internet
- fornisce molte opzioni di servizio analogamente al servizio postale.

# Ai confini della rete
Chi sono i componenti di Internet? Consideriamo inizialmente i componenti con cui abbiamo più familiarità, come i computer ed i telefoni cellulari. Questi dispositivi sono solitamente detti **end system**, in quanto si trovano ai confini di Internet ed includono: computer desktop, server, dispositivi mobili ecc.
Gli end system sono anche detti **host** in quanto ospitano ed eseguono programmi applicativi come browser, software mail, web server. Gli host sono suddivisi in due categorie:
- **client**: host che richiedono i servizi
- **server**: host che erogano i servizi
## Reti di accesso
Osserviamo da vicino le **reti di accesso**, ovvero la rete che connette fisicamente un sistema al suo **edge router**, il primo router sul percorso dal sistema d'origine ad un sistema di destinazione collocato fuori dalla rete di accesso.

![[SOR/RETI/img/img0.png|center|500]]
### Digital subscriber line (DSL)
Fornito dalla stessa compagnia telefonica che fornisce il servizio di telefonia fissa. 

![[SOR/RETI/img/img1.png|center|500]]

In figura vediamo come accedere ad Internet tramite DSL. Il modem DSL usa la linea telefonica esistente per scambiare i dati con un *DSLAM (Digital Subscriber Line Access Multiplex)* che si trova nella centrale locale della compagnia telefonica. 
Le linee telefoniche trasportano contemporaneamente dati Internet e segnali telefonici codificandoli in tre bande di frequenza non sovrapposte:
- canale *downstream* (verso l'abitazione) ad alta velocità, nella banda tra 50 kHz e 1 Mhz
- canale *upstream* (verso il DSLAM) a velocità media, nella banda tra 4 e 50 kHz 
- canale telefonico a due vie, tra 0 e 4 kHz
Questo approccio, detto multiplexing a divisione di frequenza, fa apparire un singolo collegamento DSL come tre collegamenti separati, in modo che una chiamata telefonica e una connessione Internet possano condividere contemporaneamente lo stesso collegamento DSL.
Gli standard DSL definiscono i tassi di trasmissioni in modo **asimmetrico**, ovvero con velocità di downstream e upstream differenti.
### Accesso via cavo
L'accesso ad Internet via cavo utilizza le infrastrutture della televisione via cavo. 

![[SOR/RETI/img/img2.png|center|700]]

Dalla figura vediamo come le terminazioni della fibra ottica vengano connesse alle giunzioni a livello di quartiere, dalle quali viene poi utilizzato il cavo coassiale per la distribuzione alle singole case ed appartamenti. Ogni giunzione serve generalmente da 500 a 5000 abitazioni. Questo sistema è detto **hybrid fiber coax (HFC)** perché impiega sia la fibra ottica che il cavo coassiale.
L'accesso ad Internet richiede dei modem speciali, detti **cable modem**. Alla stazione di testa, il sistema di terminazione del cable modem *(CMTS, cable modem termination system)*, svolge una funzione simile al DSLAM nelle [[#Digital subscriber line (DSL)|reti DSL]], ovvero traduce il segnale analogico inviato dai cable modem delle abitazioni in formato digitale.  
I cable modem dividono la rete HFC in due canali, upstream e downstream, il cui accesso è asimmetrico.
Un'importante caratteristica di HFC è che rappresenta un mezzo di trasmissione condiviso, infatti ciascun pacchetto inviato dalla stazione di testa viaggia sul canale di downstream in tutti i collegamenti e verso ogni abitazione. Per questo motivo, se diversi utenti stanno contemporaneamente scaricando un file video sul canale di downstram, l'effettiva velocità alla quale ciascun utente riceve il proprio file video sarà significativamente inferiore rispetto a quella totale del canale downstream. 

### FTTx
Con la sigla FTTx si indica l'architettura di rete che utilizza la fibra  ottica come mezzo trasmissivo. 

![[SOR/RETI/img/img3.png|center|300]]

Il termine si differenzia in base alle diverse configurazioni: 
- FTTH - *fiber to the home* 
- FTTB - fiber to the building
- FTTC o FTTS - fiber to the cabinet/street
- FTTN - fiber to the node
- FTTW o FTTR - fiber to the wireless/radio
Più il collegamento ottico arriva vicino alla destinazioni, tanto più sarà la velocità di trasmissione.

#### FTTH
Ci sono diverse tecnologie per la distribuzione della fibra ottica alle abitazioni. La più semplice è detta *fibra diretta*, in cui ua singola fibra collega una centrale locale ad un'abitazione. Di solito, però, una fibra uscente dalla centrale locale è condivisa da molte abitazioni e solo quando arriva vicina ad esse è suddivisa in più fibre, ognuna dedicata ad un utente. 
Ci sono due architetture che eseguono questa suddivisione:
- **le reti ottiche attive (AON)**: sono delle Ethernet coimmutate, con commutatori in grado di ricevere/trasmettere segnali ottici
- **reti ottiche passive (PON)**: usano splitter ottici non alimentati che trasmette in broadcast verso gli utenti (in figura)

![[SOR/RETI/img/img4.png|center|500]]

### Fixed Wireless Access (FWA)
È una rete mista fibra-radio che raggiunge i clienti con
- una rete a banda larga (fino a 30Mbps)
- una rete a banda ultralarga (fino a 100 Mbps)

## Come avviene l'invio di un pacchetto
L'host suddivide il messaggio in frammenti più piccoli, detti **pacchetti**, di lunghezza $L$ bit e lo trasmette su un canale tra la sorgente e la destinazione attraverso collegamenti e **commutatori di pacchetto** (come router e commutatori a livello di collegamento). I pacchetti vengono trasmessi su ciascun collegamento ad una velocità pari alla velocità di trasmissione del collegamento stesso. Quindi se un commutatore invia $L$ bit su un canale con veloctia $R$ bps, il tempo di trasmissione è pari a $$T=\frac{L}{R}s$$
$T$ è detto anche *ritardo di trasmissione del pacchetto*.

## Mezzi trasmissivi
I dati, che vengono suddivisi in bit, per viaggiare tra due end system, vengono ritrasmessi più volte. L'host di origine è il primo a trasmettere il bit, che sarà ricevuto dal primo router che lo ritrasmetterà al secondo router e così via. Il bit, nel viaggio dalla sorgente al destinatario, si propaga da un trasmettitore ad un ricevitore sottoforma di onda elettromagnetica o impulso ottico attraverso un **mezzo fisico**.
Questi si distinguono in *mezzi vincolati*, in cui le onde vengono contenute in un mezzo fisico e *mezzi non vincolati* le cui onde si propagano nell'atmosfera (wireless).

Andiamo a vedere nel dettaglio i mezzi trasmissivi più comuni.

### Doppino di rame intercciato (twisted pair)
È il meno costoso e più utilizzato. È costituito da due fili di rame distinti, intrecciati a spirale, per ridurre l'interferenza elettrica genereata dalle coppie presenti nelle vicinanze. 
- Categoria 5: 100 Mbps, 1 Gbps Ethernet
- Categoria 6: 10 Gbps Ethernet (distanze inferiori a un centinaio di metri)
## Cavo coassiale
Costituito da due conduttori di rame concentrici anziché paralleli. Grazie ad uno speciale isolamento e schermatura, il cavo coassiale può raggiungere alte frequenze di trasmissione.  È impiegato comunemente nei sistemi televisivi via cavo. Come abbiamo visto il trasmettitore trasla il segnale su una specifica banda di frequenza, il cui segnale viene inviato dal trasmettitore ad uno o più ricevitori. 
### Fibra ottica
Mezzo sottile e flessibile che conduce inpulsi di luce, ciascuno dei quali rappresenta un bit. Può supportare enormi velocità trasmissive, è immune ad interferenze elettromagnetiche e presenta attenuazione di segnale molto bassa nel raggio di 100 km. 
### Canali radio
Trasportano segnali elettromagnetici senza l'utilizzo di cavi fisici. I canali possono essere: 
- Wireless LAN (WiFi): decine/centinaia di Mbps a distanza di qualche decina di metri
- Wide Area (4G/5G): fino a 300 Mbps in un range di decine di km
- Bluetooth: distanze brevi e velocità limitate
- Microonde terrestri: punto-punto; canali fino a 45 Mbps
- Satellitari (starlink) fino a meno di 100 Mbps in downlink, ritardo punto punto di 270ms

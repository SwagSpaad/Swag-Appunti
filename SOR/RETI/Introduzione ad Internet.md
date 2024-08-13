*****
# Introduzione
**Cos'è Internet?** Possiamo rispondere a questa domanda in due modi
- *descrizione degli ingranaggi*, ovvero i componenti hardware e software che lo compongono
- *descrizione in termini di infrastruttura di rete* che fornisce servizi ad applicazioni distribuite
## Gli ingranaggi di Internet
Internet è una rete di calcolatori che interconnette miliardi di dispositivi.
Tutti i dispositivi connessi ad Internet sono detti **host** (o **sistemi periferici**), e sono tutti connessi tra di loro tramite una **rete di collegamenti** (fibra ottica, rame, radio, satellite) e **commutatori di pacchetti** (router, switch). Collegamenti diversi possono trasmettere dati a velocità diverse e la **velocità di trasmissione** viene misurata in bit/secondo (bps).

Internet è considerata una rete di reti, ovvero una serie di **ISP (Internet Service Provider)** interconnessi tra loro.
Due dispositivi, per comunicare, scambiano le loro informazioni suddividendole in **pacchetti**, li inviano al destinatario e vengono in seguito riassemblati per ottenere i dati originari.
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
Osserviamo da vicino le **reti di accesso**, ovvero la rete che connette fisicamente un dispositivo al suo **edge router**, il primo router sul percorso dal sistema d'origine ad un sistema di destinazione collocato fuori dalla rete di accesso.

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

# Nucleo della Rete
Approfondiamo ora il nucleo della rete, ovvero una maglia (mesh) di commutatori di pacchetti e collegamenti che interconnettono i sistemi periferici di Internet. 

La rete inoltra i pacchetti da un router all'altro attraverso i collegamenti lungo un percorso che connette la sorgente con il destinatario.
## Funzioni chiave del nucleo della rete
**Inoltro (forwarding)**:
- azione locale, sposta i pacchetti in arrivo al collegamento di ingresso del router al collegamento di uscita appropriato
**Instradamento (routing)**:
- azione globale: determina i percorsi presi dai pacchetti dalla sorgente alla destinazione secondo *algoritmi di instradamento*
## Commutazione di pacchetto 

I sistemi periferici suddividono i messaggi di livello applicativo in pacchetti (packets), che nel percorso tra sorgente e destinatario, viaggiano attraverso collegamenti e commutatori.
Vediamo i diversi tipi di trasmissione.
### Store-And-Forward
La maggior parte dei commutatori di pacchetto utilizza questo tipo di trasmissione.
Il router deve aver ricevuto l'intero pacchetto prima di poterlo trasmettere sul collegamento in uscita. 
Calcoliamo l'intervallo di tempo che intercorre da quando una sorgente inizia ad inviare  il primo pacchetto a quando il destinatario li riceve. 

In questo caso supponiamo che il pacchetto sia suddiviso in $L$ bit. Al tempo $\frac{L}{R}$ il router inizia ad inoltrare il primo pacchetto e la sorgente invia il secondo. Al tempo $2 \frac{L}{R}$ il destinatario ha ricevuto il primo pacchetto e il router ha ricevuto il secondo. Al tempo $3 \frac{L}{R}$ il destinatario ha ricevuto i primi due pacchetti e il router ha ricevuto il terzo pacchetto, infine al tempo $4 \frac{L}{R}$ il destinatario ha ricevuto tutti e tre i pacchetti.

![[SOR/RETI/img/img5.png|center|500]]
![[SOR/RETI/img/img6.png|center|500]]

>Il ritardo da un capo all'altro (end-t-end) per la trasmissione di 1 pacchetto su un percorso di N collegamenti di velocità R: $$d_\text{end-to-end} = N \frac{L}{R}$$
**Ovviamente trascurando il ritardo di propagazione e altre forme di ritardo**

>Il ritardo da un capo all'altro (end-t-end) per la trasmissione di P pacchetto su un percorso di N collegamenti di pari velocità R: $$d_{\text{end-to-end}} = (N + P - 1) \frac{L}{R}$$
**Ovviamente trascurando il ritardo di propagazione e altre forme di ritardo**

### Accodamento
I commutatori di pacchetto, per ogni collegamento, mantengono un **buffer di output** (detto anche *coda*) per conservare i pacchetti che devono essere inviati su quel collegamento. Un pacchetto in arrivo che deve essere inviato attraverso un collegamento occupato dalla trasmissione di un altro, deve attendere nella coda di output.
L'accodamento si verifica quando il tasso di arrivo è maggiore del tasso di trasmissione.

Dato che la dimensione del buffer è finita, un pacchetto in arrivo può trovare il buffer completamente pieno. In questo caso si verificherà un *packet loss*, verrà quindi eliminato il pacchetto in arrivo o uno di quelli in coda.
## Commutazione di circuito
Alternativamente alla commutazione di pacchetto, si può optare per la commutazione di circuito. 
In questo caso le risorse richieste lungo un percorso (buffer e velocità di trasmissione sui collegamenti) per la comunicazione tra sistemi periferici sono riservate per l'intera durata della sessione di comunicazione.

Nelle [[#Commutazione di pacchetto|reti a commutazione di pacchetto]] queste risorse non sono riservate, infatti i messaggi le utilizzano quando necessario e potrebbero dover attendere per accedervi. 
Le reti telefoniche sono esempi di rete a commutazione di curcuito, infatti quando si attiva una chiamata telefonica, la rete stabilisce una connessione tra mittente e destinatario, che viene mantenuta per tutta la durata della connesisone (in gergo telefonico questa connessione è detta **circuito**). 

I vantaggi riassunti sono:
- Non si verifica nessuna condivisione di risorse (canali dedicati)
- Trasferimento dati a velocità costante 
- I segmenti del circuito restano inattivi se non utilizzati

![[SOR/RETI/img/img7.png|center|500]]
Nella figura è mostrata una rete a commutazione di circuito, in cui i quattro commutatori sono interconnessi tramite quattro collegamenti, con gli host direttamente connessi a uno dei commutatori. Quando due host vogliono comunicare, la rete stabilisce una connessione *end-to-end* dedicata a loro (evidenziata in rosso). 

Un circuito all'interno di un collegamento è implementato tramite **multiplexing a divisione di frequenza**, **multiplexing a divisione di tempo**, che vediamo nel dettaglio nei prossimi due paragrafi.
### FDM (Frequency Division Multiplexing)
In questo tipo di multiplexing, lo spettro di frequenza di un collegamento è  suddiviso in bande, ogni circuito ha una propria banda e può trasmettere alla velocità massima di quella banda ristretta.

![[SOR/RETI/img/img8.png|center|500]]

### TDM (Time Division Multiplexing)
In questo tipo di collegamento il tempo viene suddiviso in *frame* di durata fissa ripartiti in un numero fisso di slot, dedicati alla connessione assegnata. Quando la rete stabilisce una connessione attraverso un collegamento, ciascun circuito riceve slot periodici e può trasmettere alla massima velocità della banda di frequenza solo nei propri slot temporali.

![[SOR/RETI/img/img9.png|center|500]]

Dopo aver analizzato la commutazione di circuito e di pacchetto, confrontiamo i due metodi.
I denigratori della commutazione di pacchetto sostengono che il metodo non è adatto ai servizi in tempo reale a causa dei ritardi non determinabili a priori, mentre i sostenitori affermano che offre una migliore condivisione della larghezza di banda rispetto alla commutazione di circuito ed è più efficiente e meno costosa da implementare.

Perché la commutazione di pacchetto è più efficiente? 
>**Esempio**
>Supponiamo che gli utenti condividano un collegamento da 1 Mbps e che ciascun utente genera dati ad una velocità costante di 100 kbps, solo quando è attivo (il 10% del tempo).
>Con la **commutazione di cirucito** è necessario risevare 100 kbps per ciascun utente in ogni istante, quindi il collegamento può supportare simultaneamente $10= \frac{1 \text{Mbps}}{100 \text{kbps}}$ utenti. Con la commutazione di pacchetto, dobbiamo calcolare la probabilità di avere 11 o più utenti attivi contemporaneamente su 35 utenti complessivi, che è circa pari a $0,0004$. Quando ci sono 10 o meno utenti attivi contemporaneamente, la velocità di output del collegamento è minore o uguale a 1 Mbps, quindi i pacchetti arrivano senza ritardo, mentre se ci sono più di 10 utenti attivi, la coda di output comincerà a crescere. Dato che la proababilità di avere più di 10 utenti attivi contemporaneamente è molto bassa, la commutazione di pacchetto fornisce le stesse prestazioni della commutazione di circuito, ma **consente più del triplo degli utenti**.

# Perdite, ritardi e throghput
Idealmente, vorremmo che i servizi Internet fossero in grado di spostare una quantità di dati qualsiasi tra due sistemi perifierici, istantaneamente e senza perdita di dati. Questo non è purtroppo possibile, perché le reti di calcolatori limitano il throughput, introducono ritardi tra due sistemi periferici e possono perdere pacchetti. Esistono numerosi metodi per affrontare questi problemi. In questi paragrafi iniziamo a quantificare ed esaminare il ritardo, le perdite e il throghput nelle reti di calcolatori.

## Tipi di ritardo
Ricordiamo che un pacchetto parte da un host sorgente, passa attraverso una serie di router e arriva infine ad un host destinatario. Ad ogni tappa, il pacchetto subisce vari tipi di ritardo, i cui principali sono: 
- **ritardo di elaborazione**
- **ritardo di accodamento**
- **ritardo di trasmissione** 
- **ritardo di propagazione**
questi ritardi, complessivamente formano il **ritardo totale di nodo**.

![[SOR/RETI/img/img10.png|center|500]]

Nel percorso dalla sorgente alla destinazione, il pacchetto viene inviato attraverso il router A verso il router B. Quando il pacchetto arriva al router A, esso esamina l'intestazione per determinare il collegamento in uscita appropriato e dirige il pacchetto su quel collegamento. Un pacchetto può essere trasmesso sul collegamento solo se non ci sono altri pacchetti in fase di trasmissione, altrimenti entra in coda.

### Ritardo di elaborazione $d_\text{elab}$
*Tempo richiesto per esaminare l'intestazione del pacchetto, eventuali errori a livello di bit e per determinare dove dirigerlo*. Nei router ad alta velocità questi ritardi sono dell'ordine dei microsecondi o inferiori. Dopo l'elaborazione, il router dirige il pacchetto verso la coda che precede il collegamento al router B.
### Ritardo di accodamento $d_\text{acc}$
Una volta in coda, il pacchetto attende la trasmissione sul collegamento. *La luinghezza di questo ritardo dipende dal numero di pacchetti in coda*. Se la coda è vuota, il ritardo è nullo, altrimenti sarà elevato. I ritardi di accodamento possono essere dell'ordine di mircosecondi o millisecondi.
### Ritardo di trasmissione $d_\text{trasm}$
Assumendo che i pacchetti siano trasmessi secondo la politica FIFO, il pacchetto può essere trasmesso solo dopo la trasmissione di tutti quelli che lo hanno preceduto. Sia $L$ la lunghezza del pacchetto in bit, ed $R$ bps la velocità di trasmissione del collegamento dal router A al rotuer B. Il **ritardo di trasmissione** è $\frac{L}{R}$. Solitamente è dell'ordine dei micro o millisecondi.
### Ritardo di propagazione $d_\text{prop}$
Una volta immesso sul collegamento, il bit deve arrivare fino al router B. Il tempo impiegato è il ritardo di propagazione. Il bit viaggia alla velocità di propagazione del collegameno, che dipende dal mezzo fisico in uso. *Il ritardo di propagazione è dato da $\frac{d}{v}$ dove $d$ è la distanza tra i due router, mentre $v$ è la velocità di propagazione del collegamento*. Nelle reti molto estese il ritardo è dell'ordine dei millisecondi. 

La somma di questi 4 tipi di ritardo ci permette di calcolare il ritardo totale del nodo $$d_\text{nodo}=d_\text{elab}+d_\text{acc}+d_\text{trasm}+d_\text{prop}$$
#### Il ritardo di accodamento
La componente più interessante del ritardo totale è il ritardo di accodamento, che, a differenza degli altri tre, può variare da pacchetto a pacchetto. Ad esmepio se in una coda vuota arrivano 10 pacchetti, il primo non subirà ritardo, mentre l'ultimo subirà un ritardo abbastanza grande perché deve attendere la trasmissione dei 9 pacchetti. 

Quando si considera rilevante o trascurabile il ritardo di accodamento? Questo dipende dalla velocità di arrivo dei pacchetti in coda, dalla velocità di trasmissione del collegamento e dalla natura del traffico. 

Indichiamo con $a$  la velocità media di arrivo dei pacchetti, con $R$ la velocità di trasmissione e con $L$ la lunghezza in bit dei pacchetti.

L'intensità di traffico è il rapporto $\frac{L\cdot a}{R}$ che gioca un ruolo importante nella stima del ritardo di accodamento.
- Se $\frac{La}{R} > 1$ la coda cresce senza limiti e il ritardo di coda tende all'infinito
- Se $\frac{La}{R}\sim 0$  abbiamo un ritardo di accodamento piccolo
- Se $\frac{La}{R}\to 1$ abbiamo un ritardo di accodamento grande

![[SOR/RETI/img/img11.png|center|400]]

### Traceroute
Per ottenere una misura efficiente dei ritardi in una rete si utilizza il programma **traceroute**. Quando l'utente specifica il nome di un host di destinazione, il programma invia un certo numero di pacchetti verso quella destinazione, che nel loro percorso passano attraverso una serie di router. Quando il router riceve questi pacchetti, invia un messaggio indietro all'origine che contiene il nome e l'indirizzo del router. Per ogni pacchetto si registra il tempo passato tra l'invio e la ricezione del messaggio di ritorno

## Throughput
Un'altra misura critica delle prestazioni in una rete è il throughput, ovvero la frequenza (bit per unità di tempo) alla quale i bit sono trasferiti tra mittente e destinatario. Si suddivide in: 
- *throughput istantaneo*: in un determinato istante
- *throughput medio*: in un periodo di tempo

Il throughput dipende dalla velocità di trasmissione dei collegamenti sui quale passano i dati: 
- se nel percorso non c'è traffico il $\text{throughput} \approx\min\{R_{i}\}$ dove $R_{i}$ è la velocità di trasmmissione dell'i-esimo collegamento
- altrimenti si suddivide la velocità di trasmissione di un collegamento tra i vari flussi che lo attraversano

# Sicurezza
Originariamente Internet è stata progettata senza pensare molto alla sicurezza, perché consisteva di un gruppo di utenti fidati collegati tra di loro. 
Col tempo e con l'evoluzione questo obiettivo è cambiato ed ora bisogna pensare al modo in cui le reti possono essere attaccate e come difenderle. 

I diversi tipi di attacchi possono essere:
- packet sniffing: utente malintenzionato che intercetta e legge tutti i pacchetti che attraversano un interfaccia di rete

![[SOR/RETI/img/img12.png|center|500]]

- IP spoofing: iniezione di pacchetti con indirizzo sorgente falso

![[SOR/RETI/img/img13.png|center|500]]

- attacchi DoS: rendere una rete o un host non disponibile per gli utenti legittimi. 3 categorie di attacchi: 
	- *attacchi alla vulnerabilità dei sistemi*: invio di pochi pacchetti costruiti in modo tale da causare il blocco di un servizio o lo spegnimento di un host
	- *inondazione di banda*: invio di molti pacchetti per riempire la banda ed impedire al traffico legittimo di raggiungerlo
	- *inondazione di connessioni*: stabilisce un gran numero di connessioni TCP con l'host obiettivo, impedendo altre connessioni legittime.

Per evitare questi tipi di attacchi possono essere utilizzate delle linee di difesa: 
- **autenticazione**: dimostrare che siamo che diciamo di essere. Le reti cellulari ad esempio forniscono un'identità attraverso la carta SIM
- **riservatezza**: attraverso la cifratura
- **integrità**: utilizzo di firme digitali per prevenire/rilevare le manomissioni
- **restrizioni di accesso**: utilizzo di VPN protette da password
- **firewall**: impedisce l'accesso di determinati utenti che non soddisfano dei requisiti.

# Livello di protocollo
Internet è un sistema molto complesso, con molti componenti, come ad esempio gli host, i router, le applicazioni ecc. 
Per ridurre la complessità, i protocolli sono organizzati a livelli e questa stratificazione prevede che ogni strato fornisca vari servizi allo strato sopra di esso. Questa struttura consente di modificare più facilmente l'implementazione dei servizi in ciascuno strato, lasciando invariata l'implementazione degli altri strati.

Questa stratificazione ha anche dei potenziali svantaggi, come ad esempio la duplicazione delle funzionalità del livello inferiore (correzione degli errori implementata sia a livello di trasporto che di collegamento).
Un'altro svantaggio è la necessiotà di violare questa separazione tra livelli quando un livello ha bisogno di un'informazione disponibile solo all'interno del livello inferiore.

Considerati assieme, i protocolli dei livelli sono detta **pila di protocolli**, che vediamo sotto in figura.

![[SOR/RETI/img/img14.png|center|200]]

## Livello di applicazione
Sede delle applicazioni di rete e dei relativi protocolli, tra cui HTTP, SMTP, e FTP. Un protocollo a livello di applicazione è distribuito su più sistemi periferici. L'applicazione, tramite il protocollo, scambia **messaggi** per implementare il servizio applicativo usando servizi dello stato di trasporto

## Livello di trasporto
Fornisce il servizio di trasporto dei messaggi dello strato di apllicazione fra le estremità di un'applicazione. Utilizza i protocolli TCP o UDP. 
Il TCP fornisce un servizio orientato alla connessione che garantisce il trasferimento dei messaggi dello strato di applicazione, controlla la corrispondenza tra la velocità di mittente e destinatario (controllo di flusso) e fornisce un meccanismo per frazionare i messaggi più lunghi in segmenti più piccoli. 
UDP fornisce un servizio non orientato alla connessione che è senza affidabilità, senza controllo di flusso e senza controllo della congestione.
I pacchetti a livello di trasporto sono detto **segmenti**.

## Livello di rete
È lo strato responsabile dell'instradamento dei pacchetti di rete, detti **datagrammi**, da mittente a destinatario. 
Ne fanno parte il protocollo IP che definisce i campi nel datagramma e le operazioni che host e router eseguono su questi campi. Il protocollo IP *è unico* percoò tutti gli apparati di internet che presentano un livello di rete devono supportarlo. Presenta inoltre vari protocolli di instradamento che decidono il percorso che i datagrammi devono seguire.

## Livello di collegamento
Per trasferire un pacchetto da un nodo a quello successivo, il livello di rete si affida ai servizi del livello di collegamento. In particolare, ad ogni nodo, il livello di rete passa il datagramma al livello sottostante, che lo trasporta al nodo successivo. In questo nodo, il livello di collegamento passa il datagramma al livello di rete superiore. 
I servizi forniti dallo strato di collegamento dipendono dal protocollo specifico dello strato di collegamento utilizzato sul link, come ad esempio il protocollo WI-FI che fornisce un trasferimento affidabile, oppure Ethernet che non implementano un servizio di trasferimento affidabile.

I pacchetti a livello di collegamento sono detti **frame**

## Livello fisico
Ha il compito di trasferire i singoli bit del frame da un nodo ad un nodo successivo. I protocolli di questo livello sono dipendenti dal collegamento e dal mezzo trasmissivo utilizzato, ad esempio Ethernet presenta vari protocolli a livello fisico: uno per il doppino intrecciato, uno per il cavo coassiale, fibra ottica ecc.

![[SOR/RETI/img/img15.png|center|700]]

## Incapsulamento
Un protocollo di livello n può essere distribuito tra i sistemi periferici, commutatori di pacchetto ed altri elementi della rete.

![[SOR/RETI/img/img17.png|center|700]]

La figura mostra il percorso dei dati scendendo lungo la pila dei protocolli del sistema mittente, risalendo e scendendo lungo le pile dei protocolli dei commutatori e dei router a livello di collegamento e infine risalendo la pila nel sistema ricevente.

I commutatori a livello di collegamento implementano i livelli 1 e 2, mentre i router implementano i livelli 1 e 3. Questo significa che i router Internet sono in grado di interpretare il protocollo IP (di livello 3), cosa che i commutatori a livello di collegamento non possono fare. 

La figura mostra il concetto di **incapsulamento**. Presso l'host mittente, un messaggio a livello di applicazione M viene passato al livello di trasporto, che nel caso più semplice prende il messaggio e gli concatena informazioni aggiuntive ($H_{t}$) che saranno utilizzate dalla parte ricevente del livello di trasporto. Il messaggio a livello di applicazione e le informazioni di intestazioni a livello di trasporto costituiscono il segmento a livello di trasporto che incapsula il messaggio a livello di applicazione. 
Il livello di trasporto passa il segmento a livello di rete che aggiunge le proprie informazioni ($H_{n}$)  andando a creare un datagramma a livello di rete che viene successivamente passato al livello di collegamento, che a sua volta aggiunge informazioni ($H_l$) creando un frame. 

A ciascun livello il pacchetto ha due tipi di campi: **intestazione** e **payload** ovvero il pacchetto proveniente dal livello superiore.

## Modello ISO/OSI
![[SOR/RETI/img/img16.png|center|200]]

Nel modello ISO/OSI, lo stack protocollare è a 7 livelli, e aggiunge i livelli di: 
- *presentazione*: consente alle applicazioni di interpretare il significato dei dati (crittografia, compressione ecc.)
- *sessione*: implementa le funzioni di sincronizzazione, checkpointing e ripristino dello scambio dei dati.

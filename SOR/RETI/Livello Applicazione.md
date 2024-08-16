Le applicazioni di rete (e-mail, traserimento file, e-commerce, streaming video) sono il cuore delle reti di calcolatori, infatti senza di esse non ci sarebbe bisogno di progettare protocolli di rete per supportarli.

Il cuore dello sviluppo di applicazioni di rete è costituito dalla creazione dei programmi eseguiti dai sistemi periferici e che comunicano tra di loro via rete.

Nelle applicazioni web esistono due programmi diversi che comunicano tra di loro, ovvero il *browser*, che è eseguito dall'host dell'utente, e il *web server* che si trova in un altro host, detto anch'esso web server.

Nello sviluppo di un'applicazione bisogna scrivere software in grado di funzionare su più macchine, senza la necessità di scrivere programmi per i dispositivi nel nucleo di rete, in quanto questi non eseguono applicazioni utente (sono router e commutatori di pacchetto, che implementano fino al terzo livello della pila protocollare). 

Prima di iniziare lo sviluppo software, è necessario avere un progetto dettagliato dell'*architettura dell'applicazione*, stabilendo l'organizzazione sui vari sistemi periferici, basandosi su una delle due principali architetture di rete: 
- client-server
- peer to peer

# Client-server
In questa architettura il **server** è sempre attivo e risponde alle richieste di servizio di molti altri host, detti **client**.

>**Oss.**
>I client non comunicano direttamente tra di loro.

Il server dispone di un indirizzo IP fisso e noto.
In un'applicazione client-server un singolo host server non è in grado di rispondere a tutte le richieste dei suoi client, per questo motivo in queste applicazioni sono utilizzati i data center, che ospitano molti host server.

![[Pasted image 20240814114041.png|center|500]]
# Peer-to-peer
In questa architettura non è necessario l'utilizzo di un server sempre attivo e per questo si sfrutta la *comunicazione arbitraria tra coppie di host*, chiamati **peer**. 
Il punto di forza di questa architettura è la **scalabilità**, infatti ogni peer, sebbene generi carico di lavoro richiedendo file, aggiunge anche capacità di servizio al sistema, rispondendo alle richieste di altri peer. 

![[Pasted image 20240814114052.png|center|500]]

# Processi comunicanti
Prima di costruire un applicazione di rete bisogna anche conoscere come comunicano tra di loro i processi in esecuzione su diversi host.
I processi in esecuzione sullo stesso host comunicano usando le regole dettate dal sistema operativo dell'host stesso. 
*I processi in esecuzione su host differenti* comunicano invece tramite **scambio di messaggi**.

Le applicazioni di rete sono costituite da una coppia di processi che si scambiano messaggi in rete. 

## Processi client e server
Nonostante il nome rimandi all'architettura client-server, questa classificazione è valida anche per i processi nelle applicazioni P2P.

Per ciascuna coppia di processi comunicamenti, se ne etichetta una come **client** e un'altra come **server**. 

>**Es.**
>Nel web, il browser rappresenta il processo client, mentre il web server è il processo server. Nella condivisione di file P2P, il peer che scarica il file viene detto client, mentre chi lo invia è il server

- **processo client**: colui che da inizio alla comunicazione
- **processo server**: processo che attende di essere contattato

# Interfaccia tra processo e rete
Le applicazioni consistono in coppie di processi comunicanti che si scambiano messaggi, che devono passare attraverso la rete. 
Un processo invia/riceve messaggi alla/dalla rete mediante un'interfaccia software detta **socket**. 

![[Pasted image 20240814115817.png|center|500]]

Dall'immagine vediamo come, il socket è l'interfaccia tra il livello di applicazione e il livello di trasporto.

## Indirizzamento
Per ricevere messaggi, un processo necessita di un identificatore. 
In internet, gli host sono identificati attraverso gli indirizzi IP, un numero a 32 bit. Oltre a conoscere l'indirizzo dell'host a cui è destinato il messaggio, è necessario identificare il *processo destinatario*, ovvero lil socket che deve ricevere il messaggio. Questa è un'informazione *necessaria* in quanto sullo stesso host potrebbero essere in esecuzione più processi. Il *numero di porta di destinazione* risolve questo problema, infatti alle applicazioni più note sono assegnati numeri di porta specifici, come la porta 80 per i web server. 

## Servizi di trasporto per le applicazioni
Il socket è l'interfaccia tra un processo applicativo e il protocollo a livello di trasporto. L'applicazione lato mittente invia i messaggi tramite il socket, mentre dal lato del ricevente, il protocollo a livello di trasporto consegna i messaggi al socket del processo ricevente. 

I protocolli di trasporto sono molteplici e nella progettazione di un'applicazione bisogna sceglierne uno, valutando i servizi che i protocolli offrono e scegliendo quelli che fanno al caso proprio. 

Questi servizi sono classificabili in quattro categorie: 
- trasferimento dati affidabile
- throughput
- temporizzazione
- sicurezza

### Trasferimento dati affidabile
Come abbiamo visto i pacchetti possono andare perduti. In alcune applicazioni, la perdita di dati può causare gravi errori, ad esempio nella trasmissione di file, nella posta elettronica ecc.
Per queste applicazioni è quindi necessario un protocollo che garantisca che i dati siano inviati correttamente. 

Applicazioni come quelle multimediali audio/video (streaming video, videochiamate) tollerano invece la perdita dei dati.

### Throughput
Il throughput è il tasso al quale il processo mittente può inviare i bit al processo destinatario. Dato che altre sessioni condividono la banda sul percorso di rete, il throughput può variare nel tempo.
Queste osservazioni ci guidano verso un protocollo a livello di trasporto che puiò fornire un throughput disponibile garantito. 
Ad esempio un'applicazione di rete di telefonia internet, che codifica la voce a 32kbps, necessita che i dati siano inviati nella rete e consegnati al ricevente allo stesso tasso. 

Se il protocollo di trasporto non può fornire questo throughput, l'applicazione dovrà codificare i dati a un livello inferiore. 

Le applicazioni che necessitano di un certo throughput sono dette *sensibili alla banda*.

### Temporizzazione
Alcune applicazioni richiedono che i ritardi siano bassi per essere "efficaci". Per le applicazioni non in tempo reale, i ritardi inferiori sono sempre preferibili a ritardi più consistenti.

### Sicurezza
Un protocollo a livello di trasporto può fornire servizi di sicurezza come riservatezza, integrità dei dati ed autenticazione.

## Servizi di trasporto offerti da Internet
In precedenza abbiamo visto i servizi generali che possono essere forniti. 
Vediamo ora nello specifico i servizi per le applicazioni forniti da Internet. 

I protocolli di trasporto forniti da Internet, a disposizione delle applicazioni sono due: 
- UDP
- TCP
Quando si sviluppano applicazioni web, gli sviluppatori devono subito scegliere tra questi due protocolli.

### Servizio TCP
Prevede un servizio orientato alla connessione e al trasporto affidabile dei dati. 
- **servizio orientato alla connessione**: TCP fa in modo che client e server si scambino informazioni a livello di trasporto *prima* che i messaggi comincino ad essere inviati. Questa procedura è detta *handshaking*, che stabilisce la connessione tra client e server, preparandoli all'invio di pacchetti. Dopo questa fase, si dice che esiste una **connessione TCP** tra i socket dei due processi. La connessione è *full duplex* ovvero i due processi possono scambiarsi contemporaneamente messaggi sulla connessione. Quando termina l'invio di messaggi, l'applicazione chiude la connessione. 
- **trasferimento affidabile**: i processi possono contare su TCP per trasportare i dati senza errori e nel giusto ordine. 
TCP include anche un meccanismo di controllo della congestione, che riguarda il benessere generale di Internet e non quello diretto dei processi comunicanti: quando il traffico in rete è elevato, TCP esegue unna "strozzatura" del processo di invio. 

### Servizio UDP
UDP è un protocollo di trasporto leggeo, senza connessione e fornisce un trasferimento di dati non affidabile. Quando un processo invia un messaggio tramite UDP, il protocollo non garantisce l'arrivo dei dati al processo di destinazione; i messaggi potrebbero quindi giungere a destinazione non in ordine. 
Non include neanche un meccanismo di controllo della congestione, quindi UDP spinge i dati al livello sottostante a qualsiasi velocità

### Sicurezza TCP
Né TCP né UDP forniscono cifratura dei dati, perciò i dati che il processo passa al socket sono inviati in rete in chiaro. Per questo è stato sviluppato un elemento aggiuntivo per TCP, ovvero **TLS (Transport Layer Security)**. 
TCP con l'aggiunta di TLS fa tutto ciò di cui è capace TCP, ma fornisce anche servizi di sicurezza tra processi, come la cifratura, il controllo dell'integrità dei dati e l'autenticazione.

# Web e HTTP
Tratteremo per prima cosa il web, in quanto il suo protocollo a livello di applicazione, l'**HTTP** è diretto e facile da comprendere. 
Una pagina web è costituita da oggetti indirizzabili tramite un URL (file HTML, immagine JPEG, uno script JS ecc.), ognuno dei quali è memorizzato in un web server differente. La maggioranza delle pagine web consiste di un file HTML principale e di diversi oggetti referenziati da esso.

## HTTP
**HTTP** è il protocollo a livello di applicazione del web e ne costituisce il cuore. Questo protocollo è implementato in due programmi, client e server, in esecuzione su sistemi perifierici diversi che comunicano tra loro scambiandosi messaggi HTTP. 

Il protocollo definisce il modo in cui i client web richiedono le pagine ai web server e come questi ultimi le trasferiscono ai client.
Quando un utente richiede una pagina web, il browser invia al server dei *messaggi di richiesta HTTP* per gli oggetti della pagina, il server riceve la richiesta e risponde con dei *messaggi di risposta HTTP*.

HTTP utilizza TCP come protocollo di trasporto. Il client HTTP per prima cosa inizializza una connessione TCP col server creando i socket con cui processi client e server accedono a TCP e tramite cui inviano e ricevono messaggi HTTP.

Il server che invia i file richiesti al client non memorizza alcuna informazione di stato riguardo il client, infatti HTTP è un protocollo *stateless*, ovvero non mantiene informazioni sulle richieste del client, quindi, in caso di un'ulteriore richiesta dello stesso oggetto da parte dello stesso client, anche nel giro di pochi secondi, il server invierà nuovamente l'oggetto richiesto.

### Connessioni HTTP
In molte applicazioni per Internet, client e server comunicano per molto tempo, con il client che invia una serie di richieste e il server che risponde a ciascuna di esse. In base all'applicazione le richieste possono essere effettuate in sequenza, periodicamente a intervalli regolari o in modo intermittente. Quando questo tipo di interazione client-server viene fatta con TCP, gli sviluppatori devono prendere una decisione: *ciascuna coppia richiesta/risposta deve essere inviata su una connessione TCP separata o devono essere inviate tutte sulla stessa connessione TCP?* 
Nel primo caso si parla di **connessioni non persistenti**, mentre nel secondo di **connessioni persistenti** (utilizzata nella modalità di default di HTTP).

#### HTTP con connessioni non persistenti
Vediamo passo per passo cosa succede con questo tipo di connessione. 
Supponiamo che la pagina web consista di un file HTML principale e di 10 immagini JPEG e che tutti gli oggetti risiedono sullo stesso web server. 
Utilizziamo come esempio il seguente link: 
```
http://www.someschool.edu/someDepartment/home.index
```

1. Il client inizializza una connessione TCP col server `www.scomeschool.edu` sulla porta 80, creando i socket per il client e per il server.
2. Il client HTTP, tramite il socket, invia un messaggio di richiesta HTTP che include il percorso `/someDepartment/home.index` 
3. Il server HTTP riceve il messaggio di richiesta attraverso il socket associato, recupera l'oggetto richiesto, lo incapsula in un messaggio di risposta HTTP e lo invia al client attraverso il socket
4. Il processo server HTTP comunica a TCP di chiudere la connessione, che però, prima di farlo, verifica che il client abbia ricevuto correttamente il messaggio di risposta.
5. Il client HTTP riceve il messaggio di risposta. La connessione TCP termina. Il messaggio indica che l'oggetto incapsulato è un file HTML. Il client estrae il file dal messaggio di risposta, esamina il file HTML e trova i riferimenti ai 10 oggetti JPEG
6. Vengono ripetuti i primi 4 passi per ogni oggetto JPEG

##### Tempi di risposta
Facciamo un calcolo per stimare il tempo che intercorre tra la richiesta di un file HTML da parte del client e il momento in cui il file viene ricevuto. 

**RTT (Round Trip Time)** è il tempo impiegato da un piccolo pacchetto per andare dal client al server e tornare indietro. Questo valore include i vari [[Introduzione ad Internet#Tipi di ritardo|ritardi]] già visti.

![[Pasted image 20240816135039.png|center|500]]

Vediamo cosa succede quando si clicca col mouse su un collegamento ipertestuale:
- si inizializza la connessione TCP col web server, tramite *handshake a tre vie*: il client invia un piccolo segmento TCP al server che a sua volta manda una conferma. Il client, alla ricezione, manda una conferma indietro al server. 
Le prime due parti dell'handshake richiedono un RTT. Dopo il completamento, il messaggio invia un messaggio di richiesta HTTP combinato con la terza parte dell'handshake, ovvero la conferma di ricezione (*acknowledgement*). Quando la richiesta arriva al server, questo inoltra il file richiesto e la richiesta-risposta consuma un'altro RTT. Il tempo di risposta totale è quindi di due RTT più il tempo di trasmissione del file da parte del server.

#### Connessioni persistenti
Le connessioni non persistenti hanno dei limiti, ovvero che per ogni oggetto richiesto occorre stabilire una nuova connessione, portando ad overhead sia nel client che nel server. Inoltre ogni oggetto richiesto subisce un ritardo di due RTT: uno per stabilire la connessione e uno per la richiesta-risposta.

Con le connessioni persistenti, il server lascia la connessione TCP aperta dopo l'invio della risposta, quindi le richieste e le risposte tra gli stessi client e server sono trasmesse sulla stessa connessione, col client che invia le richieste appena incontra un oggetto referenziato, necessitando di un solo RTT per essi.

### Messaggi di richeista HTTP
```HTTP
GET /somedir/page.html HTTP/1.1
Host: www.someschool.edu
Connection: close 
User-agent: Mozilla/5.0 
Accept-language: fr
```

La prima linea è detta **riga di richiesta**, quelle successive **righe di intestazione**.
- La riga di *richiesta* presenta tre campi: il campo metodo che può assumere i valori GET (utilizzato quando il browser richiede un oggetto identificato dal campo URL), POST, HEAD, PUT e DELETE, il campo URL e la versione HTTP. 
- La riga *host* specifica l'host dove risiede l'oggetto e il numero di porta (se assente si assume 80 per HTTP e 443 per HTTPS) del server a cui si invia la richiesta. 
- *User-agent*: indica l'applicazione, il sistema operativo, il vendor e la versione del browser che effettua la richiesta
- *Accept*: specifica dei valori per indicare quali tipo di contenuto il client è in grado di comprendere. In questo caso *accept-language: fr* indica al server la lingua preferita dal client
- *Connection*: controlla se la connessione rimarrà aperta al termine dello scambio richiesta-risposta. Il valore *close* indica che la conenssione sarà chiusa, altrimenti il valore *keep-alive* indica che la connessione rimarrà aperta.

Il formato generale della richiesta HTTP è la seguente: 

![[Pasted image 20240816144144.png|center|500]]

#### Metodi di richiesta HTTP
- Metodo POST: utilizzato quando nella pagina web sono presenti dei form per l'input di dati. Il corpo del messaggio contiene cosa l'utente ha inserito nei campi di input del form
- Metodo HEAD: utile per il debugging. Quando un server riceve una richiesta col metodo HEAD, invia una risposta, ma non l'oggetto richiesto.
- Metodo PUT: consente ad un utente, di caricare un oggetto su una specifica directory del web server. Sostituisce completamente il file esistente all'URL specificato con il contenuto del corpo del messaggio di richiesta HTTP PUT

### Messaggi di risposta HTTP
```HTTP
HTTP/1.1 200 OK 
Connection: close 
Date: Thu, 18 Aug 2015 15:44:04 GMT 
Server: Apache/2.2.3 (CentOS) 
Last-Modified: Tue, 18 Aug 2015 15:11:03 GMT 
Accept-Ranges: bytes
Content-Length: 6821 
Content-Type: text/html 
(data data data data data ...)
```

In un messaggio di risposta ci sono tre sezioni: **riga di stato**, delle **righe di intestazione** e il corpo.

- La riga di stato presenta tre campi: la versione del protocollo (HTTP/1.1), un codice di stato (200) e un messaggio di stato (OK).
Analizziamo ora le righe di intestazione: 
- Connection: il campo close serve a comunicare al client che ha intenzione di chiudere la connessione TCP dopo l'invio del messaggio.
- Date: indica ora e data di creazione e invio della risposta HTTP da parte del server
- Server: analogo alla riga User-agent nel messaggio di richiesta HTTP
- Last-Modified: indica l'istante e la data di creazione o ultima modifica dell'oggetto
- Accept-Ranges: indica il supporto del server ai download, indicando l'unità che si può usare per esprimere l'intervallo richiesto
- Content-Length: lunghezza in byte del corpo dell'entità inviato al ricevente
- Content-type: indica il tipo dell'oggetto inviato

#### Codici di stato della risposta HTTP
Si possono suddividere i codici in 5 categorie, in base alla prima cifra:
- **1xx Informational**: risposta intermedia per comunicare lo stato di connessione o l'avanzamento della richiesta prima di completare l'azione richiesta e inviare una risposta finale
- **2xx Successful**: richiesta ricevuta con successo, compresa e accettata
- **3xx Redirect**: client deve eseguire ulteriori azioni per soddisfare la richiesta
- **4xx Client error**: richiesta sintatticamente scorretta o non può essere soddisfatta
- **5xx Server error**: server ha fallito nel soddisfare una richiesta valida
I codici più comuni sono: 
- **200 OK**: richiesta avvenuta con successo; l'oggetto viene inviato nella risposta
- **301 Moved Permanently**: l'oggetto richiesto è stato trasferito, la nuova posizione è visibile nell'intestazione *Location* della risposta
- **400 Bad Request**: messaggio di richiesta non compreso
- **404 Not found**: documento richiesto non presente nel server a cui viene fatta la richiesta
- **406 Not Acceptable**: l'oggetto richiesto non esiste nella forma che soddisfi i vari campi *Accept-*
- **505 HTTP Version Not Supported**: il server non ha la versione di protocollo HTTP richiesta
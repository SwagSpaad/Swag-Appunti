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

![[SOR/RETI/img/img18.png|center|500]]
# Peer-to-peer
In questa architettura non è necessario l'utilizzo di un server sempre attivo e per questo si sfrutta la *comunicazione arbitraria tra coppie di host*, chiamati **peer**. 
Il punto di forza di questa architettura è la **scalabilità**, infatti ogni peer, sebbene generi carico di lavoro richiedendo file, aggiunge anche capacità di servizio al sistema, rispondendo alle richieste di altri peer. 

![[SOR/RETI/img/img19.png|center|500]]

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

![[SOR/RETI/img/img20.png|center|500]]

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

**RTT (Round Trip Time)** è il tempo impiegato da un piccolo pacchetto per andare dal client al server e tornare indietro. Questo valore include i vari [[Capitolo 0 - Introduzione ad Internet#Tipi di ritardo|ritardi]] già visti.

![[SOR/RETI/img/img21.png|center|500]]

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

![[SOR/RETI/img/img22.png|center|500]]

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

### Mantenere stato utente/server con i cookie
Come abbiamo visto HTTP è stateless. Tuttavia è utile che i server possano autenticare gli utenti, ad esempio per limitare l'accessl da parte di essi o per fornire contenuti in funzione della loro identità. Per questo scopo sono utilizzati i **cookie**, che consentono ai server di tenere traccia degli utenti.

La tecnologia dei cookie presenta 4 componenti:
- riga di intestazione nel messaggio di risposta HTTP
- riga di intestazione nel messaggio di richiesta HTTP
- un file mantenuto sul sistema dell'utente e gestito dal browser
- un database sul sito

Vediamo il funzionamento dei cookie. Supponiamo che un utente visita un sito di ecommerce per la prima volta. 
- Quando la richiesta HTTP arriva al server, l'applicazione web crea un numero identificativo nel database dei cookie del sito.
- Il server web risponde al browser inserendo il numero generato nella voce *set-cookie* del messaggio di risposta HTTP
- Quando il browser riceve la risposta HTTP, vedendo il campo *set-cookie* crea e memorizza un file cookie associato al sito.
- Quando l'utente, navigando nel sito, richiede una pagina, il browser consulta il file cookie relativo al sito, estrae il numero di identificazione e lo inserisce nella richiesta HTTP nella linea *cookie*

![[SOR/RETI/img/img23.png|center|500]]

I cookie sono utilizzati con vari scopi: 
- tracciare il comportamento su un dato sito (*cookie di prima parte*)
- tracciare il comportamento degli utenti su più siti senza che l'utente abbia scelto di visitare il sito del tracker (*cookie di terze parti*)
I cookie sono spesso oggetto di controversie legate alla violazione della privacy, in quanto mediante una combinazione di informazioni fornite dall'utente e l'utilizzo di cookie, un sito web può imparare molto sull'utente.

### Web Cache
Una *web cache* (o *proxy server*) è un entità di rete che soddisfa richieste HTTP al posto del web server effettivo. Essa ha una propria memoria su disco locale in cui conserva copie di oggetti richiesti recentemente.
Il browser può essere configurato in modo che tutte le richieste HTTP vengano dirette al proxy server: 
- se l'oggetto è nella cache, allora la cache fornisce l'oggetto al client
- altirimenti la cache richiede l'oggetto al server d'origine, lo memorizza nella cache e lo invia al client
Osserviamo che la cache opera sia da client (quando richiede al server) che da server (quando fornisce al client d'origine).

Questa tecnica è utilizzata per ridurre i tempi di risposta alle richieste dei client, in quanto la cache è più vicina ai client e perché riduce il traffico sul collegamento di accesso ad internet.

Per comprendere i benefici del web caching consideriamo l'esempio nell'immagine sottostante

![[SOR/RETI/img/img24.png|center|500]]

Vediamo la presenza di due reti: quella di un ente (ad alta velocità 100Mbps) e quella pubblica (con collegamento d'accesso a 15Mbps). Supponiamo che la dimensione di un oggetto sia di 1Mbit e che i browser abbiano una frequenza di 15 richieste al secondo verso i server. Supponiamo inoltre che i messaggi di richiesta HTTP siano piccoli e non creino traffico nelle reti e che l'RTT tra router dell'ente e router di internet sia di 2 secondi. 
Il tempo totale di risposta (dalla richiesta alla ricezione) è la somma del ritardo sulla rete locale, del ritardo di accesso e dell'RTT, che possiamo stimare nel seguente modo:
 - intensità di traffico sulla rete locale $$(15\text{ richieste/s})\cdot\frac{1\text{ Mbit/richiesta}}{100\text{ Mbps}}=0.15$$
 - intensita di traffico sul collegamento di accesso $$(15\text{ richieste/s})\cdot\frac{1\text{ Mbit/richiesta}}{15\text{ Mbps}}=1$$
Un'intensità di traffico di 0.15 sulla rete locale provoca un ritardi di decine di millisecondi, che può essere trascurato, ma quando questo valore si avvicina a 1, il ritardo cresce notevolmente e senza limiti. 

Una possibile soluzione può ampliare la banda sul collegamento d'accesso ad Internet a 100 Mbps, ma questo vuol dire che bisogna aggiornare il proprio collegamento a Internet, che può risultare costoso. 
La soluzione alternativa è mediante l'utilizzo di uun proxy nella rete dell'ente. Supponiamo che la percentuale di successo, ovvero le frazioni di richieste soddisfatte dalla cache, sia del 40%, quindi il 60% delle richieste deve essere ancora soddisfatto dai server d'origine, ma ora solo il 60% degli oggetti richiesti passa attraverso il collegamento di accesso, riducendo l'intensità di traffico da 1 a 0,6. In generale, un'intensità di traffico inferiore a 0,8 su un collegamento da 15 Mbps corrisponde ad un ritardo dell'ordine delle decine di millisecondi, che diventa trascurabile.
In base a queste considerazioni, il ritardo medio risulta quindi $$0,4\cdot(0,01 \text{ secondi})+0,6\cdot(2,01\text{ secondi})$$
che è di poco superiore a 1,2 secondi. Questa soluzione fornisce un tempo di risposta inferiore rispetto alla prima e non richiede l'aggiornametno del collegamento dell'ente ad Internet. 

#### GET condizionale
Nonostante il web caching riduce i tempi di risposta, introduce un nuovo problema, ovvero che la copia di un oggetto in cache potrebbe essere scaduta, cioé l'oggetto nel server web potrebbe essere stato modificato rispetto la copia presente nella cache. 
HTTP presenta un meccanismo che permette alla cache di verificare se i suoi oggetti sono aggiornati: il **GET condizionale**.
Una richiesta HTTP è detta messaggio di GET condizionale se usa il metodo GET e include una riga *IF-modified-since:*.

Vediamo un esempio: 
- Un proxy invia una richiesta ad un server per conto di un browser richiedente 
```HTTP
GET /fruit/kiwi.gif HTTP/1.1
Host: www.exoticquecuisine.com
```

- Il server web invia al proxy la risposta con l'oggetto richiesto
```HTTP
HTTP/1.1 200 OK
Date: Sat, 3 Oct 2015 15:39:29
Server: Apache/1.3.0 (Unix)
Last-modified: Wed, 9 Sep 2015 09:23:24
Content-type: image/gif
```

- Il proxy inoltra l'oggetto al browser richiedente e lo salva anche nella cache locale, memorizzando anche la data di ultima modifica. 
- Una settimana più tardi, un altro browser richiede lo stesso oggetto attraverso il proxy, con l'oggetto ancora in cache. Dato che l'oggetto può essere stato modificato nel web server durante il tempo trascorso, il proxy controlla se ci sono state modifiche inviando un get condizionale 
```HTTP
GET /fruit/kiwi.gif HTTP/1.1
Host: www.exotiquecuisine.com
If-modified-since: Wed 9 Sep 2015 09:23:24
```

- Il web server invia una risposta al proxy 
```HTTP
HTTP/1.1 304 Not Modified 
Date: Sat, 10 Oct 2015 15:39:29 
Server: Apache/1.3.0 (Unix) 
(corpo vuoto)
```
Nella risposta osserviamo che il corpo non viene inviato in quanto sarebbe uno spreco di banda. La riga di stato 304 Not Modified comunica al proxy che non sono avvenute modifiche e che può inoltrare la pagina dalla cache al browser richiedente.

## HTTP/2
HTTP/2 è la prima nuova versione di HTTP da HTTP/1.1. Per capire la necessità di questa introduzione, ricordiamo che HTTP/1.1 utilizza le connessioni TCP persistenti, consentendo l'invio di una pagina web dal server al client su un'unica connessione TCP. Avendo una sola connessione per pagina web, il numero di socket sul server è limitato e ogni pagina web trasportata ottiene una condivisione della larghezza di banda equa.
Col tempo  è stato scoperto che l'invio di tutti gli oggetti in una pagina web su una singola connessione TCP ha il problema del **Head Of Line Blocking (HOL Blocking)**. Ricordiamo che il server risponde alle richieste GET in ordine secondo un algoritmo FCFS e questo comporta che oggetti piccoli debbano aspettare la trasmissioni dietro ad uno o più oggetti grandi. Questo problema è aggirato dai browser HTTP/1.1 aprendo più connessioni TCP parallele. Il controllo di congestione TCP mira ad assegnare ad ogni connessione TCP che condivide un collegamento a collo di bottiglia, una quota uguale di larghezza di banda. Se ci sono $n$ connessioni TCP che operano su un link bottleneck, ogni connessione ottiene $1/n$ della larghezza di banda. Aprendo più connessioni parallele, il browser può imbrogliare e prendere una parte maggiore di larghezza di banda, infatti molti browser HTTP/1.1 aprono fino a sei connessioni TCP parallele sia per aggirare il blocco HOL sia per ottenere più banda.

L'*obiettivo* principale di HTTP/2 è di eliminare o ridurre il numero di connessioni TCP parallele, che porta anche alla riduzione del numero di socket da aprire e consente al controllo della congestione TCP di funzionare correttamente. Con una sola connessione TCP, però, richiede dei meccanisimi accurati per evitare il blocco HOL

### Framing HTTP/2
La soluzione al blocco HOL di HTTP/2 è di suddividere ogni messaggio in piccoli frame e alternare i messaggi di richiesta e risposta sulla connessione TCP, ricostruendo poi i messaggi lato client.
Quando un server vuole inviare una risposta HTTP, questa viene elaborata dal sottolivello di framing, dove è scomposta in frame. Il campo di intestazione della risposta diventa un frame e il corpo del messaggio è suddiviso in più frame aggiuntivi. I frame sono quindi intervallati ed inviati con i frame di altre risposte tramite una sola connessione TCP presistente. Man mano che il client riceve i frame, questi vengono prima ricostruiti nei messaggi di risposta originale dal sottolivello di framing ed elaborati dal browser nel modo tradizionale. 
Anche le richieste HTTP sono suddivise in frame ed intervallate.

![[SOR/RETI/img/img25.png|center|500]]

## Da HTTP/2 ad HTTP/3
HTTP/2 su una singola connessione TCP significa che il recupero dalla perdita di pacchetti blocca tutte le trasmissioni di oggetti e noin c'è nessuna sicurezza su una connessione TCP semplice. HTTP/3 aggiunge sicurezza, controllo degli errori e congestione su UDP

# Posta elettronica in Internet
La posta elettronica è stata da sempre l'applicazione più diffusa in Internet. 

![[SOR/RETI/img/img26.png|center|500]]

In figura possiamo vedere una rappresentazione del sistema postale di Internet. Si individuano tre componenti principali: 
- **user agent** (gmail, outlook ecc.): permettono all'utente di leggere, rispondere, inoltrare, salvare e comporre i messaggi, 
- **mail server**: costituisce la casella di posta personale di ogni utente, che gestisce e contiene i messaggi a lui inviati. Se il server non può consegnare un messaggio, lo trattiene in una *coda dei messaggi* e cerca di trasferirlo in un secondo momento
- **protocollo SMTP**
## SMTP
SMTP è il protocollo a livello applicativo per la posta elettronica. Utilizza TCP per il trasferimento affidabile da client a server sulla porta 25.
Nonostante sia ancora utilizzato come protocollo per l'invio di mail su Internet, presenta delle caratteristiche abbastanza vecchie, come l'utilizzo di testo ASCII a 7 bit per il corpo dei messaggi, in passato molto utile per la bassa velocità di trasferimento, ma oggi piuttosto penalizzante in quanto file multimediali vanno codificati in ASCII.

Vediamo come SMTP opera. Supponiamo che un utente mittente (Alice) voglia inviare ad un destinatario (Bob) una mail:
- Alice usa il suo user agent di posta elettronica, fornisce l'indirizzo mail di Bob, scrive il messaggio e chiede allo user agent di inviarlo
- Lo user agenti di Alice invia il messaggio al server mail di riferimento e il messaggio viene inserito in una coda dei messaggi
- Il lato client di SMTP, eseguito sul server di Alice, vede il messaggio in coda ed apre una connessione TCP verso il server SMTP di Bob
- Dopo l'handshaking SMTP, il client SMTP invia il messaggio di Alice sulla connessione TCP
- Il lato server di SMTP, il mail server di Bob, riceve il messaggio e lo colloca nella mailbox di Bob
- Bob ora, quando vuole, può utilizzare il suo user agent per leggere il messaggio

![[SOR/RETI/img/img27.png|center|500]]

### Confronto con HTTP
Entrambi i protocolli sono utilizzati per trasferire file da un host ad un altro, ma mentre HTTP è utilizzato per trasferire oggetti da un web server a un web client, SMTP trasferisce messaggi di posta elettronica da un mail server ad un altro.

Durante il trasferimento, sia HTTP che SMTP utilizzano connessioni persistenti, quindi presentano caratteristiche comuni, ma anche qualche differenza. 
Innanzitutto HTTP è un protocollo PULL, ovvero gli utenti utilizzano HTTP per attirare a sé (pull) gli oggetti sul web server, mentre SMTP è un protocollo push, ovvero il mail server di invio spinge i file al mail server di ricezione.

SMTP deve comporre l'intero messaggio in ASCII a 7 bit, anche se il messaggio contiene caratteri non appartenenti ad ASCII a 7 bit (ad esempio lettere accentate) tramite codifica. HTTP non impone questo vincolo.

Inoltre HTTP incapsula ogni oggettoi nel proprio messaggio di risposta HTTP, mentre SMTP trasmette più oggetto in un unico messaggio.

### Formato dei messaggi di posta elettronica
Un messaggio mail è costituito da una serie di linee di intestazione e dal corpo del messaggio, separate da una riga vuota. 
Come per HTTP ogni linea è formata da una parola chiave seguita dai due punti, con alcune parole chiave obbligatorie (From: e To:) e altre facoltative (Subject: ). 
Dopo l'intestazione del messaggio, segue una linea vuota e poi il corpo del messaggio, che si chiude con un punto.

### Protocolli di accesso alla posta
SMTP è usato principalmente per trasferire i messaggi dal server del mittente al server del destinatario, ma è anche usato per trasferire messaggi da un programma di posta del mittente alla sua casella di posta sul server di posta elettronica. Non consente però di trasferire messaggi dalla mailbox dell'utente al suo programma di posta elettronica. 

Esistono vari protocolli di accesso alla posta, tra cui:
- POP3
- IMAP: consente di recuperare, cancellare e archiviare i messaggi memorizzati sul server
- HTTP: consente un interfaccia web sopra a SMTP per l'invio e IMAP (o POP) per il recupero delle mail

# DNS
Gli host Internet sono identificati dall'indirizzo IP, ma più comunemente da nomi in quanto più facili da ricordare. Per le persone l'utilizzo di nomi è preferibile, ma i router prediliggono gli indirizzi IP.
Per conciliare i due approcci è necessario utilizzare un servizio per tradurre i nomi degli host nei loro indirizzi IP e questo è compito del **DNS**.

DNS è sia un database distribuito implementato in una gerarchia di DNS server, sia un protocollo a livello di applicazione che permette agli host di interrogare il database per la traduzione nome-indirizzo. 

DNS utilizza UDP e la porta 53, ma se la risposta del server DNS supera i 512 byte, si riceve un particolare messaggio che richiede di utilizzare TCP, che utilizza comunque la porta 53.

Oltre alla traduzione degli hostname in indirizzi IP, DNS mette a disposizione altri servizi tra cui:
- **Alias degli hostname**: l'hostname www.informatica.uniroma2.it ha l'alias www.cs.uniroma2.it. In questo caso www.informatica.uniroma2.it è detto *hostname canonico*
- **Mail server aliasing**: anche gli indirizzi di posta elettronica devono essere semplici da ricordare, ad esempio, con un account hotmail, l'indirizzo di posta sarà user@yahoo.com, ma l'hostname del server di posta Hotmail potrebbe avere come nome canonico relay1.west-coast.yahoo.com. Un'applicazione di posta può invocare DNS per ottenere il nome canonico di un alias fornito e il suo indirizzo IP.
- **Distribuzione del carico**: DNS svolge anche la funzione di distribuire il carico dei dati copiati su più server web. I server web più visitati sono copiati infatti su server detti *mirror* ciascuno dei quali ha un indirizzo IP differente. In questo caso per ogni hostname canonico, il DNS memorizza un gruppo di indirizzi IP. Quando i client inviano una richiesta al DNS per avere l'indirizzo di un server web mirror, DNS risponde inviando l'insieme di indirizzi IP, ma ad ogni nuova richiesta ruota l'ordine degli indirizzi, siccome ogni richiesta è inviata al primo indirizzo IP elencato nell'insieme.

## Funzionamento del DNS
Supponiamo che una certa applicazione in esecuzione sull'host di un utente debba tradurre un hostname in indirizzo IP. L'applicazione invocherà il lato client del DNS specificando l'hostname da tradurre e il DNS sull'host prende il controllo inviando una query DNS sulla rete, all'interno di *datagrammi UDP diretti alla porta 53*. Dopo un ritardo, il client DNS riceve un messaggio di risposta contenente la corrispondenza desiderata, che viene poi passata all'applicazione richiedente. 

Il DNS dal punto di vista dell'applicazione è una black-box che traduce in modo semplice e diretto, ma in pratica la black-box è cotstituita da un gran numero di server DNS distribuiti nel mondo e dal protocollo applicativo che specifica la comunicazione tra DNS server e host richiedenti. 
Un primo approccio è quello di utilizzare un DNS server che contiene tutte le corrispondenze a cui tutti i client invierebbero le richieste. Questo approccio però è inappropriato a causa di varie problematiche: 
- **Un solo point of failure**: se si guasta il server, l'intero internet ne soffre
- **Volume di traffico**: il singolo server deve gestire tutte le richieste
- **Database centralizzato distante**: un singolo server non può essere vicino a tutti i client
- **Manutenzione**: il server dovrebbe contenere record relativi a tutti gli host di internet e dovrebbe essere aggiornato frequentemente per tener conto di ogni nuovo host

### Database distribuito e gerarchico
Per ovviare ai problemi sopra, il DNS utilizza un grande numero di server distribuiti nel mondo ed organizzati gerarchicamente. Esistono tre classi di DNS server: i *root server*, i *top-level domain server* e *server autoritativi*.
Vediamo un esempio per capire la relazione tra le classi. Supponiamo che un client DNS vuole determinare l'indirizzo IP dell'hostname www.amazon.com: 
- il client contatta uno dei root server, che gli restituisce uno o più indirizzi IP relativi al TLD server per il dominio *com*.
- Il client contatta uno di questi TLD server, che gli restituisce uno o più indirizzi IP del server autoritativo *amazon.com*
- infine contatta uno dei server autoritativi di amazon.com che gli restituisce l'IP dell'hostname www.amazon.com+

![[SOR/RETI/img/img28.png|center|500]]

Analizziamo da vicino le classi di DNS server:
- **root server**: forniscono gli indirizzi IP dei TLD server. Quando un server DNS non è in grado di risolvere un nome di dominio, il root server è raggiunto come ultimo tentativo di risoluzione. Se questo non ha informazioni sul dominio richiesto, fornirà informazioni su quale TLD server contattare per ricercare il nome
- **TLD server**: si occupano dei domini .com, .org, .net e dei domini locali di alto livello come .uk, .fr, .jp ecc.
- **DNS server autoritativi**: sono DNS server propri di ogni organizzazione, che forniscono i mapping ufficiali da hostname a IP per gli host dell'organizzazione.
Esiste anche il DNS server locale, che non appartiene alla gerarchia di server, ma è centrale nell'architettura DNS. Ciascun ISP (università, residenziale ecc.) ha un DNS server locale. Quando un host si connette ad un ISP, questo gli fornisce un indirizzo IP tratto da uno o più dei suoi DNS server locali tramite DHCP. Quando un host effettua una richiesta DNS, questa viene inviata al DNS server locale che opera da proxy e inoltre la query al DNS server.
Supponiamo che l'host cse.nyu.edu voglia l'indirizzo IP di gaia.cs.umass.edu e supponiamo che il DNS server locale per cse.nyu.edu sia dns.nyu.edu, mentre un server autoritativo per gaia.cs.umass.edu sia dns.umass.edu.

![[SOR/RETI/img/img29.png|center|500]]

Come possiamo vedere in figura, l'host richiedente invia un messaggio di richiesta al DNS server locale, che contiene il nome da tradurre. Il server locale inoltra il messaggio ad un root server, che prende nota del suffisso .edu e restituisce al server locale un elenco di indirizzi IP per i TLD server responsabili di edu. Il server locale invia quindi il messaggio di richiesta ad uno dei TLD server, che prende nota del suffisso umass.edu e risponde con l'indirizzo IP del server autoritativo dell'Università del Massachusetts, ovvero dns.umass.edu. Infine il DNS server locale inoltra il messaggio di richiesta direttamente al DNS server autoritativo che risponde con l'indirizzo IP di gaia.cs.umass.edu.
Osserviamo che sono stati inviati otto messaggi DNS per ottenere la mappatura, vedremo come il DNS caching riduce questo traffico

## DNS caching
Ogni volta che un qualsiasi name server impara la mappatura di un hostname, la salva nela cache e restituisce immediatamente il mapping nella cache in risposta ad una query. Il caching migliora i tempi di risposta ed inoltre le voci della cache vanno in timeout (scompaiono) dopo un certo periodo di tempo (TTL). I server TLD sono generalmente memorizzati nella cache dei DNS server locali. 
Tuttavia le voci nella cache potrebbereo essere obsolete, infatti se l'host cambia il suo indirizzo IP, questo potrebbe non essere conosciuto su internet fino alla scadenza di tutti i TTL.

## Record DNS
I server che implementano il database distribuito, memorizzano i **record di risorsa (RR)**. Ogni messaggio di risposta DNS trasporta uno o più RR. Un record di risorsa ha il seguente formato: `(name, value, type, ttl)`.
**TTL** indica l'intervallo di tempo prima che il record venga rimosso dalla cache. I significati dei campi `name` e `value` dipendono dal campo `type`:
- se `type=A` allora `name` indica l'hostname e `value` indica l'indirizzo IP
- se `type=NS` allora `name` indica il dominio (es. foo.com) e `value` indica l'hostname del server autoritativo del dominio
- se `type=CNAME` allora `name` indica il nome alias di qualche nome canonico e `value` indica il nome canonico
-  se `type=MX` allora `value` è il nome di un mail server associato a `name.` Questo tipo di record permette ai nomi dei server di posta di avere alias.

## Messaggi DNS
I messaggi di richiesta e risposta DNS hanno lo stesso formato illustrato in figura,.

![[SOR/RETI/img/img31.png|center|500]]

L'intestazione è costituita dai primi 12 byte e ha 6 campi: 
- identificazione (16 bit): è un numero che identifica la richiesta. Il messaggio di risposta avrà lo stesso numero.
- campo flag contiene:
	- flag richiesta/risposta: se è 0 allora il messaggio è una richiesta, se è 1 il messaggio è una risposta
	- flag autorità: se è 1 nel messaggio di risposta indica che un name server è server di autorità per il nome richiesto
	- flag di richiesta di ricorsione: inserito quando un client desidera che il name server effettui una ricorsione quando non trova il record
	- flag disponibilità alla ricorsione: è inserito nella risposta quando il name server supporta la ricorsione
- Campi "numero di...": indicano il numero di occorrenze dei quattro tipi di sezioni di dati che seguono l'intestazione
- sezione delle domande: contiene informazioni sulla richiesta fatta. Contiene un campo nome che contiene il nome da richiedere e il campo tipo che contiene il tipo di richiesta (A, MX, CNAME...)
- sezione risposte: contiene i record di risorsa per il nome richiesto. Può contenere record multipli
- sezione autoritativa: contiene i record di altri server autoritativi
- sezione aggiuntiva: contiene record aggiuntivi, ad esempio il campo risposte in un messaggio di risposta a una richiesta MX conterrà il nome di un server di posta associato con il nome alias. La sezione aggiuntiva conterrà un record di tipo A che fornisce l'indirizzo IP per il nome canonico del mail server.

## Inserire record nel database DNS
Supponiamo di aver fondato una società chiamata Network Utopia. Per registrare il nome di dominio dobbiamo farlo presso un *registrar*, un ente di registrazione, che verifica l'unicità del nome di dominio e lo inserisce nel database DNS. Per registrare il nome networkutopia.com dobbiamo fornire al registrar il *nome e gli indirizzi IP* dei server autoritativi ed il registrar procede inserendo due RR nel TLD server.com:
```
(networkutopia.com, dns1.networkutopia.com, NS)
(dns1.networkutopia.com, 212.212.212.1, A)
```

## Vulnerabilità del DNS
- **Attacchi DDoS** con inondamento di banda. L'attacco sui root server non è stato effettuato con successo, in quanto possono essere configurati per bloccare messaggi ICMP. Gli attacchi potenzialmente più efficaci sono quelli che inondano di richieste DNS i server di primo livello.
- **Man-in-the-middle**: attacco che intercetta le richieste DNS e restituisce risposte false.

# Architettura P2P
Le applicazioni viste fin'ora utilizzavano un'architettura di tipo client-server, che richiedono dei server sempre attivi. Nell'architettura P2P questa dipendenza è minima, in quanto le coppie di host comunicano direttamente l'uno con l'altro. 

## Scalabilità del P2P
Per confrontare le architetture client-server e P2P ed illustrare la scalabilità del P2P consideriamo questo modello di distribuzione di file a un insieme di peer per entrambe le tipologie di architettura.

![[SOR/RETI/img/img32.png|center|500]]

Server e peer sono collegati ad internet con collegamenti di accesso. 
Sia $u_{s}$ la banda di upload del collegamento di accesso del server, $u_{i}$ la banda di download del collegamento del peer $i$ e $d_{i}$ la banda di download del peer $i$. Denotiamo con $F$ la dimensione del file in bit e $N$ il numero di peer che vuole una copia del file.
Il **tempo di distribuzione** è il tempo richiesto perché tutti gli $N$ ricevano la copia del file.

Determiniamo il tempo di distribuzione del file per l'architettura client-server e lo indichiamo con $D_{cs}$. Nel client server nessun peer aiuta nella distribuzione del file. Osserviamo che:
- il server deve trasmettere una copia del file a ciascuno degli $N$ peer, deve quindi trasmettere $NF$ bit. Dato che la banda di upload del server è $u_{s}$, il tempo per distribuire il file è almeno $\frac{NF}{u_{s}}$ 
- Sia $d_{min}$ la banda di download del peer col valore più basso; questo peer non può ricevere gli $F$ bit del file in meno di $\frac{F}{d_{min}}$ secondi. Quindi il tempo minimo di distribuzione è almeno $\frac{F}{d_{min}}$
Da questo otteniamo che: $$D_{cs}\geq\max\bigg\{\frac{NF}{u_{s}}, \frac{F}{d_{min}}\bigg\}$$ che fornisce un limite inferiore al tempo di distribuzione minimo. Osserviamo che il tempo di distribuzione aumenta linearmente col crescere del numero $N$ di peer, infatti se il numero di peer aumenta da un migliaio a un milione, il tempo richiesto per distribuire il file a tutti i peer aumenta di 1000 volte.

Facciamo un'analisi analoga per l'architettura P2P, in cui ogni peer assiste il server nella distribuzione del file, infatti quando un peer riceve dati di un file, può usare la banda di upload per distribuire a sua volta i dati ad altri peer.
Facciamo prima alcune considerazioni: 
- all'inizio della distribuzioni solo il server dispone del file, quindi per trasmetterlo ai peer deve inviare i bit del file almeno una volta nel collegamento di accesso. Il tempo minimo di distribuzione quindi è $\frac{F}{u_{s}}$. A differenza del client-server è sufficiente che il server invii i bit del file una sola volta, in quanto i peer possono redistribuire i bit tra di loro.
- Come per il client-server il peer con la velocità di download minima non può ottenere i bit del file in meno di $\frac{F}{d_{min}}$ secondi.
- Osserviamo che la capacità totale di upload del sistema è uguale alla somma di tutte le velocità di upload, compresa quella del server. Il sistema deve consegnare $F$ bit a ciascuno degli $N$ peer, consegnando in totale $NF$ bit. Questo non può essere fatto ad una velocità più grande di $u_{tot}=u_{s}+u_{1}+\dots+u_{N}$, quindi il tempo di distribuzione minimo è almeno $\frac{NF}{u_{tot}}$
Mettendo insieme questre tre osservazioni, il tempo di distribuzione minimo per l'architettura P2P è $$D_{P2P}\geq\max\Bigg\{\frac{F}{u_{s}},\frac{F}{d_{min}},\frac{NF}{u_{s}+\sum\limits_{i=1}^{N}u_{i}}\Bigg\}$$
Anche in questo caso $NF$ aumenta linearmente in $N$, ma la sommatoria sotto limita la crescita in quanto porta con sé la capacità di upload.

![[SOR/RETI/img/img33.png|center|500]]

## BitTorrent
BitTorrent è un protocollo P2P per la distribuzione di file. Nel gergo di BitTorrent, l'insieme di peer che partecipano alla distribuzione di file è detto **torrent**. I peer in un torrent scaricano **chunk (parti)** del file di uguale dimensione, con una dimensione tipica di 256 kB. Quando un peer entra a far parte di un torrent, non ha chunk del file, ma col passare del tempo accumula sempre più chunk che, mentre scarica, invia ad altri. Una volta che il peer ha acquisito l'intero file, può lasciare il torrent o rimanere per continuare ad inviare chunk ad altri peer.

Ciascun torrent ha un nodo di infrastruttura detta **tracker**. Quando un peer entra a far parte di un torrent, si registra presso il tracker, informandolo periodicamente che è ancora all'interno del torrent. In questo modo il tracker tiene traccia dei peer che stanno partecipando al torrent.

![[SOR/RETI/img/img34.png|center|500]]

In figura vediamo che Alice entra a far parte di un torrent. Il tracker le invia un sottoinsieme di peer partecipanti, fornendo i loro indirizzi IP. Alice tenta di stabilire connessioni TCP con questi peer, che, se stabilite, diventano suoi *peer vicini*. Il numero di peer vicini varia nel tempo, in quanto i peer possono lasciare il torrent o stabilire una connessione TCP con Alice .

Ogni peer possiede un sottoinsieme dei chunk di un file, che differisce tra peer. Alice richiede periodicamente ai suoi vicini la lista dei chunk che possiedono e invia richieste per i chunk mancanti.

Le decisioni principali che Alice deve prendere sono:
1. **Quali chunk richiedere:** Alice adotta la strategia **rarest first**, richiedendo per primi i chunk più rari tra quelli mancanti, ovvero i chunk con il minor numero di copie ripetute tra i peer vicini e richiederli per primi, in modo da ridistribuirli più velocemente.
2. **A quali vicini inviare chunk:** BitTorrent usa un algoritmo di scambio **tit-for-tat**. Alice assegna priorità ai peer che le inviano dati alla velocità più alta, selezionando quattro **peer unchoked** a cui inviare chunk. La velocità viene ricalcolata ogni 10 secondi e ogni 30 secondi viene scelto un nuovo vicino **optimistically unchoked** per testare nuove connessioni. Se entrambi i peer trovano vantaggioso lo scambio, continueranno a inviarsi dati fino a trovare partner migliori.

# Streaming video
Lo streaming video costituisce circa l'80% del traffico Internet. Nei video registrati, il contenuto è una sequenza di immagini mostrate a 24 o 30 fotogrammi al secondo. Ogni immagine è rappresentata da un array di pixel codificati per luminanza e crominanza. I video possono essere compressi, bilanciando qualità video e bitrate: un bitrate più alto corrisponde a una qualità migliore.

## Streaming HTTP e DASH
Nello streaming HTTP, il video è memorizzato su un server come file con un URL. Quando un utente avvia la riproduzione, il client stabilisce una connessione TCP e invia una richiesta GET. Il server invia il video, che viene memorizzato in un buffer sul lato client. Quando il buffer supera una certa soglia, il video inizia la riproduzione.

Lo svantaggio dello streaming HTTP tradizionale è che tutti i client ricevono la stessa versione del video, indipendentemente dalla larghezza di banda disponibile. Per risolvere questo problema, è stato introdotto lo **streaming dinamico adattivo su HTTP (DASH)**.
In DASH, i video sono codificati in più versioni con diversi bitrate e qualità. Il client seleziona automaticamente i chunk appropriati in base alla larghezza di banda disponibile. I video sono memorizzati su server HTTP come chunk, ciascuno con un URL differente, e un **manifest file** elenca gli URL e i bitrate delle varie versioni.
Quando un client richiede un video, scarica prima il manifest file per conoscere le opzioni disponibili, poi misura la banda e seleziona il chunk successivo di conseguenza.

## Reti per la distribuzione di contenuti
L'approccio più diretto per la distribuzione di video a milioni di utenti è quello di costruire un enorme data center, memorizzarne i video all'interno e inviarli in streaming. Questo però porta parecchi problemi: 
- se il client è lontano dal ldata center, i pacchetti devono percorrere un lungo tragitto prima di arrivare al client. Inoltre se uno dei collegamenti ha un throughput basso, anche quello totale end-to-end ne risente, causando all'utente dei blocchi nel video. 
- un singolo data center rappresenta un singolo punto di rottura. Se il data center dovesse rompersi, i video non potrebbero essere più distribuiti.
Per superare queste problematiche, le aziende di video streming utilizzano le **CDN (reti di distribuzione di contenuti)**, chememorizzano più copie dello stesso video in più siti geograficamente distribuiti.
Le CDN adottano due politiche di dislocazione dei server:
- **enter deep**: installare server della CDN all'interno delle reti di accesso con l'obiettivo di essere vicino agli utenti finali in modo da migliorare il throughput e il ritardo percepito.
- **bring home**: costruire cluster in pochi punti chiave ed interconnetterli tramite una rete privata ad alta velocità. I cluster sono posti negli IXP invece che negli ISP di accesso

### Funzionamento di una CDN
Supponiamo che un fornitore di contenuti, NetCinema, utilizzi una CDN di terze parti, KingCDN, per distribuire i video. Supponiamo che il nostro utente richieda il video con l'URL http://video.netcinema.com/6Y7B23V.
Vengono copiuti 6 passi:
- l'utente visita la pagina web di NetCinema
- l'utente seleziona il link http://video.netcinema.com/6Y7B23V ed il suo host invia una query DNS a vide.netcinema.com
- il DNS server locale, invia la query a un DNS server autoritativo per NetCinema, che osserva la stringa video nel nome dell'host. Il DNS server autoritativo per NetCinema, per passare l'interrogazione DNS a KingCDN, invece di restituire un IP, restituisce al local DNS il nome di un host nel dominio di KingCDN, per esempio a1105.kingcdn.com
- il sistema DNS di KingCDN fornisce l'IP del nodo CDN più vicino all'utente. 
- Il DNS locale inoltra l'IP del nodo CDN che fornirà il contenuto all'host dell'utente
- Una volta ricevuto l'IP, il client stabilisce una connessione TCP col server a quell'indirizzo IP, inviandogli una richiesta GET HTTP per il video. Nel caso venga impiegato DASH, il server invierà per primo il manifest file, e il client selezionerà in modo dinamico i blocchi dalle differenti versioni
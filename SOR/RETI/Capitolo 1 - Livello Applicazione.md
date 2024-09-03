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
In un'applicazione client-server un singolo host server non è in grado di rispondere a tutte le richieste dei client, per questo motivo sono utilizzati i data center, che ospitano più host server.

![[SOR/RETI/img/img18.png|center|500]]
# Peer-to-peer
In questa architettura non è necessario l'utilizzo di un server sempre attivo e si sfrutta la *comunicazione arbitraria tra coppie di host*, chiamati **peer**. 
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
In internet, gli host sono identificati attraverso gli indirizzi IP, un numero a 32 bit ed il **numero di porta di destinazione** che identifica il socket a cui consegnare il messaggio.
## Servizi di trasporto per le applicazioni
L'applicazione lato mittente invia i messaggi tramite il socket, mentre dal lato del ricevente, il protocollo a livello di trasporto consegna i messaggi al socket del processo ricevente. 

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
**HTTP** è il protocollo a livello di applicazione del web e ne costituisce il cuore consentendo ai client di richiedere pagine ai server tramite lo scambio di messaggi HTTP. 

Quando un utente richiede una pagina web, il browser invia al server dei *messaggi di richiesta HTTP* per gli oggetti della pagina, il server riceve la richiesta e risponde con dei *messaggi di risposta HTTP*.

HTTP utilizza TCP come protocollo di trasporto. 
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
Come abbiamo visto HTTP è stateless. Tuttavia è utile che i server possano autenticare gli utenti, ad esempio per limitare l'accesso da parte di essi o per fornire contenuti in funzione della loro identità. Per questo scopo sono utilizzati i **cookie**, che consentono ai server di tenere traccia degli utenti.

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
Una *web cache* (o *proxy server*) memorizza copie di oggetti richiesti recentemente per ridurre i tempi di risposta.
Il browser può essere configurato in modo che tutte le richieste HTTP vengano dirette al proxy server: 
- se l'oggetto è nella cache, allora la cache fornisce l'oggetto al client
- altirimenti la cache richiede l'oggetto al server d'origine, lo memorizza nella cache e lo invia al client
Osserviamo che la cache opera sia da client (quando richiede al server) che da server (quando fornisce al client d'origine).

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
La posta elettronica è una delle applicazioni più diffuse in Internet. 

![[SOR/RETI/img/img26.png|center|500]]

In figura vediamo i componenti principali di un sistema postale in Internet: 
- **User Agent**: Applicazioni come Gmail o Outlook che permettono agli utenti di leggere, rispondere, inoltrare, salvare e comporre messaggi.
- **Mail Server**: Gestisce la casella di posta personale di ogni utente e conserva i messaggi inviati e ricevuti. Se un messaggio non può essere consegnato immediatamente, viene messo in una coda per un tentativo successivo.
- **Protocollo SMTP**: Protocollo a livello applicativo usato per l'invio di email.
## SMTP
SMTP (Simple Mail Transfer Protocol) utilizza TCP per trasferire in modo affidabile i messaggi dal client al server sulla porta 25. Anche se ancora ampiamente utilizzato, SMTP è piuttosto datato, utilizzando testo ASCII a 7 bit per il corpo dei messaggi, che richiede la codifica di file multimediali.

**Operazione di SMTP**:

1. Il mittente (Alice) compone un'email e inserisce l'indirizzo del destinatario (Bob).
2. Il messaggio viene inviato al server mail di Alice e messo in coda.
3. Il client SMTP sul server di Alice apre una connessione TCP con il server SMTP di Bob.
4. Dopo l'handshaking, il messaggio viene inviato al server SMTP di Bob.
5. Il server di Bob riceve il messaggio e lo inserisce nella mailbox di Bob, che potrà leggerlo quando desidera.

![[SOR/RETI/img/img27.png|center|500]]

### Confronto con HTTP
Entrambi i protocolli trasferiscono file tra host, ma HTTP è un protocollo **PULL** (gli utenti richiedono oggetti), mentre SMTP è un protocollo **PUSH** (il server invia file al server di destinazione). Inoltre, SMTP richiede che tutto il contenuto sia codificato in ASCII a 7 bit, mentre HTTP non ha questa restrizione. HTTP invia oggetti separati in messaggi distinti, mentre SMTP invia più oggetti in un unico messaggio.

### Formato dei messaggi di posta elettronica
Un'email è composta da linee di intestazione e dal corpo del messaggio, separati da una riga vuota. 
Come per HTTP, ogni linea è formata da una parola chiave seguita dai due punti, con alcune parole chiave obbligatorie (From: e To:) e altre facoltative (Subject: ). 
Dopo l'intestazione del messaggio, segue una linea vuota e poi il corpo del messaggio, che si chiude con un punto.

### Protocolli di accesso alla posta
SMTP è usato principalmente per trasferire i messaggi tra server, ma non per trasferirli dalla mailbox dell'utente al suo programma di posta elettronica. Per questo, esistono altri protocolli come:

- **POP3**
- **IMAP**: Permette di recuperare, cancellare e archiviare i messaggi sul server.
- **HTTP**: Utilizzato per l'accesso web alle email, spesso integrato con SMTP e IMAP (o POP) per l'invio e il recupero delle email.
# DNS
Gli host Internet sono identificati dall'indirizzo IP, ma più comunemente da nomi in quanto più facili da ricordare.
Il **DNS** traduce i nomi degli host in indirizzi IP per il routing su Internet.

DNS è sia un database distribuito implementato in una gerarchia di DNS server, sia un protocollo a livello di applicazione che permette agli host di interrogare il database per la traduzione nome-indirizzo. 

DNS utilizza UDP e la porta 53, ma se la risposta del server DNS supera i 512 byte, si riceve un particolare messaggio che richiede di utilizzare TCP, che utilizza comunque la porta 53.

Oltre alla traduzione degli hostname in indirizzi IP, DNS mette a disposizione altri servizi tra cui:
- **Alias degli hostname**: l'hostname www.informatica.uniroma2.it ha l'alias www.cs.uniroma2.it. In questo caso www.informatica.uniroma2.it è detto *hostname canonico*
- **Mail server aliasing**: facilita la gestione degli indirizzi di posta con nomi semplici, risolvendo alias in nomi canonici.  Ad esempio, l'indirizzo di posta user@yahoo.com potrebbe avere come nome canonico relay1.west-coast.yahoo.com.
- **Distribuzione del carico**: Distribuisce il traffico tra server web mirror, restituendo IP differenti ad ogni richiesta.

## Funzionamento del DNS
Quando un’applicazione richiede la traduzione di un hostname, il DNS client invia una query UDP sulla porta 53 al DNS server, contenente l'hostname da tradurre e riceve in risposta l’indirizzo IP corrispondente. Il DNS è un sistema gerarchico distribuito in tre livelli:

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
- **root server**: forniscono gli indirizzi IP dei TLD server. 
- **TLD server**: si occupano dei domini .com, .org, .net e dei domini locali di alto livello come .uk, .fr, .jp ecc.
- **DNS server autoritativi**: sono DNS server propri di ogni organizzazione, che forniscono i mapping ufficiali da hostname a IP per gli host dell'organizzazione.

Esiste anche il DNS server locale, che non appartiene alla gerarchia di server, ma è centrale nell'architettura DNS. Ciascun ISP (università, residenziale ecc.) ha un DNS server locale. Quando un host si connette ad un ISP, questo gli fornisce un indirizzo IP tratto da uno o più dei suoi DNS server locali tramite DHCP. Quando un host effettua una richiesta DNS, questa viene inviata al DNS server locale che opera da proxy e inoltre la query al DNS server.
Supponiamo che l'host cse.nyu.edu voglia l'indirizzo IP di gaia.cs.umass.edu e supponiamo che il DNS server locale per cse.nyu.edu sia dns.nyu.edu, mentre un server autoritativo per gaia.cs.umass.edu sia dns.umass.edu.

![[SOR/RETI/img/img29.png|center|500]]

Come possiamo vedere in figura, l'host richiedente invia un messaggio di richiesta al DNS server locale, che contiene il nome da tradurre. Il server locale inoltra il messaggio ad un root server, che prende nota del suffisso .edu e restituisce al server locale un elenco di indirizzi IP per i TLD server responsabili di edu. Il server locale invia quindi il messaggio di richiesta ad uno dei TLD server, che prende nota del suffisso umass.edu e risponde con l'indirizzo IP del server autoritativo dell'Università del Massachusetts, ovvero dns.umass.edu. Infine il DNS server locale inoltra il messaggio di richiesta direttamente al DNS server autoritativo che risponde con l'indirizzo IP di gaia.cs.umass.edu.
Osserviamo che sono stati inviati otto messaggi DNS per ottenere la mappatura, vedremo come il DNS caching riduce questo traffico

## DNS caching
Il caching DNS migliora le prestazioni, memorizzando temporaneamente i risultati delle query. Tuttavia, le voci nella cache possono diventare obsolete se l’indirizzo IP cambia prima della scadenza del TTL.

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
Per registrare un dominio, si forniscono al registrar i nomi e gli indirizzi IP dei server autoritativi, che vengono inseriti nel TLD server con i relativi RR.
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

### Streaming Video

Lo streaming video rappresenta circa l'80% del traffico Internet. Nei video registrati, ogni fotogramma è una sequenza di immagini codificate in array di pixel, con luminanza e crominanza. I video possono essere compressi, bilanciando qualità e bitrate: un bitrate più alto garantisce una qualità migliore.

#### Streaming HTTP e DASH
- **Streaming HTTP tradizionale**: Il video è memorizzato su un server e trasmesso ai client tramite una connessione TCP. I video sono bufferizzati sul lato client prima della riproduzione.
- **DASH (Dynamic Adaptive Streaming over HTTP)**: I video sono disponibili in diverse versioni con bitrate e qualità variabili. Il client seleziona dinamicamente i chunk più adatti in base alla larghezza di banda disponibile, migliorando l'esperienza di visualizzazione.

#### Reti di Distribuzione di Contenuti (CDN)
Per distribuire video a milioni di utenti, si utilizzano le **CDN**, che memorizzano copie dei video in server geograficamente distribuiti. Esistono due strategie di dislocazione:
- **Enter Deep**: Server CDN collocati vicino agli utenti finali nelle reti di accesso per ridurre ritardi e migliorare il throughput.
- **Bring Home**: Cluster di server in pochi punti strategici, interconnessi tramite una rete ad alta velocità, solitamente posizionati negli IXP.

#### Funzionamento di una CDN
Quando un utente richiede un video da un fornitore che utilizza una CDN, come NetCinema con KingCDN, il processo include:
1. L'utente visita il sito web e seleziona un video.
2. Il client invia una query DNS per l'URL del video.
3. Il DNS server locale invia la query al DNS autoritativo di NetCinema, che lo reindirizza al DNS di KingCDN.
4. KingCDN fornisce l'IP del nodo CDN più vicino all'utente.
5. Il DNS locale invia l'IP al client.
6. Il client stabilisce una connessione TCP al nodo CDN e richiede il video, ricevendo eventualmente il manifest file per lo streaming dinamico con DASH.
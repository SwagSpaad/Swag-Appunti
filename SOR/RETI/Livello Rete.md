Il livello di rete è il 3 layer della pila OSI, ha il compito di trasmettere arbitrariamente pacchetti tra due host, che generalmente non sono direttamente connessi, quindi in sostanza si occupa di *inoltro o forwarding* e *instradamento o routing* verso la giusta destinazione attraverso il percorso di rete più appropriato.

# Inoltro e instradamento: piano dei dati e piano di controllo
Per far si che i pacchetti vengano trasferiti da un host a un altro si utilizzano 2 funzioni importanti:
- *Inoltro:* Quando un router riceve un pacchetto, lo deve trasferire sull' appropiato collegamento di uscita.
- *Instradamento:* Il livello di rete deve determinare il percorso che i pacchetti devono seguire tramite **algoritmi di instradamento** (algoritmi di routing)
Quindi con *inoltro* faremo riferimento all' azione locale con cui il router trasferisce i pacchetti da un' interfaccia di ingresso a quella di uscita, con *instradamento* invece, indichiamo i percorsi che un pacchetto effettua dalla sorgente alla destinazione
![[img_rete1.jpg| center | 800]]
Per inoltrare i pacchetti, i router estraggono da uno o più campi dell' intestazione i loro valori che utilizzano come indice nella **tabella di inoltro**, un elemento cruciale di qualsiasi router. Il risultato indica a quale interfaccia di uscita il pacchetto debba essere diretto.
>Ex: Il pacchetto 0110 giunge ad un router e viene inoltrato nell' uscita 2 tramite la tabella di inoltro.

## Piano di controllo: approccio tradizionale
Come vengono configurate le tabelle di inoltro?
Questo è un passaggio cruciale che mostra l' interazione tra inoltro e instradamento.
Come mostrato in figura sopra, l' algoritmo di instradamento determina i valori inseriti nella tabella
di inoltro. In questo esempio l' algoritmo di routing è implementato in ogni router, che quindi svolge sia la funzione di inoltro che quella di instradamento internamente. Le funzioni di instradamento nei router comunicano tra di loro per determinare quali valori inserire nella tabella di inoltro, ma come fanno? Attraverso dei messaggi di un protocollo di instradamento.

## Piano di controllo: approccio SDN
L' approccio menzionato sopra è quello più tradizionalmente utilizzzato dalle aziende produttrici di router, almeno fino a poco tempo fa.
![[img_reti2.jpg | center | 800]]
In questa immagine viene mostrato un metodo alternativo che utilizza un controller remoto per calcolare e distribuire le tabelle di inoltro a tutti i router.
Il controller remoto potrebbe essere implementato in un data center remoto e gestito da isp o da terze parti.
Come comunicherebbero però i router e i controller remoti? Scambiandosi messaggi contententi le tabelle di inoltro e altre informazioni di instradamento. Questo metodo viene chiamato **SDN (Software-Defined Netwoking)** nel quale la rete è gestita da un software perchè il controller che calcola le tabelle di inoltro e interagisce con i router è implementato in software.

## Modelli di servizio del livello di rete
- *Consegna garantita:* Servizio che assicura la ricezione del pacchetto, prima o poi, alla destinazione.
- *Consegna garantita con ritardo limitato:* Servizio che assicura la ricezione del pacchetto nei limiti di ritardo specificati (Ex: 100ms)
- *Consegna ordinata:* Servizio che garantisce la ricezione dei pacchetti nell' ordine di invio
- *Banda minima garantita:* Servizio che emula a livello trasmissivo un collegamento con bit rate specificato tra host di invio e di destinazione. Finchè l' host di invio trasmette pacchetti a una velocità inferiore al bit rate specificato, non si verifica perdita di pacchetti.
- *Servizio di sicurezza:* Servizio che cifra tutti i datarammi dall' host sorgente all' host di destinazione, che poi dovrà decifrarli.
Questa è una lista parziale dato che esistono diverse variazioni di essi.
Il livello di rete di internet fornisce un solo sevizio, *Best-Effort*, non si hanno garanzie che i pacchetti vengano ricevuti, nè che vengano icevuti nell' ordine di invio, non c'è garanzia ne sul ritardo end-to-end, ne sulla larghezza di banda minima.

## Che cosa si trova all' interno di un router?
![[img_reti3.jpg | center | 800]]
- *Porte di ingresso:* Svolgono le funzioni a livello fisico di terminazione di un collegamento in ingresso al router, svolgono funzioni a livello di collegamento necessarie per interoperare con le analoghe funzioni all'altro capo del collegamento di ingresso. Svolgono inoltre la cruciale funzione di ricerc, in modo che il pacchetto inoltrato nella struttura di commutazione del router esca sulla porta di uscita corretta. In un router, le porte di ingresso e uscita, e la struttura di commutazione sono implementate quasi sempre a livello hardware, ed operano sulla scala temporale dei nanosecondi mentre le funzioni di istradamento del router e altre funzioni di gestione operano sulla scala dei millisecondo/secondi. Le funzioni del piano di controllo sono solitamente implementate via software ed eseguite sul processore di instradamento.
	- _Inoltro basato sulla destinazione:_ Inoltro basato esclusivamente sull'indirizzo IP di destinazione.
	- _Inoltro generalizzato:_ Inoltro basato su più campi di intestazione.
- _Struttura di commutazione:_ Connette fisicamente le porte di ingresso con quelle di uscita ed è contenuta interamente nel router
- _Porte di uscita:_ Memorizzano i pacchetti che provengono dalla struttura di commutazione e li trasmettono sul collegamento in uscita, operando le funzionalità necessarie del livello di collegamento e fisico.
- _Processore di instradamento:_ Esegue le funzioni del piano di controllo. Nei router tradizionali esegue i protocolli di instradamento, gestisce le tablle di inoltro e le informazioni sui collegamenti attivi. Nei router SDN, il processore di instradamento è responsabile della comunicazione con il controller remoto, in modo da ricevere le occorenze della tabella di inoltro e installarle alle porte di ingresso.
## Inoltro basato sull' indirizzo di destinazione
Abbiamo visto che le porte di ingresso si occupano di determinare la porta di uscita a cui dirigere un pacchetto attraverso la struttura di commutazione. Ma come avviene la ricerca di questa porta? In ciascuna porta di ingresso, viene memorizzata una copia della tabella di inoltro, che essendo locale, la porta di ingresso non deve fare affidamento sulla CPU per la ricerca della porta di uscita.
Prendiamo il caso più semplice, in cui l'inoltro è basato sull'indirizzo di destinazione. Prendiamo per esempio questa tabella di inoltro:
![[img_reti4.png | center | 1000]]
![[img_reti5.png | center | 1000]]
Nella prima tabella, a sinistra, il router decide la porta di uscita controllando l'intero indirizzo IP mentre nella seconda tabella, un pò più semplificata, il router deve soltanto andare a confrontare un **prefisso** dell'indirizzo con una riga della tabella.
Risulta però un problema. Cosa succede quando un indrizzo IP corrisponde a più di un prefisso? Per esempio:
```
A: 11001000 00010111 00010110 10100001
B: 11001000 00010111 00011000 10101010
```

Quando si verificano corrispondenze multiple, il router adotta la regola di **corrispondenza a prefisso più lungo** in altre parole, viene determinata la corrispondenza più lunga all’interno della tabella e i pacchetti vengono inoltrati all’interfaccia di collegamento associata.
Quindi nel nostro caso l'indirizzo A verrà inoltrato alla porta di uscità 0 mentre l'indirizzo B verrà inoltrato alla porta di uscita 1 per la regola di **corrispondeza a prefisso più lungo**.
La corrispondenza a prefisso più lungo spesso è eseguita con le ternary content addressable memories (TCAM). Con una TCAM, un indirizzo IP a 32 bit è passato alla memoria che restituisce il contenuto della tupla nella tabella di inoltro corrispondente a quell’indirizzo in un tempo essenzialmente costante.

## Struttura di commutazione
Rappresenta il cuore del router, attraverso il quale i pacchetti vengono commutati dalla porta di ingresso a quella di uscita. La commutazione avviene in vari modi:
- _Commutazione in memoria:_ I primi e più semplici router erano in genere calcolatori tradizionali, e la commutazione tra porte di ingresso e di uscita veniva effettuata sotto il controllo diretto della CPU. Le porte di ingresso e di uscita funzionavano come tradizionali dispositivi di I/O. Quando sopraggiungeva un pacchetto, la porta di ingresso segnalava l’arrivo tramite interrupt e quindi lo copiava nella memoria del processore di instradamento che procedeva a estrarre dall’intestazione l’indirizzo di destinazione. Quindi, individuava tramite la tabella di inoltro l’appropriata porta di uscita nel cui buffer copiava il pacchetto.
![[img_reti6.png | center | 1000]]
- _Commutazione tramite Bus:_ In questo approccio le porte di ingresso trasferiscono un pacchetto direttamente alle porte di uscita tramite un bus condiviso e senza intervento da parte del processore di instradamento.**Bus Contention**: Se più pacchetti arrivano contemporaneamente al router, ognuno su una porta di input diversa, tutti tranne uno dovranno aspettare, dato che sul bus si può trasferire soltanto un pacchetto alla volta. Poiché ciascun pacchetto deve attraversare il bus, la larghezza di banda della commutazione è limitata da quella del bus.
![[img_reti7.png | center | 1000]]
- _Commutazione attraverso rete di interconnessione:_ Un modo per superare la limitazione di banda di un singolo bus condiviso è l’utilizzo di una rete di interconnessione più sofisticata, quale quella usata in passato nelle architettture multiprocessore. Una **matrice di commutazione (crossbar switch)** è una rete di interconnessione che consiste di 2 n bus che collegano n porte di ingresso a n porte di uscita. Quando un pacchetto giunge a una porta di ingresso A e deve essere inoltrato alla porta Y, il controller chiude l’incrocio di A e Y e la porta A invia il pacchetto sul suo bus e solo il bus Y lo riceverà. Si noti che un pacchetto dalla porta B può essere inoltrato a X nello stesso tempo, perché i pacchetti A-Y e B-X usano bus di input e output diversi. Una matrice di commutazione è **non-blocking**: un pacchetto in via di inoltro verso una porta di uscita non viene bloccato a meno che esista un altro pacchetto in via di inoltro sulla stessa porta di uscita. Questo tipo di commutazione sfrutta il parralelismo perche frammenta il datagramma in celle di lunghezza fissa all'ingresso, commuta le celle attraverso la rete di commutazione, per poi riassemblare il datagramma in uscita.
![[img_reti8.png | center | 800]]
## Accodamento

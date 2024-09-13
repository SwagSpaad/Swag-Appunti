Il livello di rete è il 3 layer della pila OSI, ha il compito di trasmettere arbitrariamente pacchetti tra due host, che generalmente non sono direttamente connessi, quindi in sostanza si occupa di *inoltro o forwarding* e *instradamento o routing* verso la giusta destinazione attraverso il percorso di rete più appropriato.

# Inoltro e instradamento: piano dei dati e piano di controllo
Per far si che i pacchetti vengano trasferiti da un host a un altro si utilizzano 2 funzioni importanti:
- *Inoltro:* Quando un router riceve un pacchetto, lo deve trasferire sull'appropiato collegamento di uscita.
- *Instradamento:* Il livello di rete deve determinare il percorso che i pacchetti devono seguire tramite **algoritmi di instradamento** (algoritmi di routing)
Quindi con *inoltro* faremo riferimento all'azione locale con cui il router trasferisce i pacchetti da un'interfaccia di ingresso a quella di uscita, con *instradamento* invece, indichiamo i percorsi che un pacchetto effettua dalla sorgente alla destinazione

![[img_rete1.jpg| center | 600]]

Per inoltrare i pacchetti, i router estraggono da uno o più campi dell'intestazione i loro valori che utilizzano come indice nella **tabella di inoltro**, un elemento cruciale di qualsiasi router. Il risultato indica a quale interfaccia di uscita il pacchetto debba essere diretto.
>Ex: Il pacchetto 0110 giunge ad un router e viene inoltrato nell' uscita 2 tramite la tabella di inoltro.

## Piano di controllo: approccio tradizionale
Le tabelle di inoltro sono configurate dall'algoritmo di instradamento che decide i valori da inserire nella tabella. Ogni router implementa sia l'inoltro che l'instradamento internamente, comunicando con altri router tramite messaggi di protocollo di instradamento.
## Piano di controllo: approccio SDN

![[img_reti2.jpg|center|500]]
Nell'approccio **SDN (Software-Defined Networking)**, un **controller remoto** calcola e distribuisce le tabelle di inoltro ai router. La comunicazione avviene attraverso messaggi scambiati tra router e controller remoto, permettendo la gestione della rete via software.

## Modelli di servizio del livello di rete 
- **Consegna garantita:** assicura la ricezione del pacchetto
- **Consegna garantita con ritardo limitato:** Il pacchetto viene ricevuto entro un ritardo specificato (es. 100ms). 
- **Consegna ordinata:** I pacchetti sono ricevuti nell'ordine di invio. 
- **Banda minima garantita:** Trasmissione senza perdita di pacchetti entro un certo bit rate. 
- **Servizio di sicurezza:** Cripta i datagrammi dall'host sorgente all'host di destinazione. 

Il livello di rete di internet fornisce solo il servizio *Best-Effort*, senza garanzie di consegna, ordine, ritardo o banda minima.

## Che cosa si trova all'interno di un router? 
![[img_reti3.jpg | center | 500]]
- **Porte di ingresso:** Terminano i collegamenti in ingresso e ricercano la porta di uscita corretta. 
- **Struttura di commutazione:** Collega fisicamente le porte di ingresso a quelle di uscita. 
- **Porte di uscita:** Trasmettono i pacchetti dalla struttura di commutazione. 
- **Processore di instradamento:** Nei router tradizionali esegue funzioni di controllo e gestisce le tabelle di inoltro. Nei router SDN è responsabile della comunicazione col controller remoto

## Inoltro basato sull'indirizzo di destinazione
Le porte di ingresso determinano la porta di uscita a cui dirigere un pacchetto. Vediamo in che modo. 
Nel caso più semplice, l'inoltro è basato sul controllo dell'intero indirizzo IP di destinazione. 

![[img_reti4.png | center | 500]]

Nella tabella sotto, invece, il router confronta solamente un prefisso dell'indirizzo per l'inoltro. 

![[img_reti5.png | center | 500]]

Sorgono dei problemi quando ad un indirizzo IP corrispondono più prefissi, per esempio:
```
IP A: 11001000 00010111 00010110 10100001
IP B: 11001000 00010111 00011000 10101010
```

In questo caso il router adotta la regola di **corrispondenza a prefisso più lungo**, quindi l'interfaccia di uscita viene determinata in base al prefisso più lungo.
Nel nostro caso l'indirizzo A verrà inoltrato alla porta di uscità 0 mentre l'indirizzo B verrà inoltrato alla porta di uscita 1 invece che alla 2. Spesso, questo processo è eseguito con le **TCAM**, che restituiscono i risultati in tempo costante.

## Struttura di commutazione
La struttura di commutazione collega le porte di ingresso a quelle di uscita in diversi modi:
- _Commutazione in memoria:_ I primi e più semplici router erano in genere calcolatori tradizionali, e la commutazione tra porte di ingresso e di uscita veniva effettuata sotto il controllo diretto della CPU. All'arrivo di un pacchetto, la porta di ingresso segnalava l’arrivo tramite interrupt, questo venica copiato nella memoria del processore di instradamento che estraeva dall’intestazione l’indirizzo di destinazione e tramite la tabella di inoltro si individuava la porta di uscita in cui venica copiato il pacchetto. 

![[img_reti6.png | center | 600]]

- _Commutazione tramite Bus:_ In questo approccio le porte di ingresso trasferiscono un pacchetto direttamente alle porte di uscita tramite un bus condiviso. 
 
![[img_reti7.png | center | 600]]

- _Commutazione attraverso rete di interconnessione:_ Usa una **matrice di commutazione (crossbar switch)** per inoltrare i pacchetti tra porte di ingresso e uscita in modo parallelo. Una matrice di commutazione è **non-blocking**: un pacchetto in via di inoltro verso una porta di uscita non viene bloccato a meno che esista un altro pacchetto in via di inoltro sulla stessa porta di uscita.

![[img_reti8.png | center | 300]]
## Accodamento
Se la struttura di commutazione è più lenta delle porte di ingresso e uscita, può verificarsi accodamento, causando **ritardi** e **perdite di pacchetti** dovute all'overflow dei buffer.

### Accodamento in Ingresso
L'accodamento si verifica quando la struttura di commutazione è lenta nel trasferire i pacchetti in arrivo. Se due pacchetti in testa a due code di ingresso sono destinati alla stessa coda di uscita, uno sarà bloccato e dovrà attendere. Questo fenomeno è noto come **blocco in testa alla coda (Head-Of-The-Line blocking, HOL)**.

### Accodamento in Uscita
- **Buffering:** Richiesto quando i datagrammi arrivano dalla struttura di commutazione più velocemente del tasso di trasmissione del collegamento. Se il buffer è pieno, bisogna decidere quale datagramma scartare. 
- **Schedulazione:** Se ci sono più pacchetti accodati, lo schedulatore deve determinare l'ordine di trasmissione.

### Memoria Buffer necessaria
- **Vecchia formula:** $B=RTT \cdot C$, dove la grandezza del buffer $B$ è pari a RTT moltiplicato per la capacità del link $C$.
- **Attuale formula:** $B = \frac {RTT \cdot C} {\sqrt{N}}$, con $N$ come numero dei flussi TCP.

Buffer troppo grandi possono causare ritardi più lunghi. In caso di buffer pieno, si usa la politica **drop-tail**, scartando il pacchetto in ingresso o uno già in coda.

## Schedulazione di pacchetti
L'obiettivo è determinare quale pacchetto inviare sulla porta di uscita.

### FIFO
I pacchetti sono trasmessi nello stesso ordine di arrivo. Se non c'è spazio nel buffer, si decide se eliminare il pacchetto in arrivo o rimuovere pacchetti dalla coda.

### Priority Queue
I pacchetti sono classificati in base a classi di priorità, con ogni classe che ha una propria coda. La trasmissione segue l'ordine delle priorità, con FIFO applicato all'interno di ogni classe.

### Round Robin
I pacchetti sono suddivisi in classi e trasmessi in alternanza, senza priorità rigida. Si trasmette un pacchetto da ciascuna classe in modo ciclico.

### Weighted Fair Queuing (WFQ)
Generalizza il Round Robin, assegnando a ciascuna classe $i$ un peso $\omega_{i}$ e la classe riceve una quantità di servizio proporzionale al suo peso: $\frac {\omega_{i}}{\Sigma_{j}\omega_{j}}$.

## Protocollo Internet, IPV4, IPV6
### Formato dei datagrammi IP

![[SOR/RETI/img/img92.png |center|600]]
- *Numero versione:* Quattro bit che specificano la versione del protocollo IP (IPv4 o IPv6).
- *Lunghezza dell'intestazione:* Quattro bit che indicano dove iniziano i dati nel datagramma.
- *Tipo di servizio:* Bit relativi al tipo di servizio. Diversi servizi da 0 a 5, mentre il servizio ECN i bit 6 e 7.
- *Lunghezza del datagramma:* 16 bit che rappresentano la lunghezza totale del datagramma IP.
- *Identificatore, flag, offset di frammentazione:* Questi 3 campi servono per la frammentazione.
- *Tempo di vita:* Il campo TTL è stato incluso per assicurare che i datagrammi non restino in circolazione per sempre nella rete. Questo campo viene decrementato ogni volta che passa da un router.
- *Protocollo:* Indica il protocollo a livello di trasporto a cui deve inviare i dati. 6 indica UDP mentre 17 indica TCP.
- *Checksum dell'intestazione:* Consente ai router di rilevare gli errori sui bit nei datagrammi ricevuti.
- *Indirizzi IP sorgente e destinazione:* Quando un host crea un datagramma, inserisce il proprio indirizzo IP nel campo indirizzo IP sorgente e quello della destinazione nel campo indirizzo IP destinazione. Entrambi gli indirizzi a 16 bit (4 byte).
- *Opzioni:* Altre opzioni da inserire nel datagramma IP.
- *Dati:* Nella maggior parte dei casi, il campo dati contiene il segmento a livello di trasporto (TCP o UDP) da consegnare alla destinazione. Tuttavia, può trasportare anche altri tipi di dati, quali i messaggi ICMP.

### Indirizzamento IPV4
Ad ogni interfaccia di un dispositivo è associato un indirizzo IP. Gli indirizzi IP sono lunghi 32 bit ($2^{32}$ indirizzi diversi) e sono solitamente scritti in **notazione decimale puntata**, ad esempio `193.32.216.9`.

![[SOR/RETI/img/img93.png | center | 500]]

Gli indirizzi non possono essere scelti in modo casuale. Una parte dell'indirizzo di un'interfaccia è determinata dalla sottorete a cui è collegata.
Una **sottorete** è un gruppo di dispositivi che possono comunicare _senza passare attraverso un router intermedio_. 
L'IP assegna a queste sottoreti gli indirizzi 223.1.1.0/24, 223.1.2.0/24, 223.1.3.0/24, dove la notazione /24, detta **maschera di sottorete** che indica che i 24 bit più a sinistra dell'indirizzo sono comuni in quella sottorete.

Gli indirizzi IP di una sottorete hanno una struttura:
- *Parte della sottorete:* I dispositivi della stessa sottorete hanno in comune i bit di ordine superiore, definiti dalla subnet mask.
- *Parte dell'host:* i rimanenti bit di ordine inferiore.

La strategia di assegnazione degli indirizzi Internet è detta **Classless Interdomain Routing CIDR** che generalizza la nozione di indirizzamento di sottorete.
L'indirizzo IP viene diviso in due parti e mantiene la forma decimale puntata $a.b.c.d/x$, con $x$ che indica il numero di bit della sottorete. 
L'indirizzo 255.255.255.255 è l'indirizzo IP di broadcast della sottorete. 

### Indirizzo di un host: DHCP
Quando si ottiene un blocco di indirizzi IP, le interfacce dei router sono configurate manualmente, mentre gli indirizzi degli host possono essere sia impostati manualmente sia dinamicamente.

Il **Dynamic Host Configuration Protocol (DHCP)** permette a un host di ottenere automaticamente un indirizzo IP e altre informazioni (maschera di sottorete, indirizzo del router, indirizzo DNS server). DHCP può assegnare un indirizzo temporaneo (cambia ongi volta che si collega alla rete) o persistente.
Il DHCP viene spesso chiamato protocollo **plug-and-play** o **zero-conf** per la sua capacità di automatizzare la connessione degli host alla rete.

Per i nuovi host, il protocollo DHCP si articola in quattro punti:
![[SOR/RETI/img/img94.png | center | 500]]

1. **Individuazione del server DHCP**.L'host invia un messaggio **DHCP discover** a 255.255.255.255.

2. **Offerta del server DHCP**. Un server risponde con **DHCP offer**, contenente l'indirizzo IP proposto e altri parametri (maschera di sottorete, durata dell'indirizzo). 

4. **Richiesta DHCP**. L'host seleziona un'offerta e risponde con **DHCP request**.

5. **Conferma DHCP**. Il server conferma con un **DHCP ACK**, confermando i parametri.

## NAT (Network Address Translation)
Dato il numero limitato di indirizzi IPv4, vengono utilizzati **indirizzi IP pubblici** per l'interfaccia esterna e **indirizzi IP privati** per la rete interna. Questi ultimi hanno significato solo all'interno della rete locale e, grazie al **NAT**, tutti i dispositivi interni possono condividere un unico indirizzo IPv4 pubblico per le comunicazioni esterne. Il **NAT** consente al router di apparire come un unico dispositivo con un indirizzo pubblico. Utilizzando una **tabella di traduzione NAT** (NAT translation table), il router associa gli indirizzi privati e le porte interne a un unico indirizzo pubblico, garantendo l'indirizzamento corretto dei datagrammi.

### Vantaggi del NAT
- È necessario _un solo_ indirizzo IP dal provider ISP per tutti i dispositivi.
- Può cambiare gli indirizzi degli host nella rete locale senza notificare il mondo esterno.
- Può cambiare ISP senza modificare gli indirizzi dei dispositivi nella rete locale.
- Sicurezza, i dispositivi interni non sono direttamente visibili dall'esterno.

### Implementazione e funzione del NAT
1. **Datagrammi in uscita:** Il router NAT sostituisce l'indirizzo IP sorgente privato e la porta del datagramma con l'indirizzo IP pubblico e una nuova porta. La tabella NAT associa ogni coppia (IP privato, porta) con (IP pubblico, nuova porta). 
2. **Datagrammi in entrata:** Quando il router NAT riceve un datagramma destinato all'indirizzo pubblico, lo traduce all'indirizzo privato e alla porta corretta utilizzando la tabella NAT.

![[SOR/RETI/img/img95.png | center | 600]]

1. L'host 10.0.0.1 invia un datagramma all'indirizzo 128.119.40.186, porta 80. 
2. Il router NAT modifica l'indirizzo sorgente a 138.76.29.7, porta 5001, e aggiorna la tabella. 
3. La risposta arriva all'indirizzo 138.76.29.7, porta 5001. 
4. Il router NAT sostituisce l'indirizzo di destinazione con 10.0.0.1, porta 3345.

## IPV6
IPv6 è stato creato principalmente per risolvere la scarsità di indirizzi IPv4, offrendo: 
- **Indirizzi estesi:** Passaggio da 32 bit a 128 bit. 
- **Intestazione ottimizzata:** Intestazione fissa di 40 byte. 
- **Etichettatura dei flussi:** Permette di identificare e gestire flussi specifici di traffico.

### Datagrammi IPV6

![[SOR/RETI/img/img97.png | center | 500]]
- **Versione:** 4 bit che identificano la versione IP. 
- **Classe di traffico:** Assegna priorità a determinati datagrammi. 
- **Etichetta di flusso:** Identifica i datagrammi appartenenti allo stesso flusso.
- **Lunghezza del payload:** 16 bit che specificano la lunghezza del payload.
- **Intestazione successiva:** Identifica il protocollo (TCP o UDP). 
- **Limite di hop:** Campo equivalente a TTL in IPv4. 
- **Indirizzi sorgente e destinazione:** Indirizzi IPv6. 
- **Dati:** Payload trasportato.

### Da IPV4 a IPV6
Per consentire la comunicazione tra nodi IPv6 attraverso una rete IPv4, si utilizza il **tunneling**. Un nodo IPv6 incapsula un datagramma IPv6 all'interno del campo dati di un datagramma IPv4, che viene instradato dai router IPv4 come un normale datagramma IPv4. Al termine del tunnel, il datagramma IPv6 viene estratto e inviato al destinatario.

![[SOR/RETI/img/img98.png | center |500]]

## Inoltro generalizzato e SDN
L'inoltro generalizzato consente l'uso di **tabelle match-action** per prendere decisioni di inoltro in base a più criteri, come indirizzi IP sorgente e destinazione o altre informazioni del pacchetto. Ogni riga di una tabella di flussi contiene: 
- **Campi di intestazione:** Valori dei campi del pacchetto confrontati con le regole. 
- **Contatori:** Aggiornati quando un pacchetto corrisponde a una regola. 
- **Azioni:** Operazioni da eseguire, come inoltrare, scartare o modificare il pacchetto.
### Match
Nella figura sottostante vengono mostrati gli 11 campi dell'intestazione del pacchetto e l'ID della porta di ingresso che possono esere confrontati in un regola match-action. Possiamo osservare che il pacchetto che arriva ad un packet switch è formato da un _frame_ a livello di collegamento che a suo interno contiene un _datagramma_ IP che a sua volta contiene un _segmento_ a livello di trasporto.
Quindi gli unici campi di cui non sappiamo nulla sono:
- **MAC Address**: rappresenta l'indirizzo a livello di collegamento della sorgente e destinatario associati alle interfacce di invio e di ricezione del frame.
- **Ethernet Type**: corrisponde al protocollo del livello sovrastante, per esempio IP al quale il carico del frame viene inviato tramite demultiplexing.
- **VLAN**: questi campi corrispondono invece alle cosiddette reti virtuali locali.
![[SOR/RETI/img/img99.png | center | 600]]

### Action
Di seguito sono mostrate alcune delle più importanti azioni possibili:
- **Inoltro**: Un pacchetto in entrata può essere inoltrato a una particolare porta in uscita, inviato in broadcast a tutte le porte tranne a quella da cui è entrato o inviato in multicast ad un insieme di porte.
- **Scarto**: Un'occorrenza della tabella dei flussi senza azioni indica che il pacchetto dovrebbe essere scartato.
- **Modifica dei Campi**: Tutti i campi trannte "Protocollo IP" possono essere riscritti.
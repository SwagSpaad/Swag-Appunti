# Cos'é?
Livello di rete 3 layer pila OSI

# Che fa?
Trasmette pacchetti tra 2 host arbitrariamente, generalmente non connessi tra loro.

# Funzioni Importanti
**Inoltro o forwarding:** un router riceve un pacchetto e lo deve reindirizzare verso l'uscita piú apporpriata

**Instradamento o routing:** si occupa mdi determinare il persorso migliore che un pacchetto deve seguire tramite algoritmi di routing

## Metodo 1(Tradizionale)
Le tabelle di inoltro contenute nei router sono configurate da algoritmi di instradamento. Ogni router implementa sia l'inoltro che l'instradamento.

## Metodo 2(SDN)
Software-defined networking, un controller remoto configura le tabelle di inoltro ai router. Si comunica tramite messaggi scambiati tra router e controller remoto. Gestione via software

# Modelli livello rete
- **Consegna garantita:** Assicura ricezione pacchetto
- **Consegna garantita con ritardo limitato:** Ricezione pacchetto entro ritardo specificato
- **Consegna ordinata:** Ricezione pacchetti nell'ordine di invio
- Banda minima garantita: Trasmissione senza packet loss entro un certo limite di banda
- **Servizio sicurezza:** Cripta i datagrammi dalla sorgente alla destinazione

Il livello di rete fornisce solo best-effort, senza assicurazioni di consegna, ordine, ritardo o banda.

# Cosa c'é dentro un router?
- **Porte di ingresso:** Terminano collegamenti in ingresso e cercano porta di uscita
- **Struttura di commutazione:** Collega le porte di ingresso con uscita
- **Porte di uscita:** Trasmettono i pacchetti dalla struttura di commutazione
- **Processore di instradamento:** (Router tradizionali) funzioni di controllo e gestisce tabelle. (SDN) responsabile comunicazione con controller remoto

## Inoltro basato su indirizzo di destinazione
In che modo le porte di ingresso determinano quelle di uscita?
_Caso semplice:_ inoltro basato sul controllo dell'intero indirizzo ip di destinazione

_Casi non banali:_ confronto di un prefisso dell'indirizzo per inoltro.
Problema: indirizzo ip con corrispondenze di piú prefissi

**Corrispondenze**			**Interfaccia**

11001000 00010111 00010   		0
11001000 00010111 00011000		1
11001000 00010111 00011 		2

IP A: **11001000 00010111** 00010110 10100001	Inoltrato a 0
IP B: **11001000 00010111** 00011000 10101010	Inoltrato a 1

In questo caso il router adotta **Corrispondenza a prefisso piú lungo**, l'interfaccia di uscita viene determinata in base al prefisso piú lungo.

## Struttura di commutazione
### Che fa?
Collega le porte di ingresso con quelle di uscita.

### Metodi
- **Commutazione in memoria:** I primi router semplici effettuavano la commutazione tra PIN e POUT sotto il controllo della CPU
- **Commutazione tramite BUS:** Tramite un bus condiviso le PIN trasferiscono i pacchetti sulle POUT
- **Commutazione attraverso rete di interconnesione:** Tramite una matrice di commutazione si inoltrano i pacchetti da PIN a POU in modo parallelo. la matrice é **non-blocking**, ovvero, un pacchetto in via di inoltro su una POUT non verrá mai bloccato a meno che non ce ne sia un altro che vada sulla stessa POUT

## Accodamento
Se la struttura di commutazione é piú lenta rispetto alle PIN e POUT si creeranno ritardi e perdite perché i buffer sono pieni.

### In ingresso
Se due pacchetti in ingresso da due PIN differenti devono essere indirizzati alla stessa POUT, uno dei due verrá bloccato e attenderá. Fenomeno chiamato **HOL** (Head-of-the-line blocking).

### In uscita
Accade quando il tasso di trasmissione é inferiore rispetto alla velocitá della struttura di commutazione. Come risolvere?
- **Buffer**
- **Schedulazione**

#### Memoria buffer necessaria
- **Vecchia formuala:** Si pensava che la grandezza del buffer dovesse essere uguale al RTT medio moltiplicato per la capacitá del link: $B=RTT \cdot C$
- **Formula attuale:** Recenti studia hanno dimostrato che quella formula era invalida e si é passati a: $B = \frac {RTT \cdot C} {\sqrt{N}}$ con $N$ come numero dei flussi TCP.
Con buffer troppo grandi si possono creare ritardi.
In caso di buffer pieno si adotta la politica del _drop-tail:_ si scarta un pacchetto o in ingresso o uno giá in coda.

#### Schedulazione
**Obiettivo:** Determinare pacchetto su POUT
---
**FIFO:** Pacchetti trasmessi in base a ordine di arrivo. Buffer pieno? _drop-tail_
**Priority Queue:** Pacchetti classificati in base a classi di prioritá, ogni classe ha una coda, FIFO viene applicato in ogni classe.
**Round Robin:** Pacchetti suddivisi in classi e trasmessi alternatamente, trasmissione in maniera ciclica.
**Weighted Fair Queuing:** Round Robin generalizzato, assegnando ad ogni classe un peso e una quantitá di servizio proporzionale al peso.

# Protocollo Internet: IPV4, IPV6
## Datagrammi IPV4
- **Numero versione:** 4 bit identificatori della versione del protocollo IP
- **Lunghezza dell'intestazione:** 4 bit che identificano l'inizio dei dati nel datagramma
- **Tipo di servizio:** Bit relativi ai tipi di servizio che servono per distinguere i tipi di datagrammi
- **Identificatore, flag, offset di frammentazione:** Utilizzati per la frammentazione di grandi datagrammi IP in piccoli datagrammi IP.
- **Lunghezza del datagramma:** 16 bit per la lunghezza totale del datagramma IP
- **Protocollo:** Indica il protocollo al livello di trasporto per la trasmissione dei dati: 6 UDP, 17 TCP
- **Tempo di vita:** Serve per assicurare che i datagrammi non vivano per sempre nella rete, viene decrementato ad ogni passaggio in un router.
- **IP sorgente e destinazione:** Quando un host crea un datagramma, inserisce nell'intestazione il suo IP sorgente e quello di destinazione. 16 bit a indirizzo.
- **Opzioni:** Opzioni aggiuntive che si possono inserire nell'intestazione.
- **Dati:** Contiene il segmento a livello di trasporto da consegnare alla destinazione. Puó trasportare anche altri tipi di dati come messaggi ICMP.

## Indirizzamento IPV4
Ad ogni intrfaccia di un dispositivo é assegnato un indirizzo IP.
Ip lunghi 32 bit.
Gli indirizzi non vengono scelti casualmente. Una parte di un indirizzo di interfaccia viene determinato dalla sottorete cui é collegato.
- **Sottorete:** Gruppo di dispositivi che comunicano tra di loro senza passare per un router.
Es: Indirizzi come 223.1.1.0/24, 223.1.2.0/24, 223.1.3.0/24 hanno /24 **Maschera di sottorete** che indica che i 24 bit piú a sinistra sono comuni in quella sottorete.

## Struttura
- **Parte della sottorete:** I dispositivi della sottorete hanno in comune i bit di ordine superiore, definiti dalla subnet mask.
- **Parte dell'host:** Bit rimanenti di ordine inferiore.

_Classless Interdomain Routing CIDR_
**Cos'é?** strategia di assegnazione degli indirizzi internet che generalizza la nozione di indirizzamento di sottorete.

L'indirizzo IP é diviso in 2 parti:  $a.b.c.d/x$, con $x$ che indica il numero di bit della sottorete.
255.255.255.255 é l'indirizzo di broadcast.

## DHCP
Ottenuto il blocco degli indirizzi IP, le interfacce dei router vengono configurate manualmente, ma quelle degli host possono essere configurate sia manualmente, sia automaticamente.
il DHCP permette ad un host di ottenere un indirizzo IP automaticamente. Puó essere temporaneo o permanente.
Spesso viene chiamato _plug-and-play_ per la sua capacitá di automatizzare la connessione degli host di una rete.

### Configurazione di nuovi host
Si svolge in 4 punti:
- **Individuazione del server DHCP:** L'host invia un messaggio DHCP discover all índirizzo di broadcast
- **Offerta del server DHCP:** Il server risponde con DHCP offer che contiene l'indirizzo IP.
- **Richiesta DHCP:** L'host seleziona l'offerta e risponde con DHCP request.
- **Conferma DHCP:** Il server conferma con un DHCP ACK.

# NAT (Network Address Traslation)
Dato il numero limitato di indirizzi IPV4, si utlizzano gli IP pubblici per l'interfaccia esterna e IP privati per quella interna.
Grazie al NAT, tutti i dispositivi interni possono condividere lo stesso indirizzo IPV4 pubblico per comunicare con l'esterno.
**Come?**
Il NAT consente al router di apparire come unico dispositivo con un indirizzo IP pubblico. Utilizzando una tabella di traduzione NAT, il router associa gli indirizzi privati e le porte interne a un unico indirizzo pubblico.

# IPV6
**Perché?**
Creato principalmente per per risolvere la scarsitá degli indirizzi IPV4, offrendo:
- **Indirizzi estesi:** da 32 a 128 bit
- **Intestazione ottimizzata:** Intestazione fissa da 40 bit
- **Etichettatura dei flussi:** Permette di identificare e gestire i flussi specifici di traffico

## Datagrammi IPV6
- **Versione:** 4 bit per la versione del protocollo (come IPV4)
- **Classe di traffico:** Assegnazione di prioritá a determinati datagrammi
- **Etichetta di flusso:** Identifica i datagrammi che provengono dallo stesso flusso
- **Lunghezza del payload:** 16 bit di lunghezza del payload
- **Intestazione successiva:** Identifica il protocollo
- **Limite di hop:** Equivalente di TTL in IPV4 (Time-to-live)
- **Indirizzi sorgente e destinazione:** Indirizzi IPV6
- **Dati:** Payload trasportato

### Da IPV4 a IPV6
Si utilizza il **tunneling** per permettere la comunicazione tra nodi IPV6 con rete IPV4
**Tunneling, come funziona?**
Nodo IPV6 incapsula un datagramma all'interno del campo dati di un datagramma IPV4, che successivamente verrá instradato da router IPV4. Finito il tunnel, il datagramma IPV6 viene estratto e inviato al destinatario
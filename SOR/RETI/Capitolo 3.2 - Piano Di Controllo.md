# Introduzione
Nel piano di controllo, il router determina il percorso seguito dai pacchetti dalla sorgente alla destinazione. Esistono due metodi principali per questo compito: 
- **Controllo per router**: Ogni router comunica con gli altri per calcolare la propria tabella di inoltro (es. protocolli OSPF e BGP). 
- **Controllo logicamente centralizzato (SDN)**: Un controller centralizzato calcola e distribuisce le tabelle di inoltro a ciascun router.
# Algoritmi di Instradamento
Gli algoritmi di instradamento mirano a trovare percorsi ottimali tra sorgenti e destinatari in una rete di router. Consideriamo un grafo $G = (V,E)$, dove $V$ sono i nodi (router) ed $E$ gli archi (collegamenti fisici tra router).

![[PianoControllo_1.png | center | 500]]

Per ogni arco $(x,y)$ tra i nodi denotiamo con $c(x,y)$ il costo di tale arco. Poniamo $c(x,y) = +\infty$ se l'arco $(x,y) \notin E$.
L'obiettivo è quello di trovare il percorso minimo tra due nodi.
- **Algoritmi di instradamento centralizzato**: Calcolano il percorso a costo minimo avendo una conoscenza globale della rete. Questi algoritmi sono chiamati **link-state**. 
- **Algoritmi di instradamento decentralizzato**: Calcolano il percorso a costo minimo in modo distribuito e iterativo, basandosi sullo scambio di informazioni tra nodi vicini. Questi algoritmi sono chiamati **distance-vector**.

## Instradamento "Link-State" - Dijkstra
Nell'instradamento link-state, la topologia della rete e i costi dei collegamenti sono noti. Ogni nodo invia pacchetti contenenti l'identità e il costo dei collegamenti ai nodi della rete. L'**algoritmo di Dijkstra** calcola i percorsi minimi da un nodo a tutti gli altri.

Patologia: Quando i costi dei collegamenti dipendono dal volume di traffico, sono possibili variazioni dei percorsi.

## Instradamento "Distance-Vector" - Bellman Ford

L'algoritmo distance-vector è iterativo, asincrono e distribuito. 
- **Distribuito**: Ogni nodo riceve informazioni dai vicini e aggiorna i propri risultati. 
- **Iterativo**: Il processo si ripete fino al termine del calcolo. 
- **Asincrono**: Non richiede che tutti i nodi operino sincronizzati. 

L'algoritmo utilizza la formula di Bellman-Ford, $d_{x}(y)$ è il costo del percorso minimo da x ad y $$d_{x}(y) = \min_{v} \big\{ c(x,v) + d_{v}(y) \big\}$$ il minimo è calcolato su tutti i nodi $v$ vicini di $x$ ed è la somma del costo del collegamento diretto tra $x$ e $v$ sommato al costo del cammino minimo tra $v$ ed $y$.

|                 | Complessità                              | Convergenza                          | Robustezza                                                                                             |
| --------------- | ---------------------------------------- | ------------------------------------ | ------------------------------------------------------------------------------------------------------ |
| Link-State      | $O(n^{2})$ messaggi inviati              | $O(n^{2})$                           | Può comunicare in broadcast un costo sbagliato, propria tabella dei costi                              |
| Distance-Vector | Scambio di messaggi tra router adiacenti | Potrebbe convergere molto lentamente | Può comunicare in broadcast percosi a costo minimi errati, la tabella di router è usata anche da altri |

Sia $d_{x}(y)$ il costo del percorso minimo tra nodo $x$ a $y$. Allora i costi minimi sono correlati dalla formula di Bellman Ford: $d_{x}(y)= min_{v} \ c(x,v) + d_{v}(y)$.
L'idea di base è: Ciascun nodo con $D_{x}(y)$, una stima del costo del percorso a costo minimo da sè stesso a $y$, per per tutti i nodi in $V$. Sia $D_{x} = [D_{x}(y) \in V]$ il vettore delle distanze del nodo $x$, che è il vettore delle stime dei costi da $x$ a tutti gli atri nodi $y$, in $V$.
Con l'algoritmo di Bellman Ford, ciascun nodo mantiene le seguenti informazioni:
- Per ciascun vicino $v$ , il costo $c ( x , v )$.
- Il vettore delle distanze $D_{x} = [ D_{x} ( y ) ∈ V ]$.
- I vettori delle distanze di ciascun vicino $D_{v} = [ D_{v} ( y ) ∈ V ] .$
Dunque, di tanto in tanto, ogni nodo invia ai vicini il proprio vettore delle distanze stimate. Quando $x$ riceve un "distance vector" da un qualsiasi vicino, aggiorna il proprio "distance vector" utilizzando l'equazione di Bellman-Ford. $D_{x} ( y ) = min_{v} \ c ( x , v ) + D_{v} ( y ) \ ∀ y ∈ V$ Di questo passo, sotto certi condizioni minori e natulari, la stima $D_{x} ( y )$ converge verso l'effettivo costo minimi $d_{x} ( y )$.

## OSPF e BGP
Fino adesso abbiamo visto la rete come un insieme di router identici ed interconessi tra loro. Nella realtà non è così, infatti internet è una rete di reti, costituita da moltissimi router e miliardi di destinazioni, per questo ogni router non può memorizzare tutte le destinazioni all'interno di una tabella di routing, perché la tabella sarebbe troppo grande e lo scambio di queste ingolferebbe i collegamenti. Inoltre ogni amministratore di rete vuole controllare l'instradamento all'interno della propria rete. 

I router sono quindi organizzati in **sistemi autonomi (AS, Autonomous System)**, dei gruppi di router posti sotto lo stesso controllo amministrativo.
Un ISP può costituire un unico AS oppure essere partizionato in più AS.
- **Intra-AS protocol:** Algoritmo di instradamento interno all'AS. I router di un AS eseguono lo stesso algoritmo di instradamento e gli uni hanno informazioni sugli altri. Sul bordo dell'AS si trovano i router di **gateway**, che connettono i diversi AS. Il protocollo più usato è OSPF.
- **Inter-AS protocol:** Algoritmo di instradamento tra AS. Uno dei più importanti protocolli di rete è BGP.

La suddivisione di Internet in vari AS risolve il problema della scalabilità perchè negli algoritmi link-state e distance-vector l’invio delle informazioni sullo stato della rete o sulle distanze è limitato all’AS in questione!
Ciò comporta tabelle più piccole e maggiore velocità di convergenza. Come detto ogni sistema autonomo può usare il proprio algoritmo di instradamento, e affinché non sia isolato dagli altri AS è necessaria la presenza del router gateway.

### OSPF (Open Shortest Path First)
OSPF è un protocollo link-state in cui ciascuin router utilizza il flooding (inondazione) per inviare in broadcast le informazioni sullo stato dei collegamenti e l’algoritmo di Dijkstra per la determinazione del percorso a costo minimo. In OSPF, ogni router ha piena conoscenza della tipologia di rete ed utilizza l’algoritmo di Dijkstra per determinare la tabella di inoltro.

**Vantaggi:**
- _Sicurezza_: Tutti i messaggi OSPF sono autenticati per preventire intrusioni dannose.
- _Percorsi con lo stesso costo_: Se ci sono più percorsi con lo stesso costo, OSPF consente di usarli senza dover sceglierne uno particolare.
- _Gerarchia a due livelli_: Una AS può essere stutturata in due aree, **locale** e **dorsale (backbone**).

### BGP
Il **border gateway protocol (BGP)**, rappresenta l’attuale standard de facto dei protocolli di instradamento tra sistemi autonomi in Internet.
BGP offre a ciascun router un modo per:
- ottenere informazioni sulla raggiungibilità delle sottoreti da parte dei sistemi confinanti
- determinare le rotte verso altre reti sulla base delle informazioni sulla raggiungibilità
- propagare le informazioni di raggiungibilità ai router interni dell'AS
- annunciare alle reti confinanti la raggiungibilità delle destinazioni.

Supponiamo ora di avere questa rete formata da 3 AS:
![[PianoControllo_2.png | center | 600]]
In una **Sessione BGP**, due router BGP si scambiano messaggi BGP attraverso una connessione TCP per annunciare all'altro i percorsi *disponibili* verso altre sottoreti. Se la sessione coinvolge due router di AS distinti viene detta *sessione BGP* esterna (sessione eBGP), mentre quella tra router dello stesso AS è chiamata sessione BGP interna (sessione iBGP). 
In un messaggio BGP, ad esempio il router 3a può comunicare al router 2c che tramite il suo router può effettuare il percorso con destinazione X. 
I messaggi BGP sono:
- **OPEN**: apre una connessione TCP e autentica il mittente BGP
- **UPDATE**: annuncia un nuovo percorso
- **KEEPALIVE**: mantiene in vita la connessione
- **NOTIFICATION**: segnala gli errori nel messaggio precedente; usato anche per chiudere la connessione

#### Comunicazione percorsi BGP
Vediamo come vengono annunciati i percorsi da BGP

![[SOR/RETI/img/img120.png|center|500]]

- Il router 3a in AS3 comunica al 2c in AS2 il percorso disponibile verso X. 
- Il router 2c accetta il percorso AS3, X e lo propaga internamente (iBGP) ai router di AS2
- in questo modo il router 2a di AS2 può comunicare al vicino 1c di AS1 il percorso AS2, AS3, X

Un router gateway può anche ricevere percorsi multipli come possiamo vedere nella prossima foto.

![[SOR/RETI/img/img121.png|center|500]]
Lo scenario è identico al precedente, con la distinzione che il router 3a di AS3 comnunica il percorso AS3, X al router 1c di AS1.
In questo caso, il router 1c deve scegliere quale percorso accettare tra AS2, AS3, X e AS3, X. In questo caso è più conveniente passare per un solo router gateway, quindi sceglie AS3, X e lo comunica internamente tramite iBGP.

- **AS-PATH**: elenco degli AS attraverso i quali è passato l'annuncio del prefisso.
- **NEXT-HOP**: indirizzo IP dell'interfaccia del router che inzia l'AS-PATH.
- **Instradamento a patata bollente**: L'algoritmo sceglie il gateway locale che ha il minimo costo INTRA-AS.
![[PianoControllo_3.png | Center | 900]]
Il router inolte, può conoscere più di un percorso verso l'AS di destinazione, seleziona il percorso in base a:
- Valore dell'attributo di **prefernza locale**.
- AS-PATH più breve.
- Router NEXT-HOP più vicino.
- Indentificatori BGP1

## SDN (Software-Defined Networking)
Il controller SDN calcola e distribuisce le tabelle di inoltro nei router. La scelta di un piano di controllo centralizzato permette una gestione più semplice della rete, perché evita errori di configurazione sui router. Inoltre le tabelle di inoltro calcolate centralmente sono più semplici da realizzare rispetto ad un calcolo delle tabelle basato sul risultato di un algoritmo implementato in ogni router.

![[PianoControllo_4.png | center | 600]]

Il controller SDN mantiene informazioni sullo stato della rete. Interagisce con le applicazioni di controllo della rete mediante *API northbound* e con gli switch di rete tramite *API southbound*. 

Nel piano di dati gli switch utilizzati sono semplici e veloci ed implementano l'inoltro nell'hardware. La tabella di inoltro è calcolata ed installata sotto la supervisione del controllore SDN. Le API per il controllo degli switch sono basate su delle tabelle e viene definito un protocollo di comunicazione col controller (es. OpenFlow).

Le applicazioni di controllo implementano le funzioni di controllo sulla rete.
### OpenFlow
Il protocollo OpenFlow opera tra un controller SDN e uno switch. Il protocollo usa TCP per lo scambio di messaggi con numero di porta 6653. Alcuni dei principali messaggi del protocollo inviati dal controller allo switch controllato sono i seguenti:
- **Configuration**: Questo messaggio permette al controller di interrogare e impostare i parametri di configurazione di uno switch
- **Modify-State**: Aggiungere, eliminare, modificare voci di flusso nelle tabelle OpenFlow
- **Read-State**: Il controllore interroga le caratteristiche dello switch, lo switch risponde
- **Send-Packet**: Questo messaggio è usato dal controller per inviare un pacchetto specifico fuori da una specifica porta dello switch.
- **Packed-in**: Trasferire il pacchetto (e il relativo controllo) al controllore. Vedere il messaggio packet-out dal controllore.
- **Flow-Removed**: Questo messaggio informa il controller che un’occorrenza della tabella dei flussi è stata cancellata.
- **Port-status:** Questo messaggio è usato dallo switch per informare il controller di un cambiamento nello stato di una porta.

**Esempio di interazione tra piano dati e piano di controllo:**
![[PianoControllo_5.png |center|600]]
1. Lo switch s1 osserva la caduta del collegamento con s2 e notifica tale cambiamento nello stato del collegamento al controller SDN utilizzando un messaggio OpenFlow port-status.
2. Il controller SDN riceve il messaggio OpenFlow con il cambiamento dello stato del collegamento, lo notifica al gestore dello stato del collegamento che aggiorna il database degli stati dei collegamenti.
3. L’applicazione di controllo di rete che implementa l’algoritmo di Dijkstra riceve la notifica del cambiamento dello stato del collegamento.
4. L'algoritmo di routing di Dijkstra accede alle informazioni sul grafo della rete, alle informazioni sullo stato dei collegamenti nel controllore e calcola nuovi percorsi.
5. L’applicazione di instradamento interagisce quindi con il gestore della tabella dei flussi che determina quali tabelle debbano essere aggiornate.
6. Il controllore utilizza OpenFlow per installare nuove tabelle negli switch che necessitano di un aggiornamento

## ICMP (Internet Message Control Protocol)
Il protocollo **ICMP (Internet Control Message Protocol)** viene usato da host e router per scambiarsi informazioni a livello di rete: il suo uso più tipico è la notifica degli errori.
ICMP è spesso considerato parte di IP, ma dal punto di vista dell’architettura si trova esattamente sopra IP, dato che i suoi messaggi vengono trasportati nei datagrammi IP: ossia, i messaggi ICMP vengono trasportati come payload di IP, esattamente come i segmenti TCP o UDP. Allo stesso modo, se un host riceve un datagramma IP, che specifica ICMP come protocollo di livello superiore, allora effettua il demultiplexing dei contenuti del datagramma a ICMP, esattamente come farebbe per contenuti TCP o UDP.

![[SOR/RETI/img/img122.png]]

Il tipo ed il codice dei messaggi comportano una tipologia di messaggio differente:
![[SOR/RETI/img/img123.png|center|300]]

Nel caso dei messaggi con tipo 3 quelli con codice 2 e 3 sono inviati dall'host di destinazione, ad esempio nel caso in cui il protocollo relativo e la porta non sono attivi. Gli altri tipi di messaggi sono invece inviati dai router lungo il percorso, nel caso di irraggiungibilità della rete o nel non trovare una rotta idonea.

## Gestione della rete
- **Sistema autonomo (rete)**: migliaia di componenti hardware e software che interagiscono tra loro.
- **Gestione della rete**: comprende il funzionamento, l'integrazione e il coordinamento di hardware, software e personale tecnico per monitorare, verificare, configurare, analizzare, valutare e controllare le risorse della rete affiché soddisfino le funzionalità in tempo reale e i requisiti di qualità del servizio a un costo accettabile. In poche parole si occupa di gestire e coordinare tutte le componenti hardware e software della rete.
**Componenti nella gestione di rete:**
- _Server di gestione:_ Raccolta, gestione, elaborazione e analisi delle informazioni. Si occupa dell'invio di informazioni e comandi.
- _Dispositivo di rete gestito:_ Dipositivi hardware e software configurabili.
- _Protocollo di gestione:_ Utilizzato dal server di gestione per interrogare i dispositivi sul loro stato e agire su di essi.
- _Dati:_ Sono gli stati dei dispostivi, come i dati di configurazione (indirizzi IP) e dati operativi e statistiche.
Metodi di approcci del operatore alla rete:
- _CLI (Command Line Interface):_ L'operatore scrive comandi sulla linea di comando o s script da remoto in `ssh`.
- _Protocollo SNMP/MIB:_ L'operatore interroga/imposta i dati contenuti negli oggetti MIB utilizzando il Simple Network Management Protocol.
- _NETCONF/YANG:_ È simile a SNMP ma con enfasi maggiore sul definire la configurazione di rete piuttosto che definire la configurazione dei singoli dispositivi. NETCONF è il protocollo e YANG è il linguaggio.

### SNMP
SNMP è un protocollo a livello di applicazione e utilizza UDP. Ha due modalità di funzionamento:
1. **Richiesta/Risposta**: Il server di gestione, detto **client SNMP**, manda una richiesta all'agente SNMP che poi invia la risposta.
2. **Modalità trap**: L'agente manda un messaggio al server di gestione per notificarlo di un evento imprevisto.
Nel formato di questi messaggi (PDU) deve essere presente un ID che associ la richiesta alla risposta. SNMP accede ai dati dal dispostivo, dati modellati nella MIB che può essere visto come un database gerarchico (root, nodi, etc...). Quindi la struttura del MIB rircorda molto la struttura di un file system gerarchico, dunque possiamo identificare un oggetto tramite un _percorso definito dai vari componenti separati da un separatore. I componenti in questo caso sono numeri ed il separatore un punto._
- **OID**: rappresenta un cammino nella base dati MIB, che permette di identificare l'oggetto MIB specifico che potrò leggere o scrivere.

### NETCONF
Per NETCONF il discorso è simile a SNMP, si tratta semplicemente di un'alternativa più moderna. L'obiettivo è quello di gestire ma anche di configurare i dispositivi della rete. In NETCONF troviamo le notifiche che hanno lo stesso ruolo dei messaggi trap in SNMP.
- **Differenza tra NETCONF E SNMP**: NETCONF supporta la manipolazione simultanea nella configurazioni di molteplici dipositivi. (Domnda d'esame).
Due estremi nel protocollo NETCONF comunicano tramite una chiamata di procedura remota `rpc` fatta stabilendo una sessione con un protocollo di trasferimento dati affidabile e sicuro, inviando messaggi `xml` atti a codificare sia la richiesta che la risposta.

#### YANG
YANG è il linguaggio di modellazione dei dati utilizzato per specificare la struttura, la sintassi e la semantica dei dati di gestione della rete NETCONF. Dal codice YANG viene generato un file `xml` che descrive il dipositivo.
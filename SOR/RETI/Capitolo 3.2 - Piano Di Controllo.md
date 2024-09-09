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
- **Algoritmi di instradamento decentralizzato**: Calcolano il percorso a costo minimo in modo distribuito e iterativo. Nessun nodo possiede informazioni complete sui collegamenti di rete. Questi algoritmi sono chiamati **distance-vector**.

## Instradamento "Link-State" - Dijkstra
Nell'instradamento link-state, la topologia della rete e i costi dei collegamenti sono noti. Ogni nodo invia pacchetti contenenti l'identità e il costo dei collegamenti ai nodi della rete. L'**algoritmo di Dijkstra** calcola i percorsi minimi.

Patologia: Quando i costi dei collegamenti dipendono dal volume di traffico, sono possibili oscillazioni dei percorsi.

## Instradamento "Distance-Vector" - Bellman Ford

L'algoritmo distance-vector è iterativo, asincrono e distribuito. 
- **Distribuito**: Ogni nodo riceve informazioni dai vicini e aggiorna i propri risultati. 
- **Iterativo**: Il processo si ripete fino al termine del calcolo. 
- **Asincrono**: Non richiede che tutti i nodi operino sincronizzati. 

L'algoritmo utilizza la formula di Bellman-Ford: $d_{x}(y) = \min_{v} \{ c(x,v) + d_{v}(y) \}$

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

## OSFP e BGP
Fino adesso abbiamo visto la rete come un insieme di router interconnessi tra loro. Ciascun router distinguibile dagli altri. Ora invece proviamo a ragrupparli come segue.

Possiamo organizzare i router in **sistemi autonomi (AS, Autonomous System)**, generalmente composti da gruppi di router posti sotto lo stesso controllo amministrativo.
Un ISP può costituire un unico AS oppure essere partizionato in più AS.
- **Intra-AS protocol:** Algoritmo di instradamento in esecuzione in un AS. I router di un AS eseguono lo stesso algoritmo di instradamento e gli uni hanno informazioni sugli altri. Sul bordo dell'AS si trovao i router di **gateway**. Il protocollo più usato è OSFP.
- **Inter-AS protocol:** Algoritmo di instradamento tra AS. I router gateway effettuano l'instradamento inter-AS oltre a quello intra-AS. Uno dei più importanti protocolli di rete è BGP.
Ciò che comporta la suddivisione di Internet in vari AS e che risolve il problema della scalabilità è che negli algoritmi link-state e distance-vector l’invio delle informazioni sullo stato della rete o sulle distanze è limitato all’AS in questione!
Ciò comporta tabelle più piccole e maggiore velocità di convergenza. Come detto ogni sistema autonomo può usare il proprio algoritmo di instradamento, e affinché non sia isolato dagli altri AS è necessaria la presenza del router gateway. I gateway partecipano sia all’instradamento inter-AS che a quello intra-AS.

### OSFP (Open Shortest Path First)
OSPF è un protocollo link-state che utilizza il flooding (inondazione) per inviare in broadcast le informazioni riguardo lo stato dei collegamenti e l’algoritmo di Dijkstra per la determinazione del percorso a costo minimo. In OSPF, un router costruisce una mappa topologica, cioè un grafo, dell’intero sistema autonomo e manda in esecuzione (locale) l’algoritmo di Dijkstra per determinare un albero dei cammini minimi verso tutte le sottoreti (albero in cui il router stesso rappresenta il nodo radice).

**Vantaggi:**
- _Sicurezza_: Tutti i messaggi OSFP sono autenticati per preventire intrusioni dannose.
- _Percorsi con lo stesso costo_: Se ci sono più percorsi con lo stesso costo, OSPF consente di usarli senza dover sceglierne uno particolare.
- _Gerarchia a due livelli_: Una AS può essere stutturata in due aree, **locale** e **dorsale (backbone**).

### BGP
Il **border gateway protocol (BGP)**, rappresenta l’attuale standard de facto dei protocolli di instradamento tra sistemi autonomi in Internet.
BGP offre a ciascun router un modo per:

#### Ottenere informazioni sulla raggiungibilità dei prefissi di sottorete da parte dei sistemi confinanti.
Supponiamo ora di avere questa rete formata da 3 AS:
![[PianoControllo_2.png | center | 800]]
Una **Sessione BGP** consiste di due router BGP che si scambiano messaggi BGP attraverso una connessione TCP. Nel caso in cui questa coinvolga due sistemi autonomi viene detta sessione BGP esterna (sessione eBGP), mentre quella tra router dello stesso sistema autonomo è chiamata sessione BGP interna (sessione iBGP). I messaggi BGP sono:
- **OPEN**: apre una connessione TCP e autentica il mittente BGP
- **UPDATE**: annuncia un nuovo percorso
- **KEEPALIVE**: mantiene in vita la connessione
- **NOTIFICATION**: segnala gli errori nel messaggio precedente; usato anche per chiudere la connessione

#### Determinare i percorsi "ottimi" verso le sottoreti.
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
- Controller SDN

![[PianoControllo_4.png | center | 800]]
Mantiene le informazioni sullo stato della rete. Interagisce con le applicazioni di controllo della rete "in alto" tramite API "northbound". Interagisce con gli switch di rete "in basso" tramite AP "southbound". È implementato come sistema distribuito per garantire prestazioni, scalabilità, tolleranza ai guasti, robustenzza e sicurezza.
- Applicazzioni di controllo
Implementano le funzioni di controllo utilizzando servizi di livello inferiore attraverso API fornite dal controller SDN.

### OpenFlow
Il protocollo OpenFlow opera tra un controller SDN e uno switch o un altro dispositivo che implementi le API OpenFlow del piano di dati. Il protocollo opera su TCP con numero di porta 6653. Alcuni dei principali messaggi del protocollo inviati dal controller allo switch controllato sono i seguenti:
- **Configuration**: Questo messaggio permette al controller di interrogare e im- postare i parametri di configurazione di uno switch
- **Modify-State**: Aggiungere, eliminare, modificare voci di flusso nelle tabelle OpenFlow
- **Read-State**: Il controllore interroga le caratteristiche dello switch, lo switch risponde
- **Send-Packet**: Questo messaggio è usato dal controller per inviare un pacchetto specifico fuori da una specifica porta dello switch.
- **Packed-in**: Trasferire il pacchetto (e il relativo controllo) al controllore. Vedere il messaggio packet-out dal controllore.
- **Flow-Removed**: Questo messaggio informa il controller che un’occorrenza della tabella dei flussi è stata cancellata.
- **Port-status**Questo messaggio è usato dallo switch per informare il controller di un cambiamento nello stato di una porta.

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
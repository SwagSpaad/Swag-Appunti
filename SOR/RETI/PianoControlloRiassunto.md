# Introduzione
**Cos'é?**
Il router determina il percorso dei pacchetti dalla sorgente alla destinazione.
2 metodi:
- **Controllo per router:** Router comunicanti per calcolare le proprie tabelle di inoltro
- **Controllo logicamente centralizzato (SDN):** Controller centralizzato comunicante con i router che calcola le tabelle per ogni router

# Algoritmi di Instradamento
Mirano a trovare il percorso piú ottimale tra sorgente e destinatario in una rete di router.
**Obiettivo**
Trovare percorso minimo tra 2 nodi:
- **Algoritmi di instradamento centralizzato:** Chiamati _link-state_, conoscono la rete globalmente e calcolnao il percorso minimo.
- **Algoritmi di instradamento decentralizzato:** Chiamati _distance-vector_, calcolano il percorso a costo minimo in maniera distributiva e iterativa. Non hanno conoscenze della rete.

## Dijkstra, Link-state
Costi e topologia della rete noti.
Ogni nodo invia pacchetti contenenti identitá e costo del collegamento ai nodi della rete.
L'algoritmo di Dijkstra calcola il percorso con costo minimo.

## Bellman Ford, Distance-vector
- **Distribuito:** Tutti i nodi ricevono informazioni dai vicini e aggiornano il proprio stato
- **Iterativo:** Ripetizione del processo fino alla fine del calcolo
- **Asincorno:** I nodi non operano in maniera sincronizzata

# OSFP, BGP
Si possono organizzare i router in _sistemi autonomi_ (AS), composti da gruppi di router gestiti dallo stesso controllo amministrativo.
Un ISP puó sia essere un unico AS oppure essere partizionato in piú AS.
- **Intra-AS protocol:** Algoritmo di instradamento in un AS. Tutti i router di un AS eseguono lo stesso algoritmo e si scambiano informazioni. Sul bordo dell'AS troviamo i router di Gateway. Il protocollo piú usato é il OSFP.
- **Inter-As protocol:** Algoritmo di instradamento in un AS. I router gateway effettuano l'instradamento inter-AS oltre a quello intra-AS. Il protocollo piú usato é BGP
Quello che comporta la suddivisone in vari AS e risolve la scalabilitá é che negli algoritmi link-state e distance-vector l ínvio delle informazioni sullo stato della rete o sulle distanze é limitato all'AS in questione.
Questo comporta tabelle di inoltro piú piccole e maggiore velocitá di convergenza.

## OSFP (Open Short Path First)
Protocollo link-state che usa flooding per l'invio delle informazioni in broadcast riguardo lo stato dei collegamenti e l'algoritmo di Dijkstra per la determinazione del percorso a costo minimo.
In OSPF un router costruisce un grafo dell'intero AS e manda in esecuzione l'algoritmo di Dijkstra per determinare un albero di cammini minimi verso tutte le sottoreti.
**Vantaggi:**
- **Sicurezza:** Tutti i messaggi OSPF sono autenticati per prevenire intrusioni
- **Percorsi a costo uguale:** Dati piú percorsi con costo uguale, OSPF permette di utilizzarli senza doverne scegliere uno.
- **Gerarchia a due livelli:** AS strutturato in due aree, **locale** e **dorsale**

## BGP (Border Gateway Protcol)
Rappresenta l'attuale standard dei protocolli di instradamento tra sistemi autonomi in internet.
BGP offre ad ogni router un modo per:
### Ottenere informazioni sulla raggiungibilitá dei prefissi di sottorete da parte dei sistemi confinanti
Una sessione BGP consiste di due router BGp che si scambiano messaggi BGP attraverso una connessione TCP. Quando si tratta di 2 AS, viene chiamata sessione BGP esterna, mentre quella tra due rouuter dello stesso AS é chiamata sessione BGP interna.
I messaggi BGP sono:
- **Open:** Apertura di connessione BGP e autenticazione mittente BGP
- **Update:** Annuncia nuovo percorso
- **Keepalive:** Mantiene stabile una connessione
- **Notification:** Segnala errori nel messaggio e chiude la connessione

### Determinare i percorsi ottimi verso le sottoreti
- **As-path:** Elenco AS attraverso i quali é passato l'annuncio del prefisso
- **Next-Hop:** Indirizzo IP dell'interfaccia del router che inizia l'AS-path
- **Instradamento a patata bollente:** L'algoritmo sceglie il gateway locale che ha il costo minimo Intra-AS
Inoltre il router puó conoscere piú di un percorso verso l'AS di destinazione, selezionando il percorso in base a:
- **Valore dell'attributo di preferenza locale**
- **AS-path piú breve**
- **Router Next-Hop piú vicino**
- **Identificatori BGP**

# SDN
Mantiene le info sullo stato della rete.
Interazione applicazioni di controllo della rete "in alto" tramite API "northbound".
Interazioni switch di rete "in basso" tramite API "southbound".
Implementato come sisitema distriuito per garantire prestazioni, scalabilitá, sicurezza e robustezza.

# OpenFlow
Questo protocollo opera tra SDN e switch o un altro dispositivo che implementa le API OpenFlow del piano dei dati. Opera su TCP con porta numero 6653.
Messaggi principali:
- **Configuration:** Permette al controller di configurare uno switch
- **Modify-State:** Modificare le voci di flusso nelle tabelle OpenFlow
- **Read-State:** Permette di interrogare le caratteristiche dello switch
- **Send-Packet:** Usato dal controller per inviare un pacchetto specifico da una porta specifica dello switch
- **Packed-In:** Trasferire il pacchetto al controller
- **Flow-Removed:** Informa il controller su una occorrenza nella tabella di flusso cancellata
- **Port-Status:** Informa il controller su una modifica nello stato di porta

# ICMP (Internet Message Control Protocol)
Utilizzato da host e router per scambiarsi informazioni a livello di rete, uso tipico: notifica degli errori.
Si trova sopra IP, dato che i suoi datagrammi vengono trasportati come payload di IP, proprio come segmenti TCP o UDP. Allo stesso modo, se un host riceve un datagramma IP, che specifica ICMP come protocollo superiore, allora si effettuerá il demultiplexing dei contenuti del datagramma ICMP.

# SNMP
Protocollo a livello applicazione che utilizza UDP.
2 modalitá:
- **Richiesta/Risposta:** Il server di gestione, chiamato client SNMP, manda una richiesta all'agente SNMP che poi invia la risposta.
- **Trap:** L'agente manda un messaggio al server di gestione notificando un imprevisto.
Il formato dei messaggi é PDU e deve essere presente un ID che associa la richiesta alla risposta. SNMP accede ai dati dal dispositivo, questi dati sono modellati nella MIB (Management Information Base) che puó essere visto come un database gerarchico.
Dato che ricorda molto un FS, si possono identificare gli oggetti tramite i path utilizzando come separatori dei punti. Con _OID_ si definisce un cammino nella MIB, che identifica l'oggetto che potró scrivere/leggere.

# Netconf
Simile a SNMP, ma piú moderno.
**Obiettivo:** Gestire e configurare i dispositivi della rete.
Troviamo anche le notifiche che svolgono lo stesso ruolo su SNMP, messaggi TRAP.
**Differenze Netconf/SNMP:** Netconf supporta la manipolazione simultanea nella configurazione di piú dispositivi.
Due estremi nel protocollo Netconf comunicano usando una procedura remota `rpc` fatta stabilendo una sessione con un protocollo di trasferimento sicuro, inviando messaggi `xml` che codificano sia la richeista che la risposta.

# Yang
Utilizzato per specificare la struttura, la sinstassi e la semantica dei dati di gestione della rete Netconf. Dal codice yang viene generato un file `xml` che descrive il dispositivo

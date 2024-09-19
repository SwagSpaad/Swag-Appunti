**WIRELESS E RETE MOBILI**
La lezione 23 esplora le **reti wireless** e la **mobilità** degli utenti, chiarendo la differenza tra i due concetti:
- **Wireless**: Indica la comunicazione tramite collegamenti senza fili (mezzi non guidati come onde radio).
- **Mobilità**: Si riferisce alla capacità di un dispositivo di spostarsi mantenendo la connessione alla rete.

#### **Caratteristiche delle Reti Wireless**
Le reti wireless differiscono dalle reti cablate in diversi modi:
- **Attenuazione del segnale**: La potenza del segnale si riduce a causa di ostacoli fisici (muri, edifici) e per l'attenuazione di spazio libero, che aumenta con il quadrato della frequenza e della distanza.
- **Propagazione su cammini multipli**: Il segnale può riflettersi su vari oggetti e raggiungere la destinazione attraverso percorsi diversi, causando interferenze.    
- **Problema del terminale nascosto**: Due dispositivi che non possono rilevarsi reciprocamente possono interferire con un dispositivo comune, causando collisioni
- **Interferenza**: Può essere causata da altri dispositivi operanti sulla stessa frequenza (ad esempio, WiFi e Bluetooth a 2.4 GHz) o da rumore elettromagnetico (ad esempio, forni a microonde).    
#### **Meccanismi di Accesso al Canale Wireless**
A causa di queste sfide, le reti wireless utilizzano specifici meccanismi per gestire l'accesso al canale:
- **CSMA/CA (Carrier Sense Multiple Access with Collision Avoidance)**: I dispositivi ascoltano il canale per verificare se è libero prima di trasmettere. Se il canale è occupato, attendono un intervallo casuale per minimizzare le collisioni.
- **Protocollo di prenotazione RTS/CTS (Request to Send/Clear to Send)**: Un dispositivo invia una richiesta per l'uso del canale (RTS) e, se il destinatario risponde con un chiaro segnale di trasmissione (CTS), può procedere alla trasmissione. Questo meccanismo riduce le collisioni, specialmente in presenza di terminali nascosti .

#### **Gestione della Mobilità nelle Reti Wireless**
Le reti wireless devono gestire la mobilità degli utenti, ovvero la capacità degli utenti di spostarsi mantenendo la connessione alla rete. Per supportare questa mobilità, vengono utilizzati i seguenti meccanismi:
- **Handover**: La transizione di un dispositivo da un punto di accesso (AP) a un altro senza interruzioni evidenti per l'utente.
- **Mobilità a livello di rete**: Utilizzo di protocolli come **Mobile IP**, che permette ai dispositivi mobili di mantenere lo stesso indirizzo IP anche quando si spostano tra reti diverse .
#### **Introduzione alle Reti Cellulari 4G e 5G**
Le reti 4G e 5G rappresentano l'evoluzione delle tecnologie di comunicazione per l'**Internet mobile** su vasta area geografica. Queste reti permettono una connessione Internet a larga banda anche durante gli spostamenti, con velocità di trasmissione che possono raggiungere centinaia di Mbps (4G) e fino a diversi Gbps (5G).

Le reti cellulari differiscono dalle reti cablate poiché la mobilità degli utenti è un requisito fondamentale. Utilizzano protocolli simili a quelli delle reti cablate per i livelli applicativi, di trasporto e di rete, ma presentano differenze significative a livello di collegamento wireless. Fanno uso di tecniche di **Software-Defined Networking (SDN)** e strategie di tunneling per gestire la mobilità e l'accesso degli utenti.
#### **Architettura delle Reti Cellulari 4G/5G**
L'architettura delle reti 4G e 5G è costruita attorno a una **rete di accesso radio** costituita da stazioni base, come l’**eNode-B** (Enhanced Node B per 4G) e **gNode-B** (Next Generation Node B per 5G). Queste stazioni, simili agli access point delle reti WiFi, coprono aree geografiche chiamate **celle** e servono dispositivi mobili, noti come **User Equipment (UE)**.
- **IMSI (International Mobile Subscriber Identity)**: Ogni dispositivo mobile è identificato da un IMSI a 64 bit, memorizzato sulla SIM card. L'IMSI identifica sia il dispositivo sia la rete "home" dell'utente, e permette di autenticare il dispositivo nella rete.
Le reti di accesso radio sono interconnesse attraverso un **nucleo di rete** chiamato **Enhanced Packet Core (EPC)**, che connette tutte le celle di un operatore e interagisce con Internet tramite collegamenti cablati. I componenti principali del nucleo includono:
- **SG-W (Serving Gateway)**: Gestisce il traffico dati tra le stazioni base e il nucleo della rete.
- **P-GW (PDN Gateway)**: Fornisce la connettività tra la rete mobile e altre reti IP, compresa Internet.

#### **Tunneling e Gestione della Mobilità**
Le reti cellulari utilizzano il **tunneling** per gestire la mobilità degli utenti. Quando un dispositivo mobile si sposta da una cella a un'altra, il traffico dati continua a fluire senza interruzioni tramite un tunnel da P-GW a S-GW, e poi alla stazione base associata al dispositivo. Questo consente al dispositivo di mantenere una connessione stabile anche quando cambia cella, migliorando l'efficienza della rete.

- **Roaming**: Quando un dispositivo entra in una rete diversa da quella "home" (roaming), il **Mobility Management Entity (MME)** della rete visitata coordina con l'HSS (Home Subscriber Service) della rete home per aggiornare la posizione del dispositivo e mantenere la connettività.
#### **Specifiche del 4G (LTE - Long Term Evolution)**
- **Separazione tra Piano di Dati e Piano di Controllo**: LTE introduce una separazione chiara tra il piano di controllo (gestione della mobilità, sicurezza e autenticazione) e il piano di dati (trasporto dei dati utente).
- **Protocolli Specifici del LTE**:
    - **PDCP (Packet Data Convergence Protocol)**: Gestisce la compressione degli header e la cifratura.
    - **RLC (Radio Link Control Protocol)**: Responsabile della frammentazione e del riassemblaggio dei frame, oltre al trasferimento affidabile dei dati.        
    - **MAC (Medium Access Control)**: Gestisce l'accesso al mezzo fisico utilizzando tecniche come OFDM (Orthogonal Frequency-Division Multiplexing), migliorando l'efficienza spettrale e la capacità di trasmissione.
#### **Evoluzione al 5G**
Il **5G** offre miglioramenti significativi rispetto al 4G:
- **Affidabilità e Bassa Latenza**: Essenziali per applicazioni critiche come la chirurgia a distanza e i veicoli autonomi.
- **Maggiore Efficienza Spettrale**: Utilizza frequenze più alte rispetto al 4G, che permettono velocità di trasmissione maggiori ma con una maggiore attenuazione del segnale, richiedendo tecniche avanzate per mitigare gli effetti di attenuazione.
#### **Gestione della Mobilità nelle Reti Cellulari**
Esistono due approcci principali per gestire la mobilità:
1. **Instradamento Indiretto**: La comunicazione avviene attraverso la rete "home" del dispositivo, che inoltra i pacchetti alla rete visitata.
2. **Instradamento Diretto**: Il corrispondente invia i pacchetti direttamente al dispositivo mobile nella rete visitata, utilizzando il suo nuovo indirizzo IP.
#### **Bluetooth: Una Panoramica Completa**
La lezione 24 copre anche il **Bluetooth**, una tecnologia di comunicazione wireless a corto raggio progettata per collegare dispositivi personali (es. smartphone, auricolari, tastiere) a bassa potenza.
- **Tecnica di Accesso al Mezzo**: Bluetooth utilizza il **FHSS (Frequency Hopping Spread Spectrum)**, che divide la banda in 79 canali e salta rapidamente tra essi per ridurre le interferenze.
- **Architettura Bluetooth**: Si basa su una topologia a stella, con un dispositivo centrale **master** che coordina fino a sette dispositivi **slave** in una rete chiamata **piconet**. Dispositivi multipli possono formare una **scatternet**.
- **Interoperabilità e Applicazioni**: Bluetooth opera nella stessa banda di frequenza del WiFi (2.4 GHz) ma può convivere grazie al salto di frequenza. È utilizzato in accessori personali, sistemi automotive, e applicazioni IoT.
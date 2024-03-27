# Nucleo della Rete

Maglia (mesh) di commutatori di pacchetti e collegamenti che interconnettono i sistemi periferici di internet 

> Commutazione di Pacchetto: I sistemi periferici suddividono i messaggi di livello applicativo in pacchetti (packets)

La rete inoltra i pacchetti da un router all'altro attraverso i collegamenti lungo un percorso che connette la sorgente con il destinatario

## Funzioni chiave del nucleo della rete

**Azione locale**: sposta i pacchetti in arrivo al collegamento di ingresso del router al collegamento di uscita appropriato

**Azione globale**: determina i percorsi presi dai pacchetti dalla sorgente alla destinazione (Algoritmo di instradamento)

## Commutazione di pacchetto 
### Store-And-FOrward

Il router deve aver ricevuto l'intero pacchetto prima di poter ritrasmetterlo al prossimo

**Ritardo di trasmissione**: servono L/R secondi per trasmettere pacchetti di L bit attraverso un collegamento a R bps

>Ex: 
>3 Pacchetti 
  Sorgente -> Nodo -> Destinazione
  T = 4L/R 

Il ritardo da un capo all'altro (e-t-e) per la trasmissione di 1 pacchetto su un percorso di N collegamenti di pari velocità R: $d_{end-to-end} = NL/R$
**Ovviamente trascurando il ritardo di propagazione e altre forme di ritardo**

Il ritardo da un capo all'altro (e-t-e) per la trasmissione di P pacchetto su un percorso di N collegamenti di pari velocità R: $d_{end-to-end} = (N + P - 1)L/R$
**Ovviamente trascurando il ritardo di propagazione e altre forme di ritardo**

### Accodamento

L'accodamento si verifica quando il lavoro da svolgere arriva più velocemente di quanto possa essere servito.

Se il tasso di arrivo (bps) al collegamento eccede il tasso di trasmissione (bps) del collegamento per un certo periodo di tempo:

- I pacchetti si accodano in attesa di essere trasmessi sul collegamento in uscita
- I pacchetti verranno scartati (packet loss) se la coda di buffer e piena e continuano ad arrivare

## Commutazione di circuito

Risorse richieste lungo un percorso per consentire la comunicazione tra sistemi periferici sono riservate per l'intera durata della sessione di comunicazione

- Non si verifica nessuna condivisione di risorse (canali dedicati)
- Trasferimento dati a velocità costante 
- I segmenti del circuito restano inattivi se non utilizzati
Viene usato più comunemente nella rete telefonica tradizionale

### FDM (Frequency Division Multiplexing)

Spettro di frequenza di un collegamento suddiviso in bande, ogni circuito ha una propria banda e può trasmettere alla velocità massima di quella banda ristretta.

### TDM (Time Division Multiplexing)

Tempo suddiviso in frame di durata fissa ripartiti in un numero fisso di slot, ciascun circuito riceve slot periodici e può trasmettere alla massima velocità della banda di frequenza solo nei propri slot temporali.

## Commutazione di pacchetto VS Commutazione di circuito 



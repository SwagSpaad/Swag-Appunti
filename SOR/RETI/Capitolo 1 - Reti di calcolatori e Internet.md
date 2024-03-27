*****
# Introduzione
**Cos'è Internet?** Possiamo rispondere a questa domanda in due modi
- *descrizione degli ingranaggi*, ovvero i componenti hardware e software che lo compongono
- *descrizione in termini di infrastruttura di rete* che fornisce servizi ad applicazioni distribuite
## Gli ingranaggi di Internet
Internet è una rete di calcolatori che interconnette miliardi di dispositivi; questi ultimi, fino a qualche tempo fa erano PC tradizionali, worksation Linux e server che fornivano servizi, oggi invece comprendono molti più dispositivi (smartphone, tablet, TV, console ecc.).
Tutti i dispositivi connessi ad Internet sono detti **host** (o **sistemi periferici**) e sono tutti connessi tra di loro tramite una **rete di collegamenti** (fibra ottica, rame, radio, satellite) e **commutatori di pacchetti** (router, switch). Collegamenti diversi possono trasmettere dati a velocità diverse e la **velocità di trasmissione** viene misurata in bit/secondo (bps).

Internet è considerata una rete di reti, ovvero una serie di **ISP (Internet Service Provider)** interconnessi tra loro.
Due dispositivi, per comunicare, scambiano le loro informazione suddividendole in **pacchetti**, li inviano al destinatario e vengono riassemblati per ottenere i dati originari.
Gli host, i commutatori di pacchetti e le altre parti di Internet utilizzano dei *protocolli* per controllare l'invio e la ricezione di informazioni.

> Un **protocollo** definisce il *formato* e *l’ordine dei messaggi* scambiati tra due o più entità in comunicazione, così come le *azioni intraprese* in fase di trasmissione e/o di ricezione di un messaggio o di un altro evento.

## Descrizione dei servizi
Internet può essere descritto anche come un'infrastruttura che fornisce servizi alle applicazioni, come la posta elettronica, la navigazione sul web, la messaggistica ecc.
Queste applicazioni sono dette **applicazioni distribuite**, in quanto coinvolgono più host che si scambiano dati. Le applicazioni Internet vengono eseguite sui **sistemi periferici** e non sui commutatori di pacchetto.
I sistemi periferici forniscono un'**interfaccia socket** che specifica come il programma possa chiedere ad Internet di recapitare dati a un programma eseguito su un altro sistema periferico


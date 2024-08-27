Mentre il livello di rete si occupa dell'instradamento dei pacchetti lungo tutto il percorso end-to-end, il **livello di collegamento** si occupa di trasferire i datagrammi da un nodo (indicheremo con questo termine sia gli *host* che i *router*) a quello fisicamente adiacente lungo il collegamento.

![[SOR/RETI/img/img100.png|center|500]]

Nella figura vediamo come un host wireless voglia inviare un datagramma a uno dei server. Per effettuare questa operazione il datagramma deve attraversare sei collegamenti diversificati tra WiFi (primo link) ed Ethernet. 
Per ogni collegamento, il nodo trasmittente incapsula il datagramma in un **frame del livello di collegamento**.

# Servizi del livello di collegamento
Il servizio base del livello di collegamento è quello del trasporto di datagrammi tra nodi adiacenti lungo un singolo canale di comunicazione. Inoltre i vari protocolli a livello di collegamento possono anche offrire diversi servizi:
- **framing**: la maggioranza dei protocolli incapsulano i datagrammi all'interno di frame prima di trasmetterli. I frame sono costituiti da un campo dati e un'intestazione.
- **accesso al collegamento**: un protocollo che controlla l'accesso al mezzo trasmissivo (protocollo di tipo **MAC, Medium Access Control**) specifica le regole con cui immettere i frame nel collegamento.
- **consegna affidabile**: questo servizio è spesso *utilizzato per i collegamenti soggetti a elevati tassi di errore*, ad esempio quelli wireless. L'idea è quello di correggere localmente l'errore, piuttosto che ritrasmettere i dati dalla sorgente alla destinazione. 
- **rilevazione e correzione degli errori**: gli errori nei bit sono causati dall'attenuazione di segnale e dai disturbi elettromagnetici. Per far sì che il trasferimento sia affidabile, è necessario rilevare gli errori, perché non è utile inviare datagrammi con errori. 

# Dov'è implementato il livello di rete
Il livello di rete è implementato in un adattatore di rete, detto **scheda di rete (NIC)**. Il cuore della scheda di rete è il controller a livello di collegamento, un chip dedicato che implementa i servizi visti precedentemente.

![[SOR/RETI/img/img101.png|center|500]]

- Lato **mittente**: il controller prende un datagramma, lo incapsula in un frame aggiungendo i vari campi dell'intestazione (bit di controllo degli errori, trasferimento dati affidabile ecc.) e lo trasmette sul canale di comunicazione.
- Lato **destinatario**: il controller riceve l'intero frame, estrae il datagramma e lo consegna al livello di rete. Se il protocollo del livello di collegamento fornisce la rilevazione degli errori, allora il controller ricevente verifica la presenza degli errori

# Rilevazione e correzione degli errori
In figura vediamo uno scenario di rilevazione e correzione degli errori.

![[SOR/RETI/img/img102.png|center|500]]

Il nodo trasmittente, aggiunge dei bit $ECD$ ai dati $D$, che comprendono i datagrammi, ma anche l'intestazione del protocollo dello stato di collegamento. I dati D e i bit ECD sono inviati in un frame al nodo ricevente, che legge una sequenza $D^{'}$ ed $ECD^{'}$ che può essere diversa dall'originale, a causa di qualche errore di trasmissione. 
Il compito del ricevente è quello di determinare se $D^{'}=D$, potendo contare solo su $D^{'}$ e $ECD^{'}$.

Tuttavia anche con l'uso di ECD, c'è una possibilita che ci siano errori non rilevati, causando un invio di informazioni errate al livello di rete. In genere per limitare le probabilità che accadano questi eventi, ci sono tecniche più sofisticate che necessitano di calcoli più  complessi e la trasmissione di molti bit aggiuntivi.

Vedremo tre tecniche per rilevare errori nei dati trasmessi: 
- controllo di parità, per la base della rilevazione e correzione di errori
- tecniche di checksum, utilizzate nel livello di trasporto
- controllo a ridondanza ciclica, impiegate nelle schede di rete

## Controllo di parità
Questa è la forma più semplice di rilevamento degli errori, che utilizza un singolo **bit di parità**. 
Supponiamo che i dati $D$ da inviare siano costituite da $d$ bit. In uno schema di *parità pari*, si aggiunge un bit addizionale scegliendo il suo valore in modo da rendere pari il numero di bit 1 (includendo anche il bit di parità) nei bit trasmessi.  
In uno schema di *parità dispari* il valore del bit di parità è scelto in modo che ci sia un numero dispari di bit 1.

![[SOR/RETI/img/img103.png|center|500]]

Con un solo bit di parità, il ricevente deve semplicemente contare il numero di bit 1 tra quelli ricevuti. Se trova un numero dispari di bit 1, in uno scenario di parità pari, sa che si è verificato almeno un errore (o meglio un numero *dispari* di errori) in un bit.
Cosa succede se si verifica un numero pari di errori? In questo caso ci troviamo in presenza di un errore non rilevato. 

Se la probabilità di errori nei bit è bassa e si può assumere che gli errori siano indipendenti, allora l'eventualità di errori multipli in un singolo pacchetto è ridotta e un solo bit di parità è sufficiente. Ma nella realtà gli errori tendono a verificarsi a gruppi di bit consecutivi (*burst*) e la probabilità che non vengano rilevati errori a burst in un frame protetto da un solo bit si avvicina al 50%. A questo scopo è necessario adottare una strategia migliore per la rilevazione degli errori. 

![[SOR/RETI/img/img104.png|center|500]]

In figura osserviamo una generalizzazione bidimensionale dello schema di parità con un bit. In questo caso i $d$ bit del dato $D$ sono suddivise in $i$ righe e $j$ colonne, per ognuna delle quali viene calcolato un bit di parità (sia di riga che di colonna). 
I risultanti $i+j+1$ bit di parità contengono i bit per la rilevazione dell'errore del frame del link dati. 

![[SOR/RETI/img/img105.png|center|500]]

Supponiamo che si verifichi un solo errore nei $d$ bit originali (in figura a sinistra). Con questo schema di **parità bidimensionale** i bit di parità della colonna e della riga contenenti il bit errato, individueranno l'errore, permettendo al ricevente, oltre alla *rilevazione dell'errore*, di *identificare il bit alterato e correggerlo*. 

Nonostante questo, lo schema può solamente rilevare (ma non correggere) qualsiasi combinazione di due o più errori. *Il controllo di parità è adatto quando la probabilità di errori nei bit è bassa e gli errori sono indipendenti*

![[SOR/RETI/img/img106.png|center|500]]
![[SOR/RETI/img/img107.png|center|500]]
![[SOR/RETI/img/img108.png|center|500]]

## Checksum
Nelle tecniche che utilizzano il checksum i $d$ bit di dati sono trattati come una sequenza di numeri interi da $k$ bit. 
Il mittente suddivide i dati in sequenze da 16 bit, sommandole tra di loro in complemento a 1, invertendo tutti i bit del risultato finale, ottenendo la checksum, che invio al destinatario insieme al messaggio. Il destinatario esegue nuovamente la somma in complemento a 1, includendo anche la checksum. Se il risultato è costituito da 16 bit a 1, vuol dire che non sono stati rilevati errori, altrimenti si.

Il metodo di checksum fornisce una protezione agli errori deboli rispetto alle tecniche CRC, utilizzate nel livello di collegamento. La motivazione per cui il checksum è utilizzato a livello di trasporto è a causa dell'implementazione lato software che richiede una tecnica di rilevazione di errore semplice e veloce per non intaccare le prestazioni. 
Nello strato di collegamento, la rilevazione dell'errore è implementata direttamente nell'hardware, consentendo di effettuare rapidamente le operazioni per il controllo dell'errore.

## CRC, Controllo a Ridondanza Ciclica
Una tecnica largamente utilizzata è basata sui codici di controllo a ridondanza ciclica, anche detti codici polinomiali, in quanto la stringa dei bit può essere vista come un polinomio con coefficienti 0 e 1.

Per capire il funzionamento dei codici CRC consideriamo $d$ bit che costituiscono i dati $D$ da trasmettere, e che sorgente e destinatario si siano accordate su una stringa di $r+1$ bit, detta **generatore**, che indichiamo con $G$, in cui *è necessario che il bit più significativo sia 1*.

![[SOR/RETI/img/img109.png|center|500]]

Dato $D$, il mittente sceglie $r$ bit, detti $R$, e li concatena a $D$, ottenendo una stringa di lunghezza $d+r$ che sia divisibile esattamente per $G$ interpretato come numero. Il ricevente, per controllare la presenza di errori, effettua la divisione $(d+r)/G$ che *se ha resto diverso da 0 significa che si è verificato un errore*.

I calcoli di CRC sono eseguiti senza riporti o prestiti nelle addizioni e sottrazioni, rendendole praticamente operazioni identiche e che entrambe equivalgono allo XOR sui bit. Le moltiplicazioni e le divisioni sono eseguite in base 2, ma anche qui, le addizioni e le sottrazioni non hanno riporti. 

Dobbiamo capire come il trasmittente calcoli $R$, ricordando che dobbamo calcolarlo in modo che $D\times2^{r} \text{ XOR }R$ sia perferramente divisibile per $G$ (quindi deve essere G stesso o un suo multiplo) $$D\times2^{r} \text{ XOR }R=nG$$
Se eseguiamo lo XOR di R con entrambi i membri dell'espressione sopra otteniamo $$\begin{align*}
&D\times2^{r} \text{ XOR }R\text{ XOR }R=nG\text{ XOR }R=\\
&=D\times2^{r}\text{ XOR }(R\text{ XOR }R)=nG\text{ XOR }R=\\
&= D\times2^{r}\text{ XOR }0=nG\text{ XOR }R=\\
&= D\times2^{r}=nG\text{ XOR }R\\
\end{align*}$$
L'ultima uguaglianza ci dice che se dividiamo $D\times 2^R$ per $G$, il valore del resto è precisamente $R$, quindi possiamo calcolare $R$ come $$R=\text{resto di }\bigg(\frac{D\times2^{r}}{G}\bigg)$$

>**Es.**
>Vediamo il calcolo per $D=101110$, $d=6$, $G=1001$, $r=3$. I bit trasmessi in questo caso sono $\underbrace{101110}_\text{D}\:\underbrace{011}_\text{R}$.
>Il mittente calcola quindi $R$ prendendo il resto della divisione di $\frac{D\times2^{3}}{G}$, che vediamo in figura 
>![[SOR/RETI/img/img110.png|center|300]]

CRC può rilevare errori a burst inferiori a $r+1$ bit, quindi saranno rilevati gli errori con un burst di lunghezza $\leq r$. 

# Protocolli di accesso multiplo
Esistono due tipi di collegamento di rete: 
- il **collegamento punto punto**: singolo trasmittente e singolo ricevente alle estremità
- **collegamento broadcast**: si hanno più nodi e ciascun frame viene ricevuto da tutti i nodi
Nel caso di collegamento broadcast, avendo più host trasmettitori, bisogna capire come coordinare l'accesso al canale per evitare il verificarsi di *collisioni*, causando una perdita di frame. Il modo in cui gli host utilizzano il canale condiviso è dettato dai **protocolli di accesso multiplo**.

![[SOR/RETI/img/img111.png|center|500]]

I protocolli di accesso multiplo si suddividono in tre categorie: 
- **protocolli a suddivisione del canale**
- **protocolli ad accesso casuale**
- **protocolli a rotazione**
Idealmente un protocollo ad accesso multiplo per un canale con la velocità di $R$ bps dovrebbe avere le seguenti caratteristiche:
1. Quando un solo nodo deve inviare dati, dispone di un throughput di $R$ bps
2. Quando $M$ nodi devono inviare dati, questi dispongono di un throughput medio di $R/M$ bps
3. Il protocollo è decentralizzato, quindi non ci sono dei nodi principali che se non funzionano bloccherebbero l'intero sistema
4. deve essere semplice ed economico

## Protocolli a suddivisione del canale
Abbiamo già visto nell'introduzione che il [[Capitolo 0 - Introduzione ad Internet#Commutazione di circuito#TDM (Time Division Multiplexing)|multiplexing a divisione di tempo]] e quello a [[Capitolo 0 - Introduzione ad Internet#FDM (Frequency Division Multiplexing)|divisione di frequenza]], sono due tecniche utilizzate per suddividere la larghezza di banda di un canale broadcast.

Supponiamo che il canale broadcast supporti $N$ nodi e che abbia una velocità di $R$ bps. TDM suddivide il tempo in intervalli di tempo e poi divide ciascun intervallo in $N$ **slot temporali** e li assegna ad ogni nodo. Ogni volta che un nodo vuole trasmettere, lo fa durante lo slot di tempo ad esso assegnato. Le dimensioni del frame vengono scelte in modo tale da consentire la trasmessione di un singolo pacchetto. *Inoltre gli slot di tempo inutilizzati restano inutilizzati*. 
Il TDM elimina le collisioni ed ottiene una velocità di trasmissione dedicata di $R/N$ bps durante il frame di tempo, ha però degli svantaggi:
- un nodo, anche se è l'unico ha frame da inviare, trasmette alla velocità di $R/N$ bps.
- un nodo per trasmettere deve attendere il suo turno anche se è l'unico che ha frame da spedire

L'FDM, invece, suddivide il canale indiverse frequenze, assegnandone ognuna a ciascun nodo. FDM, come TDM, evita le collisioni e divide in modo equo la banda tra i nodi, ma anche in questo caso, quando il nodo è l'unico con un pacchetto da spedire, la sua banda è limitata a $R/N$.

## Protocolli ad accesso casuale
Con questa tipologia, quando un nodo ha un pacchetto da inviare, lo trasmette alla massima velocità consentita dal canale, senza alcun coordinamento. Questo significa che i nodi possono trasmettere contemporaneamente, consentendo quindi il *verificarsi di collisioni*, ma questi protocolli sono progettati per **rilevarle** e **recuperare la perdita** mediante le trasmissioni.

### Slotted ALOHA
Assumiamo che:
- tutti i frame sono di $L$ bit
- il tempo è suddiviso in slot di $L/R$ secondi 
- i nodi cominciano la trasmissione dei frame all'inizio degli slot
- i nodi sono sincronizzati in modo che tutti sappiano quando iniziano gli slot
- nel caso in cui in uno slot due o più frame collidono, tutti i nodi della rete rilevano l'evento prima della fine dello slot
Indichiamo con $p$ una probabilità, ovvero un numero tra 0 e 1. ALOHA opera nel seguente modo:
- quando un nodo ha un frame da spedire attende l'inizio del prossimo slot, in cui trasmette
- Se non si verifica una collisione, il nodo può predisporre l'invio di un nuovo frame
- Se si *verifica una collisione* il nodo ritrasmette il frame nello slot successivo con probabilità $p$ finché non ha successo

>**Oss.**
>"Ritrasmettere con probabilità $p$" è analogo a lanciare una moneta con l'evento testa corrispondente a "ritrasmetti" (probabilità $p$) e l'evento croce corrispondente a "salta lo slot e lancia di nuovo la moneta nello slot successivo" (probabilità $1-p$).

![[SOR/RETI/img/img112.png|center|500]]

Questo protocollo ha diversi vantaggi. Innanzitutto con un singolo nodo attivo, questo può *trasmettere continuamente alla massima velocità del canale*, è un protocollo semplice e decentralizzato, in quanto ciascun nodo rileva le collisioni e decide quando ritrasmettere. 
Gli svantaggi risiedono nel fatto che se si verificano le collisioni, si sprecano slot di tempo (vedere in figura) ed il meccanismo di randomizzazione può portare a slot inutilizzati. 

L'efficienza di un protocollo di accesso multiplo che utilizza gli slot temporali è la frazione di slot riusciti in presenza di un numero elevato di nodi attivi che hanno un grande numero di pacchetti da inviare.
L'efficienza massima dello slotted aloha è del 37% il che significa che la reale velocità di trasmissione è $0.37\cdot R$ bps. 

Esiste una variante di Slotted ALOHA, l'**ALOHA puro** che non richiede sincronizzazione. Infatti i nodi trasmettono non appena possono, e dopo la trasmissione, se si è verificata una collisione si ragiona allo stesso modo della versione slotted. Senza sincronizzazione l'efficienza dell'ALOHA puro scende al 18%.

## CSMA: accesso multiplo con rilevamento della portante
Nei protocolli ALOHA i nodi prendono la decisione di trasmettere indipendentemente dall'attività degli altri nodi, infatti i nodi non tengono conto della presenza di un altro nodo in trasmissione e non arrestano la trasmissione se interferisce con quella di un altro nodo. 

Nel caso di protocolli di tipo **CSMA e CSMA/CD** valgono due importanti regole:
- **rilevamento della portante**: consiste nel porre un nodo in ascolto sul canale prima di trasmettere. Se il canale sta trasmettendo, il nodo si mette in attesa.
- **rilevamento della collisione**: il nodo che sta trasmettendo rimane contemporaneamente in ascolto del canale. Se c'è un nodo che inizia il trasferimento interferendo la comunicazione, il nodo in ascolto termina la propria trasmissione, aspetta del tempo e ripete il processo.

Nonostante il rilevamento della portante, nel CSMA le collisioni possono comunque verificarsi. Questo è causato dal ritardo di propagazione dei bit nel canale da parte dei nodi. 

>**Es.**
>Supponiamo che nell'istante $t_{0}$ il nodo B rileva che il canale è inattivo, inizia la sua trasmissioni e i bit si propagano nel mezzo. Per la propagazione dei bit è necessario un intervallo di tempo non nullo. Al tempo $t_{1}>t_{0}$ il nodo D ha un frame pronto da spedire e nonostante B stia ancora trasmettendo, i bit, per il **ritardo di propagazione**, non hanno ancora raggiunto D che quindi può ritenere il canale libero. 
> 
>![[SOR/RETI/img/img113.png|center|300]]

Il verificarsi di collisioni nel CSMA comporta lo spreco dell'intero tempo di trasmissione dei pacchetti. 
### CSMA/CD
Il CSMA/CD riduce la quantità di tempo sprecata nelle collisioni, infatti quando un nodo rileva una collisione, cessa immediatamente la trasmissione. 

Vediamo però prima di tutto le operazioni dal punto di vista di una scheda di rete collegata ad un canale broadcast:
1. La scheda ottiene un datagramma dal livello di rete, prepara un frame a livello di collegamento e lo pone in un buffer
2. Se il canale è libero (rileva che non c'è energia in entrata nella scheda), inizia la trasmissione. Se il canale è occupato allora resta in attesa 
3. Durante la trasmissione verifica la presenza di eventuali segnali provenienti dalle altre schede di rete presenti sul canale (controllo della collisione)
4. Se non rileva energia di segnale allora la trasmissione è stata eseguita con successo. Al contrario interrompe immediatamente la trasmissione del frame
5. Dopo aver annullato la trasmissione, la scheda di rete aspetta un tempo casuale e torna al passo 2
Resta da chiarire qual è il tempo di attesa casuale opportuno da scegliere, detto **tempo di backoff**.
Se il numero di nodi che collidono è piccolo e l'intervallo è grande, allora i nodi rimarranno fermi per troppo tempo prima di ripetere la trasmissione. Se il numero di nodi che collidono è grande e l'intervallo di tempo è piccolo allora la probabilità di collisioni aumenta. 
L'algoritmo di **binary exponential backoff** risolve questo problema. Quano il trasmettitore riceve l'$n-$esima collisione durante la trasmissione, stabilisce casualmente un valore $K$ tra $\{0,1,2,\dots,2^{n}-1\}$. Più è alto il numero di collisioni e maggiore sarà l'intervallo da cui scegliere $K$.

![[SOR/RETI/img/img114.png|center|500]]

## Protocolli a rotazione
Ricordiamo le proprietà ideali di questi protocolli:
1. quando un solo nodo è attivo esso ha un throughput di $R$ bps
2. quando sono attivi $M$ nodi, ciascuno ha un throughput di $R/M$ bps
I protocolli ALOHA e CSMA hanno la prima proprità, ma non la seconda. 
Questo ha portato allo sviluppo dei **protocolli a rotazione**.

### Polling
In questo protocollo è presente un *nodo principale*, che invia ciclicamente un messaggio ad ogni nodo, indicandogli che può trasmettere nel canale. Il nodo principale rileva che un nodo ha terminato di inviare osservando la mancanza di segnale sul canale.  
Il polling elimina le collisioni e gli slot vuoti, ma presenta svariati svantaggi:
- **ritardo di polling**: se c'è un solo nodo attivo, questo trasmetterà ad un tasso inferiore agli $R$ bps, in quanto il nodo principale, quando l'unico nodo attivo ha finito l'invio, deve comunque interrogare tutti gli altri nodi.
- **singolo punto di rottura**
### Token-passing
In questo caso non esiste un *nodo principale*, ma un **token**, ovvero un messaggio di controllo che circola tra i nodi con un ordine prefissato. 
Se il nodo che riceve il token non ha pacchetti da inviare, lo inoltra al nodo successivo, altrimenti trasmette il numero di frame consentito, per poi inoltrare il token. 
Anche questo protocollo non è privo di problemi:
- **singolo punto di rottura**


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

![[Pasted image 20240825160024.png|center|500]]

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
>![[Pasted image 20240825170311.png|center|300]]

CRC può rilevare errori a burst inferiori a $r+1$ bit, quindi saranno rilevati gli errori con un burst di lunghezza $\leq r$. 

# Protocolli di accesso multiplo
Esistono due tipi di collegamento di rete: 
- il **collegamento punto punto**
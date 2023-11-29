La memoria RAM è una risorsa che va gestita in maniera efficace. In questo capitolo studieremo come i sistemi operativi creano astrazioni della memoria  e come lo gestiscono.

Il desiderio è quello di avere una memoria privata, grande e veloce, non volatile e a basso costo. Questo purtroppo non è possibile, per ora.
Nel corso degli anni si è creata una **gerarchia della memoria**, infatti i computer possono avere pochi megabyte di memoria molto veloce, costosa e volatile, qualche gigabyte di memoria abbastanza veloce, volatile e di medio costo e qualche terabyte di memoria su disco non volatile, lenta e a basso costo. 
Il compito del sistema operativo è quella di astrarre questa gerarchia e gestire l'astrazione. 

La parte del SO che gestisce la gerarchia della memoria è chiamata **gestore della memoria** ed ha il compito di tenere traccia delle locazioni di memoria in utilizzo, allocare memoria ai processi che la richiedono e liberarla a lavoro ultimato. 

# Astrazione della memoria
## Nessuna astrazione
La più semplice astrazione della memoria è l'assenza di astrazione. I primi computer (1960 ca.) non avevano astrazione della memoria. Quando un programma eseguiva un'istruzione come `MOV REGISTER1, 1000` il computer spostava il contenuto della locazione di memoria fisica 1000 dentro REGISTER1. Il modello di memoria era quindi quello della memoria fisica: un insieme di indirizzi da 0 ad un massimo, ogni indirizzo corrispondente ad una cella di 8 bit. 

In queste condizioni non era possibile eseguire due programmi in memoria nello stesso tempo, perché se un programma avesse scritto un nuovo valore in posizione 2000, avrebbe cancellato qualunque valore ci avesse scritto il secondo programma. 
Con questo modello esistono tre diverse possibilità, che vediamo sotto in figura

![[SOR1.5/img/img7.png|center|500]]

Il SO può trovarsi sul fondo della memoria nella *RAM* (mainframe e minicomputer), in cima alla memoria nella *ROM* (sistemi embedded) oppure posizionare i driver in cima alla memoria nella *ROM* e il resto del sistema nella *RAM* in fondo alla memoria.
Il terzo modello è stato usato nei primi PC dove la parte di sistema nella ROM è chiamata *BIOS*. 
Il primo e il terzo modello hanno lo svantaggio che un difetto nel programma utente può cancellare il sistema operativo.
### Multiprogrammazione
Anche senza astrazione della memoria è possibile eseguire più programmi contemporaneamente. Il compito del SO è quello di salvare l'intero contenuto della memoria in un file su disco ed eseguire il programma succesivo. Questo metodo è detto **"swapping"**. 

![[SOR1.5/img/img8.png|center|500]]

Questa soluzione ha un'inconveniente importante descritto in figura: in memoria sono caricati consecutivamente due programmi senza astrazione dell'indirizzo, un programma da 16 KB che inizia con l'istruzione `JMP 24` ed un altro programma da 16 KB che inizia con l'istruzione `JMP 28`. L'esecuzione singola dei due programmi non crea problemi, ma al momento dell'esecuzione consecutiva, il primo programma esegue la sua istruzione `JMP 24` senza problemi, mentre al momento dell'esecuzione del secondo programma l'istruzione `JMP 28` salta all'istruzione `ADD` del primo programma (linea 28), causando un probabile errore. 
Questo perché i due programmi fanno riferimento alla memoria fisica assoluta, cosa che non vogliamo perché questo può causare problemi come la distruzione del sistema operativo o la difficoltà nell'esecuzione contemporanea di più programmi.

## Spazi degli indirizzi
La soluzione adotta l'astrazione della memoria per separare e proteggere i programmi in esecuzione. Questa soluzione sfrutta il concetto di **spazio degli indirizzi**, ovvero l'insieme degli indirizzi che un processo può usare per indirizzare la memoria; questo spazio è indipendente dagli altri processi e rappresente una forma di astrazione di memoria.

Una soluzione, ora caduta in disuso, mappava lo spazio degli indirizzi di ogni processo in parti diverse della memoria fisica. La CPU veniva equipaggiata di due registri hardware speciali chiamati **registro base** e **registro limite**, il primo contiene l'indirizzo fisico di un programma in memoria, mentre il secondo contiene la lunghezza del programma.

Ogni volta che un processo accede alla memoria, prima di inviare l'indirizzo sul bus di memoria, la CPU aggiunge il valore del registro base all'indirizzo generato tramite il processo e contemporaneamente controlla se l'indirizzo risultante sia uguale o maggiore del valore nel registro limite, nel cui caso è generato un errore e viene interrotto l'accesso. Il registro base mette quindi in atto la *rilocazione dinamica*, mentre il registro limite applica la *protezione*.

![[SOR1.5/img/img9.png|center|500]]

Il vantaggio di questo metodo è quello di offrire ad ogni processo uno spazio degli indirizzi separato e protetto, ma lo svantaggio è la necessità di eseguire somme e confronti ad ogni accesso alla memoria, causando un rallentamento delle operazioni.

Un tipico sistema operativo Linux o Windows all'avvio può caricare in memoria oltre 40 processi. Un'applicazione come Photoshop possono occupare oltre 500 MB solo per avviarsi, di conseguenza tenere tutti i processi in memoria per tutto il tempo, richiede un'enorme quantità di memoria e non può essere fatto in assenza di memoria sufficiente. 

Per gestire il sovraccarico di memoria sono stati sviluppati degli approcci differenti:
- **Swapping**: consiste nello spostamento di processi tra la memoria RAM e la memoria non volatile, con i processi inattivi archiviati su quest'ultima.
- **Memoria virtuale**: permette l'esecuzione dei programmi anche se sono parzialmente presenti nella memoria principale.

## Swapping
In figura viene illustrato il funzionamento di un sistema di swapping.

![[SOR1.5/img/img10.png|center|700]]

All'inizio solo il processo A è in memoria, in seguito vengono creati o viene fatto lo *swapping* dal disco dei processi B e C. In (d) viene eseguito lo swapping su disco di A e caricato D ed esce B, per poi ricaricare A (g). Siccome A è in una posizione diversa, i suoi indirizzi devono essere rilocati dal software quando viene eseguito lo swapping. 

Lo swapping può portare alla frammentazione della memoria, perciò è necessario ricompattarla, spostando i processi il più in basso possibile. Questa tecnica è detta **memory compaction**, ma di solito non viene eseguita perché richiede molto tempo di CPU. Per esempio su una macchina da 16 GB che copia 8 byte in 8 ns, servono 16 secondi per compattare la memoria.

Vale la pena sottolineare quanta memoria dovrebbe essere allocata per un processo quando viene creato o quando ne viene fatto lo swapping dal disco. Se i processi sono creati con una dimensione fissa, l'allocazione è semplice, ma se ci si aspetta che i segmenti dei dati possono crescere, ad esempio allocando memoria dinamicamente dallo heap, un'idea è quella di allocare un pò di memoria extra ad ogni swapping 

![[SOR1.5/img/img11.png|center|700]]

Nel caso in cui i processi abbiano due segmenti che crescono, come (b) in foto in cui i processi A e B hanno il segmento dati e il segmento stack, si propone una sistemazione alternativa: ogni processo ha uno stack in cima alla sua memoria allocata che cresce *verso il basso* e il segmento dati oltre il testo del programma che cresce vesro l'alto. La memoria in mezzo a loro può essere usata da ciascun segmento. 
Nel caso in cui la memoria venga esaurita viene eseguito:
- il trasferimento del processo
- lo swapping 
- la terminazione del processo

# Gestione della memoria libera
Quando la memoria è assegnata dinamicamente, il sistema deve gestirla. Ci sono due metodi per tener traccia dell'utilizzo della memoria: 
- *bitmap* che tiene traccia di quali blocchi vengono allocati 
- una *lista* collegata che tiene traccia della memoria non allocata
Questo tracciamento non riguarda solo la memoria, ma anche risorse come i file system

## Bitmap
La memoria, con una bitmap, è divisa in unità di allocazione, a cui corrisponde un bit della bitmap: 0 se l'unità è libera, 1 se l'unità è utilizzata. Più piccola è l'unità di allocazione, maggiore è la bitmap. Se si sceglie un'unità di allocazione grande, la bitmap sarà più piccola, ma una quantità di memoria potrebbe essere sprecata nell'ultima unità del processo, se la dimensione del processo non è esattamente un multiplo dell'unità di allocazione.
Una bitmap fornisce un metodo molto semplice per tener traccia di parole di memoria in una quantità fissa di memoria. 
Lo svantaggio della bitmap è che per poter eseguire un processo di $k$ unità in memoria, il gestore della memoria deve cercare nella bitmap una serie di $k$ bit 0 consecutivi, il che è molto molto lento.

![[SOR1.5/img/img12.png|center|900]]

## Linked list
Utilizzare delle liste di segmenti di memoria allocati e liberi, in cui un segmento o contiene un processo o è uno spazio vuoto fra due processi. Ogni voce della lista specifica uno spazio vuoto o un processo, l'indirizzo da cui parte, la lunghezza e il puntatore alla voce successiva.
Nella pratica viene usata una doppia linked list, infatti un processo che finisce ha due vicini (fatta eccezione per quelli all'inizio della lista e alla fine) e con due puntatori è più facile trovare la voce precedente e vedere se è possibile l'unione. L'utilizzo di doppie linked list porta a 4 situazioni, che vediamo in figura.

![[SOR1.5/img/img13.png|center|600]]

## Schemi di allocazione della memoria
Quando processi e spazi vuoti sono tenuti su una lista ordinata per indirizzo, molti algoritmi possono essere usati per allocare la memoria per un processo creato.
Esistono diversi algoritmi:
- *FirstFit*: il gestore della memoria scorre la lista dei segmenti finchè non trova uno spazio vuoto abbastanza grande
- *NextFit*: lavora allo stesso modo di FirstFit, ma tiene traccia di ogni posto dove ha tovato uno spazio adatto. Quando riparte, cerca nella lista dal punto dove era rimasto l'ultima volta, invece ri ripartire dall'inizio
- *BestFit*: cerca all'interno di una lista, dall'inizio alla fine, uno spazio che sia il più piccolo possibile ma comunque adatto, piuttosto che impossessarsi di uno spazio grande che potrebbe essere necessario in seguito
- *WorstFit*: quello meno utilizzato e meno ottimizzato, prende lo spazio più grande ogni volta
- *QuickFit*: mantiene delle liste divise per alcune delle dimensioni più comuni richieste. Con questo algoritmo la ricerca degli spazi è molto veloce, ma presenta lo stesso svantagio di tutti gli schemi ordinati per dimensione, infatti, quando un processo finisce o fa lo swapping su disco, trovare i suoi vicini per vedere se sia possibile unirlo a loro è dispendioso.

### Buddy Allocation
Il principale meccanismo per l'allocazione della memoria in Linux è basato sull'algoritmo di *Buddy Memory Allocation*, che opera nel seguente modo: inizialmente la memoria consiste di un singolo pezzo. All'arrivo di una richiesta di memoria, questa viene arrotondata ad una potenza di 2 e l'intero pezzo di memoria viene diviso a metà. Se ci sono pezzi ancora troppo grandi, il più basso viene diviso ancora finché non è della dimensione richiesta.

![[SOR1.5/img/img14.png|center|500]]

In figura vediamo un esempio di come opera l'algoritmo di Buddy Memory: 
- la memoria inizialmente di 64 pagine (a);
- Arriva una richiesta di 7 pagine, che viene arrotondata alla potenza di 2 più vicina, quindi 8;
- L'intero pezzo di memoria viene diviso a metà (b);
- Siccome uno spazio di 32 pagine per una richiesta di 8 è ancora troppo grande, viene diviso a metà lo spazio più basso (c) e viene ripetuta la divisione (d)
- Ora abbiamo un pezzo di memoria della dimensione corretta che viene allocata.

![[SOR1.5/img/img15.png|center|500]]

- In seguito arriva una richiesta di 8 pagine, che viene immediatamente soddisfatta (e);
- Arriva una richiesta di 4 pagine, il pezzo più piccolo disponibile viene diviso a metà (f) e diviso di nuovo per ottenere due spazi da 4 (g), che viene allocato.
- Il secondo pezzo di 8 pagine viene rilasciato (h) e in seguito anche il primo pezzo. Poiché i due pezzi di otto pagine adiacenti vengono dallo stesso pezzo di 16 pagine, vengono riuniti per ottenerlo (i).

Il BuddyAlgorithm però può causare frammentazione interna dato che, se siu necessita di unn pezzo costituito da 65 pagine, si deve richiedere un pezzo da 128 pagine.
Per risolvere questo problema, Linux utilizza *l'allocatore a slab*, che prende i pezzi usando l'algoritmo Buddy, ma poi da questi ritaglia gli *slab* e li gestisce separatamente.
Poichè il kernel crea e distrugge oggetti di un certo tipo, si affida alle cosiddette *cache degli oggetti*, contenenti puntatori a uno o più slab capaci di memorizzare un certo numero di oggetti dello stesso tipo. Ogni slab può essere pieno, parzialmente pieno, o vuoto. Quando un oggetto viene deallocato, non viene immediatamente restituito al sistema come memoria libera, ma rimane nella cache, in modo che in caso di una nuova richiesta dello stesso tipo di oggetto, possa essere immediatamente reallocata evitando ritardi dovuti alla reinizializzazione. 

![[SOR1.5/img/img16.png|center|700]]

Lo slab in foto contiene: 
- un puntatore all'inizio della memoria con gli slot degli oggetti (freccia start)
- un puntatore all'indice del prossimo slot libero
- *bufctl*, un array di indici dei prossimi slot liberi
- gli slot per gli oggetti

# Memoria Virtuale
L'idea della **memoria virtuale** nasce dalla gestione del software bloat, poiché le dimensioni dei software aumentano ancora più velocemente rispetto alla memoria disponibile. Lo [[#Swapping|swapping]] non era una soluzione efficace,a causa delle limitazioni nella velocità dei dischi.
Negli anni '60 furono introdotte delle tecnice come l'*overlay*, che divideva i programmi in piccoli pezzi e solo l'overlay necessario era caricato in memoria. Questa soluzione non trovò successo, perché la suddivisione del programma andava fatta manualmente e questo era un processo lento, noioso e poteva portare errori.

La memoria virtuale si basa sull'idea che ogni programma abbia il proprio spazio di indirizzi personale, suddiviso in *pagine*, un intervallo contiguo di indirizzi. Ogni pagina è mappata sulla memoria fisica, ma non è necessario che siano tutte presenti sulla memoria fisica per eseguire il programma. Quando un programma fa riferimento a una parte del suo spazio di indirizzi non presente in memoria fisica, l'hardware esegue il mappaggio necessario diretto e il SO si occupa di recuperare il pezzo mancate e rieseguire l'operazione fallita in precedenza.
In questo modo, l'intero spazio degli inidirizzi può essere rimappato sulla memoria fisica in unità più piccole, consentendo una gestione più efficiente dei programmi.

## Paginazione
La maggior parte dei sistemi di memoria virtuale usa una tecnica chiamata **paginazione** o **paging**. Su qualsiasi computer i programmi referenziano un insieme di indirizzi di memoria. Gli indirizzi possono essere creati dall'indicizzazione, registri base, registri segmento ed altri modi.
Questi indirizzi creati dal programma vengono chiamati **indirizzi virtuali** e vanno a formare lo **spazio virtuale degli indirizzi**.
Nei computer senza memoria virtuale, l'indirizzo virtuale viene situato direttamente nel bus, provocando la lettura o scrittura della parola della memoria fisica con lo stesso indirizzo.
Quando invece la memoria virtuale viene utilizzata, gli indirizzi virtuali non vanno direttamente nel bus, ma nella **MMU** (memory managment unit) che mappa gli indirizzi virtuali con spazi della memoria fisica.
Lo spazio  degli indirizzi virtuali e' suddiviso in unita' di dimensioni fissa, chiamate **pagine**. Le unita' corrispondenti nella memoria fisica sono chiamate **frame** o **page frame**.
Le pagine e i frame sono generalmente della stessa dimensione. 
Nei sistemi reali le pagine vanno dai 512 byte fino a 64 KB. 
Con 64 KB di spazio degli indirizzi virtuale e 32 KB di memoria fisica otteniamo 16 pagine virtuali e 8 frame. I trasferimenti tra la RAM e il disco sono sempre pagine intere.
Ogni pagina contiene esattamente 4096 indirizzi da un multiplo di 4096 a un multiplo di 4096 successivo.
>Ex1: 
>Quando un programma prova ad accedere all'indirizzo 0 usando:
>`MOV REG, 0`
>l'indirizzo virtuale 0 e' inviato alla MMU. La MMU verifica che questo indirizzo si trova nella pagina 0, che secondo il mappaggio, e' il frame 2.
>Trasforma cosi' l'indirizzo in 8192 e lo invia esternamente sul bus. Dato che la memoria non conosce l'MMU, lei ha semplicemente eseguito una richiesta per leggere o scrivere l'indirizzo 8192. Cosi' l'MMU ha mappato tutti gli indirizzi virtuali fra 0 e 4095 sugli indirizzi fisici tra 8192 a 12287.

>Ex2:
>L'istruzione:
>`MOV REG, 8192`
>viene trasformata in:
>`MOV REG, 24576`
>dato che l'indirizzo virtuale 8192 e' mappato sul 24576.

Ma, che cosa dovesse accadere se il programma referenziasse degli indirizzi non mappati usando:
`MOV REG, 32780`
che e' di 12 byte all'interno di una pagina virtuale da 8?
In questo caso, l'MMU rileva che la pagina non e' mappata e causa un **trap** della CPU verso il SO. Questo trap e' comunemente chiamato **page fault**.
Il SO preleva un frame poco utilizzato e ne scrive il suo contenuto su disco, poi prende la pagina appena referenziata e la mette nel frame appena liberato, cambia la mappa e riavvia l'istruzione che era in trap.

## MMU
La **Memory Managment Unit** è una classe di componenti hardware che gestisce le richieste di acesso di memoria da parte della CPU. 
>Ex:
>Indirizzo virtuale = 8196 (0010000000000100)
>Mappato usando la mappa della MMU.
>L'inidrizzo virtuale di 16 bit in ingresso è suddiviso in un numero di pagina di 4 bit e un offset di 12 bit.
>Con 4 bit per il numero di pagina, si possono avere 16 pagine e con 12 bit di offset possiamo indirizzare 4096 byte per pagina.
>Il numero di pagina è usato come indice nella **Tabella delle pagine** che porta al numero di frame corrispondente alla pagina virtuale. Se il bit *presente / assente* è 0, avviene un trap al SO. Se il bit è 1, il numero di frame trovato nella tabella delle pagine viene copiato nei tre bit più significativi del registro di output, insieme all'offset di 12 bit che è copiato senza modifiche dall'inidirizzo virtuale in arrivo. Insieme formano un indirizzo fisico di 15 bit. Il registro di output è poi messo sul bus di memoria come indirizzo fisico di memoria

![[SOR1.5/img/img17.png|center|700]]

## Tabelle delle pagine

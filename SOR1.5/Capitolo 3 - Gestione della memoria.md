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

Vale la pena sottolineare quanta memoria dovrebbe essere allocata per un processo quando viene creato o quando ne viene fatto lo swapping dal disco. Se i processi sono creati con una dimensione fissa, l'allocazione è semplice, ma se ci si aspetta che i segmenti dei dati possono crescere, ad esempio allocando memoria dinamicamente dallo heap, un'idea è quella di allocare un po' di memoria extra ad ogni swapping 

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
La memoria, con una bitmap, e' divisa in unita' di allocazione piccole come ualche parola o grandi come molti KB. A ogni unita' di allocazione, corrisponde un bit della bitmap, se 0, l'unita' e' libera, se 1 l'unita' e' utilizzata
Una cosa molto importante e' l'unita' di allocazione , piu' piccola e', maggiore e' la bitmap.
Una memoria di $32^n$ bit usera' $n$ bit di mappa. Cosi' la bitmap occupera' solo $1/32$  della memoria.

# Processi
Fondamentalmente un processo è un programma in esecuzione. Associato ad ogni processo c'è uno spazio degli indirizzi ovvero un elenco di locazione di memoria da 0 a un certo massimo, che il processore può eseguire operazioni di lettura e scrittura. Questo spazio contine il file eseguibile, i dati del rpogramma e lo stack. Associato al processo c'è anche un insieme di risorse che comprende i registri, un elenco di file aperti, gli allarmi in sospeso, un elenco di processi relativi e tutte le informazioni per far girare il programma. Quindi il **processo** è una sorta di contenitore che racchiude tutte le informazioni per far girare un programma.
Periodicamente il *SO* decide di fermare un processo e avviarne un altro, questo succede per esempio quando il processo stoppato ha consumato il suo tempo di CPU. Quando questo accade, il processo stoppato successivamente deve essere riavviato nel medesimo stato di quando è stato fermato. Questo significa che tutte le informazioni elaborate da quel processo fino a quel momento devono essere salvate da qualche parte durante la sospensione. 

>*Ex*: il processo potrebbe aver aperto più file per leggerli.

In tal caso ad ognuno di quei file c'è un puntatore associato che dà la posizione attuale. Quindi quando un processo viene temporaneamente sospeso, i puntatori devono essere salvati, così che dopo una chiamata di *read* eseguita dopo il riavvio leggerà i dati corretti.
In molti SO tutta l'informazione riguardante ciascun processo, diversa dai contenuti del suo spazio degli indirizzi, è salvata nella **tabella di processo** che è un array di strutture, una per ogni processo in essere.
Un processo sospeso quindi consiste del suo spazio degli indirizzi, chiamato **immagine core** e del suo elemento nella tabella di processo, insieme al contenuto dei registri e molti altri oggetti che servono per avviare il processo successivamente.
Le chiamate chiave del sistema di gestione del processo sono quelle che hanno a che fare con la creazione e chiusura dei processi.

>*Ex*: Consideriamo un processo chiamato **shell** che legge i comandi da terminale. 

L'utente ha appena digitato un comando che richiede la compilazione di un programma. Quando quel processo ha terminato la compilazione, esegue una chiamata di sistema per terminare se stesso.
Se un processo può creare uno o più processi e questi a loro volta possono creare altri processi, si arriva velocemnte ad una struttura ad albero. I processi relazionati che cooperano per realizzare un lavoro spesso necessitano di comunicazione tra loro e sincronizzare le attività.
Un processo ha a disposizione altre chiamate di sistema per richiedere più memoria, per farlo deve attendere che termini un processo figlio o sovrapporre i programmi. Esiste la possibilità di convogliare un'informazione a un processo in esecuzione senza che quest ultimo sia in attesa

>*Ex*: quando un processo sta comunicando con un altro su un diverso computer, inviandogli messaggi tramite rete. Per tutelarsi nell'eventualità che il messaggio si perda, il mittente può richiedere che il suo sistema operativo ne notifichi la ricezione dopo un certo numero di secondi.

Trascorso un certo numero di secondi, il *SO* invierà un segnale di allarme al processo. Il signale comporta che il processo sospenda temporaneamente qualunque operazione in corso, salvi i registri nello stack ed esegua una procedura di gestione dei segnali speciali.
Quando il gestore dei messaggi ha terminato il processo stoppato riprende l'esecuzione al momento del segnale. I segnali sono l'analogo degli *interrupt* hardware e possono essere generati da una varietà di cause, oltre che dalla scadenza dei timer. Molti trap come un'istruzione illegale o un indirizzo non valido sono convertiti in segnali per il processo responsabile.
A ogni persona autorizzata ad utilizzare il computer viene assegnato uno **UID** (user identification) dall'account root. Ogni processo che viene runnato ha l'UID dello user che lo ha runnato. Un processo figlio ha lo stesso UID del padre. Gli utenti possono appartenere a gruppi, ognuno dei quali ha lo stesso **GID** (group identification)

# Spazi degli indirizzi
Ogni computer ha una memoria principale che viene impiegata per il mantenimento dei programmi in esecuzione.

>*Ex*: In un SO semplice può stare in memoria al massimo un programma per volta. Per eseguire un secondo programma, il primo deve essere interrotto e il secondo messo in memoria

SO più avanzati consentono l'esecuzione in memoria di più programmi contemporaneamente. Per far si che questo avvenga e per evitare che più programmi interferiscano tra loro, c'è bisogno di un meccanismo di protezione risiedente nella parte hardware, ma che comunque verrà controllato dal SO.

Un problema distinto ma che comunque riguarda la memoria, è, la gestione dello spazio degli indirizzi nei processi.
Normalmente ogni processo dispone di uno spazio prefissato di indirizzi di memoria, generalemente da 0 a un certo massimo. Generalmente la quantità di spazio disposta dagli indirizzi è minore del totale della memoria principale.
Così facendo, anche se un processo riempie il proprio spazio di indirizzi, ci sarà ancora spazio per poter eseguire altri processi e non riempire la memoria.
Tuttavia gli indirizzi sono di 32 o 64 byte, ciò vuol dire che lo spazio degli indirizzi si aggira all'incirca o a $2^{32}$ o $2^{64}$ byte. 

>[!warning]- Più pazio degli indirizzi rispetto alla memoria
>Se un processo ha più spazio degli indirizzi rispetto alla memoria principale oggi non sarebbe un problema così grande, ma nei primi computer non avrebbe avuto chance.

 Oggi questo problema è affrontabile tramite una tecnica denominata **memoria virtuale** che consente al sistema operativo di tener parte dello spazio degli indirizzi nella memoria e parte sul disco, così che è possibile scambiare pezzi avanti e indietro secondo la necessità

>[!important]- Astrazione
>Essenzialmente il SO crea un'astrazione di uno spazio degli indirizzi come se fosse un insieme di indirizzi a cui un processo fa riferimento.

Lo spazio degli indirizzi è separato dalla memoria fisica della macchina e può essere sia più grande che più piccolo della memoria fisica.

# File
Un concetto chiave di ogni SO è il file system. Per creare, cancellare, leggere e scrivere file sono necessarie chiamate di sistema.

>*Ex*: Prima che un file possa essere letto, bisogna localizzarlo sul disco; dopo che è stato letto occorre chiuderlo, quindi le chiamate sono funzionali a questo scopo.

Per storare i file, molti SO considerano le **directory** come metodo per raggrupparli. Le voci di una directory possono essere file o altre directory. Questo modello dà luogo a una gerarchia chiamata **File System**. 

Le gerarchie del processo e dei file sono organizzate come alberi ma la loro affinità va oltre questo.
Le gerarchie del processo hanno generalmente vita breve, al massimo qualche minuto, mentre la gerarchia della directory è il contrario, la vita può esistere anche anni. Anche la proprietà e la protezione differiscono per processi e file, generalmente solo un processo padre può accedere al processo figlio, ma esistono meccanismi che ignorano questa gerarchia per permettere che file e directory siano letti da un gruppo più ampio del solo proprietario.






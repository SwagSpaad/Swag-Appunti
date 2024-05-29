# Traduzione vs Interpretazione
- **Traduzione**: sostituire, in fase iniziale, ogni istruzione del programma scritto in $L_{1}$ con una sequenza di istruzioni equivalente in $L_{0}$. Il programma quindi è costituito interamente da istruzioni in $L_{0}$ e può essere eseguito dal computer al posto del programma in $L_{1}$ 
- **Interpretazione**: scrivere un programma in $L_{0}$ che accetta come dati d'ingresso dei programmi in $L_{1}$. Questo programma esegue il programma in $L_1$ esaminando un'istruzione per volta e sostituendola direttamente con l'equivalente istruzione in $L_{0}$. Il programma in $L_{0}$ è detto *interprete*

Nella traduzione il programma viene convertito interamente in un programma in $L_{0}$ (compilazione)
Nell'interpretazione ogni istruzione viene sostituita e non viene generato nessun altro file, ma l'esecuzione di un programma interpretato è più lenta, perché deve essere effettuata ogni volta

# Livelli di un computer

![[AE/img/img1.png|center|400]]

- Livello 0: hardware e porte logiche
- Livello 1 **microarchitettura**: memoria locale formata da registri, ALU (esegue operazioni). Registri connessi alla ALU per formare percorso dati (selezionare uno o due registri, permettere alla ALU di eseguire operazioni su di loro e memorizzare il risultato). Il *microprogramma* controlla le operazioni del percorso dati
- Livello 2: sono le istruzioni eseguite dall'architettura della macchina interpretate dal microprogramma
- Livello 3: vengono introdotte istruzioni aggiuntive rispetto al livello 2, diversa organizzazione della memoria e capacità di esecuzione concorrente. Tutto questo è eseguito da un interprete storicamente chiamato sistema operativo. Le istruzioni identiche a quelle del livello 2 sono eseguite da microprogrammi e non dal sistema operativo.
- Livello 4: consente di scrivere programmi per i livello 1, 2 e 3 in modo più agevole. Traduce i programmi nei linguaggi dei livelli inferiori con l'assemblatore.
- Livello 5: definisce linguaggi di programmazione ad alto livello, tradotti da un compilatore ai livelli 3 o 4

# Macchina von Neumann

![[AE/img/img101.png|center|500]]

Composta da:
- memoria: conserva il programma e i dati su cui deve lavorare il programma
- CPU: unità di elaborazione composta da:
	- ALU: esegue istruzioni aritmetiche e logiche
	- CU (unità di controllo) recupera le istruzioni in memoria secondo l'algortimo in uso e permette l'esecuzione di esse
- Input/output: interfaccia del calcolatore verso l'esterno
- bus di comunicazione: canale che permette la comunicazione dei componenti 

# Processori
CPU: composta da unità di controllo, ALU e registri tra cui:
- Program Counter (PC): contiene l'indirizzo della prossima istruzione da eseguire
- Instruction Register (IR): mantiene l'istruzione corrente in fase di esecuzione

Il data path di una CPU di von Neumann è composta dai registri (da 1 a 32), dalla ALU e dai bus che connettono le diverse parti

I registri di input della ALU mantengono i dati di ingresso mentre la ALU esegue le operazioni su di esse, e il risultato viene memorizzato in un suo registro di output. 
Questo valore può essere immagazzinato in uno dei registri della CPU che può essere copiato in memoria in un secondo momento

Le istruzioni si possono dividere in delle categorie:
- istruzioni **registro-memoria**: necessita il caricamento di parole della memoria nei registri. permettono di prelevare parole di memoria per portarle all'interno dei registri e viceversa. questi dati possono essere utilizzati come input della ALU
- istruzioni **registro-registro**: coinvolgono operandi già presenti nei registri, si prelevano e vengono trasferiti nei registri di input della ALU, e su questi dati vengono eseguite delle operazioni.

# Esecuzione dell'istruzione
Ogni istruzione è eseguita dalla CPU compiendo una serie di passi (*fetch-decode-execute*)
- **prelevare la successiva istruzione** dalla memoria e la porta nell'IR
- **aggiorna il PC** per farlo puntare all'istruzione seguente
- **decodifica l'istruzione** appena prelevata
- se l'istruzione usa una parola in memoria, determina dove si trova
	- preleva la parola per portarla in un registro della CPU
- **esegue l'istruzione**
- torna al punto 1

# Strategie progettazione CPU
- CISC (Complex Instruction Set Computer): CPU in grado di comprendere molte istruzioni complesse 
- RISC (Reduced Instruction Set Computer): basate sull'idea che se le istruzioni sono semplici e poche esse possono essere eseguite rapidamente (un solo ciclo necessario nel datapath)
- Ibrido: contengono un sottoinsieme di istruzioni RISC (quelle più comuni) mentre le altre più complesse sono interpretate secondo la modalità CISC

# Principi di progettazione RISC
- Tutte le istruzioni eseguite dall'hardware: 
	- istruzioni non interpretate. Le architetture CISC suddividono le istruzioni complesse in microistruzioni
- Massimizzare frequenza di emissione di istruzione: 
	- il parallelismo è essenziale per le prestazioni
- Decodifica facile delle istruzioni:
	- limitare la complessità della decodifica rendendo le istruzioni regolari, di lunghezza fissa e con pochi campi.
- Solo le istruzioni LOAD e STORE fanno riferimento alla memoria:
	- l'operazione di spostamento degli operandi dalla memoria ai registri è consentito solo a istruzioni specifiche.
- Molti registri disponibili:
	- dato che l'accesso alla memoria è lento, si prevedono molti registri per evitare frequenti accessi in memoria

# Parallelismo a livello di istruzione
Poiché l'incremento del clock del processore ha raggiunto un limite fisico, si guarda al parallelismo per incrementare le prestazioni. Esso si può ottenere in due diversi modi: 
- *parallelismo a livello di istruzione*: sfruttato all'interno delle istruzioni per ottenere un maggior numero di istruzioni al secondo
- *parallelismo a livello di processore*: più CPU collaborano per risolvere lo stesso problema

# Pipelining
Una limitazione nella velocità di esecuzione sta nel prelievo delle istruzioni dalla memoria. Per ridurre questo problema, i calcolatori hanno la capacità di prelevare in anticipo le istruzioni dalla memoria, in modo da avere a disposizione nel momento in cui dovessero rendersi necessarie. 

Le istruzioni venivano memorizzate in dei buffer di prefetch e potevano essere prese nel momento in cui venivano richieste, senza dover attendere che si completasse la lettura dalla memoria. 

Il pipeline divide l'esecuzione di un istruzione in un numero maggiore di parti che possono essere eseguite in parallelo.

![[AE/img/img42.png|center|500]]

## Processori con più pipeline

![[AE/img/img102.png]]

Avere più pipeline permette un parallelismo maggiore, rispetto ad averne una sola, ma affinché le due istruzioni possano essere eseguite in parallelo, non ci devono essere conflitti nell'uso delle risorse e nessuna delle due istruzioni deve dipendere dal risultato dell'altra


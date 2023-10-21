
# Processo VS Thread
Il termine processo e' associato a quello di thread.
**Differenze**:
- Il processo e' un programma in esecuzione
- Il thread e' una suddivisione del processo che puo' essere eseguito in divisione del tempo o in parallelo ad altri thread da parte del processore.
In altre parole il thread e' una parte del processo che viene eseguita in maniera concorrente ed indipendente internamente allo stato generale del processo in esecuzione.
Se esiste un processo, allora deve esistere almeno un thread (se stesso).
**Quindi**:
- Il processo e' l'unita' del SO a cui sono assegnate tutte le risorse di sistema per l'esecuzione di un programma, tranne la CPU
- Il thread e' l'unita' del SO o dell'applicazione a cui e' assegnata la CPU per l'esecuzione
# Modello del processo
Ogni processo ha:
- Contatore di programma singolo
- Un' unica posizione
- Proprio flusso di controllo
Ogni volta che si passa da un processo all'altro, si salva il contatore di programma del primo processo e si ripristina il contatore di programma del secondo

![[SOR1.5/img/img1.png|center|600]]

Tutti i processi progrediscono, ma soltanto uno e' attivo in un dato momento

![[SOR1.5/img/img2.png|center|600]]

In linea di principio, i processi multipli sono reciprocamente indipendenti, hanno bisogno di mezzi espliciti per interagire tra loro.
La CPU viene assegnata a turno a diversi processi, ma il SO non da garanzie di tempistica o di ordine.

# Gerarchie di processo
Solitamente il SO crea solo un processo di $init$, che nei moderni sistemi operativi $init$ avvia un processo per la gestione dei thread, $kthreadd$
I sottoprocessi sono creati in maniera indipendente, quindi un processo padre puo' creare $n$ processi figli, che talvolta ne conseguira' una struttura ad albero e gruppi di processi

![[SOR1.5/img/img3.png|center|600]]

# Creazione di un processo
Ogni sistema operativo possiede un'opportuna *System Call*  di creazione di un processo. Tipicamente la System Call che crea un processo e' la $fork()$ 
Ogni processo e' creato a partire da un altro processo, il processo che ne crea un altro e' detto processo padre, il processo creato e' detto processo figlio.

Gli eventi che vanno a formare un processo sono:
- Inizializzazione del SO
- Esecuzione di una $fork()$ da parte di un processo in esecuzione
- Richiesta dell'utente di creare un nuovo processo
- Avvio di un lavoro

# Termine di un processo
Un processo termina dopo l'esecuzione dell'ultima istruzione del suo codice, quindi ci sara' un'uscita volontaria da parte di quest'ultimo, ma puo' capitare che il processo non termini solo dopo la fine dell'ultima istruzione, ma in determinate situazioni:
- Termina a causa di un errore
- Termina a causa di un errore fatale
- Killato da un altro processo

# Process management
- $fork$: Crea processo
	- Il figlio e' un clone privato del padre
	- Condivide delle risorse con il padre
- $exec$: Esegue un nuovo processo in combinazione con $fork$
- $exit$: Causa la terminazione volontaria del processo
- $kill$: Invia un segnale di terminazione a un processo o a un gruppo

# Stati di un processo 
Esistono 3 stati in cui un processo puo' trovarsi:
- *Running* o esecuzione (CPU utilizzata)
- *Ready* o pronto (Temporaneamente fermo per consentire l'esecuzione di un altro processo)
- *Blocked* o bloccato (Non viene eseguito finche' non si verifica un evento esterno)

Il SO alloca le risorse ai processi, ma per farlo deve verificare lo stato dei processi, Come?
tramite lo *Scheduler*:

![[SOR1.5/img/img4.png|center|600]]

# Informazioni associate ad un processo
- ID (PID), Utente (UID), Gruppo (GID)
- Spazio degli indirizzi di memoria
- Registri hardware 
- File aperti
- **Segnali**
- **Interrupt**
Queste info sono memorizzate nella tabella dei processi del sistema operativo

# Modello della multiprogrammazione
L'utilizzo della multiprogrammazione puo' migliorare l'utilizzo della CPU.
Un processo medio solitamente esegue calcoli solo per il 20% del tempo in cui risiede in memoria, quindi teoricamente con 5 processi la CPU sarebbe utilizzata al 100%.
Questo modello e' irrealisticamente ottimista dato che presuppone che tutti i processi non siano in attesa di I/O nello stesso momento.
Sotto un punto di vista statistico si ottiene un modello piu' realistico.
Supponiamo che un processo impieghi una frazione $p$ del suo tempo in attesa che l'I/O sia completato. Con $n$ processi in memoria contemporaneamente, la probabilita' che tutti gli $n$ processi stiano aspettando l'I/O e' $p^{n}$ .
Quindi la formula dell'utilizzo della CPU e':$$CPU=1-p^n$$ 
---SCREENSHOT Multiprogrammazione---

# Signals VS Interrupts
I segnali e gli interrupt sono meccanismi utilizzati nel SO e nelle applicazioni per gestire eventi asincroni
**Interrupts**:
- *Origine*: Dispositivi hardware (Tastier, Mouse, ecc...)
- *Gestione*: Tramite routine di servizio di interrupt 
- *Uso*: Comunicazione tra hardware e software; risposta pronta agli eventi hardware
- *Asincronia*: Si verificano in modo asincrono; vengono gestiti immediatamente

**Signals**:
- *Origine*: Eventi software; generati da un processo o dal SO
- *Gestione*: Gestori di segnali personalizzati o comportamento predefinito
- *Uso*: Gestione condizioni eccezionali nelle applicazioni
- *Asincronia*: Inviati asincronamente; possono essere gestiti in modo sincrono

# Interrupts
Per deallocare la CPU a favore dello scheduler, ci si affida al supporto per la gestione degli interrupt fornito dall'hardware. 
Questo permette allo scheduler di ottenere periodicamente il controllo, quindi ogni volta che l'hardware genera interrupt

**Interrupt Vector**:
- E' associato a ciascun dispositivo di I/O e linea di interrupt 
- E' parte della tabella dei descrittori di interrupt
- Contiene l'indirizzo iniziale di una procedura interna fornita dal SO

# Gestione dei segnali
- *Tipi di segnali*:
	- Hardware-induced ($SIGILL$)
	- Software-induced ($SIGQUIT$ or $SIGPIPE$)
- *Azioni possibili*:
	- $Term$, $Ign$, $Core$, $Stop$, $Cont$
	- Azione predefinita per ogni segnale, tipicamente sovrascrivibile
	- I segnali tipicamente possono essere bloccati
- *Gestione dei segnali*:
	- Il processo registra il gestore dei segnali
	- Il SO invia il segnale e consente al processo di eseguire l'handler
	- Il contesto di esecuzione corrente deve essere salvato/ripristinato

# Segnale CTRL+C
```C
void signalHandler(int signum) {
  printf ("Interrupt signal &d received\n", signum);
  // Cleanup and terminate program
  exit(signum);
}
int main() {
  // register signal SIGINT and signal handler 
  signal(SIGINT, signalHandler);
  while(1) {
    printf ("Going to sleep....\n");
    sleep(1);
  }
  return(0)
}
```

- Il kernel invia un segnale
- Interrompe il codice in esecuzione
- Salva il contesto
- Esegue il codice gestione del segnale
- Ripristina il contesto originale

# Thread
Perche' e' favorevole avere una specie di processo all'interno di un'altro processo?
Ci sono molte ragioni per avere questi sottoprocessi chiamati thread.
La ragione principale e' che in molte applicazioni ci sono molteplici attivita' contemporanee.
Suddividendo una di tali applicazioni in molteplici thread sequenziali eseguiti in parallelo, il modello di programmazione diventa piu' semplice.
Una seconda ragione e' che i thread e' che, dato che sono piu' leggeri dei processi, essi sono piu' veloci da creare e cancellare. E' utile quando il numero di thread cresce dinamicamente e rapidamente.
Una terza ragione per avere i thread e' data dalle prestazioni. I thread non producono un guadagno di prestazioni quando sono CPU Bound, ma quando c'e' un'attivita' considerevole, anche in presenza di un consistevole I/O, permettono alle attivita' di sovrapporsi, velocizando l'applicazione.
In conclusione i thread sono utili sui sistemi con piu' CPU, dove e' possibile il vero parallelismo.
**Thread dispatcher**:
Thread che lavora in background
```C
while(TRUE){
	get next request(&buf);
	handoff work(&buf);
}
```
**Thread worker**
Thread che lavora quando avviene una richiesta dal client
```C
while(True){
wait for work(&buf);
	look for page in cache(&buf, &page);
	if(page not in cache(&page))
		read page from disk(&buf, &page);
	return page(&page);
}

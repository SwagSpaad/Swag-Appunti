----
In questo capitolo studieremo le *forme normali*, che "certificano" la qualità dello schema di una base di dati relazionale. Infatti quando una relazione non soddisfa una forma normale, allora presenta ridondanze e si presta a comportamenti poco desiderabili durante le operazioni di aggiornamento. 
Per gli schemi che non soddisfano una forma normale, è possibile applicare un procedimento, detto *normalizzazione*, che consente di trasformare questi schemi non normalizzati in nuovi schemi per i quali il soddisfacimento di una forma normale è garantito.

# Ridondanze e anomalie
Informalmente, ogni tupla in una relazione dovrebbe rappresentare un'entità o un'istanza di relazioni. Gli attributi di diverse entità, *non dovrebbero essere mescolati* nella stessa relazione.
Per riferirsi ad altre entità, dovrebbero essere usate solo le chiavi esterne, quindi gli attributi di entità e di relazioni diverse dovrebbero essere tenuti il più possibile separati.
Consideriamo la relazione in tabella, che ha come chiave l'insieme costituito dagli attributi *Impiegato* e *Progetto*. Si noti che le tuple della relazione soddisfano le seguenti proprietà:
- lo stipendio di ciascun impiegato è unico ed è funzione del solo impiegato, indipendentemente dai progetti a cui partecipa;
- il bilancio di ciascun progetto è unico e dipende dal solo progetto, indipendentemente dagli impiegati che vi partecipano

| Impiegato | Stipendio | Progetto | Bilancio | Funzione    |
| --------- | --------- | -------- | -------- | ----------- |
| Rossi     | 20000     | Marte    | 2000     | tecnico     |
| Verdi     | 35000     | Giove    | 15000    | progettista |
| Verdi     | 35000     | Venere   | 15000    | progettista |
| Neri      | 55000     | Venere   | 15000    | direttore   |
| Neri      | 55000     | Giove    | 15000    | consulente  |
| Neri      | 55000     | Marte    | 2000     | consulente  |
| Mori      | 48000     | Marte    | 2000     | direttore   |
| Mori      | 48000     | Venere   | 15000    | progettista |
| Bianchi   | 48000     | Venere   | 15000    | progettista |
| Bianchi   | 48000     | Giove    | 15000    | direttore   |

^46e80c

- Il valore dello stipendio di ciascun impiegato è ripetuto in tutte le tuple relative a esso: si ha quindi una *ridondanza*; se per esempio un impiegato partecipasse a 20 progetti, il suo stipendio verrebbe ripetuto 20 volte.
- Se lo stipendio di un impiegato varia, è necessario andarne a modificare il valore in tutte le tuple corrispondenti; questo inconveniente, che comporta la necessità di effettuare più modifiche contemporaneamente, va sotto il nome di *anomalia di aggiornamento*.
- Se un impiegato interrompe la partecipazione a tutti i progetti senza lasciare l'azienda, e quindi tutte le corrispondenti tuple vengono eliminate, non è possibile conservare traccia del suo nome e del suo stipendio, che potrebbero rimanere di interresse; questo problema viene indicato come *anomalia di cancellazione*.
- Se si hanno informazioni su un nuovo impiegato, non è possibile inserirle finchè questi non viene assegnato a un progetto; in questo caso si parla di *anomalia di inserimento*.

Per un buono schema, bisogna progettarlo in modo che non risenta delle anomalie di inserimento, cancellazione e aggiornamento. Se sono presenti, vanno annotate in modo che le applicazioni possano tenerne conto. Le relazioni dovrebbero essere progettate in modo tale che le loro tuple abbiano il minor numero possibile di valori NULL. 
Un progetto non corretto per un database relazionale può causare risultati errati per alcune operazioni JOIN. La proprietà "*lossless join*" viene utilizzata per garantire risultati significativi per le operazioni di join.
Le relazioni dovrebbero essere progettate per soddisfare la condizione di lossless join. Non si dovrebbero creare tuple spurie facendo un natural-join di tutte le relazioni.

# Normalizzazione 
La normalizzazione è una formalizzazione teorica di alcuni problemi che possono emergere durante l'utilizzo, l'interrogazione e la gestione dei dati in un database e che possono impedire o rendere complicato l'uso delle informazioni. Non sempre è applicabile.
La normalizzazione è un procedimento utile per l'*eliminazione della ridondanza* delle informazione e per ridurre il rischio di inconsistenza della base dati. Di fatto riduce la dimensione delle relazioni a partire da relazioni con concetti tra loro indipendenti.
La normalizzazione dei dati può essere considerata come un processo di analisi degli schemi forniti, basato sulle loro dipendenze funzionali e chiavi primarie, per raggiungere le proprietà desiderate di
1. minimizzazione della ridondanza
2. minimizzazione delle anomalie di inserimento, cancellazione, modifica.
# Dipendenze funzionali
La **dipendenza funzionale** (**FD**) è un particolare vincolo di integrità per il modello relazionale che descrive legami di tipo funzionale tra gli attributi di una relazione.
Si ha **dipendenza funzionale** tra gli attributi quando il valore di un insieme di attributi A determina un singolo valore dell'attributo B e si indica con $A \rightarrow B$. Si dice anche che B dipende da A o che A è determinante per B.

**Oss.**
- Se un attributo è chiave candidata di una relazione, allora è un determinante di ogni attributo della relazione e viceversa, un attributo che determina tutti gli altri attributi è chiave candidata.
- Si ha **dipendenza transitiva** auando A determina B e B determina C. Si dice allora che C dipende transitivamente da A

Consideriamo la [[#^46e80c|tabella]] precedente. Osserviamo che lo stipendio di ogni impiegato è unico e ogni volta che in una tupla della relazione compare un certo impiegato, il valore del suo stipendio rimane sempre lo stesso. Possiamo dire che il valore dell'attributo *Impiegato* determina il valore dell'attributo *Stipendio*, o possiamo dire che esiste una funzione che associa a ogni elemento del dominio dell'attributo *Impiegato* un solo elemento del dominio dell'attributo *Stipendio*. 
Notiamo che ogni impiegato in ciascun progetto ha una sola funzione. Scriviamo quindi in notazione: 
$$\text{Impiegato} \rightarrow \text{Stipendio}$$
$$\text{Impiegato Progetto} \rightarrow \text{Funzione}$$
La seconda dipendenza si tratta però di una dipendenza funzionale *banale*, ovvero sempre soddisfatta.
- $Y \rightarrow A$ è *non banale* se A non appartiene a Y.
- $Y \rightarrow Z$ è *non banale* se nessun attributo in Z appartiene a Y

Le anomalie nella tabella sono legate ad alcune dipendenze funzionali:
- gli impiegati hanno un unico stipendio, scriviamo in notazione $$\text{Impiegato} \rightarrow \text{Stipendio}$$
- i progetti hanno un unico bilancio $$\text{Progetto} \rightarrow \text{Bilancio}$$
- in ciascun progetto, un impiegato svolge una sola funzione $$\text{Impiegato Progetto} \rightarrow \text{Funzione}$$
Osserviamo che la terza FD corrisponde ad una chiave e non causa anomalie, mentre le prime due FD non corrispondono a chiavi e causano anomalie.
La relazione contiene alcune informazioni legate alla chiave e altre ad attributi che non formano una chiave. 
Le anomalie sono causate dalla presenza di concetti eterogenei:
- proprietà degli impiegati (stipendio)
- proprietà di progetti (bilancio)
- proprietà della chiave Impiegato Progetto
Una FD è una proprietà degli attributi nello schema R. Il vincolo deve valere su ogni istanza della relazione $r(R)$ 
Se K è una chiave di R, allora K determina funzionalmente tutti gli attributi in R (poiché non abbiamo mai due tuple distinte con $t_1[K]=t_2[K]$).

# Forme normali
## Prima forma normale
Uno schema di relazione $R(X)$ con X insieme di attributi, è in 1NF se ogni attributo appartenente ad X è un attributo semplice, ovvero se il suo valore è unico e indivisibile in una ennupla. 

**Es.**

| Codice | Cognome | Nome  | Data Nascita | Figli a carico |
| ------ | ------- | ----- | ------------ | -------------- |
| 001    | Rossi   | Mario | 01/01/1978   | Luca Serena    |
| 002    | Verdi   | Luca  | 02/04/1959   | Marzia Ilaria  | 

La tabella non è in 1NF, perché l'attributo *Figli a carico* contiene più valori. 
La decomposizione genera due tabelle distinte

| Codice | Cognome | Nome  | Data Nascita |
| ------ | ------- | ----- | ------------ |
| 001    | Rossi   | Mario | 01/01/1978   |
| 002    | Verdi   | Luca  | 02/04/1959   | 

| Codice | Codice Figlio | Nome   |
| ------ | ------------- | ------ |
| 001    | 01            | Luca   |
| 001    | 02            | Serena |
| 002    | 01            | Marzia |
| 002    | 02            | Ilaria | 

## Seconda forma normale
Uno shcema di relazione $R(X)$ è in 2NF se è in 1NF e se ogni attributo non primo (non facente parte della chiave) di $R(X)$ dipende funzionalmente e completamente da ogni chiave di $R(X)$.

**Es.**

*Inventario*

| <u>CodArticolo</u> | <u>CodMagazzino</u> | DescArticoli | Quantità | IndirizzoMagazzino |
| ------------------ | ------------------- | ------------ | -------- | ------------------ |

La tabella non è in 2NF, perché tutte le colonne corrispndenti agli attributi non chiave non dipendono dall'intera chiave primaria
Si derivano quindi 3 diverse tabelle: 

*Inventario*

| <u>CodArticolo</u> | <u>CodMagazzino</u> | Quantità | 
| ------------------ | ------------------- | -------- |

*Articoli*

| <u>CodArticolo</u> | DescArticoli | 
| ------------------ | ------------ |

*Magazzino*

| <u>CodMagazzino</u> | IndirizzoMagazzino | 
| ------------------- | ------------------ |

## Terza forma normale
Uno schema di relazione $R(X)$ è in 3NF se è in 1NF e se ogni attributo non primo (non facente parte della chiave) di $R(X)$ è dipendente in modo non transitivo da ogni chiave di $R(X)$.

**Es.**

*Impiegati*

| <u>CodImpiegato</u> | Nome | Reparto | TelefonoReparto |
| ------------------- | ---- | ------- | --------------- |

La tabella non è in 3NF per i seguenti motivi: 
- *telefono del Reparto* ripetuto per ogni Impiegato di quel *Reparto* (ridondanza)
- se il telefono cambia occore modificare molte righe
- con errori di aggiornamento, si avrebbero telefoni differenti
- se un *Reparto* non ha impiegati, non si può conoscere il suo telefono
Si osservano le seguenti dipendenze funzionali:
- $\text{CodImpiegato} \rightarrow \text{Reparto}$
- $\text{Reparto}\rightarrow \text{TelefonoReparto}$
La tabella in 3NF risulta quindi:

*Impiegati*

| <u>CodImpiegato</u> | Nome | Reparto | 
| ------------------- | ---- | ------- |

*Reparto*

| <u>Reparto</u> | TelefonoReparto | 
| -------------- | --------------- |

## Forma normale di Boyce-Codd
Una relazione è in **forma normale di Boyce-Codd (BCNF)** se è in 1NF se per ogni FD $X \to A$ in R, $X$ è una superchiave di R.

**Oss.**
Ogni forma normale è strettamente più forte della precedente:
- Ogni relazione in [[#Seconda forma normale|2NF]] è in [[#Prima forma normale|1NF]]
- Ogni relazione in [[#Terza forma normale|3NF]] è in [[#Seconda forma normale|2NF]]
- Ogni relazione in [[#Forma normale di Boyce-Codd|BCNF]] è in [[#Terza forma normale|3NF]]

Esistono delle relazioni in [[#Terza forma normale|3NF]] che però non sono in [[#Forma normale di Boyce-Codd|BCNF]].
L'obiettivo è avere ogni relazione in [[#Forma normale di Boyce-Codd|BCNF]] o in [[#Terza forma normale|3NF]]

## Quarta e quinta forma normale
Risolvono i problemi che si possono creare quando nella relazione sono presenti attributi multivalore, cioè attributi che possono assumere più valori in corrispondenza dello stesso valore di un altro attributo.

# Quale forma normale
È sufficiente rappresentare le relazioni in [[#Terza forma normale|3NF]] che ha il pregio di essere sempre ottenibile senza **perdita di informazioni** e senza **perdita di dipendenze funzionali**.
Non si può dire la stessa cosa per la [[#Forma normale di Boyce-Codd|forma normale di Boyce-Codd]], perché ci sono relazioni che non possono essere normalizzate in BCNF senza perdita di dipendenze funzionali.

# Normalizzazione e decomposizione
Cosa facciamo se una relazione non rispetta la BCNF? La rimpiazziamo con altre relazioni che la soddisfano, decomponendo sulla base delle dipendenze funzionali, al fine di separare i concetti. 

## Esempio di decomposizione

| <u>Impiegato</u> | Stipendio | <u>Progetto</u> | Bilancio | Funzione    |
| ---------------- | --------- | --------------- | -------- | ----------- |
| Rossi            | 20        | Marte           | 2        | tecnico     |
| Verdi            | 35        | Giove           | 15       | progettista |
| Verdi            | 35        | Venere          | 15       | progettista |
| Neri             | 55        | Venere          | 15       | direttore   |
| Neri             | 55        | Giove           | 15       | consulente  |
| Neri             | 55        | Marte           | 2        | consulente  |
| Mori             | 48        | Marte           | 2        | direttore   |
| Mori             | 48        | Venere          | 15       | progettista |
| Bianchi          | 48        | Venere          | 15       | progettista |
| Bianchi          | 48        | Giove           | 15       | direttore   |

**Procedura intuitiva di normalizzazione**
Non valida in generale, ma solo nei casi più semplici:
- Per ogni dipendenza $X \to Y$ che viola la BCNF, definiamo una relazione su $XY$ ed eliminare $Y$ dalla relazione originaria
Ma questo non sempre è possibile

| Impiegato | Progetto | Sede   |
| --------- | -------- | ------ |
| Rossi     | Marte    | Roma   |
| Verdi     | Giove    | Milano |
| Verdi     | Venere   | Milano |
| Neri      | Saturno  | Milano |
| Neri      | Venere   | Milano |

^976ccc

Ha le seguenti dipendenza: 
- $\text{Impiegato} \to \text{Sede}$
- $\text{Progetto} \to \text{Sede}$

Decomponiamo sulla base delle dipendenze

| Impiegato | Progetto | Sede   |
| --------- | -------- | ------ |
| Rossi     | Marte    | Roma   |
| Verdi     | Giove    | Milano |
| Verdi     | Venere   | Milano |
| Neri      | Saturno  | Milano |
| Neri      | Venere   | Milano | 

| Impiegato | Sede   |
| --------- | ------ |
| Rossi     | Roma   |
| Verdi     | Milano |
| Neri      | Milano |

| Progetto | Sede   |
| -------- | ------ |
| Marte    | Roma   |
| Giove    | Milano |
| Saturno  | Milano |
| Venere   | Milano | 

**Proviamo a ricostruire**

| Impiegato | Sede   |
| --------- | ------ |
| Rossi     | Roma   |
| Verdi     | Milano |
| Neri      | Milano | 

| Progetto | Sede   |
| -------- | ------ |
| Marte    | Roma   |
| Giove    | Milano |
| Saturno  | Milano |
| Venere   | Milano | 

| Impiegato | Progetto | Sede   |
| --------- | -------- | ------ |
| Rossi     | Marte    | Roma   |
| Verdi     | Giove    | Milano |
| Verdi     | Venere   | Milano |
| Neri      | Saturno  | Milano |
| Neri      | Venere   | Milano |
| **Verdi**     | **Saturno**  | **Milano** |
| **Neri**      | **Giove**    | **Milano** | 

Gli attributi evidenziati mostrano che la tabella risultante è diversa dalla [[#^976ccc|tabella]] di partenza.

## Decomposizione senza perdita
Una relazione $r$ si decompone senza perdita su $X_1$ e $X_2$ se il join delle proiezioni di $r$ su $X_1$ e $X_2$ è uguale a $r$ stessa, ovvero non contiene tuple spurie.
La **decomposizione senza perdita** è garantita se gli attributi comuni contengono una **chiave** per almeno una delle relazioni decomposte.

### Decomponiamo senza perdita

| Impiegato | Progetto | Sede   |
| --------- | -------- | ------ |
| Rossi     | Marte    | Roma   |
| Verdi     | Giove    | Milano |
| Verdi     | Venere   | Milano |
| Neri      | Saturno  | Milano |
| Neri      | Venere   | Milano | 

| Impiegato | Sede   |
| --------- | ------ |
| Rossi     | Roma   |
| Verdi     | Milano |
| Neri      | Milano |

| Impiegato | Progetto |
| --------- | -------- |
| Rossi     | Marte    |
| Verdi     | Giove    |
| Verdi     | Venere   |
| Neri      | Saturno  |
| Neri      | Venere   | 

- $\text{Impiegato}\to \text{Sede}$
- $\text{Progetto}\to\text{Sede}$

**Un altro problema**
Supponiamo di voler inserire una nuova tupla che specifica la partecipazione dell'impiegato Neri, che opera a Milano, al progetto Marte.

| Impiegato | Sede   |
| --------- | ------ |
| Rossi     | Roma   |
| Verdi     | Milano |
| **Neri**      | **Milano** | 

| Impiegato | Progetto |
| --------- | -------- |
| Rossi     | Marte    |
| Verdi     | Giove    |
| Verdi     | Venere   |
| Neri      | Saturno  |
| Neri      | Venere   |
| **Neri**      | **Marte**    | 

- $\text{Impiegato}\to\text{Sede}$
- $\text{Progetto}\to\text{Sede}$

Ma il progetto **Marte** ha come sede **Roma**

Una decomposizione, conserva le dipendenze se ciascuna delle dipendenze funzionali dello schema originario coinvolge attributi che compaiono tutti insieme in uno degli schemi decomposti.
- $\text{Progetto}\to \text{Sede}$

Una decomposizione dovrebbe sempre soddisfare:
- la **decomposizione senza perdita**, che garantisce la ricostruzione delle informazioni originarie
- la **conservazione delle dipendenze**, che garantisce il mantenimento dei vincoli di integrità originari.

**Una relazione non normalizzata**

| Dirigente | <u>Progetto</u> | <u>Sede</u> |
| --------- | --------------- | ----------- |
| Rossi     | Marte           | Roma        |
| Verdi     | Giove           | Milano      |
| Verdi     | Marte           | Milano      |
| Neri      | Saturno         | Milano      |
| Neri      | Venere          | Milano      | 

- $\text{Progetto Sede}\to\text{Dirigente}$
- $\text{Dirigente}\to\text{Sede}$

La decomposizione è problematica, perché $\text{Progetto Sede}\to\text{Dirigente}$ coinvolge tutti gli attributi e quindi nessuna decomposizione può preservare questa dipendenza, quindi in alcuni casi la [[#Forma normale di Boyce-Codd|BCNF]] non è raggiungibile.

Una relazione $r$ è in **terza forma normale** se, per ogni FD $X\to Y$ definita su $r$, è verificata almeno una delle seguenti condizioni:
- $X$ contiene una chiave *K* di $r$
- ogni attributo in $Y$ è contenuto in almeno una chiave di $r$

La terza forma normale è meno restrittiva della forma normale di Boyce-Codd, ma ha il vantaggio di essere sempre raggiungibile. Se una relazione ha una sola chiave, allora essa è in BCNF se e solo se è in 3NF.


## Decomposizione in terza forma normale
- Si crea una relazione per ogni gruppo di attributi coinvolti in una dipendenza funzionale 
- Si verifica che alla fine una relazione contenga una chiave della relazione originaria

Se la relazione non è normalizzata si decompone in terza forma normale e alla fine si verifica se lo schema ottenuto è anche in BCNF.

Vediamo uno schema non decomponibile in BCNF

| Dirigente | <u>Progetto</u> | <u>Sede</u> |
| --------- | --------------- | ----------- |
| Rossi     | Marte           | Roma        |
| Verdi     | Giove           | Milano      |
| Verdi     | Marte           | Milano      |
| Neri      | Saturno         | Milano      |
| Neri      | Venere          | Milano      | 

- $\text{Progetto Sede}\to\text{Dirigente}$
- $\text{Dirigente}\to\text{Sede}$

La relazione può essere riorganizzata nel seguente modo:

| Dirigente | <u>Progetto</u> | <u>Sede</u> | Reparto |
| --------- | --------------- | ----------- | ------- |
| Rossi     | Marte           | Roma        | 1       |
| Verdi     | Giove           | Milano      | 1       |
| Verdi     | Marte           | Milano      | 1       |
| Neri      | Saturno         | Milano      | 2       |
| Neri      | Venere          | Milano      | 2       | 

- $\text{Dirigente}\to\text{Sede Reparto}$
- $\text{Sede Reparto}\to\text{Dirigente}$
- $\text{Progetto Sede}\to\text{Reparto}$

La relativa decomposizione in BCNF risulta

| <u>Dirigente</u> | Sede   | Reparto |
| ---------------- | ------ | ------- |
| Rossi            | Roma   | 1       |
| Verdi            | Milano | 1       |
| Neri             | Milano | 2       | 

| <u>Progetto</u> | <u>Sede</u> | Reparto |
| --------------- | ----------- | ------- |
| Marte           | Roma        | 1       |
| Giove           | Milano      | 1       |
| Marte           | Milano      | 1       |
| Saturno         | Milano      | 2       |
| Venere          | Milano      | 2       | 

## Teoria della normalizzazione
I concetti visti possono essere formalizzati in maniera precisa. 
- **Problema**: data una relazione $r$ e un insieme di dipendenze funzionali definite su $r$, generare una decomposizione di $r$ che:
	- sia senza perdita e conservi le dipendenze
	- contenga solo relazioni normalizzate

Per risolvere il problema faremo quindi riferimento alla 3NF.

### Implicazione dipendenze funzionali
Un insieme $F$ di FD **implica** un'altra FD $f$ se ogni relazione che soddisfa tutte le FD in $F$ soddisfa anche $f$.

**Es.**
$\text{R(Impiegato, Categoria, Stipendio)}$ 
Le FD $\text{Impiegato}\to\text{Categoria}$ e $\text{Categoria}\to\text{Stipendio}$ implicano la FD $\text{Impiegato}\to\text{Stipendio}$.

### Chiusura di un insieme di attributi
Dati uno schema di relazione $R(U)$, un insieme $F$ di FD definite su U e un insieme di attributi $X$ contenuti in $U$, definiamo la **chiusura di X** rispetto ad F, indicata con $X^{+}_{F}$, come l'insieme degli attributi che dipendono funzionalmente da X: $$X_{F}^{+}:\{A\: |\: A\in U\: e\: F\:\text{implica }X \to A\}$$ Se A appartiene a $X_{F}^{+}$ allora $X \to A$ è implicata da F.

Come calcoliamo $X_{F}^{+}$?
- **Input**: un insieme X di attributo e un insieme di F di dipendenze funzionali
- **Output**: un insieme $X_{p}$ di attributi

1. Inizializziamo $X_{p}$ con l'insieme di input X
2. Se esiste una FD $Y \to A$ in F con $Y \subseteq X_{p}$ e $A \notin X_{p}$, allora aggiungiamo A ad $X_{p}$ 
3. Ripetiamo il passo 2 fino a quando non ci sono ulteriori attributi che possono essere aggiunti a $X_{p}$

Un insieme di attributi K è chiave per uno schema di relazione $R(U)$ su cui è definito un insieme di dipendenze funzionali F se F implica $K \to U$. 
L'algoritmo appena mostrato può essere utilizzato per verificare se un insieme di attributi è chiave.

### Coperture di dipendenze funzionali
Due insieme di dipendenze funzionali $F_1$ ed $F_2$ sono **equivalenti** se $F_1$ implica ciascuna dipendenza in $F_2$ e viceversa. Se due insiemi sono equivalenti diciamo che ognuno è una **copertura** dell'altro. Questa proprietà consente di utilizzare, dato un insieme di dipendenze, un altro, a esso equivalente, ma più semplice.

### Proprietà desiderabili di FD
Un insieme di dipendenze $F$ è:
- **non ridondante** se non esiste dipendenza $f\in F$ tale che $F -{f}$ implica $f$
- **ridotto** se:
	- non è ridondante
	- non esiste un insieme $F'$ equivalente a $F$ ottenuto eliminando attributi dai primi membri di una o più dipendenze di $F$

**Esempio**
$F_{1}=\{A \to B; AB\to C; A \to C\}$ ridondante e equivalente a $F_2$ 
$F_{2}=\{A \to B; AB\to C\}$ non ridondante ma non ridotto
$F_{3}=\{A \to B; A\to C\}$ **ridotto**

**Calcolo copertura ridotta**
1. Sostituiamo l'insieme dato con quello equivalente che ha tutti i secondi membri costituiti da singoli attributi
2. Eliminiamo le dipendenze funzionali
3. Per ogni dipendenza verifichiamo se esistono attributi eliminabili a primo membro

In pratica, per ogni dipendenza $Y \to A \in F$, verifichiamo se esiste $Y \subseteq X$ tale che $F$ è equivalente a $F - \{X \to A\}\cup\{Y \to A\}$. 

**Sintesi di schemi in 3NF**
Dati uno schema $R(U)$ e un insieme di dipendenze $F$ di $U$
1. Viene calcolata una copertura ridotta $G$ di $F$;
2. G viene partizionato in sottoinsiemi tali che a ogni insieme appartengono dipendenze che hanno primi membri con la stessa chiusura;
3. Viene costruito un insieme $U$ di sottoinsiemi di $U$, uno per ciascuna partizione di dipendenze, con tutti gli attributi coinvolti nella partizione;
4. Se un elemento di $U$ è propriamente contenuto in un altro, allora esso viene eliminato da $U$; 
5. Viene costruito uno schema di relazione $R_{i}(U_{i})$ per ciascun elemento $U_{i}\in U$ con associate le dipendenze in G i cui attributi sono tutti contenuti in $U_{i}$;
6. Se nessuno degli $U_{i}$ è chiave per $R(U)$, allora viene calcolata una chiave $K$ di $R(U)$ e viene aggiunto allo schema generato uno schema di relazione sugli attributi K, senza dipendenze.

**Esempio**
Schema: $R(MCGRDSPA)$
FD: $M \to RSDG,\: MS\to CD,\:G\to R,\:D \to S,\:S\to D,\:MPD\to AM$ 
- Al passo 1 si ottiene la copertura ridotta: $$M\to D,\:M\to G,\:M\to C,\:G\to R,D \to S,\: S\to D, MP \to A$$
- Al passo 2 si partiziona la copertura negli insiemi: $$G_{1}=\{M\to D,\:M\to G,\:M\to C\}$$ $$G_{2}=\{G\to R\}$$ $$G_{3}=\{D\to S,\:S\to D\}$$ $$G_{4}=\{MP\to A\}$$
 - I passi 3, 4, 5 costruiscono uno schema di relazione per ciascuna partizione (senza eliminazioni), con le dipendenze corrispondenti.
 - Il passo 6 non ha effetti, perché MP è chiave per R.
 - Quindi viene generato lo schema con le relazioni:
	 - $R_{1}(MDGC)$ con le dipendenze $\{M\to D,\:M\to G,\:M \to C\}$
	 - $R_{2}(GR)$ con le dipendenze $\{G\to R\}$
	 - $R_{3}(DS)$ con le dipendenze $\{D\to S,\:S\to D\}$
	 - $R_{4}(MPA)$ con le dipendenze $\{MP\to A\}$

### Progettazione e normalizzazione
La teoria della normalizzazione può essere usata:
- nella progettazione logica per verificare lo schema relazionale finale
- durante la progettazione concettuale per verificare la qualità dello schema concettuale


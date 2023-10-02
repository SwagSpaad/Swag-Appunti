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
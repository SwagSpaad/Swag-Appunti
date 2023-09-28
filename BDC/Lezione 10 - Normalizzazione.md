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
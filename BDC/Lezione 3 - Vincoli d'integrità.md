----
Le strutture del modello relazionale ci permettono di organizzare le informazioni di interesse per le nostre applicazioni. In alcuni casi, però, non è vero che qualsiasi insieme di tuple sullo schema rappresenti informazioni corrette per l'applicazione. Consideriamo la base di dati rappresentata sotto e notiamo delle situazioni che non si dovrebbero presentare

![[BDC/img/img5.png|center|500]]

- Nella prima tupla della relazione ESAMI abbiamo un voto pari a 32, che nel sistema italiano non è ammissibile, in quanto i voti devono essere compresi tra 0 e 30.
- Nella terza tupla della relazione ESAMI è stata attribuita la lode ad un esame il cui voto è 27, il che è impossibile in quanto la lode può essere attribuita solo se il voto è 30.
- La quarta tupla della relazione ESAMI presenta, per l'attributo Studente, un valore che non compare fra i numeri di matricola nella relazione STUDENTI; questa è una situazione indesiderabile, poiché i numeri di matricola ci forniscono informazioni solo come tramite verso le corrispondenti tuple della relazione STUDENTI

In una base di dati è opportuno evitare questo tipo di situazioni e per questo è stato introdotto il concetto di **vincolo di integrità**, come proprietà che deve essere soddisfatta dalle istanze che rappresentano informazioni corrette per l'applicazione. Ogni vincolo è visto come una funzione booleana che associa ad ogni istanza il valore *vero* o *falso*.
È possibile classificare i vincoli a seconda degli elementi di una base di dati che ne sono coinvolti. Distinguiamo due categorie, la prima delle quali ha alcuni casi particolari:
- un vincolo è *intrarelazionale* se il suo soddisfacimento è definito rispetto a singole relazioni della base di dati; gli esempi esposti sopra corrispondono a vincoli intrarelazionali. A volte il coinvolgimento riguarda le tuple o i valori separatamente le une dalle altre: 
	- un **vincolo di tupla** è un vincolo che può essere valutato su ciascuna tupla indipendentemente dalle altre: i vincoli relativi ai primi due esempi rientrano in questa categoria
	- un vincolo definito con riferimento a singoli valori (come il caso in cui sono ammessi valori dell'attributo Voto compresi tra 18 e 30), sono detti **vincolo su valori o vincolo di dominio**, in quanto impone una restrizione sul dominio dell'attributo.
- Un vincolo è *interrelazionale* se coinvolge più relazioni; questo è il caso del terzo esempio, in cui la situazione indesiderata può essere vietata richiedendo che un numero di matricola compaia nella relazione ESAMI solo se compare nella relazione STUDENTI ^fab41d

# Vincoli di ennupla
Esprimono condizioni sui valori di ciascuna tupla, indipendentemente dalle altre tuple.
Una possibile sintassi per esprimere vincoli di questo tipo è quella che permette di definire espressioni booleane, cioè con connettivi AND, OR e NOT, con atomi che confrontano valori di attributo o espressioni aritmetiche su valori di attributo. 

**Es.**
$$(Voto \geq 18)\: AND\: (Voto \leq 30)$$
$$(Voto=30)\: OR \: NOT (Lode=\text{"e lode"})$$
Il primo vincolo è un vincolo di dominio, in quanto coinvolge solo l'attributo voto.

La definizione ammette anche espressioni più complesse, purché definite sui valori delle singole tuple, per esempio su una relazione del genere

![[BDC/img/img6.png|center|500]]

possiamo definire il vincolo che impone che il lordo sia pari alla somma tra ritenute e netto nel seguente modo: $$\text{Lordo = Netto + Ritenute}$$
# Chiavi
I vincoli di chiave sono i più importanti del modello relazionale, infatti senza di essi il modello stesso non avrebbe senso. Nella foto sotto notiamo che i valori delle varie tuple sull'attributo *Matricola* sono tutti diversi l'uno dall'altro, in quanto la matricola identifica **univocamente** gli studenti. 

![[BDC/img/img7.png|center|500]]

Una **chiave** è un insieme di attributi che identificano univocamente le tuple di una relazione. Formalizziamo la definizione in due passi: 
- Un insieme $K$ di attributi è **superchiave** di una relazione $r$ se essa non contiene due tuple distinte $t_{1}$ e $t_2$ con $t_1[K]=t_2[K]$
- $K$ è **chiave** di $r$ se è una superchiave minimale di $r$, ovvero se non contiene un'altra superchiave

Nella foto:
- l'insieme *{Matricola}* è una superchiave, è anche una superchiave minimale, in quanto contiene un solo attributo.

![[BDC/img/img8.png|center|500]]

- l'insieme *{Cognome, Nome, Nascita}* è superchiave, inoltre nessuno dei suoi sottoinsiemi è superchiave, infatti esistono due tuple uguali su *Cognome* e *Nascita*, due uguali su *Cognome* e *Nome* e due uguali su *Nome* e *Nascita*.
- l'insieme *{Matricola, Corso}* è superchiave, ma non è minimale, in quanto il sottoinsieme *{Matricola}* è esso stesso superchiave minimale, quindi *{Matricola, Corso}* non è una chiave.

**Oss.** La tabella in figura non contiene tuple uguali sull'insieme *{Cognome, Corso}*, quindi è superchiave. Poiché ci sono tuple uguali su *Cognome* e su *Corso*, l'insieme è anche una superchiave minimale, quindi è chiave. In questa relazione, *Cognome* e *Corso* identificano univocamente le tuple, ma questo è sempre vero? Ovviamente no, in quanto possono esistere studenti con stesso cognome iscritti allo stesso corso.

I vincoli corrispondono a proprietà del mondo reale modellato dalla base di dati ed essi sono definiti a livello di schema cioé con riferimento a tutte le istanze. Ad uno schema associamo un insieme di vincoli e consideriamo corrette le istanze che soddisfano tutti i vincoli. 

Per esempio, a uno schema: $$\text{Studenti(Matricola, Cognome, Nome, Nascita, Corso)}$$
vanno associati i vincoli che impongono come chiavi i due insiemi: 
- *{Matricola}*
- *{Cognome, Nome, Nascita}*

Una relazione non può contenere tuple distinte ma uguali e ogni relazione ha come superchiave l'insieme degli attributi su cui è definita, ovvero ha almeno una chiave.
L'esistenza delle chiavi garantisce l'accessibilità a ciascun dato della base di dati. Inoltre permette di stabilire efficacemente quelle corrispondenze fra dati contenuti in relazioni diverse che caratterizzano il modello relazionale come "modello basato sui valori".

## Chiavi e valori nulli
È necessario evitare la proliferazione di valori nulli nelle relazioni, infatti, in presenza di valori nulli, non è più vero che  i valori delle chiavi permettono di identificare univocamente le tuple delle relazioni e di stabilire riferimenti tra tuple di relazioni diverse.

| Matricola | Cognome | Nome  | Corso    | Nascita |
| --------- | ------- | ----- | -------- | ------- |
| NULL      | NULL    | Mario | Matem.   | 5/12/78 |
| 78763     | Rossi   | Mario | Fisica   | 3/11/76 |
| 65432     | Neri    | Piero | Biologia | 10/7/79 |
| 87654     | Neri    | Mario | Fisica   | NULL    |
| NULL      | Rossi   | Piero | NULL     | 5/12/78 | 

Nella tabella in figura possiamo notare che l'ultima tupla ha valori nulli su *Matricola* e *Nascita*, cioè su almeno un attributo di ciascuna chiave, quindi questa tupla non è identificabile in nessun modo; se vogliamo inserire nella base di dati un'altra tupla relativa ad uno studente di nome Piero Rossi, non possiamo sapere se ci stiamo riferendo allo stesso studente o ad un altro. 
Questo esempio ci suggerisce la necessità di porre dei limiti alla presenza di valori nulli nelle chiavi delle relazioni. 
Si adotta una soluzione che ci permette di identificare univocamente tutte le tuple e far riferimento a esse da parte di altre relazioni: su una delle chiavi (detta **chiave primaria**) si vieta la presenza di valori nulli; sulle altre i valori nulli sono in genere ammessi.
La chiave primaria viene scelta fra l'insiemedi chiavi secondo dei criteri di efficienza.

# Vincoli di integrità referenziale
Per discutere la più importante classe di [[#^fab41d|vincoli interrelazionali]], consideriamo la base di dati nella tabella sotto. La prima relazione (**INFRAZIONI**) contiene le informazioni relative a un insieme di infrazioni al codice della strada, la seconda (**AGENTI**) è relativa agli agenti di polizia che le hanno rilevate e la terza (**AUTO**) si riferisce ad un insieme di autoveicoli.

**INFRAZIONI**

| <u>Codice</u> | Data | Vigile   | Prov   | Numero     |
| ------------- | ------- | -------- | ------ | ---------- |
| 34321         | 1/2/95  | **3987** | **MI** | **39548K** |
| 53524         | 4/3/95  | **3295** | **TO** | **E39548** |
| 64521         | 5/4/96  | **3295** | **PR** | **839548** |
| 73321         | 5/2/98  | **9345** | **PR** | **839548** |

**VIGILI**

| <u>Matricola</u> | Cognome | Nome  |
| --------- | ------- | ----- |
| **3987**      | Rossi   | Luca  |
| **3295**      | Neri    | Piero |
| **9345**      | Neri    | Mario |
| 7543      | Mori    | Gino  | 

**AUTO**

| <u>Prov</u> | <u>Numero</u> | Cognome | Nome  |
| ---- | ------ | ------- | ----- |
| **MI**   | **39548K** | Rossi   | Mario |
| **TO**   | **E39548** | Rossi   | Mario |
| **PR**   | **839548** | Neri    | Luca  |

Le informazioni della relazione **INFRAZIONI** sono rese significative e complete attraverso il riferimento alle altre due relazioni: 
- alla relazione **AGENTI** tramite l'attributo *Agente* che contiene i numeri di matricola di agente corrispondenti alla chiave primaria della relazione **AGENTI** 
- alla relazione **AUTO** per mezzo degli attributi *Prov* e *Numero* che contengono i valori degli attributi che formano la chiave primaria della relazione **AUTO**
I riferimenti sono significativi in quanto i valori nella relazone INFRAZIONI sono uguali ai valori presenti nelle altre due relazioni.

Un **vincolo di integrità referenziale (foreign key)** fra un insieme di attributi $X$ di una relazione $R_{1}$ e un'altra relazione $R_{2}$ impone ai valori su $X$ in $R_{1}$ di comparire come valori della chiave primaria di $R_{2}$. 
Un esempio di vincoli di integrità referenziale relativo alle tabelle sopra sono:
- l'attributo *Vigile* della relazione **INFRAZIONI** e la relazione **VIGILI**
- gli attributi *Prov* e *Numero* di **INFRAZIONI** e la relazione **AUTO**
I vincoli di integrità referenziale giocano un ruolo fondamentale nel concetto di "modello basato su valori". In presenza di valori nulli, i vincoli possono essere resi meno resrittivi. 
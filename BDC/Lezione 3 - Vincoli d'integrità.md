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
- Un vincolo è *interrelazionale* se coinvolge più relazioni; questo è il caso del terzo esempio, in cui la situazione indesiderata può essere vietata richiedendo che un numero di matricola compaia nella relazione ESAMI solo se compare nella relazione STUDENTI

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
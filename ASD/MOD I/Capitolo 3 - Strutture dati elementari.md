----
# Gestione di collezioni di oggetti

**Tipo di dato**:
- Specifica una collezione di oggetti e delle operazioni di interesse su tale collezione (es. inserisci,cancella,cerca)

**Struttura dati**:
- Organizzazione dei dati che permette di memorizzare la collezione e supportare le operazioni di un tipo di dato usando meno risorse di calcolo possibile

## Il tipo di dato Dizionario

![[ASD/MOD I/img/img69.png|center|400]]

## Il tipo di dato Pila

![[ASD/MOD I/img/img70.png|center|400]]

## Il tipo di dato Coda

![[ASD/MOD I/img/img71.png|center|400]]

# Tecniche di rappresentazione dei dati

## Rappresentazione indicizzata
Un array è una collezione di celle numerate che contengono elementi di un tipo prestabilito

>[!important]- Proprietà
>**(forte)**: gli indici delle celle di un array sono numeri consecutivi
>**(debole)**: non è possibile aggiungere nuove celle ad un array

>[!warning]- Pro e contro
>Pro: accesso diretto ai dati tramite indici
>Contro: la dimensione è fissa, la riallocazione di un array richiede tempo lineare

## Rappresentazioni collegate
Una struttura collegata è costituita da record numerati (indirizzo di memoria), ciascuno contenente un oggetto della collezione. In questa struttura i record sono creati e distrutti individualmente e un collegamento tra due record A e B è realizzato tramite un puntatore.

>[!important]- Proprietà
>**(forte)**: è possibile aggiungere o togliere record a una struttura collegata
>**(debole)**: gli indirizzi dei record di una struttura collegata non sono necessariamente consecutivi

>[!warning]- Pro e contro
> Pro: la dimensione è variabile. È possibile aggiungere e rimuovere record in tempo costante
> Contro: accesso sequenziale ai dati

**Esempi di strutture collegate**

![[ASD/MOD I/img/img72.png|center|600]]

## Realizzazione di un dizionario

Metodo più semplice: **array non ordinato** (sovradimensionamento)

- **Insert**: costa $O(1)$ - inserisco dopo l'ultimo elemento
- **Search**: costa $O(n)$ - devo scorrere l'array
- **Delete**: costa $O(n)$ - delete = search+cancellazione

**Array ordinato**:

- **Search**: $O(log(n))$ - ricerca binaria
- **Insert**: $O(n)$ - Ho bisogno di $O(log(n))$ confronti per trovare la giusta posizione in cui inserire, $O(n)$ trasferimento per mantenere l'array ordinato
- **Delete**: $O(n)$

...e con le liste?

**Lista non ordinata**:

- **Insert**: costa $O(1)$
- **Search**: costa $O(n)$
- **Delete**: costa $O(n)$

**Lista ordinata**:

- **Insert**: costa $O(n)$ - devo manterene ordinata la lista
- **Search**: costa $O(n)$ - non posso usare la ricerca binaria
- **Delete**: costa $O(n)$

## Alberi
### Organizzazione gerarchica dei dati

![[ASD/MOD I/img/img73.png|center|600]]

I dati sono contenuti nei **nodi**, le relazioni gerarchiche definite dagli **archi** che li collegano.
Il **grado di un nodo** è il numero dei suoi figli

**Def.**
u si dice **antenato** di v se u è raggiungibile da v risalendo di padre in padre.
v si dice **discendente** di u se u è un antenato di v

### Rappresentazioni indicizzate di alberi
Le rappresentazioni più comuni sono basate su array: il **vettore dei padri** ed il **vettore posizionale**. L'idea è quella di rappresentare ogni nodo dell'albero con una cella di un array che contiene l'informazione associata al nodo, più eventuali indici che consentono di raggiungere altri nodi dell'albero. 

Le rappresentazioni indicizzate sono più facili da realizzare ma rendono più difficile l'inserimento e la cancellazioni di nodi dell'albero.

**Vettore dei padri**
Sia T un albero con n nodi numerati e P un array di dimensione n le cui celle contengono una coppia (info, parent), dove, per ogni indice i dell'array:
- **info** è il contenuto informativo del nodo i
- **parent** è l'indice (nell'array) del nodo padre di i

**Oss.**
Se v è la radice allora `P[v].parent = null`.

**Esempio vettore dei padri**

![[ASD/MOD I/img/img74.png|center|500]]

>[!info]- Osservazioni
>- num. arbitrario di figli
>- tempo per individuare il padre di un nodo: $O(1)$
>- tempo per individuare uno o più figli di un nodo: $O(n)$

**Vettore posizionale**
Nel caso partiolare di alberi d-ari completi con $d \geq 2$, si utilizza una struttura indicizzata dove ogni nodo ha una posizione prestabilita nella struttura. Sia T un albero d-ario con n nodi numerati. Un vettore posizionale è un array P di dimensione n tale che `P[v]` contiene l'informazione associata al nodo v, e tale che l'informazione associata all'i-esimo figlio di v è in posizione $P[d\cdot v +i]$, per i compreso tra 0 e d-1. 

In un albero binario completo, il figlio sinistro di v sarà in posizione 2v, mentre il figlio destro in posizione 2v+1. 

![[ASD/MOD I/img/img75.png|center|500]]

Utilizzando un vettore posizionale è possibile risalire in tempo costante $O(1)$ sia al proprio padre (che ha indice $\lfloor v/d \rfloor$ se v non è la radice), che a uno qualunque dei figli: per ogni nodo v, l'operazione `padre(v)` può essere quindi realizzata in tempo costante $O(1)$, mentre l'operazione `figli(v)` richiede tempo $O(grado(v))$.
 

### Rappresentazioni collegate di alberi
Sono più flessibili delle rappresentazioni indicizzate e permettono di supportare modifiche più efficientemente. L'idea è quella di rappresentare ogni nodo dell'albero con un record che contiene l'informazione associata al nodo, più altri puntatori che consentono di raggiungere altri nodi dell'albero.

![[ASD/MOD I/img/img76.png|center|500]]
![[ASD/MOD I/img/img77.png|center|500]]

### Visite di alberi

Vediamo degli algoritmi che consentono l'**accesso sistematico ai nodi e agli archi** di un albero.

Gli algoritmi di visita si distinguono in nodi base al particolare ordine di accesso ai nodi

#### Algoritmo di visita in profondità (DFS)

**Def.**
L'algoritmo di visita in profondità (DFS) parte da r (radice) e procede visitando nodi di figlio in figlio, fino a raggiungere una foglia. Retrocede poi al primo antenato che ha ancora figli non visitati (se esiste) e ripete il procedimento a partire da uno di quei figli.

![[ASD/MOD I/img/img78.png|center|400]]
![[ASD/MOD I/img/img79.png|center|400]]

Esempio di funzionamento: [Esempio algoritmo DFS](http://www.mat.uniroma2.it/~guala/cap3_2021.pdf) (da pag. 26 a pag. 45)

##### Complessità Temporale

^5eb32e

![[ASD/MOD I/img/img80.png|center|500]]

#### Versione ricorsiva

![[ASD/MOD I/img/img81.png|center|500]]

- **Visita in preordine**: si visita prima la radice, poi si effettuano chiamate ricorsive sul figlio sinistro e destro
- **Visita simmetrica**: si effettua la chiamata sul figlio sinistro, poi si visita la radice e infine si effettua la chiamata ricorsiva sul figlio destro (scambio riga 2 con 3)
- **Visita in postordine**: si effettuano le chaimate ricorsive sul figlio sinistro e destro e poi si visita la radice (sposta riga 2 dopo 4)

**Esempi**

![[ASD/MOD I/img/img82.png|center|500]]

La complessità temporale è la stessa della [[#^5eb32e|versione iterativa]].

#### Algoritmo di visita in ampiezza (BFS)

**Def.**
L'algoritmo di visita in ampiezza (BFS) parte da r (radice) e procede visitando nodi per livelli successivi.
Un nodo sul livello i può essere visitato solo se tutti i nodi sul livello i-1 sono stati visitati.

![[ASD/MOD I/img/img83.png|center|500]]

##### Pseudocodice

![[ASD/MOD I/img/img84.png|center|500]]
![[ASD/MOD I/img/img85.png|center|500]]

##### Complessità temporale

![[ASD/MOD I/img/img86.png|center|500]]

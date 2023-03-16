----
# Informazioni utili: tenere il tempo

![[img174.png|center|500]]

pre(v) = tempo in cui viene "scoperto" v
post(v) = tempo in cui si "abbandona" v

## Quando non tutti i nodi sono raggiungibili dal punto di partenza

![[img175.png|center|400]]

### Esempio

![[img176.png|center|500]]


### Proprietà

- Per ogni coppia di nodi u e v, gli intervalli $[pre(u),post(u)]$ e $[pre(v),post(v)]$ o sono disgiunti o l'uno è contenuto nell'altro.
- u è antenato di v nell'albero DFS, se $pre(u)\lt pre(v)\lt post(v)\lt post(u)$ condizione che rappresentiamo così:

![[img177.png|center|300]]

Possiamo usare i tempi di visiita per riconoscere il tipo di un generico arco (u,v) nel grafo?

![[img178.png|center|500]]


# Cicli, DAG e ordinamenti topologici

## Riconoscere la presenza di un ciclo in un grafo diretto

**Algoritmo**: fai una visita DFS e controlla se c'è un arco all'indietro

>[!important]- Proprietà
>Un grafo diretto G ha un ciclo se e solo se la visita DFS rivela un arco all'indietro

($\impliedby$): se c'è arco all'indietro, chairamente G ha un ciclo
($\implies$): se c'è ciclo $\lt v_{0}, \:v_{1},\:...,\:v_{k}=v_{0}\gt$ 
sia $v_i$ il primo nodo scoperto nella visita:
- $v_i$ termina la visita dopo che $v_{i+1}$ ha terminato la sua
- $v_{i+1}$ termina la visita dopo che $v_{i+2}$ ha terminato la sua e così via...
- quindi, per transitività, $v_i$ termina la visita dopo che $v_{i-1}$ ha terminato la sua
- $\implies (v_{i-1},v_i)$ è un arco all'indietro

![[img179.png|center|250]]

>[!important]- Definizione **DAG**
>Un **grafo diretto aciclico (DAG)** è un grafo diretto G che non contiene cicli (diretti)

^eeaf67

>[!important]- Definizione Ordinamento Topologico
>Un **ordinamento topologico** di un grafo diretto G = (V, E) è una funzione biettiva $\sigma:V\to\{1,2,...,n\}$ tale che, per ogni arco $(u,\:v)\in E,\:\sigma(u)\lt\sigma(v)$

![[img180.png|center|500]]


### Quali grafi (diretti) ammettono un ordinamento topologico?

>[!important]- Teorema
>Un grafo diretto G ammette un ordinamento topologico se e solo se G è un [[#^eeaf67|DAG]]

**Dim. ($\implies$)**

per assurdo: sia $\sigma$ un ordinamento topologico di G, e sia $\lt v_0,v_1,..,v_k=v_0\gt$ un ciclo, allora $\sigma(v_0)\lt\sigma(v_1)\lt...\lt\sigma(v_{k-1})\lt\sigma(v_k)=\sigma(v_0)$
$\square$

**Dim. ($\impliedby$)**: ... adesso diamo un algoritmo costruttivo

#### Calcolare ordinamento topologico

![[img181.png|center|500]]

**Esempio**

![[img182.png|center|500]]

##### Correttezza

Per ogni coppia di nodi u e v, gli intervalli $[pre(u),post(u)]$ e $[pre(v),post(v)]$ o sono disgiunti o l'uno è contenuto nell'altro

![[img183.png|center|500]]

##### Algoritmo alternativo

![[img184.png|center|500]]

$(*)$ perchè altrimenti in $\hat G$ ogni vertice deve avere almeno un arco entrante, e quindi posso trovare un ciclo percorrendo archi entranti a ritroso, e quindi G non può essere aciclico

**Tempo di esecuzione (con liste di adiacenza)**: $\Theta(n+m)$

[Esempio](http://www.mat.uniroma2.it/~guala/usi_dfs_2021.pdf#page=17)

## Componenti fortemente connesse

Una **componente fortemente connessa** di un grafo G = (V, E) è un insieme **[[#^f18f9e|massimale]]** di vertici $C\subseteq V$ tale che per ogni coppia di nodi **u** e **v** in C, **u** è raggiungibile da **v** e **v** è raggiungibile da **u**

**massimale**: se si aggiunge un qualsiasi vertice a C la proprietà non è più vera ^f18f9e

Grafo delle componenti fortemente connesse di G

![[img185.png|center|300]]

è sempre un **DAG**

![[img186.png|center|500]]

### Come si possono calcolare le componenti fortemente connesse di un grafo diretto?


**Proprietà 1**: se si esegue la procedura [[Capitolo 11.2 - Strutture dati per rappresentare grafi#Pseudo-codice|visitaDFSricorsiva]] a partire da un nodo u, la procedura termina dopo che tutti i nodi raggiungibili da u sono stati visitati

![[img185.png|center|300]]

**Idea**: eseguire una visita a partire da un nodo di una componente pozzo, "eliminare" la componente e ripetere

#### Come trovo una componente pozzo?

**Proprietà 2**: se C e C' sono due componenti e c'è un arco da un nodo in C verso uno in C', allora il più grande valore post() in C è maggiore del più alto valore di post() di C'

**Dim.** 
- se la DFS visita prima C' di C: banale
- se visita prima C, allora si ferma dopo che ha raggiunto tutti i nodi di C e C' e termina su un nodo di C

**Proprietà 3**: il nodo che riceve da una visita DFS il valore più grande di post() appartiene a una componente sorgente

![[img187.png|center|300]]

##### Ma avevamo bisogno di una componente pozzo?

**Idea**: invertiamo gli archi!

![[img188.png|center|300]]

**Nota bene:** le componenti fortemente connesse sono le stesse! **(perchè?)**

![[img189.png|center|300]]


![[img190.png|center|500]]
![[img191.png|center|500]]
![[img192.png|center|500]]






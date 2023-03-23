**# Esercizio Grafo
>edge(a,b).                          Query: path(b,X).
  edge(b,c).
  edge(c,d).
  edge(d,e).
  edge(a,e).
  edge(f,e).
>  
  path(X,Y):-
	edge(X,Y).

# Esecuzione programma
- Analizza i fatti/regole dall'alto verso il basso (conta l'ordine)

- Utilizzo del BACKTRACKING per otrnare indietro a prima che una variabile fosseujnificata o che una certa regola fosse esplorata

- Utilizzo della ricorsione per chiamare le altre regole

- Per avere altre risposte, e quindi forzare il backtracking anche se il programma ne ha già una che funziona, basta premere;

# Letture dei programmi
- Dichiarativa
	- I problemi sono risolubili attraverso la scrittura di un insieme di regole

- Procedurale 
	- I problemi sono risolubili attraverso la scrittura sequenze di istruzioni 

# Lettura Dichiarativa
- Lettura classica e più corretta:

- Una clausola con variabile come
- grandparent (X,Y):-
	- parent(X,Z),
	- parent(Z,Y).

- Viene letta:
- Per ogni X,Y,Z
	- grandparent(X,Y) è vero se
		- paren(X,Z) è vero e 
		- parent(Z,Y) è vero

- Una query come:
	- ?- grandparent(X,mario).

- Viene letta come:
	- Esiste un X tale che grandparent(X,mario) è vero?

# Lettura Procedurale
- E' una lettura necessaria:

- Una clausola con variabile come 
	- grandparent(X,Y):-
		- parent(X,Z),
		- parent(Z,Y).

- Può essere anche letta:
	- Per qualsiasi valore delle varibili X,Y,Z
		- per soddisfare il goal grandparent(X,Y) soddisfa prima il goal parent(X,Z) e poi il parent (Z,Y).

- Una query come:
	- ?- grandparent(X,mario).

- Viene letta come:
	- Soddisfare il goal grandparent(X,mario) trovando il valore della varibile X?

# Osservazioni 
- L'ordine delle clausole è importante

- path(X,Y):- 
	- path(X,Z),
	- path(Z,Y).

- path(X,Y):-
	- edge(X,Y).

- GENERA UN LOOP INFINITO!!!

# Liste
- In prolog le liste sono molto utilizzate

- Lista: sequenza di vari elementi (anche ripetuti), che possono essere a loro volta delle liste

- Es: [primo,secondo,[primo2, secondo2]].
	- Lista composta da tre elementi 
	- Il terzo elemento è a sua volta una lista

- La lista può essere vuota (Importante!)

- Ogni lista è composta da due parti:
	- Head: è il primo elemento 
	- Tail: è il resto della lista (a sua volta una lista)

- Le liste possono essere rappresentate in due modi: 
	- [a,b,c,d]
	- .(a, .(b, .(c, .(d, []))))

- Per estrarre la testa di una lista si usa la notazione:
	- [H | T], dove H è la testa e T è la lista rimanente senza il primo elemento

- Si possono estrarre più elementi contemporaneamente:
	- [H1, H2 | T]
	- H1 e H2 sono il primo e il secondo elemento
	- T è la lista rimanente (la lista di partenza meno i primi due elementi)

# Operatori su liste
- member(?Elem, ?List)
	- Restituisce true se Elem si trova nella lista, può essere usato in vari modi:
			- member(b, [a,b,c,d]) $\rightarrow$ true
			- member(e, [a,b,c,d]) $\rightarrow$ false
			- member(X, [a,b,c,d]) $\rightarrow$ X = a ; X = b ; ...
			- member(c, [a,b,c,d]) $\rightarrow$ X = c

- member(?Elem, ?List)

- possibile implementazione:
	- member2(X, [X | __]).
	- member2(X, [_ | T]):-
		- member2(X,T).

- append(?List1, ?List2, ?List1andList2)
	- List1andList2 è la concatenazione di List1 e List2. Vari utilizzi 
		- append([a,b],[c,d], X). -> X = [a,b,c,d] 
		- append([a,b],X, [a,b,c,d]). -> X = [c,d] 
		- append([a,b],[X,d], [a,b,c,d]). -> X = c 
		- append(X, Y, [a,b,c,d]).

# Esercizio
- Scrivere una possibile implementazione della append

- Scrivere la regola per invertire tutti gli elementi di una list, in modo da avere:
	- ?- reversed([a,b,c,d,e,f], X).
	- X=[f,e,d,c,b,a]

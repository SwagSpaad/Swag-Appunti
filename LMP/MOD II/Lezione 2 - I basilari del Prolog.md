# Elementi del Prolog
> - Termini
> - Predicati
> - Clausole
> - Programma logico

# Termini
> - Atomi: nomi che iniziano con la lettera MINUSCOLA, sequenza di caratteri tra ' ', numeri preceduti da caratteri
> 	- Andrea
> 	- 'Corso del Prolog'
> 	- c1p8
> - Numeri
> 	- 12345
> - Variabili: nomi che iniziano con la lettera MAIUSCOLA o con _
> 	- Tizio
> 	- _ andrea
> 	- _
> - Termini composti
> 	- Somma(1,2,X)
> 	- 1+2

# Predicati
>- Espressi tramite la notazione $f(t_1,....,t_n)$
>- f è un atomo che prende il nome di funtore
>- $t_1,....,t_n$ sono gli argomenti e sono dei termini (predicato f con n argomenti, ha arità n)

# Clausole
> - Le clausole: fatti e regole
> - I fatti sono regole senza corpo 
> - Fatti: 
> 	  parent(ben, jim)
> 	  friend(luke, daisy)
> - Regole:
> 	  grandparent(X, Y):-
> 	  parent(X, Z),
> 	  parent(Z, Y).

# Regole
> - Head :-Body . significa che affinchè la Head sia vera deve essere vero il Body (e quindi i predicati che lo compongono)
> 	- Nel Body ci sono 1 o più predicati separati da , (and) o da ; (or)
> - Ogni regola termina con .

# Fatti 
>- Un fatto è un predicato seguito da .
>- Un fatto può essere composto da più termini
>	- amico(fratello(alice, X), bob).

# Programma logico 
> 	- Insieme di regole/fatti
> 	- Risponde alle query con true o false e assegna dei valori alle variabili

# Esempio: Famiglia
> parent(anne, bill).
> parent(anne, charlie).
> parent(bill, donnie).
> grandparent(X, Y):-
> 	parent(X, Z),
> 	parent(Z, Y).
> 
	- Query:
> 	?- parent(anne, bill).
> - Risposta: 
> 	true 
> - Query
> 	?- parent(anne, X).
> - Risposta:
> 	X = bill
> 	(premo ; )
> 	X = charlie
> 	(prmeo ; )
> 	false
> 
>- Query:
>	?- parent(X, Y).
>- Risposta:
>	X = anne, Y = bill
>	(premo ; )
>	X = anne, Y = charlie
>	(premo ; )
>	X = bill, Y = donnie 
>	(premo ; )
>	false

# Esecuzione del programma
>- Prolog cerca nel proprio database di regole e fatti, quelli che soddisfano la nostra query, istanziando le variabili
>- Ogni variabile, una volta istanziata (unificata), non può assumere un secondo valore (a differenza dei linguaggi classici di programmazione, come Java, C, C++, ecc)
## Esempio
>Dati i fatti:  
	- parent( pam, bob). 
	- parent( bob, tom). 
	- parent( tom, ann). 
	- parent( bob, jerry).
	- female(pam). 
	- male(bob).  
	- male(tom).  
	- female(ann). 
	- male(jerry).

>E le regole 
>	father(X,Y):-
		male(X), 
		parent(X,Y).
	mother(X,Y):- 
		female(X),
		parent(X,Y). 
	?- mother(ann,X).

>E le regole 
>	father(X,Y):-
		male(X), 
		parent(X,Y).
	mother(X,Y):- 
		female(X), 
		parent(X,Y).

>Che risposta ho alle seguenti interrogazioni? 
	– ?- mother(X,Y).  
	– ?- father(X,Y).  
	– ?- mother(X,ann).
	– ?- father(X,ann).
	– ?- mother(ann,X).

# Comandi Utili
> edit.
> 	- Apre l'editor per modificare/aggiungere fatti e regole al file in esame
> consult('nome_file').
> 	- Carica un file con i suoi dati
> reconsult ('nome_file').
> 	- Ricarica il file con i suoi dati 
> trace / notrace .
> 	- Abilita / disabilita la stampa di tutti i passaggi intermedi (molto utile per seguire lo svolgersi del programma)

# Ordine dei predicati nelle regole 
> - Vedere l'esempio contenuto in prolog-1.pl
> - Provare le query (è 1,2,3 e 4):
> 	- pred(pam, ann).
> 	- pred(pam, andrea).


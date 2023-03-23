# Strutture Dati

- Le strutture dati, anche complesse, sono alla base dei vari linguaggi di programmazione 
- In Prolog è possibile creare ed utilizzarle in modo palese
- Un Database in Prolog può essere rappresentato come un elenco di fatti
- Per comprendere come creare/usare le strutture dati in Prolog, useremo i seguenti esempi:
	- Famiglia
	- Automa non deterministico
	- Problema delle 8 Regine

# Famiglia

- Una famiglia può essere rappresentata da un fatto, family, con 3 argomenti:
	- Padre
	- Madre
	- Figli

- Gli elementi della famiglia sono delle persone (person), rappresentati a sua volta da dei termini complessi formati da 4 elementi: nome, cognome, data di nascita e salario
- Rappresentazione della famiglia Smith
- family(
	- person(bob,smith,date(7,may,1968),30000),
	- person(ann,smith,date(18,july,1970),32000),
	- [person(dave,smith,date(1,june,1984),0),
	- person(edna,smith,date(25,may,1990),0)]]).

- Possiamo effettuare varie query, basandoci non solo sui valori ma anche sulla struttura stessa
- family (person(_ ,fox, _ , _ ), _ , _ ). si riferisce alla famiglia fox, usando solo il cognome del padre e nessuna altra informazioni
- Esiste un altro modo per riferirsi alla famiglia fox?
- family(_ , _ ,[ _ , _ , _ ] ). indica una famiglia con 3 figli
- Come si può indicare una famiglia con almeno 3 figli?
- Creiamo ora delle regole più "generiche" che però si appoggiano sempre al termine family

# Regole per family

husband(X):-
	family(X, _ , _ ).

wife(X):-
	family( _ ,X ,_ ).

child(X):-
	family( _ , _ , Childern),
	member(X, Children).

exists(X):-
	husband(X)
	;
	wife(X)
	;
	child(X).

salary(person( _ , _ , _ , S), S).

dateOfBirth(person(_ , _ , Date, _ ), Date).

# Possibili query

- ?- exists(person(mario, rossi, _ , _ )).
- ?- exists(person(Name, Surname, _ , _ )).
- ?- child(X),
	dateOFbirth(X, date( _ , _ , Y)),
	Y < 2000.
- ?- exists (X),
	salary(X, Y),
	Y > 30000.

# Automa non deterministico

![[Pasted image 20230323122219.png]]

finals(s3).

trans(s1, a, s1).
trans(s1, a , s2).
trans(s1, b, s1).
trans(s2, b, s3).
trans(s3, b, s2).
trans(s1, a, s4).

silent(s2, s4).
silent(s3, s1).

accepts(State, []):- 
	final(State).

accepts(State, [X|Rest]):- 
	trans(State, X, State1), 
	accepts(State1, Rest).

accepts(State, Rest):- 
	silent(State, State1), 
	accepts(State1, Rest).

 - ?- accepts(s1, [a,a,a,b]).
	– true

- ?- accepts(S, [a,b]).
	S=s1;
	S=s3;  

- ?- accepts(s1, [X1,X2,X3]).
	X1=a X2=a X3=b
	...  

- ?- String=[_,_,_], accepts(s1, String).
	String = [a,a,b];
	...

# Problema delle 8 regime

- Posizionare 8 regina su una scacchiera vuota in modo che nessuna possa mangiare o essere mangiata da un'altra
- Esistono  varie soluzione in Prolog, qui ne viene presentata una semplice con il minimo numero di variabili

# 8 Regine

solution( [] ).

solution( [X/Y | Others] ) :-        % First queen at X/Y, other queens at Others
solution( Others),  
member( Y, [1,2,3,4,5,6,7,8] ),  
noattack( X/Y, Others).               % First queen does not attack others

noattack( _ , [] ).                              % Nothing to attack

noattack( X/Y, [X1/Y1 | Others] ) :-  
Y = \ = Y1,                                             % Different Y-coordinates 
Y1-Y = \ = X1-X,                                 % Different diagonals 
Y1-Y = \ = X-X1,  
noattack( X/Y, Others).

% A solution template

template( [1/Y1,2/Y2,3/Y3,4/Y4,5/Y5,6/Y6,7/Y7,8/Y8] ).

# Operatori

- In Prolog è possibile definire nuovi operatori, ma ne esistono già alcuni definiti (esempio gli operatori aritmetici)
- 1 * 2+3 * 4 ha i due operatori + e *
- la scrittura in Prolog sarebbe:
	+( * (1,2),  * (3,4))

![[Pasted image 20230323143627.png]]

# Definire un operatore

- Ogni operatore ha una sua priorità
- a + b * c come deve essere letto? 
	+(a, * (b,c) ?  
	**( +(a,b), c) ?
- Nel senso comune trasmessoci, * lega di più di +,

![[Pasted image 20230323143603.png]]

Codificare la priorità: l’albero delle interpretazioni ha priorità decrescenti
+ ha priorità 500  
* ha priorità 400  
(e quindi + ha priorità più alta di * )

![[Pasted image 20230323143538.png]]

- :- op(700, yfx, somma).
- 9 somma 5 somma 7

![[Pasted image 20230323143442.png]]

- Quello a sinistra è corretto, perché?

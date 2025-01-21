```prolog
directlyIn(katarina, olga).
directlyIn(olga, natasha).
directlyIn(natasha, irina).

in(X, Y):-
    directlyIn(X, Y).

in(X, Y):-
    directlyIn(X, Z),
    in(Z, Y).

/*
 * travelFromTo è il predicato che date due destinazioni
 * se esiste una sequenza di treni diretti per andare da
 * A a B, allora restituisce true
 */
directTrain(saarbruecken,dudweiler).
directTrain(forbach,saarbruecken).
directTrain(freyming,forbach).
directTrain(stAvold,freyming).
directTrain(fahlquemont,stAvold).
directTrain(metz,fahlquemont).
directTrain(nancy,metz).

travelFromTo(X, Y):-
    directTrain(X, Y).

travelFromTo(X, Y):-
    directTrain(X, Z),
    travelFromTo(Z, Y).


/*
 * restituisce true se diamo in input una lista di sole a
 * e una lista di sole b di lunghezza uguale
 */
a2b([],[]).

a2b([a|T1], [b|T2]):-
    a2b(T1, T2).

% vedi se X è il secondo elemento di una lista
second(X, [_, X|_]).

% restituisce true se le due liste sono uguali ad eccezione
% dei primi due elementi che devono essere scambiati
swap12([X, Y|T],[Y, X|T]).



tran(eins,one).
tran(zwei,two).
tran(drei,three).
tran(vier,four).
tran(fuenf,five).
tran(sechs,six).
tran(sieben,seven).
tran(acht,eight).
tran(neun,nine).

/*
 * data una lista di numeri scritti in tedesco
 * traduci i numeri in inglese nella seconda lista
 */
listtran([], []).

listtran([X|T], [Y|T1]):-
 	tran(X, Y),   
    listtran(T, T1).

/*
 * data a sinistra una lista a destra restituisci
 * una lista con ogni elemento scritto due volte
 */
twice([], []).

twice([X|T], [X, X|T1]):-
    twice(T, T1).

myMember(X, [X|_]).
myMember(X, [_|T]):-
    myMember(X, T).

/*
 * tre liste come argomento, la terza lista combina gli elementi 
 * della prima e della seconda
 */

combine([],[],[]).

combine([H1|T1], [H2|T2], [H1,H2|Y]):-
    combine(T1, T2, Y).

/*
 * append di due liste 
 */

myAppend([], L, L).

myAppend([H|T], L, [H|T1]):-
    myAppend(T, L, T1).

/*
 * reverse di una lista
 */
reversed([], []).

reversed([H|T1], L):-
    reversed(T1, RT),
    append(RT, [H], L).

/*
 * dati due numeri A e B, Max è il massimo tra i due
 */
max(A, B, Max) :-    
    var(A), % A non è istanziato
    !, % Impedisce di passare ad altre clausole se questa è vera    
    Max >= B,
    A = Max.

max(A, B, Max) :-    
    var(B), % B non è istanziato
    !,    
    Max >= A,
    B = Max.

max(A, B, Max) :-
    A >= B,    Max = A.

max(A, B, Max) :-
    A < B,    Max = B.

/*
 * data una lista Max è il massimo della lista
 */
% caso base
% se la lista ha un solo elemento, il max è quell'elemento
maxInList([H|[]], H). 

% caso ricorsivo
% con almeno due elementi nella lista
maxInList([H1, H2|T], Max):-
	% confronta i primi due e unifica a MaxTemp
    max(H1, H2, MaxTemp),    
    % chiamata ricorsiva aggiungendo alla head della lista
    % MaxTemp
    maxInList([MaxTemp|T], Max).

% struttura dati famiglia

family(person(bob, smith, date(7,may,1968),30000),
       person(ann, smith, date(18, july, 1970), 32000),
       [person(dave, smith, date(1, june, 1984), 0),
        person(edna, smith, date(25, may, 1990), 0)]).

husband(X):-
    family(X,_,_).

wife(X):-
    family(_,X,_).

child(X):-
    family(_,_,Children), % estraggo array dei figli
    member(X,Children). % verifico se X fa parte dei figli

% automa non deterministico

final(s3). %stato finale

trans(s1, a, s1). %da s1 leggo a torno in s1
trans(s1, a, s2).
trans(s1, b, s1).
trans(s2, b, s3).
trans(s3, b, s2).
trans(s1, a, s4).

% epsilon transizione
silent(s2, s4).
silent(s3, s1).

/*
 * caso base:
 * l'automa accetta se i caratteri da consumare sono finiti
 * e lo stato in cui si trova è finale
 */
accepts(State, []):- 
    final(State).

accepts(State, [X|Rest]):-
    trans(State, X, State1),
    accepts(State1, Rest).

accepts(State, Rest):-
    silent(State, State1),
    accepts(State1, Rest).

not_member(_,[]).

not_member(X,[H|T]):-
    X \= H, % X non si unifica ad H, quindi X è diverso da H
    not_member(X, T).

/*
 * caso base:
 * il numero di volte cui compare un qualsiasi elemento 
 * in una lista vuota è zero
 */
count(_, [], 0).

/*
 * caso ricorsivo 1: l'elemento compare è l'Head
 */
count(X, [X|T], NumVolte):-
    atomic(X), % X è un atomo
    count(X, T, N), % chiamata ricorsiva sulla coda 
    NumVolte is N + 1, % NumVolte è il risultato degli N calcolati ricorsivamente + 1
    !.

/*
 * caso ricorsivo 2: l'elemento non è l'Head
 */
count(X, [H|T], NumVolte):-
    X \= H, % X non si unifica con H, X non è H
    count(X, T, NumVolte), % chiamata sulla Tail 
    !.

myFunctor(Term, Fun, Arity):-
    Term =.. [Functor|Args], % Decompone term in Functor e Argomenti
    Fun = Functor, % Fun viene unificato al Fucntor estratto
    length(Args, Arity). % Arity è il numero di elementi in Args
    

% Caso base: Arg = 1, restituisce il primo elemento della lista di argomenti
myArg(1, Term, Arg) :-
    !,
    Term =.. [_|Args], % Decompone il termine in functor e argomenti
    Args = [Arg|_].  % Prende il primo elemento degli argomenti

% Caso ricorsivo: Arg > 1, scorre la lista degli argomenti
myArg(Val, Term, Arg) :-
    Val > 1,
    Term =.. [_|Args], % Decompone il termine
    NewArg is Val - 1, % Riduce la posizione richiesta
    Args = [_|Rest],   % Scorre gli argomenti
    myArg(NewArg, Rest, Arg). % Richiama ricorsivamente

padre(gino,pino).
padre(gino,rino).
padre(gino,dino).

/* trova tutti i figli di un padre dato e li inserisce in una lista
figli(X,Y):-
	assert(figli_appoggio([])), 
	figli_w(X,Y).
	
figli_w(X,_):-
	padre(X,F),
	figli_appoggio(Y),
	assert(figli_appoggio([F|Y])),
	retract(figli_appoggio(Y)),
	fail.
	
figli_w(X,Y):-
	!,
	figli_appoggio(Y),
	retract(figli_appoggio(Y)).
*/

/* 
 * questa soluzione crea una lista di figli che rendono vera
 * la query figli(padre, Figli)
 * Figlio è la variabile che deve essere unificata
 * Padre viene unificata quando viene inviata la query
 */
figli(Padre, Figli):-
    bagof(Figlio, padre(Padre, Figlio), Figli). 

/*
 * ordina(Lista, ListaOrdinata)
 * è true se ListaOrdinata contiene tutti gli elementi 
 * di Lista ed è ordinata
 */

ordina(Lista, ListaOrdinata):-
    permutazione(Lista, ListaOrdinata), % true se ListaOrdinata è una permutazione di Lista
    ordinata(ListaOrdinata). % true se ListaOrdinata è ordinata

ordinata([]). % una lista vuota è ordinata
ordinata([_]). % una lista di un elemento è ordinata 
ordinata([H1, H2|T]):-
    H1 =< H2,
    !,
    ordinata([H2|T]).


permutazione([],[]).
permutazione([X|RX],Y):-	
    permutazione(RX,RY),	
    member_new(X,Y,RY).
member_new(X,[X|R],R).
member_new(X,[P|R],[P|RR]):-	
    member_new(X,R,RR).
```


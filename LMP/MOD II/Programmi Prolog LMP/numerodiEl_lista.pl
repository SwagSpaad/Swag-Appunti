/*
 * numerodiEL(L,El,N) -> dove L è la lista, El è il numero da cercare nella
 * lista ed N è il numero di volte che El si trova nella lista.
*/

numerodiEl([],_,0).
numerodiEl([El|T],El,N):-
    numerodiEl(T,El,M),
    N is M + 1.
numerodiEl([X|T],El,N):-
    X \= El,
    numerodiEl(T,El,N).
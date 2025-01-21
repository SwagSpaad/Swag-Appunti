/*
 * rivoltata(L,RL)->prende due argomenti L una lista generica 
 * e RL una lista vuota o che conterrà la lista L ribaltata.  
*/

rivoltata([],[]).

rivoltata([H|T],RL):-
    append(RT,[H],RL),
    rivoltata(T,RT).
	
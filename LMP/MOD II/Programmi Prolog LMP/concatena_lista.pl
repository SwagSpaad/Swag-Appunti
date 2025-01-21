/*
 * Concatenare due liste
 */

concatena([],A,A).

concatena([H|T],B,[H|L]):-
    concatena(T,B,L).
		    
    
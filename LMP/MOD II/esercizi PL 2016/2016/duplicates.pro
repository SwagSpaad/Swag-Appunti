/* duplicates(L,RL) */
/*
duplicates([],[]).

duplicates([A|L],[A|RL]):-
	\+member(A,L),
	duplicates(L,RL).

duplicates([A|L],RL):-
	member(A,L),
	duplicates(L,RL).

*/

duplicates([],[]).


duplicates([A|L],RL):-
	member(A,L),!,
	duplicates(L,RL).

duplicates([A|L],[A|RL]):-
	duplicates(L,RL).


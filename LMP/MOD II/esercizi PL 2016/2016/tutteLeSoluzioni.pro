:- dynamic figliRaccolti/1.

padre(gino,pino).
padre(gino,rino).
padre(gino,rino).
padre(gino,dino).

padre(gianni,pinotto).
padre(gianni,gennaro).


figliRaccolti([]).

inserisciFiglio(F):-
	figliRaccolti(RF),
	write(RF),write(' -> '),
	retract(figliRaccolti(RF)),
	asserta(figliRaccolti([F|RF])),
	figliRaccolti(RF1),
	write(RF1),nl.
figli(X,_):-
	padre(X,F),
	inserisciFiglio(F),
	fail.
figli(_,L):-
	figliRaccolti(L),
	retract(figliRaccolti(L)),
	asserta(figliRaccolti([])).
	


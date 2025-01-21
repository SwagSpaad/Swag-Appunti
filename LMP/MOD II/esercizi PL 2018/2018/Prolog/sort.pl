

myquicksort([],[]).
myquicksort([A],[A]).

myquicksort(L,LO):-
	pivot(L,P),
	possopartizionare(L,L1,L2,P),
	write('--------'),nl,
	write(L1),nl,
	write(L2),nl,

	myquicksort(L1,LO1),
	myquicksort(L2,LO2),
	append(LO1,LO2,LO).
	
pivot([P|_],P).

possopartizionare([],[],[],_).

possopartizionare([H|T],[H|T1],L2,P):-
 H =< P,
 possopartizionare(T,T1,L2,P).


possopartizionare([H|T],L1,[H|T2],P):-
 H > P,
 possopartizionare(T,L1,T2,P).


	
	

/*
	
myquicksort(L=[A],LO):-
	pivot(L=[A],P=A),
	possopartizionare(L=[A],L1=[A],L2=[],P=A),
	myquicksort(L1=[A],LO1),
	myquicksort(L2,LO2),
	append(LO1,LO2,LO).




myquicksort(L=[2,3,6],LO):-
	pivot(L=[2,3,6],P=2),
	possopartizionare(L=[2,3,6],L1=[2],L2=[3,6],P=2),
	write('--------'),nl,
	write(L1),nl,
	write(L2),nl,

	myquicksort(L1=[2],LO1=[2]),
	myquicksort(L2=[3,6],LO2),
	append(LO1,LO2,LO).

	
myquicksort(L=[3,6],LO):-
	pivot(L=[3,6],P=3),
	possopartizionare(L=[3,6],L1=[3],L2=[6],P),
	write('--------'),nl,
	write(L1),nl,
	write(L2),nl,

	myquicksort(L1,LO1),
	myquicksort(L2,LO2),
	append(LO1,LO2,LO).
*/
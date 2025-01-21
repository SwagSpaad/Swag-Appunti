a(A):-
	b(A),!,
	c(A).
	
	
	
b(a).
b(b).
c(b).


min1(X,Y,X) :-
	X =< Y.
min1(X,Y,Y) :-
	X > Y.

	
min2(X,Y,X) :-
	X =< Y.
min2(_,Y,Y).

min3(X,Y,X) :-
	X =< Y.
min3(_,Y,Y):-!.


min4(X,Y,X) :-
	!, X =< Y.
min4(_,Y,Y).

min5(X,Y,X) :-
	X =< Y,!.
min5(_,Y,Y).


not_member(A,L):-
	member(A,L), !, fail.
	
not_member(A,L).


notnot(PRED):-
	PRED, !, fail.
	
notnot(_).


:- dynamic f/2.

f(0,0).

f(1,1).

f(X,N):-
	Xm1 is X-1,
	Xm2 is X-2,
	f(Xm2,K),
	f(Xm1,M),
	N is M  + K,
	asserta(f(X,N)).
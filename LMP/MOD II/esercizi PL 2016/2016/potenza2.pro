

:- op(50,xfx,potenza).
:- op(100,xfx,is2).

potenzac(_,0,1).
potenzac(N,M,NelevatoM):-
	nonvar(N),
	nonvar(M),
	Mmeno1 is M - 1,
	potenzac(N,Mmeno1,NelevatoMmeno1),
	NelevatoM is NelevatoMmeno1 * N.

potenzac(N,M,NelevatoM):-
	nonvar(N),
	nonvar(NelevatoM),
	NelevatoMmeno1 is  NelevatoM/ N,
	potenzac(N,Mmeno1,NelevatoMmeno1),
	M is Mmeno1 + 1.

	
is2(A,potenza(N,M)):-
	potenzac(N,M,A).

/*
*/
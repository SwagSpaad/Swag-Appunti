
/*funtore(F,FUNC)*/
funtore(F,FUNC):-
	F =.. [FUNC|_].


/* num_args(F,N) */
num_args(F,N):-
	F =.. L,
	lunghezza(L,M),
	N is M - 1.
	
lunghezza([],0).
lunghezza([_|R],M):-
	lunghezza(R,N),
	M is N + 1.
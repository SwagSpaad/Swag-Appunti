conta(_,[],0).

conta(X,[Y|L],N):-
	nonvar(Y),
	X = Y,
	conta(X,L,M),
	N is M + 1.


conta(X,[Y|L],N):-
	var(Y),
	conta(X,L,N).
	
conta(X,[Y|L],N):-
	X \= Y,
	conta(X,L,N).


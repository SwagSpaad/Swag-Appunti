

padreDi(mario,giovanni).
padreDi(giovanni,gennaro).
padreDi(mario,gianni).
padreDi(pino,rino).
padreDi(rino,gino).

nonnoDi(X,Y):-
	padreDi(X,Z),
	padreDi(Z,Y).

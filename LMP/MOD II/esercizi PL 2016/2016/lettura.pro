cube:-
	write('Next number, please:'),
	read(X),
	process(X).

process(stop):-
	write('Mi fermo'),nl,
	!.

process(N):-
	C is N**3,
	write('Cube of '), write(N),write(' is '),
	write(C), nl,
	cube.

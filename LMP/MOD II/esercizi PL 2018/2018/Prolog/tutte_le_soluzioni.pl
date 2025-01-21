:- dynamic listaAppoggio/1.


f(a).
f(b).
f(c).
f(d).


/*tutte_le_sol(L).*/

listaAppoggio([]).

tutte_le_sol(_):-
	f(A),
	write(A),nl,
	listaAppoggio(L1),
	retract(listaAppoggio(L1)),
	assert(listaAppoggio([A|L1])),
	fail.

tutte_le_sol(L):-
	listaAppoggio(L),
	retract(listaAppoggio(L)),
	assert(listaAppoggio([])).
	



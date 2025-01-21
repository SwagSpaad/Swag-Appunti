

/*	f(a,b)	=.. [f,a,b]*/

/*functor_1(Funzione,Funtore).*/
functor_1(Funzione,Funtore):-
	Funzione =.. [Funtore|_].
	
arg_i(Funzione,Arg,I):-
	Funzione =.. [_|Args],
	elem_i(Arg,Args,I1),
	I1 is I - 1.

elem_i(Arg,[Arg|_],0).
	
elem_i(Arg,[_|Rest],I):-
	elem_i(Arg,Rest,I1),
	I is I1 + 1.

arita(Funzione,L):-
	Funzione =.. [_|Args],
	length(Args,L).





	
	
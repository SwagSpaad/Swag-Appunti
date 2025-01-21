/*count(X,L,NUM_VOLTE)*/

count(_,[],0).

count(X,[H|L],N):-
	count(X,L,N),
	X \= H.

count(X,[X|L],N):-
	count(X,L,N1),
	N is N1 + 1.


	
count1(_,[],0).

count1(X,[X|L],N):-
	!,
	count1(X,L,N1),
	N is N1 + 1.

count1(X,[_|L],N):-
	count1(X,L,N).

notequal(X,Y):-	
	\+ (X = Y).
	
count2(_,[],0).

count2(X,[Y|L],N):-
	atom(Y),
	Y = X, 
	!,
	count2(X,L,N1),
	N is N1 + 1.

count2(X,[Y|L],N):-
	atom(Y),
	count2(X,L,N).

member1(X,[X|_]).


member1(X,[_|R]):-
	member1(X,R).
	
append1([],L,L).
append1([A|L1],L,[A|L2]):-
	append1(L1,L,L2).
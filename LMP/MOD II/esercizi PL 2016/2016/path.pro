e(a,b).
e(b,c).
e(c,d).
e(d,e).
e(f,e).
e(a,e).


path(X,Y):- e(X,Y).
path(X,Y):- e(X,Z), path(Z,Y).

path(X,Y,[X,Y]):- e(X,Y).
path(X,Y,[X|R]):- e(X,Z), path(Z,Y,R).

path(_,_,_,L):- L < 0, !, fail.
path(X,Y,[X,Y],L):-  e(X,Y).
path(X,Y,[X|R],L):- e(X,Z), L1 is L - 1, path(Z,Y,R,L1).

/*
path(X,Y,P,L):-
	length(P,M),
	M < L,	
	path(X,Y,P).
*/
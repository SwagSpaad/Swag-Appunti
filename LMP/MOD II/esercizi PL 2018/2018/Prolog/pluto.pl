
primo(b).
primo(a).
secondo(a).
secondo(c).


prendi(A):-
	primo(A),!,
	secondo(A).
	
	
prendi1(A):-
	primo(A),
	secondo(A).
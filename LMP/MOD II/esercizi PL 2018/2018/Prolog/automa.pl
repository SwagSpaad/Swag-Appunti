final(s3).

t(s1, a, s1).
s1 -- a -> s1. 

t(s1, a, s2).
t(s1, b, s1).
t(s2, b, s3).
t(s3, b, s2).
t(s1, a, s4).



silent(s2, s4).
silent(s3, s1).

/*accepted(Stringa,StatoIniziale)*/
accepted([],StatoIniziale):-
	final(StatoIniziale).
	
accepted([X|R],StatoIniziale):-
		t(StatoIniziale,X,S2),
		accepted(R,S2).

accepted(R,StatoIniziale):-
		silent(StatoIniziale,S2),
		accepted(R,S2).

	

	
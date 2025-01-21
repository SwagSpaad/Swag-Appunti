
/*
posizioniDelleRegine([A1/B1,A2/B2,....,A8/B8]).
*/

[
[a,a,a,a,a,a,a,a],
[a,a,a,a,a,a,a,a],
[a,a,a,a,a,a,a,a],
[a,a,a,a,a,a,a,a],
[a,a,a,a,a,a,a,a],
[a,a,a,a,a,a,a,a],
[a,a,a,a,a,a,a,a],
[a,a,a,a,a,a,a,a],
[a,a,a,a,a,a,a,a]]


posizioniDelleRegine([1/B1,2/B2,3/B3,...,8/B8]):-
	member(B1,[1,2,3,4,5,6,7,8]),
	...
	,
	nonSiMangiano([1/B1,2/B2,3/B3,...,8/B8]).


nonSiMangiano([]).
nonSiMangiano([A/B]).

nonSiMangiano([A/B|R]):-
	nonMangia(A/B,R),
	nonSiMangiano(R).

nonMangia(A/B,[]).

nonMangia(A/B,[C/D|R]):-
	A =/= C,
	B =/= D,
	AmC is A - C,
	BmD is B - D,
	AmC =/= BmD,
	DmB is D - B,
	AmC =/= DmB,
	nonMangia(A/B,R).
	

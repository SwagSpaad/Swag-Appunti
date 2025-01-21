/*tree(a,[tree(b,[tree(h,[]),tree(k,[])]),tree(c,[])])*/

:-	dynamic sotto_albero_che_parte_dalla_radice_gia_calcolato/2.

%===========================
k(T1,T2,N):-
	s(T1,ST1),
	s(T2,ST2),
	intersect(ST1,ST2,LST),
	length(LST,N).

%===========================

/*
s(T,STL):-
	bagof(ST,sotto_albero_che_non_necessariamente_parte_dalla_radice(T,ST),STL).
	
*/

s(T,STL):-
	bagof(STR,sotto_albero_che_parte_dalla_radice(T,STR), _),
	bagof(ST,sotto_albero_che_parte_dalla_radice_gia_calcolato(T,ST),STL).
	
	
%===========================
/*
sotto_albero_che_parte_dalla_radice(T,ST):-
	sotto_albero_che_parte_dalla_radice_gia_calcolato(T,ST).
*/


sotto_albero_che_parte_dalla_radice(tree(A,[F1|F2]),tree(A,[])):-
	assert(
	sotto_albero_che_parte_dalla_radice_gia_calcolato(tree(A,[F1|F2]),tree(A,[]))),
	write(asserisco(tree(A,[F1|F2]),tree(A,[]))),nl.

sotto_albero_che_parte_dalla_radice(tree(A,L),tree(A,LS)):-
	sotto_alberi_che_partono_dalla_radice(L,LS),
	assert(
	sotto_albero_che_parte_dalla_radice_gia_calcolato(tree(A,L),tree(A,LS))),
	write(asserisco(tree(A,L),tree(A,LS))),nl.
	
sotto_alberi_che_partono_dalla_radice([],[]).

sotto_alberi_che_partono_dalla_radice([A|LA],[B|LB]):-
	write(guardo_sottoalberi),nl,
	sotto_albero_che_parte_dalla_radice(A,B),
	sotto_alberi_che_partono_dalla_radice(LA,LB).


sotto_albero_che_non_necessariamente_parte_dalla_radice(T,S):-	
	sotto_albero_che_parte_dalla_radice(T,S).
	
sotto_albero_che_non_necessariamente_parte_dalla_radice(tree(_,Fs),S):-
	member(F,Fs),
	sotto_albero_che_non_necessariamente_parte_dalla_radice(F,S).
	

	
/* intersect(ST1,ST2,LST) */

/*
member(X,LST):-
member(X,ST1),
member(X,ST2).
*/

intersect([],_,[]).

intersect(_,[],[]).

intersect([A|LA],B,[A|LI]):-
	member(A,B),!,
	intersect(LA,B,LI).

intersect([A|LA],B,LI):-
	intersect(LA,B,LI).

	

	
intersect2(A,B,[X|LI]):-
	member(X,A,AmX),
	member(X,B,BmX),!,
	intersect2(AmX,BmX,LI).

intersect2(_,_,[]).

member(X,[X|A],A).

member(X,[K|A],[K|B]):-
	member(X,A,B).


	
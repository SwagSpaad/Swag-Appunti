/*
T = vp(v(delivers),np(d(a),n(talk))) 

STs = [np(d(a),n(talk)),np(d(a),n), np(d,n(talk)) , np(d,n), ...]
*/

:- dynamic subtree_rooted_mem/2.



subtrees(T,STs):-
	assert(subtree_rooted_mem(empty,empty)),
	bagof(ST,  subtree(T,ST) ,STs),
	retractall(subtree_rooted_mem(_,_)).
	
subtree_rooted(T,ST):- 
	subtree_rooted_mem(T,ST),
	write(reusing(T,ST) ),nl, !.
	
subtree_rooted(T,ST):-
	T =.. [Root|SONS_T],
	ST =.. [Root].

	
subtree_rooted(T,ST):-
	T =.. [Root,F1|SONS_T],
	subtree_sons([F1|SONS_T],SONS_ST),
	ST =.. [Root|SONS_ST].
	
subtree_sons([],[]).
subtree_sons([SON_T|SONS_T],[SON_ST|SONS_ST]):-
	subtree_rooted(SON_T,SON_ST),
	subtree_sons(SONS_T,SONS_ST).

	
subtree(T,ST):-
		T =.. [_|SONS],
		member(SON,SONS),
		subtree(SON,ST).
		
subtree(T,ST):-
	subtree_rooted(T,ST),
	assert(subtree_rooted_mem(T,ST)).
	
		
	
	
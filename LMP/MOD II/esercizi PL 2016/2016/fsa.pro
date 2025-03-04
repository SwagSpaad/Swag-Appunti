

finale(s3).

tran(s1,s2,a).
tran(s1,s1,a).
tran(s1,s1,b).
tran(s2,s3,b).
tran(s3,s4,b).
tran(s3,s1,null).
tran(s2,s4,null).


/*accept(SI,L).*/

accept(SI,[]):-
	finale(SI).
	
accept(SI,[A|R]):-
	tran(SI,QUALCOSA,A),
	accept(QUALCOSA,R).

accept(SI,R):-
	tran(SI,QUALCOSA,null),
	accept(QUALCOSA,R).
	
	


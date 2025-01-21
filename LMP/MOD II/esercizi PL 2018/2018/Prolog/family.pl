family(
	person(bob, smith, date(7, may,1968),30000),
	 person(ann, smith, date(18, july,1970),32000),
	 [person(dave, smith, date(1, june,1984),0),
	 person(edna, smith, date(25, may,1990),0)]).

family(
	person(pino, bianchi, date(7, may,1978),30000),
	 person(rina, rossi, date(18, july,1980),32000),
	 [person(rino, bianchi, date(1, june,1998),0),
	 person(gino, bianchi, date(1, june,2006),0),
	 person(dino, bianchi, date(25, may,2005),0)]).

	 
father(P):-
	family(P,_,_).

mother(P):-
	family(_,P,_).

son(P):-
	family(_,_,Sons),
	member(P,Sons).
	
/* person(P) */
person(P):-
	father(P).
person(P):-
	mother(P).
person(P):-
	son(P).
	

/*nome(P,Nome)
cognome(P,Nome)
ddn(P,Nome)
salario(P,Nome)
person(gino, bianchi, date(1, june,2006),0)
*/
nome(person(Nome,_,_,_),Nome).
cognome(person(_,Nome,_,_),Nome).
ddn(person(_,_,DDN,_),DDN).
reddito(person(_,_,_,Salario),Salario).
salario(person(_,_,_,Salario),Salario).


reddito(CognomePadre,RedditoGlobale):-
	family(Padre,Madre,Figli),
	cognome(Padre,CognomePadre),
	reddito(Padre, RedditoPadre),
	reddito(Madre, RedditoMadre),
	redditoFigli(Figli,RedditoFigli),
	RedditoGlobale is RedditoMadre + RedditoPadre + RedditoFigli.

redditoFigli([],0).
redditoFigli([F|RF],K):-
	redditoFigli(RF,K1),
	reddito(F,RedditoF),
	K is K1 + RedditoF.
	
/*lunghezza(L,N).*/
lunghezza([],0).
lunghezza([_|R],N):-
	lunghezza(R,NR),
	N is NR + 1.

/*somma(L,Somma)*/

somma([],0).
somma([A|R],N):-
	somma(R,NR),
	N is NR + A.


familyConFigliMinorenni(CognomePadre,RedditoGlobale,
		NumeroDiFigliMinorenni).
























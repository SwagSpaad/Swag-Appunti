

/* family(P,M,LF) */

/* person(NOME,COGNOME,DATA_NASCITA,SALARIO) */
/* data(GG,MM,AA) */

family(
   person(mario,rossi,data(8,7,1967),70000),
   person(maria,bianchi,data(8,8,1965),80000),
  [ person(gino,rossi,data(8,8,2001),0),
    person(pino,rossi,data(8,8,2003),0),
    person(rino,rossi,data(8,8,2008),0)]
).


family(
   person(mimmo,marroni,data(8,7,1982),45000),
   person(mimma,verdi,data(8,8,1985),31000),
  [ person(dina,marroni,data(5,8,2009),0),
    person(pina,marroni,data(8,5,2006),0),
    person(gina,marroni,data(9,12,2012),0)]
).

/*
person(NOME,COGNOME,DN,REDDITO)
nome(P,N)
cognome(P,N)
dataNascita(P,DN)
reddito(P,R)
*/

nome(person(N,_,_,_),N).
cognome(person(_,N,_,_),N).
dataNascita(person(_,_,N,_),N).
reddito(person(_,_,_,N),N).

anno(data(_,_,A),A).
mese(data(_,A,_),A).
giorno(data(A,_,_),A).




padre(P):-
	family(P,_,_).

madre(M):-
	family(_,M,_).

figlio(F):-
	family(_,_,FIGLI),
	member(F,FIGLI).

	
	
cognome(CGN):-
	padre(person(_,CGN,_,_)).

cognome(CGN):-
	madre(person(_,CGN,_,_)).

cognome(CGN):-
	figlio(person(_,CGN,_,_)).
	

/* redditoFam(P,R).
	

*/
redditoFam(P,R):-
	padre(P),
	family(P,M,FIGLI),
	reddito(P,RP),
	reddito(M,RM),
	redditoFigli(FIGLI,RF),
	R is RF + RM + RP.
	
redditoFigli([],0).

redditoFigli([F|RF], R):-
	reddito(F,R1),
	redditoFigli(RF,R2),
	R is R1 + R2.
	

	
differenzaEtaMammaFiglioMaggiore(M,DiffEta):-
	madre(M),
	family(_,M,FIGLI),
	figlioMaggiore(F,FIGLI),
	dataNascita(F,DNF),
	dataNascita(M,DNM),
	anno(DNF,AF),
	anno(DNM,AM),
	DiffEta is AF - AM .

figlioMaggiore(F,[F]).

figlioMaggiore(F,[F|RF]):-
	dataNascita(F,DNF),
	anno(DNF,AF),
	figlioMaggiore(TF,RF),
	dataNascita(TF,DNTF),
	anno(DNTF,ATF),
	ATF > AF.
	
figlioMaggiore(TF,[F|RF]):-
	dataNascita(F,DNF),
	anno(DNF,AF),
	figlioMaggiore(TF,RF),
	dataNascita(TF,DNTF),
	anno(DNTF,ATF),
	ATF < AF.
	
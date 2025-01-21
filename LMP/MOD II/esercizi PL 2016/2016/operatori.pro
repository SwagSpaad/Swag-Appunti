

:- op(100,xfy,somma).
:- op(50,xfx,prodotto).

somma(a,b).
somma(c,d).


:- op(100,xfx,ha).
:- op(50,xfx,di).

mario ha macchina di dario.
giovanni ha cestino di maria.
gennaro ha cervello di giovanni.


:- op(100,xfx,potenza).
//:- op(90,xfx,calcola).


calcola(N,potenza(A,B)):-
	N is A*B*2 .


//// CASALINGAMENTE !!!!


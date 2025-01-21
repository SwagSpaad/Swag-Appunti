colpito(I, J, II, JJ, M):-
	II - M =< I,
    I =< II + M,
    JJ - M =< J,
    J =< JJ + M.


posizioneSicura(_,_,_,_,[]).

posizioneSicura(I, J, N, M, [(II, JJ) | T]):-
    I =< N,
    1 =< I,
    J =< N,
    1 =< J,
    not(colpito(I,J,II,JJ,M)),
    posizioneSicura(I, J, N, M, T).
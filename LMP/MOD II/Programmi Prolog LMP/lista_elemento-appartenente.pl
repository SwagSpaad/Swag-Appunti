/*
 * Data una lista L, l'elemento X appartiene a L se è il primo elemento, 
 * X appartiene a L se appartiene alla coda
 */

appartiene(X,[X|_]).

appartiene(X,[_|T]):-
    appartiene(X,T).

    
/*
 * lung(L,Lung) -> dove L è una lista e Lung è il valore
 * della lunghezza della lista.
*/

lung([],0).
lung([_|T],N):-
    lung(T,M),
    N is M + 1.
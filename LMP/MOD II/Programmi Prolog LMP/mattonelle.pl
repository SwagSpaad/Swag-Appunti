% A = mattonella grande
% B = mattonella media
% C = mattonella piccola
/* la mattonella A può sostenere il peso di A, B e C
 * la mattonella B può sostenere il peso di B e C
 * la mattonella C può sostenere solo il peso di C
 * fare un programma che costruisca pacchetti di massimo 10 mattonelle
 * impilate in modo che tutte riescano a sostenere il peso delle altre. */

/*mattonellaGrande(A).
mattonellaMedia(B).
mattonellaPiccola(C).*/

/*sostenere(A,A).
sostenere(A,B).
sostenere(A,C).
sostenere(B,B).
sostenere(B,C).
sostenere(C,C).*/

% Calcola la lunghezza di una lista
lungLista([], 0).
lungLista([_|T], L) :-
    lungLista(T, LT),
    L is LT + 1.

% Calcola la lunghezza totale delle liste A, B e C e verifica che sia 10
lunghezzaConfezione(A, B, C, L) :-
    lungLista(A, LA),
    lungLista(B, LB),
    lungLista(C, LC),
    L is LA + LB + LC,
    L =:= 10.

% Concatenazione di due liste
append([], A, A).
append([H|T], B, [H|L]) :-
    append(T, B, L).

% Crea la confezione concatenando A, B e C e verifica che la lunghezza totale sia 10
confezione(A, B, C, Confezione) :-
    lunghezzaConfezione(A, B, C, 10),
    append(A, B, Temp),
    append(Temp, C, Confezione).
    
    
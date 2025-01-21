conta_occ(_, [], 0).
conta_occ(A, [A|L], Len):-
    conta_occ(A, L, Ls),
    Len is Ls +1.
conta_occ(A, [H|L], Len):-
    not(A = H),
    conta_occ(A, L, Len).
    

lineaOrizzontaleInUnPianoFattaDi(A, Len, [H|Rest]):-
    (conta_occ(A, H, Len) ; lineaOrizzontaleInUnPianoFattaDi(A, Len, Rest)).


% query: lineaOrizzontaleInUnPianoFattaDi(a16, 3, [[a1, a2, a3, a4, a5], [a2, a3, a4, a5, a6], [a3, a4, a5, a6, a7], [a4, a5, a6, a7, a1], [a5, a6, a7, a1, a2], [a6, a7, a1, a2, a3], [a7, a1, a2, a3, a4], [a1, a2, a3, a4, a5], [a2, a3, a4, a5, a6], [a15, a16, a16, a16, a9]]).

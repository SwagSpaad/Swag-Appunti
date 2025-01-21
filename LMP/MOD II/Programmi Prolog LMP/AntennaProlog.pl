:- use_module(library(dif)).

% Definizione della mappa 9x9 vuota
mappa([
    [0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0]
]).

% Definizione dei tipi di antenne
antenna(q, [
    [1, 1, 1],
    [1, 1, 1],
    [1, 1, 1]
]).

antenna(c, [
    [0, 1, 0],
    [1, 1, 1],
    [0, 1, 0]
]).

% Predicato principale: posizione/4
posizione((AntennaID, TipoAntenna), Mappa, (X, Y), Copertura) :-
    antenna(TipoAntenna, Forma),
    posiziona_antenna(Mappa, Forma, (X, Y), NuovaMappa),
    calcola_copertura(NuovaMappa, Copertura).

% Posizionamento di un'antenna sulla mappa
posiziona_antenna(Mappa, Forma, (X, Y), NuovaMappa) :-
    sovrapponi(Mappa, Forma, (X, Y), NuovaMappa).

% Sovrapporre una forma alla mappa
sovrapponi(Mappa, Forma, (X, Y), NuovaMappa) :-
    length(Forma, N),
    N2 is N // 2,
    X1 is X - N2,
    Y1 is Y - N2,
    sovrapponi_righe(Mappa, Forma, X1, Y1, NuovaMappa).

% Sovrapporre le righe della forma alla mappa
sovrapponi_righe([], [], _, _, []).
sovrapponi_righe([RigaMappa|AltreRigheMappa], [RigaForma|AltreRigheForma], X, Y, [NuovaRiga|NuoveRighe]) :-
    sovrapponi_celle(RigaMappa, RigaForma, X, NuovaRiga),
    Y1 is Y - 1,
    sovrapponi_righe(AltreRigheMappa, AltreRigheForma, X, Y1, NuoveRighe).
sovrapponi_righe([RigaMappa|AltreRigheMappa], [], _, _, [RigaMappa|AltreRigheMappa]).

% Sovrapporre le celle della forma alla mappa
sovrapponi_celle([], [], _, []).
sovrapponi_celle([CellaMappa|AltreCelleMappa], [CellaForma|AltreCelleForma], X, [NuovaCella|NuoveCelle]) :-
    (X >= 0 ->
        (CellaForma == 1 ->
            NuovaCella = 1
        ;
            NuovaCella = CellaMappa
        )
    ;
        NuovaCella = CellaMappa
    ),
    X1 is X - 1,
    sovrapponi_celle(AltreCelleMappa, AltreCelleForma, X1, NuoveCelle).
sovrapponi_celle([CellaMappa|AltreCelleMappa], [], _, [CellaMappa|AltreCelleMappa]).

% Calcolare la copertura della mappa
calcola_copertura(Mappa, Copertura) :-
    flatten(Mappa, Lista),
    include(==(1), Lista, Coperti),
    length(Coperti, N1),
    length(Lista, N2),
    Copertura is (N1 / N2) * 100.

% Predicato per appiattire una lista di liste in una lista
flatten([], []).
flatten([L|Ls], FlatL) :-
    flatten(L, NewL),
    flatten(Ls, NewLs),
    append(NewL, NewLs, FlatL).
flatten(L, [L]).

/*mappa(Mappa), posizione((1, q), Mappa, (4, 4), Copertura), maplist(writeln, Mappa), writeln(Copertura).*/
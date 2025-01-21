
ingredienti([
            (pasta,250), 
            (pomodori,10), 
            (basilico,5), 
            (olio,3), 
            (sale,150), 
            (tonno,1)
            ]).

piatti([pasta_sugo, pasta_pesto]).

tempoPrepMax(90). %tempo massimo di preparazione dei piatti in minuti

modifica_ingrediente(_, _, [], []).

modifica_ingrediente(Nome, Sott, [(Nome, Valore)|T], [(Nome, NuovoValore)|TModificata]) :-
    NuovoValore is Valore - Sott,
    modifica_ingrediente(Nome, Sott, T, TModificata).

modifica_ingrediente(Nome, Sott, [H|T], [H|TModificata]) :-
    H = (NomeCorrente, _),
    Nome \= NomeCorrente,
    modifica_ingrediente(Nome, Sott, T, TModificata).

% Predicato per aggiornare il fatto ingredienti
aggiorna_ingredienti(NuoviIngredienti) :-
    retractall(ingredienti(_)),
    assert(ingredienti(NuoviIngredienti)).

%prova questa query per capire se funziona
/*ingredienti(Ingredienti),
modifica_ingrediente(olio, 2, Ingredienti, NuoviIngredienti),
writeln(NuoviIngredienti).*/

/*piattoPerOggi(TempoP, nPers, nomePiatto, [(X,Y)|T])
              tempoPrepMax>TempoP,*/
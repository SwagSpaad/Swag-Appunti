% Predicato per invertire due lettere in una parola
mossaInversione(Stringa1, I, J, Stringa2, Costo) :-
    string_chars(Stringa1, ListaCaratteri),  % Converte la stringa in una lista di caratteri
    nth1(I, ListaCaratteri, Char1),     % Prende il carattere alla posizione I
    nth1(J, ListaCaratteri, Char2),     % Prende il carattere alla posizione J
    sostituisciCarattere(ListaCaratteri, I, Char2, Temp), % Sostituisce Char1 con Char2
    sostituisciCarattere(Temp, J, Char1, NuovaLista),    % Sostituisce Char2 con Char1
    atom_chars(Stringa2, NuovaLista),  % Converte la lista di caratteri in una nuova stringa
    Costo is 1 + J - I.

% Predicato per sostituire un carattere in una lista di caratteri
sostituisciCarattere([_|T], 1, X, [X|T]).
sostituisciCarattere([H|T], I, X, [H|R]) :-
    I > 1,
    NI is I - 1,
    sostituisciCarattere(T, NI, X, R).

% Predicato per rimuovere un carattere da una stringa utilizzando un indice
mossaRimuovi(Stringa, Indice, NuovaStringa, Costo) :-
    string_chars(Stringa, ListaCaratteri),      % Converti la stringa in lista di caratteri
    rimuoviInLista(ListaCaratteri, Indice, ListaRisultato), % Rimuovi il carattere dalla lista
    atom_chars(NuovaStringa, ListaRisultato),   % Converti la lista di caratteri in una nuova stringa
    Costo is 5.

% Predicato per rimuovere un carattere da una lista di caratteri utilizzando un indice
rimuoviInLista(L, I, R) :-
    nth1(I, L, _, R).

% Predicato per inserire un carattere in una parola
mossaInserisci(Stringa, I, Carattere, NuovaParola, Costo) :-
    string_chars(Stringa, ListaCaratteri),          % Converti la parola in lista di caratteri
    inserisciInLista(ListaCaratteri, I, Carattere, ListaRisultato), % Inserisci il carattere nella lista
    atom_chars(NuovaParola, ListaRisultato),      % Converti la lista di caratteri in una nuova parola
    Costo is 5.


% Predicato per inserire un carattere in una lista di caratteri
inserisciInLista(Lista, I, Char, Risultato) :-
    length(Prefix, I),                          % Ottieni la parte iniziale della lista fino alla posizione desiderata
    append(Prefix, Suffix, Lista),                % Dividi la lista in parte iniziale e parte finale
    append(Prefix, [Char|Suffix], Risultato).     % Inserisci il carattere nella posizione desiderata
    
distanza(Stringa1,Stringa2,MosseDiTrasformazione,CostoTrasformazione):-
    mossaInserisci(Stringa1,
    
    
    
    
    
    
    
    
    

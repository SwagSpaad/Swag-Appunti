/*
leggiParole(File,ListaParole).

File

il bambino mangia il gelato .

["il","bambino","mangia","il","gelato","."]


get
*/

leggiParole(File,ListaParole):-
	see(File),
	processFile([],ListaParole),
	seen.

processFile(W,ListaParole) :-
	get(Char),
	process(Char,W,ListaParole).

process(-1,W,[W]):-
	!.

process(' ',W,[W|R]):-
	!,
	processFile([],R).
	
process(Char,W,ListaParole):-
	append(W,[Char],WN),
	processFile(WN,ListaParole).






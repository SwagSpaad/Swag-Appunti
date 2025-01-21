our_consult(NOME_FILE):-
	see(NOME_FILE),
	read_all_predicates,
	seen.

read_all_predicates:-
	read(X),
	( X = end_of_file ;
	assert(X),
	read_all_predicates).
	
/*
 * X è fratello di Y se Z è genitore di X e di Y
 * Trovare i fratelli di Y
 */



genitore(dam, james).
genitore(dam, alessio).
genitore(alessio, michele).
genitore(anna, marco).
genitore(anna, michela).

fratello(X,Y):-
    genitore(Z,Y), 
    genitore(Z,X).

nonno(X,Y):-
    genitore(Z,Y),
    genitore(X,Z).
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         

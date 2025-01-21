/*e(S,E)*/
/*edge serve per creare un percorso tra i nodi, un ramo*/
edge(a,b).
edge(b,c).
edge(c,d).
edge(a,e).
edge(e,f).
edge(f,k).
edge(f,c).
/*dopo aver definito il grafo definiamo il percorso*/

%passo base:
path(X,Y):-
    edge(X,Y).

%passo induttivo:
path(X,Y):- /* :- sarebbe if */
    path(X,Z), /* , sarebbe AND */
    path(Z,Y).
edge(a,b).
edge(b,c).
edge(a,e).
edge(c,d).
edge(d,e).
edge(f,e).

/*
path(X,Y):- edge(X,Y).
path(X,Y):- path(X,Z),path(Z,Y).

path(X,Y):- edge(X,Y).
path(X,Y):- path(X,Z),edge(Z,Y).

*/


path(X,Y):- edge(X,Y).
path(X,Y):- edge(X,Z),path(Z,Y).

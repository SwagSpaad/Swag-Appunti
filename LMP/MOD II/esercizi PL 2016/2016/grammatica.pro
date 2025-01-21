

s(tree(s,NP,VP)) --> np(NP,_), vp(VP).

np(tree(np,ART,NOME),G2) --> art(ART,G1), nome(NOME,G2), { compatibili(G1,G2)}.
vp(tree(vp,V)) --> v(V).
vp(tree(vp,V,NP)) --> v(V), np(NP,_).

compatibili(_,neut).
compatibili(neut,_).
compatibili(A,A).

v(sporca) --> [sporca].
v(rovescia) --> [rovescia].
art(il,m) --> [il].
art(la,f) --> [la].
art(er,neut) --> [er].
nome(bambino,m) --> [bambino].
nome(bambina,f) --> [bambina].
nome(tavolo,m) --> [tavolo].
nome(tavola,f) --> [tavola].
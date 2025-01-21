/*
 * Vogliamo costruire gli incastri di parole per un cruciverba. Per fare ciò, disegniamo uno schema
 * e indichiamo le parole che possiamo usare. Il programma ci dirà quali parole usare e come riempire
 * lo schema.
 * parole possibili ---> dog, four, baker, prolog, run, lost, forum, vanish, top, mess, green, wonder,
 * five, unit, super, yellow.
*/

word(d,o,g).
word(r,u,n).
word(t,o,p).
word(f,o,u,r).
word(l,o,s,t).
word(m,e,s,s).
word(u,n,i,t).
word(f,i,v,e).
word(b,a,k,e,r).
word(g,r,e,e,n).
word(f,o,r,u,m).
word(s,u,p,e,r).
word(p,r,o,l,o,g).
word(v,a,n,i,s,h).
word(w,o,n,d,e,r).
word(y,e,l,l,o,w).
cruciverba(A1,A2,A3,A4,A5,B1,B3,B5,C1,C2,C3,C4,C5,C6,D1,D5):-
    word(A1,A2,A3,A4,A5),
    word(A1,B1,C1,D1),
    word(C1,C2,C3,C4,C5,C6),
    word(A3,B3,C3),
    word(A5,B5,C5,D5).
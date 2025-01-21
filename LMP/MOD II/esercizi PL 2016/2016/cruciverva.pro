schema(A1,A2,A3,A4,A5,x,
B1,x,B3,X,B5,x,
C1,C2,C3,C4,C5,C6,
D1,x,x,x,D5,x):-
	w(A1,A2,A3,A4,A5),
	w(C1,C2,C3,C4,C5,C6),
	w(A1,B1,C1,D1),
	w(A3,B3,C3),
	w(A5,B5,C5,D5).
	



w(d,o,g).
w(f,o,u,r).
w(b,a,k,e,r).
w(p,r,o,l,o,g).
w(r,u,n).
w(l,o,s,t).
w(f,o,r,u,m).
w(f,a,r,u,m).
w(v,a,n,i,s,h).
w(t,o,p).
w(m,e,s,s).
w(g,r,e,e,n).
w(w,o,n,d,e,r).
w(f,i,v,e).
w(u,n,i,t).
w(s,u,p,e,r).
w(y,e,l,l,o,w).

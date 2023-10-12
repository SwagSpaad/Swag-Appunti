----
# Interpolazione polinomiale
È il metodo più naturale e semplice per approssimare una funzione, mediante l'utilizzo di polinomi, che per un calcolatore sono estremamente facili da calcolare, perché deve svolgere solo potenze, moltiplicazioni e somme.

**Problema**
È data una funzione $f:[a, b] \to \mathbb R$ di cui sono noti i valori di $f(x_{0}),\: f(x_{1}), \dots,\: f(x_{n})$ in n+1 punti distiniti $x_{0},\: x_{1},\dots,\: x_{n} \in [a,b]$. 
Si sceglie una classe $\mathcal C$ di funzioni da $[a,b] \to \mathbb R$ e si vuole approssimare la funzione $f(x)$ con una funzione $g(x)$ nella classe $\mathcal C$ tale che $g(x_{i})=f(x_{i})\: \forall \:i = 0, \dots, n$.

Una scelta comune per la sua semplicità è $\mathcal C = \mathbb R_{n}[x]=$  spazio dei polinomi di grado minore uguale di n = $\{a_{0}+a_{1}x+\dots+a_{n}x^{n}\: : \: a_{0},\:a_{1},\dots,\: a_{n} \in \mathbb R\}$.

$\exists! \: p(x)\in \mathcal C=\mathbb R_{n}[x]\: t.c.\: p(x_{i})=f(x_{i})\: \forall \: i = 0,\dots,\:n$
 
**Teorema**
Siano $(x_0,y_0),\:(x_1,y_1),\dots,(x_n,y_{n})\in \mathbb R^2$ tali che $x_{0},\: x_{1},\dots,\: x_{n}$ sono tutti distinti. Allora $\exists! \text{ polinomio } p(x)\in \mathbb R_{n}[x] \text{ tale che }  p(x_{i})=y_{i} \: \forall\: i=0,\:1,\dots,\:n$

*Dim. 1*
Un polinomio $p(x)=a_{0}+a_{1}x+\dots+a_{n}x^{n}\in \mathbb R_{n}[x]$ soddisfa la proprietà $p(x_i)=y_i$ $\forall\:i=0,\dots,n$ se e solo se 
$$\begin{cases} a_{0}+a_{1}x_{0}+a_{2}x_{0}^{2}+\dots+a_{n}x_{0}^{n}=y_0  \\
a_{0}+a_{1}x_{1}+a_{2}x_{1}^{2}+\dots+a_{n}x_{1}^{n}=y_1\\
a_{0}+a_{1}x_{2}+a_{2}x_{2}^{2}+\dots+a_{n}x_{2}^{n}=y_2 \\
\: \vdots \\
a_{0}+a_{1}x_{n}+a_{2}x_{n}^{2}+\dots+a_{n}x_{n}^{n}=y_n
\end{cases}$$
cioé se il vettore dei coefficienti di $p(x)$ ossia se $(a_{0},\:a_{1},\dots,\:a_{n})^T$ soddisfa il sistema lineare $$ \begin{bmatrix}
1 & x_0 & x_{0}^2 & ... & x_{0}^n \\
1 & x_1 & x_{1}^2 & ... & x_{1}^2 \\
1 & x_2 & x_{2}^2 & ... & x_{2}^2 \\
\vdots &  &  &  & \vdots \\
1 & x_n & x_{n}^2 & ... & x_{n}^2 
\end{bmatrix} 
\begin{bmatrix}
a_0 \\
a_1 \\
a_2 \\
\vdots \\
a_n 
\end{bmatrix} = 
\begin{bmatrix}
y_0 \\
y_1 \\
y_2 \\
\vdots \\
y_n 
\end{bmatrix}
\:\: (\star)$$
Per dimostrare che $\exists!\: p(x)\in \mathbb R_{n}[x]$ tale che $p(x_{i})=y_{i}$ $\forall \:i=0,\dots,n$ basta dimostrare che il sistema lineare $(\star)$ ha un'unica soluzione. In tal caso l'unica soluzione di $(\star)$ sarà il vettore dei coefficienti dell'unico polinomio $p(x)\in \mathbb R_{n}[x]$ tale che $p(x_{i})=y_{i}$ $\forall \:i=0,\dots,n$.

Dimostriamo dunque che il sistema $(\star)$ ha un'unica soluzione, cioè che $$\text{det}[V(x_{0},x_{1},\dots,x_{n})]\neq 0$$
Per farlo, mostriamo che $$\text{det}[V(x_{0},x_{1},\dots,x_{n})]=
\begin{cases} 1 & n=0\\
 \\
\prod_{i,j=0,\: j<i}^{n}\:(x_{i}-x_{j})\:\:(\star\star) &n\geq1 \:\: (\star\star)\\
\end{cases}
$$
Dove $\prod_{i,j=0,\: j<i}^{n}\:(x_{i}-x_{j})=(x_1-x_0)\cdot\dots(x_n-x_0)\cdot\dots(x_n-x_{n-1})$.

Dimostriamo $(\star\star)$ nel caso n = 3 (la dimostrazione nel caso n generico è analoga).
Per $i=1,\dots,3$ definiamo $d_{i}=\text{det}[V(x_{0},\dots,x_{i})]$. Il nostro obiettivo è calcolare $d_{3}=\text{det}[V(x_{0},\dots,x_{3})]$.
$$d_{3}= \begin{vmatrix}
1 & x_0 & x_{0}^2 & x_{0}^3 \\
1 & x_1 & x_{1}^2 & x_{1}^3 \\
1 & x_2 & x_{2}^2 & x_{2}^3 \\
1 & x_3 & x_{3}^2 & x_{3}^3 
\end{vmatrix} $$

Effettuiamo la riduzione di Gauss sulle colonne, partendo dall'ultima e sottraendo la colonna precedente moltiplicata per $x_{3}$, ovvero $C_{n}\to C_{n}-x_{3}\cdot C_{n-1}$, fino ad annullare i termini delle incognite nell'ultima riga. La matrice risulterà $$\begin{vmatrix}
1 & x_0-x_3 & x_{0}(x_0-x_3) & x_{0}^2(x_0-x_3) \\
1 & x_1-x_3 & x_{1}(x_1-x_3) & x_{1}^2(x_1-x_3) \\
1 & x_2-x_3 & x_{2}(x_2-x_3) & x_{2}^2(x_2-x_3) \\
1 & 0 & 0 & 0 
\end{vmatrix} $$

Calcoliamo il determinante grazie allo **sviluppo di Laplace**, fissando l'ultima riga che in seguito viene eliminata. Il calcolo del determinante risulterà quindi: $$d_{3}= (-1)^{3} \cdot\begin{vmatrix}
x_0-x_3 & x_{0}(x_0-x_3) & x_{0}^2(x_0-x_3) \\
x_1-x_3 & x_{1}(x_1-x_3) & x_{1}^2(x_1-x_3) \\
x_2-x_3 & x_{2}(x_2-x_3) & x_{2}^2(x_2-x_3) \\
\end{vmatrix} $$
Osserviamo che la prima riga (analogamente per la seconda e la terza) hanno in comune il fattore $(x_{0}-x_{3})$, che possiamo quindi portare fuori dal determinante. $$d_{3}= (-1)^{3} \cdot(x_{0}-x_{3})\cdot(x_{1}-x_{3})\cdot(x_{2}-x_{3})\begin{vmatrix}
1 & x_{0} & x_{0}^2 \\
1 & x_{1} & x_{1}^2 \\
1 & x_{2} & x_{2}^2 \\
\end{vmatrix} $$
Moltiplico il $(-1)^3$ per i termini portati fuori e quindi inverto di segno (si osservi che il determinante a destra è per definizione $d_{2}$)
$$d_{3}= (x_{3}-x_{0})\cdot(x_{3}-x_{1})\cdot(x_{3}-x_{2})\cdot d_2$$
Sviluppando quindi come già fatto per $d_{3}$ (per ricorrenza) ottengo $$d_{3}= (x_{3}-x_{0})\cdot(x_{3}-x_{1})\cdot(x_{3}-x_{2})\cdot(x_{2}-x_{0})\cdot(x_{2}-x_{1})\cdot d_{1}=$$
$$=(x_{3}-x_{0})\cdot(x_{3}-x_{1})\cdot(x_{3}-x_{2})\cdot(x_{2}-x_{0})\cdot(x_{2}-x_{1})\cdot(x_{1}-x_{0})$$
che risulta uguale allo sviluppo della produttoria $(\star\star)$ per $n=3$. $\square$ 

*Dim. 2*
$\forall j=0,\dots,n$ definiamo il polinomio $$L_j(x)=\prod_{k=0, k\neq j}^{n} \frac{x-x_{k}}{x_{j}-x_{k}}=\frac{(x-x_0)\dots(x-x_{j-1})(x-x_{j+1})\dots(x-x_{n})}{(x_{j}-x_0)\dots(x_{j}-x_{j-1})(x_{j}-x_{j+1})\dots(x_{j}-x_{n})}$$
gli $n+1$ polinomi $L_{0}(x),\: L_{1}(x),\dots,\: L_{n}(x)$ hanno grado $n$ e appartengono a $\mathbb R_n[x]$.
Mostriamo che $L_{0}(x),\: L_{1}(x),\dots,\: L_{n}(x)$ sono una base di $\mathbb R_n[x]$.
Siccome questi polinomi sono in numero di $n+1=\text{dim } \mathbb R_n[x]$, per dimostrare che sono una base, basta dimostrare che sono *linearmente dipendenti.

>**Oss.**
>- Il fatto che $\text{dim }\mathbb R_n[x]=n+1$ vale perché sappiamo che una base di $\mathbb R_n[x]$ è la base canonica $1,\:x,\:x^2,\dots,\:x^n$ che ha precisamente $n+1$ elementi
>- Ricordiamo che una base di uno spazio vettoriale è un insieme di elementi linearmente indipendenti che generano lo spazio
>- Tutte le basi di uno spazio vettoriale hanno lo stesso numero di elementi che è detto dimensione dello spazio

Per dimostrare che $L_{0}(x),\: L_{1}(x),\dots,\: L_{n}(x)$, sono linearmente indipendenti, mettiamo in luce una proprietà: $\forall\:i,\:j=0,\dots,\:n$  $$(\triangle)\:\: L_j(x_i)=\begin{cases} 1 & \text{se }i=j \\ 0 & \text{se } i\neq j
\end{cases}$$
ora dobbiamo mostrare che se $\alpha_0L_0(x)+\alpha_1L_1(x)+\dots+\alpha_nL_n(x)$ è il polinomio *identicamente nullo*, allora $\alpha_0=\alpha_1=\dots=\alpha_n=0$ .
Sia dunque $\alpha_0L_0(x)+\alpha_1L_1(x)+\dots+\alpha_nL_n(x)=0$ identicamente $\forall\:x \in \mathbb R$.
Di conseguenza $\forall \:i=0,\dots,\:n$ dovrò avere $$\alpha_0L_0(x_i)+\alpha_1L_1(x_i)+\dots+\alpha_nL_n(x_i)=0$$
allora $\alpha_0=\alpha_1=\dots=\alpha_n=0\implies L_{0}(x),L_{1}(x),\dots,L_{n}(x)$ sono linearmente indipendenti e dunque sono base di $\mathbb R_n[x]$. 
Definiamo $$p(x)=y_0L_0(x)x+y_1L_1(x)+\dots+y_nL_n(x)\in \mathbb R_n[x]$$
Verifichiamo che $p(x_i)=y_{i} \: \forall \: i =0,\dots,n$ $$p(x_{i})=y_0L_0(x_i)+y_1L_1(x_{i})+\dots+y_nL_n(x_{i})=y_{i}$$Questo dimostra l'esistenza di un polinomio $p(x)$ che soddisfa le tesi del teorema. 

Dimostriamo ora l'unicità. Supponiamo che $q(x)$ sia un altro polinomio in $\mathbb R_n[x]$ t.c. $q(x_i)=y_{i}\:\forall \: i=0,\dots,n$ , poiché $L_{0}(x),\: L_{1}(x),\dots,\: L_{n}(x)$ sono una base di $\mathbb R_n[x]$, possiamo scrivere $$q(x)=\beta_0L_0(x)+\beta_1L_1(x)+\dots+\beta_nL_n(x)$$
per certi $\beta_0,\beta_1,\dots,\beta_n\in\mathbb R$. Valutando $q(x)$ nei punti $x_0,x_1,\dots,x_n$ otteniamo che $\forall\:i=0,\dots,n$ $$q(x_i)=\beta_0L_0(x_i)+\beta_1L_1(x_i)+\dots+\beta_nL_n(x_i)=\beta_i$$
ma $q(x_i)=y_i$ per $(\triangle)$, quidni possiamo scrivere $$q(x)=y_0L_0(x)+y_1L_1(x)+\dots+y_nL_n(x)=p(x)$$Questo dimostra che $p(x)$ è l'unico polinomio in $\mathbb R_n[x]$ t.c. $p(x_i)=y_{i}\:\: \forall\: i=0,\dots,n$. $\square$

**Def.**
Siano $(x_0,y_0),(x_1,y_1),\dots,(x_n,y_n)\in\mathbb R^2$  con $x_0,x_1,\dots,x_n$ distinti. L'unico polinomio $p(x)\in \mathbb R_n[x]$ t.c. $p(x_i)=y_{i}\:\forall i=0,\dots,n$ si chiama **polinomio di interploazione** dei dati  $(x_0,y_0),(x_1,y_1),\dots,(x_n,y_n)$ o anche **polinomio di interpolazione** dei valori $y_0,\dots,y_n$ sui nodi $x_0,\dots,x_n$.

- La prima dimostrazione del teorema ci dice che $p(x)$ si scrive in forma canonica come $$p(x)=a_0+a_1x+\dots+a_nx^n$$ dove $$\begin{pmatrix} a_0\\ a_1 \\ \vdots \\ a_n \end{pmatrix} = \left[V\left(x_{0},x_{1},\dots,x_{n}\right)\right]^{-1} \begin{pmatrix}y_{0} \\ y_1 \\ \vdots \\ y_n\end{pmatrix}$$e $V(x_0,x_1,\dots,x_n)$ è la matrice di Vandermonde sui nodi $x_0,x_1,\dots,x_n$.
- La seconda dimostrazione ci dice che $$p(x)=y_0L_0(x)x+y_1L_1(x)+\dots+y_nL_{n}(x) \:\: (\$\$)$$dove $\forall \:j=0,\dots,n$ $$L_j(x)=\prod_{k=0, k\neq j}^{n} \frac{x-x_{k}}{x_{j}-x_{k}}=\frac{(x-x_0)\dots(x-x_{j-1})(x-x_{j+1})\dots(x-x_{n})}{(x_{j}-x_0)\dots(x_{j}-x_{j-1})(x_{j}-x_{j+1})\dots(x_{j}-x_{n})}$$
si chiama j-esimo polinomio di Lagrange relativo ai nodi $x_0,x_{1},\dots,x_n$. La $(\$\$)$ si chiama *forma di Lagrange* del polinomio di interpolazione 
- Se gli $y_{i}$ sono i valori nei punti $x_{i}$ di una funzione $f:[a,b] \to \mathbb R$, cioé se risulta $y_i=f(x_{i}) \:\forall \:i=0,\dots,n$, allora il polinomio $p(x)$ si chiama anche **polinomio di interpolazione della funzione $f(x)$** sui nodi $x_0,\dots,x_n$.

## Esempio
Della funzione $\sin(x)$ sono noti i valori nei tre punti $x_0=0,\:x_1=\frac{\pi}{6},\:x_2=\frac{\pi}{4}$ e sono dati da $\sin(x_0)=0,\:\sin(x_1)=\frac{1}{2},\: \sin(x_2)=\frac{\sqrt{2}}{2}$.
Scrivere in forma canonica e in forma di Lagrange il polinomio di interpolazione $p(x)$ di $\sin(x)$ sui nodi $x_0,\:x_1,\:x_2$.

**Soluzione**
Iniziamo dalla forma di Lagrange $$p(x)=\sin(x_0)L_0(x)+\sin(x_1)L_1(x)+\sin(x_2)L_2(x)=$$
$$=\cancel{\sin(x_0)\frac{(x-x_1)(x-x_2)}{(x_0-x_1)(x_0-x_2)}}+\sin(x_1)\frac{(x-x_0)(x-x_2)}{(x_1-x_0)(x_1-x_2)}+\sin(x_2)\frac{(x-x_0)(x-x_1)}{(x_2-x_0)(x_2-x_1)}=$$
$$=\frac{1}{2}\frac{x(x-\frac{\pi}{4})}{\frac{\pi}{6}\cdot(-\frac{\pi}{12})}+ \frac{\sqrt2}{2}\frac{x(x- \frac{\pi}{6})}{\frac{\pi}{4}\cdot \frac{\pi}{12}}=$$
$$=\frac{1}{2}\frac{x(x-\frac{\pi}{4})}{- \frac{\pi^{2}}{72}} + \frac{\sqrt2}{2}\frac{x(x- \frac{\pi}{6})}{\frac{\pi^{2}}{48}}$$
Sviluppando i calcoli e raccogliendo i termini della $x$, portiamo il polinomio in forma canonica:$$p(x)=\left(\frac{9}{\pi}- \frac{4\sqrt2}{\pi}\right)x+\left(- \frac{36}{\pi^{2}}+ \frac{24\sqrt2}{\pi^{2}}\right)x^{2} \:\: (\texteuro)$$
*Oss.*
Dall'espressione della forma canonica di $p(x)$ avevamo che $p(x)=a_0+a_1x+a_2x^2$ con $$\begin{pmatrix} a_0\\ a_1 \\ a_{2} \end{pmatrix} = \left[V\left(0, \frac{\pi}{6},\frac{\pi}{12}\right)\right]^{-1} \begin{pmatrix} 0\\ \frac{1}{2} \\ \frac{\sqrt2}{2} \end{pmatrix}$$
Dunque per confronto con $(\texteuro)$ concludiamo senza risolvere ikl sistema lineare di Vandermonde che $$\left[V\left(0, \frac{\pi}{6},\frac{\pi}{12}\right)\right]^{-1}\begin{pmatrix} 0\\ \frac{1}{2} \\ \frac{\sqrt2}{2} \end{pmatrix}=\begin{pmatrix}0  \\ \frac{9}{\pi}- \frac{4\sqrt2}{\pi} \\ - \frac{36}{\pi^{2}}+ \frac{24\sqrt2}{\pi^{2}}\end{pmatrix}$$

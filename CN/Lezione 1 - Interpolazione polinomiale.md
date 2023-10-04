# Interpolazione polinomiale
È il metodo più naturale e semplice per approssimare una funzione, mediante l'utilizzo di polinomi, che per un calcolatore sono estremamente facili da calcolare, perché deve svolgere solo potenze, moltiplicazioni e somme.

**Problema**
È data una funzione $f:[a, b] \to \mathbb R$ di cui sono noti i valori di $f(x_{0}),\: f(x_{1}), \dots,\: f(x_{n})$ in n+1 punti distiniti $x_{0},\: x_{1},\dots,\: x_{n} \in [a,b]$. 
Si sceglie una classe $\mathcal C$ di funzioni da $[a,b] \to \mathbb R$ e si vuole approssimare la funzione $f(x)$ con una funzione $g(x)$ nella classe $\mathcal C$ tale che $g(x_{i})=f(x_{i})\: \forall \:i = 0, \dots, n$.

Una scelta comune per la sua semplicità è $\mathcal C = \mathbb R_{n}[x]=$  spazio dei polinomi di grado minore uguale di n = $\{a_{0}+a_{1}x+\dots+a_{n}x^{n}\: : \: a_{0},\:a_{1},\dots,\: a_{n} \in \mathbb R\}$.

$\exists! \: p(x)\in \mathcal C=\mathbb R_{n}[x]\: t.c.\: p(x_{i})=f(x_{i})\: \forall \: i = 0,\dots,\:n$
 
**Teorema**
Siano $(x_0,y_0),\:(x_1,y_1),\dots,(x_n,y_{n})\in \mathbb R^2$ tali che $x_{0},\: x_{1},\dots,\: x_{n}$ sono tutti distinti. Allora $\exists! \text{ polinomio } p(x)\in \mathbb R_{n}[x] \text{ tale che }  p(x_{i})=y_{i} \: \forall\: i=0,\:1,\dots,\:n$

*Dim.*
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


# Esercizio 1
1. Scrivere in forma canonica, lagrange e newton il polinomio $p(x)$ sui nodi $x_{0},x_{1},x_{2},\dots$ 

Iniziamo da Lagrange calcolando $f(x_{0}),f(x_{1}),f(x_{2}),\dots$ sostituendo il valore dei nodi al posto della $x$ in $f(x)$. Successivamente sostituiamo nella **FORMULA GENERALE DI LAGRANGE**
$$\begin{align*}
p(x)&=f(x_{0})L_{0}(x)+f(x_{1})L_{1}(x)+\dots+f(x_{n})L_{n}(x)= \\\\
&= f(x_{0})\frac{(x-x_{1})(x-x_{2})(x-x_{3})}{(x_{0}-x_{1})(x_{0}-x_{2})(x_{0}-x_{3})}+f(x_{1})\frac{(x-x_{0})(x-x_{2})(x-x_{3})}{(x_{1}-x_{0})(x_{1}-x_{2})(x_{1}-x_{3})}+\\
&+f(x_{2})\frac{(x-x_{0})(x-x_{1})(x-x_{3})}{(x_{2}-x_{0})(x_{2}-x_{1})(x_{2}-x_{3})}+f(x_{3})\frac{(x-x_{0})(x-x_{1})(x-x_{2})}{(x_{3}-x_{0})(x_{3}-x_{1})(x_{3}-x_{2})} 
\end{align*}$$ sviluppando i calcoli della formula ottenuta sopra, andiamo a determinare la **FORMA CANONICA**.
Per determinare la **FORMA DI NEWTON** dobbiamo calcolare le differenze divise: 
$$\begin{align*}
&f[x_{0}]=f(x_{0})\\
&f[x_{1}]=f(x_{1})\\
&f[x_{2}]=f(x_{2})\\
&f[x_{3}]=f(x_{3})\\
&f[x_{0},x_{1}]=\frac{f[x_{1}]-f[x_{0}]}{x_{1}-x_{0}}\\
&f[x_{0},x_{2}]=\frac{f[x_{2}]-f[x_{0}]}{x_{2}-x_{0}}\\
&f[x_{0},x_{3}]=\frac{f[x_{1}]-f[x_{0}]}{x_{1}-x_{2}}\\
&f[x_{0},x_{1},x_{2}]=\frac{f[x_{0},x_{2}]-f[x_{0},x_{1}]}{x_{2}-x_{1}}\\
&f[x_{0},x_{1},x_{3}]=\frac{f[x_{0},x_{3}]-f[x_{0},x_{1}]}{x_{3}-x_{1}}\\
&f[x_{0},x_{1},x_{2},x_{3}]=\frac{f[x_{0},x_{1},x_{3}]-f[x_{0},x_{1},x_{2}]}{x_{3}-x_{2}}
\end{align*}$$ successivamente sostituiamo nella **FORMULA GENERALE DI NEWTON**
$$p(x)=f[x_{0}]+f[x_{0},x_{1}](x-x_{0})+f[x_{0},x_{1},x_{2}](x-x_{0})(x-x_{1})+f[x_{0},x_{1},x_{2},x_{3}]$$
Per verificare la correttezza, svolgendo i calcoli nella formula di Newton, otteniamo nuovamente la **FORMA CANONICA**.
Quando chiede di scrivere nella forma più opportuna, è richiesta la **FORMA DI LAGRANGE.** 

2. Fornire una stima dell'errore di interpolazione $|f(x)-p(x)|\:\forall x\in[a,b]$, cioè determinare $C$ tale che $|f(x)-p(x)|\le C$ 
Il **TEOREMA SULL'ERRORE DELL'INTERPOLAZIONE** è applicabile solo se la funzione è di classe $C^{\infty}[a,b]$, per ogni $x \in [a,b]$ 
$$\bigg|f(x)-p(x)\bigg|=\bigg|\frac{f^{n}(\xi)}{n!}\cdot\prod\limits_{i=1}^{n} \bigg|$$
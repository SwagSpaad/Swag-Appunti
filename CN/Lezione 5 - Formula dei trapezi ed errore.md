# Formula dei trapezi
È data una funzione (integrabile) $f:[a,b]\to \mathbb R$ e si vuole calcolare un'approssimazione di $\int_{a}^{b}f(x)dx$. A tal fine si suddivide l'intervallo $[a,b]$ in $n\ge1$ sottointervalli tutti della stessa ampiezza $h= \frac{b-a}{n}$ e si pone $x_j=a+jh$ per ogni $j=0,1,\dots,n$. Il valore che si prende come approssimazione di $\int_{a}^{b}f(x)dx$ è $\int_{a}^{b}s(x)dx$  dove $$s:[a,b]\to \mathbb R
\begin{cases} s(x)=f(x_{j})+\frac{f(x_{j+1})-f(x_j)}{x_{j+1}-x_{j}}(x-x_{j}), \\ \\

\text{per } x\in[x_{j},x_{j+1}],\:\:\:j=0,\dots,n-1
\end{cases}$$ è la funzione lineare a tratti mostrata in figura sotto 

![[CN/img/img0.png|center|500]] ^04a94e

Quindi il valore che si prende come approssimazione di $\int_{a}^{b}f(x)dx$ è $$I_n=\int_{a}^{b}s(x)dx=\sum_{j=0}^{n-1}\int_{x_{j}}^{x_{j+1}}s(x)dx=\sum_{j=0}^{n-1}\int_{x_{j}}^{x_{j+1}}\left[f(x_{j})+\frac{f(x_{j+1})-f(x_j)}{x_{j+1}-x_{j}}(x-x_{j})\right]dx$$ $$=\sum_{j=0}^{n-1}\left[f(x_{j})(x-x_{j})+\frac{f(x_{j+1})-f(x_j)}{x_{j+1}-x_{j}}\frac{(x-x_{j})^2}{2}\right]_{x_{j}}^{x_{j+1}}$$ $$=\sum_{j=0}^{n-1}\left[f(x_{j})(x_{j+1}-x_{j})+\frac{f(x_{j+1})-f(x_j)}{2}(x_{j+1}-x_{j})\right]$$ $$=\sum_{j=0}^{n-1}\frac{f(x_{j})+f(x_{j+1})}{2}h=\frac{h}{2}\sum_{j=0}^{n-1}\bigg[f(x_{j})+f(x_{j+1})\bigg]$$ $$=\frac{h}{2}\bigg[f(x_{0})+f(x_{1})+f(x_{1})+f(x_{2})+f(x_{2})+f(x_{3})\dots+f(x_{n-1})+f(x_{n})\bigg]$$ $$=h\left[\frac{f(a)+f(b)}{2}+\sum\limits_{j=1}^{n-1}f(x_{j})\right]$$
cioè $$I_n=h\left[\frac{f(a)+f(b)}{2}+\sum\limits_{j=1}^{n-1}f(x_{j})\right]\:\:\: (\star)$$
La $(\star)$ prende il nome di **formula dei trapezi di ordine n**. L'ampiezza $h= \frac{b-a}{n}$ si chiama anche *passo (di discretizzazione) della formula $I_{n}$*.

# Errore o resto della formula dei trapezi
Qual è l'errore che si commette approssimando $\int_{a}^{b}f(x)dx$ con $I_n$? Per rispondere utilizzeremo il seguente lemma.

**Lemma**
Siano $\omega,\alpha,\beta:[a,b]\to \mathbb R$ funzioni tali che: 
- $\omega(x)$ è continua e $\ge 0$ su $[a,b]$
- $\alpha(x)$ e $\beta(x)\omega(x)$ sono continue su $[a,b]$.
- $m\le \beta(x)\le M$ per ogni $x \in [a,b]$, dove $m$ e $M$ sono rispettivamente il minimo e il massimo di $\alpha(x)$ su $[a,b]$.
Allora esiste un punto $\eta \in [a,b]$ tale che $$\int_{a}^{b}\beta(x)\omega(x)dx=\alpha(\eta)\int_{a}^{b}\omega(x)dx$$ Questo lemma è una generalizzazione del teorema della media integrale. Per $\omega(x)=1$ e $\beta(x)=\alpha(x)$ si ottiene infatti il teorema della media integrale.

**Dim.**
Poiché $\omega(x)\ge 0$ su $[a,b]$ e $m\le \beta(x)\le M$ per ogni $x\in[a,b]$, si ha $m\omega(x)\le\beta(x)\omega(x)\le M\omega(x)$ per ogni $x\in[a,b]$ e $$m\int_{a}^{b}\omega(x)dx\le \int_{a}^{b}\beta(x)\omega(x)dx\le M\int_{a}^{b}\omega(x)dx$$
Consideriamo la funzione$z:[a,b]\to \mathbb R$, $$z(y)=\alpha(y)\int_{a}^{b}\omega(x)dx$$
Questa funzione è continua su $[a,b]$ perché $\alpha(y)$ è continua su $[a,b]$. Quindi per il teorema dei valori intermedi $z(y)$ assume su $[a,b]$ tutti i valori compresi tra il suo minimo $m \int_{a}^{b}\omega(x)dx$ e il suo massimo $M \int_{a}^{b}\omega(x)dx$. In particolare $z(y)$ assume il valore $\int_{a}^{b}\beta(x)\omega(x)dx$, ovvero esiste $\eta\in[a,b]$ tale che $$z(\eta)=\int_{a}^{b}\beta(x)\omega(x)dx\:\:\: \square.$$ 
**Teorema**
Sia $f:[a,b]\to \mathbb R$ di classe $C^{2}[a,b]$ e sia $I_{n}$ la formula dei trapezi di ordine $n$ e passo $h= \frac{b-a}{n}$ per approssimare $\int_{a}^{b}f(x)dx$. Allora esiste un punto $\eta\in[a,b]$ tale che $$\int_{a}^{b}f(x)dx-I_{n}=-\frac{(b-a)f^{''}(\eta)}{12}h^{2}$$

**Dim.**
Poniamo $x_j=a+jh$ per $j=0,\dots,n$ e indichiamo con $s(x)$ la funzione lineare a tratti in [[#^04a94e|figura]]. Osserviamo che $s(x)$ coincide sull'intervallo $[x_j,x_{j+1}]$ con il polinomio (retta) di interpolazione $f(x)$ sui nodi $x_j$ e $x_j+1$. Risulta $$\int_{a}^{b}f(x)dx - I_n=\int_{a}^{b}f(x)dx-\int_{a}^{b}s(x)dx=\int_{a}^{b}\big[f(x)-s(x)\big]dx$$ $$=\sum\limits_{j=0}^{n-1}\int_{x_{j}}^{x_{j+1}}\big[f(x)-s(x)\big]dx$$ $$=\sum\limits_{j=0}^{n-1}\int_{x_{j}}^{x_{j+1}}\frac{f^{''}(\xi_{j}(x))}{2}(x-x_{j})(x-x_{j+1})dx$$
(per il [[Lezione 2 - Errore o resto dell'interpolazione polinomiale#^e77fd4|teorema]] applicato sull'intervallo $[x_{j},x_{j+1}]$; $\xi_{j}(x)$ è un punto in $(x_{j},x_{j+1})$ ) $$=-\sum\limits_{j=0}^{n-1}\int_{x_{j}}^{x_{j+1}}f^{''}(\xi_{j}(x))\frac{(x-x_{j})(x_{j+1}-x)}{2}dx$$ $$=-\sum\limits_{j=0}^{n-1}f^{''}(\eta_{j})\int_{x_{j}}^{x_{j+1}}\frac{(x-x_{j})(x_{j+1}-x)}{2}dx$$
(per il lemma applicato sull'intervallo $[x_j,x_{j+1}]$ con $\alpha(x)=f^{''}(x)$, $\beta(x)=f^{''}(\xi_j(x))$, $\omega(x)=\frac{1}{2}(x-x_{j})(x_{j+1}-x)$; $\eta_j$ è un punto in $[x_j,x_{j+1}]$ )
$$=-\sum\limits_{j=0}^{n-1}f^{''}(\eta_{j})\int_{0}^{h}\frac{t(h-t)}{2}dt$$ (cambio di variabile $t=x-x_{j}\iff x=t+x_{j}$ e si ha $dt=dx$ )
$$=-\sum\limits_{j=0}^{n-1}f^{''}(\eta_{j}) \frac{1}{2}\bigg[\frac{h}{2}t^{2}- \frac{1}{3}t^{3}\bigg]=-\sum\limits_{j=0}^{n-1}f^{''}(\eta_{j}) \frac{h^{3}}{12}=-\frac{h^3n}{12}\cdot\frac{1}{n}\sum\limits_{j=0}^{n-1}f^{''}(\eta_j)=-\frac{h^2(b-a)}{12}f^{''}(\eta)$$
dove l'ultima uguaglianza vale perché, essendo $f^{''}(x)$ continua su $[a,b]$ per ipotesi ed essendo la media aritmetica $\frac{1}{n}\sum\limits_{j=0}^{n-1}f^{''}(\eta_{j})$ un valore compreso tra il minimo e il massimo di $f^{''}(x)$ su $[a,b]$, per il teorema dei valori inermedi esiste sicuramente un $\eta\in [a,b]$ tale che $f^{''}(\eta)= \frac{1}{n}\sum\limits_{j=0}^{n-1}f^{''}(\eta_{j})\:\:\:\square.$ 
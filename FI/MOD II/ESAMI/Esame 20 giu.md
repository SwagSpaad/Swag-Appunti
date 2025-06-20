![[Pasted image 20250612152034.png|center]]

# Problema 1
**Dim.**
- $L_1$ decidibile $\implies$ Esiste MdT $T_{1}$ che per ogni $x\in\Sigma^{*}$ $T_{1}(x) = q_{A}$ se $x\in L_{1}$ e $T_{1}(x)=q_{R}$ se $x\not \in L_{1}$
- $L_2$ decidibile $\implies$ Esiste MdT $T_{2}$ che per ogni $x\in\Sigma^{*}$ $T_{2}(x) = q_{A}$ se $x\in L_{2}$ e $T_{2}(x)=q_{R}$ se $x\not \in L_{2}$

Costruisco $T$ a partire da $T_{1}$ e $T_{2}$ a 3 nastri che opera nel seguente modo: 
1. A partire dal primo blank a sinistra inizio nello stato $q_{pari}$ e mi sposto di cella in cella alternando gli stati ad ogni passo da $q_{dispari}$ a $q_{pari}$. Se alla fine mi trovo nello stato $q_{pari}$ allora l'input $x$ ha lunghezza pari, altrimenti ha lunghezza dispari.
2. Se $x$ ha lunghezza pari $T$ simula $T_{1}(x)$ che se termina in $q_A$ fa terminare $T$ in $q_{A}$, mentre se termina in $q_{R}$ fa terminare $T$ in $q_R$ 
3. Se $x$ ha lunghezza dispari $T$ simula $T_{2}(x)$ che se termina in $q_A$ fa terminare $T$ in $q_{A}$, mentre se termina in $q_{R}$ fa terminare $T$ in $q_R$ 
Siccome $T$ termina per ogni $x$ allora $T$ decide $L$ dimostrando che $L$ è decidibile

# Problema 2
$\textbf{PSPACE} = \cup_{k\in\mathbb{N}}DSPACE[n^{k}]$ 
$\textbf{EXPTIME} = \cup_{k\in\mathbb{N}}DTIME[2^{n^{k}}]$ 

La dimostrazione è conseguenza del teorema che dice che data $f$ totale e calcolabile allora $DSPACE[f(n)]\subseteq DTIME[2^{O(1)f(n)}]$.
Sia $L\subseteq\Sigma^{*}$ tale che $L \in DSPACE[f(n)]$ , allora esiste MdT $T$ che decide $L$ e per ogni $x\in \Sigma^{*}$, il numero di celle utilizzato dalla computazione $T(x)$ $dspace(T,x)\in O(f(|x|))$. 

Poiché $$\begin{align*}
\text{dtime}(T,x)&\le\text{dspace}(T,x)|Q|(|\Sigma|+1)^{\text{dspace}(T,x)}
=2^{\log(\text{dspace}(T,x))}|Q|2^{[1+\log(|\Sigma|+1)]\text{dspace(T,x)}}\\\\
&\le2^{\text{dspace}(T,x)}|Q|2^{\log(|\Sigma| + 1)\text{ dspace}(T,x)}=|Q|2^{[1+\log(|\Sigma|+1)]dspace(T,x)}
\end{align*}$$ 
e quindi $dtime(T,x)\le |Q|2^{[1+\log(|\Sigma|+1)]dspace(T,x)}$ ovvero $dtime(T,x)\in O(2^{O(1)f(|x|)})$ da cui otteniamo che $L\in DTIME[2^{O(1)f(n)}]$ . 
Quindi $\textbf{PSPACE}\subseteq\textbf{EXPTIME}$ in quanto vale $DSPACE[f(n)]\subseteq DTIME[2^{O(1)f(n)}]$ 

# Problema 3
Il problema può essere formalizzato così
- $I_{\Gamma}=\{<G, s, t, k>\}$ dove G è un grafo completo e orientato, $s$ e $t$ sono due nodi del grafo e $k\in\mathbb{N}$ 
- $S_{\Gamma}(G,s,t,k) = \{<u_{1},\dots,u_{n}>\subseteq V\}$
- $\pi_{\Gamma}(G,s,t,k, S_{P}(G,s,t,k))=\exists <u_{1},\dots,u_{n}>\in S_{P}(G,s,t,k)$ tale che $\sum\limits_{i=1}^{n}\omega(u_{i}, u_{i+1})\ge k$$\forall (u_{i}, u_{i+1})\in E$ 

Per dimostrare che il problema $\Gamma\in NP$ dobbiamo trovare un certificato per $\pi$ e verificarlo in tempo polinomiale in $|<G,s,t,k>|$ .
Un certificato è $<u_{1},\dots, u_{n}>\in S_{\Gamma}(G,s,t,k)$ e verificarlo richiede tempo polinomiale in $|<G,s,t,k>|$ quindi $\Gamma\in NP$

Osserviamo che possiamo ridurre il problema LP (LONGEST PATH) a $\Gamma$ e quindi se $LP\in NP-com$ allora anche $\Gamma\in NP-com$ 

**Dim**
$I_{LP}=<G,s,t,k>\to I_\Gamma=<G',s',t','k>$

![[Pasted image 20250612171645.png|center|600]]

$G'=(V', E')$, $V'=V$, $E'= E\cup\{(u,v)\not\in E\}$ , $k'=k$ , $s'=s$, $t'=t$

Per trasformare il grafo $G$ di LP in un istanza di $\Gamma$ lo devo completare aggiungendo archi fittizi di costo 0, mentre a tutti gli altri archi assegno costo 1. La funzione peso diventa $$\omega(u,v)\begin{cases}0 &(u,v)\not\in E\\\\1 &(u,v)\in E\end{cases}$$ **Istanze si**
Se G è istanza si per $LP$ allora $\exists$ un percorso da $s$ a $t$ di lughezza $\ge$ k e quindi $\exists$ un percorso da $s'$ a $t'$ in $G'$ tale che $\sum_{i=1}^{n}\omega(u_{i}, u_{i+1})\ge k'$ quindi $G'$ è istanza si di $\Gamma$ 

**Istanza no**
Se G è istanza no per $LP$ allora $\not\exists$ un percorso da $s$ a $t$ di lughezza $\ge$ k e quindi $\not\exists$ un percorso da $s'$ a $t'$ in $G'$ tale che $\sum_{i=1}^{n}\omega(u_{i}, u_{i+1})\ge k'$ quindi $G'$ è istanza no di $\Gamma$ 

**polinomilaità**
La costruzione di $G'$ richiede tempo $O(|V|\cdot|E|)$ perché devo solo aggiungere degli archi mancanti e dargli valore 0 oppure 1. Quindi $LP\preceq_{p}\Gamma\implies \Gamma\in NPC$ 

Dimostrando che $\Gamma \in NPC$ possiamo concludere che $\Gamma\not\in P$ e $\Gamma\not\in coNP$ 
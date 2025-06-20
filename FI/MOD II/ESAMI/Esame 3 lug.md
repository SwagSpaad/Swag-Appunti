# Problema 1
$L_{1}$ accettabile, $L_{2}$ decidibile. 

$L=\{x\:\:tc\:\:x\text{ inizia con '0'} \land x\in L_{1}\}\cup L_2$ 
$L$ è decidibile?

**Dim.**
$L_1$ accettabile allora esiste una MDT $T_{L1}$ che accetta $L_1$
$L_2$ decidibile allora esiste una MDT $T_{L2}$ che decide $L_2$

A partire da $T_{L1}$ e $T_{L2}$ costruiamo $T_{L}$ a 3 nastri che su input $x\in\Sigma^{*}$ scritto su $N_{1}$ opera così:
1. $T_{L}$ simula $T_{L2}(x)$:
	- se $T_{L2}(x)=q_A$ allora $T_{L}(x)=q_{A}$
	- se $T_{L2}(x)=q_{R}$ vai in fase 2
2. $T_{L}$ simula $T_{L1}(x)$ posizionando la testina di $N_1$ sul primo blank a sinistra e spostandolo a destra di uno:
	- se $T_{L1}(x)=q_{A}$ e la testina legge '0' allora $T_{L}=q_{A}$
	- se $T_{L1}(x)=q_{R}$ allora $T_{L}=q_{R}$
	- se $T_{L1}(x)=q_A$ e la testina legge 1 allora $T_{L}=q_R$ 
Osserviamo che dato che $L_{1}$ è accettabile, nella fase 2 non possiamo affermare che $x\not\in L_{1}$ quindi se $x\not\in L_1$ non possiamo dire nulla riguardo la decidibilità di $L$ quindi $L$ è accettabile
# Problema 2
Diostrare che se $A$ è riducibile polinomialmente a $B$ e  $B$ è riducibile polinomialmente a $C$ allora $A\preceq_{p}C$ 

**Dim.**
Dato che $A\preceq_{p}B$ allora esiste $f:\Sigma_{A}^{*}\to\Sigma_{B}^{*}$ tale che $f\in FP$ e $\forall y \in \Sigma_{A}^{*}[y\in A\iff f(y)\in B]$ 

Dato che $B\preceq_{p}C$ allora esiste $g:\Sigma_{B}^{*}\to\Sigma_{C}^{*}$ tale che $g\in FP$ e $\forall x \in \Sigma_{B}^{*}[x\in B\iff g(x)\in C]$ 

Componiamo le due funzione $f$ e $g$ in una funzione chiamata $h$, che è  ancora una riduzione polinomiale da $A$ a $C$. 
Sia $x\in\Sigma_{A}^{*}$ allora $x\in A\iff g(x)\in B\land g(x)\in B\iff f(g(x))\in C$
Verifichiamo ora se $h\in FP$
Dato che $g\in FP$ allora esiste MDT $T_g$ e $k\in\mathbb{N}$ tale che $\forall x\in \Sigma_{B}^*$ $T_g(x)$ calcola $g(x)$ e $dtime(T_{g},x\le|x|^{k})$ 

Dato che $f\in FP$ allora esiste MDT $T_f$ e $c\in\mathbb{N}$ tale che $\forall y\in \Sigma_{A}^*$ $T_f(x)$ calcola $f(y)$ e $dtime(T_{f},y\le|y|^{c})$ 

Ora deriviamo un trasduttore $T_h$ a 3 nastri che con input $x\in\Sigma^{*}$ scritto su $N_{1}$ opera così: 
1. $T_{h}$ simula $T_{g}(x)$ scriendo l'output $y=g(x)$ su $N_{2}$
2. $T_{h}$ simula $T_{f}(y)$ scrivendo l'output $f(y)$ su $N_{3}$
Quindi $\forall x\in\Sigma_{B}^{*}[\text{dtime}(T_{h},x)\le|x|^{k}+|g(x)|^{c}\le |x|^k+|x|^kc\le2|x|^kc]$ e questo dimostra che $h\in FP$

# Problema 3
Vertex Cover $\cup$ k-Col

$I_{\Gamma}=\{<G,k>\}$ dove $G$ è un grafo non orientato e $k\in\mathbb{N}$
$S_{\Gamma}(G,k)=\{V'\subseteq V\land c:V\to\{1,2,\dots,k\}\}$ 
$\pi_{\Gamma}(G,k,S_{\Gamma}(G,k))=\exists <V',c>\in S_{\Gamma}(G,k)$ tale che $\{|V'|\le k\land\forall u,v\in V[v\in V' \lor u\in V\}\land\{\forall (u,v)\in E[c(u)\not=c(v)]\}$

b.) per dimostrare che $\Gamma\in NP$ dobbiamo dimostrare che esiste un certificato valido per $\pi_{\Gamma}$ e la verifica di validità deve essere fatta in tempo polinomiale deterministico in $|<G,k>|$.
Un possibile certificato è la coppia $<V',c>\in S_{\Gamma}(G,k)$ e verificare che sia valido richiede tempo $O(|V|^{2}\cdot|E|)$ quindi $\Gamma\in NP$

Dimostriamo ora la $NP-completezza$ di $\Gamma$ mediante riduzione dal problema $VC$ (vertex cover)

**Riduzione**
$<G,k>\in I_{VC}\to<G',k'>\in I_{\Gamma}$ 

![[Pasted image 20250613130404.png|center|600]]

$G'=G\cup\{w\: :\: w\not\in G\}$ 
$E'=E\cup\{(u,w)\forall u\in G\}\cup\{(u,v)\not\in E\:\: \forall u,v\in G\}$ 
$k'=k$
La funzione di riduzione aggiunge al grafo $G$ una clique di $k+1$ nodi

**Istanze si**
Se $G$ è istanza si di $VC$ allora esiste $V'\subseteq V$ in $G$ tale che $V'$ è vertex cover. Questo $V'$ esiste anche in $G'$ essendo che $G'$ contiene $G$ e quindi anche $G'$ è istanza si di $\Gamma$

**Istanze no**
Se $G$ è istanza no di $VC$ allora non esiste $V'\subseteq V$ tale che $V'$ è vertex cover per $G$. Allora questo vertex cover non esiste neanche in $G'$ ma dato che la clique aggiunta a G non permette una $k-colorazione$ allora anche $G'$ è istanza no di $\Gamma$

**Polinomialità**
Costruire $G'$ a partire da $G$ richiede tempo $O(|V|\cdot|E|)$ (basta aggiungere un nodo) che è polinomiale in $|<G,k>|$ quindi $\Gamma\in NPC$

Quindi per la prima congettura $\Gamma\not \in P$ e per la seconda congettura $\Gamma\not\in coNP$ 
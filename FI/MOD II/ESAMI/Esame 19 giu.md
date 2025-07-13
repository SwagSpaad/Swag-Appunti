![[Pasted image 20250612212450.png|center]]

# Problema 1
La classe $NTIME[f(n)]$ è definita nel seguente modo $NTIME[f(n)]=\{L\subseteq\{0,1\}^{*}\}$ tale che esiste MDT non deterministca $NT$ che accetta $L$ e $ntime(NT,x)\le f(|x|)$ cioè $O(f(|x|))$

Dimostriamo che tutti i linguaggi in $NTIME[f(n)]$ sono decidibili.

Dato che $f$ è totale e calcolabile esiste una macchina $T_f$ traduttore che calcola $f(x)\forall x\in \mathbb{N}$.
Da NT e $T_{f}$ costruiamo la macchina $NT'$ a 3 nastri che su input $x\in\Sigma^{*}$ opera così:
- su $N_1$ scriviamo x e inizia la computazione $NT'(x)$
1. viene scritto $|x|$ in unario su $N_2$ scrivendo tanti 1 su $N_{2}$ fintanto che su $N_{1}$ leggo caratteri diversi dal blank. Alla lettura del blank riposiziono le testine sul carattere più a sinistra e vado in fase 2
2. $NT'$ simula $T_{f}(|x|)$ usando $N_{2}$ come nastro di input e $N_3$ come nastro di output. La fase termina scrivendo su $N_3$ il valore di $f(|x|)$ in unario
3. $NT'$ simula i primi $f(|x|)$ passi della computazione $NT(x)$ usando $N_{1}$ come input e $N_3$ come contatore. Fintanto che leggo 1 su $N_3$ e non è raggiunto uno stato finale, eseguo un'istruzione di $NT(x)$ e la testina di $N_3$ viene spostata a destra di una cella. Se $NT$ raggiunge lo stato di accettazione/rigetto, la computazione $NT'(x)$ termina nello stato di accettazione/rigetto. Se viene letto blank, la computazione $NT'(x)$ termina nello stato di rigetto
Dato che $f$ è calcolabile e la simulazione di $NT(x)$ in fase 3 viene forzatamente terminata se non ha terminato entro le $f(|x|)$ passi, tutte le computazioni di $NT'(x)$ terminano.
Inoltre
- se $x\in L$ dato che $NT(x)=q_A$ in $f(|x|)$ passi, la simulazione di $NT(x)$ in fase 3 termina in $q_A$ prima che venga letto blank, quindi $NT'$ accetta $x$
- se $x\not\in L$ o la simulazione di $NT(x)$ in fase 3 termina in $q_R$ prima che venga letto blank, oppure non termina prima che venga letto blank. In entrambi i casi $NT'$ rigetta x
Questo dimostra che $NT'$ decide $L$ e quindi $L$ è decidibile

# Problema 2
Dato che $L'\preceq_{p} L$ allora esiste $f:\Sigma_{L'}^{*}\to\Sigma_{L}^{*}$ tale che $f\in FP$ e per ogni $x\in \Sigma^{*}_{L'} [x\in L'\iff f(x)\in L]$. Dato che $L'\in NPC$ allora esiste $L_{0}\in NP$ tale che $L_{0}\preceq_{p} L'$ e quindi esiste $g:\Sigma_{L0}^{*}\to\Sigma_{L'}^{*}$ tale che $g \in FP$ e per ogni $y\in\Sigma_{L0}^{*}[y\in L_{0}\iff g(y)\in L']$. 
La composizione di $f$ e $g$ è una riduzione da $L_{0}$ a $L$. Sia $z\in\Sigma^{*}_{L0}$ allora $x\in L_{0}\iff g(x)\in L'\land g(x)\in L'\iff f(g(x))\in L$. Chiamiamo $h$ questa composizione e vediamo quanto costa calcolare $h$
$g\in FP$ tale che $\exists\:T_{g}, k\:t.c. \forall x\in \Sigma^{*}_{L0}[T_{g}(x) \text{calcola} g(x) \land \text{dtime}(T_{g},x)\le|x|^{k}]$
$f\in FP$ tale che $\exists\:T_{f}, c\:t.c. \forall y\in \Sigma^{*}_{L'}[T_{f}(y) \text{calcola} f(y) \land \text{dtime}(T_{f},y)\le|y|^{c}]$

Definiamo la macchina $T_{h}$ la mcchina a 3 nastri di tipo trasduttore che calcola $h$. 
- input $x\in\Sigma_{L0}^{*}$ scritto sul primo nastro
1. $T_{h}$ esegue la computazione $T_{g}(x)$ scrivendo l'output $y=g(x)$ su $N_{2}$
2. $T_{h}$ esegue la computazione $T_{f}(y)$ scriendo l'output $f(y)$ su $N_{3}$
$\forall x\in\Sigma_{L0}^{*}[\text{dtime}(T_{h},x)\le|x|^{k}+|g(x)|^c\le|x|^{k}+|x|^{kc}\le2|x|^{kc}]$ e ciò dimostra che $h\in FP$. Abbiamo dimostrato che $L_{0}\preceq_{p}L$ e dato che $L_{0}$ è un qualsiasi linguaggio in $NP$ questo prova che ogni linguaggio in $NP$ è riducibile a $L$, quindi $L$ è $NP-completo$

# Problema 3
- $I_{\alpha-col}=\{<G,k>\}$ dove $G$ è un grafo non orientato e $k\in\mathbb{N}$ 
- $S_{\alpha-col}(G,k)=\{<c,V'> \:tc\:\: c:V\to\{1\}\land V'\subseteq V\}$ 
- $\pi_{\alpha-col}(G,k,S_{\alpha-col}(G,k) )=\not\exists V'\in S_{\alpha-col}\: tc\:\forall(u,v)\in E[c(u)=c(v)]\land |V'|\ge k$ 
a.) Per dire che $\alpha-col\in P$ dobbiamo trovare un algoritmo che in tempo polinomiale risolva il predicato. Per fare ciò dovremmo controllare tutti i sottoinsiemi $V'\subseteq V$ e questo richiederebbe tempo esponenziale in $|<G,k>|$ quindi $\alpha-col\not\in P$

b.)  Per dimostrare che $\alpha-col\in NP$ dobbiamo trovare un certificato e verificarlo in tempo polinomiale. Il certificato sono tutte le soluzioni possibili quindi non possiamo affermare che il problema sia in $NP$

c.) Per dimostrare che $\alpha-col\in NP$ dobbiamo dimostrare che $(\alpha-col)^{C} \in NP$. Il complemento del predicato diventa quindi $\exists <c,V'>\in S_{\alpha-col}(G,k)$ tale che $|V'|<k\lor \forall (u,v)\in E[c(u)\not=c(v) ]$ 
A questo punto un possibile certificato è la coppia $<c,V'>$ e verificare che rispetti $\pi$ richiede tempo polinomiale in $|<G,k>|$ quindi dato che $(\alpha-col)^{C}\in NP$ allora $\alpha-col\in coNP$

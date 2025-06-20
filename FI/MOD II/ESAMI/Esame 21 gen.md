![[Pasted image 20250612173736.png|center]]

# Problema 1
- $L_1$ decidibile allora esiste MDT $T_1$ che decide $L_1$
- $L_2$ accettabile allora esiste MDT $T_2$ che accetta $L_2$ 

Costruiamo $T_L$ per $L$ con 4 nastri che opera così: 
dato input $(x,k)$ 
1. verifica se $x\in\Sigma^{*}\land k\in\mathbb{N}$. In caso negativo $T_{L}=q_{R}$ altrimenti vai al secondo punto
2. Leggo $k$ e lo scrivo in unario su $N_{2}$ 
3. Posiziono la testina di $N_2$ sul primo blank a sinistra
4. $T_L$ simula $T_1(x)$. Ad ogni passo sposta a destra la testina di una posizione. 
	1. Se $T_1(x)=q_R$ allora $T_L(x)=q_R$
	2. Se $T_1(x)=q_A$ e la testina legge 1 allora vai al passo 5
	3. Se $T_{1}(x)=q_A$ e la testina legge blank allora $T_{L}(x)=q_R$
5. $T_L$ simula $T_2(x)$ riposizionando la testina sul primo blank a sinistra. Ad ogni passo sposta a destra la testina di una posizione. 
	1. Se $T_2(x)=q_A$ allora $T_L(x)=q_R$
	2. Se $T_2(x)=q_R$ e la testina legge 1 sposto la testina a destra di una posizione
		1. se legge 1 $T_{L}(x)=q_R$
		2. se legge blank $T_L(x)=q_A$
	3. Se $T_{2}(x)=q_R$ e la testina legge blank allora $T_{L}(x)=q_A$

Abbiamo costruito una macchina che potrebbe accettare $T_L$, ma siccome $L_2$ è accettabile e la simulazione $T_2(x)$ al passo 5 per le $x\in L_{2}^{C}$ non terminano, allora $T_L$ non termina. Quindi questo dimostra che $L$ è accettabile ma non decidibile.

# Problema 2
# Problema 3
**Dim.**
Se $L\in NP-completo$ allora $L\in NP$ e quindi $L^{C}\in coNP$. Grazie alla completezza di $L$ abbiamo che $\forall L'\in NP$ allora $L'\preceq L$. Quindi $\forall L'\in NP$ allora esiste una funzione $f_{L}\:\Sigma'\to\Sigma$ tale che $f_{L}\in FP$ e per ogni $x\in\Sigma'$ allora $x\in L'\iff f_{L'}(x)\in L$.
Ma questo significa che $\forall x \in \Sigma'$ allora $x\not\in L'\iff f_{L'}(x)\not\in L$, quindi per ogni $x\in \Sigma^{'}$ $x\in L^{'C}\iff F_{L'}(x)\in L^{C}$ 
Quindi $L^{C}$ è completo per $coNP$


![[Pasted image 20250612124640.png|center]]

# Problema 1

**Dim.** $\implies$ Se $L$ è decidibile allora esiste MdT $T_{L}$ che termina per ogni $x\in\Sigma^{*}$. A partire da $T_L$ costruiamo la macchina $T^{'}$ di tipo trasduttore a due nastri che calcola $\chi_{L}$ che opera nel seguente modo: 
- Dato l'input $x\in\Sigma^{*}$ $T^{'}$ simula $T_{L}(x)$ sul primo nastro. Se $T_{L}(x) = q_{A}$ allora $x\in L$ e quindi $T^{'}$ scrive $1$ sul secondo nastro. Se $T_{L}(x) = q_{R}$ allora $x\not\in L$ in quanto $L$ è decidibile per ipotesi e quindi $T^{'}$ scrive $0$ sul secondo nastro.
Poiché $T_{L}$ decide $L$, $T^{'}$ calcola correttamente $\chi_{L}$ quindi questi dimostra che se $L$ è decidibile $T_{L}$ è calcolabile

**Dim** $\impliedby$ Se $\chi_L$ è calcolabile allora esiste una MdT $T^{'}$ che calcola correttamente $\chi_{L}$. Deriviamo una MdT di tipo riconoscitore $T_{L}$ a 2 nastri che opera nel seguente modo: 
- Dato $x\in\Sigma^{*}$ $T_{L}$ simula $T^{'}(x)$ sul primo nastro. In quanto $T^{'}$ calcola $\chi_L$, la computazione termina con il valore $1$ scritto sul nastro di output se $x\in L$, o col valore $0$ altrimenti. Dopo la simulazione se sul nastro di output c'è scritto $1$ $T_L$ accetta, altrimenti rigetta. 
Per ipotesi $\chi_L$ è calcolabile, quindi sul nastro di output ci sarà scritto $1$ o $0$, pertanto, siccome $T_{L}$ termina in $q_A$ se $x\in L$ e termina in $q_{R}$ se $x\not\in L$, allora $T_{L}$ decide $L$ 

# Problema 2
# Problema 3
**Dim.** Poiché $L_{1}, L_{2}\in \textbf{NP}$ allora esistono le macchine non deterministiche $NT_{1}, NT_{2}$ che accettano $L_{1}$ e $L_{2}$ in tempo polinomiale.
Costruiamo la macchina $NT_{L}$ che simula sia $NT_{1}(x)$ che $NT_{2}(x)$.
Dato $x\in\Sigma^{*}$ $NT_{L}$ simula in contemporanea $NT_{1}(x)$ e $NT_{2}(x)$. Se $NT_{1}(x)=q_{A}\lor$ $NT_{2}(x) = q_A$ allora $NT_{L(x)}= q_A$, altrimenti rigetta.
La simulazione di $NT_{1}(x)$ e $NT_{2}(x)$ richiede tempo polinomiale perché entrambe le macchine $NT_{1}$ e $NT_{2}$ accettano in tempo polinomiale, perchiò anche $L = L_{1}\cup L_{2}$ può essere accettato in tempo polinomiale e questo dimostra quindi che $L\in\textbf{NP}$ 
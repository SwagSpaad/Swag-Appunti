![[Pasted image 20250617125002.png|center]]

# Problema 1
Per codificare le macchine di Turing sull'alfabeto $\{0,1\}$ è necessario codificare l'insieme degli stati $Q$ in binario. L'alfabeto della macchina universale sarà quindi $\Sigma_{U}=\{0,1,\oplus,\otimes,-,f,s,d\}$. Per la codifica degli stati definiamo la funzione $b^{Q}:Q\to\lceil\log|Q|\rceil$ che codifica gli stati della macchina di Turing utilizzando $m=\lceil\log|Q|\rceil$ cifre e per ogni stato $\omega\in Q$ indichiamo con $b^{Q}(\omega)=b_{1}^{Q}(\omega)b_{2}^{Q}(\omega)\dots b_{m}^{Q}(\omega)$ la codifica di $\omega$.
La descrizione di $T$ è la parola $\rho_T\in\Sigma_{U}$ descritta in questo modo: $$\rho_{T}=b^{Q}(w_{0})-b^{Q}(w_{1})\otimes b^{Q}(w_{1_1})-b_{1_{1}}-b_{1_{2}}-b^{Q}(\omega_{1_2})-m_{1}\oplus\dots\oplus$$ dove 
- $b^Q(\omega_{0})$ è la codifica in binario dello stato iniziale
- $b^Q(\omega_{1})$ è la codifica in binario dello stato di accettazione
- a partire dal carattere $\otimes$ iniziano le quintuple di $T$, la cui fine è determinata dal carattere $\oplus$ 

# Problema 2
L'esistenza di un algoritmo polinomiale per SAT avrebbe un grande impatto sulla teoria e pratica dell'informatica teorica. Il problema SAT è noto essere il primo problema NP-completo e quindi uno dei problemi "più difficili". Se esistesse un algoritmo polinomiale per SAT avremmo delle conseguenze su: 
- complessità, perché l'esistenza di un algoritmo polinomiale per SAR dimostrerebbe che $SAT\in P$, contraddicendo quindi la prima congettura fondamentale $P\not=NP$ 
- applicazioni partiche, poiché l'esistenza di un algoritmo polinomiale per SAT consentirebbe di risolvere ogni problema NP-completo in tempo deterministico polinomiale
# Problema 3
$I_{VC\land kIS}=\{<G,k>\}$ dove G è un grafo non orientato e $k\in\mathbb{N}$
$S_{VC\land kIS}((G,k))=\{<V',IS>:V'\subseteq V\land IS\subseteq V\}$ 
$\pi_{VC\land kIS}(G,k,S_{VC\land kIS}(G,k))=\exists<V',IS>:\{\forall(u,v)\in E[u\in V'\lor v\in V']\land|V'|=1\}\lor$ $\{\forall u,v\in V[(u,v)\not\in E]\land |IS|\ge k\}$ 

b.) Per verificare che il problema sia in NP dobbiamo verificare che esiste un certificato per $\pi$ e che la verifica richieda tempo polinomiale.
Un possibile certificato è la coppia $<V',IS>$ e verificare che $V'\subseteq V$ sia un $VC$ di cardinalità 1 oppure che $IS\subseteq V$ sia $IS$ per $G$ richiede tempo polinomiale in $|<G,k>|$. Dato che sia $VC$ che $IS$ sono entrambi problemi NP-completi allora il problema $\in NP$.

Mostriamo la NP-completezza tramite una riduzione dal problema $IS$

$<G,k>\in I_{IS}\to<G',k'>\in I_\Gamma$

![[Pasted image 20250617230959.png|center|500]]

$G'=G\cup\{u_{n},v_{n}:u_{n},v_{n}\not\in V\}$ 
$E'=E^{c}\cup\{(u,u_{n})\forall u\in V \land(u,v_{n})\forall u \in V\land(u_{n},v_{n}\not\in E)\}$
La funzione di riduzione aggiung al grafo di partenza due nuovi nodi e li collega a tutti gli altri tranne che tra di loro, invertendo gli archi originali

**Istanze si**
Se $G$ è  istanza si di $IS$ allora anche $G'$ è istanza si di $\Gamma$ dato che anche se il grafo è invertito esiste comunque un independent set di almeno $k$ nodi

**Istanze no**
Se $G$ è istanza no di $IS$ allora non esiste un independent set in $G$ e non esiste neanche in $G'$ ma non esiste neanche un $VC$ di cardinalità 1 dato che i 2 nodi aggiunti fanno si ch enon si possa coprire l'intero grafo con un solo nodo, ma saranno necessari 2 nodi, ovvero quelli aggiunti

Costruire il grafo $G'$ partendo da $G$ richiede tempo $O(|V|\cdot|E|)$ che è polinomiale in $|<G,k>|$. Abbiamo dimostrato quindi che $\Gamma\in NP-c$ quindi per la prima congettura $\Gamma\not\in P$ e per la seconda $\Gamma\not\in coNP$








a. Per verificare che il problema sia in P dovremmo trovare un algoritmo polinomiale che risolve il problema in tempo polinomiale, ma dato che sia $VC$ che $IS$ sono problemi $NP-completi$ allora il problema $\not\in P$

c.) Per verificare che il problema sia in coNP sobbiamo verificare che il complemento sia in NP, ovvero 
$pi^{C}=\not\exists<V',IS>:V' \:è\: VC \land IS\:è\:IS$ 

Lo spazio della soluzione è tutto $S$ e quindi non possiamo verificare un certificato in tempo polinomiale, quindi $\Gamma^{c}\not\in NP\implies\Gamma\not\in coNP$ 
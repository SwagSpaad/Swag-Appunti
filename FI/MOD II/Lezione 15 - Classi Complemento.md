*******
Consideriamo gli elementi:
$coP=\{L\subset \{0,1\}^{*}:L^{c}\in P\},$  
$coNP=\{L\subset \{0,1\}^{*}:L^{c}\in NP\},$ 

Ed allo stesso modo, anche le classi 
$coEXPTIME,\ coNEXPTIME,\ coPSPACE$ 

E:
$coDTIME[f(n)]=\{L\subset \{0,1\}^{*}:L^{c}\in DTIME[f(n)]\},$ 
$coDSPACE[f(n)]=\{L\subset \{0,1\}^{*}:L^{c}\in DSPACE[f(n)]\},$ 
$coNTIME[f(n)]=\{L\subset \{0,1\}^{*}:L^{c}\in NTIME[f(n)]\},$ 
$coNSPACE[f(n)]=\{L\subset \{0,1\}^{*}:L^{c}\in NSPACE[f(n)]\},$ 

**Oss.**
Nella definizione delle **classi di complessità complemento** non viene specificato nè la decisione nè l'accettabilità dei linguaggi appartenenti, ma soltanto la decisione e l'accettabilità dei **linguaggi complementari** appartenenti.
*Quando si parla di classi deterministiche in realtà fare questa differenziazione è irrilevante*

**Teorema 6.11**
Per ogni funzione totale e calcolabile $f:\mathbb{N} \rightarrow \mathbb{N},$
$$DTIME[f(n)] = coDTIME[f(n)]\ e\ DSPACE[f(n)] = coDSPACE[f(n)]$$
**Dim.**
Prendiamo una macchina $T$ che decide $L$ : $\forall x, dtime(T,x)\in O(f(|x|))$ o
$\forall x, dspace(T,x)\in O(f(|x|))$

Si costruisce una nuova macchina $T'$ complementando gli stati di accettazioni e di rigetto di $T$, quindi aggiungendo le quintuple $<q_{A}, \ S, \ S, \ q'_{R}, \ F>$ e $<q_{R}, \ S, \ S, \ q'_{A}, \ F>$ $\forall S \in \{0,1,\square\},$ dove $q'_{A}$ e $q'_{R}$ sono gli stati di accettazione e di rigetto di $T'$  
Quindi:
$T'$ dedice $L^{c}$ e $dtime(T',x)\in O(f(|x|))$ o $dspace(T',x)\in O(f(|x|))$

**Corollario 6.3**
$P=coP$, ma anche $coSPACE=PSPACE$

**E per le classi non deterministiche si può utilizzare la stessa dimostrazione del Teorema 6.11 ?**

**Si possono complementare gli stati di accettazione e di rigetto di una $NT$ che accetta $L$ al fine di accettare $L^{c}$ ?**

Le classi non deterministiche sono definite come classi di linguaggi accettati da macchine non deterministiche entro quantità limitate di istruzioni o celle di nastro.
**Vero,** anche se la classe $NP$ è definita come la classe dei linguaggi accettati in tempo non deterministico polinomiale, i linguaggi in $NP$ sono linguaggi decisi da macchine non deterministiche in tempo polinomiale.

Ricordiamo che una macchina di Turing non deterministica $NT$:
- **Accetta** input $x$ se $\exists \ T(x) \ in \ NT(x)$ che termina in $q_{A}$  
- **Rigetta** input $x$ se $\forall \ T(x) \ in \ NT(x)$ termina in $q_{R}$ 

Il **Problema** quindi sta propio nelle asimmetrie delle definizioni di accettazione e rigetto

Adesso proviamo ad applicare la stessa tecnica utilizzata nel teorema 6.11 ad una macchina non deterministica $NT$
- Costruiamo una macchina $NT'$ invertendo gli stati di accettazione e di rigetto di $NT$ e vediamo se accetta il complemento del linguaggio accettato da $NT$ 

**Start**: 
Scegliamo un linguaggio $L \subseteq \{ 0,1\}^{*}$ accettato da una macchina di Turing non deterministica $NT$
**Ricordiamo** che il linguaggio complemento di $L$ è $L^{c}=\{0,1\}^{*} - L,$ ovvero, $\forall x \in \{0,1\}^{*}$ 
- Se $x \in L \implies x \notin L^{c}$  
- Se $x \notin L \implies x \in L^{c}$
**Allora**:
Una macchina non deterministica $NT^{c}$ accetta $L^{c}$ se, $\forall x \in \{0,1\}^{*},$
- Se $x \in L \implies NT^{c}(x)$ non accetta 
- Se $x \notin L \implies NT^{c}(x)$ accetta
**Quindi**:
- Se $x \in L \implies \forall T(x) \in NT^{c}(x)$ non termina in $q_{A}$
- Se $x \notin L \implies \exists T(x) \in NT^{c}(x)$ che termina in $q_{A}$ 

Prima di invertire gli stati di accettazione e di rigetto di $NT,$ costruiamo una nuova macchina $NT_{1}$ che ancora accetta $L$
**Quindi**:
Prendiamo $NT$ ed aggiungiamo all'insieme delle sue quintuple, le quintuple:
$<q_{0}, \ S, \ S, \ q_{R}, \ F>, \ \forall S \in \{0,1,\square \}$ 

> [!WARNING] 
> $\forall x \in \{0,1\}^{*} \ \exists \ Sempre \ T_{1}(x)$ di $NT_{1}(x)$ che termina in $q_{R}$      

![[FI/MOD II/img/img10.png|center|500]]    
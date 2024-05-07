*******
Nella [[Lezione 13 - Funzioni time e space-constructible e specifiche classi di complessità|lezione 13]] abbiamo introdotto le specifiche [[Lezione 13 - Funzioni time e space-constructible e specifiche classi di complessità#Specifiche classi di complessità|classi di complessità]]. Consideriamo i suoi complementi
$$\begin{align*}
&co\textbf{P}=\{L\subset \{0,1\}^{*}:L^{c}\in \textbf{P}\}\\
&co\textbf{NP}=\{L\subset \{0,1\}^{*}:L^{c}\in \textbf{NP}\}\\
\end{align*}$$ 
e allo stesso modo, anche le classi 
$co\textbf{EXPTIME},\ co\textbf{NEXPTIME},\ co\textbf{PSPACE}$ 

In generale definiamo anche
$$\begin{align*}
&\text{coDTIME}[f(n)]=\Big\{L\subset \{0,1\}^{*}:L^{c}\in \text{DTIME}[f(n)]\Big\}\\
&\text{coSPACE}[f(n)]=\Big\{L\subset \{0,1\}^{*}:L^{c}\in \text{DSPACE}[f(n)]\Big\}\\
&\text{coNTIME}[f(n)]=\Big\{L\subset \{0,1\}^{*}:L^{c}\in \text{NTIME}[f(n)]\Big\}\\
&\text{coNSPACE}[f(n)]=\Big\{L\subset \{0,1\}^{*}:L^{c}\in \text{NSPACE}[f(n)]\Big\}
\end{align*}$$ 
**Oss.**
Nella definizione delle **classi di complessità complemento** non viene specificato come vengono decisi o accettati i linguaggi che vi appartengono, ma viene specificato come vengono decisi o accettati i complementi dei linguaggi che appartengono.

*Quando si parla di classi deterministiche in realtà fare questa differenziazione è irrilevante*

**Teorema 6.11**
Per ogni funzione totale e calcolabile $f:\mathbb{N} \rightarrow \mathbb{N},$
$$\text{DTIME}[f(n)] = \text{coDTIME}[f(n)]\ e\ \text{DSPACE}[f(n)] = \text{coDSPACE}[f(n)]$$
**Dim.**
Prendiamo una macchina $T$ che decide $L$ tale che, per ogni $x, \text{dtime}(T,x)\in O(f(|x|))$.
Si costruisce una nuova macchina $T^{'}$ complementando gli stati di accettazioni e di rigetto di $T$, quindi aggiungendo le quintuple $\langle q_{A},s,s,q'_{R},f\rangle$ e $\langle q_{R},s,s,q'_{A},f\rangle$ per ogni $s \in \{0,1,\square\},$ dove $q'_{A}$ e $q'_{R}$ sono gli stati di accettazione e di rigetto di $T^{'}$. 
$T^{'}$ dedice $L^{c}$ e $\text{dtime}(T',x)\in O(f(|x|))$. $\square$ ^6dd54d

Analogo per $\text{DSPACE}[f(n)] = \text{coDSPACE}[f(n)]$.

Dal **teorema 6.11** si deriva il seguente corollario

**Corollario 6.3**
$\textbf{P}=co\textbf{P}$, ma anche $co\textbf{PSPACE}=\textbf{PSPACE}$ ^3c9094

# La classe $co\textbf{NP}$
Ricordiamo che $\textbf{NP}$ è la classe dei linguaggi *accettati* in tempo polinomiale da una macchina non deterministica $NT$, quindi $co\textbf{NP}$ è la classe dei linguaggi il cui complemento è accettato in tempo polinomiale da una macchina non deterministica, cioé $$co\textbf{NP}=\{L:L^{c}\in\textbf{NP}\}$$A questo punto potremmo pensare che, come $\textbf{P}$ coincida col suo complemento $co\textbf{P}$, allora anche $\textbf{NP}$ e $co\textbf{NP}$ coincidono. 
Iniziamo ricordando l'asimmetria delle definizioni di accettazone e rigetto di una macchina non deterministica, infatti $NT$:
- **accetta** un input $x$ se esiste  una computazione deterministica in $NT(x)$ che termina in $q_{A}$  
- **rigetta** un input $x$ se ogni computazione deterministica in $NT(x)$ termina in $q_{R}$ 

Il **problema** quindi sta propio nelle asimmetrie delle definizioni di accettazione e rigetto.
Infatti, proviamo ad applicare la stessa tecnica utilizzata nel teorema 6.11 ad una macchina non deterministica $NT$
- Costruiamo una macchina $NT'$ invertendo gli stati di accettazione e di rigetto di $NT$ e vediamo se accetta il complemento del linguaggio accettato da $NT$ 
Scegliamo un linguaggio $L \subseteq \{ 0,1\}^{*}$ accettato da una macchina di Turing non deterministica $NT$.
Ricordiamo che il linguaggio complemento di $L$ è $L^{c}=\{0,1\}^{*} - L,$ ovvero, per ogni $x \in \{0,1\}^{*}$ 
- Se $x \in L \implies x \notin L^{c}$  
- Se $x \notin L \implies x \in L^{c}$
Allora, una macchina non deterministica $NT^{c}$ accetta $L^{c}$ se, per ogni $x \in \{0,1\}^{*},$
- Se $x \in L \implies NT^{c}(x)$ non accetta 
- Se $x \notin L \implies NT^{c}(x)$ accetta
e quindi
- Se $x \in L \implies$ ogni computazione deterministica in $NT^{c}(x)$ **non** termina in $q_{A}$
- Se $x \notin L \implies$ esiste una computazione deterministica in $NT^{c}(x)$ che termina in $q_{A}$ 
Prima di invertire gli stati di accettazione e di rigetto di $NT,$ costruiamo una nuova macchina $NT_{1}$ che accetta $L$.
Prendiamo $NT$ ed aggiungiamo all'insieme delle sue quintuple, le quintuple $\langle q_{0},s,s,q_{R},f\rangle$ per ogni $s \in \{0,1,\square \}$ 

> [!WARNING] 
> Per ogni $x \in \{0,1\}^{*}$ **esiste sempre** una computazione deterministica di $NT_{1}(x)$ che termina in $q_{R}$.

![[FI/MOD II/img/img10.png|center|500]]     

$NT_{1}$ **accetta** $L$, infatti, per ogni $x\in L$: poiché $NT$ accetta $L$, allora $NT(x)$ accetta e allora esiste una computazione deterministica di $NT(x)$ che termina in $q_A$, ma quella stessa computazione deterministica compare anche in $NT_{1}(x)$ e, quindi, $NT_{1}(x)$ accetta

![[FI/MOD II/img/img12.png|center|600]]

D'altra parte, per ogni $x\not\in L$: poiché $NT$ accetta $L$, allora $NT(x)$ non accetta (rigetta o non termina) e allora non esiste alcuna computazione deterministica di $NT(x)$ che termina in $q_A$, e allora stesso modo non esiste in $NT_{1}(x)$ una computazione deterministica che accetta e, quindi, $NT_{1}(x)$ non accetta

![[FI/MOD II/img/img13.png|center|600]]

Quindi abbiamo un linguaggio $L\subseteq\{0,1\}^{*}$ accettato dalla macchina non deterministica $NT_{1}$ e ora applichiamo la stessa tecnica utilizzata nella dimostrazione del [[#^6dd54d|teorema 6.11]]: costruiamo una nuova macchina $NT_{1}^{C}$ invertendo gli stati di accettazione e rigetto di $NT_{1}$. Quello che ci aspettiamo è che $NT_{1}^{C}$ accetti $L^{C}$, vediamo.
Scegliamo $x\in\{0,1\}^{*}$ e poniamo $x=x_{1}x_{2}\dots x_{n}$. Se $x\in L^{C}$:
- in $NT_{1}(x)$ esiste la computazione deterministica $\langle q_{0},x_{1},x_{1},q_{R},f\rangle$ che termina in $q_{R}$ 
- la stessa computazione deterministca compare anche in $NT_{1}^{C}(x)$ che invece termina in $q_{A}$. Quindi $NT_{1}^{C}(x)$ accetta.
Se $x\not\in L^{C}$:
- se fosse vero che $NT_{1}^{C}$ decide $L^{C}$, allora $NT_{1}(x)^{C}$ dovrebbe non accettare.
- In $NT_{1}(x)$ esiste la computazione deterministica $\langle q_{0},x_{1},x_{1},q_{R},f\rangle$ che termina in $q_R$
- la stessa computazione deterministca compare anche in $NT_{1}^{C}(x)$ che invece termina in $q_{A}$. Quindi $NT_{1}^{C}(x)$ accetta, ma ricordiamoci che $x\not\in L^{C}$ e quindi $NT_{1}(x)$ non dovrebbe accettre!
Quindi $NT_{1}^{C}$ accetta qualunque sia $x$ e allora $NT_{1}^C$ non accetta $L^{C}$! 

Quindi l'asimmetria delle definizioni di accettazione e rigetto nelle macchine non deterministiche non permette di derivare una macchina che decide $L^{C}$ invertendo gli stati di accettazione e rigetto di una macchina non deterministica che decide $L$. 

Quindi non possiamo affermare che $co\textbf{NP}=\textbf{NP}$. Ma tutto questo ragionamento ci permette di affermare che $co\textbf{NP}\neq\textbf{NP}$? No, perché la dimostrazione dell'uguaglianza potrebbe seguire una strada diversa da quella dell'inversione degli stati finali di una macchina non deterministica.

Abbiamo detto nelle lezioni passate che le relazioni fra le classi di complessità sono inclusioni deboli, nelle quali non si riesce a dimostrare né che le due classi sono diverse, né che sono uguali. 
Il caso più famoso riguarda le classi $\textbf{P}$ e $\textbf{NP}$. Sappiamo che $\textbf{P}\subseteq\textbf{NP}$ e quindi ogni problema in $\textbf{P}$ è contenuto anche in $\textbf{NP}$. Ma non sappiamo se $\textbf{P}=\textbf{NP}$, ossia se ogni problema in $\textbf{NP}$ è contenuto in $\textbf{P}$ e non sappiamo neanche  se $\textbf{P}\neq\textbf{NP}$, ossia se esiste un problema in $\textbf{NP}$ che non è contenuto in $\textbf{P}$. 

La **congettura fondamentale della teoria della complessità computazionale** ipotizza che $\textbf{P}\neq\textbf{NP}$ e ora abbiamo scoperto una nuova congettura, ovvero la **seconda congettura della teoria della complessità computazionale**, che ipotizza che $co\textbf{NP}\neq\textbf{NP}$.

Le due congetture non sono del tutto indipendenti, come descritto nel prossimo teorema. 

**Teorema 6.23**
Se $\textbf{P}=\textbf{NP}$ allora $\textbf{NP}=co\textbf{NP}$ 

**Dim.**
Per il [[#^3c9094|corollario 6.3]] $\textbf{P}=co\textbf{P}$ e per l'ipotesi $\textbf{P}=\textbf{NP}$ e quindi $co\textbf{P}=co\textbf{NP}$ e allora $\textbf{NP}=\textbf{P}=co\textbf{P}=co\textbf{NP}$. $\square$

Il teorema afferma che se è falsa la **congettura fondamentale della teoria della complessità computazionale** allora è falsa anche la **seconda congettura della teoria della complessità computazionale**

Questo teorema può essere anche letto "se $\textbf{NP}\neq co\textbf{NP}$ allora $\textbf{P}\neq\textbf{NP}$", ovvero se è vera la **seconda congettura della teoria della complessità computazionale** allora è vera anche la **congettura fondamentale della teoria della complessità computazionale**.

**Teorema 6.24**
La classe $co\textbf{NP}$ è chiusa rispetto alla riducibilità polinomiale. 

**Dim.** analoga a quella del [[Lezione 14 - Riduzioni polinomiali#^31c469|teorema 6.21]].

Come per tutte le classi di complessità, anche per la classe $co\textbf{NP}$ possiamo definire i linguaggi completi rispetto alla riducibilità polinomiale.

**Def.**
Un linguaggio è  $co\textbf{NP}$-completo se: 
- $L\in co\textbf{NP}$ 
- per ogni linguaggio $L^{'}\in co\textbf{NP}$ si  ha che $L^{'}\preceq L$ 

Come visto nella scorsa lezione, i linguaggi $\textbf{NP}$-completi sono i possibili *linguaggi separatori* fra $\textbf{P}$ e $\textbf{NP}$, ossia, nell'ipotesi $\textbf{P}\neq\textbf{NP}$, **un linguaggio $\textbf{NP}$-completo non può essere contenuto in $\textbf{P}$**.

Ci proponiamo di fare la stessa cosa con la classe $co\textbf{NP}$, quindi vogliamo dimostrare che i linguaggi $co\textbf{NP}$-completi sono i candidati ad essere i linguaggi separatori fra $\textbf{NP}$ e $co\textbf{NP}$, ossia, nell'ipotesi che $\textbf{NP}\neq co\textbf{NP}$, un linguaggio $co\textbf{NP}$-completo **non può essere contenuto** in $\textbf{NP}$. 

I prossimi due teoremi mirano proprio a questo obiettivo. 

**Teorema 6.25**
Un linguaggio $L$ è $\textbf{NP}$-completo se e solo se il suo complemento $L^{c}$ è $co\textbf{NP}$-completo.

**Dim.**
$\implies$Sia $L$ un linguaggio $\textbf{NP}$-completo e mostriamo che $L^{C}$ è un linguaggio $co\textbf{NP}$-completo.
- $L\in\textbf{NP}$ e quindi $L^{C}\in co\textbf{NP}$ 
Dobbiamo mostrare che per ogni $L_{1}\in co\textbf{NP}$ vale che $L_{1}\preceq L^{C}$.
- Sia allora $L_1$ un qualsiasi linguaggio in $co\textbf{NP}$, allora $L_{1}^{C}\in \textbf{NP}$. Poiché $L$ è completo per la classe $\textbf{NP}$ allora, per ogni $L_{0}\in \textbf{NP}$, $L_0\preceq L$, allora, in particolare, poiché $L_{1}^{C}\in \textbf{NP}$, vale che $L_{1}^{C}\preceq L$. Questo significa che esiste una funzione $f_{1}:\{0,1\}^{*}\to\{0,1\}^{*}$ tale che $f_{1}\in\textbf{FP}$ e, per ogni $x\in\{0,1\}^{*}$, $x\in L_{1}^{C}$ se e solo se $f_{1}(x)\in L$. Ma questo equivale a dire che, per ogni $x\in\{0,1\}^{*}$, $x\not\in L_{1}^{C}$ se e solo se  $f_{1}(x)\not\in L$, ossia **per ogni $x\in\{0,1\}^{*}$, $x\in L_{1}$ se e soltanto se $f_{1}(x)\in L^{C}$** e quindi $L_{1}\preceq L^{C}$. 
Poiché $L_{1}$ è un qualsiasi linguaggio in $co\textbf{NP}$, questo dimostra che $L^{C}$ è completo per $co\textbf{NP}$.

$\impliedby$ Sia $L^{C}$ un linguaggio $co\textbf{NP}$-completo e mostriamo che $L$ è $\textbf{NP}$-completo.
- $L^{C}\in co\textbf{NP}$ e quindi $L\in\textbf{NP}$ 
Dobbiamo mostrare che, per ogni $L_{1}\in \textbf{NP}$, vale che $L_{1}\preceq L$.
- Sia $L_1$ un qualsiasi linguaggio in $\textbf{NP}$, allora $L_{1}^{C}\in co\textbf{NP}$, poiché $L^{C}$ è completo per $co\textbf{NP}$, allora per ogni $L_{0}\in co\textbf{NP}$, $L_{0}\preceq L^{C}$, in particolare, poiché $L_{1}^{C}\in co\textbf{NP}$, vale che $L_{1}^{C}\preceq L^{C}$.  Questo significa che esiste una funzione $f_{1}:\{0,1\}^{*}\to\{0,1\}^{*}$ tale che $f_{1}\in\textbf{FP}$ e, per ogni $x\in\{0,1\}^{*}$, $x\in L_{1}^{C}$ se e solo se $f_{1}(x)\in L^{C}$. Ma questo equivale a dire che, per ogni $x\in\{0,1\}^{*}$, $x\not\in L_{1}^{C}$ se e solo se  $f_{1}(x)\not\in L^{C}$, ossia **per ogni $x\in\{0,1\}^{*}$, $x\in L_{1}$ se e soltanto se $f_{1}(x)\in L$**.
Poiché $L_{1}$ è un qualsiasi linguaggio in $\textbf{NP}$, questo dimostra che $L$ è completo per $\textbf{NP}$. $\square$

**Teorema 6.26**
Se esiste un liguaggio $L$ $\textbf{NP}$-completo tale che $L\in co\textbf{NP}$, allora $\textbf{NP}=co\textbf{NP}$.

**Dim.**
Dimostriamo il teorema mostrando prima che
1. $co\textbf{NP}\subseteq \textbf{NP}$
2. $\textbf{NP}\subseteq co\textbf{NP}$
Sia $L$ un qualunque linguaggio $\textbf{NP}$-completo tale che $L\in co\textbf{NP}$
1. Poiché $L\in co\textbf{NP}$, allora $L^{C}\in \textbf{NP}$. Poiché $L$ è $\textbf{NP}$-completo, per il teorema 6.25 $L^{C}$ è $co\textbf{NP}$-completo, quindi per ogni $L^{'}\in co\textbf{NP}$ si ha che $L^{'}\preceq L^{C}$. Ma $\textbf{NP}$ è chiusa rispetto alla riducibilità polinomiale e allora per ogni linguaggio $L^{'}\in co\textbf{NP}$ si ha che $L^{'}\in \textbf{NP}$. Questo dimostra che $co\textbf{NP}\subseteq \textbf{NP}$
2. Poiché $L$ è $\textbf{NP}$-completo allora, per ogni $L^{''}\in \textbf{NP}$ si ha che $L^{''}\preceq L$, ma $L\in co\textbf{NP}$ e inoltre $co\textbf{NP}$ è chiusa rispetto alla riducibilità polinomiale, allora per ogni $L^{''}\in \textbf{NP}$ si ha che $L^{''}\in co\textbf{NP}$ e questo dimostra che $\textbf{NP}\subseteq co\textbf{NP}$.
$\square$


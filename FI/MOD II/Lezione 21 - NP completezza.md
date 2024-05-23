*****
# Teorema di Cook-Levin e la struttura di NP

Partiamo dalla domande precedente:
**Fra i problemi in $NP$ che non si riesce a collocare in $P$, ce ne sono alcuni più "difficili" rispetto ad altri?**

Il *Teorema di Cook-Levin* ci dice che $NP$ contiene un problema $NP-$completo ($SAT$), e dato che sappiamo che i problemi completi per una classe sono i problemi più "difficili" fra i problemi in quella classe, il *Teorema di Cook-Levin* ci dice che $SAT$ è uno dei problemi più difficili in $NP$, perchè sappiamo che se $SAT$ appartenesse a $P$, allora anche ogni altro problema in $NP$ apparterrebbe a $P$, perchè ricordiamo, $P$ è chiuso rispetto alla riducibilità polinomiale.
Ma il *Teorema di Cook-Levin* ci dice molto di più!

# Il Teorema e la congettura
Sappiamo della congettura $P\neq NP$
Bene, arriva qualcuno e dimostra che $P = NP$ e lo fa **descrivendo un algoritmo deterministico che decide SAT in tempo polinomiale**
Ma a cosa ci serve sapere che $P=NP$?
Beh molto semplice, prendendo un problema in $NP$ so che *esiste* un algoritmo deterministico che lo decide in tempo polinomiale.
Ma che ci faccio con l'*esistenza*?

# Se sapessi che $P=NP$ 
A che mi serve sapere che, siccome un problema si trova in $NP$ e $P=NP$, un algoritmo deterministico polinomiale che lo decide *esiste*, se io un tale algoritmo non riesco a progettarlo?
In realtà il *teorema di Cook-Levin* fa molto di più che dimostrare che $SAT$ è $NP-completo$ 
La dimostrazione del teorema di *Cook-Levin* è la descrizione di un algoritmo deterministico che trasforma le istanze di un qualunque problema $NP$ in istanze di $SAT$. 
**Se abbiamo un algoritmo deterministico polinomiale che decide $SAT$ allora la dimostrazione del teorema di Cook-Levin ci mostra come costruire un algoritmo polinomiale che decide qualunque problema in $NP$**
Vediamo come avviene:

*Supponiamo* di avere un algoritmo deterministico polinomiale che decide $SAT$ e lo chiamo $T_{SAT}$, allora
$\forall y \in \{0,1\}^{*}, dtime(T_{SAT},y)\leq|y|^{k}$ 
Un problema decisionale $\Gamma$ e dimostro che $\Gamma \in NP$, quindi progetto una macchina non deterministica $NT_{\Gamma}$ che lo decide in tempo polinomiale.
*Allora*, considero il seguente algoritmo: con input $x \in \{0,1\}^{*}$
1. Costruisce $E(x)$ (COme in Cook-Levin)
2. Esegue $T_{SAT}(E(x))$: se termina in $q_{A}$ allora accetta, altrimenti $q_{R}$
Basandoci sulla dimostrazione di Cook-Levin, questo algoritmo decide $L_{\Gamma}$, inoltre, richiede tempo polinomiale $|x|$, infatti:
1. Tempo polinomiale $|x|$
2. Tempo $|E(x)|^{k}$ dove $E(x)$ ha lunghezza polinomiale in $|x|$
*Allora*, abbiamo costruito un algoritmo deterministico polinomiale che decide $\Gamma$ **Nell'ipotesi di avere un algoritmo deterministico polinomiale che decide $SAT$**

# Il Teorema e la congettura
Quindi se si dimostrasse che $P=NP$ e si trovasse un algoritmo deterministico polinomiale che decide $SAT$, allora il Teorema di Cook-Levin ci permetterebbe di costruire un algoritmo deterministico polinomiale per decidere qualunque problema in $NP$

*Ma*, se invece si dimostrasse il contrario? Ovvero che $P \neq NP$ 
In questo caso sapremmo che $SAT \notin P$ ed ogni volta che riuscissimo a dimostrare che un problema è $NP-completo$, sapremmo che quel problema $\notin P$, quindi i problemi $NP-completi$ sono i problemi sepratori fra $P$ e $NP$ nell'ipotesi $P \neq NP$ 

# Da problema a problema
Per fortuna abbiamo uno strumento che ci aiuta ogni volta nelle dimostrazioni, anche perchè se sono come quella di Cook-Levin sarebbe troppo lungo.

Dati 2 problemi $\Gamma$ e $\Lambda$ quando è che $\Gamma \preceq \Lambda$ ?
Facile, quando $L_{\Gamma} \preceq L_{\Lambda}$, dove $L_{\Gamma}$ e $L_{\Lambda}$ sono i linguaggi associati alle codifiche ragionevoli delle istanze *si* dei due problemi.
Allora $\Gamma \preceq \Lambda$ se $\exists f: \Im_{\Gamma} \rightarrow \Im_{\Lambda}$ tale che
- $f \in FP$
- $x$ è un'istanza *si* di $\Gamma$ $\iff$ $f(x)$ è un'istanza *si* di $\Lambda$ 
(Per semplicità, $x\in \Gamma$ "$x$ è un'istanza si di $\Lambda$")

**Teorema 9.3:** Sia $\Gamma$ un problema in $NP$. Se esiste un problema $NP-completo$ riducibile a $\Gamma$ allora $\Gamma$ è $NP-completo$ 

Sia $\Lambda$ un problema $NP-completo$ tale che $\Lambda \preceq \Gamma$.
Poichè $\Lambda \preceq \Gamma$, esiste $f:\Im_{\Lambda} \rightarrow \Im_{\Gamma}$ tale che $f \in FP$ e $\forall y \in \Im_{\Lambda}, y \in \Lambda \iff f(y) \in \Gamma$ 
Poichè $\Lambda è NP-completo$, $\forall$ problema $\Delta \in NP$ si ha che $\Delta \preceq \Lambda$:
- $\exists g: \Im_{\Delta} \rightarrow \Im_{\Lambda}$ tale che $g\in FP$ e,
- $\forall x\in \Im_{\Lambda}, x\in \Delta \iff g(x) \in \Lambda$ 
La composizione delle due funzioni $g,f$ è una riduzione polinomiale da $\Delta$ a $\Gamma$ 
- Sia $x \in \Im_{\Delta}$: allora $x\in \Delta \iff g(x) \in \Lambda$ e inoltre $g(x) \in \Lambda \iff f(g(x)) \in \Gamma$
Allora, chiamando $h$ la composizione delle due funzioni, dimostro che $h$ è una riduzione da $\Delta$ a $\Gamma$.
*Ma*, quanto costa calcolare $h$?
$g \in FP$, allora esistono un trasduttore $T_g$ e una costante $k \in \mathbb{N}$ tali che $\forall x \in \Im_{\Delta}, T_{g}(x)$ calcola $g(x)$ e $dtime(T_{g}, x) \leq |x|^{k}$ 
	Dato che $T_{g}(x)$ deve anche scrivere il risultato $g(x)$ sul nastro di output, allora $|g(x)| \leq |x|^{k}$ 
$f\in FP$, allora esistono un trasduttore $T_{f}$ e una costante $c \in \mathbb{N}$ tali che, $\forall y \in \Im_{\Lambda}$, $T_{f}(y)$ calcola $f(y)$ e $dtime(T_{f}, y) \leq |y|^{c}$ 

Definiamo il trasduttore $T_{h}$ a tre nastri, che calcola $h$: con $x \in \Im_{\Delta}$ scritto sul primo nastro $T_{h}$
1. Esegue la computazione $T_{g}(x)$ scrivendo il suo output $y = g(x)$ sul secondo nastro
2. Esegue la computazione $T_{f}(y)$ scrivendo il suo output $f(y)$ sul nastro di output
$\forall x\in \Im_{\Delta}$ $dtime(T_{h}, x) \leq |x|^{k} + |g(x)|^{c} \leq |x|^{k} + |x|^{kc} \leq 2|x|^{kc}$
e questo dimostra che $h\in FP$
*Quindi*, abbiamo dimostrato che $\Delta \preceq \Gamma$ poichè $\Delta$ è un qualunque problema in $NP$, questo ci prova che ogni problema in $NP$ è riducibile polinomialmente a $\Gamma$
Dall'appartenenza di $\Gamma$ a $NP$ segue che $\Gamma$ è $Np-completo$ <SLIDE 10>
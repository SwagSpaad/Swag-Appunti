*******
Le teorie della complessità e della calcolabilità sono fondate sul concetto di **appartenza di una parola ad un insieme di parole**, un concetto:
- semplice
- elegante
- **formale**
- **rigoroso**

Ma nella vita reale ti capita mai di domandarti "ma questa parola apparterrà a questo insieme?", ossia  capita di trover trovare soluzioni ad istanze di problemi. Allora queste teorie sarebbe bello trasferirle nel mondo dei problemi.
Ma "**trovare la soluzione ad una istanza di un problema**" è più arbitrario rispetto al concetto di appartenenza di una parola ad un insieme di parole. Cerchiamo di rendere il concetto di trovare la soluzione di un problema più formale.

# Tipi di problemi
Come possiamo schematizzare un problema? Allora la struttura di un problema, qualunque esso sia è la seguente: 
abbiamo un insieme di oggetti conosciuti - **i dati** - all'interno di un secondo insieme di oggetti - **le soluzioni possibili** - e dobbiamo cercare gli oggetti che soddifano certi vincoli, ovvero **le soluzioni** e sulla base di questa ricerca dobbiamo fornire una risposta. 

>**Esempio problema generico**
>Dato $n\in\mathbb{N}\dots$ \[domanda sui divisiori di $n$\].
>Dobbiamo descrivere le istanze del problema e facciamolo definendo l'insieme delle istanze $\Im$. Un elemento di $\Im$ corrisponde ad un istanza di un problema. Nell'esempio $\Im=\mathbb{N}$. 
>L'insieme delle soluzioni possibili descrive le soluzioni possibili per un istanza $x$ del problema, definendo $S(x)$, ovvero tutti gli oggetti che dobbiamo testare per verificare se soddisfano i vincoli del problema. In questo caso $S(x)=\{y\in\mathbb{N}: y\le x\}$.
>Dobbiamo ora cercare gli oggetti che soddisfano i vincoli del problema, quindi tutti gli oggetti, all'interno delle soluzioni possibili $S(x)$, che soddisfano le richieste del problema. Descriviamo tutte le soluzioni possibili associate ad un'istanza $x$ che soddisfano i vincoli del problema definiendo l'insieme $\eta(S(x))$ di **soluzioni effettive** per l'istanza $x$. L'insieme $\eta(S(x))$ contiene gli oggetti che sono soluzioni possibili per $x$ e che soddisfano i vincoli del problema, quindi $\eta(S(x))=\{y\in S(x):y\text{ è un divisore di }x\}$. 
>Sulla base degli oggetti trovati forniamo una risposta, che definiamo con una funzione $\rho$ che associa all'insieme delle soluzioni effettive per l'istanza $x$ una risposta scelta nell'insieme $R$ delle risposte.

>**Esempio 1**
>Dato $n\in\mathbb{N}$, **elencare tutti i divisori di $n$**.
>In questo caso $R=2^{\mathbb{N}}$, ossia la risposta ad un'istanza del problema è un sottoinsieme di $\mathbb{N}$ e per ogni istanza $n$ del problema $\rho(\eta(S(n)))=\eta(S(n))$.

^abd267

>**Esempio 2**
>Dato $n\in\mathbb{N}$, verificare se $n$ è primo.
>In questo caso $R=\{\text{vero, falso}\}$ e per ogni istanza $n$ del problema $\rho(\eta(S(n)))=\Big[\eta(S(n))=\{1, n\}\Big]$ 

^9958b5

>**Esempio 3**
>Dato $n\in\mathbb{N}$, **calcolare un divisore $d$ non banale di $n$** (ossia $d>1\land d<n$).
>In questo caso $R=\mathbb{N}$, e per ogni istanza $n$ del problema, $\rho(\eta(S(n)))$ è un qualunque elemento di $\eta(S(n))$ diverso da $1$ e da $n$. 
>**Attenzione**: il numero $d$ potrebbe non esistere! 

^ea4801

>**Esempio 4**
>Dato $n\in\mathbb{N}$, calcolare **il più grande** divisore $d$ non banale di $n$ (ossia $d>1\land d<n$).
>In questo caso $R=\mathbb{N}$, e per ogni istanza $n$ del problema, $\rho(\eta(S(n)))$ è il più grande elemento di $\eta(S(n))$ diverso da $1$ e da $n$. 
>**Attenzione**: il numero $d$ potrebbe non esistere! 

^b6eb6a

Tutti questi problemi possono essere classificati in base al tipo di problema, infatti: 
- **[[#^abd267|Esempio 1]]** è un *problema di enumerazione*, in quanto ci viene richiesto di elencare tutte le soluzioni effettive.
- **[[#^9958b5|Esempio 2]]** è un *problema di decisione (o decisionale)*, in quanto ci viene richiesto di decidere se l'istanza possiede una certa proprietà.
- **[[#^ea4801|Esempio 3]]** è un *problema di ricerca*, in quanto viene richiesto di trovare (e mostrare) una qualunque soluzione effettiva.
- **[[#^b6eb6a|Esempio 4]]** è un *problema di ottimizzazione*, in quanto alle soluzioni effettive è associata una misura e viene richiesto di trovare una soluzione effettiva di misura massima, oppure minima.

I due diversi tipi di macchine di Turing che abbiamo visto risolvono i diversi tipi di problemi: 
- le macchine di tipo **trasduttore** risolvono i problemi di ricerca, enumerazione e ottimizzazione
- le macchine di tipo **riconoscitore** risolvono i problemi di decisione
La teoria della complessità, vista nelle lezioni precedenti, si occupa per lo più di *decidere l'appartenenza di parole ad insiemi di parole* utilizzando riconoscitori. 
D'ora in avanti ci occuperemo solo di *problemi decisionali*, per estendere quanto studiato nelle lezioni precedenti. 

# Problemi decisionali
Un problema è una quintupla $\langle \Im, S,\eta,\rho, R\rangle$ dove:
- $\eta$ è il sottoinsieme di $S$ che specifica quali fra le *soluzioni possibili*, sono le **soluzioni effettive** per una data istanza $x\in\Im$ 
- $\rho$ è la funzione che associa all'insieme delle soluzioni effettive $\eta(S(x))$, una risposta all'istanza $x$ del problema.
Nel caso dei problemi decisionali $R=\{\text{vero, falso}\}$, questo significa che **$\rho$ è un predicato**, ovvero una funzione logica il cui valore di verità dipende da qualche incognita. Possiamo quindi riassumere $\eta,\rho,R$ in un unico predicato $\pi$, ovvero $\pi(x,S(x))=\text{vero}$ **se e solo se l'insieme delle soluzioni possibili per $x$ soddisfa i vincoli del problema**. 
Quindi un *problema decisionale* è descritto da una tripla $\langle\Im, S,\pi\rangle$.

>**Esempio 1**
>Dato un grafo non orientato $G$, una coppia di nodi $s$ e $t$ e un intero $k$, decidere se esiste in $G$ un percorso da $s$ a $t$ di lunghezza $k$.
>- $\Im=\{\langle G,s,t,k \rangle:G\text{ è un grafo non orientato}\land s,t\text{ sono due nodi di }G\land k\in\mathbb{N}\}$ 
>- $S(G,s,t,k)=\{\langle u_{0},\dots,u_{k}\rangle:\text{per }i=0,\dots,k,\:u_{i}\text{ è un nodo del grafo}\}$ 
>- $\pi(G,s,t,k,S(G,s,t,k))=\exists\langle u_{0},\dots,u_{k}\rangle\in S(G,s,t,k):s=u_{0}\land t=u_{k}\land \forall i=0,\dots,k,[(u_{i},u_{i+1})\text{ è un arco del grafo}]$ 

>**Esempio 2**
>Dato un insieme $X$ di variabili booleane ed un predicato $f$, definito sulle variabili $X$ e contenente i soli operatori $\land,\lor,\neg$, decidere se esiste una assegnazione $a$ di valori in $\{\text{vero, falso}\}$ alle variabili $X$ tali che $f(a(X))=\text{vero}$
>- $\Im=\{\langle X,f\rangle:X\text{ è un insieme di variabili booleane}\land f\text{ è un predicato su }X\}$
>- $S(X,f)=\Big\{a:X\to\{\text{vero, falso}\}\Big\}$ ($S$ è l'insieme delle assegnazioni di verità alle variabili in $X$)
>- $\pi(X,f,S(X,f))=\exists a\in S(X,f):f(a(X))=\text{vero}$ 

^9c0a67

Formalizzato il concetto di problema decisionale, siamo quasi pronti ad estendere quanto studiato sulla complessità dei linguaggi alla complessità dei problemi decisionali, e visto che la complessità dei linguaggi è studiata utilizzando la macchina di Turing, utilizzeremo quest'ultima anche per studiare la complessità dei problemi decisionali.
Ma per utilizzare una macchina di Turing per *decidere* un problema decisionale abbiamo bisogno di trasformare le istanze di quel problema in parole e quindi occorre **codificare** le istanze di un problema decisionale.

## Codifica
In questo paragrafo cerchiamo di capire il concetto di codifica di un problema decisionale. Iniziamo con l'[[#^9c0a67|esempio 2]], che è un problema di **soddisfacibilità** (in breve **SAT**). Consideriamo un caso particolare di questo problema, il **3SAT**.

Una funzione booleana $f$ si dice in *forma normale congiuntiva* se $f$ è  la congiunzione di un certo numero $m\in\mathbb{N}$ di clausole, ossia, $f=c_{1}\land c_{2}\land\dots\land c_{m}$ dove ogni clausola $c_{j}$ è la disgiunzione di variabili di $X$. Una funzione in forma normale congiuntiva è la seguente $$f=\underbrace{(x_{1}\lor\lnot x_{2}\lor\lnot x_{3})}_{c_{1}} \land\underbrace{(\lnot x_{1}\lor\lnot x_{2}\lor\lnot x_{3})}_{c_{2}}$$Nel problema **3SAT** le istanze sono funzioni booleane le cui clausole sono costituite da esattamente 3 variabili. 
Mostriamo ora due diverse codifiche per $\Im_{\text{3SAT}}$.

## Prima codifica per il 3SAT
Chiamiamo questa codifica $\chi_{1}$. Rappresentiamo ciascuna variabile di $X=\{x_{1},x_{2},\dots,x_{n}\}$ con $n=|X|$ bit. Ricordiamo che la variabile $x_{i}$ è codificata dalla parola di $n$ caratteri il cui unico $1$ è quello in posizione $i$. Codifichiamo poi ogni letterale $x_{i}$ in una clausola $c_{j}$, con la sua codifica, preceduta da uno $0$ se il letterale è non negato, mentre è preceduta da un $1$ se il letterale è negato. Gli $\lor$ in una clausola sono codificati col numero $2$, mentre gli $\land$ sono rappresentati dal numero $3$. Premettiamo alla codifica di $f$ tanti $4$ quanti gli elementi di $X$. 

>**Esempio**
>Se $X=\{x_{1},x_{2},x_{3}\}$ e $f=c_{1}\land c_{2}$ con $c_{1}=x_{1}\lor x_{2}\lor x_{3}$ e $c_{2}=x_{1}\lor \lnot x_{2}\lor\lnot x_{3}$ rappresentiamo $f$ nel seguente modo $$f=444\:\underbrace{0100}_{x_{1}}\:2\:0010\:\underbrace{2}_{\lor}\:0001\:\underbrace{3}_{\land}\ 0100\ 2\ \underbrace{1010}_{\lnot x_{2}}\ 2\ 1001$$

## Seconda codifica per il 3SAT
Chiamiamo questa codifica $\chi_{2}$. Codifichiamo $f$ in forma esplicita. Qualunque funzione è descritta completamente descrivendo i valori che assume in **tutti** i punti del suo insieme di esistenza. Naturalmente, se una funzione è definita su $\mathbb{N}$ non possiamo descrivere il valore che essa assume per ogni $n\in\mathbb{N}$, perché i numeri naturali sono infiniti. 
La nostra $f$ dell'istanza $\langle X,f\rangle$ di **3SAT** è definita su $\{\text{vero, falso}\}^{|X|}$ e poiché $X$ è un insieme finito, l'insieme di esistenza di $f$ è finito. Possiamo quindi codificare $f$ in forma esplicita mediante la sua **tavola di verità**. 

>**Esempio**
>Se $X=\{x_{1},x_{2},x_{3}\}$ e $f=c_{1}\land c_{2}$ con $c_{1}=x_{1}\lor x_{2}\lor x_{3}$ e $c_{2}=x_{1}\lor \lnot x_{2}\lor\lnot x_{3}$ rappresentiamo $f$ nel seguente modo 
>![[FI/MOD II/img/img14.png|center|500]]  
>Codificando "vero" con $1$ e "falso" con 0 e scrivendo le righe della tabella una di seguito l'altra separate da $2$, quindi la tabella è codificata nel seguente modo $$1111\ 2\ 1101\ 2\ 1011\ 2\ 1001\ 2\ 0110\ 2\ 0101\ 2\ 0011\ 2\ 0000$$

Consideriamo il seguente algoritmo che verifica, dato $\langle f,X\rangle\in I_\text{3SAT}$ se $f$ è *soddisfacibile*, ossia, se esiste un assegnazione $a$ di valori in $\text{\{vero, falso\}}$ alle variabili di $X$ tali che $f(a(X))=\text{vero}$: 
1. calcola $n=|X|$ 
2. per ogni assegnazione di verità $a$ alle variabili di $X$, verifica se $f(a(X))=\text{vero}$ e in tal caso termina nello stato di accettazione $q_A$ 
3. se non ha mai terminato in $q_A$ nel passo 2, termina nello stato di rigetto $q_{R}$
Vediamo ora quest'algoritmo in esecuzione su un istanza di **3SAT** utilizzando entrambe le codifiche.

Se $\langle X,f\rangle$ è codificata secondo la codifica $\chi_{1}$ utilizziamo una mdT $T_1$ a due nastri e che opera in due fasi: 
- all'inizio della computazione $\chi_{1}(X,f)$ è scritta sul primo nastro, mentre il secondo nastro è vuoto
- Fase 1: utilizzando i $4$ iniziali della codifica di $\langle X,f \rangle$, scrive sul secondo nastro tutte le parole binarie di lunghezza $|X|$, separate l'una dall'altra con un $5$: ciascuna parola binaria corrisponde ad una assegnazione di verità agli elementi di $X$, ad esempio, se $|X|=3$, $010$ corrisponde a $a(x_{1})=\text{falso},a(x_{2})=\text{vero},a(x_{3})=\text{falso}$ 
- Fase 2: per ogni assegnazione di verità $a$ scritta sul secondo nastro, utilizzando la codifica di $f$ scritta sul primo nastro, verifica se $a$ soddisfa $f$. Se ciò accade, accetta e termina. 
- Se la fase 2 è terminata senza accettare, allora rigetta.
Quanto vale $\text{dtime}(T_{1},\chi_{1}(X,f))$? 
- Fase 1: se $n=|X|$, la fase 1 richiede almeno $2^n$ passi (lo stesso numero di assegnazioni possibili)
- $|\chi_{1}(X,f)|<n+[3(n+1)+3](2n)^3<n^4+7n(8n^3)<57n^4$ 
e quindi $$\text{dtime}(T_1,\chi_{1}(X,f))>2^{n}>2^{\frac{\sqrt[4]{\chi_{1}(X,f)}}{57}}$$ 
Se $\langle X,f\rangle$ è codificata secondo la codifica $\chi_{2}$, ad esempio $$1111\ 2\ 1101\ 2\ 1011\ 2\ 1001\ 2\ 0110\ 2\ 0101\ 2\ 0011\ 2\ 0000$$utilizziamo una mdT $T_2$ ad un solo nastro. All'inizio della computazione $\chi_{2}(X,f)$ è scritta sul nastro. $T_{2}$ scandisce l'input: poiché il carattere a sinistra di un $2$ è il valore assunto da $f$ quando alle sue variabili sono assegnati i valori a sinistra di quel carattere, se trova un $1$ a sinistra di un $2$, allora accetta e termina. Poiché $\chi_{2}(X,f)$ contiene in sé tutte le possibili assegnazioni di verità alle variabili in $f$, se $T_{2}$ ha terminato la scansione dell'input senza accettare, allora rigetta. 
Quanto vale $\text{dtime}(T_{2},\chi_{2}(X,f))$? 
Questa volta è molto facile, perché $T_{2}$ deve scandire l'input una sola volta, quindi $\text{dtime}(T_{2},\chi_{2}(X,f))=|\chi_{2}(X,f)|$ 

Riassumendo
- se $\langle X,f\rangle$ è codificata secondo $\chi_{1}$ implementiamo l'algoritmo mediante una macchina $T_{1}$ tale che $\text{dtime}(T_1,\chi_{1}(X,f))>2^{\beta(n)}$ dove $\beta(n)=\sqrt[4]{|\chi_{1}(X,f)|}$, quindi l'algoritmo che decide **3SAT** impiega *tempo esponenziale* nella lunghezza di $\chi_{1}$.
- se $\langle X,f\rangle$ è codificata secondo $\chi_{2}$, implementiamo l'algoritmo mediante una macchina $T_{2}$ tale che $\text{dtime}(T_{2},\chi_{2}(X,f))=|\chi_{2}(X,f)|$, ossia lo stesso algoritmo che decide **3SAT** impiega *tempo lineare* nella lunghezza di $\chi_{2}$

Ora, ricordando che un linguaggio è nella classe $\textbf{P}$ se esiste una mdT deterministica che lo decide in tempo polinomiale, possiamo concludere che il linguaggio associato a **3SAT** appartiene a $\mathbf{P}$?

**Oss.**
$T_{1}$ e $T_{2}$ implementano lo stesso algoritmo, ma operano su due codifiche diverse

Dunque la caratteristica *"essere un algoritmo polinomiale"* dipende dal modo in cui è codificato l'input? Diciamo sì e no. 
Poiché la complessità di un algoritmo è espressa in termini di lunghezza dell'input, e quindi da come viene codificato, e siccome noi la codifica possiamo renderla lunga quanto vogliamo, aggiungendo ad esempio un sacco di caratteri senza senso, possiamo ad esempio prendere la codifica $\chi_{1}$ e aggiungere alla fine $2^{|X|}$ caratteri $5$ ottenendo $$\chi_{3}(X,f)=444\ 0100\ 2\ 0010\ 2\ 0001\ 3\ 0100\ 2\ 1010\ 2\ 1001 \ 55555555$$in questo modo $|\chi_{3}(X,f)|>2^{n}$ e da questa codifica deriveremmo una macchina $T_{3}$ per **3SAT** tale che  $\text{dtime}(T_{3},\chi_{3}(X,f))\in O(|\chi_{3}(X,f)|)$, ma questa codifica è **irragionevolmente** lunga.

Ripensiamo alle codifiche $\chi_{1}$ e $\chi_{2}$: 
- la codifica di $\chi_{1}$ rappresenta di $\langle X,f\rangle$ solo l'informazione *strettamente necessaria*, ossia la **struttura di f**
- la codifica di $\chi_{2}$ rappresenta, invece, $\langle X,f\rangle$ in forma estesa, infatti $\chi_{2}$ contiene la soluzione del problema così che, per trovare la soluzione è sufficiente leggere la codifica, ma questo significa che calcolare la codifica di $\chi_{2}$ ha richiesto molto tempo, ossia, **il tempo impiegato dalla computazione $T_{1}(\chi_{1}(X,f))$ lo dobbiamo impiegare noi per calcolare $\chi_{2}(X,f)$ se vogliamo utilizzare questa codifica**. In effetti $\chi_{2}$ è *esponenzialmente* più lunga di $\chi_{1}$

Informalmente, **una codifica $\chi$ per un problema $\Gamma$ è irragionevole se esiste un'altra codifica $\chi^{'}$** tale che le parole in cui $\chi$ codifica le istanze di $\Gamma$ sono "più che polinomialmente" lunghe delle parole in cui $\chi^{'}$ codifica le istanze di $\Gamma$.

Questo significa che esiste una funzione più che polinomiale $f$ tale che, per qualche istanza $x$ di $\Gamma$ $|\chi(X)|\geq f(|\chi^{'}|)$, quindi $f:\mathbb{N}\to\mathbb{N}$ è più che polinomiale se, per ogni $k\in \mathbb{N}$, $f(n)\in \Omega(n^{k})$.
Informalmente, il rapporto fra $|\chi(X)|$ e $|\chi^{'}(X)|$ è più grande di qualsiasi polinomio.
Quello che accadeva tra $\chi_{1}$ e $\chi_{2}$ era proprio che $\chi_{2}$ è una **codifica irragionevole** di **3SAT**. 

Quindi, **una codifica $\chi$ per un problema $\Gamma$ è ragionevole se: 
- comunque si scelga un'altra codifica $\chi^{'}$ per $\Gamma$, esistono tre interi $k,h_{1}$ e $h_{2}$ tali che, per ogni istanza $x$ di $\Gamma$, $|\chi(x)|\leq h_{1}|\chi^{'}(x)|^{k}+h_{2}$.
Questo significa che, se $\chi$ è una codifica ragionevole per $\Gamma$, comunque scegliamo un'altra codifica $\chi^{'}$ per $\Gamma$, può succedere che le parole risultanti dalla codifica $\chi^{'}$ siano più corte delle parole risultanti dalla codifica $\chi$, ma esiste un polinomio $p$ tale che, qualunque sia l'istanza $x$ di $\Gamma$, $|\chi(x)|$ non è più grande di $p(|\chi^{'}(x)|)$.

Alla luce di quanto detto fino ad ora, dovrebbe essere chiaro che possiamo estendere ai problemi tutto quello studiato relativamente alla complessità di linguaggi, a patto di **utilizzare codifiche ragionevoli per codificare le istanze dei problemi**, perché quando si utilizzano codifiche irragionevoli non ha più senso parlare della complessità di un problema, perché potremmo aver trasferito nella complessità della codifica la complessità di risoluzione del problema, esattamente come fatto nel caso della codifica $\chi_{2}$ del problema **3SAT**. Per questo d'ora in poi, faremo riferimento sempre a codifiche ragionevoli.


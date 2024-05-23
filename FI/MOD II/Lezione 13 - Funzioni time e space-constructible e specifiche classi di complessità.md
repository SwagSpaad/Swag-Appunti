*********
Nelle lezioni precedenti abbiamo lasciato delle questioni in sospeso, come la questione della definizione delle classi di complessità non deterministiche, dove viene richiesta l'accettabilità di un linguaggio, pur sapendo che, quando utilizziamo la quantità massima di risorse utilizzabili, un linguaggio accettabile è anche decidibile, non conosciamo la quantità di risorse che occorrono per rigettare le parole che non appartengono al linguaggio. 
Inoltre sapiamo che, tutto ciò che può essere deciso da una macchina non deterministica può essere deciso anche da una macchina deterministica, ma un linguaggio $L\in\text{NTIME}[f(n)]$ non sappiamo ancora in quale classe di complessità deterministica collocarlo e neanche sappiamo se l'appartenenza ad $\text{NTIME}[f(n)]$ ci fornisca strumenti per affermare l'appartenenza a $\text{DTIME[altra funzione]}$. 

Non è molto piacevole ammettere che se un linguaggio $L$ è in $\text{NTIME}[f(n)]$, e quindi sappiamo che esiste una macchina di Turing non deterministica $NT$ che accetta le sue parole $x\in L$ eseguendo $O(f(|x|))$ istruzioni, ma non sappiamo quanto tempo ci occorre per capire che una parola non appartiene al linguaggio $L$, ovvero quando $x\not\in L$ non sappiamo quante istruzioni sono eseguite da ciascuna computazione deterministica di $NT$ che rigetta.

**Teorema 6.16**
Sia $f:\mathbb{N}\to\mathbb{N}$ una funzione *time-constructible*. Se $L\in\text{NTIME}[f(n)]$, allora $L$ è decidibile in tempo non deterministico in $O(f(n))$. 
Sia $f:\mathbb{N}\to\mathbb{N}$ una funzione *space-constructible*. Se $L\in\text{NTIME}[f(n)]$, allora $L$ è decidibile in spazio non deterministico in $O(f(n))$.

**Dim.**
Sia $L\subseteq \Sigma^{*}$ tale che $L\in\text{NTIME}[f(n)]$. Allora esiste una macchina di Turing non deterministica $NT$ che accetta $L$ e tale che, per ogni $x\in L$, $\text{ntime}(NT,x)\le c\cdot f(|x|)$ per qualche costante $c>0$. 
Poiché $f$ è time-constructible, anche $cf$ lo è, allora esiste una macchina di Turing di tipo trasduttore $T_{f}$ tale che, per ogni $n\in\mathbb{N}$, $T_{f}(1^{n})$ termina con il valore $cf(n)$ scritto sul nastro di output in unario dopo aver eseguito $O(cf(n))$ istruzioni. 
Costruiamo ora, a partire da $T_{f}$ e $NT$, la macchina $NT^{'}$ che decide $L$, quindi per ogni $x\in\Sigma^{*}$
- $NT^{'}(x)$ scrive $|x|$ in unario sul secondo nastro e invoca $T_{f}(|x|)$ e al termine della computazione troverà sul terzo nastro $cf(|x|)$ in unario.
- $NT^{'}(x)$ invoca $NT(x)$ e, per ogni quintupla eseguita non deterministicamente da $NT(x)$ nel seguente modo. Se il terzo nastro contiene un $1$ allora $NT^{'}$ lo cancella e se $NT(x)$ accetta allora anche $NT^{'}(x)$ accetta e se $NT(x)$ rigetta allora anche $NT^{'}(x)$ rigetta. Se il terzo nastro di $NT^{'}$ è vuoto e $NT(x)$ non ha ancora terminato, allora $NT^{'}(x)$ rigetta. 
Osserviamo che le computazioni di $NT^{'}$ terminano sempre, infatti se la simulazione di una computazione di $NT(x)$ dura più di $cf(|x|)$ passi, la interrompiamo. Poi $NT^{'}$ decide $L$, infatti: 
- se $x\in L$, allora $NT(x)$ accetta in al più $cf(|x|)$ passi e quindi $NT^{'}(x)$ accetta
- se $x\not\in L$ allora o $NT(x)$ rigetta in al più $cf(|x|)$ passi e quindi $NT^{'}(x)$ rigetta, oppure $NT(x)$ non termina entro $cf(|x|)$ passi e quindi $NT^{'}(x)$ interrompe la computazione e rigetta.
Quanto impegna però $NT^{'}$ a decidere se $x\in L$ oppure no?
- $O(cf(|x|))$ per calcolare $cf(x)$ - perché $cf$ è  time-constructible
- altri $cf(|x|)$ passi per simulare $cf(|x|)$ passi di $NT(x)$ ovvero $O(f(|x|))$ passi
Quindi possiamo concludere che $L$ è decidibile in tempo non deterministico $O(f(n))$. $\square$ 

Le uniche relazioni tra classi deterministiche e non deterministiche sono quelle basate sull'osservazione che una macchina deterministica è una particolare macchina non deterministica, ovvero $$\text{DTIME}[f(n)]\subseteq\text{NTIME}[f(n)]\:\:e\:\:\text{DSPACE}[f(n)]\subseteq\text{NSPACE}[f(n)]$$ Inoltre sappiamo che tutto ciò che è deciso da una macchina non deterministica può essere deciso anche da una macchina deterministica, ma un linguaggio che appartiene ad $\text{NTIME}[f(n)]$ non sappiamo in quale classe di complessità temporale deterministica collocarlo, perché non sappiamo se esiste una funzione $g(n)$ che cresce molto più velocemente di $f(n)$ tale che possiamo affermare che "se $L\in\text{NTIME}[f(n)]$ allora $L\in\text{DTIME}[g(n)]$", a meno che la funzione limite $f$ della classe non sia una funzione *time-constructible*.

**Teorema 6.17**
Per ogni funzione *time-constructible* $f:\mathbb{N}\to\mathbb{N}$ $$\text{NTIME}[f(n)]\subseteq\text{DTIME}[2^{O(f(n))}]$$
**Dim.**
Sia $L\subseteq\Sigma^{*}$ tale che $L\in\text{NTIME}[f(n)]$; allora esistono una macchina di Turing non deterministica *$NT$ che accetta $L$* e una costante $h$ tale che per ogni $x\in L$, $\text{ntime}(NT,x\leq hf(|x|))$. 
Poiché $f$ è time-constructible, esiste una macchina di Turing di tipo trasduttore $T_{f}$ che, con input la rappresentazione in unario di un numero intero $n$, calcola la rappresentazione in unario di $f(n)$ in tempo $O(f(n))$.
Inidchiamo con $k$ il grado di non determinismo di $NT$ e utilizziamo di nuovo la tecnica della simulazione per definire una macchina di Turing deterministica $T$che simuli il comportamento di $NT$: con input $x$, $T$ simula in successione *tutte* le computazioni deterministiche di $NT(x)$ di lunghezza $hf(|x|)$. 
La macchina $T$ con input $x$ opera come descritto di seguito:  ^4fad78
1. Simula la computazione $T_{f}(|x|)$: per ogni carattere di $x$ scrive sui $N_{2}$ un carattere $1$ e, in seguito, calcola $f(|x|)$ scrivendolo su $N_{3}$. Infine, concatena $h$ volte il contenuto del nastro $N_{3}$ ottenendo il valore $hf(|x|)$
2. Simula, una alla volta, tutte le computazioni deterministiche di $NT(x)$ di lunghezza $hf(|x|)$ utilizzando, per ciascuna computazione, la posizione della testina sul nastro $N_{3}$ come contatore, ovvero: 
	- simula al più $hf(|x|)$ passi della computazione più a sinistra di tutte nell'albero $NT(x)$, se la computazione accetta entro $hf(x)$ passi allora $T$ termina in $q_A$, altrimenti
	- simula al più $hf(|x|)$ passi della computazione immediatamente più a destra di quella appena simulata, se la computazione accetta entro $hf(x)$ passi allora $T$ termina in $q_A$, altrimenti
	- $\vdots$
	- simula al più $hf(|x|)$ passi della computazione più a destra di tutte nell'albero $NT(x)$, se la computazione accetta entro $hf(x)$ passi allora $T$ termina in $q_A$, altrimenti $T$ termina in $q_{R}$
Poiché, se $x\in L$, $\text{ntime}(NT,x)\leq hf(|x|)$, allora o in $hf(|x|)$ passi $NT(x)$ termina nello stato di accettazione oppure $x\not\in L$. Quindi, se dopo aver simulato tutte le computazioni deterministiche di $NT(x)$ di lunghezza $hf(|x|)$, $T$ non ha raggiunto lo stato di accettazione, allora può correttamente entrare nello stato di rigetto. Questo prova che $T$ decide $L$.
Ma quanto tempo impiega $T$ a decidere $L$?
Intanto la fase 1 della simulazione richiede $O(hf(|x|))$ passi, perché $f$ è time-constructible, per la fase 2, detto $k$ il grado di non determinismo di $NT$, il numero di computazioni deterministiche di $NT(x)$ di lunghezza $hf(|x|)$ è $k^{hf(|x|)}$ e ciascuna di esse viene simulata da $T$ in $O(hf(|x|))$ passi. Allora $$\text{dtime}(T,x)\in O(hf(|x|))+hf(|x|)k^{hf(|x|)}=O(hf(|x|)k^{hf(|x|)})\subseteq O(2^O(f(|x|)))$$
Infine, per il *teorema 6.3* (sulle dispense nel paragrafo 6.2), esiste una macchina $T_{1}$ ad un nastro tale che, per ogni input $x$, $o_{T_{1}}(x)=o_{T}(x)$ e $$\text{dtime}(T_{1},x)\leq\text{dtime}(T,x)^{c}\subseteq O(2^{O(f(|x|))})$$
Questo conclude la dimostrazione che $L\in\text{DTIME}[2^{O(f(|x|))}]$. $\square$

# Specifiche classi di complessità
Siamo pronti ad introdurre le più rilevanti classi di complessità, definite sulla base di funzioni *time/space-constructible*.
- $\textbf{P}=\bigcup_{k\in\mathbb{N}}\text{DTIME}[n^{k}]$: è la classe dei linguaggi decidibili in *tempo deterministico polinomiale*
- $\textbf{NP}=\bigcup_{k\in\mathbb{N}}\text{NTIME}[n^{k}]$: è la classe dei linguaggi accettabili in *tempo non deterministico polinomiale*
- $\textbf{PSPACE}=\bigcup_{k\in\mathbb{N}}\text{DSPACE}[n^{k}]$: è la classe dei linguaggi decidibili in *spazio deterministico polinomiale*
- $\textbf{NPSPACE}=\bigcup_{k\in\mathbb{N}}\text{NSPACE}[n^{k}]$: è la classe dei linguaggi accettabili in *spazio deterministico non polinomiale*
- $\textbf{EXPTIME}=\bigcup_{k\in\mathbb{N}}\text{DTIME}[2^{p(n,k)}]$: è la classe dei linguaggi decidibili in *tempo deterministico esponenziale* ove l'esponente che descrive la funzione limite è un polinomio in $n$ di grado $k$, indicato come $p(n,k)$
- $\textbf{NEXPTIME}=\bigcup_{k\in\mathbb{N}}\text{NTIME}[2^{p(n,k)}]$: è la classe dei linguaggi accettabili in *tempo non deterministico esponenziale* ove l'esponente che descrive la funzione limite è un polinomio in $n$ di grado $k$, indicato come $p(n,k)$
Infine, una classse di complessità di funzioni: la classe delle *funzioni (totali) calcolabili in tempo deterministico polinomiale* $$\begin{align*}
\textbf{FP}=\bigcup_{k\in\mathbb{N}}\Big\{f:\Sigma_{1}^{*}\to\Sigma_{2}^{*}:&\text{ esiste una macchina di Turing deterministica trasduttore }T\\
&\text{ che calcola }f \text{ e, per ogni }x\in\Sigma_{1}^{*},\text{dtime}(T,x)\in O(|x|^{k})\Big\} 
\end{align*}$$
**Corollario 6.2**
Valgono le seguenti relazioni di inclusione: 
- $\textbf{P}\subseteq\textbf{NP},\:\textbf{PSPACE}\subseteq\textbf{NPSPACE},\text{ e }\textbf{EXPTIME}\subseteq\textbf{NEXPTIME}$, conseguenza diretta del **[[Lezione 12 - Classi di complessità|teorema 6.8]]**: una macchina deterministica è una macchina non deterministica con grado di non determinismo $1$ 
- $\textbf{P}\subseteq\textbf{PSPACE}$ e $\textbf{NP}\subseteq\textbf{NPSPACE}$, conseguenza diretta del **[[Lezione 12 - Classi di complessità|teorema 6.9]]**: per ogni funzione totale e calcolabile $f$ $$\text{DTIME}[f(n)]\subseteq\text{DSPACE}[f(n)]\:e\:\text{NTIME}[f(n)]\subseteq\text{NSPACE}[f(n)]$$
- $\textbf{PSPACE}\subseteq\textbf{EXPTIME}$ e $\textbf{NPSPACE}\subseteq\textbf{NEXPTIME}$, sono conseguenza diretta del **[[Lezione 12 - Classi di complessità|teorema 6.10]]**: per ogni funzione totale e calcolabile $f$ $$\text{DSPACE}[f(n)]\subseteq\text{DTIME}[2^{O(1)f(n)}]\:\:e\:\:\text{NSPACE}[f(n)]\subseteq\text{NTIME}[2^{O(1)f(n)}]$$
- $\textbf{NP}\subseteq\textbf{EXPTIME}$ conseguenza diretta del **[[#^4fad78|teorema 6.17]]**: per ogni funzione time-constructible $f$ $$\text{NTIME}[f(n)]\subseteq\text{DTIME}[2^{O(f(n))}]$$ e i polinomi sono funzioni time-constructible.
Tutte le relazioni fra classi di complessità dimostrate fino ad ora sono **inclusioni improprie**, ovvero per ciascuna di quelle relazioni non siamo in grado di dimostrare né l'inclusione propria né la coincidenza delle due classi che la costituiscono, ad esmepio sappiamo che tutti i linguaggi in $\textbf{PSPACE}$ sono anche in $\textbf{EXPTIME}$ e tutti i linguaggi che sono in $\textbf{P}$ sono anche in $\textbf{NP}$, ma non sappiamo rispondere alla domanda "tutti i linguaggi in $\textbf{EXPTIME}$ sono anche in $\textbf{PSPACE}$?" (e di conseguenza $\textbf{PSPACE}=\textbf{EXPTIME}$).
Si tratta quindi di relazioni deboli, e sarebbe tremendo se si dimostrasse che tutte quelle inclusioni improprie fossero, invece, delle uguaglianze, perché non saremmo in grado di classificare i problemi "facili" e "difficili".

Uno strumento che dimostra l'inclusione stretta fra classi di complessità è il [[Lezione 12 - Classi di complessità#^598cac|teorema di gerarchia temporale]] e come sua conseguenza vale il seguente teorema

**Teorema 6.18**
$$\textbf{P}\subset\textbf{EXPTIME}$$

**Dim.** sulle dispense per curiosità

Esiste anche un teorema che va nella direzione opposta, ovvero che dimostra l'uguaglianza di due classi, una deterministica e una non deterministica

**Teorema 6.19**
$$\textbf{PSPACE}=\textbf{NPSPACE}$$

**Dim.** sulle dispense per curiosità

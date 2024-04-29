*******
Come già affermato nella [[Lezione 13 - Funzioni time e space-constructible e specifiche classi di complessità|lezione 13]], le relazioni viste fra le classi di complessità sono *inclusioni improprie*, ad eccezione per $\textbf{P}\subset\textbf{EXPTIME}$ e $\textbf{PSPACE}=\textbf{NPSPACE}$. A parte queste due, per le altre relazioni non siamo in grado di dimostrare né l'inclusione propria, né la coincidenza. 
Pur riuscendo a dimostrare che una certa classe di complessità $\mathcal{C}_{1}$ è contenuta propriamente in un'altra classe di complessità $\mathcal{C}_{2}$, ovvero $\mathcal{C}_{1}\subset\mathcal{C}_{2}$, anche in questo caso, se dimostriamo che un linguaggio $L$ appartiene a  $\mathcal{C}_{2}$, come facciamo a sapere se quel linguaggio è anche in $\mathcal{C}_{1}$, oppure se, invece, è un linguaggio separatore fra $\mathcal{C}_{1}$ e $\mathcal{C}_{2}$, ossia $L\in\mathcal{C}_{2}-\mathcal{C}_{1}$?
Sarebbe utile se avessimo, quindi, uno strumento che permettesse di individuare i **linguaggi separatori** fra due classi di complessità $\mathcal{C}_{1}$ e $\mathcal{C}_{2}$.

# $\pi$-riduzioni 
Utilizzando una nozione collegata al concetto di [[Lezione 10 - Riduzioni|riducibilità funzionale]], è possibile individuare i linguaggi *candidati* ad essere separatori fra due classi, e la nozione è quella di linguaggio **completo** per una data classe.

Alla definizione di riduzione, aggiungiamo una piccola richiesta. 
Sia $\pi$ un *predicato* definito sull'insieme delle funzioni totali e calcolabili, ovvero una proprietà che deve essere posseduta da una funzione, ad esempio:
 - per ogni $x\in\Sigma^{*},\:|f(x)| = |x|$ 
 - per ogni $x\in\Sigma^{*}$, $f$ è calcolabile in tempo polinomiale in $|x|$

**Def.** $\pi$-riduzione
Dati due linguaggi $L_{1}\subseteq \Sigma_{1}^{*}$ e $L_{2}\subseteq \Sigma_{2}^{*}$, diciamo che **$L_{1}$ è $\pi$-riducibile ad $L_{2}$** e scriviamo $L_{1}\preceq_{\pi} L_{2}$ se esiste una funzione $f:\Sigma_{1}^{*}\to\Sigma_{2}^{*}$ tale che: 
- $f$ è totale e calcolabile, ossia 
	- è definita per ogni $x\in\Sigma_{1}^{*}$ 
	- esiste una macchina di Turing $T_{f}$ di tipo trasduttore tale che, per ogni parola $x\in\Sigma_{1}^{*}$, la computazione $T_{f}(x)$ termina con la parola $f(x)\in\Sigma_{2}^{*}$ scritta sul nastro di output
- per ogni $x\in\Sigma_{1}^{*}$ vale che $x\in L_{1}\iff f(x)\in L_{2}$ 
- $f$ soddisfa $\pi$

Lo strumento che potrebbe permettere di individuare i linguaggi separatori fra due classi di complessità è basato sui seguenti due concetti che si riferiscono alle $\pi$-riduzioni: 
- **chiusura** di una classe rispetto a una $\pi$-riduzione
- **completezza** di un linguaggio per una classe rispetto a una $\pi$-riduzione

**Def.** chiusura 
Una classe di complessità $\mathcal{C}$ è *chiusa* rispetto ad una generica $\pi$-riduzione se, per ogni coppia di linguaggi $L_{1}$ e $L_{2}$ tali che $L_{1}\preceq_{\pi}L_{2}$ e $L_{2}\in\mathcal{C}$, si ha che $L_{1}\in\mathcal{C}$.

La chiusura di una classe $\mathcal{C}$ rispetto ad una $\pi$-riduzione può essere utilizzata per dimostrare l'appartenenza di un linguaggio $L$ a $\mathcal{C}$: 
- segue direttamente dalla definizione, che, se sappiamo che una classe di complessità $\mathcal{C}$ è chiusa rispetto ad una $\pi$-riduzione e che un certo linguaggio $L_{0}$ appartiene a $\mathcal{C}$, allora, se dimostriamo che $L\preceq_{\pi}L_{0}$, possiamo dedurre che anche $L$ appartiene a $\mathcal{C}$.

**Def.** completezza
Sia $\mathcal{C}$ una classe di complessità di linguaggi e sia $\preceq_\pi$ una generica $\pi$-riduzione. Un linguaggio $L\subseteq\Sigma^{*}$ è $\mathcal{C}$-completo rispetto alla $\pi$-riducibilità se:
- $L\in\mathcal{C}$ 
- per ogni altro $L^{'}\in\mathcal{C}$, vale che $L^{'}\preceq_{\pi}L$ 

Le ultime due definizioni sono gli strumenti che ci permettono di arrivare al concetto di linguaggio "più difficile" in una classe.

>**Esempio**
>Abbiamo due classi di complessità $\mathcal{C}_1$ e $\mathcal{C}_2$ tali che $\mathcal{C}_{1}\subseteq\mathcal{C}_2$ e sappiamo che $\mathcal{C}_{1}$ è chiusa rispetto ad una qualche $\pi$-riduzione, allora, per ogni coppia di linguaggi $L_{1}$ e $L_{2}$, tali che $L_{1}\preceq_{\pi}L_{2}$ e $L_{2}\in\mathcal{C}_{1}$, si ha che $L_{1}\in\mathcal{C}_{1}$. 
>Se per caso trovassimo un linguaggio $L$ che è $\mathcal{C}_{2}$-completo rispetto a $\preceq_{\pi}$, ossia $L\in\mathcal{C}_2$ e per ogni altro $L_{0}\in\mathcal{C}_{2}$, vale che $L_{0}\preceq_{\pi}L$ e se dimostriamo che $L\in\mathcal{C}_{1}$, abbiamo che per ogni altro $L_{0}\in\mathcal{C}_2$ vale che $L_{0}\preceq_{\pi}L$ e inoltre $L\in\mathcal{C}_1$. 
>Allora in virtù della chiusura di $\mathcal{C}_{1}$ rispetto alla $\pi$-riduzione, per ogni altro $L_{0}\in\mathcal{C}_2$ vale che $L_{0}\in\mathcal{C}_{1}$ e dunque, $\mathcal{C}_{1}=\mathcal{C}_{2}$.

Riassumendo, abbiamo due classi di complessità $\mathcal{C}_{1}$ e $\mathcal{C}_{2}$, tali che $\mathcal{C}_{1}\subseteq\mathcal{C}_{2}$ e sappiamo che $\mathcal{C}_{1}$ è chiusa rispetto ad una qualche $\pi$-riduzione. Se per caso trovassimo un linguaggio $L$ che è $\mathcal{C}_{2}$-completo rispetto a $\preceq_{\pi}$ allora: 
- da un ipotetico algoritmo che decide $L$ utilizzando una quantità di risorse pari a quella che definisce la classe $\mathcal{C}_{1}$ - cioé se dimostrassimo che $L\in\mathcal{C}_{1}$ - potremmo dedurre un algoritmo che decide qualunque problema in $\mathcal{C}_{2}$ utilizzando una quantità di risorse pari a quella che definisce la classe $\mathcal{C}_{1}$
Allora se riuscissimo a dimostrare che $L\in\mathcal{C}_{1}$ sapremmo automaticamente che tutti i linguaggi in $\mathcal{C}_{2}$ sono anche in $\mathcal{C}_{1}$ - ossia sapremmo che $\mathcal{C}_{1}=\mathcal{C}_{2}$.
Ma possiamo vederla anche in un altro modo: **se $\mathcal{C}_{1}\subseteq\mathcal{C}_{2}$ e $L$ è $\mathcal{C}_{2}$-completo** e se qualcuno riuscisse a dimostrare che $\mathcal{C}_{1}\neq\mathcal{C}_{2}$ allora sapremmo automaticamente che $L\not\in\mathcal{C}_{1}$. 
$L$ sarebbe un linguaggio "più difficile" fra tutti i linguaggi in $\mathcal{C}_{2}$

**Teorema 6.20**
Siano $\mathcal{C}$ e $\mathcal{C}_{0}$ due classi di complessità tali che $\mathcal{C}_{0}\subseteq\mathcal{C}$. 
Se $\mathcal{C}_{0}$ è chiusa rispetto una $\pi$-riduzione allora, per ogni linguaggio $L$ che sia $\mathcal{C}$-completo rispetto a $\preceq_{\pi}$, $L\in\mathcal{C}_{0}$ se e solo se $\mathcal{C}=\mathcal{C}_{0}$.

**Dim.**
- Banalmente, se $\mathcal{C}=\mathcal{C}_{0}$, poiché $L$ è $\mathcal{C}$-completo e, dunque $L\in\mathcal{C}$, allora $L\in\mathcal{C}_{0}$
- viceversa, supponiamo che $L\in\mathcal{C}_{0}$. Poiché $L$ è $\mathcal{C}$-completo rispetto a $\preceq_{\pi}$, allora, per ogni $L^{'}\in\mathcal{C}$, $L^{'}\preceq_{\pi}L$. Poiché $\mathcal{C}_{0}$ è chiusa rispetto a $\preceq_{\pi}$, allora, per ogni $L^{'}\in\mathcal{C}$, $L^{'}\in\mathcal{C}_{0}$ e quindi $\mathcal{C}=\mathcal{C}_{0}$ $\square$

Introduciamo una particolare $\pi$-riduzione il cui predicato $\pi$ specifica il costo computazionale del calcolo della funzione

**Def.** riducibilità polinomiale
Siano $L_{1}\subseteq\Sigma_{1}^{*}$ e $L_{2}\subseteq\Sigma_{2}^{*}$ due linguaggi; diciamo che $L_{1}$ è **polinomialmente riducibile** a $L_{2}$ e scriviamo $L_{1}\preceq_{p}L_{2}$, se esiste una funzione totale e calcolabile $f:\Sigma_{1}^{*}\to\Sigma_{2}^{*}$ tale che
- $f\in\textbf{FP}$ ($f$ è totale e calcolabile in tempo polinomiale), ossia
	- è definita per ogni parola $x\in\Sigma_{1}^{*}$
	- esiste una macchina di Turing di tipo trasduttore $T_{f}$ tale che, per ogni parola $x\in\Sigma_{1}^{*}$, la computazione $T_{f}(x)$ termina con la parola $f(x)\in\Sigma_{2}^{*}$ scritta sul nastro di output
	- esiste una costante $c$ tale che per ogni $x\in\Sigma_{1}^{*}$, $\text{dtime}(T_{f},x)\in O(|x|^{c})$
- per ogni  $x\in\Sigma_{1}^{*}$ vale che $\Big[x\in L_{1}\iff f(x)\in  L_{2}\Big]$ 
**Oss.**
Siccome d'ora in poi faremo sempre riferimento alla riducibilità polinomiale, per semplificare le nozioni, scriveremo solo $L_{1}\preceq L_2$ per indicare che $L_1$ è riducibile polinomialmente a $L_{2}$. 

>**Esempio**
>Abbiamo due linguaggi $L_{1}\subseteq\Sigma_{1}^{*}$ e $L_{2}\subseteq\Sigma_{2}^{*}$ e riusciamo a dimostrare che $L_{1}\preceq L_{2}$, cioé dimostriamo che esistono un traduttore $T_{r}$  e una costante $c$ tali che, per ogni $x\in\Sigma_{1}^{*}$ e inoltre, per ogni $x\in\Sigma_{1}^{*}$, $\text{dtime}(T_{r},x)\in O(|x|^{c})$.
>Supponiamo di sapere che $L_{2}\in\text{DTIME}[f(n)]$, cioé esiste un riconoscitore $T_{2}$ tale che, per ogni $y\in\Sigma_{2}^{*}$, $T_{2}$ accetta se e soltanto se $y\in L_{2}$ e, inoltre, per ogni $y\in \Sigma_{2}^{*}$, $\text{dtime}(T_{2},y)\in O(f|y|)$. 
>Allora possiamo costruire la seguente macchina $T_{1}$: con input $x\in\Sigma_{1}^{*}$, $T_{1}$ opera in due fasi utilizzando due nastri
>1. $T_{1}$ simula $T_{r}(x)$ scrivendo l'output $y$ sul secondo nastro
>2. $T_{1}$ simula $T_{2}(y)$ sul secondo nastro; se $T_{2}(y)$ accetta allora anche $T_{1}$ accetta, se $T_{2}(y)$ rigetta, allora anche $T_{1}$ rigetta
>$T_1$ quindi decide $L_1$, perché $T_{2}(y)$ accetta se e solo se $y\in L_{2}$ e $y\in L_{2}$ se e solo se $x\in L_{1}$
>Ma quanto impiega $T_{1}$  a decidere $L_1$?
>Con input $x$ la prima fase termina in $O(|x|^{c})$ passi e la seconda fase termina in $O(f(|y|))$ passi. Ma quanto è grande $|y|$ in funzione di $|x|$? Poiché $T_{r}(x)$ impiega $O(|x|^c)$ passi per calcolare $y$, e in questo numero di passi sono conteggiati anche i passi che occorono a scrivere $y$ sul nastro di output, allora $|y|\in O(|x|^{c})$ e quindi per ogni $x\in\Sigma_{1}^{*}$, $T_{1}(x)$ termina in $O(|x|^{c}+f(|x|^{c}))$ passi, ossia $L_{1}\in\text{DTIME}[n^{c}+f(n^{c})]$.

In particolare: abbiamo due linguaggi $L_{1}\subseteq\Sigma_{1}^{*}$ e $L_{2}\subseteq\Sigma_{2}^{*}$ e sappiamo che $L_{1}\preceq L_{2}$. Abbiamo appena dimostrato che se $L_{2}\in\textbf{P}$ allora $L_{1}\in\textbf{P}$, infatti, in questo caso, esiste una costante $k$ tale che $L_{2}\in \text{DTIME}[n^{k}]$, allora da quanto visto nell'esempio $L_{1}\in\text{DTIME}[n^{c}+(n^{c})^{k}]\subseteq\textbf{P}$.

Tutte le [[Lezione 13 - Funzioni time e space-constructible e specifiche classi di complessità#Specifiche classi di complessità|classi di complessità]] introdotte nella [[Lezione 13 - Funzioni time e space-constructible e specifiche classi di complessità|lezione 13]] sono chiuse rispetto alla riducibilità polinomiale.

**Teorema 6.21**
La classe $\textbf{P}$ è chiusa rispetto alla riducibilità polinomiale ^31c469

Questo teorema dimostra solo il caso visto nell'esempio precedente, quindi "se $L_{1}\preceq L_{2}$ e $L_{2}\in\textbf{P}$ allora $L_{1}\in\textbf{P}$"

Allo stesso modo si dimostra che quando $L_{1}\preceq L_{2}$ se $L_{2}\in\textbf{EXPTIME}$ allora $L_{1}\in\textbf{EXPTIME}$ e così per le altre classi di complessità.

**Teorema 6.22**
Le classi $\textbf{NP},\:\textbf{PSPACE},\:\textbf{EXPTIME},\:\textbf{NEXPTIME}$ sono chiuse rispetto alla riducibilità polinomiale.


# Linguaggi NP-completi

**Def.**
Un linguaggio $L\subseteq\Sigma^{*}$ è $\textbf{NP}$-completo rispetto alla riducibilità polinomiale se:
- $L\in\textbf{NP}$
- per ogni altro $L_{0}\in\textbf{NP}$ vale che $L_{0}\preceq L$

I linguaggi $\textbf{NP}$-completi sono particolarmente importanti per il loro rualo di possibili *linguaggi separatori* fra le classi $\textbf{P}$ e $\textbf{NP}$.

**Corollario**
Se $\textbf{P}\neq\textbf{NP}$ allora, per ogni linguaggio $\textbf{NP}$-completo $L$, $L\not\in\textbf{P}$

**Dim.**
Supponiamo che $L$ sia un linguaggio $\textbf{NP}$-completo e che $L\in\textbf{P}$. 
Poiché $L$ è  $\textbf{NP}$-completo allora, per ogni linguaggio $L_{0}\in\textbf{NP}$, $L_{0}\preceq L$, ma se $L\in\textbf{P}$, poiché $\textbf{P}$ è chiusa rispetto a $\preceq$, questo implica che, per ogni $L_{0}\in\textbf{NP}$, $L_{0}\in\textbf{P}$, ossia $\textbf{P}=\textbf{NP}$, contraddicendo l'ipotesi. $\square$

Qual è il senso di questo corollario? Intanto diciamo che è molto improbabile che un linguaggio $\textbf{NP}$-completo appartenga a $\textbf{P}$, perché si sospetta che $\textbf{P}\neq\textbf{NP}$ secondo la **congettura fondamentale della complessità computazionale**.
Quindi se vogliamo dimostrare che non esiste un algoritmo deterministico che decide in tempo polinomiale un linguaggio che è in $\textbf{NP}$, allora dobbiamo dimostrare che quel linguaggio è $\textbf{NP}$-completo. Se, invece, abbiamo un linguaggio $\textbf{NP}$-completo e progettiamo un algortimo *deterministico* che decide quel linguaggio in tempo polinomiale le opzioni sono due:
- abbiamo risolto la congettura
- abbiamo sbagliato qualcosa

Nel campo ddella calcolabilità, le riduzioni si rivelano utili tanto per dimostrare che un linguaggio è accettabile/decidibile, quanto per dimostrare che un linguaggio non è accettabile/decidibile. Dato un linguaggio $L_{1}$:
- se dimostro che $L_{1}\preceq L_{2}$, per un qualche altro linguaggio $L_2$ decidibile, allora posso concludere che anche $L_1$ è decidibile
- se dimostro che $L_{0}\preceq L_{1}$, per un qualche linguaggio $L_{0}$ non decidibile, allora posso concludere che anche $L_{1}$ è  non decidibile

Allo stesso modo, le riduzioni polinomiali sono uno strumento utile tanto per dimostrare che un linguaggio è in $\textbf{P}$, quanto per dimostrare che un linguaggio *probabilmente* non è in $\textbf{P}.$ Dato un linguaggio $L_{1}$:
- se dimostro che $L_{1}\preceq L_{2}$ per un qualche altro linguaggio $L_{2}\in\textbf{P}$, allora posso concludere che anche $L_{1}\in\textbf{P}$ 
- se dimostro che $L_{0}\preceq L_{1}$, per un qualche altro linguaggio $L_{0}$, allora posso concludere che **$L_{1}$ non può essere più facile di $L_{0}$, 
- ovvero se $L_{0}$ probabilmente non appartiene a $\textbf{P}$ allora anche $L_{1}$ probabilmente non appartiene a $\textbf{P}$.

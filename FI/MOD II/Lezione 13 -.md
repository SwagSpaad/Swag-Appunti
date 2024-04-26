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

Le uniche relazioni tra classi deterministiche e non deterministiche sono quelle basate sull'osservazione che una macchina deterministica è una particolare macchina non deterministica, ovvero $$\text{DTIME}[f(n)]\subseteq\text{NTIME}[f(n)]\:\:e\:\:\text{DSPACE}[f(n)]\subseteq\text{NSPACE}[f(n)]$$ 
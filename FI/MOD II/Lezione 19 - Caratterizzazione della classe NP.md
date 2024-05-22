*********
Abbiamo visto che tutti i problemi decisionali tali che: 
- il predicato ha la forma $\pi(x,S(x))=\text{esiste }y\in S(x)$ tale che $\eta(x,y)$  
- la scelta di un elemento $y$ di $S(x)$ richiede tempo non deterministico polinomiale in $|x|$ 
- la verifica che $y$ soddisfi il predicato $\eta$ richiede tempo polinomiale in $|x|$
appartengono a $\textbf{NP}$ e questi tre punti sono, quindi, condizioni sufficienti per poter affermare l'appartenenza di un problema ad $\textbf{NP}$.

Esiste, però, un problema che non soddisfa i 3 punti, ma che appartiene comunque ad $\textbf{NP}?$ No, non è possibile e questo ce lo dice il prossimo teorema.

**Teorema 9.1** 
Un linguaggio $L\subseteq\Sigma^{*}$ appartiene ad $\textbf{NP}$ **se e soltanto se**:
- esistono una mdT deterministica $T$ e due costanti $h,k\in\mathbb{N}$ tali che, per ogni $x\in\Sigma^{*}$ $$\begin{align*}
x\in L\iff &\exists\ y_{x}\in\{0,1\}^{*}:|y_{x}|\leq|x|^{k}\\\\
&\land T(x,y_{x}) \text{accetta}\\\\
&\land\text{dtime}(T,x,y_{x})\in O(|x|^{h})
\end{align*}$$ 
Cosa vuol dire questo teorema?
Osserviamo che questo teorema è una **condizione necessaria e sufficiente** per poter dire che "$L$ appartiene ad $\textbf{NP}$", e siccome è una condizione necessaria e sufficiente, dobbiamo scomporlo in due parti.

# Dimostrazione $\implies$
Se partiamo dall'ipotesi che $L$ appartiene ad $\textbf{NP}$, il teorema ci indica una condizione necessaria e sufficiente per poter affermare che $x\in L$, ovvero $$\begin{align*}
x\in L\iff &\exists\ y_{x}\in\{0,1\}^{*}:|y_{x}|\leq|x|^{k}\\\\
&\land T(x,y_{x}) \text{accetta}\\\\
&\land\text{dtime}(T,x,y_{x})\in O(|x|^{h})
\end{align*}$$ 
Ma cosa vuol dire questa condizione?
Per poter affermare che $x\in L$ allora: 
- $\exists\ y_{x}\in\{0,1\}^{*}$ ci dice che dobbiamo trovare una parola $y$ da associare ad $x$ 
- $|y_{x}|\leq|x|^{k}$ che non sia troppo lunga
- $\land\ T(x,y_{x})\text{ accetta}$ e che induca *una certa macchina deterministica* $T$ ad accettare
- $\land\text{ dtime}(T,x,y_{x})\in O(|x|^{h})$ e ad accettare in tempi brevi!

Il teorema quindi ci dice che se $L\in \textbf{NP}$ allora esiste una mdT deterministica $T$ tale che, se le do in input due parole $x$ ed $y$, con $y$ scelta da me e non troppo lunga, la computazione $T(x,y)$, in tempo polinomiale in $|x|$, *accetta se e solo se* $x\in L$ ed ho scelto la $y$ giusta. Infatti il teorema dice che **riesco a trovare una parola $y_{x}$ che possa convincere $T$ ad accettare se $x\in L$, ma non se $x\not\in L$**!

Quindi se trovassi qualcuno in grado di suggeririmi, per ogni $x\in L$, la parola $y_{x}$ giusta, allora riesco ad accettare le parole di $L$ in tempo deterministico polinomiale.
Facciamo quindi tornare il nostro genio, ma invece di chiedergli una quintupla per volta, gli chiediamo la sequenza di quintuple che, data una parola $x$, costituiscono una computazione accettante di $NT(x)$, e quella parola è proprio $y_x$, anche se prima dobbiamo verificare la correttezza e quindi dobbiamo: 
- verificare che $y_{x}$ sia una sequenza di quintuple di $NT$ che può eseguire su input $x$
- verificare che $y_{x}$ corrisponda ad una computazione accettante
se tutti questi casi sono verificati, allora posso concludere che $x\in L$ 

Per eseguire questa verifica costruiamo una macchina deterministica $T$, che chiameremo **verificatore**.
Quanto impiega $T(x,y_{x})$ ad eseguire la verifica?
- poiché $L\in\textbf{NP}$, se $x\in L$ allora esiste una computazione deterministica accettante di $NT$ lunga $|x|^{k}$ passi - dove $\text{ntime}(NT,z)\leq|z|^{k}$ per ogni $z\in L$
- perciò $|y_x|\leq|x|^{k}$ 
- per verificare che $y_{x}$ sia una sequenza di quintuple di $NT$, $T$ impiega $O(|y_x|)$ passi 
- per verificare che $y_{x}$ corrisponda ad una computazione accettante di $NT(x)$, $T$ deve simulare l'esecuzione delle quintuple descritte in $y_x$ e dunque simula $|x|^{k}$ passi di $NT$ e impiega $O(|x|^{k}\cdot |y_x|)\subseteq O(|x|^{2k})$ passi
Quindi $T$ impiega tempo polinomiale in $|x|$ per verificare che il genio abbia detto la verità.

Ricapitolando:
se $L\in \textbf{NP}$ - e quindi è accettato da una macchina non deterministica $NT$ tale che, per ogni $x\in L$, $\text{ntime}(NT,x)\leq|x|^{k}$ - se ho un genio in grado di suggerirmi, per ogni $x\in L$, la parola $y_{x}$ che corrisponde ad una computazione accettante di $NT(x)$, allora posso costruire un **verificatore deterministico $T$** tale che, se gli do in input una parola $x$ e la parola $y_x$ che mi ha suggerito il genio, $T(x,y_x)$ accetta se e solo se $x\in L$ e il genio ha comunicato la parola $y_x$ corretta e lo fa in tempo polinomiale in $|x|$. 

**Oss.**
$T$ è in grado di verificare che il genio non ha mentito solo se $x\in L$.
Se $x\not \in L$ non c'è verso, infatti il genio non può trovare una parola che corrisponda ad una computazione accettante di $NT(x)$, ma per come abbiamo costruito $T$, se $x\not\in L$, qualunque parola $y$ ci venga indicata dal genio, **$T(x,y)$ rigetta!**

Siccome per ogni $x\in L$, $\text{ntime}(NT,x)\leq|x|^{k}$, allora posso fare in modo che, per ogni $x\in L$ e per ogni $y$ tale che $|y|\leq|x|^{k}$, $\text{dtime}(T,x,y)\leq|x|^{hk}$.

## Un po' di osservazioni
Nell'enunciato del teorema si parla dell'esistenza di una $y_{x}\in\{0,1\}^{*}$, ma la $y_{x}$ che abbiamo tirato fuori nella dimostrazione mica è una parola in $\{0,1\}^{*}$, ma sappiamo bene come codificare una parola in binario, ed abbiamo giá parlato di come trasformare una macchina di Turing definita su un alfabeto generico in una macchina di Turing definita sull'alfabeto $\{0,1\}$ in modo tale che esse siano polinomialmente correlate.
Successivamente quel che ci chiediamo é: "$x\in L$"?
Se il Genio risponde di "si", noi non gli crediamo, allora, per dimostrarci che ha detto la veritá, ci comunica la parola $y_{x}$ che poi noi successivamente verificheremo.

Per questo, se $x\in L$, $y_{x}$ prende il nome di **Certificato** per $x$

# Dimostrazione $\impliedby$

**Teorema 9.1:** Un linguaggio $L \subseteq \Sigma^{*}$ appartiene ad $NP$ se e solo se:


Esistono una macchina di Turing deterministica $T$ e due costanti $h,k \in \mathbb{N} : \forall x\in \Sigma^{*}$,

$x\in L \iff \exists y_{x}\in \{0,1\}^{*}:|y_{x}| \leq |x|^{k} \land T(x,y_{x})$ accetta $\land \ dtime(T,x,y_{x})\in O(|x|^{h})$

Dobbiamo dimostrare la seconda parte del teorema:

Dato $L$, 

Se esistono una macchina di Turing deterministica $T$ e due costanti $h,k \in \mathbb{N} : \forall x\in \Sigma^{*}$,

$x\in L \iff \exists y_{x}\in \{0,1\}^{*}:|y_{x}| \leq |x|^{k} \land T(x,y_{x})$ accetta $\land \ dtime(T,x,y_{x})\in O(|x|^{h})$

Bisogna dimostrare che $L\in NP$, ovvero che esistono una macchina di Turing non deterministica $NT$ e un intero $a$:

$\forall x\in L, \ NT(x)$ accetta e $ntime(NT,x) \in O(|x|^{a})$

$\forall x \notin L, \ NT(x)$ non accetta

E come dimostriamo che esistono $NT$ ed $a$?

1. Costruiamo $NT$ (sfruttando la nostra conoscenza delle parole in $L$ ed usando $T$)

2. Dimostriamo che $NT$ accetta $L$

3. Dimostriamo che, sulle parole di $L$, $NT$ opera in tempo polinomiale

## Fase 1.

Cosa sappiamo sulle parole di $L$?

$x\in L \iff \exists y_{x}\in \{0,1\}^{*}:|y_{x}| \leq |x|^{k} \land T(x,y_{x})$ accetta $\land \ dtime(T,x,y_{x})\in O(|x|^{h})$

$T, h, k$ li conoscimo, allora costruiamo una macchina $NT$ che opera in due fasi:$

Con input $x$:

1. $NT$ sceglie non deterministicamente una parola binaria $y$ di lunghezza $|y| \leq |x|^{k}$

2. $NT$ invoca $T(x,y)$ e, se $T(x,y)$ accetta entro $O(|x|^h)$ passi, allora $NT$ accetta

**Oss.**

$f(n) = n^{k}$ é una funzione time-costructible - sia $T_{f}$ il trasduttore che la calcola, in unario, con $dtime(T_{f}, n) \in O(n^{k})$

Vediamo ora nel dettaglio la Fase 1:

Con input $x$:

![[FI/MOD II/img/img18.png | center | 800]] 

Assumiamo che se $x\in L$, $T$ accetta entro $c|x|^{h} \in O(|x|^{h}) passi$

- anche $g(n) = cn^{h}$ è una funzione time-costructible 
- sia $T_{g}$ il trasduttore che la calcola in unario, con $dtime(T_{g}, n) \in O(cn^{h})$

Vediamo ora nel dettaglio la Fase 2:

Con input $y$:

![[FI/MOD II/img/img19.png | center | 800]]

Se $x \in L$ allora esiste $y_{x} \in \{0,1\}^{*}:|y_{x}| \leq |x|^{k} \land T(x, y_{x})$ accetta

- Allora *esiste una sequenza di scelte* nella Fase 1 che genera proprio $y_{x}$

- Allora, nella Fase 2, $T(x, y_{x})$ accetta entro $c |x|^{h}$

- Allora, anche la computazione deterministica di $NT(x)$  corrispondente alla sequenza di scelte che ha generato $y_{x}$ accetta

**Questo dimostra che, se $x \in L$, allora $NT(x)$ accetta**

Se $x \notin L$ allora non esiste alcuna $y_{x} \in \{0,1\}^{*} : |y_{x}|\leq|x|^{k} \land T(x, y_{x})$ accetta

- Allora, *qualunque sia la sequenza di scelte* nella Fase 1 per generare una parola $y$, nella Fase 2, $T(x,y_{x})$ non accetta

- Questo significa che nessuna computazione deterministica di $NT(x)$ accetta

**Questo dimostra che, se $x\in L$, allora $NT(x)$ non accetta**

3. Dimostriamo che, sulle parole di $L$, $NT$ opera in tempo polinomiale

Fase 1:

![[FI/MOD II/img/img18.png | center | 800]]

Calcolare $B$ richiede $O(|x|^{k})$ passi

Il ciclo *while* esegue $|x|^{k}$ iterazioni, in ciascuna della quali

- Sceglie un valore in un insieme di dimensione costante e quindi, impiega un numero costante di operazioni

- Incrementa di 1 una variabile, ovviamente assumendo che questo abbia costo costante (ma non è così)

*Quindi*, complessivamente, il ciclo **while** esegue $O(B) = O(|x|^{k})$ operazioni

Fase 2:

![[FI/MOD II/img/img19.png | center | 800]] 

Calcolare $A$ richiede $O(|x|^{h})$ passi

il ciclo *while* esegue $c|x|^{h}$ iterazioni, in ciascuna delle quali,

- Simula l' esecuzione di una istruzione della computazione $T(x,y)$ (costo costante)

- Confronta lo stato in cui è entrato $T$ con $q_{a}$ (costo costante)

- E, se non è $q_{a}$, incrementa di 1 una variabile (assumendo che abbia costo costante)

**Quindi**, complessivamente, il ciclo *while* esegue $O(|x|^{h})$ operazioni

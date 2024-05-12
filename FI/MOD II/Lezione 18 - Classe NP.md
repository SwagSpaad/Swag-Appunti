******
# La classe NP
Prima di addentrarci in questioni strutturali, cerchiamo di capire, perchè la classe $NP$ è così importante?
Tanto importante che qualcuno ha pensato di mettere una taglia da un milione di dollari sulla congettura $P \neq NP$ !!
La classe $P$ è importante perchè se collochiamo un problema in $P$ quel problema sappiamo risolverlo per davvero.
**MA**, La classe $NP$?
Cosa ci importa di sapere se un certo problema per il quale magari non riusciamo a trovare un algoritmo polinomiale $\in NP$.
Che ce ne importa di sapere che quel problema è deciso da una macchina
*non deterministica* in tempo polinomiale?

# L'importanza della classe NP
Se l'importanza di $NP$ non va individuata nel modello di calcolo sul quale è basata, allora non può che risiedere nei problemi che la popolano!
In effetti nella classe $NP$ si trovano tanti problemi:
- Acquistare i biglietti aerei per un giro di tutte le capitali dell’UE, spendendo in totale al massimo 10000 euro
- Suddividere un insieme di oggetti (ciascuno di peso diverso) sui due piatti di una bilancia in modo tale che alla fine la bilancia sia in equilibrio
- Piastrellare un pavimento con mattonelle di forme e dimensioni diverse in modo tale che non rimangano spazi scoperti
- Scegliere al più 10 rappresentanti degli studenti ai quali comunicare una direttiva in modo tale che ogni altro studente conosca almeno uno di quelli che sono stati scelti così da poter essere informato
- ...e tanti (ma tanti) altri...
Che hanno grande rilevanza pratica
Che non si riesce a risolvere mediante algoritmi (deterministici) polinomiali …
Ma che _non si riesce_ nemmeno a dimostrare che non possono essere risolti in tempo deterministico polinomiale

# La struttura dei problemi in NP
 Dunque, sappiamo che i $NP$ si trovano molti problemi (decisionali) importanti, e sappiamo anche che un problema é in $NP$ quando esiste una macchina di turing non deterministica che **accetta** le sue istanze **si** in tempo polinomiale.
 **Ma** allora perché continuiamo a dire che in $NP$ si trovano i linguaggi **accettati** in tempo non deterministico polinomiale? perche continuiamo a non usare la parola "**decisi**"?
 Per comprendere questo particolare dobbiamo tornare indietro di qualche lezione: il **Genio burlone e pasticcione** che costituisce uno dei modelli di una computazione non deterministica.
  > Quando durante una computazione non deterministica $NT(x)$, la macchina si trova in un certo stato $q$ e legge un certo simbolo $s$, e nell'insieme delle quintuple $NT$ essitono tante quintuple che iniziano con la coppia $(q,s)$, quale quintupla esegue $NT$?!
  > (*Multiquintupla*) 

In questo caso il **Genio burlone e pasticcione** ha la risposta 

# Multi-quintuple e Genio
Tornando a prima quindi, quale quintupla esegue tra quelle a disposizione $NT$?
Dato che $NT$ é ricorsa al **genio** per decidere si aspetta che lui tramite le sue doti magiche riesca a decidere quale sia la **quintupla giusta** da eseguire
	Ovvero la quintupla che, **nell'ipotesi che x sia una istanza sí del problema**, porti $NT$ ad accettare
L'intervento del genio possiamo modellarlo tramite un'apposita istruzione in *PascalMinimo* (istruzione:*scegli*)

## Istruzione scegli
![[FI/MOD II/img/img15.png|center|800]]
*Comprensione:*
- Fino a quando la macchina $NT$ non entra nello stato $q_{A}$ o nello stato $q_{R}$ 
	- (e lo stato in cui si trova $NT$ é memorizzato nella variabile $q$)
- Calcola l'insieme $\Psi$ delle quintuple che puó eseguire trovandosi nello stato $q$ e leggendo $N[T]$
	- $T$ indica la posizione della testina sul nastro $NT$
- Se $\Psi$ contiene almeno una quintupla, ne sceglie una de eseguire e la esegue
	- Gestendo le porzioni iniziali e finali del nastro mediante primaCella e ultimaCella

In questo caso, invece di simulare tutte le computazioni deterministiche di $NT(x)$, l'algoritmo si affida al **Genio** per scegliere, di volta in volta, le quintuple da eseguire.

**Oss.**
Se ad un certo istante $\Psi$ non contiene quintuple e $q$ non é $q_{A}$ e non é $q_{R}$, l'algoritmo entra in loop!
	Ma questo caso accade solo quando $P$ non é totale

## Ma il Genio é pasticcione
Quali conseguenze comporta ricorrere al **Genio**?
	Del quale ovviamente non ci si puó fidare!
Eseguiamo l'algoritmo della [[Lezione 7 - Tesi di Church-Turing e Turing-equivalenza]] con input $x$ - chiamiamo $\mathcal{A}$ l'algoritmo e $\mathcal{A}(x)$ la sua esecuzione sull'input $x$ 
- $\mathcal{A}(x)$ termina in $q_{A}$ o in $q_{R}$ 
	- Ovviamente assumendo che $P$ sia totale...e noi lo assumiamo!
- Se $\mathcal{A}(x)$ termina in $q_{A}$ allora possiamo essere certi che il **Genio** ci ha indicato le risposte corrette
	- Perché il **Genio** ha trovato una sequenza di quintuple da eseguire che termina nello stato $q_{A}$, e quella sequenza costituisce una computazione accettante di $NT(x)$ e, dunque, *esiste una computazione accettante di $NT(x)$!*
- Se $\mathcal{A}(x)$ termina in $q_{A}$, allora, possiamo essere certi che possiamo accettare
- Ma se $\mathcal{A}(x)$ termina in $q_{R}$ allora qualche dubbio ci viene...
	- Perché il genio ha trovato una sequenza di quintuple da eseguire che termina nello stato $q_{R}$, e quella sequenza costituisce una computazione di $NT(x)$ che rigetta e, dunque, *esiste una computazione di $NT(x)$ che rigetta*
	- Ma ovviamente, **questo non dimostra che tutte le computazioni di $NT(x)$ rigettano!**

Ecco, allora, dato che noi non ci fidiamo del **Genio**, possiamo solo concludere che il **Genio** non ha trovato la sequenza di quintuple che porta $NT$ nello stato di accettazione.
> Ma non possiamo sapere se non l'ha trovata, perché una sequnza di quintuple che induce $NT$ ad accettare non esiste o perché il* **Genio** *non é stato sufficientemente abile da trovarla!

Ecco quindi perché continuiamo a parlare di linguaggi **accettati**, piuttosto che **decisi**

# La struttura dei problemi in NP
Il **Genio** a "mezzo servizio" gioca un ruolo fondamentale per comprendere la struttura dei problemi che popolano la classe $NP$.
E per comprendere questa struttura, facciamo un po' di esempi di problemi e di esempi di **algoritmi non deterministici che li risolvono**
E siccome ci accingiamo a progettare algoritmi che decidono problemi anziché linguaggi, lí descriveremo ad "alto livello", utilizzeremo il PascalMinimo, mettendo da parte le macchine di Turing.
Ma prima di fare ció, dobbiamo chiarire una piccola questione.

**Quanto é potente questo Genio?** 
Se disponiamo di un **Genio**, perché non gli chiediamo direttamente "l'istanza $x$ é un'istanza sí del mio problema?"
Innanzitutto, perché delle risposte del **Genio** non mi fido:
- Se gli chiedo di indicarmi quale quintupla eseguire ad un certo punto della computazione, poi posso verificare che mi ha indicato una quintupla che posso eseguire davvero
- Se gli chiedo di dirmi se $x$ é un'istanza sí, poi come verificare effettivamente la risposta?
Ma soprattutto, abbiamo **introdotto il Genio per modellare il non determinismo** 
- Per questo gli chiediamo di scegliere quale quintupla eseguire a ciascun passo della computazione	
- **E il numero di quintuple fra le quali scegliere é il grado di non determinismo della macchina**
- Che é *Costante*
Detto questo, va bene trasportare il **Genio** nel mondo degli algoritmi di alto livello, a patto che gli proporremo sempre di operare fra un numero *Costante* di opzioni.

# Il problema 3Sat
Dati un insieme $X$ di variabili booleane ed un predicato $f$, definito sulle variabili in $X$ e contenente i soli operatori $\land \ \lor \ \lnot$, decidere se esiste una assegnazione $a$ di valori in $\{Vero, Falso\}$ alle variabili in $X:f(a(X))= Vero$ 
Consideriamo soltanto predicati $f$ in forma 3-congiuntiva normale (3CNF), ossia, 
- $f$ é la congiunzione di un certo numero di clausole: $f= c_{1} \land c_{2} \ ...\land c_{m}$
- Ciascuna $c_{j}$ é la disgiunzione ($\lor$) di tre letterali (3CNF), ad esempio $x_{1}\lor \lnot x_{2} \lor x_{3}$
Questo problema prende il nome di 3Sat, ed é cosí formalizzato:
- $\Im_{3Sat} = \{<X,f> \ : \ X$ é un insieme di variabili booleane $\land$ $f$ é un predicato su $X$ in 3CNF $\}$ 
- $S_{3Sat}(X,f) = \{a:X \rightarrow \{Vero, Falso\}\}$ ($S$ insieme delle assegnazioni di veritá alle variabili $X$)
- $\pi_{3Sat}(X,f,S_{3Sat}(X,f)) = \exists \ a \in S_{3Sat}(X,f):f(a(X)) = Vero$
**Possibile algoritmo non deterministico:**
![[FI/MOD II/img/img16.png|center|800]]
Questo algoritmo é logicamente suddiviso in 2 parti:
- La prima parte ha carattere prettamente non deterministico
	- Serve a scegliere una assegnazione di veritá $a$ per le variabili in $f$ 
- La seconda parte ha carattere prettamente deterministico
	- Serve a verificare deterministicamente che l'assegnazione scelta soddisfi effettivamente $f$

>[!Warning]
>Poiché il numero di possibilitá fra le quali scegliere ad ogni passo é pari a 2, si tratta effettivamente di un algoritmo non deterministico.
>Poiché l'algoritmo accetta se e solo se *esiste* una sequenza di scelte che soddisfa $f$, allora é un algoritmo che accetta 3Sat.

**Complessitá:**
- il primo *while* richiede tempo non deterministico lineare in $n = |X|$ 
- il secondo *while* richiede tempo deterministico lineare in $O(|X| \cdot|f|) = O(3nm) = O(nm)$

**Conclusione:**
l'algoritmo accetta $<X,f> \ \in \ \Im_{3Sat}$ in tempo $O(|X|\cdot|f|)$, e questo prova che 3Sat $\in \ NP$

# Il problema CLIQUE
Il problema CLIQUE consiste nel decidere, dati un grafo non orientato $G=(V,E)$ ed un intero $k\in\mathbb{N}$, se $G$ contiene un sottografo completo di almeno $k$ nodi
- Formalmente, il problema é descritto dalla tripla
	- $\Im_{CLIQUE} = \{<G=(V,E),k> : \ G$ é un grafo non orientato $\land$ $k\in\mathbb{N}\}$ 
	- $S_{CLIQUE} = (G = (V,E),k) = \{V' \subseteq V\}$ ($S$ é l'insieme dei sottoinsiemi di $V$) 
	- $\pi_{CLIQUE}(G,K, S_{CLIQUE}(G,K))= \exists V' \in S(G,K):(\forall u,v \in V'[(u,v)\in E])\land |V'| \geq k$ (ovvero, scelti due nodi in $V'$, essi siano collegati da un arco)
**Possibile algoritmo non deterministico:**
![[FI/MOD II/img/img17.png|center|800]]
Questo algoritmo é logicamente suddiviso in 2 parti:
- La prima parte ha carattere prettamente non deterministico
	- Serve a scegliere un sottoinsieme $V'$ di $V$ 
- La seconda parte ha carattere prettamente deterministico
	- Serve a verificare deterministicamente che il sottoinsieme scelto soddisfi effettivamente $\pi_{CLIQUE}(G,k,S_{CLIQUE}(G,k))$
Questo algoritmo é esattamente come quello che accetta 3SAT.
Dato che il numero di possibilitá fra le quali scegliere ad ogni passo é pari a 2, si tratta effettivamente, di un algoritmo non deterministico.
Poiché l'algoritmo accetta se esiste una sequenza di scelte che soddisfa il predicato di CLIQUE $\pi_{CLIQUE}(G,k,S_{CLIQUE})\implies$ accetta CLIQUE
**Complessitá**
- il primo *while* richiede tempo non deterministico lineare in $n = |V|$
- il secondo *while* richiede tempo deterministico in $O(|V|²(|V|+|E|))$ 
**Conclusione:**
l'algoritmo accetta $<G,k>\in \Im_{CLIQUE}$ in tempo $O(|V|^{2}|E|)$ e questo prova che CLIQUE $\in NP$

# Ma allora...
I tre problemi che abbiamo visto in questa lezione hanno in comune la struttura del predicato $\pi$
- In tutti e tre i problemi $\pi$ ha la forma: esiste almeno un elemento di $S$ che soddisfa certe proprietá, che chiameremo $\eta$
	- $\pi(x,S(x)) = \exists y \in S(x):\eta(x,y)$ 
- Non solo, ma anche gli algoritmi decisionali che abbiamo analizzato seguivano lo stesso schema: dato input $x$ 
	- F1(ND): sceglie una possibile soluzione $y\in S(x)$ 
	- F2(D): verifica che $y$ soddisfa il predicato $\eta(x,y)$ 
- E ancora:
	- F1: sceglie una soluzione possibile che $y$, richiede tempo polinomiale $|x|$
	- F2: verifica che $x$ e $y$ soddisfino il predicato $\eta$ , richiede tempo polinomiale in $|x|$ 
Troppe conincidenze!!
Beh, certamente, tutti i problemi decisionali che $\pi(x,S(x)) = \exists y \in S(x):\eta(x,y)$ possono essere risolti da un algoritmop non deterministico che opera in 2 fasi:
- F1(ND): sceglie una possibile soluzione $y\in S(x)$ 
- F2(D): verifica che $y$ soddisfa il predicato $\eta(x,y)$
E tale algoritmo richiede tempo(ND) polinomiale se:
- F1: sceglie una soluzione possibile che $y$, richiede tempo polinomiale $|x|$
- F2: verifica che $x$ e $y$ soddisfino il predicato $\eta$ , richiede tempo polinomiale in $|x|$
**Quindi:** 
possiamo dire che ogni problema il cui predicato ha la forma $\pi(x,S(x)) = \exists y \in S(x):\eta(x,y)$ 
- in cui la scelta di un elemento $y$ di $S(x)$ richiede tempo polinomiale in $|x|$
- in cui la verifica che $y$ soddisfi il predicato $\eta$, richiede tempo polinomiale in $|x|$ 
appartiene a $NP$
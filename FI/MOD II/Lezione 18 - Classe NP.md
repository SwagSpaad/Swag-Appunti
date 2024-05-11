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
E per comprendere questa struttura, facciamo un po' di esempi di problemi e di esempi di **algoritmi che li risolvono**
E siccome ci accingiamo a progettare algoritmi che decidono problemi anziché linguaggi <<DA CONTINUARE>>
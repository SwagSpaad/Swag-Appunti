****
Per risolvere un problema $\pi$ è necessario progettare una macchina di Turing $T_{\pi}$ apposita per risolvere quel problema. Una volta eseguite le istruzioni codificate nelle quintuple di $T_{\pi}$ su un dato input $x$, otteniamo una soluzione per l'istanza $x$ di $\pi$.
Una macchina di Turing non è altro che un algoritmo, quindi il *modello di calcolo* **M**acchina di Turing è un linguaggio utilizzato per descrivere gli algoritmi. 

# macchine di Turing
**Def.** Macchina di Turing
Sia $\Sigma$ un *alfabeto* finito e $Q$ un insieme finito di *stati*, in cui distinguiamo lo stato iniziale $q_{0}$ ed un insieme di stati finali $Q_{F}$. Una **Macchina di Turing** $T$ sul'alfabeto $\Sigma$ e sull'insieme di stati $Q$ è un dispositivo di calcolo dotato di: 
- un'unità di controllo che ad ogni istante si trova in uno stato qualsiasi di $Q$
- un nastro di lettura/scrittura di lunghezza infinita suddivisa in celle contenenti i caratteri di $\Sigma$ oppure il carattere $\square$ (blank). 
- una testina di lettura/scrittura posizionata sulla cella del nastro
- un programma, ossia un insieme $P$ di quintuple del tipo $\langle\:q_{1},a_{1},a_{2},q_{2},m\rangle$ in cui $q_{1} \in Q-Q_{F}$, $q_{2}\in Q$, $a_{1},a_{2}\in \Sigma$, e $m\in\{\text{sinistra, destra, fermo}\}$ 

Il funzionamento è semplice: quando l'unità di controllo si trova in uno stato $q_{i}$, la testina legge il simbolo contenuto nella testina su cui è posizionato, cerca una quintupla che ha come primi elementi $q_i$ e il simbolo letto e, se la trova, esegue le tre azioni in essa indicate, ovvero
- sovrascrive il simbolo nella cella puntata con il simbolo indicato nella quintupla
- cambia (eventualmente) lo stato interno
- muove (eventualmente) la testina
Eseguita la prima quintupla, si cerca un'altra quintupla da eseguire, finché nessuna quintupla può essere eseguita.

Una **macchina di Turing** quindi è una **quintupla** $\langle \Sigma,Q, q_{0},Q_{F},P \rangle$ 

**Def.** macchina di Turing a $k$ nastri
Una macchina di Turing a $k$ nastri è caratterizzata da:
- un alfabeto $\Sigma$ 
- un insieme *finito* $Q$ di **stati interni**
- uno stato interno iniziale
- un sottoinsieme $Q_{F}$ di $Q$ di **stati finali**
- un insieme $P$ di **quintuple** che hanno la forma $$\langle q_{1},(a_{1},a_{2},\dots,a_k),(b_{1},b_{2},\dots,b_k),q_{2},(m_{1},m_{2},\dots,m_k)\rangle$$
	- dove $a_{1}$ è il carattere letto sul nastro 1, $a_{2}$ è il carattere letto sul nastro 2 ecc.
	- $b_{1}$ è il carattere scritto sul nastro 1, $b_{2}$ è il carattere scritto sul nastro 2 ecc.
	- $m_{1}$ è il movimento da compiere sul nastro 1 ecc.

**Oss.**
Il numero di componenti del secondo elemento delle quintuple di $P$ corrisponde proprio al numero di nastri della macchina di Turing
# Stati globali
Uno stato globale $SG$ di una macchina di Turing è *informalmente* una fotografia della macchina ad un certo istante. 
Formalmente uno **stato globale** di una macchina di Turing $T$ ad un certo istante, è una parola che contiene una descrizione della porzione *non blank* del nastro della machina $T$, della posizione della testina e dallo stato interno. È rappresentato mediante la sequenza di caratteri (non blank) contenuti sul nastro in cui il carattere letto dalla testina *è preceduto* dallo stato interno.

![[FI/MOD II/img/img3.png|center|500]]

Nel caso (a) lo stato globale $SG$ è $q_{0}=812+53$, mentre nel caso (b) lo stato globale $SG$ è $=812+q_{3}^{0}5$ 

# Transizioni
Una transizione dallo stato globale $SG_{1}$ allo stato globale $SG_{2}$ avviene quando viene eseguita una quintupla che trasforma $SG_{1}$ e $SG_{2}$. 
Se $T = \langle \Sigma,Q, q_{0},Q_{F},P \rangle$  è una macchina di Turing ad un nastro, esiste una **transizione** da $SG_{1}\to SG_{2}$ se esiste una quintupla $\langle q,x,x',q',m\rangle\in P$ tale che: 
- in $SG_{1},\:T$ si trova nello stato interno $q\in Q$ 
- in $SG_{1}$, la testina di $T$ sta leggendo la cella con il carattere $x\in \Sigma$
- in $SG_{2}$ la cella che in $SG_{1}$ conteneva il carattere $x$, ora contiene il carattere $x'\in \Sigma$
- in $SG_{2}, T$ si trov nello stato $q'\in Q$
- in $SG_{2},$ la testina di $T$ sta scandendo la cella in posizione $m$ rispetto a quella che stava scandendo in $SG_{1}$ 

![[FI/MOD II/img/img4.png|center|500]]

Nella figura vediamo la transizione da $SG_{(a)}\:=812+q_{3}^{0}5$ a $SG_{(b)}=812q_{3}^{0}+5$ data dalla quintupla $\langle q_{0}^{3},5,5,q_{0}^{3},\text{sx}\rangle$ 

# Computazione
Informalmente, una computazione di una macchina di Turing *deterministica* a un nastro $T = \langle \Sigma,Q, q_{0},Q_{F},P \rangle$ è l'esecuzione delle quintuple di $T$ su una sequenza di caratteri scritti sul nastro.

**Def.** Computazione
Una **computazione** di una macchina di Turing $T$ è una sequenza $SG_{0},SG_{1},\dots,SG_{h},\dots$ di stati globali di $T$ tali che: 
- $SG_0$ è uno *stato globale iniziale*, ossia uno stato globale nel quale lo stato interno è $q_{0}$ e la testina è posizionata sul carattere più a sinistra del nastro
- per ogni $0\le i\le h-1$, esiste una transizione da $SG_{i}\to SG_{i+1}$ oppure per ogni $h\ge i+1$, $SG_{h}$ non è definito
Se esiste un indice $h$ tale che $SG_{h}$ è uno stato globale dal quale non può avvenire alcuna *transizione* allora la **computazione termina** e questo accade quando lo stato interno nel quale $T$ si trova in $SG_{h}$ è uno stato finale oppure quando $P$ non contiene una quintupla che possa essere eseguita in $SG_{h}$ 
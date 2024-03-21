*********
Introduciamo dei diversi tipi di macchine di Turing: 
>**macchine di Turing a testine solidali**
>In ogni istruzione le celle dei nastri scandite dalle testine hanno lo stesso indirizzo, quindi, assumendo che all'inizio della computazione tutte le testine siano posizionate sull'indirizzo 0 dei rispettivi nastri, all'istante successivo **tutte** le testine saranno posizionate all'indirizzo +1 o -1.
>
>Una quintupla di una macchina di Turing a $k$ nastri a testine solidali ha la forma $$\langle q_{i},\overline{s_{1}},\overline{s_{2}},q_{j},mov\rangle$$
>dove $\overline{s_{1}}=(s_{1_{1}},s_{1_{2}},\dots,s_{1_{k}})$, $\overline{s_{2}}=(s_{2_{1}},s_{2_{2}},\dots,s_{2_{k}})$ e $mov \in \{\text{destra,sinistra,fermo}\}$. Il significato di una quintupla è la seguente: *se* la macchina è nello stato $q_{i}$, la testina $1$ legge il simbolo $s_{1_{1}}$ sul nastro $1$, la testina $2$ legge il simbolo $s_{1_{2}}$ sul nastro $2$,$\dots$,la testina $k$ legge il simbolo $s_{1_{k}}$ sul nastro $k$, *allora* la testina $1$ scrive il simbolo $s_{2_{1}}$ sul nastro $1$,$\dots$, la testina $k$ scrive il simbolo $s_{2_{k}}$ sul nastro $k$, la macchina entra nello stato $q_{j}$ e tutte le testine eseguono il movimento $mov$.

>**macchine di Turing a testine indipendenti**
>In una macchina di questo tipo, in seguito all'esecuzione di una quintupla le testine si muovono indipendentemente l'una dalle altre. 
>
>Una quintupla di una macchina di Turing a $k$ nastri a testine indipendenti ha la forma $$\langle q_{i},\overline{s_{1}},\overline{s_{2}},q_{j},\overline{mov}\rangle$$ dove $\overline{s_{1}},\overline{s_{2}}$ come sopra e $\overline{mov}=(mov_{1},mov_{2},\dots,mov_{k})$ e $mov_{h}\in\{\text{sinistra,fermo,destra}\}$ per ogni $h=1,2,\dots,k$.  Il significato di una quintupla è la seguente: *se* la macchina è nello stato $q_{i}$, la testina $1$ legge il simbolo $s_{1_{1}}$ sul nastro $1$, la testina $2$ legge il simbolo $s_{1_{2}}$ sul nastro $2$,$\dots$,la testina $k$ legge il simbolo $s_{1_{k}}$ sul nastro $k$, *allora* la testina $1$ scrive il simbolo $s_{2_{1}}$ sul nastro $1$,$\dots$, la testina $k$ scrive il simbolo $s_{2_{k}}$ sul nastro $k$, la macchina entra nello stato $q_{j}$ e la testina $1$ esegue il movimento $mov_{1}$, $\dots$, la testina $k$ esegue il movimento $mov_{k}$. 

Inoltre possiamo anche prendere in considerazione macchine con **un solo nastro di lettura/scrittura**, macchine che usano un **alfabeto con tanti simboli**, macchine che usano un **alfabeto binario**.
Possiamo dimostrare che tutto quello che possiamo fare con una macchina di un qualsiasi tipo, è possibile farla con macchine di diverso tipo.

# Equivalenza testine solidali e indipendenti
Poiché una macchina a testine solidali può essere considerata come una particolare macchina a testine indipendenti in cui, ogni volta che si esegue una quintupla, tutte le testine si muovono allo stesso modo, allora **tutto ciò che facciamo con il modello a testine solidali, possiamo farlo anche con quello a testine indipendenti**. 

Mostriamo l'inverso, ovvero che **tutto ciò che facciamo col modello a testine indipendenti riusciamo a farlo col modello a testine solidali**.

Consideriamo una macchina di Turing $T_{2}$ a testine indipendenti dotata di 2 nastri e mostriamo come simularne il comportamento mediante una macchina di Turing $T_{3}$ a testine solidali. I primi due nastri di $T_{3}$ sono *inizialmente* una copia dei nastri di $T_{2}$, mentre il terzo nastro contiene un solo carattere non appartenente a $\Sigma$, ovvero "$*$" nella cella di indirizzo $0$, ed ha lo scopo di segnalare alle testine dove posizionarsi.

La macchina $T_{3}$ simula $T_{2}$ mediante una *sequenza di shift* dei contenuti dei primi due nastri in modo tale che ad ogni passo, la testina di $T_{3}$ sia sempre posizionata sulle celle il cui indirizzo coincide con quella della cella del terzo nastro in cui è scritto il carattere $*$. 

Sia $\langle q_{1},(a,b),(c,d),q_{2},(\text{mov}_{1},\text{mov}_{2})\rangle$ una quintupla di $T_{2}$ e vediamo come dipendentemente dai valori di $\text{mov}_{1}$ e $\text{mov}_{2}$. 
- se $\text{mov}_{1}=\text{mov}_{2}$ allora la quintupla $\langle q_{1},(a,b),(c,d),q_{2},(\text{mov}_{1})\rangle$=$\langle q_{1},(a,b),(c,d),q_{2},(\text{mov}_{2})\rangle$ è inserita nelle quintuple della macchina di Turing $T$ a testine solidali.
## Il caso $\text{mov}_{1}\neq\text{mov}_2$
Le cose si complicano quando $\text{mov}_{1}\neq\text{mov}_2$.
Sia $\langle q_{1},(a,b),(c,d),q_{2},(\text{mov}_{1},\text{mov}_{2})\rangle$ di $T_{2}$ a testine indipendenti. Dopo aver scritto i caratteri $c$ e $d$ rispettivamente sul primo e secondo nastro, eseguiamo uno shift dei primi due nastri in modo tale che i caratteri che devono essere letti in seguito si trovino nelle stesse celle aventi lo stesso indirizzo della cella del terzo nastro in cui è scritto il carattere "$*$". 
Consideriamo come esempio il caso in cui $\text{mov}_{1}=\text{destra}$ e $\text{mov}_{2}=\text{sinistra}$. Per eseguire questi due movimenti, dopo aver letto e riscritto i caratteri, basta *traslare* il contenuto del primo nastro di una cella a sinistra (corrisponde ad uno spostamento della testina a destra nella macchina a testine indipendenti) ed il contenuto del secondo nastro di una cella a destra. 
Alla quintupla $\langle q_{1},s_{1_{1}},s_{1_{2}}),(s_{2_{1}},s_{2_{2}}),q_{2},(\text{destra},\text{sinistra})\rangle$ di $T_{2}$ corrisponde l'insieme di quintuple di $T_{3}$: $$\begin{align*}
&\langle q_{1},(s_{1_{1}},s_{1_{2}},*),(s_{2_{1}},s_{2_{2}},*),q_{1,D}^{DS}(q_{2}),\text{ destra}\rangle\\\\
&\langle q_{1,D}^{DS}(q_{2}),(x,y,z),(x,y,z),q_{1,D}^{DS}(q_{2}),\text{ destra}\rangle \:\forall x\in \Sigma,\forall y \in \Sigma\cup\{\square\}, \forall z\in\{*,\square\}\\
&\text{(sposta la testina sull’ultimo carattere non }\square \text{ sul primo nastro)}\\
\\
&\langle q_{1,D}^{DS}(q_{2}),(\square,y,z),(\square,y,z),q_{1,S}^{DS}(q_{2},\square),\text{sinistra}\rangle \:\forall x\in \Sigma,\forall y \in \Sigma\cup\{\square\}, \forall z\in\{*,\square\}\\
&\text{(si prepara a spostare ciascun carattere sul primo nastro una cella a sinistra)}\\
\\
&\langle q_{1,S}^{DS}(q_{2},a),(x,y,z),(a,y,z),q_{1,S}^{DS}(q_{2},x),\text{sinistra}\rangle\:\forall a,x,y \in \Sigma\cup\{\square\}\::x\neq\square\:\lor\:y\neq\square\\
&\text{(esegue lo spostamento di ciascun carattere sul primo nastro una cella a sinistra)}\\
\\
&\langle q_{2,S}^{DS}(q_{2},a),(\square,\square,z),(a,\square,z),q_{2,D}^{DS}(q_{2},\square),\text{destra}\rangle\:\forall a \in \Sigma\cup\{\square\},\forall z\in\{*,\square\}\\
&\text{(raggiunta la prima posizione a destra in cui le celle del primo e secondo nastro contengono }\\
&\square,\text{ si prepara a spostare ciascun carattere sul secondo nastro una cella a destra)}\\
\\
&\langle q_{2,D}^{DS}(q_{2},b),(x,y,z),(x,b,z),q_{2,D}^{DS}(q_{2},y),\text{destra}\rangle\:\forall b,x\in \Sigma\cup\{\square\},\:\forall y\in \Sigma, \forall z \in \{*,\square\}\\
&\text{(esegue lo spostamento di ciascun carattere sul secondo nastro una cella a destra)}\\
\\
&\langle q_{2,D}^{DS}(q_{2},b),(x,\square,z),(x,b,z),q_{2,S}^{DS}(q_{2}),\text{fermo}\rangle\:\forall b,x\in \Sigma\cup\{\square\},\forall z \in \{*,\square\}\\
&\text{(terminato lo spostamento di ciascun carattere sul secondo nastro una cella a destra,}\\
&\text{si prepara a tornare sulla cella del terzo nastro che contiene *)}\\
\\
&\langle q_{2,S}^{DS}(q_{2}),(x,y,\square),(x,b,\square),q_{2,S}^{DS}(q_{2}),\text{sinistra}\rangle\:\forall x,y,\in \Sigma\cup\{\square\}\\
&\text{(raggiunge la cella del nastro che contiene *)}\\
\\
&\langle q_{2,S}^{DS}(q_{2}),(x,y,*),(x,b,),q_{2}, \text{fermo}\rangle\:\forall x,y \in \Sigma\cup\{\square\}\\
&\text{(entra nello stato }q_2\text{: la simulazione dell’esecuzione della quintupla di }T_{2}\text{ è terminata)} 
\end{align*}$$
La simulazione di una macchina $T_k$ a $k>2$ nastri a testine indipendenti, viene eseguita tramite una macchina $T_{k+1}$ a $k+1$ nastri a testine solidali, in cui il nastro $k+1$ viene utilizzato per rappresentare la posizione della testina e gli altri $k$ nastri rimanenti vengono shiftati a sinistra o a destra in base al movimento nelle quintuple delle testine indipendenti. 

Per dimostrare questa equivalenza abbiamo usato la tecnica della **simulazione**, ovvero quella di progettare una macchina $T'$ con certe caratteristiche che fa la stessa cosa di una macchina $T$ che ha altre caratteristiche. 

# Macchine a $k$ nastri e macchine ad $1$ nastro
Sempre grazie alla tecnica della simulazione, dimostreremo che la capacità computazionale di una macchina di Turing non dipende dal numero di nastri. Quindi dimostreremo che una macchina di Turing a $k$ nastri può essere simulata mediante una macchina di Turing $T_1$ ad un nastro.

In base a quanto visto nel paragrafo precedente, ci limitiamo a considerare una macchina di Turing a $k$ **testine solidali**.

Sia $T_{k}$ una macchina di Turing a $k$ nastri a testine solidali. Definiamo una macchina di Turing $T_{1}$ ad un nastro che utilizza lo stesso alfabeto $\Sigma$ utilizzato da $T_{k}$ ed il cui insieme di stati è $Q\times \Sigma^{k}$. 
Inizialmente l'input $x=(x_{1_{1}},x_{1_{2}},\dots,x_{1_{k}})(x_{2_{1}},x_{2_{2}},\dots,x_{2_{k}})\dots(x_{n_{1}},x_{n_{2}},\dots,x_{n_{k}})$  di $T_{k}$ è scritto sull'unico nastro di $T_1$ come concatenazione di tutti i simboli di $x$, ovvero nella seguente forma: $x_{1_{1}}x_{1_{2}}\dots x_{1_{k}}x_{2_{1}}x_{2_{2}}\dots x_{2_{k}}\dots x_{n_{1}}x_{n_{2}}\dots x_{n_{k}}$. 

Sia $\langle q_{1},(s_{1_{1}}, s_{1_{2}},\dots,s_{1_{k}}),(s_{2_{1}}, s_{2_{2}},\dots,s_{2_{k}}),q_{2},m\rangle$ una quintupla di $T_{k}$ e trasformiamola in un inseme di quintuple per $T_{1}$

1. Osserviamo che mentre a $T_{k}$ è sufficiente una singola operazione di lettura per poter eseguire correttamente la quintupla, $T_{1}$ deve eseguire $k$ operazioni di lettura consecutive, in quanto la quintupla viene eseguita solo se viene letto $s_{1_{1}}$ ed esso è seguito da $s_{1_{2}}$ e così via fino a $s_{1_{k}}$. Quindi per poter eseguire la quintupla è necessario leggere e ricordare $k$ simboli consecutivi $$\begin{align*}&\langle q_{1},s_{1_{1}},s_{1_{1}},q(q_{1},s_{1_{1}},\text{destra})\rangle\\
&\langle q(q_{1},s_{1_{1}}),s_{1_{2}},s_{1_{2}},q(q_{1},s_{1_{1}},s_{1_{2}}),\text{destra}\rangle\\
&\vdots\\
&\langle q(q_{1},s_{1_{1}},s_{1_{2}},\dots,s_{1_{k-1}}),s_{1_{k}},s_{1_{k}},q(q_{1},s_{1_{1}},s_{1_{2}},\dots,s_{1_{k-1}},s_{1_{k}}),\text{sinistra}\rangle
\end{align*}$$
2. Ora, $T_1$ ha verificato che la quintupla può essere eseguita e, per poterlo fare, deve riportare la testina a sinistra di $k$ posizioni $$\begin{align*}
&\langle q(q_{1},s_{1_{1}},s_{1_{2}},\dots,s_{1_{k-1}},s_{1_{k}}),s_{1_{k-1}},s_{1_{k-1}},q(q_{1},s_{1_{1}},s_{1_{2}},\dots,s_{1_{k-1}},s_{1_{k}},k-2),\text{sinistra}\rangle\\
&\langle q(q_{1},s_{1_{1}},s_{1_{2}},\dots,s_{1_{k}},i),s_{1_{i}},s_{1_{i}},q(q_{1},s_{1_{1}},s_{1_{2}},\dots,s_{1_{k}},i-1),\text{sinistra}\rangle\:\forall i=2,\dots,k-2
\end{align*}$$
3. La testina di $T_1$ è posizionata sul carattere corrispondente al carattere scritto sul primo nastro di $T_{k}$ ($s_1$) e può procedere all'esecuzione della quintupla sovrascrivendo i $k$ caratteri $$\begin{align*}
&\langle q(q_{1},s_{1_{1}},s_{1_{2}},\dots,s_{1_{k-1}},s_{1_{k}},1),s_{1_{1}},s_{2_{1}},q^{\text{write}}(q_{1},s_{1_{1}},s_{1_{2}},\dots,s_{1_{k-1}},s_{1_{k}},2),\text{destra}\rangle\\
&\langle q^{\text{write}}(q_{1},s_{1_{1}},s_{1_{2}},\dots,s_{1_{k}},i),s_{1_{i}},s_{2_{i}},q^{\text{write}}(q_{1},s_{1_{1}},s_{1_{2}},\dots,s_{1_{k}},i+1),\text{destra}\rangle \forall i=2,\dots,k\\
&\langle q^\text{write}(q_{1},s_{1_{1}},s_{1_{2}},\dots,s_{1_{k-1}},s_{k},k),s_{1_{k}},s_{2_{k}},q',m'\rangle\\
&\text{dove q' e m' dipendono dal valore di m come vedremo in seguito}
\end{align*}$$
4. $T_{1}$ ha eseguito la prima parte della quintupla, scrivendo i $k$ nuovi caratteri sul nastro; in questo istante, la sua testina è posizionata sulla cella contenente l'ultimo simbolo scritto. Restano da eseguire il cambio di stato interno ed il movimento della testina. Queste operazioni avvengono in maniera differente, dipendentemente dal valore di $m$
	- Se $m=\text{destra}$, allora $q'=q_{2}$ e $m'=\text{destra}$ e l'esecuzione della quintupla è terminata
	- Se $m=\text{fermo}$, allora $q'=q^{sin}(q_{2},k-1)$ e $m'=\text{sinistra}$ e l'esecuzione della quintupla di $T_{k}$ termina con le quintuple seguenti: $$\begin{align*}
&\langle q^{\text{sin}}(q_{2},i),x,x,q^{\text{sin}}(q_{2},i-1),\text{sinistra}\rangle\:\: \forall x\in \Sigma\cup\{\square\}\:\:\forall i=2,\dots,k-1\\
&\langle q^{\text{sin}}(q_{2},i),x,x,q_{2},\text{fermo}\rangle\:\:\forall x\in\Sigma\cup \{\square\}
\end{align*}$$
	- Se $m=\text{sinistra}$, allora $q'=q^{sin}(q_{2},2k-1)$ e $m'=\text{sinistra}$ e l'esecuzione della quintupla di $T_{k}$ termina con le quintuple seguenti: $$\begin{align*}
&\langle q^{\text{sin}}(q_{2},i),x,x,q^{\text{sin}}(q_{2},i-1),\text{sinistra}\rangle\:\: \forall x\in \Sigma\cup\{\square\}\:\:\forall i=2,\dots,2k-1\\
&\langle q^{\text{sin}}(q_{2},i),x,x,q_{2},\text{fermo}\rangle\:\:\forall x\in\Sigma\cup \{\square\}
\end{align*}$$

>**Oss.**
>L'insieme degli stati di $T_{1}$ ha una cardinalità molto maggiore dell'insieme degli stati di $T_{k}$ e l'insieme degli stati finali di $T_1$ coincide con quello degli stati finali di $T_{k}$

# Riduciamo la cardinalità di $\Sigma$ 
In questo paragrafo vediamo come ogni macchina di Turing $T$ definita su un alfabeto $\Sigma$ con $|\Sigma|>2$ possa essere simulata da una macchina di Turing $T_{01}$ definita sull'alfabeto $\{0,1\}$. Indichiamo con $Q$ l'insieme degli stati di $T$ e con $P$ l'insieme delle quintuple.
Sia $\Sigma=\{\sigma_{1},\sigma_{2},\dots,\sigma_{n}\}$, possiamo codificare $\sigma_{i}$ con la rappresentazione binaria dell'intero $i-1$ utilizzando $k=\lceil\log_{2}n\rceil$ bit e rappresentiamo con $b(\sigma)$ questa rappresentazione per ogni $\sigma\in\Sigma$ .

>**Esempio**
>Se $n=10$ allora $k=4$ e $b(\sigma_1)=0000$, $b(\sigma_{2})=0001,\dots,b(\sigma_{9})=1000,b(\sigma_{10})=1001$. 

Per ogni elemento $\sigma\in\Sigma$ e per ogni $h$ compreso tra $1$ e $k=\lceil\log_{2}n\rceil$, indichiamo con $b_h(\sigma)$ l'h-esimo bit di $b(\sigma)$ 

>**Esempio**
>$b(\sigma_{6})=0101$ e $b_{1}(\sigma_{6})=0$ 

Vediamo come simulare il comportamento di $T$ utilizzando una macchina con alfabeto binario $T_{01}$.
Costruiamo $T_{01}$ come una macchina a $k$ nastri e cominciamo a scrivere su ogni nastro la codifica in binaria dell'input scritto sull'unico nastro di $T$. 
- Sia $x_1x_2x_3\dots x_k$ l'input di $T$ (con $x_1x_2x_3\dots x_k$ elementi di $\Sigma$)
- Nelle celle di indirizzo $1$ dei nastri di $T_{01}$ scriviamo i simboli binari della codifica del carattere $x_1$, quindi se $b(x_1)=b_{1}(x_{1})b_{2}(x_{2})\dots b_{k}(x_{1})$ (ossia $b_{i}(x_1)$ indica l'i-esimo bit di $b(x_{1})$) allora scriviamo $b_{i}(x_{1})$ nella cella numero 1 dell'i-esimo nastro. 

>**Esempio** 
>Sia $\Sigma_{T}=\{a,b,c,d,e,f,g\}$ e $b(a)=000,b(b)=001,b(c)=010,\dots,b(z)=111$ 
>![[FI/MOD II/img/img5.png|center|700]]
>A questo punto la quintupla di $\langle q_{1},a,c,q_{2},m\rangle$ di $T$ diventa la quintupla $\langle q_{1},(b_{1}(a),b_{2}(a)\dots b_{k}(a)),(b_{1}(c)b_{2}(c)\dots b_{k}(c)),q_{2},m\rangle$ di $T_{01}$ 

Grazie a quanto visto nel paragrafo [[#Macchine a $k$ nastri e macchine ad $1$ nastro|precedente]] possiamo trasformare la macchina $T_{01}$ a $k$ nastri, in una macchina ad un solo nastro. 

Abbiamo visto quindi come costruire una macchina che fa le stesse cose di un'altra, ovvero formalmente, si dice che "**l'esito della computazione di una macchina su un input coincide con l'esito della computazione dell'altra macchina sullo stesso input**". 
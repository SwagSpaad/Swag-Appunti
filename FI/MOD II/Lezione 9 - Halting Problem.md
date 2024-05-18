*****
Cantor ha dimostrato che esistono insiemi infiniti "piccoli" e "grandi", basandosi sul concetto di corrispondenza biunivoca, definendo la "grandezza" di un insieme infinto con il termine *numero transfinito*. Ha dimostrato anche che non esiste una corrispondenza biunivoca fra l'insieme dei numeri naturali e l'insieme dei numeri reali, provando che l'insieme dei numeri reali $\mathbb R$ è strettamente più grande dell'insieme dei numeri reali $\mathbb N$. 

In questa lezione siamo pronti a rispondere alla seguente domanda 
>*Esiste un problema che non può essere risolto?*

Nei paragrafi 5.1 e 5.2 della [dispensa 5](http://www.informatica.uniroma2.it/upload/2023/FO/D05HaltingProblem.pdf) viene dimostrato che **esiste un problema irrisolvibile** secondo le seguenti osservazioni: siccome le macchine di Turing sono tante quanti i numeri naturali e, poiché i problemi sono tanti quanti i numeri reali, allora esiste almeno un problema che non corrisponde a nessuna macchina di Turing e quindi **non può essere risolto**. 
Questo però non ci dà l'idea di come possa essere fatto un problebma irrisolvibile, ma Turing ha costruito un problema irrisolvibile. 

Nella [[Lezione 5 - La macchina di Turing Universale|lezione 5]] avevamo descritto una macchina di Turing $T$ con alfabeto $\Sigma=\{0,1\}$, l'insieme degli stati $Q_{T}=\{\omega_{0},\dots,\omega_{k-1}\}$ con stato iniziale $\omega_{0}$, stato di accettazione $\omega_{1}$ e stato di rigetto $\omega_{2}$ e l'insieme delle quintuple $P=\{p_{1},\dots,p_{n}\}$ dove la i-esima quitupla è $$p_{i}=\langle \omega_{i_{1}},b_{i_{1}},b_{i_{2}},\omega_{i_{2}},m_{i}\rangle$$ tramite la parolona $$\rho_{T}=\omega_{0}-\omega_{1}\otimes \omega_{1_{1}}-b_{1_{1}}-b_{1_{2}}-\omega_{1_{2}}-m_{1}\oplus\dots\oplus\omega_{h_{1}}-b_{h_{1}}-b_{h_{2}}-\omega_{h_{2}}-m_{h}\oplus$$ Poi, avevamo introdotto la codifica binaria $b^{Q}$ dell'insieme degli stati $Q_{T}$ degli stati di $T$ nel seguente modo:
- $b^{Q}=Q_{T}\to\{0,1\}^{k}$, ovvero la codifica di uno stato in una parola di $k$ bit
- $b^{Q}(\omega_{i})$ è la parola che ha un 1 in posizione $i+1$ e 0 altrove, esempio $b^{Q}(\omega_{0})=1000$ 
e quindi rappresentato la nostra macchina $T$ con la parola $\beta_T$ nell'alfabeto $\Sigma=\{0,1,\oplus,\otimes,-,f,s,d\}$ nel seguente modo $$\beta_{T}=b^{Q}(w_{0})-b^{Q}(w_{1})\otimes b^{Q}(w_{1_1})-b_{1_{1}}-b_{1_{2}}-b^{Q}(\omega_{1_2})-m_{1}\oplus\dots\oplus$$ Quello che viene mostrato nel paragrafo 5.1 è trasformare la parola $\beta_T$ in un numero sostituendo in questo modo: ^a81133
- $s\to 5$
- $f\to 6$
- $d\to 7$
- $- \to 4$ 
- $\oplus\to 3$
- $\otimes\to 2$
- $\square\to 9$
dopo le sostituzioni premettiamo il numero $2$ alla parola ottenuta. In questo modo abbiamo associato ad ogni macchina di Turing un numero intero.

>**Oss.**
>L'associazione è univoca, infatti a macchine di Turing diverse sono associati interni diversi

# Halting Problem
Turing considerò il seguente linguaggio, sottoinsieme di $\mathbb N\times\mathbb N$ $$L_{H}=\{(i,x)\in\mathbb N\times\mathbb N:i\text{ è la codifica di una macchina di Turing }T_{i}\:e\:T_{i}(x)\text{ termina}\}$$ che è detto **Halting Problem** e Turing dimostrò che $L_{H}$ è accettabile e non decidibile (quindi $L_{H}^C$ non è accettabile). Questo lo dimostreremo tra poco.

Ora cerchiamo di capire che senso ha domandarsi se dato $(i,x)\in\mathbb N\times\mathbb N$ allora $(i,x)\in L_{H}$ e lo facciamo con un piccolo esempio.

>**Esempio**
>Supponiamo di aver scritto un programma ed averlo lanciato su un input $x$, che è un istanza del problema risolto dal nostro programma. Attendiamo per molto tempo la risposta, finché sorge un dubbio: **e se fosse andato in loop?**
>Supponiamo allora che esista un programma che, preso in input un programma $P$ e un suo input $x$, mi dice se l'esecuzione di $P$ su $x$ termina oppure no. Questo programma decide l'**Halting Problem**! 

**Teorema 5.4**
$L_{H}$ è un linguaggio accettabile

**Dim.**
Dobbiamo mostrare che esiste una macchina di Turing $T$ che per ogni $(i,x)\in \mathbb N\times\mathbb N$ allora $$O_{T}(i,x)=q_A\iff(i,x)\in L_{H}$$ La macchina che cerchiamo è una modifica della macchina universale $U$, che chiamiamo $U^{'}$, quindi una macchina a $4$ nastri. Su $N_{1}$ scriviamo il numero $i$ in notazione decimale e su $N_2$ scriviamo $x\in\{0,1\}^{*}$. $U^{'}$ inizia la sua computazione verificando che $i$ non contenga cifre 8 e 9 e che inizi con la cifra 2: se non è così la macchina rigetta, altrimenti cancella il $2$ iniziale e traduce quello che rimane nell'alfabeto di lavoro $\Sigma$ di $U$. 
In seguito $U^{'}$ simula la computazione di $U$ e se $U$ termina (sia nello stato di accettazione che di rigetto) allora $U^{'}$ termina nello stato di accettazione

**Oss.**
Se $i$ non è la codifica di una macchina di Turing, allora, poiché l'insieme delle quintuple di una qualsiasi macchina di Turing è [[Lezione 4 - Struttura di P e macchine non deterministiche#Totalità|totale]] e le computazioni con un input che non *rispettano le specifiche* non terminano, allora $U^{'}(i,x)$ non termina.

Sia $(i,x)\in L_{H}$: allora, la computazione $T_{i}(x)$ termina, e quindi, la computazione $U^{'}(i,x)$ accetta. 
Viceversa, sia $(i,x)\in\mathbb N\times\mathbb N$ tale che $U^{'}(i,x)$ accetta; poiché la computazione $U^{'}(i,x)$ simula la computazione $U(i,x)$, allora anche $U(i,x)$ termina e, dunque, $i$ è la codifica di una macchina di Turing e $T_{i}(x)$ termina, quindi $(i,x)\in L_H$. $\square$

**Teorema 5.5**
Il linguaggio $L_{H}$ non è decidibile

Per la dimostrazione andiamo prima a modificare la notazione. Osserviamo che, data una macchina $T$ ed un suo input $x$, il valore $O_{T}(x)$ è definito solo per gli $x$ tali che la computazione $T(x)$ termina. Poiché nella dimostrazione dobbiamo usare spesso la possibilità che una computazione non termini, indicheremo con $T(x)$ sia la computazione dalla macchina $T$ sull'input $x$ che il suo esito. Assumeremo quindi $$T(x)=\begin{cases}
q\in Q_{f} & \text{se la computazione }T(x)\text{ termina} \\
 \\
\text{non termina} & \text{se la computazione }T(x)\text{ non termina}
\end{cases}$$ 
**Dim.**
Procederemo per assurdo. Supponiamo che $L_{H}$ sia decidibile. Allora deve esistere una macchina di Turing $T$ tale che $$T(i,x)=\begin{cases}
q_{A} & \text{se }(i,x)\in L_{H} \\
 \\
q_{R} & \text{se }(i,x)\not\in L_{H} 
\end{cases}$$Assumiamo che $T$ sia una macchina ad un nastro. 
Da $T$ possiamo derivare una nuova macchina $T^{'}$, complementando gli stati di accettazione e rigetto di $T$, che, terminando su ogni input (come $T$), accetta tutte e sole le coppie $(i,x)\in\mathbb{N}\times\mathbb{N}-L_{H}$, ossia $$T^{'}(i,x)=\begin{cases}
q_{R} & \text{se }(i,x)\in L_H  \\
\\
q_{A} & \text{se }(i,x)\not\in L_H
\end{cases}$$ A partire da $T^{'}$ deriviamo una terza macchina $T^{''}$, che accetta $(i,x)$ se $(i,x)\not\in L_{H}$ mentre **non termina** se $(i,x)\in L_{H}$. Con la coppia $(i,x)$ sul nastro, $T^{''}$ invoca $T^{'}$ passandogli $(i,x)$ come parametri, risulta quindi $$T^{''}(i,x)=\begin{cases}
q_{A} & \text{se } T^{'}(i,x) \text{ termina in }q_{A} \\
 \\
\text{entra in loop}  & \text{se } T^{'}(i,x) \text{ termina in }q_{R}
\end{cases}$$ per questo è sufficiente aggiungere le quintuple $\langle q_R,y,y,q_{R},f\rangle$ per ogni $y\in\{0,1\}$ e rimuovendo $q_{R}$ dagli stati finali di $T^{''}$

**Oss.**
Poiché $(i,x)\in\mathbb{N}\times\mathbb{N}$ è una coppia di interi, allora $(i,i)$ può essere dato in pasto alle tre macchine $T,T^{'}, T^{''}$. Se $i$ è la codifica di una macchina di Turing, allora:
- $T(i,i)$ accetta se $(i,i)\in L_{H}$, ossia se $T_{i}(i)$ termina e rigetta se $(i,i)\not\in L_{H}$ ossia se $T_{i}(i)$ non termina
- $T^{'}(i,i)$ accetta se $(i,i)\not\in L_{H}$ ossia se $T_{i}(i)$ non termina e $T^{'}(i,i)$ rigetta se $(i,i)\in L_{H}$, ossia se $T_{i}(i)$ termina
- $T^{''}(i,i)$ accetta se $(i,i)\not\in L_{H}$ ossia se $T_{i}(i)$ non termina e $T^{''}(i,i)$ *non termina* se $(i,i)\in L_{H}$, ossia se $T_{i}(i)$ termina

A partire da $T^{''}$ costruiamo l'ultima macchina $T^{*}$ che *lavora con un solo input* e tale che **l'esito della computazione $T^{*}(i)$ coincide con la computazione di $T^{''}(i,i)$**, quindi se $i$ è la codifica di una macchina di Turing allora $$T^{*}(i)=\begin{cases}
q_{A} & \text{se }(i,i)\not\in L_{H}  \\
 \\
\text{non termina} & \text{se }(i,i)\in L_{H}
\end{cases}$$
Siccome abbiamo supposto che $T$ esiste, allora anche $T^*$ esiste e la possiamo codificare con un intero secondo le sostituzioni [[#^a81133|viste prima]]. Indichiamo con $k$ il codice di $T^{*}$ ottenuto applicando le sostituzioni, quindi $T^{*}=T_{k}$ e siccome $k$ è un numero intero, può essere input di $T^{*}$ e quindi di $T_{k}$. Ci chiediamo ora *qual è l'esito della computazione $T_{k}(k)$?* 
- Se $T_{k}(k)=T^{*}(k)$ accettasse, allora $T^{'}(k,k)$ dovrebbe accettare anch'essa. Ma se $T^{'}(k,k)$ accetta, allora, poiché $k$ è la codifica di una macchina di Turing, allora $(k,k)\not\in L_H$ e quindi $T_{k}(k)$ non termina. Dunque **$T_{k}(k)=T^{*}(k)$ accetta solo se $T_{k}(k)=T^{*}(k)$ non termina**.
- $T_{k}(k)=T^{*}(k)$ non termina solo se $(k,k)\in L_{H}$, ossia se $T_{k}(k)$ termina, quindi **$T_{k}(k)=T^{*}(k)$ non termina solo se $T_{k}(k)=T^{*}(k)$ termina**.
Quindi entrambi le ipotesi portano ad una contraddizione e poiché abbiamo supposto che $L_{H}$ è decidibile, abbiamo costruito una macchina che decide il linguaggio che in realtà non può esistere, portandoci alla conclusione che abbiamo sbagliato la supposizione che **$L_H$ sia decidibile**, dimostrando invece il contrario. $\square$ 

Cosa significa quindi che l'Halting Problem è accettabile ma non decidibile? Ricordiamoci che *un linguaggio $L$ è decidibile se, $L$ è accettabile ed $L^{C}$ è accettabile* e allora poiché $L_{H}$ è accettabile ma non decidibile, concludiamo dicendo che $L_{H}^C$ non è accettabile.


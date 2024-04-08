*****
Cantor ha dimostrato che esistono insiemi infiniti "piccoli" e "grandi", basandosi sul concetto di corrispondenza biunivoca, definendo la "grandezza" di un insieme infinto con il termine *numero transfinito*. Ha dimostrato anche che non esiste una corrispondenza biunivoca fra l'insieme dei numeri naturali e l'insieme dei numeri reali, provando che l'insieme dei numeri reali $\mathbb R$ è strettamente più grande dell'insieme dei numeri reali $\mathbb N$. 

In questa lezione siamo pronti a rispondere alla seguente domanda 
>*Esiste un problema che non può essere risolto?*

Nei paragrafi 5.1 e 5.2 della [dispensa 5](http://www.informatica.uniroma2.it/upload/2023/FO/D05HaltingProblem.pdf) viene dimostrato che **esiste un problema irrisolvibile** secondo le seguenti osservazioni: siccome le macchine di Turing sono tante quanti i numeri naturali e, poiché i problemi sono tanti quanti i numeri reali, allora esiste almeno un problema che non corrisponde a nessuna macchina di Turing e quindi **non può essere risolto**. 
Questo però non ci dà l'idea di come possa essere fatto un problema irrisolvibile, ma Turing ha costruito un problema irrisolvibile. 

Nella [[Lezione 5 - La macchina di Turing Universale|lezione 5]] avevamo descritto una macchina di Turing $T$ con alfabeto $\Sigma=\{0,1\}$, l'insieme degli stati $Q_{T}=\{\omega_{0},\dots,\omega_{k-1}\}$ con stato iniziale $\omega_{0}$, stato di accettazione $\omega_{1}$ e stato di rigetto $\omega_{2}$ e l'insieme delle quintuple $P=\{p_{1},\dots,p_{n}\}$ dove la i-esima quitupla è $$p_{i}=\langle \omega_{i_{1}},b_{i_{1}},b_{i_{2}},\omega_{i_{2}},m_{i}\rangle$$ tramite la parolona $$\rho_{T}=\omega_{0}-\omega_{1}\otimes \omega_{1_{1}}-b_{1_{1}}-b_{1_{2}}-\omega_{1_{2}}-m_{1}\oplus\dots\oplus\omega_{h_{1}}-b_{h_{1}}-b_{h_{2}}-\omega_{h_{2}}-m_{h}\oplus$$ Poi, avevamo introdotto la codifica binaria $b^{Q}$ dell'insieme degli stati $Q_{T}$ degli stati di $T$ nel seguente modo:
- $b^{Q}=Q_{T}\to\{0,1\}^{k}$, ovvero la codifica di uno stato in una parola di $k$ bit
- $b^{Q}(\omega_{i})$ è la parola che ha un 1 in posizione $i+1$ e 0 altrove, esempio $b^{Q}(\omega_{0})=1000$ 
e quindi rappresentato la nostra macchina $T$ con la parola $\beta_T$ nell'alfabeto $\Sigma=\{0,1,\oplus,\otimes,-,f,s,d\}$ nel seguente modo $$\beta_{T}=b^{Q}(w_{0})-b^{Q}(w_{1})\otimes b^{Q}(w_{1_1})-b_{1_{1}}-b_{1_{2}}-b^{Q}(\omega_{1_2})-m_{1}\oplus\dots\oplus$$ Quello che viene mostrato nel paragrafo 5.1 è trasformare la parola $\beta_T$ in un numero sostituendo in questo modo:
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
Dobbiamo mostrare che esiste una macchina di Turing $T$ che per ogni $(i,x)\in \mathbb N\times\mathbb N$ allora $$o_{T}(i,x)=q_A\iff(i,x)\in L_{H}$$ La macchina che cerchiamo è una modifica della macchina universale $U$, quindi una macchina a $4$ nastri. Su $N_{1}$ scriviamo il numero $i$ in notazione decimale e su $N_2$ scriviamo $x\in\{0,1\}^{*}$. $T$ inizia la sua computazione verificando che $i$ non contenga cifre 8 e 9 e che inizi con la cifra 2: se non è così la macchina rigetta, altrimenti cancella il $2$ iniziale e traduce quello che rimane nell'alfabeto di lavoro $\Sigma$ di $U$. 
In seguito $T$ simula la computazione di $U$ e se termina (sia nello stato di accettazione che di rigetto) allora $T$ termina nello stato di accettazione

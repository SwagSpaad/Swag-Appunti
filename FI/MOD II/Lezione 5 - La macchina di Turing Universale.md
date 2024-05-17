*****
Abbiamo visto che una macchina di Turing è un algoritmo, descritto nel linguaggio delle quintuple, che risolve un problema. L'input, per una macchina di Turing, è una *parola* costituita da caratteri di un certo alfabeto. 

Abbiamo detto nella [[Lezione 4 - Struttura di P e macchine non deterministiche|lezione precedente]] che è sufficiente conoscere l'insieme $P$ per sapere tutto di una macchina di Turing $T$, ma $P$ non ci dice proprio tutto, infatti dobbiamo conoscere quale sia lo stato iniziale e gli stati finali della macchina. 

Quindi data una macchina $T$, se decidiamo di costruire una parola secondo i seguenti punti
- il primo carattere della parola è $q_{0}$ che è seguito dal carattere $\text{"-"}\notin\Sigma$ 
- seguito da $q_{A}$ poi da $\text{"-"}$ e poi da $q_{R}$ 
- e questo seguito, una dopo l'altra da tutte le quintuple di $T$
La parola che abbiamo appena costruito *definisce completamente $T$*.

>**Esempio**
>Prendiamo una macchina $T_{PPAL}$ che termina in $q_A$ se la parola scritta sul suo nastro ha lunghezza pari ed è palindroma. 
>Il suo stato iniziale è $q_0$, lo stato di accettazione è $q_A$, quello di rigetto $q_R$ e le sue quintuple sono $$\begin{align*}&\langle q_{0},a,\square,q_{a},D\rangle,\langle q_{0},b,\square,q_{b},D\rangle\\
&\langle q_{a},a,a,q_{a},D\rangle,\langle q_{a},b,b,q_{a},D\rangle,\langle q_{b},a,a,q_{b},D\rangle,\langle q_{b},b,b,q_{b},D\rangle\\
&\langle q_{a},\square,\square,q_{a1},S\rangle,\langle q_{b},\square,\square,q_{b1},S\rangle\\
&\langle q_{2},a,a,q_{2},S\rangle,\langle q_{2},b,b,q_{2},S\rangle,\langle q_{2},\square,\square,q_{0},S\rangle\\
&\langle q_{0},\square,\square,q_{A},F\rangle
\end{align*}$$ allora $T_{PPAL}$ è completamente descritta dalla seguente parola $$\begin{align*}
&q_{0}-q_{A}-q_{R}\langle q_{0},a,\square,q_{a},D\rangle,\langle q_{0},b,\square,q_{b},D\rangle\langle q_{a},a,a,q_{a},D\rangle,\langle q_{a},b,b,q_{a},D\rangle,\langle q_{b},a,a,q_{b},D\rangle,\\
&\langle q_{b},b,b,q_{b},D\rangle\langle q_{a},\square,\square,q_{a1},S\rangle,\langle q_{b},\square,\square,q_{b1},S\rangle\langle q_{2},a,a,q_{2},S\rangle,\langle q_{2},b,b,q_{2},S\rangle,\\
&\langle q_{2},\square,\square,q_{0},S\rangle\langle q_{0},\square,\square,q_{A},F\rangle
\end{align*}$$
>>**Oss.**
>>L'insieme delle quintuple di $T_{PPAL}$ non è una funzione totale, infatti non è considerato il caso in cui la parola in input ha lunghezza dispari. In questo caso $T_{PPAL}(x)$ termina:
>>- nello stato $q_{a1}$ se $x$ è una parola palindroma di lunghezza dispari ed ha $a$ al centro 
>>- nello stato $q_{b1}$ se $x$ è una parola palindroma di lunghezza dispari ed ha $b$ al centro 
>>Possiamo completare $P$ aggiungendo le quintuple $$\langle q_{a1},\square,\square,q_{R},F\rangle,\langle q_{b1},\square,\square,q_{R},F\rangle$$
>>In questo modo, poiché vogliamo che $T_{PPAL}$ termini in $q_{A}$ solo se la parola scritta sul suo nastro ha lunghezza pari ed è palindroma, allora $T_{PPAL}$ rigetta le parole palindrome di lunghezza dispari

Da tutto quello che abbiamo visto sulle parole, possiamo quindi affermare che una *macchina di Turing è una parola* costituita dai caratteri dell'alfabeto $Q\cup\Sigma\cup\{-,\langle,\rangle,\square\}$.
Quindi, siccome è una parola, allora possiamo scriverla sul nastro di un'altra macchina di Turing, diciamo $U$, così che lavori sulla nostra macchina di Turing come input. 
Se sul nastro di $U$ ci scriviamo, oltre alla parola che descrive la nostra macchina di Turing di partenza (chiamiamola $T$), anche un input $x$ di $T$, allora $U$ *simula* la computazione di $T(x)$ e se chiamiamo $p_T$ la parola che descrive $T$, l'esito della computazione $U(p_{T},x)=T(x)$.
A cosa serve questo?
Pensiamo se riuscissimo a progettare una macchina di Turing $U$ che prende in input due parole: 
- $p_T$ che descrive una **qualsiasi** macchina di Turing $T$
- una parola $x$, input di $T$
e che riesce a simulare la computazione $T(x)$ per **qualunque macchina di Turing $T$**.
Secondo quest'idea $U$ sarebbe una macchina di Turing alla quale potrei comunicare un qualsiasi algoritmo e un input per quell'algoritmo e $U$ eseguirebbe l'algoritmo su quell'input. Quindi in sostanza la macchina di Turing $U$ è **l'algoritmo che descrive il comportamento di un calcolatore** e prende il nome di **macchina di Turing Universale**.

# La macchina di Turing Universale
Progettiamo la macchina di Turing Universale che, presi in input la descrizione di una macchina di Turing $T$ (ad un nastro) ed un input $x\in\{0,1\}^*$ di $T$, esegue una computazione con esito uguale a quello di $T(x)$. 
La macchina di Turing Universale $U$ è una macchina che utilizza 4 nastri a testine indipendenti (possiamo poi trasformarla grazie alle nozioni nella [[Lezione 3 - Equivalenza dei modelli delle macchine di Turing|lezione 3]]):
- $N_{1}$ il nastro su cui è memorizzata la descrizione di $T$
- $N_{2}$, il nastro di lavoro di $U$ su cui è memorizzato l'input $x$ della macchina di Turing $T$ che vogliamo simulare
- $N_{3}$ il nastro su cui, ad ogni istante della computazione che simula $T(x)$ sarà memorizzato lo stato attuale della macchina $T$
- $N_{4}$, il nastro su cui viene scritto lo stato di accettazione della macchina $T$

![[FI/MOD II/img/img7.png|center|500]]

Assumiamo che $T$ sia descritta dalla parola $\rho_{T}\in[Q_{t}\cup\{0,1,\oplus,\otimes,-\}]^*$ seguente $$\rho_{T}=\omega_{0}-\omega_{1}\otimes \omega_{1_{1}}-b_{1_{1}}-b_{1_{2}}-\omega_{1_{2}}-m_{1}\oplus\dots\oplus\omega_{h_{1}}-b_{h_{1}}-b_{h_{2}}-\omega_{h_{2}}-m_{h}\oplus$$
dove $\omega_{0}$ e $\omega_1$ indicano rispettivamente stato iniziale e di accettazione di $T$.

La macchina $U$ per simulare il comportamento della macchina $T$ esegue il seguente algoritmo

## Algoritmo di esecuzione della mdT Universale
1. Nello stato $q_{0}$, vengono copiati $\omega_0$ sul nastro $N_{3}$ e $\omega_{1}$ su $N_4$. La testina di $N_1$ viene spostata sul simbolo a destra del primo carattere $\otimes$ che incontra e la macchina entra nello stato $q_1$ $$\begin{align*}
&\langle q_{0},(x,a,\square,\square),(x,a,x,\square),q_{0},(d,f,f,f)\rangle &\forall x\in Q_{T}\land\forall a\in\{0,1,\square\}\\
&\langle q_{0},(-,a,x,\square),(-,a,x,\square),q_{0},(d,f,f,f)\rangle&\forall x\in Q_{T}\land\forall a\in\{0,1,\square\}\\
&\langle q_{0},(y,a,x,\square),(y,a,x,y),q_{0},(d,f,f,f)\rangle&\forall x,y\in Q_{T}\land\forall a\in\{0,1,\square\}\\
&\langle q_{0},(\otimes,a,x,y),(\otimes,a,x,y),q_{1},(d,f,f,f)\rangle&\forall x,y\in Q_{T}\land\forall a\in\{0,1,\square\}
\end{align*}$$
2. Nello stato $q_{1}$ inizia la ricerca di una quintupla su $N_1$ che abbia come primo simbolo lo stesso simbolo che si legge dalla testina su $N_{3}$ e come secondo simbolo lo stesso simbolo letto dalla testina di $N_{2}$ 
	- se nello stato $q_{1}$ legge lo stesso simbolo sui nastri $N_{1}$ e $N_{3}$, sposta la testina su $N_{1}$ a destra di due posizioni ed entra nello stato $q_\text{StatoCorretto}$ $$\begin{align*}
&\langle q_{1},(x,a,x,y),(x,a,x,y),q_{1},(d,f,f,f)\rangle&\forall x,y\in Q_{T}\land \forall a\in\{0,1,\square\}\\
&\langle q_{1},(-,a,x,y),(-,a,x,y),q_\text{StatoCorretto},(d,f,f,f)\rangle&\forall x,y\in Q_{T}\land \forall a\in\{0,1,\square\}\\
\end{align*}$$ Ora la testina $N_{1}$ è posizionata sul secondo elemento, ovvero il carattere letto della quintupla che si sta esaminando
		1. Se nello stato $q_\text{StatoCorretto}$ legge lo stesso simbolo sui nastri $N_{1}$ e $N_{2}$, allora ha trovato la quintupla da eseguire, quindi sposta $N_{1}$ a destra di due posizioni ed entra nello stato $q_\text{scrivi}$
		2. Se nello stato $q_\text{StatoCorretto}$ legge simboli diversi sui nastri $N_{1}$ e $N_{2}$, allora la quintupla che sta leggendo su $N_1$ non è quella corretta, quindi entra nello stato $q_{2}$ e sposta la testina su $N_{1}$ sul primo simbolo successivo al primo $\oplus$ che incontra, e se questo simbolo non è $\square$ entra in $q_{1}$, altrimenti entra nello stato di rigetto
	- se nello stato $q_{1}$ legge simboli differenti su $N_1$ e $N_3$ allora la quintupla che stiamo scandendo non è quella da eseguire (lo stato da cui parte è diverso), quindi entra nello stato $q_{3}$ e sposta la testina di $N_1$ sul primo simbolo successivo al primo $\oplus$ che incontra e se questo simbolo non è $\square$ entra nello stato $q_{1}$, altrimenti confronta lo stato attuale che legge su $N_3$ e lo confronta con lo stato di accettazione $\omega_{1}$ scritto su $N_4$ e se sono uguali entra nello stato di accettazione, altrimenti rigetta.
3. Nello stato $q_\text{scrivi}$ inizia l'esecuzione della quintupla che ha individuato sul nastro $N_1$ scrivendo il nuovo simbolo su $N_2$, che legge sul nastro $N_1$, poi entra nello stato $q_\text{CambiaStato}$ spostandosi a destra di due posizioni
4. Nello stato $q_\text{cambiaStato}$ prosegue l'esecuzione della quintupla individuata sul nastro $N_{1}$ modificando il contenuto del nastro $N_{3}$ scrivendoci lo stato che legge sul nastro $N_1$ ed entra nello stato $q_\text{muovi}$ muovendo due posizioni a destra la testina su $N_1$
5. Nello stato $q_\text{muovi}$ termina l'esecuzione della quintupla letta sul nastro $N_1$, eseguendo il movimento letto sul nastro $N_2$ e la macchina entra nello stato $q_\text{riavvolgi}$
6. Nello stato $q_\text{riavvolgi}$ viene riposizionata la testina di $N_1$ sul primo simbolo a destra del carattere $\otimes$ ed entra nello stato $q_1$eseguendo 

>**Oss.**
>La computazione $U(p_{T},x)$ rigetta ogni volta che $U$ non trova una quintupla da eseguire e lo stato scritto su $N_3$ non è uguale allo stato scritto su $N_4$, quindi $U$ rigetta il suo input $(p_T,x)$ senza verificare che la computazione $T(x)$ abbia rigettato. 

Osserviamo però che vogliamo una macchina $U$ che sappia simulare **qualsiasi** macchina di Turing $T$ e nella descrizione che abbiamo dato prima, la macchina utilizza l'insieme degli stati $Q_T$ come alfabeto della macchina $U$, ma ogni macchina $T$ ha un suo insieme degli stati $Q$ e un suo alfabeto $\Sigma$, quindi la prima cosa che potremmo pensare è che $U$ deve avere un alfabeto infinito, cosa non possibile in quanto sappiamo *che l'alfabeto di una macchina di Turing deve avere cardinalità costante*.

La soluzione è quella di codificare tutto in binario, quindi abbiamo l'alfabeto $\Sigma_{U}=\{0,1,\otimes,\oplus,-,f,s,d\}$ e definiamo $b^{Q}:Q\to\lceil\log|Q|\rceil$ una funzione che codifica in binario gli stati di $T$ utilizzando per ciascuno di essi $m=\lceil\log|Q|\rceil$ cifre e per ogni $\omega\in Q$ indichiamo con $b^{Q}(\omega)=b_1^{Q}(\omega)b_2^{Q}(\omega)\dots b_m^{Q}(\omega)$ la codifica di $\omega$.
Secondo questa soluzione, allora, la descrizione di $T$ è la parola $\beta_T\in\Sigma^*$ descritta in questo modo $$\beta_{T}=b^{Q}(w_{0})-b^{Q}(w_{1})\otimes b^{Q}(w_{1_1})-b_{1_{1}}-b_{1_{2}}-b^{Q}(\omega_{1_2})-m_{1}\oplus\dots\oplus$$
## Algoritmo di esecuzione raffinato

Nella descrizione dell'algoritmo precedente, bisogna quindi raffinare la descrizione delle operazioni, che nella descrizione precedente erano atomiche, mentre ora diventano operazioni da eseguire mediante cicli. 

1. A partire dallo stato $q_0$ vengono copiati gli $m$ caratteri della codifica $b^{Q}(\omega_0)$ sul nastro $N_3$ e gli $m$ caratteri della codifica $b_{^Q}(\omega_{1})$ sul nastro $N_4$ e le testine di $N_3$ ed $N_{4}$ vengono spostate a sinistra sul primo carattere scritto, mentre la testina di $N_1$ viene spostata sul simbolo a destra del simbolo $\otimes$ entrando nello stato $q_1$ $$\begin{align*}
&\langle q_{0},(x,a,\square,\square),(x,a,x,\square),q_{0},(d,f,d,f)\rangle&\forall x\in\{0,1\}\land\forall a\in\{0,1,\square\}\\
&\langle q_{0},(-,a,\square,\square),(-,a,\square,\square),q_{01},(d,f,f,f)\rangle&\forall a\in\{0,1,\square\}\\
&\langle q_{01},(y,a,\square,\square),(y,a,\square,y),q_{01},(d,f,f,d)\rangle&\forall y\in\{0,1\}\land\forall a\in\{0,1,\square\}\\
&\langle q_{01},(\otimes,a,\square,\square),(\otimes,a,\square,\square),q_{02},(d,f,s,s)\rangle&\forall a\in\{0,1,\square\}\\
&\langle q_{02},(b,a,x,y),(x,a,y,z),q_{02},(f,f,s,s)\rangle&\forall x,y\in\{0,1\}\land\forall a,b\in\{0,1,\square\}\\
&\langle q_{02},(b,a,\square,\square),(z,a,\square,\square),q_{1},(f,f,d,d)\rangle&\forall a,b\in\{0,1,\square\}
\end{align*}$$
2. Nello stato $q_{1}$ inizia la ricerca di una quintupla su $N_1$ che abbia come primo simbolo la parola scritta su $N_3$ e come secondo simbolo lo stesso simbolo letto dalla testina su $N_2$ 
	- se nello stato $q_{1}$ legge la stessa sequenza di simboli su $N_1$ e$N_3$ fino a quando non incontra il carattere "$-$" su $N_1$ e il carattere $\square$ su $N_3$, allora sposta la testina di $N_1$ a destra di due posizioni, la testina di $N_3$ a sinistra di $m$ posizioni ed entra nello stato $q_\text{StatoCorretto}$ 
$$\begin{align*}
&\langle q_{1},(x,a,x,y),(x,a,x,y),q_{1},(d,f,d,f)\rangle&\forall x,y\in\{0,1\}\land\forall a\in\{0,1,\square\}\\
&\langle q_{1},(-,a,\square,y),(-,a,\square,y),q_{11},(d,f,s,f)\rangle&\forall y\in\{0,1\}\land\forall a\in\{0,1,\square\}\\
&\langle q_{11},(b,a,x,y),(b,a,x,y),q_{11},(f,f,s,f)\rangle&\forall x,y\in\{0,1\}\land\forall a,b\in\{0,1,\square\}\\
&\langle q_{11},(b,a,\square,y),(b,a,\square,y),q_\text{statoCorretto},(f,f,d,f)\rangle&\forall y\in\{0,1\}\land\forall a,b\in\{0,1,\square\}
\end{align*}$$
		1. Se nello stato $q_\text{statoCorretto}$ legge lo stesso simbolo sui nastri $N_1$ e $N_2$, allora ha trovato la quintupla da eseguire, quindi sposta la testina di $N_1$ a destra di due posizioni ed entra in $q_\text{scrivi}$ 

$$\begin{align*}
&\langle q_\text{statoCorretto},(a,a,x,y),(a,a,x,y),q_\text{statoCorretto}^{'},(d,f,f,f) \rangle\\
&\langle q_\text{statoCorretto}^{'},(-,a,x,y),(-,a,x,y),q_\text{scrivi},(d,f,f,f) \rangle
\end{align*}$$
		2. Se nello stato $q_\text{statoCorretto}$ legge simboli differenti sui nastri $N_1$ e $N_2$, allora la quintupla che sta scandendo su $N_1$ non è quella corretta, quindi entra in $q_2$, sposta la testina di $N_1$ a destra fino a posizionarla sul primo simbolo successivo al carattere $\oplus$ e se tale simbolo è $0$ oppure $1$ allora entra in $q_1$, altrimenti rigetta 

$$\begin{align*}
&\langle q_\text{statoCorretto},(b,a,x,y),(b,a,x,y),q_{2},(d,f,f,f)\rangle&\forall x,y\in\{0,1\}\land\forall a,b\in\{0,1,\square\}:a\neq b\\
&\langle q_2,(z,a,x,y),(z,a,x,y),q_{2},(d,f,f,f)\rangle&\forall a\in\{0,1,\square\}\land\forall z\in\{0,1,-\} \\
&\langle q_2,(\oplus,a,x,y),(\oplus,a,x,y),q_{21},(d,f,f,f)\rangle&\forall x,y\in\{0,1\}\land\forall a\in\{0,1,\square\}\\
&\langle q_{21},(z,a,x,y),(z,a,x,y),q_{1},(f,f,f,f)\rangle&\forall x,y,z\in\{0,1\}\land\forall a\in\{0,1,\square\}\\
&\langle q_{21},(z,a,x,y),(z,a,x,y),q_{R},(f,f,f,f)\rangle&\forall a\in\{0,1,\square\}\land\forall z\not\in\{0,1\}

\end{align*}$$
	- se nello stato $q_{1}$ legge simboli differenti su $N_1$ e $N_3$, allora la quintupla su $N_1$ è quella sbagliata, quindi entra in $q_3$ e sposta la testina su $N_3$ a sinistra fino a posizionarla sul primo simbolo non $\square$, sposta la testina di $N_{1}$ a destra fino a posizionarla sul primo simbolo successivo a $\oplus$ e, se questo è $0$ oppure $1$, allora entra in $q_{1}$, altrimenti confronta lo stato attuale che sta leggendo su $N_3$ con lo stato di accettazione su $N_4$ e se sono uguali entra nello stato di accettazione, altrimenti rigetta
$$\begin{align*}
&\langle q_{1},(z,a,x,y),(z,a,x,y),q_{3},(f,f,s,f)\rangle&\forall x,y,z\in\{0,1\}:z\neq x\\
&\langle q_{3},(z,a,x,y),(z,a,x,y),q_{3},(f,f,s,f)\rangle&\forall x,y,z\in\{0,1\}\\
&\langle q_{3},(z,a,\square,y),(z,a,\square,y),q_{31},(f,f,d,f)\rangle&\forall y,z\in\{0,1\}\\
&\langle q_{31},(z,a,x,y),(z,a,x,y),q_{31},(d,f,f,f)\rangle &\forall x,y\in\{0,1\}\land\forall z\in\Sigma-\{\oplus\}\\
&\langle q_{31},(\oplus,a,x,y),(\oplus,a,x,y),q_{32},(d,f,f,f)\rangle&\forall x,y\in\{0,1\}\\
&\langle q_{32},(z,a,x,y),(z,a,x,y),q_{1},(f,f,f,f)\rangle&\forall x,y,z\in\{0,1\}\\
&\langle q_{32},(z,a,x,y),(z,a,x,y),q_{33},(f,f,f,f)\rangle&\forall x,y\in\{0,1\}\land\forall z\not\in\{0,1\}\\
&\langle q_{33},(z,a,x,x),(z,a,x,x),q_{33},(f,f,d,d)\rangle&\forall x\in\{0,1\}\land\forall z \in \Sigma\\
&\langle q_{33},(z,a,\square,\square),(z,a,\square,\square),q_{A},(f,f,f,f)\rangle&\forall x\in\{0,1\}\land\forall z \in \Sigma\\
&\langle q_{33},(z,a,x,y),(z,a,x,y),q_{R},(d,f,f,f)\rangle&\forall x,y\in\{0,1\}:x\neq y\land\forall z \in \Sigma
\end{align*}$$
3. Nello stato $q_\text{scrivi}$ si esegue la quintupla letta sul nastro $N_1$ scrivendo il nuovo simbolo su $N_2$ ed entra nello stato $q_\text{cambiaStato}$ 
$$\begin{align*}
\langle q_\text{scrivi},(b,a,x,y),(b,b,x,y),q_\text{scrivi}^{'},(d,f,f,f)\rangle\\
\langle q_\text{scrivi}^{'},(-,a,x,y),(b,b,x,y),q_\text{cambiaStato},(d,f,f,f)\rangle
\end{align*}$$
4. Nello stato $q_\text{cambiaStato}$ prosegue l'esecuzione della quintupla modificando il contenuto del nastro $N_3$ sovrascrivendo la sequenza di simboli che legge su $N_1$ fino a quando non incontra il carattere $-$ su $N_1$ e il carattere $\square$ su $N_3$, infine sposta a destra di una posizione (su uno dei caratteri $d,s,f$ che indicano lo spostamento della testina su $N_2$) sposta a sinistra la testina di $N_3$ fino a posizionarla a destra del primo $\square$ che incontra ed entra nello stato $q_\text{muovi}$
$$\begin{align*}
&\langle q_\text{cambiaStato},(z,a,x,y),(z,a,x,y),q_\text{cambiaStato},(d,f,d,f)\rangle\\
&\langle q_\text{cambiaStato},(-,a,\square,y),(-,a,\square,y),q_{4},(d,f,s,f)\rangle\\
&\langle q_{4},(z,a,x,y),(z,a,x,y),q_{4},(f,f,s,f)\rangle\\
&\langle q_{4},(z,a,\square,y),(z,a,\square,y),q_\text{muovi},(f,f,f,f)\rangle
\end{align*}$$
5. Nello stato $q_\text{muovi}$ termina l'esecuzione della quintupla che ha individuato su $N_1$ muovendo la testina del nastro $N_2$ ed entra nello stato $q_\text{riavvolgi}$ muovendo a sinistra la testina di $N_1$ 
$$\begin{align*}
&\langle q_\text{muovi},(s,a,x,y),(s,a,x,y),q_\text{riavvolgi},(f,s,f,f)\rangle\\
&\langle q_\text{muovi},(f,a,x,y),(f,a,x,y),q_\text{riavvolgi},(f,f,f,f)\rangle\\
&\langle q_\text{muovi},(d,a,x,y),(d,a,x,y),q_\text{riavvolgi},(f,d,f,f)\rangle
\end{align*}$$
6. Nello stato $q_\text{riavvolgi}$, riposiziona la testina di $N_1$ sul primo simbolo a destra del simbolo $\otimes$ e rientra nello stato $q_1$
$$\begin{align*}
&\langle q_\text{riavvolgi},(z,a,x,y),(z,a,x,y),q_\text{riavvolgi},(s,f,f,f)\rangle\\
&\langle q_\text{riavvolgi},(\otimes,a,x,y),(\otimes,a,x,y),q_1,(d,f,f,f)\rangle
\end{align*}$$
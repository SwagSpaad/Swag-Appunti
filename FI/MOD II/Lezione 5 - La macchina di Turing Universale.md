*****
Abbiamo visto che una macchina di Turing è un algoritmo, descritto nel linguaggio delle quintuple, che risolve un problema. L'input, per una macchina di Turing, è una *parola* costituita da caratteri di un certo alfabeto. 

Abbiamo detto nella [[Lezione 4 - Struttura di P e macchine non deterministiche|lezione precedente]] che è sufficiante conoscere l'insieme $P$ per sapere tutto di una macchina di Turing $T$, ma $P$ non ci dice proprio tutto, infatti dobbiamo conoscere quale sia lo stato iniziale e gli stati finali della macchina. 

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


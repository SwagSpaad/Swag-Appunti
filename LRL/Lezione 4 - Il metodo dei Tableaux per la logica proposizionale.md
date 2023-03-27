# Antipasto

Sia $\mathcal{F}$ la formula seguente:

$$((p \land q)\implies r)\implies(\lnot p \ \lor(q \implies r)), $$

$\mathcal {F}$ è una tautologia? Dalla lezione precedente sappiamo che per scoprirlo si utilizzano le tabelle di verità. Ma, per il momento accantoniamo le tabelle e concentriamoci su altro.

1. Prendiamo $\lnot \ \mathcal F$ 

$$\lnot [((p \land q ) \implies r) \implies (\lnot p \ \lor (q \implies r))]$$

Si osservi che questa formula è vera in qualche interpretazione $\iff$ $\mathcal F$ non è una tautologia. Quindi per vedere se $\mathcal F$ non è una tautologia possiamo dimenticarci di $\mathcal F$ e vedere se $\exists$ una intrpretazione che rende la formula vera.

2. La formula è del tipo $\lnot [\mathcal F_{1} \implies \mathcal F_2]$ , dove $\mathcal F_1$ è $(p \land q)\implies r$  e $\mathcal F_2$ è $\lnot p \lor (q \implies r).$ Quindi è vera $\iff$ $\mathcal F_1$ è vera e $\mathcal F_2$ è falsa, ovvero $\iff \mathcal F_1$ e $\lnot \mathcal F_2$ sono entrambe vere. La formula quindi è vera $\iff$ sono vere entrambe le formule riportate sotto.

![[LRL/img/img5.png|center|500]]

Quindi per vedere se $\exists$ un'interpretazione che rende la prima formula vera, possiamo dimenticarci della formula stessa e vedere se $\exists$ un'interpretazione che rende vere le formule 2,3

3. Consideriamo la formula 2: è un'implicazione, quindi è vera solo quando $(p\land q)$ è falso oppure quando $r$ è vero. In altre parole, quando almeno una è vera, fra le due formule quì sotto:

![[LRL/img/img6.png|center|500]]

Si osservi che le ultime 2 formule non sono state posizionate una sotto l'altra come nei casi precedenti, ma si sono creati due rami. Questo perchè per vedere se $\exists$ una interpretazione che rende vere sia la seconda formula che la terza, possiamo dimenticarci della seconda e vedere se $\exists$ una interpretazione che rende vera la terza formula e almeno una fra la quarta e la quinta

4. Si consideri la terza: è la negazine di un OR, quindi è vera $\iff$ $\lnot p$ e $(q\implies r)$ sono entrambe false, in altre parole, quando sia la sesta ch la settima sono vere

![[LRL/img/img7.png|center|500]]

Si osservi che la sesta e la settima le abbiamo messe una sotto l'altra e ripetute sotto entrambi i rami. Infatti, per vedere se $\exists$ una interpretazione che rende vera la terza e almeno una fra la quarta e la 5, possiamo dimenticarci della terza e vedere se $\exists$ una interpretazione che rende vere la quarta, la sesta e la settima, oppure la quinta, la sesta e la settima.

5. Andiamo alla quarta: è la negazione  di un AND, quindi è vera quando almeno una delle due fra p e q è falsa, ossia quando l'ottava e vera oppure la nona è vera

![[LRL/img/img8.png|center|500]]

Per vedere se $\exists$ una interpretazione che rende vere la quarta, la sesta e la settima perciò possiamo dimenticarci della quarta e vedere se $\exists$ una interpretazione che rende vere la sesta, la settima e l'ottava, oppure la sesta, la settima e la nona. Tuttavia, una interpretazione che renda vere la sesta, la settima e l'ottava $\nexists$, perchè per rendere vera la sesta la variabile p dovrebbe essere T mentre per rendere vera l'ottava, dovrebbe essere F. Diciamo che quel ramo è chiuso e nello schema indicato questo fatto con una X. Per vedere se $\exists$ un'interpretazione che rende vere la sesta, la settima e la nona dobbiamo ancora procedere fino a scomporre la settima.

6. Per quanto riguarda la quinta e la sesta, sono già delle varibili quindi non c'è più niente da scomporre. Ci resta da considerare la settima formula: è la negazione di un'implicazione, quindi..

![[LRL/img/img9.png|center|500]]

Anche il secondo e il terzo ramo si chiudono : il secondo perchè $\nexists$ una interpretazione che renda vere la nona e la decima formula, il terzo perchè $\nexists$ una interpretazione che renda vere sia la quinta che l'undicesima formula.

Non c'è più niente da scomporre e abbiamo un albero con tutti i rami chiusi. Che cosa significa? Torniamo un attimo indietro e vediamo di ripercorrere quello che abbiamo fatto con riferimento al nostro schema di formule:

![[LRL/img/img10.png|center|500]]
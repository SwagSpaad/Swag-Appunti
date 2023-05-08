----
Con questa lezione iniziamo la parte del corso in cui vedremo come la logica proposizionale può essere implementata con dei circuiti per ottenere funzioni più sofisticate.
Tutte le informazioni che devono passare per i circuiti di un computer devono essere codificate in **binario**.

# Rappresentazione dei numeri naturali
I numeri possono essere rappresentati in diversi modi, ad esempio **sette, seven, 7 ed 111** sono sequenze di simboli che esprimono lo stesso concetto con codifiche diverse.
Le codifiche posizionali, come quella decimale e binaria seguono lo stesso schema: si fissa una base $b \in \mathbb N$, con $b \geq 2$ e un insieme $B$ di $b$ simboli, ognuno dei quali rappresenta un numero compreso fra $0$ e $b-1$, una sequenza di simboli $x_{k-1}x_{k-2}...x_{1}x_{0}$, dove ognuno degli $x_{i}$ è un simbolo in $B$, rappresenta il numero 
$$x_{k-1}b^{k}+x_{k-2}b^{k-2}+...+x_{1}b+x_{0}$$ 
Scriveremo $(x_{k-1}x_{k-2}...x_{1}x_{0})_b$ per indicare che la sequenza di simboli $x_{k-1}x_{k-2}...x_{1}x_{0}$, rappresenta il numero definito sopra.
I componenti elementari di un computer sono progettati per distinguere la differenza di potenziale agli estremi di un conduttore, e il modo più semplice per farlo è distinguere i 2 valori *alto* e *basso*.

**Es.**
Data una sequenza di simboli che esprime un numero in binario $(10110)_2$, per capire di che numero in base 10 si tratta applichiamo la formula sopra: 
$$(10110)_2=1\cdot2^4+0\cdot2^3+1\cdot2^2+1\cdot2^1+0\cdot2^0=2^4+2^2+2=(22)_{10}$$
Se volessimo scrivere in binario il numero $(234)_{10}$ dobbiamo trovare la sequenza di bit $x_{k-1}...x_{0}$ tali che $$234 = x_{k-1}2^{k-1}+x_{k-2}2^{k-2}+...+x_{1}2+x_{0}$$
Per farlo, basta dividere il numero ogni volta per 2, ed in caso di resto = 1 porre il bit corrispondente ad uno, altrimenti 0.
Schematiziamo il concetto in questo modo:

![[LRL/img/img27.png|center|150]]

Il corrispondente numero in binario del numero $(234)_{10}$ è $(11101010)_{2}$.

La somma dei numeri in binario si svolge nello stesso modo della somma in decimale. Per esempio la somma tra $(3)_{10}=(11)_{2}$ e $(5)_{10}=(101)_{2}$ sarà

![[LRL/img/img28.png|center|100]]

che chiaramente risulta $(1000)_{2}=(8)_{10}$.
Quando lavoriamo con i numeri in binario in informatica, abbiamo bisogno di fissare il numero di bit che stiamo dedicando alla codifica dei numeri (tipicamente nei registri del computer le unità di dati sono sequenze di 32 o 64 bit). Per esempio se stiamo lavorando a 4 bit, i numeri che possiamo rappresentare sono quelli che vanno da $(0)_{10}$ a $(15)_{10}$. In questo caso, se la somma tra due numeri il cui risultato è maggiore di 15, diciamo che la somma va in *overflow*. In generale con k bit in binario possiamo codificare numeri che vanno da $0$ a $2^k-1$.

# I parenti del sistema binario
Un numero espresso in binario diventa una sequenza di zeri ed uni, che può essere conveniente scrivere in altri modi.
Nella codifica **esadecimale** si utilizzano 16 simboli: le cifre da 0 a 9 e le prime lettere dell'alfabeto A, B, C, D, E, F per indicare i numeri da dieci a quindici.
La sequenza esadecimale $(A2C)_{16}$ rappresenta il numero $(2602)_{10}$, infatti $$(A2C)_{16}=A\cdot16^{2}+2\cdot16^{1}+C\cdot16^{0}=10\cdot256+2\cdot16+12\cdot1=(2602)_{16}$$
Siccome sedici è una potenza di 2, per passare da esadecimale a binario è sufficiente convertire in binario ogni singolo carattere esadecimale usando quattro bit e poi concatenarli. Per esempio siccome $(A)_{16}=(1010)_{2}$, $(2)_{16}=(0010)_{2}$ e $(C)_{16}=(1100)_{2}$, il numero $(A2C)_{16}$ in binario si scriverà
$$101000101100$$

# Codifica in complemento a due
Nei paragrafi precedenti abbiamo visto come scrivere in binario i numeri naturali e abbiamo osservato che se abbiamo a disposizione $k$ bit possiamo codificare i numeri che vanno da $0$ a $2^{k}-1$. Per esempio con $k=4$ tutte le sequenze di 4 bit codificano in binario i numeri da 0 a 15. Come facciamo a codificare i numeri negativi?
La prima idea potrebbe essere quella di usare un bit per il segno, quindi per esempio con 4 bit scriveremmo il numero $(2)_{10}=(0010)_{2}$ e il numero $(-2)_10=(1010)_{2}$. Questa codifica è legittima, ma presenta qualche caratteristica che la rende inefficace. Per esempio lo zero ha due codifiche differenti: 1000 e 0000, ma soprattutto la somma fra due numeri non si può più fare nel modo che abbiamo visto. Per esempio la somma di $(2)_{10}$ e $(-2)_{10}$ deve fare 0, ma se provo a farla in questa codifica ottengo

![[LRL/img/img29.png|center|100]]

che rappresenta il numero $(-4)_{10}$. Una codifica molto conveniente per rappresentare i numeri interi positivi e negativi è quella in **complemento a due**.

**Def.** (codifica in complemento a due a $k$ bit)
Fissato il numero $k$ di bit che abbiamo a disposizione, nella codifica in complemento a due la sequenza di bit $x_{k-1}x_{k-2}...x_{1}x_{0}$ rappresenta il numero 
$$-x_{k-1}2^{k-1}+x_{k-2}2^{k-2}+...+x_{1}2+x_{0}$$
Per indicare la codifica in complemento a due utilizzaremo $\bar2$ come pedice.

**Es.**
Il numero $(-2)_{10}$ in complemento a due a quattro bit si scrive $1110$, infatti 
$$(1110)_{\bar2}=-2^3+2^2+2^1+0\cdot2^0=-8+4+2=(-2)_{10}$$
I numeri interi positivi si scrivono in complemento a due esattamente come si scrivono in binario, ma come si scrivono i numeri negativi? 

**Es.**
Voglio trovare la codifica in complemento a due a quattro bit di $(-5)_{10}$. Per prima cosa scrivo la codifica di $(5)_{10}=(0101)_{\bar2}$, poi inverto tutti i bit $1010$, infine aggiungo 1 ed ottengo 1011.

Per sommare i numeri in complemento a due si può utilizzare il metodo già visto in precedenza, per esempio in complemento a due a quattro bit abbiamo che $(-5)_{10}=(1011)_{\bar2},\:(7)_{10}=(0111)_{\bar2}$ e se faccio la somma ottengo 

![[LRL/img/img30.png|center|100]]

e $(0010)_{\bar2}=(2)_{10}$ che è il risultato corretto.

**Oss.**
Nella somma sopra è stato trascurato il bit di riporto che sarebbe stato il quinto bit e il risultato è comunque corretto. Infatti in complemento a due a $k$ bit la somma non va in overflow quando c'è un riporto non nullo per il quale avrei bisogno di $k+1$ bit, ma quando sommo due numeri positivi la cui somma è maggiore di $2^{k-1}-1$ oppure quando sommo due numeri negativi la cui somma è minore di $-2^{k-1}$.

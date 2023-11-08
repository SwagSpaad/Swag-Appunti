----
# Porte Logiche
Quando si parla di circuiti si usa una notazione diversa per esprimere gli stessi concetti che abbiamo utilizzato nella logica proprosizionale: per esempio indichiamo con 0 e 1, False e True, rispettivamente.

![[LRL/img/img31.png|center|200]]

Quando si parla di circuiti non si utilizza mai il simbolo $\implies$ ma possiamo esprimere p $\implies$q come $\bar{p}+q$ .
in logica generalmente non si usa un simbolo specifico per lo XOR, mentre quando si parla di circuiti si usa a tale scopo $p\oplus q$ .

Si hanno a disposizione delle porte logiche, che implementeranno le operazioni logiche elementari, AND, OR e NOT, senza preoccuparci della loro composizione elettrotecnica.

![[LRL/img/img32.png|center|400]]

Data una formula $\mathcal F$ della logica proposizionale, possiamo sempre costruire un circuito che implementi  $\mathcal F$ usando porte logiche elementari. Per esempio, la formula $x_{0}\implies(x_{1}\land x_2)$ è equivalente alla formula $\bar x_{0}\lor(x_{1}\land x_2)$, quindi un circuito che la implementa è 

![[LRL/img/img33.png|center|300]]

In aggiunta alle porte logiche elementari, possiamo assumere di avere porte logiche che implementano operazioni binarie più comuni, per esempio

![[LRL/img/img34.png|center|400]]

# Operazioni Aritmetiche

Si possono utilizzare le porte logiche per creare circuiti che eseguono operazioni aritmetiche.

**Esempio**
Costruiamo un circuito con tre input a, b, c e due output s e $c_{out}$ con le seguenti tabelle di verità 

![[LRL/img/img35.png|center|250]]

La tabella di s è uno XOR mentre quella di c è un AND, quindi possiamo disegnare il circuito così

![[LRL/img/img36.png|center|250]]

In questo circuito l'output s è proprio la somma dei due bit di input, mentre l'output c'è il riporto. Un tale circuito si chiama Half Adder.
Ricordiamo come facciamo la somma di due numeri espressi in binario.
Quando facciamo la somma bit a bit su ogni colonna dobbiamo avere la possibilità di sommare tre bit, ovvero i due bit di $a$ e $b$ più un eventuale riporto proveniente dalla colonna $c_{out}$. L'half adder non è sufficiente per questo, ma per ottenere questo risultato abbiamo bisogno di un FULL ADDER che ha la tabella di verità qui sotto.

![[LRL/img/img99.png|center|200]]
 
# Forme normali e circuiti

Se si ha una formula in forma normale è immediato ricavare un circuito e disegnarlo in un modo "standard" . Per esempio, data la seguente tabella di verità

![[LRL/img/img37.png|center|200]]

si può scrivere una formula in forma normale somma di prodotti $$y = x_{0}\bar x_{1} \bar x_{2} +x_{0} x_{1} \bar x_{2} + x_{0} x_{1} x_{2}$$ 
e disegnare un circuito che la implementa

![[LRL/img/img38.png|center|250]]



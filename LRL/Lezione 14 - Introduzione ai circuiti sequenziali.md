----
Tutti i circuiti visti nelle lezioni precedenti sono aciclici, ovvero non c'è mai un percorso che dall'output di una porta logica P torna in input alla stessa porta. Questi circuiti si chiamano **circuiti combinatori**. In questa lezione vedremo che si possono costruire dei circuiti contenenti dei cicli.

# Antipasto
Cosa succede se costruissimo un circuito in questo modo?

![[LRL/img/img58.png|center|200]]

Sembra un "paradosso logico", infatti se l'input della porta NOT è 1 deve anche essere 0 e viceversa. E se provassimo a costruirlo in questo modo?

![[LRL/img/img59.png|center|250]]

Non appena diamo corrente all'input $x$, qualcosa di brutto accadrà alla porta NOT. Tuttavia, costruire un circuito che contiene un ciclo non sempre genera un cortocircuito, come nell'esempio qui sotto: 

![[LRL/img/img60.png|center|250]]

Anche se contiene un ciclo, le due porte NOT non hanno problemi. Ovviamente il circuito fa ben poco, perché l'output $y$ sarà uguale all'input $x$. Chiamiamolo elemento **bistabile** e osserviamo che possiamo disegnarlo in modo differente e descrivere l'output in funzione dell'input con una tabella di verità.

![[LRL/img/img61.png|center|400]]

Cosa succede se proviamo ad utilizzare lo stesso schema utilizzando porte a due ingressi, come ad esempio delle porte OR in cui l'output di una delle due porte entra in input nell'altra porta

![[LRL/img/img62.png|center|300]]

Siccome l'output di una porta OR è 1 quando almeno uno dei due ingressi è 1, non c'è nessun dubbio su quali siano i valori degli output $y_0$ e $y_1$ quando almeno uno dei due ingressi è 1: per esempio se $x_{0}=1$ allora $y_{0}=1$ e quindi anche $y_{1}$ sarà 1. Ma cosa possiamo dire sui bit in output $(y_{0},y_{1})$, quando i bit in input sono entrambi zero, $(x_{0},x_{1})=(0,\: 0)$.

![[LRL/img/img63.png|center|200]]

Se immaginiamo che il circuito parta da una configurazione iniziale in cui sia gli input che gli output sono 0, allora non appena poniamo a 1 uno dei due bit in input entrambi gli output andranno ad 1 e da lì in poi rimarranno sempre $(y_{0}, y_{1})=(1,\:1)$.
Come per l'elemento bistabile quindi anche in questo circuito, nonostante ci siano dei cicli, non creiamo nessun "paradosso logico" assegnando dei valori in input. Anche questo circuito però non fa nulla di interessante: manovrando gli input possiamo soltanto spostare una volta i valori di output da 0 a 1, che poi rimarranno fissi a 1 indipendentemente da come riassegnamo gli input.

# SR-Latch e D-Latch
Se costruiamo un circuito come abbiamo fatto prima, ma utilizzando porte NOR (o NAND), non otteniamo paradossi logici e succede anche qualcosa di interessante.
Consideriamo questo circuito con la tabella di verità della porta NOR:

![[LRL/img/img64.png|center|300]]

![[LRL/img/img65.png|center|200]]

Come sarà la tabella di verità del circuito?

![[LRL/img/img67.png|center|250]]

Con questo circuito quindi possiamo, per esempio, impostare per un attimo gli input a $(x_{0}, x_{1})=(1,\:0)$ e così settare il bit in output $y_{1}=1$ e il bit $y_{0}= 0$, riportare gli input a $(x_{0},x_{1})= (0,0)$ e $y_{1}$, rimarrà a 1.
Inoltre, impostando per un attimo gli input a $(x_{0}, x_{1})=(0,1)$ possiamo resettare il bit $y_{1} = 0$.
Osservate che abbiamo  appena costruito una memoria: un circuito in grado di fissare un bit in output $yi$ a 0 o 1, a seconda dei valori assegnati agli input $(x_{0}, x_{1})$, e ricordare il bit assegnato a $y_{1}$ quando entrambi gli input tornano ad avere valore .
Il circuito costruito si chiama SR - Latch: gli input si chiamano S e R (Set e Reset) e gli output $Q \ e \ \bar Q$, e il simbolo con cui si indica il circuito è quello in Figura (1).

Osservate che chiamare gli output di un SR - Latch $Q  \ e \ \bar Q$ non è propriamente corretto, perchè se gli input R e S sono entrambi 1 e gli output sono entrambi zero. Inoltre impostare entrambi gli input a 1 crea anche un altro problema.

![[LRL/img/img68.png|center|400]]

Si deve assolutamente eliminare la possibilità che gli input siano entrambi 1



----
# Addizionatore / Sottrattore a propagazione di riporto

Quando abbiamo iniziato a parlare di circuiti abbiamo visto come sia semplice, usando solo porte logiche elementari, costruire un circuito, il FullAdder, che faccia la somma di 3 bit restituendo in output  un bit che indica la somma e uno che indica il riporto.

![[LRL/img/img91.png|center|400]]

Mettendo in serie k FullAdder, con $k \in \mathbb N$, otteniamo quindi un circuito che fa la somma in binario di numeri a k bit.

![[LRL/img/img92.png|center|400]]

Se si interpreta il bit $C_{out}$ come il quinto bit della somma, allora il circuito in Figura (2) calcola correttamente la somma di qualunque coppia di numeri in binario a quattro bit $(a_{3},a_{2},a_{1},a_{0}), (b_{3},b_{2},b_{1},b_{0})$ restituendo di fatto un numero a cinque bit  $(C_{out},s_{3},s_{2},s_{1},s_{0})$.

Interpretando le sequenze di bit come numeriin completamento a due, con una piccola modifica al circuito possiamo fare in modo che lo stesso circuito calcoli sia la somma che la sottrazione fra numeri in complemento a due a k bit, a seconda che il bit $C_{in}$ sia 0 oppure 1

![[LRL/img/img93.png|center|400]]

I circuiti Adder e Adder/Subtractor costruiti così si dicono a propagazione di riporto.
Osservate infatti, per esempio, che il FullAdder FA3 in figura(3), per calcolare $S_{3}$ deve ricevere il riporto in uscita da FA2 che, per calcolarlo, a sua volta deve ricevere il riporto in uscita da FA1, che deve ricevere il riporto in uscita FA0.

Se la propagazione del segnale fosse istantanea non ci sarebbe nessun problema, ma siccome i segnali non possono viaggiare più veloce della luce, questo significa che in un Adder/Subtractor a 64 bit il segnale proveniente da $C_{in}$ impiegherebbe, per arrivare all'ultimo FullAdder, il doppio del tempo di quanto ne impiegherebbe in uno a 32 bit. 
Se il circuito Adder/Subtractor è un componente di un circuito sequenziale, passare da 32 a 64 bit quindi potrebbe costringerci a dover dimezzare la velocità del clock.

# Addizionatore/Sottrattore ad anticipo di riporto
Si consideri un addizionatore/sottrattore a 16 bit e immaginiamolo diviso in 4 addizionatori a 4 bit

![[LRL/img/img94.png|center|400]]

Chiamiamo $C_i$ il riporto in ingresso dell'$i$-esimo FullAdder.
Per migliorare l'efficienza del circuito.
Per migliorare l'efficienza del circuito, in temrini di velocità di propagazione del segnale da $C_{0}$ fino a $C_{16}$ , possiamo provare a fare in modo di precomputare in ogni blocco quattro bit qualcosa che ci consenta di non dover far passare i bit dei vari riporti in ognuno dei 16 FullAdder. Vediamo.
Si consideri il generico FullAdder $i$-esimo, per $i=0,...,15$ e osserviamo che:
- Se $a_{i}b_{i}=1$ allora il FullAdder genera il bit di riporto $C_{i+1}=1$ indipendentemente se il suo riporto in entrata $C_{i}$ sia 0 o 1;
- Se $a_{i}\oplus b_{i}=1$ allora il FullAdder propaga il bit di riporto: $C_{i+1}=C_i$ 

Quindi possiamo scrivere $C_{i+1}=a_{i}b_{i}+(a_{i}\oplus b_{i})C_{i}$ .

Se per comodità chiamiamo $g_{i}= a_{i}b_{i}$ e $p_{i}= a_{i}+b_{i}$ , dall'esercizio precedente abbiamo che deve valere la relazione di ricorrenza $$c_{i+1}= g_{i}+p_{i}c_{i}$$
Applicando la relazione precedente con $i= 7$ abbiamo, per esempio, che il riporto in uscita dal secondo adder a 4-bit è $C_{s}= g_{7}+ p_{i}c_{7}$ e applicandola ricorsivamente con $i=6$
abbiamo che:$$\begin{align} C_{8}&= g_{7}+p_{7}c_{7} \\
&=g_{7}+ p_{7}(g_{6}+ p_{6}C_{6}) \\ 
&= g_{7}+p_{7}g_{6}+p_{7}p_{6}C_{6}\end{align}$$
Una volta precomputati A e B, nel momento jin cui arriva il riporto in entrata $c_{4}$ l'adder a 4-bit può calcolare il riporto $c_{8}$ in uscita usando solo una porta AND e una porta OR

![[LRL/img/img95.png|center|400]]

In questo modo, quando arriva il riporto in entrata $c_{4}$, che è necessario ai quattro FullAdder contenuti nell'Adder 1 per calcolare $(s_{7},s_{6},s_{5},s_{4})$, l'Adder 1 può anticipare il riporto $c_{8}$ all'Adder 3, che a sua volta lo userà per calcolare $(s_{11},s_{10},s_{9},s_{8})$ e per anticipare $c_{12}$ all'Adder 3.

La procedura vista in questa sezione per costruire un adder a 16 bit precomputato in ogni blocco a 4 bit ciò che serve per propagare il riporto all'adder a quattro bit successivo si può iterare: per esempio, per costruire un Adder a 64 bit si può dividerlo in blocchi da 16 bit e precomputare ciò che serve  per fare in modo che il riporto si propaghi da un blocco al successivo senza dover passare attraverso tutti i FullAdder. Un circuito Adder/Subtractor costruito in questo modo si chiama ad anticipo di riporto.
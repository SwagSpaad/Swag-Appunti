----
# Un primo tentativo
Sappiamo che le sequanze distinte di $n$ bit sono $2^n$, quindi ognuna di queste potrà rappresentare uno di $2^n$ "oggetti" diversi.  Abbiamo visto che se gli "oggetti" in questione sono i numeri interi senza segno, con la codifica binaria standard usiamo quelle sequenze di bit per indicare i numeri da 0 a $2^{n}-1$. Se gli oggetti che vogliamo rappresentare sono i numeri interi positivi e negativi, allora con la codifica in complemento a due a n bit indichiamo i numeri interi da $-2^{n-1}$ a $2^{n-1}-1$ .

Per codificare i numeri frazionari, una prima idea potrebbe essere quella di usare una parte di bit per la parte intera e una parte dei bit per la parte frazionaria. Per esempio, se abbiamo $n=8$ bit e ne usiamo 4 per la parte intera e 4 per la parte frazionaria, allora la sequenza $01101100$ rappresenterebbe il numero
$$0\cdot2^{3}+ 1 \cdot 2^{2}+ 1 \cdot 2^{1}+ 0 \cdot 2^{0}+ 1 \cdot 2^{-1} + 1 \cdot 2^{-2}+ 0 \cdot 2^{-3}+ 0 \cdot 2^{-4}= (6.75)_{10}$$
Questo tipo di codifica dei numeri frazionari si chiama a virgola fissa.
Si può anche usare la codifica a virgola fissa in complemento a due per codificare i numeri frazionari positivi e negativi.

L'utilizzo della codifica in virgola fissa non è in genere molto conveniente, perchè non ci permette di lavorare con numeri molto grandi e numeri molto piccoli.
Un modo più saggio di usare i bit che abbiamo a disposizione viene dall'utilizzo della notazione scientifica: osservate che, se dobbiamo scrivere in decimale numeri come novecentoottantasette miliardi o sessantaquattro miliardesimi, tipicamente non li scriveremo così: $$987000000000 \ \ e \ \ 0.000000064$$
ma così:$$9.86 \cdot 10^{11} \ \ e \ \ 6.4 \cdot 10^{-8}$$ Ovviamente ci sono più modi per scrivere lo stesso numero in notazione scientifica. Quando si usa un'unica cifra prima della virgola, si dice che la notazione scientifica è normalizzata. 
Anche in binario possiamo fare la stessa cosa. Per esempio, possiamo scrivere il numero $(6.75)_{10}$ in notazione scientifica così: $$1.1011 \cdot 2^2$$ Se abbiamo a disposizione $n$ bit quindi, invece che usarne alcuni per la parte intera e alcuni per quella frazionaria, possiamo decidere di usarne alcuni per l'esponente e alcuni per la mantissa.
Nel paragrafo successivo vedremo quale standard definisce l'utilizzo dei bit che abbiamo a disposizione.

# Lo standard IEEE754
Lo standard IEEE-754 per i numeri in virgola mobile a precisione singola prevede 32 bit:
- Il primo bit per il segno (0 positivo, 1 negativo);
- I successivi 8 bit per l'esponente, espresso in codifica ad eccesso;
- I successivi 23 bit per la mantissa, di cui non si memorizza l'uno più significativo.

![[LRL/img/img96.png|center|400]]

Supponiamo di dover codificare il numero $-76,28125$. È un numero negativo, quindi il primo bit sarà $1$. La parte intera è $76 = 64+8+4= 2^{6}+2^{3}+2^2$ quindi, in binario $1001100$. La parte frazionaria è $0,28125 = 2^{-2}+2^{-5}$ quindi, in binario $0.01001$. Il valore assoluto del nostro numero in binario perciò è: $1001100.01001 = 1.00110001001 \times 2^6$. Perciò la mantissa escluso l'uno più significativo è $00110001001$, a cui andremo ad aggiungere tutti gli zeri che servono per arrivare a 23 bit. L'esponente è 6, che in codifica ad eccesso diventa $6+127 = 133$, ossia $10000101$ in binario a otto bit.
Complessivamente quindi la codifica del nostro numero $-76,28125$ sarà

![[LRL/img/img97.png|center|400]]

che espresso in esadecimale diventa $1100 \ 0010 \ 1001 \ 1000 \ 0000 \ 0000 \ 0000 = C2989000$. 

Alcune sequenze di 32 bit sono riservate a codificare numeri speciali:

![[LRL/img/img98.png|center|400]]

Lo standard IEEE754 definisce anche una codifica a 64 bit per i numeri in virgola mobile.

# Cenni alla codifica dei caratteri: ASCII, Unicode, UTF-8
Oltre ai numeri interi e frazionari, abbiamo bisogno di codificare in binario anche tutti gli altri caratteri. ASCII usa 7 bit per codificare 128 caratteri. 
Unicode associa ad ogni carattere un numero compreso fra $0$ e $1114111$ per un totale di $2^{16}+2^{20}$ caratteri utilizzabili, e può essere implementato con diverse codifiche.
Le codifiche più utilizzate per implementare Unicode sono le UTF: UTF-8, UTF-16, UTF-32. La UTF-32 è una codifica a lunghezza fissa che usa 32 bit per rappresentare in binario ognuno dei numeri dell'intervallo utilizzato da Unicode. 
Quella più diffusa tuttavia è la codifica UTF-8, che è una codifica  a lunghezza variabile. I 128 caratteri del codice ASCII vengono codificati in UTF-8 con 8 bit: il primo bit a 0 e gli stessi 7 bit utilizzati dal codice ASCII 

$$0-------$$ 
Quindi UTF-8 è retrocompatibile con ASCII: ogni sequenza di bit che si può decodificare con ASCII, si può decodificare allo stesso modo anche con UTF-8. Per i caratteri codificati con due, tre, quattro byte, il primo byte comincia  con una sequenza di $1$ lunga quanto il numero di byte utilizzati per codificare il carattere, gli altri byte cominciano con $10$, i bit che restano sono utilizzati per la codifica binaria del numero che Unicode associa al carattere da codificare 
$$ \begin{align} &2 \ byte \ \ 1 \ 1 \ 0 ----- \ \ 1 \ 0 ------\\ 
& 3 \ byte \ \ 1 \ 1 \ 1 \ 0 ---- \ \ \ \  1 \ 0------  \ \ \ \ 1 \ 0 ------\\ 
& 4 \ byte \ \ 1 \ 1 \ 1 \ 1 \ 0--- \ \ \ \ 1 \ 0 ------ \ \ \ \ 1 \ 0------ \ \ \ \ 1 \ 0------\end{align}$$

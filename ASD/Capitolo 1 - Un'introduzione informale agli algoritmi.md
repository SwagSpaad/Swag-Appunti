# Capitolo 1
## Un'introduzione informale agli algoritmi
---
## Esempio di problemi e soluzione algoritimi: i numeri di Fibonacci  
Esempio dell'isola dei conigli:  
>Partendo da una coppia di conigli, quante coppie si avrebbero nell'anno **n**?

Regole di riproduzione:  
- Una coppia di conigli concepisce  2 coniglietti di sesso diverso ogni anno che andranno a formare una nuova coppia.  
- La gestazione dura un anno.  
- I conigli cominciano a riprodursi al secondo anno dopo la loro nascita.  
- I conigli sono immortali.  
![[ASD/img/img0.png]]  
Nell'anno **n** ci sono tutte le coppie dell'anno precedente più una nuova coppia di conigli per ogni coppia presente 2 anni prima.

-------aggiungere resto pag 6-10-------
### Algoritmo fibonacci2
Utilizza la definizione ricorsiva:  
**algoritmo** fibonacci2(intero n) $\rightarrow$ intero
	if (n $\leq$ 2) then return 1
	else return fibonacci2(n-1) + fibonacci2(n-2)
#### Tempo di esecuzione
Ogni linea di codice costa un'unità di tempo

T(n) = numero di linee di codice eseguite dall'algoritmo su un numero in input n
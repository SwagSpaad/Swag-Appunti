# Lezione 5
## Cambiare 
---
## Operazione sui processi 
Nei SO, i processi sono eseguiti concorrentamente e sono creati e terminati dinamicamente. Tuttavia, in alcuni sistemi si possono avere applicazioni nelle quali il numero dei processi è definito inizialmente e non è più modificato durante il tempo di vita dell'applicazione. Questa [[Lezione 4 - Gestione dei processi|gestione dei processi]] può essere implementata in sistemi in tempo reale, ad esempio per il controllo di impianti fisici, in cui tutti i processi sono creati all'avvio dell'applicazione (creazione statica). 

### Creazione e terminazione dei processi 
Durante la sua esecuzione un processo (detto **padre**), può creare altri processi (detti **figli**) utilizzando delle chiamate di sistema fornite dal kernel. Un processo è univocamente indentificato da un numero detto **PID (Process Identifier)**.  
Quando un processo crea nuovi processi, esistono due possibilità per l’esecuzione del padre. La prima prevede che esso continui la sua esecuzione concorretemene con i suoi figli; la seconda che si sospenda fino a che alcuni o tutti i suoi figli siano terminati.  
Il kernel offre System Call (SC) per la creazione e terminazione dei processi. La SC di creazione dovrà inizializzare il descrittore del processo ed inserirlo nella coda dei processi pronti. Analogamente, la funzione di terminazione provocherà l’eliminazione del descrittore dalla tabella dei descrittori di processo e la notifica che l'area di memoria può essere recuperata dal sistema operativo.  

### Creazione dei processi POSIX 
**POSIX (Portable Operating System Interface for Unix)**, indica la famiglia degli standard definiti dall'IEEE denominati formalmente IEEE 1003.  
In POSIX, un nuovo processo si crea con la chiamata di sistema ```fork()```.

```C
#include <unistd.h> 
pid_t pid; 
pid = fork();
```

Entrambi i processi, padre e il figlio, riprendono l’esecuzione dall’istruzione successiva alla ```fork()```.
La fork è una funzione che esegue operazioni molto onerose poiché il SO deve allocare al processo figlio un segmento dati e un segmento stack privati e crea un PCB per il nuovo processo inserendolo nella tabella dei processi. Il processo figlio condivide il segmento del codice del processo padre ed è sostanzialmente una copia del segmento dati del padre. Pertanto, ogni variabile del figlio è inizializzata al valore che aveva nel processo padre prima che eseguisse la ```fork()```. La ```fork()``` ritorna due valori differenti al padre e al figlio. Più precisamente, ritorna il valore zero al nuovo processo figlio, mentre al processo padre restituisce il valore del PID del figlio. 

![[SOR/img/img24.png|center|500]]

In caso di fallimento viene ritornato il valore -1.

![[SOR/img/img25.png|center|500]] 

>**Esempio di creazione di un processo in POSIX**
```C
#include<stdio.h>
#include<stdlib.h>
int main(){
	pid_t ret; 
	ret = fork(); 
/* fork ritorna il pid del figlio al padre, il valore 0 
al figlio e -1 in caso di errore. 
In base a questo è possibile separare il codice del padre 
da quello del figlio. */ 
	if (ret == -1) { 
		printf("Errore nella fork \n"); 
		exit(0); 
	} 
	else if (ret == 0) { // codice del figlio 
		printf("figlio con pid=%d \n",getpid()); 
	} 
	else { // codice del padre 
		printf("padre con pid=%d \n",getpid()); 
	} 
	// codice eseguito da entrambi i processi, padre e figlio 
	printf("Questa istruzione printf è eseguita da pid=%d \n", getpid());
}
```

### Sostituzione del codice
Tipicamente dopo una ```fork()```, uno dei due processi utilizza la chiamata ```exec()``` per sostituire lo spazio di memoria del processo con un nuovo programma. La famiglia di funzioni ```exec()``` sostituisce l’immagine del processo in corso con una nuova immagine di processo che deve essere creata da un normale file eseguibile. Nel caso di successo, la ```exec()``` non ritorna alcun valore perché l'immagine del processo chiamante è sostituita dalla immagine del nuovo processo. La ```exec()``` permette a un processo di eseguire un diverso programma. I tre segmenti codice, dati e stack del processo che esegue la ```exec()``` sono sostituiti con i segmenti del nuovo programma ma senza che sia creato un nuovo processo.
>**Oss.**
>Dopo l'esecuzione della `fork()` nel sistema è presente un processo in più, mentre dopo la  `exec()` il numero di processi non cambia, ma il codice del processo è sostituito. 

La `exec()` è una famiglia di funizoni e contiene le funzioni `execl()` e la `execv()`. 
``` C
execl(char *path,char *arg1,char *arg2,...,char *argN,(char *)0);
execv(char *path,char *argv[]);
```
Alla `execl()` è possibile passare un numero variabile di parametri. L’ultimo parametro è il carattere nullo, che indica la fine della lista di parametri. Il primo parametro path della funzione è il nome del file da eseguire; arg1 , arg2…argN sono i parametri da passare al programma specificato con il parametro path.  
>**Esempio execl**

```C
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
int main(){
	pid_t ret;
	int stato;
	ret = fork();
	printf("pid = %d \n", getpid());
	if(ret == 0){
		//figlio
		execl("./nuovo", "Saluti", " dal processo"," padre", (char *)0);
		printf("execl fallita");
		exit(1);
	}
	else if(ret > 0){
		printf("sono il padre con pid=%d", getpid());
		ret = wait(&stato);
	}
	else
		printf("Errore fork");
}
```
>File nuovo.c
```C
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
int main(int argc, char *argv[]){
	int i; 
		printf("Processo chiamato da execl con PID = %d \
		e PPID = %d \n",getpid(),getppid());
	for(i = 0; i < argc; i++){
		printf("%s ",argv[i]); //visualizza i parametri in ingresso
		printf("\n");
	}
}
```

### Terminazione dei processi 
Un processo termina la sua esecuzione quando esegue la sua ultima istruzione oppure esegue la chiamata `exit()`.
``` C
#include<stdlib.h>
void exit(int stato);
```
Tutte le risorse del processo, sono deallocate dal sistema operativo. Il parametro **stato** consente al processo figlio che chiama la `exit()` di comunicare al processo padre un valore di tipo intero che ne indica lo stato di uscita. 
Per rilevare la notifica del processo figlio, il padre deve sincronizzarsi con il processo figlio e attendere la sua terminazione. 
Per la sincronizzazione il padre può utilizzare la chiamata `wait()` o  `waitpid()`
```C
#include<stdio.h>
pid_t wait(int *stato);
pid_t waitpid(pid_t pid, int *stato, int opzioni);
```
La `wait()` ritorna il PID del figlio terminato, mentre la `waitpid()` permette di specificare il PID del figlio da attendere. 
L'argomento stato in entrambe le funzioni, è una variabile che contiene lo stato del processo figlio quando termina. Più precisamente, nel caso di terminazione volontaria, la variabile stato conterrà nel suo byte più significativo il valore che il processo figlio ha passato al parametro stato chiamando `exit()`, mentre conterrà il numero del segnale che ha causato la terminazione nel caso di terminazione forzata.

![[SOR/img/img26.png|center|400]]  

Il significato del valore stato passato nella funzione `exit()` è stabilito dal programmatore.  
Il terzo parametro opzioni in `waitpid()` stabilisce se il processo chiamante deve attendere la terminazione del figlio oppure continuare l'esecuzione. Se il valore del parametro opzioni è zero il processo chiamante si blocca, altrimenti il processo chiamante continua la sua esecuzione.
>**Oss.**
>La `waitpid()` può avere sia funzionamento bloccante o non bloccante. La `wait()`, invece, è solo bloccante e ritorna il PID del processo figlio che ha risvegliato il padre. 

Quando un processo termina, le sue risorse sono deallocate dal sistema operativo. Tuttavia, il suo PCB nella tabella dei processi deve rimanere fino a quando il processo padre chiama la `wait()`, in quanto il PCB contiene lo stato di uscita del processo.
Un processo che è terminato, senza che il suo genitore non abbia ancora rilevato il suo stato di uscita con la `wait()`, entra in uno stato detto **zombie**. Una volta chiamata la `wait()`, il PID del processo zombie e il suo PCB vengono cancellati. 

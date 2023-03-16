# Lezione 6
## Comunicazione tra processi. Memoria condivisa. Esempio di comunicazioni tra processi non sincronizzati. Memoria condivisa in POSIX.
---
## Comunicazione tra processi
I processi in esecuzione in un sistema possono essere *indipendenti* o *cooperanti*.  
Il termine **processi concorrenti** indica un insieme di processi in cui l'esecuzione si sovrappone nel tempo. In figura si mostra il caso in cui ogni processo è eseguito su diversi processori (**overlapping**) e il caso in cui più processi condividono la stessa CPU (**interleaving**). 

![[SOR/img/img27.png|center|500]] 

Un processo si dice **indipendente** se non influenza o non è influenzato dagli altri processi in esecuzione nel sistema.  
Un processo è **cooperante** se può influenzare o è influenzato dagli altri processi in esecuzione. Un processo che condivide dati con un altro è un processo cooperante. 
Un SO che permette la cooperazione tra processi offre molti vantaggi, tra cui:
- **Modularità**: Si può progettare e realizzare il sistema operativo in maniera modulare, suddividendo le funzioni di sistema in processi o thread separati.  
- **Condivisione delle informazioni**: Processi diversi potrebbero condividere le stesse informazioni.
- **Maggiore velocità di calcolo**: Per eseguire un’applicazione più velocemente, bisogna suddividerla in più attività, ciascuna delle quali sarà eseguita in parallelo con le altre. Tuttavia, un tale aumento di velocità può essere raggiunta solo se il computer dispone di più core.  
Ci sono due modelli fondamentali di comunicazione tra processi: a **memoria condivisa** ed a **scambio di messaggi**.
Nel modello a memoria condivisa, i processi allocano, mediante chiamate di sistema del kernel, zone di memoria condivise nelle quali possono scambiarsi informazioni. Solitamente, un processo esegue una chiamata per ottenere un'area di memoria condivisa e gli altri processi compiono chiamate per accedervi.  
Nel modello a scambio di messaggi la comunicazione avviene tramite messaggi scambiati tra i processi.  
L’insieme di chiamate di sistema del kernel, che permettono la comunicazione tra processi, è detto **IPC, Inter Process Comunication**.
La memoria condivisa consente velocità di trasferimento di dati più elevate rispetto allo scambio di messaggi. La maggiore efficienza in termini di velocità della memoria condivisa è dovuta al fatto che, una volta che i processi hanno ottenuto una zona di memoria comune, possono successivamente accedervi attraverso funzioni di libreria che girano nello spazio utente, senza eseguire chiamate del kernel, che invece devono essere usate ogni volta che un processo invia o riceve un messaggio.
Nelle architetture multicore, però, la comunicazione tramite messaggi fornisce prestazioni migliori rispetto alla memoria condivisa. Questo perché nelle architetture multiprocessore sono presenti molte cache, nelle quali i dati condivisi sono copiati creando problemi di coerenza. Tali problemi sono risolti con operazioni eseguite dal kernel, producendo quindi un considerevole overhead con conseguente diminuzione delle prestazioni. • Poiché il numero di core di elaborazione sui sistemi aumenta di anno in anno, è possibile che lo scambio di messaggi diverrà la tecnica preferita per l’**IPC**.  

### Memoria condivisa
Questo modello di comunicazione si basa sull'allocazione di una parte di memoria condivisa. Un processo **produttore** produce informazioni che sono elaborate e consumate da un processo **consumatore**. Per consentire l'esecuzione parallela di processi produttore e consumatori, è necessario un buffer dove il produttore inserisce i dati che saranno prelevati dal consumatore. Questo buffer risiede nell'area di memoria condivisa. 
>**Oss.**
>I processi devono essere coordinati in modo che un consumatore non tenti di utilizzare un dato non ancora prodotto.

![[SOR/img/img28.png|center|500]]  

Il buffer può avere dimensione $n$. Nel caso in cui $n = 1$, il funzionamento corretto è dato dall'alternanza di istruzioni di inserimento e prelievo.  
La figura sotto mostra come si potrebbe produrre un errore nel caso in cui due processi che condividono una variabile, tentano di modificare il valore senza sincronizzarsi. Nell'esempio, due processi **P1** e **P2** condividono una variabile **cont**, inizializzata a 0, che incrementano ogni volta che viene eseguita l'istruzione **cont = cont + 1**. Si può notare che al termine delle operazioni svolte dai due processi il valore di cont è 1, invece del valore corretto che sarebbe 2.

![[SOR/img/img29.png|center|500]]  

#### Memoria condivisa in POSIX
In POSIX, la memoria condivisa è gestita utilizzando file mappati in memoria, che associano ad un file una regione di memoria condivisa. Un processo deve prima creare una porzione di memoria, mediante la chiamata `shm_open()`:
```C
shm_fd = shm_open(const char *name, int o_flag, mode_t mode);
```
Il primo parametro `name`, specifica il nome del segmento di memoria condivisa. Il parametro o_flag specifica la modalità di accesso al segmento come ad esempio che il segmento condiviso deve essere creato se non esiste ancora (O_CREAT), o che è aperto per la lettura e la scrittura (O_RDRW). L'ultimo parametro definisce le autorizzazioni che ha il processo sul segmento di memoria. 
In caso di successo la `shm_open()` restituisce un numero intero che indica il descrittore del segmento di memoria condiviso. Una volta creato, il segmento ha dimensione nulla. La funzione `ftruncate()` è utilizzata per dimensionare il segmento in byte. 
La funzione `mmap()` crea un file mappato in memoria corrispondente al segmento condiviso e restituisce un puntatore al file utilizzato per accedere al segmento condiviso. 
I programmi di seguito mostrati, utilizzano il modello produttoreconsumatore per comunicare mediante memoria condivisa. Il produttore crea un segmento di memoria condivisa con nome `/MEMCOND` e vi scrive il messaggio "Saluti a tutti!". Il processo consumatore legge e visualizza il contenuto della memoria condivisa e chiama anche la funzione `shm_unlink()`, che rimuove il segmento di memoria condivisa. 
>**Processo produttore**
```C
#include<stdio.h>
#include<stdlib.h>
#include<sys/mman.h>
#include<sys/wait.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<unistd.h>
#include<sys/types.h>
#include<string.h>

int main(){
	const int SIZE = 4096; //dim segmento di memoria
	const char *nome = "MEMCOND"; //nome segmento
	void *shm_ptr; //puntatore al segmento condiviso 
	/* dati scritti nella memoria condivisa */
	const char *string_0 = "Saluti"; 
	const char *string_1 = "a tutti!";
	int shm_fd; //file descriptor del segmento
	/* crea il segmento di memoria condivisa */
	shm_fd = shm_open(nome, SIZE, PROT_WRITE, MAP_SHARED, shm_fd, 0);
	sprintf(shm_ptr, "%s", string_0); //scrive nel segmento
	shm_ptr += strlen(string_0);
	sprintf(shm_ptr,"%s",string_1); 
	shm_ptr += strlen(string_1); 
	return 0;	
}
```
>**Processo consumatore**
```C
#include<stdio.h>
#include<stdlib.h>
#include<sys/mman.h>
#include<sys/wait.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<unistd.h>
#include<sys/types.h>
#include<string.h>

int main(){
	const int SIZE = 4096; //dim segmento condiviso
	const char *nome = "MEMCOND"; //nome segmento 
	int shm_fd; //descrittore del segmento 
	void * shm_ptr; //puntatore al segmento condiviso
	/* Accesso al segmento in lettura */
	shm_fd = shm_open(nome, O_RDNLY, 0666);
	/* memory map del segmento */
	shm_ptr = mmap (0, SIZE, PROT_READ, MAP_SHARED, shm_fd, 0);
	/* Lettura dal segmento di memoria condivisa */ 
	printf ("% s", (char *) shm_ptr); 
	/* Rimozione del segmento di memoria condivisa */ 
	shm_unlink (nome); 
	return 0;
}
```
>**Comunicazione**
```C
#include<stdio.h>
#include<stdlib.h>
#include<sys/mman.h>
#include<sys/wait.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<unistd.h>
#include<sys/types.h>
#include<string.h>

int N=5; 
Char nome[][20]={"Pietro","Antonio","Laura","Luisa", "Lino"}; 
struct buffer_t { 
	int id; 
	char text[64]; 
} *buffer;

// processo produttore 
void produttore(){ 
	int i; 
	for (i=0; i < N; i++){
		buffer->id=i;
		strcpy(buffer->text,nome[i]); 
		printf ("msg scritto: %d %s\n",buffer->id, buffer->text);
		usleep(200); 
	} 
}

//processo consumatore
void consumatore(){ 
	int i; 
	for (i=0; i<N; i++){
	printf ("msg letto: %d %s\n",buffer->id, buffer->text); 
	usleep(200);
	}
}

int main(){
	pid_t pid;
	int shm_id, SIZE; 
	shm_id=shm_open("memcond",O_CREAT|O_RDWR,0666); 
	SIZE=sizeof(struct buffer_t); 
	ftruncate(shm_id,SIZE); 
	buffer = mmap(0,SIZE,PROT_READ|PROT_WRITE, MAP_SHARED,shm_id,0);
	pid=fork();
	if (pid==0) {
		produttore(); 
	} else { 
	consumatore(); 
	shm_unlink("memcond"); 
	wait(NULL); 
	} 
}
```


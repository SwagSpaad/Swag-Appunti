# Introduzione alla programazione su ARM
---
## Teorema di Böhm - Jacopini
Il teorema di Böhm-Jacopini, enunciato nel 1966 da due informatici italiani dai quale prende il nome, afferma che:
>«qualunque algoritmo può essere implementato utilizzando tre sole strutture: la sequenza, la selezione e l’iterazione, che possono essere tra loro innestati fino a giungere ad un qualsivoglia livello di profondità finito (come le scatole cinesi)».

Questo serve per evitare la programmazione a spaghetti (spaghetti code) che è un termine dispregiativo per il codice sorgente di quei programmi per computer che hanno una struttura di controllo del flusso complessa e/o incomprensibile, con uso esagerato ed errato di go to, eccezioni, thread e altri costrutti di branching (diramazione del controllo) non strutturati.

## Diagrammi Nassi-Shneiderman
I Diagrammi di Nassi-Shneiderman, anche detti **DNS**, sono strumenti semplici che si usano per scrivere algoritmi intrinsecamente strutturati e complessi.
Si collegano al TH Bohm-Jacopini, associando infatti questo teorema al formalismo ideato da Isaac Nassi e Ben Shneiderman, si ottengono appunto i DNS.
I concetti di sequenza, iterazione, selezione possono essere espressi, all’interno del diagramma, in blocchi che possono essere innestati e composti a piacere fino a giungere ad un qualsiasi livello di profondità.

### Sequenza
Si esprime il concetto che ogni procedimento risolutivo si può comporre attraverso un insieme finito di passaggi, o di blocchi, che verranno eseguiti nell’ordine dall’alto verso il basso, senza la possibilità di saltare la valutazione di un passaggio intermedio.

### Selezione
È il concetto attraverso il quale il percorso del flusso di esecuzione può prendere differenti cammini a seconda della condizione espressa in testa al blocco.
Può essere:
1. Singola: se la condizione è verificata, viene eseguito un blocco, altrimenti si esce e si prosegue il flusso di esecuzione (if-then o if-else)
2. Binaria: se la condizione è verificata viene eseguito il blocco then, altrimenti il blocco else (if-then-else)
3. Multipla: controlla il valore di una variabile all’interno di un intervallo di valori predefiniti. Quando la variabile corrisponde a uno dei valori della selezione, viene eseguito il blocco ad esso associato, altrimenti viene eseguito il blocco di default (switch)

### Iterazione
Si esprime la possibilità di ripetere l’esecuzione di un blocco interno fino a quando non si verificherà la condizione di uscita, per questa ragione è di uso comune denominarlo anche “ciclo”. Esistono tre tipologie: for, while, do-while
Quando si sa a priori il numero di ripetizioni da eseguire, si usa il **for**.
Quando c’è la possibilità che il blocco non venga mai eseguito, o l’iterazione è funzione di un evento non noto, si usa il **while**.
Quando l’iterazione dipende da un evento non noto, ma il blocco deve essere eseguito almeno una volta, si usa il **do-while**.

## ARM
### Cos'è?
L'architettura ARM definisce una famiglia di microprocessori di tipo RISC (Poche e semplici istruzioni per massimizzare il throughput delle istruzioni). 
Dal punto di vista circuitale è estremamente semplice rispetto a un CPU tradizionale e quindi richiede un numero minore di transistor e di silicio, questo porta ad essere più economico sul mercato ed ha un basso consumo energetico rispetto alle sue prestazioni. (Questa architetture è diffusa su cellulari, tablet, console, portatili, elettrodomestici e televisori)
Inoltre deve eseguire l’elaborazione dei dati direttamente nel processore senza intervento della memoria e quindi accede in memoria solo per operazioni di lettura e scrittura.

### Struttura ARM
Il processore ARM utilizza un'architettura RISC a 32 bit, con spazio di indirizzamento di 32 bit e la memoria indirizzabile al singolo byte, a 16 bit (halfword) o 32 bit (word). Il processore ARM può indirizzare fino a $2^{32}$ locazioni di memoria. 

#### Organizzazione interna del processore
L'organizzazione interna di ARM prevede l'utilizzo di 3 registri:
- RD: registro destinazione dove verrà scritto il risultato
- RN: registro sorgente che contiene il primo operando (OP1) ed è utilizzato direttamente dalla ALU
- RM: resgistro sorgente che contiene il secondo operando (OP2) ed è utilizzato dalla ALU dopo il passaggio nel **barrel shift** (circuito che può shiftare una word di un numero specifico di bit in un solo ciclo di clock)

![[img62.png|center|400]]
##### Barrel shifter
Un barrel shifter è un circuito che esegue lo shift di una parola binaria di un numero specificato di bit in un solo ciclo di clock. Questo è possibile con una sequenza di multiplexer disposti in parallelo in cui le uscite di un livello sono collegate agli ingressi dei mux adiacenti nel livello successivo. 
Nel barrel shifter si può definire la modalità di shift e il numero di bit da traslare.

###### Logical Shift Left (LSL) e *Logical Shift Right (LSR)*
L’operazione **LSL** e **LSR** si applica a un registro sorgente e richiede un valore che determina il numero di volte in cui eseguire lo scorrimento a sinistra (*destra*) dei bit del registro. Ad ogni passaggio viene inserito il valore zero nel bit meno (*più*) significativo del registro (LSB - least significant bit/ *MSB - most significative bit*). Lo shift a sinistra (*destra*) coincide col moltiplicare (*dividere*) il valore del registro per la base. Se l'operazione viene ripetuta n volte, permette di moltiplicare (*dividere*) il valore del registro per $2^n$. L’ultimo bit più (*meno*) significativo che esce dal registro sorgente, ovvero il bit in posizione 32-n (*n-1*), finisce nel flag C del registro CPSR.

![[img63.png|center|300]]
![[img64.png|center|300]]


![[img65.png|center|300]]
![[img66.png|center|300]]

##### Arithmetic Shift Right (ASR)
L’operazione ASR si applica a un registro sorgente e richiede un valore che determina il numero di volte in cui eseguire lo scorrimento a destra dei bit del registro. Ad ogni passaggio viene mantenuto il valore presente nel bit più significativo del registro (MSB most significant bit). L’operazione di shift a destra coincide col dividere per la base **preservando il segno del contenuto**. Se viene ripetuta n volte permette di dividere il valore del registro per $2^n$. L’ultimo bit meno significativo che esce dal registro sorgente ovvero il bit in posizione n-1 finisce nel flag C del registro CPSR

![[img67.png|center|300]]
![[img68.png|center|300]]

##### Rotate Right (ROR)
L’operazione ROR si applica a un registro sorgente e richiede un valore che determina il numero di volte in cui eseguire lo scorrimento a destra dei bit del registro. Ad ogni passaggio il LSB finisce in posizione MSB senza che nessun bit si perda. L’ultimo bit meno significativo che esce dal registro sorgente per girare ovvero il bit in posizione n-1 finisce nel flag C del registro CPSR.

![[img69.png|center|300]]
![[img70.png|center|300]]

###### Rotate Right con estensione (RRX)
L’operazione RRX funziona esattamente come la ROR ad eccezione del fatto che considera il flag C come estensione del registro sorgente. Ad ogni passaggio il LSB finisce nel flag C, il vecchio valore del flag C in posizione MSB e tutti gli altri si spostano verso destra senza che nessun bit venga perso.

![[img72.png|center|300]]

###### Rotate Left con estensone
Nel barrel shifter non esiste una operazione analoga alla RRX che sia in grado di eseguire la rotazione di bit verso sinistra (rotate left with extend).
si può utilizzare l’istruzione ADCS che produce lo stesso effetto
ADCS R0, R0, R0 ; RLX R0

#### I registri ARM
L'architettura ARM prevede la seguente organizzazione:
- 16 registri denominati R0 - R15:
	- R0 - R12 registri di uso generale utilizzabili dal programmatore per realizzare applicazioni
	- R13 è lo [[Architettura degli elaboratori#Lo stack|stack pointer]] (SP)
	- R14  è il subroutine link register (LR), registro utilizzato per ripristinare il PC alla istruzione successiva alla chamata ad una subroutine, quando quest'ultima ha terminato la sua esecuzione
	- R15 è il Program Counter (PC) e conserva l'indirizzo di memoria della successiva istruzione da eseguire
- Un registro di stato denominato Current Program Status Register (CPSR) 
- 20 registri non accessibili nel modo utente/sistema e organizzati come segue:  
	- 5 registri da R8_FIQ a R12_FIQ attivi solo nella modalità di risposta ad una eccezione causata da un interrupt ad alta priorità
	- 10 registri (SP_FIQ, LR_FIQ, SP_IRQ, LR_IRQ, SP_SVC, LR_SVC, SP_ABT, LR_ABT, SP_UND e LR_UND) attivi soltanto due per volta (stack pointer e linker registri) nella modalità di risposta ad un eccezione
	- 5 registri (SPSR_FIQ, SPSR_SVC, SPSR_ABT, SPSR_IRQ, SPSR_UND) attivo uno per volta nella modalità di risposta ad un eccezione e che permette di salvare il contenuto del registro di stato corrente CPSR nel corrispondente registro sved program status register (SPSR)

##### Current Program Status Register (CPSR)
Questo registro tiene traccia dello stato del programma. Molto utili sono i 5 bit principali di stato (flag di stato) che esprimono le seguenti condizioni aritmetiche:
- Bit N (Bit 31): risultato negativo
- Bit Z (Bit 30): risultato zero dalla ALU
- Bit C (Bit 29): flag di riporto
	- Durante un addizione,  C = 1 se c'è un riporto
	- Durante una sottrazione, C = 0 se è richiesta una cifra in prestito
	- Operazioni di shift o rotazione, viene impostato con l'ultimo bit che esce a causa dello scorrimento
- Bit V (Bit 28): V = 1 se si è verifcata una condizione di overflow. Questa circostanza accade quando si sommano o sottraggono valori con segno rappresentati in completamento alla base
- Bit Q (Bit 27): saturazione - impostato a 1 quando nel risultato è stato inserito un valore limite (estremo superiore o inferiore)
- Sono presenti inoltre altri flag di stato cioè:
	- Bit E Endian - se E=0 little-endian altrimenti se E=1 big-endian (modo di indirizzamento dei byte).
	- Bit Do Not Modify, per futuri utilizzi.
	- Bit Greater Than or Equal, vengono usati come flag di maggiore di o uguale (GE).
	- Bit Imprecise Data Abort enable/disable, abilita/disabilita ogni richiesta di abort dati imprecisi.
	- Bit Interrupt enable/disable, abilita/disabilita ogni richiesta di interrupt a bassa priorità IRQ.
	- Bit Fast Interrupt enable/disable, abilita/disabilita ogni richiesta di interrupt ad alta priorità FIQ.
	- Bit di stato Thumb, il processore entra nello stato thumb e le istruzioni vengono allineate a 16 bit e il valore del PC è memorizzato nei 31 bit più significativi.
	- Bit di stato Jazelle, il processore entra nello stato jazelle e permette il riconoscimento del byte code (Java) utilizzando un set di istruzioni a 8 bit, mantiene la modalità di accesso alla memoria a 32 bit quindi è in grado di leggere 4 istruzioni alla volta.
	- Bit M0-M4 definiscono la mod di funzionamento del processore ARM:
		- Utente - mod standard
		- Supervisor - si attiva al reset o quando il processore riceve un’istruzione di interrupt software (SWI)
		- Fast Interrupt Request - si attiva quando il bit FIQ viene asserito
		- Interrupt Request si attiva quando il bit IRQ viene asserito
		- Abort - si attiva in risposta ad una eccezione di abort
		- Undefined - si attiva quando si verifica un'eccezione dopo l’esecuzione di un'istruzione non supportata
		- System - Mod privilegiata

![[img57.png|center|500]]

#### Tabella delle condizioni aritmetico logiche

![[img58.png|center|600]]

#### Esempio registro di stato 

![[img59.png|center|550]]

L’istruzione **ADD** verrà eseguita solo se la condizione **GT** (maggiore stretto) sarà verificata. La condizione GT è identificata dal codice 1100 nella sezione del registro chiamata condition code.

---

![[img60.png|center|550]]

In questo esempio la condizione **LE** (minore uguale) è verificata, in quanto nel registro di stato CPSR i flags N Z C V rispettano almeno una delle condizioni in tabella (Z = 1 OR N != V).

#### Grado di parità di gestione delle eccezioni

![[img61.png|center|500]]


### Particolarità ARM
- Non esistono istruzioni di shift o rotazione di bit se non innestate all’interno di altre istruzioni di elaborazione (aritmetica, logica…) e possono essere applicate ad un solo operando   
- Riesce ad applicare una operazione aritmetica o logica e lo shift o di rotazione in una sola istruzione
- Non esiste una istruzione per eseguire l’operazione di divisione questo viene delegato a un coprocessore che, avendo un ambito di applicazione più specifico, può risolvere meglio il compito
- Le istruzioni normalmente non scrivono il registro CPSR ma è possibile farlo tramite un suffisso **S** (forza la scrittura nel registro CPSR) aggiungendolo al codice mnemonico dell’ istruzione.

### Big endian e Little endian
Sono 2 tipi di ordinamento dei bit e dei byte.
1.  Big Endian imposta i bit della word partendo da quello più significativo a quello meno significativo
2.  Little Endian imposta i bit della word partendo da quello meno significativo a quello più significativo

### Modalità di indirizzamento 
Sono le tecniche attraverso le quali il processore ARM individua gli operandi presenti nell'istruzione per poi operare su di essi. Le modalità più semplici sono:
- Indirizzamento immediato: l'operando è contenuto nella codifica dell'istruzione
- Indirizzamento a registro:  l’operando è contenuto nel registro
- Indirizzamento a registro con shift: l'operando è contenuto in un registro ma subirà un operazione di shift o rotazione prima di essere utilizzato

#### Indirizzamento per le operazioni di load/store
Le istruzioni di load/store utilizzano un registro base e un offset che, sommato o sottratto al registro base, restituisce l'indirizzo di memoria ove effettuare l'operazione.
Per le operazioni di load/store sono possibili le seguenti modalità di indirizzamento:
- **con offset immediato**: l'offset è un valore senza segno specificato nell'istruzione
- **con offset a registro**: l’offset è un valore contenuto in un registro generico (con esclusione del PC o R15)
- **con offset a registro scalato**: l’offset è un valore calcolato per traslazione o rotazione del contenuto in un registro generico (con esclusione del PC)
- **assoluto**: è l’unico metodo che ha una sintassi unica
Per favorire l'accesso a blocchi di memoria o a strutture complesse, sono state aggiunte le modalità indicizzate, che permettono di aggiornare il registro base con l'indirizzo calcolato. Esistono due diverse modalità di aggiornamento:
- **pre-indicizzata**: prima si calcola l'indirizzo è successivamente si scrive il registro base
- **post-indicizzata**: prima si utilizza il registro base come riferimento di memoria e dopo si aggiorna con il nuovo valore.

### Il set delle istruzioni
Le istruzioni ARM possono essere classificate in base alla tipologia e alla sintassi che le accomuna in diverse classi di istruzioni.

#### Istruzioni di elaborazione e trasferimento dati
Le istruzioni di elaborazione servono al processore per svolgere calcoli come operazioni aritmetiche o logiche, mentre le istruzioni di trasferimento dati consentono di caricare i vari registri.

##### Istruzioni aritmetiche e logiche
Implementano le operazioni matematiche. Queste possono essere di tipo **aritmetiche** (somma, differenza, prodotto), **logiche** (operazioni booleane) o **relazionali** (comparazione di valori)
Le istruzioni aritmetiche e logiche operano al massimo su due operandi e possono aggiornare i flag del registro di stato.
L'istruzione aritmetica o logica presenta la seguente sintassi:
$$
\text{< MNEM >\{< PreCond >\}\{< S >\} <Rd>, < Rn >, OP}_2
$$
- $\text{< MNEM >}$ è il codice mnemonico dell'operazione (ADD, SUB)
- $\text{< PreCond >}$ è la pre-condizione (opzionale) che consente l'esecuzione dell'istruzione quando risulta verificata, altrimenti, se falsa, l'istruzione verrà ignorata
- $\text{< S >}$ (opzionale) forza la scrittura nel registro di stato CPSR
- $\text{< Rd >}$ è il registro destinazione nel quale verrà salvato il risultato dell'operazione
- $\text{< Rn >}$ è il registro sorgente nel quale è presente il valore del primo operando
- $\text{OP}_2$ è il secondo operando, che può essere un valore immediato, un altro registro oppure il risultato di un operazione di shift su un valore o registro

![[img72.png|center|500]]

###### Esempi di istruzioni artimetiche e logiche
- **ADD R0, R1, R2**: carico in R0 la somma tra il contenuto del registro R1 ed R2 (indirizzamento a registro)
- **ADD R0, R1, #16**: carico in R0 la somma tra il contenuto del registro R1 ed il numero decimale 16 (indirizzamento immediato)
- **ADD R0, R1, # 0xF**: carico in R0 la somma tra il contenuto del registro R1 ed il numero esadecimale F (indirizzamento immediato)
- **ADDLT R0, R1, R2**: carico in R0 la somma tra il contenuto del registro R1 e del registro R2, inoltre carico lo stato del risultato nei flags NZCV del registro di stato CPSR. Per esempio, se il risultato della somma va in overflow i flag C (carry) e V (overflow) verranno settati a 1

###### Indirizzamento immediato
Si hanno a disposizione solo 12 bit su 32 che costituiscono la word dell’istruzione, quindi si potrebbero rappresentare soltanto i numeri che vanno da 0…+4095 oppure da -2048…+2047. Questo limite non è accettabile perchè il processore ARM gestisce valori che hanno un campo di variazione da 0x00000000 a 0xFFFFFFFF
Vengono memorizzati nei 12 bit due campi: posizione (4 bit) e valore (8 bit)

Il numero descritto è ottenuto per traslazione, verso destra, dei bit del campo valore per un numero di posizioni pari al doppio del campo posizione

Questo implica che si possono esprimere tutti i valori che hanno in binario bit 1 a distanza massima minore, o al più uguale, ad 8 che possano essere ottenuti per traslazione in pos pari.

in generale :
1.  convertire il valore in binario e verificare che il  bit 1 più significativo e ultimo bit 1 meno significativo siano a distanza massimo di 8 (compressi il primo bit 1 più significativo e ultimo bit 1 meno significativo) 
2.   verificare che il numero di zeri sia pari
	- il codice binario anche se in riga vanno immaginati come scritti su un cerchio perciò anche se 0xA000000A sembra non indirizzabile perchè in binario è 10100000000000000000000000001010 e sembra non rispettare i nessuno dei due punti in realtà **1010**000000000000000000000000**1010** le parti evidenziate sono come nelle mappe K vicine tra di loro

###### Indirizzamento con shift immediato e con shift a registro
Se il bit 25 vale 0, nei bit 5 e 6 il tipo di shift da applicare tra quelli disponibili nel [[#Barrel shifter|barrel shifter]] (LSL, LSR, ASR, ROR, RRX) mentre il bit 4 specifica il tipo di indirizzamento.
Esempi:
**(INDIRIZ. CON SHIFT IMMEDIATO)**:
- ADD R3, R3, R3, LSL #2 —> R3 = R3 + R3 * 4
- RSB R2, R2, R2, LSL #4—> R2 = R2 * 16 - R2
- AND R0, R1, R2, RRX —> R0 = R1 AND RRX (R2)

**(INDIRIZZ. CON SHIFT A REGISTRO)**:
ADD R0, R1, R2, LSL R3 —> R0 = R1+R2*(2^R3)
ORR R0, R0, R2, LSL R3 —> R0 = R0 0R R2 * (2^R3)
AND R0, R0, R2, LSR R3 —>R0 = R0 AND R2 / (2^R3)

###### Indirizzamento a registro
Il secondo operando è il registro Rm, senza che quest’ultimo subisca operazioni di shift
Questa istruzione è un caso particolare dell'istruzione LSL con indirizzamento immediato in cui i bit non sono traslati

##### Istruzioni aritmetiche con saturazione
Se si utilizzano 32 bit per codificare i numeri interi con segno, l’intervallo di rappresentazione si divide in due sottoinsiemi:
-   valori positivi da 1 a $2^{32}-1$
-   valori negativi da -1 a $-2^{32}-1$

Se durante un'operazione aritmetica si ottiene un numero che eccede uno dei due limiti, il risultato potrebbe essere interpretato per sbaglio nel limite opposto.
Per questo ARM usa la tecnica di aritmetica con saturazione, in cui tutte le operazioni finiscono in un dominio intero e finito di valori compresi tra un minimo e un massimo.
Quando un valore supera un estremo, il flag Q del CPSR viene attivato.
Le istruzioni che supportano le operazioni aritmetiche con saturazione sono QADD e QSUB
I bit non utilizzati nella codifica dell’istruzione sono segnati con SBZ (Should Be Zero)

La sintassi delle istruzioni aritmetiche con interi saturi è:
$$
\text{< MNEM >\{< PreCond >\} <Rd>, < Rm >, < Rn >}
$$
La codifica delle istruzioni aritmetiche con interi saturi è:

| 31.. 28 | 27 26 25 | 24.. 21 | 20 | 19.. 16 | 15.. 12 | 11.. 8 | 7 6 5 4 | 3.. 0 |
|:-------:|:--------:|:-------:|:--:|:-------:|:-------:|:------:|:-------:|:-----:|
| PreCond |   0 0 0  |  OPCODE |  0 |    Rn   |    Rd   |   SBZ  | 0 1 0 1 |   Rm  |


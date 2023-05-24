----
# Analisi di circuiti sequenziali
Consideriamo il circuito in figura

![[LRL/img/img75.png|center|450]]

Il circuito contiene alcune porte logiche elementari e un registro formato da due flip-flop. Ognuno dei due flip-flop quindi ha uno **stato attuale** (ossia il bit che sta memorizzando), dato dalla variabile indicata con $Q$ nel disegno, e uno **stato futuro** (ossia il bit che si troverà a memorizzare nel momento in cui il clock passerà dallo stato 0 allo stato 1) dato dalla variabile indicata con $D$.

Dato lo stato attuale del circuito (la coppia di bit $Q_{1},\: Q_{2}$) e l'input che gli passiamo in questo momento (il bit $x$), risultano univocamente determinati sia l'output attuale del circuito (il bit $y$), sia lo stato futuro del circuito (la coppia di bit $D_{1},\: D_{2}$). Quindi il circuito può essere descritto da un sistema di tre equazioni in cui $Q_{1},\:Q_{2}$ e $x$ costituiscono le variabili indipendenti e $D_{1},\: D_{2}$ e $y$ quelle dipendenti.

Vediamo ora la tabella di stato del circuito mostrato sopra.

![[LRL/img/img76.png|center|200]]

Oltre che con le equazioni di stato e la tabella di stato c'è anche un altro modo conveniente in cui possiamo rappresentare un circuito sequenziale: con un grafo diretto con gli archi etichettati. Usiamo i nodi del grafo per rappresentare gli stati del circuito, gli archi per le transizioni da uno stato all'altro e le etichette sugli archi per indicare gli input che determinano le transizioni e gli output del circuito. Il grafo che si ottiene si chiama **diagramma di stato** del circuito.
Per esempio, nel caso del nostro circuito abbiamo quattro possibili stati (i valori che la coppia di bit $(Q_{1},\: Q_{2})$ può assumere). Indichiamoli con $S_{0}=(0,0),$ $S_{1}=(0,1),$ $S_{2}=(1,0)$ e $S_{3}=(1, 1)$ e assumiamo che inizialmente il circuito si trovi nello stato $(Q_{1},Q_{2})=(0,0)$.

![[LRL/img/img77.png|center|300]]

Cosa succede quando il clock del circuito passa per la prima volta da 0 a 1? Questo dipende dall'input del circuito: in accordo alla figura sopra, quando il circuito è nello stato $(Q_{1}, Q_{2})=(0,0)$ abbiamo che

- se l'input è $x=0$, allora l'output è $y=0$, inoltre quando il clock passa da 0 a 1, lo stato del primo flip-flop resterà 0, mentre quello del secondo passerà ad 1 $(D_{1},D_{2})=(0,0)$, quindi al ciclo di clock successivo  il circuito sarà nello stato $S_{1}$

![[LRL/img/img78.png|center|300]]

- se invece l'input è $x=1$, l'output sarà ancora $y=0$, e quando il clock passa da 0 a 1, lo stato di entrambi i flip-flop resterà a 0 $(D_{1},D_{2})=(0,1)$, quindi al ciclo successivo di clock il circuito sarà ancora nello stato $S_{0}$

![[LRL/img/img79.png|center|300]]

# Progetto di circuiti sequenziali 
In questa sezione vediamo come il diagramma e le equazioni risultano molto utili anche quando vogliamo progettare un circuito sequenziale che svolga una determinata operazione. 
Supponiamo di voler progettare un circuito con un input $x$ e un output $y$ che si comporti nel seguente modo:
- ad ogni ciclo di clock gli passiamo un input $x$ dal valore Booleano
- l'output $y$ del circuito deve essere 1 ogni volta che gli ultimi tre bit che abbiamo dato in input formano la sequenza 101.
Per esempio se gli input $x$ passati al circuito nei primi $t=1,\:2,\:\dots,\:10$ cicli di clock sono 

![[LRL/img/img81.png|center|400]]

allora i valori restituiti in output dal circuito nei rispettivi cicli di clock devono essere

![[LRL/img/img82.png|center|400]]

Infatti $y$ deve essere 1 al quarto ciclo di clock, perché nei cicli 2, 3 e 4 la sequenza di bit in input è stata 101, e deve essere 1 anche al sesto ciclo di clock perché nei cicli 4, 5 e 6 la sequenza in input è stata 101. Per tutti gli altri cicli $t$ l'output $y$ deve essere 0 perché la sequenza di bit in input nei cicli $(t-2,\:t-1,\:t)$ è diversa da 101 per ogni $t\neq 4,6$.

Progettare il circuito pensando direttamente alle porte logiche che serviranno e come connetterle sembra molto difficile, ma se partiamo dall'astrazione che ci fornisce il diagramma di stato, il progetto del circuito diventa molto più facile.
Il nostro circuito dovrà partire da uno stato 
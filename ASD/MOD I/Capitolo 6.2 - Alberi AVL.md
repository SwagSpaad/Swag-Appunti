----
Nel [[Capitolo 6.1 - Binary Search Tree|capitolo 6.1]] abbiamo visto come gli alberi di ricerca **bilanciati** garantiscano un tempo di ricerca logaritmico. A fronte di operazioni di inserimento e cancellazione, però, il bilanciamento dell'albero potrebbe perdersi causando un peggioramento nelle prestazioni. 
È quindi opportuno mantenere il bilanciamento anche quando si inseriscono e cancellano elementi. 

# Fattore di bilanciamento
**Def.**
Il fattore di bilanciamento $\beta(v)$ di un nodo v è la differenza tra l'altezza del sottoalbero sinistro di v e l'altezza del sottoalbero destro di v.
$$\beta(v) = altezza(sin(v)) -altezza(des(v))$$
# Bilanciamento in altezza
**Def.**
Un albero si dice **bilanciato in altezza** se ogni nodo v ha un fattore di bilanciamento in valore assoluto $\leq1$

Un albero AVL è un albero binario di ricerca **bilanciato in altezza**.

Generalmente $\beta(v)$ è mantenuto come informazione addizionale nel record relativo a v.

**Esempi**

![[img103.png|center|500]]

è un albero AVL? Si, tutti i nodi hanno fattore di bilanciamento = 0

![[img104.png|center|350]]

è un albero AVL? No, Non vale la proprietà sui fattori di bilanciamento

>[!INFO]- Osservazione
>Per convenzione l'altezza di un albero vuoto = -1


![[img105.png|center|500]]

è un albero AVL? Si, proprietà sui fattori di bilanciamento rispettata

## Altezza di alberi AVL

Come dimostriamo che l'altezza di un albero AVL con n nodi sia $O(\log(n))$? L'idea è quella di considerare, tra tutti gli alberi AVL, l'altezza degli alberi più sbilanciati possibile.

### Alberi di fibonacci

Tra tutti gli alberi AVL di altezza h, un **albero di fibonacci** ha il minimo numero di nodi.
Un albero di fibonacci di altezza h, può essere costruito unendo, tramite l'aggiunta di una radice, un sottoalbero di altezza h-1 e un sottoalbero di altezza h-2. Per un albero di fibonacci, è facile osservare che il fattore di bilanciamento di ogni nodo interno è +1, e quindi questi alberi sono gli alberi bilanciati in altezza più vicini alla condizione di non bilanciamento. 

![[img106.png|center|500]]

Come si genera l'i-esimo albero di Fibonacci? Sia $T_h$ un albero di fibonacci di altezza h.
Sotto è riportato lo schema di costruzione di un generico albero di fibonacci di altezza h.

![[img107.png|center|200]]

**Lemma**
Sia $T_h$ un albero di fibonacci di altezza h e sia $n_h$ il numero dei suoi nodi. Allora risulta h = $O(\log(n_h))$

**Dim.** 
Per costruzione di $T_h$, risulta $n_h = 1 + n_{h-1}+ n_{h-2}$. Questo ricorda l'equazione generica dei numeri di fibonacci $F_i = F_{i-1} + F_{i-2}$.
Dimostreremo **per induzione** che $n_h  = F_{h+3} -1$.
Passo base: per h = 0 è banalmente verificato, dato che risulta $$n_0=1=F_3-1=2-1$$
Assumendo per ipotesi che $n_k=F_{k+3}-1 \:\: \forall \:\: k<h$ e usando le ricorrenze relative ad $n_h$ ed $F_i$ si ha:$$n_h=1+n_{h-1}+n_{h-2}=1+(F_{h-1+3}-1)+(F_{h-2+3}-1)=1+F_{h+2}-1+F_{h+1}-1=$$ $$= F_{h+3}-1$$ 
**Corollario**: Un albero AVL con n nodi ha altezza $h=O(log(n))$
**Dim.**
Sia h l'altezza dell'albero AVL. Consideriamo l'albero di fibonacci di altezza h. Sia $n_h$ il numero di nodi di tale albero. Per la definizione di albero di fibonacci, $n_h \leq n$. Il fatto che $h=O(\log(n_h))$,completa la dimostrazione.

**Posso utilizzare un albero AVL per implementare un dizionario?**

![[img108.png|center|500]]

A seguito di operazioni di inserimento e cancellazione, il fattore di bilanciamento richiesto dagli alberi AVL è cambiato. È quindi necessario ripristinare il fattore di bilanciamento tramite determinate operazioni.

## Implementazione delle operazioni

### Ribilanciamento tramite otazioni

L'operazione search procede come in un BST, ma inserimenti e cancellazioni potrebbero sbilanciare l'albero, per questo si ripristina il bilanciamento mediante opportune rotazioni. 

![[img109.png|center|500]]

La figura mostra la rotazione di base: si può ruotare su un nodo perno o verso destra o verso sinistra. La proprietà di ricerca è mantenuta dopo la rotazione, infatti l'ordine relativo alle chiavi dei sottoalberi $T_1, \:T_2, \:T_3$ e nei nodi u e v rimane invariato.
Le rotazioni vengono effettuate sui nodi non bilanciati, in cui il fattore di bilanciamento in valore assoluto è $\geq 2$. 
Sia v un nodo con fattore di bilanciamento $\pm 2$. Intituitivamente, esisterà un sottoalbero T di v che sbilancia il nodo. A seconda della posizione di T abbiamo 4 casi:
- Sinistra-Sinistra (SS): T è il sottoalbero sinistro del figlio sinistro di v
- Destra-Destra (DD): T è il sottoalbero destro del figlio destro di v
- Sinistra-Destra (SD):T è il sottoalbero sinistro del figlio destro di v
- Destra-Sinistra (DS): T è il sottoalbero destro del figlio sinistro di v
Siccome questi casi sono simmetrici a coppie (SS - DD ed SD - DS), analizzeremo solo i casi SS e SD.

#### Caso SS

Per ribilanciare il nodo v, basterà applicare una rotazione semplice verso destra su v, il cui fattore di bilanciamento passerà da 2 a 0. Si noti che l'altezza del sottoalbero coinvolto nella rotazione è h+3 prima 
della rotazione, per via del sottoalbero $T_1$, ed è h+2 immediatamente dopo.

![[img110.png|center|500]]

Sono possibili 2 sottocasi:
- L'altezza di $T_2$ è h $\implies$ l'altezza dell'albero coinvolto nella rotazione passa da h+3 ad h+2

![[img111.png|center|600]]

- L'altezza di $T_2$ è h+1 $\implies$ l'altezza dell'albero coinvolto nella rotazione rimani pari ad h+3

![[img112.png|center|600]]

##### Osservazioni sul caso SS
- Dopo la rotazione l'albero è bilanciato (tutti i fattori di bilanciamento sono in modulo $\leq1$)
- L'**inserimento** di un elemento nell'AVL (ovvero, l'aggiunta di una **foglia** a un albero bilanciato) può provocare solo il sottocaso 1 (perchè altrimenti l'AVL era già sbilanciato!)
- Invece, la **cancellazione** di un elemento dall'AVL (che necessariamente fa diminuire l'altezza di qualche sottoalbero) può provocare entrambi i casi (ad esempio, se cancello un elemento ho abbassato l'altezza di $T_3$)
- Nel caso 1, dopo la rotazione, l'albero diminuisce la sua altezza di uno

#### Caso SD

Questo caso è più complesso e richiede l'applicazione di una doppia rotazione. Sia z il figlio più sinistro di v: l'albero T che sbilancia v è il sottoalbero destro di z, radicato nel nodo w. La rotazione SD consiste in una **rotazione di base** verso sinsitra con perno in z, seguita da una **rotazione di base** verso destra con perno in v. 

- L'altezza di $T(v)=h+3$, l'altezza di $T(z)=h+2$, l'altezza di $T_1=h$, l'altezza di $T_4=h$ e l'altezza di $T(w)=h+1\implies\beta(v)=2$ e $\beta(z)=-1$ cioè lo sbilanciamento è provocato dal sottoalbero destro di z.

![[img113.png|center|300]]

![[img114.png|center|700]]

- L'altezza dell'albero dopo la rotazione passa da h+3 a h+2, poichè $T_2,\: T_3$ sono alti al più h e il fattore di bilanciamento di w diventa 0, mentre i fattori di bilanciamento di z e v sono 0 oppure $\pm1$
- Il caso SD può essere provocato sia da inserimenti (in $T_2$ o $T_3$), sia da cancellazioni che abbassano di 1 l'altezza di $T_4$

#### Insert(elem e, chiave k)

L'inserimento avviene in 3 passi:
1. Si crea un nuovo nodo u con elemento e e chiave k e lo si inserisce nell'albero come in un BST
2. Si ricalcolano i fattori di bilanciamento che sono mutati in seguito all'inserimento. Sia r la radice dell'albero AVL. Osserviamo che solo i fattori di bilanciamento dei nodi nel cammino da r ad u possono cambiare.
3. Se nel cammino da r ad u appare un fattore di bilanciamento pari a $\pm 2$, esegui una rotazione opportuna su v.

![[img115.png|center|400]]
![[img116.png|center|400]]
![[img117.png|center|400]]

#### Delete(elem e)

Anche la cancellazione avviene in 3 passi:

1. Si cancella il nodo come in un BST
2. Si ricalcolano i fattori di bilanciamento che sono mutati dopo la cancellazione. Osserviamo che solo i fattori di bilanciamento dei nodi nel cammino dalla radice al padre del nodo eliminato possono mutare. 
3. Per ogni nodo con fattore di bilanciamento $\pm 2$, procedendo dal basso verso l'alto, si effettuano rotazioni doppie o semplici 

>[!info]- Osservazione
>Potrebbero essere necessarie $O(log(n))$ rotazioni, infatti, eventuali diminuzioni di altezza indotte dalle rotazioni, possono propagare lo sbilanciamento verso l'alto nell'albero (l'altezza del sottoalbero in cui è avvenuta la rotazione **diminuisce di 1** rispetto a quella che aveva **prima della cancellazione**)

![[img118.png|center|400]]
![[img119.png|center|400]]
![[img120.png|center|400]]
![[img121.png|center|400]]

##### Cancellazione con rotazioni a cascata

![[img122.png|center|500]]

### Costo delle operazioni

Tutte le operazioni hanno costo $O(log(n))$, poichè l'altezza dell'albero è $O(log(n))$ e ciascuna rotazione richiede solo tempo costante

## Classe AlberoAVL

Vediamo la struttura di una possibile classe di alberi AVL, derivata dalla classe base AlberoBinarioDiRicerca. L'unica informazione aggiuntiva che un albero AVL ha in più rispetto ai nodi di un BST è il fattore di bilanciamento. 

![[img123.png|center|500]]

**Oss.**
Nell'analisi della complessità dell'operazione di insert/delete abbiamo implicitamente usato le seguenti tre proprietà:
1. dato un nodo v, è possibile conoscere il suo fattore di bilanciamento $\beta(v)$ in tempo $O(1)$
2. dopo aver inserito/cancellato un nodo v nell'albero come se fosse un semplice BST, è possibile ricalcolare i fattori di bilanciamento dei nodi lungo il cammino da v alla radice in tempo complessivo $O(\log(n))$
3. Nell'eseguire le rotazioni necessarie per ribilanciare l'albero, è possibile aggiornare anche i fattori di bilanciamento dei nodi coinvolti in tempo complessivo $O(\log(n))$

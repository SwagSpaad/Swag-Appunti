# Lezione 8
## Espressioni Regolari

>**Teorema**
>Tutti i linguaggi definiti da espressioni regolari sono regolari

>**Teorema**
>Data una grammatica $\mathcal G$ di tipo 3, esiste una espression regolare $r$ tale che $L(\mathcal G)=L(r)$, che descrive cioè il linguaggio generato da $\mathcal G$

Consideriamo una grammatica $\mathcal G$ di tipo 3 e il linguaggio $L$ da essa generato, che per semplicità assumiamo non contenga la stringa vuota $\varepsilon$

Se così non fosse, applichiamo le considerazioni seguenti al linguaggio $L-\lbrace\varepsilon\rbrace$, anch'esso regolare: una volta derivata un'espressione regolare $r$ che lo definisce, l'espressione regolare che definisce $L$ sarà chiaramente $r+\varepsilon$

Alla grammatica $\mathcal G$ possiamo far corrispondere un sistema di equazioni su espressioni regolari

Estensione del linguaggio delle espressioni regolari con variabili $A,...,Z$, associando una variabile ad ogni non terminale in $\mathcal G$

Tali variabili potranno assumere valori nell'insieme delle espressioni regolari

Raggruppamento di tutte le produzioni che presentano a sinistra lo stesso non terminale. Per ogni produzione del tipo $$A\to a_1B_1|a_2B_2|...|a_nB_n|b_1|b_2|...|b_m$$abbiamo un equazione del tipo:
$$A=a_1B_1+a_2B_2+...+a_nB_n+b_1+b_2+...+b_m$$

Da una grammatica regolare si ottiene un sistema di **equazioni lineari destre**, in cui ogni monomio contiene una variabile a destra di simboli terminali

Risoluzione del sistema di equazioni su espressioni regolari estese:
Individuazione dei valori (espressioni regolari normali, prive delle variabili che definiscono a loro volta espressioni regolari) che, una volta sostituiti alle variabili, soddisfano il sistema di equazioni

**Esempio**
- $A\to aA|bB$
- $B\to bB|c$
corrisponde al sistema di equazioni
$$\begin{cases}A=aA+bB\\B=bB+c\end{cases}$$
Per risolvere il sistema è possibile utilizzare, le trasformazioni algebriche applicabili sulle operazioni di unione e concatenazione, oltre alle seguenti due regole

**Regola 1**: _Sostituzione_ di una variabile con un'espressione regolare estesa

Con riferimento all'esempio precedente abbiamo 
$$\begin{cases}A=aA+b(bB+c)=aA+bbB+bc\\B=bB+c\end{cases}$$
**Regola 2**: _Eliminazione della ricursione_ (**Teorema di Arden**)

L'equazione $B=bB+c$ si risolve in $B0b^\star c$. Infatti, sostituendo a destra e sinistra abbiamo
$$b^\star c=b(b^\star c)+c=b^+c+c=(b^++\varepsilon)c=b^\star c$$
Più in generale abbiamo che un'equazione del tipo 
$$A=\alpha_1A+\alpha_2A+...+\alpha_nA+\beta_1+\beta_2+...+\beta_m$$
si risolve in 
$$A=(\alpha_1+\alpha_2+...+\alpha_n)^\star(\beta_1+\beta_2+...+\beta_m)$$
dove $\alpha_1,...,\alpha_n,\beta_1,...,\beta_m$ sono espressioni regolari estese

**Esempio concreto**

Grammatica regolare
- $A_0\to aA_1|a$
- $A_1\to bA_3|bA_2$
- $A_2\to aA_2|bA_0|b$
- $A_3\to bA_3|aA_2$
da cui si ottiene il seguente sistema lineare
$$\begin{cases}A_0&=aA_1+a\\A_1&=bA_3+bA_2\\A_2&=A_2+bA_0+b\\A_3&=bA_3+aA_2\end{cases}$$
Per eliminazione della ricursione su $A_3$:
$$\begin{cases}A_0&=aA_1+a\\A_1&=bA_3+bA_2\\A_2&=A_2+bA_0+b\\A_3&=b^\star aA_2\end{cases}$$
per eliminazione della ricursione su $A_2$:
$$\begin{cases}A_0&=aA_1+a\\A_1&=bA_3+bA_2\\A_2&=a^\star(bA_0+b)\\A_3&=b^\star aA_2\end{cases}$$
per sostituzione di $A_2$ nell'equazione relativa ad $A_3$
$$\begin{cases}A_0&=aA_1+a\\A_1&=bA_3+bA_2\\A_2&=a^\star(bA_0+b)\\A_3&=b^\star aa^\star(bA_0+b)\end{cases}$$
per sostituzione di $A_2$ e $A_3$, nell'equazione relativa ad $A_1$
$$\begin{cases}A_0&=aA_1+a\\A_1&=b(b^\star aa^\star(bA_0+b))+b(a^\star(bA_0+b))\\A_2&=a^\star(bA_0+b)\\A_3&=b^\star aa^\star(bA_0+b)\end{cases}$$
per sostituzione di $A_2$ e $A_3$, nell'equazione relativa ad $A_1$
$$\begin{cases}A_0&=aA_1+a\\A_1&=b(b^\star aa^\star+a^\star)(bA_0+b)\\A_2&=a^\star(bA_0+b)\\A_3&=b^\star aa^\star(bA_0+b)\end{cases}$$
per sostituzione di $A_1$ nell'equazione relativa a $A_0$
$$\begin{cases}A_0&=a(b(b^\star aa^\star+a^\star)(bA_0+b))+a\\A_1&=b(b^\star aa^\star+a^\star)(bA_0+b)\\A_2&=a^\star(bA_0+b)\\A_3&=b^\star aa^\star(bA_0+b)\end{cases}$$
per fattorizzazione nell'equazione relativa ad $A_0$
$$\begin{cases}A_0&=ab(b^\star aa^\star+a^\star)bA_0+ab(b^\star aa^\star+a^\star)b+a\\A_1&=b(b^\star aa^\star+a^\star)(bA_0+b)\\A_2&=a^\star(bA_0+b)\\A_3&=b^\star aa^\star(bA_0+b)\end{cases}$$

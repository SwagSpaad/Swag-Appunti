----
La logica proposizionale è un linguaggio i quali elementi sono le proposizioni e le relazoni fra esse.

# Lettere proposizionali, connettivi e tabelle di verità
Indichiamo con $p,\: q, \:r, ...$  delle variabili booleane, ossia variabili che assumono valori true o false.
A partire dalle variabili, che chiamiamo lettere proposizionali, possiamo costruire delle formule più complesse utilizzando i connettivi.
Data una variabile $p$, indichiamo con $\lnot{p}$, la formula che è T $\iff$ $p$ è F (**negazione**).
Indichiamo con $p \lor q$  la formula che è T  $\iff$ una fra $p$ e $q$ è T (**or, disgiunzione**).
Indichiamo con $p \land q$ la formula che è T quando $p$ e $q$ sono entrambe T (**and, congiunzione**).

![[LRL/img/img0.png|center|400]]

Esistono anche altri connettivi come l'implicazione ($\implies$) e l'equivalenza ($\equiv$ )

![[LRL/img/img1.png|center|400]]

# Formule ben formate
Ad ogni formula della logica proposizionale, possiamo associare una tabella di verità. Per esempio, la formula $p \land (q\lor \lnot{r})$ vale T $\iff$  $p$ è T e ($q$ è T oppure $r$ è F). 
La tabella di verità sarà la seguente:

![[LRL/img/img2.png|center|400]]

**Def.** Formule ben formate
Le lettere proposizionali sono formule ben formate (abbreviato f.b.f.). Inoltre:
1. se $\mathcal F$ 

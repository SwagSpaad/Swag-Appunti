----
Il metodo dei tableaux visto nella [[Lezione 3 - Il metodo dei Tableaux per la logica proposizionale|lezione 4]] si può estendere anche alla logica del primo ordine. Vediamo come.

# Le regole per i quantificatori
Abbiamo visto che le formule della logica proposizionale si possono classificare in $\alpha$-formule per quelle di tipo AND e in $\beta$-formule per quelle di tipo OR.

![[LRL/img/img18.png|center|500]]

Ad ogni formula corrisponde una regola di estensione nei tableaux del tipo

![[LRL/img/img19.png|center|300]]

Nella logica del primo ordine, in aggiunta alle $\alpha$-formule e $\beta$-formule, abbiamo anche le formule che coinvolgono i quantificatori, come ad esempio $\forall x P(x)$ e $\exists x P(x)$. Vediamo quali sono le regole di estensione per i quantificatori.

![[LRL/img/img20.png|center|300]]

Sebbene siano identiche, manca ancora qualcosa, ma prima vediamo il significato della regola.

La prima regola afferma che se è vero che la proprità P vale per ogni elemento del dominio, allora prendiamo un elemento $a$ che appartenga al dominio per il quale la proprità P è vera.

La seconda regola afferma che se è vero che esiste un elemento del dominio per il quale la proprietà P vale, allora prendiamo un elemento $a$ appartenente al dominio che faccia risultare vera la proprità. Cosa manca?
Quando incontriamo una formula del tipo $\exists x P(x)$ e aggiungiamo $P(a)$ al tableau, questo è legittimo, ma cosa succede se più avanti troviamo una formula del tipo $\exists x Q(x)$? Possiamo aggiungere al tableau $Q(a)$? No, perché non è detto che l'elemento per cui vale la proprietà $Q$ sia lo stesso per cui vale la proprietà $P$, quinid in questo caso dobbiamo scegliere un nuovo elemento, diciamo $b$. 
Vale lo stesso anche se più avanti incontriamo una formula del tipo $\forall x Q(x)$? No, perché appunto $Q$ vale per ogni elemento del dominio, quindi anche per $a$. Riassumiamo le regole in questo modo:

Dobbiamo anche stabilire delle regole per
----
# Elementi del Prolog

## Termini
- Atomi: nomi che iniziano con la lettera **minuscola**, o scritti tra virgolette o numeri preceduti da caratteri ^9b41e5
	- andrea
	- ' Corso di prolog '
	- c1p8
- Numeri
	- 12345
- Variabili: nomi che iniziano con lettera **maiuscola** o con **underscore**:
	- Tizio
	- \_andrea
	- \_
- Termini composti
	- somma(1, 2, X)
	- 1+2

## Predicati
Sono espressi tramite la notazione f$(t_1, \dots, t_n)$, dove f è un [[#^9b41e5|atomo]] che prende il nome di funtore, mentre  $t_1, \dots, t_n$ sono gli argomenti e sono dei termini

## Clausole
Le clausole si suddividono in fatti e regole

### Regole
Ad esempio:
- grandparent(X, Y):- 
	- parent(X, Z), 
	- parent(Z, Y).

Le regole hanno la seguente sintassi:
- head :-
	- body
Affinché la head sia vera devono essere veri tutti i predicati che compongono il body. I predicati nel body sono separati da una virgola per indicare l'**and** e/o da un punto e virgola per indicare l'**or**.
Ogni regola termina con il punto

### Fatti
Un fatto è un predicato seguito da un punto e può essere composto da più termini:
- parent(ben, jim).
- amico(fratello(alice, X), bob).

## Programma logico
È costituito da un insieme di regole/fatti e risponde a delle query con true o false e assegna dei valori alle variabili.

**Es. Programma famiglia**
{fatti}
parent(anne, bill). 
parent(anne, charlie). 
parent(bill, donnie). 

{regole}
grandparent(X,Y):- 
parent(X,Z), 
parent(Z,Y).

- Query: ?- parent(anne, bill)
- Risposta: true

- Query: ?- parent(anne, X)
- Risposta: X = bill, X = charlie

Durante l'esecuzione Prolog cerca nel proprio database di regole e fatti, quelli che soddisfano la nostra query, istanziando le variabili. Una variabile una volta istanziata (**UNIFICATA**), non può assumere un secondo valore
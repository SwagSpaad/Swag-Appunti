----
# Modello Relazionale

Il modello relazionale è stato proposto da E.F Codd nel 1970 per favorire **l'indipendenza dei dati**, fu reso disponibile in **DBMS** nel 1981 dato che non fu facile implementare l'indipendenza con efficienza e affidabilità.
Si basa sul concetto matematico di *relazione* che hanno naturale rappresentazione per mezzo di **tabelle**

# Relazione matematica

- *$D_{1}, ...., D_{n}$* : insiemi anche non distinti detti **domini**
- il **prodotto cartesiano** *$D_{1}, \times \ .... \ \times, D_{n}$* è definito come: $$L'insieme \ di \ tutte \ le \ n-tuple \ (d_{1},...d_{n}) \ tali \ che \ d_{1} \in D_{1},....,d_{n} \in D_{n}$$
- Una **relazione matematica** su *$D_{1},....,D_{n}$* è un sottoinsieme di $D_{1} \times .... \times D_{n}$
- $D_{1},....D_{n}$ sono i **domini della relazione**
Quindi una **relazione** è un *insieme*:
1. Non ordinato fra le n-tuple;
2. Le n-tuple sono distinte
3. Ciascuna n-tupla è ordinata: l'i-esimo valore proviene dall'i-esimo dominio

*EX*:
$$Partite \subseteq string \ \times \ string \ \times \ int \ \times \ int   $$

| Juve  | Lazio | 3   | 1   |
| ----- | ----- | --- | --- |
| Lazio | Milan | 2   | 0   |
| Juve  | Roma  | 0   | 2   |
| Roma  | Milan | 0   | 2   |

La struttura è **posizionale** e ciascuno dei domini ha due **ruoli** diversi, distinguibili attraverso la posizione

# Struttura non posizionale

Se a ciascun dominio si associa un nome (**attributo**), che ne descrive il "ruolo", la struttura diviene non posizionale. 

| Casa  | Fuori | Reti Casa | Reti Fuori |
| ----- | ----- | --------- | ---------- |
| Juve  | Lazio | 3         | 1          |
| Lazio | Milan | 2         | 0          |
| Juve  | Roma  | 0         | 2          |
| Roma  | Milan | 0         | 2          | 

# Collezione di funzioni

Per meglio catturare il concetto di relazione del modello relazionale definiamo:
- $X=\{A_{1},....,A_{n}\}$: insieme *non ordinato* di **attributi**  
- $DOM=X \rightarrow D$: funzione che associa ad un **attributo** il corrispondente dominio.
- Una **ennupla** o **tupla** è una funzione *t* che associa ad ogni $A \in X$ **valore del dominio**
- $t[A]$ denota il valore della ennupla $t$ sull'**attributo** $A$

# Modello relazionale

*EX*:

Relazione rappresentata attraverso una tabella:

| Nome  | Cognome | Matricola | Voto medio |
| ----- | ------- | --------- | ---------- |
| Mario | Rossi   | 1         | 24         |
| Luigi | Bianchi | 2         | 28         |
| Rosa  | Rossa   | 3         | 26         |

# Modello relazionale: notazione

**Notazione**:
Se $t$ è una tupla su $X$ e $A$ è un **attributo**, con $A \in X$ allora $t[A]$ indica un valore di $t$ su $A$ 

*EX*:
Se $t$ è la prima tupla allora...$$t[Cognome] \rightarrow \text{'Rossi'}$$

| Nome  | Cognome | Matricola | Voto medio |
| ----- | ------- | --------- | ---------- |
| Mario | Rossi   | 1         | 24         |
| Luigi | Bianchi | 2         | 28         |
| Rosa  | Rossa   | 3         | 26         |

# Tabelle e relazioni

Una tabella rappresenta una relazione se
- I valori di ogni *colonna* sono fra loro *omogenei*
- Le *righe* sono *diverse* fra loro
- Le *intestazioni* delle *colonne* sono diverse fra loro
In una *tabella* che rappresenta una *relazione*:
- L'ordinamento tra le righe è irrilevante
- L'ordinamento tra le colonne è irrilevante

*EX*:

- Ordine non rilevante:

| Nome  | Cognome | Matricola | Voto medio |
| ----- | ------- | --------- | ---------- |
| Mario | Rossi   | 1         | 24         |
| Luigi | Bianchi | 2         | 28         |
| Rosa  | Rossa   | 3         | 26         | 

- No due righe uguali:

| Nome  | Cognome | Matricola | Voto medio |
| ----- | ------- | --------- | ---------- |
| Mario | Rossi   | 1         | 24         |
| Luigi | Bianchi | 2         | 28         |
| Rosa  | Rossa   | 3         | 26         |
| Mario | Rossi   | 1         | 24         |

- No dati non omogenei:

| Nome  | Cognome | Matricola | Voto medio |
| ----- | ------- | --------- | ---------- |
| Rossi | Mario   | 1         | 24         |
| Luigi | 2       | Biachi    | 28         |
| 26    | Rossa   | 3         | Rosa       |

# Modelli basati sui valori

I riferimenti fra dati di relazioni diverse sono rappresentati per mezzo di valori dei domini che compaiono nelle ennuple

| Studenti | Matricola | Nome  | Cognome | Data di nascita |
| -------- | --------- | ----- | ------- | --------------- |
|          | 6554      | Mario | Rossi   | 05/12/1978      |
|          | 8765      | Paolo | NERI    | 03/11/1976      |
|          | 9283      | Luisa | Verdi   | 12/11/1979      |
|          | 3456      | Maria | Rossi   | 01/02/1978      |


| Esami | Studenti | Voto | Corso |
| ----- | -------- | ---- | ----- |
|       | 3456     | 30   | 04    |
|       | 3456     | 24   | 02    |
|       | 9283     | 28   | 01    |
|       | 6554     | 26   | 01    |


| Corsi | Codice | Titolo  | Docente |
| ----- | ------ | ------- | ------- |
|       | 01     | Analisi | Mario   |
|       | 02     | Chimica | Bruni   |
|       | 04     | Chimica | Verdi   |

#### Relazioni:

Studenti $\rightarrow$ Esami
Corso $\rightarrow$ Docente

# Vantaggi della struttura basata sui valori

- Indipendenza dalle strutture fisiche che possono cambiare dinamicamente
- Si rappresenta solo ciò che è rilevante dal punto di vista dell'applicazione
- L'utente finale vede gli stessi dati dei programmatori
- I dati solo trasferibili più facilmente da un sistema ad un altro
- I puntatori sono direzionali

# Modello relazionale: Definizioni

- **Schema di relazioni**:
	Un nome $R$ con un insime di attributi $A_{1},....,A_{n}$;$$R(A_{1},....,A_{n})$$
- **Schema di base di dati**
	Insieme di schemi di relazione:$$R=\{R_{1}(X_{1}),....,R_{k}(X_{k})\}$$

# Schema di relazione e di base di dati:

- **Schema di relazione** 
	*Studenti (Matricola, Cognome, Nome, Data di Nascita)*
- **Schema di basi di dati**
	*Studenti (Matricola, Cognome, Nome, Data di Nascita)*
	*Esami (Matricola, Voto, Corso)*
	*Corso (Codice, Titolo, Docente)*

# Modello di relazioni

- (Istanza di) **relazione** su uno schema $R(X)$:
	Insieme $x$ di ennuple su $X$ 
- (Istanza di) **base di dati** su uno schema $R=\{R_{1}(X_{1}),....,R_{n}(X_{n})\}$:
	Insieme di relazioni $r=\{r_{1},....,r_{n}\}$

# Relazioni su singoli attributi

**Studenti**:

| Matricola | Cognome | Nome  | Data di Nascita |
| --------- | ------- | ----- | --------------- |
| 6554      | Rossi   | Mario | 05/12/1978      |
| 8765      | Neri    | Paolo | 03/11/1976      |
| 9283      | Verdi   | Luisa | 12/11/1979      |
| 3456      | Rossi   | Maria | 01/02/1978                |

**Studenti lavoratori**:

| Matricola |
| --------- |
| 6554      |
| 3456          |

# Strutture nidificate

#### Da Filippo, Via Roma 2, Roma
*Ricevute Fiscali*:

|     |           |       |
| --- | --------- | ----- |
| 3   | Coperti   | 3,00 |
| 2   | Antipasti | 6,20  |
| 3   | Primi     | 12,00 |
| 2   | Bistecche | 18,00 |
|     |           |       |
|     | Totale    | 39,20 |

|     |           |       |
| --- | --------- | ----- |
| 2   | Coperti   | 2,00  |
| 2   | Antipasti | 7,00  |
| 2   | Primi     | 8,00  |
| 2   | Orate     | 20,00 |
| 2   | Caffè     | 2,00  |
|     |           |       |
|     | Totale    | 39,00      |

# Rappresentazione relazionale delle struttere nidificate

**Ricevute**

| Numero | Data       | Totale |
| ------ | ---------- | ------ |
| 1235   | 12/10/2000 | 39,20  |
| 1240   | 13/10/2000 | 39       |

**Dettaglio**

| Numero | Qtà | Descrizione | 3,00  |
| ------ | --- | ----------- | ----- |
| 1235   | 3   | Coperti     | 3,00  |
| 1235   | 2   | Antipasti   | 6,20  |
| 1235   | 3   | Primi       | 12,00 |
| 1235   | 2   | Bistecche   | 18,00 |
| 1240   | 2   | Coperti     | 2,00      |

# Strutture nidificate

Abbiamo rappresentato bene tutti gli aspetti delle ricevute?
Dipende da che cosa ci interessa?
- L'ordine delle righe è rilevante? 
- Possono esistere linee ripetute in una ricevuta?

- Sono possibili rappresentazioni diverse

# Rappresentazione alternativa

**Ricevute**

| Numero | Data       | Totale |
| ------ | ---------- | ------ |
| 1235   | 12/10/2000 | 39,20  |
| 1240   | 13/10/2000 | 39     |

**Dettaglio** 

| Numero | Riga | Qtà | Descrizione | Imposto |
| ------ | ---- | --- | ----------- | ------- |
| 1235   | 1    | 3   | Coperti     | 3,00    |
| 1235   | 2    | 2   | Antipasti   | 6,20    |
| 1235   | 3    | 3   | Primi       | 12,00   |
| 1235   | 4    | 2   | Bistecche   | 18,00   |
| 1240   | 1    | 2   | Coperti     | 2,00        |

# Informazione incompleta: motivazioni

| Nome     | SecondoNome | Cognome   |
| -------- | ----------- | --------- |
| Franklin | Delano      | Roosvelt  |
| Winston  |             | Churchill |
| Charles  |             | De Gaulle |
| Josip    |             | Stalin          |

# Informazione incompleta: soluzioni?

- Non conviene usare valori del dominio
	- Potrebbero non esistere valori "non utilizzati"
	- Valori "non utilizzati" potrebbero diventare significativi
	- In fase di utilizzo sarebbe necessario ogni volta tener conto del "significato" di questi valori

# Informazione incompleta nel modello relazionale

- Si adotta una tecnica rudimentale ma efficace:
	- **Valore nullo**: denota l'assenza di un valore del dominio
- $t[A]$, per ogni attributo $A$, è un valore del dominio $dom(A)$ oppure il valore nullo $NULL$ 
- Si possono imporre restrizioni sulla presenza di valori nulli

# Troppi valori nulli

**Studenti**

| Matricola | Cognome | Nome  | Data di Nascita |
| --------- | ------- | ----- | --------------- |
| 6554      | Rossi   | Mario | 05/12/1978      |
| 9583      | Verdi   | Luisa | 12/11/1979      |
| NULL      | Rossi   | Maria | 01/02/1978                |

**Esami**

| Studenti | Voto | Corso |
| -------- | ---- | ----- |
| NULL     | 30   | NULL  |
| NULL     | 24   | 02    |
| 9283     | 28   | 01      |

**Corsi**

| Codice | Titoli  | Docente |
| ------ | ------- | ------- |
| 01     | Analisi | Mario   |
| 02     | NULL    | NULL    |
| 04     | Chimica | Verdi        |

# Tipi di valore nullo

Esistono almeno tre casi differenti:
- Valore **sconosciuto**
- Valore **inesistente**
- Valore **senza informazione**
I DBMS non distinguono i tipi di valore nullo
# ER $\rightarrow$ Modello relazionale
### Dal modello ER al modello relazionale:

- Ogni **entità** diventa una relazione (o tabella)
- Ogni **attributo** di entità divnenta un attributo di relazione, ossia una **colonna di tabella**, ereditando le caratteristiche dell'attributo da cui deriva
- **L'identificatore univoco di una entità** diventa la **chiave primaria (PK)** della relazione
- La rappresentazione di una tabella avviene tramite il suo schema: 
	$nomerelazione(nome \ attributo, \ nome attributo,... )$

![[BDC/img/img38.png|center|900]]

# ER $\rightarrow$ Modello relazionale -1:1

Dal modello ER al modello relazionale:
La **relazione 1:1** diventa un'unica relazione che contiene gli attributi della prima e della seconda entità

*EX con partecipazione obbligatoria*:

![[BDC/img/img39.png|center|900]]

*EX*:
**Direttori(Codice, Cognome, Stipendio, DipartimentoDiretto, InizioDirezione)**
**Dipartimenti(Nome, Telefono, Sede)**

![[BDC/img/img40.png|center|900]]

*EX*:

**Direttori(Codice, Cognome, Stipendio)**
**Dipartimenti(Nome, Telefono, Sede, Direttore, InizioDirezione)

![[BDC/img/img41.png|center|800]]

# ER $\rightarrow$ Modello relazionale
### Dal modello ER al modello relazionale:

- La **relazione 1:1** con una entità con *partecipazione opzionale* viene trattata come una *associazione uno a molti* scegliendo l'entità con *partecipazione opzionale come se fosse a molti*
- Se *entrambe* le entità partecipano in modo opzionale si tratta come *una associazione molti a molti*

*EX come partecipazione opzionale da un lato*

![[BDC/img/img42.png|center|800]]

*EX*:

**Professore(Matricola, Nome, Cognome)**
**Università(NomeUniversità, Città, Sede, Matricola, Data elezione)**

![[BDC/img/img43.png|center|800]]

*EX con partecipazione opzionale da entrambi i lati*:

![[BDC/img/img44.png|center|800]]

*EX*:

**Professore(Matricola , Nome, Cognome)**
**Università(NomeUniversità, Città)**
**Rettore(Matricola, NomeUniversità, Data elezione)**

![[BDC/img/img45.png|center|900]]

# ER $\rightarrow$ Modello relazionale -1:N
### Dal modello ER al modello relazione:

- La **relazione 1:N** viene rappresentata aggiungendo, agli attributi dall'entità che svolge il **ruolo a molti**, l'identificatori univoco dell'entità col ruolo a uno. Questo identificatore prende il nome di **chiave esterna (foreign key=FK)** dell'entità associata. Eventuali attributi dell'associazione vengono inseriti anch'essi nell'entità con ruolo a molti, insieme alla chiave esterna.

*EX*: Relazione 1:N

![[BDC/img/img46.png|center|800]]

*EX*: Relazione 1:N

**Persona(CodiceFiscale , Nome, Cognome, NomeComune, DataTrasferrimento)**
**Comune(NomeComune, Provincia)**

![[BDC/img/img47.png|center|800]]

*EX*: Relazione 1:N con partecipazione opzionale

![[BDC/img/img48.png|center|800]]

*EX*: Relazione 1:N

**Studente(Matricola, Nome, Cognome)**
**Facoltà(NomeFacoltà, Città)**
**Laurea(Matricola, NomeFacoltà, DataLaurea)

![[BDC/img/img49.png|center|800]]

*EX*: Relazione 1:N

**Studente(Matricola, Nome, Cognome, NomeFacoltà*, DataLaurea*)**
**Facoltà(NomeFacoltà, Città)**

![[BDC/img/img50.png|center|800]]

La **relazione ricorsiva 1:N** oltre che con due relazioni è traducibile con una sola relazione che contiene due volte l'attributo identificatore, una volta come chiave esterna con un nome che riflette il ruolo dell'entità



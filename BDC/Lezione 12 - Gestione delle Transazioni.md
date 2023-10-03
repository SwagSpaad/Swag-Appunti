
*DEF*:
**Transazione**
- Parte di programma caratterizzata da un inizio(**begin-transaction**, *start transaction* in SQL), una fine(**end-transaction**, non esplicita in SQL) e al cui interno deve essere eseguito una e una sola volta uno dei seguenti comandi
	- **Commit work** per terminare correttamente
	- **Rollback work** per abortire la transazione

- Un **sistema transazionale** (**OLTP - Online Transaction Processing**) è in grado di definire ed eseguire transazioni per conto di un certo numero di applicazioni concorrenti
Javascript è un linguaggio sviluppato per rendere interattive le pagine web. 
Javascript è: 
- **Dinamico**: non è compilato e gira su VM
- **Loosely typed**: non bisogna specificare il tipo di una variabile
- **Case-sensitive**

# Transpilers
Sono utilizzati per tradurre linguaggi differenti in una versione specifica di JS. Utili nel caso in cui utilizziamo codice alternativo, ma dobbiamo supportare anche browser più vecchi

# Polyfill
Sono librerie JS che aggiunge funzionalità che determinati browser (quelli più vecchi) non supportano, come API o funzioni moderne. 

Ad esempio supponiamo di voler utilizzare `Array.prototype.includes` introdotto in ES7 non supportato da Internet Explorer.

```JS
if (!Array.prototype.includes) {
  Array.prototype.includes = function(searchElement) {
    return this.indexOf(searchElement) !== -1;
  };
}

```

Quindi se il browser non lo supporta, definiamo manualmente il comportamento.

# Garbage collector
Algoritmo che capisce gli oggetti non più raggiungibili dallo script e rilascia la memoria.

# Metodo
In javascript sono delle funzioni associate ad un oggetto e utilizza i dati dell'oggetto che chiama il metodo. 

In HTTP i metodi più comuni sono GET e POST. Il metodo GET permette di ottenere il contenuto di una risorsa indicata nell'endpoint. Il metodo POST viene utilizzato per inviare informazioni al server, come ad esempio i dati di un form.

# Strict mode
Si attiva utilizzando `"use strict";` e attiva una modalità più "rigorosa", rendendo errori degli errori che normalmente JavaScript ignora o gestisce male, come ad esempio la dichiarazione di variabili senza let/var/const. 
Utile per avere un codice più sicuro, pulito e debug-friendly.

# Scope
Lo scope è la visibilità di una variabile, ovvero la porzione di codice in cui possiamo utilizzare il nome della variabile. 

Le variabili definite all'interno di una funzione hanno lo scope relativo al blocco funzionale.
Le variabili definite fuori da ogni funzione, hanno scope globale e sono visibili ad ogni altro js nella pagina.

# Parametri mancanti
I parametri mancanti nelle funzioni in javascript sono impostati ad undefined. 
```JS
function somma(a, b) {
  let somma = a + b; //b è undefined
  // int + undefined = NaN (Not a Number)
  return somma;
}

s = somma(3); //il risultato e NaN
```
Possono essere utilizzati valori di default per i parametri per evitare questo comportamento. 

# Arrow function 
È una sintassi compatta per scrivere funzioni. 
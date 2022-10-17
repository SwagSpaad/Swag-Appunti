# Domande che possono capitare all'esame
## Quanti tipi di variabili si possono usare?
- Globali
- Locali
- Parametri
- Campi
## A cosa serve l'interfaccia in Java?
Forniscono un vocabolario unificante per interagire con oggetti aventi implementazioni differenti (cioè basati su classi diverse)

## Quanti tipi si conoscono in Java?
- Primitivi:
	- int
	- string
	- float
	- double
	- boolean
- Oggetti:
	- classi
	- interfacce

# Concetti della programmazione Object-Oriented
- **Cos'è un oggetto?** Un oggetto è un pacchetto software di stati e comportamenti correlati.Gli oggetti software sono spesso utilizzati per modellare gli oggetti della vita reale che puoi trovare ogni giorno
- **Cos'è una classe?** Una classe è lo schema o prototipo da cui sono creati gli oggetti
- **Cos'è l'ereditarietà?** L'ereditarietà fornisce un meccanismo potente e naturale per organizzare e strutturare software
- **Cos'è un interfaccia?** L'interfaccia è un "contratto" tra una classe e il mondo esterno. Quando una classe implementa un'interfaccia, promette di fornire il comportamento pubblicato da quell'interfaccia.
- **Cos'è un pacchetto?** Un pacchetto è uno spazio dei nomi che serve ad organizzare classi e interfaccie in maniera logica. Inserire il codice nei pacchetti semplifica la gestione di progetti software di grandi dimensioni

## Oggetto
Gli oggetti sono la chiave per capire la tecnologia object-oriented. Ci sono vari esempi di oggetti nella vita reale: un cane, la scrivania, etc...
Gli oggetti condividono due caratteristiche:
- stati (state)
- comportamenti (behavior)
Identificare lo stato e il comportamento di un oggetto nella vita reale è una grande idea per iniziare a pensare in termini di OOP

![[LMP/img/img3.png|center|500]]

Gli oggetti software sono concettualmente simili agli oggetti nella vita reale: anche loro sono composti da stati e comportamenti. Un oggetto software salva i suoi stati nei **fields** (campi o variabili in qualche linguaggio di programmazione) ed espone i suoi comportamenti tramite i **methods** (metodi o funzioni in qualche linguaggio di programmazione). I metodi operano sullo stato interno di un oggetto e servono come il meccanismo primario per la comunicazione object-to-object.
Nascondere lo stato interno e richiedere che tutte le interazioni vengano eseguite attraverso i metodi di un oggetto è noto come incapsulamento dei dati (**data encapsulation**), un principio fondamentale della programmazione orientata agli oggetti
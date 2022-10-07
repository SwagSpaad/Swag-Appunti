# Lezione 1
## Introduzione al corso. Introduzione sui sistemi operativi. Struttura della memoria. Struttura di I/O.
---
## I sistemi operativi
Un sistema operativo è un software che gestisce l'hardware di un computer  fornendo un interfaccia di comunicazione tra l'utente e il computer. Nel tempo sono stati progettati diverse tipologie di sistemi operativi, come quello per mainframe, progettato per ottimizzare l'utilizzo dell'hardware; i sistemi time-sharing, nati per consentire l'uso del calcolatore a più utenti in contemporanea ecc.  
Un sistema operativo è un software molto complesso e quindi è realizzato in moduli, ciascuno dei quali svolge un compito ben preciso.  
Un sistema di elaborazione può essere seguito nel seguente modo:  
![[SOR/img/img0.png]]  
- L'hardware, costituito dai componenti fisici del computer, fornisce le risorse di calcolo di base al sistema.  
Il sistema operativo gestisce e controlla le risorse hardware, necessarie per l'esecuzione di un programma, coordinando il suo utilizzo tra le varie applicazioni degli utenti.
## Funzionamento di un computer
Un moderno computer è costituito da uno o più processori e da un numero di controller di dispositivi (in celeste nella foto) connessi mediante bus di sistema comune che forniscono l’accesso alla memoria condivisa.  
![[img1.png]]  
- Un controller è un modulo elettronico programmabile. Ogni controller gestisce un particolare dispositivo.  
- I processori e i controller dei dispositivi sono in grado di eseguire istruzioni in parallelo, competendo tra loro per accedere alla memoria. Per garantire un accesso ordinato alla memoria, nell'hardware del computer è presente un dispositivo che sincronizza l'accesso ad essa.
- Al momento dell'accensione del computer, il processore inizia a eseguire istruzioni a partire da un indirizzo predefinito. A tale indirizzo è mappato un chip di memoria EEPROM (Electrically Erasable Programmable Read-Only Memory), una memoria programmabile cancellabile elettricamente che può essere riscritta più volte pur mantenendo i dati in modo permanente. In questo chip è memorizzato il firmware del calcolatore, noto con il termine UEFI (che di recente ha sostituito il BIOS), il quale è costituito da una serie di routine che inizializzano e testano le componenti hardware. Inoltre nel firmaware è memorizzato l'indirizzo del disco in cui è presente un programma che carica il kernel del sistema operativo. Il kernel, una volta in esecuzione, legge i file di configurazione del sistema e avvia un certo numero di programmi di sistema
/*
 * Un processo genera due figli P1 e P2. 
 * P1 esegue un ciclo indeterminato durante il quale genera casualmente interi compresi tra 0 e 100
 * P1 comunica ad ogni iterazione il numero al padre se e solo se è dispari
 * P2 esegue un ciclo indeterminato durante il quale genera casualmente interi compresi tra 0 e 100
 * P2 comunica ad ogni iterazione il numero al padre se e solo se è pari
 * Il padre per ogni coppia di numeri che riceve dai figli ne fa la somma e la stampa
 * Il programma deve terminare quando la somma dei due numeri ricevuti supera 190 e invia un segnale di terminazione a ciascuno dei figli
*/

#include <signal.h>
#include <stdio.h> 
#include <stdlib.h>
#include <time.h>
#include <unistd.h> 

#define STDIN 0
#define STDOUT 1
#define PIPE_RD 0 
#define PIPE_WR 1

int main(){
  
}
